package net.optifine.gui;

import com.mojang.authlib.exceptions.InvalidCredentialsException;
import java.math.BigInteger;
import java.net.URI;
import java.util.Random;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.src.Config;
import net.optifine.Lang;

public class GuiScreenCapeOF extends GuiScreenOF {
   private final GuiScreen parentScreen;
   private String title;
   private String message;
   private long messageHideTimeMs;
   private String linkUrl;
   private GuiButtonOF buttonCopyLink;
   private FontRenderer fontRenderer;

   public GuiScreenCapeOF(GuiScreen parentScreenIn) {
      this.fontRenderer = Config.getMinecraft().field_71466_p;
      this.parentScreen = parentScreenIn;
   }

   public void func_73866_w_() {
      int i = 0;
      this.title = I18n.func_135052_a("of.options.capeOF.title");
      int i = i + 2;
      this.field_146292_n.add(new GuiButtonOF(210, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 24 * (i >> 1), 150, 20, I18n.func_135052_a("of.options.capeOF.openEditor")));
      this.field_146292_n.add(new GuiButtonOF(220, this.field_146294_l / 2 - 155 + 160, this.field_146295_m / 6 + 24 * (i >> 1), 150, 20, I18n.func_135052_a("of.options.capeOF.reloadCape")));
      i += 6;
      this.buttonCopyLink = new GuiButtonOF(230, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 24 * (i >> 1), 200, 20, I18n.func_135052_a("of.options.capeOF.copyEditorLink"));
      this.buttonCopyLink.field_146125_m = this.linkUrl != null;
      this.field_146292_n.add(this.buttonCopyLink);
      i += 4;
      this.field_146292_n.add(new GuiButtonOF(200, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 24 * (i >> 1), I18n.func_135052_a("gui.done")));
   }

   protected void func_146284_a(GuiButton button) {
      if (button.field_146124_l) {
         if (button.field_146127_k == 200) {
            this.field_146297_k.func_147108_a(this.parentScreen);
         }

         if (button.field_146127_k == 210) {
            try {
               String userName = this.field_146297_k.func_110432_I().func_148256_e().getName();
               String userId = this.field_146297_k.func_110432_I().func_148256_e().getId().toString().replace("-", "");
               String accessToken = this.field_146297_k.func_110432_I().func_148254_d();
               Random r1 = new Random();
               Random r2 = new Random((long)System.identityHashCode(new Object()));
               BigInteger random1Bi = new BigInteger(128, r1);
               BigInteger random2Bi = new BigInteger(128, r2);
               BigInteger serverBi = random1Bi.xor(random2Bi);
               String serverId = serverBi.toString(16);
               this.field_146297_k.func_152347_ac().joinServer(this.field_146297_k.func_110432_I().func_148256_e(), accessToken, serverId);
               String urlStr = "https://optifine.net/capeChange?u=" + userId + "&n=" + userName + "&s=" + serverId;
               boolean opened = Config.openWebLink(new URI(urlStr));
               if (opened) {
                  this.showMessage(Lang.get("of.message.capeOF.openEditor"), 10000L);
               } else {
                  this.showMessage(Lang.get("of.message.capeOF.openEditorError"), 10000L);
                  this.setLinkUrl(urlStr);
               }
            } catch (InvalidCredentialsException var13) {
               Config.showGuiMessage(I18n.func_135052_a("of.message.capeOF.error1"), I18n.func_135052_a("of.message.capeOF.error2", var13.getMessage()));
               Config.warn("Mojang authentication failed");
               Config.warn(var13.getClass().getName() + ": " + var13.getMessage());
            } catch (Exception var14) {
               Config.warn("Error opening OptiFine cape link");
               Config.warn(var14.getClass().getName() + ": " + var14.getMessage());
            }
         }

         if (button.field_146127_k == 220) {
            this.showMessage(Lang.get("of.message.capeOF.reloadCape"), 15000L);
            if (this.field_146297_k.field_71439_g != null) {
               long delayMs = 15000L;
               long reloadTimeMs = System.currentTimeMillis() + delayMs;
               this.field_146297_k.field_71439_g.setReloadCapeTimeMs(reloadTimeMs);
            }
         }

         if (button.field_146127_k == 230 && this.linkUrl != null) {
            func_146275_d(this.linkUrl);
         }
      }

   }

   private void showMessage(String msg, long timeMs) {
      this.message = msg;
      this.messageHideTimeMs = System.currentTimeMillis() + timeMs;
      this.setLinkUrl((String)null);
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.func_146276_q_();
      this.func_73732_a(this.fontRenderer, this.title, this.field_146294_l / 2, 20, 16777215);
      if (this.message != null) {
         this.func_73732_a(this.fontRenderer, this.message, this.field_146294_l / 2, this.field_146295_m / 6 + 60, 16777215);
         if (System.currentTimeMillis() > this.messageHideTimeMs) {
            this.message = null;
            this.setLinkUrl((String)null);
         }
      }

      super.func_73863_a(mouseX, mouseY, partialTicks);
   }

   public void setLinkUrl(String linkUrl) {
      this.linkUrl = linkUrl;
      this.buttonCopyLink.field_146125_m = linkUrl != null;
   }
}
