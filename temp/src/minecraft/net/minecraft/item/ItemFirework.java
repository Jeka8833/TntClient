package net.minecraft.item;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFirework extends Item {
   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if (!p_180614_3_.field_72995_K) {
         EntityFireworkRocket lvt_9_1_ = new EntityFireworkRocket(p_180614_3_, (double)((float)p_180614_4_.func_177958_n() + p_180614_6_), (double)((float)p_180614_4_.func_177956_o() + p_180614_7_), (double)((float)p_180614_4_.func_177952_p() + p_180614_8_), p_180614_1_);
         p_180614_3_.func_72838_d(lvt_9_1_);
         if (!p_180614_2_.field_71075_bZ.field_75098_d) {
            --p_180614_1_.field_77994_a;
         }

         return true;
      } else {
         return false;
      }
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      if (p_77624_1_.func_77942_o()) {
         NBTTagCompound lvt_5_1_ = p_77624_1_.func_77978_p().func_74775_l("Fireworks");
         if (lvt_5_1_ != null) {
            if (lvt_5_1_.func_150297_b("Flight", 99)) {
               p_77624_3_.add(StatCollector.func_74838_a("item.fireworks.flight") + " " + lvt_5_1_.func_74771_c("Flight"));
            }

            NBTTagList lvt_6_1_ = lvt_5_1_.func_150295_c("Explosions", 10);
            if (lvt_6_1_ != null && lvt_6_1_.func_74745_c() > 0) {
               for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_.func_74745_c(); ++lvt_7_1_) {
                  NBTTagCompound lvt_8_1_ = lvt_6_1_.func_150305_b(lvt_7_1_);
                  List<String> lvt_9_1_ = Lists.newArrayList();
                  ItemFireworkCharge.func_150902_a(lvt_8_1_, lvt_9_1_);
                  if (lvt_9_1_.size() > 0) {
                     for(int lvt_10_1_ = 1; lvt_10_1_ < lvt_9_1_.size(); ++lvt_10_1_) {
                        lvt_9_1_.set(lvt_10_1_, "  " + (String)lvt_9_1_.get(lvt_10_1_));
                     }

                     p_77624_3_.addAll(lvt_9_1_);
                  }
               }
            }

         }
      }
   }
}
