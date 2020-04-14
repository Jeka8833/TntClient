package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesArmor {
   private String[][] field_77611_a = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
   private Item[][] field_77610_b;

   public RecipesArmor() {
      this.field_77610_b = new Item[][]{{Items.field_151116_aA, Items.field_151042_j, Items.field_151045_i, Items.field_151043_k}, {Items.field_151024_Q, Items.field_151028_Y, Items.field_151161_ac, Items.field_151169_ag}, {Items.field_151027_R, Items.field_151030_Z, Items.field_151163_ad, Items.field_151171_ah}, {Items.field_151026_S, Items.field_151165_aa, Items.field_151173_ae, Items.field_151149_ai}, {Items.field_151021_T, Items.field_151167_ab, Items.field_151175_af, Items.field_151151_aj}};
   }

   public void func_77609_a(CraftingManager p_77609_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_77610_b[0].length; ++lvt_2_1_) {
         Item lvt_3_1_ = this.field_77610_b[0][lvt_2_1_];

         for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_77610_b.length - 1; ++lvt_4_1_) {
            Item lvt_5_1_ = this.field_77610_b[lvt_4_1_ + 1][lvt_2_1_];
            p_77609_1_.func_92103_a(new ItemStack(lvt_5_1_), this.field_77611_a[lvt_4_1_], 'X', lvt_3_1_);
         }
      }

   }
}
