package net.optifine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiOtherSettingsOF extends GuiScreen implements GuiYesNoCallback {
   private GuiScreen prevScreen;
   protected String title;
   private GameSettings settings;
   private static GameSettings.Options[] enumOptions;
   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

   public GuiOtherSettingsOF(GuiScreen guiscreen, GameSettings gamesettings) {
      this.prevScreen = guiscreen;
      this.settings = gamesettings;
   }

   public void func_73866_w_() {
      this.title = I18n.func_135052_a("of.options.otherTitle");
      this.field_146292_n.clear();

      for(int i = 0; i < enumOptions.length; ++i) {
         GameSettings.Options enumoptions = enumOptions[i];
         int x = this.field_146294_l / 2 - 155 + i % 2 * 160;
         int y = this.field_146295_m / 6 + 21 * (i / 2) - 12;
         if (!enumoptions.func_74380_a()) {
            this.field_146292_n.add(new GuiOptionButtonOF(enumoptions.func_74381_c(), x, y, enumoptions, this.settings.func_74297_c(enumoptions)));
         } else {
            this.field_146292_n.add(new GuiOptionSliderOF(enumoptions.func_74381_c(), x, y, enumoptions));
         }
      }

      this.field_146292_n.add(new GuiButton(210, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168 + 11 - 44, I18n.func_135052_a("of.options.other.reset")));
      this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168 + 11, I18n.func_135052_a("gui.done")));
   }

   protected void func_146284_a(GuiButton guibutton) {
      if (guibutton.field_146124_l) {
         if (guibutton.field_146127_k < 200 && guibutton instanceof GuiOptionButton) {
            this.settings.func_74306_a(((GuiOptionButton)guibutton).func_146136_c(), 1);
            guibutton.field_146126_j = this.settings.func_74297_c(GameSettings.Options.func_74379_a(guibutton.field_146127_k));
         }

         if (guibutton.field_146127_k == 200) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(this.prevScreen);
         }

         if (guibutton.field_146127_k == 210) {
            this.field_146297_k.field_71474_y.func_74303_b();
            GuiYesNo guiyesno = new GuiYesNo(this, I18n.func_135052_a("of.message.other.reset"), "", 9999);
            this.field_146297_k.func_147108_a(guiyesno);
         }

      }
   }

   public void func_73878_a(boolean flag, int i) {
      if (flag) {
         this.field_146297_k.field_71474_y.resetSettings();
      }

      this.field_146297_k.func_147108_a(this);
   }

   public void func_73863_a(int x, int y, float f) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.title, this.field_146294_l / 2, 15, 16777215);
      super.func_73863_a(x, y, f);
      this.tooltipManager.drawTooltips(x, y, this.field_146292_n);
   }

   static {
      enumOptions = new GameSettings.Options[]{GameSettings.Options.LAGOMETER, GameSettings.Options.PROFILER, GameSettings.Options.SHOW_FPS, GameSettings.Options.ADVANCED_TOOLTIPS, GameSettings.Options.WEATHER, GameSettings.Options.TIME, GameSettings.Options.USE_FULLSCREEN, GameSettings.Options.FULLSCREEN_MODE, GameSettings.Options.ANAGLYPH, GameSettings.Options.AUTOSAVE_TICKS, GameSettings.Options.SCREENSHOT_SIZE, GameSettings.Options.SHOW_GL_ERRORS};
   }
}
