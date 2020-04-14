package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderPainting extends Render<EntityPainting> {
   private static final ResourceLocation field_110807_a = new ResourceLocation("textures/painting/paintings_kristoffer_zetterstrand.png");

   public RenderPainting(RenderManager p_i46150_1_) {
      super(p_i46150_1_);
   }

   public void func_76986_a(EntityPainting p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179137_b(p_76986_2_, p_76986_4_, p_76986_6_);
      GlStateManager.func_179114_b(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179091_B();
      this.func_180548_c(p_76986_1_);
      EntityPainting.EnumArt lvt_10_1_ = p_76986_1_.field_70522_e;
      float lvt_11_1_ = 0.0625F;
      GlStateManager.func_179152_a(lvt_11_1_, lvt_11_1_, lvt_11_1_);
      this.func_77010_a(p_76986_1_, lvt_10_1_.field_75703_B, lvt_10_1_.field_75704_C, lvt_10_1_.field_75699_D, lvt_10_1_.field_75700_E);
      GlStateManager.func_179101_C();
      GlStateManager.func_179121_F();
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityPainting p_110775_1_) {
      return field_110807_a;
   }

   private void func_77010_a(EntityPainting p_77010_1_, int p_77010_2_, int p_77010_3_, int p_77010_4_, int p_77010_5_) {
      float lvt_6_1_ = (float)(-p_77010_2_) / 2.0F;
      float lvt_7_1_ = (float)(-p_77010_3_) / 2.0F;
      float lvt_8_1_ = 0.5F;
      float lvt_9_1_ = 0.75F;
      float lvt_10_1_ = 0.8125F;
      float lvt_11_1_ = 0.0F;
      float lvt_12_1_ = 0.0625F;
      float lvt_13_1_ = 0.75F;
      float lvt_14_1_ = 0.8125F;
      float lvt_15_1_ = 0.001953125F;
      float lvt_16_1_ = 0.001953125F;
      float lvt_17_1_ = 0.7519531F;
      float lvt_18_1_ = 0.7519531F;
      float lvt_19_1_ = 0.0F;
      float lvt_20_1_ = 0.0625F;

      for(int lvt_21_1_ = 0; lvt_21_1_ < p_77010_2_ / 16; ++lvt_21_1_) {
         for(int lvt_22_1_ = 0; lvt_22_1_ < p_77010_3_ / 16; ++lvt_22_1_) {
            float lvt_23_1_ = lvt_6_1_ + (float)((lvt_21_1_ + 1) * 16);
            float lvt_24_1_ = lvt_6_1_ + (float)(lvt_21_1_ * 16);
            float lvt_25_1_ = lvt_7_1_ + (float)((lvt_22_1_ + 1) * 16);
            float lvt_26_1_ = lvt_7_1_ + (float)(lvt_22_1_ * 16);
            this.func_77008_a(p_77010_1_, (lvt_23_1_ + lvt_24_1_) / 2.0F, (lvt_25_1_ + lvt_26_1_) / 2.0F);
            float lvt_27_1_ = (float)(p_77010_4_ + p_77010_2_ - lvt_21_1_ * 16) / 256.0F;
            float lvt_28_1_ = (float)(p_77010_4_ + p_77010_2_ - (lvt_21_1_ + 1) * 16) / 256.0F;
            float lvt_29_1_ = (float)(p_77010_5_ + p_77010_3_ - lvt_22_1_ * 16) / 256.0F;
            float lvt_30_1_ = (float)(p_77010_5_ + p_77010_3_ - (lvt_22_1_ + 1) * 16) / 256.0F;
            Tessellator lvt_31_1_ = Tessellator.func_178181_a();
            WorldRenderer lvt_32_1_ = lvt_31_1_.func_178180_c();
            lvt_32_1_.func_181668_a(7, DefaultVertexFormats.field_181710_j);
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_26_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_28_1_, (double)lvt_29_1_).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_26_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_27_1_, (double)lvt_29_1_).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_25_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_27_1_, (double)lvt_30_1_).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_25_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_28_1_, (double)lvt_30_1_).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_25_1_, (double)lvt_8_1_).func_181673_a((double)lvt_9_1_, (double)lvt_11_1_).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_25_1_, (double)lvt_8_1_).func_181673_a((double)lvt_10_1_, (double)lvt_11_1_).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_26_1_, (double)lvt_8_1_).func_181673_a((double)lvt_10_1_, (double)lvt_12_1_).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_26_1_, (double)lvt_8_1_).func_181673_a((double)lvt_9_1_, (double)lvt_12_1_).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_25_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_13_1_, (double)lvt_15_1_).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_25_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_14_1_, (double)lvt_15_1_).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_25_1_, (double)lvt_8_1_).func_181673_a((double)lvt_14_1_, (double)lvt_16_1_).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_25_1_, (double)lvt_8_1_).func_181673_a((double)lvt_13_1_, (double)lvt_16_1_).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_26_1_, (double)lvt_8_1_).func_181673_a((double)lvt_13_1_, (double)lvt_15_1_).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_26_1_, (double)lvt_8_1_).func_181673_a((double)lvt_14_1_, (double)lvt_15_1_).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_26_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_14_1_, (double)lvt_16_1_).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_26_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_13_1_, (double)lvt_16_1_).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_25_1_, (double)lvt_8_1_).func_181673_a((double)lvt_18_1_, (double)lvt_19_1_).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_26_1_, (double)lvt_8_1_).func_181673_a((double)lvt_18_1_, (double)lvt_20_1_).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_26_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_17_1_, (double)lvt_20_1_).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_23_1_, (double)lvt_25_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_17_1_, (double)lvt_19_1_).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_25_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_18_1_, (double)lvt_19_1_).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_26_1_, (double)(-lvt_8_1_)).func_181673_a((double)lvt_18_1_, (double)lvt_20_1_).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_26_1_, (double)lvt_8_1_).func_181673_a((double)lvt_17_1_, (double)lvt_20_1_).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
            lvt_32_1_.func_181662_b((double)lvt_24_1_, (double)lvt_25_1_, (double)lvt_8_1_).func_181673_a((double)lvt_17_1_, (double)lvt_19_1_).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
            lvt_31_1_.func_78381_a();
         }
      }

   }

   private void func_77008_a(EntityPainting p_77008_1_, float p_77008_2_, float p_77008_3_) {
      int lvt_4_1_ = MathHelper.func_76128_c(p_77008_1_.field_70165_t);
      int lvt_5_1_ = MathHelper.func_76128_c(p_77008_1_.field_70163_u + (double)(p_77008_3_ / 16.0F));
      int lvt_6_1_ = MathHelper.func_76128_c(p_77008_1_.field_70161_v);
      EnumFacing lvt_7_1_ = p_77008_1_.field_174860_b;
      if (lvt_7_1_ == EnumFacing.NORTH) {
         lvt_4_1_ = MathHelper.func_76128_c(p_77008_1_.field_70165_t + (double)(p_77008_2_ / 16.0F));
      }

      if (lvt_7_1_ == EnumFacing.WEST) {
         lvt_6_1_ = MathHelper.func_76128_c(p_77008_1_.field_70161_v - (double)(p_77008_2_ / 16.0F));
      }

      if (lvt_7_1_ == EnumFacing.SOUTH) {
         lvt_4_1_ = MathHelper.func_76128_c(p_77008_1_.field_70165_t - (double)(p_77008_2_ / 16.0F));
      }

      if (lvt_7_1_ == EnumFacing.EAST) {
         lvt_6_1_ = MathHelper.func_76128_c(p_77008_1_.field_70161_v + (double)(p_77008_2_ / 16.0F));
      }

      int lvt_8_1_ = this.field_76990_c.field_78722_g.func_175626_b(new BlockPos(lvt_4_1_, lvt_5_1_, lvt_6_1_), 0);
      int lvt_9_1_ = lvt_8_1_ % 65536;
      int lvt_10_1_ = lvt_8_1_ / 65536;
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)lvt_9_1_, (float)lvt_10_1_);
      GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
   }
}
