package net.minecraft.inventory;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ContainerHorseInventory extends Container {
   private IInventory field_111243_a;
   private EntityHorse field_111242_f;

   public ContainerHorseInventory(IInventory p_i45791_1_, IInventory p_i45791_2_, final EntityHorse p_i45791_3_, EntityPlayer p_i45791_4_) {
      this.field_111243_a = p_i45791_2_;
      this.field_111242_f = p_i45791_3_;
      int lvt_5_1_ = 3;
      p_i45791_2_.func_174889_b(p_i45791_4_);
      int lvt_6_1_ = (lvt_5_1_ - 4) * 18;
      this.func_75146_a(new Slot(p_i45791_2_, 0, 8, 18) {
         public boolean func_75214_a(ItemStack p_75214_1_) {
            return super.func_75214_a(p_75214_1_) && p_75214_1_.func_77973_b() == Items.field_151141_av && !this.func_75216_d();
         }
      });
      this.func_75146_a(new Slot(p_i45791_2_, 1, 8, 36) {
         public boolean func_75214_a(ItemStack p_75214_1_) {
            return super.func_75214_a(p_75214_1_) && p_i45791_3_.func_110259_cr() && EntityHorse.func_146085_a(p_75214_1_.func_77973_b());
         }

         public boolean func_111238_b() {
            return p_i45791_3_.func_110259_cr();
         }
      });
      int lvt_7_3_;
      int lvt_8_2_;
      if (p_i45791_3_.func_110261_ca()) {
         for(lvt_7_3_ = 0; lvt_7_3_ < lvt_5_1_; ++lvt_7_3_) {
            for(lvt_8_2_ = 0; lvt_8_2_ < 5; ++lvt_8_2_) {
               this.func_75146_a(new Slot(p_i45791_2_, 2 + lvt_8_2_ + lvt_7_3_ * 5, 80 + lvt_8_2_ * 18, 18 + lvt_7_3_ * 18));
            }
         }
      }

      for(lvt_7_3_ = 0; lvt_7_3_ < 3; ++lvt_7_3_) {
         for(lvt_8_2_ = 0; lvt_8_2_ < 9; ++lvt_8_2_) {
            this.func_75146_a(new Slot(p_i45791_1_, lvt_8_2_ + lvt_7_3_ * 9 + 9, 8 + lvt_8_2_ * 18, 102 + lvt_7_3_ * 18 + lvt_6_1_));
         }
      }

      for(lvt_7_3_ = 0; lvt_7_3_ < 9; ++lvt_7_3_) {
         this.func_75146_a(new Slot(p_i45791_1_, lvt_7_3_, 8 + lvt_7_3_ * 18, 160 + lvt_6_1_));
      }

   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return this.field_111243_a.func_70300_a(p_75145_1_) && this.field_111242_f.func_70089_S() && this.field_111242_f.func_70032_d(p_75145_1_) < 8.0F;
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if (p_82846_2_ < this.field_111243_a.func_70302_i_()) {
            if (!this.func_75135_a(lvt_5_1_, this.field_111243_a.func_70302_i_(), this.field_75151_b.size(), true)) {
               return null;
            }
         } else if (this.func_75139_a(1).func_75214_a(lvt_5_1_) && !this.func_75139_a(1).func_75216_d()) {
            if (!this.func_75135_a(lvt_5_1_, 1, 2, false)) {
               return null;
            }
         } else if (this.func_75139_a(0).func_75214_a(lvt_5_1_)) {
            if (!this.func_75135_a(lvt_5_1_, 0, 1, false)) {
               return null;
            }
         } else if (this.field_111243_a.func_70302_i_() <= 2 || !this.func_75135_a(lvt_5_1_, 2, this.field_111243_a.func_70302_i_(), false)) {
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
      this.field_111243_a.func_174886_c(p_75134_1_);
   }
}
