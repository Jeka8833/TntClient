package net.minecraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ShapedRecipes implements IRecipe {
   private final int field_77576_b;
   private final int field_77577_c;
   private final ItemStack[] field_77574_d;
   private final ItemStack field_77575_e;
   private boolean field_92101_f;

   public ShapedRecipes(int p_i1917_1_, int p_i1917_2_, ItemStack[] p_i1917_3_, ItemStack p_i1917_4_) {
      this.field_77576_b = p_i1917_1_;
      this.field_77577_c = p_i1917_2_;
      this.field_77574_d = p_i1917_3_;
      this.field_77575_e = p_i1917_4_;
   }

   public ItemStack func_77571_b() {
      return this.field_77575_e;
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

   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      for(int lvt_3_1_ = 0; lvt_3_1_ <= 3 - this.field_77576_b; ++lvt_3_1_) {
         for(int lvt_4_1_ = 0; lvt_4_1_ <= 3 - this.field_77577_c; ++lvt_4_1_) {
            if (this.func_77573_a(p_77569_1_, lvt_3_1_, lvt_4_1_, true)) {
               return true;
            }

            if (this.func_77573_a(p_77569_1_, lvt_3_1_, lvt_4_1_, false)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean func_77573_a(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
      for(int lvt_5_1_ = 0; lvt_5_1_ < 3; ++lvt_5_1_) {
         for(int lvt_6_1_ = 0; lvt_6_1_ < 3; ++lvt_6_1_) {
            int lvt_7_1_ = lvt_5_1_ - p_77573_2_;
            int lvt_8_1_ = lvt_6_1_ - p_77573_3_;
            ItemStack lvt_9_1_ = null;
            if (lvt_7_1_ >= 0 && lvt_8_1_ >= 0 && lvt_7_1_ < this.field_77576_b && lvt_8_1_ < this.field_77577_c) {
               if (p_77573_4_) {
                  lvt_9_1_ = this.field_77574_d[this.field_77576_b - lvt_7_1_ - 1 + lvt_8_1_ * this.field_77576_b];
               } else {
                  lvt_9_1_ = this.field_77574_d[lvt_7_1_ + lvt_8_1_ * this.field_77576_b];
               }
            }

            ItemStack lvt_10_1_ = p_77573_1_.func_70463_b(lvt_5_1_, lvt_6_1_);
            if (lvt_10_1_ != null || lvt_9_1_ != null) {
               if (lvt_10_1_ == null && lvt_9_1_ != null || lvt_10_1_ != null && lvt_9_1_ == null) {
                  return false;
               }

               if (lvt_9_1_.func_77973_b() != lvt_10_1_.func_77973_b()) {
                  return false;
               }

               if (lvt_9_1_.func_77960_j() != 32767 && lvt_9_1_.func_77960_j() != lvt_10_1_.func_77960_j()) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      ItemStack lvt_2_1_ = this.func_77571_b().func_77946_l();
      if (this.field_92101_f) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < p_77572_1_.func_70302_i_(); ++lvt_3_1_) {
            ItemStack lvt_4_1_ = p_77572_1_.func_70301_a(lvt_3_1_);
            if (lvt_4_1_ != null && lvt_4_1_.func_77942_o()) {
               lvt_2_1_.func_77982_d((NBTTagCompound)lvt_4_1_.func_77978_p().func_74737_b());
            }
         }
      }

      return lvt_2_1_;
   }

   public int func_77570_a() {
      return this.field_77576_b * this.field_77577_c;
   }
}
