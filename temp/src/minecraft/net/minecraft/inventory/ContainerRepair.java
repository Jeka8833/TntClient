package net.minecraft.inventory;

import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContainerRepair extends Container {
   private static final Logger field_148326_f = LogManager.getLogger();
   private IInventory field_82852_f;
   private IInventory field_82853_g;
   private World field_82860_h;
   private BlockPos field_178156_j;
   public int field_82854_e;
   private int field_82856_l;
   private String field_82857_m;
   private final EntityPlayer field_82855_n;

   public ContainerRepair(InventoryPlayer p_i45806_1_, World p_i45806_2_, EntityPlayer p_i45806_3_) {
      this(p_i45806_1_, p_i45806_2_, BlockPos.field_177992_a, p_i45806_3_);
   }

   public ContainerRepair(InventoryPlayer p_i45807_1_, final World p_i45807_2_, final BlockPos p_i45807_3_, EntityPlayer p_i45807_4_) {
      this.field_82852_f = new InventoryCraftResult();
      this.field_82853_g = new InventoryBasic("Repair", true, 2) {
         public void func_70296_d() {
            super.func_70296_d();
            ContainerRepair.this.func_75130_a(this);
         }
      };
      this.field_178156_j = p_i45807_3_;
      this.field_82860_h = p_i45807_2_;
      this.field_82855_n = p_i45807_4_;
      this.func_75146_a(new Slot(this.field_82853_g, 0, 27, 47));
      this.func_75146_a(new Slot(this.field_82853_g, 1, 76, 47));
      this.func_75146_a(new Slot(this.field_82852_f, 2, 134, 47) {
         public boolean func_75214_a(ItemStack p_75214_1_) {
            return false;
         }

         public boolean func_82869_a(EntityPlayer p_82869_1_) {
            return (p_82869_1_.field_71075_bZ.field_75098_d || p_82869_1_.field_71068_ca >= ContainerRepair.this.field_82854_e) && ContainerRepair.this.field_82854_e > 0 && this.func_75216_d();
         }

         public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
            if (!p_82870_1_.field_71075_bZ.field_75098_d) {
               p_82870_1_.func_82242_a(-ContainerRepair.this.field_82854_e);
            }

            ContainerRepair.this.field_82853_g.func_70299_a(0, (ItemStack)null);
            if (ContainerRepair.this.field_82856_l > 0) {
               ItemStack lvt_3_1_ = ContainerRepair.this.field_82853_g.func_70301_a(1);
               if (lvt_3_1_ != null && lvt_3_1_.field_77994_a > ContainerRepair.this.field_82856_l) {
                  lvt_3_1_.field_77994_a -= ContainerRepair.this.field_82856_l;
                  ContainerRepair.this.field_82853_g.func_70299_a(1, lvt_3_1_);
               } else {
                  ContainerRepair.this.field_82853_g.func_70299_a(1, (ItemStack)null);
               }
            } else {
               ContainerRepair.this.field_82853_g.func_70299_a(1, (ItemStack)null);
            }

            ContainerRepair.this.field_82854_e = 0;
            IBlockState lvt_3_2_ = p_i45807_2_.func_180495_p(p_i45807_3_);
            if (!p_82870_1_.field_71075_bZ.field_75098_d && !p_i45807_2_.field_72995_K && lvt_3_2_.func_177230_c() == Blocks.field_150467_bQ && p_82870_1_.func_70681_au().nextFloat() < 0.12F) {
               int lvt_4_1_ = (Integer)lvt_3_2_.func_177229_b(BlockAnvil.field_176505_b);
               ++lvt_4_1_;
               if (lvt_4_1_ > 2) {
                  p_i45807_2_.func_175698_g(p_i45807_3_);
                  p_i45807_2_.func_175718_b(1020, p_i45807_3_, 0);
               } else {
                  p_i45807_2_.func_180501_a(p_i45807_3_, lvt_3_2_.func_177226_a(BlockAnvil.field_176505_b, lvt_4_1_), 2);
                  p_i45807_2_.func_175718_b(1021, p_i45807_3_, 0);
               }
            } else if (!p_i45807_2_.field_72995_K) {
               p_i45807_2_.func_175718_b(1021, p_i45807_3_, 0);
            }

         }
      });

      int lvt_5_2_;
      for(lvt_5_2_ = 0; lvt_5_2_ < 3; ++lvt_5_2_) {
         for(int lvt_6_1_ = 0; lvt_6_1_ < 9; ++lvt_6_1_) {
            this.func_75146_a(new Slot(p_i45807_1_, lvt_6_1_ + lvt_5_2_ * 9 + 9, 8 + lvt_6_1_ * 18, 84 + lvt_5_2_ * 18));
         }
      }

      for(lvt_5_2_ = 0; lvt_5_2_ < 9; ++lvt_5_2_) {
         this.func_75146_a(new Slot(p_i45807_1_, lvt_5_2_, 8 + lvt_5_2_ * 18, 142));
      }

   }

   public void func_75130_a(IInventory p_75130_1_) {
      super.func_75130_a(p_75130_1_);
      if (p_75130_1_ == this.field_82853_g) {
         this.func_82848_d();
      }

   }

   public void func_82848_d() {
      int lvt_1_1_ = false;
      int lvt_2_1_ = true;
      int lvt_3_1_ = true;
      int lvt_4_1_ = true;
      int lvt_5_1_ = true;
      int lvt_6_1_ = true;
      int lvt_7_1_ = true;
      ItemStack lvt_8_1_ = this.field_82853_g.func_70301_a(0);
      this.field_82854_e = 1;
      int lvt_9_1_ = 0;
      int lvt_10_1_ = 0;
      int lvt_11_1_ = 0;
      if (lvt_8_1_ == null) {
         this.field_82852_f.func_70299_a(0, (ItemStack)null);
         this.field_82854_e = 0;
      } else {
         ItemStack lvt_12_1_ = lvt_8_1_.func_77946_l();
         ItemStack lvt_13_1_ = this.field_82853_g.func_70301_a(1);
         Map<Integer, Integer> lvt_14_1_ = EnchantmentHelper.func_82781_a(lvt_12_1_);
         boolean lvt_15_1_ = false;
         int lvt_10_1_ = lvt_10_1_ + lvt_8_1_.func_82838_A() + (lvt_13_1_ == null ? 0 : lvt_13_1_.func_82838_A());
         this.field_82856_l = 0;
         int lvt_16_4_;
         if (lvt_13_1_ != null) {
            lvt_15_1_ = lvt_13_1_.func_77973_b() == Items.field_151134_bR && Items.field_151134_bR.func_92110_g(lvt_13_1_).func_74745_c() > 0;
            int lvt_17_2_;
            int lvt_18_3_;
            if (lvt_12_1_.func_77984_f() && lvt_12_1_.func_77973_b().func_82789_a(lvt_8_1_, lvt_13_1_)) {
               lvt_16_4_ = Math.min(lvt_12_1_.func_77952_i(), lvt_12_1_.func_77958_k() / 4);
               if (lvt_16_4_ <= 0) {
                  this.field_82852_f.func_70299_a(0, (ItemStack)null);
                  this.field_82854_e = 0;
                  return;
               }

               for(lvt_17_2_ = 0; lvt_16_4_ > 0 && lvt_17_2_ < lvt_13_1_.field_77994_a; ++lvt_17_2_) {
                  lvt_18_3_ = lvt_12_1_.func_77952_i() - lvt_16_4_;
                  lvt_12_1_.func_77964_b(lvt_18_3_);
                  ++lvt_9_1_;
                  lvt_16_4_ = Math.min(lvt_12_1_.func_77952_i(), lvt_12_1_.func_77958_k() / 4);
               }

               this.field_82856_l = lvt_17_2_;
            } else {
               if (!lvt_15_1_ && (lvt_12_1_.func_77973_b() != lvt_13_1_.func_77973_b() || !lvt_12_1_.func_77984_f())) {
                  this.field_82852_f.func_70299_a(0, (ItemStack)null);
                  this.field_82854_e = 0;
                  return;
               }

               int lvt_20_2_;
               if (lvt_12_1_.func_77984_f() && !lvt_15_1_) {
                  lvt_16_4_ = lvt_8_1_.func_77958_k() - lvt_8_1_.func_77952_i();
                  lvt_17_2_ = lvt_13_1_.func_77958_k() - lvt_13_1_.func_77952_i();
                  lvt_18_3_ = lvt_17_2_ + lvt_12_1_.func_77958_k() * 12 / 100;
                  int lvt_19_1_ = lvt_16_4_ + lvt_18_3_;
                  lvt_20_2_ = lvt_12_1_.func_77958_k() - lvt_19_1_;
                  if (lvt_20_2_ < 0) {
                     lvt_20_2_ = 0;
                  }

                  if (lvt_20_2_ < lvt_12_1_.func_77960_j()) {
                     lvt_12_1_.func_77964_b(lvt_20_2_);
                     lvt_9_1_ += 2;
                  }
               }

               Map<Integer, Integer> lvt_16_3_ = EnchantmentHelper.func_82781_a(lvt_13_1_);
               Iterator lvt_17_3_ = lvt_16_3_.keySet().iterator();

               label144:
               while(true) {
                  Enchantment lvt_19_2_;
                  do {
                     if (!lvt_17_3_.hasNext()) {
                        break label144;
                     }

                     lvt_18_3_ = (Integer)lvt_17_3_.next();
                     lvt_19_2_ = Enchantment.func_180306_c(lvt_18_3_);
                  } while(lvt_19_2_ == null);

                  lvt_20_2_ = lvt_14_1_.containsKey(lvt_18_3_) ? (Integer)lvt_14_1_.get(lvt_18_3_) : 0;
                  int lvt_21_1_ = (Integer)lvt_16_3_.get(lvt_18_3_);
                  int var10000;
                  if (lvt_20_2_ == lvt_21_1_) {
                     ++lvt_21_1_;
                     var10000 = lvt_21_1_;
                  } else {
                     var10000 = Math.max(lvt_21_1_, lvt_20_2_);
                  }

                  lvt_21_1_ = var10000;
                  boolean lvt_22_1_ = lvt_19_2_.func_92089_a(lvt_8_1_);
                  if (this.field_82855_n.field_71075_bZ.field_75098_d || lvt_8_1_.func_77973_b() == Items.field_151134_bR) {
                     lvt_22_1_ = true;
                  }

                  Iterator lvt_23_1_ = lvt_14_1_.keySet().iterator();

                  while(lvt_23_1_.hasNext()) {
                     int lvt_24_1_ = (Integer)lvt_23_1_.next();
                     if (lvt_24_1_ != lvt_18_3_ && !lvt_19_2_.func_77326_a(Enchantment.func_180306_c(lvt_24_1_))) {
                        lvt_22_1_ = false;
                        ++lvt_9_1_;
                     }
                  }

                  if (lvt_22_1_) {
                     if (lvt_21_1_ > lvt_19_2_.func_77325_b()) {
                        lvt_21_1_ = lvt_19_2_.func_77325_b();
                     }

                     lvt_14_1_.put(lvt_18_3_, lvt_21_1_);
                     int lvt_23_2_ = 0;
                     switch(lvt_19_2_.func_77324_c()) {
                     case 1:
                        lvt_23_2_ = 8;
                        break;
                     case 2:
                        lvt_23_2_ = 4;
                     case 3:
                     case 4:
                     case 6:
                     case 7:
                     case 8:
                     case 9:
                     default:
                        break;
                     case 5:
                        lvt_23_2_ = 2;
                        break;
                     case 10:
                        lvt_23_2_ = 1;
                     }

                     if (lvt_15_1_) {
                        lvt_23_2_ = Math.max(1, lvt_23_2_ / 2);
                     }

                     lvt_9_1_ += lvt_23_2_ * lvt_21_1_;
                  }
               }
            }
         }

         if (StringUtils.isBlank(this.field_82857_m)) {
            if (lvt_8_1_.func_82837_s()) {
               lvt_11_1_ = 1;
               lvt_9_1_ += lvt_11_1_;
               lvt_12_1_.func_135074_t();
            }
         } else if (!this.field_82857_m.equals(lvt_8_1_.func_82833_r())) {
            lvt_11_1_ = 1;
            lvt_9_1_ += lvt_11_1_;
            lvt_12_1_.func_151001_c(this.field_82857_m);
         }

         this.field_82854_e = lvt_10_1_ + lvt_9_1_;
         if (lvt_9_1_ <= 0) {
            lvt_12_1_ = null;
         }

         if (lvt_11_1_ == lvt_9_1_ && lvt_11_1_ > 0 && this.field_82854_e >= 40) {
            this.field_82854_e = 39;
         }

         if (this.field_82854_e >= 40 && !this.field_82855_n.field_71075_bZ.field_75098_d) {
            lvt_12_1_ = null;
         }

         if (lvt_12_1_ != null) {
            lvt_16_4_ = lvt_12_1_.func_82838_A();
            if (lvt_13_1_ != null && lvt_16_4_ < lvt_13_1_.func_82838_A()) {
               lvt_16_4_ = lvt_13_1_.func_82838_A();
            }

            lvt_16_4_ = lvt_16_4_ * 2 + 1;
            lvt_12_1_.func_82841_c(lvt_16_4_);
            EnchantmentHelper.func_82782_a(lvt_14_1_, lvt_12_1_);
         }

         this.field_82852_f.func_70299_a(0, lvt_12_1_);
         this.func_75142_b();
      }
   }

   public void func_75132_a(ICrafting p_75132_1_) {
      super.func_75132_a(p_75132_1_);
      p_75132_1_.func_71112_a(this, 0, this.field_82854_e);
   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
      if (p_75137_1_ == 0) {
         this.field_82854_e = p_75137_2_;
      }

   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      super.func_75134_a(p_75134_1_);
      if (!this.field_82860_h.field_72995_K) {
         for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_82853_g.func_70302_i_(); ++lvt_2_1_) {
            ItemStack lvt_3_1_ = this.field_82853_g.func_70304_b(lvt_2_1_);
            if (lvt_3_1_ != null) {
               p_75134_1_.func_71019_a(lvt_3_1_, false);
            }
         }

      }
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      if (this.field_82860_h.func_180495_p(this.field_178156_j).func_177230_c() != Blocks.field_150467_bQ) {
         return false;
      } else {
         return p_75145_1_.func_70092_e((double)this.field_178156_j.func_177958_n() + 0.5D, (double)this.field_178156_j.func_177956_o() + 0.5D, (double)this.field_178156_j.func_177952_p() + 0.5D) <= 64.0D;
      }
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
         } else if (p_82846_2_ != 0 && p_82846_2_ != 1) {
            if (p_82846_2_ >= 3 && p_82846_2_ < 39 && !this.func_75135_a(lvt_5_1_, 0, 2, false)) {
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

   public void func_82850_a(String p_82850_1_) {
      this.field_82857_m = p_82850_1_;
      if (this.func_75139_a(2).func_75216_d()) {
         ItemStack lvt_2_1_ = this.func_75139_a(2).func_75211_c();
         if (StringUtils.isBlank(p_82850_1_)) {
            lvt_2_1_.func_135074_t();
         } else {
            lvt_2_1_.func_151001_c(this.field_82857_m);
         }
      }

      this.func_82848_d();
   }
}
