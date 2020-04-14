package net.minecraft.client.renderer.entity.layers;

import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.boss.EntityDragon;

public class LayerEnderDragonDeath implements LayerRenderer<EntityDragon> {
   public void func_177141_a(EntityDragon p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
      if (p_177141_1_.field_70995_bG > 0) {
         Tessellator lvt_9_1_ = Tessellator.func_178181_a();
         WorldRenderer lvt_10_1_ = lvt_9_1_.func_178180_c();
         RenderHelper.func_74518_a();
         float lvt_11_1_ = ((float)p_177141_1_.field_70995_bG + p_177141_4_) / 200.0F;
         float lvt_12_1_ = 0.0F;
         if (lvt_11_1_ > 0.8F) {
            lvt_12_1_ = (lvt_11_1_ - 0.8F) / 0.2F;
         }

         Random lvt_13_1_ = new Random(432L);
         GlStateManager.func_179090_x();
         GlStateManager.func_179103_j(7425);
         GlStateManager.func_179147_l();
         GlStateManager.func_179112_b(770, 1);
         GlStateManager.func_179118_c();
         GlStateManager.func_179089_o();
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b(0.0F, -1.0F, -2.0F);

         for(int lvt_14_1_ = 0; (float)lvt_14_1_ < (lvt_11_1_ + lvt_11_1_ * lvt_11_1_) / 2.0F * 60.0F; ++lvt_14_1_) {
            GlStateManager.func_179114_b(lvt_13_1_.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(lvt_13_1_.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179114_b(lvt_13_1_.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179114_b(lvt_13_1_.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(lvt_13_1_.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179114_b(lvt_13_1_.nextFloat() * 360.0F + lvt_11_1_ * 90.0F, 0.0F, 0.0F, 1.0F);
            float lvt_15_1_ = lvt_13_1_.nextFloat() * 20.0F + 5.0F + lvt_12_1_ * 10.0F;
            float lvt_16_1_ = lvt_13_1_.nextFloat() * 2.0F + 1.0F + lvt_12_1_ * 2.0F;
            lvt_10_1_.func_181668_a(6, DefaultVertexFormats.field_181706_f);
            lvt_10_1_.func_181662_b(0.0D, 0.0D, 0.0D).func_181669_b(255, 255, 255, (int)(255.0F * (1.0F - lvt_12_1_))).func_181675_d();
            lvt_10_1_.func_181662_b(-0.866D * (double)lvt_16_1_, (double)lvt_15_1_, (double)(-0.5F * lvt_16_1_)).func_181669_b(255, 0, 255, 0).func_181675_d();
            lvt_10_1_.func_181662_b(0.866D * (double)lvt_16_1_, (double)lvt_15_1_, (double)(-0.5F * lvt_16_1_)).func_181669_b(255, 0, 255, 0).func_181675_d();
            lvt_10_1_.func_181662_b(0.0D, (double)lvt_15_1_, (double)(1.0F * lvt_16_1_)).func_181669_b(255, 0, 255, 0).func_181675_d();
            lvt_10_1_.func_181662_b(-0.866D * (double)lvt_16_1_, (double)lvt_15_1_, (double)(-0.5F * lvt_16_1_)).func_181669_b(255, 0, 255, 0).func_181675_d();
            lvt_9_1_.func_78381_a();
         }

         GlStateManager.func_179121_F();
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179129_p();
         GlStateManager.func_179084_k();
         GlStateManager.func_179103_j(7424);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179098_w();
         GlStateManager.func_179141_d();
         RenderHelper.func_74519_b();
      }
   }

   public boolean func_177142_b() {
      return false;
   }
}
