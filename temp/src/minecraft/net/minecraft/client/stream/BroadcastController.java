package net.minecraft.client.stream;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ThreadSafeBoundList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.twitch.AuthToken;
import tv.twitch.Core;
import tv.twitch.ErrorCode;
import tv.twitch.MessageLevel;
import tv.twitch.StandardCoreAPI;
import tv.twitch.broadcast.ArchivingState;
import tv.twitch.broadcast.AudioDeviceType;
import tv.twitch.broadcast.AudioParams;
import tv.twitch.broadcast.ChannelInfo;
import tv.twitch.broadcast.DesktopStreamAPI;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.GameInfoList;
import tv.twitch.broadcast.IStatCallbacks;
import tv.twitch.broadcast.IStreamCallbacks;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.PixelFormat;
import tv.twitch.broadcast.StartFlags;
import tv.twitch.broadcast.StatType;
import tv.twitch.broadcast.Stream;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.StreamInfoForSetting;
import tv.twitch.broadcast.UserInfo;
import tv.twitch.broadcast.VideoParams;

public class BroadcastController {
   private static final Logger field_152861_B = LogManager.getLogger();
   protected final int field_152865_a = 30;
   protected final int field_152866_b = 3;
   private static final ThreadSafeBoundList<String> field_152862_C = new ThreadSafeBoundList(String.class, 50);
   private String field_152863_D = null;
   protected BroadcastController.BroadcastListener field_152867_c = null;
   protected String field_152868_d = "";
   protected String field_152869_e = "";
   protected String field_152870_f = "";
   protected boolean field_152871_g = true;
   protected Core field_152872_h = null;
   protected Stream field_152873_i = null;
   protected List<FrameBuffer> field_152874_j = Lists.newArrayList();
   protected List<FrameBuffer> field_152875_k = Lists.newArrayList();
   protected boolean field_152876_l = false;
   protected boolean field_152877_m = false;
   protected boolean field_152878_n = false;
   protected BroadcastController.BroadcastState field_152879_o;
   protected String field_152880_p;
   protected VideoParams field_152881_q;
   protected AudioParams field_152882_r;
   protected IngestList field_152883_s;
   protected IngestServer field_152884_t;
   protected AuthToken field_152885_u;
   protected ChannelInfo field_152886_v;
   protected UserInfo field_152887_w;
   protected StreamInfo field_152888_x;
   protected ArchivingState field_152889_y;
   protected long field_152890_z;
   protected IngestServerTester field_152860_A;
   private ErrorCode field_152864_E;
   protected IStreamCallbacks field_177948_B;
   protected IStatCallbacks field_177949_C;

   public void func_152841_a(BroadcastController.BroadcastListener p_152841_1_) {
      this.field_152867_c = p_152841_1_;
   }

   public boolean func_152858_b() {
      return this.field_152876_l;
   }

   public void func_152842_a(String p_152842_1_) {
      this.field_152868_d = p_152842_1_;
   }

   public StreamInfo func_152816_j() {
      return this.field_152888_x;
   }

   public ChannelInfo func_152843_l() {
      return this.field_152886_v;
   }

   public boolean func_152850_m() {
      return this.field_152879_o == BroadcastController.BroadcastState.Broadcasting || this.field_152879_o == BroadcastController.BroadcastState.Paused;
   }

   public boolean func_152857_n() {
      return this.field_152879_o == BroadcastController.BroadcastState.ReadyToBroadcast;
   }

   public boolean func_152825_o() {
      return this.field_152879_o == BroadcastController.BroadcastState.IngestTesting;
   }

   public boolean func_152839_p() {
      return this.field_152879_o == BroadcastController.BroadcastState.Paused;
   }

   public boolean func_152849_q() {
      return this.field_152877_m;
   }

   public IngestServer func_152833_s() {
      return this.field_152884_t;
   }

   public void func_152824_a(IngestServer p_152824_1_) {
      this.field_152884_t = p_152824_1_;
   }

   public IngestList func_152855_t() {
      return this.field_152883_s;
   }

   public void func_152829_a(float p_152829_1_) {
      this.field_152873_i.setVolume(AudioDeviceType.TTV_RECORDER_DEVICE, p_152829_1_);
   }

   public void func_152837_b(float p_152837_1_) {
      this.field_152873_i.setVolume(AudioDeviceType.TTV_PLAYBACK_DEVICE, p_152837_1_);
   }

   public IngestServerTester func_152856_w() {
      return this.field_152860_A;
   }

