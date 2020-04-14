package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryEnderChest extends InventoryBasic {
   private TileEntityEnderChest field_70488_a;

   public InventoryEnderChest() {
      super("container.enderchest", false, 27);
   }

   public void func_146031_a(TileEntityEnderChest p_146031_1_) {
      this.field_70488_a = p_146031_1_;
   }

   public void func_70486_a(NBTTagList p_70486_1_) {
      int lvt_2_2_;
      for(lvt_2_2_ = 0; lvt_2_2_ < this.func_70302_i_(); ++lvt_2_2_) {
         this.func_70299_a(lvt_2_2_, (ItemStack)null);
      }

      for(lvt_2_2_ = 0; lvt_2_2_ < p_70486_1_.func_74745_c(); ++lvt_2_2_) {
         NBTTagCompound lvt_3_1_ = p_70486_1_.func_150305_b(lvt_2_2_);
         int lvt_4_1_ = lvt_3_1_.func_74771_c("Slot") & 255;
         if (lvt_4_1_ >= 0 && lvt_4_1_ < this.func_70302_i_()) {
            this.func_70299_a(lvt_4_1_, ItemStack.func_77949_a(lvt_3_1_));
         }
      }

   }

   public NBTTagList func_70487_g() {
      NBTTagList lvt_1_1_ = new NBTTagList();

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.func_70302_i_(); ++lvt_2_1_) {
         ItemStack lvt_3_1_ = this.func_70301_a(lvt_2_1_);
         if (lvt_3_1_ != null) {
            NBTTagCompound lvt_4_1_ = new NBTTagCompound();
            lvt_4_1_.func_74774_a("Slot", (byte)lvt_2_1_);
            lvt_3_1_.func_77955_b(lvt_4_1_);
            lvt_1_1_.func_74742_a(lvt_4_1_);
         }
      }

      return lvt_1_1_;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.field_70488_a != null && !this.field_70488_a.func_145971_a(p_70300_1_) ? false : super.func_70300_a(p_70300_1_);
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
      if (this.field_70488_a != null) {
         this.field_70488_a.func_145969_a();
      }

      super.func_174889_b(p_174889_1_);
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
      if (this.field_70488_a != null) {
         this.field_70488_a.func_145970_b();
      }

      super.func_174886_c(p_174886_1_);
      this.field_70488_a = null;
   }
}
