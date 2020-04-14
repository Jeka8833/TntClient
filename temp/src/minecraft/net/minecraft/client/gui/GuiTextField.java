package net.minecraft.client.gui;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;

public class GuiTextField extends Gui {
   private final int field_175208_g;
   private final FontRenderer field_146211_a;
   public int field_146209_f;
   public int field_146210_g;
   private final int field_146218_h;
   private final int field_146219_i;
   private String field_146216_j = "";
   private int field_146217_k = 32;
   private int field_146214_l;
   private boolean field_146215_m = true;
   private boolean field_146212_n = true;
   private boolean field_146213_o;
   private boolean field_146226_p = true;
   private int field_146225_q;
   private int field_146224_r;
   private int field_146223_s;
   private int field_146222_t = 14737632;
   private int field_146221_u = 7368816;
   private boolean field_146220_v = true;
   private GuiPageButtonList.GuiResponder field_175210_x;
   private Predicate<String> field_175209_y = Predicates.alwaysTrue();

   public GuiTextField(int p_i45542_1_, FontRenderer p_i45542_2_, int p_i45542_3_, int p_i45542_4_, int p_i45542_5_, int p_i45542_6_) {
      this.field_175208_g = p_i45542_1_;
      this.field_146211_a = p_i45542_2_;
      this.field_146209_f = p_i45542_3_;
      this.field_146210_g = p_i45542_4_;
      this.field_146218_h = p_i45542_5_;
      this.field_146219_i = p_i45542_6_;
   }

   public void func_175207_a(GuiPageButtonList.GuiResponder p_175207_1_) {
      this.field_175210_x = p_175207_1_;
   }

   public void func_146178_a() {
      ++this.field_146214_l;
   }

   public void func_146180_a(String p_146180_1_) {
      if (this.field_175209_y.apply(p_146180_1_)) {
         if (p_146180_1_.length() > this.field_146217_k) {
            this.field_146216_j = p_146180_1_.substring(0, this.field_146217_k);
         } else {
            this.field_146216_j = p_146180_1_;
         }

         this.func_146202_e();
      }
   }

   public String func_146179_b() {
      return this.field_146216_j;
   }

   public String func_146207_c() {
      int lvt_1_1_ = this.field_146224_r < this.field_146223_s ? this.field_146224_r : this.field_146223_s;
      int lvt_2_1_ = this.field_146224_r < this.field_146223_s ? this.field_146223_s : this.field_146224_r;
      return this.field_146216_j.substring(lvt_1_1_, lvt_2_1_);
   }

   public void func_175205_a(Predicate<String> p_175205_1_) {
      this.field_175209_y = p_175205_1_;
   }

   public void func_146191_b(String p_146191_1_) {
      String lvt_2_1_ = "";
      String lvt_3_1_ = ChatAllowedCharacters.func_71565_a(p_146191_1_);
      int lvt_4_1_ = this.field_146224_r < this.field_146223_s ? this.field_146224_r : this.field_146223_s;
      int lvt_5_1_ = this.field_146224_r < this.field_146223_s ? this.field_146223_s : this.field_146224_r;
      int lvt_6_1_ = this.field_146217_k - this.field_146216_j.length() - (lvt_4_1_ - lvt_5_1_);
      int lvt_7_1_ = false;
      if (this.field_146216_j.length() > 0) {
         lvt_2_1_ = lvt_2_1_ + this.field_146216_j.substring(0, lvt_4_1_);
      }

      int lvt_7_1_;
      if (lvt_6_1_ < lvt_3_1_.length()) {
         lvt_2_1_ = lvt_2_1_ + lvt_3_1_.substring(0, lvt_6_1_);
         lvt_7_1_ = lvt_6_1_;
      } else {
         lvt_2_1_ = lvt_2_1_ + lvt_3_1_;
         lvt_7_1_ = lvt_3_1_.length();
      }

      if (this.field_146216_j.length() > 0 && lvt_5_1_ < this.field_146216_j.length()) {
         lvt_2_1_ = lvt_2_1_ + this.field_146216_j.substring(lvt_5_1_);
      }

      if (this.field_175209_y.apply(lvt_2_1_)) {
         this.field_146216_j = lvt_2_1_;
         this.func_146182_d(lvt_4_1_ - this.field_146223_s + lvt_7_1_);
         if (this.field_175210_x != null) {
            this.field_175210_x.func_175319_a(this.field_175208_g, this.field_146216_j);
         }

      }
   }

