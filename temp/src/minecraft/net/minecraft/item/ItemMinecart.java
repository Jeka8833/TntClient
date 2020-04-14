package net.minecraft.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemMinecart extends Item {
   private static final IBehaviorDispenseItem field_96602_b = new BehaviorDefaultDispenseItem() {
      private final BehaviorDefaultDispenseItem field_96465_b = new BehaviorDefaultDispenseItem();

      public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
         EnumFacing lvt_3_1_ = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
         World lvt_4_1_ = p_82487_1_.func_82618_k();
         double lvt_5_1_ = p_82487_1_.func_82615_a() + (double)lvt_3_1_.func_82601_c() * 1.125D;
         double lvt_7_1_ = Math.floor(p_82487_1_.func_82617_b()) + (double)lvt_3_1_.func_96559_d();
         double lvt_9_1_ = p_82487_1_.func_82616_c() + (double)lvt_3_1_.func_82599_e() * 1.125D;
         BlockPos lvt_11_1_ = p_82487_1_.func_180699_d().func_177972_a(lvt_3_1_);
         IBlockState lvt_12_1_ = lvt_4_1_.func_180495_p(lvt_11_1_);
         BlockRailBase.EnumRailDirection lvt_13_1_ = lvt_12_1_.func_177230_c() instanceof BlockRailBase ? (BlockRailBase.EnumRailDirection)lvt_12_1_.func_177229_b(((BlockRailBase)lvt_12_1_.func_177230_c()).func_176560_l()) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         double lvt_14_5_;
         if (BlockRailBase.func_176563_d(lvt_12_1_)) {
            if (lvt_13_1_.func_177018_c()) {
               lvt_14_5_ = 0.6D;
            } else {
               lvt_14_5_ = 0.1D;
            }
         } else {
            if (lvt_12_1_.func_177230_c().func_149688_o() != Material.field_151579_a || !BlockRailBase.func_176563_d(lvt_4_1_.func_180495_p(lvt_11_1_.func_177977_b()))) {
               return this.field_96465_b.func_82482_a(p_82487_1_, p_82487_2_);
            }

            IBlockState lvt_16_1_ = lvt_4_1_.func_180495_p(lvt_11_1_.func_177977_b());
            BlockRailBase.EnumRailDirection lvt_17_1_ = lvt_16_1_.func_177230_c() instanceof BlockRailBase ? (BlockRailBase.EnumRailDirection)lvt_16_1_.func_177229_b(((BlockRailBase)lvt_16_1_.func_177230_c()).func_176560_l()) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
            if (lvt_3_1_ != EnumFacing.DOWN && lvt_17_1_.func_177018_c()) {
               lvt_14_5_ = -0.4D;
            } else {
               lvt_14_5_ = -0.9D;
            }
         }

         EntityMinecart lvt_16_2_ = EntityMinecart.func_180458_a(lvt_4_1_, lvt_5_1_, lvt_7_1_ + lvt_14_5_, lvt_9_1_, ((ItemMinecart)p_82487_2_.func_77973_b()).field_77841_a);
         if (p_82487_2_.func_82837_s()) {
            lvt_16_2_.func_96094_a(p_82487_2_.func_82833_r());
         }

         lvt_4_1_.func_72838_d(lvt_16_2_);
         p_82487_2_.func_77979_a(1);
         return p_82487_2_;
      }

      protected void func_82485_a(IBlockSource p_82485_1_) {
         p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
      }
   };
   private final EntityMinecart.EnumMinecartType field_77841_a;

   public ItemMinecart(EntityMinecart.EnumMinecartType p_i45785_1_) {
      this.field_77777_bU = 1;
      this.field_77841_a = p_i45785_1_;
      this.func_77637_a(CreativeTabs.field_78029_e);
      BlockDispenser.field_149943_a.func_82595_a(this, field_96602_b);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      IBlockState lvt_9_1_ = p_180614_3_.func_180495_p(p_180614_4_);
      if (BlockRailBase.func_176563_d(lvt_9_1_)) {
         if (!p_180614_3_.field_72995_K) {
            BlockRailBase.EnumRailDirection lvt_10_1_ = lvt_9_1_.func_177230_c() instanceof BlockRailBase ? (BlockRailBase.EnumRailDirection)lvt_9_1_.func_177229_b(((BlockRailBase)lvt_9_1_.func_177230_c()).func_176560_l()) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
            double lvt_11_1_ = 0.0D;
            if (lvt_10_1_.func_177018_c()) {
               lvt_11_1_ = 0.5D;
            }

            EntityMinecart lvt_13_1_ = EntityMinecart.func_180458_a(p_180614_3_, (double)p_180614_4_.func_177958_n() + 0.5D, (double)p_180614_4_.func_177956_o() + 0.0625D + lvt_11_1_, (double)p_180614_4_.func_177952_p() + 0.5D, this.field_77841_a);
            if (p_180614_1_.func_82837_s()) {
               lvt_13_1_.func_96094_a(p_180614_1_.func_82833_r());
            }

            p_180614_3_.func_72838_d(lvt_13_1_);
         }

         --p_180614_1_.field_77994_a;
         return true;
      } else {
         return false;
      }
   }
}
