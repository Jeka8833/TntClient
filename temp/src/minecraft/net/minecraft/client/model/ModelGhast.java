package net.minecraft.client.model;

import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelGhast extends ModelBase {
   ModelRenderer field_78128_a;
   ModelRenderer[] field_78127_b = new ModelRenderer[9];

   public ModelGhast() {
      int lvt_1_1_ = -16;
      this.field_78128_a = new ModelRenderer(this, 0, 0);
      this.field_78128_a.func_78789_a(-8.0F, -8.0F, -8.0F, 16, 16, 16);
      ModelRenderer var10000 = this.field_78128_a;
      var10000.field_78797_d += (float)(24 + lvt_1_1_);
      Random lvt_2_1_ = new Random(1660L);

      for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_78127_b.length; ++lvt_3_1_) {
         this.field_78127_b[lvt_3_1_] = new ModelRenderer(this, 0, 0);
         float lvt_4_1_ = (((float)(lvt_3_1_ % 3) - (float)(lvt_3_1_ / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
         float lvt_5_1_ = ((float)(lvt_3_1_ / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
         int lvt_6_1_ = lvt_2_1_.nextInt(7) + 8;
         this.field_78127_b[lvt_3_1_].func_78789_a(-1.0F, 0.0F, -1.0F, 2, lvt_6_1_, 2);
         this.field_78127_b[lvt_3_1_].field_78800_c = lvt_4_1_;
         this.field_78127_b[lvt_3_1_].field_78798_e = lvt_5_1_;
         this.field_78127_b[lvt_3_1_].field_78797_d = (float)(31 + lvt_1_1_);
      }

   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_78127_b.length; ++lvt_8_1_) {
         this.field_78127_b[lvt_8_1_].field_78795_f = 0.2F * MathHelper.func_76126_a(p_78087_3_ * 0.3F + (float)lvt_8_1_) + 0.4F;
      }

   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(0.0F, 0.6F, 0.0F);
      this.field_78128_a.func_78785_a(p_78088_7_);
      ModelRenderer[] lvt_8_1_ = this.field_78127_b;
      int lvt_9_1_ = lvt_8_1_.length;

      for(int lvt_10_1_ = 0; lvt_10_1_ < lvt_9_1_; ++lvt_10_1_) {
         ModelRenderer lvt_11_1_ = lvt_8_1_[lvt_10_1_];
         lvt_11_1_.func_78785_a(p_78088_7_);
      }

      GlStateManager.func_179121_F();
   }
}
