package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiWinGame extends GuiScreen {
   private static final Logger field_146580_a = LogManager.getLogger();
   private static final ResourceLocation field_146576_f = new ResourceLocation("textures/gui/title/minecraft.png");
   private static final ResourceLocation field_146577_g = new ResourceLocation("textures/misc/vignette.png");
   private int field_146581_h;
   private List<String> field_146582_i;
   private int field_146579_r;
   private float field_146578_s = 0.5F;

   public void func_73876_c() {
      MusicTicker lvt_1_1_ = this.field_146297_k.func_181535_r();
      SoundHandler lvt_2_1_ = this.field_146297_k.func_147118_V();
      if (this.field_146581_h == 0) {
         lvt_1_1_.func_181557_a();
         lvt_1_1_.func_181558_a(MusicTicker.MusicType.CREDITS);
         lvt_2_1_.func_147687_e();
      }

      lvt_2_1_.func_73660_a();
      ++this.field_146581_h;
      float lvt_3_1_ = (float)(this.field_146579_r + this.field_146295_m + this.field_146295_m + 24) / this.field_146578_s;
      if ((float)this.field_146581_h > lvt_3_1_) {
         this.func_146574_g();
      }

   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if (p_73869_2_ == 1) {
         this.func_146574_g();
      }

   }

   private void func_146574_g() {
      this.field_146297_k.field_71439_g.field_71174_a.func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
      this.field_146297_k.func_147108_a((GuiScreen)null);
   }

   public boolean func_73868_f() {
      return true;
   }

   public void func_73866_w_() {
      if (this.field_146582_i == null) {
         this.field_146582_i = Lists.newArrayList();

         try {
            String lvt_1_1_ = "";
            String lvt_2_1_ = "" + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + EnumChatFormatting.GREEN + EnumChatFormatting.AQUA;
            int lvt_3_1_ = 274;
            InputStream lvt_4_1_ = this.field_146297_k.func_110442_L().func_110536_a(new ResourceLocation("texts/end.txt")).func_110527_b();
            BufferedReader lvt_5_1_ = new BufferedReader(new InputStreamReader(lvt_4_1_, Charsets.UTF_8));
            Random lvt_6_1_ = new Random(8124371L);

            int lvt_7_2_;
            while((lvt_1_1_ = lvt_5_1_.readLine()) != null) {
               String lvt_8_1_;
               String lvt_9_1_;
               for(lvt_1_1_ = lvt_1_1_.replaceAll("PLAYERNAME", this.field_146297_k.func_110432_I().func_111285_a()); lvt_1_1_.contains(lvt_2_1_); lvt_1_1_ = lvt_8_1_ + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, lvt_6_1_.nextInt(4) + 3) + lvt_9_1_) {
                  lvt_7_2_ = lvt_1_1_.indexOf(lvt_2_1_);
                  lvt_8_1_ = lvt_1_1_.substring(0, lvt_7_2_);
                  lvt_9_1_ = lvt_1_1_.substring(lvt_7_2_ + lvt_2_1_.length());
               }

               this.field_146582_i.addAll(this.field_146297_k.field_71466_p.func_78271_c(lvt_1_1_, lvt_3_1_));
               this.field_146582_i.add("");
            }

            lvt_4_1_.close();

            for(lvt_7_2_ = 0; lvt_7_2_ < 8; ++lvt_7_2_) {
               this.field_146582_i.add("");
            }

            lvt_4_1_ = this.field_146297_k.func_110442_L().func_110536_a(new ResourceLocation("texts/credits.txt")).func_110527_b();
            lvt_5_1_ = new BufferedReader(new InputStreamReader(lvt_4_1_, Charsets.UTF_8));

            while((lvt_1_1_ = lvt_5_1_.readLine()) != null) {
               lvt_1_1_ = lvt_1_1_.replaceAll("PLAYERNAME", this.field_146297_k.func_110432_I().func_111285_a());
               lvt_1_1_ = lvt_1_1_.replaceAll("\t", "    ");
               this.field_146582_i.addAll(this.field_146297_k.field_71466_p.func_78271_c(lvt_1_1_, lvt_3_1_));
               this.field_146582_i.add("");
            }

            lvt_4_1_.close();
            this.field_146579_r = this.field_146582_i.size() * 12;
         } catch (Exception var10) {
            field_146580_a.error((String)"Couldn't load credits", (Throwable)var10);
         }

      }
   }

   private void func_146575_b(int p_146575_1_, int p_146575_2_, float p_146575_3_) {
      Tessellator lvt_4_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_5_1_ = lvt_4_1_.func_178180_c();
      this.field_146297_k.func_110434_K().func_110577_a(Gui.field_110325_k);
      lvt_5_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      int lvt_6_1_ = this.field_146294_l;
      float lvt_7_1_ = 0.0F - ((float)this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
      float lvt_8_1_ = (float)this.field_146295_m - ((float)this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
      float lvt_9_1_ = 0.015625F;
      float lvt_10_1_ = ((float)this.field_146581_h + p_146575_3_ - 0.0F) * 0.02F;
      float lvt_11_1_ = (float)(this.field_146579_r + this.field_146295_m + this.field_146295_m + 24) / this.field_146578_s;
      float lvt_12_1_ = (lvt_11_1_ - 20.0F - ((float)this.field_146581_h + p_146575_3_)) * 0.005F;
      if (lvt_12_1_ < lvt_10_1_) {
         lvt_10_1_ = lvt_12_1_;
      }

      if (lvt_10_1_ > 1.0F) {
         lvt_10_1_ = 1.0F;
      }

      lvt_10_1_ *= lvt_10_1_;
      lvt_10_1_ = lvt_10_1_ * 96.0F / 255.0F;
      lvt_5_1_.func_181662_b(0.0D, (double)this.field_146295_m, (double)this.field_73735_i).func_181673_a(0.0D, (double)(lvt_7_1_ * lvt_9_1_)).func_181666_a(lvt_10_1_, lvt_10_1_, lvt_10_1_, 1.0F).func_181675_d();
      lvt_5_1_.func_181662_b((double)lvt_6_1_, (double)this.field_146295_m, (double)this.field_73735_i).func_181673_a((double)((float)lvt_6_1_ * lvt_9_1_), (double)(lvt_7_1_ * lvt_9_1_)).func_181666_a(lvt_10_1_, lvt_10_1_, lvt_10_1_, 1.0F).func_181675_d();
      lvt_5_1_.func_181662_b((double)lvt_6_1_, 0.0D, (double)this.field_73735_i).func_181673_a((double)((float)lvt_6_1_ * lvt_9_1_), (double)(lvt_8_1_ * lvt_9_1_)).func_181666_a(lvt_10_1_, lvt_10_1_, lvt_10_1_, 1.0F).func_181675_d();
      lvt_5_1_.func_181662_b(0.0D, 0.0D, (double)this.field_73735_i).func_181673_a(0.0D, (double)(lvt_8_1_ * lvt_9_1_)).func_181666_a(lvt_10_1_, lvt_10_1_, lvt_10_1_, 1.0F).func_181675_d();
      lvt_4_1_.func_78381_a();
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146575_b(p_73863_1_, p_73863_2_, p_73863_3_);
      Tessellator lvt_4_1_ = Tessellator.func_178181_a();
      WorldRenderer lvt_5_1_ = lvt_4_1_.func_178180_c();
      int lvt_6_1_ = 274;
      int lvt_7_1_ = this.field_146294_l / 2 - lvt_6_1_ / 2;
      int lvt_8_1_ = this.field_146295_m + 50;
      float lvt_9_1_ = -((float)this.field_146581_h + p_73863_3_) * this.field_146578_s;
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(0.0F, lvt_9_1_, 0.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_146576_f);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.func_73729_b(lvt_7_1_, lvt_8_1_, 0, 0, 155, 44);
      this.func_73729_b(lvt_7_1_ + 155, lvt_8_1_, 0, 45, 155, 44);
      int lvt_10_1_ = lvt_8_1_ + 200;

      int lvt_11_1_;
      for(lvt_11_1_ = 0; lvt_11_1_ < this.field_146582_i.size(); ++lvt_11_1_) {
         if (lvt_11_1_ == this.field_146582_i.size() - 1) {
            float lvt_12_1_ = (float)lvt_10_1_ + lvt_9_1_ - (float)(this.field_146295_m / 2 - 6);
            if (lvt_12_1_ < 0.0F) {
               GlStateManager.func_179109_b(0.0F, -lvt_12_1_, 0.0F);
            }
         }

         if ((float)lvt_10_1_ + lvt_9_1_ + 12.0F + 8.0F > 0.0F && (float)lvt_10_1_ + lvt_9_1_ < (float)this.field_146295_m) {
            String lvt_12_2_ = (String)this.field_146582_i.get(lvt_11_1_);
            if (lvt_12_2_.startsWith("[C]")) {
               this.field_146289_q.func_175063_a(lvt_12_2_.substring(3), (float)(lvt_7_1_ + (lvt_6_1_ - this.field_146289_q.func_78256_a(lvt_12_2_.substring(3))) / 2), (float)lvt_10_1_, 16777215);
            } else {
               this.field_146289_q.field_78289_c.setSeed((long)lvt_11_1_ * 4238972211L + (long)(this.field_146581_h / 4));
               this.field_146289_q.func_175063_a(lvt_12_2_, (float)lvt_7_1_, (float)lvt_10_1_, 16777215);
            }
         }

         lvt_10_1_ += 12;
      }

      GlStateManager.func_179121_F();
      this.field_146297_k.func_110434_K().func_110577_a(field_146577_g);
      GlStateManager.func_179147_l();
      GlStateManager.func_179112_b(0, 769);
      lvt_11_1_ = this.field_146294_l;
      int lvt_12_3_ = this.field_146295_m;
      lvt_5_1_.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      lvt_5_1_.func_181662_b(0.0D, (double)lvt_12_3_, (double)this.field_73735_i).func_181673_a(0.0D, 1.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      lvt_5_1_.func_181662_b((double)lvt_11_1_, (double)lvt_12_3_, (double)this.field_73735_i).func_181673_a(1.0D, 1.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      lvt_5_1_.func_181662_b((double)lvt_11_1_, 0.0D, (double)this.field_73735_i).func_181673_a(1.0D, 0.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      lvt_5_1_.func_181662_b(0.0D, 0.0D, (double)this.field_73735_i).func_181673_a(0.0D, 0.0D).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
      lvt_4_1_.func_78381_a();
      GlStateManager.func_179084_k();
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
