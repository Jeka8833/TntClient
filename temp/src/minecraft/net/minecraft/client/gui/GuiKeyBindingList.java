package net.minecraft.client.gui;

import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.ArrayUtils;

public class GuiKeyBindingList extends GuiListExtended {
   private final GuiControls field_148191_k;
   private final Minecraft field_148189_l;
   private final GuiListExtended.IGuiListEntry[] field_148190_m;
   private int field_148188_n = 0;

   public GuiKeyBindingList(GuiControls p_i45031_1_, Minecraft p_i45031_2_) {
      super(p_i45031_2_, p_i45031_1_.field_146294_l, p_i45031_1_.field_146295_m, 63, p_i45031_1_.field_146295_m - 32, 20);
      this.field_148191_k = p_i45031_1_;
      this.field_148189_l = p_i45031_2_;
      KeyBinding[] lvt_3_1_ = (KeyBinding[])ArrayUtils.clone((Object[])p_i45031_2_.field_71474_y.field_74317_L);
      this.field_148190_m = new GuiListExtended.IGuiListEntry[lvt_3_1_.length + KeyBinding.func_151467_c().size()];
      Arrays.sort(lvt_3_1_);
      int lvt_4_1_ = 0;
      String lvt_5_1_ = null;
      KeyBinding[] lvt_6_1_ = lvt_3_1_;
      int lvt_7_1_ = lvt_3_1_.length;

      for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_; ++lvt_8_1_) {
         KeyBinding lvt_9_1_ = lvt_6_1_[lvt_8_1_];
         String lvt_10_1_ = lvt_9_1_.func_151466_e();
         if (!lvt_10_1_.equals(lvt_5_1_)) {
            lvt_5_1_ = lvt_10_1_;
            this.field_148190_m[lvt_4_1_++] = new GuiKeyBindingList.CategoryEntry(lvt_10_1_);
         }

         int lvt_11_1_ = p_i45031_2_.field_71466_p.func_78256_a(I18n.func_135052_a(lvt_9_1_.func_151464_g()));
         if (lvt_11_1_ > this.field_148188_n) {
            this.field_148188_n = lvt_11_1_;
         }

         this.field_148190_m[lvt_4_1_++] = new GuiKeyBindingList.KeyEntry(lvt_9_1_);
      }

   }

   protected int func_148127_b() {
      return this.field_148190_m.length;
   }

   public GuiListExtended.IGuiListEntry func_148180_b(int p_148180_1_) {
      return this.field_148190_m[p_148180_1_];
   }

   protected int func_148137_d() {
      return super.func_148137_d() + 15;
   }

   public int func_148139_c() {
      return super.func_148139_c() + 32;
   }

   public class KeyEntry implements GuiListExtended.IGuiListEntry {
      private final KeyBinding field_148282_b;
      private final String field_148283_c;
      private final GuiButton field_148280_d;
      private final GuiButton field_148281_e;

      private KeyEntry(KeyBinding p_i45029_2_) {
         this.field_148282_b = p_i45029_2_;
         this.field_148283_c = I18n.func_135052_a(p_i45029_2_.func_151464_g());
         this.field_148280_d = new GuiButton(0, 0, 0, 75, 20, I18n.func_135052_a(p_i45029_2_.func_151464_g()));
         this.field_148281_e = new GuiButton(0, 0, 0, 50, 20, I18n.func_135052_a("controls.reset"));
      }

      public void func_180790_a(int p_180790_1_, int p_180790_2_, int p_180790_3_, int p_180790_4_, int p_180790_5_, int p_180790_6_, int p_180790_7_, boolean p_180790_8_) {
         boolean lvt_9_1_ = GuiKeyBindingList.this.field_148191_k.field_146491_f == this.field_148282_b;
         GuiKeyBindingList.this.field_148189_l.field_71466_p.func_78276_b(this.field_148283_c, p_180790_2_ + 90 - GuiKeyBindingList.this.field_148188_n, p_180790_3_ + p_180790_5_ / 2 - GuiKeyBindingList.this.field_148189_l.field_71466_p.field_78288_b / 2, 16777215);
         this.field_148281_e.field_146128_h = p_180790_2_ + 190;
         this.field_148281_e.field_146129_i = p_180790_3_;
         this.field_148281_e.field_146124_l = this.field_148282_b.func_151463_i() != this.field_148282_b.func_151469_h();
         this.field_148281_e.func_146112_a(GuiKeyBindingList.this.field_148189_l, p_180790_6_, p_180790_7_);
         this.field_148280_d.field_146128_h = p_180790_2_ + 105;
         this.field_148280_d.field_146129_i = p_180790_3_;
         this.field_148280_d.field_146126_j = GameSettings.func_74298_c(this.field_148282_b.func_151463_i());
         boolean lvt_10_1_ = false;
         if (this.field_148282_b.func_151463_i() != 0) {
            KeyBinding[] lvt_11_1_ = GuiKeyBindingList.this.field_148189_l.field_71474_y.field_74317_L;
            int lvt_12_1_ = lvt_11_1_.length;

            for(int lvt_13_1_ = 0; lvt_13_1_ < lvt_12_1_; ++lvt_13_1_) {
               KeyBinding lvt_14_1_ = lvt_11_1_[lvt_13_1_];
               if (lvt_14_1_ != this.field_148282_b && lvt_14_1_.func_151463_i() == this.field_148282_b.func_151463_i()) {
                  lvt_10_1_ = true;
                  break;
               }
            }
         }

         if (lvt_9_1_) {
            this.field_148280_d.field_146126_j = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.field_148280_d.field_146126_j + EnumChatFormatting.WHITE + " <";
         } else if (lvt_10_1_) {
            this.field_148280_d.field_146126_j = EnumChatFormatting.RED + this.field_148280_d.field_146126_j;
         }

         this.field_148280_d.func_146112_a(GuiKeyBindingList.this.field_148189_l, p_180790_6_, p_180790_7_);
      }

      public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
         if (this.field_148280_d.func_146116_c(GuiKeyBindingList.this.field_148189_l, p_148278_2_, p_148278_3_)) {
            GuiKeyBindingList.this.field_148191_k.field_146491_f = this.field_148282_b;
            return true;
         } else if (this.field_148281_e.func_146116_c(GuiKeyBindingList.this.field_148189_l, p_148278_2_, p_148278_3_)) {
            GuiKeyBindingList.this.field_148189_l.field_71474_y.func_151440_a(this.field_148282_b, this.field_148282_b.func_151469_h());
            KeyBinding.func_74508_b();
            return true;
         } else {
            return false;
         }
      }

      public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {
         this.field_148280_d.func_146118_a(p_148277_2_, p_148277_3_);
         this.field_148281_e.func_146118_a(p_148277_2_, p_148277_3_);
      }

      public void func_178011_a(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
      }
   }

   public class CategoryEntry implements GuiListExtended.IGuiListEntry {
      private final String field_148285_b;
      private final int field_148286_c;

      public CategoryEntry(String p_i45028_2_) {
         this.field_148285_b = I18n.func_135052_a(p_i45028_2_);
         this.field_148286_c = GuiKeyBindingList.this.field_148189_l.field_71466_p.func_78256_a(this.field_148285_b);
      }

      public void func_180790_a(int p_180790_1_, int p_180790_2_, int p_180790_3_, int p_180790_4_, int p_180790_5_, int p_180790_6_, int p_180790_7_, boolean p_180790_8_) {
         GuiKeyBindingList.this.field_148189_l.field_71466_p.func_78276_b(this.field_148285_b, GuiKeyBindingList.this.field_148189_l.field_71462_r.field_146294_l / 2 - this.field_148286_c / 2, p_180790_3_ + p_180790_5_ - GuiKeyBindingList.this.field_148189_l.field_71466_p.field_78288_b - 1, 16777215);
      }

      public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
         return false;
      }

      public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {
      }

      public void func_178011_a(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
      }
   }
}
