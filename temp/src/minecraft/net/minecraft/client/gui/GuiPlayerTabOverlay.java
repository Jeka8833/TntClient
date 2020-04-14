package net.minecraft.client.gui;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings;

public class GuiPlayerTabOverlay extends Gui {
   private static final Ordering<NetworkPlayerInfo> field_175252_a = Ordering.from((Comparator)(new GuiPlayerTabOverlay.PlayerComparator()));
   private final Minecraft field_175250_f;
   private final GuiIngame field_175251_g;
   private IChatComponent field_175255_h;
   private IChatComponent field_175256_i;
   private long field_175253_j;
   private boolean field_175254_k;

   public GuiPlayerTabOverlay(Minecraft p_i45529_1_, GuiIngame p_i45529_2_) {
      this.field_175250_f = p_i45529_1_;
      this.field_175251_g = p_i45529_2_;
   }

   public String func_175243_a(NetworkPlayerInfo p_175243_1_) {
      return p_175243_1_.func_178854_k() != null ? p_175243_1_.func_178854_k().func_150254_d() : ScorePlayerTeam.func_96667_a(p_175243_1_.func_178850_i(), p_175243_1_.func_178845_a().getName());
   }

   public void func_175246_a(boolean p_175246_1_) {
      if (p_175246_1_ && !this.field_175254_k) {
         this.field_175253_j = Minecraft.func_71386_F();
      }

      this.field_175254_k = p_175246_1_;
   }

