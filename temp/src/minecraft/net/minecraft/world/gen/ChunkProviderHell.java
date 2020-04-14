package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenNetherBridge;

public class ChunkProviderHell implements IChunkProvider {
   private final World field_73175_o;
   private final boolean field_177466_i;
   private final Random field_73181_i;
   private double[] field_73185_q = new double[256];
   private double[] field_73184_r = new double[256];
   private double[] field_73183_s = new double[256];
   private double[] field_73186_p;
   private final NoiseGeneratorOctaves field_73178_j;
   private final NoiseGeneratorOctaves field_73179_k;
   private final NoiseGeneratorOctaves field_73176_l;
   private final NoiseGeneratorOctaves field_73177_m;
   private final NoiseGeneratorOctaves field_73174_n;
   public final NoiseGeneratorOctaves field_73173_a;
   public final NoiseGeneratorOctaves field_73171_b;
   private final WorldGenFire field_177470_t = new WorldGenFire();
   private final WorldGenGlowStone1 field_177469_u = new WorldGenGlowStone1();
   private final WorldGenGlowStone2 field_177468_v = new WorldGenGlowStone2();
   private final WorldGenerator field_177467_w;
   private final WorldGenHellLava field_177473_x;
   private final WorldGenHellLava field_177472_y;
   private final GeneratorBushFeature field_177471_z;
   private final GeneratorBushFeature field_177465_A;
   private final MapGenNetherBridge field_73172_c;
   private final MapGenBase field_73182_t;
   double[] field_73169_d;
   double[] field_73170_e;
   double[] field_73167_f;
   double[] field_73168_g;
   double[] field_73180_h;

   public ChunkProviderHell(World p_i45637_1_, boolean p_i45637_2_, long p_i45637_3_) {
      this.field_177467_w = new WorldGenMinable(Blocks.field_150449_bY.func_176223_P(), 14, BlockHelper.func_177642_a(Blocks.field_150424_aL));
      this.field_177473_x = new WorldGenHellLava(Blocks.field_150356_k, true);
      this.field_177472_y = new WorldGenHellLava(Blocks.field_150356_k, false);
      this.field_177471_z = new GeneratorBushFeature(Blocks.field_150338_P);
      this.field_177465_A = new GeneratorBushFeature(Blocks.field_150337_Q);
      this.field_73172_c = new MapGenNetherBridge();
      this.field_73182_t = new MapGenCavesHell();
      this.field_73175_o = p_i45637_1_;
      this.field_177466_i = p_i45637_2_;
      this.field_73181_i = new Random(p_i45637_3_);
      this.field_73178_j = new NoiseGeneratorOctaves(this.field_73181_i, 16);
      this.field_73179_k = new NoiseGeneratorOctaves(this.field_73181_i, 16);
      this.field_73176_l = new NoiseGeneratorOctaves(this.field_73181_i, 8);
      this.field_73177_m = new NoiseGeneratorOctaves(this.field_73181_i, 4);
      this.field_73174_n = new NoiseGeneratorOctaves(this.field_73181_i, 4);
      this.field_73173_a = new NoiseGeneratorOctaves(this.field_73181_i, 10);
      this.field_73171_b = new NoiseGeneratorOctaves(this.field_73181_i, 16);
      p_i45637_1_.func_181544_b(63);
   }

