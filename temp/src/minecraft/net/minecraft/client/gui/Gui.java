package net.minecraft.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class Gui {
   public static final ResourceLocation field_110325_k = new ResourceLocation("textures/gui/options_background.png");
   public static final ResourceLocation field_110323_l = new ResourceLocation("textures/gui/container/stats_icons.png");
   public static final ResourceLocation field_110324_m = new ResourceLocation("textures/gui/icons.png");
   protected float field_73735_i;

   protected void func_73730_a(int p_73730_1_, int p_73730_2_, int p_73730_3_, int p_73730_4_) {
      if (p_73730_2_ < p_73730_1_) {
         int lvt_5_1_ = p_73730_1_;
         p_73730_1_ = p_73730_2_;
         p_73730_2_ = lvt_5_1_;
      }

      func_73734_a(p_73730_1_, p_73730_3_, p_73730_2_ + 1, p_73730_3_ + 1, p_73730_4_);
   }

   protected void func_73728_b(int p_73728_1_, int p_73728_2_, int p_73728_3_, int p_73728_4_) {
      if (p_73728_3_ < p_73728_2_) {
         int lvt_5_1_ = p_73728_2_;
         p_73728_2_ = p_73728_3_;
         p_73728_3_ = lvt_5_1_;
      }

      func_73734_a(p_73728_1_, p_73728_2_ + 1, p_73728_1_ + 1, p_73728_3_, p_73728_4_);
   }

   public static void func_73734_a(int p_73734_0_, int p_73734_1_, int p_73734_2_, int p_73734_3_, int p_73734_4_) {
      int lvt_5_2_;
      if (p_73734_0_ < p_73734_2_) {
         lvt_5_2_ = p_73734_0_;
         p_73734_0_ = p_73734_2_;
         p_73734_2_ = lvt_5_2_;
      }

      if (p_73734_1_ < p_73734_3_) {
         lvt_5_2_ = p_73734_1_;
         p_73734_1_ = p_73734_3_;
         p_73734_3_ = lvt_5_2_;
      }

      float lvt_5_3_ = (float)(p_73734_4_ >> 24 & 255) / 255.0F;
      float lvt_6_1_ = (float)(p_73734_4_ >> 16 & 255) / 255.0F;
      float lvt_7_1_ = (float)(p_73734_4_ >> 8 & 255) / 255.0F;
      float lvt_8_1_ = (float)(p_73734_4_ & 255) / 255.0F;
      Tessellator lvt_9_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_10_1_ = lvt_9_1_.func_178180_c();
      GlStateManager.func_179147_l();
      GlStateManager.func_179090_x();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179131_c(lvt_6_1_, lvt_7_1_, lvt_8_1_, lvt_5_3_);
      lvt_10_1_.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      lvt_10_1_.func_181662_b((double)p_73734_0_, (double)p_73734_3_, 0.0D).func_181675_d();
      lvt_10_1_.func_181662_b((double)p_73734_2_, (double)p_73734_3_, 0.0D).func_181675_d();
      lvt_10_1_.func_181662_b((double)p_73734_2_, (double)p_73734_1_, 0.0D).func_181675_d();
      lvt_10_1_.func_181662_b((double)p_73734_0_, (double)p_73734_1_, 0.0D).func_181675_d();
      lvt_9_1_.func_78381_a();
      GlStateManager.func_179098_w();
      GlStateManager.func_179084_k();
   }

   protected void func_73733_a(int p_73733_1_, int p_73733_2_, int p_73733_3_, int p_73733_4_, int p_73733_5_, int p_73733_6_) {
      float lvt_7_1_ = (float)(p_73733_5_ >> 24 & 255) / 255.0F;
      float lvt_8_1_ = (float)(p_73733_5_ >> 16 & 255) / 255.0F;
      float lvt_9_1_ = (float)(p_73733_5_ >> 8 & 255) / 255.0F;
      float lvt_10_1_ = (float)(p_73733_5_ & 255) / 255.0F;
      float lvt_11_1_ = (float)(p_73733_6_ >> 24 & 255) / 255.0F;
      float lvt_12_1_ = (float)(p_73733_6_ >> 16 & 255) / 255.0F;
      float lvt_13_1_ = (float)(p_73733_6_ >> 8 & 255) / 255.0F;
      float lvt_14_1_ = (float)(p_73733_6_ & 255) / 255.0F;
      GlStateManager.func_179090_x();
      GlStateManager.func_179147_l();
      GlStateManager.func_179118_c();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179103_j(7425);
      Tessellator lvt_15_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_16_1_ = lvt_15_1_.func_178180_c();
      lvt_16_1_.func_181668_a(7, DefaultVertexFormats.field_181706_f);
      lvt_16_1_.func_181662_b((double)p_73733_3_, (double)p_73733_2_, (double)this.field_73735_i).func_181666_a(lvt_8_1_, lvt_9_1_, lvt_10_1_, lvt_7_1_).func_181675_d();
      lvt_16_1_.func_181662_b((double)p_73733_1_, (double)p_73733_2_, (double)this.field_73735_i).func_181666_a(lvt_8_1_, lvt_9_1_, lvt_10_1_, lvt_7_1_).func_181675_d();
      lvt_16_1_.func_181662_b((double)p_73733_1_, (double)p_73733_4_, (double)this.field_73735_i).func_181666_a(lvt_12_1_, lvt_13_1_, lvt_14_1_, lvt_11_1_).func_181675_d();
      lvt_16_1_.func_181662_b((double)p_73733_3_, (double)p_73733_4_, (double)this.field_73735_i).func_181666_a(lvt_12_1_, lvt_13_1_, lvt_14_1_, lvt_11_1_).func_181675_d();
      lvt_15_1_.func_78381_a();
      GlStateManager.func_179103_j(7424);
      GlStateManager.func_179084_k();
      GlStateManager.func_179141_d();
      GlStateManager.func_179098_w();
   }

   public void func_73732_a(FontRenderer p_73732_1_, String p_73732_2_, int p_73732_3_, int p_73732_4_, int p_73732_5_) {
      p_73732_1_.func_175063_a(p_73732_2_, (float)(p_73732_3_ - p_73732_1_.func_78256_a(p_73732_2_) / 2), (float)p_73732_4_, p_73732_5_);
   }

   public void func_73731_b(FontRenderer p_73731_1_, String p_73731_2_, int p_73731_3_, int p_73731_4_, int p_73731_5_) {
      p_73731_1_.func_175063_a(p_73731_2_, (float)p_73731_3_, (float)p_73731_4_, p_73731_5_);
   }

   public void func_73729_b(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_) {
      float lvt_7_1_ = 0.00390625F;
      float lvt_8_1_ = 0.00390625F;
      Tessellator lvt_9_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_10_1_ = lvt_9_1_.func_178180_c();
      lvt_10_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      lvt_10_1_.func_181662_b((double)(p_73729_1_ + 0), (double)(p_73729_2_ + p_73729_6_), (double)this.field_73735_i).func_181673_a((double)((float)(p_73729_3_ + 0) * lvt_7_1_), (double)((float)(p_73729_4_ + p_73729_6_) * lvt_8_1_)).func_181675_d();
      lvt_10_1_.func_181662_b((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + p_73729_6_), (double)this.field_73735_i).func_181673_a((double)((float)(p_73729_3_ + p_73729_5_) * lvt_7_1_), (double)((float)(p_73729_4_ + p_73729_6_) * lvt_8_1_)).func_181675_d();
      lvt_10_1_.func_181662_b((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + 0), (double)this.field_73735_i).func_181673_a((double)((float)(p_73729_3_ + p_73729_5_) * lvt_7_1_), (double)((float)(p_73729_4_ + 0) * lvt_8_1_)).func_181675_d();
      lvt_10_1_.func_181662_b((double)(p_73729_1_ + 0), (double)(p_73729_2_ + 0), (double)this.field_73735_i).func_181673_a((double)((float)(p_73729_3_ + 0) * lvt_7_1_), (double)((float)(p_73729_4_ + 0) * lvt_8_1_)).func_181675_d();
      lvt_9_1_.func_78381_a();
   }

   public void func_175174_a(float p_175174_1_, float p_175174_2_, int p_175174_3_, int p_175174_4_, int p_175174_5_, int p_175174_6_) {
      float lvt_7_1_ = 0.00390625F;
      float lvt_8_1_ = 0.00390625F;
      Tessellator lvt_9_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_10_1_ = lvt_9_1_.func_178180_c();
      lvt_10_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      lvt_10_1_.func_181662_b((double)(p_175174_1_ + 0.0F), (double)(p_175174_2_ + (float)p_175174_6_), (double)this.field_73735_i).func_181673_a((double)((float)(p_175174_3_ + 0) * lvt_7_1_), (double)((float)(p_175174_4_ + p_175174_6_) * lvt_8_1_)).func_181675_d();
      lvt_10_1_.func_181662_b((double)(p_175174_1_ + (float)p_175174_5_), (double)(p_175174_2_ + (float)p_175174_6_), (double)this.field_73735_i).func_181673_a((double)((float)(p_175174_3_ + p_175174_5_) * lvt_7_1_), (double)((float)(p_175174_4_ + p_175174_6_) * lvt_8_1_)).func_181675_d();
      lvt_10_1_.func_181662_b((double)(p_175174_1_ + (float)p_175174_5_), (double)(p_175174_2_ + 0.0F), (double)this.field_73735_i).func_181673_a((double)((float)(p_175174_3_ + p_175174_5_) * lvt_7_1_), (double)((float)(p_175174_4_ + 0) * lvt_8_1_)).func_181675_d();
      lvt_10_1_.func_181662_b((double)(p_175174_1_ + 0.0F), (double)(p_175174_2_ + 0.0F), (double)this.field_73735_i).func_181673_a((double)((float)(p_175174_3_ + 0) * lvt_7_1_), (double)((float)(p_175174_4_ + 0) * lvt_8_1_)).func_181675_d();
      lvt_9_1_.func_78381_a();
   }

   public void func_175175_a(int p_175175_1_, int p_175175_2_, TextureAtlasSprite p_175175_3_, int p_175175_4_, int p_175175_5_) {
      Tessellator lvt_6_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_7_1_ = lvt_6_1_.func_178180_c();
      lvt_7_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      lvt_7_1_.func_181662_b((double)(p_175175_1_ + 0), (double)(p_175175_2_ + p_175175_5_), (double)this.field_73735_i).func_181673_a((double)p_175175_3_.func_94209_e(), (double)p_175175_3_.func_94210_h()).func_181675_d();
      lvt_7_1_.func_181662_b((double)(p_175175_1_ + p_175175_4_), (double)(p_175175_2_ + p_175175_5_), (double)this.field_73735_i).func_181673_a((double)p_175175_3_.func_94212_f(), (double)p_175175_3_.func_94210_h()).func_181675_d();
      lvt_7_1_.func_181662_b((double)(p_175175_1_ + p_175175_4_), (double)(p_175175_2_ + 0), (double)this.field_73735_i).func_181673_a((double)p_175175_3_.func_94212_f(), (double)p_175175_3_.func_94206_g()).func_181675_d();
      lvt_7_1_.func_181662_b((double)(p_175175_1_ + 0), (double)(p_175175_2_ + 0), (double)this.field_73735_i).func_181673_a((double)p_175175_3_.func_94209_e(), (double)p_175175_3_.func_94206_g()).func_181675_d();
      lvt_6_1_.func_78381_a();
   }

   public static void func_146110_a(int p_146110_0_, int p_146110_1_, float p_146110_2_, float p_146110_3_, int p_146110_4_, int p_146110_5_, float p_146110_6_, float p_146110_7_) {
      float lvt_8_1_ = 1.0F / p_146110_6_;
      float lvt_9_1_ = 1.0F / p_146110_7_;
      Tessellator lvt_10_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_11_1_ = lvt_10_1_.func_178180_c();
      lvt_11_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      lvt_11_1_.func_181662_b((double)p_146110_0_, (double)(p_146110_1_ + p_146110_5_), 0.0D).func_181673_a((double)(p_146110_2_ * lvt_8_1_), (double)((p_146110_3_ + (float)p_146110_5_) * lvt_9_1_)).func_181675_d();
      lvt_11_1_.func_181662_b((double)(p_146110_0_ + p_146110_4_), (double)(p_146110_1_ + p_146110_5_), 0.0D).func_181673_a((double)((p_146110_2_ + (float)p_146110_4_) * lvt_8_1_), (double)((p_146110_3_ + (float)p_146110_5_) * lvt_9_1_)).func_181675_d();
      lvt_11_1_.func_181662_b((double)(p_146110_0_ + p_146110_4_), (double)p_146110_1_, 0.0D).func_181673_a((double)((p_146110_2_ + (float)p_146110_4_) * lvt_8_1_), (double)(p_146110_3_ * lvt_9_1_)).func_181675_d();
      lvt_11_1_.func_181662_b((double)p_146110_0_, (double)p_146110_1_, 0.0D).func_181673_a((double)(p_146110_2_ * lvt_8_1_), (double)(p_146110_3_ * lvt_9_1_)).func_181675_d();
      lvt_10_1_.func_78381_a();
   }

   public static void func_152125_a(int p_152125_0_, int p_152125_1_, float p_152125_2_, float p_152125_3_, int p_152125_4_, int p_152125_5_, int p_152125_6_, int p_152125_7_, float p_152125_8_, float p_152125_9_) {
      float lvt_10_1_ = 1.0F / p_152125_8_;
      float lvt_11_1_ = 1.0F / p_152125_9_;
      Tessellator lvt_12_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_13_1_ = lvt_12_1_.func_178180_c();
      lvt_13_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      lvt_13_1_.func_181662_b((double)p_152125_0_, (double)(p_152125_1_ + p_152125_7_), 0.0D).func_181673_a((double)(p_152125_2_ * lvt_10_1_), (double)((p_152125_3_ + (float)p_152125_5_) * lvt_11_1_)).func_181675_d();
      lvt_13_1_.func_181662_b((double)(p_152125_0_ + p_152125_6_), (double)(p_152125_1_ + p_152125_7_), 0.0D).func_181673_a((double)((p_152125_2_ + (float)p_152125_4_) * lvt_10_1_), (double)((p_152125_3_ + (float)p_152125_5_) * lvt_11_1_)).func_181675_d();
      lvt_13_1_.func_181662_b((double)(p_152125_0_ + p_152125_6_), (double)p_152125_1_, 0.0D).func_181673_a((double)((p_152125_2_ + (float)p_152125_4_) * lvt_10_1_), (double)(p_152125_3_ * lvt_11_1_)).func_181675_d();
      lvt_13_1_.func_181662_b((double)p_152125_0_, (double)p_152125_1_, 0.0D).func_181673_a((double)(p_152125_2_ * lvt_10_1_), (double)(p_152125_3_ * lvt_11_1_)).func_181675_d();
      lvt_12_1_.func_78381_a();
   }
}