   public void func_175249_a(int p_175249_1_, Scoreboard p_175249_2_, ScoreObjective p_175249_3_) {
      NetHandlerPlayClient lvt_4_1_ = this.field_175250_f.field_71439_g.field_71174_a;
      List<NetworkPlayerInfo> lvt_5_1_ = field_175252_a.sortedCopy(lvt_4_1_.func_175106_d());
      int lvt_6_1_ = 0;
      int lvt_7_1_ = 0;
      Iterator lvt_8_1_ = lvt_5_1_.iterator();

      int lvt_10_2_;
      while(lvt_8_1_.hasNext()) {
         NetworkPlayerInfo lvt_9_1_ = (NetworkPlayerInfo)lvt_8_1_.next();
         lvt_10_2_ = this.field_175250_f.field_71466_p.func_78256_a(this.func_175243_a(lvt_9_1_));
         lvt_6_1_ = Math.max(lvt_6_1_, lvt_10_2_);
         if (p_175249_3_ != null && p_175249_3_.func_178766_e() != IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
            lvt_10_2_ = this.field_175250_f.field_71466_p.func_78256_a(" " + p_175249_2_.func_96529_a(lvt_9_1_.func_178845_a().getName(), p_175249_3_).func_96652_c());
            lvt_7_1_ = Math.max(lvt_7_1_, lvt_10_2_);
         }
      }

      lvt_5_1_ = lvt_5_1_.subList(0, Math.min(lvt_5_1_.size(), 80));
      int lvt_8_2_ = lvt_5_1_.size();
      int lvt_9_2_ = lvt_8_2_;

      for(lvt_10_2_ = 1; lvt_9_2_ > 20; lvt_9_2_ = (lvt_8_2_ + lvt_10_2_ - 1) / lvt_10_2_) {
         ++lvt_10_2_;
      }

      boolean lvt_11_1_ = this.field_175250_f.func_71387_A() || this.field_175250_f.func_147114_u().func_147298_b().func_179292_f();
      int lvt_12_3_;
      if (p_175249_3_ != null) {
         if (p_175249_3_.func_178766_e() == IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
            lvt_12_3_ = 90;
         } else {
            lvt_12_3_ = lvt_7_1_;
         }
      } else {
         lvt_12_3_ = 0;
      }

      int lvt_13_1_ = Math.min(lvt_10_2_ * ((lvt_11_1_ ? 9 : 0) + lvt_6_1_ + lvt_12_3_ + 13), p_175249_1_ - 50) / lvt_10_2_;
      int lvt_14_1_ = p_175249_1_ / 2 - (lvt_13_1_ * lvt_10_2_ + (lvt_10_2_ - 1) * 5) / 2;
      int lvt_15_1_ = 10;
      int lvt_16_1_ = lvt_13_1_ * lvt_10_2_ + (lvt_10_2_ - 1) * 5;
      List<String> lvt_17_1_ = null;
      List<String> lvt_18_1_ = null;
      Iterator lvt_19_5_;
      String lvt_20_5_;
      if (this.field_175256_i != null) {
         lvt_17_1_ = this.field_175250_f.field_71466_p.func_78271_c(this.field_175256_i.func_150254_d(), p_175249_1_ - 50);

         for(lvt_19_5_ = lvt_17_1_.iterator(); lvt_19_5_.hasNext(); lvt_16_1_ = Math.max(lvt_16_1_, this.field_175250_f.field_71466_p.func_78256_a(lvt_20_5_))) {
            lvt_20_5_ = (String)lvt_19_5_.next();
         }
      }

      if (this.field_175255_h != null) {
         lvt_18_1_ = this.field_175250_f.field_71466_p.func_78271_c(this.field_175255_h.func_150254_d(), p_175249_1_ - 50);

         for(lvt_19_5_ = lvt_18_1_.iterator(); lvt_19_5_.hasNext(); lvt_16_1_ = Math.max(lvt_16_1_, this.field_175250_f.field_71466_p.func_78256_a(lvt_20_5_))) {
            lvt_20_5_ = (String)lvt_19_5_.next();
         }
      }

      int lvt_21_3_;
      if (lvt_17_1_ != null) {
         func_73734_a(p_175249_1_ / 2 - lvt_16_1_ / 2 - 1, lvt_15_1_ - 1, p_175249_1_ / 2 + lvt_16_1_ / 2 + 1, lvt_15_1_ + lvt_17_1_.size() * this.field_175250_f.field_71466_p.field_78288_b, Integer.MIN_VALUE);

         for(lvt_19_5_ = lvt_17_1_.iterator(); lvt_19_5_.hasNext(); lvt_15_1_ += this.field_175250_f.field_71466_p.field_78288_b) {
            lvt_20_5_ = (String)lvt_19_5_.next();
            lvt_21_3_ = this.field_175250_f.field_71466_p.func_78256_a(lvt_20_5_);
            this.field_175250_f.field_71466_p.func_175063_a(lvt_20_5_, (float)(p_175249_1_ / 2 - lvt_21_3_ / 2), (float)lvt_15_1_, -1);
         }

         ++lvt_15_1_;
      }

      func_73734_a(p_175249_1_ / 2 - lvt_16_1_ / 2 - 1, lvt_15_1_ - 1, p_175249_1_ / 2 + lvt_16_1_ / 2 + 1, lvt_15_1_ + lvt_9_2_ * 9, Integer.MIN_VALUE);

      for(int lvt_19_4_ = 0; lvt_19_4_ < lvt_8_2_; ++lvt_19_4_) {
         int lvt_20_4_ = lvt_19_4_ / lvt_9_2_;
         lvt_21_3_ = lvt_19_4_ % lvt_9_2_;
         int lvt_22_1_ = lvt_14_1_ + lvt_20_4_ * lvt_13_1_ + lvt_20_4_ * 5;
         int lvt_23_1_ = lvt_15_1_ + lvt_21_3_ * 9;
         func_73734_a(lvt_22_1_, lvt_23_1_, lvt_22_1_ + lvt_13_1_, lvt_23_1_ + 8, 553648127);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179141_d();
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         if (lvt_19_4_ < lvt_5_1_.size()) {
            NetworkPlayerInfo lvt_24_1_ = (NetworkPlayerInfo)lvt_5_1_.get(lvt_19_4_);
            String lvt_25_1_ = this.func_175243_a(lvt_24_1_);
            GameProfile lvt_26_1_ = lvt_24_1_.func_178845_a();
            if (lvt_11_1_) {
               EntityPlayer lvt_27_1_ = this.field_175250_f.field_71441_e.func_152378_a(lvt_26_1_.getId());
               boolean lvt_28_1_ = lvt_27_1_ != null && lvt_27_1_.func_175148_a(EnumPlayerModelParts.CAPE) && (lvt_26_1_.getName().equals("Dinnerbone") || lvt_26_1_.getName().equals("Grumm"));
               this.field_175250_f.func_110434_K().func_110577_a(lvt_24_1_.func_178837_g());
               int lvt_29_1_ = 8 + (lvt_28_1_ ? 8 : 0);
               int lvt_30_1_ = 8 * (lvt_28_1_ ? -1 : 1);
               Gui.func_152125_a(lvt_22_1_, lvt_23_1_, 8.0F, (float)lvt_29_1_, 8, lvt_30_1_, 8, 8, 64.0F, 64.0F);
               if (lvt_27_1_ != null && lvt_27_1_.func_175148_a(EnumPlayerModelParts.HAT)) {
                  int lvt_31_1_ = 8 + (lvt_28_1_ ? 8 : 0);
                  int lvt_32_1_ = 8 * (lvt_28_1_ ? -1 : 1);
                  Gui.func_152125_a(lvt_22_1_, lvt_23_1_, 40.0F, (float)lvt_31_1_, 8, lvt_32_1_, 8, 8, 64.0F, 64.0F);
               }

               lvt_22_1_ += 9;
            }

            if (lvt_24_1_.func_178848_b() == WorldSettings.GameType.SPECTATOR) {
               lvt_25_1_ = EnumChatFormatting.ITALIC + lvt_25_1_;
               this.field_175250_f.field_71466_p.func_175063_a(lvt_25_1_, (float)lvt_22_1_, (float)lvt_23_1_, -1862270977);
            } else {
               this.field_175250_f.field_71466_p.func_175063_a(lvt_25_1_, (float)lvt_22_1_, (float)lvt_23_1_, -1);
            }

            if (p_175249_3_ != null && lvt_24_1_.func_178848_b() != WorldSettings.GameType.SPECTATOR) {
               int lvt_27_2_ = lvt_22_1_ + lvt_6_1_ + 1;
               int lvt_28_2_ = lvt_27_2_ + lvt_12_3_;
               if (lvt_28_2_ - lvt_27_2_ > 5) {
                  this.func_175247_a(p_175249_3_, lvt_23_1_, lvt_26_1_.getName(), lvt_27_2_, lvt_28_2_, lvt_24_1_);
               }
            }

            this.func_175245_a(lvt_13_1_, lvt_22_1_ - (lvt_11_1_ ? 9 : 0), lvt_23_1_, lvt_24_1_);
         }
      }

      if (lvt_18_1_ != null) {
         lvt_15_1_ += lvt_9_2_ * 9 + 1;
         func_73734_a(p_175249_1_ / 2 - lvt_16_1_ / 2 - 1, lvt_15_1_ - 1, p_175249_1_ / 2 + lvt_16_1_ / 2 + 1, lvt_15_1_ + lvt_18_1_.size() * this.field_175250_f.field_71466_p.field_78288_b, Integer.MIN_VALUE);

         for(lvt_19_5_ = lvt_18_1_.iterator(); lvt_19_5_.hasNext(); lvt_15_1_ += this.field_175250_f.field_71466_p.field_78288_b) {
            lvt_20_5_ = (String)lvt_19_5_.next();
            lvt_21_3_ = this.field_175250_f.field_71466_p.func_78256_a(lvt_20_5_);
            this.field_175250_f.field_71466_p.func_175063_a(lvt_20_5_, (float)(p_175249_1_ / 2 - lvt_21_3_ / 2), (float)lvt_15_1_, -1);
         }
      }

   }

