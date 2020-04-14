package net.optifine.shaders.gui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.src.Config;
import net.optifine.Lang;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.Shaders;
import net.optifine.util.ResUtils;

class GuiSlotShaders extends GuiSlot {
   private ArrayList shaderslist;
   private int selectedIndex;
   private long lastClickedCached = 0L;
   final GuiShaders shadersGui;

   public GuiSlotShaders(GuiShaders par1GuiShaders, int width, int height, int top, int bottom, int slotHeight) {
      super(par1GuiShaders.getMc(), width, height, top, bottom, slotHeight);
      this.shadersGui = par1GuiShaders;
      this.updateList();
      this.field_148169_q = 0.0F;
      int posYSelected = this.selectedIndex * slotHeight;
      int wMid = (bottom - top) / 2;
      if (posYSelected > wMid) {
         this.func_148145_f(posYSelected - wMid);
      }

   }

   public int func_148139_c() {
      return this.field_148155_a - 20;
   }

   public void updateList() {
      this.shaderslist = Shaders.listOfShaders();
      this.selectedIndex = 0;
      int i = 0;

      for(int n = this.shaderslist.size(); i < n; ++i) {
         if (((String)this.shaderslist.get(i)).equals(Shaders.currentShaderName)) {
            this.selectedIndex = i;
            break;
         }
      }

   }

   protected int func_148127_b() {
      return this.shaderslist.size();
   }

   protected void func_148144_a(int index, boolean doubleClicked, int mouseX, int mouseY) {
      if (index != this.selectedIndex || this.field_148167_s != this.lastClickedCached) {
         String name = (String)this.shaderslist.get(index);
         IShaderPack sp = Shaders.getShaderPack(name);
         if (this.checkCompatible(sp, index)) {
            this.selectIndex(index);
         }
      }
   }

   private void selectIndex(int index) {
      this.selectedIndex = index;
      this.lastClickedCached = this.field_148167_s;
      Shaders.setShaderPack((String)this.shaderslist.get(index));
      Shaders.uninit();
      this.shadersGui.updateButtons();
   }

   private boolean checkCompatible(IShaderPack sp, final int index) {
      if (sp == null) {
         return true;
      } else {
         InputStream in = sp.getResourceAsStream("/shaders/shaders.properties");
         Properties props = ResUtils.readProperties(in, "Shaders");
         if (props == null) {
            return true;
         } else {
            String keyVer = "version.1.8.9";
            String relMin = props.getProperty(keyVer);
            if (relMin == null) {
               return true;
            } else {
               relMin = relMin.trim();
               String rel = "L5";
               int res = Config.compareRelease(rel, relMin);
               if (res >= 0) {
                  return true;
               } else {
                  String verMin = ("HD_U_" + relMin).replace('_', ' ');
                  String msg1 = I18n.func_135052_a("of.message.shaders.nv1", verMin);
                  String msg2 = I18n.func_135052_a("of.message.shaders.nv2");
                  GuiYesNoCallback callback = new GuiYesNoCallback() {
                     public void func_73878_a(boolean result, int id) {
                        if (result) {
                           GuiSlotShaders.this.selectIndex(index);
                        }

                        GuiSlotShaders.this.field_148161_k.func_147108_a(GuiSlotShaders.this.shadersGui);
                     }
                  };
                  GuiYesNo guiYesNo = new GuiYesNo(callback, msg1, msg2, 0);
                  this.field_148161_k.func_147108_a(guiYesNo);
                  return false;
               }
            }
         }
      }
   }

   protected boolean func_148131_a(int index) {
      return index == this.selectedIndex;
   }

   protected int func_148137_d() {
      return this.field_148155_a - 6;
   }

   protected int func_148138_e() {
      return this.func_148127_b() * 18;
   }

   protected void func_148123_a() {
   }

   protected void func_180791_a(int index, int posX, int posY, int contentY, int mouseX, int mouseY) {
      String label = (String)this.shaderslist.get(index);
      if (label.equals("OFF")) {
         label = Lang.get("of.options.shaders.packNone");
      } else if (label.equals("(internal)")) {
         label = Lang.get("of.options.shaders.packDefault");
      }

      this.shadersGui.drawCenteredString(label, this.field_148155_a / 2, posY + 1, 14737632);
   }

   public int getSelectedIndex() {
      return this.selectedIndex;
   }
}
