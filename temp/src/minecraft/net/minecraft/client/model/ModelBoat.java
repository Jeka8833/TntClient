package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelBoat extends ModelBase {
   public ModelRenderer[] field_78103_a = new ModelRenderer[5];

   public ModelBoat() {
      this.field_78103_a[0] = new ModelRenderer(this, 0, 8);
      this.field_78103_a[1] = new ModelRenderer(this, 0, 0);
      this.field_78103_a[2] = new ModelRenderer(this, 0, 0);
      this.field_78103_a[3] = new ModelRenderer(this, 0, 0);
      this.field_78103_a[4] = new ModelRenderer(this, 0, 0);
      int lvt_1_1_ = 24;
      int lvt_2_1_ = 6;
      int lvt_3_1_ = 20;
      int lvt_4_1_ = 4;
      this.field_78103_a[0].func_78790_a((float)(-lvt_1_1_ / 2), (float)(-lvt_3_1_ / 2 + 2), -3.0F, lvt_1_1_, lvt_3_1_ - 4, 4, 0.0F);
      this.field_78103_a[0].func_78793_a(0.0F, (float)lvt_4_1_, 0.0F);
      this.field_78103_a[1].func_78790_a((float)(-lvt_1_1_ / 2 + 2), (float)(-lvt_2_1_ - 1), -1.0F, lvt_1_1_ - 4, lvt_2_1_, 2, 0.0F);
      this.field_78103_a[1].func_78793_a((float)(-lvt_1_1_ / 2 + 1), (float)lvt_4_1_, 0.0F);
      this.field_78103_a[2].func_78790_a((float)(-lvt_1_1_ / 2 + 2), (float)(-lvt_2_1_ - 1), -1.0F, lvt_1_1_ - 4, lvt_2_1_, 2, 0.0F);
      this.field_78103_a[2].func_78793_a((float)(lvt_1_1_ / 2 - 1), (float)lvt_4_1_, 0.0F);
      this.field_78103_a[3].func_78790_a((float)(-lvt_1_1_ / 2 + 2), (float)(-lvt_2_1_ - 1), -1.0F, lvt_1_1_ - 4, lvt_2_1_, 2, 0.0F);
      this.field_78103_a[3].func_78793_a(0.0F, (float)lvt_4_1_, (float)(-lvt_3_1_ / 2 + 1));
      this.field_78103_a[4].func_78790_a((float)(-lvt_1_1_ / 2 + 2), (float)(-lvt_2_1_ - 1), -1.0F, lvt_1_1_ - 4, lvt_2_1_, 2, 0.0F);
      this.field_78103_a[4].func_78793_a(0.0F, (float)lvt_4_1_, (float)(lvt_3_1_ / 2 - 1));
      this.field_78103_a[0].field_78795_f = 1.5707964F;
      this.field_78103_a[1].field_78796_g = 4.712389F;
      this.field_78103_a[2].field_78796_g = 1.5707964F;
      this.field_78103_a[3].field_78796_g = 3.1415927F;
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      for(int lvt_8_1_ = 0; lvt_8_1_ < 5; ++lvt_8_1_) {
         this.field_78103_a[lvt_8_1_].func_78785_a(p_78088_7_);
      }

   }
}
