package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayerHills extends GenLayer {
   private static final Logger field_151629_c = LogManager.getLogger();
   private GenLayer field_151628_d;

   public GenLayerHills(long p_i45479_1_, GenLayer p_i45479_3_, GenLayer p_i45479_4_) {
      super(p_i45479_1_);
      this.field_75909_a = p_i45479_3_;
      this.field_151628_d = p_i45479_4_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] lvt_5_1_ = this.field_75909_a.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] lvt_6_1_ = this.field_151628_d.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] lvt_7_1_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int lvt_8_1_ = 0; lvt_8_1_ < p_75904_4_; ++lvt_8_1_) {
         for(int lvt_9_1_ = 0; lvt_9_1_ < p_75904_3_; ++lvt_9_1_) {
            this.func_75903_a((long)(lvt_9_1_ + p_75904_1_), (long)(lvt_8_1_ + p_75904_2_));
            int lvt_10_1_ = lvt_5_1_[lvt_9_1_ + 1 + (lvt_8_1_ + 1) * (p_75904_3_ + 2)];
            int lvt_11_1_ = lvt_6_1_[lvt_9_1_ + 1 + (lvt_8_1_ + 1) * (p_75904_3_ + 2)];
            boolean lvt_12_1_ = (lvt_11_1_ - 2) % 29 == 0;
            if (lvt_10_1_ > 255) {
               field_151629_c.debug("old! " + lvt_10_1_);
            }

            if (lvt_10_1_ != 0 && lvt_11_1_ >= 2 && (lvt_11_1_ - 2) % 29 == 1 && lvt_10_1_ < 128) {
               if (BiomeGenBase.func_150568_d(lvt_10_1_ + 128) != null) {
                  lvt_7_1_[lvt_9_1_ + lvt_8_1_ * p_75904_3_] = lvt_10_1_ + 128;
               } else {
                  lvt_7_1_[lvt_9_1_ + lvt_8_1_ * p_75904_3_] = lvt_10_1_;
               }
            } else if (this.func_75902_a(3) != 0 && !lvt_12_1_) {
               lvt_7_1_[lvt_9_1_ + lvt_8_1_ * p_75904_3_] = lvt_10_1_;
            } else {
               int lvt_13_1_ = lvt_10_1_;
               int lvt_14_1_;
               if (lvt_10_1_ == BiomeGenBase.field_76769_d.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_76786_s.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_76767_f.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_76785_t.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_150583_P.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_150582_Q.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_150585_R.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_76772_c.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_76768_g.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_76784_u.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_150578_U.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_150581_V.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_150584_S.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_150579_T.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_76772_c.field_76756_M) {
                  if (this.func_75902_a(3) == 0) {
                     lvt_13_1_ = BiomeGenBase.field_76785_t.field_76756_M;
                  } else {
                     lvt_13_1_ = BiomeGenBase.field_76767_f.field_76756_M;
                  }
               } else if (lvt_10_1_ == BiomeGenBase.field_76774_n.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_76775_o.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_76782_w.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_76792_x.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_76771_b.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_150575_M.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_76770_e.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_150580_W.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_150588_X.field_76756_M) {
                  lvt_13_1_ = BiomeGenBase.field_150587_Y.field_76756_M;
               } else if (func_151616_a(lvt_10_1_, BiomeGenBase.field_150607_aa.field_76756_M)) {
                  lvt_13_1_ = BiomeGenBase.field_150589_Z.field_76756_M;
               } else if (lvt_10_1_ == BiomeGenBase.field_150575_M.field_76756_M && this.func_75902_a(3) == 0) {
                  lvt_14_1_ = this.func_75902_a(2);
                  if (lvt_14_1_ == 0) {
                     lvt_13_1_ = BiomeGenBase.field_76772_c.field_76756_M;
                  } else {
                     lvt_13_1_ = BiomeGenBase.field_76767_f.field_76756_M;
                  }
               }

               if (lvt_12_1_ && lvt_13_1_ != lvt_10_1_) {
                  if (BiomeGenBase.func_150568_d(lvt_13_1_ + 128) != null) {
                     lvt_13_1_ += 128;
                  } else {
                     lvt_13_1_ = lvt_10_1_;
                  }
               }

               if (lvt_13_1_ == lvt_10_1_) {
                  lvt_7_1_[lvt_9_1_ + lvt_8_1_ * p_75904_3_] = lvt_10_1_;
               } else {
                  lvt_14_1_ = lvt_5_1_[lvt_9_1_ + 1 + (lvt_8_1_ + 1 - 1) * (p_75904_3_ + 2)];
                  int lvt_15_1_ = lvt_5_1_[lvt_9_1_ + 1 + 1 + (lvt_8_1_ + 1) * (p_75904_3_ + 2)];
                  int lvt_16_1_ = lvt_5_1_[lvt_9_1_ + 1 - 1 + (lvt_8_1_ + 1) * (p_75904_3_ + 2)];
                  int lvt_17_1_ = lvt_5_1_[lvt_9_1_ + 1 + (lvt_8_1_ + 1 + 1) * (p_75904_3_ + 2)];
                  int lvt_18_1_ = 0;
                  if (func_151616_a(lvt_14_1_, lvt_10_1_)) {
                     ++lvt_18_1_;
                  }

                  if (func_151616_a(lvt_15_1_, lvt_10_1_)) {
                     ++lvt_18_1_;
                  }

                  if (func_151616_a(lvt_16_1_, lvt_10_1_)) {
                     ++lvt_18_1_;
                  }

                  if (func_151616_a(lvt_17_1_, lvt_10_1_)) {
                     ++lvt_18_1_;
                  }

                  if (lvt_18_1_ >= 3) {
                     lvt_7_1_[lvt_9_1_ + lvt_8_1_ * p_75904_3_] = lvt_13_1_;
                  } else {
                     lvt_7_1_[lvt_9_1_ + lvt_8_1_ * p_75904_3_] = lvt_10_1_;
                  }
               }
            }
         }
      }

      return lvt_7_1_;
   }
}
