package net.minecraft.client.gui.achievement;

import java.io.IOException;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class GuiAchievements extends GuiScreen implements IProgressMeter {
   private static final int field_146572_y;
   private static final int field_146571_z;
   private static final int field_146559_A;
   private static final int field_146560_B;
   private static final ResourceLocation field_146561_C;
   protected GuiScreen field_146562_a;
   protected int field_146555_f = 256;
   protected int field_146557_g = 202;
   protected int field_146563_h;
   protected int field_146564_i;
   protected float field_146570_r = 1.0F;
   protected double field_146569_s;
   protected double field_146568_t;
   protected double field_146567_u;
   protected double field_146566_v;
   protected double field_146565_w;
   protected double field_146573_x;
   private int field_146554_D;
   private StatFileWriter field_146556_E;
   private boolean field_146558_F = true;

   public GuiAchievements(GuiScreen p_i45026_1_, StatFileWriter p_i45026_2_) {
      this.field_146562_a = p_i45026_1_;
      this.field_146556_E = p_i45026_2_;
      int lvt_3_1_ = 141;
      int lvt_4_1_ = 141;
      this.field_146569_s = this.field_146567_u = this.field_146565_w = (double)(AchievementList.field_76004_f.field_75993_a * 24 - lvt_3_1_ / 2 - 12);
      this.field_146568_t = this.field_146566_v = this.field_146573_x = (double)(AchievementList.field_76004_f.field_75991_b * 24 - lvt_4_1_ / 2);
   }

   public void func_73866_w_() {
      this.field_146297_k.func_147114_u().func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiOptionButton(1, this.field_146294_l / 2 + 24, this.field_146295_m / 2 + 74, 80, 20, I18n.func_135052_a("gui.done")));
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if (!this.field_146558_F) {
         if (p_146284_1_.field_146127_k == 1) {
            this.field_146297_k.func_147108_a(this.field_146562_a);
         }

      }
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if (p_73869_2_ == this.field_146297_k.field_71474_y.field_74313_G.func_151463_i()) {
         this.field_146297_k.func_147108_a((GuiScreen)null);
         this.field_146297_k.func_71381_h();
      } else {
         super.func_73869_a(p_73869_1_, p_73869_2_);
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      if (this.field_146558_F) {
         this.func_146276_q_();
         this.func_73732_a(this.field_146289_q, I18n.func_135052_a("multiplayer.downloadingStats"), this.field_146294_l / 2, this.field_146295_m / 2, 16777215);
         this.func_73732_a(this.field_146289_q, field_146510_b_[(int)(Minecraft.func_71386_F() / 150L % (long)field_146510_b_.length)], this.field_146294_l / 2, this.field_146295_m / 2 + this.field_146289_q.field_78288_b * 2, 16777215);
      } else {
         int lvt_4_2_;
         if (Mouse.isButtonDown(0)) {
            lvt_4_2_ = (this.field_146294_l - this.field_146555_f) / 2;
            int lvt_5_1_ = (this.field_146295_m - this.field_146557_g) / 2;
            int lvt_6_1_ = lvt_4_2_ + 8;
            int lvt_7_1_ = lvt_5_1_ + 17;
            if ((this.field_146554_D == 0 || this.field_146554_D == 1) && p_73863_1_ >= lvt_6_1_ && p_73863_1_ < lvt_6_1_ + 224 && p_73863_2_ >= lvt_7_1_ && p_73863_2_ < lvt_7_1_ + 155) {
               if (this.field_146554_D == 0) {
                  this.field_146554_D = 1;
               } else {
                  this.field_146567_u -= (double)((float)(p_73863_1_ - this.field_146563_h) * this.field_146570_r);
                  this.field_146566_v -= (double)((float)(p_73863_2_ - this.field_146564_i) * this.field_146570_r);
                  this.field_146565_w = this.field_146569_s = this.field_146567_u;
                  this.field_146573_x = this.field_146568_t = this.field_146566_v;
               }

               this.field_146563_h = p_73863_1_;
               this.field_146564_i = p_73863_2_;
            }
         } else {
            this.field_146554_D = 0;
         }

         lvt_4_2_ = Mouse.getDWheel();
         float lvt_5_2_ = this.field_146570_r;
         if (lvt_4_2_ < 0) {
            this.field_146570_r += 0.25F;
         } else if (lvt_4_2_ > 0) {
            this.field_146570_r -= 0.25F;
         }

         this.field_146570_r = MathHelper.func_76131_a(this.field_146570_r, 1.0F, 2.0F);
         if (this.field_146570_r != lvt_5_2_) {
            float var10000 = lvt_5_2_ - this.field_146570_r;
            float lvt_7_2_ = lvt_5_2_ * (float)this.field_146555_f;
            float lvt_8_1_ = lvt_5_2_ * (float)this.field_146557_g;
            float lvt_9_1_ = this.field_146570_r * (float)this.field_146555_f;
            float lvt_10_1_ = this.field_146570_r * (float)this.field_146557_g;
            this.field_146567_u -= (double)((lvt_9_1_ - lvt_7_2_) * 0.5F);
            this.field_146566_v -= (double)((lvt_10_1_ - lvt_8_1_) * 0.5F);
            this.field_146565_w = this.field_146569_s = this.field_146567_u;
            this.field_146573_x = this.field_146568_t = this.field_146566_v;
         }

         if (this.field_146565_w < (double)field_146572_y) {
            this.field_146565_w = (double)field_146572_y;
         }

         if (this.field_146573_x < (double)field_146571_z) {
            this.field_146573_x = (double)field_146571_z;
         }

         if (this.field_146565_w >= (double)field_146559_A) {
            this.field_146565_w = (double)(field_146559_A - 1);
         }

         if (this.field_146573_x >= (double)field_146560_B) {
            this.field_146573_x = (double)(field_146560_B - 1);
         }

         this.func_146276_q_();
         this.func_146552_b(p_73863_1_, p_73863_2_, p_73863_3_);
         GlStateManager.func_179140_f();
         GlStateManager.func_179097_i();
         this.func_146553_h();
         GlStateManager.func_179145_e();
         GlStateManager.func_179126_j();
      }

   }

   public void func_146509_g() {
      if (this.field_146558_F) {
         this.field_146558_F = false;
      }

   }

   public void func_73876_c() {
      if (!this.field_146558_F) {
         this.field_146569_s = this.field_146567_u;
         this.field_146568_t = this.field_146566_v;
         double lvt_1_1_ = this.field_146565_w - this.field_146567_u;
         double lvt_3_1_ = this.field_146573_x - this.field_146566_v;
         if (lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ < 4.0D) {
            this.field_146567_u += lvt_1_1_;
            this.field_146566_v += lvt_3_1_;
         } else {
            this.field_146567_u += lvt_1_1_ * 0.85D;
            this.field_146566_v += lvt_3_1_ * 0.85D;
         }

      }
   }

   protected void func_146553_h() {
      int lvt_1_1_ = (this.field_146294_l - this.field_146555_f) / 2;
      int lvt_2_1_ = (this.field_146295_m - this.field_146557_g) / 2;
      this.field_146289_q.func_78276_b(I18n.func_135052_a("gui.achievements"), lvt_1_1_ + 15, lvt_2_1_ + 5, 4210752);
   }

   protected void func_146552_b(int p_146552_1_, int p_146552_2_, float p_146552_3_) {
      int lvt_4_1_ = MathHelper.func_76128_c(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * (double)p_146552_3_);
      int lvt_5_1_ = MathHelper.func_76128_c(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * (double)p_146552_3_);
      if (lvt_4_1_ < field_146572_y) {
         lvt_4_1_ = field_146572_y;
      }

      if (lvt_5_1_ < field_146571_z) {
         lvt_5_1_ = field_146571_z;
      }

      if (lvt_4_1_ >= field_146559_A) {
         lvt_4_1_ = field_146559_A - 1;
      }

      if (lvt_5_1_ >= field_146560_B) {
         lvt_5_1_ = field_146560_B - 1;
      }

      int lvt_6_1_ = (this.field_146294_l - this.field_146555_f) / 2;
      int lvt_7_1_ = (this.field_146295_m - this.field_146557_g) / 2;
      int lvt_8_1_ = lvt_6_1_ + 16;
      int lvt_9_1_ = lvt_7_1_ + 17;
      this.field_73735_i = 0.0F;
      GlStateManager.func_179143_c(518);
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)lvt_8_1_, (float)lvt_9_1_, -200.0F);
      GlStateManager.func_179152_a(1.0F / this.field_146570_r, 1.0F / this.field_146570_r, 0.0F);
      GlStateManager.func_179098_w();
      GlStateManager.func_179140_f();
      GlStateManager.func_179091_B();
      GlStateManager.func_179142_g();
      int lvt_10_1_ = lvt_4_1_ + 288 >> 4;
      int lvt_11_1_ = lvt_5_1_ + 288 >> 4;
      int lvt_12_1_ = (lvt_4_1_ + 288) % 16;
      int lvt_13_1_ = (lvt_5_1_ + 288) % 16;
      int lvt_14_1_ = true;
      int lvt_15_1_ = true;
      int lvt_16_1_ = true;
      int lvt_17_1_ = true;
      int lvt_18_1_ = true;
      Random lvt_19_1_ = new Random();
      float lvt_20_1_ = 16.0F / this.field_146570_r;
      float lvt_21_1_ = 16.0F / this.field_146570_r;

      int lvt_22_1_;
      float lvt_23_3_;
      int lvt_24_2_;
      int lvt_25_2_;
      for(lvt_22_1_ = 0; (float)lvt_22_1_ * lvt_20_1_ - (float)lvt_13_1_ < 155.0F; ++lvt_22_1_) {
         lvt_23_3_ = 0.6F - (float)(lvt_11_1_ + lvt_22_1_) / 25.0F * 0.3F;
         GlStateManager.func_179131_c(lvt_23_3_, lvt_23_3_, lvt_23_3_, 1.0F);

         for(lvt_24_2_ = 0; (float)lvt_24_2_ * lvt_21_1_ - (float)lvt_12_1_ < 224.0F; ++lvt_24_2_) {
            lvt_19_1_.setSeed((long)(this.field_146297_k.func_110432_I().func_148255_b().hashCode() + lvt_10_1_ + lvt_24_2_ + (lvt_11_1_ + lvt_22_1_) * 16));
            lvt_25_2_ = lvt_19_1_.nextInt(1 + lvt_11_1_ + lvt_22_1_) + (lvt_11_1_ + lvt_22_1_) / 2;
            TextureAtlasSprite lvt_26_1_ = this.func_175371_a(Blocks.field_150354_m);
            if (lvt_25_2_ <= 37 && lvt_11_1_ + lvt_22_1_ != 35) {
               if (lvt_25_2_ == 22) {
                  if (lvt_19_1_.nextInt(2) == 0) {
                     lvt_26_1_ = this.func_175371_a(Blocks.field_150482_ag);
                  } else {
                     lvt_26_1_ = this.func_175371_a(Blocks.field_150450_ax);
                  }
               } else if (lvt_25_2_ == 10) {
                  lvt_26_1_ = this.func_175371_a(Blocks.field_150366_p);
               } else if (lvt_25_2_ == 8) {
                  lvt_26_1_ = this.func_175371_a(Blocks.field_150365_q);
               } else if (lvt_25_2_ > 4) {
                  lvt_26_1_ = this.func_175371_a(Blocks.field_150348_b);
               } else if (lvt_25_2_ > 0) {
                  lvt_26_1_ = this.func_175371_a(Blocks.field_150346_d);
               }
            } else {
               Block lvt_27_1_ = Blocks.field_150357_h;
               lvt_26_1_ = this.func_175371_a(lvt_27_1_);
            }

            this.field_146297_k.func_110434_K().func_110577_a(TextureMap.field_110575_b);
            this.func_175175_a(lvt_24_2_ * 16 - lvt_12_1_, lvt_22_1_ * 16 - lvt_13_1_, lvt_26_1_, 16, 16);
         }
      }

      GlStateManager.func_179126_j();
      GlStateManager.func_179143_c(515);
      this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);

      int lvt_30_10_;
      int lvt_31_2_;
      int lvt_27_3_;
      for(lvt_22_1_ = 0; lvt_22_1_ < AchievementList.field_76007_e.size(); ++lvt_22_1_) {
         Achievement lvt_23_2_ = (Achievement)AchievementList.field_76007_e.get(lvt_22_1_);
         if (lvt_23_2_.field_75992_c != null) {
            lvt_24_2_ = lvt_23_2_.field_75993_a * 24 - lvt_4_1_ + 11;
            lvt_25_2_ = lvt_23_2_.field_75991_b * 24 - lvt_5_1_ + 11;
            int lvt_26_2_ = lvt_23_2_.field_75992_c.field_75993_a * 24 - lvt_4_1_ + 11;
            lvt_27_3_ = lvt_23_2_.field_75992_c.field_75991_b * 24 - lvt_5_1_ + 11;
            boolean lvt_28_1_ = this.field_146556_E.func_77443_a(lvt_23_2_);
            boolean lvt_29_1_ = this.field_146556_E.func_77442_b(lvt_23_2_);
            lvt_30_10_ = this.field_146556_E.func_150874_c(lvt_23_2_);
            if (lvt_30_10_ <= 4) {
               lvt_31_2_ = -16777216;
               if (lvt_28_1_) {
                  lvt_31_2_ = -6250336;
               } else if (lvt_29_1_) {
                  lvt_31_2_ = -16711936;
               }

               this.func_73730_a(lvt_24_2_, lvt_26_2_, lvt_25_2_, lvt_31_2_);
               this.func_73728_b(lvt_26_2_, lvt_25_2_, lvt_27_3_, lvt_31_2_);
               if (lvt_24_2_ > lvt_26_2_) {
                  this.func_73729_b(lvt_24_2_ - 11 - 7, lvt_25_2_ - 5, 114, 234, 7, 11);
               } else if (lvt_24_2_ < lvt_26_2_) {
                  this.func_73729_b(lvt_24_2_ + 11, lvt_25_2_ - 5, 107, 234, 7, 11);
               } else if (lvt_25_2_ > lvt_27_3_) {
                  this.func_73729_b(lvt_24_2_ - 5, lvt_25_2_ - 11 - 7, 96, 234, 11, 7);
               } else if (lvt_25_2_ < lvt_27_3_) {
                  this.func_73729_b(lvt_24_2_ - 5, lvt_25_2_ + 11, 96, 241, 11, 7);
               }
            }
         }
      }

      Achievement lvt_22_3_ = null;
      lvt_23_3_ = (float)(p_146552_1_ - lvt_8_1_) * this.field_146570_r;
      float lvt_24_3_ = (float)(p_146552_2_ - lvt_9_1_) * this.field_146570_r;
      RenderHelper.func_74520_c();
      GlStateManager.func_179140_f();
      GlStateManager.func_179091_B();
      GlStateManager.func_179142_g();

      int lvt_28_2_;
      int lvt_29_2_;
      for(lvt_25_2_ = 0; lvt_25_2_ < AchievementList.field_76007_e.size(); ++lvt_25_2_) {
         Achievement lvt_26_3_ = (Achievement)AchievementList.field_76007_e.get(lvt_25_2_);
         lvt_27_3_ = lvt_26_3_.field_75993_a * 24 - lvt_4_1_;
         lvt_28_2_ = lvt_26_3_.field_75991_b * 24 - lvt_5_1_;
         if (lvt_27_3_ >= -24 && lvt_28_2_ >= -24 && (float)lvt_27_3_ <= 224.0F * this.field_146570_r && (float)lvt_28_2_ <= 155.0F * this.field_146570_r) {
            lvt_29_2_ = this.field_146556_E.func_150874_c(lvt_26_3_);
            float lvt_30_6_;
            if (this.field_146556_E.func_77443_a(lvt_26_3_)) {
               lvt_30_6_ = 0.75F;
               GlStateManager.func_179131_c(lvt_30_6_, lvt_30_6_, lvt_30_6_, 1.0F);
            } else if (this.field_146556_E.func_77442_b(lvt_26_3_)) {
               lvt_30_6_ = 1.0F;
               GlStateManager.func_179131_c(lvt_30_6_, lvt_30_6_, lvt_30_6_, 1.0F);
            } else if (lvt_29_2_ < 3) {
               lvt_30_6_ = 0.3F;
               GlStateManager.func_179131_c(lvt_30_6_, lvt_30_6_, lvt_30_6_, 1.0F);
            } else if (lvt_29_2_ == 3) {
               lvt_30_6_ = 0.2F;
               GlStateManager.func_179131_c(lvt_30_6_, lvt_30_6_, lvt_30_6_, 1.0F);
            } else {
               if (lvt_29_2_ != 4) {
                  continue;
               }

               lvt_30_6_ = 0.1F;
               GlStateManager.func_179131_c(lvt_30_6_, lvt_30_6_, lvt_30_6_, 1.0F);
            }

            this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);
            if (lvt_26_3_.func_75984_f()) {
               this.func_73729_b(lvt_27_3_ - 2, lvt_28_2_ - 2, 26, 202, 26, 26);
            } else {
               this.func_73729_b(lvt_27_3_ - 2, lvt_28_2_ - 2, 0, 202, 26, 26);
            }

            if (!this.field_146556_E.func_77442_b(lvt_26_3_)) {
               lvt_30_6_ = 0.1F;
               GlStateManager.func_179131_c(lvt_30_6_, lvt_30_6_, lvt_30_6_, 1.0F);
               this.field_146296_j.func_175039_a(false);
            }

            GlStateManager.func_179145_e();
            GlStateManager.func_179089_o();
            this.field_146296_j.func_180450_b(lvt_26_3_.field_75990_d, lvt_27_3_ + 3, lvt_28_2_ + 3);
            GlStateManager.func_179112_b(770, 771);
            GlStateManager.func_179140_f();
            if (!this.field_146556_E.func_77442_b(lvt_26_3_)) {
               this.field_146296_j.func_175039_a(true);
            }

            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            if (lvt_23_3_ >= (float)lvt_27_3_ && lvt_23_3_ <= (float)(lvt_27_3_ + 22) && lvt_24_3_ >= (float)lvt_28_2_ && lvt_24_3_ <= (float)(lvt_28_2_ + 22)) {
               lvt_22_3_ = lvt_26_3_;
            }
         }
      }

      GlStateManager.func_179097_i();
      GlStateManager.func_179147_l();
      GlStateManager.func_179121_F();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);
      this.func_73729_b(lvt_6_1_, lvt_7_1_, 0, 0, this.field_146555_f, this.field_146557_g);
      this.field_73735_i = 0.0F;
      GlStateManager.func_179143_c(515);
      GlStateManager.func_179097_i();
      GlStateManager.func_179098_w();
      super.func_73863_a(p_146552_1_, p_146552_2_, p_146552_3_);
      if (lvt_22_3_ != null) {
         String lvt_25_4_ = lvt_22_3_.func_150951_e().func_150260_c();
         String lvt_26_4_ = lvt_22_3_.func_75989_e();
         lvt_27_3_ = p_146552_1_ + 12;
         lvt_28_2_ = p_146552_2_ - 4;
         lvt_29_2_ = this.field_146556_E.func_150874_c(lvt_22_3_);
         if (this.field_146556_E.func_77442_b(lvt_22_3_)) {
            lvt_30_10_ = Math.max(this.field_146289_q.func_78256_a(lvt_25_4_), 120);
            lvt_31_2_ = this.field_146289_q.func_78267_b(lvt_26_4_, lvt_30_10_);
            if (this.field_146556_E.func_77443_a(lvt_22_3_)) {
               lvt_31_2_ += 12;
            }

            this.func_73733_a(lvt_27_3_ - 3, lvt_28_2_ - 3, lvt_27_3_ + lvt_30_10_ + 3, lvt_28_2_ + lvt_31_2_ + 3 + 12, -1073741824, -1073741824);
            this.field_146289_q.func_78279_b(lvt_26_4_, lvt_27_3_, lvt_28_2_ + 12, lvt_30_10_, -6250336);
            if (this.field_146556_E.func_77443_a(lvt_22_3_)) {
               this.field_146289_q.func_175063_a(I18n.func_135052_a("achievement.taken"), (float)lvt_27_3_, (float)(lvt_28_2_ + lvt_31_2_ + 4), -7302913);
            }
         } else {
            int lvt_32_2_;
            String lvt_31_4_;
            if (lvt_29_2_ == 3) {
               lvt_25_4_ = I18n.func_135052_a("achievement.unknown");
               lvt_30_10_ = Math.max(this.field_146289_q.func_78256_a(lvt_25_4_), 120);
               lvt_31_4_ = (new ChatComponentTranslation("achievement.requires", new Object[]{lvt_22_3_.field_75992_c.func_150951_e()})).func_150260_c();
               lvt_32_2_ = this.field_146289_q.func_78267_b(lvt_31_4_, lvt_30_10_);
               this.func_73733_a(lvt_27_3_ - 3, lvt_28_2_ - 3, lvt_27_3_ + lvt_30_10_ + 3, lvt_28_2_ + lvt_32_2_ + 12 + 3, -1073741824, -1073741824);
               this.field_146289_q.func_78279_b(lvt_31_4_, lvt_27_3_, lvt_28_2_ + 12, lvt_30_10_, -9416624);
            } else if (lvt_29_2_ < 3) {
               lvt_30_10_ = Math.max(this.field_146289_q.func_78256_a(lvt_25_4_), 120);
               lvt_31_4_ = (new ChatComponentTranslation("achievement.requires", new Object[]{lvt_22_3_.field_75992_c.func_150951_e()})).func_150260_c();
               lvt_32_2_ = this.field_146289_q.func_78267_b(lvt_31_4_, lvt_30_10_);
               this.func_73733_a(lvt_27_3_ - 3, lvt_28_2_ - 3, lvt_27_3_ + lvt_30_10_ + 3, lvt_28_2_ + lvt_32_2_ + 12 + 3, -1073741824, -1073741824);
               this.field_146289_q.func_78279_b(lvt_31_4_, lvt_27_3_, lvt_28_2_ + 12, lvt_30_10_, -9416624);
            } else {
               lvt_25_4_ = null;
            }
         }

         if (lvt_25_4_ != null) {
            this.field_146289_q.func_175063_a(lvt_25_4_, (float)lvt_27_3_, (float)lvt_28_2_, this.field_146556_E.func_77442_b(lvt_22_3_) ? (lvt_22_3_.func_75984_f() ? -128 : -1) : (lvt_22_3_.func_75984_f() ? -8355776 : -8355712));
         }
      }

      GlStateManager.func_179126_j();
      GlStateManager.func_179145_e();
      RenderHelper.func_74518_a();
   }

   private TextureAtlasSprite func_175371_a(Block p_175371_1_) {
      return Minecraft.func_71410_x().func_175602_ab().func_175023_a().func_178122_a(p_175371_1_.func_176223_P());
   }

   public boolean func_73868_f() {
      return !this.field_146558_F;
   }

   static {
      field_146572_y = AchievementList.field_76010_a * 24 - 112;
      field_146571_z = AchievementList.field_76008_b * 24 - 112;
      field_146559_A = AchievementList.field_76009_c * 24 - 77;
      field_146560_B = AchievementList.field_76006_d * 24 - 77;
      field_146561_C = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   }
}
