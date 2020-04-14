package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ContainerBeacon extends Container {
   private IInventory field_82866_e;
   private final ContainerBeacon.BeaconSlot field_82864_f;

   public ContainerBeacon(IInventory p_i45804_1_, IInventory p_i45804_2_) {
      this.field_82866_e = p_i45804_2_;
      this.func_75146_a(this.field_82864_f = new ContainerBeacon.BeaconSlot(p_i45804_2_, 0, 136, 110));
      int lvt_3_1_ = 36;
      int lvt_4_1_ = 137;

      int lvt_5_2_;
      for(lvt_5_2_ = 0; lvt_5_2_ < 3; ++lvt_5_2_) {
         for(int lvt_6_1_ = 0; lvt_6_1_ < 9; ++lvt_6_1_) {
            this.func_75146_a(new Slot(p_i45804_1_, lvt_6_1_ + lvt_5_2_ * 9 + 9, lvt_3_1_ + lvt_6_1_ * 18, lvt_4_1_ + lvt_5_2_ * 18));
         }
      }

      for(lvt_5_2_ = 0; lvt_5_2_ < 9; ++lvt_5_2_) {
         this.func_75146_a(new Slot(p_i45804_1_, lvt_5_2_, lvt_3_1_ + lvt_5_2_ * 18, 58 + lvt_4_1_));
      }

   }

   public void func_75132_a(ICrafting p_75132_1_) {
      super.func_75132_a(p_75132_1_);
      p_75132_1_.func_175173_a(this, this.field_82866_e);
   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
      this.field_82866_e.func_174885_b(p_75137_1_, p_75137_2_);
   }

   public IInventory func_180611_e() {
      return this.field_82866_e;
   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      super.func_75134_a(p_75134_1_);
      if (p_75134_1_ != null && !p_75134_1_.field_70170_p.field_72995_K) {
         ItemStack lvt_2_1_ = this.field_82864_f.func_75209_a(this.field_82864_f.func_75219_a());
         if (lvt_2_1_ != null) {
            p_75134_1_.func_71019_a(lvt_2_1_, false);
         }

      }
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return this.field_82866_e.func_70300_a(p_75145_1_);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if (p_82846_2_ == 0) {
            if (!this.func_75135_a(lvt_5_1_, 1, 37, true)) {
               return null;
            }

            lvt_4_1_.func_75220_a(lvt_5_1_, lvt_3_1_);
         } else if (!this.field_82864_f.func_75216_d() && this.field_82864_f.func_75214_a(lvt_5_1_) && lvt_5_1_.field_77994_a == 1) {
            if (!this.func_75135_a(lvt_5_1_, 0, 1, false)) {
               return null;
            }
         } else if (p_82846_2_ >= 1 && p_82846_2_ < 28) {
            if (!this.func_75135_a(lvt_5_1_, 28, 37, false)) {
               return null;
            }
         } else if (p_82846_2_ >= 28 && p_82846_2_ < 37) {
            if (!this.func_75135_a(lvt_5_1_, 1, 28, false)) {
               return null;
            }
         } else if (!this.func_75135_a(lvt_5_1_, 1, 37, false)) {
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

   class BeaconSlot extends Slot {
      public BeaconSlot(IInventory p_i1801_2_, int p_i1801_3_, int p_i1801_4_, int p_i1801_5_) {
         super(p_i1801_2_, p_i1801_3_, p_i1801_4_, p_i1801_5_);
      }

      public boolean func_75214_a(ItemStack p_75214_1_) {
         if (p_75214_1_ == null) {
            return false;
         } else {
            return p_75214_1_.func_77973_b() == Items.field_151166_bC || p_75214_1_.func_77973_b() == Items.field_151045_i || p_75214_1_.func_77973_b() == Items.field_151043_k || p_75214_1_.func_77973_b() == Items.field_151042_j;
         }
      }

      public int func_75219_a() {
         return 1;
      }
   }
}
