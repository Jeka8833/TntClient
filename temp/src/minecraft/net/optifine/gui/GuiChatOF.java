package net.optifine.gui;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.src.Config;
import net.optifine.shaders.Shaders;

public class GuiChatOF extends GuiChat {
   private static final String CMD_RELOAD_SHADERS = "/reloadShaders";
   private static final String CMD_RELOAD_CHUNKS = "/reloadChunks";

   public GuiChatOF(GuiChat guiChat) {
      super(GuiVideoSettings.getGuiChatText(guiChat));
   }

   public void func_175275_f(String msg) {
      if (this.checkCustomCommand(msg)) {
         this.field_146297_k.field_71456_v.func_146158_b().func_146239_a(msg);
      } else {
         super.func_175275_f(msg);
      }
   }

   private boolean checkCustomCommand(String msg) {
      if (msg == null) {
         return false;
      } else {
         msg = msg.trim();
         if (msg.equals("/reloadShaders")) {
            if (Config.isShaders()) {
               Shaders.uninit();
               Shaders.loadShaderPack();
            }

            return true;
         } else if (msg.equals("/reloadChunks")) {
            this.field_146297_k.field_71438_f.func_72712_a();
            return true;
         } else {
            return false;
         }
      }
   }
}
