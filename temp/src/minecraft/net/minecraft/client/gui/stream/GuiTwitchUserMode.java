package net.minecraft.client.gui.stream;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.stream.IStream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.ChatUserMode;
import tv.twitch.chat.ChatUserSubscription;

public class GuiTwitchUserMode extends GuiScreen {
   private static final EnumChatFormatting field_152331_a;
   private static final EnumChatFormatting field_152335_f;
   private static final EnumChatFormatting field_152336_g;
   private final ChatUserInfo field_152337_h;
   private final IChatComponent field_152338_i;
   private final List<IChatComponent> field_152332_r = Lists.newArrayList();
   private final IStream field_152333_s;
   private int field_152334_t;

   public GuiTwitchUserMode(IStream p_i1064_1_, ChatUserInfo p_i1064_2_) {
      this.field_152333_s = p_i1064_1_;
      this.field_152337_h = p_i1064_2_;
      this.field_152338_i = new ChatComponentText(p_i1064_2_.displayName);
      this.field_152332_r.addAll(func_152328_a(p_i1064_2_.modes, p_i1064_2_.subscriptions, p_i1064_1_));
   }

   public static List<IChatComponent> func_152328_a(Set<ChatUserMode> p_152328_0_, Set<ChatUserSubscription> p_152328_1_, IStream p_152328_2_) {
      String lvt_3_1_ = p_152328_2_ == null ? null : p_152328_2_.func_152921_C();
      boolean lvt_4_1_ = p_152328_2_ != null && p_152328_2_.func_152927_B();
      List<IChatComponent> lvt_5_1_ = Lists.newArrayList();
      Iterator lvt_6_2_ = p_152328_0_.iterator();

      IChatComponent lvt_8_2_;
      ChatComponentText lvt_9_2_;
      while(lvt_6_2_.hasNext()) {
         ChatUserMode lvt_7_1_ = (ChatUserMode)lvt_6_2_.next();
         lvt_8_2_ = func_152329_a(lvt_7_1_, lvt_3_1_, lvt_4_1_);
         if (lvt_8_2_ != null) {
            lvt_9_2_ = new ChatComponentText("- ");
            lvt_9_2_.func_150257_a(lvt_8_2_);
            lvt_5_1_.add(lvt_9_2_);
         }
      }

      lvt_6_2_ = p_152328_1_.iterator();

      while(lvt_6_2_.hasNext()) {
         ChatUserSubscription lvt_7_2_ = (ChatUserSubscription)lvt_6_2_.next();
         lvt_8_2_ = func_152330_a(lvt_7_2_, lvt_3_1_, lvt_4_1_);
         if (lvt_8_2_ != null) {
            lvt_9_2_ = new ChatComponentText("- ");
            lvt_9_2_.func_150257_a(lvt_8_2_);
            lvt_5_1_.add(lvt_9_2_);
         }
      }

      return lvt_5_1_;
   }

   public static IChatComponent func_152330_a(ChatUserSubscription p_152330_0_, String p_152330_1_, boolean p_152330_2_) {
      IChatComponent lvt_3_1_ = null;
      if (p_152330_0_ == ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER) {
         if (p_152330_1_ == null) {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.subscription.subscriber", new Object[0]);
         } else if (p_152330_2_) {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.subscription.subscriber.self", new Object[0]);
         } else {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.subscription.subscriber.other", new Object[]{p_152330_1_});
         }

         lvt_3_1_.func_150256_b().func_150238_a(field_152331_a);
      } else if (p_152330_0_ == ChatUserSubscription.TTV_CHAT_USERSUB_TURBO) {
         lvt_3_1_ = new ChatComponentTranslation("stream.user.subscription.turbo", new Object[0]);
         lvt_3_1_.func_150256_b().func_150238_a(field_152336_g);
      }

      return lvt_3_1_;
   }

