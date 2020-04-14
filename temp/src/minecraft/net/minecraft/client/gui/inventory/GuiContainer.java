package net.minecraft.client.gui.inventory;

import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public abstract class GuiContainer extends GuiScreen {
   protected static final ResourceLocation field_147001_a = new ResourceLocation("textures/gui/container/inventory.png");
   protected int field_146999_f = 176;
   protected int field_147000_g = 166;
   public Container field_147002_h;
   protected int field_147003_i;
   protected int field_147009_r;
   private Slot field_147006_u;
   private Slot field_147005_v;
   private boolean field_147004_w;
   private ItemStack field_147012_x;
   private int field_147011_y;
   private int field_147010_z;
   private Slot field_146989_A;
   private long field_146990_B;
   private ItemStack field_146991_C;
   private Slot field_146985_D;
   private long field_146986_E;
   protected final Set<Slot> field_147008_s = Sets.newHashSet();
   protected boolean field_147007_t;
   private int field_146987_F;
   private int field_146988_G;
   private boolean field_146995_H;
   private int field_146996_I;
   private long field_146997_J;
   private Slot field_146998_K;
   private int field_146992_L;
   private boolean field_146993_M;
   private ItemStack field_146994_N;

   public GuiContainer(Container p_i1072_1_) {
      this.field_147002_h = p_i1072_1_;
      this.field_146995_H = true;
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      this.field_146297_k.field_71439_g.field_71070_bA = this.field_147002_h;
      this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
      this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      int lvt_4_1_ = this.field_147003_i;
      int lvt_5_1_ = this.field_147009_r;
      this.func_146976_a(p_73863_3_, p_73863_1_, p_73863_2_);
      GlStateManager.func_179101_C();
      RenderHelper.func_74518_a();
      GlStateManager.func_179140_f();
      GlStateManager.func_179097_i();
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
      RenderHelper.func_74520_c();
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)lvt_4_1_, (float)lvt_5_1_, 0.0F);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179091_B();
      this.field_147006_u = null;
      int lvt_6_1_ = 240;
      int lvt_7_1_ = 240;
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)lvt_6_1_ / 1.0F, (float)lvt_7_1_ / 1.0F);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);

      int lvt_11_3_;
      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_147002_h.field_75151_b.size(); ++lvt_8_1_) {
         Slot lvt_9_1_ = (Slot)this.field_147002_h.field_75151_b.get(lvt_8_1_);
         this.func_146977_a(lvt_9_1_);
         if (this.func_146981_a(lvt_9_1_, p_73863_1_, p_73863_2_) && lvt_9_1_.func_111238_b()) {
            this.field_147006_u = lvt_9_1_;
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            int lvt_10_1_ = lvt_9_1_.field_75223_e;
            lvt_11_3_ = lvt_9_1_.field_75221_f;
            GlStateManager.func_179135_a(true, true, true, false);
            this.func_73733_a(lvt_10_1_, lvt_11_3_, lvt_10_1_ + 16, lvt_11_3_ + 16, -2130706433, -2130706433);
            GlStateManager.func_179135_a(true, true, true, true);
            GlStateManager.func_179145_e();
            GlStateManager.func_179126_j();
         }
      }

      RenderHelper.func_74518_a();
      this.func_146979_b(p_73863_1_, p_73863_2_);
      RenderHelper.func_74520_c();
      InventoryPlayer lvt_8_2_ = this.field_146297_k.field_71439_g.field_71071_by;
      ItemStack lvt_9_2_ = this.field_147012_x == null ? lvt_8_2_.func_70445_o() : this.field_147012_x;
      if (lvt_9_2_ != null) {
         int lvt_10_2_ = 8;
         lvt_11_3_ = this.field_147012_x == null ? 8 : 16;
         String lvt_12_1_ = null;
         if (this.field_147012_x != null && this.field_147004_w) {
            lvt_9_2_ = lvt_9_2_.func_77946_l();
            lvt_9_2_.field_77994_a = MathHelper.func_76123_f((float)lvt_9_2_.field_77994_a / 2.0F);
         } else if (this.field_147007_t && this.field_147008_s.size() > 1) {
            lvt_9_2_ = lvt_9_2_.func_77946_l();
            lvt_9_2_.field_77994_a = this.field_146996_I;
            if (lvt_9_2_.field_77994_a == 0) {
               lvt_12_1_ = "" + EnumChatFormatting.YELLOW + "0";
            }
         }

         this.func_146982_a(lvt_9_2_, p_73863_1_ - lvt_4_1_ - lvt_10_2_, p_73863_2_ - lvt_5_1_ - lvt_11_3_, lvt_12_1_);
      }

      if (this.field_146991_C != null) {
         float lvt_10_3_ = (float)(Minecraft.func_71386_F() - this.field_146990_B) / 100.0F;
         if (lvt_10_3_ >= 1.0F) {
            lvt_10_3_ = 1.0F;
            this.field_146991_C = null;
         }

         lvt_11_3_ = this.field_146989_A.field_75223_e - this.field_147011_y;
         int lvt_12_2_ = this.field_146989_A.field_75221_f - this.field_147010_z;
         int lvt_13_1_ = this.field_147011_y + (int)((float)lvt_11_3_ * lvt_10_3_);
         int lvt_14_1_ = this.field_147010_z + (int)((float)lvt_12_2_ * lvt_10_3_);
         this.func_146982_a(this.field_146991_C, lvt_13_1_, lvt_14_1_, (String)null);
      }

      GlStateManager.func_179121_F();
      if (lvt_8_2_.func_70445_o() == null && this.field_147006_u != null && this.field_147006_u.func_75216_d()) {
         ItemStack lvt_10_4_ = this.field_147006_u.func_75211_c();
         this.func_146285_a(lvt_10_4_, p_73863_1_, p_73863_2_);
      }

      GlStateManager.func_179145_e();
      GlStateManager.func_179126_j();
      RenderHelper.func_74519_b();
   }

   private void func_146982_a(ItemStack p_146982_1_, int p_146982_2_, int p_146982_3_, String p_146982_4_) {
      GlStateManager.func_179109_b(0.0F, 0.0F, 32.0F);
      this.field_73735_i = 200.0F;
      this.field_146296_j.field_77023_b = 200.0F;
      this.field_146296_j.func_180450_b(p_146982_1_, p_146982_2_, p_146982_3_);
      this.field_146296_j.func_180453_a(this.field_146289_q, p_146982_1_, p_146982_2_, p_146982_3_ - (this.field_147012_x == null ? 0 : 8), p_146982_4_);
      this.field_73735_i = 0.0F;
      this.field_146296_j.field_77023_b = 0.0F;
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
   }

   protected abstract void func_146976_a(float var1, int var2, int var3);

   private void func_146977_a(Slot p_146977_1_) {
      int lvt_2_1_ = p_146977_1_.field_75223_e;
      int lvt_3_1_ = p_146977_1_.field_75221_f;
      ItemStack lvt_4_1_ = p_146977_1_.func_75211_c();
      boolean lvt_5_1_ = false;
      boolean lvt_6_1_ = p_146977_1_ == this.field_147005_v && this.field_147012_x != null && !this.field_147004_w;
      ItemStack lvt_7_1_ = this.field_146297_k.field_71439_g.field_71071_by.func_70445_o();
      String lvt_8_1_ = null;
      if (p_146977_1_ == this.field_147005_v && this.field_147012_x != null && this.field_147004_w && lvt_4_1_ != null) {
         lvt_4_1_ = lvt_4_1_.func_77946_l();
         lvt_4_1_.field_77994_a /= 2;
      } else if (this.field_147007_t && this.field_147008_s.contains(p_146977_1_) && lvt_7_1_ != null) {
         if (this.field_147008_s.size() == 1) {
            return;
         }

         if (Container.func_94527_a(p_146977_1_, lvt_7_1_, true) && this.field_147002_h.func_94531_b(p_146977_1_)) {
            lvt_4_1_ = lvt_7_1_.func_77946_l();
            lvt_5_1_ = true;
            Container.func_94525_a(this.field_147008_s, this.field_146987_F, lvt_4_1_, p_146977_1_.func_75211_c() == null ? 0 : p_146977_1_.func_75211_c().field_77994_a);
            if (lvt_4_1_.field_77994_a > lvt_4_1_.func_77976_d()) {
               lvt_8_1_ = EnumChatFormatting.YELLOW + "" + lvt_4_1_.func_77976_d();
               lvt_4_1_.field_77994_a = lvt_4_1_.func_77976_d();
            }

            if (lvt_4_1_.field_77994_a > p_146977_1_.func_178170_b(lvt_4_1_)) {
               lvt_8_1_ = EnumChatFormatting.YELLOW + "" + p_146977_1_.func_178170_b(lvt_4_1_);
               lvt_4_1_.field_77994_a = p_146977_1_.func_178170_b(lvt_4_1_);
            }
         } else {
            this.field_147008_s.remove(p_146977_1_);
            this.func_146980_g();
         }
      }

      this.field_73735_i = 100.0F;
      this.field_146296_j.field_77023_b = 100.0F;
      if (lvt_4_1_ == null) {
         String lvt_9_1_ = p_146977_1_.func_178171_c();
         if (lvt_9_1_ != null) {
            TextureAtlasSprite lvt_10_1_ = this.field_146297_k.func_147117_R().func_110572_b(lvt_9_1_);
            GlStateManager.func_179140_f();
            this.field_146297_k.func_110434_K().func_110577_a(TextureMap.field_110575_b);
            this.func_175175_a(lvt_2_1_, lvt_3_1_, lvt_10_1_, 16, 16);
            GlStateManager.func_179145_e();
            lvt_6_1_ = true;
         }
      }

      if (!lvt_6_1_) {
         if (lvt_5_1_) {
            func_73734_a(lvt_2_1_, lvt_3_1_, lvt_2_1_ + 16, lvt_3_1_ + 16, -2130706433);
         }

         GlStateManager.func_179126_j();
         this.field_146296_j.func_180450_b(lvt_4_1_, lvt_2_1_, lvt_3_1_);
         this.field_146296_j.func_180453_a(this.field_146289_q, lvt_4_1_, lvt_2_1_, lvt_3_1_, lvt_8_1_);
      }

      this.field_146296_j.field_77023_b = 0.0F;
      this.field_73735_i = 0.0F;
   }

   private void func_146980_g() {
      ItemStack lvt_1_1_ = this.field_146297_k.field_71439_g.field_71071_by.func_70445_o();
      if (lvt_1_1_ != null && this.field_147007_t) {
         this.field_146996_I = lvt_1_1_.field_77994_a;

         ItemStack lvt_4_1_;
         int lvt_5_1_;
         for(Iterator lvt_2_1_ = this.field_147008_s.iterator(); lvt_2_1_.hasNext(); this.field_146996_I -= lvt_4_1_.field_77994_a - lvt_5_1_) {
            Slot lvt_3_1_ = (Slot)lvt_2_1_.next();
            lvt_4_1_ = lvt_1_1_.func_77946_l();
            lvt_5_1_ = lvt_3_1_.func_75211_c() == null ? 0 : lvt_3_1_.func_75211_c().field_77994_a;
            Container.func_94525_a(this.field_147008_s, this.field_146987_F, lvt_4_1_, lvt_5_1_);
            if (lvt_4_1_.field_77994_a > lvt_4_1_.func_77976_d()) {
               lvt_4_1_.field_77994_a = lvt_4_1_.func_77976_d();
            }

            if (lvt_4_1_.field_77994_a > lvt_3_1_.func_178170_b(lvt_4_1_)) {
               lvt_4_1_.field_77994_a = lvt_3_1_.func_178170_b(lvt_4_1_);
            }
         }

      }
   }

   private Slot func_146975_c(int p_146975_1_, int p_146975_2_) {
      for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_147002_h.field_75151_b.size(); ++lvt_3_1_) {
         Slot lvt_4_1_ = (Slot)this.field_147002_h.field_75151_b.get(lvt_3_1_);
         if (this.func_146981_a(lvt_4_1_, p_146975_1_, p_146975_2_)) {
            return lvt_4_1_;
         }
      }

      return null;
   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
      boolean lvt_4_1_ = p_73864_3_ == this.field_146297_k.field_71474_y.field_74310_D.func_151463_i() + 100;
      Slot lvt_5_1_ = this.func_146975_c(p_73864_1_, p_73864_2_);
      long lvt_6_1_ = Minecraft.func_71386_F();
      this.field_146993_M = this.field_146998_K == lvt_5_1_ && lvt_6_1_ - this.field_146997_J < 250L && this.field_146992_L == p_73864_3_;
      this.field_146995_H = false;
      if (p_73864_3_ == 0 || p_73864_3_ == 1 || lvt_4_1_) {
         int lvt_8_1_ = this.field_147003_i;
         int lvt_9_1_ = this.field_147009_r;
         boolean lvt_10_1_ = p_73864_1_ < lvt_8_1_ || p_73864_2_ < lvt_9_1_ || p_73864_1_ >= lvt_8_1_ + this.field_146999_f || p_73864_2_ >= lvt_9_1_ + this.field_147000_g;
         int lvt_11_1_ = -1;
         if (lvt_5_1_ != null) {
            lvt_11_1_ = lvt_5_1_.field_75222_d;
         }

         if (lvt_10_1_) {
            lvt_11_1_ = -999;
         }

         if (this.field_146297_k.field_71474_y.field_85185_A && lvt_10_1_ && this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() == null) {
            this.field_146297_k.func_147108_a((GuiScreen)null);
            return;
         }

         if (lvt_11_1_ != -1) {
            if (this.field_146297_k.field_71474_y.field_85185_A) {
               if (lvt_5_1_ != null && lvt_5_1_.func_75216_d()) {
                  this.field_147005_v = lvt_5_1_;
                  this.field_147012_x = null;
                  this.field_147004_w = p_73864_3_ == 1;
               } else {
                  this.field_147005_v = null;
               }
            } else if (!this.field_147007_t) {
               if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() == null) {
                  if (p_73864_3_ == this.field_146297_k.field_71474_y.field_74310_D.func_151463_i() + 100) {
                     this.func_146984_a(lvt_5_1_, lvt_11_1_, p_73864_3_, 3);
                  } else {
                     boolean lvt_12_1_ = lvt_11_1_ != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                     int lvt_13_1_ = 0;
                     if (lvt_12_1_) {
                        this.field_146994_N = lvt_5_1_ != null && lvt_5_1_.func_75216_d() ? lvt_5_1_.func_75211_c() : null;
                        lvt_13_1_ = 1;
                     } else if (lvt_11_1_ == -999) {
                        lvt_13_1_ = 4;
                     }

                     this.func_146984_a(lvt_5_1_, lvt_11_1_, p_73864_3_, lvt_13_1_);
                  }

                  this.field_146995_H = true;
               } else {
                  this.field_147007_t = true;
                  this.field_146988_G = p_73864_3_;
                  this.field_147008_s.clear();
                  if (p_73864_3_ == 0) {
                     this.field_146987_F = 0;
                  } else if (p_73864_3_ == 1) {
                     this.field_146987_F = 1;
                  } else if (p_73864_3_ == this.field_146297_k.field_71474_y.field_74310_D.func_151463_i() + 100) {
                     this.field_146987_F = 2;
                  }
               }
            }
         }
      }

      this.field_146998_K = lvt_5_1_;
      this.field_146997_J = lvt_6_1_;
      this.field_146992_L = p_73864_3_;
   }

   protected void func_146273_a(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_) {
      Slot lvt_6_1_ = this.func_146975_c(p_146273_1_, p_146273_2_);
      ItemStack lvt_7_1_ = this.field_146297_k.field_71439_g.field_71071_by.func_70445_o();
      if (this.field_147005_v != null && this.field_146297_k.field_71474_y.field_85185_A) {
         if (p_146273_3_ == 0 || p_146273_3_ == 1) {
            if (this.field_147012_x == null) {
               if (lvt_6_1_ != this.field_147005_v && this.field_147005_v.func_75211_c() != null) {
                  this.field_147012_x = this.field_147005_v.func_75211_c().func_77946_l();
               }
            } else if (this.field_147012_x.field_77994_a > 1 && lvt_6_1_ != null && Container.func_94527_a(lvt_6_1_, this.field_147012_x, false)) {
               long lvt_8_1_ = Minecraft.func_71386_F();
               if (this.field_146985_D == lvt_6_1_) {
                  if (lvt_8_1_ - this.field_146986_E > 500L) {
                     this.func_146984_a(this.field_147005_v, this.field_147005_v.field_75222_d, 0, 0);
                     this.func_146984_a(lvt_6_1_, lvt_6_1_.field_75222_d, 1, 0);
                     this.func_146984_a(this.field_147005_v, this.field_147005_v.field_75222_d, 0, 0);
                     this.field_146986_E = lvt_8_1_ + 750L;
                     --this.field_147012_x.field_77994_a;
                  }
               } else {
                  this.field_146985_D = lvt_6_1_;
                  this.field_146986_E = lvt_8_1_;
               }
            }
         }
      } else if (this.field_147007_t && lvt_6_1_ != null && lvt_7_1_ != null && lvt_7_1_.field_77994_a > this.field_147008_s.size() && Container.func_94527_a(lvt_6_1_, lvt_7_1_, true) && lvt_6_1_.func_75214_a(lvt_7_1_) && this.field_147002_h.func_94531_b(lvt_6_1_)) {
         this.field_147008_s.add(lvt_6_1_);
         this.func_146980_g();
      }

   }

   protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
      Slot lvt_4_1_ = this.func_146975_c(p_146286_1_, p_146286_2_);
      int lvt_5_1_ = this.field_147003_i;
      int lvt_6_1_ = this.field_147009_r;
      boolean lvt_7_1_ = p_146286_1_ < lvt_5_1_ || p_146286_2_ < lvt_6_1_ || p_146286_1_ >= lvt_5_1_ + this.field_146999_f || p_146286_2_ >= lvt_6_1_ + this.field_147000_g;
      int lvt_8_1_ = -1;
      if (lvt_4_1_ != null) {
         lvt_8_1_ = lvt_4_1_.field_75222_d;
      }

      if (lvt_7_1_) {
         lvt_8_1_ = -999;
      }

      Slot lvt_10_2_;
      Iterator lvt_9_3_;
      if (this.field_146993_M && lvt_4_1_ != null && p_146286_3_ == 0 && this.field_147002_h.func_94530_a((ItemStack)null, lvt_4_1_)) {
         if (func_146272_n()) {
            if (lvt_4_1_ != null && lvt_4_1_.field_75224_c != null && this.field_146994_N != null) {
               lvt_9_3_ = this.field_147002_h.field_75151_b.iterator();

               while(lvt_9_3_.hasNext()) {
                  lvt_10_2_ = (Slot)lvt_9_3_.next();
                  if (lvt_10_2_ != null && lvt_10_2_.func_82869_a(this.field_146297_k.field_71439_g) && lvt_10_2_.func_75216_d() && lvt_10_2_.field_75224_c == lvt_4_1_.field_75224_c && Container.func_94527_a(lvt_10_2_, this.field_146994_N, true)) {
                     this.func_146984_a(lvt_10_2_, lvt_10_2_.field_75222_d, p_146286_3_, 1);
                  }
               }
            }
         } else {
            this.func_146984_a(lvt_4_1_, lvt_8_1_, p_146286_3_, 6);
         }

         this.field_146993_M = false;
         this.field_146997_J = 0L;
      } else {
         if (this.field_147007_t && this.field_146988_G != p_146286_3_) {
            this.field_147007_t = false;
            this.field_147008_s.clear();
            this.field_146995_H = true;
            return;
         }

         if (this.field_146995_H) {
            this.field_146995_H = false;
            return;
         }

         boolean lvt_9_4_;
         if (this.field_147005_v != null && this.field_146297_k.field_71474_y.field_85185_A) {
            if (p_146286_3_ == 0 || p_146286_3_ == 1) {
               if (this.field_147012_x == null && lvt_4_1_ != this.field_147005_v) {
                  this.field_147012_x = this.field_147005_v.func_75211_c();
               }

               lvt_9_4_ = Container.func_94527_a(lvt_4_1_, this.field_147012_x, false);
               if (lvt_8_1_ != -1 && this.field_147012_x != null && lvt_9_4_) {
                  this.func_146984_a(this.field_147005_v, this.field_147005_v.field_75222_d, p_146286_3_, 0);
                  this.func_146984_a(lvt_4_1_, lvt_8_1_, 0, 0);
                  if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() != null) {
                     this.func_146984_a(this.field_147005_v, this.field_147005_v.field_75222_d, p_146286_3_, 0);
                     this.field_147011_y = p_146286_1_ - lvt_5_1_;
                     this.field_147010_z = p_146286_2_ - lvt_6_1_;
                     this.field_146989_A = this.field_147005_v;
                     this.field_146991_C = this.field_147012_x;
                     this.field_146990_B = Minecraft.func_71386_F();
                  } else {
                     this.field_146991_C = null;
                  }
               } else if (this.field_147012_x != null) {
                  this.field_147011_y = p_146286_1_ - lvt_5_1_;
                  this.field_147010_z = p_146286_2_ - lvt_6_1_;
                  this.field_146989_A = this.field_147005_v;
                  this.field_146991_C = this.field_147012_x;
                  this.field_146990_B = Minecraft.func_71386_F();
               }

               this.field_147012_x = null;
               this.field_147005_v = null;
            }
         } else if (this.field_147007_t && !this.field_147008_s.isEmpty()) {
            this.func_146984_a((Slot)null, -999, Container.func_94534_d(0, this.field_146987_F), 5);
            lvt_9_3_ = this.field_147008_s.iterator();

            while(lvt_9_3_.hasNext()) {
               lvt_10_2_ = (Slot)lvt_9_3_.next();
               this.func_146984_a(lvt_10_2_, lvt_10_2_.field_75222_d, Container.func_94534_d(1, this.field_146987_F), 5);
            }

            this.func_146984_a((Slot)null, -999, Container.func_94534_d(2, this.field_146987_F), 5);
         } else if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() != null) {
            if (p_146286_3_ == this.field_146297_k.field_71474_y.field_74310_D.func_151463_i() + 100) {
               this.func_146984_a(lvt_4_1_, lvt_8_1_, p_146286_3_, 3);
            } else {
               lvt_9_4_ = lvt_8_1_ != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
               if (lvt_9_4_) {
                  this.field_146994_N = lvt_4_1_ != null && lvt_4_1_.func_75216_d() ? lvt_4_1_.func_75211_c() : null;
               }

               this.func_146984_a(lvt_4_1_, lvt_8_1_, p_146286_3_, lvt_9_4_ ? 1 : 0);
            }
         }
      }

      if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() == null) {
         this.field_146997_J = 0L;
      }

      this.field_147007_t = false;
   }

   private boolean func_146981_a(Slot p_146981_1_, int p_146981_2_, int p_146981_3_) {
      return this.func_146978_c(p_146981_1_.field_75223_e, p_146981_1_.field_75221_f, 16, 16, p_146981_2_, p_146981_3_);
   }

   protected boolean func_146978_c(int p_146978_1_, int p_146978_2_, int p_146978_3_, int p_146978_4_, int p_146978_5_, int p_146978_6_) {
      int lvt_7_1_ = this.field_147003_i;
      int lvt_8_1_ = this.field_147009_r;
      p_146978_5_ -= lvt_7_1_;
      p_146978_6_ -= lvt_8_1_;
      return p_146978_5_ >= p_146978_1_ - 1 && p_146978_5_ < p_146978_1_ + p_146978_3_ + 1 && p_146978_6_ >= p_146978_2_ - 1 && p_146978_6_ < p_146978_2_ + p_146978_4_ + 1;
   }

   protected void func_146984_a(Slot p_146984_1_, int p_146984_2_, int p_146984_3_, int p_146984_4_) {
      if (p_146984_1_ != null) {
         p_146984_2_ = p_146984_1_.field_75222_d;
      }

      this.field_146297_k.field_71442_b.func_78753_a(this.field_147002_h.field_75152_c, p_146984_2_, p_146984_3_, p_146984_4_, this.field_146297_k.field_71439_g);
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if (p_73869_2_ == 1 || p_73869_2_ == this.field_146297_k.field_71474_y.field_74313_G.func_151463_i()) {
         this.field_146297_k.field_71439_g.func_71053_j();
      }

      this.func_146983_a(p_73869_2_);
      if (this.field_147006_u != null && this.field_147006_u.func_75216_d()) {
         if (p_73869_2_ == this.field_146297_k.field_71474_y.field_74310_D.func_151463_i()) {
            this.func_146984_a(this.field_147006_u, this.field_147006_u.field_75222_d, 0, 3);
         } else if (p_73869_2_ == this.field_146297_k.field_71474_y.field_74312_F.func_151463_i()) {
            this.func_146984_a(this.field_147006_u, this.field_147006_u.field_75222_d, func_146271_m() ? 1 : 0, 4);
         }
      }

   }

   protected boolean func_146983_a(int p_146983_1_) {
      if (this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() == null && this.field_147006_u != null) {
         for(int lvt_2_1_ = 0; lvt_2_1_ < 9; ++lvt_2_1_) {
            if (p_146983_1_ == this.field_146297_k.field_71474_y.field_74324_K[lvt_2_1_].func_151463_i()) {
               this.func_146984_a(this.field_147006_u, this.field_147006_u.field_75222_d, lvt_2_1_, 2);
               return true;
            }
         }
      }

      return false;
   }

   public void func_146281_b() {
      if (this.field_146297_k.field_71439_g != null) {
         this.field_147002_h.func_75134_a(this.field_146297_k.field_71439_g);
      }
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73876_c() {
      super.func_73876_c();
      if (!this.field_146297_k.field_71439_g.func_70089_S() || this.field_146297_k.field_71439_g.field_70128_L) {
         this.field_146297_k.field_71439_g.func_71053_j();
      }

   }
}
