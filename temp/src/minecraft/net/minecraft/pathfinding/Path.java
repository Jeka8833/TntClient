package net.minecraft.pathfinding;

public class Path {
   private PathPoint[] field_75852_a = new PathPoint[1024];
   private int field_75851_b;

   public PathPoint func_75849_a(PathPoint p_75849_1_) {
      if (p_75849_1_.field_75835_d >= 0) {
         throw new IllegalStateException("OW KNOWS!");
      } else {
         if (this.field_75851_b == this.field_75852_a.length) {
            PathPoint[] lvt_2_1_ = new PathPoint[this.field_75851_b << 1];
            System.arraycopy(this.field_75852_a, 0, lvt_2_1_, 0, this.field_75851_b);
            this.field_75852_a = lvt_2_1_;
         }

         this.field_75852_a[this.field_75851_b] = p_75849_1_;
         p_75849_1_.field_75835_d = this.field_75851_b;
         this.func_75847_a(this.field_75851_b++);
         return p_75849_1_;
      }
   }

   public void func_75848_a() {
      this.field_75851_b = 0;
   }

   public PathPoint func_75844_c() {
      PathPoint lvt_1_1_ = this.field_75852_a[0];
      this.field_75852_a[0] = this.field_75852_a[--this.field_75851_b];
      this.field_75852_a[this.field_75851_b] = null;
      if (this.field_75851_b > 0) {
         this.func_75846_b(0);
      }

      lvt_1_1_.field_75835_d = -1;
      return lvt_1_1_;
   }

   public void func_75850_a(PathPoint p_75850_1_, float p_75850_2_) {
      float lvt_3_1_ = p_75850_1_.field_75834_g;
      p_75850_1_.field_75834_g = p_75850_2_;
      if (p_75850_2_ < lvt_3_1_) {
         this.func_75847_a(p_75850_1_.field_75835_d);
      } else {
         this.func_75846_b(p_75850_1_.field_75835_d);
      }

   }

   private void func_75847_a(int p_75847_1_) {
      PathPoint lvt_2_1_ = this.field_75852_a[p_75847_1_];

      int lvt_4_1_;
      for(float lvt_3_1_ = lvt_2_1_.field_75834_g; p_75847_1_ > 0; p_75847_1_ = lvt_4_1_) {
         lvt_4_1_ = p_75847_1_ - 1 >> 1;
         PathPoint lvt_5_1_ = this.field_75852_a[lvt_4_1_];
         if (lvt_3_1_ >= lvt_5_1_.field_75834_g) {
            break;
         }

         this.field_75852_a[p_75847_1_] = lvt_5_1_;
         lvt_5_1_.field_75835_d = p_75847_1_;
      }

      this.field_75852_a[p_75847_1_] = lvt_2_1_;
      lvt_2_1_.field_75835_d = p_75847_1_;
   }

   private void func_75846_b(int p_75846_1_) {
      PathPoint lvt_2_1_ = this.field_75852_a[p_75846_1_];
      float lvt_3_1_ = lvt_2_1_.field_75834_g;

      while(true) {
         int lvt_4_1_ = 1 + (p_75846_1_ << 1);
         int lvt_5_1_ = lvt_4_1_ + 1;
         if (lvt_4_1_ >= this.field_75851_b) {
            break;
         }

         PathPoint lvt_6_1_ = this.field_75852_a[lvt_4_1_];
         float lvt_7_1_ = lvt_6_1_.field_75834_g;
         PathPoint lvt_8_2_;
         float lvt_9_2_;
         if (lvt_5_1_ >= this.field_75851_b) {
            lvt_8_2_ = null;
            lvt_9_2_ = Float.POSITIVE_INFINITY;
         } else {
            lvt_8_2_ = this.field_75852_a[lvt_5_1_];
            lvt_9_2_ = lvt_8_2_.field_75834_g;
         }

         if (lvt_7_1_ < lvt_9_2_) {
            if (lvt_7_1_ >= lvt_3_1_) {
               break;
            }

            this.field_75852_a[p_75846_1_] = lvt_6_1_;
            lvt_6_1_.field_75835_d = p_75846_1_;
            p_75846_1_ = lvt_4_1_;
         } else {
            if (lvt_9_2_ >= lvt_3_1_) {
               break;
            }

            this.field_75852_a[p_75846_1_] = lvt_8_2_;
            lvt_8_2_.field_75835_d = p_75846_1_;
            p_75846_1_ = lvt_5_1_;
         }
      }

      this.field_75852_a[p_75846_1_] = lvt_2_1_;
      lvt_2_1_.field_75835_d = p_75846_1_;
   }

   public boolean func_75845_e() {
      return this.field_75851_b == 0;
   }
}
