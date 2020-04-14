package net.minecraft.util;

import org.apache.commons.lang3.Validate;

public class ResourceLocation {
   protected final String field_110626_a;
   protected final String field_110625_b;

   protected ResourceLocation(int p_i45928_1_, String... p_i45928_2_) {
      this.field_110626_a = org.apache.commons.lang3.StringUtils.isEmpty(p_i45928_2_[0]) ? "minecraft" : p_i45928_2_[0].toLowerCase();
      this.field_110625_b = p_i45928_2_[1];
      Validate.notNull(this.field_110625_b);
   }

   public ResourceLocation(String p_i1293_1_) {
      this(0, func_177516_a(p_i1293_1_));
   }

   public ResourceLocation(String p_i1292_1_, String p_i1292_2_) {
      this(0, p_i1292_1_, p_i1292_2_);
   }

   protected static String[] func_177516_a(String p_177516_0_) {
      String[] lvt_1_1_ = new String[]{null, p_177516_0_};
      int lvt_2_1_ = p_177516_0_.indexOf(58);
      if (lvt_2_1_ >= 0) {
         lvt_1_1_[1] = p_177516_0_.substring(lvt_2_1_ + 1, p_177516_0_.length());
         if (lvt_2_1_ > 1) {
            lvt_1_1_[0] = p_177516_0_.substring(0, lvt_2_1_);
         }
      }

      return lvt_1_1_;
   }

   public String func_110623_a() {
      return this.field_110625_b;
   }

   public String func_110624_b() {
      return this.field_110626_a;
   }

   public String toString() {
      return this.field_110626_a + ':' + this.field_110625_b;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof ResourceLocation)) {
         return false;
      } else {
         ResourceLocation lvt_2_1_ = (ResourceLocation)p_equals_1_;
         return this.field_110626_a.equals(lvt_2_1_.field_110626_a) && this.field_110625_b.equals(lvt_2_1_.field_110625_b);
      }
   }

   public int hashCode() {
      return 31 * this.field_110626_a.hashCode() + this.field_110625_b.hashCode();
   }
}
