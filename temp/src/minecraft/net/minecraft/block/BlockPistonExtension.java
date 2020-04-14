package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonExtension extends Block {
   public static final PropertyDirection field_176326_a = PropertyDirection.func_177714_a("facing");
   public static final PropertyEnum<BlockPistonExtension.EnumPistonType> field_176325_b = PropertyEnum.func_177709_a("type", BlockPistonExtension.EnumPistonType.class);
   public static final PropertyBool field_176327_M = PropertyBool.func_177716_a("short");

   public BlockPistonExtension() {
      super(Material.field_76233_E);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176326_a, EnumFacing.NORTH).func_177226_a(field_176325_b, BlockPistonExtension.EnumPistonType.DEFAULT).func_177226_a(field_176327_M, false));
      this.func_149672_a(field_149780_i);
      this.func_149711_c(0.5F);
   }

   public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, IBlockState p_176208_3_, EntityPlayer p_176208_4_) {
      if (p_176208_4_.field_71075_bZ.field_75098_d) {
         EnumFacing lvt_5_1_ = (EnumFacing)p_176208_3_.func_177229_b(field_176326_a);
         if (lvt_5_1_ != null) {
            BlockPos lvt_6_1_ = p_176208_2_.func_177972_a(lvt_5_1_.func_176734_d());
            Block lvt_7_1_ = p_176208_1_.func_180495_p(lvt_6_1_).func_177230_c();
            if (lvt_7_1_ == Blocks.field_150331_J || lvt_7_1_ == Blocks.field_150320_F) {
               p_176208_1_.func_175698_g(lvt_6_1_);
            }
         }
      }

      super.func_176208_a(p_176208_1_, p_176208_2_, p_176208_3_, p_176208_4_);
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
      EnumFacing lvt_4_1_ = ((EnumFacing)p_180663_3_.func_177229_b(field_176326_a)).func_176734_d();
      p_180663_2_ = p_180663_2_.func_177972_a(lvt_4_1_);
      IBlockState lvt_5_1_ = p_180663_1_.func_180495_p(p_180663_2_);
      if ((lvt_5_1_.func_177230_c() == Blocks.field_150331_J || lvt_5_1_.func_177230_c() == Blocks.field_150320_F) && (Boolean)lvt_5_1_.func_177229_b(BlockPistonBase.field_176320_b)) {
         lvt_5_1_.func_177230_c().func_176226_b(p_180663_1_, p_180663_2_, lvt_5_1_, 0);
         p_180663_1_.func_175698_g(p_180663_2_);
      }

   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return false;
   }

   public boolean func_176198_a(World p_176198_1_, BlockPos p_176198_2_, EnumFacing p_176198_3_) {
      return false;
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public void func_180638_a(World p_180638_1_, BlockPos p_180638_2_, IBlockState p_180638_3_, AxisAlignedBB p_180638_4_, List<AxisAlignedBB> p_180638_5_, Entity p_180638_6_) {
      this.func_176324_d(p_180638_3_);
      super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
      this.func_176323_e(p_180638_3_);
      super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   private void func_176323_e(IBlockState p_176323_1_) {
      float lvt_2_1_ = 0.25F;
      float lvt_3_1_ = 0.375F;
      float lvt_4_1_ = 0.625F;
      float lvt_5_1_ = 0.25F;
      float lvt_6_1_ = 0.75F;
      switch((EnumFacing)p_176323_1_.func_177229_b(field_176326_a)) {
      case DOWN:
         this.func_149676_a(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
         break;
      case UP:
         this.func_149676_a(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
         break;
      case NORTH:
         this.func_149676_a(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
         break;
      case SOUTH:
         this.func_149676_a(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
         break;
      case WEST:
         this.func_149676_a(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
         break;
      case EAST:
         this.func_149676_a(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
      }

   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      this.func_176324_d(p_180654_1_.func_180495_p(p_180654_2_));
   }

   public void func_176324_d(IBlockState p_176324_1_) {
      float lvt_2_1_ = 0.25F;
      EnumFacing lvt_3_1_ = (EnumFacing)p_176324_1_.func_177229_b(field_176326_a);
      if (lvt_3_1_ != null) {
         switch(lvt_3_1_) {
         case DOWN:
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
            break;
         case UP:
            this.func_149676_a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;
         case NORTH:
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
            break;
         case SOUTH:
            this.func_149676_a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
            break;
         case WEST:
            this.func_149676_a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
            break;
         case EAST:
            this.func_149676_a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         }

      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      EnumFacing lvt_5_1_ = (EnumFacing)p_176204_3_.func_177229_b(field_176326_a);
      BlockPos lvt_6_1_ = p_176204_2_.func_177972_a(lvt_5_1_.func_176734_d());
      IBlockState lvt_7_1_ = p_176204_1_.func_180495_p(lvt_6_1_);
      if (lvt_7_1_.func_177230_c() != Blocks.field_150331_J && lvt_7_1_.func_177230_c() != Blocks.field_150320_F) {
         p_176204_1_.func_175698_g(p_176204_2_);
      } else {
         lvt_7_1_.func_177230_c().func_176204_a(p_176204_1_, lvt_6_1_, lvt_7_1_, p_176204_4_);
      }

   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      return true;
   }

   public static EnumFacing func_176322_b(int p_176322_0_) {
      int lvt_1_1_ = p_176322_0_ & 7;
      return lvt_1_1_ > 5 ? null : EnumFacing.func_82600_a(lvt_1_1_);
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return p_180665_1_.func_180495_p(p_180665_2_).func_177229_b(field_176325_b) == BlockPistonExtension.EnumPistonType.STICKY ? Item.func_150898_a(Blocks.field_150320_F) : Item.func_150898_a(Blocks.field_150331_J);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176326_a, func_176322_b(p_176203_1_)).func_177226_a(field_176325_b, (p_176203_1_ & 8) > 0 ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_ = lvt_2_1_ | ((EnumFacing)p_176201_1_.func_177229_b(field_176326_a)).func_176745_a();
      if (p_176201_1_.func_177229_b(field_176325_b) == BlockPistonExtension.EnumPistonType.STICKY) {
         lvt_2_1_ |= 8;
      }

      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176326_a, field_176325_b, field_176327_M});
   }

   public static enum EnumPistonType implements IStringSerializable {
      DEFAULT("normal"),
      STICKY("sticky");

      private final String field_176714_c;

      private EnumPistonType(String p_i45666_3_) {
         this.field_176714_c = p_i45666_3_;
      }

      public String toString() {
         return this.field_176714_c;
      }

      public String func_176610_l() {
         return this.field_176714_c;
      }
   }
}
