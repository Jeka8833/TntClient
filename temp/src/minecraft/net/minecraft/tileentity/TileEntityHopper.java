package net.minecraft.tileentity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHopper extends TileEntityLockable implements IHopper, ITickable {
   private ItemStack[] field_145900_a = new ItemStack[5];
   private String field_145902_i;
   private int field_145901_j = -1;

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      NBTTagList lvt_2_1_ = p_145839_1_.func_150295_c("Items", 10);
      this.field_145900_a = new ItemStack[this.func_70302_i_()];
      if (p_145839_1_.func_150297_b("CustomName", 8)) {
         this.field_145902_i = p_145839_1_.func_74779_i("CustomName");
      }

      this.field_145901_j = p_145839_1_.func_74762_e("TransferCooldown");

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
         NBTTagCompound lvt_4_1_ = lvt_2_1_.func_150305_b(lvt_3_1_);
         int lvt_5_1_ = lvt_4_1_.func_74771_c("Slot");
         if (lvt_5_1_ >= 0 && lvt_5_1_ < this.field_145900_a.length) {
            this.field_145900_a[lvt_5_1_] = ItemStack.func_77949_a(lvt_4_1_);
         }
      }

   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      NBTTagList lvt_2_1_ = new NBTTagList();

      for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_145900_a.length; ++lvt_3_1_) {
         if (this.field_145900_a[lvt_3_1_] != null) {
            NBTTagCompound lvt_4_1_ = new NBTTagCompound();
            lvt_4_1_.func_74774_a("Slot", (byte)lvt_3_1_);
            this.field_145900_a[lvt_3_1_].func_77955_b(lvt_4_1_);
            lvt_2_1_.func_74742_a(lvt_4_1_);
         }
      }

      p_145841_1_.func_74782_a("Items", lvt_2_1_);
      p_145841_1_.func_74768_a("TransferCooldown", this.field_145901_j);
      if (this.func_145818_k_()) {
         p_145841_1_.func_74778_a("CustomName", this.field_145902_i);
      }

   }

   public void func_70296_d() {
      super.func_70296_d();
   }

   public int func_70302_i_() {
      return this.field_145900_a.length;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return this.field_145900_a[p_70301_1_];
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if (this.field_145900_a[p_70298_1_] != null) {
         ItemStack lvt_3_2_;
         if (this.field_145900_a[p_70298_1_].field_77994_a <= p_70298_2_) {
            lvt_3_2_ = this.field_145900_a[p_70298_1_];
            this.field_145900_a[p_70298_1_] = null;
            return lvt_3_2_;
         } else {
            lvt_3_2_ = this.field_145900_a[p_70298_1_].func_77979_a(p_70298_2_);
            if (this.field_145900_a[p_70298_1_].field_77994_a == 0) {
               this.field_145900_a[p_70298_1_] = null;
            }

            return lvt_3_2_;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if (this.field_145900_a[p_70304_1_] != null) {
         ItemStack lvt_2_1_ = this.field_145900_a[p_70304_1_];
         this.field_145900_a[p_70304_1_] = null;
         return lvt_2_1_;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      this.field_145900_a[p_70299_1_] = p_70299_2_;
      if (p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_()) {
         p_70299_2_.field_77994_a = this.func_70297_j_();
      }

   }

   public String func_70005_c_() {
      return this.func_145818_k_() ? this.field_145902_i : "container.hopper";
   }

   public boolean func_145818_k_() {
      return this.field_145902_i != null && this.field_145902_i.length() > 0;
   }

   public void func_145886_a(String p_145886_1_) {
      this.field_145902_i = p_145886_1_;
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
      return true;
   }

   public void func_73660_a() {
      if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
         --this.field_145901_j;
         if (!this.func_145888_j()) {
            this.func_145896_c(0);
            this.func_145887_i();
         }

      }
   }

   public boolean func_145887_i() {
      if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
         if (!this.func_145888_j() && BlockHopper.func_149917_c(this.func_145832_p())) {
            boolean lvt_1_1_ = false;
            if (!this.func_152104_k()) {
               lvt_1_1_ = this.func_145883_k();
            }

            if (!this.func_152105_l()) {
               lvt_1_1_ = func_145891_a(this) || lvt_1_1_;
            }

            if (lvt_1_1_) {
               this.func_145896_c(8);
               this.func_70296_d();
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean func_152104_k() {
      ItemStack[] lvt_1_1_ = this.field_145900_a;
      int lvt_2_1_ = lvt_1_1_.length;

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
         ItemStack lvt_4_1_ = lvt_1_1_[lvt_3_1_];
         if (lvt_4_1_ != null) {
            return false;
         }
      }

      return true;
   }

   private boolean func_152105_l() {
      ItemStack[] lvt_1_1_ = this.field_145900_a;
      int lvt_2_1_ = lvt_1_1_.length;

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
         ItemStack lvt_4_1_ = lvt_1_1_[lvt_3_1_];
         if (lvt_4_1_ == null || lvt_4_1_.field_77994_a != lvt_4_1_.func_77976_d()) {
            return false;
         }
      }

      return true;
   }

   private boolean func_145883_k() {
      IInventory lvt_1_1_ = this.func_145895_l();
      if (lvt_1_1_ == null) {
         return false;
      } else {
         EnumFacing lvt_2_1_ = BlockHopper.func_176428_b(this.func_145832_p()).func_176734_d();
         if (this.func_174919_a(lvt_1_1_, lvt_2_1_)) {
            return false;
         } else {
            for(int lvt_3_1_ = 0; lvt_3_1_ < this.func_70302_i_(); ++lvt_3_1_) {
               if (this.func_70301_a(lvt_3_1_) != null) {
                  ItemStack lvt_4_1_ = this.func_70301_a(lvt_3_1_).func_77946_l();
                  ItemStack lvt_5_1_ = func_174918_a(lvt_1_1_, this.func_70298_a(lvt_3_1_, 1), lvt_2_1_);
                  if (lvt_5_1_ == null || lvt_5_1_.field_77994_a == 0) {
                     lvt_1_1_.func_70296_d();
                     return true;
                  }

                  this.func_70299_a(lvt_3_1_, lvt_4_1_);
               }
            }

            return false;
         }
      }
   }

   private boolean func_174919_a(IInventory p_174919_1_, EnumFacing p_174919_2_) {
      if (p_174919_1_ instanceof ISidedInventory) {
         ISidedInventory lvt_3_1_ = (ISidedInventory)p_174919_1_;
         int[] lvt_4_1_ = lvt_3_1_.func_180463_a(p_174919_2_);

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.length; ++lvt_5_1_) {
            ItemStack lvt_6_1_ = lvt_3_1_.func_70301_a(lvt_4_1_[lvt_5_1_]);
            if (lvt_6_1_ == null || lvt_6_1_.field_77994_a != lvt_6_1_.func_77976_d()) {
               return false;
            }
         }
      } else {
         int lvt_3_2_ = p_174919_1_.func_70302_i_();

         for(int lvt_4_2_ = 0; lvt_4_2_ < lvt_3_2_; ++lvt_4_2_) {
            ItemStack lvt_5_2_ = p_174919_1_.func_70301_a(lvt_4_2_);
            if (lvt_5_2_ == null || lvt_5_2_.field_77994_a != lvt_5_2_.func_77976_d()) {
               return false;
            }
         }
      }

      return true;
   }

   private static boolean func_174917_b(IInventory p_174917_0_, EnumFacing p_174917_1_) {
      if (p_174917_0_ instanceof ISidedInventory) {
         ISidedInventory lvt_2_1_ = (ISidedInventory)p_174917_0_;
         int[] lvt_3_1_ = lvt_2_1_.func_180463_a(p_174917_1_);

         for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_.length; ++lvt_4_1_) {
            if (lvt_2_1_.func_70301_a(lvt_3_1_[lvt_4_1_]) != null) {
               return false;
            }
         }
      } else {
         int lvt_2_2_ = p_174917_0_.func_70302_i_();

         for(int lvt_3_2_ = 0; lvt_3_2_ < lvt_2_2_; ++lvt_3_2_) {
            if (p_174917_0_.func_70301_a(lvt_3_2_) != null) {
               return false;
            }
         }
      }

      return true;
   }

   public static boolean func_145891_a(IHopper p_145891_0_) {
      IInventory lvt_1_1_ = func_145884_b(p_145891_0_);
      if (lvt_1_1_ != null) {
         EnumFacing lvt_2_1_ = EnumFacing.DOWN;
         if (func_174917_b(lvt_1_1_, lvt_2_1_)) {
            return false;
         }

         if (lvt_1_1_ instanceof ISidedInventory) {
            ISidedInventory lvt_3_1_ = (ISidedInventory)lvt_1_1_;
            int[] lvt_4_1_ = lvt_3_1_.func_180463_a(lvt_2_1_);

            for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.length; ++lvt_5_1_) {
               if (func_174915_a(p_145891_0_, lvt_1_1_, lvt_4_1_[lvt_5_1_], lvt_2_1_)) {
                  return true;
               }
            }
         } else {
            int lvt_3_2_ = lvt_1_1_.func_70302_i_();

            for(int lvt_4_2_ = 0; lvt_4_2_ < lvt_3_2_; ++lvt_4_2_) {
               if (func_174915_a(p_145891_0_, lvt_1_1_, lvt_4_2_, lvt_2_1_)) {
                  return true;
               }
            }
         }
      } else {
         Iterator lvt_2_2_ = func_181556_a(p_145891_0_.func_145831_w(), p_145891_0_.func_96107_aA(), p_145891_0_.func_96109_aB() + 1.0D, p_145891_0_.func_96108_aC()).iterator();

         while(lvt_2_2_.hasNext()) {
            EntityItem lvt_3_3_ = (EntityItem)lvt_2_2_.next();
            if (func_145898_a(p_145891_0_, lvt_3_3_)) {
               return true;
            }
         }
      }

      return false;
   }

   private static boolean func_174915_a(IHopper p_174915_0_, IInventory p_174915_1_, int p_174915_2_, EnumFacing p_174915_3_) {
      ItemStack lvt_4_1_ = p_174915_1_.func_70301_a(p_174915_2_);
      if (lvt_4_1_ != null && func_174921_b(p_174915_1_, lvt_4_1_, p_174915_2_, p_174915_3_)) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_77946_l();
         ItemStack lvt_6_1_ = func_174918_a(p_174915_0_, p_174915_1_.func_70298_a(p_174915_2_, 1), (EnumFacing)null);
         if (lvt_6_1_ == null || lvt_6_1_.field_77994_a == 0) {
            p_174915_1_.func_70296_d();
            return true;
         }

         p_174915_1_.func_70299_a(p_174915_2_, lvt_5_1_);
      }

      return false;
   }

   public static boolean func_145898_a(IInventory p_145898_0_, EntityItem p_145898_1_) {
      boolean lvt_2_1_ = false;
      if (p_145898_1_ == null) {
         return false;
      } else {
         ItemStack lvt_3_1_ = p_145898_1_.func_92059_d().func_77946_l();
         ItemStack lvt_4_1_ = func_174918_a(p_145898_0_, lvt_3_1_, (EnumFacing)null);
         if (lvt_4_1_ != null && lvt_4_1_.field_77994_a != 0) {
            p_145898_1_.func_92058_a(lvt_4_1_);
         } else {
            lvt_2_1_ = true;
            p_145898_1_.func_70106_y();
         }

         return lvt_2_1_;
      }
   }

   public static ItemStack func_174918_a(IInventory p_174918_0_, ItemStack p_174918_1_, EnumFacing p_174918_2_) {
      if (p_174918_0_ instanceof ISidedInventory && p_174918_2_ != null) {
         ISidedInventory lvt_3_1_ = (ISidedInventory)p_174918_0_;
         int[] lvt_4_1_ = lvt_3_1_.func_180463_a(p_174918_2_);

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.length && p_174918_1_ != null && p_174918_1_.field_77994_a > 0; ++lvt_5_1_) {
            p_174918_1_ = func_174916_c(p_174918_0_, p_174918_1_, lvt_4_1_[lvt_5_1_], p_174918_2_);
         }
      } else {
         int lvt_3_2_ = p_174918_0_.func_70302_i_();

         for(int lvt_4_2_ = 0; lvt_4_2_ < lvt_3_2_ && p_174918_1_ != null && p_174918_1_.field_77994_a > 0; ++lvt_4_2_) {
            p_174918_1_ = func_174916_c(p_174918_0_, p_174918_1_, lvt_4_2_, p_174918_2_);
         }
      }

      if (p_174918_1_ != null && p_174918_1_.field_77994_a == 0) {
         p_174918_1_ = null;
      }

      return p_174918_1_;
   }

   private static boolean func_174920_a(IInventory p_174920_0_, ItemStack p_174920_1_, int p_174920_2_, EnumFacing p_174920_3_) {
      if (!p_174920_0_.func_94041_b(p_174920_2_, p_174920_1_)) {
         return false;
      } else {
         return !(p_174920_0_ instanceof ISidedInventory) || ((ISidedInventory)p_174920_0_).func_180462_a(p_174920_2_, p_174920_1_, p_174920_3_);
      }
   }

   private static boolean func_174921_b(IInventory p_174921_0_, ItemStack p_174921_1_, int p_174921_2_, EnumFacing p_174921_3_) {
      return !(p_174921_0_ instanceof ISidedInventory) || ((ISidedInventory)p_174921_0_).func_180461_b(p_174921_2_, p_174921_1_, p_174921_3_);
   }

   private static ItemStack func_174916_c(IInventory p_174916_0_, ItemStack p_174916_1_, int p_174916_2_, EnumFacing p_174916_3_) {
      ItemStack lvt_4_1_ = p_174916_0_.func_70301_a(p_174916_2_);
      if (func_174920_a(p_174916_0_, p_174916_1_, p_174916_2_, p_174916_3_)) {
         boolean lvt_5_1_ = false;
         if (lvt_4_1_ == null) {
            p_174916_0_.func_70299_a(p_174916_2_, p_174916_1_);
            p_174916_1_ = null;
            lvt_5_1_ = true;
         } else if (func_145894_a(lvt_4_1_, p_174916_1_)) {
            int lvt_6_1_ = p_174916_1_.func_77976_d() - lvt_4_1_.field_77994_a;
            int lvt_7_1_ = Math.min(p_174916_1_.field_77994_a, lvt_6_1_);
            p_174916_1_.field_77994_a -= lvt_7_1_;
            lvt_4_1_.field_77994_a += lvt_7_1_;
            lvt_5_1_ = lvt_7_1_ > 0;
         }

         if (lvt_5_1_) {
            if (p_174916_0_ instanceof TileEntityHopper) {
               TileEntityHopper lvt_6_2_ = (TileEntityHopper)p_174916_0_;
               if (lvt_6_2_.func_174914_o()) {
                  lvt_6_2_.func_145896_c(8);
               }

               p_174916_0_.func_70296_d();
            }

            p_174916_0_.func_70296_d();
         }
      }

      return p_174916_1_;
   }

   private IInventory func_145895_l() {
      EnumFacing lvt_1_1_ = BlockHopper.func_176428_b(this.func_145832_p());
      return func_145893_b(this.func_145831_w(), (double)(this.field_174879_c.func_177958_n() + lvt_1_1_.func_82601_c()), (double)(this.field_174879_c.func_177956_o() + lvt_1_1_.func_96559_d()), (double)(this.field_174879_c.func_177952_p() + lvt_1_1_.func_82599_e()));
   }

   public static IInventory func_145884_b(IHopper p_145884_0_) {
      return func_145893_b(p_145884_0_.func_145831_w(), p_145884_0_.func_96107_aA(), p_145884_0_.func_96109_aB() + 1.0D, p_145884_0_.func_96108_aC());
   }

   public static List<EntityItem> func_181556_a(World p_181556_0_, double p_181556_1_, double p_181556_3_, double p_181556_5_) {
      return p_181556_0_.func_175647_a(EntityItem.class, new AxisAlignedBB(p_181556_1_ - 0.5D, p_181556_3_ - 0.5D, p_181556_5_ - 0.5D, p_181556_1_ + 0.5D, p_181556_3_ + 0.5D, p_181556_5_ + 0.5D), EntitySelectors.field_94557_a);
   }

   public static IInventory func_145893_b(World p_145893_0_, double p_145893_1_, double p_145893_3_, double p_145893_5_) {
      IInventory lvt_7_1_ = null;
      int lvt_8_1_ = MathHelper.func_76128_c(p_145893_1_);
      int lvt_9_1_ = MathHelper.func_76128_c(p_145893_3_);
      int lvt_10_1_ = MathHelper.func_76128_c(p_145893_5_);
      BlockPos lvt_11_1_ = new BlockPos(lvt_8_1_, lvt_9_1_, lvt_10_1_);
      Block lvt_12_1_ = p_145893_0_.func_180495_p(lvt_11_1_).func_177230_c();
      if (lvt_12_1_.func_149716_u()) {
         TileEntity lvt_13_1_ = p_145893_0_.func_175625_s(lvt_11_1_);
         if (lvt_13_1_ instanceof IInventory) {
            lvt_7_1_ = (IInventory)lvt_13_1_;
            if (lvt_7_1_ instanceof TileEntityChest && lvt_12_1_ instanceof BlockChest) {
               lvt_7_1_ = ((BlockChest)lvt_12_1_).func_180676_d(p_145893_0_, lvt_11_1_);
            }
         }
      }

      if (lvt_7_1_ == null) {
         List<Entity> lvt_13_2_ = p_145893_0_.func_175674_a((Entity)null, new AxisAlignedBB(p_145893_1_ - 0.5D, p_145893_3_ - 0.5D, p_145893_5_ - 0.5D, p_145893_1_ + 0.5D, p_145893_3_ + 0.5D, p_145893_5_ + 0.5D), EntitySelectors.field_96566_b);
         if (lvt_13_2_.size() > 0) {
            lvt_7_1_ = (IInventory)lvt_13_2_.get(p_145893_0_.field_73012_v.nextInt(lvt_13_2_.size()));
         }
      }

      return (IInventory)lvt_7_1_;
   }

   private static boolean func_145894_a(ItemStack p_145894_0_, ItemStack p_145894_1_) {
      if (p_145894_0_.func_77973_b() != p_145894_1_.func_77973_b()) {
         return false;
      } else if (p_145894_0_.func_77960_j() != p_145894_1_.func_77960_j()) {
         return false;
      } else if (p_145894_0_.field_77994_a > p_145894_0_.func_77976_d()) {
         return false;
      } else {
         return ItemStack.func_77970_a(p_145894_0_, p_145894_1_);
      }
   }

   public double func_96107_aA() {
      return (double)this.field_174879_c.func_177958_n() + 0.5D;
   }

   public double func_96109_aB() {
      return (double)this.field_174879_c.func_177956_o() + 0.5D;
   }

   public double func_96108_aC() {
      return (double)this.field_174879_c.func_177952_p() + 0.5D;
   }

   public void func_145896_c(int p_145896_1_) {
      this.field_145901_j = p_145896_1_;
   }

   public boolean func_145888_j() {
      return this.field_145901_j > 0;
   }

   public boolean func_174914_o() {
      return this.field_145901_j <= 1;
   }

   public String func_174875_k() {
      return "minecraft:hopper";
   }

   public Container func_174876_a(InventoryPlayer p_174876_1_, EntityPlayer p_174876_2_) {
      return new ContainerHopper(p_174876_1_, this, p_174876_2_);
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
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_145900_a.length; ++lvt_1_1_) {
         this.field_145900_a[lvt_1_1_] = null;
      }

   }
}
