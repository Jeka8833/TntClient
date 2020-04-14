package net.optifine.shaders.gui;

import java.util.Iterator;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.optifine.Lang;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionScreen;

public class GuiShaderOptions extends GuiScreenOF {
   private GuiScreen prevScreen;
   protected String title;
   private GameSettings settings;
   private TooltipManager tooltipManager;
   private String screenName;
   private String screenText;
   private boolean changed;
   public static final String OPTION_PROFILE = "<profile>";
   public static final String OPTION_EMPTY = "<empty>";
   public static final String OPTION_REST = "*";

   public GuiShaderOptions(GuiScreen guiscreen, GameSettings gamesettings) {
      this.tooltipManager = new TooltipManager(this, new TooltipProviderShaderOptions());
      this.screenName = null;
      this.screenText = null;
      this.changed = false;
      this.title = "Shader Options";
      this.prevScreen = guiscreen;
      this.settings = gamesettings;
   }

   public GuiShaderOptions(GuiScreen guiscreen, GameSettings gamesettings, String screenName) {
      this(guiscreen, gamesettings);
      this.screenName = screenName;
      if (screenName != null) {
         this.screenText = Shaders.translate("screen." + screenName, screenName);
      }

   }

   public void func_73866_w_() {
      this.title = I18n.func_135052_a("of.options.shaderOptionsTitle");
      int baseId = 100;
      int baseX = false;
      int baseY = 30;
      int stepY = 20;
      int btnWidth = 120;
      int btnHeight = 20;
      int columns = Shaders.getShaderPackColumns(this.screenName, 2);
      ShaderOption[] ops = Shaders.getShaderPackOptions(this.screenName);
      if (ops != null) {
         int colsMin = MathHelper.func_76143_f((double)ops.length / 9.0D);
         if (columns < colsMin) {
            columns = colsMin;
         }

         for(int i = 0; i < ops.length; ++i) {
            ShaderOption so = ops[i];
            if (so != null && so.isVisible()) {
               int col = i % columns;
               int row = i / columns;
               int colWidth = Math.min(this.field_146294_l / columns, 200);
               int baseX = (this.field_146294_l - colWidth * columns) / 2;
               int x = col * colWidth + 5 + baseX;
               int y = baseY + row * stepY;
               int w = colWidth - 10;
               String text = getButtonText(so, w);
               Object btn;
               if (Shaders.isShaderPackOptionSlider(so.getName())) {
                  btn = new GuiSliderShaderOption(baseId + i, x, y, w, btnHeight, so, text);
               } else {
                  btn = new GuiButtonShaderOption(baseId + i, x, y, w, btnHeight, so, text);
               }

               ((GuiButtonShaderOption)btn).field_146124_l = so.isEnabled();
               this.field_146292_n.add(btn);
            }
         }
      }

      this.field_146292_n.add(new GuiButton(201, this.field_146294_l / 2 - btnWidth - 20, this.field_146295_m / 6 + 168 + 11, btnWidth, btnHeight, I18n.func_135052_a("controls.reset")));
      this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 + 20, this.field_146295_m / 6 + 168 + 11, btnWidth, btnHeight, I18n.func_135052_a("gui.done")));
   }

   public static String getButtonText(ShaderOption so, int btnWidth) {
      String labelName = so.getNameText();
      if (so instanceof ShaderOptionScreen) {
         ShaderOptionScreen soScr = (ShaderOptionScreen)so;
         return labelName + "...";
      } else {
         FontRenderer fr = Config.getMinecraft().field_71466_p;

         for(int lenSuffix = fr.func_78256_a(": " + Lang.getOff()) + 5; fr.func_78256_a(labelName) + lenSuffix >= btnWidth && labelName.length() > 0; labelName = labelName.substring(0, labelName.length() - 1)) {
         }

         String col = so.isChanged() ? so.getValueColor(so.getValue()) : "";
         String labelValue = so.getValueText(so.getValue());
         return labelName + ": " + col + labelValue;
      }
   }

   protected void func_146284_a(GuiButton guibutton) {
      if (guibutton.field_146124_l) {
         if (guibutton.field_146127_k < 200 && guibutton instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption btnSo = (GuiButtonShaderOption)guibutton;
            ShaderOption so = btnSo.getShaderOption();
            if (so instanceof ShaderOptionScreen) {
               String screenName = so.getName();
               GuiShaderOptions scr = new GuiShaderOptions(this, this.settings, screenName);
               this.field_146297_k.func_147108_a(scr);
               return;
            }

            if (func_146272_n()) {
               so.resetValue();
            } else if (btnSo.isSwitchable()) {
               so.nextValue();
            }

            this.updateAllButtons();
            this.changed = true;
         }

         if (guibutton.field_146127_k == 201) {
            ShaderOption[] opts = Shaders.getChangedOptions(Shaders.getShaderPackOptions());

            for(int i = 0; i < opts.length; ++i) {
               ShaderOption opt = opts[i];
               opt.resetValue();
               this.changed = true;
            }

            this.updateAllButtons();
         }

         if (guibutton.field_146127_k == 200) {
            if (this.changed) {
               Shaders.saveShaderPackOptions();
               this.changed = false;
               Shaders.uninit();
            }

            this.field_146297_k.func_147108_a(this.prevScreen);
         }

      }
   }

   protected void actionPerformedRightClick(GuiButton btn) {
      if (btn instanceof GuiButtonShaderOption) {
         GuiButtonShaderOption btnSo = (GuiButtonShaderOption)btn;
         ShaderOption so = btnSo.getShaderOption();
         if (func_146272_n()) {
            so.resetValue();
         } else if (btnSo.isSwitchable()) {
            so.prevValue();
         }

         this.updateAllButtons();
         this.changed = true;
      }

   }

   public void func_146281_b() {
      super.func_146281_b();
      if (this.changed) {
         Shaders.saveShaderPackOptions();
         this.changed = false;
         Shaders.uninit();
      }

   }

   private void updateAllButtons() {
      Iterator it = this.field_146292_n.iterator();

      while(it.hasNext()) {
         GuiButton btn = (GuiButton)it.next();
         if (btn instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption gbso = (GuiButtonShaderOption)btn;
            ShaderOption opt = gbso.getShaderOption();
            if (opt instanceof ShaderOptionProfile) {
               ShaderOptionProfile optProf = (ShaderOptionProfile)opt;
               optProf.updateProfile();
            }

            gbso.field_146126_j = getButtonText(opt, gbso.func_146117_b());
            gbso.valueChanged();
         }
      }

   }

   public void func_73863_a(int x, int y, float f) {
      this.func_146276_q_();
      if (this.screenText != null) {
         this.func_73732_a(this.field_146289_q, this.screenText, this.field_146294_l / 2, 15, 16777215);
      } else {
         this.func_73732_a(this.field_146289_q, this.title, this.field_146294_l / 2, 15, 16777215);
      }

      super.func_73863_a(x, y, f);
      this.tooltipManager.drawTooltips(x, y, this.field_146292_n);
   }
}
