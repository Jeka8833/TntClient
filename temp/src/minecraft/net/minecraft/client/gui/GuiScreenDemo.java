package net.minecraft.client.gui;

import java.io.IOException;
import java.net.URI;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiScreenDemo extends GuiScreen {
   private static final Logger field_146349_a = LogManager.getLogger();
   private static final ResourceLocation field_146348_f = new ResourceLocation("textures/gui/demo_background.png");

   public void func_73866_w_() {
      this.field_146292_n.clear();
      int lvt_1_1_ = -16;
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 116, this.field_146295_m / 2 + 62 + lvt_1_1_, 114, 20, I18n.func_135052_a("demo.help.buy")));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 + 2, this.field_146295_m / 2 + 62 + lvt_1_1_, 114, 20, I18n.func_135052_a("demo.help.later")));
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      switch(p_146284_1_.field_146127_k) {
      case 1:
         p_146284_1_.field_146124_l = false;

         try {
            Class<?> lvt_2_1_ = Class.forName("java.awt.Desktop");
            Object lvt_3_1_ = lvt_2_1_.getMethod("getDesktop").invoke((Object)null);
            lvt_2_1_.getMethod("browse", URI.class).invoke(lvt_3_1_, new URI("http://www.minecraft.net/store?source=demo"));
         } catch (Throwable var4) {
            field_146349_a.error("Couldn't open link", var4);
         }
         break;
      case 2:
         this.field_146297_k.func_147108_a((GuiScreen)null);
         this.field_146297_k.func_71381_h();
      }

   }

   public void func_73876_c() {
      super.func_73876_c();
   }

   public void func_146276_q_() {
      super.func_146276_q_();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_146348_f);
      int lvt_1_1_ = (this.field_146294_l - 248) / 2;
      int lvt_2_1_ = (this.field_146295_m - 166) / 2;
      this.func_73729_b(lvt_1_1_, lvt_2_1_, 0, 0, 248, 166);
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      int lvt_4_1_ = (this.field_146294_l - 248) / 2 + 10;
      int lvt_5_1_ = (this.field_146295_m - 166) / 2 + 8;
      this.field_146289_q.func_78276_b(I18n.func_135052_a("demo.help.title"), lvt_4_1_, lvt_5_1_, 2039583);
      lvt_5_1_ += 12;
      GameSettings lvt_6_1_ = this.field_146297_k.field_71474_y;
      this.field_146289_q.func_78276_b(I18n.func_135052_a("demo.help.movementShort", GameSettings.func_74298_c(lvt_6_1_.field_74370_x.func_151463_i()), GameSettings.func_74298_c(lvt_6_1_.field_74368_y.func_151463_i()), GameSettings.func_74298_c(lvt_6_1_.field_74366_z.func_151463_i()), GameSettings.func_74298_c(lvt_6_1_.field_74314_A.func_151463_i())), lvt_4_1_, lvt_5_1_, 5197647);
      this.field_146289_q.func_78276_b(I18n.func_135052_a("demo.help.movementMouse"), lvt_4_1_, lvt_5_1_ + 12, 5197647);
      this.field_146289_q.func_78276_b(I18n.func_135052_a("demo.help.jump", GameSettings.func_74298_c(lvt_6_1_.field_74311_E.func_151463_i())), lvt_4_1_, lvt_5_1_ + 24, 5197647);
      this.field_146289_q.func_78276_b(I18n.func_135052_a("demo.help.inventory", GameSettings.func_74298_c(lvt_6_1_.field_74313_G.func_151463_i())), lvt_4_1_, lvt_5_1_ + 36, 5197647);
      this.field_146289_q.func_78279_b(I18n.func_135052_a("demo.help.fullWrapped"), lvt_4_1_, lvt_5_1_ + 68, 218, 2039583);
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
