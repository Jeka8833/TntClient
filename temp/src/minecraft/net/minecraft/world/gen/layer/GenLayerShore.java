package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;

public class GenLayerShore extends GenLayer {
   public GenLayerShore(long p_i2130_1_, GenLayer p_i2130_3_) {
      super(p_i2130_1_);
      this.field_75909_a = p_i2130_3_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] lvt_5_1_ = this.field_75909_a.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] lvt_6_1_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int lvt_7_1_ = 0; lvt_7_1_ < p_75904_4_; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < p_75904_3_; ++lvt_8_1_) {
            this.func_75903_a((long)(lvt_8_1_ + p_75904_1_), (long)(lvt_7_1_ + p_75904_2_));
            int lvt_9_1_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
            BiomeGenBase lvt_10_1_ = BiomeGenBase.func_150568_d(lvt_9_1_);
            int lvt_11_3_;
            int lvt_12_3_;
            int lvt_13_3_;
            int lvt_14_3_;
            if (lvt_9_1_ == BiomeGenBase.field_76789_p.field_76756_M) {
               lvt_11_3_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 - 1) * (p_75904_3_ + 2)];
               lvt_12_3_ = lvt_5_1_[lvt_8_1_ + 1 + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
               lvt_13_3_ = lvt_5_1_[lvt_8_1_ + 1 - 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
               lvt_14_3_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 + 1) * (p_75904_3_ + 2)];
               if (lvt_11_3_ != BiomeGenBase.field_76771_b.field_76756_M && lvt_12_3_ != BiomeGenBase.field_76771_b.field_76756_M && lvt_13_3_ != BiomeGenBase.field_76771_b.field_76756_M && lvt_14_3_ != BiomeGenBase.field_76771_b.field_76756_M) {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
               } else {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_76788_q.field_76756_M;
               }
            } else if (lvt_10_1_ != null && lvt_10_1_.func_150562_l() == BiomeGenJungle.class) {
               lvt_11_3_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 - 1) * (p_75904_3_ + 2)];
               lvt_12_3_ = lvt_5_1_[lvt_8_1_ + 1 + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
               lvt_13_3_ = lvt_5_1_[lvt_8_1_ + 1 - 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
               lvt_14_3_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 + 1) * (p_75904_3_ + 2)];
               if (this.func_151631_c(lvt_11_3_) && this.func_151631_c(lvt_12_3_) && this.func_151631_c(lvt_13_3_) && this.func_151631_c(lvt_14_3_)) {
                  if (!func_151618_b(lvt_11_3_) && !func_151618_b(lvt_12_3_) && !func_151618_b(lvt_13_3_) && !func_151618_b(lvt_14_3_)) {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
                  } else {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_76787_r.field_76756_M;
                  }
               } else {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_150574_L.field_76756_M;
               }
            } else if (lvt_9_1_ != BiomeGenBase.field_76770_e.field_76756_M && lvt_9_1_ != BiomeGenBase.field_150580_W.field_76756_M && lvt_9_1_ != BiomeGenBase.field_76783_v.field_76756_M) {
               if (lvt_10_1_ != null && lvt_10_1_.func_150559_j()) {
                  this.func_151632_a(lvt_5_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, p_75904_3_, lvt_9_1_, BiomeGenBase.field_150577_O.field_76756_M);
               } else if (lvt_9_1_ != BiomeGenBase.field_150589_Z.field_76756_M && lvt_9_1_ != BiomeGenBase.field_150607_aa.field_76756_M) {
                  if (lvt_9_1_ != BiomeGenBase.field_76771_b.field_76756_M && lvt_9_1_ != BiomeGenBase.field_150575_M.field_76756_M && lvt_9_1_ != BiomeGenBase.field_76781_i.field_76756_M && lvt_9_1_ != BiomeGenBase.field_76780_h.field_76756_M) {
                     lvt_11_3_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 - 1) * (p_75904_3_ + 2)];
                     lvt_12_3_ = lvt_5_1_[lvt_8_1_ + 1 + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
                     lvt_13_3_ = lvt_5_1_[lvt_8_1_ + 1 - 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
                     lvt_14_3_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 + 1) * (p_75904_3_ + 2)];
                     if (!func_151618_b(lvt_11_3_) && !func_151618_b(lvt_12_3_) && !func_151618_b(lvt_13_3_) && !func_151618_b(lvt_14_3_)) {
                        lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
                     } else {
                        lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_76787_r.field_76756_M;
                     }
                  } else {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
                  }
               } else {
                  lvt_11_3_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 - 1) * (p_75904_3_ + 2)];
                  lvt_12_3_ = lvt_5_1_[lvt_8_1_ + 1 + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
                  lvt_13_3_ = lvt_5_1_[lvt_8_1_ + 1 - 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
                  lvt_14_3_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 + 1) * (p_75904_3_ + 2)];
                  if (!func_151618_b(lvt_11_3_) && !func_151618_b(lvt_12_3_) && !func_151618_b(lvt_13_3_) && !func_151618_b(lvt_14_3_)) {
                     if (this.func_151633_d(lvt_11_3_) && this.func_151633_d(lvt_12_3_) && this.func_151633_d(lvt_13_3_) && this.func_151633_d(lvt_14_3_)) {
                        lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
                     } else {
                        lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_76769_d.field_76756_M;
                     }
                  } else {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
                  }
               }
            } else {
               this.func_151632_a(lvt_5_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, p_75904_3_, lvt_9_1_, BiomeGenBase.field_150576_N.field_76756_M);
            }
         }
      }

      return lvt_6_1_;
   }

   private void func_151632_a(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_) {
      if (func_151618_b(p_151632_6_)) {
         p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
      } else {
         int lvt_8_1_ = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
         int lvt_9_1_ = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
         int lvt_10_1_ = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
         int lvt_11_1_ = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];
         if (!func_151618_b(lvt_8_1_) && !func_151618_b(lvt_9_1_) && !func_151618_b(lvt_10_1_) && !func_151618_b(lvt_11_1_)) {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
         } else {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
         }

      }
   }

   private boolean func_151631_c(int p_151631_1_) {
      if (BiomeGenBase.func_150568_d(p_151631_1_) != null && BiomeGenBase.func_150568_d(p_151631_1_).func_150562_l() == BiomeGenJungle.class) {
         return true;
      } else {
         return p_151631_1_ == BiomeGenBase.field_150574_L.field_76756_M || p_151631_1_ == BiomeGenBase.field_76782_w.field_76756_M || p_151631_1_ == BiomeGenBase.field_76792_x.field_76756_M || p_151631_1_ == BiomeGenBase.field_76767_f.field_76756_M || p_151631_1_ == BiomeGenBase.field_76768_g.field_76756_M || func_151618_b(p_151631_1_);
      }
   }

   private boolean func_151633_d(int p_151633_1_) {
      return BiomeGenBase.func_150568_d(p_151633_1_) instanceof BiomeGenMesa;
   }
}
