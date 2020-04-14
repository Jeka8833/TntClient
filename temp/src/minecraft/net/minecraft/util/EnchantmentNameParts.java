package net.minecraft.util;

import java.util.Random;

public class EnchantmentNameParts {
   private static final EnchantmentNameParts field_148338_a = new EnchantmentNameParts();
   private Random field_148336_b = new Random();
   private String[] field_148337_c = "the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale ".split(" ");

   private EnchantmentNameParts() {
   }

   public static EnchantmentNameParts func_178176_a() {
      return field_148338_a;
   }

   public String func_148334_a() {
      int lvt_1_1_ = this.field_148336_b.nextInt(2) + 3;
      String lvt_2_1_ = "";

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_1_1_; ++lvt_3_1_) {
         if (lvt_3_1_ > 0) {
            lvt_2_1_ = lvt_2_1_ + " ";
         }

         lvt_2_1_ = lvt_2_1_ + this.field_148337_c[this.field_148336_b.nextInt(this.field_148337_c.length)];
      }

      return lvt_2_1_;
   }

   public void func_148335_a(long p_148335_1_) {
      this.field_148336_b.setSeed(p_148335_1_);
   }
}
