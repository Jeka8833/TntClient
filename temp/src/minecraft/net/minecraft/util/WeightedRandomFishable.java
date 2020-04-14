package net.minecraft.util;

import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class WeightedRandomFishable extends WeightedRandom.Item {
   private final ItemStack field_150711_b;
   private float field_150712_c;
   private boolean field_150710_d;

   public WeightedRandomFishable(ItemStack p_i45317_1_, int p_i45317_2_) {
      super(p_i45317_2_);
      this.field_150711_b = p_i45317_1_;
   }

   public ItemStack func_150708_a(Random p_150708_1_) {
      ItemStack lvt_2_1_ = this.field_150711_b.func_77946_l();
      if (this.field_150712_c > 0.0F) {
         int lvt_3_1_ = (int)(this.field_150712_c * (float)this.field_150711_b.func_77958_k());
         int lvt_4_1_ = lvt_2_1_.func_77958_k() - p_150708_1_.nextInt(p_150708_1_.nextInt(lvt_3_1_) + 1);
         if (lvt_4_1_ > lvt_3_1_) {
            lvt_4_1_ = lvt_3_1_;
         }

         if (lvt_4_1_ < 1) {
            lvt_4_1_ = 1;
         }

         lvt_2_1_.func_77964_b(lvt_4_1_);
      }

      if (this.field_150710_d) {
         EnchantmentHelper.func_77504_a(p_150708_1_, lvt_2_1_, 30);
      }

      return lvt_2_1_;
   }

   public WeightedRandomFishable func_150709_a(float p_150709_1_) {
      this.field_150712_c = p_150709_1_;
      return this;
   }

   public WeightedRandomFishable func_150707_a() {
      this.field_150710_d = true;
      return this;
   }
}
