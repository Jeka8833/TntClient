package net.optifine.gui;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.src.Config;

public class GuiMessage extends GuiScreen {
   private GuiScreen parentScreen;
   private String messageLine1;
   private String messageLine2;
   private final List listLines2 = Lists.newArrayList();
   protected String confirmButtonText;
   private int ticksUntilEnable;

   public GuiMessage(GuiScreen parentScreen, String line1, String line2) {
      this.parentScreen = parentScreen;
      this.messageLine1 = line1;
      this.messageLine2 = line2;
      this.confirmButtonText = I18n.func_135052_a("gui.done");
   }

   public void func_73866_w_() {
      this.field_146292_n.add(new GuiOptionButton(0, this.field_146294_l / 2 - 74, this.field_146295_m / 6 + 96, this.confirmButtonText));
      this.listLines2.clear();
      this.listLines2.addAll(this.field_146289_q.func_78271_c(this.messageLine2, this.field_146294_l - 50));
   }

   protected void func_146284_a(GuiButton button) throws IOException {
      Config.getMinecraft().func_147108_a(this.parentScreen);
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.messageLine1, this.field_146294_l / 2, 70, 16777215);
      int var4 = 90;

      for(Iterator var5 = this.listLines2.iterator(); var5.hasNext(); var4 += this.field_146289_q.field_78288_b) {
         String var6 = (String)var5.next();
         this.func_73732_a(this.field_146289_q, var6, this.field_146294_l / 2, var4, 16777215);
      }

      super.func_73863_a(mouseX, mouseY, partialTicks);
   }

   public void setButtonDelay(int ticksUntilEnable) {
      this.ticksUntilEnable = ticksUntilEnable;

      GuiButton var3;
      for(Iterator var2 = this.field_146292_n.iterator(); var2.hasNext(); var3.field_146124_l = false) {
         var3 = (GuiButton)var2.next();
      }

   }

   public void func_73876_c() {
      super.func_73876_c();
      GuiButton var2;
      if (--this.ticksUntilEnable == 0) {
         for(Iterator var1 = this.field_146292_n.iterator(); var1.hasNext(); var2.field_146124_l = true) {
            var2 = (GuiButton)var1.next();
         }
      }

   }
}
