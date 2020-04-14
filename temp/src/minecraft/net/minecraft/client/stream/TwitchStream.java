package net.minecraft.client.stream;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.opengl.GL11;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.VideoParams;
import tv.twitch.chat.ChatRawMessage;
import tv.twitch.chat.ChatTokenizedMessage;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.ChatUserMode;
import tv.twitch.chat.ChatUserSubscription;

public class TwitchStream implements BroadcastController.BroadcastListener, ChatController.ChatListener, IngestServerTester.IngestTestListener, IStream {
   private static final Logger field_152950_b = LogManager.getLogger();
   public static final Marker field_152949_a = MarkerManager.getMarker("STREAM");
   private final BroadcastController field_152951_c;
   private final ChatController field_152952_d;
   private String field_176029_e;
   private final Minecraft field_152953_e;
   private final IChatComponent field_152954_f = new ChatComponentText("Twitch");
   private final Map<String, ChatUserInfo> field_152955_g = Maps.newHashMap();
   private Framebuffer field_152956_h;
   private boolean field_152957_i;
   private int field_152958_j = 30;
   private long field_152959_k = 0L;
   private boolean field_152960_l = false;
   private boolean field_152961_m;
   private boolean field_152962_n;
   private boolean field_152963_o;
   private IStream.AuthFailureReason field_152964_p;
   private static boolean field_152965_q;

   public TwitchStream(Minecraft p_i46057_1_, final Property p_i46057_2_) {
      this.field_152964_p = IStream.AuthFailureReason.ERROR;
      this.field_152953_e = p_i46057_1_;
      this.field_152951_c = new BroadcastController();
      this.field_152952_d = new ChatController();
      this.field_152951_c.func_152841_a(this);
      this.field_152952_d.func_152990_a(this);
      this.field_152951_c.func_152842_a("nmt37qblda36pvonovdkbopzfzw3wlq");
      this.field_152952_d.func_152984_a("nmt37qblda36pvonovdkbopzfzw3wlq");
      this.field_152954_f.func_150256_b().func_150238_a(EnumChatFormatting.DARK_PURPLE);
      if (p_i46057_2_ != null && !Strings.isNullOrEmpty(p_i46057_2_.getValue()) && OpenGlHelper.field_148823_f) {
         Thread lvt_3_1_ = new Thread("Twitch authenticator") {
            public void run() {
               try {
                  URL lvt_1_1_ = new URL("https://api.twitch.tv/kraken?oauth_token=" + URLEncoder.encode(p_i46057_2_.getValue(), "UTF-8"));
                  String lvt_2_1_ = HttpUtil.func_152755_a(lvt_1_1_);
                  JsonObject lvt_3_1_ = JsonUtils.func_151210_l((new JsonParser()).parse(lvt_2_1_), "Response");
                  JsonObject lvt_4_1_ = JsonUtils.func_152754_s(lvt_3_1_, "token");
                  if (JsonUtils.func_151212_i(lvt_4_1_, "valid")) {
                     String lvt_5_1_ = JsonUtils.func_151200_h(lvt_4_1_, "user_name");
                     TwitchStream.field_152950_b.debug(TwitchStream.field_152949_a, "Authenticated with twitch; username is {}", lvt_5_1_);
                     AuthToken lvt_6_1_ = new AuthToken();
                     lvt_6_1_.data = p_i46057_2_.getValue();
                     TwitchStream.this.field_152951_c.func_152818_a(lvt_5_1_, lvt_6_1_);
                     TwitchStream.this.field_152952_d.func_152998_c(lvt_5_1_);
                     TwitchStream.this.field_152952_d.func_152994_a(lvt_6_1_);
                     Runtime.getRuntime().addShutdownHook(new Thread("Twitch shutdown hook") {
                        public void run() {
                           TwitchStream.this.func_152923_i();
                        }
                     });
                     TwitchStream.this.field_152951_c.func_152817_A();
                     TwitchStream.this.field_152952_d.func_175984_n();
                  } else {
                     TwitchStream.this.field_152964_p = IStream.AuthFailureReason.INVALID_TOKEN;
                     TwitchStream.field_152950_b.error(TwitchStream.field_152949_a, "Given twitch access token is invalid");
                  }
               } catch (IOException var7) {
                  TwitchStream.this.field_152964_p = IStream.AuthFailureReason.ERROR;
                  TwitchStream.field_152950_b.error(TwitchStream.field_152949_a, (String)"Could not authenticate with twitch", (Throwable)var7);
               }

            }
         };
         lvt_3_1_.setDaemon(true);
         lvt_3_1_.start();
      }

   }

