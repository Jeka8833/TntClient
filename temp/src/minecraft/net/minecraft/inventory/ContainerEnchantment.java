package net.minecraft.inventory;

import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerEnchantment extends Container {
   public IInventory field_75168_e;
   private World field_75172_h;
   private BlockPos field_178150_j;
   private Random field_75169_l;
   public int field_178149_f;
   public int[] field_75167_g;
   public int[] field_178151_h;

   public ContainerEnchantment(InventoryPlayer p_i45797_1_, World p_i45797_2_) {
      this(p_i45797_1_, p_i45797_2_, BlockPos.field_177992_a);
   }

   public ContainerEnchantment(InventoryPlayer p_i45798_1_, World p_i45798_2_, BlockPos p_i45798_3_) {
      this.field_75168_e = new InventoryBasic("Enchant", true, 2) {
         public int func_70297_j_() {
            return 64;
         }

         public void func_70296_d() {
            super.func_70296_d();
            ContainerEnchantment.this.func_75130_a(this);
         }
      };
      this.field_75169_l = new Random();
      this.field_75167_g = new int[3];
      this.field_178151_h = new int[]{-1, -1, -1};
      this.field_75172_h = p_i45798_2_;
      this.field_178150_j = p_i45798_3_;
      this.field_178149_f = p_i45798_1_.field_70458_d.func_175138_ci();
      this.func_75146_a(new Slot(this.field_75168_e, 0, 15, 47) {
         public boolean func_75214_a(ItemStack p_75214_1_) {
            return true;
         }

         public int func_75219_a() {
            return 1;
         }
      });
      this.func_75146_a(new Slot(this.field_75168_e, 1, 35, 47) {
         public boolean func_75214_a(ItemStack p_75214_1_) {
            return p_75214_1_.func_77973_b() == Items.field_151100_aR && EnumDyeColor.func_176766_a(p_75214_1_.func_77960_j()) == EnumDyeColor.BLUE;
         }
      });

      int lvt_4_2_;
      for(lvt_4_2_ = 0; lvt_4_2_ < 3; ++lvt_4_2_) {
         for(int lvt_5_1_ = 0; lvt_5_1_ < 9; ++lvt_5_1_) {
            this.func_75146_a(new Slot(p_i45798_1_, lvt_5_1_ + lvt_4_2_ * 9 + 9, 8 + lvt_5_1_ * 18, 84 + lvt_4_2_ * 18));
         }
      }

      for(lvt_4_2_ = 0; lvt_4_2_ < 9; ++lvt_4_2_) {
         this.func_75146_a(new Slot(p_i45798_1_, lvt_4_2_, 8 + lvt_4_2_ * 18, 142));
      }

   }

   public void func_75132_a(ICrafting p_75132_1_) {
      super.func_75132_a(p_75132_1_);
      p_75132_1_.func_71112_a(this, 0, this.field_75167_g[0]);
      p_75132_1_.func_71112_a(this, 1, this.field_75167_g[1]);
      p_75132_1_.func_71112_a(this, 2, this.field_75167_g[2]);
      p_75132_1_.func_71112_a(this, 3, this.field_178149_f & -16);
      p_75132_1_.func_71112_a(this, 4, this.field_178151_h[0]);
      p_75132_1_.func_71112_a(this, 5, this.field_178151_h[1]);
      p_75132_1_.func_71112_a(this, 6, this.field_178151_h[2]);
   }

   public void func_75142_b() {
      super.func_75142_b();

      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_75149_d.size(); ++lvt_1_1_) {
         ICrafting lvt_2_1_ = (ICrafting)this.field_75149_d.get(lvt_1_1_);
         lvt_2_1_.func_71112_a(this, 0, this.field_75167_g[0]);
         lvt_2_1_.func_71112_a(this, 1, this.field_75167_g[1]);
         lvt_2_1_.func_71112_a(this, 2, this.field_75167_g[2]);
         lvt_2_1_.func_71112_a(this, 3, this.field_178149_f & -16);
         lvt_2_1_.func_71112_a(this, 4, this.field_178151_h[0]);
         lvt_2_1_.func_71112_a(this, 5, this.field_178151_h[1]);
         lvt_2_1_.func_71112_a(this, 6, this.field_178151_h[2]);
      }

   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
      if (p_75137_1_ >= 0 && p_75137_1_ <= 2) {
         this.field_75167_g[p_75137_1_] = p_75137_2_;
      } else if (p_75137_1_ == 3) {
         this.field_178149_f = p_75137_2_;
      } else if (p_75137_1_ >= 4 && p_75137_1_ <= 6) {
         this.field_178151_h[p_75137_1_ - 4] = p_75137_2_;
      } else {
         super.func_75137_b(p_75137_1_, p_75137_2_);
      }

   }

   public void func_75130_a(IInventory p_75130_1_) {
      if (p_75130_1_ == this.field_75168_e) {
         ItemStack lvt_2_1_ = p_75130_1_.func_70301_a(0);
         int lvt_3_2_;
         if (lvt_2_1_ != null && lvt_2_1_.func_77956_u()) {
            if (!this.field_75172_h.field_72995_K) {
               lvt_3_2_ = 0;

               int lvt_4_1_;
               for(lvt_4_1_ = -1; lvt_4_1_ <= 1; ++lvt_4_1_) {
                  for(int lvt_5_1_ = -1; lvt_5_1_ <= 1; ++lvt_5_1_) {
                     if ((lvt_4_1_ != 0 || lvt_5_1_ != 0) && this.field_75172_h.func_175623_d(this.field_178150_j.func_177982_a(lvt_5_1_, 0, lvt_4_1_)) && this.field_75172_h.func_175623_d(this.field_178150_j.func_177982_a(lvt_5_1_, 1, lvt_4_1_))) {
                        if (this.field_75172_h.func_180495_p(this.field_178150_j.func_177982_a(lvt_5_1_ * 2, 0, lvt_4_1_ * 2)).func_177230_c() == Blocks.field_150342_X) {
                           ++lvt_3_2_;
                        }

                        if (this.field_75172_h.func_180495_p(this.field_178150_j.func_177982_a(lvt_5_1_ * 2, 1, lvt_4_1_ * 2)).func_177230_c() == Blocks.field_150342_X) {
                           ++lvt_3_2_;
                        }

                        if (lvt_5_1_ != 0 && lvt_4_1_ != 0) {
                           if (this.field_75172_h.func_180495_p(this.field_178150_j.func_177982_a(lvt_5_1_ * 2, 0, lvt_4_1_)).func_177230_c() == Blocks.field_150342_X) {
                              ++lvt_3_2_;
                           }

                           if (this.field_75172_h.func_180495_p(this.field_178150_j.func_177982_a(lvt_5_1_ * 2, 1, lvt_4_1_)).func_177230_c() == Blocks.field_150342_X) {
                              ++lvt_3_2_;
                           }

                           if (this.field_75172_h.func_180495_p(this.field_178150_j.func_177982_a(lvt_5_1_, 0, lvt_4_1_ * 2)).func_177230_c() == Blocks.field_150342_X) {
                              ++lvt_3_2_;
                           }

                           if (this.field_75172_h.func_180495_p(this.field_178150_j.func_177982_a(lvt_5_1_, 1, lvt_4_1_ * 2)).func_177230_c() == Blocks.field_150342_X) {
                              ++lvt_3_2_;
                           }
                        }
                     }
                  }
               }

               this.field_75169_l.setSeed((long)this.field_178149_f);

               for(lvt_4_1_ = 0; lvt_4_1_ < 3; ++lvt_4_1_) {
                  this.field_75167_g[lvt_4_1_] = EnchantmentHelper.func_77514_a(this.field_75169_l, lvt_4_1_, lvt_3_2_, lvt_2_1_);
                  this.field_178151_h[lvt_4_1_] = -1;
                  if (this.field_75167_g[lvt_4_1_] < lvt_4_1_ + 1) {
                     this.field_75167_g[lvt_4_1_] = 0;
                  }
               }

               for(lvt_4_1_ = 0; lvt_4_1_ < 3; ++lvt_4_1_) {
                  if (this.field_75167_g[lvt_4_1_] > 0) {
                     List<EnchantmentData> lvt_5_2_ = this.func_178148_a(lvt_2_1_, lvt_4_1_, this.field_75167_g[lvt_4_1_]);
                     if (lvt_5_2_ != null && !lvt_5_2_.isEmpty()) {
                        EnchantmentData lvt_6_1_ = (EnchantmentData)lvt_5_2_.get(this.field_75169_l.nextInt(lvt_5_2_.size()));
                        this.field_178151_h[lvt_4_1_] = lvt_6_1_.field_76302_b.field_77352_x | lvt_6_1_.field_76303_c << 8;
                     }
                  }
               }

               this.func_75142_b();
            }
         } else {
            for(lvt_3_2_ = 0; lvt_3_2_ < 3; ++lvt_3_2_) {
               this.field_75167_g[lvt_3_2_] = 0;
               this.field_178151_h[lvt_3_2_] = -1;
            }
         }
      }

   }

   public boolean func_75140_a(EntityPlayer p_75140_1_, int p_75140_2_) {
      ItemStack lvt_3_1_ = this.field_75168_e.func_70301_a(0);
      ItemStack lvt_4_1_ = this.field_75168_e.func_70301_a(1);
      int lvt_5_1_ = p_75140_2_ + 1;
      if ((lvt_4_1_ == null || lvt_4_1_.field_77994_a < lvt_5_1_) && !p_75140_1_.field_71075_bZ.field_75098_d) {
         return false;
      } else if (this.field_75167_g[p_75140_2_] <= 0 || lvt_3_1_ == null || (p_75140_1_.field_71068_ca < lvt_5_1_ || p_75140_1_.field_71068_ca < this.field_75167_g[p_75140_2_]) && !p_75140_1_.field_71075_bZ.field_75098_d) {
         return false;
      } else {
         if (!this.field_75172_h.field_72995_K) {
            List<EnchantmentData> lvt_6_1_ = this.func_178148_a(lvt_3_1_, p_75140_2_, this.field_75167_g[p_75140_2_]);
            boolean lvt_7_1_ = lvt_3_1_.func_77973_b() == Items.field_151122_aG;
            if (lvt_6_1_ != null) {
               p_75140_1_.func_71013_b(lvt_5_1_);
               if (lvt_7_1_) {
                  lvt_3_1_.func_150996_a(Items.field_151134_bR);
               }

               for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_6_1_.size(); ++lvt_8_1_) {
                  EnchantmentData lvt_9_1_ = (EnchantmentData)lvt_6_1_.get(lvt_8_1_);
                  if (lvt_7_1_) {
                     Items.field_151134_bR.func_92115_a(lvt_3_1_, lvt_9_1_);
                  } else {
                     lvt_3_1_.func_77966_a(lvt_9_1_.field_76302_b, lvt_9_1_.field_76303_c);
                  }
               }

               if (!p_75140_1_.field_71075_bZ.field_75098_d) {
                  lvt_4_1_.field_77994_a -= lvt_5_1_;
                  if (lvt_4_1_.field_77994_a <= 0) {
                     this.field_75168_e.func_70299_a(1, (ItemStack)null);
                  }
               }

               p_75140_1_.func_71029_a(StatList.field_181739_W);
               this.field_75168_e.func_70296_d();
               this.field_178149_f = p_75140_1_.func_175138_ci();
               this.func_75130_a(this.field_75168_e);
            }
         }

         return true;
      }
   }

   private List<EnchantmentData> func_178148_a(ItemStack p_178148_1_, int p_178148_2_, int p_178148_3_) {
      this.field_75169_l.setSeed((long)(this.field_178149_f + p_178148_2_));
      List<EnchantmentData> lvt_4_1_ = EnchantmentHelper.func_77513_b(this.field_75169_l, p_178148_1_, p_178148_3_);
      if (p_178148_1_.func_77973_b() == Items.field_151122_aG && lvt_4_1_ != null && lvt_4_1_.size() > 1) {
         lvt_4_1_.remove(this.field_75169_l.nextInt(lvt_4_1_.size()));
      }

      return lvt_4_1_;
   }

   public int func_178147_e() {
      ItemStack lvt_1_1_ = this.field_75168_e.func_70301_a(1);
      return lvt_1_1_ == null ? 0 : lvt_1_1_.field_77994_a;
   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      super.func_75134_a(p_75134_1_);
      if (!this.field_75172_h.field_72995_K) {
         for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_75168_e.func_70302_i_(); ++lvt_2_1_) {
            ItemStack lvt_3_1_ = this.field_75168_e.func_70304_b(lvt_2_1_);
            if (lvt_3_1_ != null) {
               p_75134_1_.func_71019_a(lvt_3_1_, false);
            }
         }

      }
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      if (this.field_75172_h.func_180495_p(this.field_178150_j).func_177230_c() != Blocks.field_150381_bn) {
         return false;
      } else {
         return p_75145_1_.func_70092_e((double)this.field_178150_j.func_177958_n() + 0.5D, (double)this.field_178150_j.func_177956_o() + 0.5D, (double)this.field_178150_j.func_177952_p() + 0.5D) <= 64.0D;
      }
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack lvt_3_1_ = null;
      Slot lvt_4_1_ = (Slot)this.field_75151_b.get(p_82846_2_);
      if (lvt_4_1_ != null && lvt_4_1_.func_75216_d()) {
         ItemStack lvt_5_1_ = lvt_4_1_.func_75211_c();
         lvt_3_1_ = lvt_5_1_.func_77946_l();
         if (p_82846_2_ == 0) {
            if (!this.func_75135_a(lvt_5_1_, 2, 38, true)) {
               return null;
            }
         } else if (p_82846_2_ == 1) {
            if (!this.func_75135_a(lvt_5_1_, 2, 38, true)) {
               return null;
            }
         } else if (lvt_5_1_.func_77973_b() == Items.field_151100_aR && EnumDyeColor.func_176766_a(lvt_5_1_.func_77960_j()) == EnumDyeColor.BLUE) {
            if (!this.func_75135_a(lvt_5_1_, 1, 2, true)) {
               return null;
            }
         } else {
            if (((Slot)this.field_75151_b.get(0)).func_75216_d() || !((Slot)this.field_75151_b.get(0)).func_75214_a(lvt_5_1_)) {
               return null;
            }

            if (lvt_5_1_.func_77942_o() && lvt_5_1_.field_77994_a == 1) {
               ((Slot)this.field_75151_b.get(0)).func_75215_d(lvt_5_1_.func_77946_l());
               lvt_5_1_.field_77994_a = 0;
            } else if (lvt_5_1_.field_77994_a >= 1) {
               ((Slot)this.field_75151_b.get(0)).func_75215_d(new ItemStack(lvt_5_1_.func_77973_b(), 1, lvt_5_1_.func_77960_j()));
               --lvt_5_1_.field_77994_a;
            }
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
