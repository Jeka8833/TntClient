package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerWorkbench extends Container {
   public InventoryCrafting field_75162_e = new InventoryCrafting(this, 3, 3);
   public IInventory field_75160_f = new InventoryCraftResult();
   private World field_75161_g;
   private BlockPos field_178145_h;

   public ContainerWorkbench(InventoryPlayer p_i45800_1_, World p_i45800_2_, BlockPos p_i45800_3_) {
      this.field_75161_g = p_i45800_2_;
      this.field_178145_h = p_i45800_3_;
      this.func_75146_a(new SlotCrafting(p_i45800_1_.field_70458_d, this.field_75162_e, this.field_75160_f, 0, 124, 35));

      int lvt_4_3_;
      int lvt_5_2_;
      for(lvt_4_3_ = 0; lvt_4_3_ < 3; ++lvt_4_3_) {
         for(lvt_5_2_ = 0; lvt_5_2_ < 3; ++lvt_5_2_) {
            this.func_75146_a(new Slot(this.field_75162_e, lvt_5_2_ + lvt_4_3_ * 3, 30 + lvt_5_2_ * 18, 17 + lvt_4_3_ * 18));
         }
      }

      for(lvt_4_3_ = 0; lvt_4_3_ < 3; ++lvt_4_3_) {
         for(lvt_5_2_ = 0; lvt_5_2_ < 9; ++lvt_5_2_) {
            this.func_75146_a(new Slot(p_i45800_1_, lvt_5_2_ + lvt_4_3_ * 9 + 9, 8 + lvt_5_2_ * 18, 84 + lvt_4_3_ * 18));
         }
      }

      for(lvt_4_3_ = 0; lvt_4_3_ < 9; ++lvt_4_3_) {
         this.func_75146_a(new Slot(p_i45800_1_, lvt_4_3_, 8 + lvt_4_3_ * 18, 142));
      }

      this.func_75130_a(this.field_75162_e);
   }

   public void func_75130_a(IInventory p_75130_1_) {
      this.field_75160_f.func_70299_a(0, CraftingManager.func_77594_a().func_82787_a(this.field_75162_e, this.field_75161_g));
   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      super.func_75134_a(p_75134_1_);
      if (!this.field_75161_g.field_72995_K) {
         for(int lvt_2_1_ = 0; lvt_2_1_ < 9; ++lvt_2_1_) {
            ItemStack lvt_3_1_ = this.field_75162_e.func_70304_b(lvt_2_1_);
            if (lvt_3_1_ != null) {
               p_75134_1_.func_71019_a(lvt_3_1_, false);
            }
         }

      }
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      if (this.field_75161_g.func_180495_p(this.field_178145_h).func_177230_c() != Blocks.field_150462_ai) {
         return false;
      } else {
         return p_75145_1_.func_70092_e((double)this.field_178145_h.func_177958_n() + 0.5D, (double)this.field_178145_h.func_177956_o() + 0.5D, (double)this.field_178145_h.func_177952_p() + 0.5D) <= 64.0D;
      }
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if (p_82846_2_ == 0) {
            if (!this.func_75135_a(lvt_5_1_, 10, 46, true)) {
               return null;
            }

            lvt_4_1_.func_75220_a(lvt_5_1_, lvt_3_1_);
         } else if (p_82846_2_ >= 10 && p_82846_2_ < 37) {
            if (!this.func_75135_a(lvt_5_1_, 37, 46, false)) {
               return null;
            }
         } else if (p_82846_2_ >= 37 && p_82846_2_ < 46) {
            if (!this.func_75135_a(lvt_5_1_, 10, 37, false)) {
               return null;
            }
         } else if (!this.func_75135_a(lvt_5_1_, 10, 46, false)) {
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
      return p_94530_2_.field_75224_c != this.field_75160_f && super.func_94530_a(p_94530_1_, p_94530_2_);
   }
}
