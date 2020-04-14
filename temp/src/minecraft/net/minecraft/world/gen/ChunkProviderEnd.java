package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderEnd implements IChunkProvider {
   private Random field_73204_i;
   private NoiseGeneratorOctaves field_73201_j;
   private NoiseGeneratorOctaves field_73202_k;
   private NoiseGeneratorOctaves field_73199_l;
   public NoiseGeneratorOctaves field_73196_a;
   public NoiseGeneratorOctaves field_73194_b;
   private World field_73200_m;
   private double[] field_73197_n;
   private BiomeGenBase[] field_73198_o;
   double[] field_73195_c;
   double[] field_73192_d;
   double[] field_73193_e;
   double[] field_73190_f;
   double[] field_73191_g;

   public ChunkProviderEnd(World p_i2007_1_, long p_i2007_2_) {
      this.field_73200_m = p_i2007_1_;
      this.field_73204_i = new Random(p_i2007_2_);
      this.field_73201_j = new NoiseGeneratorOctaves(this.field_73204_i, 16);
      this.field_73202_k = new NoiseGeneratorOctaves(this.field_73204_i, 16);
      this.field_73199_l = new NoiseGeneratorOctaves(this.field_73204_i, 8);
      this.field_73196_a = new NoiseGeneratorOctaves(this.field_73204_i, 10);
      this.field_73194_b = new NoiseGeneratorOctaves(this.field_73204_i, 16);
   }

   public void func_180520_a(int p_180520_1_, int p_180520_2_, ChunkPrimer p_180520_3_) {
      int lvt_4_1_ = 2;
      int lvt_5_1_ = lvt_4_1_ + 1;
      int lvt_6_1_ = 33;
      int lvt_7_1_ = lvt_4_1_ + 1;
      this.field_73197_n = this.func_73187_a(this.field_73197_n, p_180520_1_ * lvt_4_1_, 0, p_180520_2_ * lvt_4_1_, lvt_5_1_, lvt_6_1_, lvt_7_1_);

      for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_4_1_; ++lvt_8_1_) {
         for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_4_1_; ++lvt_9_1_) {
            for(int lvt_10_1_ = 0; lvt_10_1_ < 32; ++lvt_10_1_) {
               double lvt_11_1_ = 0.25D;
               double lvt_13_1_ = this.field_73197_n[((lvt_8_1_ + 0) * lvt_7_1_ + lvt_9_1_ + 0) * lvt_6_1_ + lvt_10_1_ + 0];
               double lvt_15_1_ = this.field_73197_n[((lvt_8_1_ + 0) * lvt_7_1_ + lvt_9_1_ + 1) * lvt_6_1_ + lvt_10_1_ + 0];
               double lvt_17_1_ = this.field_73197_n[((lvt_8_1_ + 1) * lvt_7_1_ + lvt_9_1_ + 0) * lvt_6_1_ + lvt_10_1_ + 0];
               double lvt_19_1_ = this.field_73197_n[((lvt_8_1_ + 1) * lvt_7_1_ + lvt_9_1_ + 1) * lvt_6_1_ + lvt_10_1_ + 0];
               double lvt_21_1_ = (this.field_73197_n[((lvt_8_1_ + 0) * lvt_7_1_ + lvt_9_1_ + 0) * lvt_6_1_ + lvt_10_1_ + 1] - lvt_13_1_) * lvt_11_1_;
               double lvt_23_1_ = (this.field_73197_n[((lvt_8_1_ + 0) * lvt_7_1_ + lvt_9_1_ + 1) * lvt_6_1_ + lvt_10_1_ + 1] - lvt_15_1_) * lvt_11_1_;
               double lvt_25_1_ = (this.field_73197_n[((lvt_8_1_ + 1) * lvt_7_1_ + lvt_9_1_ + 0) * lvt_6_1_ + lvt_10_1_ + 1] - lvt_17_1_) * lvt_11_1_;
               double lvt_27_1_ = (this.field_73197_n[((lvt_8_1_ + 1) * lvt_7_1_ + lvt_9_1_ + 1) * lvt_6_1_ + lvt_10_1_ + 1] - lvt_19_1_) * lvt_11_1_;

               for(int lvt_29_1_ = 0; lvt_29_1_ < 4; ++lvt_29_1_) {
                  double lvt_30_1_ = 0.125D;
                  double lvt_32_1_ = lvt_13_1_;
                  double lvt_34_1_ = lvt_15_1_;
                  double lvt_36_1_ = (lvt_17_1_ - lvt_13_1_) * lvt_30_1_;
                  double lvt_38_1_ = (lvt_19_1_ - lvt_15_1_) * lvt_30_1_;

                  for(int lvt_40_1_ = 0; lvt_40_1_ < 8; ++lvt_40_1_) {
                     double lvt_41_1_ = 0.125D;
                     double lvt_43_1_ = lvt_32_1_;
                     double lvt_45_1_ = (lvt_34_1_ - lvt_32_1_) * lvt_41_1_;

                     for(int lvt_47_1_ = 0; lvt_47_1_ < 8; ++lvt_47_1_) {
                        IBlockState lvt_48_1_ = null;
                        if (lvt_43_1_ > 0.0D) {
                           lvt_48_1_ = Blocks.field_150377_bs.func_176223_P();
                        }

                        int lvt_49_1_ = lvt_40_1_ + lvt_8_1_ * 8;
                        int lvt_50_1_ = lvt_29_1_ + lvt_10_1_ * 4;
                        int lvt_51_1_ = lvt_47_1_ + lvt_9_1_ * 8;
                        p_180520_3_.func_177855_a(lvt_49_1_, lvt_50_1_, lvt_51_1_, lvt_48_1_);
                        lvt_43_1_ += lvt_45_1_;
                     }

                     lvt_32_1_ += lvt_36_1_;
                     lvt_34_1_ += lvt_38_1_;
                  }

                  lvt_13_1_ += lvt_21_1_;
                  lvt_15_1_ += lvt_23_1_;
                  lvt_17_1_ += lvt_25_1_;
                  lvt_19_1_ += lvt_27_1_;
               }
            }
         }
      }

   }

   public void func_180519_a(ChunkPrimer p_180519_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < 16; ++lvt_2_1_) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < 16; ++lvt_3_1_) {
            int lvt_4_1_ = 1;
            int lvt_5_1_ = -1;
            IBlockState lvt_6_1_ = Blocks.field_150377_bs.func_176223_P();
            IBlockState lvt_7_1_ = Blocks.field_150377_bs.func_176223_P();

            for(int lvt_8_1_ = 127; lvt_8_1_ >= 0; --lvt_8_1_) {
               IBlockState lvt_9_1_ = p_180519_1_.func_177856_a(lvt_2_1_, lvt_8_1_, lvt_3_1_);
               if (lvt_9_1_.func_177230_c().func_149688_o() == Material.field_151579_a) {
                  lvt_5_1_ = -1;
               } else if (lvt_9_1_.func_177230_c() == Blocks.field_150348_b) {
                  if (lvt_5_1_ == -1) {
                     if (lvt_4_1_ <= 0) {
                        lvt_6_1_ = Blocks.field_150350_a.func_176223_P();
                        lvt_7_1_ = Blocks.field_150377_bs.func_176223_P();
                     }

                     lvt_5_1_ = lvt_4_1_;
                     if (lvt_8_1_ >= 0) {
                        p_180519_1_.func_177855_a(lvt_2_1_, lvt_8_1_, lvt_3_1_, lvt_6_1_);
                     } else {
                        p_180519_1_.func_177855_a(lvt_2_1_, lvt_8_1_, lvt_3_1_, lvt_7_1_);
                     }
                  } else if (lvt_5_1_ > 0) {
                     --lvt_5_1_;
                     p_180519_1_.func_177855_a(lvt_2_1_, lvt_8_1_, lvt_3_1_, lvt_7_1_);
                  }
               }
            }
         }
      }

   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      this.field_73204_i.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
      ChunkPrimer lvt_3_1_ = new ChunkPrimer();
      this.field_73198_o = this.field_73200_m.func_72959_q().func_76933_b(this.field_73198_o, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      this.func_180520_a(p_73154_1_, p_73154_2_, lvt_3_1_);
      this.func_180519_a(lvt_3_1_);
      Chunk lvt_4_1_ = new Chunk(this.field_73200_m, lvt_3_1_, p_73154_1_, p_73154_2_);
      byte[] lvt_5_1_ = lvt_4_1_.func_76605_m();

      for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_.length; ++lvt_6_1_) {
         lvt_5_1_[lvt_6_1_] = (byte)this.field_73198_o[lvt_6_1_].field_76756_M;
      }

      lvt_4_1_.func_76603_b();
      return lvt_4_1_;
   }

   private double[] func_73187_a(double[] p_73187_1_, int p_73187_2_, int p_73187_3_, int p_73187_4_, int p_73187_5_, int p_73187_6_, int p_73187_7_) {
      if (p_73187_1_ == null) {
         p_73187_1_ = new double[p_73187_5_ * p_73187_6_ * p_73187_7_];
      }

      double lvt_8_1_ = 684.412D;
      double lvt_10_1_ = 684.412D;
      this.field_73190_f = this.field_73196_a.func_76305_a(this.field_73190_f, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 1.121D, 1.121D, 0.5D);
      this.field_73191_g = this.field_73194_b.func_76305_a(this.field_73191_g, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 200.0D, 200.0D, 0.5D);
      lvt_8_1_ *= 2.0D;
      this.field_73195_c = this.field_73199_l.func_76304_a(this.field_73195_c, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, lvt_8_1_ / 80.0D, lvt_10_1_ / 160.0D, lvt_8_1_ / 80.0D);
      this.field_73192_d = this.field_73201_j.func_76304_a(this.field_73192_d, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, lvt_8_1_, lvt_10_1_, lvt_8_1_);
      this.field_73193_e = this.field_73202_k.func_76304_a(this.field_73193_e, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, lvt_8_1_, lvt_10_1_, lvt_8_1_);
      int lvt_12_1_ = 0;

      for(int lvt_13_1_ = 0; lvt_13_1_ < p_73187_5_; ++lvt_13_1_) {
         for(int lvt_14_1_ = 0; lvt_14_1_ < p_73187_7_; ++lvt_14_1_) {
            float lvt_15_1_ = (float)(lvt_13_1_ + p_73187_2_) / 1.0F;
            float lvt_16_1_ = (float)(lvt_14_1_ + p_73187_4_) / 1.0F;
            float lvt_17_1_ = 100.0F - MathHelper.func_76129_c(lvt_15_1_ * lvt_15_1_ + lvt_16_1_ * lvt_16_1_) * 8.0F;
            if (lvt_17_1_ > 80.0F) {
               lvt_17_1_ = 80.0F;
            }

            if (lvt_17_1_ < -100.0F) {
               lvt_17_1_ = -100.0F;
            }

            for(int lvt_18_1_ = 0; lvt_18_1_ < p_73187_6_; ++lvt_18_1_) {
               double lvt_19_1_ = 0.0D;
               double lvt_21_1_ = this.field_73192_d[lvt_12_1_] / 512.0D;
               double lvt_23_1_ = this.field_73193_e[lvt_12_1_] / 512.0D;
               double lvt_25_1_ = (this.field_73195_c[lvt_12_1_] / 10.0D + 1.0D) / 2.0D;
               if (lvt_25_1_ < 0.0D) {
                  lvt_19_1_ = lvt_21_1_;
               } else if (lvt_25_1_ > 1.0D) {
                  lvt_19_1_ = lvt_23_1_;
               } else {
                  lvt_19_1_ = lvt_21_1_ + (lvt_23_1_ - lvt_21_1_) * lvt_25_1_;
               }

               lvt_19_1_ -= 8.0D;
               lvt_19_1_ += (double)lvt_17_1_;
               int lvt_27_1_ = 2;
               double lvt_28_2_;
               if (lvt_18_1_ > p_73187_6_ / 2 - lvt_27_1_) {
                  lvt_28_2_ = (double)((float)(lvt_18_1_ - (p_73187_6_ / 2 - lvt_27_1_)) / 64.0F);
                  lvt_28_2_ = MathHelper.func_151237_a(lvt_28_2_, 0.0D, 1.0D);
                  lvt_19_1_ = lvt_19_1_ * (1.0D - lvt_28_2_) + -3000.0D * lvt_28_2_;
               }

               lvt_27_1_ = 8;
               if (lvt_18_1_ < lvt_27_1_) {
                  lvt_28_2_ = (double)((float)(lvt_27_1_ - lvt_18_1_) / ((float)lvt_27_1_ - 1.0F));
                  lvt_19_1_ = lvt_19_1_ * (1.0D - lvt_28_2_) + -30.0D * lvt_28_2_;
               }

               p_73187_1_[lvt_12_1_] = lvt_19_1_;
               ++lvt_12_1_;
            }
         }
      }

      return p_73187_1_;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      BlockFalling.field_149832_M = true;
      BlockPos lvt_4_1_ = new BlockPos(p_73153_2_ * 16, 0, p_73153_3_ * 16);
      this.field_73200_m.func_180494_b(lvt_4_1_.func_177982_a(16, 0, 16)).func_180624_a(this.field_73200_m, this.field_73200_m.field_73012_v, lvt_4_1_);
      BlockFalling.field_149832_M = false;
   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      return false;
   }

   public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
      return true;
   }

   public void func_104112_b() {
   }

   public boolean func_73156_b() {
      return false;
   }

   public boolean func_73157_c() {
      return true;
   }

   public String func_73148_d() {
      return "RandomLevelSource";
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      return this.field_73200_m.func_180494_b(p_177458_2_).func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
