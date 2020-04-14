package net.minecraft.world.gen.layer;

public class GenLayerIsland extends GenLayer {
   public GenLayerIsland(long p_i2124_1_) {
      super(p_i2124_1_);
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] lvt_5_1_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int lvt_6_1_ = 0; lvt_6_1_ < p_75904_4_; ++lvt_6_1_) {
         for(int lvt_7_1_ = 0; lvt_7_1_ < p_75904_3_; ++lvt_7_1_) {
            this.func_75903_a((long)(p_75904_1_ + lvt_7_1_), (long)(p_75904_2_ + lvt_6_1_));
            lvt_5_1_[lvt_7_1_ + lvt_6_1_ * p_75904_3_] = this.func_75902_a(10) == 0 ? 1 : 0;
         }
      }

      if (p_75904_1_ > -p_75904_3_ && p_75904_1_ <= 0 && p_75904_2_ > -p_75904_4_ && p_75904_2_ <= 0) {
         lvt_5_1_[-p_75904_1_ + -p_75904_2_ * p_75904_3_] = 1;
      }

      return lvt_5_1_;
   }
}
