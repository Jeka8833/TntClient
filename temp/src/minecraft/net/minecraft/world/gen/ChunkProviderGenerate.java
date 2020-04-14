package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class ChunkProviderGenerate implements IChunkProvider {
   private Random field_73220_k;
   private NoiseGeneratorOctaves field_147431_j;
   private NoiseGeneratorOctaves field_147432_k;
   private NoiseGeneratorOctaves field_147429_l;
   private NoiseGeneratorPerlin field_147430_m;
   public NoiseGeneratorOctaves field_73214_a;
   public NoiseGeneratorOctaves field_73212_b;
   public NoiseGeneratorOctaves field_73213_c;
   private World field_73230_p;
   private final boolean field_73229_q;
   private WorldType field_177475_o;
   private final double[] field_147434_q;
   private final float[] field_147433_r;
   private ChunkProviderSettings field_177477_r;
   private Block field_177476_s;
   private double[] field_73227_s;
   private MapGenBase field_73226_t;
   private MapGenStronghold field_73225_u;
   private MapGenVillage field_73224_v;
   private MapGenMineshaft field_73223_w;
   private MapGenScatteredFeature field_73233_x;
   private MapGenBase field_73232_y;
   private StructureOceanMonument field_177474_A;
   private BiomeGenBase[] field_73231_z;
   double[] field_147427_d;
   double[] field_147428_e;
   double[] field_147425_f;
   double[] field_147426_g;

   public ChunkProviderGenerate(World p_i45636_1_, long p_i45636_2_, boolean p_i45636_4_, String p_i45636_5_) {
      this.field_177476_s = Blocks.field_150355_j;
      this.field_73227_s = new double[256];
      this.field_73226_t = new MapGenCaves();
      this.field_73225_u = new MapGenStronghold();
      this.field_73224_v = new MapGenVillage();
      this.field_73223_w = new MapGenMineshaft();
      this.field_73233_x = new MapGenScatteredFeature();
      this.field_73232_y = new MapGenRavine();
      this.field_177474_A = new StructureOceanMonument();
      this.field_73230_p = p_i45636_1_;
      this.field_73229_q = p_i45636_4_;
      this.field_177475_o = p_i45636_1_.func_72912_H().func_76067_t();
      this.field_73220_k = new Random(p_i45636_2_);
      this.field_147431_j = new NoiseGeneratorOctaves(this.field_73220_k, 16);
      this.field_147432_k = new NoiseGeneratorOctaves(this.field_73220_k, 16);
      this.field_147429_l = new NoiseGeneratorOctaves(this.field_73220_k, 8);
      this.field_147430_m = new NoiseGeneratorPerlin(this.field_73220_k, 4);
      this.field_73214_a = new NoiseGeneratorOctaves(this.field_73220_k, 10);
      this.field_73212_b = new NoiseGeneratorOctaves(this.field_73220_k, 16);
      this.field_73213_c = new NoiseGeneratorOctaves(this.field_73220_k, 8);
      this.field_147434_q = new double[825];
      this.field_147433_r = new float[25];

      for(int lvt_6_1_ = -2; lvt_6_1_ <= 2; ++lvt_6_1_) {
         for(int lvt_7_1_ = -2; lvt_7_1_ <= 2; ++lvt_7_1_) {
            float lvt_8_1_ = 10.0F / MathHelper.func_76129_c((float)(lvt_6_1_ * lvt_6_1_ + lvt_7_1_ * lvt_7_1_) + 0.2F);
            this.field_147433_r[lvt_6_1_ + 2 + (lvt_7_1_ + 2) * 5] = lvt_8_1_;
         }
      }

      if (p_i45636_5_ != null) {
         this.field_177477_r = ChunkProviderSettings.Factory.func_177865_a(p_i45636_5_).func_177864_b();
         this.field_177476_s = this.field_177477_r.field_177778_E ? Blocks.field_150353_l : Blocks.field_150355_j;
         p_i45636_1_.func_181544_b(this.field_177477_r.field_177841_q);
      }

   }

   public void func_180518_a(int p_180518_1_, int p_180518_2_, ChunkPrimer p_180518_3_) {
      this.field_73231_z = this.field_73230_p.func_72959_q().func_76937_a(this.field_73231_z, p_180518_1_ * 4 - 2, p_180518_2_ * 4 - 2, 10, 10);
      this.func_147423_a(p_180518_1_ * 4, 0, p_180518_2_ * 4);

      for(int lvt_4_1_ = 0; lvt_4_1_ < 4; ++lvt_4_1_) {
         int lvt_5_1_ = lvt_4_1_ * 5;
         int lvt_6_1_ = (lvt_4_1_ + 1) * 5;

         for(int lvt_7_1_ = 0; lvt_7_1_ < 4; ++lvt_7_1_) {
            int lvt_8_1_ = (lvt_5_1_ + lvt_7_1_) * 33;
            int lvt_9_1_ = (lvt_5_1_ + lvt_7_1_ + 1) * 33;
            int lvt_10_1_ = (lvt_6_1_ + lvt_7_1_) * 33;
            int lvt_11_1_ = (lvt_6_1_ + lvt_7_1_ + 1) * 33;

            for(int lvt_12_1_ = 0; lvt_12_1_ < 32; ++lvt_12_1_) {
               double lvt_13_1_ = 0.125D;
               double lvt_15_1_ = this.field_147434_q[lvt_8_1_ + lvt_12_1_];
               double lvt_17_1_ = this.field_147434_q[lvt_9_1_ + lvt_12_1_];
               double lvt_19_1_ = this.field_147434_q[lvt_10_1_ + lvt_12_1_];
               double lvt_21_1_ = this.field_147434_q[lvt_11_1_ + lvt_12_1_];
               double lvt_23_1_ = (this.field_147434_q[lvt_8_1_ + lvt_12_1_ + 1] - lvt_15_1_) * lvt_13_1_;
               double lvt_25_1_ = (this.field_147434_q[lvt_9_1_ + lvt_12_1_ + 1] - lvt_17_1_) * lvt_13_1_;
               double lvt_27_1_ = (this.field_147434_q[lvt_10_1_ + lvt_12_1_ + 1] - lvt_19_1_) * lvt_13_1_;
               double lvt_29_1_ = (this.field_147434_q[lvt_11_1_ + lvt_12_1_ + 1] - lvt_21_1_) * lvt_13_1_;

               for(int lvt_31_1_ = 0; lvt_31_1_ < 8; ++lvt_31_1_) {
                  double lvt_32_1_ = 0.25D;
                  double lvt_34_1_ = lvt_15_1_;
                  double lvt_36_1_ = lvt_17_1_;
                  double lvt_38_1_ = (lvt_19_1_ - lvt_15_1_) * lvt_32_1_;
                  double lvt_40_1_ = (lvt_21_1_ - lvt_17_1_) * lvt_32_1_;

                  for(int lvt_42_1_ = 0; lvt_42_1_ < 4; ++lvt_42_1_) {
                     double lvt_43_1_ = 0.25D;
                     double lvt_47_1_ = (lvt_36_1_ - lvt_34_1_) * lvt_43_1_;
                     double lvt_45_1_ = lvt_34_1_ - lvt_47_1_;

                     for(int lvt_49_1_ = 0; lvt_49_1_ < 4; ++lvt_49_1_) {
                        if ((lvt_45_1_ += lvt_47_1_) > 0.0D) {
                           p_180518_3_.func_177855_a(lvt_4_1_ * 4 + lvt_42_1_, lvt_12_1_ * 8 + lvt_31_1_, lvt_7_1_ * 4 + lvt_49_1_, Blocks.field_150348_b.func_176223_P());
                        } else if (lvt_12_1_ * 8 + lvt_31_1_ < this.field_177477_r.field_177841_q) {
                           p_180518_3_.func_177855_a(lvt_4_1_ * 4 + lvt_42_1_, lvt_12_1_ * 8 + lvt_31_1_, lvt_7_1_ * 4 + lvt_49_1_, this.field_177476_s.func_176223_P());
                        }
                     }

                     lvt_34_1_ += lvt_38_1_;
                     lvt_36_1_ += lvt_40_1_;
                  }

                  lvt_15_1_ += lvt_23_1_;
                  lvt_17_1_ += lvt_25_1_;
                  lvt_19_1_ += lvt_27_1_;
                  lvt_21_1_ += lvt_29_1_;
               }
            }
         }
      }

   }

   public void func_180517_a(int p_180517_1_, int p_180517_2_, ChunkPrimer p_180517_3_, BiomeGenBase[] p_180517_4_) {
      double lvt_5_1_ = 0.03125D;
      this.field_73227_s = this.field_147430_m.func_151599_a(this.field_73227_s, (double)(p_180517_1_ * 16), (double)(p_180517_2_ * 16), 16, 16, lvt_5_1_ * 2.0D, lvt_5_1_ * 2.0D, 1.0D);

      for(int lvt_7_1_ = 0; lvt_7_1_ < 16; ++lvt_7_1_) {
         for(int lvt_8_1_ = 0; lvt_8_1_ < 16; ++lvt_8_1_) {
            BiomeGenBase lvt_9_1_ = p_180517_4_[lvt_8_1_ + lvt_7_1_ * 16];
            lvt_9_1_.func_180622_a(this.field_73230_p, this.field_73220_k, p_180517_3_, p_180517_1_ * 16 + lvt_7_1_, p_180517_2_ * 16 + lvt_8_1_, this.field_73227_s[lvt_8_1_ + lvt_7_1_ * 16]);
         }
      }

   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      this.field_73220_k.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
      ChunkPrimer lvt_3_1_ = new ChunkPrimer();
      this.func_180518_a(p_73154_1_, p_73154_2_, lvt_3_1_);
      this.field_73231_z = this.field_73230_p.func_72959_q().func_76933_b(this.field_73231_z, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      this.func_180517_a(p_73154_1_, p_73154_2_, lvt_3_1_, this.field_73231_z);
      if (this.field_177477_r.field_177839_r) {
         this.field_73226_t.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      if (this.field_177477_r.field_177850_z) {
         this.field_73232_y.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      if (this.field_177477_r.field_177829_w && this.field_73229_q) {
         this.field_73223_w.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      if (this.field_177477_r.field_177831_v && this.field_73229_q) {
         this.field_73224_v.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      if (this.field_177477_r.field_177833_u && this.field_73229_q) {
         this.field_73225_u.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      if (this.field_177477_r.field_177854_x && this.field_73229_q) {
         this.field_73233_x.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      if (this.field_177477_r.field_177852_y && this.field_73229_q) {
         this.field_177474_A.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      Chunk lvt_4_1_ = new Chunk(this.field_73230_p, lvt_3_1_, p_73154_1_, p_73154_2_);
      byte[] lvt_5_1_ = lvt_4_1_.func_76605_m();

      for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_.length; ++lvt_6_1_) {
         lvt_5_1_[lvt_6_1_] = (byte)this.field_73231_z[lvt_6_1_].field_76756_M;
      }

      lvt_4_1_.func_76603_b();
      return lvt_4_1_;
   }

   private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_) {
      this.field_147426_g = this.field_73212_b.func_76305_a(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, (double)this.field_177477_r.field_177808_e, (double)this.field_177477_r.field_177803_f, (double)this.field_177477_r.field_177804_g);
      float lvt_4_1_ = this.field_177477_r.field_177811_a;
      float lvt_5_1_ = this.field_177477_r.field_177809_b;
      this.field_147427_d = this.field_147429_l.func_76304_a(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)(lvt_4_1_ / this.field_177477_r.field_177825_h), (double)(lvt_5_1_ / this.field_177477_r.field_177827_i), (double)(lvt_4_1_ / this.field_177477_r.field_177821_j));
      this.field_147428_e = this.field_147431_j.func_76304_a(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)lvt_4_1_, (double)lvt_5_1_, (double)lvt_4_1_);
      this.field_147425_f = this.field_147432_k.func_76304_a(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)lvt_4_1_, (double)lvt_5_1_, (double)lvt_4_1_);
      int p_147423_3_ = false;
      int p_147423_1_ = false;
      int lvt_6_1_ = 0;
      int lvt_7_1_ = 0;

      for(int lvt_8_1_ = 0; lvt_8_1_ < 5; ++lvt_8_1_) {
         for(int lvt_9_1_ = 0; lvt_9_1_ < 5; ++lvt_9_1_) {
            float lvt_10_1_ = 0.0F;
            float lvt_11_1_ = 0.0F;
            float lvt_12_1_ = 0.0F;
            int lvt_13_1_ = 2;
            BiomeGenBase lvt_14_1_ = this.field_73231_z[lvt_8_1_ + 2 + (lvt_9_1_ + 2) * 10];

            for(int lvt_15_1_ = -lvt_13_1_; lvt_15_1_ <= lvt_13_1_; ++lvt_15_1_) {
               for(int lvt_16_1_ = -lvt_13_1_; lvt_16_1_ <= lvt_13_1_; ++lvt_16_1_) {
                  BiomeGenBase lvt_17_1_ = this.field_73231_z[lvt_8_1_ + lvt_15_1_ + 2 + (lvt_9_1_ + lvt_16_1_ + 2) * 10];
                  float lvt_18_1_ = this.field_177477_r.field_177813_n + lvt_17_1_.field_76748_D * this.field_177477_r.field_177819_m;
                  float lvt_19_1_ = this.field_177477_r.field_177843_p + lvt_17_1_.field_76749_E * this.field_177477_r.field_177815_o;
                  if (this.field_177475_o == WorldType.field_151360_e && lvt_18_1_ > 0.0F) {
                     lvt_18_1_ = 1.0F + lvt_18_1_ * 2.0F;
                     lvt_19_1_ = 1.0F + lvt_19_1_ * 4.0F;
                  }

                  float lvt_20_1_ = this.field_147433_r[lvt_15_1_ + 2 + (lvt_16_1_ + 2) * 5] / (lvt_18_1_ + 2.0F);
                  if (lvt_17_1_.field_76748_D > lvt_14_1_.field_76748_D) {
                     lvt_20_1_ /= 2.0F;
                  }

                  lvt_10_1_ += lvt_19_1_ * lvt_20_1_;
                  lvt_11_1_ += lvt_18_1_ * lvt_20_1_;
                  lvt_12_1_ += lvt_20_1_;
               }
            }

            lvt_10_1_ /= lvt_12_1_;
            lvt_11_1_ /= lvt_12_1_;
            lvt_10_1_ = lvt_10_1_ * 0.9F + 0.1F;
            lvt_11_1_ = (lvt_11_1_ * 4.0F - 1.0F) / 8.0F;
            double lvt_15_2_ = this.field_147426_g[lvt_7_1_] / 8000.0D;
            if (lvt_15_2_ < 0.0D) {
               lvt_15_2_ = -lvt_15_2_ * 0.3D;
            }

            lvt_15_2_ = lvt_15_2_ * 3.0D - 2.0D;
            if (lvt_15_2_ < 0.0D) {
               lvt_15_2_ /= 2.0D;
               if (lvt_15_2_ < -1.0D) {
                  lvt_15_2_ = -1.0D;
               }

               lvt_15_2_ /= 1.4D;
               lvt_15_2_ /= 2.0D;
            } else {
               if (lvt_15_2_ > 1.0D) {
                  lvt_15_2_ = 1.0D;
               }

               lvt_15_2_ /= 8.0D;
            }

            ++lvt_7_1_;
            double lvt_17_2_ = (double)lvt_11_1_;
            double lvt_19_2_ = (double)lvt_10_1_;
            lvt_17_2_ += lvt_15_2_ * 0.2D;
            lvt_17_2_ = lvt_17_2_ * (double)this.field_177477_r.field_177823_k / 8.0D;
            double lvt_21_1_ = (double)this.field_177477_r.field_177823_k + lvt_17_2_ * 4.0D;

            for(int lvt_23_1_ = 0; lvt_23_1_ < 33; ++lvt_23_1_) {
               double lvt_24_1_ = ((double)lvt_23_1_ - lvt_21_1_) * (double)this.field_177477_r.field_177817_l * 128.0D / 256.0D / lvt_19_2_;
               if (lvt_24_1_ < 0.0D) {
                  lvt_24_1_ *= 4.0D;
               }

               double lvt_26_1_ = this.field_147428_e[lvt_6_1_] / (double)this.field_177477_r.field_177806_d;
               double lvt_28_1_ = this.field_147425_f[lvt_6_1_] / (double)this.field_177477_r.field_177810_c;
               double lvt_30_1_ = (this.field_147427_d[lvt_6_1_] / 10.0D + 1.0D) / 2.0D;
               double lvt_32_1_ = MathHelper.func_151238_b(lvt_26_1_, lvt_28_1_, lvt_30_1_) - lvt_24_1_;
               if (lvt_23_1_ > 29) {
                  double lvt_34_1_ = (double)((float)(lvt_23_1_ - 29) / 3.0F);
                  lvt_32_1_ = lvt_32_1_ * (1.0D - lvt_34_1_) + -10.0D * lvt_34_1_;
               }

               this.field_147434_q[lvt_6_1_] = lvt_32_1_;
               ++lvt_6_1_;
            }
         }
      }

   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      BlockFalling.field_149832_M = true;
      int lvt_4_1_ = p_73153_2_ * 16;
      int lvt_5_1_ = p_73153_3_ * 16;
      BlockPos lvt_6_1_ = new BlockPos(lvt_4_1_, 0, lvt_5_1_);
      BiomeGenBase lvt_7_1_ = this.field_73230_p.func_180494_b(lvt_6_1_.func_177982_a(16, 0, 16));
      this.field_73220_k.setSeed(this.field_73230_p.func_72905_C());
      long lvt_8_1_ = this.field_73220_k.nextLong() / 2L * 2L + 1L;
      long lvt_10_1_ = this.field_73220_k.nextLong() / 2L * 2L + 1L;
      this.field_73220_k.setSeed((long)p_73153_2_ * lvt_8_1_ + (long)p_73153_3_ * lvt_10_1_ ^ this.field_73230_p.func_72905_C());
      boolean lvt_12_1_ = false;
      ChunkCoordIntPair lvt_13_1_ = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
      if (this.field_177477_r.field_177829_w && this.field_73229_q) {
         this.field_73223_w.func_175794_a(this.field_73230_p, this.field_73220_k, lvt_13_1_);
      }

      if (this.field_177477_r.field_177831_v && this.field_73229_q) {
         lvt_12_1_ = this.field_73224_v.func_175794_a(this.field_73230_p, this.field_73220_k, lvt_13_1_);
      }

      if (this.field_177477_r.field_177833_u && this.field_73229_q) {
         this.field_73225_u.func_175794_a(this.field_73230_p, this.field_73220_k, lvt_13_1_);
      }

      if (this.field_177477_r.field_177854_x && this.field_73229_q) {
         this.field_73233_x.func_175794_a(this.field_73230_p, this.field_73220_k, lvt_13_1_);
      }

      if (this.field_177477_r.field_177852_y && this.field_73229_q) {
         this.field_177474_A.func_175794_a(this.field_73230_p, this.field_73220_k, lvt_13_1_);
      }

      int lvt_14_4_;
      int lvt_15_4_;
      int lvt_16_3_;
      if (lvt_7_1_ != BiomeGenBase.field_76769_d && lvt_7_1_ != BiomeGenBase.field_76786_s && this.field_177477_r.field_177781_A && !lvt_12_1_ && this.field_73220_k.nextInt(this.field_177477_r.field_177782_B) == 0) {
         lvt_14_4_ = this.field_73220_k.nextInt(16) + 8;
         lvt_15_4_ = this.field_73220_k.nextInt(256);
         lvt_16_3_ = this.field_73220_k.nextInt(16) + 8;
         (new WorldGenLakes(Blocks.field_150355_j)).func_180709_b(this.field_73230_p, this.field_73220_k, lvt_6_1_.func_177982_a(lvt_14_4_, lvt_15_4_, lvt_16_3_));
      }

      if (!lvt_12_1_ && this.field_73220_k.nextInt(this.field_177477_r.field_177777_D / 10) == 0 && this.field_177477_r.field_177783_C) {
         lvt_14_4_ = this.field_73220_k.nextInt(16) + 8;
         lvt_15_4_ = this.field_73220_k.nextInt(this.field_73220_k.nextInt(248) + 8);
         lvt_16_3_ = this.field_73220_k.nextInt(16) + 8;
         if (lvt_15_4_ < this.field_73230_p.func_181545_F() || this.field_73220_k.nextInt(this.field_177477_r.field_177777_D / 8) == 0) {
            (new WorldGenLakes(Blocks.field_150353_l)).func_180709_b(this.field_73230_p, this.field_73220_k, lvt_6_1_.func_177982_a(lvt_14_4_, lvt_15_4_, lvt_16_3_));
         }
      }

      if (this.field_177477_r.field_177837_s) {
         for(lvt_14_4_ = 0; lvt_14_4_ < this.field_177477_r.field_177835_t; ++lvt_14_4_) {
            lvt_15_4_ = this.field_73220_k.nextInt(16) + 8;
            lvt_16_3_ = this.field_73220_k.nextInt(256);
            int lvt_17_1_ = this.field_73220_k.nextInt(16) + 8;
            (new WorldGenDungeons()).func_180709_b(this.field_73230_p, this.field_73220_k, lvt_6_1_.func_177982_a(lvt_15_4_, lvt_16_3_, lvt_17_1_));
         }
      }

      lvt_7_1_.func_180624_a(this.field_73230_p, this.field_73220_k, new BlockPos(lvt_4_1_, 0, lvt_5_1_));
      SpawnerAnimals.func_77191_a(this.field_73230_p, lvt_7_1_, lvt_4_1_ + 8, lvt_5_1_ + 8, 16, 16, this.field_73220_k);
      lvt_6_1_ = lvt_6_1_.func_177982_a(8, 0, 8);

      for(lvt_14_4_ = 0; lvt_14_4_ < 16; ++lvt_14_4_) {
         for(lvt_15_4_ = 0; lvt_15_4_ < 16; ++lvt_15_4_) {
            BlockPos lvt_16_4_ = this.field_73230_p.func_175725_q(lvt_6_1_.func_177982_a(lvt_14_4_, 0, lvt_15_4_));
            BlockPos lvt_17_2_ = lvt_16_4_.func_177977_b();
            if (this.field_73230_p.func_175675_v(lvt_17_2_)) {
               this.field_73230_p.func_180501_a(lvt_17_2_, Blocks.field_150432_aD.func_176223_P(), 2);
            }

            if (this.field_73230_p.func_175708_f(lvt_16_4_, true)) {
               this.field_73230_p.func_180501_a(lvt_16_4_, Blocks.field_150431_aC.func_176223_P(), 2);
            }
         }
      }

      BlockFalling.field_149832_M = false;
   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      boolean lvt_5_1_ = false;
      if (this.field_177477_r.field_177852_y && this.field_73229_q && p_177460_2_.func_177416_w() < 3600L) {
         lvt_5_1_ |= this.field_177474_A.func_175794_a(this.field_73230_p, this.field_73220_k, new ChunkCoordIntPair(p_177460_3_, p_177460_4_));
      }

      return lvt_5_1_;
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
      BiomeGenBase lvt_3_1_ = this.field_73230_p.func_180494_b(p_177458_2_);
      if (this.field_73229_q) {
         if (p_177458_1_ == EnumCreatureType.MONSTER && this.field_73233_x.func_175798_a(p_177458_2_)) {
            return this.field_73233_x.func_82667_a();
         }

         if (p_177458_1_ == EnumCreatureType.MONSTER && this.field_177477_r.field_177852_y && this.field_177474_A.func_175796_a(this.field_73230_p, p_177458_2_)) {
            return this.field_177474_A.func_175799_b();
         }
      }

      return lvt_3_1_.func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return "Stronghold".equals(p_180513_2_) && this.field_73225_u != null ? this.field_73225_u.func_180706_b(p_180513_1_, p_180513_3_) : null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
      if (this.field_177477_r.field_177829_w && this.field_73229_q) {
         this.field_73223_w.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

      if (this.field_177477_r.field_177831_v && this.field_73229_q) {
         this.field_73224_v.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

      if (this.field_177477_r.field_177833_u && this.field_73229_q) {
         this.field_73225_u.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

      if (this.field_177477_r.field_177854_x && this.field_73229_q) {
         this.field_73233_x.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

      if (this.field_177477_r.field_177852_y && this.field_73229_q) {
         this.field_177474_A.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