   protected void func_175245_a(int p_175245_1_, int p_175245_2_, int p_175245_3_, NetworkPlayerInfo p_175245_4_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_175250_f.func_110434_K().func_110577_a(field_110324_m);
      int lvt_5_1_ = 0;
      int lvt_6_1_ = false;
      byte lvt_6_1_;
      if (p_175245_4_.func_178853_c() < 0) {
         lvt_6_1_ = 5;
      } else if (p_175245_4_.func_178853_c() < 150) {
         lvt_6_1_ = 0;
      } else if (p_175245_4_.func_178853_c() < 300) {
         lvt_6_1_ = 1;
      } else if (p_175245_4_.func_178853_c() < 600) {
         lvt_6_1_ = 2;
      } else if (p_175245_4_.func_178853_c() < 1000) {
         lvt_6_1_ = 3;
      } else {
         lvt_6_1_ = 4;
      }

      this.field_73735_i += 100.0F;
      this.func_73729_b(p_175245_2_ + p_175245_1_ - 11, p_175245_3_, 0 + lvt_5_1_ * 10, 176 + lvt_6_1_ * 8, 10, 8);
      this.field_73735_i -= 100.0F;
   }

   private void func_175247_a(ScoreObjective p_175247_1_, int p_175247_2_, String p_175247_3_, int p_175247_4_, int p_175247_5_, NetworkPlayerInfo p_175247_6_) {
      int lvt_7_1_ = p_175247_1_.func_96682_a().func_96529_a(p_175247_3_, p_175247_1_).func_96652_c();
      if (p_175247_1_.func_178766_e() == IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
         this.field_175250_f.func_110434_K().func_110577_a(field_110324_m);
         if (this.field_175253_j == p_175247_6_.func_178855_p()) {
            if (lvt_7_1_ < p_175247_6_.func_178835_l()) {
               p_175247_6_.func_178846_a(Minecraft.func_71386_F());
               p_175247_6_.func_178844_b((long)(this.field_175251_g.func_73834_c() + 20));
            } else if (lvt_7_1_ > p_175247_6_.func_178835_l()) {
               p_175247_6_.func_178846_a(Minecraft.func_71386_F());
               p_175247_6_.func_178844_b((long)(this.field_175251_g.func_73834_c() + 10));
            }
         }

         if (Minecraft.func_71386_F() - p_175247_6_.func_178847_n() > 1000L || this.field_175253_j != p_175247_6_.func_178855_p()) {
            p_175247_6_.func_178836_b(lvt_7_1_);
            p_175247_6_.func_178857_c(lvt_7_1_);
            p_175247_6_.func_178846_a(Minecraft.func_71386_F());
         }

         p_175247_6_.func_178843_c(this.field_175253_j);
         p_175247_6_.func_178836_b(lvt_7_1_);
         int lvt_8_1_ = MathHelper.func_76123_f((float)Math.max(lvt_7_1_, p_175247_6_.func_178860_m()) / 2.0F);
         int lvt_9_1_ = Math.max(MathHelper.func_76123_f((float)(lvt_7_1_ / 2)), Math.max(MathHelper.func_76123_f((float)(p_175247_6_.func_178860_m() / 2)), 10));
         boolean lvt_10_1_ = p_175247_6_.func_178858_o() > (long)this.field_175251_g.func_73834_c() && (p_175247_6_.func_178858_o() - (long)this.field_175251_g.func_73834_c()) / 3L % 2L == 1L;
         if (lvt_8_1_ > 0) {
            float lvt_11_1_ = Math.min((float)(p_175247_5_ - p_175247_4_ - 4) / (float)lvt_9_1_, 9.0F);
            if (lvt_11_1_ > 3.0F) {
               int lvt_12_2_;
               for(lvt_12_2_ = lvt_8_1_; lvt_12_2_ < lvt_9_1_; ++lvt_12_2_) {
                  this.func_175174_a((float)p_175247_4_ + (float)lvt_12_2_ * lvt_11_1_, (float)p_175247_2_, lvt_10_1_ ? 25 : 16, 0, 9, 9);
               }

               for(lvt_12_2_ = 0; lvt_12_2_ < lvt_8_1_; ++lvt_12_2_) {
                  this.func_175174_a((float)p_175247_4_ + (float)lvt_12_2_ * lvt_11_1_, (float)p_175247_2_, lvt_10_1_ ? 25 : 16, 0, 9, 9);
                  if (lvt_10_1_) {
                     if (lvt_12_2_ * 2 + 1 < p_175247_6_.func_178860_m()) {
                        this.func_175174_a((float)p_175247_4_ + (float)lvt_12_2_ * lvt_11_1_, (float)p_175247_2_, 70, 0, 9, 9);
                     }

                     if (lvt_12_2_ * 2 + 1 == p_175247_6_.func_178860_m()) {
                        this.func_175174_a((float)p_175247_4_ + (float)lvt_12_2_ * lvt_11_1_, (float)p_175247_2_, 79, 0, 9, 9);
                     }
                  }

                  if (lvt_12_2_ * 2 + 1 < lvt_7_1_) {
                     this.func_175174_a((float)p_175247_4_ + (float)lvt_12_2_ * lvt_11_1_, (float)p_175247_2_, lvt_12_2_ >= 10 ? 160 : 52, 0, 9, 9);
                  }

                  if (lvt_12_2_ * 2 + 1 == lvt_7_1_) {
                     this.func_175174_a((float)p_175247_4_ + (float)lvt_12_2_ * lvt_11_1_, (float)p_175247_2_, lvt_12_2_ >= 10 ? 169 : 61, 0, 9, 9);
                  }
               }
            } else {
               float lvt_12_3_ = MathHelper.func_76131_a((float)lvt_7_1_ / 20.0F, 0.0F, 1.0F);
               int lvt_13_1_ = (int)((1.0F - lvt_12_3_) * 255.0F) << 16 | (int)(lvt_12_3_ * 255.0F) << 8;
               String lvt_14_1_ = "" + (float)lvt_7_1_ / 2.0F;
               if (p_175247_5_ - this.field_175250_f.field_71466_p.func_78256_a(lvt_14_1_ + "hp") >= p_175247_4_) {
                  lvt_14_1_ = lvt_14_1_ + "hp";
               }

               this.field_175250_f.field_71466_p.func_175063_a(lvt_14_1_, (float)((p_175247_5_ + p_175247_4_) / 2 - this.field_175250_f.field_71466_p.func_78256_a(lvt_14_1_) / 2), (float)p_175247_2_, lvt_13_1_);
            }
         }
      } else {
         String lvt_8_2_ = EnumChatFormatting.YELLOW + "" + lvt_7_1_;
         this.field_175250_f.field_71466_p.func_175063_a(lvt_8_2_, (float)(p_175247_5_ - this.field_175250_f.field_71466_p.func_78256_a(lvt_8_2_)), (float)p_175247_2_, 16777215);
      }

   }

