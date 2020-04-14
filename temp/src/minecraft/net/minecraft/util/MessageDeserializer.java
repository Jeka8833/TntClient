package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class MessageDeserializer extends ByteToMessageDecoder {
   private static final Logger field_150800_a = LogManager.getLogger();
   private static final Marker field_150799_b;
   private final EnumPacketDirection field_152499_c;

   public MessageDeserializer(EnumPacketDirection p_i45999_1_) {
      this.field_152499_c = p_i45999_1_;
   }

   protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_) throws IOException, InstantiationException, IllegalAccessException, Exception {
      if (p_decode_2_.readableBytes() != 0) {
         PacketBuffer lvt_4_1_ = new PacketBuffer(p_decode_2_);
         int lvt_5_1_ = lvt_4_1_.func_150792_a();
         Packet lvt_6_1_ = ((EnumConnectionState)p_decode_1_.channel().attr(NetworkManager.field_150739_c).get()).func_179244_a(this.field_152499_c, lvt_5_1_);
         if (lvt_6_1_ == null) {
            throw new IOException("Bad packet id " + lvt_5_1_);
         } else {
            lvt_6_1_.func_148837_a(lvt_4_1_);
            if (lvt_4_1_.readableBytes() > 0) {
               throw new IOException("Packet " + ((EnumConnectionState)p_decode_1_.channel().attr(NetworkManager.field_150739_c).get()).func_150759_c() + "/" + lvt_5_1_ + " (" + lvt_6_1_.getClass().getSimpleName() + ") was larger than I expected, found " + lvt_4_1_.readableBytes() + " bytes extra whilst reading packet " + lvt_5_1_);
            } else {
               p_decode_3_.add(lvt_6_1_);
               if (field_150800_a.isDebugEnabled()) {
                  field_150800_a.debug(field_150799_b, " IN: [{}:{}] {}", p_decode_1_.channel().attr(NetworkManager.field_150739_c).get(), lvt_5_1_, lvt_6_1_.getClass().getName());
               }

            }
         }
      }
   }

   static {
      field_150799_b = MarkerManager.getMarker("PACKET_RECEIVED", NetworkManager.field_150738_b);
   }
}