   public void func_146177_a(int p_146177_1_) {
      if (this.field_146216_j.length() != 0) {
         if (this.field_146223_s != this.field_146224_r) {
            this.func_146191_b("");
         } else {
            this.func_146175_b(this.func_146187_c(p_146177_1_) - this.field_146224_r);
         }
      }
   }

   public void func_146175_b(int p_146175_1_) {
      if (this.field_146216_j.length() != 0) {
         if (this.field_146223_s != this.field_146224_r) {
            this.func_146191_b("");
         } else {
            boolean lvt_2_1_ = p_146175_1_ < 0;
            int lvt_3_1_ = lvt_2_1_ ? this.field_146224_r + p_146175_1_ : this.field_146224_r;
            int lvt_4_1_ = lvt_2_1_ ? this.field_146224_r : this.field_146224_r + p_146175_1_;
            String lvt_5_1_ = "";
            if (lvt_3_1_ >= 0) {
               lvt_5_1_ = this.field_146216_j.substring(0, lvt_3_1_);
            }

            if (lvt_4_1_ < this.field_146216_j.length()) {
               lvt_5_1_ = lvt_5_1_ + this.field_146216_j.substring(lvt_4_1_);
            }

            if (this.field_175209_y.apply(lvt_5_1_)) {
               this.field_146216_j = lvt_5_1_;
               if (lvt_2_1_) {
                  this.func_146182_d(p_146175_1_);
               }

               if (this.field_175210_x != null) {
                  this.field_175210_x.func_175319_a(this.field_175208_g, this.field_146216_j);
               }

            }
         }
      }
   }

   public int func_175206_d() {
      return this.field_175208_g;
   }

   public int func_146187_c(int p_146187_1_) {
      return this.func_146183_a(p_146187_1_, this.func_146198_h());
   }

   public int func_146183_a(int p_146183_1_, int p_146183_2_) {
      return this.func_146197_a(p_146183_1_, p_146183_2_, true);
   }

   public int func_146197_a(int p_146197_1_, int p_146197_2_, boolean p_146197_3_) {
      int lvt_4_1_ = p_146197_2_;
      boolean lvt_5_1_ = p_146197_1_ < 0;
      int lvt_6_1_ = Math.abs(p_146197_1_);

      for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_; ++lvt_7_1_) {
         if (!lvt_5_1_) {
            int lvt_8_1_ = this.field_146216_j.length();
            lvt_4_1_ = this.field_146216_j.indexOf(32, lvt_4_1_);
            if (lvt_4_1_ == -1) {
               lvt_4_1_ = lvt_8_1_;
            } else {
               while(p_146197_3_ && lvt_4_1_ < lvt_8_1_ && this.field_146216_j.charAt(lvt_4_1_) == ' ') {
                  ++lvt_4_1_;
               }
            }
         } else {
            while(p_146197_3_ && lvt_4_1_ > 0 && this.field_146216_j.charAt(lvt_4_1_ - 1) == ' ') {
               --lvt_4_1_;
            }

            while(lvt_4_1_ > 0 && this.field_146216_j.charAt(lvt_4_1_ - 1) != ' ') {
               --lvt_4_1_;
            }
         }
      }