   public void func_152923_i() {
      field_152950_b.debug(field_152949_a, "Shutdown streaming");
      this.field_152951_c.statCallback();
      this.field_152952_d.func_175988_p();
   }

   public void func_152935_j() {
      int lvt_1_1_ = this.field_152953_e.field_71474_y.field_152408_R;
      boolean lvt_2_1_ = this.field_176029_e != null && this.field_152952_d.func_175990_d(this.field_176029_e);
      boolean lvt_3_1_ = this.field_152952_d.func_153000_j() == ChatController.ChatState.Initialized && (this.field_176029_e == null || this.field_152952_d.func_175989_e(this.field_176029_e) == ChatController.EnumChannelState.Disconnected);
      if (lvt_1_1_ == 2) {
         if (lvt_2_1_) {
            field_152950_b.debug(field_152949_a, "Disconnecting from twitch chat per user options");
            this.field_152952_d.func_175991_l(this.field_176029_e);
         }
      } else if (lvt_1_1_ == 1) {
         if (lvt_3_1_ && this.field_152951_c.func_152849_q()) {
            field_152950_b.debug(field_152949_a, "Connecting to twitch chat per user options");
            this.func_152942_I();
         }
      } else if (lvt_1_1_ == 0) {
         if (lvt_2_1_ && !this.func_152934_n()) {
            field_152950_b.debug(field_152949_a, "Disconnecting from twitch chat as user is no longer streaming");
            this.field_152952_d.func_175991_l(this.field_176029_e);
         } else if (lvt_3_1_ && this.func_152934_n()) {
            field_152950_b.debug(field_152949_a, "Connecting to twitch chat as user is streaming");
            this.func_152942_I();
         }
      }

      this.field_152951_c.func_152821_H();
      this.field_152952_d.func_152997_n();
   }

   protected void func_152942_I() {
      ChatController.ChatState lvt_1_1_ = this.field_152952_d.func_153000_j();
      String lvt_2_1_ = this.field_152951_c.func_152843_l().name;
      this.field_176029_e = lvt_2_1_;
      if (lvt_1_1_ != ChatController.ChatState.Initialized) {
         field_152950_b.warn("Invalid twitch chat state {}", lvt_1_1_);
      } else if (this.field_152952_d.func_175989_e(this.field_176029_e) == ChatController.EnumChannelState.Disconnected) {
         this.field_152952_d.func_152986_d(lvt_2_1_);
      } else {
         field_152950_b.warn("Invalid twitch chat state {}", lvt_1_1_);
      }

   }

