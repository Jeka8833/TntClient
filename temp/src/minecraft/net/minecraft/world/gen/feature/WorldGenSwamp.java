package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSwamp extends WorldGenAbstractTree {
   private static final IBlockState field_181648_a;
   private static final IBlockState field_181649_b;

   public WorldGenSwamp() {
      super(false);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_;
      for(lvt_4_1_ = p_180709_2_.nextInt(4) + 5; p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c().func_149688_o() == Material.field_151586_h; p_180709_3_ = p_180709_3_.func_177977_b()) {
      }

      boolean lvt_5_1_ = true;
      if (p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + lvt_4_1_ + 1 <= 256) {
         int lvt_9_3_;
         int lvt_10_2_;
         for(int lvt_6_1_ = p_180709_3_.func_177956_o(); lvt_6_1_ <= p_180709_3_.func_177956_o() + 1 + lvt_4_1_; ++lvt_6_1_) {
            int lvt_7_1_ = 1;
            if (lvt_6_1_ == p_180709_3_.func_177956_o()) {
               lvt_7_1_ = 0;
            }

            if (lvt_6_1_ >= p_180709_3_.func_177956_o() + 1 + lvt_4_1_ - 2) {
               lvt_7_1_ = 3;
            }

            BlockPos.MutableBlockPos lvt_8_1_ = new BlockPos.MutableBlockPos();

            for(lvt_9_3_ = p_180709_3_.func_177958_n() - lvt_7_1_; lvt_9_3_ <= p_180709_3_.func_177958_n() + lvt_7_1_ && lvt_5_1_; ++lvt_9_3_) {
               for(lvt_10_2_ = p_180709_3_.func_177952_p() - lvt_7_1_; lvt_10_2_ <= p_180709_3_.func_177952_p() + lvt_7_1_ && lvt_5_1_; ++lvt_10_2_) {
                  if (lvt_6_1_ >= 0 && lvt_6_1_ < 256) {
                     Block lvt_11_1_ = p_180709_1_.func_180495_p(lvt_8_1_.func_181079_c(lvt_9_3_, lvt_6_1_, lvt_10_2_)).func_177230_c();
                     if (lvt_11_1_.func_149688_o() != Material.field_151579_a && lvt_11_1_.func_149688_o() != Material.field_151584_j) {
                        if (lvt_11_1_ != Blocks.field_150355_j && lvt_11_1_ != Blocks.field_150358_i) {
                           lvt_5_1_ = false;
                        } else if (lvt_6_1_ > p_180709_3_.func_177956_o()) {
                           lvt_5_1_ = false;
                        }
                     }
                  } else {
                     lvt_5_1_ = false;
                  }
               }
            }
         }

         if (!lvt_5_1_) {
            return false;
         } else {
            Block lvt_6_2_ = p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c();
            if ((lvt_6_2_ == Blocks.field_150349_c || lvt_6_2_ == Blocks.field_150346_d) && p_180709_3_.func_177956_o() < 256 - lvt_4_1_ - 1) {
               this.func_175921_a(p_180709_1_, p_180709_3_.func_177977_b());

               int lvt_12_2_;
               BlockPos lvt_14_2_;
               int lvt_7_4_;
               int lvt_8_2_;
               int lvt_11_3_;
               for(lvt_7_4_ = p_180709_3_.func_177956_o() - 3 + lvt_4_1_; lvt_7_4_ <= p_180709_3_.func_177956_o() + lvt_4_1_; ++lvt_7_4_) {
                  lvt_8_2_ = lvt_7_4_ - (p_180709_3_.func_177956_o() + lvt_4_1_);
                  lvt_9_3_ = 2 - lvt_8_2_ / 2;

                  for(lvt_10_2_ = p_180709_3_.func_177958_n() - lvt_9_3_; lvt_10_2_ <= p_180709_3_.func_177958_n() + lvt_9_3_; ++lvt_10_2_) {
                     lvt_11_3_ = lvt_10_2_ - p_180709_3_.func_177958_n();

                     for(lvt_12_2_ = p_180709_3_.func_177952_p() - lvt_9_3_; lvt_12_2_ <= p_180709_3_.func_177952_p() + lvt_9_3_; ++lvt_12_2_) {
                        int lvt_13_1_ = lvt_12_2_ - p_180709_3_.func_177952_p();
                        if (Math.abs(lvt_11_3_) != lvt_9_3_ || Math.abs(lvt_13_1_) != lvt_9_3_ || p_180709_2_.nextInt(2) != 0 && lvt_8_2_ != 0) {
                           lvt_14_2_ = new BlockPos(lvt_10_2_, lvt_7_4_, lvt_12_2_);
                           if (!p_180709_1_.func_180495_p(lvt_14_2_).func_177230_c().func_149730_j()) {
                              this.func_175903_a(p_180709_1_, lvt_14_2_, field_181649_b);
                           }
                        }
                     }
                  }
               }

               for(lvt_7_4_ = 0; lvt_7_4_ < lvt_4_1_; ++lvt_7_4_) {
                  Block lvt_8_3_ = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(lvt_7_4_)).func_177230_c();
                  if (lvt_8_3_.func_149688_o() == Material.field_151579_a || lvt_8_3_.func_149688_o() == Material.field_151584_j || lvt_8_3_ == Blocks.field_150358_i || lvt_8_3_ == Blocks.field_150355_j) {
                     this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(lvt_7_4_), field_181648_a);
                  }
               }

               for(lvt_7_4_ = p_180709_3_.func_177956_o() - 3 + lvt_4_1_; lvt_7_4_ <= p_180709_3_.func_177956_o() + lvt_4_1_; ++lvt_7_4_) {
                  lvt_8_2_ = lvt_7_4_ - (p_180709_3_.func_177956_o() + lvt_4_1_);
                  lvt_9_3_ = 2 - lvt_8_2_ / 2;
                  BlockPos.MutableBlockPos lvt_10_3_ = new BlockPos.MutableBlockPos();

                  for(lvt_11_3_ = p_180709_3_.func_177958_n() - lvt_9_3_; lvt_11_3_ <= p_180709_3_.func_177958_n() + lvt_9_3_; ++lvt_11_3_) {
                     for(lvt_12_2_ = p_180709_3_.func_177952_p() - lvt_9_3_; lvt_12_2_ <= p_180709_3_.func_177952_p() + lvt_9_3_; ++lvt_12_2_) {
                        lvt_10_3_.func_181079_c(lvt_11_3_, lvt_7_4_, lvt_12_2_);
                        if (p_180709_1_.func_180495_p(lvt_10_3_).func_177230_c().func_149688_o() == Material.field_151584_j) {
                           BlockPos lvt_13_2_ = lvt_10_3_.func_177976_e();
                           lvt_14_2_ = lvt_10_3_.func_177974_f();
                           BlockPos lvt_15_1_ = lvt_10_3_.func_177978_c();
                           BlockPos lvt_16_1_ = lvt_10_3_.func_177968_d();
                           if (p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(lvt_13_2_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                              this.func_181647_a(p_180709_1_, lvt_13_2_, BlockVine.field_176278_M);
                           }

                           if (p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(lvt_14_2_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                              this.func_181647_a(p_180709_1_, lvt_14_2_, BlockVine.field_176280_O);
                           }

                           if (p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(lvt_15_1_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                              this.func_181647_a(p_180709_1_, lvt_15_1_, BlockVine.field_176279_N);
                           }

                           if (p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(lvt_16_1_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                              this.func_181647_a(p_180709_1_, lvt_16_1_, BlockVine.field_176273_b);
                           }
                        }
                     }
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private void func_181647_a(World p_181647_1_, BlockPos p_181647_2_, PropertyBool p_181647_3_) {
      IBlockState lvt_4_1_ = Blocks.field_150395_bd.func_176223_P().func_177226_a(p_181647_3_, true);
      this.func_175903_a(p_181647_1_, p_181647_2_, lvt_4_1_);
      int lvt_5_1_ = 4;

      for(p_181647_2_ = p_181647_2_.func_177977_b(); p_181647_1_.func_180495_p(p_181647_2_).func_177230_c().func_149688_o() == Material.field_151579_a && lvt_5_1_ > 0; --lvt_5_1_) {
         this.func_175903_a(p_181647_1_, p_181647_2_, lvt_4_1_);
         p_181647_2_ = p_181647_2_.func_177977_b();
      }

   }

   static {
      field_181648_a = Blocks.field_150364_r.func_176223_P().func_177226_a(BlockOldLog.field_176301_b, BlockPlanks.EnumType.OAK);
      field_181649_b = Blocks.field_150362_t.func_176223_P().func_177226_a(BlockOldLeaf.field_176239_P, BlockPlanks.EnumType.OAK).func_177226_a(BlockOldLeaf.field_176236_b, false);
   }
}