   public long func_152844_x() {
      return this.field_152873_i.getStreamTime();
   }

   protected boolean func_152848_y() {
      return true;
   }

   public ErrorCode func_152852_P() {
      return this.field_152864_E;
   }

   public BroadcastController() {
      this.field_152879_o = BroadcastController.BroadcastState.Uninitialized;
      this.field_152880_p = null;
      this.field_152881_q = null;
      this.field_152882_r = null;
      this.field_152883_s = new IngestList(new IngestServer[0]);
      this.field_152884_t = null;
      this.field_152885_u = new AuthToken();
      this.field_152886_v = new ChannelInfo();
      this.field_152887_w = new UserInfo();
      this.field_152888_x = new StreamInfo();
      this.field_152889_y = new ArchivingState();
      this.field_152890_z = 0L;
      this.field_152860_A = null;
      this.field_177948_B = new IStreamCallbacks() {
         public void requestAuthTokenCallback(ErrorCode p_requestAuthTokenCallback_1_, AuthToken p_requestAuthTokenCallback_2_) {
            if (ErrorCode.succeeded(p_requestAuthTokenCallback_1_)) {
               BroadcastController.this.field_152885_u = p_requestAuthTokenCallback_2_;
               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Authenticated);
            } else {
               BroadcastController.this.field_152885_u.data = "";
               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Initialized);
               String lvt_3_1_ = ErrorCode.getString(p_requestAuthTokenCallback_1_);
               BroadcastController.this.func_152820_d(String.format("RequestAuthTokenDoneCallback got failure: %s", lvt_3_1_));
            }

            try {
               if (BroadcastController.this.field_152867_c != null) {
                  BroadcastController.this.field_152867_c.func_152900_a(p_requestAuthTokenCallback_1_, p_requestAuthTokenCallback_2_);
               }
            } catch (Exception var4) {
               BroadcastController.this.func_152820_d(var4.toString());
            }

         }

