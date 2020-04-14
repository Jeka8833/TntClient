package net.minecraft.util;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;

public class WeightedRandomChestContent extends WeightedRandom.Item {
   private ItemStack field_76297_b;
   private int field_76295_d;
   private int field_76296_e;

   public WeightedRandomChestContent(Item p_i45311_1_, int p_i45311_2_, int p_i45311_3_, int p_i45311_4_, int p_i45311_5_) {
      super(p_i45311_5_);
      this.field_76297_b = new ItemStack(p_i45311_1_, 1, p_i45311_2_);
      this.field_76295_d = p_i45311_3_;
      this.field_76296_e = p_i45311_4_;
   }

   public WeightedRandomChestContent(ItemStack p_i1558_1_, int p_i1558_2_, int p_i1558_3_, int p_i1558_4_) {
      super(p_i1558_4_);
      this.field_76297_b = p_i1558_1_;
      this.field_76295_d = p_i1558_2_;
      this.field_76296_e = p_i1558_3_;
   }

   public static void func_177630_a(Random p_177630_0_, List<WeightedRandomChestContent> p_177630_1_, IInventory p_177630_2_, int p_177630_3_) {
      for(int lvt_4_1_ = 0; lvt_4_1_ < p_177630_3_; ++lvt_4_1_) {
         WeightedRandomChestContent lvt_5_1_ = (WeightedRandomChestContent)WeightedRandom.func_76271_a(p_177630_0_, p_177630_1_);
         int lvt_6_1_ = lvt_5_1_.field_76295_d + p_177630_0_.nextInt(lvt_5_1_.field_76296_e - lvt_5_1_.field_76295_d + 1);
         if (lvt_5_1_.field_76297_b.func_77976_d() >= lvt_6_1_) {
            ItemStack lvt_7_1_ = lvt_5_1_.field_76297_b.func_77946_l();
            lvt_7_1_.field_77994_a = lvt_6_1_;
            p_177630_2_.func_70299_a(p_177630_0_.nextInt(p_177630_2_.func_70302_i_()), lvt_7_1_);
         } else {
            for(int lvt_7_2_ = 0; lvt_7_2_ < lvt_6_1_; ++lvt_7_2_) {
               ItemStack lvt_8_1_ = lvt_5_1_.field_76297_b.func_77946_l();
               lvt_8_1_.field_77994_a = 1;
               p_177630_2_.func_70299_a(p_177630_0_.nextInt(p_177630_2_.func_70302_i_()), lvt_8_1_);
            }
         }
      }

   }

   public static void func_177631_a(Random p_177631_0_, List<WeightedRandomChestContent> p_177631_1_, TileEntityDispenser p_177631_2_, int p_177631_3_) {
      for(int lvt_4_1_ = 0; lvt_4_1_ < p_177631_3_; ++lvt_4_1_) {
         WeightedRandomChestContent lvt_5_1_ = (WeightedRandomChestContent)WeightedRandom.func_76271_a(p_177631_0_, p_177631_1_);
         int lvt_6_1_ = lvt_5_1_.field_76295_d + p_177631_0_.nextInt(lvt_5_1_.field_76296_e - lvt_5_1_.field_76295_d + 1);
         if (lvt_5_1_.field_76297_b.func_77976_d() >= lvt_6_1_) {
            ItemStack lvt_7_1_ = lvt_5_1_.field_76297_b.func_77946_l();
            lvt_7_1_.field_77994_a = lvt_6_1_;
            p_177631_2_.func_70299_a(p_177631_0_.nextInt(p_177631_2_.func_70302_i_()), lvt_7_1_);
         } else {
            for(int lvt_7_2_ = 0; lvt_7_2_ < lvt_6_1_; ++lvt_7_2_) {
               ItemStack lvt_8_1_ = lvt_5_1_.field_76297_b.func_77946_l();
               lvt_8_1_.field_77994_a = 1;
               p_177631_2_.func_70299_a(p_177631_0_.nextInt(p_177631_2_.func_70302_i_()), lvt_8_1_);
            }
         }
      }

   }

   public static List<WeightedRandomChestContent> func_177629_a(List<WeightedRandomChestContent> p_177629_0_, WeightedRandomChestContent... p_177629_1_) {
      List<WeightedRandomChestContent> lvt_2_1_ = Lists.newArrayList((Iterable)p_177629_0_);
      Collections.addAll(lvt_2_1_, p_177629_1_);
      return lvt_2_1_;
   }
}
