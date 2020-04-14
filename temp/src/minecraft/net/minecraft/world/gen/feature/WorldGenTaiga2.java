package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenTaiga2 extends WorldGenAbstractTree {
   private static final IBlockState field_181645_a;
   private static final IBlockState field_181646_b;

   public WorldGenTaiga2(boolean p_i2025_1_) {
      super(p_i2025_1_);
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = p_180709_2_.nextInt(4) + 6;
      int lvt_5_1_ = 1 + p_180709_2_.nextInt(2);
      int lvt_6_1_ = lvt_4_1_ - lvt_5_1_;
      int lvt_7_1_ = 2 + p_180709_2_.nextInt(2);
      boolean lvt_8_1_ = true;
      if (p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + lvt_4_1_ + 1 <= 256) {
         int lvt_13_1_;
         int lvt_10_2_;
         for(int lvt_9_1_ = p_180709_3_.func_177956_o(); lvt_9_1_ <= p_180709_3_.func_177956_o() + 1 + lvt_4_1_ && lvt_8_1_; ++lvt_9_1_) {
            int lvt_10_1_ = true;
            if (lvt_9_1_ - p_180709_3_.func_177956_o() < lvt_5_1_) {
               lvt_10_2_ = 0;
            } else {
               lvt_10_2_ = lvt_7_1_;
            }

            BlockPos.MutableBlockPos lvt_11_1_ = new BlockPos.MutableBlockPos();

            for(int lvt_12_1_ = p_180709_3_.func_177958_n() - lvt_10_2_; lvt_12_1_ <= p_180709_3_.func_177958_n() + lvt_10_2_ && lvt_8_1_; ++lvt_12_1_) {
               for(lvt_13_1_ = p_180709_3_.func_177952_p() - lvt_10_2_; lvt_13_1_ <= p_180709_3_.func_177952_p() + lvt_10_2_ && lvt_8_1_; ++lvt_13_1_) {
                  if (lvt_9_1_ >= 0 && lvt_9_1_ < 256) {
                     Block lvt_14_1_ = p_180709_1_.func_180495_p(lvt_11_1_.func_181079_c(lvt_12_1_, lvt_9_1_, lvt_13_1_)).func_177230_c();
                     if (lvt_14_1_.func_149688_o() != Material.field_151579_a && lvt_14_1_.func_149688_o() != Material.field_151584_j) {
                        lvt_8_1_ = false;
                     }
                  } else {
                     lvt_8_1_ = false;
                  }
               }
            }
         }

         if (!lvt_8_1_) {
            return false;
         } else {
            Block lvt_9_2_ = p_180709_1_.func_180495_p(p_180709_3_.func_177977_b()).func_177230_c();
            if ((lvt_9_2_ == Blocks.field_150349_c || lvt_9_2_ == Blocks.field_150346_d || lvt_9_2_ == Blocks.field_150458_ak) && p_180709_3_.func_177956_o() < 256 - lvt_4_1_ - 1) {
               this.func_175921_a(p_180709_1_, p_180709_3_.func_177977_b());
               lvt_10_2_ = p_180709_2_.nextInt(2);
               int lvt_11_2_ = 1;
               int lvt_12_2_ = 0;

               int lvt_14_2_;
               for(lvt_13_1_ = 0; lvt_13_1_ <= lvt_6_1_; ++lvt_13_1_) {
                  lvt_14_2_ = p_180709_3_.func_177956_o() + lvt_4_1_ - lvt_13_1_;

                  for(int lvt_15_1_ = p_180709_3_.func_177958_n() - lvt_10_2_; lvt_15_1_ <= p_180709_3_.func_177958_n() + lvt_10_2_; ++lvt_15_1_) {
                     int lvt_16_1_ = lvt_15_1_ - p_180709_3_.func_177958_n();

                     for(int lvt_17_1_ = p_180709_3_.func_177952_p() - lvt_10_2_; lvt_17_1_ <= p_180709_3_.func_177952_p() + lvt_10_2_; ++lvt_17_1_) {
                        int lvt_18_1_ = lvt_17_1_ - p_180709_3_.func_177952_p();
                        if (Math.abs(lvt_16_1_) != lvt_10_2_ || Math.abs(lvt_18_1_) != lvt_10_2_ || lvt_10_2_ <= 0) {
                           BlockPos lvt_19_1_ = new BlockPos(lvt_15_1_, lvt_14_2_, lvt_17_1_);
                           if (!p_180709_1_.func_180495_p(lvt_19_1_).func_177230_c().func_149730_j()) {
                              this.func_175903_a(p_180709_1_, lvt_19_1_, field_181646_b);
                           }
                        }
                     }
                  }

                  if (lvt_10_2_ >= lvt_11_2_) {
                     lvt_10_2_ = lvt_12_2_;
                     lvt_12_2_ = 1;
                     ++lvt_11_2_;
                     if (lvt_11_2_ > lvt_7_1_) {
                        lvt_11_2_ = lvt_7_1_;
                     }
                  } else {
                     ++lvt_10_2_;
                  }
               }

               lvt_13_1_ = p_180709_2_.nextInt(3);

               for(lvt_14_2_ = 0; lvt_14_2_ < lvt_4_1_ - lvt_13_1_; ++lvt_14_2_) {
                  Block lvt_15_2_ = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(lvt_14_2_)).func_177230_c();
                  if (lvt_15_2_.func_149688_o() == Material.field_151579_a || lvt_15_2_.func_149688_o() == Material.field_151584_j) {
                     this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(lvt_14_2_), field_181645_a);
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

   static {
      field_181645_a = Blocks.field_150364_r.func_176223_P().func_177226_a(BlockOldLog.field_176301_b, BlockPlanks.EnumType.SPRUCE);
      field_181646_b = Blocks.field_150362_t.func_176223_P().func_177226_a(BlockOldLeaf.field_176239_P, BlockPlanks.EnumType.SPRUCE).func_177226_a(BlockLeaves.field_176236_b, false);
   }
}