         public void loginCallback(ErrorCode p_loginCallback_1_, ChannelInfo p_loginCallback_2_) {
            if (ErrorCode.succeeded(p_loginCallback_1_)) {
               BroadcastController.this.field_152886_v = p_loginCallback_2_;
               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.LoggedIn);
               BroadcastController.this.field_152877_m = true;
            } else {
               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Initialized);
               BroadcastController.this.field_152877_m = false;
               String lvt_3_1_ = ErrorCode.getString(p_loginCallback_1_);
               BroadcastController.this.func_152820_d(String.format("LoginCallback got failure: %s", lvt_3_1_));
            }

            try {
               if (BroadcastController.this.field_152867_c != null) {
                  BroadcastController.this.field_152867_c.func_152897_a(p_loginCallback_1_);
               }
            } catch (Exception var4) {
               BroadcastController.this.func_152820_d(var4.toString());
            }

         }

         public void getIngestServersCallback(ErrorCode p_getIngestServersCallback_1_, IngestList p_getIngestServersCallback_2_) {
            if (ErrorCode.succeeded(p_getIngestServersCallback_1_)) {
               BroadcastController.this.field_152883_s = p_getIngestServersCallback_2_;
               BroadcastController.this.field_152884_t = BroadcastController.this.field_152883_s.getDefaultServer();
               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.ReceivedIngestServers);

               try {
                  if (BroadcastController.this.field_152867_c != null) {
                     BroadcastController.this.field_152867_c.func_152896_a(p_getIngestServersCallback_2_);
                  }
               } catch (Exception var4) {
                  BroadcastController.this.func_152820_d(var4.toString());
               }
            } else {
               String lvt_3_2_ = ErrorCode.getString(p_getIngestServersCallback_1_);
               BroadcastController.this.func_152820_d(String.format("IngestListCallback got failure: %s", lvt_3_2_));
               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.LoggingIn);
            }

         }

         public void getUserInfoCallback(ErrorCode p_getUserInfoCallback_1_, UserInfo p_getUserInfoCallback_2_) {
            BroadcastController.this.field_152887_w = p_getUserInfoCallback_2_;
            if (ErrorCode.failed(p_getUserInfoCallback_1_)) {
               String lvt_3_1_ = ErrorCode.getString(p_getUserInfoCallback_1_);
               BroadcastController.this.func_152820_d(String.format("UserInfoDoneCallback got failure: %s", lvt_3_1_));
            }

         }

         public void getStreamInfoCallback(ErrorCode p_getStreamInfoCallback_1_, StreamInfo p_getStreamInfoCallback_2_) {
            if (ErrorCode.succeeded(p_getStreamInfoCallback_1_)) {
               BroadcastController.this.field_152888_x = p_getStreamInfoCallback_2_;

               try {
                  if (BroadcastController.this.field_152867_c != null) {
                     BroadcastController.this.field_152867_c.func_152894_a(p_getStreamInfoCallback_2_);
                  }
               } catch (Exception var4) {
                  BroadcastController.this.func_152820_d(var4.toString());
               }
            } else {
               String lvt_3_2_ = ErrorCode.getString(p_getStreamInfoCallback_1_);
               BroadcastController.this.func_152832_e(String.format("StreamInfoDoneCallback got failure: %s", lvt_3_2_));
            }

         }

         public void getArchivingStateCallback(ErrorCode p_getArchivingStateCallback_1_, ArchivingState p_getArchivingStateCallback_2_) {
            BroadcastController.this.field_152889_y = p_getArchivingStateCallback_2_;
            if (ErrorCode.failed(p_getArchivingStateCallback_1_)) {
            }

         }

         public void runCommercialCallback(ErrorCode p_runCommercialCallback_1_) {
            if (ErrorCode.failed(p_runCommercialCallback_1_)) {
               String lvt_2_1_ = ErrorCode.getString(p_runCommercialCallback_1_);
               BroadcastController.this.func_152832_e(String.format("RunCommercialCallback got failure: %s", lvt_2_1_));
            }

         }

         public void setStreamInfoCallback(ErrorCode p_setStreamInfoCallback_1_) {
            if (ErrorCode.failed(p_setStreamInfoCallback_1_)) {
               String lvt_2_1_ = ErrorCode.getString(p_setStreamInfoCallback_1_);
               BroadcastController.this.func_152832_e(String.format("SetStreamInfoCallback got failure: %s", lvt_2_1_));
            }

         }

         public void getGameNameListCallback(ErrorCode p_getGameNameListCallback_1_, GameInfoList p_getGameNameListCallback_2_) {
            if (ErrorCode.failed(p_getGameNameListCallback_1_)) {
               String lvt_3_1_ = ErrorCode.getString(p_getGameNameListCallback_1_);
               BroadcastController.this.func_152820_d(String.format("GameNameListCallback got failure: %s", lvt_3_1_));
            }

            try {
               if (BroadcastController.this.field_152867_c != null) {
                  BroadcastController.this.field_152867_c.func_152898_a(p_getGameNameListCallback_1_, p_getGameNameListCallback_2_ == null ? new GameInfo[0] : p_getGameNameListCallback_2_.list);
               }
            } catch (Exception var4) {
               BroadcastController.this.func_152820_d(var4.toString());
            }

         }

         public void bufferUnlockCallback(long p_bufferUnlockCallback_1_) {
            FrameBuffer lvt_3_1_ = FrameBuffer.lookupBuffer(p_bufferUnlockCallback_1_);
            BroadcastController.this.field_152875_k.add(lvt_3_1_);
         }

         public void startCallback(ErrorCode p_startCallback_1_) {
            if (ErrorCode.succeeded(p_startCallback_1_)) {
               try {
                  if (BroadcastController.this.field_152867_c != null) {
                     BroadcastController.this.field_152867_c.func_152899_b();
                  }
               } catch (Exception var4) {
                  BroadcastController.this.func_152820_d(var4.toString());
               }

               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Broadcasting);
            } else {
               BroadcastController.this.field_152881_q = null;
               BroadcastController.this.field_152882_r = null;
               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.ReadyToBroadcast);

               try {
                  if (BroadcastController.this.field_152867_c != null) {
                     BroadcastController.this.field_152867_c.func_152892_c(p_startCallback_1_);
                  }
               } catch (Exception var3) {
                  BroadcastController.this.func_152820_d(var3.toString());
               }

               String lvt_2_3_ = ErrorCode.getString(p_startCallback_1_);
               BroadcastController.this.func_152820_d(String.format("startCallback got failure: %s", lvt_2_3_));
            }

         }

         public void stopCallback(ErrorCode p_stopCallback_1_) {
            if (ErrorCode.succeeded(p_stopCallback_1_)) {
               BroadcastController.this.field_152881_q = null;
               BroadcastController.this.field_152882_r = null;
               BroadcastController.this.func_152831_M();

               try {
                  if (BroadcastController.this.field_152867_c != null) {
                     BroadcastController.this.field_152867_c.func_152901_c();
                  }
               } catch (Exception var3) {
                  BroadcastController.this.func_152820_d(var3.toString());
               }

               if (BroadcastController.this.field_152877_m) {
                  BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.ReadyToBroadcast);
               } else {
                  BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Initialized);
               }
            } else {
               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.ReadyToBroadcast);
               String lvt_2_2_ = ErrorCode.getString(p_stopCallback_1_);
               BroadcastController.this.func_152820_d(String.format("stopCallback got failure: %s", lvt_2_2_));
            }

         }

         public void sendActionMetaDataCallback(ErrorCode p_sendActionMetaDataCallback_1_) {
            if (ErrorCode.failed(p_sendActionMetaDataCallback_1_)) {
               String lvt_2_1_ = ErrorCode.getString(p_sendActionMetaDataCallback_1_);
               BroadcastController.this.func_152820_d(String.format("sendActionMetaDataCallback got failure: %s", lvt_2_1_));
            }

         }

         public void sendStartSpanMetaDataCallback(ErrorCode p_sendStartSpanMetaDataCallback_1_) {
            if (ErrorCode.failed(p_sendStartSpanMetaDataCallback_1_)) {
               String lvt_2_1_ = ErrorCode.getString(p_sendStartSpanMetaDataCallback_1_);
               BroadcastController.this.func_152820_d(String.format("sendStartSpanMetaDataCallback got failure: %s", lvt_2_1_));
            }

         }

         public void sendEndSpanMetaDataCallback(ErrorCode p_sendEndSpanMetaDataCallback_1_) {
            if (ErrorCode.failed(p_sendEndSpanMetaDataCallback_1_)) {
               String lvt_2_1_ = ErrorCode.getString(p_sendEndSpanMetaDataCallback_1_);
               BroadcastController.this.func_152820_d(String.format("sendEndSpanMetaDataCallback got failure: %s", lvt_2_1_));
            }

         }
      };
      this.field_177949_C = new IStatCallbacks() {
         public void statCallback(StatType p_statCallback_1_, long p_statCallback_2_) {
         }
      };
      this.field_152872_h = Core.getInstance();
      if (Core.getInstance() == null) {
         this.field_152872_h = new Core(new StandardCoreAPI());
      }

      this.field_152873_i = new Stream(new DesktopStreamAPI());
   }

   protected PixelFormat func_152826_z() {
      return PixelFormat.TTV_PF_RGBA;
   }

   public boolean func_152817_A() {
      if (this.field_152876_l) {
         return false;
      } else {
         this.field_152873_i.setStreamCallbacks(this.field_177948_B);
         ErrorCode lvt_1_1_ = this.field_152872_h.initialize(this.field_152868_d, System.getProperty("java.library.path"));
         if (!this.func_152853_a(lvt_1_1_)) {
            this.field_152873_i.setStreamCallbacks((IStreamCallbacks)null);
            this.field_152864_E = lvt_1_1_;
            return false;
         } else {
            lvt_1_1_ = this.field_152872_h.setTraceLevel(MessageLevel.TTV_ML_ERROR);
            if (!this.func_152853_a(lvt_1_1_)) {
               this.field_152873_i.setStreamCallbacks((IStreamCallbacks)null);
               this.field_152872_h.shutdown();
               this.field_152864_E = lvt_1_1_;
               return false;
            } else if (ErrorCode.succeeded(lvt_1_1_)) {
               this.field_152876_l = true;
               this.func_152827_a(BroadcastController.BroadcastState.Initialized);
               return true;
            } else {
               this.field_152864_E = lvt_1_1_;
               this.field_152872_h.shutdown();
               return false;
            }
         }
      }
   }

   public boolean func_152851_B() {
      if (!this.field_152876_l) {
         return true;
      } else if (this.func_152825_o()) {
         return false;
      } else {
         this.field_152878_n = true;
         this.func_152845_C();
         this.field_152873_i.setStreamCallbacks((IStreamCallbacks)null);
         this.field_152873_i.setStatCallbacks((IStatCallbacks)null);
         ErrorCode lvt_1_1_ = this.field_152872_h.shutdown();
         this.func_152853_a(lvt_1_1_);
         this.field_152876_l = false;
         this.field_152878_n = false;
         this.func_152827_a(BroadcastController.BroadcastState.Uninitialized);
         return true;
      }
   }

   public void statCallback() {
      if (this.field_152879_o != BroadcastController.BroadcastState.Uninitialized) {
         if (this.field_152860_A != null) {
            this.field_152860_A.func_153039_l();
         }

         for(; this.field_152860_A != null; this.func_152821_H()) {
            try {
               Thread.sleep(200L);
            } catch (Exception var2) {
               this.func_152820_d(var2.toString());
            }
         }

         this.func_152851_B();
      }

   }

   public boolean func_152818_a(String p_152818_1_, AuthToken p_152818_2_) {
      if (this.func_152825_o()) {
         return false;
      } else {
         this.func_152845_C();
         if (p_152818_1_ != null && !p_152818_1_.isEmpty()) {
            if (p_152818_2_ != null && p_152818_2_.data != null && !p_152818_2_.data.isEmpty()) {
               this.field_152880_p = p_152818_1_;
               this.field_152885_u = p_152818_2_;
               if (this.func_152858_b()) {
                  this.func_152827_a(BroadcastController.BroadcastState.Authenticated);
               }

               return true;
            } else {
               this.func_152820_d("Auth token must be valid");
               return false;
            }
         } else {
            this.func_152820_d("Username must be valid");
            return false;
         }
      }
   }

   public boolean func_152845_C() {
      if (this.func_152825_o()) {
         return false;
      } else {
         if (this.func_152850_m()) {
            this.field_152873_i.stop(false);
         }

         this.field_152880_p = "";
         this.field_152885_u = new AuthToken();
         if (!this.field_152877_m) {
            return false;
         } else {
            this.field_152877_m = false;
            if (!this.field_152878_n) {
               try {
                  if (this.field_152867_c != null) {
                     this.field_152867_c.func_152895_a();
                  }
               } catch (Exception var2) {
                  this.func_152820_d(var2.toString());
               }
            }

            this.func_152827_a(BroadcastController.BroadcastState.Initialized);
            return true;
         }
      }
   }

   public boolean func_152828_a(String p_152828_1_, String p_152828_2_, String p_152828_3_) {
      if (!this.field_152877_m) {
         return false;
      } else {
         if (p_152828_1_ == null || p_152828_1_.equals("")) {
            p_152828_1_ = this.field_152880_p;
         }

         if (p_152828_2_ == null) {
            p_152828_2_ = "";
         }

         if (p_152828_3_ == null) {
            p_152828_3_ = "";
         }

         StreamInfoForSetting lvt_4_1_ = new StreamInfoForSetting();
         lvt_4_1_.streamTitle = p_152828_3_;
         lvt_4_1_.gameName = p_152828_2_;
         ErrorCode lvt_5_1_ = this.field_152873_i.setStreamInfo(this.field_152885_u, p_152828_1_, lvt_4_1_);
         this.func_152853_a(lvt_5_1_);
         return ErrorCode.succeeded(lvt_5_1_);
      }
   }

   public boolean func_152830_D() {
      if (!this.func_152850_m()) {
         return false;
      } else {
         ErrorCode lvt_1_1_ = this.field_152873_i.runCommercial(this.field_152885_u);
         this.func_152853_a(lvt_1_1_);
         return ErrorCode.succeeded(lvt_1_1_);
      }
   }

   public VideoParams func_152834_a(int p_152834_1_, int p_152834_2_, float p_152834_3_, float p_152834_4_) {
      int[] lvt_5_1_ = this.field_152873_i.getMaxResolution(p_152834_1_, p_152834_2_, p_152834_3_, p_152834_4_);
      VideoParams lvt_6_1_ = new VideoParams();
      lvt_6_1_.maxKbps = p_152834_1_;
      lvt_6_1_.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
      lvt_6_1_.pixelFormat = this.func_152826_z();
      lvt_6_1_.targetFps = p_152834_2_;
      lvt_6_1_.outputWidth = lvt_5_1_[0];
      lvt_6_1_.outputHeight = lvt_5_1_[1];
      lvt_6_1_.disableAdaptiveBitrate = false;
      lvt_6_1_.verticalFlip = false;
      return lvt_6_1_;
   }

   public boolean func_152836_a(VideoParams p_152836_1_) {
      if (p_152836_1_ != null && this.func_152857_n()) {
         this.field_152881_q = p_152836_1_.clone();
         this.field_152882_r = new AudioParams();
         this.field_152882_r.audioEnabled = this.field_152871_g && this.func_152848_y();
         this.field_152882_r.enableMicCapture = this.field_152882_r.audioEnabled;
         this.field_152882_r.enablePlaybackCapture = this.field_152882_r.audioEnabled;
         this.field_152882_r.enablePassthroughAudio = false;
         if (!this.func_152823_L()) {
            this.field_152881_q = null;
            this.field_152882_r = null;
            return false;
         } else {
            ErrorCode lvt_2_1_ = this.field_152873_i.start(p_152836_1_, this.field_152882_r, this.field_152884_t, StartFlags.None, true);
            if (ErrorCode.failed(lvt_2_1_)) {
               this.func_152831_M();
               String lvt_3_1_ = ErrorCode.getString(lvt_2_1_);
               this.func_152820_d(String.format("Error while starting to broadcast: %s", lvt_3_1_));
               this.field_152881_q = null;
               this.field_152882_r = null;
               return false;
            } else {
               this.func_152827_a(BroadcastController.BroadcastState.Starting);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   public boolean func_152819_E() {
      if (!this.func_152850_m()) {
         return false;
      } else {
         ErrorCode lvt_1_1_ = this.field_152873_i.stop(true);
         if (ErrorCode.failed(lvt_1_1_)) {
            String lvt_2_1_ = ErrorCode.getString(lvt_1_1_);
            this.func_152820_d(String.format("Error while stopping the broadcast: %s", lvt_2_1_));
            return false;
         } else {
            this.func_152827_a(BroadcastController.BroadcastState.Stopping);
            return ErrorCode.succeeded(lvt_1_1_);
         }
      }
   }

   public boolean func_152847_F() {
      if (!this.func_152850_m()) {
         return false;
      } else {
         ErrorCode lvt_1_1_ = this.field_152873_i.pauseVideo();
         if (ErrorCode.failed(lvt_1_1_)) {
            this.func_152819_E();
            String lvt_2_1_ = ErrorCode.getString(lvt_1_1_);
            this.func_152820_d(String.format("Error pausing stream: %s\n", lvt_2_1_));
         } else {
            this.func_152827_a(BroadcastController.BroadcastState.Paused);
         }

         return ErrorCode.succeeded(lvt_1_1_);
      }
   }

   public boolean func_152854_G() {
      if (!this.func_152839_p()) {
         return false;
      } else {
         this.func_152827_a(BroadcastController.BroadcastState.Broadcasting);
         return true;
      }
   }

   public boolean func_152840_a(String p_152840_1_, long p_152840_2_, String p_152840_4_, String p_152840_5_) {
      ErrorCode lvt_6_1_ = this.field_152873_i.sendActionMetaData(this.field_152885_u, p_152840_1_, p_152840_2_, p_152840_4_, p_152840_5_);
      if (ErrorCode.failed(lvt_6_1_)) {
         String lvt_7_1_ = ErrorCode.getString(lvt_6_1_);
         this.func_152820_d(String.format("Error while sending meta data: %s\n", lvt_7_1_));
         return false;
      } else {
         return true;
      }
   }

   public long func_177946_b(String p_177946_1_, long p_177946_2_, String p_177946_4_, String p_177946_5_) {
      long lvt_6_1_ = this.field_152873_i.sendStartSpanMetaData(this.field_152885_u, p_177946_1_, p_177946_2_, p_177946_4_, p_177946_5_);
      if (lvt_6_1_ == -1L) {
         this.func_152820_d(String.format("Error in SendStartSpanMetaData\n"));
      }

      return lvt_6_1_;
   }

   public boolean func_177947_a(String p_177947_1_, long p_177947_2_, long p_177947_4_, String p_177947_6_, String p_177947_7_) {
      if (p_177947_4_ == -1L) {
         this.func_152820_d(String.format("Invalid sequence id: %d\n", p_177947_4_));
         return false;
      } else {
         ErrorCode lvt_8_1_ = this.field_152873_i.sendEndSpanMetaData(this.field_152885_u, p_177947_1_, p_177947_2_, p_177947_4_, p_177947_6_, p_177947_7_);
         if (ErrorCode.failed(lvt_8_1_)) {
            String lvt_9_1_ = ErrorCode.getString(lvt_8_1_);
            this.func_152820_d(String.format("Error in SendStopSpanMetaData: %s\n", lvt_9_1_));
            return false;
         } else {
            return true;
         }
      }
   }

   protected void func_152827_a(BroadcastController.BroadcastState p_152827_1_) {
      if (p_152827_1_ != this.field_152879_o) {
         this.field_152879_o = p_152827_1_;

         try {
            if (this.field_152867_c != null) {
               this.field_152867_c.func_152891_a(p_152827_1_);
            }
         } catch (Exception var3) {
            this.func_152820_d(var3.toString());
         }

      }
   }

   public void func_152821_H() {
      if (this.field_152873_i != null && this.field_152876_l) {
         ErrorCode lvt_1_1_ = this.field_152873_i.pollTasks();
         this.func_152853_a(lvt_1_1_);
         if (this.func_152825_o()) {
            this.field_152860_A.func_153041_j();
            if (this.field_152860_A.func_153032_e()) {
               this.field_152860_A = null;
               this.func_152827_a(BroadcastController.BroadcastState.ReadyToBroadcast);
            }
         }

         String lvt_2_4_;
         switch(this.field_152879_o) {
         case Authenticated:
            this.func_152827_a(BroadcastController.BroadcastState.LoggingIn);
            lvt_1_1_ = this.field_152873_i.login(this.field_152885_u);
            if (ErrorCode.failed(lvt_1_1_)) {
               lvt_2_4_ = ErrorCode.getString(lvt_1_1_);
               this.func_152820_d(String.format("Error in TTV_Login: %s\n", lvt_2_4_));
            }
            break;
         case LoggedIn:
            this.func_152827_a(BroadcastController.BroadcastState.FindingIngestServer);
            lvt_1_1_ = this.field_152873_i.getIngestServers(this.field_152885_u);
            if (ErrorCode.failed(lvt_1_1_)) {
               this.func_152827_a(BroadcastController.BroadcastState.LoggedIn);
               lvt_2_4_ = ErrorCode.getString(lvt_1_1_);
               this.func_152820_d(String.format("Error in TTV_GetIngestServers: %s\n", lvt_2_4_));
            }
            break;
         case ReceivedIngestServers:
            this.func_152827_a(BroadcastController.BroadcastState.ReadyToBroadcast);
            lvt_1_1_ = this.field_152873_i.getUserInfo(this.field_152885_u);
            if (ErrorCode.failed(lvt_1_1_)) {
               lvt_2_4_ = ErrorCode.getString(lvt_1_1_);
               this.func_152820_d(String.format("Error in TTV_GetUserInfo: %s\n", lvt_2_4_));
            }

            this.func_152835_I();
            lvt_1_1_ = this.field_152873_i.getArchivingState(this.field_152885_u);
            if (ErrorCode.failed(lvt_1_1_)) {
               lvt_2_4_ = ErrorCode.getString(lvt_1_1_);
               this.func_152820_d(String.format("Error in TTV_GetArchivingState: %s\n", lvt_2_4_));
            }
         case Starting:
         case Stopping:
         case FindingIngestServer:
         case Authenticating:
         case Initialized:
         case Uninitialized:
         case IngestTesting:
         default:
            break;
         case Paused:
         case Broadcasting:
            this.func_152835_I();
         }

      }
   }

   protected void func_152835_I() {
      long lvt_1_1_ = System.nanoTime();
      long lvt_3_1_ = (lvt_1_1_ - this.field_152890_z) / 1000000000L;
      if (lvt_3_1_ >= 30L) {
         this.field_152890_z = lvt_1_1_;
         ErrorCode lvt_5_1_ = this.field_152873_i.getStreamInfo(this.field_152885_u, this.field_152880_p);
         if (ErrorCode.failed(lvt_5_1_)) {
            String lvt_6_1_ = ErrorCode.getString(lvt_5_1_);
            this.func_152820_d(String.format("Error in TTV_GetStreamInfo: %s", lvt_6_1_));
         }

      }
   }

   public IngestServerTester func_152838_J() {
      if (this.func_152857_n() && this.field_152883_s != null) {
         if (this.func_152825_o()) {
            return null;
         } else {
            this.field_152860_A = new IngestServerTester(this.field_152873_i, this.field_152883_s);
            this.field_152860_A.func_176004_j();
            this.func_152827_a(BroadcastController.BroadcastState.IngestTesting);
            return this.field_152860_A;
         }
      } else {
         return null;
      }
   }

   protected boolean func_152823_L() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < 3; ++lvt_1_1_) {
         FrameBuffer lvt_2_1_ = this.field_152873_i.allocateFrameBuffer(this.field_152881_q.outputWidth * this.field_152881_q.outputHeight * 4);
         if (!lvt_2_1_.getIsValid()) {
            this.func_152820_d(String.format("Error while allocating frame buffer"));
            return false;
         }

         this.field_152874_j.add(lvt_2_1_);
         this.field_152875_k.add(lvt_2_1_);
      }

      return true;
   }

   protected void func_152831_M() {
      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_152874_j.size(); ++lvt_1_1_) {
         FrameBuffer lvt_2_1_ = (FrameBuffer)this.field_152874_j.get(lvt_1_1_);
         lvt_2_1_.free();
      }

      this.field_152875_k.clear();
      this.field_152874_j.clear();
   }

   public FrameBuffer func_152822_N() {
      if (this.field_152875_k.size() == 0) {
         this.func_152820_d(String.format("Out of free buffers, this should never happen"));
         return null;
      } else {
         FrameBuffer lvt_1_1_ = (FrameBuffer)this.field_152875_k.get(this.field_152875_k.size() - 1);
         this.field_152875_k.remove(this.field_152875_k.size() - 1);
         return lvt_1_1_;
      }
   }

   public void func_152846_a(FrameBuffer p_152846_1_) {
      try {
         this.field_152873_i.captureFrameBuffer_ReadPixels(p_152846_1_);
      } catch (Throwable var5) {
         CrashReport lvt_3_1_ = CrashReport.func_85055_a(var5, "Trying to submit a frame to Twitch");
         CrashReportCategory lvt_4_1_ = lvt_3_1_.func_85058_a("Broadcast State");
         lvt_4_1_.func_71507_a("Last reported errors", Arrays.toString(field_152862_C.func_152756_c()));
         lvt_4_1_.func_71507_a("Buffer", p_152846_1_);
         lvt_4_1_.func_71507_a("Free buffer count", this.field_152875_k.size());
         lvt_4_1_.func_71507_a("Capture buffer count", this.field_152874_j.size());
         throw new ReportedException(lvt_3_1_);
      }
   }

   public ErrorCode func_152859_b(FrameBuffer p_152859_1_) {
      if (this.func_152839_p()) {
         this.func_152854_G();
      } else if (!this.func_152850_m()) {
         return ErrorCode.TTV_EC_STREAM_NOT_STARTED;
      }

      ErrorCode lvt_2_1_ = this.field_152873_i.submitVideoFrame(p_152859_1_);
      if (lvt_2_1_ != ErrorCode.TTV_EC_SUCCESS) {
         String lvt_3_1_ = ErrorCode.getString(lvt_2_1_);
         if (ErrorCode.succeeded(lvt_2_1_)) {
            this.func_152832_e(String.format("Warning in SubmitTexturePointer: %s\n", lvt_3_1_));
         } else {
            this.func_152820_d(String.format("Error in SubmitTexturePointer: %s\n", lvt_3_1_));
            this.func_152819_E();
         }

         if (this.field_152867_c != null) {
            this.field_152867_c.func_152893_b(lvt_2_1_);
         }
      }

      return lvt_2_1_;
   }

   protected boolean func_152853_a(ErrorCode p_152853_1_) {
      if (ErrorCode.failed(p_152853_1_)) {
         this.func_152820_d(ErrorCode.getString(p_152853_1_));
         return false;
      } else {
         return true;
      }
   }

   protected void func_152820_d(String p_152820_1_) {
      this.field_152863_D = p_152820_1_;
      field_152862_C.func_152757_a("<Error> " + p_152820_1_);
      field_152861_B.error(TwitchStream.field_152949_a, "[Broadcast controller] {}", p_152820_1_);
   }

   protected void func_152832_e(String p_152832_1_) {
      field_152862_C.func_152757_a("<Warning> " + p_152832_1_);
      field_152861_B.warn(TwitchStream.field_152949_a, "[Broadcast controller] {}", p_152832_1_);
   }

   public interface BroadcastListener {
      void func_152900_a(ErrorCode var1, AuthToken var2);

      void func_152897_a(ErrorCode var1);

      void func_152898_a(ErrorCode var1, GameInfo[] var2);

      void func_152891_a(BroadcastController.BroadcastState var1);

      void func_152895_a();

      void func_152894_a(StreamInfo var1);

      void func_152896_a(IngestList var1);

      void func_152893_b(ErrorCode var1);

      void func_152899_b();

      void func_152901_c();

      void func_152892_c(ErrorCode var1);
   }

   public static enum BroadcastState {
      Uninitialized,
      Initialized,
      Authenticating,
      Authenticated,
      LoggingIn,
      LoggedIn,
      FindingIngestServer,
      ReceivedIngestServers,
      ReadyToBroadcast,
      Starting,
      Broadcasting,
      Stopping,
      Paused,
      IngestTesting;
   }
}
