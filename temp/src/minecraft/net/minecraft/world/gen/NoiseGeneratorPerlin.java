package net.minecraft.world.gen;

import java.util.Random;

public class NoiseGeneratorPerlin extends NoiseGenerator {
   private NoiseGeneratorSimplex[] field_151603_a;
   private int field_151602_b;

   public NoiseGeneratorPerlin(Random p_i45470_1_, int p_i45470_2_) {
      this.field_151602_b = p_i45470_2_;
      this.field_151603_a = new NoiseGeneratorSimplex[p_i45470_2_];

      for(int lvt_3_1_ = 0; lvt_3_1_ < p_i45470_2_; ++lvt_3_1_) {
         this.field_151603_a[lvt_3_1_] = new NoiseGeneratorSimplex(p_i45470_1_);
      }

   }

   public double func_151601_a(double p_151601_1_, double p_151601_3_) {
      double lvt_5_1_ = 0.0D;
      double lvt_7_1_ = 1.0D;

      for(int lvt_9_1_ = 0; lvt_9_1_ < this.field_151602_b; ++lvt_9_1_) {
         lvt_5_1_ += this.field_151603_a[lvt_9_1_].func_151605_a(p_151601_1_ * lvt_7_1_, p_151601_3_ * lvt_7_1_) / lvt_7_1_;
         lvt_7_1_ /= 2.0D;
      }

      return lvt_5_1_;
   }

   public double[] func_151599_a(double[] p_151599_1_, double p_151599_2_, double p_151599_4_, int p_151599_6_, int p_151599_7_, double p_151599_8_, double p_151599_10_, double p_151599_12_) {
      return this.func_151600_a(p_151599_1_, p_151599_2_, p_151599_4_, p_151599_6_, p_151599_7_, p_151599_8_, p_151599_10_, p_151599_12_, 0.5D);
   }

   public double[] func_151600_a(double[] p_151600_1_, double p_151600_2_, double p_151600_4_, int p_151600_6_, int p_151600_7_, double p_151600_8_, double p_151600_10_, double p_151600_12_, double p_151600_14_) {
      if (p_151600_1_ != null && p_151600_1_.length >= p_151600_6_ * p_151600_7_) {
         for(int lvt_16_1_ = 0; lvt_16_1_ < p_151600_1_.length; ++lvt_16_1_) {
            p_151600_1_[lvt_16_1_] = 0.0D;
         }
      } else {
         p_151600_1_ = new double[p_151600_6_ * p_151600_7_];
      }

      double lvt_16_2_ = 1.0D;
      double lvt_18_1_ = 1.0D;

      for(int lvt_20_1_ = 0; lvt_20_1_ < this.field_151602_b; ++lvt_20_1_) {
         this.field_151603_a[lvt_20_1_].func_151606_a(p_151600_1_, p_151600_2_, p_151600_4_, p_151600_6_, p_151600_7_, p_151600_8_ * lvt_18_1_ * lvt_16_2_, p_151600_10_ * lvt_18_1_ * lvt_16_2_, 0.55D / lvt_16_2_);
         lvt_18_1_ *= p_151600_12_;
         lvt_16_2_ *= p_151600_14_;
      }

      return p_151600_1_;
   }
}
