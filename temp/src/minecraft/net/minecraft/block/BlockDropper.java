package net.minecraft.block;

import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDropper extends BlockDispenser {
   private final IBehaviorDispenseItem field_149947_P = new BehaviorDefaultDispenseItem();

   protected IBehaviorDispenseItem func_149940_a(ItemStack p_149940_1_) {
      return this.field_149947_P;
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityDropper();
   }

   protected void func_176439_d(World p_176439_1_, BlockPos p_176439_2_) {
      BlockSourceImpl lvt_3_1_ = new BlockSourceImpl(p_176439_1_, p_176439_2_);
      TileEntityDispenser lvt_4_1_ = (TileEntityDispenser)lvt_3_1_.func_150835_j();
      if (lvt_4_1_ != null) {
         int lvt_5_1_ = lvt_4_1_.func_146017_i();
         if (lvt_5_1_ < 0) {
            p_176439_1_.func_175718_b(1001, p_176439_2_, 0);
         } else {
            ItemStack lvt_6_1_ = lvt_4_1_.func_70301_a(lvt_5_1_);
            if (lvt_6_1_ != null) {
               EnumFacing lvt_7_1_ = (EnumFacing)p_176439_1_.func_180495_p(p_176439_2_).func_177229_b(field_176441_a);
               BlockPos lvt_8_1_ = p_176439_2_.func_177972_a(lvt_7_1_);
               IInventory lvt_9_1_ = TileEntityHopper.func_145893_b(p_176439_1_, (double)lvt_8_1_.func_177958_n(), (double)lvt_8_1_.func_177956_o(), (double)lvt_8_1_.func_177952_p());
               ItemStack lvt_10_2_;
               if (lvt_9_1_ == null) {
                  lvt_10_2_ = this.field_149947_P.func_82482_a(lvt_3_1_, lvt_6_1_);
                  if (lvt_10_2_ != null && lvt_10_2_.field_77994_a <= 0) {
                     lvt_10_2_ = null;
                  }
               } else {
                  lvt_10_2_ = TileEntityHopper.func_174918_a(lvt_9_1_, lvt_6_1_.func_77946_l().func_77979_a(1), lvt_7_1_.func_176734_d());
                  if (lvt_10_2_ == null) {
                     lvt_10_2_ = lvt_6_1_.func_77946_l();
                     if (--lvt_10_2_.field_77994_a <= 0) {
                        lvt_10_2_ = null;
                     }
                  } else {
                     lvt_10_2_ = lvt_6_1_.func_77946_l();
                  }
               }

               lvt_4_1_.func_70299_a(lvt_5_1_, lvt_10_2_);
            }
         }
      }
   }
}
