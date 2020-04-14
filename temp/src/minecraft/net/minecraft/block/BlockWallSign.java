package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWallSign extends BlockSign {
   public static final PropertyDirection field_176412_a;

   public BlockWallSign() {
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176412_a, EnumFacing.NORTH));
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      EnumFacing lvt_3_1_ = (EnumFacing)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176412_a);
      float lvt_4_1_ = 0.28125F;
      float lvt_5_1_ = 0.78125F;
      float lvt_6_1_ = 0.0F;
      float lvt_7_1_ = 1.0F;
      float lvt_8_1_ = 0.125F;
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      switch(lvt_3_1_) {
      case NORTH:
         this.func_149676_a(lvt_6_1_, lvt_4_1_, 1.0F - lvt_8_1_, lvt_7_1_, lvt_5_1_, 1.0F);
         break;
      case SOUTH:
         this.func_149676_a(lvt_6_1_, lvt_4_1_, 0.0F, lvt_7_1_, lvt_5_1_, lvt_8_1_);
         break;
      case WEST:
         this.func_149676_a(1.0F - lvt_8_1_, lvt_4_1_, lvt_6_1_, 1.0F, lvt_5_1_, lvt_7_1_);
         break;
      case EAST:
         this.func_149676_a(0.0F, lvt_4_1_, lvt_6_1_, lvt_8_1_, lvt_5_1_, lvt_7_1_);
      }

   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      EnumFacing lvt_5_1_ = (EnumFacing)p_176204_3_.func_177229_b(field_176412_a);
      if (!p_176204_1_.func_180495_p(p_176204_2_.func_177972_a(lvt_5_1_.func_176734_d())).func_177230_c().func_149688_o().func_76220_a()) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

      super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing lvt_2_1_ = EnumFacing.func_82600_a(p_176203_1_);
      if (lvt_2_1_.func_176740_k() == EnumFacing.Axis.Y) {
         lvt_2_1_ = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176412_a, lvt_2_1_);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176412_a)).func_176745_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176412_a});
   }

   static {
      field_176412_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   }
}