   public static IChatComponent func_152329_a(ChatUserMode p_152329_0_, String p_152329_1_, boolean p_152329_2_) {
      IChatComponent lvt_3_1_ = null;
      if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR) {
         lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.administrator", new Object[0]);
         lvt_3_1_.func_150256_b().func_150238_a(field_152336_g);
      } else if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_BANNED) {
         if (p_152329_1_ == null) {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.banned", new Object[0]);
         } else if (p_152329_2_) {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.banned.self", new Object[0]);
         } else {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.banned.other", new Object[]{p_152329_1_});
         }

         lvt_3_1_.func_150256_b().func_150238_a(field_152335_f);
      } else if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_BROADCASTER) {
         if (p_152329_1_ == null) {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.broadcaster", new Object[0]);
         } else if (p_152329_2_) {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.broadcaster.self", new Object[0]);
         } else {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.broadcaster.other", new Object[0]);
         }

         lvt_3_1_.func_150256_b().func_150238_a(field_152331_a);
      } else if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_MODERATOR) {
         if (p_152329_1_ == null) {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.moderator", new Object[0]);
         } else if (p_152329_2_) {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.moderator.self", new Object[0]);
         } else {
            lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.moderator.other", new Object[]{p_152329_1_});
         }

         lvt_3_1_.func_150256_b().func_150238_a(field_152331_a);
      } else if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_STAFF) {
         lvt_3_1_ = new ChatComponentTranslation("stream.user.mode.staff", new Object[0]);
         lvt_3_1_.func_150256_b().func_150238_a(field_152336_g);
      }

      return lvt_3_1_;
   }

   public void func_73866_w_() {
      int lvt_1_1_ = this.field_146294_l / 3;
      int lvt_2_1_ = lvt_1_1_ - 130;
      this.field_146292_n.add(new GuiButton(1, lvt_1_1_ * 0 + lvt_2_1_ / 2, this.field_146295_m - 70, 130, 20, I18n.func_135052_a("stream.userinfo.timeout")));
      this.field_146292_n.add(new GuiButton(0, lvt_1_1_ * 1 + lvt_2_1_ / 2, this.field_146295_m - 70, 130, 20, I18n.func_135052_a("stream.userinfo.ban")));
      this.field_146292_n.add(new GuiButton(2, lvt_1_1_ * 2 + lvt_2_1_ / 2, this.field_146295_m - 70, 130, 20, I18n.func_135052_a("stream.userinfo.mod")));
      this.field_146292_n.add(new GuiButton(5, lvt_1_1_ * 0 + lvt_2_1_ / 2, this.field_146295_m - 45, 130, 20, I18n.func_135052_a("gui.cancel")));
      this.field_146292_n.add(new GuiButton(3, lvt_1_1_ * 1 + lvt_2_1_ / 2, this.field_146295_m - 45, 130, 20, I18n.func_135052_a("stream.userinfo.unban")));
      this.field_146292_n.add(new GuiButton(4, lvt_1_1_ * 2 + lvt_2_1_ / 2, this.field_146295_m - 45, 130, 20, I18n.func_135052_a("stream.userinfo.unmod")));
      int lvt_3_1_ = 0;

      IChatComponent lvt_5_1_;
      for(Iterator lvt_4_1_ = this.field_152332_r.iterator(); lvt_4_1_.hasNext(); lvt_3_1_ = Math.max(lvt_3_1_, this.field_146289_q.func_78256_a(lvt_5_1_.func_150254_d()))) {
         lvt_5_1_ = (IChatComponent)lvt_4_1_.next();
      }

      this.field_152334_t = this.field_146294_l / 2 - lvt_3_1_ / 2;
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if (p_146284_1_.field_146124_l) {
         if (p_146284_1_.field_146127_k == 0) {
            this.field_152333_s.func_152917_b("/ban " + this.field_152337_h.displayName);
         } else if (p_146284_1_.field_146127_k == 3) {
            this.field_152333_s.func_152917_b("/unban " + this.field_152337_h.displayName);
         } else if (p_146284_1_.field_146127_k == 2) {
            this.field_152333_s.func_152917_b("/mod " + this.field_152337_h.displayName);
         } else if (p_146284_1_.field_146127_k == 4) {
            this.field_152333_s.func_152917_b("/unmod " + this.field_152337_h.displayName);
         } else if (p_146284_1_.field_146127_k == 1) {
            this.field_152333_s.func_152917_b("/timeout " + this.field_152337_h.displayName);
         }

         this.field_146297_k.func_147108_a((GuiScreen)null);
      }
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.field_152338_i.func_150260_c(), this.field_146294_l / 2, 70, 16777215);
      int lvt_4_1_ = 80;

      for(Iterator lvt_5_1_ = this.field_152332_r.iterator(); lvt_5_1_.hasNext(); lvt_4_1_ += this.field_146289_q.field_78288_b) {
         IChatComponent lvt_6_1_ = (IChatComponent)lvt_5_1_.next();
         this.func_73731_b(this.field_146289_q, lvt_6_1_.func_150254_d(), this.field_152334_t, lvt_4_1_, 16777215);
      }

      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   static {
      field_152331_a = EnumChatFormatting.DARK_GREEN;
      field_152335_f = EnumChatFormatting.RED;
      field_152336_g = EnumChatFormatting.DARK_PURPLE;
   }
}
