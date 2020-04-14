package net.minecraft.client.model;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;

public class ModelDragon extends ModelBase {
   private ModelRenderer field_78221_a;
   private ModelRenderer field_78219_b;
   private ModelRenderer field_78220_c;
   private ModelRenderer field_78217_d;
   private ModelRenderer field_78218_e;
   private ModelRenderer field_78215_f;
   private ModelRenderer field_78216_g;
   private ModelRenderer field_78226_h;
   private ModelRenderer field_78227_i;
   private ModelRenderer field_78224_j;
   private ModelRenderer field_78225_k;
   private ModelRenderer field_78222_l;
   private float field_78223_m;

   public ModelDragon(float p_i46360_1_) {
      this.field_78090_t = 256;
      this.field_78089_u = 256;
      this.func_78085_a("body.body", 0, 0);
      this.func_78085_a("wing.skin", -56, 88);
      this.func_78085_a("wingtip.skin", -56, 144);
      this.func_78085_a("rearleg.main", 0, 0);
      this.func_78085_a("rearfoot.main", 112, 0);
      this.func_78085_a("rearlegtip.main", 196, 0);
      this.func_78085_a("head.upperhead", 112, 30);
      this.func_78085_a("wing.bone", 112, 88);
      this.func_78085_a("head.upperlip", 176, 44);
      this.func_78085_a("jaw.jaw", 176, 65);
      this.func_78085_a("frontleg.main", 112, 104);
      this.func_78085_a("wingtip.bone", 112, 136);
      this.func_78085_a("frontfoot.main", 144, 104);
      this.func_78085_a("neck.box", 192, 104);
      this.func_78085_a("frontlegtip.main", 226, 138);
      this.func_78085_a("body.scale", 220, 53);
      this.func_78085_a("head.scale", 0, 0);
      this.func_78085_a("neck.scale", 48, 0);
      this.func_78085_a("head.nostril", 112, 0);
      float lvt_2_1_ = -16.0F;
      this.field_78221_a = new ModelRenderer(this, "head");
      this.field_78221_a.func_78786_a("upperlip", -6.0F, -1.0F, -8.0F + lvt_2_1_, 12, 5, 16);
      this.field_78221_a.func_78786_a("upperhead", -8.0F, -8.0F, 6.0F + lvt_2_1_, 16, 16, 16);
      this.field_78221_a.field_78809_i = true;
      this.field_78221_a.func_78786_a("scale", -5.0F, -12.0F, 12.0F + lvt_2_1_, 2, 4, 6);
      this.field_78221_a.func_78786_a("nostril", -5.0F, -3.0F, -6.0F + lvt_2_1_, 2, 2, 4);
      this.field_78221_a.field_78809_i = false;
      this.field_78221_a.func_78786_a("scale", 3.0F, -12.0F, 12.0F + lvt_2_1_, 2, 4, 6);
      this.field_78221_a.func_78786_a("nostril", 3.0F, -3.0F, -6.0F + lvt_2_1_, 2, 2, 4);
      this.field_78220_c = new ModelRenderer(this, "jaw");
      this.field_78220_c.func_78793_a(0.0F, 4.0F, 8.0F + lvt_2_1_);
      this.field_78220_c.func_78786_a("jaw", -6.0F, 0.0F, -16.0F, 12, 4, 16);
      this.field_78221_a.func_78792_a(this.field_78220_c);
      this.field_78219_b = new ModelRenderer(this, "neck");
      this.field_78219_b.func_78786_a("box", -5.0F, -5.0F, -5.0F, 10, 10, 10);
      this.field_78219_b.func_78786_a("scale", -1.0F, -9.0F, -3.0F, 2, 4, 6);
      this.field_78217_d = new ModelRenderer(this, "body");
      this.field_78217_d.func_78793_a(0.0F, 4.0F, 8.0F);
      this.field_78217_d.func_78786_a("body", -12.0F, 0.0F, -16.0F, 24, 24, 64);
      this.field_78217_d.func_78786_a("scale", -1.0F, -6.0F, -10.0F, 2, 6, 12);
      this.field_78217_d.func_78786_a("scale", -1.0F, -6.0F, 10.0F, 2, 6, 12);
      this.field_78217_d.func_78786_a("scale", -1.0F, -6.0F, 30.0F, 2, 6, 12);
      this.field_78225_k = new ModelRenderer(this, "wing");
      this.field_78225_k.func_78793_a(-12.0F, 5.0F, 2.0F);
      this.field_78225_k.func_78786_a("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
      this.field_78225_k.func_78786_a("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.field_78222_l = new ModelRenderer(this, "wingtip");
      this.field_78222_l.func_78793_a(-56.0F, 0.0F, 0.0F);
      this.field_78222_l.func_78786_a("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
      this.field_78222_l.func_78786_a("skin", -56.0F, 0.0F, 2.0F, 56, 0, 56);
      this.field_78225_k.func_78792_a(this.field_78222_l);
      this.field_78215_f = new ModelRenderer(this, "frontleg");
      this.field_78215_f.func_78793_a(-12.0F, 20.0F, 2.0F);
      this.field_78215_f.func_78786_a("main", -4.0F, -4.0F, -4.0F, 8, 24, 8);
      this.field_78226_h = new ModelRenderer(this, "frontlegtip");
      this.field_78226_h.func_78793_a(0.0F, 20.0F, -1.0F);
      this.field_78226_h.func_78786_a("main", -3.0F, -1.0F, -3.0F, 6, 24, 6);
      this.field_78215_f.func_78792_a(this.field_78226_h);
      this.field_78224_j = new ModelRenderer(this, "frontfoot");
      this.field_78224_j.func_78793_a(0.0F, 23.0F, 0.0F);
      this.field_78224_j.func_78786_a("main", -4.0F, 0.0F, -12.0F, 8, 4, 16);
      this.field_78226_h.func_78792_a(this.field_78224_j);
      this.field_78218_e = new ModelRenderer(this, "rearleg");
      this.field_78218_e.func_78793_a(-16.0F, 16.0F, 42.0F);
      this.field_78218_e.func_78786_a("main", -8.0F, -4.0F, -8.0F, 16, 32, 16);
      this.field_78216_g = new ModelRenderer(this, "rearlegtip");
      this.field_78216_g.func_78793_a(0.0F, 32.0F, -4.0F);
      this.field_78216_g.func_78786_a("main", -6.0F, -2.0F, 0.0F, 12, 32, 12);
      this.field_78218_e.func_78792_a(this.field_78216_g);
      this.field_78227_i = new ModelRenderer(this, "rearfoot");
      this.field_78227_i.func_78793_a(0.0F, 31.0F, 4.0F);
      this.field_78227_i.func_78786_a("main", -9.0F, 0.0F, -20.0F, 18, 6, 24);
      this.field_78216_g.func_78792_a(this.field_78227_i);
   }

   public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
      this.field_78223_m = p_78086_4_;
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      GlStateManager.func_179094_E();
      EntityDragon lvt_8_1_ = (EntityDragon)p_78088_1_;
      float lvt_9_1_ = lvt_8_1_.field_70991_bC + (lvt_8_1_.field_70988_bD - lvt_8_1_.field_70991_bC) * this.field_78223_m;
      this.field_78220_c.field_78795_f = (float)(Math.sin((double)(lvt_9_1_ * 3.1415927F * 2.0F)) + 1.0D) * 0.2F;
      float lvt_10_1_ = (float)(Math.sin((double)(lvt_9_1_ * 3.1415927F * 2.0F - 1.0F)) + 1.0D);
      lvt_10_1_ = (lvt_10_1_ * lvt_10_1_ * 1.0F + lvt_10_1_ * 2.0F) * 0.05F;
      GlStateManager.func_179109_b(0.0F, lvt_10_1_ - 2.0F, -3.0F);
      GlStateManager.func_179114_b(lvt_10_1_ * 2.0F, 1.0F, 0.0F, 0.0F);
      float lvt_11_1_ = -30.0F;
      float lvt_13_1_ = 0.0F;
      float lvt_14_1_ = 1.5F;
      double[] lvt_15_1_ = lvt_8_1_.func_70974_a(6, this.field_78223_m);
      float lvt_16_1_ = this.func_78214_a(lvt_8_1_.func_70974_a(5, this.field_78223_m)[0] - lvt_8_1_.func_70974_a(10, this.field_78223_m)[0]);
      float lvt_17_1_ = this.func_78214_a(lvt_8_1_.func_70974_a(5, this.field_78223_m)[0] + (double)(lvt_16_1_ / 2.0F));
      lvt_11_1_ += 2.0F;
      float lvt_18_1_ = lvt_9_1_ * 3.1415927F * 2.0F;
      lvt_11_1_ = 20.0F;
      float lvt_12_1_ = -12.0F;

      float lvt_21_2_;
      for(int lvt_19_1_ = 0; lvt_19_1_ < 5; ++lvt_19_1_) {
         double[] lvt_20_1_ = lvt_8_1_.func_70974_a(5 - lvt_19_1_, this.field_78223_m);
         lvt_21_2_ = (float)Math.cos((double)((float)lvt_19_1_ * 0.45F + lvt_18_1_)) * 0.15F;
         this.field_78219_b.field_78796_g = this.func_78214_a(lvt_20_1_[0] - lvt_15_1_[0]) * 3.1415927F / 180.0F * lvt_14_1_;
         this.field_78219_b.field_78795_f = lvt_21_2_ + (float)(lvt_20_1_[1] - lvt_15_1_[1]) * 3.1415927F / 180.0F * lvt_14_1_ * 5.0F;
         this.field_78219_b.field_78808_h = -this.func_78214_a(lvt_20_1_[0] - (double)lvt_17_1_) * 3.1415927F / 180.0F * lvt_14_1_;
         this.field_78219_b.field_78797_d = lvt_11_1_;
         this.field_78219_b.field_78798_e = lvt_12_1_;
         this.field_78219_b.field_78800_c = lvt_13_1_;
         lvt_11_1_ = (float)((double)lvt_11_1_ + Math.sin((double)this.field_78219_b.field_78795_f) * 10.0D);
         lvt_12_1_ = (float)((double)lvt_12_1_ - Math.cos((double)this.field_78219_b.field_78796_g) * Math.cos((double)this.field_78219_b.field_78795_f) * 10.0D);
         lvt_13_1_ = (float)((double)lvt_13_1_ - Math.sin((double)this.field_78219_b.field_78796_g) * Math.cos((double)this.field_78219_b.field_78795_f) * 10.0D);
         this.field_78219_b.func_78785_a(p_78088_7_);
      }

      this.field_78221_a.field_78797_d = lvt_11_1_;
      this.field_78221_a.field_78798_e = lvt_12_1_;
      this.field_78221_a.field_78800_c = lvt_13_1_;
      double[] lvt_19_2_ = lvt_8_1_.func_70974_a(0, this.field_78223_m);
      this.field_78221_a.field_78796_g = this.func_78214_a(lvt_19_2_[0] - lvt_15_1_[0]) * 3.1415927F / 180.0F * 1.0F;
      this.field_78221_a.field_78808_h = -this.func_78214_a(lvt_19_2_[0] - (double)lvt_17_1_) * 3.1415927F / 180.0F * 1.0F;
      this.field_78221_a.func_78785_a(p_78088_7_);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(-lvt_16_1_ * lvt_14_1_ * 1.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.func_179109_b(0.0F, -1.0F, 0.0F);
      this.field_78217_d.field_78808_h = 0.0F;
      this.field_78217_d.func_78785_a(p_78088_7_);

      for(int lvt_20_2_ = 0; lvt_20_2_ < 2; ++lvt_20_2_) {
         GlStateManager.func_179089_o();
         lvt_21_2_ = lvt_9_1_ * 3.1415927F * 2.0F;
         this.field_78225_k.field_78795_f = 0.125F - (float)Math.cos((double)lvt_21_2_) * 0.2F;
         this.field_78225_k.field_78796_g = 0.25F;
         this.field_78225_k.field_78808_h = (float)(Math.sin((double)lvt_21_2_) + 0.125D) * 0.8F;
         this.field_78222_l.field_78808_h = -((float)(Math.sin((double)(lvt_21_2_ + 2.0F)) + 0.5D)) * 0.75F;
         this.field_78218_e.field_78795_f = 1.0F + lvt_10_1_ * 0.1F;
         this.field_78216_g.field_78795_f = 0.5F + lvt_10_1_ * 0.1F;
         this.field_78227_i.field_78795_f = 0.75F + lvt_10_1_ * 0.1F;
         this.field_78215_f.field_78795_f = 1.3F + lvt_10_1_ * 0.1F;
         this.field_78226_h.field_78795_f = -0.5F - lvt_10_1_ * 0.1F;
         this.field_78224_j.field_78795_f = 0.75F + lvt_10_1_ * 0.1F;
         this.field_78225_k.func_78785_a(p_78088_7_);
         this.field_78215_f.func_78785_a(p_78088_7_);
         this.field_78218_e.func_78785_a(p_78088_7_);
         GlStateManager.func_179152_a(-1.0F, 1.0F, 1.0F);
         if (lvt_20_2_ == 0) {
            GlStateManager.func_179107_e(1028);
         }
      }

      GlStateManager.func_179121_F();
      GlStateManager.func_179107_e(1029);
      GlStateManager.func_179129_p();
      float lvt_20_3_ = -((float)Math.sin((double)(lvt_9_1_ * 3.1415927F * 2.0F))) * 0.0F;
      lvt_18_1_ = lvt_9_1_ * 3.1415927F * 2.0F;
      lvt_11_1_ = 10.0F;
      lvt_12_1_ = 60.0F;
      lvt_13_1_ = 0.0F;
      lvt_15_1_ = lvt_8_1_.func_70974_a(11, this.field_78223_m);

      for(int lvt_21_3_ = 0; lvt_21_3_ < 12; ++lvt_21_3_) {
         lvt_19_2_ = lvt_8_1_.func_70974_a(12 + lvt_21_3_, this.field_78223_m);
         lvt_20_3_ = (float)((double)lvt_20_3_ + Math.sin((double)((float)lvt_21_3_ * 0.45F + lvt_18_1_)) * 0.05000000074505806D);
         this.field_78219_b.field_78796_g = (this.func_78214_a(lvt_19_2_[0] - lvt_15_1_[0]) * lvt_14_1_ + 180.0F) * 3.1415927F / 180.0F;
         this.field_78219_b.field_78795_f = lvt_20_3_ + (float)(lvt_19_2_[1] - lvt_15_1_[1]) * 3.1415927F / 180.0F * lvt_14_1_ * 5.0F;
         this.field_78219_b.field_78808_h = this.func_78214_a(lvt_19_2_[0] - (double)lvt_17_1_) * 3.1415927F / 180.0F * lvt_14_1_;
         this.field_78219_b.field_78797_d = lvt_11_1_;
         this.field_78219_b.field_78798_e = lvt_12_1_;
         this.field_78219_b.field_78800_c = lvt_13_1_;
         lvt_11_1_ = (float)((double)lvt_11_1_ + Math.sin((double)this.field_78219_b.field_78795_f) * 10.0D);
         lvt_12_1_ = (float)((double)lvt_12_1_ - Math.cos((double)this.field_78219_b.field_78796_g) * Math.cos((double)this.field_78219_b.field_78795_f) * 10.0D);
         lvt_13_1_ = (float)((double)lvt_13_1_ - Math.sin((double)this.field_78219_b.field_78796_g) * Math.cos((double)this.field_78219_b.field_78795_f) * 10.0D);
         this.field_78219_b.func_78785_a(p_78088_7_);
      }

      GlStateManager.func_179121_F();
   }

   private float func_78214_a(double p_78214_1_) {
      while(p_78214_1_ >= 180.0D) {
         p_78214_1_ -= 360.0D;
      }

      while(p_78214_1_ < -180.0D) {
         p_78214_1_ += 360.0D;
      }

      return (float)p_78214_1_;
   }
}
