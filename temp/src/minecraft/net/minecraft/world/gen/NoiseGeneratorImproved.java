package net.minecraft.world.gen;

import java.util.Random;

public class NoiseGeneratorImproved extends NoiseGenerator {
   private int[] field_76312_d;
   public double field_76315_a;
   public double field_76313_b;
   public double field_76314_c;
   private static final double[] field_152381_e = new double[]{1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, -1.0D, 0.0D};
   private static final double[] field_152382_f = new double[]{1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D};
   private static final double[] field_152383_g = new double[]{0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, -1.0D, -1.0D, 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 1.0D, 0.0D, -1.0D};
   private static final double[] field_152384_h = new double[]{1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, -1.0D, 0.0D};
   private static final double[] field_152385_i = new double[]{0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, -1.0D, -1.0D, 1.0D, 1.0D, -1.0D, -1.0D, 0.0D, 1.0D, 0.0D, -1.0D};

   public NoiseGeneratorImproved() {
      this(new Random());
   }

   public NoiseGeneratorImproved(Random p_i45469_1_) {
      this.field_76312_d = new int[512];
      this.field_76315_a = p_i45469_1_.nextDouble() * 256.0D;
      this.field_76313_b = p_i45469_1_.nextDouble() * 256.0D;
      this.field_76314_c = p_i45469_1_.nextDouble() * 256.0D;

      int lvt_2_2_;
      for(lvt_2_2_ = 0; lvt_2_2_ < 256; this.field_76312_d[lvt_2_2_] = lvt_2_2_++) {
      }

      for(lvt_2_2_ = 0; lvt_2_2_ < 256; ++lvt_2_2_) {
         int lvt_3_1_ = p_i45469_1_.nextInt(256 - lvt_2_2_) + lvt_2_2_;
         int lvt_4_1_ = this.field_76312_d[lvt_2_2_];
         this.field_76312_d[lvt_2_2_] = this.field_76312_d[lvt_3_1_];
         this.field_76312_d[lvt_3_1_] = lvt_4_1_;
         this.field_76312_d[lvt_2_2_ + 256] = this.field_76312_d[lvt_2_2_];
      }

   }

   public final double func_76311_b(double p_76311_1_, double p_76311_3_, double p_76311_5_) {
      return p_76311_3_ + p_76311_1_ * (p_76311_5_ - p_76311_3_);
   }

   public final double func_76309_a(int p_76309_1_, double p_76309_2_, double p_76309_4_) {
      int lvt_6_1_ = p_76309_1_ & 15;
      return field_152384_h[lvt_6_1_] * p_76309_2_ + field_152385_i[lvt_6_1_] * p_76309_4_;
   }

   public final double func_76310_a(int p_76310_1_, double p_76310_2_, double p_76310_4_, double p_76310_6_) {
      int lvt_8_1_ = p_76310_1_ & 15;
      return field_152381_e[lvt_8_1_] * p_76310_2_ + field_152382_f[lvt_8_1_] * p_76310_4_ + field_152383_g[lvt_8_1_] * p_76310_6_;
   }

