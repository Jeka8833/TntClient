package net.minecraft.util;

import net.minecraft.client.Minecraft;

public class Timer {
   float field_74282_a;
   private double field_74276_f;
   public int field_74280_b;
   public float field_74281_c;
   public float field_74278_d = 1.0F;
   public float field_74279_e;
   private long field_74277_g;
   private long field_74284_h;
   private long field_74285_i;
   private double field_74283_j = 1.0D;

   public Timer(float p_i1018_1_) {
      this.field_74282_a = p_i1018_1_;
      this.field_74277_g = Minecraft.func_71386_F();
      this.field_74284_h = System.nanoTime() / 1000000L;
   }

   public void func_74275_a() {
      long lvt_1_1_ = Minecraft.func_71386_F();
      long lvt_3_1_ = lvt_1_1_ - this.field_74277_g;
      long lvt_5_1_ = System.nanoTime() / 1000000L;
      double lvt_7_1_ = (double)lvt_5_1_ / 1000.0D;
      if (lvt_3_1_ <= 1000L && lvt_3_1_ >= 0L) {
         this.field_74285_i += lvt_3_1_;
         if (this.field_74285_i > 1000L) {
            long lvt_9_1_ = lvt_5_1_ - this.field_74284_h;
            double lvt_11_1_ = (double)this.field_74285_i / (double)lvt_9_1_;
            this.field_74283_j += (lvt_11_1_ - this.field_74283_j) * 0.20000000298023224D;
            this.field_74284_h = lvt_5_1_;
            this.field_74285_i = 0L;
         }

         if (this.field_74285_i < 0L) {
            this.field_74284_h = lvt_5_1_;
         }
      } else {
         this.field_74276_f = lvt_7_1_;
      }

      this.field_74277_g = lvt_1_1_;
      double lvt_9_2_ = (lvt_7_1_ - this.field_74276_f) * this.field_74283_j;
      this.field_74276_f = lvt_7_1_;
      lvt_9_2_ = MathHelper.func_151237_a(lvt_9_2_, 0.0D, 1.0D);
      this.field_74279_e = (float)((double)this.field_74279_e + lvt_9_2_ * (double)this.field_74278_d * (double)this.field_74282_a);
      this.field_74280_b = (int)this.field_74279_e;
      this.field_74279_e -= (float)this.field_74280_b;
      if (this.field_74280_b > 10) {
         this.field_74280_b = 10;
      }

      this.field_74281_c = this.field_74279_e;
   }
}
