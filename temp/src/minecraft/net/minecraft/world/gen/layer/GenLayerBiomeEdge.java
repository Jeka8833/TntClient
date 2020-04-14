package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerBiomeEdge extends GenLayer {
   public GenLayerBiomeEdge(long p_i45475_1_, GenLayer p_i45475_3_) {
      super(p_i45475_1_);
      this.field_75909_a = p_i45475_3_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] lvt_5_1_ = this.field_75909_a.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] lvt_6_1_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int lvt_7_1_ = 0; lvt_7_1_ < p_75904_4_; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < p_75904_3_; ++lvt_8_1_) {
            this.func_75903_a((long)(lvt_8_1_ + p_75904_1_), (long)(lvt_7_1_ + p_75904_2_));
            int lvt_9_1_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
            if (!this.func_151636_a(lvt_5_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, p_75904_3_, lvt_9_1_, BiomeGenBase.field_76770_e.field_76756_M, BiomeGenBase.field_76783_v.field_76756_M) && !this.func_151635_b(lvt_5_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, p_75904_3_, lvt_9_1_, BiomeGenBase.field_150607_aa.field_76756_M, BiomeGenBase.field_150589_Z.field_76756_M) && !this.func_151635_b(lvt_5_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, p_75904_3_, lvt_9_1_, BiomeGenBase.field_150608_ab.field_76756_M, BiomeGenBase.field_150589_Z.field_76756_M) && !this.func_151635_b(lvt_5_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, p_75904_3_, lvt_9_1_, BiomeGenBase.field_150578_U.field_76756_M, BiomeGenBase.field_76768_g.field_76756_M)) {
               int lvt_10_2_;
               int lvt_11_2_;
               int lvt_12_2_;
               int lvt_13_2_;
               if (lvt_9_1_ == BiomeGenBase.field_76769_d.field_76756_M) {
                  lvt_10_2_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 - 1) * (p_75904_3_ + 2)];
                  lvt_11_2_ = lvt_5_1_[lvt_8_1_ + 1 + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
                  lvt_12_2_ = lvt_5_1_[lvt_8_1_ + 1 - 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
                  lvt_13_2_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 + 1) * (p_75904_3_ + 2)];
                  if (lvt_10_2_ != BiomeGenBase.field_76774_n.field_76756_M && lvt_11_2_ != BiomeGenBase.field_76774_n.field_76756_M && lvt_12_2_ != BiomeGenBase.field_76774_n.field_76756_M && lvt_13_2_ != BiomeGenBase.field_76774_n.field_76756_M) {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
                  } else {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_150580_W.field_76756_M;
                  }
               } else if (lvt_9_1_ == BiomeGenBase.field_76780_h.field_76756_M) {
                  lvt_10_2_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 - 1) * (p_75904_3_ + 2)];
                  lvt_11_2_ = lvt_5_1_[lvt_8_1_ + 1 + 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
                  lvt_12_2_ = lvt_5_1_[lvt_8_1_ + 1 - 1 + (lvt_7_1_ + 1) * (p_75904_3_ + 2)];
                  lvt_13_2_ = lvt_5_1_[lvt_8_1_ + 1 + (lvt_7_1_ + 1 + 1) * (p_75904_3_ + 2)];
                  if (lvt_10_2_ != BiomeGenBase.field_76769_d.field_76756_M && lvt_11_2_ != BiomeGenBase.field_76769_d.field_76756_M && lvt_12_2_ != BiomeGenBase.field_76769_d.field_76756_M && lvt_13_2_ != BiomeGenBase.field_76769_d.field_76756_M && lvt_10_2_ != BiomeGenBase.field_150584_S.field_76756_M && lvt_11_2_ != BiomeGenBase.field_150584_S.field_76756_M && lvt_12_2_ != BiomeGenBase.field_150584_S.field_76756_M && lvt_13_2_ != BiomeGenBase.field_150584_S.field_76756_M && lvt_10_2_ != BiomeGenBase.field_76774_n.field_76756_M && lvt_11_2_ != BiomeGenBase.field_76774_n.field_76756_M && lvt_12_2_ != BiomeGenBase.field_76774_n.field_76756_M && lvt_13_2_ != BiomeGenBase.field_76774_n.field_76756_M) {
                     if (lvt_10_2_ != BiomeGenBase.field_76782_w.field_76756_M && lvt_13_2_ != BiomeGenBase.field_76782_w.field_76756_M && lvt_11_2_ != BiomeGenBase.field_76782_w.field_76756_M && lvt_12_2_ != BiomeGenBase.field_76782_w.field_76756_M) {
                        lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
                     } else {
                        lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_150574_L.field_76756_M;
                     }
                  } else {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_76772_c.field_76756_M;
                  }
               } else {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
               }
            }
         }
      }

      return lvt_6_1_;
   }

   private boolean func_151636_a(int[] p_151636_1_, int[] p_151636_2_, int p_151636_3_, int p_151636_4_, int p_151636_5_, int p_151636_6_, int p_151636_7_, int p_151636_8_) {
      if (!func_151616_a(p_151636_6_, p_151636_7_)) {
         return false;
      } else {
         int lvt_9_1_ = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 - 1) * (p_151636_5_ + 2)];
         int lvt_10_1_ = p_151636_1_[p_151636_3_ + 1 + 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
         int lvt_11_1_ = p_151636_1_[p_151636_3_ + 1 - 1 + (p_151636_4_ + 1) * (p_151636_5_ + 2)];
         int lvt_12_1_ = p_151636_1_[p_151636_3_ + 1 + (p_151636_4_ + 1 + 1) * (p_151636_5_ + 2)];
         if (this.func_151634_b(lvt_9_1_, p_151636_7_) && this.func_151634_b(lvt_10_1_, p_151636_7_) && this.func_151634_b(lvt_11_1_, p_151636_7_) && this.func_151634_b(lvt_12_1_, p_151636_7_)) {
            p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_6_;
         } else {
            p_151636_2_[p_151636_3_ + p_151636_4_ * p_151636_5_] = p_151636_8_;
         }

         return true;
      }
   }

   private boolean func_151635_b(int[] p_151635_1_, int[] p_151635_2_, int p_151635_3_, int p_151635_4_, int p_151635_5_, int p_151635_6_, int p_151635_7_, int p_151635_8_) {
      if (p_151635_6_ != p_151635_7_) {
         return false;
      } else {
         int lvt_9_1_ = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 - 1) * (p_151635_5_ + 2)];
         int lvt_10_1_ = p_151635_1_[p_151635_3_ + 1 + 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
         int lvt_11_1_ = p_151635_1_[p_151635_3_ + 1 - 1 + (p_151635_4_ + 1) * (p_151635_5_ + 2)];
         int lvt_12_1_ = p_151635_1_[p_151635_3_ + 1 + (p_151635_4_ + 1 + 1) * (p_151635_5_ + 2)];
         if (func_151616_a(lvt_9_1_, p_151635_7_) && func_151616_a(lvt_10_1_, p_151635_7_) && func_151616_a(lvt_11_1_, p_151635_7_) && func_151616_a(lvt_12_1_, p_151635_7_)) {
            p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_6_;
         } else {
            p_151635_2_[p_151635_3_ + p_151635_4_ * p_151635_5_] = p_151635_8_;
         }

         return true;
      }
   }

   private boolean func_151634_b(int p_151634_1_, int p_151634_2_) {
      if (func_151616_a(p_151634_1_, p_151634_2_)) {
         return true;
      } else {
         BiomeGenBase lvt_3_1_ = BiomeGenBase.func_150568_d(p_151634_1_);
         BiomeGenBase lvt_4_1_ = BiomeGenBase.func_150568_d(p_151634_2_);
         if (lvt_3_1_ != null && lvt_4_1_ != null) {
            BiomeGenBase.TempCategory lvt_5_1_ = lvt_3_1_.func_150561_m();
            BiomeGenBase.TempCategory lvt_6_1_ = lvt_4_1_.func_150561_m();
            return lvt_5_1_ == lvt_6_1_ || lvt_5_1_ == BiomeGenBase.TempCategory.MEDIUM || lvt_6_1_ == BiomeGenBase.TempCategory.MEDIUM;
         } else {
            return false;
         }
      }
   }
}