   public void func_76308_a(double[] p_76308_1_, double p_76308_2_, double p_76308_4_, double p_76308_6_, int p_76308_8_, int p_76308_9_, int p_76308_10_, double p_76308_11_, double p_76308_13_, double p_76308_15_, double p_76308_17_) {
      int var10001;
      int lvt_19_1_;
      int lvt_22_2_;
      double lvt_31_2_;
      double lvt_35_2_;
      int lvt_37_1_;
      double lvt_38_2_;
      int lvt_40_1_;
      int lvt_41_2_;
      double lvt_42_2_;
      int lvt_27_2_;
      if (p_76308_9_ == 1) {
         int lvt_19_1_ = false;
         int lvt_20_1_ = false;
         int lvt_21_1_ = false;
         int lvt_22_1_ = false;
         double lvt_23_1_ = 0.0D;
         double lvt_25_1_ = 0.0D;
         lvt_27_2_ = 0;
         double lvt_28_1_ = 1.0D / p_76308_17_;

         for(int lvt_30_1_ = 0; lvt_30_1_ < p_76308_8_; ++lvt_30_1_) {
            lvt_31_2_ = p_76308_2_ + (double)lvt_30_1_ * p_76308_11_ + this.field_76315_a;
            int lvt_33_1_ = (int)lvt_31_2_;
            if (lvt_31_2_ < (double)lvt_33_1_) {
               --lvt_33_1_;
            }

            int lvt_34_1_ = lvt_33_1_ & 255;
            lvt_31_2_ -= (double)lvt_33_1_;
            lvt_35_2_ = lvt_31_2_ * lvt_31_2_ * lvt_31_2_ * (lvt_31_2_ * (lvt_31_2_ * 6.0D - 15.0D) + 10.0D);

            for(lvt_37_1_ = 0; lvt_37_1_ < p_76308_10_; ++lvt_37_1_) {
               lvt_38_2_ = p_76308_6_ + (double)lvt_37_1_ * p_76308_15_ + this.field_76314_c;
               lvt_40_1_ = (int)lvt_38_2_;
               if (lvt_38_2_ < (double)lvt_40_1_) {
                  --lvt_40_1_;
               }

               lvt_41_2_ = lvt_40_1_ & 255;
               lvt_38_2_ -= (double)lvt_40_1_;
               lvt_42_2_ = lvt_38_2_ * lvt_38_2_ * lvt_38_2_ * (lvt_38_2_ * (lvt_38_2_ * 6.0D - 15.0D) + 10.0D);
               lvt_19_1_ = this.field_76312_d[lvt_34_1_] + 0;
               int lvt_20_1_ = this.field_76312_d[lvt_19_1_] + lvt_41_2_;
               int lvt_21_1_ = this.field_76312_d[lvt_34_1_ + 1] + 0;
               lvt_22_2_ = this.field_76312_d[lvt_21_1_] + lvt_41_2_;
               lvt_23_1_ = this.func_76311_b(lvt_35_2_, this.func_76309_a(this.field_76312_d[lvt_20_1_], lvt_31_2_, lvt_38_2_), this.func_76310_a(this.field_76312_d[lvt_22_2_], lvt_31_2_ - 1.0D, 0.0D, lvt_38_2_));
               lvt_25_1_ = this.func_76311_b(lvt_35_2_, this.func_76310_a(this.field_76312_d[lvt_20_1_ + 1], lvt_31_2_, 0.0D, lvt_38_2_ - 1.0D), this.func_76310_a(this.field_76312_d[lvt_22_2_ + 1], lvt_31_2_ - 1.0D, 0.0D, lvt_38_2_ - 1.0D));
               double lvt_44_1_ = this.func_76311_b(lvt_42_2_, lvt_23_1_, lvt_25_1_);
               var10001 = lvt_27_2_++;
               p_76308_1_[var10001] += lvt_44_1_ * lvt_28_1_;
            }
         }

      } else {
         lvt_19_1_ = 0;
         double lvt_20_2_ = 1.0D / p_76308_17_;
         lvt_22_2_ = -1;
         int lvt_23_2_ = false;
         int lvt_24_1_ = false;
         int lvt_25_2_ = false;
         int lvt_26_1_ = false;
         int lvt_27_2_ = false;
         int lvt_28_2_ = false;
         double lvt_29_1_ = 0.0D;
         lvt_31_2_ = 0.0D;
         double lvt_33_2_ = 0.0D;
         lvt_35_2_ = 0.0D;

         for(lvt_37_1_ = 0; lvt_37_1_ < p_76308_8_; ++lvt_37_1_) {
            lvt_38_2_ = p_76308_2_ + (double)lvt_37_1_ * p_76308_11_ + this.field_76315_a;
            lvt_40_1_ = (int)lvt_38_2_;
            if (lvt_38_2_ < (double)lvt_40_1_) {
               --lvt_40_1_;
            }

            lvt_41_2_ = lvt_40_1_ & 255;
            lvt_38_2_ -= (double)lvt_40_1_;
            lvt_42_2_ = lvt_38_2_ * lvt_38_2_ * lvt_38_2_ * (lvt_38_2_ * (lvt_38_2_ * 6.0D - 15.0D) + 10.0D);

            for(int lvt_44_2_ = 0; lvt_44_2_ < p_76308_10_; ++lvt_44_2_) {
               double lvt_45_1_ = p_76308_6_ + (double)lvt_44_2_ * p_76308_15_ + this.field_76314_c;
               int lvt_47_1_ = (int)lvt_45_1_;
               if (lvt_45_1_ < (double)lvt_47_1_) {
                  --lvt_47_1_;
               }

               int lvt_48_1_ = lvt_47_1_ & 255;
               lvt_45_1_ -= (double)lvt_47_1_;
               double lvt_49_1_ = lvt_45_1_ * lvt_45_1_ * lvt_45_1_ * (lvt_45_1_ * (lvt_45_1_ * 6.0D - 15.0D) + 10.0D);

               for(int lvt_51_1_ = 0; lvt_51_1_ < p_76308_9_; ++lvt_51_1_) {
                  double lvt_52_1_ = p_76308_4_ + (double)lvt_51_1_ * p_76308_13_ + this.field_76313_b;
                  int lvt_54_1_ = (int)lvt_52_1_;
                  if (lvt_52_1_ < (double)lvt_54_1_) {
                     --lvt_54_1_;
                  }

                  int lvt_55_1_ = lvt_54_1_ & 255;
                  lvt_52_1_ -= (double)lvt_54_1_;
                  double lvt_56_1_ = lvt_52_1_ * lvt_52_1_ * lvt_52_1_ * (lvt_52_1_ * (lvt_52_1_ * 6.0D - 15.0D) + 10.0D);
                  if (lvt_51_1_ == 0 || lvt_55_1_ != lvt_22_2_) {
                     lvt_22_2_ = lvt_55_1_;
                     int lvt_23_2_ = this.field_76312_d[lvt_41_2_] + lvt_55_1_;
                     int lvt_24_1_ = this.field_76312_d[lvt_23_2_] + lvt_48_1_;
                     int lvt_25_2_ = this.field_76312_d[lvt_23_2_ + 1] + lvt_48_1_;
                     int lvt_26_1_ = this.field_76312_d[lvt_41_2_ + 1] + lvt_55_1_;
                     lvt_27_2_ = this.field_76312_d[lvt_26_1_] + lvt_48_1_;
                     int lvt_28_2_ = this.field_76312_d[lvt_26_1_ + 1] + lvt_48_1_;
                     lvt_29_1_ = this.func_76311_b(lvt_42_2_, this.func_76310_a(this.field_76312_d[lvt_24_1_], lvt_38_2_, lvt_52_1_, lvt_45_1_), this.func_76310_a(this.field_76312_d[lvt_27_2_], lvt_38_2_ - 1.0D, lvt_52_1_, lvt_45_1_));
                     lvt_31_2_ = this.func_76311_b(lvt_42_2_, this.func_76310_a(this.field_76312_d[lvt_25_2_], lvt_38_2_, lvt_52_1_ - 1.0D, lvt_45_1_), this.func_76310_a(this.field_76312_d[lvt_28_2_], lvt_38_2_ - 1.0D, lvt_52_1_ - 1.0D, lvt_45_1_));
                     lvt_33_2_ = this.func_76311_b(lvt_42_2_, this.func_76310_a(this.field_76312_d[lvt_24_1_ + 1], lvt_38_2_, lvt_52_1_, lvt_45_1_ - 1.0D), this.func_76310_a(this.field_76312_d[lvt_27_2_ + 1], lvt_38_2_ - 1.0D, lvt_52_1_, lvt_45_1_ - 1.0D));
                     lvt_35_2_ = this.func_76311_b(lvt_42_2_, this.func_76310_a(this.field_76312_d[lvt_25_2_ + 1], lvt_38_2_, lvt_52_1_ - 1.0D, lvt_45_1_ - 1.0D), this.func_76310_a(this.field_76312_d[lvt_28_2_ + 1], lvt_38_2_ - 1.0D, lvt_52_1_ - 1.0D, lvt_45_1_ - 1.0D));
                  }

                  double lvt_58_1_ = this.func_76311_b(lvt_56_1_, lvt_29_1_, lvt_31_2_);
                  double lvt_60_1_ = this.func_76311_b(lvt_56_1_, lvt_33_2_, lvt_35_2_);
                  double lvt_62_1_ = this.func_76311_b(lvt_49_1_, lvt_58_1_, lvt_60_1_);
                  var10001 = lvt_19_1_++;
                  p_76308_1_[var10001] += lvt_62_1_ * lvt_20_2_;
               }
            }
         }

      }
   }
}
