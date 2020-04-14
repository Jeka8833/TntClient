package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

public class GuiScreenResourcePacks extends GuiScreen {
   private static final Logger field_146968_a = LogManager.getLogger();
   private final GuiScreen field_146965_f;
   private List<ResourcePackListEntry> field_146966_g;
   private List<ResourcePackListEntry> field_146969_h;
   private GuiResourcePackAvailable field_146970_i;
   private GuiResourcePackSelected field_146967_r;
   private boolean field_175289_s = false;

   public GuiScreenResourcePacks(GuiScreen p_i45050_1_) {
      this.field_146965_f = p_i45050_1_;
   }

   public void func_73866_w_() {
      this.field_146292_n.add(new GuiOptionButton(2, this.field_146294_l / 2 - 154, this.field_146295_m - 48, I18n.func_135052_a("resourcePack.openFolder")));
      this.field_146292_n.add(new GuiOptionButton(1, this.field_146294_l / 2 + 4, this.field_146295_m - 48, I18n.func_135052_a("gui.done")));
      if (!this.field_175289_s) {
         this.field_146966_g = Lists.newArrayList();
         this.field_146969_h = Lists.newArrayList();
         ResourcePackRepository lvt_1_1_ = this.field_146297_k.func_110438_M();
         lvt_1_1_.func_110611_a();
         List<ResourcePackRepository.Entry> lvt_2_1_ = Lists.newArrayList((Iterable)lvt_1_1_.func_110609_b());
         lvt_2_1_.removeAll(lvt_1_1_.func_110613_c());
         Iterator lvt_3_2_ = lvt_2_1_.iterator();

         ResourcePackRepository.Entry lvt_4_2_;
         while(lvt_3_2_.hasNext()) {
            lvt_4_2_ = (ResourcePackRepository.Entry)lvt_3_2_.next();
            this.field_146966_g.add(new ResourcePackListEntryFound(this, lvt_4_2_));
         }

         lvt_3_2_ = Lists.reverse(lvt_1_1_.func_110613_c()).iterator();

         while(lvt_3_2_.hasNext()) {
            lvt_4_2_ = (ResourcePackRepository.Entry)lvt_3_2_.next();
            this.field_146969_h.add(new ResourcePackListEntryFound(this, lvt_4_2_));
         }

         this.field_146969_h.add(new ResourcePackListEntryDefault(this));
      }

      this.field_146970_i = new GuiResourcePackAvailable(this.field_146297_k, 200, this.field_146295_m, this.field_146966_g);
      this.field_146970_i.func_148140_g(this.field_146294_l / 2 - 4 - 200);
      this.field_146970_i.func_148134_d(7, 8);
      this.field_146967_r = new GuiResourcePackSelected(this.field_146297_k, 200, this.field_146295_m, this.field_146969_h);
      this.field_146967_r.func_148140_g(this.field_146294_l / 2 + 4);
      this.field_146967_r.func_148134_d(7, 8);
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      this.field_146967_r.func_178039_p();
      this.field_146970_i.func_178039_p();
   }

   public boolean func_146961_a(ResourcePackListEntry p_146961_1_) {
      return this.field_146969_h.contains(p_146961_1_);
   }

   public List<ResourcePackListEntry> func_146962_b(ResourcePackListEntry p_146962_1_) {
      return this.func_146961_a(p_146962_1_) ? this.field_146969_h : this.field_146966_g;
   }

   public List<ResourcePackListEntry> func_146964_g() {
      return this.field_146966_g;
   }

