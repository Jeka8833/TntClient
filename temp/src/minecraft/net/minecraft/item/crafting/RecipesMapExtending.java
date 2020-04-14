package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class RecipesMapExtending extends ShapedRecipes {
   public RecipesMapExtending() {
      super(3, 3, new ItemStack[]{new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151098_aY, 0, 32767), new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151121_aF)}, new ItemStack(Items.field_151148_bJ, 0, 0));
   }

   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      if (!super.func_77569_a(p_77569_1_, p_77569_2_)) {
         return false;
      } else {
         ItemStack lvt_3_1_ = null;

         for(int lvt_4_1_ = 0; lvt_4_1_ < p_77569_1_.func_70302_i_() && lvt_3_1_ == null; ++lvt_4_1_) {
            ItemStack lvt_5_1_ = p_77569_1_.func_70301_a(lvt_4_1_);
            if (lvt_5_1_ != null && lvt_5_1_.func_77973_b() == Items.field_151098_aY) {
               lvt_3_1_ = lvt_5_1_;
            }
         }

         if (lvt_3_1_ == null) {
            return false;
         } else {
            MapData lvt_4_2_ = Items.field_151098_aY.func_77873_a(lvt_3_1_, p_77569_2_);
            if (lvt_4_2_ == null) {
               return false;
            } else {
               return lvt_4_2_.field_76197_d < 4;
            }
         }
      }
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      ItemStack lvt_2_1_ = null;

      for(int lvt_3_1_ = 0; lvt_3_1_ < p_77572_1_.func_70302_i_() && lvt_2_1_ == null; ++lvt_3_1_) {
         ItemStack lvt_4_1_ = p_77572_1_.func_70301_a(lvt_3_1_);
         if (lvt_4_1_ != null && lvt_4_1_.func_77973_b() == Items.field_151098_aY) {
            lvt_2_1_ = lvt_4_1_;
         }
      }

      lvt_2_1_ = lvt_2_1_.func_77946_l();
      lvt_2_1_.field_77994_a = 1;
      if (lvt_2_1_.func_77978_p() == null) {
         lvt_2_1_.func_77982_d(new NBTTagCompound());
      }

      lvt_2_1_.func_77978_p().func_74757_a("map_is_scaling", true);
      return lvt_2_1_;
   }
}
