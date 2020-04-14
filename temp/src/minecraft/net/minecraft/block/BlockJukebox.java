package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockJukebox extends BlockContainer {
   public static final PropertyBool field_176432_a = PropertyBool.func_177716_a("has_record");

   protected BlockJukebox() {
      super(Material.field_151575_d, MapColor.field_151664_l);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176432_a, false));
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if ((Boolean)p_180639_3_.func_177229_b(field_176432_a)) {
         this.func_180678_e(p_180639_1_, p_180639_2_, p_180639_3_);
         p_180639_3_ = p_180639_3_.func_177226_a(field_176432_a, false);
         p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_, 2);
         return true;
      } else {
         return false;
      }
   }

   public void func_176431_a(World p_176431_1_, BlockPos p_176431_2_, IBlockState p_176431_3_, ItemStack p_176431_4_) {
      if (!p_176431_1_.field_72995_K) {
         TileEntity lvt_5_1_ = p_176431_1_.func_175625_s(p_176431_2_);
         if (lvt_5_1_ instanceof BlockJukebox.TileEntityJukebox) {
            ((BlockJukebox.TileEntityJukebox)lvt_5_1_).func_145857_a(new ItemStack(p_176431_4_.func_77973_b(), 1, p_176431_4_.func_77960_j()));
            p_176431_1_.func_180501_a(p_176431_2_, p_176431_3_.func_177226_a(field_176432_a, true), 2);
         }
      }
   }

   private void func_180678_e(World p_180678_1_, BlockPos p_180678_2_, IBlockState p_180678_3_) {
      if (!p_180678_1_.field_72995_K) {
         TileEntity lvt_4_1_ = p_180678_1_.func_175625_s(p_180678_2_);
         if (lvt_4_1_ instanceof BlockJukebox.TileEntityJukebox) {
            BlockJukebox.TileEntityJukebox lvt_5_1_ = (BlockJukebox.TileEntityJukebox)lvt_4_1_;
            ItemStack lvt_6_1_ = lvt_5_1_.func_145856_a();
            if (lvt_6_1_ != null) {
               p_180678_1_.func_175718_b(1005, p_180678_2_, 0);
               p_180678_1_.func_175717_a(p_180678_2_, (String)null);
               lvt_5_1_.func_145857_a((ItemStack)null);
               float lvt_7_1_ = 0.7F;
               double lvt_8_1_ = (double)(p_180678_1_.field_73012_v.nextFloat() * lvt_7_1_) + (double)(1.0F - lvt_7_1_) * 0.5D;
               double lvt_10_1_ = (double)(p_180678_1_.field_73012_v.nextFloat() * lvt_7_1_) + (double)(1.0F - lvt_7_1_) * 0.2D + 0.6D;
               double lvt_12_1_ = (double)(p_180678_1_.field_73012_v.nextFloat() * lvt_7_1_) + (double)(1.0F - lvt_7_1_) * 0.5D;
               ItemStack lvt_14_1_ = lvt_6_1_.func_77946_l();
               EntityItem lvt_15_1_ = new EntityItem(p_180678_1_, (double)p_180678_2_.func_177958_n() + lvt_8_1_, (double)p_180678_2_.func_177956_o() + lvt_10_1_, (double)p_180678_2_.func_177952_p() + lvt_12_1_, lvt_14_1_);
               lvt_15_1_.func_174869_p();
               p_180678_1_.func_72838_d(lvt_15_1_);
            }
         }
      }
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      this.func_180678_e(p_180663_1_, p_180663_2_, p_180663_3_);
      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      if (!p_180653_1_.field_72995_K) {
         super.func_180653_a(p_180653_1_, p_180653_2_, p_180653_3_, p_180653_4_, 0);
      }
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new BlockJukebox.TileEntityJukebox();
   }

   public boolean func_149740_M() {
      return true;
   }

   public int func_180641_l(World p_180641_1_, BlockPos p_180641_2_) {
      TileEntity lvt_3_1_ = p_180641_1_.func_175625_s(p_180641_2_);
      if (lvt_3_1_ instanceof BlockJukebox.TileEntityJukebox) {
         ItemStack lvt_4_1_ = ((BlockJukebox.TileEntityJukebox)lvt_3_1_).func_145856_a();
         if (lvt_4_1_ != null) {
            return Item.func_150891_b(lvt_4_1_.func_77973_b()) + 1 - Item.func_150891_b(Items.field_151096_cd);
         }
      }

      return 0;
   }

   public int func_149645_b() {
      return 3;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176432_a, p_176203_1_ > 0);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return (Boolean)p_176201_1_.func_177229_b(field_176432_a) ? 1 : 0;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176432_a});
   }

   public static class TileEntityJukebox extends TileEntity {
      private ItemStack field_145858_a;

      public void func_145839_a(NBTTagCompound p_145839_1_) {
         super.func_145839_a(p_145839_1_);
         if (p_145839_1_.func_150297_b("RecordItem", 10)) {
            this.func_145857_a(ItemStack.func_77949_a(p_145839_1_.func_74775_l("RecordItem")));
         } else if (p_145839_1_.func_74762_e("Record") > 0) {
            this.func_145857_a(new ItemStack(Item.func_150899_d(p_145839_1_.func_74762_e("Record")), 1, 0));
         }

      }

      public void func_145841_b(NBTTagCompound p_145841_1_) {
         super.func_145841_b(p_145841_1_);
         if (this.func_145856_a() != null) {
            p_145841_1_.func_74782_a("RecordItem", this.func_145856_a().func_77955_b(new NBTTagCompound()));
         }

      }

      public ItemStack func_145856_a() {
         return this.field_145858_a;
      }

      public void func_145857_a(ItemStack p_145857_1_) {
         this.field_145858_a = p_145857_1_;
         this.func_70296_d();
      }
   }
}
