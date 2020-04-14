package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.village.MerchantRecipe;

public class SlotMerchantResult extends Slot {
   private final InventoryMerchant field_75233_a;
   private EntityPlayer field_75232_b;
   private int field_75231_g;
   private final IMerchant field_75234_h;

   public SlotMerchantResult(EntityPlayer p_i1822_1_, IMerchant p_i1822_2_, InventoryMerchant p_i1822_3_, int p_i1822_4_, int p_i1822_5_, int p_i1822_6_) {
      super(p_i1822_3_, p_i1822_4_, p_i1822_5_, p_i1822_6_);
      this.field_75232_b = p_i1822_1_;
      this.field_75234_h = p_i1822_2_;
      this.field_75233_a = p_i1822_3_;
   }

   public boolean func_75214_a(ItemStack p_75214_1_) {
      return false;
   }

   public ItemStack func_75209_a(int p_75209_1_) {
      if (this.func_75216_d()) {
         this.field_75231_g += Math.min(p_75209_1_, this.func_75211_c().field_77994_a);
      }

      return super.func_75209_a(p_75209_1_);
   }

   protected void func_75210_a(ItemStack p_75210_1_, int p_75210_2_) {
      this.field_75231_g += p_75210_2_;
      this.func_75208_c(p_75210_1_);
   }

   protected void func_75208_c(ItemStack p_75208_1_) {
      p_75208_1_.func_77980_a(this.field_75232_b.field_70170_p, this.field_75232_b, this.field_75231_g);
      this.field_75231_g = 0;
   }

   public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
      this.func_75208_c(p_82870_2_);
      MerchantRecipe lvt_3_1_ = this.field_75233_a.func_70468_h();
      if (lvt_3_1_ != null) {
         ItemStack lvt_4_1_ = this.field_75233_a.func_70301_a(0);
         ItemStack lvt_5_1_ = this.field_75233_a.func_70301_a(1);
         if (this.func_75230_a(lvt_3_1_, lvt_4_1_, lvt_5_1_) || this.func_75230_a(lvt_3_1_, lvt_5_1_, lvt_4_1_)) {
            this.field_75234_h.func_70933_a(lvt_3_1_);
            p_82870_1_.func_71029_a(StatList.field_180206_G);
            if (lvt_4_1_ != null && lvt_4_1_.field_77994_a <= 0) {
               lvt_4_1_ = null;
            }

            if (lvt_5_1_ != null && lvt_5_1_.field_77994_a <= 0) {
               lvt_5_1_ = null;
            }

            this.field_75233_a.func_70299_a(0, lvt_4_1_);
            this.field_75233_a.func_70299_a(1, lvt_5_1_);
         }
      }

   }

   private boolean func_75230_a(MerchantRecipe p_75230_1_, ItemStack p_75230_2_, ItemStack p_75230_3_) {
      ItemStack lvt_4_1_ = p_75230_1_.func_77394_a();
      ItemStack lvt_5_1_ = p_75230_1_.func_77396_b();
      if (p_75230_2_ != null && p_75230_2_.func_77973_b() == lvt_4_1_.func_77973_b()) {
         if (lvt_5_1_ != null && p_75230_3_ != null && lvt_5_1_.func_77973_b() == p_75230_3_.func_77973_b()) {
            p_75230_2_.field_77994_a -= lvt_4_1_.field_77994_a;
            p_75230_3_.field_77994_a -= lvt_5_1_.field_77994_a;
            return true;
         }

         if (lvt_5_1_ == null && p_75230_3_ == null) {
            p_75230_2_.field_77994_a -= lvt_4_1_.field_77994_a;
            return true;
         }
      }

      return false;
   }
}
