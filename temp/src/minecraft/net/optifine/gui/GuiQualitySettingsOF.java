package net.optifine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiQualitySettingsOF extends GuiScreen {
   private GuiScreen prevScreen;
   protected String title;
   private GameSettings settings;
   private static GameSettings.Options[] enumOptions;
   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

   public GuiQualitySettingsOF(GuiScreen guiscreen, GameSettings gamesettings) {
      this.prevScreen = guiscreen;
      this.settings = gamesettings;
   }

   public void func_73866_w_() {
      this.title = I18n.func_135052_a("of.options.qualityTitle");
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

         if (guibutton.field_146127_k != GameSettings.Options.AA_LEVEL.ordinal()) {
            ScaledResolution sr = new ScaledResolution(this.field_146297_k);
            this.func_146280_a(this.field_146297_k, sr.func_78326_a(), sr.func_78328_b());
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
      enumOptions = new GameSettings.Options[]{GameSettings.Options.MIPMAP_LEVELS, GameSettings.Options.MIPMAP_TYPE, GameSettings.Options.AF_LEVEL, GameSettings.Options.AA_LEVEL, GameSettings.Options.CLEAR_WATER, GameSettings.Options.RANDOM_ENTITIES, GameSettings.Options.BETTER_GRASS, GameSettings.Options.BETTER_SNOW, GameSettings.Options.CUSTOM_FONTS, GameSettings.Options.CUSTOM_COLORS, GameSettings.Options.CONNECTED_TEXTURES, GameSettings.Options.NATURAL_TEXTURES, GameSettings.Options.CUSTOM_SKY, GameSettings.Options.CUSTOM_ITEMS, GameSettings.Options.CUSTOM_ENTITY_MODELS, GameSettings.Options.CUSTOM_GUIS, GameSettings.Options.EMISSIVE_TEXTURES};
   }
}
