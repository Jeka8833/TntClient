package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;

public class ContainerBrewingStand extends Container {
   private IInventory field_75188_e;
   private final Slot field_75186_f;
   private int field_75187_g;

   public ContainerBrewingStand(InventoryPlayer p_i45802_1_, IInventory p_i45802_2_) {
      this.field_75188_e = p_i45802_2_;
      this.func_75146_a(new ContainerBrewingStand.Potion(p_i45802_1_.field_70458_d, p_i45802_2_, 0, 56, 46));
      this.func_75146_a(new ContainerBrewingStand.Potion(p_i45802_1_.field_70458_d, p_i45802_2_, 1, 79, 53));
      this.func_75146_a(new ContainerBrewingStand.Potion(p_i45802_1_.field_70458_d, p_i45802_2_, 2, 102, 46));
      this.field_75186_f = this.func_75146_a(new ContainerBrewingStand.Ingredient(p_i45802_2_, 3, 79, 17));

      int lvt_3_2_;
      for(lvt_3_2_ = 0; lvt_3_2_ < 3; ++lvt_3_2_) {
         for(int lvt_4_1_ = 0; lvt_4_1_ < 9; ++lvt_4_1_) {
            this.func_75146_a(new Slot(p_i45802_1_, lvt_4_1_ + lvt_3_2_ * 9 + 9, 8 + lvt_4_1_ * 18, 84 + lvt_3_2_ * 18));
         }
      }

      for(lvt_3_2_ = 0; lvt_3_2_ < 9; ++lvt_3_2_) {
         this.func_75146_a(new Slot(p_i45802_1_, lvt_3_2_, 8 + lvt_3_2_ * 18, 142));
      }

   }

   public void func_75132_a(ICrafting p_75132_1_) {
      super.func_75132_a(p_75132_1_);
      p_75132_1_.func_175173_a(this, this.field_75188_e);
   }

   public void func_75142_b() {
      super.func_75142_b();

      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_75149_d.size(); ++lvt_1_1_) {
         ICrafting lvt_2_1_ = (ICrafting)this.field_75149_d.get(lvt_1_1_);
         if (this.field_75187_g != this.field_75188_e.func_174887_a_(0)) {
            lvt_2_1_.func_71112_a(this, 0, this.field_75188_e.func_174887_a_(0));
         }
      }

      this.field_75187_g = this.field_75188_e.func_174887_a_(0);
   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
      this.field_75188_e.func_174885_b(p_75137_1_, p_75137_2_);
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return this.field_75188_e.func_70300_a(p_75145_1_);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if ((p_82846_2_ < 0 || p_82846_2_ > 2) && p_82846_2_ != 3) {
            if (!this.field_75186_f.func_75216_d() && this.field_75186_f.func_75214_a(lvt_5_1_)) {
               if (!this.func_75135_a(lvt_5_1_, 3, 4, false)) {
                  return null;
               }
            } else if (ContainerBrewingStand.Potion.func_75243_a_(lvt_3_1_)) {
               if (!this.func_75135_a(lvt_5_1_, 0, 3, false)) {
                  return null;
               }
            } else if (p_82846_2_ >= 4 && p_82846_2_ < 31) {
               if (!this.func_75135_a(lvt_5_1_, 31, 40, false)) {
                  return null;
               }
            } else if (p_82846_2_ >= 31 && p_82846_2_ < 40) {
               if (!this.func_75135_a(lvt_5_1_, 4, 31, false)) {
                  return null;
               }
            } else if (!this.func_75135_a(lvt_5_1_, 4, 40, false)) {
               return null;
            }
         } else {
            if (!this.func_75135_a(lvt_5_1_, 4, 40, true)) {
               return null;
            }

            lvt_4_1_.func_75220_a(lvt_5_1_, lvt_3_1_);
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

   class Ingredient extends Slot {
      public Ingredient(IInventory p_i1803_2_, int p_i1803_3_, int p_i1803_4_, int p_i1803_5_) {
         super(p_i1803_2_, p_i1803_3_, p_i1803_4_, p_i1803_5_);
      }

      public boolean func_75214_a(ItemStack p_75214_1_) {
         return p_75214_1_ != null ? p_75214_1_.func_77973_b().func_150892_m(p_75214_1_) : false;
      }

      public int func_75219_a() {
         return 64;
      }
   }

   static class Potion extends Slot {
      private EntityPlayer field_75244_a;

      public Potion(EntityPlayer p_i1804_1_, IInventory p_i1804_2_, int p_i1804_3_, int p_i1804_4_, int p_i1804_5_) {
         super(p_i1804_2_, p_i1804_3_, p_i1804_4_, p_i1804_5_);
         this.field_75244_a = p_i1804_1_;
      }

      public boolean func_75214_a(ItemStack p_75214_1_) {
         return func_75243_a_(p_75214_1_);
      }

      public int func_75219_a() {
         return 1;
      }

      public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
         if (p_82870_2_.func_77973_b() == Items.field_151068_bn && p_82870_2_.func_77960_j() > 0) {
            this.field_75244_a.func_71029_a(AchievementList.field_76001_A);
         }

         super.func_82870_a(p_82870_1_, p_82870_2_);
      }

      public static boolean func_75243_a_(ItemStack p_75243_0_) {
         return p_75243_0_ != null && (p_75243_0_.func_77973_b() == Items.field_151068_bn || p_75243_0_.func_77973_b() == Items.field_151069_bo);
      }
   }
}
