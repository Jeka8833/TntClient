package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;

public class NettyCompressionEncoder extends MessageToByteEncoder<ByteBuf> {
   private final byte[] field_179302_a = new byte[8192];
   private final Deflater field_179300_b;
   private int field_179301_c;

   public NettyCompressionEncoder(int p_i46005_1_) {
      this.field_179301_c = p_i46005_1_;
      this.field_179300_b = new Deflater();
   }

   protected void encode(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) throws Exception {
      int lvt_4_1_ = p_encode_2_.readableBytes();
      PacketBuffer lvt_5_1_ = new PacketBuffer(p_encode_3_);
      if (lvt_4_1_ < this.field_179301_c) {
         lvt_5_1_.func_150787_b(0);
         lvt_5_1_.writeBytes(p_encode_2_);
      } else {
         byte[] lvt_6_1_ = new byte[lvt_4_1_];
         p_encode_2_.readBytes(lvt_6_1_);
         lvt_5_1_.func_150787_b(lvt_6_1_.length);
         this.field_179300_b.setInput(lvt_6_1_, 0, lvt_4_1_);
         this.field_179300_b.finish();

         while(!this.field_179300_b.finished()) {
            int lvt_7_1_ = this.field_179300_b.deflate(this.field_179302_a);
            lvt_5_1_.writeBytes((byte[])this.field_179302_a, 0, lvt_7_1_);
         }

         this.field_179300_b.reset();
      }

   }

   public void func_179299_a(int p_179299_1_) {
      this.field_179301_c = p_179299_1_;
   }
}
