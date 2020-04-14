package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.optifine.Lang;
import net.optifine.gui.GuiAnimationSettingsOF;
import net.optifine.gui.GuiDetailSettingsOF;
import net.optifine.gui.GuiOptionButtonOF;
import net.optifine.gui.GuiOptionSliderOF;
import net.optifine.gui.GuiOtherSettingsOF;
import net.optifine.gui.GuiPerformanceSettingsOF;
import net.optifine.gui.GuiQualitySettingsOF;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;
import net.optifine.shaders.gui.GuiShaders;

public class GuiVideoSettings extends GuiScreenOF {
   private GuiScreen field_146498_f;
   protected String field_146500_a = "Video Settings";
   private GameSettings field_146499_g;
   private static GameSettings.Options[] field_146502_i;
   private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

   public GuiVideoSettings(GuiScreen p_i1062_1_, GameSettings p_i1062_2_) {
      this.field_146498_f = p_i1062_1_;
      this.field_146499_g = p_i1062_2_;
   }

   public void func_73866_w_() {
      this.field_146500_a = I18n.func_135052_a("options.videoTitle");
      this.field_146292_n.clear();

      int i;
      for(i = 0; i < field_146502_i.length; ++i) {
         GameSettings.Options opt = field_146502_i[i];
         if (opt != null) {
            int x = this.field_146294_l / 2 - 155 + i % 2 * 160;
            int y = this.field_146295_m / 6 + 21 * (i / 2) - 12;
            if (opt.func_74380_a()) {
               this.field_146292_n.add(new GuiOptionSliderOF(opt.func_74381_c(), x, y, opt));
            } else {
               this.field_146292_n.add(new GuiOptionButtonOF(opt.func_74381_c(), x, y, opt, this.field_146499_g.func_74297_c(opt)));
            }
         }
      }

      i = this.field_146295_m / 6 + 21 * (field_146502_i.length / 2) - 12;
      int x = false;
      int x = this.field_146294_l / 2 - 155 + 0;
      this.field_146292_n.add(new GuiOptionButton(231, x, i, Lang.get("of.options.shaders")));
      x = this.field_146294_l / 2 - 155 + 160;
      this.field_146292_n.add(new GuiOptionButton(202, x, i, Lang.get("of.options.quality")));
      i += 21;
      x = this.field_146294_l / 2 - 155 + 0;
      this.field_146292_n.add(new GuiOptionButton(201, x, i, Lang.get("of.options.details")));
      x = this.field_146294_l / 2 - 155 + 160;
      this.field_146292_n.add(new GuiOptionButton(212, x, i, Lang.get("of.options.performance")));
      i += 21;
      x = this.field_146294_l / 2 - 155 + 0;
      this.field_146292_n.add(new GuiOptionButton(211, x, i, Lang.get("of.options.animations")));
      x = this.field_146294_l / 2 - 155 + 160;
      this.field_146292_n.add(new GuiOptionButton(222, x, i, Lang.get("of.options.other")));
      i += 21;
      this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168 + 11, I18n.func_135052_a("gui.done")));
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      this.actionPerformed(p_146284_1_, 1);
   }

   protected void actionPerformedRightClick(GuiButton p_actionPerformedRightClick_1_) {
      if (p_actionPerformedRightClick_1_.field_146127_k == GameSettings.Options.GUI_SCALE.ordinal()) {
         this.actionPerformed(p_actionPerformedRightClick_1_, -1);
      }

   }

   private void actionPerformed(GuiButton p_actionPerformed_1_, int p_actionPerformed_2_) {
      if (p_actionPerformed_1_.field_146124_l) {
         int guiScale = this.field_146499_g.field_74362_aa;
         if (p_actionPerformed_1_.field_146127_k < 200 && p_actionPerformed_1_ instanceof GuiOptionButton) {
            this.field_146499_g.func_74306_a(((GuiOptionButton)p_actionPerformed_1_).func_146136_c(), p_actionPerformed_2_);
            p_actionPerformed_1_.field_146126_j = this.field_146499_g.func_74297_c(GameSettings.Options.func_74379_a(p_actionPerformed_1_.field_146127_k));
         }

         if (p_actionPerformed_1_.field_146127_k == 200) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(this.field_146498_f);
         }

         if (this.field_146499_g.field_74362_aa != guiScale) {
            ScaledResolution var3 = new ScaledResolution(this.field_146297_k);
            int var4 = var3.func_78326_a();
            int var5 = var3.func_78328_b();
            this.func_146280_a(this.field_146297_k, var4, var5);
         }

         if (p_actionPerformed_1_.field_146127_k == 201) {
            this.field_146297_k.field_71474_y.func_74303_b();
            GuiDetailSettingsOF scr = new GuiDetailSettingsOF(this, this.field_146499_g);
            this.field_146297_k.func_147108_a(scr);
         }

         if (p_actionPerformed_1_.field_146127_k == 202) {
            this.field_146297_k.field_71474_y.func_74303_b();
            GuiQualitySettingsOF scr = new GuiQualitySettingsOF(this, this.field_146499_g);
            this.field_146297_k.func_147108_a(scr);
         }

         if (p_actionPerformed_1_.field_146127_k == 211) {
            this.field_146297_k.field_71474_y.func_74303_b();
            GuiAnimationSettingsOF scr = new GuiAnimationSettingsOF(this, this.field_146499_g);
            this.field_146297_k.func_147108_a(scr);
         }

         if (p_actionPerformed_1_.field_146127_k == 212) {
            this.field_146297_k.field_71474_y.func_74303_b();
            GuiPerformanceSettingsOF scr = new GuiPerformanceSettingsOF(this, this.field_146499_g);
            this.field_146297_k.func_147108_a(scr);
         }

         if (p_actionPerformed_1_.field_146127_k == 222) {
            this.field_146297_k.field_71474_y.func_74303_b();
            GuiOtherSettingsOF scr = new GuiOtherSettingsOF(this, this.field_146499_g);
            this.field_146297_k.func_147108_a(scr);
         }

         if (p_actionPerformed_1_.field_146127_k == 231) {
            if (Config.isAntialiasing() || Config.isAntialiasingConfigured()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.aa1"), Lang.get("of.message.shaders.aa2"));
               return;
            }

            if (Config.isAnisotropicFiltering()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.af1"), Lang.get("of.message.shaders.af2"));
               return;
            }

            if (Config.isFastRender()) {
               Config.showGuiMessage(Lang.get("of.message.shaders.fr1"), Lang.get("of.message.shaders.fr2"));
               return;
            }

            if (Config.getGameSettings().field_74337_g) {
               Config.showGuiMessage(Lang.get("of.message.shaders.an1"), Lang.get("of.message.shaders.an2"));
               return;
            }

            this.field_146297_k.field_71474_y.func_74303_b();
            GuiShaders scr = new GuiShaders(this, this.field_146499_g);
            this.field_146297_k.func_147108_a(scr);
         }

      }
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.field_146500_a, this.field_146294_l / 2, 15, 16777215);
      String ver = Config.getVersion();
      String ed = "HD_U";
      if (ed.equals("HD")) {
         ver = "OptiFine HD L5";
      }

      if (ed.equals("HD_U")) {
         ver = "OptiFine HD L5 Ultra";
      }

      if (ed.equals("L")) {
         ver = "OptiFine L5 Light";
      }

      this.func_73731_b(this.field_146289_q, ver, 2, this.field_146295_m - 10, 8421504);
      String verMc = "Minecraft 1.8.9";
      int lenMc = this.field_146289_q.func_78256_a(verMc);
      this.func_73731_b(this.field_146289_q, verMc, this.field_146294_l - lenMc - 2, this.field_146295_m - 10, 8421504);
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
      this.tooltipManager.drawTooltips(p_73863_1_, p_73863_2_, this.field_146292_n);
   }

   public static int getButtonWidth(GuiButton p_getButtonWidth_0_) {
      return p_getButtonWidth_0_.field_146120_f;
   }

   public static int getButtonHeight(GuiButton p_getButtonHeight_0_) {
      return p_getButtonHeight_0_.field_146121_g;
   }

   public static void drawGradientRect(GuiScreen p_drawGradientRect_0_, int p_drawGradientRect_1_, int p_drawGradientRect_2_, int p_drawGradientRect_3_, int p_drawGradientRect_4_, int p_drawGradientRect_5_, int p_drawGradientRect_6_) {
      p_drawGradientRect_0_.func_73733_a(p_drawGradientRect_1_, p_drawGradientRect_2_, p_drawGradientRect_3_, p_drawGradientRect_4_, p_drawGradientRect_5_, p_drawGradientRect_6_);
   }

   public static String getGuiChatText(GuiChat p_getGuiChatText_0_) {
      return p_getGuiChatText_0_.field_146415_a.func_146179_b();
   }

   static {
      field_146502_i = new GameSettings.Options[]{GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.AO_LEVEL, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.GUI_SCALE, GameSettings.Options.USE_VBO, GameSettings.Options.GAMMA, GameSettings.Options.BLOCK_ALTERNATIVES, GameSettings.Options.DYNAMIC_LIGHTS, GameSettings.Options.DYNAMIC_FOV};
   }
}
