package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockPistonStructureHelper;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonBase extends Block {
   public static final PropertyDirection field_176321_a = PropertyDirection.func_177714_a("facing");
   public static final PropertyBool field_176320_b = PropertyBool.func_177716_a("extended");
   private final boolean field_150082_a;

   public BlockPistonBase(boolean p_i45443_1_) {
      super(Material.field_76233_E);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176321_a, EnumFacing.NORTH).func_177226_a(field_176320_b, false));
      this.field_150082_a = p_i45443_1_;
      this.func_149672_a(field_149780_i);
      this.func_149711_c(0.5F);
      this.func_149647_a(CreativeTabs.field_78028_d);
   }

   public boolean func_149662_c() {
      return false;
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_.func_177226_a(field_176321_a, func_180695_a(p_180633_1_, p_180633_2_, p_180633_4_)), 2);
      if (!p_180633_1_.field_72995_K) {
         this.func_176316_e(p_180633_1_, p_180633_2_, p_180633_3_);
      }

   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if (!p_176204_1_.field_72995_K) {
         this.func_176316_e(p_176204_1_, p_176204_2_, p_176204_3_);
      }

   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      if (!p_176213_1_.field_72995_K && p_176213_1_.func_175625_s(p_176213_2_) == null) {
         this.func_176316_e(p_176213_1_, p_176213_2_, p_176213_3_);
      }

   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176321_a, func_180695_a(p_180642_1_, p_180642_2_, p_180642_8_)).func_177226_a(field_176320_b, false);
   }

   private void func_176316_e(World p_176316_1_, BlockPos p_176316_2_, IBlockState p_176316_3_) {
      EnumFacing lvt_4_1_ = (EnumFacing)p_176316_3_.func_177229_b(field_176321_a);
      boolean lvt_5_1_ = this.func_176318_b(p_176316_1_, p_176316_2_, lvt_4_1_);
      if (lvt_5_1_ && !(Boolean)p_176316_3_.func_177229_b(field_176320_b)) {
         if ((new BlockPistonStructureHelper(p_176316_1_, p_176316_2_, lvt_4_1_, true)).func_177253_a()) {
            p_176316_1_.func_175641_c(p_176316_2_, this, 0, lvt_4_1_.func_176745_a());
         }
      } else if (!lvt_5_1_ && (Boolean)p_176316_3_.func_177229_b(field_176320_b)) {
         p_176316_1_.func_180501_a(p_176316_2_, p_176316_3_.func_177226_a(field_176320_b, false), 2);
         p_176316_1_.func_175641_c(p_176316_2_, this, 1, lvt_4_1_.func_176745_a());
      }

   }

   private boolean func_176318_b(World p_176318_1_, BlockPos p_176318_2_, EnumFacing p_176318_3_) {
      EnumFacing[] lvt_4_1_ = EnumFacing.values();
      int lvt_5_1_ = lvt_4_1_.length;

      int lvt_6_2_;
      for(lvt_6_2_ = 0; lvt_6_2_ < lvt_5_1_; ++lvt_6_2_) {
         EnumFacing lvt_7_1_ = lvt_4_1_[lvt_6_2_];
         if (lvt_7_1_ != p_176318_3_ && p_176318_1_.func_175709_b(p_176318_2_.func_177972_a(lvt_7_1_), lvt_7_1_)) {
            return true;
         }
      }

      if (p_176318_1_.func_175709_b(p_176318_2_, EnumFacing.DOWN)) {
         return true;
      } else {
         BlockPos lvt_4_2_ = p_176318_2_.func_177984_a();
         EnumFacing[] lvt_5_2_ = EnumFacing.values();
         lvt_6_2_ = lvt_5_2_.length;

         for(int lvt_7_2_ = 0; lvt_7_2_ < lvt_6_2_; ++lvt_7_2_) {
            EnumFacing lvt_8_1_ = lvt_5_2_[lvt_7_2_];
            if (lvt_8_1_ != EnumFacing.DOWN && p_176318_1_.func_175709_b(lvt_4_2_.func_177972_a(lvt_8_1_), lvt_8_1_)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean func_180648_a(World p_180648_1_, BlockPos p_180648_2_, IBlockState p_180648_3_, int p_180648_4_, int p_180648_5_) {
      EnumFacing lvt_6_1_ = (EnumFacing)p_180648_3_.func_177229_b(field_176321_a);
      if (!p_180648_1_.field_72995_K) {
         boolean lvt_7_1_ = this.func_176318_b(p_180648_1_, p_180648_2_, lvt_6_1_);
         if (lvt_7_1_ && p_180648_4_ == 1) {
            p_180648_1_.func_180501_a(p_180648_2_, p_180648_3_.func_177226_a(field_176320_b, true), 2);
            return false;
         }

         if (!lvt_7_1_ && p_180648_4_ == 0) {
            return false;
         }
      }

      if (p_180648_4_ == 0) {
         if (!this.func_176319_a(p_180648_1_, p_180648_2_, lvt_6_1_, true)) {
            return false;
         }

         p_180648_1_.func_180501_a(p_180648_2_, p_180648_3_.func_177226_a(field_176320_b, true), 2);
         p_180648_1_.func_72908_a((double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 0.5D, (double)p_180648_2_.func_177952_p() + 0.5D, "tile.piston.out", 0.5F, p_180648_1_.field_73012_v.nextFloat() * 0.25F + 0.6F);
      } else if (p_180648_4_ == 1) {
         TileEntity lvt_7_2_ = p_180648_1_.func_175625_s(p_180648_2_.func_177972_a(lvt_6_1_));
         if (lvt_7_2_ instanceof TileEntityPiston) {
            ((TileEntityPiston)lvt_7_2_).func_145866_f();
         }

         p_180648_1_.func_180501_a(p_180648_2_, Blocks.field_180384_M.func_176223_P().func_177226_a(BlockPistonMoving.field_176426_a, lvt_6_1_).func_177226_a(BlockPistonMoving.field_176425_b, this.field_150082_a ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT), 3);
         p_180648_1_.func_175690_a(p_180648_2_, BlockPistonMoving.func_176423_a(this.func_176203_a(p_180648_5_), lvt_6_1_, false, true));
         if (this.field_150082_a) {
            BlockPos lvt_8_1_ = p_180648_2_.func_177982_a(lvt_6_1_.func_82601_c() * 2, lvt_6_1_.func_96559_d() * 2, lvt_6_1_.func_82599_e() * 2);
            Block lvt_9_1_ = p_180648_1_.func_180495_p(lvt_8_1_).func_177230_c();
            boolean lvt_10_1_ = false;
            if (lvt_9_1_ == Blocks.field_180384_M) {
               TileEntity lvt_11_1_ = p_180648_1_.func_175625_s(lvt_8_1_);
               if (lvt_11_1_ instanceof TileEntityPiston) {
                  TileEntityPiston lvt_12_1_ = (TileEntityPiston)lvt_11_1_;
                  if (lvt_12_1_.func_174930_e() == lvt_6_1_ && lvt_12_1_.func_145868_b()) {
                     lvt_12_1_.func_145866_f();
                     lvt_10_1_ = true;
                  }
               }
            }

            if (!lvt_10_1_ && lvt_9_1_.func_149688_o() != Material.field_151579_a && func_180696_a(lvt_9_1_, p_180648_1_, lvt_8_1_, lvt_6_1_.func_176734_d(), false) && (lvt_9_1_.func_149656_h() == 0 || lvt_9_1_ == Blocks.field_150331_J || lvt_9_1_ == Blocks.field_150320_F)) {
               this.func_176319_a(p_180648_1_, p_180648_2_, lvt_6_1_, false);
            }
         } else {
            p_180648_1_.func_175698_g(p_180648_2_.func_177972_a(lvt_6_1_));
         }

         p_180648_1_.func_72908_a((double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 0.5D, (double)p_180648_2_.func_177952_p() + 0.5D, "tile.piston.in", 0.5F, p_180648_1_.field_73012_v.nextFloat() * 0.15F + 0.6F);
      }

      return true;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      IBlockState lvt_3_1_ = p_180654_1_.func_180495_p(p_180654_2_);
      if (lvt_3_1_.func_177230_c() == this && (Boolean)lvt_3_1_.func_177229_b(field_176320_b)) {
         float lvt_4_1_ = 0.25F;
         EnumFacing lvt_5_1_ = (EnumFacing)lvt_3_1_.func_177229_b(field_176321_a);
         if (lvt_5_1_ != null) {
            switch(lvt_5_1_) {
            case DOWN:
               this.func_149676_a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
               break;
            case UP:
               this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
               break;
            case NORTH:
               this.func_149676_a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
               break;
            case SOUTH:
               this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
               break;
            case WEST:
               this.func_149676_a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
               break;
            case EAST:
               this.func_149676_a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
         }
      } else {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public void func_149683_g() {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void func_180638_a(World p_180638_1_, BlockPos p_180638_2_, IBlockState p_180638_3_, AxisAlignedBB p_180638_4_, List<AxisAlignedBB> p_180638_5_, Entity p_180638_6_) {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      this.func_180654_a(p_180640_1_, p_180640_2_);
      return super.func_180640_a(p_180640_1_, p_180640_2_, p_180640_3_);
   }

   public boolean func_149686_d() {
      return false;
   }

   public static EnumFacing func_176317_b(int p_176317_0_) {
      int lvt_1_1_ = p_176317_0_ & 7;
      return lvt_1_1_ > 5 ? null : EnumFacing.func_82600_a(lvt_1_1_);
   }

   public static EnumFacing func_180695_a(World p_180695_0_, BlockPos p_180695_1_, EntityLivingBase p_180695_2_) {
      if (MathHelper.func_76135_e((float)p_180695_2_.field_70165_t - (float)p_180695_1_.func_177958_n()) < 2.0F && MathHelper.func_76135_e((float)p_180695_2_.field_70161_v - (float)p_180695_1_.func_177952_p()) < 2.0F) {
         double lvt_3_1_ = p_180695_2_.field_70163_u + (double)p_180695_2_.func_70047_e();
         if (lvt_3_1_ - (double)p_180695_1_.func_177956_o() > 2.0D) {
            return EnumFacing.UP;
         }

         if ((double)p_180695_1_.func_177956_o() - lvt_3_1_ > 0.0D) {
            return EnumFacing.DOWN;
         }
      }

      return p_180695_2_.func_174811_aO().func_176734_d();
   }

   public static boolean func_180696_a(Block p_180696_0_, World p_180696_1_, BlockPos p_180696_2_, EnumFacing p_180696_3_, boolean p_180696_4_) {
      if (p_180696_0_ == Blocks.field_150343_Z) {
         return false;
      } else if (!p_180696_1_.func_175723_af().func_177746_a(p_180696_2_)) {
         return false;
      } else if (p_180696_2_.func_177956_o() < 0 || p_180696_3_ == EnumFacing.DOWN && p_180696_2_.func_177956_o() == 0) {
         return false;
      } else if (p_180696_2_.func_177956_o() <= p_180696_1_.func_72800_K() - 1 && (p_180696_3_ != EnumFacing.UP || p_180696_2_.func_177956_o() != p_180696_1_.func_72800_K() - 1)) {
         if (p_180696_0_ != Blocks.field_150331_J && p_180696_0_ != Blocks.field_150320_F) {
            if (p_180696_0_.func_176195_g(p_180696_1_, p_180696_2_) == -1.0F) {
               return false;
            }

            if (p_180696_0_.func_149656_h() == 2) {
               return false;
            }

            if (p_180696_0_.func_149656_h() == 1) {
               if (!p_180696_4_) {
                  return false;
               }

               return true;
            }
         } else if ((Boolean)p_180696_1_.func_180495_p(p_180696_2_).func_177229_b(field_176320_b)) {
            return false;
         }

         return !(p_180696_0_ instanceof ITileEntityProvider);
      } else {
         return false;
      }
   }

   private boolean func_176319_a(World p_176319_1_, BlockPos p_176319_2_, EnumFacing p_176319_3_, boolean p_176319_4_) {
      if (!p_176319_4_) {
         p_176319_1_.func_175698_g(p_176319_2_.func_177972_a(p_176319_3_));
      }

      BlockPistonStructureHelper lvt_5_1_ = new BlockPistonStructureHelper(p_176319_1_, p_176319_2_, p_176319_3_, p_176319_4_);
      List<BlockPos> lvt_6_1_ = lvt_5_1_.func_177254_c();
      List<BlockPos> lvt_7_1_ = lvt_5_1_.func_177252_d();
      if (!lvt_5_1_.func_177253_a()) {
         return false;
      } else {
         int lvt_8_1_ = lvt_6_1_.size() + lvt_7_1_.size();
         Block[] lvt_9_1_ = new Block[lvt_8_1_];
         EnumFacing lvt_10_1_ = p_176319_4_ ? p_176319_3_ : p_176319_3_.func_176734_d();

         int lvt_11_2_;
         BlockPos lvt_12_2_;
         for(lvt_11_2_ = lvt_7_1_.size() - 1; lvt_11_2_ >= 0; --lvt_11_2_) {
            lvt_12_2_ = (BlockPos)lvt_7_1_.get(lvt_11_2_);
            Block lvt_13_1_ = p_176319_1_.func_180495_p(lvt_12_2_).func_177230_c();
            lvt_13_1_.func_176226_b(p_176319_1_, lvt_12_2_, p_176319_1_.func_180495_p(lvt_12_2_), 0);
            p_176319_1_.func_175698_g(lvt_12_2_);
            --lvt_8_1_;
            lvt_9_1_[lvt_8_1_] = lvt_13_1_;
         }

         IBlockState lvt_13_3_;
         for(lvt_11_2_ = lvt_6_1_.size() - 1; lvt_11_2_ >= 0; --lvt_11_2_) {
            lvt_12_2_ = (BlockPos)lvt_6_1_.get(lvt_11_2_);
            lvt_13_3_ = p_176319_1_.func_180495_p(lvt_12_2_);
            Block lvt_14_1_ = lvt_13_3_.func_177230_c();
            lvt_14_1_.func_176201_c(lvt_13_3_);
            p_176319_1_.func_175698_g(lvt_12_2_);
            lvt_12_2_ = lvt_12_2_.func_177972_a(lvt_10_1_);
            p_176319_1_.func_180501_a(lvt_12_2_, Blocks.field_180384_M.func_176223_P().func_177226_a(field_176321_a, p_176319_3_), 4);
            p_176319_1_.func_175690_a(lvt_12_2_, BlockPistonMoving.func_176423_a(lvt_13_3_, p_176319_3_, p_176319_4_, false));
            --lvt_8_1_;
            lvt_9_1_[lvt_8_1_] = lvt_14_1_;
         }

         BlockPos lvt_11_3_ = p_176319_2_.func_177972_a(p_176319_3_);
         if (p_176319_4_) {
            BlockPistonExtension.EnumPistonType lvt_12_3_ = this.field_150082_a ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT;
            lvt_13_3_ = Blocks.field_150332_K.func_176223_P().func_177226_a(BlockPistonExtension.field_176326_a, p_176319_3_).func_177226_a(BlockPistonExtension.field_176325_b, lvt_12_3_);
            IBlockState lvt_14_2_ = Blocks.field_180384_M.func_176223_P().func_177226_a(BlockPistonMoving.field_176426_a, p_176319_3_).func_177226_a(BlockPistonMoving.field_176425_b, this.field_150082_a ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
            p_176319_1_.func_180501_a(lvt_11_3_, lvt_14_2_, 4);
            p_176319_1_.func_175690_a(lvt_11_3_, BlockPistonMoving.func_176423_a(lvt_13_3_, p_176319_3_, true, false));
         }

         int lvt_12_5_;
         for(lvt_12_5_ = lvt_7_1_.size() - 1; lvt_12_5_ >= 0; --lvt_12_5_) {
            p_176319_1_.func_175685_c((BlockPos)lvt_7_1_.get(lvt_12_5_), lvt_9_1_[lvt_8_1_++]);
         }

         for(lvt_12_5_ = lvt_6_1_.size() - 1; lvt_12_5_ >= 0; --lvt_12_5_) {
            p_176319_1_.func_175685_c((BlockPos)lvt_6_1_.get(lvt_12_5_), lvt_9_1_[lvt_8_1_++]);
         }

         if (p_176319_4_) {
            p_176319_1_.func_175685_c(lvt_11_3_, Blocks.field_150332_K);
            p_176319_1_.func_175685_c(p_176319_2_, this);
         }

         return true;
      }
   }

   public IBlockState func_176217_b(IBlockState p_176217_1_) {
      return this.func_176223_P().func_177226_a(field_176321_a, EnumFacing.UP);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176321_a, func_176317_b(p_176203_1_)).func_177226_a(field_176320_b, (p_176203_1_ & 8) > 0);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_ = lvt_2_1_ | ((EnumFacing)p_176201_1_.func_177229_b(field_176321_a)).func_176745_a();
      if ((Boolean)p_176201_1_.func_177229_b(field_176320_b)) {
         lvt_2_1_ |= 8;
      }

      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176321_a, field_176320_b});
   }
}
