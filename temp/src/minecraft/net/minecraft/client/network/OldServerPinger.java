package net.minecraft.client.network;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldServerPinger {
   private static final Splitter field_147230_a = Splitter.on('\u0000').limit(6);
   private static final Logger field_147228_b = LogManager.getLogger();
   private final List<NetworkManager> field_147229_c = Collections.synchronizedList(Lists.newArrayList());

   public void func_147224_a(final ServerData p_147224_1_) throws UnknownHostException {
      ServerAddress lvt_2_1_ = ServerAddress.func_78860_a(p_147224_1_.field_78845_b);
      final NetworkManager lvt_3_1_ = NetworkManager.func_181124_a(InetAddress.getByName(lvt_2_1_.func_78861_a()), lvt_2_1_.func_78864_b(), false);
      this.field_147229_c.add(lvt_3_1_);
      p_147224_1_.field_78843_d = "Pinging...";
      p_147224_1_.field_78844_e = -1L;
      p_147224_1_.field_147412_i = null;
      lvt_3_1_.func_150719_a(new INetHandlerStatusClient() {
         private boolean field_147403_d = false;
         private boolean field_183009_e = false;
         private long field_175092_e = 0L;

         public void func_147397_a(S00PacketServerInfo p_147397_1_) {
            if (this.field_183009_e) {
               lvt_3_1_.func_150718_a(new ChatComponentText("Received unrequested status"));
            } else {
               this.field_183009_e = true;
               ServerStatusResponse lvt_2_1_ = p_147397_1_.func_149294_c();
               if (lvt_2_1_.func_151317_a() != null) {
                  p_147224_1_.field_78843_d = lvt_2_1_.func_151317_a().func_150254_d();
               } else {
                  p_147224_1_.field_78843_d = "";
               }

               if (lvt_2_1_.func_151322_c() != null) {
                  p_147224_1_.field_82822_g = lvt_2_1_.func_151322_c().func_151303_a();
                  p_147224_1_.field_82821_f = lvt_2_1_.func_151322_c().func_151304_b();
               } else {
                  p_147224_1_.field_82822_g = "Old";
                  p_147224_1_.field_82821_f = 0;
               }

               if (lvt_2_1_.func_151318_b() != null) {
                  p_147224_1_.field_78846_c = EnumChatFormatting.GRAY + "" + lvt_2_1_.func_151318_b().func_151333_b() + "" + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + lvt_2_1_.func_151318_b().func_151332_a();
                  if (ArrayUtils.isNotEmpty((Object[])lvt_2_1_.func_151318_b().func_151331_c())) {
                     StringBuilder lvt_3_1_x = new StringBuilder();
                     GameProfile[] lvt_4_1_ = lvt_2_1_.func_151318_b().func_151331_c();
                     int lvt_5_1_ = lvt_4_1_.length;

                     for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_; ++lvt_6_1_) {
                        GameProfile lvt_7_1_ = lvt_4_1_[lvt_6_1_];
                        if (lvt_3_1_x.length() > 0) {
                           lvt_3_1_x.append("\n");
                        }

                        lvt_3_1_x.append(lvt_7_1_.getName());
                     }

                     if (lvt_2_1_.func_151318_b().func_151331_c().length < lvt_2_1_.func_151318_b().func_151333_b()) {
                        if (lvt_3_1_x.length() > 0) {
                           lvt_3_1_x.append("\n");
                        }

                        lvt_3_1_x.append("... and ").append(lvt_2_1_.func_151318_b().func_151333_b() - lvt_2_1_.func_151318_b().func_151331_c().length).append(" more ...");
                     }

                     p_147224_1_.field_147412_i = lvt_3_1_x.toString();
                  }
               } else {
                  p_147224_1_.field_78846_c = EnumChatFormatting.DARK_GRAY + "???";
               }

               if (lvt_2_1_.func_151316_d() != null) {
                  String lvt_3_2_ = lvt_2_1_.func_151316_d();
                  if (lvt_3_2_.startsWith("data:image/png;base64,")) {
                     p_147224_1_.func_147407_a(lvt_3_2_.substring("data:image/png;base64,".length()));
                  } else {
                     OldServerPinger.field_147228_b.error("Invalid server icon (unknown format)");
                  }
               } else {
                  p_147224_1_.func_147407_a((String)null);
               }

               this.field_175092_e = Minecraft.func_71386_F();
               lvt_3_1_.func_179290_a(new C01PacketPing(this.field_175092_e));
               this.field_147403_d = true;
            }
         }

         public void func_147398_a(S01PacketPong p_147398_1_) {
            long lvt_2_1_ = this.field_175092_e;
            long lvt_4_1_ = Minecraft.func_71386_F();
            p_147224_1_.field_78844_e = lvt_4_1_ - lvt_2_1_;
            lvt_3_1_.func_150718_a(new ChatComponentText("Finished"));
         }

         public void func_147231_a(IChatComponent p_147231_1_) {
            if (!this.field_147403_d) {
               OldServerPinger.field_147228_b.error("Can't ping " + p_147224_1_.field_78845_b + ": " + p_147231_1_.func_150260_c());
               p_147224_1_.field_78843_d = EnumChatFormatting.DARK_RED + "Can't connect to server.";
               p_147224_1_.field_78846_c = "";
               OldServerPinger.this.func_147225_b(p_147224_1_);
            }

         }
      });

      try {
         lvt_3_1_.func_179290_a(new C00Handshake(47, lvt_2_1_.func_78861_a(), lvt_2_1_.func_78864_b(), EnumConnectionState.STATUS));
         lvt_3_1_.func_179290_a(new C00PacketServerQuery());
      } catch (Throwable var5) {
         field_147228_b.error((Object)var5);
      }

   }

   private void func_147225_b(final ServerData p_147225_1_) {
      final ServerAddress lvt_2_1_ = ServerAddress.func_78860_a(p_147225_1_.field_78845_b);
      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)NetworkManager.field_179295_d.func_179281_c())).handler(new ChannelInitializer<Channel>() {
         protected void initChannel(Channel p_initChannel_1_) throws Exception {
            try {
               p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, true);
            } catch (ChannelException var3) {
            }

            p_initChannel_1_.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
               public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
                  super.channelActive(p_channelActive_1_);
                  ByteBuf lvt_2_1_x = Unpooled.buffer();

                  try {
                     lvt_2_1_x.writeByte(254);
                     lvt_2_1_x.writeByte(1);
                     lvt_2_1_x.writeByte(250);
                     char[] lvt_3_1_ = "MC|PingHost".toCharArray();
                     lvt_2_1_x.writeShort(lvt_3_1_.length);
                     char[] lvt_4_2_ = lvt_3_1_;
                     int lvt_5_2_ = lvt_3_1_.length;

                     int lvt_6_2_;
                     char lvt_7_2_;
                     for(lvt_6_2_ = 0; lvt_6_2_ < lvt_5_2_; ++lvt_6_2_) {
                        lvt_7_2_ = lvt_4_2_[lvt_6_2_];
                        lvt_2_1_x.writeChar(lvt_7_2_);
                     }

                     lvt_2_1_x.writeShort(7 + 2 * lvt_2_1_.func_78861_a().length());
                     lvt_2_1_x.writeByte(127);
                     lvt_3_1_ = lvt_2_1_.func_78861_a().toCharArray();
                     lvt_2_1_x.writeShort(lvt_3_1_.length);
                     lvt_4_2_ = lvt_3_1_;
                     lvt_5_2_ = lvt_3_1_.length;

                     for(lvt_6_2_ = 0; lvt_6_2_ < lvt_5_2_; ++lvt_6_2_) {
                        lvt_7_2_ = lvt_4_2_[lvt_6_2_];
                        lvt_2_1_x.writeChar(lvt_7_2_);
                     }

                     lvt_2_1_x.writeInt(lvt_2_1_.func_78864_b());
                     p_channelActive_1_.channel().writeAndFlush(lvt_2_1_x).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                  } finally {
                     lvt_2_1_x.release();
                  }
               }

               protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, ByteBuf p_channelRead0_2_) throws Exception {
                  short lvt_3_1_ = p_channelRead0_2_.readUnsignedByte();
                  if (lvt_3_1_ == 255) {
                     String lvt_4_1_ = new String(p_channelRead0_2_.readBytes(p_channelRead0_2_.readShort() * 2).array(), Charsets.UTF_16BE);
                     String[] lvt_5_1_ = (String[])Iterables.toArray(OldServerPinger.field_147230_a.split(lvt_4_1_), String.class);
                     if ("\u00a71".equals(lvt_5_1_[0])) {
                        int lvt_6_1_ = MathHelper.func_82715_a(lvt_5_1_[1], 0);
                        String lvt_7_1_ = lvt_5_1_[2];
                        String lvt_8_1_ = lvt_5_1_[3];
                        int lvt_9_1_ = MathHelper.func_82715_a(lvt_5_1_[4], -1);
                        int lvt_10_1_ = MathHelper.func_82715_a(lvt_5_1_[5], -1);
                        p_147225_1_.field_82821_f = -1;
                        p_147225_1_.field_82822_g = lvt_7_1_;
                        p_147225_1_.field_78843_d = lvt_8_1_;
                        p_147225_1_.field_78846_c = EnumChatFormatting.GRAY + "" + lvt_9_1_ + "" + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + lvt_10_1_;
                     }
                  }

                  p_channelRead0_1_.close();
               }

               public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) throws Exception {
                  p_exceptionCaught_1_.close();
               }
            });
         }
      })).channel(NioSocketChannel.class)).connect(lvt_2_1_.func_78861_a(), lvt_2_1_.func_78864_b());
   }

   public void func_147223_a() {
      synchronized(this.field_147229_c) {
         Iterator lvt_2_1_ = this.field_147229_c.iterator();

         while(lvt_2_1_.hasNext()) {
            NetworkManager lvt_3_1_ = (NetworkManager)lvt_2_1_.next();
            if (lvt_3_1_.func_150724_d()) {
               lvt_3_1_.func_74428_b();
            } else {
               lvt_2_1_.remove();
               lvt_3_1_.func_179293_l();
            }
         }

      }
   }

   public void func_147226_b() {
      synchronized(this.field_147229_c) {
         Iterator lvt_2_1_ = this.field_147229_c.iterator();

         while(lvt_2_1_.hasNext()) {
            NetworkManager lvt_3_1_ = (NetworkManager)lvt_2_1_.next();
            if (lvt_3_1_.func_150724_d()) {
               lvt_2_1_.remove();
               lvt_3_1_.func_150718_a(new ChatComponentText("Cancelled"));
            }
         }

      }
   }
}