   public void func_152922_k() {
      if (this.field_152951_c.func_152850_m() && !this.field_152951_c.func_152839_p()) {
         long lvt_1_1_ = System.nanoTime();
         long lvt_3_1_ = (long)(1000000000 / this.field_152958_j);
         long lvt_5_1_ = lvt_1_1_ - this.field_152959_k;
         boolean lvt_7_1_ = lvt_5_1_ >= lvt_3_1_;
         if (lvt_7_1_) {
            FrameBuffer lvt_8_1_ = this.field_152951_c.func_152822_N();
            Framebuffer lvt_9_1_ = this.field_152953_e.func_147110_a();
            this.field_152956_h.func_147610_a(true);
            GlStateManager.func_179128_n(5889);
            GlStateManager.func_179094_E();
            GlStateManager.func_179096_D();
            GlStateManager.func_179130_a(0.0D, (double)this.field_152956_h.field_147621_c, (double)this.field_152956_h.field_147618_d, 0.0D, 1000.0D, 3000.0D);
            GlStateManager.func_179128_n(5888);
            GlStateManager.func_179094_E();
            GlStateManager.func_179096_D();
            GlStateManager.func_179109_b(0.0F, 0.0F, -2000.0F);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179083_b(0, 0, this.field_152956_h.field_147621_c, this.field_152956_h.field_147618_d);
            GlStateManager.func_179098_w();
            GlStateManager.func_179118_c();
            GlStateManager.func_179084_k();
            float lvt_10_1_ = (float)this.field_152956_h.field_147621_c;
            float lvt_11_1_ = (float)this.field_152956_h.field_147618_d;
            float lvt_12_1_ = (float)lvt_9_1_.field_147621_c / (float)lvt_9_1_.field_147622_a;
            float lvt_13_1_ = (float)lvt_9_1_.field_147618_d / (float)lvt_9_1_.field_147620_b;
            lvt_9_1_.func_147612_c();
            GL11.glTexParameterf(3553, 10241, 9729.0F);
            GL11.glTexParameterf(3553, 10240, 9729.0F);
            Tessellator lvt_14_1_ = Tessellator.func_178181_a();
            WorldRenderer lvt_15_1_ = lvt_14_1_.func_178180_c();
            lvt_15_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            lvt_15_1_.func_181662_b(0.0D, (double)lvt_11_1_, 0.0D).func_181673_a(0.0D, (double)lvt_13_1_).func_181675_d();
            lvt_15_1_.func_181662_b((double)lvt_10_1_, (double)lvt_11_1_, 0.0D).func_181673_a((double)lvt_12_1_, (double)lvt_13_1_).func_181675_d();
            lvt_15_1_.func_181662_b((double)lvt_10_1_, 0.0D, 0.0D).func_181673_a((double)lvt_12_1_, 0.0D).func_181675_d();
            lvt_15_1_.func_181662_b(0.0D, 0.0D, 0.0D).func_181673_a(0.0D, 0.0D).func_181675_d();
            lvt_14_1_.func_78381_a();
            lvt_9_1_.func_147606_d();
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n(5889);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n(5888);
            this.field_152951_c.func_152846_a(lvt_8_1_);
            this.field_152956_h.func_147609_e();
            this.field_152951_c.func_152859_b(lvt_8_1_);
            this.field_152959_k = lvt_1_1_;
         }

      }
   }

   public boolean func_152936_l() {
      return this.field_152951_c.func_152849_q();
   }

   public boolean func_152924_m() {
      return this.field_152951_c.func_152857_n();
   }

   public boolean func_152934_n() {
      return this.field_152951_c.func_152850_m();
   }

   public void func_152911_a(Metadata p_152911_1_, long p_152911_2_) {
      if (this.func_152934_n() && this.field_152957_i) {
         long lvt_4_1_ = this.field_152951_c.func_152844_x();
         if (!this.field_152951_c.func_152840_a(p_152911_1_.func_152810_c(), lvt_4_1_ + p_152911_2_, p_152911_1_.func_152809_a(), p_152911_1_.func_152806_b())) {
            field_152950_b.warn(field_152949_a, "Couldn't send stream metadata action at {}: {}", lvt_4_1_ + p_152911_2_, p_152911_1_);
         } else {
            field_152950_b.debug(field_152949_a, "Sent stream metadata action at {}: {}", lvt_4_1_ + p_152911_2_, p_152911_1_);
         }

      }
   }

   public void func_176026_a(Metadata p_176026_1_, long p_176026_2_, long p_176026_4_) {
      if (this.func_152934_n() && this.field_152957_i) {
         long lvt_6_1_ = this.field_152951_c.func_152844_x();
         String lvt_8_1_ = p_176026_1_.func_152809_a();
         String lvt_9_1_ = p_176026_1_.func_152806_b();
         long lvt_10_1_ = this.field_152951_c.func_177946_b(p_176026_1_.func_152810_c(), lvt_6_1_ + p_176026_2_, lvt_8_1_, lvt_9_1_);
         if (lvt_10_1_ < 0L) {
            field_152950_b.warn(field_152949_a, "Could not send stream metadata sequence from {} to {}: {}", lvt_6_1_ + p_176026_2_, lvt_6_1_ + p_176026_4_, p_176026_1_);
         } else if (this.field_152951_c.func_177947_a(p_176026_1_.func_152810_c(), lvt_6_1_ + p_176026_4_, lvt_10_1_, lvt_8_1_, lvt_9_1_)) {
            field_152950_b.debug(field_152949_a, "Sent stream metadata sequence from {} to {}: {}", lvt_6_1_ + p_176026_2_, lvt_6_1_ + p_176026_4_, p_176026_1_);
         } else {
            field_152950_b.warn(field_152949_a, "Half-sent stream metadata sequence from {} to {}: {}", lvt_6_1_ + p_176026_2_, lvt_6_1_ + p_176026_4_, p_176026_1_);
         }

      }
   }

