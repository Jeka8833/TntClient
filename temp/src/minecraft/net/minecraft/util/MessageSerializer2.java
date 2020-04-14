package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.network.PacketBuffer;

public class MessageSerializer2 extends MessageToByteEncoder<ByteBuf> {
   protected void encode(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) throws Exception {
      int lvt_4_1_ = p_encode_2_.readableBytes();
      int lvt_5_1_ = PacketBuffer.func_150790_a(lvt_4_1_);
      if (lvt_5_1_ > 3) {
         throw new IllegalArgumentException("unable to fit " + lvt_4_1_ + " into " + 3);
      } else {
         PacketBuffer lvt_6_1_ = new PacketBuffer(p_encode_3_);
         lvt_6_1_.ensureWritable(lvt_5_1_ + lvt_4_1_);
         lvt_6_1_.func_150787_b(lvt_4_1_);
         lvt_6_1_.writeBytes(p_encode_2_, p_encode_2_.readerIndex(), lvt_4_1_);
      }
   }
}
