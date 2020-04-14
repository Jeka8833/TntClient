package net.minecraft.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.StatCollector;

public class ItemFireworkCharge extends Item {
   public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
      if (p_82790_2_ != 1) {
         return super.func_82790_a(p_82790_1_, p_82790_2_);
      } else {
         NBTBase lvt_3_1_ = func_150903_a(p_82790_1_, "Colors");
         if (!(lvt_3_1_ instanceof NBTTagIntArray)) {
            return 9079434;
         } else {
            NBTTagIntArray lvt_4_1_ = (NBTTagIntArray)lvt_3_1_;
            int[] lvt_5_1_ = lvt_4_1_.func_150302_c();
            if (lvt_5_1_.length == 1) {
               return lvt_5_1_[0];
            } else {
               int lvt_6_1_ = 0;
               int lvt_7_1_ = 0;
               int lvt_8_1_ = 0;
               int[] lvt_9_1_ = lvt_5_1_;
               int lvt_10_1_ = lvt_5_1_.length;

               for(int lvt_11_1_ = 0; lvt_11_1_ < lvt_10_1_; ++lvt_11_1_) {
                  int lvt_12_1_ = lvt_9_1_[lvt_11_1_];
                  lvt_6_1_ += (lvt_12_1_ & 16711680) >> 16;
                  lvt_7_1_ += (lvt_12_1_ & '\uff00') >> 8;
                  lvt_8_1_ += (lvt_12_1_ & 255) >> 0;
               }

               lvt_6_1_ /= lvt_5_1_.length;
               lvt_7_1_ /= lvt_5_1_.length;
               lvt_8_1_ /= lvt_5_1_.length;
               return lvt_6_1_ << 16 | lvt_7_1_ << 8 | lvt_8_1_;
            }
         }
      }
   }

   public static NBTBase func_150903_a(ItemStack p_150903_0_, String p_150903_1_) {
      if (p_150903_0_.func_77942_o()) {
         NBTTagCompound lvt_2_1_ = p_150903_0_.func_77978_p().func_74775_l("Explosion");
         if (lvt_2_1_ != null) {
            return lvt_2_1_.func_74781_a(p_150903_1_);
         }
      }

      return null;
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      if (p_77624_1_.func_77942_o()) {
         NBTTagCompound lvt_5_1_ = p_77624_1_.func_77978_p().func_74775_l("Explosion");
         if (lvt_5_1_ != null) {
            func_150902_a(lvt_5_1_, p_77624_3_);
         }
      }

   }

   public static void func_150902_a(NBTTagCompound p_150902_0_, List<String> p_150902_1_) {
      byte lvt_2_1_ = p_150902_0_.func_74771_c("Type");
      if (lvt_2_1_ >= 0 && lvt_2_1_ <= 4) {
         p_150902_1_.add(StatCollector.func_74838_a("item.fireworksCharge.type." + lvt_2_1_).trim());
      } else {
         p_150902_1_.add(StatCollector.func_74838_a("item.fireworksCharge.type").trim());
      }

      int[] lvt_3_1_ = p_150902_0_.func_74759_k("Colors");
      int lvt_8_2_;
      int lvt_9_1_;
      if (lvt_3_1_.length > 0) {
         boolean lvt_4_1_ = true;
         String lvt_5_1_ = "";
         int[] lvt_6_1_ = lvt_3_1_;
         int lvt_7_1_ = lvt_3_1_.length;

         for(lvt_8_2_ = 0; lvt_8_2_ < lvt_7_1_; ++lvt_8_2_) {
            lvt_9_1_ = lvt_6_1_[lvt_8_2_];
            if (!lvt_4_1_) {
               lvt_5_1_ = lvt_5_1_ + ", ";
            }

            lvt_4_1_ = false;
            boolean lvt_10_1_ = false;

            for(int lvt_11_1_ = 0; lvt_11_1_ < ItemDye.field_150922_c.length; ++lvt_11_1_) {
               if (lvt_9_1_ == ItemDye.field_150922_c[lvt_11_1_]) {
                  lvt_10_1_ = true;
                  lvt_5_1_ = lvt_5_1_ + StatCollector.func_74838_a("item.fireworksCharge." + EnumDyeColor.func_176766_a(lvt_11_1_).func_176762_d());
                  break;
               }
            }

            if (!lvt_10_1_) {
               lvt_5_1_ = lvt_5_1_ + StatCollector.func_74838_a("item.fireworksCharge.customColor");
            }
         }

         p_150902_1_.add(lvt_5_1_);
      }

      int[] lvt_4_2_ = p_150902_0_.func_74759_k("FadeColors");
      boolean lvt_5_2_;
      if (lvt_4_2_.length > 0) {
         lvt_5_2_ = true;
         String lvt_6_2_ = StatCollector.func_74838_a("item.fireworksCharge.fadeTo") + " ";
         int[] lvt_7_2_ = lvt_4_2_;
         lvt_8_2_ = lvt_4_2_.length;

         for(lvt_9_1_ = 0; lvt_9_1_ < lvt_8_2_; ++lvt_9_1_) {
            int lvt_10_2_ = lvt_7_2_[lvt_9_1_];
            if (!lvt_5_2_) {
               lvt_6_2_ = lvt_6_2_ + ", ";
            }

            lvt_5_2_ = false;
            boolean lvt_11_2_ = false;

            for(int lvt_12_1_ = 0; lvt_12_1_ < 16; ++lvt_12_1_) {
               if (lvt_10_2_ == ItemDye.field_150922_c[lvt_12_1_]) {
                  lvt_11_2_ = true;
                  lvt_6_2_ = lvt_6_2_ + StatCollector.func_74838_a("item.fireworksCharge." + EnumDyeColor.func_176766_a(lvt_12_1_).func_176762_d());
                  break;
               }
            }

            if (!lvt_11_2_) {
               lvt_6_2_ = lvt_6_2_ + StatCollector.func_74838_a("item.fireworksCharge.customColor");
            }
         }

         p_150902_1_.add(lvt_6_2_);
      }

      lvt_5_2_ = p_150902_0_.func_74767_n("Trail");
      if (lvt_5_2_) {
         p_150902_1_.add(StatCollector.func_74838_a("item.fireworksCharge.trail"));
      }

      boolean lvt_6_3_ = p_150902_0_.func_74767_n("Flicker");
      if (lvt_6_3_) {
         p_150902_1_.add(StatCollector.func_74838_a("item.fireworksCharge.flicker"));
      }

   }
}
