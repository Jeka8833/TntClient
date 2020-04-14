package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.IOException;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class MessageSerializer extends MessageToByteEncoder<Packet> {
   private static final Logger field_150798_a = LogManager.getLogger();
   private static final Marker field_150797_b;
   private final EnumPacketDirection field_152500_c;

   public MessageSerializer(EnumPacketDirection p_i45998_1_) {
      this.field_152500_c = p_i45998_1_;
   }

   protected void encode(ChannelHandlerContext p_encode_1_, Packet p_encode_2_, ByteBuf p_encode_3_) throws IOException, Exception {
      Integer lvt_4_1_ = ((EnumConnectionState)p_encode_1_.channel().attr(NetworkManager.field_150739_c).get()).func_179246_a(this.field_152500_c, p_encode_2_);
      if (field_150798_a.isDebugEnabled()) {
         field_150798_a.debug(field_150797_b, "OUT: [{}:{}] {}", p_encode_1_.channel().attr(NetworkManager.field_150739_c).get(), lvt_4_1_, p_encode_2_.getClass().getName());
      }

      if (lvt_4_1_ == null) {
         throw new IOException("Can't serialize unregistered packet");
      } else {
         PacketBuffer lvt_5_1_ = new PacketBuffer(p_encode_3_);
         lvt_5_1_.func_150787_b(lvt_4_1_);

         try {
            if (p_encode_2_ instanceof S0CPacketSpawnPlayer) {
               p_encode_2_ = p_encode_2_;
            }

            p_encode_2_.func_148840_b(lvt_5_1_);
         } catch (Throwable var7) {
            field_150798_a.error((Object)var7);
         }

      }
   }

   static {
      field_150797_b = MarkerManager.getMarker("PACKET_SENT", NetworkManager.field_150738_b);
   }
}
