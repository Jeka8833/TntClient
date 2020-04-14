package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;

public class ModelMagmaCube extends ModelBase {
   ModelRenderer[] field_78109_a = new ModelRenderer[8];
   ModelRenderer field_78108_b;

   public ModelMagmaCube() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_78109_a.length; ++lvt_1_1_) {
         int lvt_2_1_ = 0;
         int lvt_3_1_ = lvt_1_1_;
         if (lvt_1_1_ == 2) {
            lvt_2_1_ = 24;
            lvt_3_1_ = 10;
         } else if (lvt_1_1_ == 3) {
            lvt_2_1_ = 24;
            lvt_3_1_ = 19;
         }

         this.field_78109_a[lvt_1_1_] = new ModelRenderer(this, lvt_2_1_, lvt_3_1_);
         this.field_78109_a[lvt_1_1_].func_78789_a(-4.0F, (float)(16 + lvt_1_1_), -4.0F, 8, 1, 8);
      }

      this.field_78108_b = new ModelRenderer(this, 0, 16);
      this.field_78108_b.func_78789_a(-2.0F, 18.0F, -2.0F, 4, 4, 4);
   }

   public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
      EntityMagmaCube lvt_5_1_ = (EntityMagmaCube)p_78086_1_;
      float lvt_6_1_ = lvt_5_1_.field_70812_c + (lvt_5_1_.field_70811_b - lvt_5_1_.field_70812_c) * p_78086_4_;
      if (lvt_6_1_ < 0.0F) {
         lvt_6_1_ = 0.0F;
      }

      for(int lvt_7_1_ = 0; lvt_7_1_ < this.field_78109_a.length; ++lvt_7_1_) {
         this.field_78109_a[lvt_7_1_].field_78797_d = (float)(-(4 - lvt_7_1_)) * lvt_6_1_ * 1.7F;
      }

   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
      this.field_78108_b.func_78785_a(p_78088_7_);

      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_78109_a.length; ++lvt_8_1_) {
         this.field_78109_a[lvt_8_1_].func_78785_a(p_78088_7_);
      }

   }
}
