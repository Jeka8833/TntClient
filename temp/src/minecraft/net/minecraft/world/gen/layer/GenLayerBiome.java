package net.minecraft.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;

public class GenLayerBiome extends GenLayer {
   private BiomeGenBase[] field_151623_c;
   private BiomeGenBase[] field_151621_d;
   private BiomeGenBase[] field_151622_e;
   private BiomeGenBase[] field_151620_f;
   private final ChunkProviderSettings field_175973_g;

   public GenLayerBiome(long p_i45560_1_, GenLayer p_i45560_3_, WorldType p_i45560_4_, String p_i45560_5_) {
      super(p_i45560_1_);
      this.field_151623_c = new BiomeGenBase[]{BiomeGenBase.field_76769_d, BiomeGenBase.field_76769_d, BiomeGenBase.field_76769_d, BiomeGenBase.field_150588_X, BiomeGenBase.field_150588_X, BiomeGenBase.field_76772_c};
      this.field_151621_d = new BiomeGenBase[]{BiomeGenBase.field_76767_f, BiomeGenBase.field_150585_R, BiomeGenBase.field_76770_e, BiomeGenBase.field_76772_c, BiomeGenBase.field_150583_P, BiomeGenBase.field_76780_h};
      this.field_151622_e = new BiomeGenBase[]{BiomeGenBase.field_76767_f, BiomeGenBase.field_76770_e, BiomeGenBase.field_76768_g, BiomeGenBase.field_76772_c};
      this.field_151620_f = new BiomeGenBase[]{BiomeGenBase.field_76774_n, BiomeGenBase.field_76774_n, BiomeGenBase.field_76774_n, BiomeGenBase.field_150584_S};
      this.field_75909_a = p_i45560_3_;
      if (p_i45560_4_ == WorldType.field_77136_e) {
         this.field_151623_c = new BiomeGenBase[]{BiomeGenBase.field_76769_d, BiomeGenBase.field_76767_f, BiomeGenBase.field_76770_e, BiomeGenBase.field_76780_h, BiomeGenBase.field_76772_c, BiomeGenBase.field_76768_g};
         this.field_175973_g = null;
      } else if (p_i45560_4_ == WorldType.field_180271_f) {
         this.field_175973_g = ChunkProviderSettings.Factory.func_177865_a(p_i45560_5_).func_177864_b();
      } else {
         this.field_175973_g = null;
      }

   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] lvt_5_1_ = this.field_75909_a.func_75904_a(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
      int[] lvt_6_1_ = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int lvt_7_1_ = 0; lvt_7_1_ < p_75904_4_; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < p_75904_3_; ++lvt_8_1_) {
            this.func_75903_a((long)(lvt_8_1_ + p_75904_1_), (long)(lvt_7_1_ + p_75904_2_));
            int lvt_9_1_ = lvt_5_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_];
            int lvt_10_1_ = (lvt_9_1_ & 3840) >> 8;
            lvt_9_1_ &= -3841;
            if (this.field_175973_g != null && this.field_175973_g.field_177779_F >= 0) {
               lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = this.field_175973_g.field_177779_F;
            } else if (func_151618_b(lvt_9_1_)) {
               lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
            } else if (lvt_9_1_ == BiomeGenBase.field_76789_p.field_76756_M) {
               lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = lvt_9_1_;
            } else if (lvt_9_1_ == 1) {
               if (lvt_10_1_ > 0) {
                  if (this.func_75902_a(3) == 0) {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_150608_ab.field_76756_M;
                  } else {
                     lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_150607_aa.field_76756_M;
                  }
               } else {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = this.field_151623_c[this.func_75902_a(this.field_151623_c.length)].field_76756_M;
               }
            } else if (lvt_9_1_ == 2) {
               if (lvt_10_1_ > 0) {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_76782_w.field_76756_M;
               } else {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = this.field_151621_d[this.func_75902_a(this.field_151621_d.length)].field_76756_M;
               }
            } else if (lvt_9_1_ == 3) {
               if (lvt_10_1_ > 0) {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_150578_U.field_76756_M;
               } else {
                  lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = this.field_151622_e[this.func_75902_a(this.field_151622_e.length)].field_76756_M;
               }
            } else if (lvt_9_1_ == 4) {
               lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = this.field_151620_f[this.func_75902_a(this.field_151620_f.length)].field_76756_M;
            } else {
               lvt_6_1_[lvt_8_1_ + lvt_7_1_ * p_75904_3_] = BiomeGenBase.field_76789_p.field_76756_M;
            }
         }
      }

      return lvt_6_1_;
   }
}
