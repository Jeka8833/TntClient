package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipeRepairItem implements IRecipe {
   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      List<ItemStack> lvt_3_1_ = Lists.newArrayList();

      for(int lvt_4_1_ = 0; lvt_4_1_ < p_77569_1_.func_70302_i_(); ++lvt_4_1_) {
         ItemStack lvt_5_1_ = p_77569_1_.func_70301_a(lvt_4_1_);
         if (lvt_5_1_ != null) {
            lvt_3_1_.add(lvt_5_1_);
            if (lvt_3_1_.size() > 1) {
               ItemStack lvt_6_1_ = (ItemStack)lvt_3_1_.get(0);
               if (lvt_5_1_.func_77973_b() != lvt_6_1_.func_77973_b() || lvt_6_1_.field_77994_a != 1 || lvt_5_1_.field_77994_a != 1 || !lvt_6_1_.func_77973_b().func_77645_m()) {
                  return false;
               }
            }
         }
      }

      return lvt_3_1_.size() == 2;
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      List<ItemStack> lvt_2_1_ = Lists.newArrayList();

      ItemStack lvt_4_1_;
      for(int lvt_3_1_ = 0; lvt_3_1_ < p_77572_1_.func_70302_i_(); ++lvt_3_1_) {
         lvt_4_1_ = p_77572_1_.func_70301_a(lvt_3_1_);
         if (lvt_4_1_ != null) {
            lvt_2_1_.add(lvt_4_1_);
            if (lvt_2_1_.size() > 1) {
               ItemStack lvt_5_1_ = (ItemStack)lvt_2_1_.get(0);
               if (lvt_4_1_.func_77973_b() != lvt_5_1_.func_77973_b() || lvt_5_1_.field_77994_a != 1 || lvt_4_1_.field_77994_a != 1 || !lvt_5_1_.func_77973_b().func_77645_m()) {
                  return null;
               }
            }
         }
      }

      if (lvt_2_1_.size() == 2) {
         ItemStack lvt_3_2_ = (ItemStack)lvt_2_1_.get(0);
         lvt_4_1_ = (ItemStack)lvt_2_1_.get(1);
         if (lvt_3_2_.func_77973_b() == lvt_4_1_.func_77973_b() && lvt_3_2_.field_77994_a == 1 && lvt_4_1_.field_77994_a == 1 && lvt_3_2_.func_77973_b().func_77645_m()) {
            Item lvt_5_2_ = lvt_3_2_.func_77973_b();
            int lvt_6_1_ = lvt_5_2_.func_77612_l() - lvt_3_2_.func_77952_i();
            int lvt_7_1_ = lvt_5_2_.func_77612_l() - lvt_4_1_.func_77952_i();
            int lvt_8_1_ = lvt_6_1_ + lvt_7_1_ + lvt_5_2_.func_77612_l() * 5 / 100;
            int lvt_9_1_ = lvt_5_2_.func_77612_l() - lvt_8_1_;
            if (lvt_9_1_ < 0) {
               lvt_9_1_ = 0;
            }

            return new ItemStack(lvt_3_2_.func_77973_b(), 1, lvt_9_1_);
         }
      }

      return null;
   }

   public int func_77570_a() {
      return 4;
   }

   public ItemStack func_77571_b() {
      return null;
   }

   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
      ItemStack[] lvt_2_1_ = new ItemStack[p_179532_1_.func_70302_i_()];

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.length; ++lvt_3_1_) {
         ItemStack lvt_4_1_ = p_179532_1_.func_70301_a(lvt_3_1_);
         if (lvt_4_1_ != null && lvt_4_1_.func_77973_b().func_77634_r()) {
            lvt_2_1_[lvt_3_1_] = new ItemStack(lvt_4_1_.func_77973_b().func_77668_q());
         }
      }

      return lvt_2_1_;
   }
}
