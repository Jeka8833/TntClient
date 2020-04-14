package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiNewChat extends Gui {
   private static final Logger field_146249_a = LogManager.getLogger();
   private final Minecraft field_146247_f;
   private final List<String> field_146248_g = Lists.newArrayList();
   private final List<ChatLine> field_146252_h = Lists.newArrayList();
   private final List<ChatLine> field_146253_i = Lists.newArrayList();
   private int field_146250_j;
   private boolean field_146251_k;

   public GuiNewChat(Minecraft p_i1022_1_) {
      this.field_146247_f = p_i1022_1_;
   }

   public void func_146230_a(int p_146230_1_) {
      if (this.field_146247_f.field_71474_y.field_74343_n != EntityPlayer.EnumChatVisibility.HIDDEN) {
         int lvt_2_1_ = this.func_146232_i();
         boolean lvt_3_1_ = false;
         int lvt_4_1_ = 0;
         int lvt_5_1_ = this.field_146253_i.size();
         float lvt_6_1_ = this.field_146247_f.field_71474_y.field_74357_r * 0.9F + 0.1F;
         if (lvt_5_1_ > 0) {
            if (this.func_146241_e()) {
               lvt_3_1_ = true;
            }

            float lvt_7_1_ = this.func_146244_h();
            int lvt_8_1_ = MathHelper.func_76123_f((float)this.func_146228_f() / lvt_7_1_);
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(2.0F, 20.0F, 0.0F);
            GlStateManager.func_179152_a(lvt_7_1_, lvt_7_1_, 1.0F);

            int lvt_9_1_;
            int lvt_11_1_;
            int lvt_14_1_;
            for(lvt_9_1_ = 0; lvt_9_1_ + this.field_146250_j < this.field_146253_i.size() && lvt_9_1_ < lvt_2_1_; ++lvt_9_1_) {
               ChatLine lvt_10_1_ = (ChatLine)this.field_146253_i.get(lvt_9_1_ + this.field_146250_j);
               if (lvt_10_1_ != null) {
                  lvt_11_1_ = p_146230_1_ - lvt_10_1_.func_74540_b();
                  if (lvt_11_1_ < 200 || lvt_3_1_) {
                     double lvt_12_1_ = (double)lvt_11_1_ / 200.0D;
                     lvt_12_1_ = 1.0D - lvt_12_1_;
                     lvt_12_1_ *= 10.0D;
                     lvt_12_1_ = MathHelper.func_151237_a(lvt_12_1_, 0.0D, 1.0D);
                     lvt_12_1_ *= lvt_12_1_;
                     lvt_14_1_ = (int)(255.0D * lvt_12_1_);
                     if (lvt_3_1_) {
                        lvt_14_1_ = 255;
                     }

                     lvt_14_1_ = (int)((float)lvt_14_1_ * lvt_6_1_);
                     ++lvt_4_1_;
                     if (lvt_14_1_ > 3) {
                        int lvt_15_1_ = 0;
                        int lvt_16_1_ = -lvt_9_1_ * 9;
                        func_73734_a(lvt_15_1_, lvt_16_1_ - 9, lvt_15_1_ + lvt_8_1_ + 4, lvt_16_1_, lvt_14_1_ / 2 << 24);
                        String lvt_17_1_ = lvt_10_1_.func_151461_a().func_150254_d();
                        GlStateManager.func_179147_l();
                        this.field_146247_f.field_71466_p.func_175063_a(lvt_17_1_, (float)lvt_15_1_, (float)(lvt_16_1_ - 8), 16777215 + (lvt_14_1_ << 24));
                        GlStateManager.func_179118_c();
                        GlStateManager.func_179084_k();
                     }
                  }
               }
            }

            if (lvt_3_1_) {
               lvt_9_1_ = this.field_146247_f.field_71466_p.field_78288_b;
               GlStateManager.func_179109_b(-3.0F, 0.0F, 0.0F);
               int lvt_10_2_ = lvt_5_1_ * lvt_9_1_ + lvt_5_1_;
               lvt_11_1_ = lvt_4_1_ * lvt_9_1_ + lvt_4_1_;
               int lvt_12_2_ = this.field_146250_j * lvt_11_1_ / lvt_5_1_;
               int lvt_13_1_ = lvt_11_1_ * lvt_11_1_ / lvt_10_2_;
               if (lvt_10_2_ != lvt_11_1_) {
                  lvt_14_1_ = lvt_12_2_ > 0 ? 170 : 96;
                  int lvt_15_2_ = this.field_146251_k ? 13382451 : 3355562;
                  func_73734_a(0, -lvt_12_2_, 2, -lvt_12_2_ - lvt_13_1_, lvt_15_2_ + (lvt_14_1_ << 24));
                  func_73734_a(2, -lvt_12_2_, 1, -lvt_12_2_ - lvt_13_1_, 13421772 + (lvt_14_1_ << 24));
               }
            }

            GlStateManager.func_179121_F();
         }
      }
   }

   public void func_146231_a() {
      this.field_146253_i.clear();
      this.field_146252_h.clear();
      this.field_146248_g.clear();
   }

   public void func_146227_a(IChatComponent p_146227_1_) {
      this.func_146234_a(p_146227_1_, 0);
   }

   public void func_146234_a(IChatComponent p_146234_1_, int p_146234_2_) {
      this.func_146237_a(p_146234_1_, p_146234_2_, this.field_146247_f.field_71456_v.func_73834_c(), false);
      field_146249_a.info("[CHAT] " + p_146234_1_.func_150260_c());
   }

   private void func_146237_a(IChatComponent p_146237_1_, int p_146237_2_, int p_146237_3_, boolean p_146237_4_) {
      if (p_146237_2_ != 0) {
         this.func_146242_c(p_146237_2_);
      }

      int lvt_5_1_ = MathHelper.func_76141_d((float)this.func_146228_f() / this.func_146244_h());
      List<IChatComponent> lvt_6_1_ = GuiUtilRenderComponents.func_178908_a(p_146237_1_, lvt_5_1_, this.field_146247_f.field_71466_p, false, false);
      boolean lvt_7_1_ = this.func_146241_e();

      IChatComponent lvt_9_1_;
      for(Iterator lvt_8_1_ = lvt_6_1_.iterator(); lvt_8_1_.hasNext(); this.field_146253_i.add(0, new ChatLine(p_146237_3_, lvt_9_1_, p_146237_2_))) {
         lvt_9_1_ = (IChatComponent)lvt_8_1_.next();
         if (lvt_7_1_ && this.field_146250_j > 0) {
            this.field_146251_k = true;
            this.func_146229_b(1);
         }
      }

      while(this.field_146253_i.size() > 100) {
         this.field_146253_i.remove(this.field_146253_i.size() - 1);
      }

      if (!p_146237_4_) {
         this.field_146252_h.add(0, new ChatLine(p_146237_3_, p_146237_1_, p_146237_2_));

         while(this.field_146252_h.size() > 100) {
            this.field_146252_h.remove(this.field_146252_h.size() - 1);
         }
      }

   }

   public void func_146245_b() {
      this.field_146253_i.clear();
      this.func_146240_d();

      for(int lvt_1_1_ = this.field_146252_h.size() - 1; lvt_1_1_ >= 0; --lvt_1_1_) {
         ChatLine lvt_2_1_ = (ChatLine)this.field_146252_h.get(lvt_1_1_);
         this.func_146237_a(lvt_2_1_.func_151461_a(), lvt_2_1_.func_74539_c(), lvt_2_1_.func_74540_b(), true);
      }

   }

   public List<String> func_146238_c() {
      return this.field_146248_g;
   }

   public void func_146239_a(String p_146239_1_) {
      if (this.field_146248_g.isEmpty() || !((String)this.field_146248_g.get(this.field_146248_g.size() - 1)).equals(p_146239_1_)) {
         this.field_146248_g.add(p_146239_1_);
      }

   }

   public void func_146240_d() {
      this.field_146250_j = 0;
      this.field_146251_k = false;
   }

   public void func_146229_b(int p_146229_1_) {
      this.field_146250_j += p_146229_1_;
      int lvt_2_1_ = this.field_146253_i.size();
      if (this.field_146250_j > lvt_2_1_ - this.func_146232_i()) {
         this.field_146250_j = lvt_2_1_ - this.func_146232_i();
      }

      if (this.field_146250_j <= 0) {
         this.field_146250_j = 0;
         this.field_146251_k = false;
      }

   }

   public IChatComponent func_146236_a(int p_146236_1_, int p_146236_2_) {
      if (!this.func_146241_e()) {
         return null;
      } else {
         ScaledResolution lvt_3_1_ = new ScaledResolution(this.field_146247_f);
         int lvt_4_1_ = lvt_3_1_.func_78325_e();
         float lvt_5_1_ = this.func_146244_h();
         int lvt_6_1_ = p_146236_1_ / lvt_4_1_ - 3;
         int lvt_7_1_ = p_146236_2_ / lvt_4_1_ - 27;
         lvt_6_1_ = MathHelper.func_76141_d((float)lvt_6_1_ / lvt_5_1_);
         lvt_7_1_ = MathHelper.func_76141_d((float)lvt_7_1_ / lvt_5_1_);
         if (lvt_6_1_ >= 0 && lvt_7_1_ >= 0) {
            int lvt_8_1_ = Math.min(this.func_146232_i(), this.field_146253_i.size());
            if (lvt_6_1_ <= MathHelper.func_76141_d((float)this.func_146228_f() / this.func_146244_h()) && lvt_7_1_ < this.field_146247_f.field_71466_p.field_78288_b * lvt_8_1_ + lvt_8_1_) {
               int lvt_9_1_ = lvt_7_1_ / this.field_146247_f.field_71466_p.field_78288_b + this.field_146250_j;
               if (lvt_9_1_ >= 0 && lvt_9_1_ < this.field_146253_i.size()) {
                  ChatLine lvt_10_1_ = (ChatLine)this.field_146253_i.get(lvt_9_1_);
                  int lvt_11_1_ = 0;
                  Iterator lvt_12_1_ = lvt_10_1_.func_151461_a().iterator();

                  while(lvt_12_1_.hasNext()) {
                     IChatComponent lvt_13_1_ = (IChatComponent)lvt_12_1_.next();
                     if (lvt_13_1_ instanceof ChatComponentText) {
                        lvt_11_1_ += this.field_146247_f.field_71466_p.func_78256_a(GuiUtilRenderComponents.func_178909_a(((ChatComponentText)lvt_13_1_).func_150265_g(), false));
                        if (lvt_11_1_ > lvt_6_1_) {
                           return lvt_13_1_;
                        }
                     }
                  }
               }

               return null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public boolean func_146241_e() {
      return this.field_146247_f.field_71462_r instanceof GuiChat;
   }

   public void func_146242_c(int p_146242_1_) {
      Iterator lvt_2_1_ = this.field_146253_i.iterator();

      ChatLine lvt_3_2_;
      while(lvt_2_1_.hasNext()) {
         lvt_3_2_ = (ChatLine)lvt_2_1_.next();
         if (lvt_3_2_.func_74539_c() == p_146242_1_) {
            lvt_2_1_.remove();
         }
      }

      lvt_2_1_ = this.field_146252_h.iterator();

      while(lvt_2_1_.hasNext()) {
         lvt_3_2_ = (ChatLine)lvt_2_1_.next();
         if (lvt_3_2_.func_74539_c() == p_146242_1_) {
            lvt_2_1_.remove();
            break;
         }
      }

   }

   public int func_146228_f() {
      return func_146233_a(this.field_146247_f.field_71474_y.field_96692_F);
   }

   public int func_146246_g() {
      return func_146243_b(this.func_146241_e() ? this.field_146247_f.field_71474_y.field_96694_H : this.field_146247_f.field_71474_y.field_96693_G);
   }

   public float func_146244_h() {
      return this.field_146247_f.field_71474_y.field_96691_E;
   }

   public static int func_146233_a(float p_146233_0_) {
      int lvt_1_1_ = 320;
      int lvt_2_1_ = 40;
      return MathHelper.func_76141_d(p_146233_0_ * (float)(lvt_1_1_ - lvt_2_1_) + (float)lvt_2_1_);
   }

   public static int func_146243_b(float p_146243_0_) {
      int lvt_1_1_ = 180;
      int lvt_2_1_ = 20;
      return MathHelper.func_76141_d(p_146243_0_ * (float)(lvt_1_1_ - lvt_2_1_) + (float)lvt_2_1_);
   }

   public int func_146232_i() {
      return this.func_146246_g() / 9;
   }
}
