package net.minecraft.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderFish extends Render<EntityFishHook> {
   private static final ResourceLocation field_110792_a = new ResourceLocation("textures/particle/particles.png");

   public RenderFish(RenderManager p_i46175_1_) {
      super(p_i46175_1_);
   }

   public void func_76986_a(EntityFishHook p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
      this.func_180548_c(p_76986_1_);
      Tessellator lvt_10_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_11_1_ = lvt_10_1_.func_178180_c();
      int lvt_12_1_ = true;
      int lvt_13_1_ = true;
      float lvt_14_1_ = 0.0625F;
      float lvt_15_1_ = 0.125F;
      float lvt_16_1_ = 0.125F;
      float lvt_17_1_ = 0.1875F;
      float lvt_18_1_ = 1.0F;
      float lvt_19_1_ = 0.5F;
      float lvt_20_1_ = 0.5F;
      GlStateManager.func_179114_b(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
      lvt_11_1_.func_181668_a(7, DefaultVertexFormats.field_181710_j);
      lvt_11_1_.func_181662_b(-0.5D, -0.5D, 0.0D).func_181673_a(0.0625D, 0.1875D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      lvt_11_1_.func_181662_b(0.5D, -0.5D, 0.0D).func_181673_a(0.125D, 0.1875D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      lvt_11_1_.func_181662_b(0.5D, 0.5D, 0.0D).func_181673_a(0.125D, 0.125D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      lvt_11_1_.func_181662_b(-0.5D, 0.5D, 0.0D).func_181673_a(0.0625D, 0.125D).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
      lvt_10_1_.func_78381_a();
      GlStateManager.func_179101_C();
      GlStateManager.func_179121_F();
      if (p_76986_1_.field_146042_b != null) {
         float lvt_21_1_ = p_76986_1_.field_146042_b.func_70678_g(p_76986_9_);
         float lvt_22_1_ = MathHelper.func_76126_a(MathHelper.func_76129_c(lvt_21_1_) * 3.1415927F);
         Vec3 lvt_23_1_ = new Vec3(-0.36D, 0.03D, 0.35D);
         lvt_23_1_ = lvt_23_1_.func_178789_a(-(p_76986_1_.field_146042_b.field_70127_C + (p_76986_1_.field_146042_b.field_70125_A - p_76986_1_.field_146042_b.field_70127_C) * p_76986_9_) * 3.1415927F / 180.0F);
         lvt_23_1_ = lvt_23_1_.func_178785_b(-(p_76986_1_.field_146042_b.field_70126_B + (p_76986_1_.field_146042_b.field_70177_z - p_76986_1_.field_146042_b.field_70126_B) * p_76986_9_) * 3.1415927F / 180.0F);
         lvt_23_1_ = lvt_23_1_.func_178785_b(lvt_22_1_ * 0.5F);
         lvt_23_1_ = lvt_23_1_.func_178789_a(-lvt_22_1_ * 0.7F);
         double lvt_24_1_ = p_76986_1_.field_146042_b.field_70169_q + (p_76986_1_.field_146042_b.field_70165_t - p_76986_1_.field_146042_b.field_70169_q) * (double)p_76986_9_ + lvt_23_1_.field_72450_a;
         double lvt_26_1_ = p_76986_1_.field_146042_b.field_70167_r + (p_76986_1_.field_146042_b.field_70163_u - p_76986_1_.field_146042_b.field_70167_r) * (double)p_76986_9_ + lvt_23_1_.field_72448_b;
         double lvt_28_1_ = p_76986_1_.field_146042_b.field_70166_s + (p_76986_1_.field_146042_b.field_70161_v - p_76986_1_.field_146042_b.field_70166_s) * (double)p_76986_9_ + lvt_23_1_.field_72449_c;
         double lvt_30_1_ = (double)p_76986_1_.field_146042_b.func_70047_e();
         if (this.field_76990_c.field_78733_k != null && this.field_76990_c.field_78733_k.field_74330_P > 0 || p_76986_1_.field_146042_b != Minecraft.func_71410_x().field_71439_g) {
            float lvt_32_1_ = (p_76986_1_.field_146042_b.field_70760_ar + (p_76986_1_.field_146042_b.field_70761_aq - p_76986_1_.field_146042_b.field_70760_ar) * p_76986_9_) * 3.1415927F / 180.0F;
            double lvt_33_1_ = (double)MathHelper.func_76126_a(lvt_32_1_);
            double lvt_35_1_ = (double)MathHelper.func_76134_b(lvt_32_1_);
            double lvt_37_1_ = 0.35D;
            double lvt_39_1_ = 0.8D;
            lvt_24_1_ = p_76986_1_.field_146042_b.field_70169_q + (p_76986_1_.field_146042_b.field_70165_t - p_76986_1_.field_146042_b.field_70169_q) * (double)p_76986_9_ - lvt_35_1_ * 0.35D - lvt_33_1_ * 0.8D;
            lvt_26_1_ = p_76986_1_.field_146042_b.field_70167_r + lvt_30_1_ + (p_76986_1_.field_146042_b.field_70163_u - p_76986_1_.field_146042_b.field_70167_r) * (double)p_76986_9_ - 0.45D;
            lvt_28_1_ = p_76986_1_.field_146042_b.field_70166_s + (p_76986_1_.field_146042_b.field_70161_v - p_76986_1_.field_146042_b.field_70166_s) * (double)p_76986_9_ - lvt_33_1_ * 0.35D + lvt_35_1_ * 0.8D;
            lvt_30_1_ = p_76986_1_.field_146042_b.func_70093_af() ? -0.1875D : 0.0D;
         }

         double lvt_32_2_ = p_76986_1_.field_70169_q + (p_76986_1_.field_70165_t - p_76986_1_.field_70169_q) * (double)p_76986_9_;
         double lvt_34_1_ = p_76986_1_.field_70167_r + (p_76986_1_.field_70163_u - p_76986_1_.field_70167_r) * (double)p_76986_9_ + 0.25D;
         double lvt_36_1_ = p_76986_1_.field_70166_s + (p_76986_1_.field_70161_v - p_76986_1_.field_70166_s) * (double)p_76986_9_;
         double lvt_38_1_ = (double)((float)(lvt_24_1_ - lvt_32_2_));
         double lvt_40_1_ = (double)((float)(lvt_26_1_ - lvt_34_1_)) + lvt_30_1_;
         double lvt_42_1_ = (double)((float)(lvt_28_1_ - lvt_36_1_));
         GlStateManager.func_179090_x();
         GlStateManager.func_179140_f();
         lvt_11_1_.func_181668_a(3, DefaultVertexFormats.field_181706_f);
         int lvt_44_1_ = true;

         for(int lvt_45_1_ = 0; lvt_45_1_ <= 16; ++lvt_45_1_) {
            float lvt_46_1_ = (float)lvt_45_1_ / 16.0F;
            lvt_11_1_.func_181662_b(p_76986_2_ + lvt_38_1_ * (double)lvt_46_1_, p_76986_4_ + lvt_40_1_ * (double)(lvt_46_1_ * lvt_46_1_ + lvt_46_1_) * 0.5D + 0.25D, p_76986_6_ + lvt_42_1_ * (double)lvt_46_1_).func_181669_b(0, 0, 0, 255).func_181675_d();
         }

         lvt_10_1_.func_78381_a();
         GlStateManager.func_179145_e();
         GlStateManager.func_179098_w();
         super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
      }

   }

   protected ResourceLocation func_110775_a(EntityFishHook p_110775_1_) {
      return field_110792_a;
   }
}
