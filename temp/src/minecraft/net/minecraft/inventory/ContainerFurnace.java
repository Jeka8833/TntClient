package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerFurnace extends Container {
   private final IInventory field_75158_e;
   private int field_178152_f;
   private int field_178153_g;
   private int field_178154_h;
   private int field_178155_i;

   public ContainerFurnace(InventoryPlayer p_i45794_1_, IInventory p_i45794_2_) {
      this.field_75158_e = p_i45794_2_;
      this.func_75146_a(new Slot(p_i45794_2_, 0, 56, 17));
      this.func_75146_a(new SlotFurnaceFuel(p_i45794_2_, 1, 56, 53));
      this.func_75146_a(new SlotFurnaceOutput(p_i45794_1_.field_70458_d, p_i45794_2_, 2, 116, 35));

      int lvt_3_2_;
      for(lvt_3_2_ = 0; lvt_3_2_ < 3; ++lvt_3_2_) {
         for(int lvt_4_1_ = 0; lvt_4_1_ < 9; ++lvt_4_1_) {
            this.func_75146_a(new Slot(p_i45794_1_, lvt_4_1_ + lvt_3_2_ * 9 + 9, 8 + lvt_4_1_ * 18, 84 + lvt_3_2_ * 18));
         }
      }

      for(lvt_3_2_ = 0; lvt_3_2_ < 9; ++lvt_3_2_) {
         this.func_75146_a(new Slot(p_i45794_1_, lvt_3_2_, 8 + lvt_3_2_ * 18, 142));
      }

   }

   public void func_75132_a(ICrafting p_75132_1_) {
      super.func_75132_a(p_75132_1_);
      p_75132_1_.func_175173_a(this, this.field_75158_e);
   }

   public void func_75142_b() {
      super.func_75142_b();

      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_75149_d.size(); ++lvt_1_1_) {
         ICrafting lvt_2_1_ = (ICrafting)this.field_75149_d.get(lvt_1_1_);
         if (this.field_178152_f != this.field_75158_e.func_174887_a_(2)) {
            lvt_2_1_.func_71112_a(this, 2, this.field_75158_e.func_174887_a_(2));
         }

         if (this.field_178154_h != this.field_75158_e.func_174887_a_(0)) {
            lvt_2_1_.func_71112_a(this, 0, this.field_75158_e.func_174887_a_(0));
         }

         if (this.field_178155_i != this.field_75158_e.func_174887_a_(1)) {
            lvt_2_1_.func_71112_a(this, 1, this.field_75158_e.func_174887_a_(1));
         }

         if (this.field_178153_g != this.field_75158_e.func_174887_a_(3)) {
            lvt_2_1_.func_71112_a(this, 3, this.field_75158_e.func_174887_a_(3));
         }
      }

      this.field_178152_f = this.field_75158_e.func_174887_a_(2);
      this.field_178154_h = this.field_75158_e.func_174887_a_(0);
      this.field_178155_i = this.field_75158_e.func_174887_a_(1);
      this.field_178153_g = this.field_75158_e.func_174887_a_(3);
   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
      this.field_75158_e.func_174885_b(p_75137_1_, p_75137_2_);
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return this.field_75158_e.func_70300_a(p_75145_1_);
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
         } else if (p_82846_2_ != 1 && p_82846_2_ != 0) {
            if (FurnaceRecipes.func_77602_a().func_151395_a(lvt_5_1_) != null) {
               if (!this.func_75135_a(lvt_5_1_, 0, 1, false)) {
                  return null;
               }
            } else if (TileEntityFurnace.func_145954_b(lvt_5_1_)) {
               if (!this.func_75135_a(lvt_5_1_, 1, 2, false)) {
                  return null;
               }
            } else if (p_82846_2_ >= 3 && p_82846_2_ < 30) {
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
}
