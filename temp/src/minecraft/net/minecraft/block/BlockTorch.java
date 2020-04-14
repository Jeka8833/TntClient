package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BlockTorch extends Block {
   public static final PropertyDirection field_176596_a = PropertyDirection.func_177712_a("facing", new Predicate<EnumFacing>() {
      public boolean apply(EnumFacing p_apply_1_) {
         return p_apply_1_ != EnumFacing.DOWN;
      }
   });

   protected BlockTorch() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176596_a, EnumFacing.UP));
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   private boolean func_176594_d(World p_176594_1_, BlockPos p_176594_2_) {
      if (World.func_175683_a(p_176594_1_, p_176594_2_)) {
         return true;
      } else {
         Block lvt_3_1_ = p_176594_1_.func_180495_p(p_176594_2_).func_177230_c();
         return lvt_3_1_ instanceof BlockFence || lvt_3_1_ == Blocks.field_150359_w || lvt_3_1_ == Blocks.field_150463_bK || lvt_3_1_ == Blocks.field_150399_cn;
      }
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      Iterator lvt_3_1_ = field_176596_a.func_177700_c().iterator();

      EnumFacing lvt_4_1_;
      do {
         if (!lvt_3_1_.hasNext()) {
            return false;
         }

         lvt_4_1_ = (EnumFacing)lvt_3_1_.next();
      } while(!this.func_176595_b(p_176196_1_, p_176196_2_, lvt_4_1_));

      return true;
   }

   private boolean func_176595_b(World p_176595_1_, BlockPos p_176595_2_, EnumFacing p_176595_3_) {
      BlockPos lvt_4_1_ = p_176595_2_.func_177972_a(p_176595_3_.func_176734_d());
      boolean lvt_5_1_ = p_176595_3_.func_176740_k().func_176722_c();
      return lvt_5_1_ && p_176595_1_.func_175677_d(lvt_4_1_, true) || p_176595_3_.equals(EnumFacing.UP) && this.func_176594_d(p_176595_1_, lvt_4_1_);
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      if (this.func_176595_b(p_180642_1_, p_180642_2_, p_180642_3_)) {
         return this.func_176223_P().func_177226_a(field_176596_a, p_180642_3_);
      } else {
         Iterator lvt_9_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing lvt_10_1_;
         do {
            if (!lvt_9_1_.hasNext()) {
               return this.func_176223_P();
            }

            lvt_10_1_ = (EnumFacing)lvt_9_1_.next();
         } while(!p_180642_1_.func_175677_d(p_180642_2_.func_177972_a(lvt_10_1_.func_176734_d()), true));

         return this.func_176223_P().func_177226_a(field_176596_a, lvt_10_1_);
      }
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176593_f(p_176213_1_, p_176213_2_, p_176213_3_);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      this.func_176592_e(p_176204_1_, p_176204_2_, p_176204_3_);
   }

   protected boolean func_176592_e(World p_176592_1_, BlockPos p_176592_2_, IBlockState p_176592_3_) {
      if (!this.func_176593_f(p_176592_1_, p_176592_2_, p_176592_3_)) {
         return true;
      } else {
         EnumFacing lvt_4_1_ = (EnumFacing)p_176592_3_.func_177229_b(field_176596_a);
         EnumFacing.Axis lvt_5_1_ = lvt_4_1_.func_176740_k();
         EnumFacing lvt_6_1_ = lvt_4_1_.func_176734_d();
         boolean lvt_7_1_ = false;
         if (lvt_5_1_.func_176722_c() && !p_176592_1_.func_175677_d(p_176592_2_.func_177972_a(lvt_6_1_), true)) {
            lvt_7_1_ = true;
         } else if (lvt_5_1_.func_176720_b() && !this.func_176594_d(p_176592_1_, p_176592_2_.func_177972_a(lvt_6_1_))) {
            lvt_7_1_ = true;
         }

         if (lvt_7_1_) {
            this.func_176226_b(p_176592_1_, p_176592_2_, p_176592_3_, 0);
            p_176592_1_.func_175698_g(p_176592_2_);
            return true;
         } else {
            return false;
         }
      }
   }

   protected boolean func_176593_f(World p_176593_1_, BlockPos p_176593_2_, IBlockState p_176593_3_) {
      if (p_176593_3_.func_177230_c() == this && this.func_176595_b(p_176593_1_, p_176593_2_, (EnumFacing)p_176593_3_.func_177229_b(field_176596_a))) {
         return true;
      } else {
         if (p_176593_1_.func_180495_p(p_176593_2_).func_177230_c() == this) {
            this.func_176226_b(p_176593_1_, p_176593_2_, p_176593_3_, 0);
            p_176593_1_.func_175698_g(p_176593_2_);
         }

         return false;
      }
   }

   public MovingObjectPosition func_180636_a(World p_180636_1_, BlockPos p_180636_2_, Vec3 p_180636_3_, Vec3 p_180636_4_) {
      EnumFacing lvt_5_1_ = (EnumFacing)p_180636_1_.func_180495_p(p_180636_2_).func_177229_b(field_176596_a);
      float lvt_6_1_ = 0.15F;
      if (lvt_5_1_ == EnumFacing.EAST) {
         this.func_149676_a(0.0F, 0.2F, 0.5F - lvt_6_1_, lvt_6_1_ * 2.0F, 0.8F, 0.5F + lvt_6_1_);
      } else if (lvt_5_1_ == EnumFacing.WEST) {
         this.func_149676_a(1.0F - lvt_6_1_ * 2.0F, 0.2F, 0.5F - lvt_6_1_, 1.0F, 0.8F, 0.5F + lvt_6_1_);
      } else if (lvt_5_1_ == EnumFacing.SOUTH) {
         this.func_149676_a(0.5F - lvt_6_1_, 0.2F, 0.0F, 0.5F + lvt_6_1_, 0.8F, lvt_6_1_ * 2.0F);
      } else if (lvt_5_1_ == EnumFacing.NORTH) {
         this.func_149676_a(0.5F - lvt_6_1_, 0.2F, 1.0F - lvt_6_1_ * 2.0F, 0.5F + lvt_6_1_, 0.8F, 1.0F);
      } else {
         lvt_6_1_ = 0.1F;
         this.func_149676_a(0.5F - lvt_6_1_, 0.0F, 0.5F - lvt_6_1_, 0.5F + lvt_6_1_, 0.6F, 0.5F + lvt_6_1_);
      }

      return super.func_180636_a(p_180636_1_, p_180636_2_, p_180636_3_, p_180636_4_);
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      EnumFacing lvt_5_1_ = (EnumFacing)p_180655_3_.func_177229_b(field_176596_a);
      double lvt_6_1_ = (double)p_180655_2_.func_177958_n() + 0.5D;
      double lvt_8_1_ = (double)p_180655_2_.func_177956_o() + 0.7D;
      double lvt_10_1_ = (double)p_180655_2_.func_177952_p() + 0.5D;
      double lvt_12_1_ = 0.22D;
      double lvt_14_1_ = 0.27D;
      if (lvt_5_1_.func_176740_k().func_176722_c()) {
         EnumFacing lvt_16_1_ = lvt_5_1_.func_176734_d();
         p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_6_1_ + lvt_14_1_ * (double)lvt_16_1_.func_82601_c(), lvt_8_1_ + lvt_12_1_, lvt_10_1_ + lvt_14_1_ * (double)lvt_16_1_.func_82599_e(), 0.0D, 0.0D, 0.0D);
         p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, lvt_6_1_ + lvt_14_1_ * (double)lvt_16_1_.func_82601_c(), lvt_8_1_ + lvt_12_1_, lvt_10_1_ + lvt_14_1_ * (double)lvt_16_1_.func_82599_e(), 0.0D, 0.0D, 0.0D);
      } else {
         p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_6_1_, lvt_8_1_, lvt_10_1_, 0.0D, 0.0D, 0.0D);
         p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, lvt_6_1_, lvt_8_1_, lvt_10_1_, 0.0D, 0.0D, 0.0D);
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      IBlockState lvt_2_1_ = this.func_176223_P();
      switch(p_176203_1_) {
      case 1:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176596_a, EnumFacing.EAST);
         break;
      case 2:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176596_a, EnumFacing.WEST);
         break;
      case 3:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176596_a, EnumFacing.SOUTH);
         break;
      case 4:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176596_a, EnumFacing.NORTH);
         break;
      case 5:
      default:
         lvt_2_1_ = lvt_2_1_.func_177226_a(field_176596_a, EnumFacing.UP);
      }

      return lvt_2_1_;
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_;
      switch((EnumFacing)p_176201_1_.func_177229_b(field_176596_a)) {
      case EAST:
         lvt_2_1_ = lvt_2_1_ | 1;
         break;
      case WEST:
         lvt_2_1_ = lvt_2_1_ | 2;
         break;
      case SOUTH:
         lvt_2_1_ = lvt_2_1_ | 3;
         break;
      case NORTH:
         lvt_2_1_ = lvt_2_1_ | 4;
         break;
      case DOWN:
      case UP:
      default:
         lvt_2_1_ = lvt_2_1_ | 5;
      }

      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176596_a});
   }
}