   public boolean func_152919_o() {
      return this.field_152951_c.func_152839_p();
   }

   public void func_152931_p() {
      if (this.field_152951_c.func_152830_D()) {
         field_152950_b.debug(field_152949_a, "Requested commercial from Twitch");
      } else {
         field_152950_b.warn(field_152949_a, "Could not request commercial from Twitch");
      }

   }

   public void func_152916_q() {
      this.field_152951_c.func_152847_F();
      this.field_152962_n = true;
      this.func_152915_s();
   }

   public void func_152933_r() {
      this.field_152951_c.func_152854_G();
      this.field_152962_n = false;
      this.func_152915_s();
   }

   public void func_152915_s() {
      if (this.func_152934_n()) {
         float lvt_1_1_ = this.field_152953_e.field_71474_y.field_152402_L;
         boolean lvt_2_1_ = this.field_152962_n || lvt_1_1_ <= 0.0F;
         this.field_152951_c.func_152837_b(lvt_2_1_ ? 0.0F : lvt_1_1_);
         this.field_152951_c.func_152829_a(this.func_152929_G() ? 0.0F : this.field_152953_e.field_71474_y.field_152401_K);
      }

   }

   public void func_152930_t() {
      GameSettings lvt_1_1_ = this.field_152953_e.field_71474_y;
      VideoParams lvt_2_1_ = this.field_152951_c.func_152834_a(func_152946_b(lvt_1_1_.field_152403_M), func_152948_a(lvt_1_1_.field_152404_N), func_152947_c(lvt_1_1_.field_152400_J), (float)this.field_152953_e.field_71443_c / (float)this.field_152953_e.field_71440_d);
      switch(lvt_1_1_.field_152405_O) {
      case 0:
         lvt_2_1_.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_LOW;
         break;
      case 1:
         lvt_2_1_.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_MEDIUM;
         break;
      case 2:
         lvt_2_1_.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
      }

      if (this.field_152956_h == null) {
         this.field_152956_h = new Framebuffer(lvt_2_1_.outputWidth, lvt_2_1_.outputHeight, false);
      } else {
         this.field_152956_h.func_147613_a(lvt_2_1_.outputWidth, lvt_2_1_.outputHeight);
      }

      if (lvt_1_1_.field_152407_Q != null && lvt_1_1_.field_152407_Q.length() > 0) {
         IngestServer[] lvt_3_1_ = this.func_152925_v();
         int lvt_4_1_ = lvt_3_1_.length;

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
            IngestServer lvt_6_1_ = lvt_3_1_[lvt_5_1_];
            if (lvt_6_1_.serverUrl.equals(lvt_1_1_.field_152407_Q)) {
               this.field_152951_c.func_152824_a(lvt_6_1_);
               break;
            }
         }
      }

