package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.stats.AchievementList;

public class SlotCrafting extends Slot {
   private final InventoryCrafting field_75239_a;
   private final EntityPlayer field_75238_b;
   private int field_75237_g;

   public SlotCrafting(EntityPlayer p_i45790_1_, InventoryCrafting p_i45790_2_, IInventory p_i45790_3_, int p_i45790_4_, int p_i45790_5_, int p_i45790_6_) {
      super(p_i45790_3_, p_i45790_4_, p_i45790_5_, p_i45790_6_);
      this.field_75238_b = p_i45790_1_;
      this.field_75239_a = p_i45790_2_;
   }

   public boolean func_75214_a(ItemStack p_75214_1_) {
      return false;
   }

   public ItemStack func_75209_a(int p_75209_1_) {
      if (this.func_75216_d()) {
         this.field_75237_g += Math.min(p_75209_1_, this.func_75211_c().field_77994_a);
      }

      return super.func_75209_a(p_75209_1_);
   }

   protected void func_75210_a(ItemStack p_75210_1_, int p_75210_2_) {
      this.field_75237_g += p_75210_2_;
      this.func_75208_c(p_75210_1_);
   }

   protected void func_75208_c(ItemStack p_75208_1_) {
      if (this.field_75237_g > 0) {
         p_75208_1_.func_77980_a(this.field_75238_b.field_70170_p, this.field_75238_b, this.field_75237_g);
      }

      this.field_75237_g = 0;
      if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150462_ai)) {
         this.field_75238_b.func_71029_a(AchievementList.field_76017_h);
      }

      if (p_75208_1_.func_77973_b() instanceof ItemPickaxe) {
         this.field_75238_b.func_71029_a(AchievementList.field_76018_i);
      }

      if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150460_al)) {
         this.field_75238_b.func_71029_a(AchievementList.field_76015_j);
      }

      if (p_75208_1_.func_77973_b() instanceof ItemHoe) {
         this.field_75238_b.func_71029_a(AchievementList.field_76013_l);
      }

      if (p_75208_1_.func_77973_b() == Items.field_151025_P) {
         this.field_75238_b.func_71029_a(AchievementList.field_76014_m);
      }

      if (p_75208_1_.func_77973_b() == Items.field_151105_aU) {
         this.field_75238_b.func_71029_a(AchievementList.field_76011_n);
      }

      if (p_75208_1_.func_77973_b() instanceof ItemPickaxe && ((ItemPickaxe)p_75208_1_.func_77973_b()).func_150913_i() != Item.ToolMaterial.WOOD) {
         this.field_75238_b.func_71029_a(AchievementList.field_76012_o);
      }

      if (p_75208_1_.func_77973_b() instanceof ItemSword) {
         this.field_75238_b.func_71029_a(AchievementList.field_76024_r);
      }

      if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150381_bn)) {
         this.field_75238_b.func_71029_a(AchievementList.field_75998_D);
      }

      if (p_75208_1_.func_77973_b() == Item.func_150898_a(Blocks.field_150342_X)) {
         this.field_75238_b.func_71029_a(AchievementList.field_76000_F);
      }

      if (p_75208_1_.func_77973_b() == Items.field_151153_ao && p_75208_1_.func_77960_j() == 1) {
         this.field_75238_b.func_71029_a(AchievementList.field_180219_M);
      }

   }

   public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
      this.func_75208_c(p_82870_2_);
      ItemStack[] lvt_3_1_ = CraftingManager.func_77594_a().func_180303_b(this.field_75239_a, p_82870_1_.field_70170_p);

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_.length; ++lvt_4_1_) {
         ItemStack lvt_5_1_ = this.field_75239_a.func_70301_a(lvt_4_1_);
         ItemStack lvt_6_1_ = lvt_3_1_[lvt_4_1_];
         if (lvt_5_1_ != null) {
            this.field_75239_a.func_70298_a(lvt_4_1_, 1);
         }

         if (lvt_6_1_ != null) {
            if (this.field_75239_a.func_70301_a(lvt_4_1_) == null) {
               this.field_75239_a.func_70299_a(lvt_4_1_, lvt_6_1_);
            } else if (!this.field_75238_b.field_71071_by.func_70441_a(lvt_6_1_)) {
               this.field_75238_b.func_71019_a(lvt_6_1_, false);
            }
         }
      }

   }
}
