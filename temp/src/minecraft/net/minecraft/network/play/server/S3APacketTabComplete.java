package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S3APacketTabComplete implements Packet<INetHandlerPlayClient> {
   private String[] field_149632_a;

   public S3APacketTabComplete() {
   }

   public S3APacketTabComplete(String[] p_i45178_1_) {
      this.field_149632_a = p_i45178_1_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149632_a = new String[p_148837_1_.func_150792_a()];

      for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_149632_a.length; ++lvt_2_1_) {
         this.field_149632_a[lvt_2_1_] = p_148837_1_.func_150789_c(32767);
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_150787_b(this.field_149632_a.length);
      String[] lvt_2_1_ = this.field_149632_a;
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         String lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         p_148840_1_.func_180714_a(lvt_5_1_);
      }

   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147274_a(this);
   }

   public String[] func_149630_c() {
      return this.field_149632_a;
   }
}