   public void func_180515_a(int p_180515_1_, int p_180515_2_, ChunkPrimer p_180515_3_) {
      int lvt_4_1_ = 4;
      int lvt_5_1_ = this.field_73175_o.func_181545_F() / 2 + 1;
      int lvt_6_1_ = lvt_4_1_ + 1;
      int lvt_7_1_ = 17;
      int lvt_8_1_ = lvt_4_1_ + 1;
      this.field_73186_p = this.func_73164_a(this.field_73186_p, p_180515_1_ * lvt_4_1_, 0, p_180515_2_ * lvt_4_1_, lvt_6_1_, lvt_7_1_, lvt_8_1_);

      for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_4_1_; ++lvt_9_1_) {
         for(int lvt_10_1_ = 0; lvt_10_1_ < lvt_4_1_; ++lvt_10_1_) {
            for(int lvt_11_1_ = 0; lvt_11_1_ < 16; ++lvt_11_1_) {
               double lvt_12_1_ = 0.125D;
               double lvt_14_1_ = this.field_73186_p[((lvt_9_1_ + 0) * lvt_8_1_ + lvt_10_1_ + 0) * lvt_7_1_ + lvt_11_1_ + 0];
               double lvt_16_1_ = this.field_73186_p[((lvt_9_1_ + 0) * lvt_8_1_ + lvt_10_1_ + 1) * lvt_7_1_ + lvt_11_1_ + 0];
               double lvt_18_1_ = this.field_73186_p[((lvt_9_1_ + 1) * lvt_8_1_ + lvt_10_1_ + 0) * lvt_7_1_ + lvt_11_1_ + 0];
               double lvt_20_1_ = this.field_73186_p[((lvt_9_1_ + 1) * lvt_8_1_ + lvt_10_1_ + 1) * lvt_7_1_ + lvt_11_1_ + 0];
               double lvt_22_1_ = (this.field_73186_p[((lvt_9_1_ + 0) * lvt_8_1_ + lvt_10_1_ + 0) * lvt_7_1_ + lvt_11_1_ + 1] - lvt_14_1_) * lvt_12_1_;
               double lvt_24_1_ = (this.field_73186_p[((lvt_9_1_ + 0) * lvt_8_1_ + lvt_10_1_ + 1) * lvt_7_1_ + lvt_11_1_ + 1] - lvt_16_1_) * lvt_12_1_;
               double lvt_26_1_ = (this.field_73186_p[((lvt_9_1_ + 1) * lvt_8_1_ + lvt_10_1_ + 0) * lvt_7_1_ + lvt_11_1_ + 1] - lvt_18_1_) * lvt_12_1_;
               double lvt_28_1_ = (this.field_73186_p[((lvt_9_1_ + 1) * lvt_8_1_ + lvt_10_1_ + 1) * lvt_7_1_ + lvt_11_1_ + 1] - lvt_20_1_) * lvt_12_1_;

               for(int lvt_30_1_ = 0; lvt_30_1_ < 8; ++lvt_30_1_) {
                  double lvt_31_1_ = 0.25D;
                  double lvt_33_1_ = lvt_14_1_;
                  double lvt_35_1_ = lvt_16_1_;
                  double lvt_37_1_ = (lvt_18_1_ - lvt_14_1_) * lvt_31_1_;
                  double lvt_39_1_ = (lvt_20_1_ - lvt_16_1_) * lvt_31_1_;

                  for(int lvt_41_1_ = 0; lvt_41_1_ < 4; ++lvt_41_1_) {
                     double lvt_42_1_ = 0.25D;
                     double lvt_44_1_ = lvt_33_1_;
                     double lvt_46_1_ = (lvt_35_1_ - lvt_33_1_) * lvt_42_1_;

                     for(int lvt_48_1_ = 0; lvt_48_1_ < 4; ++lvt_48_1_) {
                        IBlockState lvt_49_1_ = null;
                        if (lvt_11_1_ * 8 + lvt_30_1_ < lvt_5_1_) {
                           lvt_49_1_ = Blocks.field_150353_l.func_176223_P();
                        }

                        if (lvt_44_1_ > 0.0D) {
                           lvt_49_1_ = Blocks.field_150424_aL.func_176223_P();
                        }

                        int lvt_50_1_ = lvt_41_1_ + lvt_9_1_ * 4;
                        int lvt_51_1_ = lvt_30_1_ + lvt_11_1_ * 8;
                        int lvt_52_1_ = lvt_48_1_ + lvt_10_1_ * 4;
                        p_180515_3_.func_177855_a(lvt_50_1_, lvt_51_1_, lvt_52_1_, lvt_49_1_);
                        lvt_44_1_ += lvt_46_1_;
                     }

                     lvt_33_1_ += lvt_37_1_;
                     lvt_35_1_ += lvt_39_1_;
                  }

                  lvt_14_1_ += lvt_22_1_;
                  lvt_16_1_ += lvt_24_1_;
                  lvt_18_1_ += lvt_26_1_;
                  lvt_20_1_ += lvt_28_1_;
               }
            }
         }
      }

   }

   public void func_180516_b(int p_180516_1_, int p_180516_2_, ChunkPrimer p_180516_3_) {
      int lvt_4_1_ = this.field_73175_o.func_181545_F() + 1;
      double lvt_5_1_ = 0.03125D;
      this.field_73185_q = this.field_73177_m.func_76304_a(this.field_73185_q, p_180516_1_ * 16, p_180516_2_ * 16, 0, 16, 16, 1, lvt_5_1_, lvt_5_1_, 1.0D);
      this.field_73184_r = this.field_73177_m.func_76304_a(this.field_73184_r, p_180516_1_ * 16, 109, p_180516_2_ * 16, 16, 1, 16, lvt_5_1_, 1.0D, lvt_5_1_);
      this.field_73183_s = this.field_73174_n.func_76304_a(this.field_73183_s, p_180516_1_ * 16, p_180516_2_ * 16, 0, 16, 16, 1, lvt_5_1_ * 2.0D, lvt_5_1_ * 2.0D, lvt_5_1_ * 2.0D);

      for(int lvt_7_1_ = 0; lvt_7_1_ < 16; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < 16; ++lvt_8_1_) {
            boolean lvt_9_1_ = this.field_73185_q[lvt_7_1_ + lvt_8_1_ * 16] + this.field_73181_i.nextDouble() * 0.2D > 0.0D;
            boolean lvt_10_1_ = this.field_73184_r[lvt_7_1_ + lvt_8_1_ * 16] + this.field_73181_i.nextDouble() * 0.2D > 0.0D;
            int lvt_11_1_ = (int)(this.field_73183_s[lvt_7_1_ + lvt_8_1_ * 16] / 3.0D + 3.0D + this.field_73181_i.nextDouble() * 0.25D);
            int lvt_12_1_ = -1;
            IBlockState lvt_13_1_ = Blocks.field_150424_aL.func_176223_P();
            IBlockState lvt_14_1_ = Blocks.field_150424_aL.func_176223_P();

            for(int lvt_15_1_ = 127; lvt_15_1_ >= 0; --lvt_15_1_) {
               if (lvt_15_1_ < 127 - this.field_73181_i.nextInt(5) && lvt_15_1_ > this.field_73181_i.nextInt(5)) {
                  IBlockState lvt_16_1_ = p_180516_3_.func_177856_a(lvt_8_1_, lvt_15_1_, lvt_7_1_);
                  if (lvt_16_1_.func_177230_c() != null && lvt_16_1_.func_177230_c().func_149688_o() != Material.field_151579_a) {
                     if (lvt_16_1_.func_177230_c() == Blocks.field_150424_aL) {
                        if (lvt_12_1_ == -1) {
                           if (lvt_11_1_ <= 0) {
                              lvt_13_1_ = null;
                              lvt_14_1_ = Blocks.field_150424_aL.func_176223_P();
                           } else if (lvt_15_1_ >= lvt_4_1_ - 4 && lvt_15_1_ <= lvt_4_1_ + 1) {
                              lvt_13_1_ = Blocks.field_150424_aL.func_176223_P();
                              lvt_14_1_ = Blocks.field_150424_aL.func_176223_P();
                              if (lvt_10_1_) {
                                 lvt_13_1_ = Blocks.field_150351_n.func_176223_P();
                                 lvt_14_1_ = Blocks.field_150424_aL.func_176223_P();
                              }

                              if (lvt_9_1_) {
                                 lvt_13_1_ = Blocks.field_150425_aM.func_176223_P();
                                 lvt_14_1_ = Blocks.field_150425_aM.func_176223_P();
                              }
                           }

                           if (lvt_15_1_ < lvt_4_1_ && (lvt_13_1_ == null || lvt_13_1_.func_177230_c().func_149688_o() == Material.field_151579_a)) {
                              lvt_13_1_ = Blocks.field_150353_l.func_176223_P();
                           }

                           lvt_12_1_ = lvt_11_1_;
                           if (lvt_15_1_ >= lvt_4_1_ - 1) {
                              p_180516_3_.func_177855_a(lvt_8_1_, lvt_15_1_, lvt_7_1_, lvt_13_1_);
                           } else {
                              p_180516_3_.func_177855_a(lvt_8_1_, lvt_15_1_, lvt_7_1_, lvt_14_1_);
                           }
                        } else if (lvt_12_1_ > 0) {
                           --lvt_12_1_;
                           p_180516_3_.func_177855_a(lvt_8_1_, lvt_15_1_, lvt_7_1_, lvt_14_1_);
                        }
                     }
                  } else {
                     lvt_12_1_ = -1;
                  }
               } else {
                  p_180516_3_.func_177855_a(lvt_8_1_, lvt_15_1_, lvt_7_1_, Blocks.field_150357_h.func_176223_P());
               }
            }
         }
      }

   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      this.field_73181_i.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
      ChunkPrimer lvt_3_1_ = new ChunkPrimer();
      this.func_180515_a(p_73154_1_, p_73154_2_, lvt_3_1_);
      this.func_180516_b(p_73154_1_, p_73154_2_, lvt_3_1_);
      this.field_73182_t.func_175792_a(this, this.field_73175_o, p_73154_1_, p_73154_2_, lvt_3_1_);
      if (this.field_177466_i) {
         this.field_73172_c.func_175792_a(this, this.field_73175_o, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      Chunk lvt_4_1_ = new Chunk(this.field_73175_o, lvt_3_1_, p_73154_1_, p_73154_2_);
      BiomeGenBase[] lvt_5_1_ = this.field_73175_o.func_72959_q().func_76933_b((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      byte[] lvt_6_1_ = lvt_4_1_.func_76605_m();

      for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_.length; ++lvt_7_1_) {
         lvt_6_1_[lvt_7_1_] = (byte)lvt_5_1_[lvt_7_1_].field_76756_M;
      }

      lvt_4_1_.func_76613_n();
      return lvt_4_1_;
   }

   private double[] func_73164_a(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_) {
      if (p_73164_1_ == null) {
         p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_];
      }

      double lvt_8_1_ = 684.412D;
      double lvt_10_1_ = 2053.236D;
      this.field_73168_g = this.field_73173_a.func_76304_a(this.field_73168_g, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0D, 0.0D, 1.0D);
      this.field_73180_h = this.field_73171_b.func_76304_a(this.field_73180_h, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 100.0D, 0.0D, 100.0D);
      this.field_73169_d = this.field_73176_l.func_76304_a(this.field_73169_d, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, lvt_8_1_ / 80.0D, lvt_10_1_ / 60.0D, lvt_8_1_ / 80.0D);
      this.field_73170_e = this.field_73178_j.func_76304_a(this.field_73170_e, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, lvt_8_1_, lvt_10_1_, lvt_8_1_);
      this.field_73167_f = this.field_73179_k.func_76304_a(this.field_73167_f, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, lvt_8_1_, lvt_10_1_, lvt_8_1_);
      int lvt_12_1_ = 0;
      double[] lvt_13_1_ = new double[p_73164_6_];

      int lvt_14_1_;
      for(lvt_14_1_ = 0; lvt_14_1_ < p_73164_6_; ++lvt_14_1_) {
         lvt_13_1_[lvt_14_1_] = Math.cos((double)lvt_14_1_ * 3.141592653589793D * 6.0D / (double)p_73164_6_) * 2.0D;
         double lvt_15_1_ = (double)lvt_14_1_;
         if (lvt_14_1_ > p_73164_6_ / 2) {
            lvt_15_1_ = (double)(p_73164_6_ - 1 - lvt_14_1_);
         }

         if (lvt_15_1_ < 4.0D) {
            lvt_15_1_ = 4.0D - lvt_15_1_;
            lvt_13_1_[lvt_14_1_] -= lvt_15_1_ * lvt_15_1_ * lvt_15_1_ * 10.0D;
         }
      }

      for(lvt_14_1_ = 0; lvt_14_1_ < p_73164_5_; ++lvt_14_1_) {
         for(int lvt_15_2_ = 0; lvt_15_2_ < p_73164_7_; ++lvt_15_2_) {
            double lvt_16_1_ = 0.0D;

            for(int lvt_18_1_ = 0; lvt_18_1_ < p_73164_6_; ++lvt_18_1_) {
               double lvt_19_1_ = 0.0D;
               double lvt_21_1_ = lvt_13_1_[lvt_18_1_];
               double lvt_23_1_ = this.field_73170_e[lvt_12_1_] / 512.0D;
               double lvt_25_1_ = this.field_73167_f[lvt_12_1_] / 512.0D;
               double lvt_27_1_ = (this.field_73169_d[lvt_12_1_] / 10.0D + 1.0D) / 2.0D;
               if (lvt_27_1_ < 0.0D) {
                  lvt_19_1_ = lvt_23_1_;
               } else if (lvt_27_1_ > 1.0D) {
                  lvt_19_1_ = lvt_25_1_;
               } else {
                  lvt_19_1_ = lvt_23_1_ + (lvt_25_1_ - lvt_23_1_) * lvt_27_1_;
               }

               lvt_19_1_ -= lvt_21_1_;
               double lvt_29_2_;
               if (lvt_18_1_ > p_73164_6_ - 4) {
                  lvt_29_2_ = (double)((float)(lvt_18_1_ - (p_73164_6_ - 4)) / 3.0F);
                  lvt_19_1_ = lvt_19_1_ * (1.0D - lvt_29_2_) + -10.0D * lvt_29_2_;
               }

               if ((double)lvt_18_1_ < lvt_16_1_) {
                  lvt_29_2_ = (lvt_16_1_ - (double)lvt_18_1_) / 4.0D;
                  lvt_29_2_ = MathHelper.func_151237_a(lvt_29_2_, 0.0D, 1.0D);
                  lvt_19_1_ = lvt_19_1_ * (1.0D - lvt_29_2_) + -10.0D * lvt_29_2_;
               }

               p_73164_1_[lvt_12_1_] = lvt_19_1_;
               ++lvt_12_1_;
            }
         }
      }

      return p_73164_1_;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      BlockFalling.field_149832_M = true;
      BlockPos lvt_4_1_ = new BlockPos(p_73153_2_ * 16, 0, p_73153_3_ * 16);
      ChunkCoordIntPair lvt_5_1_ = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
      this.field_73172_c.func_175794_a(this.field_73175_o, this.field_73181_i, lvt_5_1_);

      int lvt_6_6_;
      for(lvt_6_6_ = 0; lvt_6_6_ < 8; ++lvt_6_6_) {
         this.field_177472_y.func_180709_b(this.field_73175_o, this.field_73181_i, lvt_4_1_.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(120) + 4, this.field_73181_i.nextInt(16) + 8));
      }

      for(lvt_6_6_ = 0; lvt_6_6_ < this.field_73181_i.nextInt(this.field_73181_i.nextInt(10) + 1) + 1; ++lvt_6_6_) {
         this.field_177470_t.func_180709_b(this.field_73175_o, this.field_73181_i, lvt_4_1_.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(120) + 4, this.field_73181_i.nextInt(16) + 8));
      }

      for(lvt_6_6_ = 0; lvt_6_6_ < this.field_73181_i.nextInt(this.field_73181_i.nextInt(10) + 1); ++lvt_6_6_) {
         this.field_177469_u.func_180709_b(this.field_73175_o, this.field_73181_i, lvt_4_1_.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(120) + 4, this.field_73181_i.nextInt(16) + 8));
      }

      for(lvt_6_6_ = 0; lvt_6_6_ < 10; ++lvt_6_6_) {
         this.field_177468_v.func_180709_b(this.field_73175_o, this.field_73181_i, lvt_4_1_.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(128), this.field_73181_i.nextInt(16) + 8));
      }

      if (this.field_73181_i.nextBoolean()) {
         this.field_177471_z.func_180709_b(this.field_73175_o, this.field_73181_i, lvt_4_1_.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(128), this.field_73181_i.nextInt(16) + 8));
      }

      if (this.field_73181_i.nextBoolean()) {
         this.field_177465_A.func_180709_b(this.field_73175_o, this.field_73181_i, lvt_4_1_.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(128), this.field_73181_i.nextInt(16) + 8));
      }

      for(lvt_6_6_ = 0; lvt_6_6_ < 16; ++lvt_6_6_) {
         this.field_177467_w.func_180709_b(this.field_73175_o, this.field_73181_i, lvt_4_1_.func_177982_a(this.field_73181_i.nextInt(16), this.field_73181_i.nextInt(108) + 10, this.field_73181_i.nextInt(16)));
      }

      for(lvt_6_6_ = 0; lvt_6_6_ < 16; ++lvt_6_6_) {
         this.field_177473_x.func_180709_b(this.field_73175_o, this.field_73181_i, lvt_4_1_.func_177982_a(this.field_73181_i.nextInt(16), this.field_73181_i.nextInt(108) + 10, this.field_73181_i.nextInt(16)));
      }

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
      return "HellRandomLevelSource";
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      if (p_177458_1_ == EnumCreatureType.MONSTER) {
         if (this.field_73172_c.func_175795_b(p_177458_2_)) {
            return this.field_73172_c.func_75059_a();
         }

         if (this.field_73172_c.func_175796_a(this.field_73175_o, p_177458_2_) && this.field_73175_o.func_180495_p(p_177458_2_.func_177977_b()).func_177230_c() == Blocks.field_150385_bj) {
            return this.field_73172_c.func_75059_a();
         }
      }

      BiomeGenBase lvt_3_1_ = this.field_73175_o.func_180494_b(p_177458_2_);
      return lvt_3_1_.func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
      this.field_73172_c.func_175792_a(this, this.field_73175_o, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
