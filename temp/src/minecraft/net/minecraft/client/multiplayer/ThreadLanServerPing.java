package net.minecraft.client.multiplayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadLanServerPing extends Thread {
   private static final AtomicInteger field_148658_a = new AtomicInteger(0);
   private static final Logger field_148657_b = LogManager.getLogger();
   private final String field_77528_b;
   private final DatagramSocket field_77529_c;
   private boolean field_77526_d = true;
   private final String field_77527_e;

   public ThreadLanServerPing(String p_i1321_1_, String p_i1321_2_) throws IOException {
      super("LanServerPinger #" + field_148658_a.incrementAndGet());
      this.field_77528_b = p_i1321_1_;
      this.field_77527_e = p_i1321_2_;
      this.setDaemon(true);
      this.field_77529_c = new DatagramSocket();
   }

   public void run() {
      String lvt_1_1_ = func_77525_a(this.field_77528_b, this.field_77527_e);
      byte[] lvt_2_1_ = lvt_1_1_.getBytes();

      while(!this.isInterrupted() && this.field_77526_d) {
         try {
            InetAddress lvt_3_1_ = InetAddress.getByName("224.0.2.60");
            DatagramPacket lvt_4_1_ = new DatagramPacket(lvt_2_1_, lvt_2_1_.length, lvt_3_1_, 4445);
            this.field_77529_c.send(lvt_4_1_);
         } catch (IOException var6) {
            field_148657_b.warn("LanServerPinger: " + var6.getMessage());
            break;
         }

         try {
            sleep(1500L);
         } catch (InterruptedException var5) {
         }
      }

   }

   public void interrupt() {
      super.interrupt();
      this.field_77526_d = false;
   }

   public static String func_77525_a(String p_77525_0_, String p_77525_1_) {
      return "[MOTD]" + p_77525_0_ + "[/MOTD][AD]" + p_77525_1_ + "[/AD]";
   }

   public static String func_77524_a(String p_77524_0_) {
      int lvt_1_1_ = p_77524_0_.indexOf("[MOTD]");
      if (lvt_1_1_ < 0) {
         return "missing no";
      } else {
         int lvt_2_1_ = p_77524_0_.indexOf("[/MOTD]", lvt_1_1_ + "[MOTD]".length());
         return lvt_2_1_ < lvt_1_1_ ? "missing no" : p_77524_0_.substring(lvt_1_1_ + "[MOTD]".length(), lvt_2_1_);
      }
   }

   public static String func_77523_b(String p_77523_0_) {
      int lvt_1_1_ = p_77523_0_.indexOf("[/MOTD]");
      if (lvt_1_1_ < 0) {
         return null;
      } else {
         int lvt_2_1_ = p_77523_0_.indexOf("[/MOTD]", lvt_1_1_ + "[/MOTD]".length());
         if (lvt_2_1_ >= 0) {
            return null;
         } else {
            int lvt_3_1_ = p_77523_0_.indexOf("[AD]", lvt_1_1_ + "[/MOTD]".length());
            if (lvt_3_1_ < 0) {
               return null;
            } else {
               int lvt_4_1_ = p_77523_0_.indexOf("[/AD]", lvt_3_1_ + "[AD]".length());
               return lvt_4_1_ < lvt_3_1_ ? null : p_77523_0_.substring(lvt_3_1_ + "[AD]".length(), lvt_4_1_);
            }
         }
      }
   }
}
