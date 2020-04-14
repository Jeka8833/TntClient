package net.minecraft.util;

public class AxisAlignedBB {
   public final double field_72340_a;
   public final double field_72338_b;
   public final double field_72339_c;
   public final double field_72336_d;
   public final double field_72337_e;
   public final double field_72334_f;

   public AxisAlignedBB(double p_i2300_1_, double p_i2300_3_, double p_i2300_5_, double p_i2300_7_, double p_i2300_9_, double p_i2300_11_) {
      this.field_72340_a = Math.min(p_i2300_1_, p_i2300_7_);
      this.field_72338_b = Math.min(p_i2300_3_, p_i2300_9_);
      this.field_72339_c = Math.min(p_i2300_5_, p_i2300_11_);
      this.field_72336_d = Math.max(p_i2300_1_, p_i2300_7_);
      this.field_72337_e = Math.max(p_i2300_3_, p_i2300_9_);
      this.field_72334_f = Math.max(p_i2300_5_, p_i2300_11_);
   }

   public AxisAlignedBB(BlockPos p_i45554_1_, BlockPos p_i45554_2_) {
      this.field_72340_a = (double)p_i45554_1_.func_177958_n();
      this.field_72338_b = (double)p_i45554_1_.func_177956_o();
      this.field_72339_c = (double)p_i45554_1_.func_177952_p();
      this.field_72336_d = (double)p_i45554_2_.func_177958_n();
      this.field_72337_e = (double)p_i45554_2_.func_177956_o();
      this.field_72334_f = (double)p_i45554_2_.func_177952_p();
   }

   public AxisAlignedBB func_72321_a(double p_72321_1_, double p_72321_3_, double p_72321_5_) {
      double lvt_7_1_ = this.field_72340_a;
      double lvt_9_1_ = this.field_72338_b;
      double lvt_11_1_ = this.field_72339_c;
      double lvt_13_1_ = this.field_72336_d;
      double lvt_15_1_ = this.field_72337_e;
      double lvt_17_1_ = this.field_72334_f;
      if (p_72321_1_ < 0.0D) {
         lvt_7_1_ += p_72321_1_;
      } else if (p_72321_1_ > 0.0D) {
         lvt_13_1_ += p_72321_1_;
      }

      if (p_72321_3_ < 0.0D) {
         lvt_9_1_ += p_72321_3_;
      } else if (p_72321_3_ > 0.0D) {
         lvt_15_1_ += p_72321_3_;
      }

      if (p_72321_5_ < 0.0D) {
         lvt_11_1_ += p_72321_5_;
      } else if (p_72321_5_ > 0.0D) {
         lvt_17_1_ += p_72321_5_;
      }

      return new AxisAlignedBB(lvt_7_1_, lvt_9_1_, lvt_11_1_, lvt_13_1_, lvt_15_1_, lvt_17_1_);
   }

   public AxisAlignedBB func_72314_b(double p_72314_1_, double p_72314_3_, double p_72314_5_) {
      double lvt_7_1_ = this.field_72340_a - p_72314_1_;
      double lvt_9_1_ = this.field_72338_b - p_72314_3_;
      double lvt_11_1_ = this.field_72339_c - p_72314_5_;
      double lvt_13_1_ = this.field_72336_d + p_72314_1_;
      double lvt_15_1_ = this.field_72337_e + p_72314_3_;
      double lvt_17_1_ = this.field_72334_f + p_72314_5_;
      return new AxisAlignedBB(lvt_7_1_, lvt_9_1_, lvt_11_1_, lvt_13_1_, lvt_15_1_, lvt_17_1_);
   }

   public AxisAlignedBB func_111270_a(AxisAlignedBB p_111270_1_) {
      double lvt_2_1_ = Math.min(this.field_72340_a, p_111270_1_.field_72340_a);
      double lvt_4_1_ = Math.min(this.field_72338_b, p_111270_1_.field_72338_b);
      double lvt_6_1_ = Math.min(this.field_72339_c, p_111270_1_.field_72339_c);
      double lvt_8_1_ = Math.max(this.field_72336_d, p_111270_1_.field_72336_d);
      double lvt_10_1_ = Math.max(this.field_72337_e, p_111270_1_.field_72337_e);
      double lvt_12_1_ = Math.max(this.field_72334_f, p_111270_1_.field_72334_f);
      return new AxisAlignedBB(lvt_2_1_, lvt_4_1_, lvt_6_1_, lvt_8_1_, lvt_10_1_, lvt_12_1_);
   }

