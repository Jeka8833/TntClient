package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerPlayer extends Container {
   public InventoryCrafting field_75181_e = new InventoryCrafting(this, 2, 2);
   public IInventory field_75179_f = new InventoryCraftResult();
   public boolean field_75180_g;
   private final EntityPlayer field_82862_h;

   public ContainerPlayer(InventoryPlayer p_i1819_1_, boolean p_i1819_2_, EntityPlayer p_i1819_3_) {
      this.field_75180_g = p_i1819_2_;
      this.field_82862_h = p_i1819_3_;
      this.func_75146_a(new SlotCrafting(p_i1819_1_.field_70458_d, this.field_75181_e, this.field_75179_f, 0, 144, 36));

      final int lvt_4_4_;
      int lvt_5_3_;
      for(lvt_4_4_ = 0; lvt_4_4_ < 2; ++lvt_4_4_) {
         for(lvt_5_3_ = 0; lvt_5_3_ < 2; ++lvt_5_3_) {
            this.func_75146_a(new Slot(this.field_75181_e, lvt_5_3_ + lvt_4_4_ * 2, 88 + lvt_5_3_ * 18, 26 + lvt_4_4_ * 18));
         }
      }

      for(lvt_4_4_ = 0; lvt_4_4_ < 4; ++lvt_4_4_) {
         this.func_75146_a(new Slot(p_i1819_1_, p_i1819_1_.func_70302_i_() - 1 - lvt_4_4_, 8, 8 + lvt_4_4_ * 18) {
            public int func_75219_a() {
               return 1;
            }

            public boolean func_75214_a(ItemStack p_75214_1_) {
               if (p_75214_1_ == null) {
                  return false;
               } else if (p_75214_1_.func_77973_b() instanceof ItemArmor) {
                  return ((ItemArmor)p_75214_1_.func_77973_b()).field_77881_a == lvt_4_4_;
               } else if (p_75214_1_.func_77973_b() != Item.func_150898_a(Blocks.field_150423_aK) && p_75214_1_.func_77973_b() != Items.field_151144_bL) {
                  return false;
               } else {
                  return lvt_4_4_ == 0;
               }
            }

            public String func_178171_c() {
               return ItemArmor.field_94603_a[lvt_4_4_];
            }
         });
      }

      for(lvt_4_4_ = 0; lvt_4_4_ < 3; ++lvt_4_4_) {
         for(lvt_5_3_ = 0; lvt_5_3_ < 9; ++lvt_5_3_) {
            this.func_75146_a(new Slot(p_i1819_1_, lvt_5_3_ + (lvt_4_4_ + 1) * 9, 8 + lvt_5_3_ * 18, 84 + lvt_4_4_ * 18));
         }
      }

      for(lvt_4_4_ = 0; lvt_4_4_ < 9; ++lvt_4_4_) {
         this.func_75146_a(new Slot(p_i1819_1_, lvt_4_4_, 8 + lvt_4_4_ * 18, 142));
      }

      this.func_75130_a(this.field_75181_e);
   }

   public void func_75130_a(IInventory p_75130_1_) {
      this.field_75179_f.func_70299_a(0, CraftingManager.func_77594_a().func_82787_a(this.field_75181_e, this.field_82862_h.field_70170_p));
   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      super.func_75134_a(p_75134_1_);

      for(int lvt_2_1_ = 0; lvt_2_1_ < 4; ++lvt_2_1_) {
         ItemStack lvt_3_1_ = this.field_75181_e.func_70304_b(lvt_2_1_);
         if (lvt_3_1_ != null) {
            p_75134_1_.func_71019_a(lvt_3_1_, false);
         }
      }

      this.field_75179_f.func_70299_a(0, (ItemStack)null);
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return true;
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if (p_82846_2_ == 0) {
            if (!this.func_75135_a(lvt_5_1_, 9, 45, true)) {
               return null;
            }

            lvt_4_1_.func_75220_a(lvt_5_1_, lvt_3_1_);
         } else if (p_82846_2_ >= 1 && p_82846_2_ < 5) {
            if (!this.func_75135_a(lvt_5_1_, 9, 45, false)) {
               return null;
            }
         } else if (p_82846_2_ >= 5 && p_82846_2_ < 9) {
            if (!this.func_75135_a(lvt_5_1_, 9, 45, false)) {
               return null;
            }
         } else if (lvt_3_1_.func_77973_b() instanceof ItemArmor && !((Slot)this.field_75151_b.get(5 + ((ItemArmor)lvt_3_1_.func_77973_b()).field_77881_a)).func_75216_d()) {
            int lvt_6_1_ = 5 + ((ItemArmor)lvt_3_1_.func_77973_b()).field_77881_a;
            if (!this.func_75135_a(lvt_5_1_, lvt_6_1_, lvt_6_1_ + 1, false)) {
               return null;
            }
         } else if (p_82846_2_ >= 9 && p_82846_2_ < 36) {
            if (!this.func_75135_a(lvt_5_1_, 36, 45, false)) {
               return null;
            }
         } else if (p_82846_2_ >= 36 && p_82846_2_ < 45) {
            if (!this.func_75135_a(lvt_5_1_, 9, 36, false)) {
               return null;
            }
         } else if (!this.func_75135_a(lvt_5_1_, 9, 45, false)) {
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

   public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
      return p_94530_2_.field_75224_c != this.field_75179_f && super.func_94530_a(p_94530_1_, p_94530_2_);
   }
}
