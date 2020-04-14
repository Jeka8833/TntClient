package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class ServerListEntryLanScan implements GuiListExtended.IGuiListEntry {
   private final Minecraft field_148288_a = Minecraft.func_71410_x();

   public void func_180790_a(int p_180790_1_, int p_180790_2_, int p_180790_3_, int p_180790_4_, int p_180790_5_, int p_180790_6_, int p_180790_7_, boolean p_180790_8_) {
      int lvt_9_1_ = p_180790_3_ + p_180790_5_ / 2 - this.field_148288_a.field_71466_p.field_78288_b / 2;
      this.field_148288_a.field_71466_p.func_78276_b(I18n.func_135052_a("lanServer.scanning"), this.field_148288_a.field_71462_r.field_146294_l / 2 - this.field_148288_a.field_71466_p.func_78256_a(I18n.func_135052_a("lanServer.scanning")) / 2, lvt_9_1_, 16777215);
      String lvt_10_3_;
      switch((int)(Minecraft.func_71386_F() / 300L % 4L)) {
      case 0:
      default:
         lvt_10_3_ = "O o o";
         break;
      case 1:
      case 3:
         lvt_10_3_ = "o O o";
         break;
      case 2:
         lvt_10_3_ = "o o O";
      }

      this.field_148288_a.field_71466_p.func_78276_b(lvt_10_3_, this.field_148288_a.field_71462_r.field_146294_l / 2 - this.field_148288_a.field_71466_p.func_78256_a(lvt_10_3_) / 2, lvt_9_1_ + this.field_148288_a.field_71466_p.field_78288_b, 8421504);
   }

   public void func_178011_a(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
   }

   public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
      return false;
   }

   public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {
   }
}
