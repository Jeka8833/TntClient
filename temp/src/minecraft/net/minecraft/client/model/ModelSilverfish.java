package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSilverfish extends ModelBase {
   private ModelRenderer[] field_78171_a = new ModelRenderer[7];
   private ModelRenderer[] field_78169_b;
   private float[] field_78170_c = new float[7];
   private static final int[][] field_78167_d = new int[][]{{3, 2, 2}, {4, 3, 2}, {6, 4, 3}, {3, 3, 3}, {2, 2, 3}, {2, 1, 2}, {1, 1, 2}};
   private static final int[][] field_78168_e = new int[][]{{0, 0}, {0, 4}, {0, 9}, {0, 16}, {0, 22}, {11, 0}, {13, 4}};

   public ModelSilverfish() {
      float lvt_1_1_ = -3.5F;

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_78171_a.length; ++lvt_2_1_) {
         this.field_78171_a[lvt_2_1_] = new ModelRenderer(this, field_78168_e[lvt_2_1_][0], field_78168_e[lvt_2_1_][1]);
         this.field_78171_a[lvt_2_1_].func_78789_a((float)field_78167_d[lvt_2_1_][0] * -0.5F, 0.0F, (float)field_78167_d[lvt_2_1_][2] * -0.5F, field_78167_d[lvt_2_1_][0], field_78167_d[lvt_2_1_][1], field_78167_d[lvt_2_1_][2]);
         this.field_78171_a[lvt_2_1_].func_78793_a(0.0F, (float)(24 - field_78167_d[lvt_2_1_][1]), lvt_1_1_);
         this.field_78170_c[lvt_2_1_] = lvt_1_1_;
         if (lvt_2_1_ < this.field_78171_a.length - 1) {
            lvt_1_1_ += (float)(field_78167_d[lvt_2_1_][2] + field_78167_d[lvt_2_1_ + 1][2]) * 0.5F;
         }
      }

      this.field_78169_b = new ModelRenderer[3];
      this.field_78169_b[0] = new ModelRenderer(this, 20, 0);
      this.field_78169_b[0].func_78789_a(-5.0F, 0.0F, (float)field_78167_d[2][2] * -0.5F, 10, 8, field_78167_d[2][2]);
      this.field_78169_b[0].func_78793_a(0.0F, 16.0F, this.field_78170_c[2]);
      this.field_78169_b[1] = new ModelRenderer(this, 20, 11);
      this.field_78169_b[1].func_78789_a(-3.0F, 0.0F, (float)field_78167_d[4][2] * -0.5F, 6, 4, field_78167_d[4][2]);
      this.field_78169_b[1].func_78793_a(0.0F, 20.0F, this.field_78170_c[4]);
      this.field_78169_b[2] = new ModelRenderer(this, 20, 18);
      this.field_78169_b[2].func_78789_a(-3.0F, 0.0F, (float)field_78167_d[4][2] * -0.5F, 6, 5, field_78167_d[1][2]);
      this.field_78169_b[2].func_78793_a(0.0F, 19.0F, this.field_78170_c[1]);
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

      int lvt_8_2_;
      for(lvt_8_2_ = 0; lvt_8_2_ < this.field_78171_a.length; ++lvt_8_2_) {
         this.field_78171_a[lvt_8_2_].func_78785_a(p_78088_7_);
      }

      for(lvt_8_2_ = 0; lvt_8_2_ < this.field_78169_b.length; ++lvt_8_2_) {
         this.field_78169_b[lvt_8_2_].func_78785_a(p_78088_7_);
      }

   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_78171_a.length; ++lvt_8_1_) {
         this.field_78171_a[lvt_8_1_].field_78796_g = MathHelper.func_76134_b(p_78087_3_ * 0.9F + (float)lvt_8_1_ * 0.15F * 3.1415927F) * 3.1415927F * 0.05F * (float)(1 + Math.abs(lvt_8_1_ - 2));
         this.field_78171_a[lvt_8_1_].field_78800_c = MathHelper.func_76126_a(p_78087_3_ * 0.9F + (float)lvt_8_1_ * 0.15F * 3.1415927F) * 3.1415927F * 0.2F * (float)Math.abs(lvt_8_1_ - 2);
      }

      this.field_78169_b[0].field_78796_g = this.field_78171_a[2].field_78796_g;
      this.field_78169_b[1].field_78796_g = this.field_78171_a[4].field_78796_g;
      this.field_78169_b[1].field_78800_c = this.field_78171_a[4].field_78800_c;
      this.field_78169_b[2].field_78796_g = this.field_78171_a[1].field_78796_g;
      this.field_78169_b[2].field_78800_c = this.field_78171_a[1].field_78800_c;
   }
}
