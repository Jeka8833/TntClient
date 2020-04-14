package net.minecraft.realms;

import java.net.InetAddress;
import java.net.UnknownHostException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsConnect {
   private static final Logger LOGGER = LogManager.getLogger();
   private final RealmsScreen onlineScreen;
   private volatile boolean aborted = false;
   private NetworkManager connection;

   public RealmsConnect(RealmsScreen p_i1079_1_) {
      this.onlineScreen = p_i1079_1_;
   }

   public void connect(final String p_connect_1_, final int p_connect_2_) {
      Realms.setConnectedToRealms(true);
      (new Thread("Realms-connect-task") {
         public void run() {
            InetAddress lvt_1_1_ = null;

            try {
               lvt_1_1_ = InetAddress.getByName(p_connect_1_);
               if (RealmsConnect.this.aborted) {
                  return;
               }

               RealmsConnect.this.connection = NetworkManager.func_181124_a(lvt_1_1_, p_connect_2_, Minecraft.func_71410_x().field_71474_y.func_181148_f());
               if (RealmsConnect.this.aborted) {
                  return;
               }

               RealmsConnect.this.connection.func_150719_a(new NetHandlerLoginClient(RealmsConnect.this.connection, Minecraft.func_71410_x(), RealmsConnect.this.onlineScreen.getProxy()));
               if (RealmsConnect.this.aborted) {
                  return;
               }

               RealmsConnect.this.connection.func_179290_a(new C00Handshake(47, p_connect_1_, p_connect_2_, EnumConnectionState.LOGIN));
               if (RealmsConnect.this.aborted) {
                  return;
               }

               RealmsConnect.this.connection.func_179290_a(new C00PacketLoginStart(Minecraft.func_71410_x().func_110432_I().func_148256_e()));
            } catch (UnknownHostException var5) {
               Realms.clearResourcePack();
               if (RealmsConnect.this.aborted) {
                  return;
               }

               RealmsConnect.LOGGER.error((String)"Couldn't connect to world", (Throwable)var5);
               Minecraft.func_71410_x().func_110438_M().func_148529_f();
               Realms.setScreen(new DisconnectedRealmsScreen(RealmsConnect.this.onlineScreen, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{"Unknown host '" + p_connect_1_ + "'"})));
            } catch (Exception var6) {
               Realms.clearResourcePack();
               if (RealmsConnect.this.aborted) {
                  return;
               }

               RealmsConnect.LOGGER.error((String)"Couldn't connect to world", (Throwable)var6);
               String lvt_3_1_ = var6.toString();
               if (lvt_1_1_ != null) {
                  String lvt_4_1_ = lvt_1_1_.toString() + ":" + p_connect_2_;
                  lvt_3_1_ = lvt_3_1_.replaceAll(lvt_4_1_, "");
               }

               Realms.setScreen(new DisconnectedRealmsScreen(RealmsConnect.this.onlineScreen, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{lvt_3_1_})));
            }

         }
      }).start();
   }

   public void abort() {
      this.aborted = true;
   }

   public void tick() {
      if (this.connection != null) {
         if (this.connection.func_150724_d()) {
            this.connection.func_74428_b();
         } else {
            this.connection.func_179293_l();
         }
      }

   }
}
