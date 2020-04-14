package net.minecraft.enchantment;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public enum EnumEnchantmentType {
   ALL,
   ARMOR,
   ARMOR_FEET,
   ARMOR_LEGS,
   ARMOR_TORSO,
   ARMOR_HEAD,
   WEAPON,
   DIGGER,
   FISHING_ROD,
   BREAKABLE,
   BOW;

   public boolean func_77557_a(Item p_77557_1_) {
      if (this == ALL) {
         return true;
      } else if (this == BREAKABLE && p_77557_1_.func_77645_m()) {
         return true;
      } else if (p_77557_1_ instanceof ItemArmor) {
         if (this == ARMOR) {
            return true;
         } else {
            ItemArmor lvt_2_1_ = (ItemArmor)p_77557_1_;
            if (lvt_2_1_.field_77881_a == 0) {
               return this == ARMOR_HEAD;
            } else if (lvt_2_1_.field_77881_a == 2) {
               return this == ARMOR_LEGS;
            } else if (lvt_2_1_.field_77881_a == 1) {
               return this == ARMOR_TORSO;
            } else if (lvt_2_1_.field_77881_a == 3) {
               return this == ARMOR_FEET;
            } else {
               return false;
            }
         }
      } else if (p_77557_1_ instanceof ItemSword) {
         return this == WEAPON;
      } else if (p_77557_1_ instanceof ItemTool) {
         return this == DIGGER;
      } else if (p_77557_1_ instanceof ItemBow) {
         return this == BOW;
      } else if (p_77557_1_ instanceof ItemFishingRod) {
         return this == FISHING_ROD;
      } else {
         return false;
      }
   }
}
