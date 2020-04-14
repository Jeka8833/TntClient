package net.minecraft.inventory;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryBasic implements IInventory {
   private String field_70483_a;
   private int field_70481_b;
   private ItemStack[] field_70482_c;
   private List<IInvBasic> field_70480_d;
   private boolean field_94051_e;

   public InventoryBasic(String p_i1561_1_, boolean p_i1561_2_, int p_i1561_3_) {
      this.field_70483_a = p_i1561_1_;
      this.field_94051_e = p_i1561_2_;
      this.field_70481_b = p_i1561_3_;
      this.field_70482_c = new ItemStack[p_i1561_3_];
   }

   public InventoryBasic(IChatComponent p_i45902_1_, int p_i45902_2_) {
      this(p_i45902_1_.func_150260_c(), true, p_i45902_2_);
   }

   public void func_110134_a(IInvBasic p_110134_1_) {
      if (this.field_70480_d == null) {
         this.field_70480_d = Lists.newArrayList();
      }

      this.field_70480_d.add(p_110134_1_);
   }

   public void func_110132_b(IInvBasic p_110132_1_) {
      this.field_70480_d.remove(p_110132_1_);
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return p_70301_1_ >= 0 && p_70301_1_ < this.field_70482_c.length ? this.field_70482_c[p_70301_1_] : null;
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if (this.field_70482_c[p_70298_1_] != null) {
         ItemStack lvt_3_2_;
         if (this.field_70482_c[p_70298_1_].field_77994_a <= p_70298_2_) {
            lvt_3_2_ = this.field_70482_c[p_70298_1_];
            this.field_70482_c[p_70298_1_] = null;
            this.func_70296_d();
            return lvt_3_2_;
         } else {
            lvt_3_2_ = this.field_70482_c[p_70298_1_].func_77979_a(p_70298_2_);
            if (this.field_70482_c[p_70298_1_].field_77994_a == 0) {
               this.field_70482_c[p_70298_1_] = null;
            }

            this.func_70296_d();
            return lvt_3_2_;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_174894_a(ItemStack p_174894_1_) {
      ItemStack lvt_2_1_ = p_174894_1_.func_77946_l();

      for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_70481_b; ++lvt_3_1_) {
         ItemStack lvt_4_1_ = this.func_70301_a(lvt_3_1_);
         if (lvt_4_1_ == null) {
            this.func_70299_a(lvt_3_1_, lvt_2_1_);
            this.func_70296_d();
            return null;
         }

         if (ItemStack.func_179545_c(lvt_4_1_, lvt_2_1_)) {
            int lvt_5_1_ = Math.min(this.func_70297_j_(), lvt_4_1_.func_77976_d());
            int lvt_6_1_ = Math.min(lvt_2_1_.field_77994_a, lvt_5_1_ - lvt_4_1_.field_77994_a);
            if (lvt_6_1_ > 0) {
               lvt_4_1_.field_77994_a += lvt_6_1_;
               lvt_2_1_.field_77994_a -= lvt_6_1_;
               if (lvt_2_1_.field_77994_a <= 0) {
                  this.func_70296_d();
                  return null;
               }
            }
         }
      }

      if (lvt_2_1_.field_77994_a != p_174894_1_.field_77994_a) {
         this.func_70296_d();
      }

      return lvt_2_1_;
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if (this.field_70482_c[p_70304_1_] != null) {
         ItemStack lvt_2_1_ = this.field_70482_c[p_70304_1_];
         this.field_70482_c[p_70304_1_] = null;
         return lvt_2_1_;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      this.field_70482_c[p_70299_1_] = p_70299_2_;
      if (p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_()) {
         p_70299_2_.field_77994_a = this.func_70297_j_();
      }

      this.func_70296_d();
   }

   public int func_70302_i_() {
      return this.field_70481_b;
   }

   public String func_70005_c_() {
      return this.field_70483_a;
   }

   public boolean func_145818_k_() {
      return this.field_94051_e;
   }

   public void func_110133_a(String p_110133_1_) {
      this.field_94051_e = true;
      this.field_70483_a = p_110133_1_;
   }

   public IChatComponent func_145748_c_() {
      return (IChatComponent)(this.func_145818_k_() ? new ChatComponentText(this.func_70005_c_()) : new ChatComponentTranslation(this.func_70005_c_(), new Object[0]));
   }

   public int func_70297_j_() {
      return 64;
   }

   public void func_70296_d() {
      if (this.field_70480_d != null) {
         for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_70480_d.size(); ++lvt_1_1_) {
            ((IInvBasic)this.field_70480_d.get(lvt_1_1_)).func_76316_a(this);
         }
      }

   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return true;
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   public int func_174887_a_(int p_174887_1_) {
      return 0;
   }

   public void func_174885_b(int p_174885_1_, int p_174885_2_) {
   }

   public int func_174890_g() {
      return 0;
   }

   public void func_174888_l() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_70482_c.length; ++lvt_1_1_) {
         this.field_70482_c[lvt_1_1_] = null;
      }

   }
}
