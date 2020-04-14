package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ContainerDispenser extends Container {
   private IInventory field_178146_a;

   public ContainerDispenser(IInventory p_i45799_1_, IInventory p_i45799_2_) {
      this.field_178146_a = p_i45799_2_;

      int lvt_3_3_;
      int lvt_4_2_;
      for(lvt_3_3_ = 0; lvt_3_3_ < 3; ++lvt_3_3_) {
         for(lvt_4_2_ = 0; lvt_4_2_ < 3; ++lvt_4_2_) {
            this.func_75146_a(new Slot(p_i45799_2_, lvt_4_2_ + lvt_3_3_ * 3, 62 + lvt_4_2_ * 18, 17 + lvt_3_3_ * 18));
         }
      }

      for(lvt_3_3_ = 0; lvt_3_3_ < 3; ++lvt_3_3_) {
         for(lvt_4_2_ = 0; lvt_4_2_ < 9; ++lvt_4_2_) {
            this.func_75146_a(new Slot(p_i45799_1_, lvt_4_2_ + lvt_3_3_ * 9 + 9, 8 + lvt_4_2_ * 18, 84 + lvt_3_3_ * 18));
         }
      }

      for(lvt_3_3_ = 0; lvt_3_3_ < 9; ++lvt_3_3_) {
         this.func_75146_a(new Slot(p_i45799_1_, lvt_3_3_, 8 + lvt_3_3_ * 18, 142));
      }

   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return this.field_178146_a.func_70300_a(p_75145_1_);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if (p_82846_2_ < 9) {
            if (!this.func_75135_a(lvt_5_1_, 9, 45, true)) {
               return null;
            }
         } else if (!this.func_75135_a(lvt_5_1_, 0, 9, false)) {
            return null;
         }

         if (lvt_5_1_.field_77994_a == 0) {
            lvt_4_1_.func_75215_d((ItemStack)null);
         } else {
            lvt_4_1_.func_75218_e();
         }

         if (lvt_5_1_.field_77994_a == lvt_3_1_.field_77994_a) {
            return null;
         }

         lvt_4_1_.func_82870_a(p_82846_1_, lvt_5_1_);
      }

      return lvt_3_1_;
   }
}
