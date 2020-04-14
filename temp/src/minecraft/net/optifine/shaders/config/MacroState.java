package net.optifine.shaders.config;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.src.Config;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.IExpressionResolver;
import net.optifine.expr.ParseException;

public class MacroState {
   private boolean active = true;
   private Deque<Boolean> dequeState = new ArrayDeque();
   private Deque<Boolean> dequeResolved = new ArrayDeque();
   private Map<String, String> mapMacroValues = new HashMap();
   private static final Pattern PATTERN_DIRECTIVE = Pattern.compile("\\s*#\\s*(\\w+)\\s*(.*)");
   private static final Pattern PATTERN_DEFINED = Pattern.compile("defined\\s+(\\w+)");
   private static final Pattern PATTERN_DEFINED_FUNC = Pattern.compile("defined\\s*\\(\\s*(\\w+)\\s*\\)");
   private static final Pattern PATTERN_MACRO = Pattern.compile("(\\w+)");
   private static final String DEFINE = "define";
   private static final String UNDEF = "undef";
   private static final String IFDEF = "ifdef";
   private static final String IFNDEF = "ifndef";
   private static final String IF = "if";
   private static final String ELSE = "else";
   private static final String ELIF = "elif";
   private static final String ENDIF = "endif";
   private static final List<String> MACRO_NAMES = Arrays.asList("define", "undef", "ifdef", "ifndef", "if", "else", "elif", "endif");

   public boolean processLine(String line) {
      Matcher m = PATTERN_DIRECTIVE.matcher(line);
      if (!m.matches()) {
         return this.active;
      } else {
         String name = m.group(1);
         String param = m.group(2);
         int posComment = param.indexOf("//");
         if (posComment >= 0) {
            param = param.substring(0, posComment);
         }

         boolean activePrev = this.active;
         this.processMacro(name, param);
         this.active = !this.dequeState.contains(Boolean.FALSE);
         return this.active || activePrev;
      }
   }

   public static boolean isMacroLine(String line) {
      Matcher m = PATTERN_DIRECTIVE.matcher(line);
      if (!m.matches()) {
         return false;
      } else {
         String name = m.group(1);
         return MACRO_NAMES.contains(name);
      }
   }

   private void processMacro(String name, String param) {
      StringTokenizer tok = new StringTokenizer(param, " \t");
      String macro = tok.hasMoreTokens() ? tok.nextToken() : "";
      String rest = tok.hasMoreTokens() ? tok.nextToken("").trim() : "";
      if (name.equals("define")) {
         this.mapMacroValues.put(macro, rest);
      } else if (name.equals("undef")) {
         this.mapMacroValues.remove(macro);
      } else {
         boolean lastState;
         if (name.equals("ifdef")) {
            lastState = this.mapMacroValues.containsKey(macro);
            this.dequeState.add(lastState);
            this.dequeResolved.add(lastState);
         } else if (name.equals("ifndef")) {
            lastState = !this.mapMacroValues.containsKey(macro);
            this.dequeState.add(lastState);
            this.dequeResolved.add(lastState);
         } else if (name.equals("if")) {
            lastState = this.eval(param);
            this.dequeState.add(lastState);
            this.dequeResolved.add(lastState);
         } else if (!this.dequeState.isEmpty()) {
            boolean lastResolved;
            boolean act;
            if (name.equals("elif")) {
               lastState = (Boolean)this.dequeState.removeLast();
               lastResolved = (Boolean)this.dequeResolved.removeLast();
               if (lastResolved) {
                  this.dequeState.add(false);
                  this.dequeResolved.add(lastResolved);
               } else {
                  act = this.eval(param);
                  this.dequeState.add(act);
                  this.dequeResolved.add(act);
               }

            } else if (name.equals("else")) {
               lastState = (Boolean)this.dequeState.removeLast();
               lastResolved = (Boolean)this.dequeResolved.removeLast();
               act = !lastResolved;
               this.dequeState.add(act);
               this.dequeResolved.add(true);
            } else if (name.equals("endif")) {
               this.dequeState.removeLast();
               this.dequeResolved.removeLast();
            }
         }
      }
   }

   private boolean eval(String str) {
      Matcher md = PATTERN_DEFINED.matcher(str);
      str = md.replaceAll("defined_$1");
      Matcher mdf = PATTERN_DEFINED_FUNC.matcher(str);
      str = mdf.replaceAll("defined_$1");
      boolean replaced = false;
      int count = 0;

      label68:
      do {
         replaced = false;
         Matcher mmn = PATTERN_MACRO.matcher(str);

         String match;
         char ch;
         do {
            do {
               do {
                  if (!mmn.find()) {
                     continue label68;
                  }

                  match = mmn.group();
               } while(match.length() <= 0);

               ch = match.charAt(0);
            } while(!Character.isLetter(ch) && ch != '_');
         } while(!this.mapMacroValues.containsKey(match));

         String val = (String)this.mapMacroValues.get(match);
         if (val == null) {
            val = "1";
         }

         int start = mmn.start();
         int end = mmn.end();
         str = str.substring(0, start) + " " + val + " " + str.substring(end);
         replaced = true;
         ++count;
      } while(replaced && count < 100);

      if (count >= 100) {
         Config.warn("Too many iterations: " + count + ", when resolving: " + str);
         return true;
      } else {
         try {
            IExpressionResolver er = new MacroExpressionResolver(this.mapMacroValues);
            ExpressionParser ep = new ExpressionParser(er);
            IExpression expr = ep.parse(str);
            if (expr.getExpressionType() == ExpressionType.BOOL) {
               IExpressionBool exprBool = (IExpressionBool)expr;
               boolean ret = exprBool.eval();
               return ret;
            } else if (expr.getExpressionType() == ExpressionType.FLOAT) {
               IExpressionFloat exprFloat = (IExpressionFloat)expr;
               float val = exprFloat.eval();
               boolean ret = val != 0.0F;
               return ret;
            } else {
               throw new ParseException("Not a boolean or float expression: " + expr.getExpressionType());
            }
         } catch (ParseException var12) {
            Config.warn("Invalid macro expression: " + str);
            Config.warn("Error: " + var12.getMessage());
            return false;
         }
      }
   }
}
