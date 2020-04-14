package net.minecraft.block;

import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLadder extends Block {
   public static final PropertyDirection field_176382_a;

   protected BlockLadder() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176382_a, EnumFacing.NORTH));
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      this.func_180654_a(p_180640_1_, p_180640_2_);
      return super.func_180640_a(p_180640_1_, p_180640_2_, p_180640_3_);
   }

   public AxisAlignedBB func_180646_a(World p_180646_1_, BlockPos p_180646_2_) {
      this.func_180654_a(p_180646_1_, p_180646_2_);
      return super.func_180646_a(p_180646_1_, p_180646_2_);
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      IBlockState lvt_3_1_ = p_180654_1_.func_180495_p(p_180654_2_);
      if (lvt_3_1_.func_177230_c() == this) {
         float lvt_4_1_ = 0.125F;
         switch((EnumFacing)lvt_3_1_.func_177229_b(field_176382_a)) {
         case NORTH:
            this.func_149676_a(0.0F, 0.0F, 1.0F - lvt_4_1_, 1.0F, 1.0F, 1.0F);
            break;
         case SOUTH:
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, lvt_4_1_);
            break;
         case WEST:
            this.func_149676_a(1.0F - lvt_4_1_, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;
         case EAST:
         default:
            this.func_149676_a(0.0F, 0.0F, 0.0F, lvt_4_1_, 1.0F, 1.0F);
         }

      }
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      if (p_176196_1_.func_180495_p(p_176196_2_.func_177976_e()).func_177230_c().func_149721_r()) {
         return true;
      } else if (p_176196_1_.func_180495_p(p_176196_2_.func_177974_f()).func_177230_c().func_149721_r()) {
         return true;
      } else if (p_176196_1_.func_180495_p(p_176196_2_.func_177978_c()).func_177230_c().func_149721_r()) {
         return true;
      } else {
         return p_176196_1_.func_180495_p(p_176196_2_.func_177968_d()).func_177230_c().func_149721_r();
      }
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      if (p_180642_3_.func_176740_k().func_176722_c() && this.func_176381_b(p_180642_1_, p_180642_2_, p_180642_3_)) {
         return this.func_176223_P().func_177226_a(field_176382_a, p_180642_3_);
      } else {
         Iterator lvt_9_1_ = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing lvt_10_1_;
         do {
            if (!lvt_9_1_.hasNext()) {
               return this.func_176223_P();
            }

            lvt_10_1_ = (EnumFacing)lvt_9_1_.next();
         } while(!this.func_176381_b(p_180642_1_, p_180642_2_, lvt_10_1_));

         return this.func_176223_P().func_177226_a(field_176382_a, lvt_10_1_);
      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      EnumFacing lvt_5_1_ = (EnumFacing)p_176204_3_.func_177229_b(field_176382_a);
      if (!this.func_176381_b(p_176204_1_, p_176204_2_, lvt_5_1_)) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

      super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
   }

   protected boolean func_176381_b(World p_176381_1_, BlockPos p_176381_2_, EnumFacing p_176381_3_) {
      return p_176381_1_.func_180495_p(p_176381_2_.func_177972_a(p_176381_3_.func_176734_d())).func_177230_c().func_149721_r();
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing lvt_2_1_ = EnumFacing.func_82600_a(p_176203_1_);
      if (lvt_2_1_.func_176740_k() == EnumFacing.Axis.Y) {
         lvt_2_1_ = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176382_a, lvt_2_1_);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176382_a)).func_176745_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176382_a});
   }

   static {
      field_176382_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   }
}
