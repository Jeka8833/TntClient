package net.minecraft.network;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.client.network.NetHandlerHandshakeMemory;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;
import net.minecraft.util.ReportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkSystem {
   private static final Logger field_151275_b = LogManager.getLogger();
   public static final LazyLoadBase<NioEventLoopGroup> field_151276_c = new LazyLoadBase<NioEventLoopGroup>() {
      protected NioEventLoopGroup func_179280_b() {
         return new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Server IO #%d").setDaemon(true).build());
      }
   };
   public static final LazyLoadBase<EpollEventLoopGroup> field_181141_b = new LazyLoadBase<EpollEventLoopGroup>() {
      protected EpollEventLoopGroup func_179280_b() {
         return new EpollEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
      }
   };
   public static final LazyLoadBase<LocalEventLoopGroup> field_180232_b = new LazyLoadBase<LocalEventLoopGroup>() {
      protected LocalEventLoopGroup func_179280_b() {
         return new LocalEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
      }
   };
   private final MinecraftServer field_151273_d;
   public volatile boolean field_151277_a;
   private final List<ChannelFuture> field_151274_e = Collections.synchronizedList(Lists.newArrayList());
   private final List<NetworkManager> field_151272_f = Collections.synchronizedList(Lists.newArrayList());

   public NetworkSystem(MinecraftServer p_i45292_1_) {
      this.field_151273_d = p_i45292_1_;
      this.field_151277_a = true;
   }

   public void func_151265_a(InetAddress p_151265_1_, int p_151265_2_) throws IOException {
      synchronized(this.field_151274_e) {
         Class lvt_4_2_;
         LazyLoadBase lvt_5_2_;
         if (Epoll.isAvailable() && this.field_151273_d.func_181035_ah()) {
            lvt_4_2_ = EpollServerSocketChannel.class;
            lvt_5_2_ = field_181141_b;
            field_151275_b.info("Using epoll channel type");
         } else {
            lvt_4_2_ = NioServerSocketChannel.class;
            lvt_5_2_ = field_151276_c;
            field_151275_b.info("Using default channel type");
         }

         this.field_151274_e.add(((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(lvt_4_2_)).childHandler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel p_initChannel_1_) throws Exception {
               try {
                  p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, true);
               } catch (ChannelException var3) {
               }

               p_initChannel_1_.pipeline().addLast((String)"timeout", (ChannelHandler)(new ReadTimeoutHandler(30))).addLast((String)"legacy_query", (ChannelHandler)(new PingResponseHandler(NetworkSystem.this))).addLast((String)"splitter", (ChannelHandler)(new MessageDeserializer2())).addLast((String)"decoder", (ChannelHandler)(new MessageDeserializer(EnumPacketDirection.SERVERBOUND))).addLast((String)"prepender", (ChannelHandler)(new MessageSerializer2())).addLast((String)"encoder", (ChannelHandler)(new MessageSerializer(EnumPacketDirection.CLIENTBOUND)));
               NetworkManager lvt_2_1_ = new NetworkManager(EnumPacketDirection.SERVERBOUND);
               NetworkSystem.this.field_151272_f.add(lvt_2_1_);
               p_initChannel_1_.pipeline().addLast((String)"packet_handler", (ChannelHandler)lvt_2_1_);
               lvt_2_1_.func_150719_a(new NetHandlerHandshakeTCP(NetworkSystem.this.field_151273_d, lvt_2_1_));
            }
         }).group((EventLoopGroup)lvt_5_2_.func_179281_c()).localAddress(p_151265_1_, p_151265_2_)).bind().syncUninterruptibly());
      }
   }

   public SocketAddress func_151270_a() {
      ChannelFuture lvt_1_2_;
      synchronized(this.field_151274_e) {
         lvt_1_2_ = ((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(LocalServerChannel.class)).childHandler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel p_initChannel_1_) throws Exception {
               NetworkManager lvt_2_1_ = new NetworkManager(EnumPacketDirection.SERVERBOUND);
               lvt_2_1_.func_150719_a(new NetHandlerHandshakeMemory(NetworkSystem.this.field_151273_d, lvt_2_1_));
               NetworkSystem.this.field_151272_f.add(lvt_2_1_);
               p_initChannel_1_.pipeline().addLast((String)"packet_handler", (ChannelHandler)lvt_2_1_);
            }
         }).group((EventLoopGroup)field_151276_c.func_179281_c()).localAddress(LocalAddress.ANY)).bind().syncUninterruptibly();
         this.field_151274_e.add(lvt_1_2_);
      }

      return lvt_1_2_.channel().localAddress();
   }

   public void func_151268_b() {
      this.field_151277_a = false;
      Iterator lvt_1_1_ = this.field_151274_e.iterator();

      while(lvt_1_1_.hasNext()) {
         ChannelFuture lvt_2_1_ = (ChannelFuture)lvt_1_1_.next();

         try {
            lvt_2_1_.channel().close().sync();
         } catch (InterruptedException var4) {
            field_151275_b.error("Interrupted whilst closing channel");
         }
      }

   }

   public void func_151269_c() {
      synchronized(this.field_151272_f) {
         Iterator lvt_2_1_ = this.field_151272_f.iterator();

         while(true) {
            while(true) {
               final NetworkManager lvt_3_1_;
               do {
                  if (!lvt_2_1_.hasNext()) {
                     return;
                  }

                  lvt_3_1_ = (NetworkManager)lvt_2_1_.next();
               } while(lvt_3_1_.func_179291_h());

               if (!lvt_3_1_.func_150724_d()) {
                  lvt_2_1_.remove();
                  lvt_3_1_.func_179293_l();
               } else {
                  try {
                     lvt_3_1_.func_74428_b();
                  } catch (Exception var8) {
                     if (lvt_3_1_.func_150731_c()) {
                        CrashReport lvt_5_1_ = CrashReport.func_85055_a(var8, "Ticking memory connection");
                        CrashReportCategory lvt_6_1_ = lvt_5_1_.func_85058_a("Ticking connection");
                        lvt_6_1_.func_71500_a("Connection", new Callable<String>() {
                           public String call() throws Exception {
                              return lvt_3_1_.toString();
                           }
                        });
                        throw new ReportedException(lvt_5_1_);
                     }

                     field_151275_b.warn((String)("Failed to handle packet for " + lvt_3_1_.func_74430_c()), (Throwable)var8);
                     final ChatComponentText lvt_5_2_ = new ChatComponentText("Internal server error");
                     lvt_3_1_.func_179288_a(new S40PacketDisconnect(lvt_5_2_), new GenericFutureListener<Future<? super Void>>() {
                        public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
                           lvt_3_1_.func_150718_a(lvt_5_2_);
                        }
                     });
                     lvt_3_1_.func_150721_g();
                  }
               }
            }
         }
      }
   }

   public MinecraftServer func_151267_d() {
      return this.field_151273_d;
   }
}
