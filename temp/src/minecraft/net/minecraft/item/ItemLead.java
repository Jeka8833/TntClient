package net.minecraft.item;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemLead extends Item {
   public ItemLead() {
      this.func_77637_a(CreativeTabs.field_78040_i);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      Block lvt_9_1_ = p_180614_3_.func_180495_p(p_180614_4_).func_177230_c();
      if (lvt_9_1_ instanceof BlockFence) {
         if (p_180614_3_.field_72995_K) {
            return true;
         } else {
            func_180618_a(p_180614_2_, p_180614_3_, p_180614_4_);
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean func_180618_a(EntityPlayer p_180618_0_, World p_180618_1_, BlockPos p_180618_2_) {
      EntityLeashKnot lvt_3_1_ = EntityLeashKnot.func_174863_b(p_180618_1_, p_180618_2_);
      boolean lvt_4_1_ = false;
      double lvt_5_1_ = 7.0D;
      int lvt_7_1_ = p_180618_2_.func_177958_n();
      int lvt_8_1_ = p_180618_2_.func_177956_o();
      int lvt_9_1_ = p_180618_2_.func_177952_p();
      List<EntityLiving> lvt_10_1_ = p_180618_1_.func_72872_a(EntityLiving.class, new AxisAlignedBB((double)lvt_7_1_ - lvt_5_1_, (double)lvt_8_1_ - lvt_5_1_, (double)lvt_9_1_ - lvt_5_1_, (double)lvt_7_1_ + lvt_5_1_, (double)lvt_8_1_ + lvt_5_1_, (double)lvt_9_1_ + lvt_5_1_));
      Iterator lvt_11_1_ = lvt_10_1_.iterator();

      while(lvt_11_1_.hasNext()) {
         EntityLiving lvt_12_1_ = (EntityLiving)lvt_11_1_.next();
         if (lvt_12_1_.func_110167_bD() && lvt_12_1_.func_110166_bE() == p_180618_0_) {
            if (lvt_3_1_ == null) {
               lvt_3_1_ = EntityLeashKnot.func_174862_a(p_180618_1_, p_180618_2_);
            }

            lvt_12_1_.func_110162_b(lvt_3_1_, true);
            lvt_4_1_ = true;
         }
      }

      return lvt_4_1_;
   }
}
