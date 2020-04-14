package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockFurnace extends BlockContainer {
   public static final PropertyDirection field_176447_a;
   private final boolean field_149932_b;
   private static boolean field_149934_M;

   protected BlockFurnace(boolean p_i45407_1_) {
      super(Material.field_151576_e);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176447_a, EnumFacing.NORTH));
      this.field_149932_b = p_i45407_1_;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Item.func_150898_a(Blocks.field_150460_al);
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176445_e(p_176213_1_, p_176213_2_, p_176213_3_);
   }

   private void func_176445_e(World p_176445_1_, BlockPos p_176445_2_, IBlockState p_176445_3_) {
      if (!p_176445_1_.field_72995_K) {
         Block lvt_4_1_ = p_176445_1_.func_180495_p(p_176445_2_.func_177978_c()).func_177230_c();
         Block lvt_5_1_ = p_176445_1_.func_180495_p(p_176445_2_.func_177968_d()).func_177230_c();
         Block lvt_6_1_ = p_176445_1_.func_180495_p(p_176445_2_.func_177976_e()).func_177230_c();
         Block lvt_7_1_ = p_176445_1_.func_180495_p(p_176445_2_.func_177974_f()).func_177230_c();
         EnumFacing lvt_8_1_ = (EnumFacing)p_176445_3_.func_177229_b(field_176447_a);
         if (lvt_8_1_ == EnumFacing.NORTH && lvt_4_1_.func_149730_j() && !lvt_5_1_.func_149730_j()) {
            lvt_8_1_ = EnumFacing.SOUTH;
         } else if (lvt_8_1_ == EnumFacing.SOUTH && lvt_5_1_.func_149730_j() && !lvt_4_1_.func_149730_j()) {
            lvt_8_1_ = EnumFacing.NORTH;
         } else if (lvt_8_1_ == EnumFacing.WEST && lvt_6_1_.func_149730_j() && !lvt_7_1_.func_149730_j()) {
            lvt_8_1_ = EnumFacing.EAST;
         } else if (lvt_8_1_ == EnumFacing.EAST && lvt_7_1_.func_149730_j() && !lvt_6_1_.func_149730_j()) {
            lvt_8_1_ = EnumFacing.WEST;
         }

         p_176445_1_.func_180501_a(p_176445_2_, p_176445_3_.func_177226_a(field_176447_a, lvt_8_1_), 2);
      }
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      if (this.field_149932_b) {
         EnumFacing lvt_5_1_ = (EnumFacing)p_180655_3_.func_177229_b(field_176447_a);
         double lvt_6_1_ = (double)p_180655_2_.func_177958_n() + 0.5D;
         double lvt_8_1_ = (double)p_180655_2_.func_177956_o() + p_180655_4_.nextDouble() * 6.0D / 16.0D;
         double lvt_10_1_ = (double)p_180655_2_.func_177952_p() + 0.5D;
         double lvt_12_1_ = 0.52D;
         double lvt_14_1_ = p_180655_4_.nextDouble() * 0.6D - 0.3D;
         switch(lvt_5_1_) {
         case WEST:
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_6_1_ - lvt_12_1_, lvt_8_1_, lvt_10_1_ + lvt_14_1_, 0.0D, 0.0D, 0.0D);
            p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, lvt_6_1_ - lvt_12_1_, lvt_8_1_, lvt_10_1_ + lvt_14_1_, 0.0D, 0.0D, 0.0D);
            break;
         case EAST:
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_6_1_ + lvt_12_1_, lvt_8_1_, lvt_10_1_ + lvt_14_1_, 0.0D, 0.0D, 0.0D);
            p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, lvt_6_1_ + lvt_12_1_, lvt_8_1_, lvt_10_1_ + lvt_14_1_, 0.0D, 0.0D, 0.0D);
            break;
         case NORTH:
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_6_1_ + lvt_14_1_, lvt_8_1_, lvt_10_1_ - lvt_12_1_, 0.0D, 0.0D, 0.0D);
            p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, lvt_6_1_ + lvt_14_1_, lvt_8_1_, lvt_10_1_ - lvt_12_1_, 0.0D, 0.0D, 0.0D);
            break;
         case SOUTH:
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, lvt_6_1_ + lvt_14_1_, lvt_8_1_, lvt_10_1_ + lvt_12_1_, 0.0D, 0.0D, 0.0D);
            p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, lvt_6_1_ + lvt_14_1_, lvt_8_1_, lvt_10_1_ + lvt_12_1_, 0.0D, 0.0D, 0.0D);
         }

      }
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if (p_180639_1_.field_72995_K) {
         return true;
      } else {
         TileEntity lvt_9_1_ = p_180639_1_.func_175625_s(p_180639_2_);
         if (lvt_9_1_ instanceof TileEntityFurnace) {
            p_180639_4_.func_71007_a((TileEntityFurnace)lvt_9_1_);
            p_180639_4_.func_71029_a(StatList.field_181741_Y);
         }

         return true;
      }
   }

   public static void func_176446_a(boolean p_176446_0_, World p_176446_1_, BlockPos p_176446_2_) {
      IBlockState lvt_3_1_ = p_176446_1_.func_180495_p(p_176446_2_);
      TileEntity lvt_4_1_ = p_176446_1_.func_175625_s(p_176446_2_);
      field_149934_M = true;
      if (p_176446_0_) {
         p_176446_1_.func_180501_a(p_176446_2_, Blocks.field_150470_am.func_176223_P().func_177226_a(field_176447_a, lvt_3_1_.func_177229_b(field_176447_a)), 3);
         p_176446_1_.func_180501_a(p_176446_2_, Blocks.field_150470_am.func_176223_P().func_177226_a(field_176447_a, lvt_3_1_.func_177229_b(field_176447_a)), 3);
      } else {
         p_176446_1_.func_180501_a(p_176446_2_, Blocks.field_150460_al.func_176223_P().func_177226_a(field_176447_a, lvt_3_1_.func_177229_b(field_176447_a)), 3);
         p_176446_1_.func_180501_a(p_176446_2_, Blocks.field_150460_al.func_176223_P().func_177226_a(field_176447_a, lvt_3_1_.func_177229_b(field_176447_a)), 3);
      }

      field_149934_M = false;
      if (lvt_4_1_ != null) {
         lvt_4_1_.func_145829_t();
         p_176446_1_.func_175690_a(p_176446_2_, lvt_4_1_);
      }

   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityFurnace();
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176447_a, p_180642_8_.func_174811_aO().func_176734_d());
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_.func_177226_a(field_176447_a, p_180633_4_.func_174811_aO().func_176734_d()), 2);
      if (p_180633_5_.func_82837_s()) {
         TileEntity lvt_6_1_ = p_180633_1_.func_175625_s(p_180633_2_);
         if (lvt_6_1_ instanceof TileEntityFurnace) {
            ((TileEntityFurnace)lvt_6_1_).func_145951_a(p_180633_5_.func_82833_r());
         }
      }

   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      if (!field_149934_M) {
         TileEntity lvt_4_1_ = p_180663_1_.func_175625_s(p_180663_2_);
         if (lvt_4_1_ instanceof TileEntityFurnace) {
            InventoryHelper.func_180175_a(p_180663_1_, p_180663_2_, (TileEntityFurnace)lvt_4_1_);
            p_180663_1_.func_175666_e(p_180663_2_, this);
         }
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public boolean func_149740_M() {
      return true;
   }

   public int func_180641_l(World p_180641_1_, BlockPos p_180641_2_) {
      return Container.func_178144_a(p_180641_1_.func_175625_s(p_180641_2_));
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Item.func_150898_a(Blocks.field_150460_al);
   }

   public int func_149645_b() {
      return 3;
   }

   public IBlockState func_176217_b(IBlockState p_176217_1_) {
      return this.func_176223_P().func_177226_a(field_176447_a, EnumFacing.SOUTH);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing lvt_2_1_ = EnumFacing.func_82600_a(p_176203_1_);
      if (lvt_2_1_.func_176740_k() == EnumFacing.Axis.Y) {
         lvt_2_1_ = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176447_a, lvt_2_1_);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176447_a)).func_176745_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176447_a});
   }

   static {
      field_176447_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   }
}