      this.field_152958_j = lvt_2_1_.targetFps;
      this.field_152957_i = lvt_1_1_.field_152406_P;
      this.field_152951_c.func_152836_a(lvt_2_1_);
      field_152950_b.info(field_152949_a, "Streaming at {}/{} at {} kbps to {}", lvt_2_1_.outputWidth, lvt_2_1_.outputHeight, lvt_2_1_.maxKbps, this.field_152951_c.func_152833_s().serverUrl);
      this.field_152951_c.func_152828_a((String)null, "Minecraft", (String)null);
   }

   public void func_152914_u() {
      if (this.field_152951_c.func_152819_E()) {
         field_152950_b.info(field_152949_a, "Stopped streaming to Twitch");
      } else {
         field_152950_b.warn(field_152949_a, "Could not stop streaming to Twitch");
      }

   }

   public void func_152900_a(ErrorCode p_152900_1_, AuthToken p_152900_2_) {
   }

   public void func_152897_a(ErrorCode p_152897_1_) {
      if (ErrorCode.succeeded(p_152897_1_)) {
         field_152950_b.debug(field_152949_a, "Login attempt successful");
         this.field_152961_m = true;
      } else {
         field_152950_b.warn(field_152949_a, "Login attempt unsuccessful: {} (error code {})", ErrorCode.getString(p_152897_1_), p_152897_1_.getValue());
         this.field_152961_m = false;
      }

   }

   public void func_152898_a(ErrorCode p_152898_1_, GameInfo[] p_152898_2_) {
   }

   public void func_152891_a(BroadcastController.BroadcastState p_152891_1_) {
      field_152950_b.debug(field_152949_a, "Broadcast state changed to {}", p_152891_1_);
      if (p_152891_1_ == BroadcastController.BroadcastState.Initialized) {
         this.field_152951_c.func_152827_a(BroadcastController.BroadcastState.Authenticated);
      }

   }

   public void func_152895_a() {
      field_152950_b.info(field_152949_a, "Logged out of twitch");
   }

   public void func_152894_a(StreamInfo p_152894_1_) {
      field_152950_b.debug(field_152949_a, "Stream info updated; {} viewers on stream ID {}", p_152894_1_.viewers, p_152894_1_.streamId);
   }

   public void func_152896_a(IngestList p_152896_1_) {
   }

   public void func_152893_b(ErrorCode p_152893_1_) {
      field_152950_b.warn(field_152949_a, "Issue submitting frame: {} (Error code {})", ErrorCode.getString(p_152893_1_), p_152893_1_.getValue());
      this.field_152953_e.field_71456_v.func_146158_b().func_146234_a(new ChatComponentText("Issue streaming frame: " + p_152893_1_ + " (" + ErrorCode.getString(p_152893_1_) + ")"), 2);
   }

   public void func_152899_b() {
      this.func_152915_s();
      field_152950_b.info(field_152949_a, "Broadcast to Twitch has started");
   }

   public void func_152901_c() {
      field_152950_b.info(field_152949_a, "Broadcast to Twitch has stopped");
   }

   public void func_152892_c(ErrorCode p_152892_1_) {
      ChatComponentTranslation lvt_2_1_;
      if (p_152892_1_ == ErrorCode.TTV_EC_SOUNDFLOWER_NOT_INSTALLED) {
         lvt_2_1_ = new ChatComponentTranslation("stream.unavailable.soundflower.chat.link", new Object[0]);
         lvt_2_1_.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://help.mojang.com/customer/portal/articles/1374877-configuring-soundflower-for-streaming-on-apple-computers"));
         lvt_2_1_.func_150256_b().func_150228_d(true);
         IChatComponent lvt_3_1_ = new ChatComponentTranslation("stream.unavailable.soundflower.chat", new Object[]{lvt_2_1_});
         lvt_3_1_.func_150256_b().func_150238_a(EnumChatFormatting.DARK_RED);
         this.field_152953_e.field_71456_v.func_146158_b().func_146227_a(lvt_3_1_);
      } else {
         lvt_2_1_ = new ChatComponentTranslation("stream.unavailable.unknown.chat", new Object[]{ErrorCode.getString(p_152892_1_)});
         lvt_2_1_.func_150256_b().func_150238_a(EnumChatFormatting.DARK_RED);
         this.field_152953_e.field_71456_v.func_146158_b().func_146227_a(lvt_2_1_);
      }

   }

   public void func_152907_a(IngestServerTester p_152907_1_, IngestServerTester.IngestTestState p_152907_2_) {
      field_152950_b.debug(field_152949_a, "Ingest test state changed to {}", p_152907_2_);
      if (p_152907_2_ == IngestServerTester.IngestTestState.Finished) {
         this.field_152960_l = true;
      }

   }

   public static int func_152948_a(float p_152948_0_) {
      return MathHelper.func_76141_d(10.0F + p_152948_0_ * 50.0F);
   }

   public static int func_152946_b(float p_152946_0_) {
      return MathHelper.func_76141_d(230.0F + p_152946_0_ * 3270.0F);
   }

   public static float func_152947_c(float p_152947_0_) {
      return 0.1F + p_152947_0_ * 0.1F;
   }

   public IngestServer[] func_152925_v() {
      return this.field_152951_c.func_152855_t().getServers();
   }

   public void func_152909_x() {
      IngestServerTester lvt_1_1_ = this.field_152951_c.func_152838_J();
      if (lvt_1_1_ != null) {
         lvt_1_1_.func_153042_a(this);
      }

   }

   public IngestServerTester func_152932_y() {
      return this.field_152951_c.func_152856_w();
   }

   public boolean func_152908_z() {
      return this.field_152951_c.func_152825_o();
   }

   public int func_152920_A() {
      return this.func_152934_n() ? this.field_152951_c.func_152816_j().viewers : 0;
   }

   public void func_176023_d(ErrorCode p_176023_1_) {
      if (ErrorCode.failed(p_176023_1_)) {
         field_152950_b.error(field_152949_a, "Chat failed to initialize");
      }

   }

   public void func_176022_e(ErrorCode p_176022_1_) {
      if (ErrorCode.failed(p_176022_1_)) {
         field_152950_b.error(field_152949_a, "Chat failed to shutdown");
      }

   }

   public void func_176017_a(ChatController.ChatState p_176017_1_) {
   }

   public void func_180605_a(String p_180605_1_, ChatRawMessage[] p_180605_2_) {
      ChatRawMessage[] lvt_3_1_ = p_180605_2_;
      int lvt_4_1_ = p_180605_2_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
         ChatRawMessage lvt_6_1_ = lvt_3_1_[lvt_5_1_];
         this.func_176027_a(lvt_6_1_.userName, lvt_6_1_);
         if (this.func_176028_a(lvt_6_1_.modes, lvt_6_1_.subscriptions, this.field_152953_e.field_71474_y.field_152409_S)) {
            IChatComponent lvt_7_1_ = new ChatComponentText(lvt_6_1_.userName);
            IChatComponent lvt_8_1_ = new ChatComponentTranslation("chat.stream." + (lvt_6_1_.action ? "emote" : "text"), new Object[]{this.field_152954_f, lvt_7_1_, EnumChatFormatting.func_110646_a(lvt_6_1_.message)});
            if (lvt_6_1_.action) {
               lvt_8_1_.func_150256_b().func_150217_b(true);
            }

            IChatComponent lvt_9_1_ = new ChatComponentText("");
            lvt_9_1_.func_150257_a(new ChatComponentTranslation("stream.userinfo.chatTooltip", new Object[0]));
            Iterator lvt_10_1_ = GuiTwitchUserMode.func_152328_a(lvt_6_1_.modes, lvt_6_1_.subscriptions, (IStream)null).iterator();

            while(lvt_10_1_.hasNext()) {
               IChatComponent lvt_11_1_ = (IChatComponent)lvt_10_1_.next();
               lvt_9_1_.func_150258_a("\n");
               lvt_9_1_.func_150257_a(lvt_11_1_);
            }

            lvt_7_1_.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, lvt_9_1_));
            lvt_7_1_.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.TWITCH_USER_INFO, lvt_6_1_.userName));
            this.field_152953_e.field_71456_v.func_146158_b().func_146227_a(lvt_8_1_);
         }
      }

   }

   public void func_176025_a(String p_176025_1_, ChatTokenizedMessage[] p_176025_2_) {
   }

   private void func_176027_a(String p_176027_1_, ChatRawMessage p_176027_2_) {
      ChatUserInfo lvt_3_1_ = (ChatUserInfo)this.field_152955_g.get(p_176027_1_);
      if (lvt_3_1_ == null) {
         lvt_3_1_ = new ChatUserInfo();
         lvt_3_1_.displayName = p_176027_1_;
         this.field_152955_g.put(p_176027_1_, lvt_3_1_);
      }

      lvt_3_1_.subscriptions = p_176027_2_.subscriptions;
      lvt_3_1_.modes = p_176027_2_.modes;
      lvt_3_1_.nameColorARGB = p_176027_2_.nameColorARGB;
   }

   private boolean func_176028_a(Set<ChatUserMode> p_176028_1_, Set<ChatUserSubscription> p_176028_2_, int p_176028_3_) {
      if (p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_BANNED)) {
         return false;
      } else if (p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR)) {
         return true;
      } else if (p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_MODERATOR)) {
         return true;
      } else if (p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_STAFF)) {
         return true;
      } else if (p_176028_3_ == 0) {
         return true;
      } else {
         return p_176028_3_ == 1 ? p_176028_2_.contains(ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER) : false;
      }
   }

   public void func_176018_a(String p_176018_1_, ChatUserInfo[] p_176018_2_, ChatUserInfo[] p_176018_3_, ChatUserInfo[] p_176018_4_) {
      ChatUserInfo[] lvt_5_3_ = p_176018_3_;
      int lvt_6_3_ = p_176018_3_.length;

      int lvt_7_3_;
      ChatUserInfo lvt_8_3_;
      for(lvt_7_3_ = 0; lvt_7_3_ < lvt_6_3_; ++lvt_7_3_) {
         lvt_8_3_ = lvt_5_3_[lvt_7_3_];
         this.field_152955_g.remove(lvt_8_3_.displayName);
      }

      lvt_5_3_ = p_176018_4_;
      lvt_6_3_ = p_176018_4_.length;

      for(lvt_7_3_ = 0; lvt_7_3_ < lvt_6_3_; ++lvt_7_3_) {
         lvt_8_3_ = lvt_5_3_[lvt_7_3_];
         this.field_152955_g.put(lvt_8_3_.displayName, lvt_8_3_);
      }

      lvt_5_3_ = p_176018_2_;
      lvt_6_3_ = p_176018_2_.length;

      for(lvt_7_3_ = 0; lvt_7_3_ < lvt_6_3_; ++lvt_7_3_) {
         lvt_8_3_ = lvt_5_3_[lvt_7_3_];
         this.field_152955_g.put(lvt_8_3_.displayName, lvt_8_3_);
      }

   }

   public void func_180606_a(String p_180606_1_) {
      field_152950_b.debug(field_152949_a, "Chat connected");
   }

   public void func_180607_b(String p_180607_1_) {
      field_152950_b.debug(field_152949_a, "Chat disconnected");
      this.field_152955_g.clear();
   }

   public void func_176019_a(String p_176019_1_, String p_176019_2_) {
   }

   public void func_176021_d() {
   }

   public void func_176024_e() {
   }

   public void func_176016_c(String p_176016_1_) {
   }

   public void func_176020_d(String p_176020_1_) {
   }

   public boolean func_152927_B() {
      return this.field_176029_e != null && this.field_176029_e.equals(this.field_152951_c.func_152843_l().name);
   }

   public String func_152921_C() {
      return this.field_176029_e;
   }

   public ChatUserInfo func_152926_a(String p_152926_1_) {
      return (ChatUserInfo)this.field_152955_g.get(p_152926_1_);
   }

   public void func_152917_b(String p_152917_1_) {
      this.field_152952_d.func_175986_a(this.field_176029_e, p_152917_1_);
   }

   public boolean func_152928_D() {
      return field_152965_q && this.field_152951_c.func_152858_b();
   }

   public ErrorCode func_152912_E() {
      return !field_152965_q ? ErrorCode.TTV_EC_OS_TOO_OLD : this.field_152951_c.func_152852_P();
   }

   public boolean func_152913_F() {
      return this.field_152961_m;
   }

   public void func_152910_a(boolean p_152910_1_) {
      this.field_152963_o = p_152910_1_;
      this.func_152915_s();
   }

   public boolean func_152929_G() {
      boolean lvt_1_1_ = this.field_152953_e.field_71474_y.field_152410_T == 1;
      return this.field_152962_n || this.field_152953_e.field_71474_y.field_152401_K <= 0.0F || lvt_1_1_ != this.field_152963_o;
   }

   public IStream.AuthFailureReason func_152918_H() {
      return this.field_152964_p;
   }

   static {
      try {
         if (Util.func_110647_a() == Util.EnumOS.WINDOWS) {
            System.loadLibrary("avutil-ttv-51");
            System.loadLibrary("swresample-ttv-0");
            System.loadLibrary("libmp3lame-ttv");
            if (System.getProperty("os.arch").contains("64")) {
               System.loadLibrary("libmfxsw64");
            } else {
               System.loadLibrary("libmfxsw32");
            }
         }

         field_152965_q = true;
      } catch (Throwable var1) {
         field_152965_q = false;
      }

   }
}
