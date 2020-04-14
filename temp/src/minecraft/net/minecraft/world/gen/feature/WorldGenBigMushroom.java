package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenBigMushroom extends WorldGenerator {
   private Block field_76523_a;

   public WorldGenBigMushroom(Block p_i46449_1_) {
      super(true);
      this.field_76523_a = p_i46449_1_;
   }

   public WorldGenBigMushroom() {
      super(false);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      if (this.field_76523_a == null) {
         this.field_76523_a = p_180709_2_.nextBoolean() ? Blocks.field_150420_aW : Blocks.field_150419_aX;
      }

      int lvt_4_1_ = p_180709_2_.nextInt(3) + 4;
      boolean lvt_5_1_ = true;
      if (p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + lvt_4_1_ + 1 < 256) {
         int lvt_9_2_;
         int lvt_10_2_;
         for(int lvt_6_1_ = p_180709_3_.func_177956_o(); lvt_6_1_ <= p_180709_3_.func_177956_o() + 1 + lvt_4_1_; ++lvt_6_1_) {
            int lvt_7_1_ = 3;
            if (lvt_6_1_ <= p_180709_3_.func_177956_o() + 3) {
               lvt_7_1_ = 0;
            }

            BlockPos.MutableBlockPos lvt_8_1_ = new BlockPos.MutableBlockPos();

            for(lvt_9_2_ = p_180709_3_.func_177958_n() - lvt_7_1_; lvt_9_2_ <= p_180709_3_.func_177958_n() + lvt_7_1_ && lvt_5_1_; ++lvt_9_2_) {
               for(lvt_10_2_ = p_180709_3_.func_177952_p() - lvt_7_1_; lvt_10_2_ <= p_180709_3_.func_177952_p() + lvt_7_1_ && lvt_5_1_; ++lvt_10_2_) {
                  if (lvt_6_1_ >= 0 && lvt_6_1_ < 256) {
                     Block lvt_11_1_ = p_180709_1_.func_180495_p(lvt_8_1_.func_181079_c(lvt_9_2_, lvt_6_1_, lvt_10_2_)).func_177230_c();
                     if (lvt_11_1_.func_149688_o() != Material.field_151579_a && lvt_11_1_.func_149688_o() != Material.field_151584_j) {
                        lvt_5_1_ = false;
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
            if (lvt_6_2_ != Blocks.field_150346_d && lvt_6_2_ != Blocks.field_150349_c && lvt_6_2_ != Blocks.field_150391_bh) {
               return false;
            } else {
               int lvt_7_2_ = p_180709_3_.func_177956_o() + lvt_4_1_;
               if (this.field_76523_a == Blocks.field_150419_aX) {
                  lvt_7_2_ = p_180709_3_.func_177956_o() + lvt_4_1_ - 3;
               }

               int lvt_8_2_;
               for(lvt_8_2_ = lvt_7_2_; lvt_8_2_ <= p_180709_3_.func_177956_o() + lvt_4_1_; ++lvt_8_2_) {
                  lvt_9_2_ = 1;
                  if (lvt_8_2_ < p_180709_3_.func_177956_o() + lvt_4_1_) {
                     ++lvt_9_2_;
                  }

                  if (this.field_76523_a == Blocks.field_150420_aW) {
                     lvt_9_2_ = 3;
                  }

                  lvt_10_2_ = p_180709_3_.func_177958_n() - lvt_9_2_;
                  int lvt_11_2_ = p_180709_3_.func_177958_n() + lvt_9_2_;
                  int lvt_12_1_ = p_180709_3_.func_177952_p() - lvt_9_2_;
                  int lvt_13_1_ = p_180709_3_.func_177952_p() + lvt_9_2_;

                  for(int lvt_14_1_ = lvt_10_2_; lvt_14_1_ <= lvt_11_2_; ++lvt_14_1_) {
                     for(int lvt_15_1_ = lvt_12_1_; lvt_15_1_ <= lvt_13_1_; ++lvt_15_1_) {
                        int lvt_16_1_ = 5;
                        if (lvt_14_1_ == lvt_10_2_) {
                           --lvt_16_1_;
                        } else if (lvt_14_1_ == lvt_11_2_) {
                           ++lvt_16_1_;
                        }

                        if (lvt_15_1_ == lvt_12_1_) {
                           lvt_16_1_ -= 3;
                        } else if (lvt_15_1_ == lvt_13_1_) {
                           lvt_16_1_ += 3;
                        }

                        BlockHugeMushroom.EnumType lvt_17_1_ = BlockHugeMushroom.EnumType.func_176895_a(lvt_16_1_);
                        if (this.field_76523_a == Blocks.field_150420_aW || lvt_8_2_ < p_180709_3_.func_177956_o() + lvt_4_1_) {
                           if ((lvt_14_1_ == lvt_10_2_ || lvt_14_1_ == lvt_11_2_) && (lvt_15_1_ == lvt_12_1_ || lvt_15_1_ == lvt_13_1_)) {
                              continue;
                           }

                           if (lvt_14_1_ == p_180709_3_.func_177958_n() - (lvt_9_2_ - 1) && lvt_15_1_ == lvt_12_1_) {
                              lvt_17_1_ = BlockHugeMushroom.EnumType.NORTH_WEST;
                           }

                           if (lvt_14_1_ == lvt_10_2_ && lvt_15_1_ == p_180709_3_.func_177952_p() - (lvt_9_2_ - 1)) {
                              lvt_17_1_ = BlockHugeMushroom.EnumType.NORTH_WEST;
                           }

                           if (lvt_14_1_ == p_180709_3_.func_177958_n() + (lvt_9_2_ - 1) && lvt_15_1_ == lvt_12_1_) {
                              lvt_17_1_ = BlockHugeMushroom.EnumType.NORTH_EAST;
                           }

                           if (lvt_14_1_ == lvt_11_2_ && lvt_15_1_ == p_180709_3_.func_177952_p() - (lvt_9_2_ - 1)) {
                              lvt_17_1_ = BlockHugeMushroom.EnumType.NORTH_EAST;
                           }

                           if (lvt_14_1_ == p_180709_3_.func_177958_n() - (lvt_9_2_ - 1) && lvt_15_1_ == lvt_13_1_) {
                              lvt_17_1_ = BlockHugeMushroom.EnumType.SOUTH_WEST;
                           }

                           if (lvt_14_1_ == lvt_10_2_ && lvt_15_1_ == p_180709_3_.func_177952_p() + (lvt_9_2_ - 1)) {
                              lvt_17_1_ = BlockHugeMushroom.EnumType.SOUTH_WEST;
                           }

                           if (lvt_14_1_ == p_180709_3_.func_177958_n() + (lvt_9_2_ - 1) && lvt_15_1_ == lvt_13_1_) {
                              lvt_17_1_ = BlockHugeMushroom.EnumType.SOUTH_EAST;
                           }

                           if (lvt_14_1_ == lvt_11_2_ && lvt_15_1_ == p_180709_3_.func_177952_p() + (lvt_9_2_ - 1)) {
                              lvt_17_1_ = BlockHugeMushroom.EnumType.SOUTH_EAST;
                           }
                        }

                        if (lvt_17_1_ == BlockHugeMushroom.EnumType.CENTER && lvt_8_2_ < p_180709_3_.func_177956_o() + lvt_4_1_) {
                           lvt_17_1_ = BlockHugeMushroom.EnumType.ALL_INSIDE;
                        }

                        if (p_180709_3_.func_177956_o() >= p_180709_3_.func_177956_o() + lvt_4_1_ - 1 || lvt_17_1_ != BlockHugeMushroom.EnumType.ALL_INSIDE) {
                           BlockPos lvt_18_1_ = new BlockPos(lvt_14_1_, lvt_8_2_, lvt_15_1_);
                           if (!p_180709_1_.func_180495_p(lvt_18_1_).func_177230_c().func_149730_j()) {
                              this.func_175903_a(p_180709_1_, lvt_18_1_, this.field_76523_a.func_176223_P().func_177226_a(BlockHugeMushroom.field_176380_a, lvt_17_1_));
                           }
                        }
                     }
                  }
               }

               for(lvt_8_2_ = 0; lvt_8_2_ < lvt_4_1_; ++lvt_8_2_) {
                  Block lvt_9_3_ = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(lvt_8_2_)).func_177230_c();
                  if (!lvt_9_3_.func_149730_j()) {
                     this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(lvt_8_2_), this.field_76523_a.func_176223_P().func_177226_a(BlockHugeMushroom.field_176380_a, BlockHugeMushroom.EnumType.STEM));
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
