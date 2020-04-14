package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class GuiUtilRenderComponents {
   public static String func_178909_a(String p_178909_0_, boolean p_178909_1_) {
      return !p_178909_1_ && !Minecraft.func_71410_x().field_71474_y.field_74344_o ? EnumChatFormatting.func_110646_a(p_178909_0_) : p_178909_0_;
   }

   public static List<IChatComponent> func_178908_a(IChatComponent p_178908_0_, int p_178908_1_, FontRenderer p_178908_2_, boolean p_178908_3_, boolean p_178908_4_) {
      int lvt_5_1_ = 0;
      IChatComponent lvt_6_1_ = new ChatComponentText("");
      List<IChatComponent> lvt_7_1_ = Lists.newArrayList();
      List<IChatComponent> lvt_8_1_ = Lists.newArrayList((Iterable)p_178908_0_);

      for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_.size(); ++lvt_9_1_) {
         IChatComponent lvt_10_1_ = (IChatComponent)lvt_8_1_.get(lvt_9_1_);
         String lvt_11_1_ = lvt_10_1_.func_150261_e();
         boolean lvt_12_1_ = false;
         String lvt_14_2_;
         if (lvt_11_1_.contains("\n")) {
            int lvt_13_1_ = lvt_11_1_.indexOf(10);
            lvt_14_2_ = lvt_11_1_.substring(lvt_13_1_ + 1);
            lvt_11_1_ = lvt_11_1_.substring(0, lvt_13_1_ + 1);
            ChatComponentText lvt_15_1_ = new ChatComponentText(lvt_14_2_);
            lvt_15_1_.func_150255_a(lvt_10_1_.func_150256_b().func_150232_l());
            lvt_8_1_.add(lvt_9_1_ + 1, lvt_15_1_);
            lvt_12_1_ = true;
         }

         String lvt_13_2_ = func_178909_a(lvt_10_1_.func_150256_b().func_150218_j() + lvt_11_1_, p_178908_4_);
         lvt_14_2_ = lvt_13_2_.endsWith("\n") ? lvt_13_2_.substring(0, lvt_13_2_.length() - 1) : lvt_13_2_;
         int lvt_15_2_ = p_178908_2_.func_78256_a(lvt_14_2_);
         ChatComponentText lvt_16_1_ = new ChatComponentText(lvt_14_2_);
         lvt_16_1_.func_150255_a(lvt_10_1_.func_150256_b().func_150232_l());
         if (lvt_5_1_ + lvt_15_2_ > p_178908_1_) {
            String lvt_17_1_ = p_178908_2_.func_78262_a(lvt_13_2_, p_178908_1_ - lvt_5_1_, false);
            String lvt_18_1_ = lvt_17_1_.length() < lvt_13_2_.length() ? lvt_13_2_.substring(lvt_17_1_.length()) : null;
            if (lvt_18_1_ != null && lvt_18_1_.length() > 0) {
               int lvt_19_1_ = lvt_17_1_.lastIndexOf(" ");
               if (lvt_19_1_ >= 0 && p_178908_2_.func_78256_a(lvt_13_2_.substring(0, lvt_19_1_)) > 0) {
                  lvt_17_1_ = lvt_13_2_.substring(0, lvt_19_1_);
                  if (p_178908_3_) {
                     ++lvt_19_1_;
                  }

                  lvt_18_1_ = lvt_13_2_.substring(lvt_19_1_);
               } else if (lvt_5_1_ > 0 && !lvt_13_2_.contains(" ")) {
                  lvt_17_1_ = "";
                  lvt_18_1_ = lvt_13_2_;
               }

               ChatComponentText lvt_20_1_ = new ChatComponentText(lvt_18_1_);
               lvt_20_1_.func_150255_a(lvt_10_1_.func_150256_b().func_150232_l());
               lvt_8_1_.add(lvt_9_1_ + 1, lvt_20_1_);
            }

            lvt_15_2_ = p_178908_2_.func_78256_a(lvt_17_1_);
            lvt_16_1_ = new ChatComponentText(lvt_17_1_);
            lvt_16_1_.func_150255_a(lvt_10_1_.func_150256_b().func_150232_l());
            lvt_12_1_ = true;
         }

         if (lvt_5_1_ + lvt_15_2_ <= p_178908_1_) {
            lvt_5_1_ += lvt_15_2_;
            lvt_6_1_.func_150257_a(lvt_16_1_);
         } else {
            lvt_12_1_ = true;
         }

         if (lvt_12_1_) {
            lvt_7_1_.add(lvt_6_1_);
            lvt_5_1_ = 0;
            lvt_6_1_ = new ChatComponentText("");
         }
      }

      lvt_7_1_.add(lvt_6_1_);
      return lvt_7_1_;
   }
}
