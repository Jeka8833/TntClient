package net.optifine.gui;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiVideoSettings;

public class GuiScreenOF extends GuiScreen {
   protected void actionPerformedRightClick(GuiButton button) throws IOException {
   }

   protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.func_73864_a(mouseX, mouseY, mouseButton);
      if (mouseButton == 1) {
         GuiButton btn = getSelectedButton(mouseX, mouseY, this.field_146292_n);
         if (btn != null && btn.field_146124_l) {
            btn.func_146113_a(this.field_146297_k.func_147118_V());
            this.actionPerformedRightClick(btn);
         }
      }

   }

   public static GuiButton getSelectedButton(int x, int y, List<GuiButton> listButtons) {
      for(int i = 0; i < listButtons.size(); ++i) {
         GuiButton btn = (GuiButton)listButtons.get(i);
         if (btn.field_146125_m) {
            int btnWidth = GuiVideoSettings.getButtonWidth(btn);
            int btnHeight = GuiVideoSettings.getButtonHeight(btn);
            if (x >= btn.field_146128_h && y >= btn.field_146129_i && x < btn.field_146128_h + btnWidth && y < btn.field_146129_i + btnHeight) {
               return btn;
            }
         }
      }

      return null;
   }
}
