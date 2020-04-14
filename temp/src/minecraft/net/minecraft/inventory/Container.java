package net.minecraft.inventory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public abstract class Container {
   public List<ItemStack> field_75153_a = Lists.newArrayList();
   public List<Slot> field_75151_b = Lists.newArrayList();
   public int field_75152_c;
   private short field_75150_e;
   private int field_94535_f = -1;
   private int field_94536_g;
   private final Set<Slot> field_94537_h = Sets.newHashSet();
   protected List<ICrafting> field_75149_d = Lists.newArrayList();
   private Set<EntityPlayer> field_75148_f = Sets.newHashSet();

   protected Slot func_75146_a(Slot p_75146_1_) {
      p_75146_1_.field_75222_d = this.field_75151_b.size();
      this.field_75151_b.add(p_75146_1_);
      this.field_75153_a.add((Object)null);
      return p_75146_1_;
   }

   public void func_75132_a(ICrafting p_75132_1_) {
      if (this.field_75149_d.contains(p_75132_1_)) {
         throw new IllegalArgumentException("Listener already listening");
      } else {
         this.field_75149_d.add(p_75132_1_);
         p_75132_1_.func_71110_a(this, this.func_75138_a());
         this.func_75142_b();
      }
   }

   public void func_82847_b(ICrafting p_82847_1_) {
      this.field_75149_d.remove(p_82847_1_);
   }

   public List<ItemStack> func_75138_a() {
      List<ItemStack> lvt_1_1_ = Lists.newArrayList();

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_75151_b.size(); ++lvt_2_1_) {
         lvt_1_1_.add(((Slot)this.field_75151_b.get(lvt_2_1_)).func_75211_c());
      }

      return lvt_1_1_;
   }

   public void func_75142_b() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_75151_b.size(); ++lvt_1_1_) {
         ItemStack lvt_2_1_ = ((Slot)this.field_75151_b.get(lvt_1_1_)).func_75211_c();
         ItemStack lvt_3_1_ = (ItemStack)this.field_75153_a.get(lvt_1_1_);
         if (!ItemStack.func_77989_b(lvt_3_1_, lvt_2_1_)) {
            lvt_3_1_ = lvt_2_1_ == null ? null : lvt_2_1_.func_77946_l();
            this.field_75153_a.set(lvt_1_1_, lvt_3_1_);

            for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_75149_d.size(); ++lvt_4_1_) {
               ((ICrafting)this.field_75149_d.get(lvt_4_1_)).func_71111_a(this, lvt_1_1_, lvt_3_1_);
            }
         }
      }

   }

   public boolean func_75140_a(EntityPlayer p_75140_1_, int p_75140_2_) {
      return false;
   }

   public Slot func_75147_a(IInventory p_75147_1_, int p_75147_2_) {
      for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_75151_b.size(); ++lvt_3_1_) {
         Slot lvt_4_1_ = (Slot)this.field_75151_b.get(lvt_3_1_);
         if (lvt_4_1_.func_75217_a(p_75147_1_, p_75147_2_)) {
            return lvt_4_1_;
         }
      }

      return null;
   }

   public Slot func_75139_a(int p_75139_1_) {
      return (Slot)this.field_75151_b.get(p_75139_1_);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      Slot lvt_3_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      return lvt_3_1_ != null ? lvt_3_1_.func_75211_c() : null;
   }

   public ItemStack func_75144_a(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_) {
      ItemStack lvt_5_1_ = null;
      InventoryPlayer lvt_6_1_ = p_75144_4_.field_71071_by;
      int lvt_9_1_;
      ItemStack lvt_8_4_;
      if (p_75144_3_ == 5) {
         int lvt_7_1_ = this.field_94536_g;
         this.field_94536_g = func_94532_c(p_75144_2_);
         if ((lvt_7_1_ != 1 || this.field_94536_g != 2) && lvt_7_1_ != this.field_94536_g) {
            this.func_94533_d();
         } else if (lvt_6_1_.func_70445_o() == null) {
            this.func_94533_d();
         } else if (this.field_94536_g == 0) {
            this.field_94535_f = func_94529_b(p_75144_2_);
            if (func_180610_a(this.field_94535_f, p_75144_4_)) {
               this.field_94536_g = 1;
               this.field_94537_h.clear();
            } else {
               this.func_94533_d();
            }
         } else if (this.field_94536_g == 1) {
            Slot lvt_8_1_ = (Slot)this.field_75151_b.get(p_75144_1_);
            if (lvt_8_1_ != null && func_94527_a(lvt_8_1_, lvt_6_1_.func_70445_o(), true) && lvt_8_1_.func_75214_a(lvt_6_1_.func_70445_o()) && lvt_6_1_.func_70445_o().field_77994_a > this.field_94537_h.size() && this.func_94531_b(lvt_8_1_)) {
               this.field_94537_h.add(lvt_8_1_);
            }
         } else if (this.field_94536_g == 2) {
            if (!this.field_94537_h.isEmpty()) {
               lvt_8_4_ = lvt_6_1_.func_70445_o().func_77946_l();
               lvt_9_1_ = lvt_6_1_.func_70445_o().field_77994_a;
               Iterator lvt_10_1_ = this.field_94537_h.iterator();

               while(lvt_10_1_.hasNext()) {
                  Slot lvt_11_1_ = (Slot)lvt_10_1_.next();
                  if (lvt_11_1_ != null && func_94527_a(lvt_11_1_, lvt_6_1_.func_70445_o(), true) && lvt_11_1_.func_75214_a(lvt_6_1_.func_70445_o()) && lvt_6_1_.func_70445_o().field_77994_a >= this.field_94537_h.size() && this.func_94531_b(lvt_11_1_)) {
                     ItemStack lvt_12_1_ = lvt_8_4_.func_77946_l();
                     int lvt_13_1_ = lvt_11_1_.func_75216_d() ? lvt_11_1_.func_75211_c().field_77994_a : 0;
                     func_94525_a(this.field_94537_h, this.field_94535_f, lvt_12_1_, lvt_13_1_);
                     if (lvt_12_1_.field_77994_a > lvt_12_1_.func_77976_d()) {
                        lvt_12_1_.field_77994_a = lvt_12_1_.func_77976_d();
                     }

                     if (lvt_12_1_.field_77994_a > lvt_11_1_.func_178170_b(lvt_12_1_)) {
                        lvt_12_1_.field_77994_a = lvt_11_1_.func_178170_b(lvt_12_1_);
                     }

                     lvt_9_1_ -= lvt_12_1_.field_77994_a - lvt_13_1_;
                     lvt_11_1_.func_75215_d(lvt_12_1_);
                  }
               }

               lvt_8_4_.field_77994_a = lvt_9_1_;
               if (lvt_8_4_.field_77994_a <= 0) {
                  lvt_8_4_ = null;
               }

               lvt_6_1_.func_70437_b(lvt_8_4_);
            }

            this.func_94533_d();
         } else {
            this.func_94533_d();
         }
      } else if (this.field_94536_g != 0) {
         this.func_94533_d();
      } else {
         Slot lvt_7_3_;
         int lvt_10_4_;
         ItemStack lvt_11_2_;
         if ((p_75144_3_ == 0 || p_75144_3_ == 1) && (p_75144_2_ == 0 || p_75144_2_ == 1)) {
            if (p_75144_1_ == -999) {
               if (lvt_6_1_.func_70445_o() != null) {
                  if (p_75144_2_ == 0) {
                     p_75144_4_.func_71019_a(lvt_6_1_.func_70445_o(), true);
                     lvt_6_1_.func_70437_b((ItemStack)null);
                  }

                  if (p_75144_2_ == 1) {
                     p_75144_4_.func_71019_a(lvt_6_1_.func_70445_o().func_77979_a(1), true);
                     if (lvt_6_1_.func_70445_o().field_77994_a == 0) {
                        lvt_6_1_.func_70437_b((ItemStack)null);
                     }
                  }
               }
            } else if (p_75144_3_ == 1) {
               if (p_75144_1_ < 0) {
                  return null;
               }

               lvt_7_3_ = (Slot)this.field_75151_b.get(p_75144_1_);
               if (lvt_7_3_ != null && lvt_7_3_.func_82869_a(p_75144_4_)) {
                  lvt_8_4_ = this.func_82846_b(p_75144_4_, p_75144_1_);
                  if (lvt_8_4_ != null) {
                     Item lvt_9_2_ = lvt_8_4_.func_77973_b();
                     lvt_5_1_ = lvt_8_4_.func_77946_l();
                     if (lvt_7_3_.func_75211_c() != null && lvt_7_3_.func_75211_c().func_77973_b() == lvt_9_2_) {
                        this.func_75133_b(p_75144_1_, p_75144_2_, true, p_75144_4_);
                     }
                  }
               }
            } else {
               if (p_75144_1_ < 0) {
                  return null;
               }

               lvt_7_3_ = (Slot)this.field_75151_b.get(p_75144_1_);
               if (lvt_7_3_ != null) {
                  lvt_8_4_ = lvt_7_3_.func_75211_c();
                  ItemStack lvt_9_3_ = lvt_6_1_.func_70445_o();
                  if (lvt_8_4_ != null) {
                     lvt_5_1_ = lvt_8_4_.func_77946_l();
                  }

                  if (lvt_8_4_ == null) {
                     if (lvt_9_3_ != null && lvt_7_3_.func_75214_a(lvt_9_3_)) {
                        lvt_10_4_ = p_75144_2_ == 0 ? lvt_9_3_.field_77994_a : 1;
                        if (lvt_10_4_ > lvt_7_3_.func_178170_b(lvt_9_3_)) {
                           lvt_10_4_ = lvt_7_3_.func_178170_b(lvt_9_3_);
                        }

                        if (lvt_9_3_.field_77994_a >= lvt_10_4_) {
                           lvt_7_3_.func_75215_d(lvt_9_3_.func_77979_a(lvt_10_4_));
                        }

                        if (lvt_9_3_.field_77994_a == 0) {
                           lvt_6_1_.func_70437_b((ItemStack)null);
                        }
                     }
                  } else if (lvt_7_3_.func_82869_a(p_75144_4_)) {
                     if (lvt_9_3_ == null) {
                        lvt_10_4_ = p_75144_2_ == 0 ? lvt_8_4_.field_77994_a : (lvt_8_4_.field_77994_a + 1) / 2;
                        lvt_11_2_ = lvt_7_3_.func_75209_a(lvt_10_4_);
                        lvt_6_1_.func_70437_b(lvt_11_2_);
                        if (lvt_8_4_.field_77994_a == 0) {
                           lvt_7_3_.func_75215_d((ItemStack)null);
                        }

                        lvt_7_3_.func_82870_a(p_75144_4_, lvt_6_1_.func_70445_o());
                     } else if (lvt_7_3_.func_75214_a(lvt_9_3_)) {
                        if (lvt_8_4_.func_77973_b() == lvt_9_3_.func_77973_b() && lvt_8_4_.func_77960_j() == lvt_9_3_.func_77960_j() && ItemStack.func_77970_a(lvt_8_4_, lvt_9_3_)) {
                           lvt_10_4_ = p_75144_2_ == 0 ? lvt_9_3_.field_77994_a : 1;
                           if (lvt_10_4_ > lvt_7_3_.func_178170_b(lvt_9_3_) - lvt_8_4_.field_77994_a) {
                              lvt_10_4_ = lvt_7_3_.func_178170_b(lvt_9_3_) - lvt_8_4_.field_77994_a;
                           }

                           if (lvt_10_4_ > lvt_9_3_.func_77976_d() - lvt_8_4_.field_77994_a) {
                              lvt_10_4_ = lvt_9_3_.func_77976_d() - lvt_8_4_.field_77994_a;
                           }

                           lvt_9_3_.func_77979_a(lvt_10_4_);
                           if (lvt_9_3_.field_77994_a == 0) {
                              lvt_6_1_.func_70437_b((ItemStack)null);
                           }

                           lvt_8_4_.field_77994_a += lvt_10_4_;
                        } else if (lvt_9_3_.field_77994_a <= lvt_7_3_.func_178170_b(lvt_9_3_)) {
                           lvt_7_3_.func_75215_d(lvt_9_3_);
                           lvt_6_1_.func_70437_b(lvt_8_4_);
                        }
                     } else if (lvt_8_4_.func_77973_b() == lvt_9_3_.func_77973_b() && lvt_9_3_.func_77976_d() > 1 && (!lvt_8_4_.func_77981_g() || lvt_8_4_.func_77960_j() == lvt_9_3_.func_77960_j()) && ItemStack.func_77970_a(lvt_8_4_, lvt_9_3_)) {
                        lvt_10_4_ = lvt_8_4_.field_77994_a;
                        if (lvt_10_4_ > 0 && lvt_10_4_ + lvt_9_3_.field_77994_a <= lvt_9_3_.func_77976_d()) {
                           lvt_9_3_.field_77994_a += lvt_10_4_;
                           lvt_8_4_ = lvt_7_3_.func_75209_a(lvt_10_4_);
                           if (lvt_8_4_.field_77994_a == 0) {
                              lvt_7_3_.func_75215_d((ItemStack)null);
                           }

                           lvt_7_3_.func_82870_a(p_75144_4_, lvt_6_1_.func_70445_o());
                        }
                     }
                  }

                  lvt_7_3_.func_75218_e();
               }
            }
         } else if (p_75144_3_ == 2 && p_75144_2_ >= 0 && p_75144_2_ < 9) {
            lvt_7_3_ = (Slot)this.field_75151_b.get(p_75144_1_);
            if (lvt_7_3_.func_82869_a(p_75144_4_)) {
               lvt_8_4_ = lvt_6_1_.func_70301_a(p_75144_2_);
               boolean lvt_9_4_ = lvt_8_4_ == null || lvt_7_3_.field_75224_c == lvt_6_1_ && lvt_7_3_.func_75214_a(lvt_8_4_);
               lvt_10_4_ = -1;
               if (!lvt_9_4_) {
                  lvt_10_4_ = lvt_6_1_.func_70447_i();
                  lvt_9_4_ |= lvt_10_4_ > -1;
               }

               if (lvt_7_3_.func_75216_d() && lvt_9_4_) {
                  lvt_11_2_ = lvt_7_3_.func_75211_c();
                  lvt_6_1_.func_70299_a(p_75144_2_, lvt_11_2_.func_77946_l());
                  if ((lvt_7_3_.field_75224_c != lvt_6_1_ || !lvt_7_3_.func_75214_a(lvt_8_4_)) && lvt_8_4_ != null) {
                     if (lvt_10_4_ > -1) {
                        lvt_6_1_.func_70441_a(lvt_8_4_);
                        lvt_7_3_.func_75209_a(lvt_11_2_.field_77994_a);
                        lvt_7_3_.func_75215_d((ItemStack)null);
                        lvt_7_3_.func_82870_a(p_75144_4_, lvt_11_2_);
                     }
                  } else {
                     lvt_7_3_.func_75209_a(lvt_11_2_.field_77994_a);
                     lvt_7_3_.func_75215_d(lvt_8_4_);
                     lvt_7_3_.func_82870_a(p_75144_4_, lvt_11_2_);
                  }
               } else if (!lvt_7_3_.func_75216_d() && lvt_8_4_ != null && lvt_7_3_.func_75214_a(lvt_8_4_)) {
                  lvt_6_1_.func_70299_a(p_75144_2_, (ItemStack)null);
                  lvt_7_3_.func_75215_d(lvt_8_4_);
               }
            }
         } else if (p_75144_3_ == 3 && p_75144_4_.field_71075_bZ.field_75098_d && lvt_6_1_.func_70445_o() == null && p_75144_1_ >= 0) {
            lvt_7_3_ = (Slot)this.field_75151_b.get(p_75144_1_);
            if (lvt_7_3_ != null && lvt_7_3_.func_75216_d()) {
               lvt_8_4_ = lvt_7_3_.func_75211_c().func_77946_l();
               lvt_8_4_.field_77994_a = lvt_8_4_.func_77976_d();
               lvt_6_1_.func_70437_b(lvt_8_4_);
            }
         } else if (p_75144_3_ == 4 && lvt_6_1_.func_70445_o() == null && p_75144_1_ >= 0) {
            lvt_7_3_ = (Slot)this.field_75151_b.get(p_75144_1_);
            if (lvt_7_3_ != null && lvt_7_3_.func_75216_d() && lvt_7_3_.func_82869_a(p_75144_4_)) {
               lvt_8_4_ = lvt_7_3_.func_75209_a(p_75144_2_ == 0 ? 1 : lvt_7_3_.func_75211_c().field_77994_a);
               lvt_7_3_.func_82870_a(p_75144_4_, lvt_8_4_);
               p_75144_4_.func_71019_a(lvt_8_4_, true);
            }
         } else if (p_75144_3_ == 6 && p_75144_1_ >= 0) {
            lvt_7_3_ = (Slot)this.field_75151_b.get(p_75144_1_);
            lvt_8_4_ = lvt_6_1_.func_70445_o();
            if (lvt_8_4_ != null && (lvt_7_3_ == null || !lvt_7_3_.func_75216_d() || !lvt_7_3_.func_82869_a(p_75144_4_))) {
               lvt_9_1_ = p_75144_2_ == 0 ? 0 : this.field_75151_b.size() - 1;
               lvt_10_4_ = p_75144_2_ == 0 ? 1 : -1;

               for(int lvt_11_4_ = 0; lvt_11_4_ < 2; ++lvt_11_4_) {
                  for(int lvt_12_2_ = lvt_9_1_; lvt_12_2_ >= 0 && lvt_12_2_ < this.field_75151_b.size() && lvt_8_4_.field_77994_a < lvt_8_4_.func_77976_d(); lvt_12_2_ += lvt_10_4_) {
                     Slot lvt_13_2_ = (Slot)this.field_75151_b.get(lvt_12_2_);
                     if (lvt_13_2_.func_75216_d() && func_94527_a(lvt_13_2_, lvt_8_4_, true) && lvt_13_2_.func_82869_a(p_75144_4_) && this.func_94530_a(lvt_8_4_, lvt_13_2_) && (lvt_11_4_ != 0 || lvt_13_2_.func_75211_c().field_77994_a != lvt_13_2_.func_75211_c().func_77976_d())) {
                        int lvt_14_1_ = Math.min(lvt_8_4_.func_77976_d() - lvt_8_4_.field_77994_a, lvt_13_2_.func_75211_c().field_77994_a);
                        ItemStack lvt_15_1_ = lvt_13_2_.func_75209_a(lvt_14_1_);
                        lvt_8_4_.field_77994_a += lvt_14_1_;
                        if (lvt_15_1_.field_77994_a <= 0) {
                           lvt_13_2_.func_75215_d((ItemStack)null);
                        }

                        lvt_13_2_.func_82870_a(p_75144_4_, lvt_15_1_);
                     }
                  }
               }
            }

            this.func_75142_b();
         }
      }

      return lvt_5_1_;
   }

   public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
      return true;
   }

   protected void func_75133_b(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {
      this.func_75144_a(p_75133_1_, p_75133_2_, 1, p_75133_4_);
   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      InventoryPlayer lvt_2_1_ = p_75134_1_.field_71071_by;
      if (lvt_2_1_.func_70445_o() != null) {
         p_75134_1_.func_71019_a(lvt_2_1_.func_70445_o(), false);
         lvt_2_1_.func_70437_b((ItemStack)null);
      }

   }

   public void func_75130_a(IInventory p_75130_1_) {
      this.func_75142_b();
   }

   public void func_75141_a(int p_75141_1_, ItemStack p_75141_2_) {
      this.func_75139_a(p_75141_1_).func_75215_d(p_75141_2_);
   }

   public void func_75131_a(ItemStack[] p_75131_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < p_75131_1_.length; ++lvt_2_1_) {
         this.func_75139_a(lvt_2_1_).func_75215_d(p_75131_1_[lvt_2_1_]);
      }

   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
   }

   public short func_75136_a(InventoryPlayer p_75136_1_) {
      ++this.field_75150_e;
      return this.field_75150_e;
   }

   public boolean func_75129_b(EntityPlayer p_75129_1_) {
      return !this.field_75148_f.contains(p_75129_1_);
   }

   public void func_75128_a(EntityPlayer p_75128_1_, boolean p_75128_2_) {
      if (p_75128_2_) {
         this.field_75148_f.remove(p_75128_1_);
      } else {
         this.field_75148_f.add(p_75128_1_);
      }

   }

   public abstract boolean func_75145_c(EntityPlayer var1);

   protected boolean func_75135_a(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_) {
      boolean lvt_5_1_ = false;
      int lvt_6_1_ = p_75135_2_;
      if (p_75135_4_) {
         lvt_6_1_ = p_75135_3_ - 1;
      }

      Slot lvt_7_2_;
      ItemStack lvt_8_2_;
      if (p_75135_1_.func_77985_e()) {
         while(p_75135_1_.field_77994_a > 0 && (!p_75135_4_ && lvt_6_1_ < p_75135_3_ || p_75135_4_ && lvt_6_1_ >= p_75135_2_)) {
            lvt_7_2_ = (Slot)this.field_75151_b.get(lvt_6_1_);
            lvt_8_2_ = lvt_7_2_.func_75211_c();
            if (lvt_8_2_ != null && lvt_8_2_.func_77973_b() == p_75135_1_.func_77973_b() && (!p_75135_1_.func_77981_g() || p_75135_1_.func_77960_j() == lvt_8_2_.func_77960_j()) && ItemStack.func_77970_a(p_75135_1_, lvt_8_2_)) {
               int lvt_9_1_ = lvt_8_2_.field_77994_a + p_75135_1_.field_77994_a;
               if (lvt_9_1_ <= p_75135_1_.func_77976_d()) {
                  p_75135_1_.field_77994_a = 0;
                  lvt_8_2_.field_77994_a = lvt_9_1_;
                  lvt_7_2_.func_75218_e();
                  lvt_5_1_ = true;
               } else if (lvt_8_2_.field_77994_a < p_75135_1_.func_77976_d()) {
                  p_75135_1_.field_77994_a -= p_75135_1_.func_77976_d() - lvt_8_2_.field_77994_a;
                  lvt_8_2_.field_77994_a = p_75135_1_.func_77976_d();
                  lvt_7_2_.func_75218_e();
                  lvt_5_1_ = true;
               }
            }

            if (p_75135_4_) {
               --lvt_6_1_;
            } else {
               ++lvt_6_1_;
            }
         }
      }

      if (p_75135_1_.field_77994_a > 0) {
         if (p_75135_4_) {
            lvt_6_1_ = p_75135_3_ - 1;
         } else {
            lvt_6_1_ = p_75135_2_;
         }

         while(!p_75135_4_ && lvt_6_1_ < p_75135_3_ || p_75135_4_ && lvt_6_1_ >= p_75135_2_) {
            lvt_7_2_ = (Slot)this.field_75151_b.get(lvt_6_1_);
            lvt_8_2_ = lvt_7_2_.func_75211_c();
            if (lvt_8_2_ == null) {
               lvt_7_2_.func_75215_d(p_75135_1_.func_77946_l());
               lvt_7_2_.func_75218_e();
               p_75135_1_.field_77994_a = 0;
               lvt_5_1_ = true;
               break;
            }

            if (p_75135_4_) {
               --lvt_6_1_;
            } else {
               ++lvt_6_1_;
            }
         }
      }

      return lvt_5_1_;
   }

   public static int func_94529_b(int p_94529_0_) {
      return p_94529_0_ >> 2 & 3;
   }

   public static int func_94532_c(int p_94532_0_) {
      return p_94532_0_ & 3;
   }

   public static int func_94534_d(int p_94534_0_, int p_94534_1_) {
      return p_94534_0_ & 3 | (p_94534_1_ & 3) << 2;
   }

   public static boolean func_180610_a(int p_180610_0_, EntityPlayer p_180610_1_) {
      if (p_180610_0_ == 0) {
         return true;
      } else if (p_180610_0_ == 1) {
         return true;
      } else {
         return p_180610_0_ == 2 && p_180610_1_.field_71075_bZ.field_75098_d;
      }
   }

   protected void func_94533_d() {
      this.field_94536_g = 0;
      this.field_94537_h.clear();
   }

   public static boolean func_94527_a(Slot p_94527_0_, ItemStack p_94527_1_, boolean p_94527_2_) {
      boolean lvt_3_1_ = p_94527_0_ == null || !p_94527_0_.func_75216_d();
      if (p_94527_0_ != null && p_94527_0_.func_75216_d() && p_94527_1_ != null && p_94527_1_.func_77969_a(p_94527_0_.func_75211_c()) && ItemStack.func_77970_a(p_94527_0_.func_75211_c(), p_94527_1_)) {
         lvt_3_1_ |= p_94527_0_.func_75211_c().field_77994_a + (p_94527_2_ ? 0 : p_94527_1_.field_77994_a) <= p_94527_1_.func_77976_d();
      }

      return lvt_3_1_;
   }

   public static void func_94525_a(Set<Slot> p_94525_0_, int p_94525_1_, ItemStack p_94525_2_, int p_94525_3_) {
      switch(p_94525_1_) {
      case 0:
         p_94525_2_.field_77994_a = MathHelper.func_76141_d((float)p_94525_2_.field_77994_a / (float)p_94525_0_.size());
         break;
      case 1:
         p_94525_2_.field_77994_a = 1;
         break;
      case 2:
         p_94525_2_.field_77994_a = p_94525_2_.func_77973_b().func_77639_j();
      }

      p_94525_2_.field_77994_a += p_94525_3_;
   }

   public boolean func_94531_b(Slot p_94531_1_) {
      return true;
   }

   public static int func_178144_a(TileEntity p_178144_0_) {
      return p_178144_0_ instanceof IInventory ? func_94526_b((IInventory)p_178144_0_) : 0;
   }

   public static int func_94526_b(IInventory p_94526_0_) {
      if (p_94526_0_ == null) {
         return 0;
      } else {
         int lvt_1_1_ = 0;
         float lvt_2_1_ = 0.0F;

         for(int lvt_3_1_ = 0; lvt_3_1_ < p_94526_0_.func_70302_i_(); ++lvt_3_1_) {
            ItemStack lvt_4_1_ = p_94526_0_.func_70301_a(lvt_3_1_);
            if (lvt_4_1_ != null) {
               lvt_2_1_ += (float)lvt_4_1_.field_77994_a / (float)Math.min(p_94526_0_.func_70297_j_(), lvt_4_1_.func_77976_d());
               ++lvt_1_1_;
            }
         }

         lvt_2_1_ /= (float)p_94526_0_.func_70302_i_();
         return MathHelper.func_76141_d(lvt_2_1_ * 14.0F) + (lvt_1_1_ > 0 ? 1 : 0);
      }
   }
}
