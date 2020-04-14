package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEnderMite extends ModelBase {
   private static final int[][] field_178716_a = new int[][]{{4, 3, 2}, {6, 4, 5}, {3, 3, 1}, {1, 2, 1}};
   private static final int[][] field_178714_b = new int[][]{{0, 0}, {0, 5}, {0, 14}, {0, 18}};
   private static final int field_178715_c;
   private final ModelRenderer[] field_178713_d;

   public ModelEnderMite() {
      this.field_178713_d = new ModelRenderer[field_178715_c];
      float lvt_1_1_ = -3.5F;

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_178713_d.length; ++lvt_2_1_) {
         this.field_178713_d[lvt_2_1_] = new ModelRenderer(this, field_178714_b[lvt_2_1_][0], field_178714_b[lvt_2_1_][1]);
         this.field_178713_d[lvt_2_1_].func_78789_a((float)field_178716_a[lvt_2_1_][0] * -0.5F, 0.0F, (float)field_178716_a[lvt_2_1_][2] * -0.5F, field_178716_a[lvt_2_1_][0], field_178716_a[lvt_2_1_][1], field_178716_a[lvt_2_1_][2]);
         this.field_178713_d[lvt_2_1_].func_78793_a(0.0F, (float)(24 - field_178716_a[lvt_2_1_][1]), lvt_1_1_);
         if (lvt_2_1_ < this.field_178713_d.length - 1) {
            lvt_1_1_ += (float)(field_178716_a[lvt_2_1_][2] + field_178716_a[lvt_2_1_ + 1][2]) * 0.5F;
         }
      }

   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_178713_d.length; ++lvt_8_1_) {
         this.field_178713_d[lvt_8_1_].func_78785_a(p_78088_7_);
      }

   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_178713_d.length; ++lvt_8_1_) {
         this.field_178713_d[lvt_8_1_].field_78796_g = MathHelper.func_76134_b(p_78087_3_ * 0.9F + (float)lvt_8_1_ * 0.15F * 3.1415927F) * 3.1415927F * 0.01F * (float)(1 + Math.abs(lvt_8_1_ - 2));
         this.field_178713_d[lvt_8_1_].field_78800_c = MathHelper.func_76126_a(p_78087_3_ * 0.9F + (float)lvt_8_1_ * 0.15F * 3.1415927F) * 3.1415927F * 0.1F * (float)Math.abs(lvt_8_1_ - 2);
      }

   }

   static {
      field_178715_c = field_178716_a.length;
   }
}
