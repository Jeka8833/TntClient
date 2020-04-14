package net.minecraft.server;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetworkSystem;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.profiler.IPlayerUsage;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Util;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MinecraftServer implements Runnable, ICommandSender, IThreadListener, IPlayerUsage {
   private static final Logger field_147145_h = LogManager.getLogger();
   public static final File field_152367_a = new File("usercache.json");
   private static MinecraftServer field_71309_l;
   private final ISaveFormat field_71310_m;
   private final PlayerUsageSnooper field_71307_n = new PlayerUsageSnooper("server", this, func_130071_aq());
   private final File field_71308_o;
   private final List<ITickable> field_71322_p = Lists.newArrayList();
   protected final ICommandManager field_71321_q;
   public final Profiler field_71304_b = new Profiler();
   private final NetworkSystem field_147144_o;
   private final ServerStatusResponse field_147147_p = new ServerStatusResponse();
   private final Random field_147146_q = new Random();
   private int field_71319_s = -1;
   public WorldServer[] field_71305_c;
   private ServerConfigurationManager field_71318_t;
   private boolean field_71317_u = true;
   private boolean field_71316_v;
   private int field_71315_w;
   protected final Proxy field_110456_c;
   public String field_71302_d;
   public int field_71303_e;
   private boolean field_71325_x;
   private boolean field_71324_y;
   private boolean field_71323_z;
   private boolean field_71284_A;
   private boolean field_71285_B;
   private String field_71286_C;
   private int field_71280_D;
   private int field_143008_E = 0;
   public final long[] field_71311_j = new long[100];
   public long[][] field_71312_k;
   private KeyPair field_71292_I;
   private String field_71293_J;
   private String field_71294_K;
   private String field_71287_L;
   private boolean field_71288_M;
   private boolean field_71289_N;
   private boolean field_71290_O;
   private String field_147141_M = "";
   private String field_175588_P = "";
   private boolean field_71296_Q;
   private long field_71299_R;
   private String field_71298_S;
   private boolean field_71295_T;
   private boolean field_104057_T;
   private final YggdrasilAuthenticationService field_152364_T;
   private final MinecraftSessionService field_147143_S;
   private long field_147142_T = 0L;
   private final GameProfileRepository field_152365_W;
   private final PlayerProfileCache field_152366_X;
   protected final Queue<FutureTask<?>> field_175589_i = Queues.newArrayDeque();
   private Thread field_175590_aa;
   private long field_175591_ab = func_130071_aq();

   public MinecraftServer(Proxy p_i46053_1_, File p_i46053_2_) {
      this.field_110456_c = p_i46053_1_;
      field_71309_l = this;
      this.field_71308_o = null;
      this.field_147144_o = null;
      this.field_152366_X = new PlayerProfileCache(this, p_i46053_2_);
      this.field_71321_q = null;
      this.field_71310_m = null;
      this.field_152364_T = new YggdrasilAuthenticationService(p_i46053_1_, UUID.randomUUID().toString());
      this.field_147143_S = this.field_152364_T.createMinecraftSessionService();
      this.field_152365_W = this.field_152364_T.createProfileRepository();
   }

   public MinecraftServer(File p_i46054_1_, Proxy p_i46054_2_, File p_i46054_3_) {
      this.field_110456_c = p_i46054_2_;
      field_71309_l = this;
      this.field_71308_o = p_i46054_1_;
      this.field_147144_o = new NetworkSystem(this);
      this.field_152366_X = new PlayerProfileCache(this, p_i46054_3_);
      this.field_71321_q = this.func_175582_h();
      this.field_71310_m = new AnvilSaveConverter(p_i46054_1_);
      this.field_152364_T = new YggdrasilAuthenticationService(p_i46054_2_, UUID.randomUUID().toString());
      this.field_147143_S = this.field_152364_T.createMinecraftSessionService();
      this.field_152365_W = this.field_152364_T.createProfileRepository();
   }

   protected ServerCommandManager func_175582_h() {
      return new ServerCommandManager();
   }

   protected abstract boolean func_71197_b() throws IOException;

   protected void func_71237_c(String p_71237_1_) {
      if (this.func_71254_M().func_75801_b(p_71237_1_)) {
         field_147145_h.info("Converting map!");
         this.func_71192_d("menu.convertingLevel");
         this.func_71254_M().func_75805_a(p_71237_1_, new IProgressUpdate() {
            private long field_96245_b = MinecraftServer.func_130071_aq();

            public void func_73720_a(String p_73720_1_) {
            }

            public void func_73721_b(String p_73721_1_) {
            }

            public void func_73718_a(int p_73718_1_) {
               if (MinecraftServer.func_130071_aq() - this.field_96245_b >= 1000L) {
                  this.field_96245_b = MinecraftServer.func_130071_aq();
                  MinecraftServer.field_147145_h.info("Converting... " + p_73718_1_ + "%");
               }

            }

            public void func_146586_a() {
            }

            public void func_73719_c(String p_73719_1_) {
            }
         });
      }

   }

   protected synchronized void func_71192_d(String p_71192_1_) {
      this.field_71298_S = p_71192_1_;
   }

   public synchronized String func_71195_b_() {
      return this.field_71298_S;
   }

   protected void func_71247_a(String p_71247_1_, String p_71247_2_, long p_71247_3_, WorldType p_71247_5_, String p_71247_6_) {
      this.func_71237_c(p_71247_1_);
      this.func_71192_d("menu.loadingLevel");
      this.field_71305_c = new WorldServer[3];
      this.field_71312_k = new long[this.field_71305_c.length][100];
      ISaveHandler lvt_7_1_ = this.field_71310_m.func_75804_a(p_71247_1_, true);
      this.func_175584_a(this.func_71270_I(), lvt_7_1_);
      WorldInfo lvt_9_1_ = lvt_7_1_.func_75757_d();
      WorldSettings lvt_8_3_;
      if (lvt_9_1_ == null) {
         if (this.func_71242_L()) {
            lvt_8_3_ = DemoWorldServer.field_73071_a;
         } else {
            lvt_8_3_ = new WorldSettings(p_71247_3_, this.func_71265_f(), this.func_71225_e(), this.func_71199_h(), p_71247_5_);
            lvt_8_3_.func_82750_a(p_71247_6_);
            if (this.field_71289_N) {
               lvt_8_3_.func_77159_a();
            }
         }

         lvt_9_1_ = new WorldInfo(lvt_8_3_, p_71247_2_);
      } else {
         lvt_9_1_.func_76062_a(p_71247_2_);
         lvt_8_3_ = new WorldSettings(lvt_9_1_);
      }

      for(int lvt_10_1_ = 0; lvt_10_1_ < this.field_71305_c.length; ++lvt_10_1_) {
         int lvt_11_1_ = 0;
         if (lvt_10_1_ == 1) {
            lvt_11_1_ = -1;
         }

         if (lvt_10_1_ == 2) {
            lvt_11_1_ = 1;
         }

         if (lvt_10_1_ == 0) {
            if (this.func_71242_L()) {
               this.field_71305_c[lvt_10_1_] = (WorldServer)(new DemoWorldServer(this, lvt_7_1_, lvt_9_1_, lvt_11_1_, this.field_71304_b)).func_175643_b();
            } else {
               this.field_71305_c[lvt_10_1_] = (WorldServer)(new WorldServer(this, lvt_7_1_, lvt_9_1_, lvt_11_1_, this.field_71304_b)).func_175643_b();
            }

            this.field_71305_c[lvt_10_1_].func_72963_a(lvt_8_3_);
         } else {
            this.field_71305_c[lvt_10_1_] = (WorldServer)(new WorldServerMulti(this, lvt_7_1_, lvt_11_1_, this.field_71305_c[0], this.field_71304_b)).func_175643_b();
         }

         this.field_71305_c[lvt_10_1_].func_72954_a(new WorldManager(this, this.field_71305_c[lvt_10_1_]));
         if (!this.func_71264_H()) {
            this.field_71305_c[lvt_10_1_].func_72912_H().func_76060_a(this.func_71265_f());
         }
      }

      this.field_71318_t.func_72364_a(this.field_71305_c);
      this.func_147139_a(this.func_147135_j());
      this.func_71222_d();
   }

   protected void func_71222_d() {
      int lvt_1_1_ = true;
      int lvt_2_1_ = true;
      int lvt_3_1_ = true;
      int lvt_4_1_ = true;
      int lvt_5_1_ = 0;
      this.func_71192_d("menu.generatingTerrain");
      int lvt_6_1_ = 0;
      field_147145_h.info("Preparing start region for level " + lvt_6_1_);
      WorldServer lvt_7_1_ = this.field_71305_c[lvt_6_1_];
      BlockPos lvt_8_1_ = lvt_7_1_.func_175694_M();
      long lvt_9_1_ = func_130071_aq();

      for(int lvt_11_1_ = -192; lvt_11_1_ <= 192 && this.func_71278_l(); lvt_11_1_ += 16) {
         for(int lvt_12_1_ = -192; lvt_12_1_ <= 192 && this.func_71278_l(); lvt_12_1_ += 16) {
            long lvt_13_1_ = func_130071_aq();
            if (lvt_13_1_ - lvt_9_1_ > 1000L) {
               this.func_71216_a_("Preparing spawn area", lvt_5_1_ * 100 / 625);
               lvt_9_1_ = lvt_13_1_;
            }

            ++lvt_5_1_;
            lvt_7_1_.field_73059_b.func_73158_c(lvt_8_1_.func_177958_n() + lvt_11_1_ >> 4, lvt_8_1_.func_177952_p() + lvt_12_1_ >> 4);
         }
      }

      this.func_71243_i();
   }

   protected void func_175584_a(String p_175584_1_, ISaveHandler p_175584_2_) {
      File lvt_3_1_ = new File(p_175584_2_.func_75765_b(), "resources.zip");
      if (lvt_3_1_.isFile()) {
         this.func_180507_a_("level://" + p_175584_1_ + "/" + lvt_3_1_.getName(), "");
      }

   }

   public abstract boolean func_71225_e();

   public abstract WorldSettings.GameType func_71265_f();

   public abstract EnumDifficulty func_147135_j();

   public abstract boolean func_71199_h();

   public abstract int func_110455_j();

   public abstract boolean func_181034_q();

   public abstract boolean func_183002_r();

   protected void func_71216_a_(String p_71216_1_, int p_71216_2_) {
      this.field_71302_d = p_71216_1_;
      this.field_71303_e = p_71216_2_;
      field_147145_h.info(p_71216_1_ + ": " + p_71216_2_ + "%");
   }

   protected void func_71243_i() {
      this.field_71302_d = null;
      this.field_71303_e = 0;
   }

   protected void func_71267_a(boolean p_71267_1_) {
      if (!this.field_71290_O) {
         WorldServer[] lvt_2_1_ = this.field_71305_c;
         int lvt_3_1_ = lvt_2_1_.length;

         for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
            WorldServer lvt_5_1_ = lvt_2_1_[lvt_4_1_];
            if (lvt_5_1_ != null) {
               if (!p_71267_1_) {
                  field_147145_h.info("Saving chunks for level '" + lvt_5_1_.func_72912_H().func_76065_j() + "'/" + lvt_5_1_.field_73011_w.func_80007_l());
               }

               try {
                  lvt_5_1_.func_73044_a(true, (IProgressUpdate)null);
               } catch (MinecraftException var7) {
                  field_147145_h.warn(var7.getMessage());
               }
            }
         }

      }
   }

   public void func_71260_j() {
      if (!this.field_71290_O) {
         field_147145_h.info("Stopping server");
         if (this.func_147137_ag() != null) {
            this.func_147137_ag().func_151268_b();
         }

         if (this.field_71318_t != null) {
            field_147145_h.info("Saving players");
            this.field_71318_t.func_72389_g();
            this.field_71318_t.func_72392_r();
         }

         if (this.field_71305_c != null) {
            field_147145_h.info("Saving worlds");
            this.func_71267_a(false);

            for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_71305_c.length; ++lvt_1_1_) {
               WorldServer lvt_2_1_ = this.field_71305_c[lvt_1_1_];
               lvt_2_1_.func_73041_k();
            }
         }

         if (this.field_71307_n.func_76468_d()) {
            this.field_71307_n.func_76470_e();
         }

      }
   }

   public boolean func_71278_l() {
      return this.field_71317_u;
   }

   public void func_71263_m() {
      this.field_71317_u = false;
   }

   protected void func_175585_v() {
      field_71309_l = this;
   }

   public void run() {
      try {
         if (this.func_71197_b()) {
            this.field_175591_ab = func_130071_aq();
            long lvt_1_1_ = 0L;
            this.field_147147_p.func_151315_a(new ChatComponentText(this.field_71286_C));
            this.field_147147_p.func_151321_a(new ServerStatusResponse.MinecraftProtocolVersionIdentifier("1.8.9", 47));
            this.func_147138_a(this.field_147147_p);

            while(this.field_71317_u) {
               long lvt_3_1_ = func_130071_aq();
               long lvt_5_1_ = lvt_3_1_ - this.field_175591_ab;
               if (lvt_5_1_ > 2000L && this.field_175591_ab - this.field_71299_R >= 15000L) {
                  field_147145_h.warn("Can't keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)", lvt_5_1_, lvt_5_1_ / 50L);
                  lvt_5_1_ = 2000L;
                  this.field_71299_R = this.field_175591_ab;
               }

               if (lvt_5_1_ < 0L) {
                  field_147145_h.warn("Time ran backwards! Did the system time change?");
                  lvt_5_1_ = 0L;
               }

               lvt_1_1_ += lvt_5_1_;
               this.field_175591_ab = lvt_3_1_;
               if (this.field_71305_c[0].func_73056_e()) {
                  this.func_71217_p();
                  lvt_1_1_ = 0L;
               } else {
                  while(lvt_1_1_ > 50L) {
                     lvt_1_1_ -= 50L;
                     this.func_71217_p();
                  }
               }

               Thread.sleep(Math.max(1L, 50L - lvt_1_1_));
               this.field_71296_Q = true;
            }
         } else {
            this.func_71228_a((CrashReport)null);
         }
      } catch (Throwable var46) {
         field_147145_h.error("Encountered an unexpected exception", var46);
         CrashReport lvt_2_1_ = null;
         if (var46 instanceof ReportedException) {
            lvt_2_1_ = this.func_71230_b(((ReportedException)var46).func_71575_a());
         } else {
            lvt_2_1_ = this.func_71230_b(new CrashReport("Exception in server tick loop", var46));
         }

         File lvt_3_2_ = new File(new File(this.func_71238_n(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");
         if (lvt_2_1_.func_147149_a(lvt_3_2_)) {
            field_147145_h.error("This crash report has been saved to: " + lvt_3_2_.getAbsolutePath());
         } else {
            field_147145_h.error("We were unable to save this crash report to disk.");
         }

         this.func_71228_a(lvt_2_1_);
      } finally {
         try {
            this.field_71316_v = true;
            this.func_71260_j();
         } catch (Throwable var44) {
            field_147145_h.error("Exception stopping the server", var44);
         } finally {
            this.func_71240_o();
         }

      }

   }

   private void func_147138_a(ServerStatusResponse p_147138_1_) {
      File lvt_2_1_ = this.func_71209_f("server-icon.png");
      if (lvt_2_1_.isFile()) {
         ByteBuf lvt_3_1_ = Unpooled.buffer();

         try {
            BufferedImage lvt_4_1_ = ImageIO.read(lvt_2_1_);
            Validate.validState(lvt_4_1_.getWidth() == 64, "Must be 64 pixels wide");
            Validate.validState(lvt_4_1_.getHeight() == 64, "Must be 64 pixels high");
            ImageIO.write(lvt_4_1_, "PNG", new ByteBufOutputStream(lvt_3_1_));
            ByteBuf lvt_5_1_ = Base64.encode(lvt_3_1_);
            p_147138_1_.func_151320_a("data:image/png;base64," + lvt_5_1_.toString(Charsets.UTF_8));
         } catch (Exception var9) {
            field_147145_h.error((String)"Couldn't load server icon", (Throwable)var9);
         } finally {
            lvt_3_1_.release();
         }
      }

   }

   public File func_71238_n() {
      return new File(".");
   }

   protected void func_71228_a(CrashReport p_71228_1_) {
   }

   protected void func_71240_o() {
   }

   public void func_71217_p() {
      long lvt_1_1_ = System.nanoTime();
      ++this.field_71315_w;
      if (this.field_71295_T) {
         this.field_71295_T = false;
         this.field_71304_b.field_76327_a = true;
         this.field_71304_b.func_76317_a();
      }

      this.field_71304_b.func_76320_a("root");
      this.func_71190_q();
      if (lvt_1_1_ - this.field_147142_T >= 5000000000L) {
         this.field_147142_T = lvt_1_1_;
         this.field_147147_p.func_151319_a(new ServerStatusResponse.PlayerCountData(this.func_71275_y(), this.func_71233_x()));
         GameProfile[] lvt_3_1_ = new GameProfile[Math.min(this.func_71233_x(), 12)];
         int lvt_4_1_ = MathHelper.func_76136_a(this.field_147146_q, 0, this.func_71233_x() - lvt_3_1_.length);

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_3_1_.length; ++lvt_5_1_) {
            lvt_3_1_[lvt_5_1_] = ((EntityPlayerMP)this.field_71318_t.func_181057_v().get(lvt_4_1_ + lvt_5_1_)).func_146103_bH();
         }

         Collections.shuffle(Arrays.asList(lvt_3_1_));
         this.field_147147_p.func_151318_b().func_151330_a(lvt_3_1_);
      }

      if (this.field_71315_w % 900 == 0) {
         this.field_71304_b.func_76320_a("save");
         this.field_71318_t.func_72389_g();
         this.func_71267_a(true);
         this.field_71304_b.func_76319_b();
      }

      this.field_71304_b.func_76320_a("tallying");
      this.field_71311_j[this.field_71315_w % 100] = System.nanoTime() - lvt_1_1_;
      this.field_71304_b.func_76319_b();
      this.field_71304_b.func_76320_a("snooper");
      if (!this.field_71307_n.func_76468_d() && this.field_71315_w > 100) {
         this.field_71307_n.func_76463_a();
      }

      if (this.field_71315_w % 6000 == 0) {
         this.field_71307_n.func_76471_b();
      }

      this.field_71304_b.func_76319_b();
      this.field_71304_b.func_76319_b();
   }

   public void func_71190_q() {
      this.field_71304_b.func_76320_a("jobs");
      synchronized(this.field_175589_i) {
         while(!this.field_175589_i.isEmpty()) {
            Util.func_181617_a((FutureTask)this.field_175589_i.poll(), field_147145_h);
         }
      }

      this.field_71304_b.func_76318_c("levels");

      int lvt_1_2_;
      for(lvt_1_2_ = 0; lvt_1_2_ < this.field_71305_c.length; ++lvt_1_2_) {
         long lvt_2_1_ = System.nanoTime();
         if (lvt_1_2_ == 0 || this.func_71255_r()) {
            WorldServer lvt_4_1_ = this.field_71305_c[lvt_1_2_];
            this.field_71304_b.func_76320_a(lvt_4_1_.func_72912_H().func_76065_j());
            if (this.field_71315_w % 20 == 0) {
               this.field_71304_b.func_76320_a("timeSync");
               this.field_71318_t.func_148537_a(new S03PacketTimeUpdate(lvt_4_1_.func_82737_E(), lvt_4_1_.func_72820_D(), lvt_4_1_.func_82736_K().func_82766_b("doDaylightCycle")), lvt_4_1_.field_73011_w.func_177502_q());
               this.field_71304_b.func_76319_b();
            }

            this.field_71304_b.func_76320_a("tick");

            CrashReport lvt_6_2_;
            try {
               lvt_4_1_.func_72835_b();
            } catch (Throwable var8) {
               lvt_6_2_ = CrashReport.func_85055_a(var8, "Exception ticking world");
               lvt_4_1_.func_72914_a(lvt_6_2_);
               throw new ReportedException(lvt_6_2_);
            }

            try {
               lvt_4_1_.func_72939_s();
            } catch (Throwable var7) {
               lvt_6_2_ = CrashReport.func_85055_a(var7, "Exception ticking world entities");
               lvt_4_1_.func_72914_a(lvt_6_2_);
               throw new ReportedException(lvt_6_2_);
            }

            this.field_71304_b.func_76319_b();
            this.field_71304_b.func_76320_a("tracker");
            lvt_4_1_.func_73039_n().func_72788_a();
            this.field_71304_b.func_76319_b();
            this.field_71304_b.func_76319_b();
         }

         this.field_71312_k[lvt_1_2_][this.field_71315_w % 100] = System.nanoTime() - lvt_2_1_;
      }

      this.field_71304_b.func_76318_c("connection");
      this.func_147137_ag().func_151269_c();
      this.field_71304_b.func_76318_c("players");
      this.field_71318_t.func_72374_b();
      this.field_71304_b.func_76318_c("tickables");

      for(lvt_1_2_ = 0; lvt_1_2_ < this.field_71322_p.size(); ++lvt_1_2_) {
         ((ITickable)this.field_71322_p.get(lvt_1_2_)).func_73660_a();
      }

      this.field_71304_b.func_76319_b();
   }

   public boolean func_71255_r() {
      return true;
   }

   public void func_71256_s() {
      this.field_175590_aa = new Thread(this, "Server thread");
      this.field_175590_aa.start();
   }

   public File func_71209_f(String p_71209_1_) {
      return new File(this.func_71238_n(), p_71209_1_);
   }

   public void func_71236_h(String p_71236_1_) {
      field_147145_h.warn(p_71236_1_);
   }

   public WorldServer func_71218_a(int p_71218_1_) {
      if (p_71218_1_ == -1) {
         return this.field_71305_c[1];
      } else {
         return p_71218_1_ == 1 ? this.field_71305_c[2] : this.field_71305_c[0];
      }
   }

   public String func_71249_w() {
      return "1.8.9";
   }

   public int func_71233_x() {
      return this.field_71318_t.func_72394_k();
   }

   public int func_71275_y() {
      return this.field_71318_t.func_72352_l();
   }

   public String[] func_71213_z() {
      return this.field_71318_t.func_72369_d();
   }

   public GameProfile[] func_152357_F() {
      return this.field_71318_t.func_152600_g();
   }

   public String getServerModName() {
      return "vanilla";
   }

   public CrashReport func_71230_b(CrashReport p_71230_1_) {
      p_71230_1_.func_85056_g().func_71500_a("Profiler Position", new Callable<String>() {
         public String call() throws Exception {
            return MinecraftServer.this.field_71304_b.field_76327_a ? MinecraftServer.this.field_71304_b.func_76322_c() : "N/A (disabled)";
         }
      });
      if (this.field_71318_t != null) {
         p_71230_1_.func_85056_g().func_71500_a("Player Count", new Callable<String>() {
            public String call() {
               return MinecraftServer.this.field_71318_t.func_72394_k() + " / " + MinecraftServer.this.field_71318_t.func_72352_l() + "; " + MinecraftServer.this.field_71318_t.func_181057_v();
            }
         });
      }

      return p_71230_1_;
   }

   public List<String> func_180506_a(ICommandSender p_180506_1_, String p_180506_2_, BlockPos p_180506_3_) {
      List<String> lvt_4_1_ = Lists.newArrayList();
      if (p_180506_2_.startsWith("/")) {
         p_180506_2_ = p_180506_2_.substring(1);
         boolean lvt_5_1_ = !p_180506_2_.contains(" ");
         List<String> lvt_6_1_ = this.field_71321_q.func_180524_a(p_180506_1_, p_180506_2_, p_180506_3_);
         if (lvt_6_1_ != null) {
            Iterator lvt_7_1_ = lvt_6_1_.iterator();

            while(lvt_7_1_.hasNext()) {
               String lvt_8_1_ = (String)lvt_7_1_.next();
               if (lvt_5_1_) {
                  lvt_4_1_.add("/" + lvt_8_1_);
               } else {
                  lvt_4_1_.add(lvt_8_1_);
               }
            }
         }

         return lvt_4_1_;
      } else {
         String[] lvt_5_2_ = p_180506_2_.split(" ", -1);
         String lvt_6_2_ = lvt_5_2_[lvt_5_2_.length - 1];
         String[] lvt_7_2_ = this.field_71318_t.func_72369_d();
         int lvt_8_2_ = lvt_7_2_.length;

         for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_2_; ++lvt_9_1_) {
            String lvt_10_1_ = lvt_7_2_[lvt_9_1_];
            if (CommandBase.func_71523_a(lvt_6_2_, lvt_10_1_)) {
               lvt_4_1_.add(lvt_10_1_);
            }
         }

         return lvt_4_1_;
      }
   }

   public static MinecraftServer func_71276_C() {
      return field_71309_l;
   }

   public boolean func_175578_N() {
      return this.field_71308_o != null;
   }

   public String func_70005_c_() {
      return "Server";
   }

   public void func_145747_a(IChatComponent p_145747_1_) {
      field_147145_h.info(p_145747_1_.func_150260_c());
   }

   public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
      return true;
   }

   public ICommandManager func_71187_D() {
      return this.field_71321_q;
   }

   public KeyPair func_71250_E() {
      return this.field_71292_I;
   }

   public String func_71214_G() {
      return this.field_71293_J;
   }

   public void func_71224_l(String p_71224_1_) {
      this.field_71293_J = p_71224_1_;
   }

   public boolean func_71264_H() {
      return this.field_71293_J != null;
   }

   public String func_71270_I() {
      return this.field_71294_K;
   }

   public void func_71261_m(String p_71261_1_) {
      this.field_71294_K = p_71261_1_;
   }

   public void func_71246_n(String p_71246_1_) {
      this.field_71287_L = p_71246_1_;
   }

   public String func_71221_J() {
      return this.field_71287_L;
   }

   public void func_71253_a(KeyPair p_71253_1_) {
      this.field_71292_I = p_71253_1_;
   }

   public void func_147139_a(EnumDifficulty p_147139_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_71305_c.length; ++lvt_2_1_) {
         World lvt_3_1_ = this.field_71305_c[lvt_2_1_];
         if (lvt_3_1_ != null) {
            if (lvt_3_1_.func_72912_H().func_76093_s()) {
               lvt_3_1_.func_72912_H().func_176144_a(EnumDifficulty.HARD);
               lvt_3_1_.func_72891_a(true, true);
            } else if (this.func_71264_H()) {
               lvt_3_1_.func_72912_H().func_176144_a(p_147139_1_);
               lvt_3_1_.func_72891_a(lvt_3_1_.func_175659_aa() != EnumDifficulty.PEACEFUL, true);
            } else {
               lvt_3_1_.func_72912_H().func_176144_a(p_147139_1_);
               lvt_3_1_.func_72891_a(this.func_71193_K(), this.field_71324_y);
            }
         }
      }

   }

   protected boolean func_71193_K() {
      return true;
   }

   public boolean func_71242_L() {
      return this.field_71288_M;
   }

   public void func_71204_b(boolean p_71204_1_) {
      this.field_71288_M = p_71204_1_;
   }

   public void func_71194_c(boolean p_71194_1_) {
      this.field_71289_N = p_71194_1_;
   }

   public ISaveFormat func_71254_M() {
      return this.field_71310_m;
   }

   public void func_71272_O() {
      this.field_71290_O = true;
      this.func_71254_M().func_75800_d();

      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_71305_c.length; ++lvt_1_1_) {
         WorldServer lvt_2_1_ = this.field_71305_c[lvt_1_1_];
         if (lvt_2_1_ != null) {
            lvt_2_1_.func_73041_k();
         }
      }

      this.func_71254_M().func_75802_e(this.field_71305_c[0].func_72860_G().func_75760_g());
      this.func_71263_m();
   }

   public String func_147133_T() {
      return this.field_147141_M;
   }

   public String func_175581_ab() {
      return this.field_175588_P;
   }

   public void func_180507_a_(String p_180507_1_, String p_180507_2_) {
      this.field_147141_M = p_180507_1_;
      this.field_175588_P = p_180507_2_;
   }

   public void func_70000_a(PlayerUsageSnooper p_70000_1_) {
      p_70000_1_.func_152768_a("whitelist_enabled", false);
      p_70000_1_.func_152768_a("whitelist_count", 0);
      if (this.field_71318_t != null) {
         p_70000_1_.func_152768_a("players_current", this.func_71233_x());
         p_70000_1_.func_152768_a("players_max", this.func_71275_y());
         p_70000_1_.func_152768_a("players_seen", this.field_71318_t.func_72373_m().length);
      }

      p_70000_1_.func_152768_a("uses_auth", this.field_71325_x);
      p_70000_1_.func_152768_a("gui_state", this.func_71279_ae() ? "enabled" : "disabled");
      p_70000_1_.func_152768_a("run_time", (func_130071_aq() - p_70000_1_.func_130105_g()) / 60L * 1000L);
      p_70000_1_.func_152768_a("avg_tick_ms", (int)(MathHelper.func_76127_a(this.field_71311_j) * 1.0E-6D));
      int lvt_2_1_ = 0;
      if (this.field_71305_c != null) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_71305_c.length; ++lvt_3_1_) {
            if (this.field_71305_c[lvt_3_1_] != null) {
               WorldServer lvt_4_1_ = this.field_71305_c[lvt_3_1_];
               WorldInfo lvt_5_1_ = lvt_4_1_.func_72912_H();
               p_70000_1_.func_152768_a("world[" + lvt_2_1_ + "][dimension]", lvt_4_1_.field_73011_w.func_177502_q());
               p_70000_1_.func_152768_a("world[" + lvt_2_1_ + "][mode]", lvt_5_1_.func_76077_q());
               p_70000_1_.func_152768_a("world[" + lvt_2_1_ + "][difficulty]", lvt_4_1_.func_175659_aa());
               p_70000_1_.func_152768_a("world[" + lvt_2_1_ + "][hardcore]", lvt_5_1_.func_76093_s());
               p_70000_1_.func_152768_a("world[" + lvt_2_1_ + "][generator_name]", lvt_5_1_.func_76067_t().func_77127_a());
               p_70000_1_.func_152768_a("world[" + lvt_2_1_ + "][generator_version]", lvt_5_1_.func_76067_t().func_77131_c());
               p_70000_1_.func_152768_a("world[" + lvt_2_1_ + "][height]", this.field_71280_D);
               p_70000_1_.func_152768_a("world[" + lvt_2_1_ + "][chunks_loaded]", lvt_4_1_.func_72863_F().func_73152_e());
               ++lvt_2_1_;
            }
         }
      }

      p_70000_1_.func_152768_a("worlds", lvt_2_1_);
   }

   public void func_70001_b(PlayerUsageSnooper p_70001_1_) {
      p_70001_1_.func_152767_b("singleplayer", this.func_71264_H());
      p_70001_1_.func_152767_b("server_brand", this.getServerModName());
      p_70001_1_.func_152767_b("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
      p_70001_1_.func_152767_b("dedicated", this.func_71262_S());
   }

   public boolean func_70002_Q() {
      return true;
   }

   public abstract boolean func_71262_S();

   public boolean func_71266_T() {
      return this.field_71325_x;
   }

   public void func_71229_d(boolean p_71229_1_) {
      this.field_71325_x = p_71229_1_;
   }

   public boolean func_71268_U() {
      return this.field_71324_y;
   }

   public void func_71251_e(boolean p_71251_1_) {
      this.field_71324_y = p_71251_1_;
   }

   public boolean func_71220_V() {
      return this.field_71323_z;
   }

   public abstract boolean func_181035_ah();

   public void func_71257_f(boolean p_71257_1_) {
      this.field_71323_z = p_71257_1_;
   }

   public boolean func_71219_W() {
      return this.field_71284_A;
   }

   public void func_71188_g(boolean p_71188_1_) {
      this.field_71284_A = p_71188_1_;
   }

   public boolean func_71231_X() {
      return this.field_71285_B;
   }

   public void func_71245_h(boolean p_71245_1_) {
      this.field_71285_B = p_71245_1_;
   }

   public abstract boolean func_82356_Z();

   public String func_71273_Y() {
      return this.field_71286_C;
   }

   public void func_71205_p(String p_71205_1_) {
      this.field_71286_C = p_71205_1_;
   }

   public int func_71207_Z() {
      return this.field_71280_D;
   }

   public void func_71191_d(int p_71191_1_) {
      this.field_71280_D = p_71191_1_;
   }

   public boolean func_71241_aa() {
      return this.field_71316_v;
   }

   public ServerConfigurationManager func_71203_ab() {
      return this.field_71318_t;
   }

   public void func_152361_a(ServerConfigurationManager p_152361_1_) {
      this.field_71318_t = p_152361_1_;
   }

   public void func_71235_a(WorldSettings.GameType p_71235_1_) {
      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_71305_c.length; ++lvt_2_1_) {
         func_71276_C().field_71305_c[lvt_2_1_].func_72912_H().func_76060_a(p_71235_1_);
      }

   }

   public NetworkSystem func_147137_ag() {
      return this.field_147144_o;
   }

   public boolean func_71200_ad() {
      return this.field_71296_Q;
   }

   public boolean func_71279_ae() {
      return false;
   }

   public abstract String func_71206_a(WorldSettings.GameType var1, boolean var2);

   public int func_71259_af() {
      return this.field_71315_w;
   }

   public void func_71223_ag() {
      this.field_71295_T = true;
   }

   public PlayerUsageSnooper func_80003_ah() {
      return this.field_71307_n;
   }

   public BlockPos func_180425_c() {
      return BlockPos.field_177992_a;
   }

   public Vec3 func_174791_d() {
      return new Vec3(0.0D, 0.0D, 0.0D);
   }

   public World func_130014_f_() {
      return this.field_71305_c[0];
   }

   public Entity func_174793_f() {
      return null;
   }

   public int func_82357_ak() {
      return 16;
   }

   public boolean func_175579_a(World p_175579_1_, BlockPos p_175579_2_, EntityPlayer p_175579_3_) {
      return false;
   }

   public boolean func_104056_am() {
      return this.field_104057_T;
   }

   public Proxy func_110454_ao() {
      return this.field_110456_c;
   }

   public static long func_130071_aq() {
      return System.currentTimeMillis();
   }

   public int func_143007_ar() {
      return this.field_143008_E;
   }

   public void func_143006_e(int p_143006_1_) {
      this.field_143008_E = p_143006_1_;
   }

   public IChatComponent func_145748_c_() {
      return new ChatComponentText(this.func_70005_c_());
   }

   public boolean func_147136_ar() {
      return true;
   }

   public MinecraftSessionService func_147130_as() {
      return this.field_147143_S;
   }

   public GameProfileRepository func_152359_aw() {
      return this.field_152365_W;
   }

   public PlayerProfileCache func_152358_ax() {
      return this.field_152366_X;
   }

   public ServerStatusResponse func_147134_at() {
      return this.field_147147_p;
   }

   public void func_147132_au() {
      this.field_147142_T = 0L;
   }

   public Entity func_175576_a(UUID p_175576_1_) {
      WorldServer[] lvt_2_1_ = this.field_71305_c;
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         WorldServer lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         if (lvt_5_1_ != null) {
            Entity lvt_6_1_ = lvt_5_1_.func_175733_a(p_175576_1_);
            if (lvt_6_1_ != null) {
               return lvt_6_1_;
            }
         }
      }

      return null;
   }

   public boolean func_174792_t_() {
      return func_71276_C().field_71305_c[0].func_82736_K().func_82766_b("sendCommandFeedback");
   }

   public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
   }

   public int func_175580_aG() {
      return 29999984;
   }

   public <V> ListenableFuture<V> func_175586_a(Callable<V> p_175586_1_) {
      Validate.notNull(p_175586_1_);
      if (!this.func_152345_ab() && !this.func_71241_aa()) {
         ListenableFutureTask<V> lvt_2_1_ = ListenableFutureTask.create(p_175586_1_);
         synchronized(this.field_175589_i) {
            this.field_175589_i.add(lvt_2_1_);
            return lvt_2_1_;
         }
      } else {
         try {
            return Futures.immediateFuture(p_175586_1_.call());
         } catch (Exception var6) {
            return Futures.immediateFailedCheckedFuture(var6);
         }
      }
   }

   public ListenableFuture<Object> func_152344_a(Runnable p_152344_1_) {
      Validate.notNull(p_152344_1_);
      return this.func_175586_a(Executors.callable(p_152344_1_));
   }

   public boolean func_152345_ab() {
      return Thread.currentThread() == this.field_175590_aa;
   }

   public int func_175577_aI() {
      return 256;
   }
}
