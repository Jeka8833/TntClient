package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.ResourceLocation;

public class RenderLightningBolt extends Render<EntityLightningBolt> {
   public RenderLightningBolt(RenderManager p_i46157_1_) {
      super(p_i46157_1_);
   }

   public void func_76986_a(EntityLightningBolt p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      Tessellator lvt_10_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_11_1_ = lvt_10_1_.func_178180_c();
      GlStateManager.func_179090_x();
      GlStateManager.func_179140_f();
      GlStateManager.func_179147_l();
      GlStateManager.func_179112_b(770, 1);
      double[] lvt_12_1_ = new double[8];
      double[] lvt_13_1_ = new double[8];
      double lvt_14_1_ = 0.0D;
      double lvt_16_1_ = 0.0D;
      Random lvt_18_1_ = new Random(p_76986_1_.field_70264_a);

      for(int lvt_19_1_ = 7; lvt_19_1_ >= 0; --lvt_19_1_) {
         lvt_12_1_[lvt_19_1_] = lvt_14_1_;
         lvt_13_1_[lvt_19_1_] = lvt_16_1_;
         lvt_14_1_ += (double)(lvt_18_1_.nextInt(11) - 5);
         lvt_16_1_ += (double)(lvt_18_1_.nextInt(11) - 5);
      }

      for(int lvt_18_2_ = 0; lvt_18_2_ < 4; ++lvt_18_2_) {
         Random lvt_19_2_ = new Random(p_76986_1_.field_70264_a);

         for(int lvt_20_1_ = 0; lvt_20_1_ < 3; ++lvt_20_1_) {
            int lvt_21_1_ = 7;
            int lvt_22_1_ = 0;
            if (lvt_20_1_ > 0) {
               lvt_21_1_ = 7 - lvt_20_1_;
            }

            if (lvt_20_1_ > 0) {
               lvt_22_1_ = lvt_21_1_ - 2;
            }

            double lvt_23_1_ = lvt_12_1_[lvt_21_1_] - lvt_14_1_;
            double lvt_25_1_ = lvt_13_1_[lvt_21_1_] - lvt_16_1_;

            for(int lvt_27_1_ = lvt_21_1_; lvt_27_1_ >= lvt_22_1_; --lvt_27_1_) {
               double lvt_28_1_ = lvt_23_1_;
               double lvt_30_1_ = lvt_25_1_;
               if (lvt_20_1_ == 0) {
                  lvt_23_1_ += (double)(lvt_19_2_.nextInt(11) - 5);
                  lvt_25_1_ += (double)(lvt_19_2_.nextInt(11) - 5);
               } else {
                  lvt_23_1_ += (double)(lvt_19_2_.nextInt(31) - 15);
                  lvt_25_1_ += (double)(lvt_19_2_.nextInt(31) - 15);
               }

               lvt_11_1_.func_181668_a(5, DefaultVertexFormats.field_181706_f);
               float lvt_32_1_ = 0.5F;
               float lvt_33_1_ = 0.45F;
               float lvt_34_1_ = 0.45F;
               float lvt_35_1_ = 0.5F;
               double lvt_36_1_ = 0.1D + (double)lvt_18_2_ * 0.2D;
               if (lvt_20_1_ == 0) {
                  lvt_36_1_ *= (double)lvt_27_1_ * 0.1D + 1.0D;
               }

               double lvt_38_1_ = 0.1D + (double)lvt_18_2_ * 0.2D;
               if (lvt_20_1_ == 0) {
                  lvt_38_1_ *= (double)(lvt_27_1_ - 1) * 0.1D + 1.0D;
               }

               for(int lvt_40_1_ = 0; lvt_40_1_ < 5; ++lvt_40_1_) {
                  double lvt_41_1_ = p_76986_2_ + 0.5D - lvt_36_1_;
                  double lvt_43_1_ = p_76986_6_ + 0.5D - lvt_36_1_;
                  if (lvt_40_1_ == 1 || lvt_40_1_ == 2) {
                     lvt_41_1_ += lvt_36_1_ * 2.0D;
                  }

                  if (lvt_40_1_ == 2 || lvt_40_1_ == 3) {
                     lvt_43_1_ += lvt_36_1_ * 2.0D;
                  }

                  double lvt_45_1_ = p_76986_2_ + 0.5D - lvt_38_1_;
                  double lvt_47_1_ = p_76986_6_ + 0.5D - lvt_38_1_;
                  if (lvt_40_1_ == 1 || lvt_40_1_ == 2) {
                     lvt_45_1_ += lvt_38_1_ * 2.0D;
                  }

                  if (lvt_40_1_ == 2 || lvt_40_1_ == 3) {
                     lvt_47_1_ += lvt_38_1_ * 2.0D;
                  }

                  lvt_11_1_.func_181662_b(lvt_45_1_ + lvt_23_1_, p_76986_4_ + (double)(lvt_27_1_ * 16), lvt_47_1_ + lvt_25_1_).func_181666_a(0.45F, 0.45F, 0.5F, 0.3F).func_181675_d();
                  lvt_11_1_.func_181662_b(lvt_41_1_ + lvt_28_1_, p_76986_4_ + (double)((lvt_27_1_ + 1) * 16), lvt_43_1_ + lvt_30_1_).func_181666_a(0.45F, 0.45F, 0.5F, 0.3F).func_181675_d();
               }

               lvt_10_1_.func_78381_a();
            }
         }
      }

      GlStateManager.func_179084_k();
      GlStateManager.func_179145_e();
      GlStateManager.func_179098_w();
   }

   protected ResourceLocation func_110775_a(EntityLightningBolt p_110775_1_) {
      return null;
   }
}
