package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelSquid extends ModelBase {
   ModelRenderer field_78202_a;
   ModelRenderer[] field_78201_b = new ModelRenderer[8];

   public ModelSquid() {
      int lvt_1_1_ = -16;
      this.field_78202_a = new ModelRenderer(this, 0, 0);
      this.field_78202_a.func_78789_a(-6.0F, -8.0F, -6.0F, 12, 16, 12);
      ModelRenderer var10000 = this.field_78202_a;
      var10000.field_78797_d += (float)(24 + lvt_1_1_);

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_78201_b.length; ++lvt_2_1_) {
         this.field_78201_b[lvt_2_1_] = new ModelRenderer(this, 48, 0);
         double lvt_3_1_ = (double)lvt_2_1_ * 3.141592653589793D * 2.0D / (double)this.field_78201_b.length;
         float lvt_5_1_ = (float)Math.cos(lvt_3_1_) * 5.0F;
         float lvt_6_1_ = (float)Math.sin(lvt_3_1_) * 5.0F;
         this.field_78201_b[lvt_2_1_].func_78789_a(-1.0F, 0.0F, -1.0F, 2, 18, 2);
         this.field_78201_b[lvt_2_1_].field_78800_c = lvt_5_1_;
         this.field_78201_b[lvt_2_1_].field_78798_e = lvt_6_1_;
         this.field_78201_b[lvt_2_1_].field_78797_d = (float)(31 + lvt_1_1_);
         lvt_3_1_ = (double)lvt_2_1_ * 3.141592653589793D * -2.0D / (double)this.field_78201_b.length + 1.5707963267948966D;
         this.field_78201_b[lvt_2_1_].field_78796_g = (float)lvt_3_1_;
      }

   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      ModelRenderer[] lvt_8_1_ = this.field_78201_b;
      int lvt_9_1_ = lvt_8_1_.length;

      for(int lvt_10_1_ = 0; lvt_10_1_ < lvt_9_1_; ++lvt_10_1_) {
         ModelRenderer lvt_11_1_ = lvt_8_1_[lvt_10_1_];
         lvt_11_1_.field_78795_f = p_78087_3_;
      }

   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
      this.field_78202_a.func_78785_a(p_78088_7_);

      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_78201_b.length; ++lvt_8_1_) {
         this.field_78201_b[lvt_8_1_].func_78785_a(p_78088_7_);
      }

   }
}
