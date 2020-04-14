package net.minecraft.client.gui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiSelectWorld extends GuiScreen implements GuiYesNoCallback {
   private static final Logger field_146629_g = LogManager.getLogger();
   private final DateFormat field_146633_h = new SimpleDateFormat();
   protected GuiScreen field_146632_a;
   protected String field_146628_f = "Select world";
   private boolean field_146634_i;
   private int field_146640_r;
   private java.util.List<SaveFormatComparator> field_146639_s;
   private GuiSelectWorld.List field_146638_t;
   private String field_146637_u;
   private String field_146636_v;
   private String[] field_146635_w = new String[4];
   private boolean field_146643_x;
   private GuiButton field_146642_y;
   private GuiButton field_146641_z;
   private GuiButton field_146630_A;
   private GuiButton field_146631_B;

   public GuiSelectWorld(GuiScreen p_i1054_1_) {
      this.field_146632_a = p_i1054_1_;
   }

   public void func_73866_w_() {
      this.field_146628_f = I18n.func_135052_a("selectWorld.title");

      try {
         this.func_146627_h();
      } catch (AnvilConverterException var2) {
         field_146629_g.error((String)"Couldn't load level list", (Throwable)var2);
         this.field_146297_k.func_147108_a(new GuiErrorScreen("Unable to load worlds", var2.getMessage()));
         return;
      }

      this.field_146637_u = I18n.func_135052_a("selectWorld.world");
      this.field_146636_v = I18n.func_135052_a("selectWorld.conversion");
      this.field_146635_w[WorldSettings.GameType.SURVIVAL.func_77148_a()] = I18n.func_135052_a("gameMode.survival");
      this.field_146635_w[WorldSettings.GameType.CREATIVE.func_77148_a()] = I18n.func_135052_a("gameMode.creative");
      this.field_146635_w[WorldSettings.GameType.ADVENTURE.func_77148_a()] = I18n.func_135052_a("gameMode.adventure");
      this.field_146635_w[WorldSettings.GameType.SPECTATOR.func_77148_a()] = I18n.func_135052_a("gameMode.spectator");
      this.field_146638_t = new GuiSelectWorld.List(this.field_146297_k);
      this.field_146638_t.func_148134_d(4, 5);
      this.func_146618_g();
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      this.field_146638_t.func_178039_p();
   }

   private void func_146627_h() throws AnvilConverterException {
      ISaveFormat lvt_1_1_ = this.field_146297_k.func_71359_d();
      this.field_146639_s = lvt_1_1_.func_75799_b();
      Collections.sort(this.field_146639_s);
      this.field_146640_r = -1;
   }

   protected String func_146621_a(int p_146621_1_) {
      return ((SaveFormatComparator)this.field_146639_s.get(p_146621_1_)).func_75786_a();
   }

   protected String func_146614_d(int p_146614_1_) {
      String lvt_2_1_ = ((SaveFormatComparator)this.field_146639_s.get(p_146614_1_)).func_75788_b();
      if (StringUtils.isEmpty(lvt_2_1_)) {
         lvt_2_1_ = I18n.func_135052_a("selectWorld.world") + " " + (p_146614_1_ + 1);
      }

      return lvt_2_1_;
   }

   public void func_146618_g() {
      this.field_146292_n.add(this.field_146641_z = new GuiButton(1, this.field_146294_l / 2 - 154, this.field_146295_m - 52, 150, 20, I18n.func_135052_a("selectWorld.select")));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 2 + 4, this.field_146295_m - 52, 150, 20, I18n.func_135052_a("selectWorld.create")));
      this.field_146292_n.add(this.field_146630_A = new GuiButton(6, this.field_146294_l / 2 - 154, this.field_146295_m - 28, 72, 20, I18n.func_135052_a("selectWorld.rename")));
      this.field_146292_n.add(this.field_146642_y = new GuiButton(2, this.field_146294_l / 2 - 76, this.field_146295_m - 28, 72, 20, I18n.func_135052_a("selectWorld.delete")));
      this.field_146292_n.add(this.field_146631_B = new GuiButton(7, this.field_146294_l / 2 + 4, this.field_146295_m - 28, 72, 20, I18n.func_135052_a("selectWorld.recreate")));
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 + 82, this.field_146295_m - 28, 72, 20, I18n.func_135052_a("gui.cancel")));
      this.field_146641_z.field_146124_l = false;
      this.field_146642_y.field_146124_l = false;
      this.field_146630_A.field_146124_l = false;
      this.field_146631_B.field_146124_l = false;
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if (p_146284_1_.field_146124_l) {
         if (p_146284_1_.field_146127_k == 2) {
            String lvt_2_1_ = this.func_146614_d(this.field_146640_r);
            if (lvt_2_1_ != null) {
               this.field_146643_x = true;
               GuiYesNo lvt_3_1_ = func_152129_a(this, lvt_2_1_, this.field_146640_r);
               this.field_146297_k.func_147108_a(lvt_3_1_);
            }
         } else if (p_146284_1_.field_146127_k == 1) {
            this.func_146615_e(this.field_146640_r);
         } else if (p_146284_1_.field_146127_k == 3) {
            this.field_146297_k.func_147108_a(new GuiCreateWorld(this));
         } else if (p_146284_1_.field_146127_k == 6) {
            this.field_146297_k.func_147108_a(new GuiRenameWorld(this, this.func_146621_a(this.field_146640_r)));
         } else if (p_146284_1_.field_146127_k == 0) {
            this.field_146297_k.func_147108_a(this.field_146632_a);
         } else if (p_146284_1_.field_146127_k == 7) {
            GuiCreateWorld lvt_2_2_ = new GuiCreateWorld(this);
            ISaveHandler lvt_3_2_ = this.field_146297_k.func_71359_d().func_75804_a(this.func_146621_a(this.field_146640_r), false);
            WorldInfo lvt_4_1_ = lvt_3_2_.func_75757_d();
            lvt_3_2_.func_75759_a();
            lvt_2_2_.func_146318_a(lvt_4_1_);
            this.field_146297_k.func_147108_a(lvt_2_2_);
         } else {
            this.field_146638_t.func_148147_a(p_146284_1_);
         }

      }
   }

   public void func_146615_e(int p_146615_1_) {
      this.field_146297_k.func_147108_a((GuiScreen)null);
      if (!this.field_146634_i) {
         this.field_146634_i = true;
         String lvt_2_1_ = this.func_146621_a(p_146615_1_);
         if (lvt_2_1_ == null) {
            lvt_2_1_ = "World" + p_146615_1_;
         }

         String lvt_3_1_ = this.func_146614_d(p_146615_1_);
         if (lvt_3_1_ == null) {
            lvt_3_1_ = "World" + p_146615_1_;
         }

         if (this.field_146297_k.func_71359_d().func_90033_f(lvt_2_1_)) {
            this.field_146297_k.func_71371_a(lvt_2_1_, lvt_3_1_, (WorldSettings)null);
         }

      }
   }

   public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {
      if (this.field_146643_x) {
         this.field_146643_x = false;
         if (p_73878_1_) {
            ISaveFormat lvt_3_1_ = this.field_146297_k.func_71359_d();
            lvt_3_1_.func_75800_d();
            lvt_3_1_.func_75802_e(this.func_146621_a(p_73878_2_));

            try {
               this.func_146627_h();
            } catch (AnvilConverterException var5) {
               field_146629_g.error((String)"Couldn't load level list", (Throwable)var5);
            }
         }

         this.field_146297_k.func_147108_a(this);
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.field_146638_t.func_148128_a(p_73863_1_, p_73863_2_, p_73863_3_);
      this.func_73732_a(this.field_146289_q, this.field_146628_f, this.field_146294_l / 2, 20, 16777215);
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   public static GuiYesNo func_152129_a(GuiYesNoCallback p_152129_0_, String p_152129_1_, int p_152129_2_) {
      String lvt_3_1_ = I18n.func_135052_a("selectWorld.deleteQuestion");
      String lvt_4_1_ = "'" + p_152129_1_ + "' " + I18n.func_135052_a("selectWorld.deleteWarning");
      String lvt_5_1_ = I18n.func_135052_a("selectWorld.deleteButton");
      String lvt_6_1_ = I18n.func_135052_a("gui.cancel");
      GuiYesNo lvt_7_1_ = new GuiYesNo(p_152129_0_, lvt_3_1_, lvt_4_1_, lvt_5_1_, lvt_6_1_, p_152129_2_);
      return lvt_7_1_;
   }

   class List extends GuiSlot {
      public List(Minecraft p_i45517_2_) {
         super(p_i45517_2_, GuiSelectWorld.this.field_146294_l, GuiSelectWorld.this.field_146295_m, 32, GuiSelectWorld.this.field_146295_m - 64, 36);
      }

      protected int func_148127_b() {
         return GuiSelectWorld.this.field_146639_s.size();
      }

      protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
         GuiSelectWorld.this.field_146640_r = p_148144_1_;
         boolean lvt_5_1_ = GuiSelectWorld.this.field_146640_r >= 0 && GuiSelectWorld.this.field_146640_r < this.func_148127_b();
         GuiSelectWorld.this.field_146641_z.field_146124_l = lvt_5_1_;
         GuiSelectWorld.this.field_146642_y.field_146124_l = lvt_5_1_;
         GuiSelectWorld.this.field_146630_A.field_146124_l = lvt_5_1_;
         GuiSelectWorld.this.field_146631_B.field_146124_l = lvt_5_1_;
         if (p_148144_2_ && lvt_5_1_) {
            GuiSelectWorld.this.func_146615_e(p_148144_1_);
         }

      }

      protected boolean func_148131_a(int p_148131_1_) {
         return p_148131_1_ == GuiSelectWorld.this.field_146640_r;
      }

      protected int func_148138_e() {
         return GuiSelectWorld.this.field_146639_s.size() * 36;
      }

      protected void func_148123_a() {
         GuiSelectWorld.this.func_146276_q_();
      }

      protected void func_180791_a(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
         SaveFormatComparator lvt_7_1_ = (SaveFormatComparator)GuiSelectWorld.this.field_146639_s.get(p_180791_1_);
         String lvt_8_1_ = lvt_7_1_.func_75788_b();
         if (StringUtils.isEmpty(lvt_8_1_)) {
            lvt_8_1_ = GuiSelectWorld.this.field_146637_u + " " + (p_180791_1_ + 1);
         }

         String lvt_9_1_ = lvt_7_1_.func_75786_a();
         lvt_9_1_ = lvt_9_1_ + " (" + GuiSelectWorld.this.field_146633_h.format(new Date(lvt_7_1_.func_75784_e()));
         lvt_9_1_ = lvt_9_1_ + ")";
         String lvt_10_1_ = "";
         if (lvt_7_1_.func_75785_d()) {
            lvt_10_1_ = GuiSelectWorld.this.field_146636_v + " " + lvt_10_1_;
         } else {
            lvt_10_1_ = GuiSelectWorld.this.field_146635_w[lvt_7_1_.func_75790_f().func_77148_a()];
            if (lvt_7_1_.func_75789_g()) {
               lvt_10_1_ = EnumChatFormatting.DARK_RED + I18n.func_135052_a("gameMode.hardcore") + EnumChatFormatting.RESET;
            }

            if (lvt_7_1_.func_75783_h()) {
               lvt_10_1_ = lvt_10_1_ + ", " + I18n.func_135052_a("selectWorld.cheats");
            }
         }

         GuiSelectWorld.this.func_73731_b(GuiSelectWorld.this.field_146289_q, lvt_8_1_, p_180791_2_ + 2, p_180791_3_ + 1, 16777215);
         GuiSelectWorld.this.func_73731_b(GuiSelectWorld.this.field_146289_q, lvt_9_1_, p_180791_2_ + 2, p_180791_3_ + 12, 8421504);
         GuiSelectWorld.this.func_73731_b(GuiSelectWorld.this.field_146289_q, lvt_10_1_, p_180791_2_ + 2, p_180791_3_ + 12 + 10, 8421504);
      }
   }
}
