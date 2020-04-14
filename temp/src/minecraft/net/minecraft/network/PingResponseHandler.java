package net.minecraft.network;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PingResponseHandler extends ChannelInboundHandlerAdapter {
   private static final Logger field_151258_a = LogManager.getLogger();
   private NetworkSystem field_151257_b;

   public PingResponseHandler(NetworkSystem p_i45286_1_) {
      this.field_151257_b = p_i45286_1_;
   }

   public void channelRead(ChannelHandlerContext p_channelRead_1_, Object p_channelRead_2_) throws Exception {
      ByteBuf lvt_3_1_ = (ByteBuf)p_channelRead_2_;
      lvt_3_1_.markReaderIndex();
      boolean lvt_4_1_ = true;

      try {
         try {
            if (lvt_3_1_.readUnsignedByte() != 254) {
               return;
            }

            InetSocketAddress lvt_5_1_ = (InetSocketAddress)p_channelRead_1_.channel().remoteAddress();
            MinecraftServer lvt_6_1_ = this.field_151257_b.func_151267_d();
            int lvt_7_1_ = lvt_3_1_.readableBytes();
            String lvt_8_2_;
            switch(lvt_7_1_) {
            case 0:
               field_151258_a.debug("Ping: (<1.3.x) from {}:{}", lvt_5_1_.getAddress(), lvt_5_1_.getPort());
               lvt_8_2_ = String.format("%s\u00a7%d\u00a7%d", lvt_6_1_.func_71273_Y(), lvt_6_1_.func_71233_x(), lvt_6_1_.func_71275_y());
               this.func_151256_a(p_channelRead_1_, this.func_151255_a(lvt_8_2_));
               break;
            case 1:
               if (lvt_3_1_.readUnsignedByte() != 1) {
                  return;
               }

               field_151258_a.debug("Ping: (1.4-1.5.x) from {}:{}", lvt_5_1_.getAddress(), lvt_5_1_.getPort());
               lvt_8_2_ = String.format("\u00a71\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d", 127, lvt_6_1_.func_71249_w(), lvt_6_1_.func_71273_Y(), lvt_6_1_.func_71233_x(), lvt_6_1_.func_71275_y());
               this.func_151256_a(p_channelRead_1_, this.func_151255_a(lvt_8_2_));
               break;
            default:
               boolean lvt_8_3_ = lvt_3_1_.readUnsignedByte() == 1;
               lvt_8_3_ &= lvt_3_1_.readUnsignedByte() == 250;
               lvt_8_3_ &= "MC|PingHost".equals(new String(lvt_3_1_.readBytes(lvt_3_1_.readShort() * 2).array(), Charsets.UTF_16BE));
               int lvt_9_1_ = lvt_3_1_.readUnsignedShort();
               lvt_8_3_ &= lvt_3_1_.readUnsignedByte() >= 73;
               lvt_8_3_ &= 3 + lvt_3_1_.readBytes(lvt_3_1_.readShort() * 2).array().length + 4 == lvt_9_1_;
               lvt_8_3_ &= lvt_3_1_.readInt() <= 65535;
               lvt_8_3_ &= lvt_3_1_.readableBytes() == 0;
               if (!lvt_8_3_) {
                  return;
               }

               field_151258_a.debug("Ping: (1.6) from {}:{}", lvt_5_1_.getAddress(), lvt_5_1_.getPort());
               String lvt_10_1_ = String.format("\u00a71\u0000%d\u0000%s\u0000%s\u0000%d\u0000%d", 127, lvt_6_1_.func_71249_w(), lvt_6_1_.func_71273_Y(), lvt_6_1_.func_71233_x(), lvt_6_1_.func_71275_y());
               ByteBuf lvt_11_1_ = this.func_151255_a(lvt_10_1_);

               try {
                  this.func_151256_a(p_channelRead_1_, lvt_11_1_);
               } finally {
                  lvt_11_1_.release();
               }
            }

            lvt_3_1_.release();
            lvt_4_1_ = false;
         } catch (RuntimeException var21) {
         }

      } finally {
         if (lvt_4_1_) {
            lvt_3_1_.resetReaderIndex();
            p_channelRead_1_.channel().pipeline().remove("legacy_query");
            p_channelRead_1_.fireChannelRead(p_channelRead_2_);
         }

      }
   }

   private void func_151256_a(ChannelHandlerContext p_151256_1_, ByteBuf p_151256_2_) {
      p_151256_1_.pipeline().firstContext().writeAndFlush(p_151256_2_).addListener(ChannelFutureListener.CLOSE);
   }

   private ByteBuf func_151255_a(String p_151255_1_) {
      ByteBuf lvt_2_1_ = Unpooled.buffer();
      lvt_2_1_.writeByte(255);
      char[] lvt_3_1_ = p_151255_1_.toCharArray();
      lvt_2_1_.writeShort(lvt_3_1_.length);
      char[] lvt_4_1_ = lvt_3_1_;
      int lvt_5_1_ = lvt_3_1_.length;

      for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_; ++lvt_6_1_) {
         char lvt_7_1_ = lvt_4_1_[lvt_6_1_];
         lvt_2_1_.writeChar(lvt_7_1_);
      }

      return lvt_2_1_;
   }
}
