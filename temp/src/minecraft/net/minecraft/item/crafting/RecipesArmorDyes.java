package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipesArmorDyes implements IRecipe {
   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      ItemStack lvt_3_1_ = null;
      List<ItemStack> lvt_4_1_ = Lists.newArrayList();

      for(int lvt_5_1_ = 0; lvt_5_1_ < p_77569_1_.func_70302_i_(); ++lvt_5_1_) {
         ItemStack lvt_6_1_ = p_77569_1_.func_70301_a(lvt_5_1_);
         if (lvt_6_1_ != null) {
            if (lvt_6_1_.func_77973_b() instanceof ItemArmor) {
               ItemArmor lvt_7_1_ = (ItemArmor)lvt_6_1_.func_77973_b();
               if (lvt_7_1_.func_82812_d() != ItemArmor.ArmorMaterial.LEATHER || lvt_3_1_ != null) {
                  return false;
               }

               lvt_3_1_ = lvt_6_1_;
            } else {
               if (lvt_6_1_.func_77973_b() != Items.field_151100_aR) {
                  return false;
               }

               lvt_4_1_.add(lvt_6_1_);
            }
         }
      }

      return lvt_3_1_ != null && !lvt_4_1_.isEmpty();
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      ItemStack lvt_2_1_ = null;
      int[] lvt_3_1_ = new int[3];
      int lvt_4_1_ = 0;
      int lvt_5_1_ = 0;
      ItemArmor lvt_6_1_ = null;

      int lvt_7_2_;
      int lvt_9_1_;
      float lvt_10_1_;
      float lvt_11_1_;
      int lvt_12_2_;
      for(lvt_7_2_ = 0; lvt_7_2_ < p_77572_1_.func_70302_i_(); ++lvt_7_2_) {
         ItemStack lvt_8_1_ = p_77572_1_.func_70301_a(lvt_7_2_);
         if (lvt_8_1_ != null) {
            if (lvt_8_1_.func_77973_b() instanceof ItemArmor) {
               lvt_6_1_ = (ItemArmor)lvt_8_1_.func_77973_b();
               if (lvt_6_1_.func_82812_d() != ItemArmor.ArmorMaterial.LEATHER || lvt_2_1_ != null) {
                  return null;
               }

               lvt_2_1_ = lvt_8_1_.func_77946_l();
               lvt_2_1_.field_77994_a = 1;
               if (lvt_6_1_.func_82816_b_(lvt_8_1_)) {
                  lvt_9_1_ = lvt_6_1_.func_82814_b(lvt_2_1_);
                  lvt_10_1_ = (float)(lvt_9_1_ >> 16 & 255) / 255.0F;
                  lvt_11_1_ = (float)(lvt_9_1_ >> 8 & 255) / 255.0F;
                  float lvt_12_1_ = (float)(lvt_9_1_ & 255) / 255.0F;
                  lvt_4_1_ = (int)((float)lvt_4_1_ + Math.max(lvt_10_1_, Math.max(lvt_11_1_, lvt_12_1_)) * 255.0F);
                  lvt_3_1_[0] = (int)((float)lvt_3_1_[0] + lvt_10_1_ * 255.0F);
                  lvt_3_1_[1] = (int)((float)lvt_3_1_[1] + lvt_11_1_ * 255.0F);
                  lvt_3_1_[2] = (int)((float)lvt_3_1_[2] + lvt_12_1_ * 255.0F);
                  ++lvt_5_1_;
               }
            } else {
               if (lvt_8_1_.func_77973_b() != Items.field_151100_aR) {
                  return null;
               }

               float[] lvt_9_2_ = EntitySheep.func_175513_a(EnumDyeColor.func_176766_a(lvt_8_1_.func_77960_j()));
               int lvt_10_2_ = (int)(lvt_9_2_[0] * 255.0F);
               int lvt_11_2_ = (int)(lvt_9_2_[1] * 255.0F);
               lvt_12_2_ = (int)(lvt_9_2_[2] * 255.0F);
               lvt_4_1_ += Math.max(lvt_10_2_, Math.max(lvt_11_2_, lvt_12_2_));
               lvt_3_1_[0] += lvt_10_2_;
               lvt_3_1_[1] += lvt_11_2_;
               lvt_3_1_[2] += lvt_12_2_;
               ++lvt_5_1_;
            }
         }
      }

      if (lvt_6_1_ == null) {
         return null;
      } else {
         lvt_7_2_ = lvt_3_1_[0] / lvt_5_1_;
         int lvt_8_2_ = lvt_3_1_[1] / lvt_5_1_;
         lvt_9_1_ = lvt_3_1_[2] / lvt_5_1_;
         lvt_10_1_ = (float)lvt_4_1_ / (float)lvt_5_1_;
         lvt_11_1_ = (float)Math.max(lvt_7_2_, Math.max(lvt_8_2_, lvt_9_1_));
         lvt_7_2_ = (int)((float)lvt_7_2_ * lvt_10_1_ / lvt_11_1_);
         lvt_8_2_ = (int)((float)lvt_8_2_ * lvt_10_1_ / lvt_11_1_);
         lvt_9_1_ = (int)((float)lvt_9_1_ * lvt_10_1_ / lvt_11_1_);
         lvt_12_2_ = (lvt_7_2_ << 8) + lvt_8_2_;
         lvt_12_2_ = (lvt_12_2_ << 8) + lvt_9_1_;
         lvt_6_1_.func_82813_b(lvt_2_1_, lvt_12_2_);
         return lvt_2_1_;
      }
   }

   public int func_77570_a() {
      return 10;
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
