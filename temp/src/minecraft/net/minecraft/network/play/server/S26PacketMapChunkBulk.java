package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.chunk.Chunk;

public class S26PacketMapChunkBulk implements Packet<INetHandlerPlayClient> {
   private int[] field_149266_a;
   private int[] field_149264_b;
   private S21PacketChunkData.Extracted[] field_179755_c;
   private boolean field_149267_h;

   public S26PacketMapChunkBulk() {
   }

   public S26PacketMapChunkBulk(List<Chunk> p_i45197_1_) {
      int lvt_2_1_ = p_i45197_1_.size();
      this.field_149266_a = new int[lvt_2_1_];
      this.field_149264_b = new int[lvt_2_1_];
      this.field_179755_c = new S21PacketChunkData.Extracted[lvt_2_1_];
      this.field_149267_h = !((Chunk)p_i45197_1_.get(0)).func_177412_p().field_73011_w.func_177495_o();

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
         Chunk lvt_4_1_ = (Chunk)p_i45197_1_.get(lvt_3_1_);
         S21PacketChunkData.Extracted lvt_5_1_ = S21PacketChunkData.func_179756_a(lvt_4_1_, true, this.field_149267_h, 65535);
         this.field_149266_a[lvt_3_1_] = lvt_4_1_.field_76635_g;
         this.field_149264_b[lvt_3_1_] = lvt_4_1_.field_76647_h;
         this.field_179755_c[lvt_3_1_] = lvt_5_1_;
      }

   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149267_h = p_148837_1_.readBoolean();
      int lvt_2_1_ = p_148837_1_.func_150792_a();
      this.field_149266_a = new int[lvt_2_1_];
      this.field_149264_b = new int[lvt_2_1_];
      this.field_179755_c = new S21PacketChunkData.Extracted[lvt_2_1_];

      int lvt_3_2_;
      for(lvt_3_2_ = 0; lvt_3_2_ < lvt_2_1_; ++lvt_3_2_) {
         this.field_149266_a[lvt_3_2_] = p_148837_1_.readInt();
         this.field_149264_b[lvt_3_2_] = p_148837_1_.readInt();
         this.field_179755_c[lvt_3_2_] = new S21PacketChunkData.Extracted();
         this.field_179755_c[lvt_3_2_].field_150280_b = p_148837_1_.readShort() & '\uffff';
         this.field_179755_c[lvt_3_2_].field_150282_a = new byte[S21PacketChunkData.func_180737_a(Integer.bitCount(this.field_179755_c[lvt_3_2_].field_150280_b), this.field_149267_h, true)];
      }

      for(lvt_3_2_ = 0; lvt_3_2_ < lvt_2_1_; ++lvt_3_2_) {
         p_148837_1_.readBytes(this.field_179755_c[lvt_3_2_].field_150282_a);
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeBoolean(this.field_149267_h);
      p_148840_1_.func_150787_b(this.field_179755_c.length);

      int lvt_2_2_;
      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_149266_a.length; ++lvt_2_2_) {
         p_148840_1_.writeInt(this.field_149266_a[lvt_2_2_]);
         p_148840_1_.writeInt(this.field_149264_b[lvt_2_2_]);
         p_148840_1_.writeShort((short)(this.field_179755_c[lvt_2_2_].field_150280_b & '\uffff'));
      }

      for(lvt_2_2_ = 0; lvt_2_2_ < this.field_149266_a.length; ++lvt_2_2_) {
         p_148840_1_.writeBytes(this.field_179755_c[lvt_2_2_].field_150282_a);
      }

   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147269_a(this);
   }

   public int func_149255_a(int p_149255_1_) {
      return this.field_149266_a[p_149255_1_];
   }

   public int func_149253_b(int p_149253_1_) {
      return this.field_149264_b[p_149253_1_];
   }

   public int func_149254_d() {
      return this.field_149266_a.length;
   }

   public byte[] func_149256_c(int p_149256_1_) {
      return this.field_179755_c[p_149256_1_].field_150282_a;
   }

   public int func_179754_d(int p_179754_1_) {
      return this.field_179755_c[p_179754_1_].field_150280_b;
   }
}
