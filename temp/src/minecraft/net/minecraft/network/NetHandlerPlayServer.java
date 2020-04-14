package net.minecraft.network;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.util.concurrent.Futures;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.block.material.Material;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayServer implements INetHandlerPlayServer, ITickable {
   private static final Logger field_147370_c = LogManager.getLogger();
   public final NetworkManager field_147371_a;
   private final MinecraftServer field_147367_d;
   public EntityPlayerMP field_147369_b;
   private int field_147368_e;
   private int field_175090_f;
   private int field_147365_f;
   private boolean field_147366_g;
   private int field_147378_h;
   private long field_147379_i;
   private long field_147377_k;
   private int field_147374_l;
   private int field_147375_m;
   private IntHashMap<Short> field_147372_n = new IntHashMap();
   private double field_147373_o;
   private double field_147382_p;
   private double field_147381_q;
   private boolean field_147380_r = true;

   public NetHandlerPlayServer(MinecraftServer p_i1530_1_, NetworkManager p_i1530_2_, EntityPlayerMP p_i1530_3_) {
      this.field_147367_d = p_i1530_1_;
      this.field_147371_a = p_i1530_2_;
      p_i1530_2_.func_150719_a(this);
      this.field_147369_b = p_i1530_3_;
      p_i1530_3_.field_71135_a = this;
   }

   public void func_73660_a() {
      this.field_147366_g = false;
      ++this.field_147368_e;
      this.field_147367_d.field_71304_b.func_76320_a("keepAlive");
      if ((long)this.field_147368_e - this.field_147377_k > 40L) {
         this.field_147377_k = (long)this.field_147368_e;
         this.field_147379_i = this.func_147363_d();
         this.field_147378_h = (int)this.field_147379_i;
         this.func_147359_a(new S00PacketKeepAlive(this.field_147378_h));
      }

      this.field_147367_d.field_71304_b.func_76319_b();
      if (this.field_147374_l > 0) {
         --this.field_147374_l;
      }

      if (this.field_147375_m > 0) {
         --this.field_147375_m;
      }

      if (this.field_147369_b.func_154331_x() > 0L && this.field_147367_d.func_143007_ar() > 0 && MinecraftServer.func_130071_aq() - this.field_147369_b.func_154331_x() > (long)(this.field_147367_d.func_143007_ar() * 1000 * 60)) {
         this.func_147360_c("You have been idle for too long!");
      }

   }

   public NetworkManager func_147362_b() {
      return this.field_147371_a;
   }

   public void func_147360_c(String p_147360_1_) {
      final ChatComponentText lvt_2_1_ = new ChatComponentText(p_147360_1_);
      this.field_147371_a.func_179288_a(new S40PacketDisconnect(lvt_2_1_), new GenericFutureListener<Future<? super Void>>() {
         public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
            NetHandlerPlayServer.this.field_147371_a.func_150718_a(lvt_2_1_);
         }
      });
      this.field_147371_a.func_150721_g();
      Futures.getUnchecked(this.field_147367_d.func_152344_a(new Runnable() {
         public void run() {
            NetHandlerPlayServer.this.field_147371_a.func_179293_l();
         }
      }));
   }

   public void func_147358_a(C0CPacketInput p_147358_1_) {
      PacketThreadUtil.func_180031_a(p_147358_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_110430_a(p_147358_1_.func_149620_c(), p_147358_1_.func_149616_d(), p_147358_1_.func_149618_e(), p_147358_1_.func_149617_f());
   }

   private boolean func_183006_b(C03PacketPlayer p_183006_1_) {
      return !Doubles.isFinite(p_183006_1_.func_149464_c()) || !Doubles.isFinite(p_183006_1_.func_149467_d()) || !Doubles.isFinite(p_183006_1_.func_149472_e()) || !Floats.isFinite(p_183006_1_.func_149470_h()) || !Floats.isFinite(p_183006_1_.func_149462_g());
   }

   public void func_147347_a(C03PacketPlayer p_147347_1_) {
      PacketThreadUtil.func_180031_a(p_147347_1_, this, this.field_147369_b.func_71121_q());
      if (this.func_183006_b(p_147347_1_)) {
         this.func_147360_c("Invalid move packet received");
      } else {
         WorldServer lvt_2_1_ = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
         this.field_147366_g = true;
         if (!this.field_147369_b.field_71136_j) {
            double lvt_3_1_ = this.field_147369_b.field_70165_t;
            double lvt_5_1_ = this.field_147369_b.field_70163_u;
            double lvt_7_1_ = this.field_147369_b.field_70161_v;
            double lvt_9_1_ = 0.0D;
            double lvt_11_1_ = p_147347_1_.func_149464_c() - this.field_147373_o;
            double lvt_13_1_ = p_147347_1_.func_149467_d() - this.field_147382_p;
            double lvt_15_1_ = p_147347_1_.func_149472_e() - this.field_147381_q;
            if (p_147347_1_.func_149466_j()) {
               lvt_9_1_ = lvt_11_1_ * lvt_11_1_ + lvt_13_1_ * lvt_13_1_ + lvt_15_1_ * lvt_15_1_;
               if (!this.field_147380_r && lvt_9_1_ < 0.25D) {
                  this.field_147380_r = true;
               }
            }

            if (this.field_147380_r) {
               this.field_175090_f = this.field_147368_e;
               double lvt_19_2_;
               double lvt_21_2_;
               double lvt_23_2_;
               if (this.field_147369_b.field_70154_o != null) {
                  float lvt_17_1_ = this.field_147369_b.field_70177_z;
                  float lvt_18_1_ = this.field_147369_b.field_70125_A;
                  this.field_147369_b.field_70154_o.func_70043_V();
                  lvt_19_2_ = this.field_147369_b.field_70165_t;
                  lvt_21_2_ = this.field_147369_b.field_70163_u;
                  lvt_23_2_ = this.field_147369_b.field_70161_v;
                  if (p_147347_1_.func_149463_k()) {
                     lvt_17_1_ = p_147347_1_.func_149462_g();
                     lvt_18_1_ = p_147347_1_.func_149470_h();
                  }

                  this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
                  this.field_147369_b.func_71127_g();
                  this.field_147369_b.func_70080_a(lvt_19_2_, lvt_21_2_, lvt_23_2_, lvt_17_1_, lvt_18_1_);
                  if (this.field_147369_b.field_70154_o != null) {
                     this.field_147369_b.field_70154_o.func_70043_V();
                  }

                  this.field_147367_d.func_71203_ab().func_72358_d(this.field_147369_b);
                  if (this.field_147369_b.field_70154_o != null) {
                     if (lvt_9_1_ > 4.0D) {
                        Entity lvt_25_1_ = this.field_147369_b.field_70154_o;
                        this.field_147369_b.field_71135_a.func_147359_a(new S18PacketEntityTeleport(lvt_25_1_));
                        this.func_147364_a(this.field_147369_b.field_70165_t, this.field_147369_b.field_70163_u, this.field_147369_b.field_70161_v, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                     }

                     this.field_147369_b.field_70154_o.field_70160_al = true;
                  }

                  if (this.field_147380_r) {
                     this.field_147373_o = this.field_147369_b.field_70165_t;
                     this.field_147382_p = this.field_147369_b.field_70163_u;
                     this.field_147381_q = this.field_147369_b.field_70161_v;
                  }

                  lvt_2_1_.func_72870_g(this.field_147369_b);
                  return;
               }

               if (this.field_147369_b.func_70608_bn()) {
                  this.field_147369_b.func_71127_g();
                  this.field_147369_b.func_70080_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                  lvt_2_1_.func_72870_g(this.field_147369_b);
                  return;
               }

               double lvt_17_2_ = this.field_147369_b.field_70163_u;
               this.field_147373_o = this.field_147369_b.field_70165_t;
               this.field_147382_p = this.field_147369_b.field_70163_u;
               this.field_147381_q = this.field_147369_b.field_70161_v;
               lvt_19_2_ = this.field_147369_b.field_70165_t;
               lvt_21_2_ = this.field_147369_b.field_70163_u;
               lvt_23_2_ = this.field_147369_b.field_70161_v;
               float lvt_25_2_ = this.field_147369_b.field_70177_z;
               float lvt_26_1_ = this.field_147369_b.field_70125_A;
               if (p_147347_1_.func_149466_j() && p_147347_1_.func_149467_d() == -999.0D) {
                  p_147347_1_.func_149469_a(false);
               }

               if (p_147347_1_.func_149466_j()) {
                  lvt_19_2_ = p_147347_1_.func_149464_c();
                  lvt_21_2_ = p_147347_1_.func_149467_d();
                  lvt_23_2_ = p_147347_1_.func_149472_e();
                  if (Math.abs(p_147347_1_.func_149464_c()) > 3.0E7D || Math.abs(p_147347_1_.func_149472_e()) > 3.0E7D) {
                     this.func_147360_c("Illegal position");
                     return;
                  }
               }

               if (p_147347_1_.func_149463_k()) {
                  lvt_25_2_ = p_147347_1_.func_149462_g();
                  lvt_26_1_ = p_147347_1_.func_149470_h();
               }

               this.field_147369_b.func_71127_g();
               this.field_147369_b.func_70080_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, lvt_25_2_, lvt_26_1_);
               if (!this.field_147380_r) {
                  return;
               }

               double lvt_27_1_ = lvt_19_2_ - this.field_147369_b.field_70165_t;
               double lvt_29_1_ = lvt_21_2_ - this.field_147369_b.field_70163_u;
               double lvt_31_1_ = lvt_23_2_ - this.field_147369_b.field_70161_v;
               double lvt_33_1_ = this.field_147369_b.field_70159_w * this.field_147369_b.field_70159_w + this.field_147369_b.field_70181_x * this.field_147369_b.field_70181_x + this.field_147369_b.field_70179_y * this.field_147369_b.field_70179_y;
               double lvt_35_1_ = lvt_27_1_ * lvt_27_1_ + lvt_29_1_ * lvt_29_1_ + lvt_31_1_ * lvt_31_1_;
               if (lvt_35_1_ - lvt_33_1_ > 100.0D && (!this.field_147367_d.func_71264_H() || !this.field_147367_d.func_71214_G().equals(this.field_147369_b.func_70005_c_()))) {
                  field_147370_c.warn(this.field_147369_b.func_70005_c_() + " moved too quickly! " + lvt_27_1_ + "," + lvt_29_1_ + "," + lvt_31_1_ + " (" + lvt_27_1_ + ", " + lvt_29_1_ + ", " + lvt_31_1_ + ")");
                  this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                  return;
               }

               float lvt_37_1_ = 0.0625F;
               boolean lvt_38_1_ = lvt_2_1_.func_72945_a(this.field_147369_b, this.field_147369_b.func_174813_aQ().func_72331_e((double)lvt_37_1_, (double)lvt_37_1_, (double)lvt_37_1_)).isEmpty();
               if (this.field_147369_b.field_70122_E && !p_147347_1_.func_149465_i() && lvt_29_1_ > 0.0D) {
                  this.field_147369_b.func_70664_aZ();
               }

               this.field_147369_b.func_70091_d(lvt_27_1_, lvt_29_1_, lvt_31_1_);
               this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
               double lvt_39_1_ = lvt_29_1_;
               lvt_27_1_ = lvt_19_2_ - this.field_147369_b.field_70165_t;
               lvt_29_1_ = lvt_21_2_ - this.field_147369_b.field_70163_u;
               if (lvt_29_1_ > -0.5D || lvt_29_1_ < 0.5D) {
                  lvt_29_1_ = 0.0D;
               }

               lvt_31_1_ = lvt_23_2_ - this.field_147369_b.field_70161_v;
               lvt_35_1_ = lvt_27_1_ * lvt_27_1_ + lvt_29_1_ * lvt_29_1_ + lvt_31_1_ * lvt_31_1_;
               boolean lvt_41_1_ = false;
               if (lvt_35_1_ > 0.0625D && !this.field_147369_b.func_70608_bn() && !this.field_147369_b.field_71134_c.func_73083_d()) {
                  lvt_41_1_ = true;
                  field_147370_c.warn(this.field_147369_b.func_70005_c_() + " moved wrongly!");
               }

               this.field_147369_b.func_70080_a(lvt_19_2_, lvt_21_2_, lvt_23_2_, lvt_25_2_, lvt_26_1_);
               this.field_147369_b.func_71000_j(this.field_147369_b.field_70165_t - lvt_3_1_, this.field_147369_b.field_70163_u - lvt_5_1_, this.field_147369_b.field_70161_v - lvt_7_1_);
               if (!this.field_147369_b.field_70145_X) {
                  boolean lvt_42_1_ = lvt_2_1_.func_72945_a(this.field_147369_b, this.field_147369_b.func_174813_aQ().func_72331_e((double)lvt_37_1_, (double)lvt_37_1_, (double)lvt_37_1_)).isEmpty();
                  if (lvt_38_1_ && (lvt_41_1_ || !lvt_42_1_) && !this.field_147369_b.func_70608_bn()) {
                     this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, lvt_25_2_, lvt_26_1_);
                     return;
                  }
               }

               AxisAlignedBB lvt_42_2_ = this.field_147369_b.func_174813_aQ().func_72314_b((double)lvt_37_1_, (double)lvt_37_1_, (double)lvt_37_1_).func_72321_a(0.0D, -0.55D, 0.0D);
               if (!this.field_147367_d.func_71231_X() && !this.field_147369_b.field_71075_bZ.field_75101_c && !lvt_2_1_.func_72829_c(lvt_42_2_)) {
                  if (lvt_39_1_ >= -0.03125D) {
                     ++this.field_147365_f;
                     if (this.field_147365_f > 80) {
                        field_147370_c.warn(this.field_147369_b.func_70005_c_() + " was kicked for floating too long!");
                        this.func_147360_c("Flying is not enabled on this server");
                        return;
                     }
                  }
               } else {
                  this.field_147365_f = 0;
               }

               this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
               this.field_147367_d.func_71203_ab().func_72358_d(this.field_147369_b);
               this.field_147369_b.func_71122_b(this.field_147369_b.field_70163_u - lvt_17_2_, p_147347_1_.func_149465_i());
            } else if (this.field_147368_e - this.field_175090_f > 20) {
               this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
            }

         }
      }
   }

   public void func_147364_a(double p_147364_1_, double p_147364_3_, double p_147364_5_, float p_147364_7_, float p_147364_8_) {
      this.func_175089_a(p_147364_1_, p_147364_3_, p_147364_5_, p_147364_7_, p_147364_8_, Collections.emptySet());
   }

   public void func_175089_a(double p_175089_1_, double p_175089_3_, double p_175089_5_, float p_175089_7_, float p_175089_8_, Set<S08PacketPlayerPosLook.EnumFlags> p_175089_9_) {
      this.field_147380_r = false;
      this.field_147373_o = p_175089_1_;
      this.field_147382_p = p_175089_3_;
      this.field_147381_q = p_175089_5_;
      if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.X)) {
         this.field_147373_o += this.field_147369_b.field_70165_t;
      }

      if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
         this.field_147382_p += this.field_147369_b.field_70163_u;
      }

      if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Z)) {
         this.field_147381_q += this.field_147369_b.field_70161_v;
      }

      float lvt_10_1_ = p_175089_7_;
      float lvt_11_1_ = p_175089_8_;
      if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
         lvt_10_1_ = p_175089_7_ + this.field_147369_b.field_70177_z;
      }

      if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.X_ROT)) {
         lvt_11_1_ = p_175089_8_ + this.field_147369_b.field_70125_A;
      }

      this.field_147369_b.func_70080_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, lvt_10_1_, lvt_11_1_);
      this.field_147369_b.field_71135_a.func_147359_a(new S08PacketPlayerPosLook(p_175089_1_, p_175089_3_, p_175089_5_, p_175089_7_, p_175089_8_, p_175089_9_));
   }

   public void func_147345_a(C07PacketPlayerDigging p_147345_1_) {
      PacketThreadUtil.func_180031_a(p_147345_1_, this, this.field_147369_b.func_71121_q());
      WorldServer lvt_2_1_ = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      BlockPos lvt_3_1_ = p_147345_1_.func_179715_a();
      this.field_147369_b.func_143004_u();
      switch(p_147345_1_.func_180762_c()) {
      case DROP_ITEM:
         if (!this.field_147369_b.func_175149_v()) {
            this.field_147369_b.func_71040_bB(false);
         }

         return;
      case DROP_ALL_ITEMS:
         if (!this.field_147369_b.func_175149_v()) {
            this.field_147369_b.func_71040_bB(true);
         }

         return;
      case RELEASE_USE_ITEM:
         this.field_147369_b.func_71034_by();
         return;
      case START_DESTROY_BLOCK:
      case ABORT_DESTROY_BLOCK:
      case STOP_DESTROY_BLOCK:
         double lvt_4_1_ = this.field_147369_b.field_70165_t - ((double)lvt_3_1_.func_177958_n() + 0.5D);
         double lvt_6_1_ = this.field_147369_b.field_70163_u - ((double)lvt_3_1_.func_177956_o() + 0.5D) + 1.5D;
         double lvt_8_1_ = this.field_147369_b.field_70161_v - ((double)lvt_3_1_.func_177952_p() + 0.5D);
         double lvt_10_1_ = lvt_4_1_ * lvt_4_1_ + lvt_6_1_ * lvt_6_1_ + lvt_8_1_ * lvt_8_1_;
         if (lvt_10_1_ > 36.0D) {
            return;
         } else if (lvt_3_1_.func_177956_o() >= this.field_147367_d.func_71207_Z()) {
            return;
         } else {
            if (p_147345_1_.func_180762_c() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
               if (!this.field_147367_d.func_175579_a(lvt_2_1_, lvt_3_1_, this.field_147369_b) && lvt_2_1_.func_175723_af().func_177746_a(lvt_3_1_)) {
                  this.field_147369_b.field_71134_c.func_180784_a(lvt_3_1_, p_147345_1_.func_179714_b());
               } else {
                  this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(lvt_2_1_, lvt_3_1_));
               }
            } else {
               if (p_147345_1_.func_180762_c() == C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                  this.field_147369_b.field_71134_c.func_180785_a(lvt_3_1_);
               } else if (p_147345_1_.func_180762_c() == C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK) {
                  this.field_147369_b.field_71134_c.func_180238_e();
               }

               if (lvt_2_1_.func_180495_p(lvt_3_1_).func_177230_c().func_149688_o() != Material.field_151579_a) {
                  this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(lvt_2_1_, lvt_3_1_));
               }
            }

            return;
         }
      default:
         throw new IllegalArgumentException("Invalid player action");
      }
   }

   public void func_147346_a(C08PacketPlayerBlockPlacement p_147346_1_) {
      PacketThreadUtil.func_180031_a(p_147346_1_, this, this.field_147369_b.func_71121_q());
      WorldServer lvt_2_1_ = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      ItemStack lvt_3_1_ = this.field_147369_b.field_71071_by.func_70448_g();
      boolean lvt_4_1_ = false;
      BlockPos lvt_5_1_ = p_147346_1_.func_179724_a();
      EnumFacing lvt_6_1_ = EnumFacing.func_82600_a(p_147346_1_.func_149568_f());
      this.field_147369_b.func_143004_u();
      if (p_147346_1_.func_149568_f() == 255) {
         if (lvt_3_1_ == null) {
            return;
         }

         this.field_147369_b.field_71134_c.func_73085_a(this.field_147369_b, lvt_2_1_, lvt_3_1_);
      } else if (lvt_5_1_.func_177956_o() >= this.field_147367_d.func_71207_Z() - 1 && (lvt_6_1_ == EnumFacing.UP || lvt_5_1_.func_177956_o() >= this.field_147367_d.func_71207_Z())) {
         ChatComponentTranslation lvt_7_1_ = new ChatComponentTranslation("build.tooHigh", new Object[]{this.field_147367_d.func_71207_Z()});
         lvt_7_1_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         this.field_147369_b.field_71135_a.func_147359_a(new S02PacketChat(lvt_7_1_));
         lvt_4_1_ = true;
      } else {
         if (this.field_147380_r && this.field_147369_b.func_70092_e((double)lvt_5_1_.func_177958_n() + 0.5D, (double)lvt_5_1_.func_177956_o() + 0.5D, (double)lvt_5_1_.func_177952_p() + 0.5D) < 64.0D && !this.field_147367_d.func_175579_a(lvt_2_1_, lvt_5_1_, this.field_147369_b) && lvt_2_1_.func_175723_af().func_177746_a(lvt_5_1_)) {
            this.field_147369_b.field_71134_c.func_180236_a(this.field_147369_b, lvt_2_1_, lvt_3_1_, lvt_5_1_, lvt_6_1_, p_147346_1_.func_149573_h(), p_147346_1_.func_149569_i(), p_147346_1_.func_149575_j());
         }

         lvt_4_1_ = true;
      }

      if (lvt_4_1_) {
         this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(lvt_2_1_, lvt_5_1_));
         this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(lvt_2_1_, lvt_5_1_.func_177972_a(lvt_6_1_)));
      }

      lvt_3_1_ = this.field_147369_b.field_71071_by.func_70448_g();
      if (lvt_3_1_ != null && lvt_3_1_.field_77994_a == 0) {
         this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c] = null;
         lvt_3_1_ = null;
      }

      if (lvt_3_1_ == null || lvt_3_1_.func_77988_m() == 0) {
         this.field_147369_b.field_71137_h = true;
         this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c] = ItemStack.func_77944_b(this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c]);
         Slot lvt_7_2_ = this.field_147369_b.field_71070_bA.func_75147_a(this.field_147369_b.field_71071_by, this.field_147369_b.field_71071_by.field_70461_c);
         this.field_147369_b.field_71070_bA.func_75142_b();
         this.field_147369_b.field_71137_h = false;
         if (!ItemStack.func_77989_b(this.field_147369_b.field_71071_by.func_70448_g(), p_147346_1_.func_149574_g())) {
            this.func_147359_a(new S2FPacketSetSlot(this.field_147369_b.field_71070_bA.field_75152_c, lvt_7_2_.field_75222_d, this.field_147369_b.field_71071_by.func_70448_g()));
         }
      }

   }

   public void func_175088_a(C18PacketSpectate p_175088_1_) {
      PacketThreadUtil.func_180031_a(p_175088_1_, this, this.field_147369_b.func_71121_q());
      if (this.field_147369_b.func_175149_v()) {
         Entity lvt_2_1_ = null;
         WorldServer[] lvt_3_1_ = this.field_147367_d.field_71305_c;
         int lvt_4_1_ = lvt_3_1_.length;

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
            WorldServer lvt_6_1_ = lvt_3_1_[lvt_5_1_];
            if (lvt_6_1_ != null) {
               lvt_2_1_ = p_175088_1_.func_179727_a(lvt_6_1_);
               if (lvt_2_1_ != null) {
                  break;
               }
            }
         }

         if (lvt_2_1_ != null) {
            this.field_147369_b.func_175399_e(this.field_147369_b);
            this.field_147369_b.func_70078_a((Entity)null);
            if (lvt_2_1_.field_70170_p != this.field_147369_b.field_70170_p) {
               WorldServer lvt_3_2_ = this.field_147369_b.func_71121_q();
               WorldServer lvt_4_2_ = (WorldServer)lvt_2_1_.field_70170_p;
               this.field_147369_b.field_71093_bK = lvt_2_1_.field_71093_bK;
               this.func_147359_a(new S07PacketRespawn(this.field_147369_b.field_71093_bK, lvt_3_2_.func_175659_aa(), lvt_3_2_.func_72912_H().func_76067_t(), this.field_147369_b.field_71134_c.func_73081_b()));
               lvt_3_2_.func_72973_f(this.field_147369_b);
               this.field_147369_b.field_70128_L = false;
               this.field_147369_b.func_70012_b(lvt_2_1_.field_70165_t, lvt_2_1_.field_70163_u, lvt_2_1_.field_70161_v, lvt_2_1_.field_70177_z, lvt_2_1_.field_70125_A);
               if (this.field_147369_b.func_70089_S()) {
                  lvt_3_2_.func_72866_a(this.field_147369_b, false);
                  lvt_4_2_.func_72838_d(this.field_147369_b);
                  lvt_4_2_.func_72866_a(this.field_147369_b, false);
               }

               this.field_147369_b.func_70029_a(lvt_4_2_);
               this.field_147367_d.func_71203_ab().func_72375_a(this.field_147369_b, lvt_3_2_);
               this.field_147369_b.func_70634_a(lvt_2_1_.field_70165_t, lvt_2_1_.field_70163_u, lvt_2_1_.field_70161_v);
               this.field_147369_b.field_71134_c.func_73080_a(lvt_4_2_);
               this.field_147367_d.func_71203_ab().func_72354_b(this.field_147369_b, lvt_4_2_);
               this.field_147367_d.func_71203_ab().func_72385_f(this.field_147369_b);
            } else {
               this.field_147369_b.func_70634_a(lvt_2_1_.field_70165_t, lvt_2_1_.field_70163_u, lvt_2_1_.field_70161_v);
            }
         }
      }

   }

   public void func_175086_a(C19PacketResourcePackStatus p_175086_1_) {
   }

   public void func_147231_a(IChatComponent p_147231_1_) {
      field_147370_c.info(this.field_147369_b.func_70005_c_() + " lost connection: " + p_147231_1_);
      this.field_147367_d.func_147132_au();
      ChatComponentTranslation lvt_2_1_ = new ChatComponentTranslation("multiplayer.player.left", new Object[]{this.field_147369_b.func_145748_c_()});
      lvt_2_1_.func_150256_b().func_150238_a(EnumChatFormatting.YELLOW);
      this.field_147367_d.func_71203_ab().func_148539_a(lvt_2_1_);
      this.field_147369_b.func_71123_m();
      this.field_147367_d.func_71203_ab().func_72367_e(this.field_147369_b);
      if (this.field_147367_d.func_71264_H() && this.field_147369_b.func_70005_c_().equals(this.field_147367_d.func_71214_G())) {
         field_147370_c.info("Stopping singleplayer server as player logged out");
         this.field_147367_d.func_71263_m();
      }

   }

   public void func_147359_a(final Packet p_147359_1_) {
      if (p_147359_1_ instanceof S02PacketChat) {
         S02PacketChat lvt_2_1_ = (S02PacketChat)p_147359_1_;
         EntityPlayer.EnumChatVisibility lvt_3_1_ = this.field_147369_b.func_147096_v();
         if (lvt_3_1_ == EntityPlayer.EnumChatVisibility.HIDDEN) {
            return;
         }

         if (lvt_3_1_ == EntityPlayer.EnumChatVisibility.SYSTEM && !lvt_2_1_.func_148916_d()) {
            return;
         }
      }

      try {
         this.field_147371_a.func_179290_a(p_147359_1_);
      } catch (Throwable var5) {
         CrashReport lvt_3_2_ = CrashReport.func_85055_a(var5, "Sending packet");
         CrashReportCategory lvt_4_1_ = lvt_3_2_.func_85058_a("Packet being sent");
         lvt_4_1_.func_71500_a("Packet class", new Callable<String>() {
            public String call() throws Exception {
               return p_147359_1_.getClass().getCanonicalName();
            }
         });
         throw new ReportedException(lvt_3_2_);
      }
   }

   public void func_147355_a(C09PacketHeldItemChange p_147355_1_) {
      PacketThreadUtil.func_180031_a(p_147355_1_, this, this.field_147369_b.func_71121_q());
      if (p_147355_1_.func_149614_c() >= 0 && p_147355_1_.func_149614_c() < InventoryPlayer.func_70451_h()) {
         this.field_147369_b.field_71071_by.field_70461_c = p_147355_1_.func_149614_c();
         this.field_147369_b.func_143004_u();
      } else {
         field_147370_c.warn(this.field_147369_b.func_70005_c_() + " tried to set an invalid carried item");
      }
   }

   public void func_147354_a(C01PacketChatMessage p_147354_1_) {
      PacketThreadUtil.func_180031_a(p_147354_1_, this, this.field_147369_b.func_71121_q());
      if (this.field_147369_b.func_147096_v() == EntityPlayer.EnumChatVisibility.HIDDEN) {
         ChatComponentTranslation lvt_2_1_ = new ChatComponentTranslation("chat.cannotSend", new Object[0]);
         lvt_2_1_.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         this.func_147359_a(new S02PacketChat(lvt_2_1_));
      } else {
         this.field_147369_b.func_143004_u();
         String lvt_2_2_ = p_147354_1_.func_149439_c();
         lvt_2_2_ = StringUtils.normalizeSpace(lvt_2_2_);

         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_2_.length(); ++lvt_3_1_) {
            if (!ChatAllowedCharacters.func_71566_a(lvt_2_2_.charAt(lvt_3_1_))) {
               this.func_147360_c("Illegal characters in chat");
               return;
            }
         }

         if (lvt_2_2_.startsWith("/")) {
            this.func_147361_d(lvt_2_2_);
         } else {
            IChatComponent lvt_3_2_ = new ChatComponentTranslation("chat.type.text", new Object[]{this.field_147369_b.func_145748_c_(), lvt_2_2_});
            this.field_147367_d.func_71203_ab().func_148544_a(lvt_3_2_, false);
         }

         this.field_147374_l += 20;
         if (this.field_147374_l > 200 && !this.field_147367_d.func_71203_ab().func_152596_g(this.field_147369_b.func_146103_bH())) {
            this.func_147360_c("disconnect.spam");
         }

      }
   }

   private void func_147361_d(String p_147361_1_) {
      this.field_147367_d.func_71187_D().func_71556_a(this.field_147369_b, p_147361_1_);
   }

   public void func_175087_a(C0APacketAnimation p_175087_1_) {
      PacketThreadUtil.func_180031_a(p_175087_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      this.field_147369_b.func_71038_i();
   }

   public void func_147357_a(C0BPacketEntityAction p_147357_1_) {
      PacketThreadUtil.func_180031_a(p_147357_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      switch(p_147357_1_.func_180764_b()) {
      case START_SNEAKING:
         this.field_147369_b.func_70095_a(true);
         break;
      case STOP_SNEAKING:
         this.field_147369_b.func_70095_a(false);
         break;
      case START_SPRINTING:
         this.field_147369_b.func_70031_b(true);
         break;
      case STOP_SPRINTING:
         this.field_147369_b.func_70031_b(false);
         break;
      case STOP_SLEEPING:
         this.field_147369_b.func_70999_a(false, true, true);
         this.field_147380_r = false;
         break;
      case RIDING_JUMP:
         if (this.field_147369_b.field_70154_o instanceof EntityHorse) {
            ((EntityHorse)this.field_147369_b.field_70154_o).func_110206_u(p_147357_1_.func_149512_e());
         }
         break;
      case OPEN_INVENTORY:
         if (this.field_147369_b.field_70154_o instanceof EntityHorse) {
            ((EntityHorse)this.field_147369_b.field_70154_o).func_110199_f(this.field_147369_b);
         }
         break;
      default:
         throw new IllegalArgumentException("Invalid client command!");
      }

   }

   public void func_147340_a(C02PacketUseEntity p_147340_1_) {
      PacketThreadUtil.func_180031_a(p_147340_1_, this, this.field_147369_b.func_71121_q());
      WorldServer lvt_2_1_ = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      Entity lvt_3_1_ = p_147340_1_.func_149564_a(lvt_2_1_);
      this.field_147369_b.func_143004_u();
      if (lvt_3_1_ != null) {
         boolean lvt_4_1_ = this.field_147369_b.func_70685_l(lvt_3_1_);
         double lvt_5_1_ = 36.0D;
         if (!lvt_4_1_) {
            lvt_5_1_ = 9.0D;
         }

         if (this.field_147369_b.func_70068_e(lvt_3_1_) < lvt_5_1_) {
            if (p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.INTERACT) {
               this.field_147369_b.func_70998_m(lvt_3_1_);
            } else if (p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.INTERACT_AT) {
               lvt_3_1_.func_174825_a(this.field_147369_b, p_147340_1_.func_179712_b());
            } else if (p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.ATTACK) {
               if (lvt_3_1_ instanceof EntityItem || lvt_3_1_ instanceof EntityXPOrb || lvt_3_1_ instanceof EntityArrow || lvt_3_1_ == this.field_147369_b) {
                  this.func_147360_c("Attempting to attack an invalid entity");
                  this.field_147367_d.func_71236_h("Player " + this.field_147369_b.func_70005_c_() + " tried to attack an invalid entity");
                  return;
               }

               this.field_147369_b.func_71059_n(lvt_3_1_);
            }
         }
      }

   }

   public void func_147342_a(C16PacketClientStatus p_147342_1_) {
      PacketThreadUtil.func_180031_a(p_147342_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      C16PacketClientStatus.EnumState lvt_2_1_ = p_147342_1_.func_149435_c();
      switch(lvt_2_1_) {
      case PERFORM_RESPAWN:
         if (this.field_147369_b.field_71136_j) {
            this.field_147369_b = this.field_147367_d.func_71203_ab().func_72368_a(this.field_147369_b, 0, true);
         } else if (this.field_147369_b.func_71121_q().func_72912_H().func_76093_s()) {
            if (this.field_147367_d.func_71264_H() && this.field_147369_b.func_70005_c_().equals(this.field_147367_d.func_71214_G())) {
               this.field_147369_b.field_71135_a.func_147360_c("You have died. Game over, man, it's game over!");
               this.field_147367_d.func_71272_O();
            } else {
               UserListBansEntry lvt_3_1_ = new UserListBansEntry(this.field_147369_b.func_146103_bH(), (Date)null, "(You just lost the game)", (Date)null, "Death in Hardcore");
               this.field_147367_d.func_71203_ab().func_152608_h().func_152687_a(lvt_3_1_);
               this.field_147369_b.field_71135_a.func_147360_c("You have died. Game over, man, it's game over!");
            }
         } else {
            if (this.field_147369_b.func_110143_aJ() > 0.0F) {
               return;
            }

            this.field_147369_b = this.field_147367_d.func_71203_ab().func_72368_a(this.field_147369_b, 0, false);
         }
         break;
      case REQUEST_STATS:
         this.field_147369_b.func_147099_x().func_150876_a(this.field_147369_b);
         break;
      case OPEN_INVENTORY_ACHIEVEMENT:
         this.field_147369_b.func_71029_a(AchievementList.field_76004_f);
      }

   }

   public void func_147356_a(C0DPacketCloseWindow p_147356_1_) {
      PacketThreadUtil.func_180031_a(p_147356_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_71128_l();
   }

   public void func_147351_a(C0EPacketClickWindow p_147351_1_) {
      PacketThreadUtil.func_180031_a(p_147351_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      if (this.field_147369_b.field_71070_bA.field_75152_c == p_147351_1_.func_149548_c() && this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b)) {
         if (this.field_147369_b.func_175149_v()) {
            List<ItemStack> lvt_2_1_ = Lists.newArrayList();

            for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_147369_b.field_71070_bA.field_75151_b.size(); ++lvt_3_1_) {
               lvt_2_1_.add(((Slot)this.field_147369_b.field_71070_bA.field_75151_b.get(lvt_3_1_)).func_75211_c());
            }

            this.field_147369_b.func_71110_a(this.field_147369_b.field_71070_bA, lvt_2_1_);
         } else {
            ItemStack lvt_2_2_ = this.field_147369_b.field_71070_bA.func_75144_a(p_147351_1_.func_149544_d(), p_147351_1_.func_149543_e(), p_147351_1_.func_149542_h(), this.field_147369_b);
            if (ItemStack.func_77989_b(p_147351_1_.func_149546_g(), lvt_2_2_)) {
               this.field_147369_b.field_71135_a.func_147359_a(new S32PacketConfirmTransaction(p_147351_1_.func_149548_c(), p_147351_1_.func_149547_f(), true));
               this.field_147369_b.field_71137_h = true;
               this.field_147369_b.field_71070_bA.func_75142_b();
               this.field_147369_b.func_71113_k();
               this.field_147369_b.field_71137_h = false;
            } else {
               this.field_147372_n.func_76038_a(this.field_147369_b.field_71070_bA.field_75152_c, p_147351_1_.func_149547_f());
               this.field_147369_b.field_71135_a.func_147359_a(new S32PacketConfirmTransaction(p_147351_1_.func_149548_c(), p_147351_1_.func_149547_f(), false));
               this.field_147369_b.field_71070_bA.func_75128_a(this.field_147369_b, false);
               List<ItemStack> lvt_3_2_ = Lists.newArrayList();

               for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_147369_b.field_71070_bA.field_75151_b.size(); ++lvt_4_1_) {
                  lvt_3_2_.add(((Slot)this.field_147369_b.field_71070_bA.field_75151_b.get(lvt_4_1_)).func_75211_c());
               }

               this.field_147369_b.func_71110_a(this.field_147369_b.field_71070_bA, lvt_3_2_);
            }
         }
      }

   }

   public void func_147338_a(C11PacketEnchantItem p_147338_1_) {
      PacketThreadUtil.func_180031_a(p_147338_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      if (this.field_147369_b.field_71070_bA.field_75152_c == p_147338_1_.func_149539_c() && this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b) && !this.field_147369_b.func_175149_v()) {
         this.field_147369_b.field_71070_bA.func_75140_a(this.field_147369_b, p_147338_1_.func_149537_d());
         this.field_147369_b.field_71070_bA.func_75142_b();
      }

   }

   public void func_147344_a(C10PacketCreativeInventoryAction p_147344_1_) {
      PacketThreadUtil.func_180031_a(p_147344_1_, this, this.field_147369_b.func_71121_q());
      if (this.field_147369_b.field_71134_c.func_73083_d()) {
         boolean lvt_2_1_ = p_147344_1_.func_149627_c() < 0;
         ItemStack lvt_3_1_ = p_147344_1_.func_149625_d();
         if (lvt_3_1_ != null && lvt_3_1_.func_77942_o() && lvt_3_1_.func_77978_p().func_150297_b("BlockEntityTag", 10)) {
            NBTTagCompound lvt_4_1_ = lvt_3_1_.func_77978_p().func_74775_l("BlockEntityTag");
            if (lvt_4_1_.func_74764_b("x") && lvt_4_1_.func_74764_b("y") && lvt_4_1_.func_74764_b("z")) {
               BlockPos lvt_5_1_ = new BlockPos(lvt_4_1_.func_74762_e("x"), lvt_4_1_.func_74762_e("y"), lvt_4_1_.func_74762_e("z"));
               TileEntity lvt_6_1_ = this.field_147369_b.field_70170_p.func_175625_s(lvt_5_1_);
               if (lvt_6_1_ != null) {
                  NBTTagCompound lvt_7_1_ = new NBTTagCompound();
                  lvt_6_1_.func_145841_b(lvt_7_1_);
                  lvt_7_1_.func_82580_o("x");
                  lvt_7_1_.func_82580_o("y");
                  lvt_7_1_.func_82580_o("z");
                  lvt_3_1_.func_77983_a("BlockEntityTag", lvt_7_1_);
               }
            }
         }

         boolean lvt_4_2_ = p_147344_1_.func_149627_c() >= 1 && p_147344_1_.func_149627_c() < 36 + InventoryPlayer.func_70451_h();
         boolean lvt_5_2_ = lvt_3_1_ == null || lvt_3_1_.func_77973_b() != null;
         boolean lvt_6_2_ = lvt_3_1_ == null || lvt_3_1_.func_77960_j() >= 0 && lvt_3_1_.field_77994_a <= 64 && lvt_3_1_.field_77994_a > 0;
         if (lvt_4_2_ && lvt_5_2_ && lvt_6_2_) {
            if (lvt_3_1_ == null) {
               this.field_147369_b.field_71069_bz.func_75141_a(p_147344_1_.func_149627_c(), (ItemStack)null);
            } else {
               this.field_147369_b.field_71069_bz.func_75141_a(p_147344_1_.func_149627_c(), lvt_3_1_);
            }

            this.field_147369_b.field_71069_bz.func_75128_a(this.field_147369_b, true);
         } else if (lvt_2_1_ && lvt_5_2_ && lvt_6_2_ && this.field_147375_m < 200) {
            this.field_147375_m += 20;
            EntityItem lvt_7_2_ = this.field_147369_b.func_71019_a(lvt_3_1_, true);
            if (lvt_7_2_ != null) {
               lvt_7_2_.func_70288_d();
            }
         }
      }

   }

   public void func_147339_a(C0FPacketConfirmTransaction p_147339_1_) {
      PacketThreadUtil.func_180031_a(p_147339_1_, this, this.field_147369_b.func_71121_q());
      Short lvt_2_1_ = (Short)this.field_147372_n.func_76041_a(this.field_147369_b.field_71070_bA.field_75152_c);
      if (lvt_2_1_ != null && p_147339_1_.func_149533_d() == lvt_2_1_ && this.field_147369_b.field_71070_bA.field_75152_c == p_147339_1_.func_149532_c() && !this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b) && !this.field_147369_b.func_175149_v()) {
         this.field_147369_b.field_71070_bA.func_75128_a(this.field_147369_b, true);
      }

   }

   public void func_147343_a(C12PacketUpdateSign p_147343_1_) {
      PacketThreadUtil.func_180031_a(p_147343_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      WorldServer lvt_2_1_ = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      BlockPos lvt_3_1_ = p_147343_1_.func_179722_a();
      if (lvt_2_1_.func_175667_e(lvt_3_1_)) {
         TileEntity lvt_4_1_ = lvt_2_1_.func_175625_s(lvt_3_1_);
         if (!(lvt_4_1_ instanceof TileEntitySign)) {
            return;
         }

         TileEntitySign lvt_5_1_ = (TileEntitySign)lvt_4_1_;
         if (!lvt_5_1_.func_145914_a() || lvt_5_1_.func_145911_b() != this.field_147369_b) {
            this.field_147367_d.func_71236_h("Player " + this.field_147369_b.func_70005_c_() + " just tried to change non-editable sign");
            return;
         }

         IChatComponent[] lvt_6_1_ = p_147343_1_.func_180768_b();

         for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_.length; ++lvt_7_1_) {
            lvt_5_1_.field_145915_a[lvt_7_1_] = new ChatComponentText(EnumChatFormatting.func_110646_a(lvt_6_1_[lvt_7_1_].func_150260_c()));
         }

         lvt_5_1_.func_70296_d();
         lvt_2_1_.func_175689_h(lvt_3_1_);
      }

   }

   public void func_147353_a(C00PacketKeepAlive p_147353_1_) {
      if (p_147353_1_.func_149460_c() == this.field_147378_h) {
         int lvt_2_1_ = (int)(this.func_147363_d() - this.field_147379_i);
         this.field_147369_b.field_71138_i = (this.field_147369_b.field_71138_i * 3 + lvt_2_1_) / 4;
      }

   }

   private long func_147363_d() {
      return System.nanoTime() / 1000000L;
   }

   public void func_147348_a(C13PacketPlayerAbilities p_147348_1_) {
      PacketThreadUtil.func_180031_a(p_147348_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.field_71075_bZ.field_75100_b = p_147348_1_.func_149488_d() && this.field_147369_b.field_71075_bZ.field_75101_c;
   }

   public void func_147341_a(C14PacketTabComplete p_147341_1_) {
      PacketThreadUtil.func_180031_a(p_147341_1_, this, this.field_147369_b.func_71121_q());
      List<String> lvt_2_1_ = Lists.newArrayList();
      Iterator lvt_3_1_ = this.field_147367_d.func_180506_a(this.field_147369_b, p_147341_1_.func_149419_c(), p_147341_1_.func_179709_b()).iterator();

      while(lvt_3_1_.hasNext()) {
         String lvt_4_1_ = (String)lvt_3_1_.next();
         lvt_2_1_.add(lvt_4_1_);
      }

      this.field_147369_b.field_71135_a.func_147359_a(new S3APacketTabComplete((String[])lvt_2_1_.toArray(new String[lvt_2_1_.size()])));
   }

   public void func_147352_a(C15PacketClientSettings p_147352_1_) {
      PacketThreadUtil.func_180031_a(p_147352_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_147100_a(p_147352_1_);
   }

   public void func_147349_a(C17PacketCustomPayload p_147349_1_) {
      PacketThreadUtil.func_180031_a(p_147349_1_, this, this.field_147369_b.func_71121_q());
      PacketBuffer lvt_2_5_;
      ItemStack lvt_3_3_;
      ItemStack lvt_4_2_;
      if ("MC|BEdit".equals(p_147349_1_.func_149559_c())) {
         lvt_2_5_ = new PacketBuffer(Unpooled.wrappedBuffer((ByteBuf)p_147349_1_.func_180760_b()));

         try {
            lvt_3_3_ = lvt_2_5_.func_150791_c();
            if (lvt_3_3_ == null) {
               return;
            }

            if (!ItemWritableBook.func_150930_a(lvt_3_3_.func_77978_p())) {
               throw new IOException("Invalid book tag!");
            }

            lvt_4_2_ = this.field_147369_b.field_71071_by.func_70448_g();
            if (lvt_4_2_ == null) {
               return;
            }

            if (lvt_3_3_.func_77973_b() == Items.field_151099_bA && lvt_3_3_.func_77973_b() == lvt_4_2_.func_77973_b()) {
               lvt_4_2_.func_77983_a("pages", lvt_3_3_.func_77978_p().func_150295_c("pages", 8));
            }
         } catch (Exception var38) {
            field_147370_c.error((String)"Couldn't handle book info", (Throwable)var38);
         } finally {
            lvt_2_5_.release();
         }
      } else if ("MC|BSign".equals(p_147349_1_.func_149559_c())) {
         lvt_2_5_ = new PacketBuffer(Unpooled.wrappedBuffer((ByteBuf)p_147349_1_.func_180760_b()));

         try {
            lvt_3_3_ = lvt_2_5_.func_150791_c();
            if (lvt_3_3_ == null) {
               return;
            }

            if (!ItemEditableBook.func_77828_a(lvt_3_3_.func_77978_p())) {
               throw new IOException("Invalid book tag!");
            }

            lvt_4_2_ = this.field_147369_b.field_71071_by.func_70448_g();
            if (lvt_4_2_ == null) {
               return;
            }

            if (lvt_3_3_.func_77973_b() == Items.field_151164_bB && lvt_4_2_.func_77973_b() == Items.field_151099_bA) {
               lvt_4_2_.func_77983_a("author", new NBTTagString(this.field_147369_b.func_70005_c_()));
               lvt_4_2_.func_77983_a("title", new NBTTagString(lvt_3_3_.func_77978_p().func_74779_i("title")));
               lvt_4_2_.func_77983_a("pages", lvt_3_3_.func_77978_p().func_150295_c("pages", 8));
               lvt_4_2_.func_150996_a(Items.field_151164_bB);
            }
         } catch (Exception var36) {
            field_147370_c.error((String)"Couldn't sign book", (Throwable)var36);
         } finally {
            lvt_2_5_.release();
         }
      } else if ("MC|TrSel".equals(p_147349_1_.func_149559_c())) {
         try {
            int lvt_2_3_ = p_147349_1_.func_180760_b().readInt();
            Container lvt_3_5_ = this.field_147369_b.field_71070_bA;
            if (lvt_3_5_ instanceof ContainerMerchant) {
               ((ContainerMerchant)lvt_3_5_).func_75175_c(lvt_2_3_);
            }
         } catch (Exception var35) {
            field_147370_c.error((String)"Couldn't select trade", (Throwable)var35);
         }
      } else if ("MC|AdvCdm".equals(p_147349_1_.func_149559_c())) {
         if (!this.field_147367_d.func_82356_Z()) {
            this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.notEnabled", new Object[0]));
         } else if (this.field_147369_b.func_70003_b(2, "") && this.field_147369_b.field_71075_bZ.field_75098_d) {
            lvt_2_5_ = p_147349_1_.func_180760_b();

            try {
               int lvt_3_6_ = lvt_2_5_.readByte();
               CommandBlockLogic lvt_4_3_ = null;
               if (lvt_3_6_ == 0) {
                  TileEntity lvt_5_1_ = this.field_147369_b.field_70170_p.func_175625_s(new BlockPos(lvt_2_5_.readInt(), lvt_2_5_.readInt(), lvt_2_5_.readInt()));
                  if (lvt_5_1_ instanceof TileEntityCommandBlock) {
                     lvt_4_3_ = ((TileEntityCommandBlock)lvt_5_1_).func_145993_a();
                  }
               } else if (lvt_3_6_ == 1) {
                  Entity lvt_5_2_ = this.field_147369_b.field_70170_p.func_73045_a(lvt_2_5_.readInt());
                  if (lvt_5_2_ instanceof EntityMinecartCommandBlock) {
                     lvt_4_3_ = ((EntityMinecartCommandBlock)lvt_5_2_).func_145822_e();
                  }
               }

               String lvt_5_3_ = lvt_2_5_.func_150789_c(lvt_2_5_.readableBytes());
               boolean lvt_6_1_ = lvt_2_5_.readBoolean();
               if (lvt_4_3_ != null) {
                  lvt_4_3_.func_145752_a(lvt_5_3_);
                  lvt_4_3_.func_175573_a(lvt_6_1_);
                  if (!lvt_6_1_) {
                     lvt_4_3_.func_145750_b((IChatComponent)null);
                  }

                  lvt_4_3_.func_145756_e();
                  this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.setCommand.success", new Object[]{lvt_5_3_}));
               }
            } catch (Exception var33) {
               field_147370_c.error((String)"Couldn't set command block", (Throwable)var33);
            } finally {
               lvt_2_5_.release();
            }
         } else {
            this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.notAllowed", new Object[0]));
         }
      } else if ("MC|Beacon".equals(p_147349_1_.func_149559_c())) {
         if (this.field_147369_b.field_71070_bA instanceof ContainerBeacon) {
            try {
               lvt_2_5_ = p_147349_1_.func_180760_b();
               int lvt_3_8_ = lvt_2_5_.readInt();
               int lvt_4_4_ = lvt_2_5_.readInt();
               ContainerBeacon lvt_5_4_ = (ContainerBeacon)this.field_147369_b.field_71070_bA;
               Slot lvt_6_2_ = lvt_5_4_.func_75139_a(0);
               if (lvt_6_2_.func_75216_d()) {
                  lvt_6_2_.func_75209_a(1);
                  IInventory lvt_7_1_ = lvt_5_4_.func_180611_e();
                  lvt_7_1_.func_174885_b(1, lvt_3_8_);
                  lvt_7_1_.func_174885_b(2, lvt_4_4_);
                  lvt_7_1_.func_70296_d();
               }
            } catch (Exception var32) {
               field_147370_c.error((String)"Couldn't set beacon", (Throwable)var32);
            }
         }
      } else if ("MC|ItemName".equals(p_147349_1_.func_149559_c()) && this.field_147369_b.field_71070_bA instanceof ContainerRepair) {
         ContainerRepair lvt_2_8_ = (ContainerRepair)this.field_147369_b.field_71070_bA;
         if (p_147349_1_.func_180760_b() != null && p_147349_1_.func_180760_b().readableBytes() >= 1) {
            String lvt_3_9_ = ChatAllowedCharacters.func_71565_a(p_147349_1_.func_180760_b().func_150789_c(32767));
            if (lvt_3_9_.length() <= 30) {
               lvt_2_8_.func_82850_a(lvt_3_9_);
            }
         } else {
            lvt_2_8_.func_82850_a("");
         }
      }

   }
}
