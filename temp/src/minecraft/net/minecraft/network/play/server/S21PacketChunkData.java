package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class S21PacketChunkData implements Packet<INetHandlerPlayClient> {
   private int field_149284_a;
   private int field_149282_b;
   private S21PacketChunkData.Extracted field_179758_c;
   private boolean field_149279_g;

   public S21PacketChunkData() {
   }

   public S21PacketChunkData(Chunk p_i45196_1_, boolean p_i45196_2_, int p_i45196_3_) {
      this.field_149284_a = p_i45196_1_.field_76635_g;
      this.field_149282_b = p_i45196_1_.field_76647_h;
      this.field_149279_g = p_i45196_2_;
      this.field_179758_c = func_179756_a(p_i45196_1_, p_i45196_2_, !p_i45196_1_.func_177412_p().field_73011_w.func_177495_o(), p_i45196_3_);
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149284_a = p_148837_1_.readInt();
      this.field_149282_b = p_148837_1_.readInt();
      this.field_149279_g = p_148837_1_.readBoolean();
      this.field_179758_c = new S21PacketChunkData.Extracted();
      this.field_179758_c.field_150280_b = p_148837_1_.readShort();
      this.field_179758_c.field_150282_a = p_148837_1_.func_179251_a();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeInt(this.field_149284_a);
      p_148840_1_.writeInt(this.field_149282_b);
      p_148840_1_.writeBoolean(this.field_149279_g);
      p_148840_1_.writeShort((short)(this.field_179758_c.field_150280_b & '\uffff'));
      p_148840_1_.func_179250_a(this.field_179758_c.field_150282_a);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147263_a(this);
   }

   public byte[] func_149272_d() {
      return this.field_179758_c.field_150282_a;
   }

   protected static int func_180737_a(int p_180737_0_, boolean p_180737_1_, boolean p_180737_2_) {
      int lvt_3_1_ = p_180737_0_ * 2 * 16 * 16 * 16;
      int lvt_4_1_ = p_180737_0_ * 16 * 16 * 16 / 2;
      int lvt_5_1_ = p_180737_1_ ? p_180737_0_ * 16 * 16 * 16 / 2 : 0;
      int lvt_6_1_ = p_180737_2_ ? 256 : 0;
      return lvt_3_1_ + lvt_4_1_ + lvt_5_1_ + lvt_6_1_;
   }

   public static S21PacketChunkData.Extracted func_179756_a(Chunk p_179756_0_, boolean p_179756_1_, boolean p_179756_2_, int p_179756_3_) {
      ExtendedBlockStorage[] lvt_4_1_ = p_179756_0_.func_76587_i();
      S21PacketChunkData.Extracted lvt_5_1_ = new S21PacketChunkData.Extracted();
      List<ExtendedBlockStorage> lvt_6_1_ = Lists.newArrayList();

      int lvt_7_2_;
      for(lvt_7_2_ = 0; lvt_7_2_ < lvt_4_1_.length; ++lvt_7_2_) {
         ExtendedBlockStorage lvt_8_1_ = lvt_4_1_[lvt_7_2_];
         if (lvt_8_1_ != null && (!p_179756_1_ || !lvt_8_1_.func_76663_a()) && (p_179756_3_ & 1 << lvt_7_2_) != 0) {
            lvt_5_1_.field_150280_b |= 1 << lvt_7_2_;
            lvt_6_1_.add(lvt_8_1_);
         }
      }

      lvt_5_1_.field_150282_a = new byte[func_180737_a(Integer.bitCount(lvt_5_1_.field_150280_b), p_179756_2_, p_179756_1_)];
      lvt_7_2_ = 0;
      Iterator lvt_8_4_ = lvt_6_1_.iterator();

      ExtendedBlockStorage lvt_9_3_;
      while(lvt_8_4_.hasNext()) {
         lvt_9_3_ = (ExtendedBlockStorage)lvt_8_4_.next();
         char[] lvt_10_1_ = lvt_9_3_.func_177487_g();
         char[] lvt_11_1_ = lvt_10_1_;
         int lvt_12_1_ = lvt_10_1_.length;

         for(int lvt_13_1_ = 0; lvt_13_1_ < lvt_12_1_; ++lvt_13_1_) {
            char lvt_14_1_ = lvt_11_1_[lvt_13_1_];
            lvt_5_1_.field_150282_a[lvt_7_2_++] = (byte)(lvt_14_1_ & 255);
            lvt_5_1_.field_150282_a[lvt_7_2_++] = (byte)(lvt_14_1_ >> 8 & 255);
         }
      }

      for(lvt_8_4_ = lvt_6_1_.iterator(); lvt_8_4_.hasNext(); lvt_7_2_ = func_179757_a(lvt_9_3_.func_76661_k().func_177481_a(), lvt_5_1_.field_150282_a, lvt_7_2_)) {
         lvt_9_3_ = (ExtendedBlockStorage)lvt_8_4_.next();
      }

      if (p_179756_2_) {
         for(lvt_8_4_ = lvt_6_1_.iterator(); lvt_8_4_.hasNext(); lvt_7_2_ = func_179757_a(lvt_9_3_.func_76671_l().func_177481_a(), lvt_5_1_.field_150282_a, lvt_7_2_)) {
            lvt_9_3_ = (ExtendedBlockStorage)lvt_8_4_.next();
         }
      }

      if (p_179756_1_) {
         func_179757_a(p_179756_0_.func_76605_m(), lvt_5_1_.field_150282_a, lvt_7_2_);
      }

      return lvt_5_1_;
   }

   private static int func_179757_a(byte[] p_179757_0_, byte[] p_179757_1_, int p_179757_2_) {
      System.arraycopy(p_179757_0_, 0, p_179757_1_, p_179757_2_, p_179757_0_.length);
      return p_179757_2_ + p_179757_0_.length;
   }

   public int func_149273_e() {
      return this.field_149284_a;
   }

   public int func_149271_f() {
      return this.field_149282_b;
   }

   public int func_149276_g() {
      return this.field_179758_c.field_150280_b;
   }

   public boolean func_149274_i() {
      return this.field_149279_g;
   }

   public static class Extracted {
      public byte[] field_150282_a;
      public int field_150280_b;
   }
}
