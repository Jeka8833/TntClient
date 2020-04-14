package net.minecraft.block;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStairs extends Block {
   public static final PropertyDirection field_176309_a;
   public static final PropertyEnum<BlockStairs.EnumHalf> field_176308_b;
   public static final PropertyEnum<BlockStairs.EnumShape> field_176310_M;
   private static final int[][] field_150150_a;
   private final Block field_150149_b;
   private final IBlockState field_150151_M;
   private boolean field_150152_N;
   private int field_150153_O;

   protected BlockStairs(IBlockState p_i45684_1_) {
      super(p_i45684_1_.func_177230_c().field_149764_J);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176309_a, EnumFacing.NORTH).func_177226_a(field_176308_b, BlockStairs.EnumHalf.BOTTOM).func_177226_a(field_176310_M, BlockStairs.EnumShape.STRAIGHT));
      this.field_150149_b = p_i45684_1_.func_177230_c();
      this.field_150151_M = p_i45684_1_;
      this.func_149711_c(this.field_150149_b.field_149782_v);
      this.func_149752_b(this.field_150149_b.field_149781_w / 3.0F);
      this.func_149672_a(this.field_150149_b.field_149762_H);
      this.func_149713_g(255);
      this.func_149647_a(CreativeTabs.field_78030_b);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      if (this.field_150152_N) {
         this.func_149676_a(0.5F * (float)(this.field_150153_O % 2), 0.5F * (float)(this.field_150153_O / 4 % 2), 0.5F * (float)(this.field_150153_O / 2 % 2), 0.5F + 0.5F * (float)(this.field_150153_O % 2), 0.5F + 0.5F * (float)(this.field_150153_O / 4 % 2), 0.5F + 0.5F * (float)(this.field_150153_O / 2 % 2));
      } else {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public void func_176303_e(IBlockAccess p_176303_1_, BlockPos p_176303_2_) {
      if (p_176303_1_.func_180495_p(p_176303_2_).func_177229_b(field_176308_b) == BlockStairs.EnumHalf.TOP) {
         this.func_149676_a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }

   }

   public static boolean func_150148_a(Block p_150148_0_) {
      return p_150148_0_ instanceof BlockStairs;
   }

   public static boolean func_176302_a(IBlockAccess p_176302_0_, BlockPos p_176302_1_, IBlockState p_176302_2_) {
      IBlockState lvt_3_1_ = p_176302_0_.func_180495_p(p_176302_1_);
      Block lvt_4_1_ = lvt_3_1_.func_177230_c();
      return func_150148_a(lvt_4_1_) && lvt_3_1_.func_177229_b(field_176308_b) == p_176302_2_.func_177229_b(field_176308_b) && lvt_3_1_.func_177229_b(field_176309_a) == p_176302_2_.func_177229_b(field_176309_a);
   }

   public int func_176307_f(IBlockAccess p_176307_1_, BlockPos p_176307_2_) {
      IBlockState lvt_3_1_ = p_176307_1_.func_180495_p(p_176307_2_);
      EnumFacing lvt_4_1_ = (EnumFacing)lvt_3_1_.func_177229_b(field_176309_a);
      BlockStairs.EnumHalf lvt_5_1_ = (BlockStairs.EnumHalf)lvt_3_1_.func_177229_b(field_176308_b);
      boolean lvt_6_1_ = lvt_5_1_ == BlockStairs.EnumHalf.TOP;
      IBlockState lvt_7_4_;
      Block lvt_8_4_;
      EnumFacing lvt_9_4_;
      if (lvt_4_1_ == EnumFacing.EAST) {
         lvt_7_4_ = p_176307_1_.func_180495_p(p_176307_2_.func_177974_f());
         lvt_8_4_ = lvt_7_4_.func_177230_c();
         if (func_150148_a(lvt_8_4_) && lvt_5_1_ == lvt_7_4_.func_177229_b(field_176308_b)) {
            lvt_9_4_ = (EnumFacing)lvt_7_4_.func_177229_b(field_176309_a);
            if (lvt_9_4_ == EnumFacing.NORTH && !func_176302_a(p_176307_1_, p_176307_2_.func_177968_d(), lvt_3_1_)) {
               return lvt_6_1_ ? 1 : 2;
            }

            if (lvt_9_4_ == EnumFacing.SOUTH && !func_176302_a(p_176307_1_, p_176307_2_.func_177978_c(), lvt_3_1_)) {
               return lvt_6_1_ ? 2 : 1;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.WEST) {
         lvt_7_4_ = p_176307_1_.func_180495_p(p_176307_2_.func_177976_e());
         lvt_8_4_ = lvt_7_4_.func_177230_c();
         if (func_150148_a(lvt_8_4_) && lvt_5_1_ == lvt_7_4_.func_177229_b(field_176308_b)) {
            lvt_9_4_ = (EnumFacing)lvt_7_4_.func_177229_b(field_176309_a);
            if (lvt_9_4_ == EnumFacing.NORTH && !func_176302_a(p_176307_1_, p_176307_2_.func_177968_d(), lvt_3_1_)) {
               return lvt_6_1_ ? 2 : 1;
            }

            if (lvt_9_4_ == EnumFacing.SOUTH && !func_176302_a(p_176307_1_, p_176307_2_.func_177978_c(), lvt_3_1_)) {
               return lvt_6_1_ ? 1 : 2;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.SOUTH) {
         lvt_7_4_ = p_176307_1_.func_180495_p(p_176307_2_.func_177968_d());
         lvt_8_4_ = lvt_7_4_.func_177230_c();
         if (func_150148_a(lvt_8_4_) && lvt_5_1_ == lvt_7_4_.func_177229_b(field_176308_b)) {
            lvt_9_4_ = (EnumFacing)lvt_7_4_.func_177229_b(field_176309_a);
            if (lvt_9_4_ == EnumFacing.WEST && !func_176302_a(p_176307_1_, p_176307_2_.func_177974_f(), lvt_3_1_)) {
               return lvt_6_1_ ? 2 : 1;
            }

            if (lvt_9_4_ == EnumFacing.EAST && !func_176302_a(p_176307_1_, p_176307_2_.func_177976_e(), lvt_3_1_)) {
               return lvt_6_1_ ? 1 : 2;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.NORTH) {
         lvt_7_4_ = p_176307_1_.func_180495_p(p_176307_2_.func_177978_c());
         lvt_8_4_ = lvt_7_4_.func_177230_c();
         if (func_150148_a(lvt_8_4_) && lvt_5_1_ == lvt_7_4_.func_177229_b(field_176308_b)) {
            lvt_9_4_ = (EnumFacing)lvt_7_4_.func_177229_b(field_176309_a);
            if (lvt_9_4_ == EnumFacing.WEST && !func_176302_a(p_176307_1_, p_176307_2_.func_177974_f(), lvt_3_1_)) {
               return lvt_6_1_ ? 1 : 2;
            }

            if (lvt_9_4_ == EnumFacing.EAST && !func_176302_a(p_176307_1_, p_176307_2_.func_177976_e(), lvt_3_1_)) {
               return lvt_6_1_ ? 2 : 1;
            }
         }
      }

      return 0;
   }

   public int func_176305_g(IBlockAccess p_176305_1_, BlockPos p_176305_2_) {
      IBlockState lvt_3_1_ = p_176305_1_.func_180495_p(p_176305_2_);
      EnumFacing lvt_4_1_ = (EnumFacing)lvt_3_1_.func_177229_b(field_176309_a);
      BlockStairs.EnumHalf lvt_5_1_ = (BlockStairs.EnumHalf)lvt_3_1_.func_177229_b(field_176308_b);
      boolean lvt_6_1_ = lvt_5_1_ == BlockStairs.EnumHalf.TOP;
      IBlockState lvt_7_4_;
      Block lvt_8_4_;
      EnumFacing lvt_9_4_;
      if (lvt_4_1_ == EnumFacing.EAST) {
         lvt_7_4_ = p_176305_1_.func_180495_p(p_176305_2_.func_177976_e());
         lvt_8_4_ = lvt_7_4_.func_177230_c();
         if (func_150148_a(lvt_8_4_) && lvt_5_1_ == lvt_7_4_.func_177229_b(field_176308_b)) {
            lvt_9_4_ = (EnumFacing)lvt_7_4_.func_177229_b(field_176309_a);
            if (lvt_9_4_ == EnumFacing.NORTH && !func_176302_a(p_176305_1_, p_176305_2_.func_177978_c(), lvt_3_1_)) {
               return lvt_6_1_ ? 1 : 2;
            }

            if (lvt_9_4_ == EnumFacing.SOUTH && !func_176302_a(p_176305_1_, p_176305_2_.func_177968_d(), lvt_3_1_)) {
               return lvt_6_1_ ? 2 : 1;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.WEST) {
         lvt_7_4_ = p_176305_1_.func_180495_p(p_176305_2_.func_177974_f());
         lvt_8_4_ = lvt_7_4_.func_177230_c();
         if (func_150148_a(lvt_8_4_) && lvt_5_1_ == lvt_7_4_.func_177229_b(field_176308_b)) {
            lvt_9_4_ = (EnumFacing)lvt_7_4_.func_177229_b(field_176309_a);
            if (lvt_9_4_ == EnumFacing.NORTH && !func_176302_a(p_176305_1_, p_176305_2_.func_177978_c(), lvt_3_1_)) {
               return lvt_6_1_ ? 2 : 1;
            }

            if (lvt_9_4_ == EnumFacing.SOUTH && !func_176302_a(p_176305_1_, p_176305_2_.func_177968_d(), lvt_3_1_)) {
               return lvt_6_1_ ? 1 : 2;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.SOUTH) {
         lvt_7_4_ = p_176305_1_.func_180495_p(p_176305_2_.func_177978_c());
         lvt_8_4_ = lvt_7_4_.func_177230_c();
         if (func_150148_a(lvt_8_4_) && lvt_5_1_ == lvt_7_4_.func_177229_b(field_176308_b)) {
            lvt_9_4_ = (EnumFacing)lvt_7_4_.func_177229_b(field_176309_a);
            if (lvt_9_4_ == EnumFacing.WEST && !func_176302_a(p_176305_1_, p_176305_2_.func_177976_e(), lvt_3_1_)) {
               return lvt_6_1_ ? 2 : 1;
            }

            if (lvt_9_4_ == EnumFacing.EAST && !func_176302_a(p_176305_1_, p_176305_2_.func_177974_f(), lvt_3_1_)) {
               return lvt_6_1_ ? 1 : 2;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.NORTH) {
         lvt_7_4_ = p_176305_1_.func_180495_p(p_176305_2_.func_177968_d());
         lvt_8_4_ = lvt_7_4_.func_177230_c();
         if (func_150148_a(lvt_8_4_) && lvt_5_1_ == lvt_7_4_.func_177229_b(field_176308_b)) {
            lvt_9_4_ = (EnumFacing)lvt_7_4_.func_177229_b(field_176309_a);
            if (lvt_9_4_ == EnumFacing.WEST && !func_176302_a(p_176305_1_, p_176305_2_.func_177976_e(), lvt_3_1_)) {
               return lvt_6_1_ ? 1 : 2;
            }

            if (lvt_9_4_ == EnumFacing.EAST && !func_176302_a(p_176305_1_, p_176305_2_.func_177974_f(), lvt_3_1_)) {
               return lvt_6_1_ ? 2 : 1;
            }
         }
      }

      return 0;
   }

   public boolean func_176306_h(IBlockAccess p_176306_1_, BlockPos p_176306_2_) {
      IBlockState lvt_3_1_ = p_176306_1_.func_180495_p(p_176306_2_);
      EnumFacing lvt_4_1_ = (EnumFacing)lvt_3_1_.func_177229_b(field_176309_a);
      BlockStairs.EnumHalf lvt_5_1_ = (BlockStairs.EnumHalf)lvt_3_1_.func_177229_b(field_176308_b);
      boolean lvt_6_1_ = lvt_5_1_ == BlockStairs.EnumHalf.TOP;
      float lvt_7_1_ = 0.5F;
      float lvt_8_1_ = 1.0F;
      if (lvt_6_1_) {
         lvt_7_1_ = 0.0F;
         lvt_8_1_ = 0.5F;
      }

      float lvt_9_1_ = 0.0F;
      float lvt_10_1_ = 1.0F;
      float lvt_11_1_ = 0.0F;
      float lvt_12_1_ = 0.5F;
      boolean lvt_13_1_ = true;
      IBlockState lvt_14_4_;
      Block lvt_15_4_;
      EnumFacing lvt_16_4_;
      if (lvt_4_1_ == EnumFacing.EAST) {
         lvt_9_1_ = 0.5F;
         lvt_12_1_ = 1.0F;
         lvt_14_4_ = p_176306_1_.func_180495_p(p_176306_2_.func_177974_f());
         lvt_15_4_ = lvt_14_4_.func_177230_c();
         if (func_150148_a(lvt_15_4_) && lvt_5_1_ == lvt_14_4_.func_177229_b(field_176308_b)) {
            lvt_16_4_ = (EnumFacing)lvt_14_4_.func_177229_b(field_176309_a);
            if (lvt_16_4_ == EnumFacing.NORTH && !func_176302_a(p_176306_1_, p_176306_2_.func_177968_d(), lvt_3_1_)) {
               lvt_12_1_ = 0.5F;
               lvt_13_1_ = false;
            } else if (lvt_16_4_ == EnumFacing.SOUTH && !func_176302_a(p_176306_1_, p_176306_2_.func_177978_c(), lvt_3_1_)) {
               lvt_11_1_ = 0.5F;
               lvt_13_1_ = false;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.WEST) {
         lvt_10_1_ = 0.5F;
         lvt_12_1_ = 1.0F;
         lvt_14_4_ = p_176306_1_.func_180495_p(p_176306_2_.func_177976_e());
         lvt_15_4_ = lvt_14_4_.func_177230_c();
         if (func_150148_a(lvt_15_4_) && lvt_5_1_ == lvt_14_4_.func_177229_b(field_176308_b)) {
            lvt_16_4_ = (EnumFacing)lvt_14_4_.func_177229_b(field_176309_a);
            if (lvt_16_4_ == EnumFacing.NORTH && !func_176302_a(p_176306_1_, p_176306_2_.func_177968_d(), lvt_3_1_)) {
               lvt_12_1_ = 0.5F;
               lvt_13_1_ = false;
            } else if (lvt_16_4_ == EnumFacing.SOUTH && !func_176302_a(p_176306_1_, p_176306_2_.func_177978_c(), lvt_3_1_)) {
               lvt_11_1_ = 0.5F;
               lvt_13_1_ = false;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.SOUTH) {
         lvt_11_1_ = 0.5F;
         lvt_12_1_ = 1.0F;
         lvt_14_4_ = p_176306_1_.func_180495_p(p_176306_2_.func_177968_d());
         lvt_15_4_ = lvt_14_4_.func_177230_c();
         if (func_150148_a(lvt_15_4_) && lvt_5_1_ == lvt_14_4_.func_177229_b(field_176308_b)) {
            lvt_16_4_ = (EnumFacing)lvt_14_4_.func_177229_b(field_176309_a);
            if (lvt_16_4_ == EnumFacing.WEST && !func_176302_a(p_176306_1_, p_176306_2_.func_177974_f(), lvt_3_1_)) {
               lvt_10_1_ = 0.5F;
               lvt_13_1_ = false;
            } else if (lvt_16_4_ == EnumFacing.EAST && !func_176302_a(p_176306_1_, p_176306_2_.func_177976_e(), lvt_3_1_)) {
               lvt_9_1_ = 0.5F;
               lvt_13_1_ = false;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.NORTH) {
         lvt_14_4_ = p_176306_1_.func_180495_p(p_176306_2_.func_177978_c());
         lvt_15_4_ = lvt_14_4_.func_177230_c();
         if (func_150148_a(lvt_15_4_) && lvt_5_1_ == lvt_14_4_.func_177229_b(field_176308_b)) {
            lvt_16_4_ = (EnumFacing)lvt_14_4_.func_177229_b(field_176309_a);
            if (lvt_16_4_ == EnumFacing.WEST && !func_176302_a(p_176306_1_, p_176306_2_.func_177974_f(), lvt_3_1_)) {
               lvt_10_1_ = 0.5F;
               lvt_13_1_ = false;
            } else if (lvt_16_4_ == EnumFacing.EAST && !func_176302_a(p_176306_1_, p_176306_2_.func_177976_e(), lvt_3_1_)) {
               lvt_9_1_ = 0.5F;
               lvt_13_1_ = false;
            }
         }
      }

      this.func_149676_a(lvt_9_1_, lvt_7_1_, lvt_11_1_, lvt_10_1_, lvt_8_1_, lvt_12_1_);
      return lvt_13_1_;
   }

   public boolean func_176304_i(IBlockAccess p_176304_1_, BlockPos p_176304_2_) {
      IBlockState lvt_3_1_ = p_176304_1_.func_180495_p(p_176304_2_);
      EnumFacing lvt_4_1_ = (EnumFacing)lvt_3_1_.func_177229_b(field_176309_a);
      BlockStairs.EnumHalf lvt_5_1_ = (BlockStairs.EnumHalf)lvt_3_1_.func_177229_b(field_176308_b);
      boolean lvt_6_1_ = lvt_5_1_ == BlockStairs.EnumHalf.TOP;
      float lvt_7_1_ = 0.5F;
      float lvt_8_1_ = 1.0F;
      if (lvt_6_1_) {
         lvt_7_1_ = 0.0F;
         lvt_8_1_ = 0.5F;
      }

      float lvt_9_1_ = 0.0F;
      float lvt_10_1_ = 0.5F;
      float lvt_11_1_ = 0.5F;
      float lvt_12_1_ = 1.0F;
      boolean lvt_13_1_ = false;
      IBlockState lvt_14_4_;
      Block lvt_15_4_;
      EnumFacing lvt_16_4_;
      if (lvt_4_1_ == EnumFacing.EAST) {
         lvt_14_4_ = p_176304_1_.func_180495_p(p_176304_2_.func_177976_e());
         lvt_15_4_ = lvt_14_4_.func_177230_c();
         if (func_150148_a(lvt_15_4_) && lvt_5_1_ == lvt_14_4_.func_177229_b(field_176308_b)) {
            lvt_16_4_ = (EnumFacing)lvt_14_4_.func_177229_b(field_176309_a);
            if (lvt_16_4_ == EnumFacing.NORTH && !func_176302_a(p_176304_1_, p_176304_2_.func_177978_c(), lvt_3_1_)) {
               lvt_11_1_ = 0.0F;
               lvt_12_1_ = 0.5F;
               lvt_13_1_ = true;
            } else if (lvt_16_4_ == EnumFacing.SOUTH && !func_176302_a(p_176304_1_, p_176304_2_.func_177968_d(), lvt_3_1_)) {
               lvt_11_1_ = 0.5F;
               lvt_12_1_ = 1.0F;
               lvt_13_1_ = true;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.WEST) {
         lvt_14_4_ = p_176304_1_.func_180495_p(p_176304_2_.func_177974_f());
         lvt_15_4_ = lvt_14_4_.func_177230_c();
         if (func_150148_a(lvt_15_4_) && lvt_5_1_ == lvt_14_4_.func_177229_b(field_176308_b)) {
            lvt_9_1_ = 0.5F;
            lvt_10_1_ = 1.0F;
            lvt_16_4_ = (EnumFacing)lvt_14_4_.func_177229_b(field_176309_a);
            if (lvt_16_4_ == EnumFacing.NORTH && !func_176302_a(p_176304_1_, p_176304_2_.func_177978_c(), lvt_3_1_)) {
               lvt_11_1_ = 0.0F;
               lvt_12_1_ = 0.5F;
               lvt_13_1_ = true;
            } else if (lvt_16_4_ == EnumFacing.SOUTH && !func_176302_a(p_176304_1_, p_176304_2_.func_177968_d(), lvt_3_1_)) {
               lvt_11_1_ = 0.5F;
               lvt_12_1_ = 1.0F;
               lvt_13_1_ = true;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.SOUTH) {
         lvt_14_4_ = p_176304_1_.func_180495_p(p_176304_2_.func_177978_c());
         lvt_15_4_ = lvt_14_4_.func_177230_c();
         if (func_150148_a(lvt_15_4_) && lvt_5_1_ == lvt_14_4_.func_177229_b(field_176308_b)) {
            lvt_11_1_ = 0.0F;
            lvt_12_1_ = 0.5F;
            lvt_16_4_ = (EnumFacing)lvt_14_4_.func_177229_b(field_176309_a);
            if (lvt_16_4_ == EnumFacing.WEST && !func_176302_a(p_176304_1_, p_176304_2_.func_177976_e(), lvt_3_1_)) {
               lvt_13_1_ = true;
            } else if (lvt_16_4_ == EnumFacing.EAST && !func_176302_a(p_176304_1_, p_176304_2_.func_177974_f(), lvt_3_1_)) {
               lvt_9_1_ = 0.5F;
               lvt_10_1_ = 1.0F;
               lvt_13_1_ = true;
            }
         }
      } else if (lvt_4_1_ == EnumFacing.NORTH) {
         lvt_14_4_ = p_176304_1_.func_180495_p(p_176304_2_.func_177968_d());
         lvt_15_4_ = lvt_14_4_.func_177230_c();
         if (func_150148_a(lvt_15_4_) && lvt_5_1_ == lvt_14_4_.func_177229_b(field_176308_b)) {
            lvt_16_4_ = (EnumFacing)lvt_14_4_.func_177229_b(field_176309_a);
            if (lvt_16_4_ == EnumFacing.WEST && !func_176302_a(p_176304_1_, p_176304_2_.func_177976_e(), lvt_3_1_)) {
               lvt_13_1_ = true;
            } else if (lvt_16_4_ == EnumFacing.EAST && !func_176302_a(p_176304_1_, p_176304_2_.func_177974_f(), lvt_3_1_)) {
               lvt_9_1_ = 0.5F;
               lvt_10_1_ = 1.0F;
               lvt_13_1_ = true;
            }
         }
      }

      if (lvt_13_1_) {
         this.func_149676_a(lvt_9_1_, lvt_7_1_, lvt_11_1_, lvt_10_1_, lvt_8_1_, lvt_12_1_);
      }

      return lvt_13_1_;
   }

   public void func_180638_a(World p_180638_1_, BlockPos p_180638_2_, IBlockState p_180638_3_, AxisAlignedBB p_180638_4_, List<AxisAlignedBB> p_180638_5_, Entity p_180638_6_) {
      this.func_176303_e(p_180638_1_, p_180638_2_);
      super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
      boolean lvt_7_1_ = this.func_176306_h(p_180638_1_, p_180638_2_);
      super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
      if (lvt_7_1_ && this.func_176304_i(p_180638_1_, p_180638_2_)) {
         super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
      }

      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      this.field_150149_b.func_180655_c(p_180655_1_, p_180655_2_, p_180655_3_, p_180655_4_);
   }

   public void func_180649_a(World p_180649_1_, BlockPos p_180649_2_, EntityPlayer p_180649_3_) {
      this.field_150149_b.func_180649_a(p_180649_1_, p_180649_2_, p_180649_3_);
   }

   public void func_176206_d(World p_176206_1_, BlockPos p_176206_2_, IBlockState p_176206_3_) {
      this.field_150149_b.func_176206_d(p_176206_1_, p_176206_2_, p_176206_3_);
   }

   public int func_176207_c(IBlockAccess p_176207_1_, BlockPos p_176207_2_) {
      return this.field_150149_b.func_176207_c(p_176207_1_, p_176207_2_);
   }

   public float func_149638_a(Entity p_149638_1_) {
      return this.field_150149_b.func_149638_a(p_149638_1_);
   }

   public EnumWorldBlockLayer func_180664_k() {
      return this.field_150149_b.func_180664_k();
   }

   public int func_149738_a(World p_149738_1_) {
      return this.field_150149_b.func_149738_a(p_149738_1_);
   }

   public AxisAlignedBB func_180646_a(World p_180646_1_, BlockPos p_180646_2_) {
      return this.field_150149_b.func_180646_a(p_180646_1_, p_180646_2_);
   }

   public Vec3 func_176197_a(World p_176197_1_, BlockPos p_176197_2_, Entity p_176197_3_, Vec3 p_176197_4_) {
      return this.field_150149_b.func_176197_a(p_176197_1_, p_176197_2_, p_176197_3_, p_176197_4_);
   }

   public boolean func_149703_v() {
      return this.field_150149_b.func_149703_v();
   }

   public boolean func_176209_a(IBlockState p_176209_1_, boolean p_176209_2_) {
      return this.field_150149_b.func_176209_a(p_176209_1_, p_176209_2_);
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return this.field_150149_b.func_176196_c(p_176196_1_, p_176196_2_);
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176204_a(p_176213_1_, p_176213_2_, this.field_150151_M, Blocks.field_150350_a);
      this.field_150149_b.func_176213_c(p_176213_1_, p_176213_2_, this.field_150151_M);
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      this.field_150149_b.func_180663_b(p_180663_1_, p_180663_2_, this.field_150151_M);
   }

   public void func_176199_a(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
      this.field_150149_b.func_176199_a(p_176199_1_, p_176199_2_, p_176199_3_);
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      this.field_150149_b.func_180650_b(p_180650_1_, p_180650_2_, p_180650_3_, p_180650_4_);
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      return this.field_150149_b.func_180639_a(p_180639_1_, p_180639_2_, this.field_150151_M, p_180639_4_, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
   }

   public void func_180652_a(World p_180652_1_, BlockPos p_180652_2_, Explosion p_180652_3_) {
      this.field_150149_b.func_180652_a(p_180652_1_, p_180652_2_, p_180652_3_);
   }

   public MapColor func_180659_g(IBlockState p_180659_1_) {
      return this.field_150149_b.func_180659_g(this.field_150151_M);
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      IBlockState lvt_9_1_ = super.func_180642_a(p_180642_1_, p_180642_2_, p_180642_3_, p_180642_4_, p_180642_5_, p_180642_6_, p_180642_7_, p_180642_8_);
      lvt_9_1_ = lvt_9_1_.func_177226_a(field_176309_a, p_180642_8_.func_174811_aO()).func_177226_a(field_176310_M, BlockStairs.EnumShape.STRAIGHT);
      return p_180642_3_ != EnumFacing.DOWN && (p_180642_3_ == EnumFacing.UP || (double)p_180642_5_ <= 0.5D) ? lvt_9_1_.func_177226_a(field_176308_b, BlockStairs.EnumHalf.BOTTOM) : lvt_9_1_.func_177226_a(field_176308_b, BlockStairs.EnumHalf.TOP);
   }

   public MovingObjectPosition func_180636_a(World p_180636_1_, BlockPos p_180636_2_, Vec3 p_180636_3_, Vec3 p_180636_4_) {
      MovingObjectPosition[] lvt_5_1_ = new MovingObjectPosition[8];
      IBlockState lvt_6_1_ = p_180636_1_.func_180495_p(p_180636_2_);
      int lvt_7_1_ = ((EnumFacing)lvt_6_1_.func_177229_b(field_176309_a)).func_176736_b();
      boolean lvt_8_1_ = lvt_6_1_.func_177229_b(field_176308_b) == BlockStairs.EnumHalf.TOP;
      int[] lvt_9_1_ = field_150150_a[lvt_7_1_ + (lvt_8_1_ ? 4 : 0)];
      this.field_150152_N = true;

      for(int lvt_10_1_ = 0; lvt_10_1_ < 8; ++lvt_10_1_) {
         this.field_150153_O = lvt_10_1_;
         if (Arrays.binarySearch(lvt_9_1_, lvt_10_1_) < 0) {
            lvt_5_1_[lvt_10_1_] = super.func_180636_a(p_180636_1_, p_180636_2_, p_180636_3_, p_180636_4_);
         }
      }

      int[] lvt_10_2_ = lvt_9_1_;
      int lvt_11_1_ = lvt_9_1_.length;

      for(int lvt_12_1_ = 0; lvt_12_1_ < lvt_11_1_; ++lvt_12_1_) {
         int lvt_13_1_ = lvt_10_2_[lvt_12_1_];
         lvt_5_1_[lvt_13_1_] = null;
      }

      MovingObjectPosition lvt_10_3_ = null;
      double lvt_11_2_ = 0.0D;
      MovingObjectPosition[] lvt_13_2_ = lvt_5_1_;
      int lvt_14_1_ = lvt_5_1_.length;

      for(int lvt_15_1_ = 0; lvt_15_1_ < lvt_14_1_; ++lvt_15_1_) {
         MovingObjectPosition lvt_16_1_ = lvt_13_2_[lvt_15_1_];
         if (lvt_16_1_ != null) {
            double lvt_17_1_ = lvt_16_1_.field_72307_f.func_72436_e(p_180636_4_);
            if (lvt_17_1_ > lvt_11_2_) {
               lvt_10_3_ = lvt_16_1_;
               lvt_11_2_ = lvt_17_1_;
            }
         }
      }

      return lvt_10_3_;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      IBlockState lvt_2_1_ = this.func_176223_P().func_177226_a(field_176308_b, (p_176203_1_ & 4) > 0 ? BlockStairs.EnumHalf.TOP : BlockStairs.EnumHalf.BOTTOM);
      lvt_2_1_ = lvt_2_1_.func_177226_a(field_176309_a, EnumFacing.func_82600_a(5 - (p_176203_1_ & 3)));
      return lvt_2_1_;
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      if (p_176201_1_.func_177229_b(field_176308_b) == BlockStairs.EnumHalf.TOP) {
         lvt_2_1_ |= 4;
      }

      lvt_2_1_ |= 5 - ((EnumFacing)p_176201_1_.func_177229_b(field_176309_a)).func_176745_a();
      return lvt_2_1_;
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      if (this.func_176306_h(p_176221_2_, p_176221_3_)) {
         switch(this.func_176305_g(p_176221_2_, p_176221_3_)) {
         case 0:
            p_176221_1_ = p_176221_1_.func_177226_a(field_176310_M, BlockStairs.EnumShape.STRAIGHT);
            break;
         case 1:
            p_176221_1_ = p_176221_1_.func_177226_a(field_176310_M, BlockStairs.EnumShape.INNER_RIGHT);
            break;
         case 2:
            p_176221_1_ = p_176221_1_.func_177226_a(field_176310_M, BlockStairs.EnumShape.INNER_LEFT);
         }
      } else {
         switch(this.func_176307_f(p_176221_2_, p_176221_3_)) {
         case 0:
            p_176221_1_ = p_176221_1_.func_177226_a(field_176310_M, BlockStairs.EnumShape.STRAIGHT);
            break;
         case 1:
            p_176221_1_ = p_176221_1_.func_177226_a(field_176310_M, BlockStairs.EnumShape.OUTER_RIGHT);
            break;
         case 2:
            p_176221_1_ = p_176221_1_.func_177226_a(field_176310_M, BlockStairs.EnumShape.OUTER_LEFT);
         }
      }

      return p_176221_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176309_a, field_176308_b, field_176310_M});
   }

   static {
      field_176309_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
      field_176308_b = PropertyEnum.func_177709_a("half", BlockStairs.EnumHalf.class);
      field_176310_M = PropertyEnum.func_177709_a("shape", BlockStairs.EnumShape.class);
      field_150150_a = new int[][]{{4, 5}, {5, 7}, {6, 7}, {4, 6}, {0, 1}, {1, 3}, {2, 3}, {0, 2}};
   }

   public static enum EnumShape implements IStringSerializable {
      STRAIGHT("straight"),
      INNER_LEFT("inner_left"),
      INNER_RIGHT("inner_right"),
      OUTER_LEFT("outer_left"),
      OUTER_RIGHT("outer_right");

      private final String field_176699_f;

      private EnumShape(String p_i45682_3_) {
         this.field_176699_f = p_i45682_3_;
      }

      public String toString() {
         return this.field_176699_f;
      }

      public String func_176610_l() {
         return this.field_176699_f;
      }
   }

   public static enum EnumHalf implements IStringSerializable {
      TOP("top"),
      BOTTOM("bottom");

      private final String field_176709_c;

      private EnumHalf(String p_i45683_3_) {
         this.field_176709_c = p_i45683_3_;
      }

      public String toString() {
         return this.field_176709_c;
      }

      public String func_176610_l() {
         return this.field_176709_c;
      }
   }
}
