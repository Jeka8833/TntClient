package net.minecraft.tileentity;

import java.util.Arrays;
import java.util.List;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityBrewingStand extends TileEntityLockable implements ITickable, ISidedInventory {
   private static final int[] field_145941_a = new int[]{3};
   private static final int[] field_145947_i = new int[]{0, 1, 2};
   private ItemStack[] field_145945_j = new ItemStack[4];
   private int field_145946_k;
   private boolean[] field_145943_l;
   private Item field_145944_m;
   private String field_145942_n;

   public String func_70005_c_() {
      return this.func_145818_k_() ? this.field_145942_n : "container.brewing";
   }

   public boolean func_145818_k_() {
      return this.field_145942_n != null && this.field_145942_n.length() > 0;
   }

   public void func_145937_a(String p_145937_1_) {
      this.field_145942_n = p_145937_1_;
   }

   public int func_70302_i_() {
      return this.field_145945_j.length;
   }

   public void func_73660_a() {
      if (this.field_145946_k > 0) {
         --this.field_145946_k;
         if (this.field_145946_k == 0) {
            this.func_145940_l();
            this.func_70296_d();
         } else if (!this.func_145934_k()) {
            this.field_145946_k = 0;
            this.func_70296_d();
         } else if (this.field_145944_m != this.field_145945_j[3].func_77973_b()) {
            this.field_145946_k = 0;
            this.func_70296_d();
         }
      } else if (this.func_145934_k()) {
         this.field_145946_k = 400;
         this.field_145944_m = this.field_145945_j[3].func_77973_b();
      }

      if (!this.field_145850_b.field_72995_K) {
         boolean[] lvt_1_1_ = this.func_174902_m();
         if (!Arrays.equals(lvt_1_1_, this.field_145943_l)) {
            this.field_145943_l = lvt_1_1_;
            IBlockState lvt_2_1_ = this.field_145850_b.func_180495_p(this.func_174877_v());
            if (!(lvt_2_1_.func_177230_c() instanceof BlockBrewingStand)) {
               return;
            }

            for(int lvt_3_1_ = 0; lvt_3_1_ < BlockBrewingStand.field_176451_a.length; ++lvt_3_1_) {
               lvt_2_1_ = lvt_2_1_.func_177226_a(BlockBrewingStand.field_176451_a[lvt_3_1_], lvt_1_1_[lvt_3_1_]);
            }

            this.field_145850_b.func_180501_a(this.field_174879_c, lvt_2_1_, 2);
         }
      }

   }

   private boolean func_145934_k() {
      if (this.field_145945_j[3] != null && this.field_145945_j[3].field_77994_a > 0) {
         ItemStack lvt_1_1_ = this.field_145945_j[3];
         if (!lvt_1_1_.func_77973_b().func_150892_m(lvt_1_1_)) {
            return false;
         } else {
            boolean lvt_2_1_ = false;

            for(int lvt_3_1_ = 0; lvt_3_1_ < 3; ++lvt_3_1_) {
               if (this.field_145945_j[lvt_3_1_] != null && this.field_145945_j[lvt_3_1_].func_77973_b() == Items.field_151068_bn) {
                  int lvt_4_1_ = this.field_145945_j[lvt_3_1_].func_77960_j();
                  int lvt_5_1_ = this.func_145936_c(lvt_4_1_, lvt_1_1_);
                  if (!ItemPotion.func_77831_g(lvt_4_1_) && ItemPotion.func_77831_g(lvt_5_1_)) {
                     lvt_2_1_ = true;
                     break;
                  }

                  List<PotionEffect> lvt_6_1_ = Items.field_151068_bn.func_77834_f(lvt_4_1_);
                  List<PotionEffect> lvt_7_1_ = Items.field_151068_bn.func_77834_f(lvt_5_1_);
                  if ((lvt_4_1_ <= 0 || lvt_6_1_ != lvt_7_1_) && (lvt_6_1_ == null || !lvt_6_1_.equals(lvt_7_1_) && lvt_7_1_ != null) && lvt_4_1_ != lvt_5_1_) {
                     lvt_2_1_ = true;
                     break;
                  }
               }
            }

            return lvt_2_1_;
         }
      } else {
         return false;
      }
   }

   private void func_145940_l() {
      if (this.func_145934_k()) {
         ItemStack lvt_1_1_ = this.field_145945_j[3];

         for(int lvt_2_1_ = 0; lvt_2_1_ < 3; ++lvt_2_1_) {
            if (this.field_145945_j[lvt_2_1_] != null && this.field_145945_j[lvt_2_1_].func_77973_b() == Items.field_151068_bn) {
               int lvt_3_1_ = this.field_145945_j[lvt_2_1_].func_77960_j();
               int lvt_4_1_ = this.func_145936_c(lvt_3_1_, lvt_1_1_);
               List<PotionEffect> lvt_5_1_ = Items.field_151068_bn.func_77834_f(lvt_3_1_);
               List<PotionEffect> lvt_6_1_ = Items.field_151068_bn.func_77834_f(lvt_4_1_);
               if ((lvt_3_1_ <= 0 || lvt_5_1_ != lvt_6_1_) && (lvt_5_1_ == null || !lvt_5_1_.equals(lvt_6_1_) && lvt_6_1_ != null)) {
                  if (lvt_3_1_ != lvt_4_1_) {
                     this.field_145945_j[lvt_2_1_].func_77964_b(lvt_4_1_);
                  }
               } else if (!ItemPotion.func_77831_g(lvt_3_1_) && ItemPotion.func_77831_g(lvt_4_1_)) {
                  this.field_145945_j[lvt_2_1_].func_77964_b(lvt_4_1_);
               }
            }
         }

         if (lvt_1_1_.func_77973_b().func_77634_r()) {
            this.field_145945_j[3] = new ItemStack(lvt_1_1_.func_77973_b().func_77668_q());
         } else {
            --this.field_145945_j[3].field_77994_a;
            if (this.field_145945_j[3].field_77994_a <= 0) {
               this.field_145945_j[3] = null;
            }
         }

      }
   }

   private int func_145936_c(int p_145936_1_, ItemStack p_145936_2_) {
      if (p_145936_2_ == null) {
         return p_145936_1_;
      } else {
         return p_145936_2_.func_77973_b().func_150892_m(p_145936_2_) ? PotionHelper.func_77913_a(p_145936_1_, p_145936_2_.func_77973_b().func_150896_i(p_145936_2_)) : p_145936_1_;
      }
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      NBTTagList lvt_2_1_ = p_145839_1_.func_150295_c("Items", 10);
      this.field_145945_j = new ItemStack[this.func_70302_i_()];

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
         NBTTagCompound lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_);
         int lvt_5_1_ = lvt_4_1_.func_74771_c("Slot");
         if (lvt_5_1_ >= 0 && lvt_5_1_ < this.field_145945_j.length) {
            this.field_145945_j[lvt_5_1_] = ItemStack.func_77949_a(lvt_4_1_);
         }
      }

      this.field_145946_k = p_145839_1_.func_74765_d("BrewTime");
      if (p_145839_1_.func_150297_b("CustomName", 8)) {
         this.field_145942_n = p_145839_1_.func_74779_i("CustomName");
      }

   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74777_a("BrewTime", (short)this.field_145946_k);
      NBTTagList lvt_2_1_ = new NBTTagList();

      for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_145945_j.length; ++lvt_3_1_) {
         if (this.field_145945_j[lvt_3_1_] != null) {
            NBTTagCompound lvt_4_1_ = new NBTTagCompound();
            lvt_4_1_.func_74774_a("Slot", (byte)lvt_3_1_);
            this.field_145945_j[lvt_3_1_].func_77955_b(lvt_4_1_);
            lvt_2_1_.func_74742_a(lvt_4_1_);
         }
      }

      p_145841_1_.func_74782_a("Items", lvt_2_1_);
      if (this.func_145818_k_()) {
         p_145841_1_.func_74778_a("CustomName", this.field_145942_n);
      }

   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return p_70301_1_ >= 0 && p_70301_1_ < this.field_145945_j.length ? this.field_145945_j[p_70301_1_] : null;
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if (p_70298_1_ >= 0 && p_70298_1_ < this.field_145945_j.length) {
         ItemStack lvt_3_1_ = this.field_145945_j[p_70298_1_];
         this.field_145945_j[p_70298_1_] = null;
         return lvt_3_1_;
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if (p_70304_1_ >= 0 && p_70304_1_ < this.field_145945_j.length) {
         ItemStack lvt_2_1_ = this.field_145945_j[p_70304_1_];
         this.field_145945_j[p_70304_1_] = null;
         return lvt_2_1_;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      if (p_70299_1_ >= 0 && p_70299_1_ < this.field_145945_j.length) {
         this.field_145945_j[p_70299_1_] = p_70299_2_;
      }

   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      if (this.field_145850_b.func_175625_s(this.field_174879_c) != this) {
         return false;
      } else {
         return p_70300_1_.func_70092_e((double)this.field_174879_c.func_177958_n() + 0.5D, (double)this.field_174879_c.func_177956_o() + 0.5D, (double)this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D;
      }
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      if (p_94041_1_ == 3) {
         return p_94041_2_.func_77973_b().func_150892_m(p_94041_2_);
      } else {
         return p_94041_2_.func_77973_b() == Items.field_151068_bn || p_94041_2_.func_77973_b() == Items.field_151069_bo;
      }
   }

   public boolean[] func_174902_m() {
      boolean[] lvt_1_1_ = new boolean[3];

      for(int lvt_2_1_ = 0; lvt_2_1_ < 3; ++lvt_2_1_) {
         if (this.field_145945_j[lvt_2_1_] != null) {
            lvt_1_1_[lvt_2_1_] = true;
         }
      }

      return lvt_1_1_;
   }

   public int[] func_180463_a(EnumFacing p_180463_1_) {
      return p_180463_1_ == EnumFacing.UP ? field_145941_a : field_145947_i;
   }

   public boolean func_180462_a(int p_180462_1_, ItemStack p_180462_2_, EnumFacing p_180462_3_) {
      return this.func_94041_b(p_180462_1_, p_180462_2_);
   }

   public boolean func_180461_b(int p_180461_1_, ItemStack p_180461_2_, EnumFacing p_180461_3_) {
      return true;
   }

   public String func_174875_k() {
      return "minecraft:brewing_stand";
   }

   public Container func_174876_a(InventoryPlayer p_174876_1_, EntityPlayer p_174876_2_) {
      return new ContainerBrewingStand(p_174876_1_, this);
   }

   public int func_174887_a_(int p_174887_1_) {
      switch(p_174887_1_) {
      case 0:
         return this.field_145946_k;
      default:
         return 0;
      }
   }

   public void func_174885_b(int p_174885_1_, int p_174885_2_) {
      switch(p_174885_1_) {
      case 0:
         this.field_145946_k = p_174885_2_;
      default:
      }
   }

   public int func_174890_g() {
      return 1;
   }

   public void func_174888_l() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_145945_j.length; ++lvt_1_1_) {
         this.field_145945_j[lvt_1_1_] = null;
      }

   }
}
