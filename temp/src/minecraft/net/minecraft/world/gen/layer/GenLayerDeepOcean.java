package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerDeepOcean extends GenLayer {
   public GenLayerDeepOcean(long p_i45472_1_, GenLayer p_i45472_3_) {
      super(p_i45472_1_);
      this.field_75909_a = p_i45472_3_;
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
            int lvt_18_1_ = 0;
            if (lvt_13_1_ == 0) {
               ++lvt_18_1_;
            }

            if (lvt_14_1_ == 0) {
               ++lvt_18_1_;
            }

            if (lvt_15_1_ == 0) {
               ++lvt_18_1_;
            }

            if (lvt_16_1_ == 0) {
               ++lvt_18_1_;
            }

            if (lvt_17_1_ == 0 && lvt_18_1_ > 3) {
               lvt_10_1_[lvt_12_1_ + lvt_11_1_ * p_75904_3_] = BiomeGenBase.field_150575_M.field_76756_M;
            } else {
               lvt_10_1_[lvt_12_1_ + lvt_11_1_ * p_75904_3_] = lvt_17_1_;
            }
         }
      }

      return lvt_10_1_;
   }
}