      return lvt_4_1_;
   }

   public void func_146182_d(int p_146182_1_) {
      this.func_146190_e(this.field_146223_s + p_146182_1_);
   }

   public void func_146190_e(int p_146190_1_) {
      this.field_146224_r = p_146190_1_;
      int lvt_2_1_ = this.field_146216_j.length();
      this.field_146224_r = MathHelper.func_76125_a(this.field_146224_r, 0, lvt_2_1_);
      this.func_146199_i(this.field_146224_r);
   }

   public void func_146196_d() {
      this.func_146190_e(0);
   }

   public void func_146202_e() {
      this.func_146190_e(this.field_146216_j.length());
   }

   public boolean func_146201_a(char p_146201_1_, int p_146201_2_) {
      if (!this.field_146213_o) {
         return false;
      } else if (GuiScreen.func_175278_g(p_146201_2_)) {
         this.func_146202_e();
         this.func_146199_i(0);
         return true;
      } else if (GuiScreen.func_175280_f(p_146201_2_)) {
         GuiScreen.func_146275_d(this.func_146207_c());
         return true;
      } else if (GuiScreen.func_175279_e(p_146201_2_)) {
         if (this.field_146226_p) {
            this.func_146191_b(GuiScreen.func_146277_j());
         }

         return true;
      } else if (GuiScreen.func_175277_d(p_146201_2_)) {
         GuiScreen.func_146275_d(this.func_146207_c());
         if (this.field_146226_p) {
            this.func_146191_b("");
         }

         return true;
      } else {
         switch(p_146201_2_) {
         case 14:
            if (GuiScreen.func_146271_m()) {
               if (this.field_146226_p) {
                  this.func_146177_a(-1);
               }
            } else if (this.field_146226_p) {
               this.func_146175_b(-1);
            }

            return true;
         case 199:
            if (GuiScreen.func_146272_n()) {
               this.func_146199_i(0);
            } else {
               this.func_146196_d();
            }

            return true;
         case 203:
            if (GuiScreen.func_146272_n()) {
               if (GuiScreen.func_146271_m()) {
                  this.func_146199_i(this.func_146183_a(-1, this.func_146186_n()));
               } else {
                  this.func_146199_i(this.func_146186_n() - 1);
               }
            } else if (GuiScreen.func_146271_m()) {
               this.func_146190_e(this.func_146187_c(-1));
            } else {
               this.func_146182_d(-1);
            }

            return true;
         case 205:
            if (GuiScreen.func_146272_n()) {
               if (GuiScreen.func_146271_m()) {
                  this.func_146199_i(this.func_146183_a(1, this.func_146186_n()));
               } else {
                  this.func_146199_i(this.func_146186_n() + 1);
               }
            } else if (GuiScreen.func_146271_m()) {
               this.func_146190_e(this.func_146187_c(1));
            } else {
               this.func_146182_d(1);
            }

            return true;
         case 207:
            if (GuiScreen.func_146272_n()) {
               this.func_146199_i(this.field_146216_j.length());
            } else {
               this.func_146202_e();
            }

            return true;
         case 211:
            if (GuiScreen.func_146271_m()) {
               if (this.field_146226_p) {
                  this.func_146177_a(1);
               }
            } else if (this.field_146226_p) {
               this.func_146175_b(1);
            }

            return true;
         default:
            if (ChatAllowedCharacters.func_71566_a(p_146201_1_)) {
               if (this.field_146226_p) {
                  this.func_146191_b(Character.toString(p_146201_1_));
               }

               return true;
            } else {
               return false;
            }
         }
      }
   }

   public void func_146192_a(int p_146192_1_, int p_146192_2_, int p_146192_3_) {
      boolean lvt_4_1_ = p_146192_1_ >= this.field_146209_f && p_146192_1_ < this.field_146209_f + this.field_146218_h && p_146192_2_ >= this.field_146210_g && p_146192_2_ < this.field_146210_g + this.field_146219_i;
      if (this.field_146212_n) {
         this.func_146195_b(lvt_4_1_);
      }

      if (this.field_146213_o && lvt_4_1_ && p_146192_3_ == 0) {
         int lvt_5_1_ = p_146192_1_ - this.field_146209_f;
         if (this.field_146215_m) {
            lvt_5_1_ -= 4;
         }

         String lvt_6_1_ = this.field_146211_a.func_78269_a(this.field_146216_j.substring(this.field_146225_q), this.func_146200_o());
         this.func_146190_e(this.field_146211_a.func_78269_a(lvt_6_1_, lvt_5_1_).length() + this.field_146225_q);
      }

   }

   public void func_146194_f() {
      if (this.func_146176_q()) {
         if (this.func_146181_i()) {
            func_73734_a(this.field_146209_f - 1, this.field_146210_g - 1, this.field_146209_f + this.field_146218_h + 1, this.field_146210_g + this.field_146219_i + 1, -6250336);
            func_73734_a(this.field_146209_f, this.field_146210_g, this.field_146209_f + this.field_146218_h, this.field_146210_g + this.field_146219_i, -16777216);
         }

         int lvt_1_1_ = this.field_146226_p ? this.field_146222_t : this.field_146221_u;
         int lvt_2_1_ = this.field_146224_r - this.field_146225_q;
         int lvt_3_1_ = this.field_146223_s - this.field_146225_q;
         String lvt_4_1_ = this.field_146211_a.func_78269_a(this.field_146216_j.substring(this.field_146225_q), this.func_146200_o());
         boolean lvt_5_1_ = lvt_2_1_ >= 0 && lvt_2_1_ <= lvt_4_1_.length();
         boolean lvt_6_1_ = this.field_146213_o && this.field_146214_l / 6 % 2 == 0 && lvt_5_1_;
         int lvt_7_1_ = this.field_146215_m ? this.field_146209_f + 4 : this.field_146209_f;
         int lvt_8_1_ = this.field_146215_m ? this.field_146210_g + (this.field_146219_i - 8) / 2 : this.field_146210_g;
         int lvt_9_1_ = lvt_7_1_;
         if (lvt_3_1_ > lvt_4_1_.length()) {
            lvt_3_1_ = lvt_4_1_.length();
         }

         if (lvt_4_1_.length() > 0) {
            String lvt_10_1_ = lvt_5_1_ ? lvt_4_1_.substring(0, lvt_2_1_) : lvt_4_1_;
            lvt_9_1_ = this.field_146211_a.func_175063_a(lvt_10_1_, (float)lvt_7_1_, (float)lvt_8_1_, lvt_1_1_);
         }

         boolean lvt_10_2_ = this.field_146224_r < this.field_146216_j.length() || this.field_146216_j.length() >= this.func_146208_g();
         int lvt_11_1_ = lvt_9_1_;
         if (!lvt_5_1_) {
            lvt_11_1_ = lvt_2_1_ > 0 ? lvt_7_1_ + this.field_146218_h : lvt_7_1_;
         } else if (lvt_10_2_) {
            lvt_11_1_ = lvt_9_1_ - 1;
            --lvt_9_1_;
         }

         if (lvt_4_1_.length() > 0 && lvt_5_1_ && lvt_2_1_ < lvt_4_1_.length()) {
            lvt_9_1_ = this.field_146211_a.func_175063_a(lvt_4_1_.substring(lvt_2_1_), (float)lvt_9_1_, (float)lvt_8_1_, lvt_1_1_);
         }

         if (lvt_6_1_) {
            if (lvt_10_2_) {
               Gui.func_73734_a(lvt_11_1_, lvt_8_1_ - 1, lvt_11_1_ + 1, lvt_8_1_ + 1 + this.field_146211_a.field_78288_b, -3092272);
            } else {
               this.field_146211_a.func_175063_a("_", (float)lvt_11_1_, (float)lvt_8_1_, lvt_1_1_);
            }
         }

         if (lvt_3_1_ != lvt_2_1_) {
            int lvt_12_1_ = lvt_7_1_ + this.field_146211_a.func_78256_a(lvt_4_1_.substring(0, lvt_3_1_));
            this.func_146188_c(lvt_11_1_, lvt_8_1_ - 1, lvt_12_1_ - 1, lvt_8_1_ + 1 + this.field_146211_a.field_78288_b);
         }

      }
   }

   private void func_146188_c(int p_146188_1_, int p_146188_2_, int p_146188_3_, int p_146188_4_) {
      int lvt_5_2_;
      if (p_146188_1_ < p_146188_3_) {
         lvt_5_2_ = p_146188_1_;
         p_146188_1_ = p_146188_3_;
         p_146188_3_ = lvt_5_2_;
      }

      if (p_146188_2_ < p_146188_4_) {
         lvt_5_2_ = p_146188_2_;
         p_146188_2_ = p_146188_4_;
         p_146188_4_ = lvt_5_2_;
      }

      if (p_146188_3_ > this.field_146209_f + this.field_146218_h) {
         p_146188_3_ = this.field_146209_f + this.field_146218_h;
      }

      if (p_146188_1_ > this.field_146209_f + this.field_146218_h) {
         p_146188_1_ = this.field_146209_f + this.field_146218_h;
      }

      Tessellator lvt_5_3_ = Tessellator.func_178181_a();
      WorldRenderer lvt_6_1_ = lvt_5_3_.func_178180_c();
      GlStateManager.func_179131_c(0.0F, 0.0F, 255.0F, 255.0F);
      GlStateManager.func_179090_x();
      GlStateManager.func_179115_u();
      GlStateManager.func_179116_f(5387);
      lvt_6_1_.func_181668_a(7, DefaultVertexFormats.field_181705_e);
      lvt_6_1_.func_181662_b((double)p_146188_1_, (double)p_146188_4_, 0.0D).func_181675_d();
      lvt_6_1_.func_181662_b((double)p_146188_3_, (double)p_146188_4_, 0.0D).func_181675_d();
      lvt_6_1_.func_181662_b((double)p_146188_3_, (double)p_146188_2_, 0.0D).func_181675_d();
      lvt_6_1_.func_181662_b((double)p_146188_1_, (double)p_146188_2_, 0.0D).func_181675_d();
      lvt_5_3_.func_78381_a();
      GlStateManager.func_179134_v();
      GlStateManager.func_179098_w();
   }

   public void func_146203_f(int p_146203_1_) {
      this.field_146217_k = p_146203_1_;
      if (this.field_146216_j.length() > p_146203_1_) {
         this.field_146216_j = this.field_146216_j.substring(0, p_146203_1_);
      }

   }

   public int func_146208_g() {
      return this.field_146217_k;
   }

   public int func_146198_h() {
      return this.field_146224_r;
   }

   public boolean func_146181_i() {
      return this.field_146215_m;
   }

   public void func_146185_a(boolean p_146185_1_) {
      this.field_146215_m = p_146185_1_;
   }

   public void func_146193_g(int p_146193_1_) {
      this.field_146222_t = p_146193_1_;
   }

   public void func_146204_h(int p_146204_1_) {
      this.field_146221_u = p_146204_1_;
   }

   public void func_146195_b(boolean p_146195_1_) {
      if (p_146195_1_ && !this.field_146213_o) {
         this.field_146214_l = 0;
      }

      this.field_146213_o = p_146195_1_;
   }

   public boolean func_146206_l() {
      return this.field_146213_o;
   }

   public void func_146184_c(boolean p_146184_1_) {
      this.field_146226_p = p_146184_1_;
   }

   public int func_146186_n() {
      return this.field_146223_s;
   }

   public int func_146200_o() {
      return this.func_146181_i() ? this.field_146218_h - 8 : this.field_146218_h;
   }

   public void func_146199_i(int p_146199_1_) {
      int lvt_2_1_ = this.field_146216_j.length();
      if (p_146199_1_ > lvt_2_1_) {
         p_146199_1_ = lvt_2_1_;
      }

      if (p_146199_1_ < 0) {
         p_146199_1_ = 0;
      }

      this.field_146223_s = p_146199_1_;
      if (this.field_146211_a != null) {
         if (this.field_146225_q > lvt_2_1_) {
            this.field_146225_q = lvt_2_1_;
         }

         int lvt_3_1_ = this.func_146200_o();
         String lvt_4_1_ = this.field_146211_a.func_78269_a(this.field_146216_j.substring(this.field_146225_q), lvt_3_1_);
         int lvt_5_1_ = lvt_4_1_.length() + this.field_146225_q;
         if (p_146199_1_ == this.field_146225_q) {
            this.field_146225_q -= this.field_146211_a.func_78262_a(this.field_146216_j, lvt_3_1_, true).length();
         }

         if (p_146199_1_ > lvt_5_1_) {
            this.field_146225_q += p_146199_1_ - lvt_5_1_;
         } else if (p_146199_1_ <= this.field_146225_q) {
            this.field_146225_q -= this.field_146225_q - p_146199_1_;
         }

         this.field_146225_q = MathHelper.func_76125_a(this.field_146225_q, 0, lvt_2_1_);
      }

   }

   public void func_146205_d(boolean p_146205_1_) {
      this.field_146212_n = p_146205_1_;
   }

   public boolean func_146176_q() {
      return this.field_146220_v;
   }

   public void func_146189_e(boolean p_146189_1_) {
      this.field_146220_v = p_146189_1_;
   }
}
