package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.util.MathHelper;

public class NoiseGeneratorOctaves extends NoiseGenerator {
   private NoiseGeneratorImproved[] field_76307_a;
   private int field_76306_b;

   public NoiseGeneratorOctaves(Random p_i2111_1_, int p_i2111_2_) {
      this.field_76306_b = p_i2111_2_;
      this.field_76307_a = new NoiseGeneratorImproved[p_i2111_2_];

      for(int lvt_3_1_ = 0; lvt_3_1_ < p_i2111_2_; ++lvt_3_1_) {
         this.field_76307_a[lvt_3_1_] = new NoiseGeneratorImproved(p_i2111_1_);
      }

   }

   public double[] func_76304_a(double[] p_76304_1_, int p_76304_2_, int p_76304_3_, int p_76304_4_, int p_76304_5_, int p_76304_6_, int p_76304_7_, double p_76304_8_, double p_76304_10_, double p_76304_12_) {
      if (p_76304_1_ == null) {
         p_76304_1_ = new double[p_76304_5_ * p_76304_6_ * p_76304_7_];
      } else {
         for(int lvt_14_1_ = 0; lvt_14_1_ < p_76304_1_.length; ++lvt_14_1_) {
            p_76304_1_[lvt_14_1_] = 0.0D;
         }
      }

      double lvt_14_2_ = 1.0D;

      for(int lvt_16_1_ = 0; lvt_16_1_ < this.field_76306_b; ++lvt_16_1_) {
         double lvt_17_1_ = (double)p_76304_2_ * lvt_14_2_ * p_76304_8_;
         double lvt_19_1_ = (double)p_76304_3_ * lvt_14_2_ * p_76304_10_;
         double lvt_21_1_ = (double)p_76304_4_ * lvt_14_2_ * p_76304_12_;
         long lvt_23_1_ = MathHelper.func_76124_d(lvt_17_1_);
         long lvt_25_1_ = MathHelper.func_76124_d(lvt_21_1_);
         lvt_17_1_ -= (double)lvt_23_1_;
         lvt_21_1_ -= (double)lvt_25_1_;
         lvt_23_1_ %= 16777216L;
         lvt_25_1_ %= 16777216L;
         lvt_17_1_ += (double)lvt_23_1_;
         lvt_21_1_ += (double)lvt_25_1_;
         this.field_76307_a[lvt_16_1_].func_76308_a(p_76304_1_, lvt_17_1_, lvt_19_1_, lvt_21_1_, p_76304_5_, p_76304_6_, p_76304_7_, p_76304_8_ * lvt_14_2_, p_76304_10_ * lvt_14_2_, p_76304_12_ * lvt_14_2_, lvt_14_2_);
         lvt_14_2_ /= 2.0D;
      }

      return p_76304_1_;
   }

   public double[] func_76305_a(double[] p_76305_1_, int p_76305_2_, int p_76305_3_, int p_76305_4_, int p_76305_5_, double p_76305_6_, double p_76305_8_, double p_76305_10_) {
      return this.func_76304_a(p_76305_1_, p_76305_2_, 10, p_76305_3_, p_76305_4_, 1, p_76305_5_, p_76305_6_, 1.0D, p_76305_8_);
   }
}
