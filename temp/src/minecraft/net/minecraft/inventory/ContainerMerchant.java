package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMerchant extends Container {
   private IMerchant field_75178_e;
   private InventoryMerchant field_75176_f;
   private final World field_75177_g;

   public ContainerMerchant(InventoryPlayer p_i1821_1_, IMerchant p_i1821_2_, World p_i1821_3_) {
      this.field_75178_e = p_i1821_2_;
      this.field_75177_g = p_i1821_3_;
      this.field_75176_f = new InventoryMerchant(p_i1821_1_.field_70458_d, p_i1821_2_);
      this.func_75146_a(new Slot(this.field_75176_f, 0, 36, 53));
      this.func_75146_a(new Slot(this.field_75176_f, 1, 62, 53));
      this.func_75146_a(new SlotMerchantResult(p_i1821_1_.field_70458_d, p_i1821_2_, this.field_75176_f, 2, 120, 53));

      int lvt_4_2_;
      for(lvt_4_2_ = 0; lvt_4_2_ < 3; ++lvt_4_2_) {
         for(int lvt_5_1_ = 0; lvt_5_1_ < 9; ++lvt_5_1_) {
            this.func_75146_a(new Slot(p_i1821_1_, lvt_5_1_ + lvt_4_2_ * 9 + 9, 8 + lvt_5_1_ * 18, 84 + lvt_4_2_ * 18));
         }
      }

      for(lvt_4_2_ = 0; lvt_4_2_ < 9; ++lvt_4_2_) {
         this.func_75146_a(new Slot(p_i1821_1_, lvt_4_2_, 8 + lvt_4_2_ * 18, 142));
      }

   }

   public InventoryMerchant func_75174_d() {
      return this.field_75176_f;
   }

   public void func_75132_a(ICrafting p_75132_1_) {
      super.func_75132_a(p_75132_1_);
   }

   public void func_75142_b() {
      super.func_75142_b();
   }

   public void func_75130_a(IInventory p_75130_1_) {
      this.field_75176_f.func_70470_g();
      super.func_75130_a(p_75130_1_);
   }

   public void func_75175_c(int p_75175_1_) {
      this.field_75176_f.func_70471_c(p_75175_1_);
   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return this.field_75178_e.func_70931_l_() == p_75145_1_;
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if (p_82846_2_ == 2) {
            if (!this.func_75135_a(lvt_5_1_, 3, 39, true)) {
               return null;
            }

            lvt_4_1_.func_75220_a(lvt_5_1_, lvt_3_1_);
         } else if (p_82846_2_ != 0 && p_82846_2_ != 1) {
            if (p_82846_2_ >= 3 && p_82846_2_ < 30) {
               if (!this.func_75135_a(lvt_5_1_, 30, 39, false)) {
                  return null;
               }
            } else if (p_82846_2_ >= 30 && p_82846_2_ < 39 && !this.func_75135_a(lvt_5_1_, 3, 30, false)) {
               return null;
            }
         } else if (!this.func_75135_a(lvt_5_1_, 3, 39, false)) {
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

   public void func_75134_a(EntityPlayer p_75134_1_) {
      super.func_75134_a(p_75134_1_);
      this.field_75178_e.func_70932_a_((EntityPlayer)null);
      super.func_75134_a(p_75134_1_);
      if (!this.field_75177_g.field_72995_K) {
         ItemStack lvt_2_1_ = this.field_75176_f.func_70304_b(0);
         if (lvt_2_1_ != null) {
            p_75134_1_.func_71019_a(lvt_2_1_, false);
         }

         lvt_2_1_ = this.field_75176_f.func_70304_b(1);
         if (lvt_2_1_ != null) {
            p_75134_1_.func_71019_a(lvt_2_1_, false);
         }

      }
   }
}
