package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerRareBiome extends GenLayer {
   public GenLayerRareBiome(long p_i45478_1_, GenLayer p_i45478_3_) {
      super(p_i45478_1_);
      this.field_75909_a = p_i45478_3_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] lvt_5_1_ = this.field_75909_a.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] lvt_6_1_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int lvt_7_1_ = 0; lvt_7_1_ < p_75904_4_; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < p_75904_3_; ++lvt_8_1_) {
            this.func_75903_a((long)(lvt_8_1_ + p_75904_1_), (long)(lvt_7_1_ + p_75904_2_));
            int lvt_9_1_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
            if (this.func_75902_a(57) == 0) {
               if (lvt_9_1_ == BiomeGenBase.field_76772_c.field_76756_M) {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_76772_c.field_76756_M + 128;
               } else {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
               }
            } else {
               lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
            }
         }
      }

      return lvt_6_1_;
   }
}
