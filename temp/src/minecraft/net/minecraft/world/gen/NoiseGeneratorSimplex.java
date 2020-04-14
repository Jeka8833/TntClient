package net.minecraft.world.gen;

import java.util.Random;

public class NoiseGeneratorSimplex {
   private static int[][] field_151611_e = new int[][]{{1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0}, {1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}};
   public static final double field_151614_a = Math.sqrt(3.0D);
   private int[] field_151608_f;
   public double field_151612_b;
   public double field_151613_c;
   public double field_151610_d;
   private static final double field_151609_g;
   private static final double field_151615_h;

   public NoiseGeneratorSimplex() {
      this(new Random());
   }

   public NoiseGeneratorSimplex(Random p_i45471_1_) {
      this.field_151608_f = new int[512];
      this.field_151612_b = p_i45471_1_.nextDouble() * 256.0D;
      this.field_151613_c = p_i45471_1_.nextDouble() * 256.0D;
      this.field_151610_d = p_i45471_1_.nextDouble() * 256.0D;

      int lvt_2_2_;
      for(lvt_2_2_ = 0; lvt_2_2_ < 256; this.field_151608_f[lvt_2_2_] = lvt_2_2_++) {
      }

      for(lvt_2_2_ = 0; lvt_2_2_ < 256; ++lvt_2_2_) {
         int lvt_3_1_ = p_i45471_1_.nextInt(256 - lvt_2_2_) + lvt_2_2_;
         int lvt_4_1_ = this.field_151608_f[lvt_2_2_];
         this.field_151608_f[lvt_2_2_] = this.field_151608_f[lvt_3_1_];
         this.field_151608_f[lvt_3_1_] = lvt_4_1_;
         this.field_151608_f[lvt_2_2_ + 256] = this.field_151608_f[lvt_2_2_];
      }

   }

   private static int func_151607_a(double p_151607_0_) {
      return p_151607_0_ > 0.0D ? (int)p_151607_0_ : (int)p_151607_0_ - 1;
   }

   private static double func_151604_a(int[] p_151604_0_, double p_151604_1_, double p_151604_3_) {
      return (double)p_151604_0_[0] * p_151604_1_ + (double)p_151604_0_[1] * p_151604_3_;
   }

   public double func_151605_a(double p_151605_1_, double p_151605_3_) {
      double lvt_11_1_ = 0.5D * (field_151614_a - 1.0D);
      double lvt_13_1_ = (p_151605_1_ + p_151605_3_) * lvt_11_1_;
      int lvt_15_1_ = func_151607_a(p_151605_1_ + lvt_13_1_);
      int lvt_16_1_ = func_151607_a(p_151605_3_ + lvt_13_1_);
      double lvt_17_1_ = (3.0D - field_151614_a) / 6.0D;
      double lvt_19_1_ = (double)(lvt_15_1_ + lvt_16_1_) * lvt_17_1_;
      double lvt_21_1_ = (double)lvt_15_1_ - lvt_19_1_;
      double lvt_23_1_ = (double)lvt_16_1_ - lvt_19_1_;
      double lvt_25_1_ = p_151605_1_ - lvt_21_1_;
      double lvt_27_1_ = p_151605_3_ - lvt_23_1_;
      byte lvt_29_2_;
      byte lvt_30_2_;
      if (lvt_25_1_ > lvt_27_1_) {
         lvt_29_2_ = 1;
         lvt_30_2_ = 0;
      } else {
         lvt_29_2_ = 0;
         lvt_30_2_ = 1;
      }

      double lvt_31_1_ = lvt_25_1_ - (double)lvt_29_2_ + lvt_17_1_;
      double lvt_33_1_ = lvt_27_1_ - (double)lvt_30_2_ + lvt_17_1_;
      double lvt_35_1_ = lvt_25_1_ - 1.0D + 2.0D * lvt_17_1_;
      double lvt_37_1_ = lvt_27_1_ - 1.0D + 2.0D * lvt_17_1_;
      int lvt_39_1_ = lvt_15_1_ & 255;
      int lvt_40_1_ = lvt_16_1_ & 255;
      int lvt_41_1_ = this.field_151608_f[lvt_39_1_ + this.field_151608_f[lvt_40_1_]] % 12;
      int lvt_42_1_ = this.field_151608_f[lvt_39_1_ + lvt_29_2_ + this.field_151608_f[lvt_40_1_ + lvt_30_2_]] % 12;
      int lvt_43_1_ = this.field_151608_f[lvt_39_1_ + 1 + this.field_151608_f[lvt_40_1_ + 1]] % 12;
      double lvt_44_1_ = 0.5D - lvt_25_1_ * lvt_25_1_ - lvt_27_1_ * lvt_27_1_;
      double lvt_5_2_;
      if (lvt_44_1_ < 0.0D) {
         lvt_5_2_ = 0.0D;
      } else {
         lvt_44_1_ *= lvt_44_1_;
         lvt_5_2_ = lvt_44_1_ * lvt_44_1_ * func_151604_a(field_151611_e[lvt_41_1_], lvt_25_1_, lvt_27_1_);
      }

      double lvt_46_1_ = 0.5D - lvt_31_1_ * lvt_31_1_ - lvt_33_1_ * lvt_33_1_;
      double lvt_7_2_;
      if (lvt_46_1_ < 0.0D) {
         lvt_7_2_ = 0.0D;
      } else {
         lvt_46_1_ *= lvt_46_1_;
         lvt_7_2_ = lvt_46_1_ * lvt_46_1_ * func_151604_a(field_151611_e[lvt_42_1_], lvt_31_1_, lvt_33_1_);
      }

      double lvt_48_1_ = 0.5D - lvt_35_1_ * lvt_35_1_ - lvt_37_1_ * lvt_37_1_;
      double lvt_9_2_;
      if (lvt_48_1_ < 0.0D) {
         lvt_9_2_ = 0.0D;
      } else {
         lvt_48_1_ *= lvt_48_1_;
         lvt_9_2_ = lvt_48_1_ * lvt_48_1_ * func_151604_a(field_151611_e[lvt_43_1_], lvt_35_1_, lvt_37_1_);
      }

      return 70.0D * (lvt_5_2_ + lvt_7_2_ + lvt_9_2_);
   }

