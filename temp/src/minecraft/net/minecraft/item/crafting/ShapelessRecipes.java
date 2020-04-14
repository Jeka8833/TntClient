package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShapelessRecipes implements IRecipe {
   private final ItemStack field_77580_a;
   private final List<ItemStack> field_77579_b;

   public ShapelessRecipes(ItemStack p_i1918_1_, List<ItemStack> p_i1918_2_) {
      this.field_77580_a = p_i1918_1_;
      this.field_77579_b = p_i1918_2_;
   }

   public ItemStack func_77571_b() {
      return this.field_77580_a;
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
      List<ItemStack> lvt_3_1_ = Lists.newArrayList((Iterable)this.field_77579_b);

      for(int lvt_4_1_ = 0; lvt_4_1_ < p_77569_1_.func_174923_h(); ++lvt_4_1_) {
         for(int lvt_5_1_ = 0; lvt_5_1_ < p_77569_1_.func_174922_i(); ++lvt_5_1_) {
            ItemStack lvt_6_1_ = p_77569_1_.func_70463_b(lvt_5_1_, lvt_4_1_);
            if (lvt_6_1_ != null) {
               boolean lvt_7_1_ = false;
               Iterator lvt_8_1_ = lvt_3_1_.iterator();

               while(lvt_8_1_.hasNext()) {
                  ItemStack lvt_9_1_ = (ItemStack)lvt_8_1_.next();
                  if (lvt_6_1_.func_77973_b() == lvt_9_1_.func_77973_b() && (lvt_9_1_.func_77960_j() == 32767 || lvt_6_1_.func_77960_j() == lvt_9_1_.func_77960_j())) {
                     lvt_7_1_ = true;
                     lvt_3_1_.remove(lvt_9_1_);
                     break;
                  }
               }

               if (!lvt_7_1_) {
                  return false;
               }
            }
         }
      }

      return lvt_3_1_.isEmpty();
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      return this.field_77580_a.func_77946_l();
   }

   public int func_77570_a() {
      return this.field_77579_b.size();
   }
}
