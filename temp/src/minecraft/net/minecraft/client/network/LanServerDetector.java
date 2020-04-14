package net.minecraft.client.network;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanServerDetector {
   private static final AtomicInteger field_148551_a = new AtomicInteger(0);
   private static final Logger field_148550_b = LogManager.getLogger();

   public static class ThreadLanServerFind extends Thread {
      private final LanServerDetector.LanServerList field_77500_a;
      private final InetAddress field_77498_b;
      private final MulticastSocket field_77499_c;

      public ThreadLanServerFind(LanServerDetector.LanServerList p_i1320_1_) throws IOException {
         super("LanServerDetector #" + LanServerDetector.field_148551_a.incrementAndGet());
         this.field_77500_a = p_i1320_1_;
         this.setDaemon(true);
         this.field_77499_c = new MulticastSocket(4445);
         this.field_77498_b = InetAddress.getByName("224.0.2.60");
         this.field_77499_c.setSoTimeout(5000);
         this.field_77499_c.joinGroup(this.field_77498_b);
      }

      public void run() {
         byte[] lvt_2_1_ = new byte[1024];

         while(!this.isInterrupted()) {
            DatagramPacket lvt_1_1_ = new DatagramPacket(lvt_2_1_, lvt_2_1_.length);

            try {
               this.field_77499_c.receive(lvt_1_1_);
            } catch (SocketTimeoutException var5) {
               continue;
            } catch (IOException var6) {
               LanServerDetector.field_148550_b.error((String)"Couldn't ping server", (Throwable)var6);
               break;
            }

            String lvt_3_3_ = new String(lvt_1_1_.getData(), lvt_1_1_.getOffset(), lvt_1_1_.getLength());
            LanServerDetector.field_148550_b.debug(lvt_1_1_.getAddress() + ": " + lvt_3_3_);
            this.field_77500_a.func_77551_a(lvt_3_3_, lvt_1_1_.getAddress());
         }

         try {
            this.field_77499_c.leaveGroup(this.field_77498_b);
         } catch (IOException var4) {
         }

         this.field_77499_c.close();
      }
   }

   public static class LanServer {
      private String field_77492_a;
      private String field_77490_b;
      private long field_77491_c;

      public LanServer(String p_i1319_1_, String p_i1319_2_) {
         this.field_77492_a = p_i1319_1_;
         this.field_77490_b = p_i1319_2_;
         this.field_77491_c = Minecraft.func_71386_F();
      }

      public String func_77487_a() {
         return this.field_77492_a;
      }

      public String func_77488_b() {
         return this.field_77490_b;
      }

      public void func_77489_c() {
         this.field_77491_c = Minecraft.func_71386_F();
      }
   }

   public static class LanServerList {
      private List<LanServerDetector.LanServer> field_77555_b = Lists.newArrayList();
      boolean field_77556_a;

      public synchronized boolean func_77553_a() {
         return this.field_77556_a;
      }

      public synchronized void func_77552_b() {
         this.field_77556_a = false;
      }

      public synchronized List<LanServerDetector.LanServer> func_77554_c() {
         return Collections.unmodifiableList(this.field_77555_b);
      }

      public synchronized void func_77551_a(String p_77551_1_, InetAddress p_77551_2_) {
         String lvt_3_1_ = ThreadLanServerPing.func_77524_a(p_77551_1_);
         String lvt_4_1_ = ThreadLanServerPing.func_77523_b(p_77551_1_);
         if (lvt_4_1_ != null) {
            lvt_4_1_ = p_77551_2_.getHostAddress() + ":" + lvt_4_1_;
            boolean lvt_5_1_ = false;
            Iterator lvt_6_1_ = this.field_77555_b.iterator();

            while(lvt_6_1_.hasNext()) {
               LanServerDetector.LanServer lvt_7_1_ = (LanServerDetector.LanServer)lvt_6_1_.next();
               if (lvt_7_1_.func_77488_b().equals(lvt_4_1_)) {
                  lvt_7_1_.func_77489_c();
                  lvt_5_1_ = true;
                  break;
               }
            }

            if (!lvt_5_1_) {
               this.field_77555_b.add(new LanServerDetector.LanServer(lvt_3_1_, lvt_4_1_));
               this.field_77556_a = true;
            }

         }
      }
   }
}