   public void func_151606_a(double[] p_151606_1_, double p_151606_2_, double p_151606_4_, int p_151606_6_, int p_151606_7_, double p_151606_8_, double p_151606_10_, double p_151606_12_) {
      int lvt_14_1_ = 0;

      for(int lvt_15_1_ = 0; lvt_15_1_ < p_151606_7_; ++lvt_15_1_) {
         double lvt_16_1_ = (p_151606_4_ + (double)lvt_15_1_) * p_151606_10_ + this.field_151613_c;

         for(int lvt_18_1_ = 0; lvt_18_1_ < p_151606_6_; ++lvt_18_1_) {
            double lvt_19_1_ = (p_151606_2_ + (double)lvt_18_1_) * p_151606_8_ + this.field_151612_b;
            double lvt_27_1_ = (lvt_19_1_ + lvt_16_1_) * field_151609_g;
            int lvt_29_1_ = func_151607_a(lvt_19_1_ + lvt_27_1_);
            int lvt_30_1_ = func_151607_a(lvt_16_1_ + lvt_27_1_);
            double lvt_31_1_ = (double)(lvt_29_1_ + lvt_30_1_) * field_151615_h;
            double lvt_33_1_ = (double)lvt_29_1_ - lvt_31_1_;
            double lvt_35_1_ = (double)lvt_30_1_ - lvt_31_1_;
            double lvt_37_1_ = lvt_19_1_ - lvt_33_1_;
            double lvt_39_1_ = lvt_16_1_ - lvt_35_1_;
            byte lvt_41_2_;
            byte lvt_42_2_;
            if (lvt_37_1_ > lvt_39_1_) {
               lvt_41_2_ = 1;
               lvt_42_2_ = 0;
            } else {
               lvt_41_2_ = 0;
               lvt_42_2_ = 1;
            }

            double lvt_43_1_ = lvt_37_1_ - (double)lvt_41_2_ + field_151615_h;
            double lvt_45_1_ = lvt_39_1_ - (double)lvt_42_2_ + field_151615_h;
            double lvt_47_1_ = lvt_37_1_ - 1.0D + 2.0D * field_151615_h;
            double lvt_49_1_ = lvt_39_1_ - 1.0D + 2.0D * field_151615_h;
            int lvt_51_1_ = lvt_29_1_ & 255;
            int lvt_52_1_ = lvt_30_1_ & 255;
            int lvt_53_1_ = this.field_151608_f[lvt_51_1_ + this.field_151608_f[lvt_52_1_]] % 12;
            int lvt_54_1_ = this.field_151608_f[lvt_51_1_ + lvt_41_2_ + this.field_151608_f[lvt_52_1_ + lvt_42_2_]] % 12;
            int lvt_55_1_ = this.field_151608_f[lvt_51_1_ + 1 + this.field_151608_f[lvt_52_1_ + 1]] % 12;
            double lvt_56_1_ = 0.5D - lvt_37_1_ * lvt_37_1_ - lvt_39_1_ * lvt_39_1_;
            double lvt_21_2_;
            if (lvt_56_1_ < 0.0D) {
               lvt_21_2_ = 0.0D;
            } else {
               lvt_56_1_ *= lvt_56_1_;
               lvt_21_2_ = lvt_56_1_ * lvt_56_1_ * func_151604_a(field_151611_e[lvt_53_1_], lvt_37_1_, lvt_39_1_);
            }

            double lvt_58_1_ = 0.5D - lvt_43_1_ * lvt_43_1_ - lvt_45_1_ * lvt_45_1_;
            double lvt_23_2_;
            if (lvt_58_1_ < 0.0D) {
               lvt_23_2_ = 0.0D;
            } else {
               lvt_58_1_ *= lvt_58_1_;
               lvt_23_2_ = lvt_58_1_ * lvt_58_1_ * func_151604_a(field_151611_e[lvt_54_1_], lvt_43_1_, lvt_45_1_);
            }

            double lvt_60_1_ = 0.5D - lvt_47_1_ * lvt_47_1_ - lvt_49_1_ * lvt_49_1_;
            double lvt_25_2_;
            if (lvt_60_1_ < 0.0D) {
               lvt_25_2_ = 0.0D;
            } else {
               lvt_60_1_ *= lvt_60_1_;
               lvt_25_2_ = lvt_60_1_ * lvt_60_1_ * func_151604_a(field_151611_e[lvt_55_1_], lvt_47_1_, lvt_49_1_);
            }

            int var10001 = lvt_14_1_++;
            p_151606_1_[var10001] += 70.0D * (lvt_21_2_ + lvt_23_2_ + lvt_25_2_) * p_151606_12_;
         }
      }

   }

   static {
      field_151609_g = 0.5D * (field_151614_a - 1.0D);
      field_151615_h = (3.0D - field_151614_a) / 6.0D;
   }
}
