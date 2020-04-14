package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesWeapons {
   private String[][] field_77585_a = new String[][]{{"X", "X", "#"}};
   private Object[][] field_77584_b;

   public RecipesWeapons() {
      this.field_77584_b = new Object[][]{{Blocks.field_150344_f, Blocks.field_150347_e, Items.field_151042_j, Items.field_151045_i, Items.field_151043_k}, {Items.field_151041_m, Items.field_151052_q, Items.field_151040_l, Items.field_151048_u, Items.field_151010_B}};
   }

   public void func_77583_a(CraftingManager p_77583_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_77584_b[0].length; ++lvt_2_1_) {
         Object lvt_3_1_ = this.field_77584_b[0][lvt_2_1_];

         for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_77584_b.length - 1; ++lvt_4_1_) {
            Item lvt_5_1_ = (Item)this.field_77584_b[lvt_4_1_ + 1][lvt_2_1_];
            p_77583_1_.func_92103_a(new ItemStack(lvt_5_1_), this.field_77585_a[lvt_4_1_], '#', Items.field_151055_y, 'X', lvt_3_1_);
         }
      }

      p_77583_1_.func_92103_a(new ItemStack(Items.field_151031_f, 1), " #X", "# X", " #X", 'X', Items.field_151007_F, '#', Items.field_151055_y);
      p_77583_1_.func_92103_a(new ItemStack(Items.field_151032_g, 4), "X", "#", "Y", 'Y', Items.field_151008_G, 'X', Items.field_151145_ak, '#', Items.field_151055_y);
   }
}