   public static AxisAlignedBB func_178781_a(double p_178781_0_, double p_178781_2_, double p_178781_4_, double p_178781_6_, double p_178781_8_, double p_178781_10_) {
      double lvt_12_1_ = Math.min(p_178781_0_, p_178781_6_);
      double lvt_14_1_ = Math.min(p_178781_2_, p_178781_8_);
      double lvt_16_1_ = Math.min(p_178781_4_, p_178781_10_);
      double lvt_18_1_ = Math.max(p_178781_0_, p_178781_6_);
      double lvt_20_1_ = Math.max(p_178781_2_, p_178781_8_);
      double lvt_22_1_ = Math.max(p_178781_4_, p_178781_10_);
      return new AxisAlignedBB(lvt_12_1_, lvt_14_1_, lvt_16_1_, lvt_18_1_, lvt_20_1_, lvt_22_1_);
   }

   public AxisAlignedBB func_72317_d(double p_72317_1_, double p_72317_3_, double p_72317_5_) {
      return new AxisAlignedBB(this.field_72340_a + p_72317_1_, this.field_72338_b + p_72317_3_, this.field_72339_c + p_72317_5_, this.field_72336_d + p_72317_1_, this.field_72337_e + p_72317_3_, this.field_72334_f + p_72317_5_);
   }

   public double func_72316_a(AxisAlignedBB p_72316_1_, double p_72316_2_) {
      if (p_72316_1_.field_72337_e > this.field_72338_b && p_72316_1_.field_72338_b < this.field_72337_e && p_72316_1_.field_72334_f > this.field_72339_c && p_72316_1_.field_72339_c < this.field_72334_f) {
         double lvt_4_2_;
         if (p_72316_2_ > 0.0D && p_72316_1_.field_72336_d <= this.field_72340_a) {
            lvt_4_2_ = this.field_72340_a - p_72316_1_.field_72336_d;
            if (lvt_4_2_ < p_72316_2_) {
               p_72316_2_ = lvt_4_2_;
            }
         } else if (p_72316_2_ < 0.0D && p_72316_1_.field_72340_a >= this.field_72336_d) {
            lvt_4_2_ = this.field_72336_d - p_72316_1_.field_72340_a;
            if (lvt_4_2_ > p_72316_2_) {
               p_72316_2_ = lvt_4_2_;
            }
         }

         return p_72316_2_;
      } else {
         return p_72316_2_;
      }
   }

   public double func_72323_b(AxisAlignedBB p_72323_1_, double p_72323_2_) {
      if (p_72323_1_.field_72336_d > this.field_72340_a && p_72323_1_.field_72340_a < this.field_72336_d && p_72323_1_.field_72334_f > this.field_72339_c && p_72323_1_.field_72339_c < this.field_72334_f) {
         double lvt_4_2_;
         if (p_72323_2_ > 0.0D && p_72323_1_.field_72337_e <= this.field_72338_b) {
            lvt_4_2_ = this.field_72338_b - p_72323_1_.field_72337_e;
            if (lvt_4_2_ < p_72323_2_) {
               p_72323_2_ = lvt_4_2_;
            }
         } else if (p_72323_2_ < 0.0D && p_72323_1_.field_72338_b >= this.field_72337_e) {
            lvt_4_2_ = this.field_72337_e - p_72323_1_.field_72338_b;
            if (lvt_4_2_ > p_72323_2_) {
               p_72323_2_ = lvt_4_2_;
            }
         }

         return p_72323_2_;
      } else {
         return p_72323_2_;
      }
   }

