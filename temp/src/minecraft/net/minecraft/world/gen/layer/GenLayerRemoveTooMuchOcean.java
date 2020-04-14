package net.minecraft.world.gen.layer;

public class GenLayerRemoveTooMuchOcean extends GenLayer {
   public GenLayerRemoveTooMuchOcean(long p_i45480_1_, GenLayer p_i45480_3_) {
      super(p_i45480_1_);
      this.field_75909_a = p_i45480_3_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int lvt_5_1_ = p_75904_1_ - 1;
      int lvt_6_1_ = p_75904_2_ - 1;
      int lvt_7_1_ = p_75904_3_ + 2;
      int lvt_8_1_ = p_75904_4_ + 2;
      int[] lvt_9_1_ = this.field_75909_a.func_75904_a(lvt_5_1_, lvt_6_1_, lvt_7_1_, lvt_8_1_);
      int[] lvt_10_1_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int lvt_11_1_ = 0; lvt_11_1_ < p_75904_4_; ++lvt_11_1_) {
         for(int lvt_12_1_ = 0; lvt_12_1_ < p_75904_3_; ++lvt_12_1_) {
            int lvt_13_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1 - 1) * (p_75904_3_ + 2)];
            int lvt_14_1_ = lvt_9_1_[lvt_12_1_ + 1 + 1 + (lvt_11_1_ + 1) * (p_75904_3_ + 2)];
            int lvt_15_1_ = lvt_9_1_[lvt_12_1_ + 1 - 1 + (lvt_11_1_ + 1) * (p_75904_3_ + 2)];
            int lvt_16_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1 + 1) * (p_75904_3_ + 2)];
            int lvt_17_1_ = lvt_9_1_[lvt_12_1_ + 1 + (lvt_11_1_ + 1) * lvt_7_1_];
            lvt_10_1_[lvt_12_1_ + lvt_11_1_ * p_75904_3_] = lvt_17_1_;
            this.func_75903_a((long)(lvt_12_1_ + p_75904_1_), (long)(lvt_11_1_ + p_75904_2_));
            if (lvt_17_1_ == 0 && lvt_13_1_ == 0 && lvt_14_1_ == 0 && lvt_15_1_ == 0 && lvt_16_1_ == 0 && this.func_75902_a(2) == 0) {
               lvt_10_1_[lvt_12_1_ + lvt_11_1_ * p_75904_3_] = 1;
            }
         }
      }

      return lvt_10_1_;
   }
}
