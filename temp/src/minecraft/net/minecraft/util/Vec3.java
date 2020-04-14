package net.minecraft.util;

public class Vec3 {
   public final double field_72450_a;
   public final double field_72448_b;
   public final double field_72449_c;

   public Vec3(double p_i1108_1_, double p_i1108_3_, double p_i1108_5_) {
      if (p_i1108_1_ == -0.0D) {
         p_i1108_1_ = 0.0D;
      }

      if (p_i1108_3_ == -0.0D) {
         p_i1108_3_ = 0.0D;
      }

      if (p_i1108_5_ == -0.0D) {
         p_i1108_5_ = 0.0D;
      }

      this.field_72450_a = p_i1108_1_;
      this.field_72448_b = p_i1108_3_;
      this.field_72449_c = p_i1108_5_;
   }

   public Vec3(Vec3i p_i46377_1_) {
      this((double)p_i46377_1_.func_177958_n(), (double)p_i46377_1_.func_177956_o(), (double)p_i46377_1_.func_177952_p());
   }

   public Vec3 func_72444_a(Vec3 p_72444_1_) {
      return new Vec3(p_72444_1_.field_72450_a - this.field_72450_a, p_72444_1_.field_72448_b - this.field_72448_b, p_72444_1_.field_72449_c - this.field_72449_c);
   }

   public Vec3 func_72432_b() {
      double lvt_1_1_ = (double)MathHelper.func_76133_a(this.field_72450_a * this.field_72450_a + this.field_72448_b * this.field_72448_b + this.field_72449_c * this.field_72449_c);
      return lvt_1_1_ < 1.0E-4D ? new Vec3(0.0D, 0.0D, 0.0D) : new Vec3(this.field_72450_a / lvt_1_1_, this.field_72448_b / lvt_1_1_, this.field_72449_c / lvt_1_1_);
   }

   public double func_72430_b(Vec3 p_72430_1_) {
      return this.field_72450_a * p_72430_1_.field_72450_a + this.field_72448_b * p_72430_1_.field_72448_b + this.field_72449_c * p_72430_1_.field_72449_c;
   }

   public Vec3 func_72431_c(Vec3 p_72431_1_) {
      return new Vec3(this.field_72448_b * p_72431_1_.field_72449_c - this.field_72449_c * p_72431_1_.field_72448_b, this.field_72449_c * p_72431_1_.field_72450_a - this.field_72450_a * p_72431_1_.field_72449_c, this.field_72450_a * p_72431_1_.field_72448_b - this.field_72448_b * p_72431_1_.field_72450_a);
   }

   public Vec3 func_178788_d(Vec3 p_178788_1_) {
      return this.func_178786_a(p_178788_1_.field_72450_a, p_178788_1_.field_72448_b, p_178788_1_.field_72449_c);
   }

   public Vec3 func_178786_a(double p_178786_1_, double p_178786_3_, double p_178786_5_) {
      return this.func_72441_c(-p_178786_1_, -p_178786_3_, -p_178786_5_);
   }

   public Vec3 func_178787_e(Vec3 p_178787_1_) {
      return this.func_72441_c(p_178787_1_.field_72450_a, p_178787_1_.field_72448_b, p_178787_1_.field_72449_c);
   }

   public Vec3 func_72441_c(double p_72441_1_, double p_72441_3_, double p_72441_5_) {
      return new Vec3(this.field_72450_a + p_72441_1_, this.field_72448_b + p_72441_3_, this.field_72449_c + p_72441_5_);
   }

   public double func_72438_d(Vec3 p_72438_1_) {
      double lvt_2_1_ = p_72438_1_.field_72450_a - this.field_72450_a;
      double lvt_4_1_ = p_72438_1_.field_72448_b - this.field_72448_b;
      double lvt_6_1_ = p_72438_1_.field_72449_c - this.field_72449_c;
      return (double)MathHelper.func_76133_a(lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_);
   }

   public double func_72436_e(Vec3 p_72436_1_) {
      double lvt_2_1_ = p_72436_1_.field_72450_a - this.field_72450_a;
      double lvt_4_1_ = p_72436_1_.field_72448_b - this.field_72448_b;
      double lvt_6_1_ = p_72436_1_.field_72449_c - this.field_72449_c;
      return lvt_2_1_ * lvt_2_1_ + lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_;
   }

   public double func_72433_c() {
      return (double)MathHelper.func_76133_a(this.field_72450_a * this.field_72450_a + this.field_72448_b * this.field_72448_b + this.field_72449_c * this.field_72449_c);
   }