   public List<ResourcePackListEntry> func_146963_h() {
      return this.field_146969_h;
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if (p_146284_1_.field_146124_l) {
         if (p_146284_1_.field_146127_k == 2) {
            File lvt_2_1_ = this.field_146297_k.func_110438_M().func_110612_e();
            String lvt_3_1_ = lvt_2_1_.getAbsolutePath();
            if (Util.func_110647_a() == Util.EnumOS.OSX) {
               try {
                  field_146968_a.info(lvt_3_1_);
                  Runtime.getRuntime().exec(new String[]{"/usr/bin/open", lvt_3_1_});
                  return;
               } catch (IOException var9) {
                  field_146968_a.error((String)"Couldn't open file", (Throwable)var9);
               }
            } else if (Util.func_110647_a() == Util.EnumOS.WINDOWS) {
               String lvt_4_2_ = String.format("cmd.exe /C start \"Open file\" \"%s\"", lvt_3_1_);

               try {
                  Runtime.getRuntime().exec(lvt_4_2_);
                  return;
               } catch (IOException var8) {
                  field_146968_a.error((String)"Couldn't open file", (Throwable)var8);
               }
            }

            boolean lvt_4_3_ = false;

            try {
               Class<?> lvt_5_2_ = Class.forName("java.awt.Desktop");
               Object lvt_6_1_ = lvt_5_2_.getMethod("getDesktop").invoke((Object)null);
               lvt_5_2_.getMethod("browse", URI.class).invoke(lvt_6_1_, lvt_2_1_.toURI());
            } catch (Throwable var7) {
               field_146968_a.error("Couldn't open link", var7);
               lvt_4_3_ = true;
            }

            if (lvt_4_3_) {
               field_146968_a.info("Opening via system class!");
               Sys.openURL("file://" + lvt_3_1_);
            }
         } else if (p_146284_1_.field_146127_k == 1) {
            if (this.field_175289_s) {
               List<ResourcePackRepository.Entry> lvt_2_2_ = Lists.newArrayList();
               Iterator lvt_3_3_ = this.field_146969_h.iterator();

               while(lvt_3_3_.hasNext()) {
                  ResourcePackListEntry lvt_4_4_ = (ResourcePackListEntry)lvt_3_3_.next();
                  if (lvt_4_4_ instanceof ResourcePackListEntryFound) {
                     lvt_2_2_.add(((ResourcePackListEntryFound)lvt_4_4_).func_148318_i());
                  }
               }

               Collections.reverse(lvt_2_2_);
               this.field_146297_k.func_110438_M().func_148527_a(lvt_2_2_);
               this.field_146297_k.field_71474_y.field_151453_l.clear();
               this.field_146297_k.field_71474_y.field_183018_l.clear();
               lvt_3_3_ = lvt_2_2_.iterator();

               while(lvt_3_3_.hasNext()) {
                  ResourcePackRepository.Entry lvt_4_5_ = (ResourcePackRepository.Entry)lvt_3_3_.next();
                  this.field_146297_k.field_71474_y.field_151453_l.add(lvt_4_5_.func_110515_d());
                  if (lvt_4_5_.func_183027_f() != 1) {
                     this.field_146297_k.field_71474_y.field_183018_l.add(lvt_4_5_.func_110515_d());
                  }
               }

               this.field_146297_k.field_71474_y.func_74303_b();
               this.field_146297_k.func_110436_a();
            }

            this.field_146297_k.func_147108_a(this.field_146965_f);
         }

      }
   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
      this.field_146970_i.func_148179_a(p_73864_1_, p_73864_2_, p_73864_3_);
      this.field_146967_r.func_148179_a(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
      super.func_146286_b(p_146286_1_, p_146286_2_, p_146286_3_);
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146278_c(0);
      this.field_146970_i.func_148128_a(p_73863_1_, p_73863_2_, p_73863_3_);
      this.field_146967_r.func_148128_a(p_73863_1_, p_73863_2_, p_73863_3_);
      this.func_73732_a(this.field_146289_q, I18n.func_135052_a("resourcePack.title"), this.field_146294_l / 2, 16, 16777215);
      this.func_73732_a(this.field_146289_q, I18n.func_135052_a("resourcePack.folderInfo"), this.field_146294_l / 2 - 77, this.field_146295_m - 26, 8421504);
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   public void func_175288_g() {
      this.field_175289_s = true;
   }
}
