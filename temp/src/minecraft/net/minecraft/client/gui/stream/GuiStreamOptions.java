package net.minecraft.client.gui.stream;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;

public class GuiStreamOptions extends GuiScreen {
   private static final GameSettings.Options[] field_152312_a;
   private static final GameSettings.Options[] field_152316_f;
   private final GuiScreen field_152317_g;
   private final GameSettings field_152318_h;
   private String field_152319_i;
   private String field_152313_r;
   private int field_152314_s;
   private boolean field_152315_t = false;

   public GuiStreamOptions(GuiScreen p_i1073_1_, GameSettings p_i1073_2_) {
      this.field_152317_g = p_i1073_1_;
      this.field_152318_h = p_i1073_2_;
   }

   public void func_73866_w_() {
      int lvt_1_1_ = 0;
      this.field_152319_i = I18n.func_135052_a("options.stream.title");
      this.field_152313_r = I18n.func_135052_a("options.stream.chat.title");
      GameSettings.Options[] lvt_2_2_ = field_152312_a;
      int lvt_3_2_ = lvt_2_2_.length;

      int lvt_4_2_;
      GameSettings.Options lvt_5_2_;
      for(lvt_4_2_ = 0; lvt_4_2_ < lvt_3_2_; ++lvt_4_2_) {
         lvt_5_2_ = lvt_2_2_[lvt_4_2_];
         if (lvt_5_2_.func_74380_a()) {
            this.field_146292_n.add(new GuiOptionSlider(lvt_5_2_.func_74381_c(), this.field_146294_l / 2 - 155 + lvt_1_1_ % 2 * 160, this.field_146295_m / 6 + 24 * (lvt_1_1_ >> 1), lvt_5_2_));
         } else {
            this.field_146292_n.add(new GuiOptionButton(lvt_5_2_.func_74381_c(), this.field_146294_l / 2 - 155 + lvt_1_1_ % 2 * 160, this.field_146295_m / 6 + 24 * (lvt_1_1_ >> 1), lvt_5_2_, this.field_152318_h.func_74297_c(lvt_5_2_)));
         }

         ++lvt_1_1_;
      }

      if (lvt_1_1_ % 2 == 1) {
         ++lvt_1_1_;
      }

      this.field_152314_s = this.field_146295_m / 6 + 24 * (lvt_1_1_ >> 1) + 6;
      lvt_1_1_ += 2;
      lvt_2_2_ = field_152316_f;
      lvt_3_2_ = lvt_2_2_.length;

      for(lvt_4_2_ = 0; lvt_4_2_ < lvt_3_2_; ++lvt_4_2_) {
         lvt_5_2_ = lvt_2_2_[lvt_4_2_];
         if (lvt_5_2_.func_74380_a()) {
            this.field_146292_n.add(new GuiOptionSlider(lvt_5_2_.func_74381_c(), this.field_146294_l / 2 - 155 + lvt_1_1_ % 2 * 160, this.field_146295_m / 6 + 24 * (lvt_1_1_ >> 1), lvt_5_2_));
         } else {
            this.field_146292_n.add(new GuiOptionButton(lvt_5_2_.func_74381_c(), this.field_146294_l / 2 - 155 + lvt_1_1_ % 2 * 160, this.field_146295_m / 6 + 24 * (lvt_1_1_ >> 1), lvt_5_2_, this.field_152318_h.func_74297_c(lvt_5_2_)));
         }

         ++lvt_1_1_;
      }

      this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 168, 150, 20, I18n.func_135052_a("gui.done")));
      GuiButton lvt_2_3_ = new GuiButton(201, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 168, 150, 20, I18n.func_135052_a("options.stream.ingestSelection"));
      lvt_2_3_.field_146124_l = this.field_146297_k.func_152346_Z().func_152924_m() && this.field_146297_k.func_152346_Z().func_152925_v().length > 0 || this.field_146297_k.func_152346_Z().func_152908_z();
      this.field_146292_n.add(lvt_2_3_);
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if (p_146284_1_.field_146124_l) {
         if (p_146284_1_.field_146127_k < 100 && p_146284_1_ instanceof GuiOptionButton) {
            GameSettings.Options lvt_2_1_ = ((GuiOptionButton)p_146284_1_).func_146136_c();
            this.field_152318_h.func_74306_a(lvt_2_1_, 1);
            p_146284_1_.field_146126_j = this.field_152318_h.func_74297_c(GameSettings.Options.func_74379_a(p_146284_1_.field_146127_k));
            if (this.field_146297_k.func_152346_Z().func_152934_n() && lvt_2_1_ != GameSettings.Options.STREAM_CHAT_ENABLED && lvt_2_1_ != GameSettings.Options.STREAM_CHAT_USER_FILTER) {
               this.field_152315_t = true;
            }
         } else if (p_146284_1_ instanceof GuiOptionSlider) {
            if (p_146284_1_.field_146127_k == GameSettings.Options.STREAM_VOLUME_MIC.func_74381_c()) {
               this.field_146297_k.func_152346_Z().func_152915_s();
            } else if (p_146284_1_.field_146127_k == GameSettings.Options.STREAM_VOLUME_SYSTEM.func_74381_c()) {
               this.field_146297_k.func_152346_Z().func_152915_s();
            } else if (this.field_146297_k.func_152346_Z().func_152934_n()) {
               this.field_152315_t = true;
            }
         }

         if (p_146284_1_.field_146127_k == 200) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(this.field_152317_g);
         } else if (p_146284_1_.field_146127_k == 201) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new GuiIngestServers(this));
         }

      }
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.field_152319_i, this.field_146294_l / 2, 20, 16777215);
      this.func_73732_a(this.field_146289_q, this.field_152313_r, this.field_146294_l / 2, this.field_152314_s, 16777215);
      if (this.field_152315_t) {
         this.func_73732_a(this.field_146289_q, EnumChatFormatting.RED + I18n.func_135052_a("options.stream.changes"), this.field_146294_l / 2, 20 + this.field_146289_q.field_78288_b, 16777215);
      }

      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   static {
      field_152312_a = new GameSettings.Options[]{GameSettings.Options.STREAM_BYTES_PER_PIXEL, GameSettings.Options.STREAM_FPS, GameSettings.Options.STREAM_KBPS, GameSettings.Options.STREAM_SEND_METADATA, GameSettings.Options.STREAM_VOLUME_MIC, GameSettings.Options.STREAM_VOLUME_SYSTEM, GameSettings.Options.STREAM_MIC_TOGGLE_BEHAVIOR, GameSettings.Options.STREAM_COMPRESSION};
      field_152316_f = new GameSettings.Options[]{GameSettings.Options.STREAM_CHAT_ENABLED, GameSettings.Options.STREAM_CHAT_USER_FILTER};
   }
}
