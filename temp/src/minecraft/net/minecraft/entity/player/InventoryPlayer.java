package net.minecraft.entity.player;

import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;

public class InventoryPlayer implements IInventory {
   public ItemStack[] field_70462_a = new ItemStack[36];
   public ItemStack[] field_70460_b = new ItemStack[4];
   public int field_70461_c;
   public EntityPlayer field_70458_d;
   private ItemStack field_70457_g;
   public boolean field_70459_e;

   public InventoryPlayer(EntityPlayer p_i1750_1_) {
      this.field_70458_d = p_i1750_1_;
   }

   public ItemStack func_70448_g() {
      return this.field_70461_c < 9 && this.field_70461_c >= 0 ? this.field_70462_a[this.field_70461_c] : null;
   }

   public static int func_70451_h() {
      return 9;
   }

   private int func_146029_c(Item p_146029_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_70462_a.length; ++lvt_2_1_) {
         if (this.field_70462_a[lvt_2_1_] != null && this.field_70462_a[lvt_2_1_].func_77973_b() == p_146029_1_) {
            return lvt_2_1_;
         }
      }

      return -1;
   }

   private int func_146024_c(Item p_146024_1_, int p_146024_2_) {
      for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_70462_a.length; ++lvt_3_1_) {
         if (this.field_70462_a[lvt_3_1_] != null && this.field_70462_a[lvt_3_1_].func_77973_b() == p_146024_1_ && this.field_70462_a[lvt_3_1_].func_77960_j() == p_146024_2_) {
            return lvt_3_1_;
         }
      }

      return -1;
   }

   private int func_70432_d(ItemStack p_70432_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_70462_a.length; ++lvt_2_1_) {
         if (this.field_70462_a[lvt_2_1_] != null && this.field_70462_a[lvt_2_1_].func_77973_b() == p_70432_1_.func_77973_b() && this.field_70462_a[lvt_2_1_].func_77985_e() && this.field_70462_a[lvt_2_1_].field_77994_a < this.field_70462_a[lvt_2_1_].func_77976_d() && this.field_70462_a[lvt_2_1_].field_77994_a < this.func_70297_j_() && (!this.field_70462_a[lvt_2_1_].func_77981_g() || this.field_70462_a[lvt_2_1_].func_77960_j() == p_70432_1_.func_77960_j()) && ItemStack.func_77970_a(this.field_70462_a[lvt_2_1_], p_70432_1_)) {
            return lvt_2_1_;
         }
      }

      return -1;
   }

   public int func_70447_i() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_70462_a.length; ++lvt_1_1_) {
         if (this.field_70462_a[lvt_1_1_] == null) {
            return lvt_1_1_;
         }
      }

      return -1;
   }

   public void func_146030_a(Item p_146030_1_, int p_146030_2_, boolean p_146030_3_, boolean p_146030_4_) {
      ItemStack lvt_5_1_ = this.func_70448_g();
      int lvt_6_1_ = p_146030_3_ ? this.func_146024_c(p_146030_1_, p_146030_2_) : this.func_146029_c(p_146030_1_);
      if (lvt_6_1_ >= 0 && lvt_6_1_ < 9) {
         this.field_70461_c = lvt_6_1_;
      } else if (p_146030_4_ && p_146030_1_ != null) {
         int lvt_7_1_ = this.func_70447_i();
         if (lvt_7_1_ >= 0 && lvt_7_1_ < 9) {
            this.field_70461_c = lvt_7_1_;
         }

         if (lvt_5_1_ == null || !lvt_5_1_.func_77956_u() || this.func_146024_c(lvt_5_1_.func_77973_b(), lvt_5_1_.func_77952_i()) != this.field_70461_c) {
            int lvt_8_1_ = this.func_146024_c(p_146030_1_, p_146030_2_);
            int lvt_9_2_;
            if (lvt_8_1_ >= 0) {
               lvt_9_2_ = this.field_70462_a[lvt_8_1_].field_77994_a;
               this.field_70462_a[lvt_8_1_] = this.field_70462_a[this.field_70461_c];
            } else {
               lvt_9_2_ = 1;
            }

            this.field_70462_a[this.field_70461_c] = new ItemStack(p_146030_1_, lvt_9_2_, p_146030_2_);
         }

      }
   }

   public void func_70453_c(int p_70453_1_) {
      if (p_70453_1_ > 0) {
         p_70453_1_ = 1;
      }

      if (p_70453_1_ < 0) {
         p_70453_1_ = -1;
      }

      for(this.field_70461_c -= p_70453_1_; this.field_70461_c < 0; this.field_70461_c += 9) {
      }

      while(this.field_70461_c >= 9) {
         this.field_70461_c -= 9;
      }

   }

   public int func_174925_a(Item p_174925_1_, int p_174925_2_, int p_174925_3_, NBTTagCompound p_174925_4_) {
      int lvt_5_1_ = 0;

      ItemStack var10000;
      int lvt_6_3_;
      ItemStack lvt_7_2_;
      int lvt_8_2_;
      for(lvt_6_3_ = 0; lvt_6_3_ < this.field_70462_a.length; ++lvt_6_3_) {
         lvt_7_2_ = this.field_70462_a[lvt_6_3_];
         if (lvt_7_2_ != null && (p_174925_1_ == null || lvt_7_2_.func_77973_b() == p_174925_1_) && (p_174925_2_ <= -1 || lvt_7_2_.func_77960_j() == p_174925_2_) && (p_174925_4_ == null || NBTUtil.func_181123_a(p_174925_4_, lvt_7_2_.func_77978_p(), true))) {
            lvt_8_2_ = p_174925_3_ <= 0 ? lvt_7_2_.field_77994_a : Math.min(p_174925_3_ - lvt_5_1_, lvt_7_2_.field_77994_a);
            lvt_5_1_ += lvt_8_2_;
            if (p_174925_3_ != 0) {
               var10000 = this.field_70462_a[lvt_6_3_];
               var10000.field_77994_a -= lvt_8_2_;
               if (this.field_70462_a[lvt_6_3_].field_77994_a == 0) {
                  this.field_70462_a[lvt_6_3_] = null;
               }

               if (p_174925_3_ > 0 && lvt_5_1_ >= p_174925_3_) {
                  return lvt_5_1_;
               }
            }
         }
      }

      for(lvt_6_3_ = 0; lvt_6_3_ < this.field_70460_b.length; ++lvt_6_3_) {
         lvt_7_2_ = this.field_70460_b[lvt_6_3_];
         if (lvt_7_2_ != null && (p_174925_1_ == null || lvt_7_2_.func_77973_b() == p_174925_1_) && (p_174925_2_ <= -1 || lvt_7_2_.func_77960_j() == p_174925_2_) && (p_174925_4_ == null || NBTUtil.func_181123_a(p_174925_4_, lvt_7_2_.func_77978_p(), false))) {
            lvt_8_2_ = p_174925_3_ <= 0 ? lvt_7_2_.field_77994_a : Math.min(p_174925_3_ - lvt_5_1_, lvt_7_2_.field_77994_a);
            lvt_5_1_ += lvt_8_2_;
            if (p_174925_3_ != 0) {
               var10000 = this.field_70460_b[lvt_6_3_];
               var10000.field_77994_a -= lvt_8_2_;
               if (this.field_70460_b[lvt_6_3_].field_77994_a == 0) {
                  this.field_70460_b[lvt_6_3_] = null;
               }

               if (p_174925_3_ > 0 && lvt_5_1_ >= p_174925_3_) {
                  return lvt_5_1_;
               }
            }
         }
      }

      if (this.field_70457_g != null) {
         if (p_174925_1_ != null && this.field_70457_g.func_77973_b() != p_174925_1_) {
            return lvt_5_1_;
         }

         if (p_174925_2_ > -1 && this.field_70457_g.func_77960_j() != p_174925_2_) {
            return lvt_5_1_;
         }

         if (p_174925_4_ != null && !NBTUtil.func_181123_a(p_174925_4_, this.field_70457_g.func_77978_p(), false)) {
            return lvt_5_1_;
         }

         lvt_6_3_ = p_174925_3_ <= 0 ? this.field_70457_g.field_77994_a : Math.min(p_174925_3_ - lvt_5_1_, this.field_70457_g.field_77994_a);
         lvt_5_1_ += lvt_6_3_;
         if (p_174925_3_ != 0) {
            var10000 = this.field_70457_g;
            var10000.field_77994_a -= lvt_6_3_;
            if (this.field_70457_g.field_77994_a == 0) {
               this.field_70457_g = null;
            }

            if (p_174925_3_ > 0 && lvt_5_1_ >= p_174925_3_) {
               return lvt_5_1_;
            }
         }
      }

      return lvt_5_1_;
   }

   private int func_70452_e(ItemStack p_70452_1_) {
      Item lvt_2_1_ = p_70452_1_.func_77973_b();
      int lvt_3_1_ = p_70452_1_.field_77994_a;
      int lvt_4_1_ = this.func_70432_d(p_70452_1_);
      if (lvt_4_1_ < 0) {
         lvt_4_1_ = this.func_70447_i();
      }

      if (lvt_4_1_ < 0) {
         return lvt_3_1_;
      } else {
         if (this.field_70462_a[lvt_4_1_] == null) {
            this.field_70462_a[lvt_4_1_] = new ItemStack(lvt_2_1_, 0, p_70452_1_.func_77960_j());
            if (p_70452_1_.func_77942_o()) {
               this.field_70462_a[lvt_4_1_].func_77982_d((NBTTagCompound)p_70452_1_.func_77978_p().func_74737_b());
            }
         }

         int lvt_5_1_ = lvt_3_1_;
         if (lvt_3_1_ > this.field_70462_a[lvt_4_1_].func_77976_d() - this.field_70462_a[lvt_4_1_].field_77994_a) {
            lvt_5_1_ = this.field_70462_a[lvt_4_1_].func_77976_d() - this.field_70462_a[lvt_4_1_].field_77994_a;
         }

         if (lvt_5_1_ > this.func_70297_j_() - this.field_70462_a[lvt_4_1_].field_77994_a) {
            lvt_5_1_ = this.func_70297_j_() - this.field_70462_a[lvt_4_1_].field_77994_a;
         }

         if (lvt_5_1_ == 0) {
            return lvt_3_1_;
         } else {
            lvt_3_1_ -= lvt_5_1_;
            ItemStack var10000 = this.field_70462_a[lvt_4_1_];
            var10000.field_77994_a += lvt_5_1_;
            this.field_70462_a[lvt_4_1_].field_77992_b = 5;
            return lvt_3_1_;
         }
      }
   }

   public void func_70429_k() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_70462_a.length; ++lvt_1_1_) {
         if (this.field_70462_a[lvt_1_1_] != null) {
            this.field_70462_a[lvt_1_1_].func_77945_a(this.field_70458_d.field_70170_p, this.field_70458_d, lvt_1_1_, this.field_70461_c == lvt_1_1_);
         }
      }

   }

   public boolean func_146026_a(Item p_146026_1_) {
      int lvt_2_1_ = this.func_146029_c(p_146026_1_);
      if (lvt_2_1_ < 0) {
         return false;
      } else {
         if (--this.field_70462_a[lvt_2_1_].field_77994_a <= 0) {
            this.field_70462_a[lvt_2_1_] = null;
         }

         return true;
      }
   }

   public boolean func_146028_b(Item p_146028_1_) {
      int lvt_2_1_ = this.func_146029_c(p_146028_1_);
      return lvt_2_1_ >= 0;
   }

   public boolean func_70441_a(final ItemStack p_70441_1_) {
      if (p_70441_1_ != null && p_70441_1_.field_77994_a != 0 && p_70441_1_.func_77973_b() != null) {
         try {
            int lvt_2_1_;
            if (p_70441_1_.func_77951_h()) {
               lvt_2_1_ = this.func_70447_i();
               if (lvt_2_1_ >= 0) {
                  this.field_70462_a[lvt_2_1_] = ItemStack.func_77944_b(p_70441_1_);
                  this.field_70462_a[lvt_2_1_].field_77992_b = 5;
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else if (this.field_70458_d.field_71075_bZ.field_75098_d) {
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else {
                  return false;
               }
            } else {
               do {
                  lvt_2_1_ = p_70441_1_.field_77994_a;
                  p_70441_1_.field_77994_a = this.func_70452_e(p_70441_1_);
               } while(p_70441_1_.field_77994_a > 0 && p_70441_1_.field_77994_a < lvt_2_1_);

               if (p_70441_1_.field_77994_a == lvt_2_1_ && this.field_70458_d.field_71075_bZ.field_75098_d) {
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else {
                  return p_70441_1_.field_77994_a < lvt_2_1_;
               }
            }
         } catch (Throwable var5) {
            CrashReport lvt_3_1_ = CrashReport.func_85055_a(var5, "Adding item to inventory");
            CrashReportCategory lvt_4_1_ = lvt_3_1_.func_85058_a("Item being added");
            lvt_4_1_.func_71507_a("Item ID", Item.func_150891_b(p_70441_1_.func_77973_b()));
            lvt_4_1_.func_71507_a("Item data", p_70441_1_.func_77960_j());
            lvt_4_1_.func_71500_a("Item name", new Callable<String>() {
               public String call() throws Exception {
                  return p_70441_1_.func_82833_r();
               }
            });
            throw new ReportedException(lvt_3_1_);
         }
      } else {
         return false;
      }
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      ItemStack[] lvt_3_1_ = this.field_70462_a;
      if (p_70298_1_ >= this.field_70462_a.length) {
         lvt_3_1_ = this.field_70460_b;
         p_70298_1_ -= this.field_70462_a.length;
      }

      if (lvt_3_1_[p_70298_1_] != null) {
         ItemStack lvt_4_2_;
         if (lvt_3_1_[p_70298_1_].field_77994_a <= p_70298_2_) {
            lvt_4_2_ = lvt_3_1_[p_70298_1_];
            lvt_3_1_[p_70298_1_] = null;
            return lvt_4_2_;
         } else {
            lvt_4_2_ = lvt_3_1_[p_70298_1_].func_77979_a(p_70298_2_);
            if (lvt_3_1_[p_70298_1_].field_77994_a == 0) {
               lvt_3_1_[p_70298_1_] = null;
            }

            return lvt_4_2_;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      ItemStack[] lvt_2_1_ = this.field_70462_a;
      if (p_70304_1_ >= this.field_70462_a.length) {
         lvt_2_1_ = this.field_70460_b;
         p_70304_1_ -= this.field_70462_a.length;
      }

      if (lvt_2_1_[p_70304_1_] != null) {
         ItemStack lvt_3_1_ = lvt_2_1_[p_70304_1_];
         lvt_2_1_[p_70304_1_] = null;
         return lvt_3_1_;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      ItemStack[] lvt_3_1_ = this.field_70462_a;
      if (p_70299_1_ >= lvt_3_1_.length) {
         p_70299_1_ -= lvt_3_1_.length;
         lvt_3_1_ = this.field_70460_b;
      }

      lvt_3_1_[p_70299_1_] = p_70299_2_;
   }

   public float func_146023_a(Block p_146023_1_) {
      float lvt_2_1_ = 1.0F;
      if (this.field_70462_a[this.field_70461_c] != null) {
         lvt_2_1_ *= this.field_70462_a[this.field_70461_c].func_150997_a(p_146023_1_);
      }

      return lvt_2_1_;
   }

   public NBTTagList func_70442_a(NBTTagList p_70442_1_) {
      int lvt_2_2_;
      NBTTagCompound lvt_3_2_;
      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_70462_a.length; ++lvt_2_2_) {
         if (this.field_70462_a[lvt_2_2_] != null) {
            lvt_3_2_ = new NBTTagCompound();
            lvt_3_2_.func_74774_a("Slot", (byte)lvt_2_2_);
            this.field_70462_a[lvt_2_2_].func_77955_b(lvt_3_2_);
            p_70442_1_.func_74742_a(lvt_3_2_);
         }
      }

      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_70460_b.length; ++lvt_2_2_) {
         if (this.field_70460_b[lvt_2_2_] != null) {
            lvt_3_2_ = new NBTTagCompound();
            lvt_3_2_.func_74774_a("Slot", (byte)(lvt_2_2_ + 100));
            this.field_70460_b[lvt_2_2_].func_77955_b(lvt_3_2_);
            p_70442_1_.func_74742_a(lvt_3_2_);
         }
      }

      return p_70442_1_;
   }

   public void func_70443_b(NBTTagList p_70443_1_) {
      this.field_70462_a = new ItemStack[36];
      this.field_70460_b = new ItemStack[4];

      for(int lvt_2_1_ = 0; lvt_2_1_ < p_70443_1_.func_74745_c(); ++lvt_2_1_) {
         NBTTagCompound lvt_3_1_ = p_70443_1_.func_150305_b(lvt_2_1_);
         int lvt_4_1_ = lvt_3_1_.func_74771_c("Slot") & 255;
         ItemStack lvt_5_1_ = ItemStack.func_77949_a(lvt_3_1_);
         if (lvt_5_1_ != null) {
            if (lvt_4_1_ >= 0 && lvt_4_1_ < this.field_70462_a.length) {
               this.field_70462_a[lvt_4_1_] = lvt_5_1_;
            }

            if (lvt_4_1_ >= 100 && lvt_4_1_ < this.field_70460_b.length + 100) {
               this.field_70460_b[lvt_4_1_ - 100] = lvt_5_1_;
            }
         }
      }

   }

   public int func_70302_i_() {
      return this.field_70462_a.length + 4;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      ItemStack[] lvt_2_1_ = this.field_70462_a;
      if (p_70301_1_ >= lvt_2_1_.length) {
         p_70301_1_ -= lvt_2_1_.length;
         lvt_2_1_ = this.field_70460_b;
      }

      return lvt_2_1_[p_70301_1_];
   }

   public String func_70005_c_() {
      return "container.inventory";
   }

   public boolean func_145818_k_() {
      return false;
   }

   public IChatComponent func_145748_c_() {
      return (IChatComponent)(this.func_145818_k_() ? new ChatComponentText(this.func_70005_c_()) : new ChatComponentTranslation(this.func_70005_c_(), new Object[0]));
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_146025_b(Block p_146025_1_) {
      if (p_146025_1_.func_149688_o().func_76229_l()) {
         return true;
      } else {
         ItemStack lvt_2_1_ = this.func_70301_a(this.field_70461_c);
         return lvt_2_1_ != null ? lvt_2_1_.func_150998_b(p_146025_1_) : false;
      }
   }

   public ItemStack func_70440_f(int p_70440_1_) {
      return this.field_70460_b[p_70440_1_];
   }

   public int func_70430_l() {
      int lvt_1_1_ = 0;

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_70460_b.length; ++lvt_2_1_) {
         if (this.field_70460_b[lvt_2_1_] != null && this.field_70460_b[lvt_2_1_].func_77973_b() instanceof ItemArmor) {
            int lvt_3_1_ = ((ItemArmor)this.field_70460_b[lvt_2_1_].func_77973_b()).field_77879_b;
            lvt_1_1_ += lvt_3_1_;
         }
      }

      return lvt_1_1_;
   }

   public void func_70449_g(float p_70449_1_) {
      p_70449_1_ /= 4.0F;
      if (p_70449_1_ < 1.0F) {
         p_70449_1_ = 1.0F;
      }

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_70460_b.length; ++lvt_2_1_) {
         if (this.field_70460_b[lvt_2_1_] != null && this.field_70460_b[lvt_2_1_].func_77973_b() instanceof ItemArmor) {
            this.field_70460_b[lvt_2_1_].func_77972_a((int)p_70449_1_, this.field_70458_d);
            if (this.field_70460_b[lvt_2_1_].field_77994_a == 0) {
               this.field_70460_b[lvt_2_1_] = null;
            }
         }
      }

   }

   public void func_70436_m() {
      int lvt_1_2_;
      for(lvt_1_2_ = 0; lvt_1_2_ < this.field_70462_a.length; ++lvt_1_2_) {
         if (this.field_70462_a[lvt_1_2_] != null) {
            this.field_70458_d.func_146097_a(this.field_70462_a[lvt_1_2_], true, false);
            this.field_70462_a[lvt_1_2_] = null;
         }
      }

      for(lvt_1_2_ = 0; lvt_1_2_ < this.field_70460_b.length; ++lvt_1_2_) {
         if (this.field_70460_b[lvt_1_2_] != null) {
            this.field_70458_d.func_146097_a(this.field_70460_b[lvt_1_2_], true, false);
            this.field_70460_b[lvt_1_2_] = null;
         }
      }

   }

   public void func_70296_d() {
      this.field_70459_e = true;
   }

   public void func_70437_b(ItemStack p_70437_1_) {
      this.field_70457_g = p_70437_1_;
   }

   public ItemStack func_70445_o() {
      return this.field_70457_g;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      if (this.field_70458_d.field_70128_L) {
         return false;
      } else {
         return p_70300_1_.func_70068_e(this.field_70458_d) <= 64.0D;
      }
   }

   public boolean func_70431_c(ItemStack p_70431_1_) {
      int lvt_2_2_;
      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_70460_b.length; ++lvt_2_2_) {
         if (this.field_70460_b[lvt_2_2_] != null && this.field_70460_b[lvt_2_2_].func_77969_a(p_70431_1_)) {
            return true;
         }
      }

      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_70462_a.length; ++lvt_2_2_) {
         if (this.field_70462_a[lvt_2_2_] != null && this.field_70462_a[lvt_2_2_].func_77969_a(p_70431_1_)) {
            return true;
         }
      }

      return false;
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   public void func_70455_b(InventoryPlayer p_70455_1_) {
      int lvt_2_2_;
      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_70462_a.length; ++lvt_2_2_) {
         this.field_70462_a[lvt_2_2_] = ItemStack.func_77944_b(p_70455_1_.field_70462_a[lvt_2_2_]);
      }

      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_70460_b.length; ++lvt_2_2_) {
         this.field_70460_b[lvt_2_2_] = ItemStack.func_77944_b(p_70455_1_.field_70460_b[lvt_2_2_]);
      }

      this.field_70461_c = p_70455_1_.field_70461_c;
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
      int lvt_1_2_;
      for(lvt_1_2_ = 0; lvt_1_2_ < this.field_70462_a.length; ++lvt_1_2_) {
         this.field_70462_a[lvt_1_2_] = null;
      }

      for(lvt_1_2_ = 0; lvt_1_2_ < this.field_70460_b.length; ++lvt_1_2_) {
         this.field_70460_b[lvt_1_2_] = null;
      }

   }
}