   public void func_175248_a(IChatComponent p_175248_1_) {
      this.field_175255_h = p_175248_1_;
   }

   public void func_175244_b(IChatComponent p_175244_1_) {
      this.field_175256_i = p_175244_1_;
   }

   public void func_181030_a() {
      this.field_175256_i = null;
      this.field_175255_h = null;
   }

   static class PlayerComparator implements Comparator<NetworkPlayerInfo> {
      private PlayerComparator() {
      }

      public int compare(NetworkPlayerInfo p_compare_1_, NetworkPlayerInfo p_compare_2_) {
         ScorePlayerTeam lvt_3_1_ = p_compare_1_.func_178850_i();
         ScorePlayerTeam lvt_4_1_ = p_compare_2_.func_178850_i();
         return ComparisonChain.start().compareTrueFirst(p_compare_1_.func_178848_b() != WorldSettings.GameType.SPECTATOR, p_compare_2_.func_178848_b() != WorldSettings.GameType.SPECTATOR).compare(lvt_3_1_ != null ? lvt_3_1_.func_96661_b() : "", lvt_4_1_ != null ? lvt_4_1_.func_96661_b() : "").compare(p_compare_1_.func_178845_a().getName(), p_compare_2_.func_178845_a().getName()).result();
      }
   }
}