   public Vec3 func_72429_b(Vec3 p_72429_1_, double p_72429_2_) {
      double lvt_4_1_ = p_72429_1_.field_72450_a - this.field_72450_a;
      double lvt_6_1_ = p_72429_1_.field_72448_b - this.field_72448_b;
      double lvt_8_1_ = p_72429_1_.field_72449_c - this.field_72449_c;
      if (lvt_4_1_ * lvt_4_1_ < 1.0000000116860974E-7D) {
         return null;
      } else {
         double lvt_10_1_ = (p_72429_2_ - this.field_72450_a) / lvt_4_1_;
         return lvt_10_1_ >= 0.0D && lvt_10_1_ <= 1.0D ? new Vec3(this.field_72450_a + lvt_4_1_ * lvt_10_1_, this.field_72448_b + lvt_6_1_ * lvt_10_1_, this.field_72449_c + lvt_8_1_ * lvt_10_1_) : null;
      }
   }

   public Vec3 func_72435_c(Vec3 p_72435_1_, double p_72435_2_) {
      double lvt_4_1_ = p_72435_1_.field_72450_a - this.field_72450_a;
      double lvt_6_1_ = p_72435_1_.field_72448_b - this.field_72448_b;
      double lvt_8_1_ = p_72435_1_.field_72449_c - this.field_72449_c;
      if (lvt_6_1_ * lvt_6_1_ < 1.0000000116860974E-7D) {
         return null;
      } else {
         double lvt_10_1_ = (p_72435_2_ - this.field_72448_b) / lvt_6_1_;
         return lvt_10_1_ >= 0.0D && lvt_10_1_ <= 1.0D ? new Vec3(this.field_72450_a + lvt_4_1_ * lvt_10_1_, this.field_72448_b + lvt_6_1_ * lvt_10_1_, this.field_72449_c + lvt_8_1_ * lvt_10_1_) : null;
      }
   }

   public Vec3 func_72434_d(Vec3 p_72434_1_, double p_72434_2_) {
      double lvt_4_1_ = p_72434_1_.field_72450_a - this.field_72450_a;
      double lvt_6_1_ = p_72434_1_.field_72448_b - this.field_72448_b;
      double lvt_8_1_ = p_72434_1_.field_72449_c - this.field_72449_c;
      if (lvt_8_1_ * lvt_8_1_ < 1.0000000116860974E-7D) {
         return null;
      } else {
         double lvt_10_1_ = (p_72434_2_ - this.field_72449_c) / lvt_8_1_;
         return lvt_10_1_ >= 0.0D && lvt_10_1_ <= 1.0D ? new Vec3(this.field_72450_a + lvt_4_1_ * lvt_10_1_, this.field_72448_b + lvt_6_1_ * lvt_10_1_, this.field_72449_c + lvt_8_1_ * lvt_10_1_) : null;
      }
   }

   public String toString() {
      return "(" + this.field_72450_a + ", " + this.field_72448_b + ", " + this.field_72449_c + ")";
   }

   public Vec3 func_178789_a(float p_178789_1_) {
      float lvt_2_1_ = MathHelper.func_76134_b(p_178789_1_);
      float lvt_3_1_ = MathHelper.func_76126_a(p_178789_1_);
      double lvt_4_1_ = this.field_72450_a;
      double lvt_6_1_ = this.field_72448_b * (double)lvt_2_1_ + this.field_72449_c * (double)lvt_3_1_;
      double lvt_8_1_ = this.field_72449_c * (double)lvt_2_1_ - this.field_72448_b * (double)lvt_3_1_;
      return new Vec3(lvt_4_1_, lvt_6_1_, lvt_8_1_);
   }

   public Vec3 func_178785_b(float p_178785_1_) {
      float lvt_2_1_ = MathHelper.func_76134_b(p_178785_1_);
      float lvt_3_1_ = MathHelper.func_76126_a(p_178785_1_);
      double lvt_4_1_ = this.field_72450_a * (double)lvt_2_1_ + this.field_72449_c * (double)lvt_3_1_;
      double lvt_6_1_ = this.field_72448_b;
      double lvt_8_1_ = this.field_72449_c * (double)lvt_2_1_ - this.field_72450_a * (double)lvt_3_1_;
      return new Vec3(lvt_4_1_, lvt_6_1_, lvt_8_1_);
   }
}
