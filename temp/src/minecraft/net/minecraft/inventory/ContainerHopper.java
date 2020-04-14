package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerHopper extends Container {
   private final IInventory field_94538_a;

   public ContainerHopper(InventoryPlayer p_i45792_1_, IInventory p_i45792_2_, EntityPlayer p_i45792_3_) {
      this.field_94538_a = p_i45792_2_;
      p_i45792_2_.func_174889_b(p_i45792_3_);
      int lvt_4_1_ = 51;

      int lvt_5_3_;
      for(lvt_5_3_ = 0; lvt_5_3_ < p_i45792_2_.func_70302_i_(); ++lvt_5_3_) {
         this.func_75146_a(new Slot(p_i45792_2_, lvt_5_3_, 44 + lvt_5_3_ * 18, 20));
      }

      for(lvt_5_3_ = 0; lvt_5_3_ < 3; ++lvt_5_3_) {
         for(int lvt_6_1_ = 0; lvt_6_1_ < 9; ++lvt_6_1_) {
            this.func_75146_a(new Slot(p_i45792_1_, lvt_6_1_ + lvt_5_3_ * 9 + 9, 8 + lvt_6_1_ * 18, lvt_5_3_ * 18 + lvt_4_1_));
         }
      }

      for(lvt_5_3_ = 0; lvt_5_3_ < 9; ++lvt_5_3_) {
         this.func_75146_a(new Slot(p_i45792_1_, lvt_5_3_, 8 + lvt_5_3_ * 18, 58 + lvt_4_1_));
      }

   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return this.field_94538_a.func_70300_a(p_75145_1_);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if (p_82846_2_ < this.field_94538_a.func_70302_i_()) {
            if (!this.func_75135_a(lvt_5_1_, this.field_94538_a.func_70302_i_(), this.field_75151_b.size(), true)) {
               return null;
            }
         } else if (!this.func_75135_a(lvt_5_1_, 0, this.field_94538_a.func_70302_i_(), false)) {
            return null;
         }

         if (lvt_5_1_.field_77994_a == 0) {
            lvt_4_1_.func_75215_d((ItemStack)null);
         } else {
            lvt_4_1_.func_75218_e();
         }
      }

      return lvt_3_1_;
   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      super.func_75134_a(p_75134_1_);
      this.field_94538_a.func_174886_c(p_75134_1_);
   }
}
