package net.minecraft.world.gen.feature;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenTrees extends WorldGenAbstractTree {
   private static final IBlockState field_181653_a;
   private static final IBlockState field_181654_b;
   private final int field_76533_a;
   private final boolean field_76531_b;
   private final IBlockState field_76532_c;
   private final IBlockState field_76530_d;

   public WorldGenTrees(boolean p_i2027_1_) {
      this(p_i2027_1_, 4, field_181653_a, field_181654_b, false);
   }

   public WorldGenTrees(boolean p_i46446_1_, int p_i46446_2_, IBlockState p_i46446_3_, IBlockState p_i46446_4_, boolean p_i46446_5_) {
      super(p_i46446_1_);
      this.field_76533_a = p_i46446_2_;
      this.field_76532_c = p_i46446_3_;
      this.field_76530_d = p_i46446_4_;
      this.field_76531_b = p_i46446_5_;
   }

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int lvt_4_1_ = p_180709_2_.nextInt(3) + this.field_76533_a;
      boolean lvt_5_1_ = true;
      if (p_180709_3_.func_177956_o() >= 1 && p_180709_3_.func_177956_o() + lvt_4_1_ + 1 <= 256) {
         byte lvt_7_1_;
         int lvt_9_5_;
         int lvt_10_2_;
         for(int lvt_6_1_ = p_180709_3_.func_177956_o(); lvt_6_1_ <= p_180709_3_.func_177956_o() + 1 + lvt_4_1_; ++lvt_6_1_) {
            lvt_7_1_ = 1;
            if (lvt_6_1_ == p_180709_3_.func_177956_o()) {
               lvt_7_1_ = 0;
            }

            if (lvt_6_1_ >= p_180709_3_.func_177956_o() + 1 + lvt_4_1_ - 2) {
               lvt_7_1_ = 2;
            }

            BlockPos.MutableBlockPos lvt_8_1_ = new BlockPos.MutableBlockPos();

            for(lvt_9_5_ = p_180709_3_.func_177958_n() - lvt_7_1_; lvt_9_5_ <= p_180709_3_.func_177958_n() + lvt_7_1_ && lvt_5_1_; ++lvt_9_5_) {
               for(lvt_10_2_ = p_180709_3_.func_177952_p() - lvt_7_1_; lvt_10_2_ <= p_180709_3_.func_177952_p() + lvt_7_1_ && lvt_5_1_; ++lvt_10_2_) {
                  if (lvt_6_1_ >= 0 && lvt_6_1_ < 256) {
                     if (!this.func_150523_a(p_180709_1_.func_180495_p(lvt_8_1_.func_181079_c(lvt_9_5_, lvt_6_1_, lvt_10_2_)).func_177230_c())) {
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
            if ((lvt_6_2_ == Blocks.field_150349_c || lvt_6_2_ == Blocks.field_150346_d || lvt_6_2_ == Blocks.field_150458_ak) && p_180709_3_.func_177956_o() < 256 - lvt_4_1_ - 1) {
               this.func_175921_a(p_180709_1_, p_180709_3_.func_177977_b());
               lvt_7_1_ = 3;
               int lvt_8_2_ = 0;

               int lvt_11_2_;
               int lvt_13_2_;
               int lvt_14_2_;
               BlockPos lvt_16_2_;
               for(lvt_9_5_ = p_180709_3_.func_177956_o() - lvt_7_1_ + lvt_4_1_; lvt_9_5_ <= p_180709_3_.func_177956_o() + lvt_4_1_; ++lvt_9_5_) {
                  lvt_10_2_ = lvt_9_5_ - (p_180709_3_.func_177956_o() + lvt_4_1_);
                  lvt_11_2_ = lvt_8_2_ + 1 - lvt_10_2_ / 2;

                  for(int lvt_12_1_ = p_180709_3_.func_177958_n() - lvt_11_2_; lvt_12_1_ <= p_180709_3_.func_177958_n() + lvt_11_2_; ++lvt_12_1_) {
                     lvt_13_2_ = lvt_12_1_ - p_180709_3_.func_177958_n();

                     for(lvt_14_2_ = p_180709_3_.func_177952_p() - lvt_11_2_; lvt_14_2_ <= p_180709_3_.func_177952_p() + lvt_11_2_; ++lvt_14_2_) {
                        int lvt_15_1_ = lvt_14_2_ - p_180709_3_.func_177952_p();
                        if (Math.abs(lvt_13_2_) != lvt_11_2_ || Math.abs(lvt_15_1_) != lvt_11_2_ || p_180709_2_.nextInt(2) != 0 && lvt_10_2_ != 0) {
                           lvt_16_2_ = new BlockPos(lvt_12_1_, lvt_9_5_, lvt_14_2_);
                           Block lvt_17_1_ = p_180709_1_.func_180495_p(lvt_16_2_).func_177230_c();
                           if (lvt_17_1_.func_149688_o() == Material.field_151579_a || lvt_17_1_.func_149688_o() == Material.field_151584_j || lvt_17_1_.func_149688_o() == Material.field_151582_l) {
                              this.func_175903_a(p_180709_1_, lvt_16_2_, this.field_76530_d);
                           }
                        }
                     }
                  }
               }

               for(lvt_9_5_ = 0; lvt_9_5_ < lvt_4_1_; ++lvt_9_5_) {
                  Block lvt_10_3_ = p_180709_1_.func_180495_p(p_180709_3_.func_177981_b(lvt_9_5_)).func_177230_c();
                  if (lvt_10_3_.func_149688_o() == Material.field_151579_a || lvt_10_3_.func_149688_o() == Material.field_151584_j || lvt_10_3_.func_149688_o() == Material.field_151582_l) {
                     this.func_175903_a(p_180709_1_, p_180709_3_.func_177981_b(lvt_9_5_), this.field_76532_c);
                     if (this.field_76531_b && lvt_9_5_ > 0) {
                        if (p_180709_2_.nextInt(3) > 0 && p_180709_1_.func_175623_d(p_180709_3_.func_177982_a(-1, lvt_9_5_, 0))) {
                           this.func_181651_a(p_180709_1_, p_180709_3_.func_177982_a(-1, lvt_9_5_, 0), BlockVine.field_176278_M);
                        }

                        if (p_180709_2_.nextInt(3) > 0 && p_180709_1_.func_175623_d(p_180709_3_.func_177982_a(1, lvt_9_5_, 0))) {
                           this.func_181651_a(p_180709_1_, p_180709_3_.func_177982_a(1, lvt_9_5_, 0), BlockVine.field_176280_O);
                        }

                        if (p_180709_2_.nextInt(3) > 0 && p_180709_1_.func_175623_d(p_180709_3_.func_177982_a(0, lvt_9_5_, -1))) {
                           this.func_181651_a(p_180709_1_, p_180709_3_.func_177982_a(0, lvt_9_5_, -1), BlockVine.field_176279_N);
                        }

                        if (p_180709_2_.nextInt(3) > 0 && p_180709_1_.func_175623_d(p_180709_3_.func_177982_a(0, lvt_9_5_, 1))) {
                           this.func_181651_a(p_180709_1_, p_180709_3_.func_177982_a(0, lvt_9_5_, 1), BlockVine.field_176273_b);
                        }
                     }
                  }
               }

               if (this.field_76531_b) {
                  for(lvt_9_5_ = p_180709_3_.func_177956_o() - 3 + lvt_4_1_; lvt_9_5_ <= p_180709_3_.func_177956_o() + lvt_4_1_; ++lvt_9_5_) {
                     lvt_10_2_ = lvt_9_5_ - (p_180709_3_.func_177956_o() + lvt_4_1_);
                     lvt_11_2_ = 2 - lvt_10_2_ / 2;
                     BlockPos.MutableBlockPos lvt_12_2_ = new BlockPos.MutableBlockPos();

                     for(lvt_13_2_ = p_180709_3_.func_177958_n() - lvt_11_2_; lvt_13_2_ <= p_180709_3_.func_177958_n() + lvt_11_2_; ++lvt_13_2_) {
                        for(lvt_14_2_ = p_180709_3_.func_177952_p() - lvt_11_2_; lvt_14_2_ <= p_180709_3_.func_177952_p() + lvt_11_2_; ++lvt_14_2_) {
                           lvt_12_2_.func_181079_c(lvt_13_2_, lvt_9_5_, lvt_14_2_);
                           if (p_180709_1_.func_180495_p(lvt_12_2_).func_177230_c().func_149688_o() == Material.field_151584_j) {
                              BlockPos lvt_15_2_ = lvt_12_2_.func_177976_e();
                              lvt_16_2_ = lvt_12_2_.func_177974_f();
                              BlockPos lvt_17_2_ = lvt_12_2_.func_177978_c();
                              BlockPos lvt_18_1_ = lvt_12_2_.func_177968_d();
                              if (p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(lvt_15_2_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                                 this.func_181650_b(p_180709_1_, lvt_15_2_, BlockVine.field_176278_M);
                              }

                              if (p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(lvt_16_2_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                                 this.func_181650_b(p_180709_1_, lvt_16_2_, BlockVine.field_176280_O);
                              }

                              if (p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(lvt_17_2_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                                 this.func_181650_b(p_180709_1_, lvt_17_2_, BlockVine.field_176279_N);
                              }

                              if (p_180709_2_.nextInt(4) == 0 && p_180709_1_.func_180495_p(lvt_18_1_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                                 this.func_181650_b(p_180709_1_, lvt_18_1_, BlockVine.field_176273_b);
                              }
                           }
                        }
                     }
                  }

                  if (p_180709_2_.nextInt(5) == 0 && lvt_4_1_ > 5) {
                     for(lvt_9_5_ = 0; lvt_9_5_ < 2; ++lvt_9_5_) {
                        Iterator lvt_10_5_ = EnumFacing.Plane.HORIZONTAL.iterator();

                        while(lvt_10_5_.hasNext()) {
                           EnumFacing lvt_11_3_ = (EnumFacing)lvt_10_5_.next();
                           if (p_180709_2_.nextInt(4 - lvt_9_5_) == 0) {
                              EnumFacing lvt_12_3_ = lvt_11_3_.func_176734_d();
                              this.func_181652_a(p_180709_1_, p_180709_2_.nextInt(3), p_180709_3_.func_177982_a(lvt_12_3_.func_82601_c(), lvt_4_1_ - 5 + lvt_9_5_, lvt_12_3_.func_82599_e()), lvt_11_3_);
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

   private void func_181652_a(World p_181652_1_, int p_181652_2_, BlockPos p_181652_3_, EnumFacing p_181652_4_) {
      this.func_175903_a(p_181652_1_, p_181652_3_, Blocks.field_150375_by.func_176223_P().func_177226_a(BlockCocoa.field_176501_a, p_181652_2_).func_177226_a(BlockCocoa.field_176387_N, p_181652_4_));
   }

   private void func_181651_a(World p_181651_1_, BlockPos p_181651_2_, PropertyBool p_181651_3_) {
      this.func_175903_a(p_181651_1_, p_181651_2_, Blocks.field_150395_bd.func_176223_P().func_177226_a(p_181651_3_, true));
   }

   private void func_181650_b(World p_181650_1_, BlockPos p_181650_2_, PropertyBool p_181650_3_) {
      this.func_181651_a(p_181650_1_, p_181650_2_, p_181650_3_);
      int lvt_4_1_ = 4;

      for(p_181650_2_ = p_181650_2_.func_177977_b(); p_181650_1_.func_180495_p(p_181650_2_).func_177230_c().func_149688_o() == Material.field_151579_a && lvt_4_1_ > 0; --lvt_4_1_) {
         this.func_181651_a(p_181650_1_, p_181650_2_, p_181650_3_);
         p_181650_2_ = p_181650_2_.func_177977_b();
      }

   }

   static {
      field_181653_a = Blocks.field_150364_r.func_176223_P().func_177226_a(BlockOldLog.field_176301_b, BlockPlanks.EnumType.OAK);
      field_181654_b = Blocks.field_150362_t.func_176223_P().func_177226_a(BlockOldLeaf.field_176239_P, BlockPlanks.EnumType.OAK).func_177226_a(BlockLeaves.field_176236_b, false);
   }
}
