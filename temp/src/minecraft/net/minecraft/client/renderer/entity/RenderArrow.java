package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderArrow extends Render<EntityArrow> {
   private static final ResourceLocation field_110780_a = new ResourceLocation("textures/entity/arrow.png");

   public RenderArrow(RenderManager p_i46193_1_) {
      super(p_i46193_1_);
   }

   public void func_76986_a(EntityArrow p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      this.func_180548_c(p_76986_1_);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
      GlStateManager.func_179114_b(p_76986_1_.field_70126_B + (p_76986_1_.field_70177_z - p_76986_1_.field_70126_B) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_, 0.0F, 0.0F, 1.0F);
      Tessellator lvt_10_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_11_1_ = lvt_10_1_.func_178180_c();
      int lvt_12_1_ = 0;
      float lvt_13_1_ = 0.0F;
      float lvt_14_1_ = 0.5F;
      float lvt_15_1_ = (float)(0 + lvt_12_1_ * 10) / 32.0F;
      float lvt_16_1_ = (float)(5 + lvt_12_1_ * 10) / 32.0F;
      float lvt_17_1_ = 0.0F;
      float lvt_18_1_ = 0.15625F;
      float lvt_19_1_ = (float)(5 + lvt_12_1_ * 10) / 32.0F;
      float lvt_20_1_ = (float)(10 + lvt_12_1_ * 10) / 32.0F;
      float lvt_21_1_ = 0.05625F;
      GlStateManager.func_179091_B();
      float lvt_22_1_ = (float)p_76986_1_.field_70249_b - p_76986_9_;
      if (lvt_22_1_ > 0.0F) {
         float lvt_23_1_ = -MathHelper.func_76126_a(lvt_22_1_ * 3.0F) * lvt_22_1_;
         GlStateManager.func_179114_b(lvt_23_1_, 0.0F, 0.0F, 1.0F);
      }

      GlStateManager.func_179114_b(45.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.func_179152_a(lvt_21_1_, lvt_21_1_, lvt_21_1_);
      GlStateManager.func_179109_b(-4.0F, 0.0F, 0.0F);
      GL11.glNormal3f(lvt_21_1_, 0.0F, 0.0F);
      lvt_11_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      lvt_11_1_.func_181662_b(-7.0D, -2.0D, -2.0D).func_181673_a((double)lvt_17_1_, (double)lvt_19_1_).func_181675_d();
      lvt_11_1_.func_181662_b(-7.0D, -2.0D, 2.0D).func_181673_a((double)lvt_18_1_, (double)lvt_19_1_).func_181675_d();
      lvt_11_1_.func_181662_b(-7.0D, 2.0D, 2.0D).func_181673_a((double)lvt_18_1_, (double)lvt_20_1_).func_181675_d();
      lvt_11_1_.func_181662_b(-7.0D, 2.0D, -2.0D).func_181673_a((double)lvt_17_1_, (double)lvt_20_1_).func_181675_d();
      lvt_10_1_.func_78381_a();
      GL11.glNormal3f(-lvt_21_1_, 0.0F, 0.0F);
      lvt_11_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      lvt_11_1_.func_181662_b(-7.0D, 2.0D, -2.0D).func_181673_a((double)lvt_17_1_, (double)lvt_19_1_).func_181675_d();
      lvt_11_1_.func_181662_b(-7.0D, 2.0D, 2.0D).func_181673_a((double)lvt_18_1_, (double)lvt_19_1_).func_181675_d();
      lvt_11_1_.func_181662_b(-7.0D, -2.0D, 2.0D).func_181673_a((double)lvt_18_1_, (double)lvt_20_1_).func_181675_d();
      lvt_11_1_.func_181662_b(-7.0D, -2.0D, -2.0D).func_181673_a((double)lvt_17_1_, (double)lvt_20_1_).func_181675_d();
      lvt_10_1_.func_78381_a();

      for(int lvt_23_2_ = 0; lvt_23_2_ < 4; ++lvt_23_2_) {
         GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glNormal3f(0.0F, 0.0F, lvt_21_1_);
         lvt_11_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
         lvt_11_1_.func_181662_b(-8.0D, -2.0D, 0.0D).func_181673_a((double)lvt_13_1_, (double)lvt_15_1_).func_181675_d();
         lvt_11_1_.func_181662_b(8.0D, -2.0D, 0.0D).func_181673_a((double)lvt_14_1_, (double)lvt_15_1_).func_181675_d();
         lvt_11_1_.func_181662_b(8.0D, 2.0D, 0.0D).func_181673_a((double)lvt_14_1_, (double)lvt_16_1_).func_181675_d();
         lvt_11_1_.func_181662_b(-8.0D, 2.0D, 0.0D).func_181673_a((double)lvt_13_1_, (double)lvt_16_1_).func_181675_d();
         lvt_10_1_.func_78381_a();
      }

      GlStateManager.func_179101_C();
      GlStateManager.func_179121_F();
      super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
   }

   protected ResourceLocation func_110775_a(EntityArrow p_110775_1_) {
      return field_110780_a;
   }
}
