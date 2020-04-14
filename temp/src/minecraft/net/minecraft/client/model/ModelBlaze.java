package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBlaze extends ModelBase {
   private ModelRenderer[] field_78106_a = new ModelRenderer[12];
   private ModelRenderer field_78105_b;

   public ModelBlaze() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_78106_a.length; ++lvt_1_1_) {
         this.field_78106_a[lvt_1_1_] = new ModelRenderer(this, 0, 16);
         this.field_78106_a[lvt_1_1_].func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
      }

      this.field_78105_b = new ModelRenderer(this, 0, 0);
      this.field_78105_b.func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
      this.field_78105_b.func_78785_a(p_78088_7_);

      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_78106_a.length; ++lvt_8_1_) {
         this.field_78106_a[lvt_8_1_].func_78785_a(p_78088_7_);
      }

   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      float lvt_8_1_ = p_78087_3_ * 3.1415927F * -0.1F;

      int lvt_9_3_;
      for(lvt_9_3_ = 0; lvt_9_3_ < 4; ++lvt_9_3_) {
         this.field_78106_a[lvt_9_3_].field_78797_d = -2.0F + MathHelper.func_76134_b(((float)(lvt_9_3_ * 2) + p_78087_3_) * 0.25F);
         this.field_78106_a[lvt_9_3_].field_78800_c = MathHelper.func_76134_b(lvt_8_1_) * 9.0F;
         this.field_78106_a[lvt_9_3_].field_78798_e = MathHelper.func_76126_a(lvt_8_1_) * 9.0F;
         ++lvt_8_1_;
      }

      lvt_8_1_ = 0.7853982F + p_78087_3_ * 3.1415927F * 0.03F;

      for(lvt_9_3_ = 4; lvt_9_3_ < 8; ++lvt_9_3_) {
         this.field_78106_a[lvt_9_3_].field_78797_d = 2.0F + MathHelper.func_76134_b(((float)(lvt_9_3_ * 2) + p_78087_3_) * 0.25F);
         this.field_78106_a[lvt_9_3_].field_78800_c = MathHelper.func_76134_b(lvt_8_1_) * 7.0F;
         this.field_78106_a[lvt_9_3_].field_78798_e = MathHelper.func_76126_a(lvt_8_1_) * 7.0F;
         ++lvt_8_1_;
      }

      lvt_8_1_ = 0.47123894F + p_78087_3_ * 3.1415927F * -0.05F;

      for(lvt_9_3_ = 8; lvt_9_3_ < 12; ++lvt_9_3_) {
         this.field_78106_a[lvt_9_3_].field_78797_d = 11.0F + MathHelper.func_76134_b(((float)lvt_9_3_ * 1.5F + p_78087_3_) * 0.5F);
         this.field_78106_a[lvt_9_3_].field_78800_c = MathHelper.func_76134_b(lvt_8_1_) * 5.0F;
         this.field_78106_a[lvt_9_3_].field_78798_e = MathHelper.func_76126_a(lvt_8_1_) * 5.0F;
         ++lvt_8_1_;
      }

      this.field_78105_b.field_78796_g = p_78087_4_ / 57.295776F;
      this.field_78105_b.field_78795_f = p_78087_5_ / 57.295776F;
   }
}
