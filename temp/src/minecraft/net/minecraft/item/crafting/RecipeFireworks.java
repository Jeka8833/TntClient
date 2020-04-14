package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RecipeFireworks implements IRecipe {
   private ItemStack field_92102_a;

   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      this.field_92102_a = null;
      int lvt_3_1_ = 0;
      int lvt_4_1_ = 0;
      int lvt_5_1_ = 0;
      int lvt_6_1_ = 0;
      int lvt_7_1_ = 0;
      int lvt_8_1_ = 0;

      for(int lvt_9_1_ = 0; lvt_9_1_ < p_77569_1_.func_70302_i_(); ++lvt_9_1_) {
         ItemStack lvt_10_1_ = p_77569_1_.func_70301_a(lvt_9_1_);
         if (lvt_10_1_ != null) {
            if (lvt_10_1_.func_77973_b() == Items.field_151016_H) {
               ++lvt_4_1_;
            } else if (lvt_10_1_.func_77973_b() == Items.field_151154_bQ) {
               ++lvt_6_1_;
            } else if (lvt_10_1_.func_77973_b() == Items.field_151100_aR) {
               ++lvt_5_1_;
            } else if (lvt_10_1_.func_77973_b() == Items.field_151121_aF) {
               ++lvt_3_1_;
            } else if (lvt_10_1_.func_77973_b() == Items.field_151114_aO) {
               ++lvt_7_1_;
            } else if (lvt_10_1_.func_77973_b() == Items.field_151045_i) {
               ++lvt_7_1_;
            } else if (lvt_10_1_.func_77973_b() == Items.field_151059_bz) {
               ++lvt_8_1_;
            } else if (lvt_10_1_.func_77973_b() == Items.field_151008_G) {
               ++lvt_8_1_;
            } else if (lvt_10_1_.func_77973_b() == Items.field_151074_bl) {
               ++lvt_8_1_;
            } else {
               if (lvt_10_1_.func_77973_b() != Items.field_151144_bL) {
                  return false;
               }

               ++lvt_8_1_;
            }
         }
      }

      lvt_7_1_ += lvt_5_1_ + lvt_8_1_;
      if (lvt_4_1_ <= 3 && lvt_3_1_ <= 1) {
         NBTTagCompound lvt_9_3_;
         NBTTagCompound lvt_10_3_;
         if (lvt_4_1_ >= 1 && lvt_3_1_ == 1 && lvt_7_1_ == 0) {
            this.field_92102_a = new ItemStack(Items.field_151152_bP);
            if (lvt_6_1_ > 0) {
               lvt_9_3_ = new NBTTagCompound();
               lvt_10_3_ = new NBTTagCompound();
               NBTTagList lvt_11_1_ = new NBTTagList();

               for(int lvt_12_1_ = 0; lvt_12_1_ < p_77569_1_.func_70302_i_(); ++lvt_12_1_) {
                  ItemStack lvt_13_1_ = p_77569_1_.func_70301_a(lvt_12_1_);
                  if (lvt_13_1_ != null && lvt_13_1_.func_77973_b() == Items.field_151154_bQ && lvt_13_1_.func_77942_o() && lvt_13_1_.func_77978_p().func_150297_b("Explosion", 10)) {
                     lvt_11_1_.func_74742_a(lvt_13_1_.func_77978_p().func_74775_l("Explosion"));
                  }
               }

               lvt_10_3_.func_74782_a("Explosions", lvt_11_1_);
               lvt_10_3_.func_74774_a("Flight", (byte)lvt_4_1_);
               lvt_9_3_.func_74782_a("Fireworks", lvt_10_3_);
               this.field_92102_a.func_77982_d(lvt_9_3_);
            }

            return true;
         } else if (lvt_4_1_ == 1 && lvt_3_1_ == 0 && lvt_6_1_ == 0 && lvt_5_1_ > 0 && lvt_8_1_ <= 1) {
            this.field_92102_a = new ItemStack(Items.field_151154_bQ);
            lvt_9_3_ = new NBTTagCompound();
            lvt_10_3_ = new NBTTagCompound();
            byte lvt_11_2_ = 0;
            List<Integer> lvt_12_2_ = Lists.newArrayList();

            for(int lvt_13_2_ = 0; lvt_13_2_ < p_77569_1_.func_70302_i_(); ++lvt_13_2_) {
               ItemStack lvt_14_1_ = p_77569_1_.func_70301_a(lvt_13_2_);
               if (lvt_14_1_ != null) {
                  if (lvt_14_1_.func_77973_b() == Items.field_151100_aR) {
                     lvt_12_2_.add(ItemDye.field_150922_c[lvt_14_1_.func_77960_j() & 15]);
                  } else if (lvt_14_1_.func_77973_b() == Items.field_151114_aO) {
                     lvt_10_3_.func_74757_a("Flicker", true);
                  } else if (lvt_14_1_.func_77973_b() == Items.field_151045_i) {
                     lvt_10_3_.func_74757_a("Trail", true);
                  } else if (lvt_14_1_.func_77973_b() == Items.field_151059_bz) {
                     lvt_11_2_ = 1;
                  } else if (lvt_14_1_.func_77973_b() == Items.field_151008_G) {
                     lvt_11_2_ = 4;
                  } else if (lvt_14_1_.func_77973_b() == Items.field_151074_bl) {
                     lvt_11_2_ = 2;
                  } else if (lvt_14_1_.func_77973_b() == Items.field_151144_bL) {
                     lvt_11_2_ = 3;
                  }
               }
            }

            int[] lvt_13_3_ = new int[lvt_12_2_.size()];

            for(int lvt_14_2_ = 0; lvt_14_2_ < lvt_13_3_.length; ++lvt_14_2_) {
               lvt_13_3_[lvt_14_2_] = (Integer)lvt_12_2_.get(lvt_14_2_);
            }

            lvt_10_3_.func_74783_a("Colors", lvt_13_3_);
            lvt_10_3_.func_74774_a("Type", lvt_11_2_);
            lvt_9_3_.func_74782_a("Explosion", lvt_10_3_);
            this.field_92102_a.func_77982_d(lvt_9_3_);
            return true;
         } else if (lvt_4_1_ == 0 && lvt_3_1_ == 0 && lvt_6_1_ == 1 && lvt_5_1_ > 0 && lvt_5_1_ == lvt_7_1_) {
            List<Integer> lvt_9_4_ = Lists.newArrayList();

            for(int lvt_10_4_ = 0; lvt_10_4_ < p_77569_1_.func_70302_i_(); ++lvt_10_4_) {
               ItemStack lvt_11_3_ = p_77569_1_.func_70301_a(lvt_10_4_);
               if (lvt_11_3_ != null) {
                  if (lvt_11_3_.func_77973_b() == Items.field_151100_aR) {
                     lvt_9_4_.add(ItemDye.field_150922_c[lvt_11_3_.func_77960_j() & 15]);
                  } else if (lvt_11_3_.func_77973_b() == Items.field_151154_bQ) {
                     this.field_92102_a = lvt_11_3_.func_77946_l();
                     this.field_92102_a.field_77994_a = 1;
                  }
               }
            }

            int[] lvt_10_5_ = new int[lvt_9_4_.size()];

            for(int lvt_11_4_ = 0; lvt_11_4_ < lvt_10_5_.length; ++lvt_11_4_) {
               lvt_10_5_[lvt_11_4_] = (Integer)lvt_9_4_.get(lvt_11_4_);
            }

            if (this.field_92102_a != null && this.field_92102_a.func_77942_o()) {
               NBTTagCompound lvt_11_5_ = this.field_92102_a.func_77978_p().func_74775_l("Explosion");
               if (lvt_11_5_ == null) {
                  return false;
               } else {
                  lvt_11_5_.func_74783_a("FadeColors", lvt_10_5_);
                  return true;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      return this.field_92102_a.func_77946_l();
   }

   public int func_77570_a() {
      return 10;
   }

   public ItemStack func_77571_b() {
      return this.field_92102_a;
   }

   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
      ItemStack[] lvt_2_1_ = new ItemStack[p_179532_1_.func_70302_i_()];

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.length; ++lvt_3_1_) {
         ItemStack lvt_4_1_ = p_179532_1_.func_70301_a(lvt_3_1_);
         if (lvt_4_1_ != null && lvt_4_1_.func_77973_b().func_77634_r()) {
            lvt_2_1_[lvt_3_1_] = new ItemStack(lvt_4_1_.func_77973_b().func_77668_q());
         }
      }

      return lvt_2_1_;
   }
}
