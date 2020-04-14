package net.minecraft.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatComponentTranslation extends ChatComponentStyle {
   private final String field_150276_d;
   private final Object[] field_150277_e;
   private final Object field_150274_f = new Object();
   private long field_150275_g = -1L;
   List<IChatComponent> field_150278_b = Lists.newArrayList();
   public static final Pattern field_150279_c = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

   public ChatComponentTranslation(String p_i45160_1_, Object... p_i45160_2_) {
      this.field_150276_d = p_i45160_1_;
      this.field_150277_e = p_i45160_2_;
      Object[] lvt_3_1_ = p_i45160_2_;
      int lvt_4_1_ = p_i45160_2_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         Object lvt_6_1_ = lvt_3_1_[lvt_5_1_];
         if (lvt_6_1_ instanceof IChatComponent) {
            ((IChatComponent)lvt_6_1_).func_150256_b().func_150221_a(this.func_150256_b());
         }
      }

   }

   synchronized void func_150270_g() {
      synchronized(this.field_150274_f) {
         long lvt_2_1_ = StatCollector.func_150827_a();
         if (lvt_2_1_ == this.field_150275_g) {
            return;
         }

         this.field_150275_g = lvt_2_1_;
         this.field_150278_b.clear();
      }

      try {
         this.func_150269_b(StatCollector.func_74838_a(this.field_150276_d));
      } catch (ChatComponentTranslationFormatException var6) {
         this.field_150278_b.clear();

         try {
            this.func_150269_b(StatCollector.func_150826_b(this.field_150276_d));
         } catch (ChatComponentTranslationFormatException var5) {
            throw var6;
         }
      }

   }

   protected void func_150269_b(String p_150269_1_) {
      boolean lvt_2_1_ = false;
      Matcher lvt_3_1_ = field_150279_c.matcher(p_150269_1_);
      int lvt_4_1_ = 0;
      int lvt_5_1_ = 0;

      try {
         int lvt_7_1_;
         for(; lvt_3_1_.find(lvt_5_1_); lvt_5_1_ = lvt_7_1_) {
            int lvt_6_1_ = lvt_3_1_.start();
            lvt_7_1_ = lvt_3_1_.end();
            if (lvt_6_1_ > lvt_5_1_) {
               ChatComponentText lvt_8_1_ = new ChatComponentText(String.format(p_150269_1_.substring(lvt_5_1_, lvt_6_1_)));
               lvt_8_1_.func_150256_b().func_150221_a(this.func_150256_b());
               this.field_150278_b.add(lvt_8_1_);
            }

            String lvt_8_2_ = lvt_3_1_.group(2);
            String lvt_9_1_ = p_150269_1_.substring(lvt_6_1_, lvt_7_1_);
            if ("%".equals(lvt_8_2_) && "%%".equals(lvt_9_1_)) {
               ChatComponentText lvt_10_1_ = new ChatComponentText("%");
               lvt_10_1_.func_150256_b().func_150221_a(this.func_150256_b());
               this.field_150278_b.add(lvt_10_1_);
            } else {
               if (!"s".equals(lvt_8_2_)) {
                  throw new ChatComponentTranslationFormatException(this, "Unsupported format: '" + lvt_9_1_ + "'");
               }

               String lvt_10_2_ = lvt_3_1_.group(1);
               int lvt_11_1_ = lvt_10_2_ != null ? Integer.parseInt(lvt_10_2_) - 1 : lvt_4_1_++;
               if (lvt_11_1_ < this.field_150277_e.length) {
                  this.field_150278_b.add(this.func_150272_a(lvt_11_1_));
               }
            }
         }

         if (lvt_5_1_ < p_150269_1_.length()) {
            ChatComponentText lvt_6_2_ = new ChatComponentText(String.format(p_150269_1_.substring(lvt_5_1_)));
            lvt_6_2_.func_150256_b().func_150221_a(this.func_150256_b());
            this.field_150278_b.add(lvt_6_2_);
         }

      } catch (IllegalFormatException var12) {
         throw new ChatComponentTranslationFormatException(this, var12);
      }
   }

   private IChatComponent func_150272_a(int p_150272_1_) {
      if (p_150272_1_ >= this.field_150277_e.length) {
         throw new ChatComponentTranslationFormatException(this, p_150272_1_);
      } else {
         Object lvt_2_1_ = this.field_150277_e[p_150272_1_];
         Object lvt_3_2_;
         if (lvt_2_1_ instanceof IChatComponent) {
            lvt_3_2_ = (IChatComponent)lvt_2_1_;
         } else {
            lvt_3_2_ = new ChatComponentText(lvt_2_1_ == null ? "null" : lvt_2_1_.toString());
            ((IChatComponent)lvt_3_2_).func_150256_b().func_150221_a(this.func_150256_b());
         }

         return (IChatComponent)lvt_3_2_;
      }
   }

   public IChatComponent func_150255_a(ChatStyle p_150255_1_) {
      super.func_150255_a(p_150255_1_);
      Object[] lvt_2_1_ = this.field_150277_e;
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         Object lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         if (lvt_5_1_ instanceof IChatComponent) {
            ((IChatComponent)lvt_5_1_).func_150256_b().func_150221_a(this.func_150256_b());
         }
      }

      if (this.field_150275_g > -1L) {
         Iterator lvt_2_2_ = this.field_150278_b.iterator();

         while(lvt_2_2_.hasNext()) {
            IChatComponent lvt_3_2_ = (IChatComponent)lvt_2_2_.next();
            lvt_3_2_.func_150256_b().func_150221_a(p_150255_1_);
         }
      }

      return this;
   }

   public Iterator<IChatComponent> iterator() {
      this.func_150270_g();
      return Iterators.concat(func_150262_a(this.field_150278_b), func_150262_a(this.field_150264_a));
   }

   public String func_150261_e() {
      this.func_150270_g();
      StringBuilder lvt_1_1_ = new StringBuilder();
      Iterator lvt_2_1_ = this.field_150278_b.iterator();

      while(lvt_2_1_.hasNext()) {
         IChatComponent lvt_3_1_ = (IChatComponent)lvt_2_1_.next();
         lvt_1_1_.append(lvt_3_1_.func_150261_e());
      }

      return lvt_1_1_.toString();
   }

   public ChatComponentTranslation func_150259_f() {
      Object[] lvt_1_1_ = new Object[this.field_150277_e.length];

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_150277_e.length; ++lvt_2_1_) {
         if (this.field_150277_e[lvt_2_1_] instanceof IChatComponent) {
            lvt_1_1_[lvt_2_1_] = ((IChatComponent)this.field_150277_e[lvt_2_1_]).func_150259_f();
         } else {
            lvt_1_1_[lvt_2_1_] = this.field_150277_e[lvt_2_1_];
         }
      }

      ChatComponentTranslation lvt_2_2_ = new ChatComponentTranslation(this.field_150276_d, lvt_1_1_);
      lvt_2_2_.func_150255_a(this.func_150256_b().func_150232_l());
      Iterator lvt_3_1_ = this.func_150253_a().iterator();

      while(lvt_3_1_.hasNext()) {
         IChatComponent lvt_4_1_ = (IChatComponent)lvt_3_1_.next();
         lvt_2_2_.func_150257_a(lvt_4_1_.func_150259_f());
      }

      return lvt_2_2_;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof ChatComponentTranslation)) {
         return false;
      } else {
         ChatComponentTranslation lvt_2_1_ = (ChatComponentTranslation)p_equals_1_;
         return Arrays.equals(this.field_150277_e, lvt_2_1_.field_150277_e) && this.field_150276_d.equals(lvt_2_1_.field_150276_d) && super.equals(p_equals_1_);
      }
   }

   public int hashCode() {
      int lvt_1_1_ = super.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_150276_d.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + Arrays.hashCode(this.field_150277_e);
      return lvt_1_1_;
   }

   public String toString() {
      return "TranslatableComponent{key='" + this.field_150276_d + '\'' + ", args=" + Arrays.toString(this.field_150277_e) + ", siblings=" + this.field_150264_a + ", style=" + this.func_150256_b() + '}';
   }

   public String func_150268_i() {
      return this.field_150276_d;
   }

   public Object[] func_150271_j() {
      return this.field_150277_e;
   }
}
