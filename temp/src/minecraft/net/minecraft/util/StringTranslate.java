package net.minecraft.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class StringTranslate {
   private static final Pattern field_111053_a = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
   private static final Splitter field_135065_b = Splitter.on('=').limit(2);
   private static StringTranslate field_74817_a = new StringTranslate();
   private final Map<String, String> field_74816_c = Maps.newHashMap();
   private long field_150511_e;

   public StringTranslate() {
      try {
         InputStream lvt_1_1_ = StringTranslate.class.getResourceAsStream("/assets/minecraft/lang/en_US.lang");
         Iterator lvt_2_1_ = IOUtils.readLines(lvt_1_1_, Charsets.UTF_8).iterator();

         while(lvt_2_1_.hasNext()) {
            String lvt_3_1_ = (String)lvt_2_1_.next();
            if (!lvt_3_1_.isEmpty() && lvt_3_1_.charAt(0) != '#') {
               String[] lvt_4_1_ = (String[])Iterables.toArray(field_135065_b.split(lvt_3_1_), String.class);
               if (lvt_4_1_ != null && lvt_4_1_.length == 2) {
                  String lvt_5_1_ = lvt_4_1_[0];
                  String lvt_6_1_ = field_111053_a.matcher(lvt_4_1_[1]).replaceAll("%$1s");
                  this.field_74816_c.put(lvt_5_1_, lvt_6_1_);
               }
            }
         }

         this.field_150511_e = System.currentTimeMillis();
      } catch (IOException var7) {
      }

   }

   static StringTranslate func_74808_a() {
      return field_74817_a;
   }

   public static synchronized void func_135063_a(Map<String, String> p_135063_0_) {
      field_74817_a.field_74816_c.clear();
      field_74817_a.field_74816_c.putAll(p_135063_0_);
      field_74817_a.field_150511_e = System.currentTimeMillis();
   }

   public synchronized String func_74805_b(String p_74805_1_) {
      return this.func_135064_c(p_74805_1_);
   }

   public synchronized String func_74803_a(String p_74803_1_, Object... p_74803_2_) {
      String lvt_3_1_ = this.func_135064_c(p_74803_1_);

      try {
         return String.format(lvt_3_1_, p_74803_2_);
      } catch (IllegalFormatException var5) {
         return "Format error: " + lvt_3_1_;
      }
   }

   private String func_135064_c(String p_135064_1_) {
      String lvt_2_1_ = (String)this.field_74816_c.get(p_135064_1_);
      return lvt_2_1_ == null ? p_135064_1_ : lvt_2_1_;
   }

   public synchronized boolean func_94520_b(String p_94520_1_) {
      return this.field_74816_c.containsKey(p_94520_1_);
   }

   public long func_150510_c() {
      return this.field_150511_e;
   }
}