   public double func_72322_c(AxisAlignedBB p_72322_1_, double p_72322_2_) {
      if (p_72322_1_.field_72336_d > this.field_72340_a && p_72322_1_.field_72340_a < this.field_72336_d && p_72322_1_.field_72337_e > this.field_72338_b && p_72322_1_.field_72338_b < this.field_72337_e) {
         double lvt_4_2_;
         if (p_72322_2_ > 0.0D && p_72322_1_.field_72334_f <= this.field_72339_c) {
            lvt_4_2_ = this.field_72339_c - p_72322_1_.field_72334_f;
            if (lvt_4_2_ < p_72322_2_) {
               p_72322_2_ = lvt_4_2_;
            }
         } else if (p_72322_2_ < 0.0D && p_72322_1_.field_72339_c >= this.field_72334_f) {
            lvt_4_2_ = this.field_72334_f - p_72322_1_.field_72339_c;
            if (lvt_4_2_ > p_72322_2_) {
               p_72322_2_ = lvt_4_2_;
            }
         }

         return p_72322_2_;
      } else {
         return p_72322_2_;
      }
   }

   public boolean func_72326_a(AxisAlignedBB p_72326_1_) {
      if (p_72326_1_.field_72336_d > this.field_72340_a && p_72326_1_.field_72340_a < this.field_72336_d) {
         if (p_72326_1_.field_72337_e > this.field_72338_b && p_72326_1_.field_72338_b < this.field_72337_e) {
            return p_72326_1_.field_72334_f > this.field_72339_c && p_72326_1_.field_72339_c < this.field_72334_f;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean func_72318_a(Vec3 p_72318_1_) {
      if (p_72318_1_.field_72450_a > this.field_72340_a && p_72318_1_.field_72450_a < this.field_72336_d) {
         if (p_72318_1_.field_72448_b > this.field_72338_b && p_72318_1_.field_72448_b < this.field_72337_e) {
            return p_72318_1_.field_72449_c > this.field_72339_c && p_72318_1_.field_72449_c < this.field_72334_f;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public double func_72320_b() {
      double lvt_1_1_ = this.field_72336_d - this.field_72340_a;
      double lvt_3_1_ = this.field_72337_e - this.field_72338_b;
      double lvt_5_1_ = this.field_72334_f - this.field_72339_c;
      return (lvt_1_1_ + lvt_3_1_ + lvt_5_1_) / 3.0D;
   }

   public AxisAlignedBB func_72331_e(double p_72331_1_, double p_72331_3_, double p_72331_5_) {
      double lvt_7_1_ = this.field_72340_a + p_72331_1_;
      double lvt_9_1_ = this.field_72338_b + p_72331_3_;
      double lvt_11_1_ = this.field_72339_c + p_72331_5_;
      double lvt_13_1_ = this.field_72336_d - p_72331_1_;
      double lvt_15_1_ = this.field_72337_e - p_72331_3_;
      double lvt_17_1_ = this.field_72334_f - p_72331_5_;
      return new AxisAlignedBB(lvt_7_1_, lvt_9_1_, lvt_11_1_, lvt_13_1_, lvt_15_1_, lvt_17_1_);
   }

   public MovingObjectPosition func_72327_a(Vec3 p_72327_1_, Vec3 p_72327_2_) {
      Vec3 lvt_3_1_ = p_72327_1_.func_72429_b(p_72327_2_, this.field_72340_a);
      Vec3 lvt_4_1_ = p_72327_1_.func_72429_b(p_72327_2_, this.field_72336_d);
      Vec3 lvt_5_1_ = p_72327_1_.func_72435_c(p_72327_2_, this.field_72338_b);
      Vec3 lvt_6_1_ = p_72327_1_.func_72435_c(p_72327_2_, this.field_72337_e);
      Vec3 lvt_7_1_ = p_72327_1_.func_72434_d(p_72327_2_, this.field_72339_c);
      Vec3 lvt_8_1_ = p_72327_1_.func_72434_d(p_72327_2_, this.field_72334_f);
      if (!this.func_72333_b(lvt_3_1_)) {
         lvt_3_1_ = null;
      }

      if (!this.func_72333_b(lvt_4_1_)) {
         lvt_4_1_ = null;
      }

      if (!this.func_72315_c(lvt_5_1_)) {
         lvt_5_1_ = null;
      }

      if (!this.func_72315_c(lvt_6_1_)) {
         lvt_6_1_ = null;
      }

      if (!this.func_72319_d(lvt_7_1_)) {
         lvt_7_1_ = null;
      }

      if (!this.func_72319_d(lvt_8_1_)) {
         lvt_8_1_ = null;
      }

      Vec3 lvt_9_1_ = null;
      if (lvt_3_1_ != null) {
         lvt_9_1_ = lvt_3_1_;
      }

      if (lvt_4_1_ != null && (lvt_9_1_ == null || p_72327_1_.func_72436_e(lvt_4_1_) < p_72327_1_.func_72436_e(lvt_9_1_))) {
         lvt_9_1_ = lvt_4_1_;
      }

      if (lvt_5_1_ != null && (lvt_9_1_ == null || p_72327_1_.func_72436_e(lvt_5_1_) < p_72327_1_.func_72436_e(lvt_9_1_))) {
         lvt_9_1_ = lvt_5_1_;
      }

      if (lvt_6_1_ != null && (lvt_9_1_ == null || p_72327_1_.func_72436_e(lvt_6_1_) < p_72327_1_.func_72436_e(lvt_9_1_))) {
         lvt_9_1_ = lvt_6_1_;
      }

      if (lvt_7_1_ != null && (lvt_9_1_ == null || p_72327_1_.func_72436_e(lvt_7_1_) < p_72327_1_.func_72436_e(lvt_9_1_))) {
         lvt_9_1_ = lvt_7_1_;
      }

      if (lvt_8_1_ != null && (lvt_9_1_ == null || p_72327_1_.func_72436_e(lvt_8_1_) < p_72327_1_.func_72436_e(lvt_9_1_))) {
         lvt_9_1_ = lvt_8_1_;
      }

      if (lvt_9_1_ == null) {
         return null;
      } else {
         EnumFacing lvt_10_1_ = null;
         if (lvt_9_1_ == lvt_3_1_) {
            lvt_10_1_ = EnumFacing.WEST;
         } else if (lvt_9_1_ == lvt_4_1_) {
            lvt_10_1_ = EnumFacing.EAST;
         } else if (lvt_9_1_ == lvt_5_1_) {
            lvt_10_1_ = EnumFacing.DOWN;
         } else if (lvt_9_1_ == lvt_6_1_) {
            lvt_10_1_ = EnumFacing.UP;
         } else if (lvt_9_1_ == lvt_7_1_) {
            lvt_10_1_ = EnumFacing.NORTH;
         } else {
            lvt_10_1_ = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(lvt_9_1_, lvt_10_1_);
      }
   }

   private boolean func_72333_b(Vec3 p_72333_1_) {
      if (p_72333_1_ == null) {
         return false;
      } else {
         return p_72333_1_.field_72448_b >= this.field_72338_b && p_72333_1_.field_72448_b <= this.field_72337_e && p_72333_1_.field_72449_c >= this.field_72339_c && p_72333_1_.field_72449_c <= this.field_72334_f;
      }
   }

   private boolean func_72315_c(Vec3 p_72315_1_) {
      if (p_72315_1_ == null) {
         return false;
      } else {
         return p_72315_1_.field_72450_a >= this.field_72340_a && p_72315_1_.field_72450_a <= this.field_72336_d && p_72315_1_.field_72449_c >= this.field_72339_c && p_72315_1_.field_72449_c <= this.field_72334_f;
      }
   }

   private boolean func_72319_d(Vec3 p_72319_1_) {
      if (p_72319_1_ == null) {
         return false;
      } else {
         return p_72319_1_.field_72450_a >= this.field_72340_a && p_72319_1_.field_72450_a <= this.field_72336_d && p_72319_1_.field_72448_b >= this.field_72338_b && p_72319_1_.field_72448_b <= this.field_72337_e;
      }
   }

   public String toString() {
      return "box[" + this.field_72340_a + ", " + this.field_72338_b + ", " + this.field_72339_c + " -> " + this.field_72336_d + ", " + this.field_72337_e + ", " + this.field_72334_f + "]";
   }

   public boolean func_181656_b() {
      return Double.isNaN(this.field_72340_a) || Double.isNaN(this.field_72338_b) || Double.isNaN(this.field_72339_c) || Double.isNaN(this.field_72336_d) || Double.isNaN(this.field_72337_e) || Double.isNaN(this.field_72334_f);
   }
}
