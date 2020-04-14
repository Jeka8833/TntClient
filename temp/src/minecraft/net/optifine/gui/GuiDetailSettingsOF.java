package net.optifine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiDetailSettingsOF extends GuiScreen {
   private GuiScreen prevScreen;
   protected String title;
   private GameSettings settings;
   private static GameSettings.Options[] enumOptions;
   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

   public GuiDetailSettingsOF(GuiScreen guiscreen, GameSettings gamesettings) {
      this.prevScreen = guiscreen;
      this.settings = gamesettings;
   }

   public void func_73866_w_() {
      this.title = I18n.func_135052_a("of.options.detailsTitle");
      this.field_146292_n.clear();

      for(int i = 0; i < enumOptions.length; ++i) {
         GameSettings.Options opt = enumOptions[i];
         int x = this.field_146294_l / 2 - 155 + i % 2 * 160;
         int y = this.field_146295_m / 6 + 21 * (i / 2) - 12;
         if (!opt.func_74380_a()) {
            this.field_146292_n.add(new GuiOptionButtonOF(opt.func_74381_c(), x, y, opt, this.settings.func_74297_c(opt)));
         } else {
            this.field_146292_n.add(new GuiOptionSliderOF(opt.func_74381_c(), x, y, opt));
         }
      }

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

      }
   }

   public void func_73863_a(int x, int y, float f) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.title, this.field_146294_l / 2, 15, 16777215);
      super.func_73863_a(x, y, f);
      this.tooltipManager.drawTooltips(x, y, this.field_146292_n);
   }

   static {
      enumOptions = new GameSettings.Options[]{GameSettings.Options.CLOUDS, GameSettings.Options.CLOUD_HEIGHT, GameSettings.Options.TREES, GameSettings.Options.RAIN, GameSettings.Options.SKY, GameSettings.Options.STARS, GameSettings.Options.SUN_MOON, GameSettings.Options.SHOW_CAPES, GameSettings.Options.FOG_FANCY, GameSettings.Options.FOG_START, GameSettings.Options.TRANSLUCENT_BLOCKS, GameSettings.Options.HELD_ITEM_TOOLTIPS, GameSettings.Options.DROPPED_ITEMS, GameSettings.Options.ENTITY_SHADOWS, GameSettings.Options.VIGNETTE, GameSettings.Options.ALTERNATE_BLOCKS, GameSettings.Options.SWAMP_COLORS, GameSettings.Options.SMOOTH_BIOMES};
   }
}
