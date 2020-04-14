package net.minecraft.world.pathfinder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class WalkNodeProcessor extends NodeProcessor {
   private boolean field_176180_f;
   private boolean field_176181_g;
   private boolean field_176183_h;
   private boolean field_176184_i;
   private boolean field_176182_j;

   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_) {
      super.func_176162_a(p_176162_1_, p_176162_2_);
      this.field_176182_j = this.field_176183_h;
   }

   public void func_176163_a() {
      super.func_176163_a();
      this.field_176183_h = this.field_176182_j;
   }

   public PathPoint func_176161_a(Entity p_176161_1_) {
      int lvt_2_1_;
      if (this.field_176184_i && p_176161_1_.func_70090_H()) {
         lvt_2_1_ = (int)p_176161_1_.func_174813_aQ().field_72338_b;
         BlockPos.MutableBlockPos lvt_3_1_ = new BlockPos.MutableBlockPos(MathHelper.func_76128_c(p_176161_1_.field_70165_t), lvt_2_1_, MathHelper.func_76128_c(p_176161_1_.field_70161_v));

         for(Block lvt_4_1_ = this.field_176169_a.func_180495_p(lvt_3_1_).func_177230_c(); lvt_4_1_ == Blocks.field_150358_i || lvt_4_1_ == Blocks.field_150355_j; lvt_4_1_ = this.field_176169_a.func_180495_p(lvt_3_1_).func_177230_c()) {
            ++lvt_2_1_;
            lvt_3_1_.func_181079_c(MathHelper.func_76128_c(p_176161_1_.field_70165_t), lvt_2_1_, MathHelper.func_76128_c(p_176161_1_.field_70161_v));
         }

         this.field_176183_h = false;
      } else {
         lvt_2_1_ = MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72338_b + 0.5D);
      }

      return this.func_176159_a(MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72340_a), lvt_2_1_, MathHelper.func_76128_c(p_176161_1_.func_174813_aQ().field_72339_c));
   }

   public PathPoint func_176160_a(Entity p_176160_1_, double p_176160_2_, double p_176160_4_, double p_176160_6_) {
      return this.func_176159_a(MathHelper.func_76128_c(p_176160_2_ - (double)(p_176160_1_.field_70130_N / 2.0F)), MathHelper.func_76128_c(p_176160_4_), MathHelper.func_76128_c(p_176160_6_ - (double)(p_176160_1_.field_70130_N / 2.0F)));
   }

   public int func_176164_a(PathPoint[] p_176164_1_, Entity p_176164_2_, PathPoint p_176164_3_, PathPoint p_176164_4_, float p_176164_5_) {
      int lvt_6_1_ = 0;
      int lvt_7_1_ = 0;
      if (this.func_176177_a(p_176164_2_, p_176164_3_.field_75839_a, p_176164_3_.field_75837_b + 1, p_176164_3_.field_75838_c) == 1) {
         lvt_7_1_ = 1;
      }

      PathPoint lvt_8_1_ = this.func_176171_a(p_176164_2_, p_176164_3_.field_75839_a, p_176164_3_.field_75837_b, p_176164_3_.field_75838_c + 1, lvt_7_1_);
      PathPoint lvt_9_1_ = this.func_176171_a(p_176164_2_, p_176164_3_.field_75839_a - 1, p_176164_3_.field_75837_b, p_176164_3_.field_75838_c, lvt_7_1_);
      PathPoint lvt_10_1_ = this.func_176171_a(p_176164_2_, p_176164_3_.field_75839_a + 1, p_176164_3_.field_75837_b, p_176164_3_.field_75838_c, lvt_7_1_);
      PathPoint lvt_11_1_ = this.func_176171_a(p_176164_2_, p_176164_3_.field_75839_a, p_176164_3_.field_75837_b, p_176164_3_.field_75838_c - 1, lvt_7_1_);
      if (lvt_8_1_ != null && !lvt_8_1_.field_75842_i && lvt_8_1_.func_75829_a(p_176164_4_) < p_176164_5_) {
         p_176164_1_[lvt_6_1_++] = lvt_8_1_;
      }

      if (lvt_9_1_ != null && !lvt_9_1_.field_75842_i && lvt_9_1_.func_75829_a(p_176164_4_) < p_176164_5_) {
         p_176164_1_[lvt_6_1_++] = lvt_9_1_;
      }

      if (lvt_10_1_ != null && !lvt_10_1_.field_75842_i && lvt_10_1_.func_75829_a(p_176164_4_) < p_176164_5_) {
         p_176164_1_[lvt_6_1_++] = lvt_10_1_;
      }

      if (lvt_11_1_ != null && !lvt_11_1_.field_75842_i && lvt_11_1_.func_75829_a(p_176164_4_) < p_176164_5_) {
         p_176164_1_[lvt_6_1_++] = lvt_11_1_;
      }

      return lvt_6_1_;
   }

   private PathPoint func_176171_a(Entity p_176171_1_, int p_176171_2_, int p_176171_3_, int p_176171_4_, int p_176171_5_) {
      PathPoint lvt_6_1_ = null;
      int lvt_7_1_ = this.func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_, p_176171_4_);
      if (lvt_7_1_ == 2) {
         return this.func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_);
      } else {
         if (lvt_7_1_ == 1) {
            lvt_6_1_ = this.func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_);
         }

         if (lvt_6_1_ == null && p_176171_5_ > 0 && lvt_7_1_ != -3 && lvt_7_1_ != -4 && this.func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_ + p_176171_5_, p_176171_4_) == 1) {
            lvt_6_1_ = this.func_176159_a(p_176171_2_, p_176171_3_ + p_176171_5_, p_176171_4_);
            p_176171_3_ += p_176171_5_;
         }

         if (lvt_6_1_ != null) {
            int lvt_8_1_ = 0;

            int lvt_9_1_;
            for(lvt_9_1_ = 0; p_176171_3_ > 0; lvt_6_1_ = this.func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_)) {
               lvt_9_1_ = this.func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_ - 1, p_176171_4_);
               if (this.field_176183_h && lvt_9_1_ == -1) {
                  return null;
               }

               if (lvt_9_1_ != 1) {
                  break;
               }

               if (lvt_8_1_++ >= p_176171_1_.func_82143_as()) {
                  return null;
               }

               --p_176171_3_;
               if (p_176171_3_ <= 0) {
                  return null;
               }
            }

            if (lvt_9_1_ == -2) {
               return null;
            }
         }

         return lvt_6_1_;
      }
   }

   private int func_176177_a(Entity p_176177_1_, int p_176177_2_, int p_176177_3_, int p_176177_4_) {
      return func_176170_a(this.field_176169_a, p_176177_1_, p_176177_2_, p_176177_3_, p_176177_4_, this.field_176168_c, this.field_176165_d, this.field_176166_e, this.field_176183_h, this.field_176181_g, this.field_176180_f);
   }

   public static int func_176170_a(IBlockAccess p_176170_0_, Entity p_176170_1_, int p_176170_2_, int p_176170_3_, int p_176170_4_, int p_176170_5_, int p_176170_6_, int p_176170_7_, boolean p_176170_8_, boolean p_176170_9_, boolean p_176170_10_) {
      boolean lvt_11_1_ = false;
      BlockPos lvt_12_1_ = new BlockPos(p_176170_1_);
      BlockPos.MutableBlockPos lvt_13_1_ = new BlockPos.MutableBlockPos();

      for(int lvt_14_1_ = p_176170_2_; lvt_14_1_ < p_176170_2_ + p_176170_5_; ++lvt_14_1_) {
         for(int lvt_15_1_ = p_176170_3_; lvt_15_1_ < p_176170_3_ + p_176170_6_; ++lvt_15_1_) {
            for(int lvt_16_1_ = p_176170_4_; lvt_16_1_ < p_176170_4_ + p_176170_7_; ++lvt_16_1_) {
               lvt_13_1_.func_181079_c(lvt_14_1_, lvt_15_1_, lvt_16_1_);
               Block lvt_17_1_ = p_176170_0_.func_180495_p(lvt_13_1_).func_177230_c();
               if (lvt_17_1_.func_149688_o() != Material.field_151579_a) {
                  if (lvt_17_1_ != Blocks.field_150415_aT && lvt_17_1_ != Blocks.field_180400_cw) {
                     if (lvt_17_1_ != Blocks.field_150358_i && lvt_17_1_ != Blocks.field_150355_j) {
                        if (!p_176170_10_ && lvt_17_1_ instanceof BlockDoor && lvt_17_1_.func_149688_o() == Material.field_151575_d) {
                           return 0;
                        }
                     } else {
                        if (p_176170_8_) {
                           return -1;
                        }

                        lvt_11_1_ = true;
                     }
                  } else {
                     lvt_11_1_ = true;
                  }

                  if (p_176170_1_.field_70170_p.func_180495_p(lvt_13_1_).func_177230_c() instanceof BlockRailBase) {
                     if (!(p_176170_1_.field_70170_p.func_180495_p(lvt_12_1_).func_177230_c() instanceof BlockRailBase) && !(p_176170_1_.field_70170_p.func_180495_p(lvt_12_1_.func_177977_b()).func_177230_c() instanceof BlockRailBase)) {
                        return -3;
                     }
                  } else if (!lvt_17_1_.func_176205_b(p_176170_0_, lvt_13_1_) && (!p_176170_9_ || !(lvt_17_1_ instanceof BlockDoor) || lvt_17_1_.func_149688_o() != Material.field_151575_d)) {
                     if (lvt_17_1_ instanceof BlockFence || lvt_17_1_ instanceof BlockFenceGate || lvt_17_1_ instanceof BlockWall) {
                        return -3;
                     }

                     if (lvt_17_1_ == Blocks.field_150415_aT || lvt_17_1_ == Blocks.field_180400_cw) {
                        return -4;
                     }

                     Material lvt_18_1_ = lvt_17_1_.func_149688_o();
                     if (lvt_18_1_ != Material.field_151587_i) {
                        return 0;
                     }

                     if (!p_176170_1_.func_180799_ab()) {
                        return -2;
                     }
                  }
               }
            }
         }
      }

      return lvt_11_1_ ? 2 : 1;
   }

   public void func_176175_a(boolean p_176175_1_) {
      this.field_176180_f = p_176175_1_;
   }

   public void func_176172_b(boolean p_176172_1_) {
      this.field_176181_g = p_176172_1_;
   }

   public void func_176176_c(boolean p_176176_1_) {
      this.field_176183_h = p_176176_1_;
   }

   public void func_176178_d(boolean p_176178_1_) {
      this.field_176184_i = p_176178_1_;
   }

   public boolean func_176179_b() {
      return this.field_176180_f;
   }

   public boolean func_176174_d() {
      return this.field_176184_i;
   }

   public boolean func_176173_e() {
      return this.field_176183_h;
   }
}
