package net.minecraft.server.integrated;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.CryptManager;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.ClearWater;
import net.optifine.reflect.Reflector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntegratedServer extends MinecraftServer {
   private static final Logger field_147148_h = LogManager.getLogger();
   private final Minecraft field_71349_l;
   private final WorldSettings field_71350_m;
   private boolean field_71348_o;
   private boolean field_71346_p;
   private ThreadLanServerPing field_71345_q;
   private long ticksSaveLast = 0L;
   public World difficultyUpdateWorld = null;
   public BlockPos difficultyUpdatePos = null;
   public DifficultyInstance difficultyLast = null;

   public IntegratedServer(Minecraft p_i46070_1_) {
      super(p_i46070_1_.func_110437_J(), new File(p_i46070_1_.field_71412_D, field_152367_a.getName()));
      this.field_71349_l = p_i46070_1_;
      this.field_71350_m = null;
   }

   public IntegratedServer(Minecraft p_i1317_1_, String p_i1317_2_, String p_i1317_3_, WorldSettings p_i1317_4_) {
      super(new File(p_i1317_1_.field_71412_D, "saves"), p_i1317_1_.func_110437_J(), new File(p_i1317_1_.field_71412_D, field_152367_a.getName()));
      this.func_71224_l(p_i1317_1_.func_110432_I().func_111285_a());
      this.func_71261_m(p_i1317_2_);
      this.func_71246_n(p_i1317_3_);
      this.func_71204_b(p_i1317_1_.func_71355_q());
      this.func_71194_c(p_i1317_4_.func_77167_c());
      this.func_71191_d(256);
      this.func_152361_a(new IntegratedPlayerList(this));
      this.field_71349_l = p_i1317_1_;
      this.field_71350_m = this.func_71242_L() ? DemoWorldServer.field_73071_a : p_i1317_4_;
      ISaveHandler isavehandler = this.func_71254_M().func_75804_a(p_i1317_2_, false);
      WorldInfo worldinfo = isavehandler.func_75757_d();
      if (worldinfo != null) {
         NBTTagCompound nbt = worldinfo.func_76072_h();
         if (nbt != null && nbt.func_74764_b("Dimension")) {
            int dim = nbt.func_74762_e("Dimension");
            PacketThreadUtil.lastDimensionId = dim;
            this.field_71349_l.field_71461_s.func_73718_a(-1);
         }
      }

   }

   protected ServerCommandManager func_175582_h() {
      return new IntegratedServerCommandManager();
   }

   protected void func_71247_a(String p_71247_1_, String p_71247_2_, long p_71247_3_, WorldType p_71247_5_, String p_71247_6_) {
      this.func_71237_c(p_71247_1_);
      boolean forge = Reflector.DimensionManager.exists();
      if (!forge) {
         this.field_71305_c = new WorldServer[3];
         this.field_71312_k = new long[this.field_71305_c.length][100];
      }

      ISaveHandler isavehandler = this.func_71254_M().func_75804_a(p_71247_1_, true);
      this.func_175584_a(this.func_71270_I(), isavehandler);
      WorldInfo worldinfo = isavehandler.func_75757_d();
      if (worldinfo == null) {
         worldinfo = new WorldInfo(this.field_71350_m, p_71247_2_);
      } else {
         worldinfo.func_76062_a(p_71247_2_);
      }

      if (forge) {
         WorldServer overWorld = this.func_71242_L() ? (WorldServer)((WorldServer)(new DemoWorldServer(this, isavehandler, worldinfo, 0, this.field_71304_b)).func_175643_b()) : (WorldServer)(new WorldServer(this, isavehandler, worldinfo, 0, this.field_71304_b)).func_175643_b();
         overWorld.func_72963_a(this.field_71350_m);
         Integer[] dimIds = (Integer[])((Integer[])Reflector.call(Reflector.DimensionManager_getStaticDimensionIDs));
         Integer[] arr$ = dimIds;
         int len$ = dimIds.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            int dim = arr$[i$];
            WorldServer world = dim == 0 ? overWorld : (WorldServer)((WorldServer)(new WorldServerMulti(this, isavehandler, dim, overWorld, this.field_71304_b)).func_175643_b());
            world.func_72954_a(new WorldManager(this, world));
            if (!this.func_71264_H()) {
               world.func_72912_H().func_76060_a(this.func_71265_f());
            }

            if (Reflector.EventBus.exists()) {
               Reflector.postForgeBusEvent(Reflector.WorldEvent_Load_Constructor, world);
            }
         }

         this.func_71203_ab().func_72364_a(new WorldServer[]{overWorld});
         if (overWorld.func_72912_H().func_176130_y() == null) {
            this.func_147139_a(this.field_71349_l.field_71474_y.field_74319_N);
         }
      } else {
         for(int i = 0; i < this.field_71305_c.length; ++i) {
            int j = 0;
            if (i == 1) {
               j = -1;
            }

            if (i == 2) {
               j = 1;
            }

            if (i == 0) {
               if (this.func_71242_L()) {
                  this.field_71305_c[i] = (WorldServer)(new DemoWorldServer(this, isavehandler, worldinfo, j, this.field_71304_b)).func_175643_b();
               } else {
                  this.field_71305_c[i] = (WorldServer)(new WorldServer(this, isavehandler, worldinfo, j, this.field_71304_b)).func_175643_b();
               }

               this.field_71305_c[i].func_72963_a(this.field_71350_m);
            } else {
               this.field_71305_c[i] = (WorldServer)(new WorldServerMulti(this, isavehandler, j, this.field_71305_c[0], this.field_71304_b)).func_175643_b();
            }

            this.field_71305_c[i].func_72954_a(new WorldManager(this, this.field_71305_c[i]));
         }

         this.func_71203_ab().func_72364_a(this.field_71305_c);
         if (this.field_71305_c[0].func_72912_H().func_176130_y() == null) {
            this.func_147139_a(this.field_71349_l.field_71474_y.field_74319_N);
         }
      }

      this.func_71222_d();
   }

   protected boolean func_71197_b() throws IOException {
      field_147148_h.info("Starting integrated minecraft server version 1.9");
      this.func_71229_d(true);
      this.func_71251_e(true);
      this.func_71257_f(true);
      this.func_71188_g(true);
      this.func_71245_h(true);
      field_147148_h.info("Generating keypair");
      this.func_71253_a(CryptManager.func_75891_b());
      Object inst;
      if (Reflector.FMLCommonHandler_handleServerAboutToStart.exists()) {
         inst = Reflector.call(Reflector.FMLCommonHandler_instance);
         if (!Reflector.callBoolean(inst, Reflector.FMLCommonHandler_handleServerAboutToStart, this)) {
            return false;
         }
      }

      this.func_71247_a(this.func_71270_I(), this.func_71221_J(), this.field_71350_m.func_77160_d(), this.field_71350_m.func_77165_h(), this.field_71350_m.func_82749_j());
      this.func_71205_p(this.func_71214_G() + " - " + this.field_71305_c[0].func_72912_H().func_76065_j());
      if (Reflector.FMLCommonHandler_handleServerStarting.exists()) {
         inst = Reflector.call(Reflector.FMLCommonHandler_instance);
         if (Reflector.FMLCommonHandler_handleServerStarting.getReturnType() == Boolean.TYPE) {
            return Reflector.callBoolean(inst, Reflector.FMLCommonHandler_handleServerStarting, this);
         }

         Reflector.callVoid(inst, Reflector.FMLCommonHandler_handleServerStarting, this);
      }

      return true;
   }

   public void func_71217_p() {
      this.onTick();
      boolean flag = this.field_71348_o;
      this.field_71348_o = Minecraft.func_71410_x().func_147114_u() != null && Minecraft.func_71410_x().func_147113_T();
      if (!flag && this.field_71348_o) {
         field_147148_h.info("Saving and pausing game...");
         this.func_71203_ab().func_72389_g();
         this.func_71267_a(false);
      }

      if (this.field_71348_o) {
         synchronized(this.field_175589_i) {
            while(!this.field_175589_i.isEmpty()) {
               Util.func_181617_a((FutureTask)this.field_175589_i.poll(), field_147148_h);
            }
         }
      } else {
         super.func_71217_p();
         if (this.field_71349_l.field_71474_y.field_151451_c != this.func_71203_ab().func_72395_o()) {
            field_147148_h.info("Changing view distance to {}, from {}", this.field_71349_l.field_71474_y.field_151451_c, this.func_71203_ab().func_72395_o());
            this.func_71203_ab().func_152611_a(this.field_71349_l.field_71474_y.field_151451_c);
         }

         if (this.field_71349_l.field_71441_e != null) {
            WorldInfo worldinfo1 = this.field_71305_c[0].func_72912_H();
            WorldInfo worldinfo = this.field_71349_l.field_71441_e.func_72912_H();
            if (!worldinfo1.func_176123_z() && worldinfo.func_176130_y() != worldinfo1.func_176130_y()) {
               field_147148_h.info("Changing difficulty to {}, from {}", worldinfo.func_176130_y(), worldinfo1.func_176130_y());
               this.func_147139_a(worldinfo.func_176130_y());
            } else if (worldinfo.func_176123_z() && !worldinfo1.func_176123_z()) {
               field_147148_h.info("Locking difficulty to {}", worldinfo.func_176130_y());
               WorldServer[] arr$ = this.field_71305_c;
               int len$ = arr$.length;

               for(int i$ = 0; i$ < len$; ++i$) {
                  WorldServer worldserver = arr$[i$];
                  if (worldserver != null) {
                     worldserver.func_72912_H().func_180783_e(true);
                  }
               }
            }
         }
      }

   }

   public boolean func_71225_e() {
      return false;
   }

   public WorldSettings.GameType func_71265_f() {
      return this.field_71350_m.func_77162_e();
   }

   public EnumDifficulty func_147135_j() {
      return this.field_71349_l.field_71441_e == null ? this.field_71349_l.field_71474_y.field_74319_N : this.field_71349_l.field_71441_e.func_72912_H().func_176130_y();
   }

   public boolean func_71199_h() {
      return this.field_71350_m.func_77158_f();
   }

   public boolean func_181034_q() {
      return true;
   }

   public boolean func_183002_r() {
      return true;
   }

   public void func_71267_a(boolean p_71267_1_) {
      if (p_71267_1_) {
         int ticks = this.func_71259_af();
         int ticksSaveInterval = this.field_71349_l.field_71474_y.ofAutoSaveTicks;
         if ((long)ticks < this.ticksSaveLast + (long)ticksSaveInterval) {
            return;
         }

         this.ticksSaveLast = (long)ticks;
      }

      super.func_71267_a(p_71267_1_);
   }

   public File func_71238_n() {
      return this.field_71349_l.field_71412_D;
   }

   public boolean func_71262_S() {
      return false;
   }

   public boolean func_181035_ah() {
      return false;
   }

   protected void func_71228_a(CrashReport p_71228_1_) {
      this.field_71349_l.func_71404_a(p_71228_1_);
   }

   public CrashReport func_71230_b(CrashReport p_71230_1_) {
      p_71230_1_ = super.func_71230_b(p_71230_1_);
      p_71230_1_.func_85056_g().func_71500_a("Type", new Callable<String>() {
         public String call() throws Exception {
            return "Integrated Server (map_client.txt)";
         }
      });
      p_71230_1_.func_85056_g().func_71500_a("Is Modded", new Callable<String>() {
         public String call() throws Exception {
            String s = ClientBrandRetriever.getClientModName();
            if (!s.equals("vanilla")) {
               return "Definitely; Client brand changed to '" + s + "'";
            } else {
               s = IntegratedServer.this.getServerModName();
               return !s.equals("vanilla") ? "Definitely; Server brand changed to '" + s + "'" : (Minecraft.class.getSigners() == null ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and both client + server brands are untouched.");
            }
         }
      });
      return p_71230_1_;
   }

   public void func_147139_a(EnumDifficulty p_147139_1_) {
      super.func_147139_a(p_147139_1_);
      if (this.field_71349_l.field_71441_e != null) {
         this.field_71349_l.field_71441_e.func_72912_H().func_176144_a(p_147139_1_);
      }

   }

   public void func_70000_a(PlayerUsageSnooper p_70000_1_) {
      super.func_70000_a(p_70000_1_);
      p_70000_1_.func_152768_a("snooper_partner", this.field_71349_l.func_71378_E().func_80006_f());
   }

   public boolean func_70002_Q() {
      return Minecraft.func_71410_x().func_70002_Q();
   }

   public String func_71206_a(WorldSettings.GameType p_71206_1_, boolean p_71206_2_) {
      try {
         int i = -1;

         try {
            i = HttpUtil.func_76181_a();
         } catch (IOException var5) {
         }

         if (i <= 0) {
            i = 25564;
         }

         this.func_147137_ag().func_151265_a((InetAddress)null, i);
         field_147148_h.info("Started on " + i);
         this.field_71346_p = true;
         this.field_71345_q = new ThreadLanServerPing(this.func_71273_Y(), i + "");
         this.field_71345_q.start();
         this.func_71203_ab().func_152604_a(p_71206_1_);
         this.func_71203_ab().func_72387_b(p_71206_2_);
         return i + "";
      } catch (IOException var6) {
         return null;
      }
   }

   public void func_71260_j() {
      super.func_71260_j();
      if (this.field_71345_q != null) {
         this.field_71345_q.interrupt();
         this.field_71345_q = null;
      }

   }

   public void func_71263_m() {
      if (!Reflector.MinecraftForge.exists() || this.func_71278_l()) {
         Futures.getUnchecked(this.func_152344_a(new Runnable() {
            public void run() {
               Iterator i$ = Lists.newArrayList((Iterable)IntegratedServer.this.func_71203_ab().func_181057_v()).iterator();

               while(i$.hasNext()) {
                  EntityPlayerMP entityplayermp = (EntityPlayerMP)i$.next();
                  IntegratedServer.this.func_71203_ab().func_72367_e(entityplayermp);
               }

            }
         }));
      }

      super.func_71263_m();
      if (this.field_71345_q != null) {
         this.field_71345_q.interrupt();
         this.field_71345_q = null;
      }

   }

   public void func_175592_a() {
      this.func_175585_v();
   }

   public boolean func_71344_c() {
      return this.field_71346_p;
   }

   public void func_71235_a(WorldSettings.GameType p_71235_1_) {
      this.func_71203_ab().func_152604_a(p_71235_1_);
   }

   public boolean func_82356_Z() {
      return true;
   }

   public int func_110455_j() {
      return 4;
   }

   private void onTick() {
      Iterable<WorldServer> iws = Arrays.asList(this.field_71305_c);
      Iterator it = iws.iterator();

      while(it.hasNext()) {
         WorldServer ws = (WorldServer)it.next();
         this.onTick(ws);
      }

   }

   public DifficultyInstance getDifficultyAsync(World p_getDifficultyAsync_1_, BlockPos p_getDifficultyAsync_2_) {
      this.difficultyUpdateWorld = p_getDifficultyAsync_1_;
      this.difficultyUpdatePos = p_getDifficultyAsync_2_;
      return this.difficultyLast;
   }

   private void onTick(WorldServer p_onTick_1_) {
      if (!Config.isTimeDefault()) {
         this.fixWorldTime(p_onTick_1_);
      }

      if (!Config.isWeatherEnabled()) {
         this.fixWorldWeather(p_onTick_1_);
      }

      if (Config.waterOpacityChanged) {
         Config.waterOpacityChanged = false;
         ClearWater.updateWaterOpacity(Config.getGameSettings(), p_onTick_1_);
      }

      if (this.difficultyUpdateWorld == p_onTick_1_ && this.difficultyUpdatePos != null) {
         this.difficultyLast = p_onTick_1_.func_175649_E(this.difficultyUpdatePos);
         this.difficultyUpdateWorld = null;
         this.difficultyUpdatePos = null;
      }

   }

   private void fixWorldWeather(WorldServer p_fixWorldWeather_1_) {
      WorldInfo worldInfo = p_fixWorldWeather_1_.func_72912_H();
      if (worldInfo.func_76059_o() || worldInfo.func_76061_m()) {
         worldInfo.func_76080_g(0);
         worldInfo.func_76084_b(false);
         p_fixWorldWeather_1_.func_72894_k(0.0F);
         worldInfo.func_76090_f(0);
         worldInfo.func_76069_a(false);
         p_fixWorldWeather_1_.func_147442_i(0.0F);
         this.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(2, 0.0F));
         this.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(7, 0.0F));
         this.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(8, 0.0F));
      }

   }

   private void fixWorldTime(WorldServer p_fixWorldTime_1_) {
      WorldInfo worldInfo = p_fixWorldTime_1_.func_72912_H();
      if (worldInfo.func_76077_q().func_77148_a() == 1) {
         long time = p_fixWorldTime_1_.func_72820_D();
         long timeOfDay = time % 24000L;
         if (Config.isTimeDayOnly()) {
            if (timeOfDay <= 1000L) {
               p_fixWorldTime_1_.func_72877_b(time - timeOfDay + 1001L);
            }

            if (timeOfDay >= 11000L) {
               p_fixWorldTime_1_.func_72877_b(time - timeOfDay + 24001L);
            }
         }

         if (Config.isTimeNightOnly()) {
            if (timeOfDay <= 14000L) {
               p_fixWorldTime_1_.func_72877_b(time - timeOfDay + 14001L);
            }

            if (timeOfDay >= 22000L) {
               p_fixWorldTime_1_.func_72877_b(time - timeOfDay + 24000L + 14001L);
            }
         }

      }
   }
}
