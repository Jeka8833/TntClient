package net.minecraft.world.biome;

import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMesa extends BiomeGenBase {
   private IBlockState[] field_150621_aC;
   private long field_150622_aD;
   private NoiseGeneratorPerlin field_150623_aE;
   private NoiseGeneratorPerlin field_150624_aF;
   private NoiseGeneratorPerlin field_150625_aG;
   private boolean field_150626_aH;
   private boolean field_150620_aI;

   public BiomeGenMesa(int p_i45380_1_, boolean p_i45380_2_, boolean p_i45380_3_) {
      super(p_i45380_1_);
      this.field_150626_aH = p_i45380_2_;
      this.field_150620_aI = p_i45380_3_;
      this.func_76745_m();
      this.func_76732_a(2.0F, 0.0F);
      this.field_76762_K.clear();
      this.field_76752_A = Blocks.field_150354_m.func_176223_P().func_177226_a(BlockSand.field_176504_a, BlockSand.EnumType.RED_SAND);
      this.field_76753_B = Blocks.field_150406_ce.func_176223_P();
      this.field_76760_I.field_76832_z = -999;
      this.field_76760_I.field_76804_C = 20;
      this.field_76760_I.field_76799_E = 3;
      this.field_76760_I.field_76800_F = 5;
      this.field_76760_I.field_76802_A = 0;
      this.field_76762_K.clear();
      if (p_i45380_3_) {
         this.field_76760_I.field_76832_z = 5;
      }

   }

   public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
      return this.field_76757_N;
   }

   public int func_180625_c(BlockPos p_180625_1_) {
      return 10387789;
   }

   public int func_180627_b(BlockPos p_180627_1_) {
      return 9470285;
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      super.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
   }

   public void func_180622_a(World p_180622_1_, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
      if (this.field_150621_aC == null || this.field_150622_aD != p_180622_1_.func_72905_C()) {
         this.func_150619_a(p_180622_1_.func_72905_C());
      }

      if (this.field_150623_aE == null || this.field_150624_aF == null || this.field_150622_aD != p_180622_1_.func_72905_C()) {
         Random lvt_8_1_ = new Random(this.field_150622_aD);
         this.field_150623_aE = new NoiseGeneratorPerlin(lvt_8_1_, 4);
         this.field_150624_aF = new NoiseGeneratorPerlin(lvt_8_1_, 1);
      }

      this.field_150622_aD = p_180622_1_.func_72905_C();
      double lvt_8_2_ = 0.0D;
      int lvt_10_2_;
      int lvt_11_2_;
      if (this.field_150626_aH) {
         lvt_10_2_ = (p_180622_4_ & -16) + (p_180622_5_ & 15);
         lvt_11_2_ = (p_180622_5_ & -16) + (p_180622_4_ & 15);
         double lvt_12_1_ = Math.min(Math.abs(p_180622_6_), this.field_150623_aE.func_151601_a((double)lvt_10_2_ * 0.25D, (double)lvt_11_2_ * 0.25D));
         if (lvt_12_1_ > 0.0D) {
            double lvt_14_1_ = 0.001953125D;
            double lvt_16_1_ = Math.abs(this.field_150624_aF.func_151601_a((double)lvt_10_2_ * lvt_14_1_, (double)lvt_11_2_ * lvt_14_1_));
            lvt_8_2_ = lvt_12_1_ * lvt_12_1_ * 2.5D;
            double lvt_18_1_ = Math.ceil(lvt_16_1_ * 50.0D) + 14.0D;
            if (lvt_8_2_ > lvt_18_1_) {
               lvt_8_2_ = lvt_18_1_;
            }

            lvt_8_2_ += 64.0D;
         }
      }

      lvt_10_2_ = p_180622_4_ & 15;
      lvt_11_2_ = p_180622_5_ & 15;
      int lvt_12_2_ = p_180622_1_.func_181545_F();
      IBlockState lvt_13_1_ = Blocks.field_150406_ce.func_176223_P();
      IBlockState lvt_14_2_ = this.field_76753_B;
      int lvt_15_1_ = (int)(p_180622_6_ / 3.0D + 3.0D + p_180622_2_.nextDouble() * 0.25D);
      boolean lvt_16_2_ = Math.cos(p_180622_6_ / 3.0D * 3.141592653589793D) > 0.0D;
      int lvt_17_1_ = -1;
      boolean lvt_18_2_ = false;

      for(int lvt_19_1_ = 255; lvt_19_1_ >= 0; --lvt_19_1_) {
         if (p_180622_3_.func_177856_a(lvt_11_2_, lvt_19_1_, lvt_10_2_).func_177230_c().func_149688_o() == Material.field_151579_a && lvt_19_1_ < (int)lvt_8_2_) {
            p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, Blocks.field_150348_b.func_176223_P());
         }

         if (lvt_19_1_ <= p_180622_2_.nextInt(5)) {
            p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, Blocks.field_150357_h.func_176223_P());
         } else {
            IBlockState lvt_20_1_ = p_180622_3_.func_177856_a(lvt_11_2_, lvt_19_1_, lvt_10_2_);
            if (lvt_20_1_.func_177230_c().func_149688_o() == Material.field_151579_a) {
               lvt_17_1_ = -1;
            } else if (lvt_20_1_.func_177230_c() == Blocks.field_150348_b) {
               IBlockState lvt_21_3_;
               if (lvt_17_1_ == -1) {
                  lvt_18_2_ = false;
                  if (lvt_15_1_ <= 0) {
                     lvt_13_1_ = null;
                     lvt_14_2_ = Blocks.field_150348_b.func_176223_P();
                  } else if (lvt_19_1_ >= lvt_12_2_ - 4 && lvt_19_1_ <= lvt_12_2_ + 1) {
                     lvt_13_1_ = Blocks.field_150406_ce.func_176223_P();
                     lvt_14_2_ = this.field_76753_B;
                  }

                  if (lvt_19_1_ < lvt_12_2_ && (lvt_13_1_ == null || lvt_13_1_.func_177230_c().func_149688_o() == Material.field_151579_a)) {
                     lvt_13_1_ = Blocks.field_150355_j.func_176223_P();
                  }

                  lvt_17_1_ = lvt_15_1_ + Math.max(0, lvt_19_1_ - lvt_12_2_);
                  if (lvt_19_1_ < lvt_12_2_ - 1) {
                     p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, lvt_14_2_);
                     if (lvt_14_2_.func_177230_c() == Blocks.field_150406_ce) {
                        p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, lvt_14_2_.func_177230_c().func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.ORANGE));
                     }
                  } else if (this.field_150620_aI && lvt_19_1_ > 86 + lvt_15_1_ * 2) {
                     if (lvt_16_2_) {
                        p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.COARSE_DIRT));
                     } else {
                        p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, Blocks.field_150349_c.func_176223_P());
                     }
                  } else if (lvt_19_1_ <= lvt_12_2_ + 3 + lvt_15_1_) {
                     p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, this.field_76752_A);
                     lvt_18_2_ = true;
                  } else {
                     if (lvt_19_1_ >= 64 && lvt_19_1_ <= 127) {
                        if (lvt_16_2_) {
                           lvt_21_3_ = Blocks.field_150405_ch.func_176223_P();
                        } else {
                           lvt_21_3_ = this.func_180629_a(p_180622_4_, lvt_19_1_, p_180622_5_);
                        }
                     } else {
                        lvt_21_3_ = Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.ORANGE);
                     }

                     p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, lvt_21_3_);
                  }
               } else if (lvt_17_1_ > 0) {
                  --lvt_17_1_;
                  if (lvt_18_2_) {
                     p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.ORANGE));
                  } else {
                     lvt_21_3_ = this.func_180629_a(p_180622_4_, lvt_19_1_, p_180622_5_);
                     p_180622_3_.func_177855_a(lvt_11_2_, lvt_19_1_, lvt_10_2_, lvt_21_3_);
                  }
               }
            }
         }
      }

   }

   private void func_150619_a(long p_150619_1_) {
      this.field_150621_aC = new IBlockState[64];
      Arrays.fill(this.field_150621_aC, Blocks.field_150405_ch.func_176223_P());
      Random lvt_3_1_ = new Random(p_150619_1_);
      this.field_150625_aG = new NoiseGeneratorPerlin(lvt_3_1_, 1);

      int lvt_4_2_;
      for(lvt_4_2_ = 0; lvt_4_2_ < 64; ++lvt_4_2_) {
         lvt_4_2_ += lvt_3_1_.nextInt(5) + 1;
         if (lvt_4_2_ < 64) {
            this.field_150621_aC[lvt_4_2_] = Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.ORANGE);
         }
      }

      lvt_4_2_ = lvt_3_1_.nextInt(4) + 2;

      int lvt_5_2_;
      int lvt_6_3_;
      int lvt_7_4_;
      int lvt_8_4_;
      for(lvt_5_2_ = 0; lvt_5_2_ < lvt_4_2_; ++lvt_5_2_) {
         lvt_6_3_ = lvt_3_1_.nextInt(3) + 1;
         lvt_7_4_ = lvt_3_1_.nextInt(64);

         for(lvt_8_4_ = 0; lvt_7_4_ + lvt_8_4_ < 64 && lvt_8_4_ < lvt_6_3_; ++lvt_8_4_) {
            this.field_150621_aC[lvt_7_4_ + lvt_8_4_] = Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.YELLOW);
         }
      }

      lvt_5_2_ = lvt_3_1_.nextInt(4) + 2;

      int lvt_9_2_;
      for(lvt_6_3_ = 0; lvt_6_3_ < lvt_5_2_; ++lvt_6_3_) {
         lvt_7_4_ = lvt_3_1_.nextInt(3) + 2;
         lvt_8_4_ = lvt_3_1_.nextInt(64);

         for(lvt_9_2_ = 0; lvt_8_4_ + lvt_9_2_ < 64 && lvt_9_2_ < lvt_7_4_; ++lvt_9_2_) {
            this.field_150621_aC[lvt_8_4_ + lvt_9_2_] = Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.BROWN);
         }
      }

      lvt_6_3_ = lvt_3_1_.nextInt(4) + 2;

      for(lvt_7_4_ = 0; lvt_7_4_ < lvt_6_3_; ++lvt_7_4_) {
         lvt_8_4_ = lvt_3_1_.nextInt(3) + 1;
         lvt_9_2_ = lvt_3_1_.nextInt(64);

         for(int lvt_10_1_ = 0; lvt_9_2_ + lvt_10_1_ < 64 && lvt_10_1_ < lvt_8_4_; ++lvt_10_1_) {
            this.field_150621_aC[lvt_9_2_ + lvt_10_1_] = Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.RED);
         }
      }

      lvt_7_4_ = lvt_3_1_.nextInt(3) + 3;
      lvt_8_4_ = 0;

      for(lvt_9_2_ = 0; lvt_9_2_ < lvt_7_4_; ++lvt_9_2_) {
         int lvt_10_2_ = 1;
         lvt_8_4_ += lvt_3_1_.nextInt(16) + 4;

         for(int lvt_11_1_ = 0; lvt_8_4_ + lvt_11_1_ < 64 && lvt_11_1_ < lvt_10_2_; ++lvt_11_1_) {
            this.field_150621_aC[lvt_8_4_ + lvt_11_1_] = Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.WHITE);
            if (lvt_8_4_ + lvt_11_1_ > 1 && lvt_3_1_.nextBoolean()) {
               this.field_150621_aC[lvt_8_4_ + lvt_11_1_ - 1] = Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.SILVER);
            }

            if (lvt_8_4_ + lvt_11_1_ < 63 && lvt_3_1_.nextBoolean()) {
               this.field_150621_aC[lvt_8_4_ + lvt_11_1_ + 1] = Blocks.field_150406_ce.func_176223_P().func_177226_a(BlockColored.field_176581_a, EnumDyeColor.SILVER);
            }
         }
      }

   }

   private IBlockState func_180629_a(int p_180629_1_, int p_180629_2_, int p_180629_3_) {
      int lvt_4_1_ = (int)Math.round(this.field_150625_aG.func_151601_a((double)p_180629_1_ * 1.0D / 512.0D, (double)p_180629_1_ * 1.0D / 512.0D) * 2.0D);
      return this.field_150621_aC[(p_180629_2_ + lvt_4_1_ + 64) % 64];
   }

   protected BiomeGenBase func_180277_d(int p_180277_1_) {
      boolean lvt_2_1_ = this.field_76756_M == BiomeGenBase.field_150589_Z.field_76756_M;
      BiomeGenMesa lvt_3_1_ = new BiomeGenMesa(p_180277_1_, lvt_2_1_, this.field_150620_aI);
      if (!lvt_2_1_) {
         lvt_3_1_.func_150570_a(field_150591_g);
         lvt_3_1_.func_76735_a(this.field_76791_y + " M");
      } else {
         lvt_3_1_.func_76735_a(this.field_76791_y + " (Bryce)");
      }

      lvt_3_1_.func_150557_a(this.field_76790_z, true);
      return lvt_3_1_;
   }
}
