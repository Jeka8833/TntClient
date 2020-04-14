package net.minecraft.client.network;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.GuardianSound;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.GuiScreenDemo;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.client.player.inventory.ContainerLocalMenu;
import net.minecraft.client.player.inventory.LocalBlockIntercommunication;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.MetadataAchievement;
import net.minecraft.client.stream.MetadataCombat;
import net.minecraft.client.stream.MetadataPlayerDeath;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.potion.PotionEffect;
import net.minecraft.realms.DisconnectedRealmsScreen;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.Explosion;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayClient implements INetHandlerPlayClient {
   private static final Logger field_147301_d = LogManager.getLogger();
   private final NetworkManager field_147302_e;
   private final GameProfile field_175107_d;
   private final GuiScreen field_147307_j;
   private Minecraft field_147299_f;
   private WorldClient field_147300_g;
   private boolean field_147309_h;
   private final Map<UUID, NetworkPlayerInfo> field_147310_i = Maps.newHashMap();
   public int field_147304_c = 20;
   private boolean field_147308_k = false;
   private final Random field_147306_l = new Random();

   public NetHandlerPlayClient(Minecraft p_i46300_1_, GuiScreen p_i46300_2_, NetworkManager p_i46300_3_, GameProfile p_i46300_4_) {
      this.field_147299_f = p_i46300_1_;
      this.field_147307_j = p_i46300_2_;
      this.field_147302_e = p_i46300_3_;
      this.field_175107_d = p_i46300_4_;
   }

   public void func_147296_c() {
      this.field_147300_g = null;
   }

   public void func_147282_a(S01PacketJoinGame p_147282_1_) {
      PacketThreadUtil.func_180031_a(p_147282_1_, this, this.field_147299_f);
      this.field_147299_f.field_71442_b = new PlayerControllerMP(this.field_147299_f, this);
      this.field_147300_g = new WorldClient(this, new WorldSettings(0L, p_147282_1_.func_149198_e(), false, p_147282_1_.func_149195_d(), p_147282_1_.func_149196_i()), p_147282_1_.func_149194_f(), p_147282_1_.func_149192_g(), this.field_147299_f.field_71424_I);
      this.field_147299_f.field_71474_y.field_74319_N = p_147282_1_.func_149192_g();
      this.field_147299_f.func_71403_a(this.field_147300_g);
      this.field_147299_f.field_71439_g.field_71093_bK = p_147282_1_.func_149194_f();
      this.field_147299_f.func_147108_a(new GuiDownloadTerrain(this));
      this.field_147299_f.field_71439_g.func_145769_d(p_147282_1_.func_149197_c());
      this.field_147304_c = p_147282_1_.func_149193_h();
      this.field_147299_f.field_71439_g.func_175150_k(p_147282_1_.func_179744_h());
      this.field_147299_f.field_71442_b.func_78746_a(p_147282_1_.func_149198_e());
      this.field_147299_f.field_71474_y.func_82879_c();
      this.field_147302_e.func_179290_a(new C17PacketCustomPayload("MC|Brand", (new PacketBuffer(Unpooled.buffer())).func_180714_a(ClientBrandRetriever.getClientModName())));
   }

   public void func_147235_a(S0EPacketSpawnObject p_147235_1_) {
      PacketThreadUtil.func_180031_a(p_147235_1_, this, this.field_147299_f);
      double lvt_2_1_ = (double)p_147235_1_.func_148997_d() / 32.0D;
      double lvt_4_1_ = (double)p_147235_1_.func_148998_e() / 32.0D;
      double lvt_6_1_ = (double)p_147235_1_.func_148994_f() / 32.0D;
      Entity lvt_8_1_ = null;
      if (p_147235_1_.func_148993_l() == 10) {
         lvt_8_1_ = EntityMinecart.func_180458_a(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, EntityMinecart.EnumMinecartType.func_180038_a(p_147235_1_.func_149009_m()));
      } else if (p_147235_1_.func_148993_l() == 90) {
         Entity lvt_9_1_ = this.field_147300_g.func_73045_a(p_147235_1_.func_149009_m());
         if (lvt_9_1_ instanceof EntityPlayer) {
            lvt_8_1_ = new EntityFishHook(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, (EntityPlayer)lvt_9_1_);
         }

         p_147235_1_.func_149002_g(0);
      } else if (p_147235_1_.func_148993_l() == 60) {
         lvt_8_1_ = new EntityArrow(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 61) {
         lvt_8_1_ = new EntitySnowball(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 71) {
         lvt_8_1_ = new EntityItemFrame(this.field_147300_g, new BlockPos(MathHelper.func_76128_c(lvt_2_1_), MathHelper.func_76128_c(lvt_4_1_), MathHelper.func_76128_c(lvt_6_1_)), EnumFacing.func_176731_b(p_147235_1_.func_149009_m()));
         p_147235_1_.func_149002_g(0);
      } else if (p_147235_1_.func_148993_l() == 77) {
         lvt_8_1_ = new EntityLeashKnot(this.field_147300_g, new BlockPos(MathHelper.func_76128_c(lvt_2_1_), MathHelper.func_76128_c(lvt_4_1_), MathHelper.func_76128_c(lvt_6_1_)));
         p_147235_1_.func_149002_g(0);
      } else if (p_147235_1_.func_148993_l() == 65) {
         lvt_8_1_ = new EntityEnderPearl(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 72) {
         lvt_8_1_ = new EntityEnderEye(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 76) {
         lvt_8_1_ = new EntityFireworkRocket(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, (ItemStack)null);
      } else if (p_147235_1_.func_148993_l() == 63) {
         lvt_8_1_ = new EntityLargeFireball(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, (double)p_147235_1_.func_149010_g() / 8000.0D, (double)p_147235_1_.func_149004_h() / 8000.0D, (double)p_147235_1_.func_148999_i() / 8000.0D);
         p_147235_1_.func_149002_g(0);
      } else if (p_147235_1_.func_148993_l() == 64) {
         lvt_8_1_ = new EntitySmallFireball(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, (double)p_147235_1_.func_149010_g() / 8000.0D, (double)p_147235_1_.func_149004_h() / 8000.0D, (double)p_147235_1_.func_148999_i() / 8000.0D);
         p_147235_1_.func_149002_g(0);
      } else if (p_147235_1_.func_148993_l() == 66) {
         lvt_8_1_ = new EntityWitherSkull(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, (double)p_147235_1_.func_149010_g() / 8000.0D, (double)p_147235_1_.func_149004_h() / 8000.0D, (double)p_147235_1_.func_148999_i() / 8000.0D);
         p_147235_1_.func_149002_g(0);
      } else if (p_147235_1_.func_148993_l() == 62) {
         lvt_8_1_ = new EntityEgg(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 73) {
         lvt_8_1_ = new EntityPotion(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, p_147235_1_.func_149009_m());
         p_147235_1_.func_149002_g(0);
      } else if (p_147235_1_.func_148993_l() == 75) {
         lvt_8_1_ = new EntityExpBottle(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
         p_147235_1_.func_149002_g(0);
      } else if (p_147235_1_.func_148993_l() == 1) {
         lvt_8_1_ = new EntityBoat(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 50) {
         lvt_8_1_ = new EntityTNTPrimed(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, (EntityLivingBase)null);
      } else if (p_147235_1_.func_148993_l() == 78) {
         lvt_8_1_ = new EntityArmorStand(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 51) {
         lvt_8_1_ = new EntityEnderCrystal(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 2) {
         lvt_8_1_ = new EntityItem(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      } else if (p_147235_1_.func_148993_l() == 70) {
         lvt_8_1_ = new EntityFallingBlock(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_, Block.func_176220_d(p_147235_1_.func_149009_m() & '\uffff'));
         p_147235_1_.func_149002_g(0);
      }

      if (lvt_8_1_ != null) {
         ((Entity)lvt_8_1_).field_70118_ct = p_147235_1_.func_148997_d();
         ((Entity)lvt_8_1_).field_70117_cu = p_147235_1_.func_148998_e();
         ((Entity)lvt_8_1_).field_70116_cv = p_147235_1_.func_148994_f();
         ((Entity)lvt_8_1_).field_70125_A = (float)(p_147235_1_.func_149008_j() * 360) / 256.0F;
         ((Entity)lvt_8_1_).field_70177_z = (float)(p_147235_1_.func_149006_k() * 360) / 256.0F;
         Entity[] lvt_9_2_ = ((Entity)lvt_8_1_).func_70021_al();
         if (lvt_9_2_ != null) {
            int lvt_10_1_ = p_147235_1_.func_149001_c() - ((Entity)lvt_8_1_).func_145782_y();

            for(int lvt_11_1_ = 0; lvt_11_1_ < lvt_9_2_.length; ++lvt_11_1_) {
               lvt_9_2_[lvt_11_1_].func_145769_d(lvt_9_2_[lvt_11_1_].func_145782_y() + lvt_10_1_);
            }
         }

         ((Entity)lvt_8_1_).func_145769_d(p_147235_1_.func_149001_c());
         this.field_147300_g.func_73027_a(p_147235_1_.func_149001_c(), (Entity)lvt_8_1_);
         if (p_147235_1_.func_149009_m() > 0) {
            if (p_147235_1_.func_148993_l() == 60) {
               Entity lvt_10_2_ = this.field_147300_g.func_73045_a(p_147235_1_.func_149009_m());
               if (lvt_10_2_ instanceof EntityLivingBase && lvt_8_1_ instanceof EntityArrow) {
                  ((EntityArrow)lvt_8_1_).field_70250_c = lvt_10_2_;
               }
            }

            ((Entity)lvt_8_1_).func_70016_h((double)p_147235_1_.func_149010_g() / 8000.0D, (double)p_147235_1_.func_149004_h() / 8000.0D, (double)p_147235_1_.func_148999_i() / 8000.0D);
         }
      }

   }

   public void func_147286_a(S11PacketSpawnExperienceOrb p_147286_1_) {
      PacketThreadUtil.func_180031_a(p_147286_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = new EntityXPOrb(this.field_147300_g, (double)p_147286_1_.func_148984_d() / 32.0D, (double)p_147286_1_.func_148983_e() / 32.0D, (double)p_147286_1_.func_148982_f() / 32.0D, p_147286_1_.func_148986_g());
      lvt_2_1_.field_70118_ct = p_147286_1_.func_148984_d();
      lvt_2_1_.field_70117_cu = p_147286_1_.func_148983_e();
      lvt_2_1_.field_70116_cv = p_147286_1_.func_148982_f();
      lvt_2_1_.field_70177_z = 0.0F;
      lvt_2_1_.field_70125_A = 0.0F;
      lvt_2_1_.func_145769_d(p_147286_1_.func_148985_c());
      this.field_147300_g.func_73027_a(p_147286_1_.func_148985_c(), lvt_2_1_);
   }

   public void func_147292_a(S2CPacketSpawnGlobalEntity p_147292_1_) {
      PacketThreadUtil.func_180031_a(p_147292_1_, this, this.field_147299_f);
      double lvt_2_1_ = (double)p_147292_1_.func_149051_d() / 32.0D;
      double lvt_4_1_ = (double)p_147292_1_.func_149050_e() / 32.0D;
      double lvt_6_1_ = (double)p_147292_1_.func_149049_f() / 32.0D;
      Entity lvt_8_1_ = null;
      if (p_147292_1_.func_149053_g() == 1) {
         lvt_8_1_ = new EntityLightningBolt(this.field_147300_g, lvt_2_1_, lvt_4_1_, lvt_6_1_);
      }

      if (lvt_8_1_ != null) {
         lvt_8_1_.field_70118_ct = p_147292_1_.func_149051_d();
         lvt_8_1_.field_70117_cu = p_147292_1_.func_149050_e();
         lvt_8_1_.field_70116_cv = p_147292_1_.func_149049_f();
         lvt_8_1_.field_70177_z = 0.0F;
         lvt_8_1_.field_70125_A = 0.0F;
         lvt_8_1_.func_145769_d(p_147292_1_.func_149052_c());
         this.field_147300_g.func_72942_c(lvt_8_1_);
      }

   }

   public void func_147288_a(S10PacketSpawnPainting p_147288_1_) {
      PacketThreadUtil.func_180031_a(p_147288_1_, this, this.field_147299_f);
      EntityPainting lvt_2_1_ = new EntityPainting(this.field_147300_g, p_147288_1_.func_179837_b(), p_147288_1_.func_179836_c(), p_147288_1_.func_148961_h());
      this.field_147300_g.func_73027_a(p_147288_1_.func_148965_c(), lvt_2_1_);
   }

   public void func_147244_a(S12PacketEntityVelocity p_147244_1_) {
      PacketThreadUtil.func_180031_a(p_147244_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147244_1_.func_149412_c());
      if (lvt_2_1_ != null) {
         lvt_2_1_.func_70016_h((double)p_147244_1_.func_149411_d() / 8000.0D, (double)p_147244_1_.func_149410_e() / 8000.0D, (double)p_147244_1_.func_149409_f() / 8000.0D);
      }
   }

   public void func_147284_a(S1CPacketEntityMetadata p_147284_1_) {
      PacketThreadUtil.func_180031_a(p_147284_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147284_1_.func_149375_d());
      if (lvt_2_1_ != null && p_147284_1_.func_149376_c() != null) {
         lvt_2_1_.func_70096_w().func_75687_a(p_147284_1_.func_149376_c());
      }

   }

   public void func_147237_a(S0CPacketSpawnPlayer p_147237_1_) {
      PacketThreadUtil.func_180031_a(p_147237_1_, this, this.field_147299_f);
      double lvt_2_1_ = (double)p_147237_1_.func_148942_f() / 32.0D;
      double lvt_4_1_ = (double)p_147237_1_.func_148949_g() / 32.0D;
      double lvt_6_1_ = (double)p_147237_1_.func_148946_h() / 32.0D;
      float lvt_8_1_ = (float)(p_147237_1_.func_148941_i() * 360) / 256.0F;
      float lvt_9_1_ = (float)(p_147237_1_.func_148945_j() * 360) / 256.0F;
      EntityOtherPlayerMP lvt_10_1_ = new EntityOtherPlayerMP(this.field_147299_f.field_71441_e, this.func_175102_a(p_147237_1_.func_179819_c()).func_178845_a());
      lvt_10_1_.field_70169_q = lvt_10_1_.field_70142_S = (double)(lvt_10_1_.field_70118_ct = p_147237_1_.func_148942_f());
      lvt_10_1_.field_70167_r = lvt_10_1_.field_70137_T = (double)(lvt_10_1_.field_70117_cu = p_147237_1_.func_148949_g());
      lvt_10_1_.field_70166_s = lvt_10_1_.field_70136_U = (double)(lvt_10_1_.field_70116_cv = p_147237_1_.func_148946_h());
      int lvt_11_1_ = p_147237_1_.func_148947_k();
      if (lvt_11_1_ == 0) {
         lvt_10_1_.field_71071_by.field_70462_a[lvt_10_1_.field_71071_by.field_70461_c] = null;
      } else {
         lvt_10_1_.field_71071_by.field_70462_a[lvt_10_1_.field_71071_by.field_70461_c] = new ItemStack(Item.func_150899_d(lvt_11_1_), 1, 0);
      }

      lvt_10_1_.func_70080_a(lvt_2_1_, lvt_4_1_, lvt_6_1_, lvt_8_1_, lvt_9_1_);
      this.field_147300_g.func_73027_a(p_147237_1_.func_148943_d(), lvt_10_1_);
      List<DataWatcher.WatchableObject> lvt_12_1_ = p_147237_1_.func_148944_c();
      if (lvt_12_1_ != null) {
         lvt_10_1_.func_70096_w().func_75687_a(lvt_12_1_);
      }

   }

   public void func_147275_a(S18PacketEntityTeleport p_147275_1_) {
      PacketThreadUtil.func_180031_a(p_147275_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147275_1_.func_149451_c());
      if (lvt_2_1_ != null) {
         lvt_2_1_.field_70118_ct = p_147275_1_.func_149449_d();
         lvt_2_1_.field_70117_cu = p_147275_1_.func_149448_e();
         lvt_2_1_.field_70116_cv = p_147275_1_.func_149446_f();
         double lvt_3_1_ = (double)lvt_2_1_.field_70118_ct / 32.0D;
         double lvt_5_1_ = (double)lvt_2_1_.field_70117_cu / 32.0D;
         double lvt_7_1_ = (double)lvt_2_1_.field_70116_cv / 32.0D;
         float lvt_9_1_ = (float)(p_147275_1_.func_149450_g() * 360) / 256.0F;
         float lvt_10_1_ = (float)(p_147275_1_.func_149447_h() * 360) / 256.0F;
         if (Math.abs(lvt_2_1_.field_70165_t - lvt_3_1_) < 0.03125D && Math.abs(lvt_2_1_.field_70163_u - lvt_5_1_) < 0.015625D && Math.abs(lvt_2_1_.field_70161_v - lvt_7_1_) < 0.03125D) {
            lvt_2_1_.func_180426_a(lvt_2_1_.field_70165_t, lvt_2_1_.field_70163_u, lvt_2_1_.field_70161_v, lvt_9_1_, lvt_10_1_, 3, true);
         } else {
            lvt_2_1_.func_180426_a(lvt_3_1_, lvt_5_1_, lvt_7_1_, lvt_9_1_, lvt_10_1_, 3, true);
         }

         lvt_2_1_.field_70122_E = p_147275_1_.func_179697_g();
      }
   }

   public void func_147257_a(S09PacketHeldItemChange p_147257_1_) {
      PacketThreadUtil.func_180031_a(p_147257_1_, this, this.field_147299_f);
      if (p_147257_1_.func_149385_c() >= 0 && p_147257_1_.func_149385_c() < InventoryPlayer.func_70451_h()) {
         this.field_147299_f.field_71439_g.field_71071_by.field_70461_c = p_147257_1_.func_149385_c();
      }

   }

   public void func_147259_a(S14PacketEntity p_147259_1_) {
      PacketThreadUtil.func_180031_a(p_147259_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = p_147259_1_.func_149065_a(this.field_147300_g);
      if (lvt_2_1_ != null) {
         lvt_2_1_.field_70118_ct += p_147259_1_.func_149062_c();
         lvt_2_1_.field_70117_cu += p_147259_1_.func_149061_d();
         lvt_2_1_.field_70116_cv += p_147259_1_.func_149064_e();
         double lvt_3_1_ = (double)lvt_2_1_.field_70118_ct / 32.0D;
         double lvt_5_1_ = (double)lvt_2_1_.field_70117_cu / 32.0D;
         double lvt_7_1_ = (double)lvt_2_1_.field_70116_cv / 32.0D;
         float lvt_9_1_ = p_147259_1_.func_149060_h() ? (float)(p_147259_1_.func_149066_f() * 360) / 256.0F : lvt_2_1_.field_70177_z;
         float lvt_10_1_ = p_147259_1_.func_149060_h() ? (float)(p_147259_1_.func_149063_g() * 360) / 256.0F : lvt_2_1_.field_70125_A;
         lvt_2_1_.func_180426_a(lvt_3_1_, lvt_5_1_, lvt_7_1_, lvt_9_1_, lvt_10_1_, 3, false);
         lvt_2_1_.field_70122_E = p_147259_1_.func_179742_g();
      }
   }

   public void func_147267_a(S19PacketEntityHeadLook p_147267_1_) {
      PacketThreadUtil.func_180031_a(p_147267_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = p_147267_1_.func_149381_a(this.field_147300_g);
      if (lvt_2_1_ != null) {
         float lvt_3_1_ = (float)(p_147267_1_.func_149380_c() * 360) / 256.0F;
         lvt_2_1_.func_70034_d(lvt_3_1_);
      }
   }

   public void func_147238_a(S13PacketDestroyEntities p_147238_1_) {
      PacketThreadUtil.func_180031_a(p_147238_1_, this, this.field_147299_f);

      for(int lvt_2_1_ = 0; lvt_2_1_ < p_147238_1_.func_149098_c().length; ++lvt_2_1_) {
         this.field_147300_g.func_73028_b(p_147238_1_.func_149098_c()[lvt_2_1_]);
      }

   }

   public void func_147258_a(S08PacketPlayerPosLook p_147258_1_) {
      PacketThreadUtil.func_180031_a(p_147258_1_, this, this.field_147299_f);
      EntityPlayer lvt_2_1_ = this.field_147299_f.field_71439_g;
      double lvt_3_1_ = p_147258_1_.func_148932_c();
      double lvt_5_1_ = p_147258_1_.func_148928_d();
      double lvt_7_1_ = p_147258_1_.func_148933_e();
      float lvt_9_1_ = p_147258_1_.func_148931_f();
      float lvt_10_1_ = p_147258_1_.func_148930_g();
      if (p_147258_1_.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.X)) {
         lvt_3_1_ += lvt_2_1_.field_70165_t;
      } else {
         lvt_2_1_.field_70159_w = 0.0D;
      }

      if (p_147258_1_.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
         lvt_5_1_ += lvt_2_1_.field_70163_u;
      } else {
         lvt_2_1_.field_70181_x = 0.0D;
      }

      if (p_147258_1_.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Z)) {
         lvt_7_1_ += lvt_2_1_.field_70161_v;
      } else {
         lvt_2_1_.field_70179_y = 0.0D;
      }

      if (p_147258_1_.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.X_ROT)) {
         lvt_10_1_ += lvt_2_1_.field_70125_A;
      }

      if (p_147258_1_.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
         lvt_9_1_ += lvt_2_1_.field_70177_z;
      }

      lvt_2_1_.func_70080_a(lvt_3_1_, lvt_5_1_, lvt_7_1_, lvt_9_1_, lvt_10_1_);
      this.field_147302_e.func_179290_a(new C03PacketPlayer.C06PacketPlayerPosLook(lvt_2_1_.field_70165_t, lvt_2_1_.func_174813_aQ().field_72338_b, lvt_2_1_.field_70161_v, lvt_2_1_.field_70177_z, lvt_2_1_.field_70125_A, false));
      if (!this.field_147309_h) {
         this.field_147299_f.field_71439_g.field_70169_q = this.field_147299_f.field_71439_g.field_70165_t;
         this.field_147299_f.field_71439_g.field_70167_r = this.field_147299_f.field_71439_g.field_70163_u;
         this.field_147299_f.field_71439_g.field_70166_s = this.field_147299_f.field_71439_g.field_70161_v;
         this.field_147309_h = true;
         this.field_147299_f.func_147108_a((GuiScreen)null);
      }

   }

   public void func_147287_a(S22PacketMultiBlockChange p_147287_1_) {
      PacketThreadUtil.func_180031_a(p_147287_1_, this, this.field_147299_f);
      S22PacketMultiBlockChange.BlockUpdateData[] lvt_2_1_ = p_147287_1_.func_179844_a();
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         S22PacketMultiBlockChange.BlockUpdateData lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         this.field_147300_g.func_180503_b(lvt_5_1_.func_180090_a(), lvt_5_1_.func_180088_c());
      }

   }

   public void func_147263_a(S21PacketChunkData p_147263_1_) {
      PacketThreadUtil.func_180031_a(p_147263_1_, this, this.field_147299_f);
      if (p_147263_1_.func_149274_i()) {
         if (p_147263_1_.func_149276_g() == 0) {
            this.field_147300_g.func_73025_a(p_147263_1_.func_149273_e(), p_147263_1_.func_149271_f(), false);
            return;
         }

         this.field_147300_g.func_73025_a(p_147263_1_.func_149273_e(), p_147263_1_.func_149271_f(), true);
      }

      this.field_147300_g.func_73031_a(p_147263_1_.func_149273_e() << 4, 0, p_147263_1_.func_149271_f() << 4, (p_147263_1_.func_149273_e() << 4) + 15, 256, (p_147263_1_.func_149271_f() << 4) + 15);
      Chunk lvt_2_1_ = this.field_147300_g.func_72964_e(p_147263_1_.func_149273_e(), p_147263_1_.func_149271_f());
      lvt_2_1_.func_177439_a(p_147263_1_.func_149272_d(), p_147263_1_.func_149276_g(), p_147263_1_.func_149274_i());
      this.field_147300_g.func_147458_c(p_147263_1_.func_149273_e() << 4, 0, p_147263_1_.func_149271_f() << 4, (p_147263_1_.func_149273_e() << 4) + 15, 256, (p_147263_1_.func_149271_f() << 4) + 15);
      if (!p_147263_1_.func_149274_i() || !(this.field_147300_g.field_73011_w instanceof WorldProviderSurface)) {
         lvt_2_1_.func_76613_n();
      }

   }

   public void func_147234_a(S23PacketBlockChange p_147234_1_) {
      PacketThreadUtil.func_180031_a(p_147234_1_, this, this.field_147299_f);
      this.field_147300_g.func_180503_b(p_147234_1_.func_179827_b(), p_147234_1_.func_180728_a());
   }

   public void func_147253_a(S40PacketDisconnect p_147253_1_) {
      this.field_147302_e.func_150718_a(p_147253_1_.func_149165_c());
   }

   public void func_147231_a(IChatComponent p_147231_1_) {
      this.field_147299_f.func_71403_a((WorldClient)null);
      if (this.field_147307_j != null) {
         if (this.field_147307_j instanceof GuiScreenRealmsProxy) {
            this.field_147299_f.func_147108_a((new DisconnectedRealmsScreen(((GuiScreenRealmsProxy)this.field_147307_j).func_154321_a(), "disconnect.lost", p_147231_1_)).getProxy());
         } else {
            this.field_147299_f.func_147108_a(new GuiDisconnected(this.field_147307_j, "disconnect.lost", p_147231_1_));
         }
      } else {
         this.field_147299_f.func_147108_a(new GuiDisconnected(new GuiMultiplayer(new GuiMainMenu()), "disconnect.lost", p_147231_1_));
      }

   }

   public void func_147297_a(Packet p_147297_1_) {
      this.field_147302_e.func_179290_a(p_147297_1_);
   }

   public void func_147246_a(S0DPacketCollectItem p_147246_1_) {
      PacketThreadUtil.func_180031_a(p_147246_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147246_1_.func_149354_c());
      EntityLivingBase lvt_3_1_ = (EntityLivingBase)this.field_147300_g.func_73045_a(p_147246_1_.func_149353_d());
      if (lvt_3_1_ == null) {
         lvt_3_1_ = this.field_147299_f.field_71439_g;
      }

      if (lvt_2_1_ != null) {
         if (lvt_2_1_ instanceof EntityXPOrb) {
            this.field_147300_g.func_72956_a(lvt_2_1_, "random.orb", 0.2F, ((this.field_147306_l.nextFloat() - this.field_147306_l.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         } else {
            this.field_147300_g.func_72956_a(lvt_2_1_, "random.pop", 0.2F, ((this.field_147306_l.nextFloat() - this.field_147306_l.nextFloat()) * 0.7F + 1.0F) * 2.0F);
         }

         this.field_147299_f.field_71452_i.func_78873_a(new EntityPickupFX(this.field_147300_g, lvt_2_1_, (Entity)lvt_3_1_, 0.5F));
         this.field_147300_g.func_73028_b(p_147246_1_.func_149354_c());
      }

   }

   public void func_147251_a(S02PacketChat p_147251_1_) {
      PacketThreadUtil.func_180031_a(p_147251_1_, this, this.field_147299_f);
      if (p_147251_1_.func_179841_c() == 2) {
         this.field_147299_f.field_71456_v.func_175188_a(p_147251_1_.func_148915_c(), false);
      } else {
         this.field_147299_f.field_71456_v.func_146158_b().func_146227_a(p_147251_1_.func_148915_c());
      }

   }

   public void func_147279_a(S0BPacketAnimation p_147279_1_) {
      PacketThreadUtil.func_180031_a(p_147279_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147279_1_.func_148978_c());
      if (lvt_2_1_ != null) {
         if (p_147279_1_.func_148977_d() == 0) {
            EntityLivingBase lvt_3_1_ = (EntityLivingBase)lvt_2_1_;
            lvt_3_1_.func_71038_i();
         } else if (p_147279_1_.func_148977_d() == 1) {
            lvt_2_1_.func_70057_ab();
         } else if (p_147279_1_.func_148977_d() == 2) {
            EntityPlayer lvt_3_2_ = (EntityPlayer)lvt_2_1_;
            lvt_3_2_.func_70999_a(false, false, false);
         } else if (p_147279_1_.func_148977_d() == 4) {
            this.field_147299_f.field_71452_i.func_178926_a(lvt_2_1_, EnumParticleTypes.CRIT);
         } else if (p_147279_1_.func_148977_d() == 5) {
            this.field_147299_f.field_71452_i.func_178926_a(lvt_2_1_, EnumParticleTypes.CRIT_MAGIC);
         }

      }
   }

   public void func_147278_a(S0APacketUseBed p_147278_1_) {
      PacketThreadUtil.func_180031_a(p_147278_1_, this, this.field_147299_f);
      p_147278_1_.func_149091_a(this.field_147300_g).func_180469_a(p_147278_1_.func_179798_a());
   }

   public void func_147281_a(S0FPacketSpawnMob p_147281_1_) {
      PacketThreadUtil.func_180031_a(p_147281_1_, this, this.field_147299_f);
      double lvt_2_1_ = (double)p_147281_1_.func_149023_f() / 32.0D;
      double lvt_4_1_ = (double)p_147281_1_.func_149034_g() / 32.0D;
      double lvt_6_1_ = (double)p_147281_1_.func_149029_h() / 32.0D;
      float lvt_8_1_ = (float)(p_147281_1_.func_149028_l() * 360) / 256.0F;
      float lvt_9_1_ = (float)(p_147281_1_.func_149030_m() * 360) / 256.0F;
      EntityLivingBase lvt_10_1_ = (EntityLivingBase)EntityList.func_75616_a(p_147281_1_.func_149025_e(), this.field_147299_f.field_71441_e);
      lvt_10_1_.field_70118_ct = p_147281_1_.func_149023_f();
      lvt_10_1_.field_70117_cu = p_147281_1_.func_149034_g();
      lvt_10_1_.field_70116_cv = p_147281_1_.func_149029_h();
      lvt_10_1_.field_70761_aq = lvt_10_1_.field_70759_as = (float)(p_147281_1_.func_149032_n() * 360) / 256.0F;
      Entity[] lvt_11_1_ = lvt_10_1_.func_70021_al();
      if (lvt_11_1_ != null) {
         int lvt_12_1_ = p_147281_1_.func_149024_d() - lvt_10_1_.func_145782_y();

         for(int lvt_13_1_ = 0; lvt_13_1_ < lvt_11_1_.length; ++lvt_13_1_) {
            lvt_11_1_[lvt_13_1_].func_145769_d(lvt_11_1_[lvt_13_1_].func_145782_y() + lvt_12_1_);
         }
      }

      lvt_10_1_.func_145769_d(p_147281_1_.func_149024_d());
      lvt_10_1_.func_70080_a(lvt_2_1_, lvt_4_1_, lvt_6_1_, lvt_8_1_, lvt_9_1_);
      lvt_10_1_.field_70159_w = (double)((float)p_147281_1_.func_149026_i() / 8000.0F);
      lvt_10_1_.field_70181_x = (double)((float)p_147281_1_.func_149033_j() / 8000.0F);
      lvt_10_1_.field_70179_y = (double)((float)p_147281_1_.func_149031_k() / 8000.0F);
      this.field_147300_g.func_73027_a(p_147281_1_.func_149024_d(), lvt_10_1_);
      List<DataWatcher.WatchableObject> lvt_12_2_ = p_147281_1_.func_149027_c();
      if (lvt_12_2_ != null) {
         lvt_10_1_.func_70096_w().func_75687_a(lvt_12_2_);
      }

   }

   public void func_147285_a(S03PacketTimeUpdate p_147285_1_) {
      PacketThreadUtil.func_180031_a(p_147285_1_, this, this.field_147299_f);
      this.field_147299_f.field_71441_e.func_82738_a(p_147285_1_.func_149366_c());
      this.field_147299_f.field_71441_e.func_72877_b(p_147285_1_.func_149365_d());
   }

   public void func_147271_a(S05PacketSpawnPosition p_147271_1_) {
      PacketThreadUtil.func_180031_a(p_147271_1_, this, this.field_147299_f);
      this.field_147299_f.field_71439_g.func_180473_a(p_147271_1_.func_179800_a(), true);
      this.field_147299_f.field_71441_e.func_72912_H().func_176143_a(p_147271_1_.func_179800_a());
   }

   public void func_147243_a(S1BPacketEntityAttach p_147243_1_) {
      PacketThreadUtil.func_180031_a(p_147243_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147243_1_.func_149403_d());
      Entity lvt_3_1_ = this.field_147300_g.func_73045_a(p_147243_1_.func_149402_e());
      if (p_147243_1_.func_149404_c() == 0) {
         boolean lvt_4_1_ = false;
         if (p_147243_1_.func_149403_d() == this.field_147299_f.field_71439_g.func_145782_y()) {
            lvt_2_1_ = this.field_147299_f.field_71439_g;
            if (lvt_3_1_ instanceof EntityBoat) {
               ((EntityBoat)lvt_3_1_).func_70270_d(false);
            }

            lvt_4_1_ = ((Entity)lvt_2_1_).field_70154_o == null && lvt_3_1_ != null;
         } else if (lvt_3_1_ instanceof EntityBoat) {
            ((EntityBoat)lvt_3_1_).func_70270_d(true);
         }

         if (lvt_2_1_ == null) {
            return;
         }

         ((Entity)lvt_2_1_).func_70078_a(lvt_3_1_);
         if (lvt_4_1_) {
            GameSettings lvt_5_1_ = this.field_147299_f.field_71474_y;
            this.field_147299_f.field_71456_v.func_110326_a(I18n.func_135052_a("mount.onboard", GameSettings.func_74298_c(lvt_5_1_.field_151444_V.func_151463_i())), false);
         }
      } else if (p_147243_1_.func_149404_c() == 1 && lvt_2_1_ instanceof EntityLiving) {
         if (lvt_3_1_ != null) {
            ((EntityLiving)lvt_2_1_).func_110162_b(lvt_3_1_, false);
         } else {
            ((EntityLiving)lvt_2_1_).func_110160_i(false, false);
         }
      }

   }

   public void func_147236_a(S19PacketEntityStatus p_147236_1_) {
      PacketThreadUtil.func_180031_a(p_147236_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = p_147236_1_.func_149161_a(this.field_147300_g);
      if (lvt_2_1_ != null) {
         if (p_147236_1_.func_149160_c() == 21) {
            this.field_147299_f.func_147118_V().func_147682_a(new GuardianSound((EntityGuardian)lvt_2_1_));
         } else {
            lvt_2_1_.func_70103_a(p_147236_1_.func_149160_c());
         }
      }

   }

   public void func_147249_a(S06PacketUpdateHealth p_147249_1_) {
      PacketThreadUtil.func_180031_a(p_147249_1_, this, this.field_147299_f);
      this.field_147299_f.field_71439_g.func_71150_b(p_147249_1_.func_149332_c());
      this.field_147299_f.field_71439_g.func_71024_bL().func_75114_a(p_147249_1_.func_149330_d());
      this.field_147299_f.field_71439_g.func_71024_bL().func_75119_b(p_147249_1_.func_149331_e());
   }

   public void func_147295_a(S1FPacketSetExperience p_147295_1_) {
      PacketThreadUtil.func_180031_a(p_147295_1_, this, this.field_147299_f);
      this.field_147299_f.field_71439_g.func_71152_a(p_147295_1_.func_149397_c(), p_147295_1_.func_149396_d(), p_147295_1_.func_149395_e());
   }

   public void func_147280_a(S07PacketRespawn p_147280_1_) {
      PacketThreadUtil.func_180031_a(p_147280_1_, this, this.field_147299_f);
      if (p_147280_1_.func_149082_c() != this.field_147299_f.field_71439_g.field_71093_bK) {
         this.field_147309_h = false;
         Scoreboard lvt_2_1_ = this.field_147300_g.func_96441_U();
         this.field_147300_g = new WorldClient(this, new WorldSettings(0L, p_147280_1_.func_149083_e(), false, this.field_147299_f.field_71441_e.func_72912_H().func_76093_s(), p_147280_1_.func_149080_f()), p_147280_1_.func_149082_c(), p_147280_1_.func_149081_d(), this.field_147299_f.field_71424_I);
         this.field_147300_g.func_96443_a(lvt_2_1_);
         this.field_147299_f.func_71403_a(this.field_147300_g);
         this.field_147299_f.field_71439_g.field_71093_bK = p_147280_1_.func_149082_c();
         this.field_147299_f.func_147108_a(new GuiDownloadTerrain(this));
      }

      this.field_147299_f.func_71354_a(p_147280_1_.func_149082_c());
      this.field_147299_f.field_71442_b.func_78746_a(p_147280_1_.func_149083_e());
   }

   public void func_147283_a(S27PacketExplosion p_147283_1_) {
      PacketThreadUtil.func_180031_a(p_147283_1_, this, this.field_147299_f);
      Explosion lvt_2_1_ = new Explosion(this.field_147299_f.field_71441_e, (Entity)null, p_147283_1_.func_149148_f(), p_147283_1_.func_149143_g(), p_147283_1_.func_149145_h(), p_147283_1_.func_149146_i(), p_147283_1_.func_149150_j());
      lvt_2_1_.func_77279_a(true);
      EntityPlayerSP var10000 = this.field_147299_f.field_71439_g;
      var10000.field_70159_w += (double)p_147283_1_.func_149149_c();
      var10000 = this.field_147299_f.field_71439_g;
      var10000.field_70181_x += (double)p_147283_1_.func_149144_d();
      var10000 = this.field_147299_f.field_71439_g;
      var10000.field_70179_y += (double)p_147283_1_.func_149147_e();
   }

   public void func_147265_a(S2DPacketOpenWindow p_147265_1_) {
      PacketThreadUtil.func_180031_a(p_147265_1_, this, this.field_147299_f);
      EntityPlayerSP lvt_2_1_ = this.field_147299_f.field_71439_g;
      if ("minecraft:container".equals(p_147265_1_.func_148902_e())) {
         lvt_2_1_.func_71007_a(new InventoryBasic(p_147265_1_.func_179840_c(), p_147265_1_.func_148898_f()));
         lvt_2_1_.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
      } else if ("minecraft:villager".equals(p_147265_1_.func_148902_e())) {
         lvt_2_1_.func_180472_a(new NpcMerchant(lvt_2_1_, p_147265_1_.func_179840_c()));
         lvt_2_1_.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
      } else if ("EntityHorse".equals(p_147265_1_.func_148902_e())) {
         Entity lvt_3_1_ = this.field_147300_g.func_73045_a(p_147265_1_.func_148897_h());
         if (lvt_3_1_ instanceof EntityHorse) {
            lvt_2_1_.func_110298_a((EntityHorse)lvt_3_1_, new AnimalChest(p_147265_1_.func_179840_c(), p_147265_1_.func_148898_f()));
            lvt_2_1_.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
         }
      } else if (!p_147265_1_.func_148900_g()) {
         lvt_2_1_.func_180468_a(new LocalBlockIntercommunication(p_147265_1_.func_148902_e(), p_147265_1_.func_179840_c()));
         lvt_2_1_.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
      } else {
         ContainerLocalMenu lvt_3_2_ = new ContainerLocalMenu(p_147265_1_.func_148902_e(), p_147265_1_.func_179840_c(), p_147265_1_.func_148898_f());
         lvt_2_1_.func_71007_a(lvt_3_2_);
         lvt_2_1_.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
      }

   }

   public void func_147266_a(S2FPacketSetSlot p_147266_1_) {
      PacketThreadUtil.func_180031_a(p_147266_1_, this, this.field_147299_f);
      EntityPlayer lvt_2_1_ = this.field_147299_f.field_71439_g;
      if (p_147266_1_.func_149175_c() == -1) {
         lvt_2_1_.field_71071_by.func_70437_b(p_147266_1_.func_149174_e());
      } else {
         boolean lvt_3_1_ = false;
         if (this.field_147299_f.field_71462_r instanceof GuiContainerCreative) {
            GuiContainerCreative lvt_4_1_ = (GuiContainerCreative)this.field_147299_f.field_71462_r;
            lvt_3_1_ = lvt_4_1_.func_147056_g() != CreativeTabs.field_78036_m.func_78021_a();
         }

         if (p_147266_1_.func_149175_c() == 0 && p_147266_1_.func_149173_d() >= 36 && p_147266_1_.func_149173_d() < 45) {
            ItemStack lvt_4_2_ = lvt_2_1_.field_71069_bz.func_75139_a(p_147266_1_.func_149173_d()).func_75211_c();
            if (p_147266_1_.func_149174_e() != null && (lvt_4_2_ == null || lvt_4_2_.field_77994_a < p_147266_1_.func_149174_e().field_77994_a)) {
               p_147266_1_.func_149174_e().field_77992_b = 5;
            }

            lvt_2_1_.field_71069_bz.func_75141_a(p_147266_1_.func_149173_d(), p_147266_1_.func_149174_e());
         } else if (p_147266_1_.func_149175_c() == lvt_2_1_.field_71070_bA.field_75152_c && (p_147266_1_.func_149175_c() != 0 || !lvt_3_1_)) {
            lvt_2_1_.field_71070_bA.func_75141_a(p_147266_1_.func_149173_d(), p_147266_1_.func_149174_e());
         }
      }

   }

   public void func_147239_a(S32PacketConfirmTransaction p_147239_1_) {
      PacketThreadUtil.func_180031_a(p_147239_1_, this, this.field_147299_f);
      Container lvt_2_1_ = null;
      EntityPlayer lvt_3_1_ = this.field_147299_f.field_71439_g;
      if (p_147239_1_.func_148889_c() == 0) {
         lvt_2_1_ = lvt_3_1_.field_71069_bz;
      } else if (p_147239_1_.func_148889_c() == lvt_3_1_.field_71070_bA.field_75152_c) {
         lvt_2_1_ = lvt_3_1_.field_71070_bA;
      }

      if (lvt_2_1_ != null && !p_147239_1_.func_148888_e()) {
         this.func_147297_a(new C0FPacketConfirmTransaction(p_147239_1_.func_148889_c(), p_147239_1_.func_148890_d(), true));
      }

   }

   public void func_147241_a(S30PacketWindowItems p_147241_1_) {
      PacketThreadUtil.func_180031_a(p_147241_1_, this, this.field_147299_f);
      EntityPlayer lvt_2_1_ = this.field_147299_f.field_71439_g;
      if (p_147241_1_.func_148911_c() == 0) {
         lvt_2_1_.field_71069_bz.func_75131_a(p_147241_1_.func_148910_d());
      } else if (p_147241_1_.func_148911_c() == lvt_2_1_.field_71070_bA.field_75152_c) {
         lvt_2_1_.field_71070_bA.func_75131_a(p_147241_1_.func_148910_d());
      }

   }

   public void func_147268_a(S36PacketSignEditorOpen p_147268_1_) {
      PacketThreadUtil.func_180031_a(p_147268_1_, this, this.field_147299_f);
      TileEntity lvt_2_1_ = this.field_147300_g.func_175625_s(p_147268_1_.func_179777_a());
      if (!(lvt_2_1_ instanceof TileEntitySign)) {
         lvt_2_1_ = new TileEntitySign();
         ((TileEntity)lvt_2_1_).func_145834_a(this.field_147300_g);
         ((TileEntity)lvt_2_1_).func_174878_a(p_147268_1_.func_179777_a());
      }

      this.field_147299_f.field_71439_g.func_175141_a((TileEntitySign)lvt_2_1_);
   }

   public void func_147248_a(S33PacketUpdateSign p_147248_1_) {
      PacketThreadUtil.func_180031_a(p_147248_1_, this, this.field_147299_f);
      boolean lvt_2_1_ = false;
      if (this.field_147299_f.field_71441_e.func_175667_e(p_147248_1_.func_179704_a())) {
         TileEntity lvt_3_1_ = this.field_147299_f.field_71441_e.func_175625_s(p_147248_1_.func_179704_a());
         if (lvt_3_1_ instanceof TileEntitySign) {
            TileEntitySign lvt_4_1_ = (TileEntitySign)lvt_3_1_;
            if (lvt_4_1_.func_145914_a()) {
               System.arraycopy(p_147248_1_.func_180753_b(), 0, lvt_4_1_.field_145915_a, 0, 4);
               lvt_4_1_.func_70296_d();
            }

            lvt_2_1_ = true;
         }
      }

      if (!lvt_2_1_ && this.field_147299_f.field_71439_g != null) {
         this.field_147299_f.field_71439_g.func_145747_a(new ChatComponentText("Unable to locate sign at " + p_147248_1_.func_179704_a().func_177958_n() + ", " + p_147248_1_.func_179704_a().func_177956_o() + ", " + p_147248_1_.func_179704_a().func_177952_p()));
      }

   }

   public void func_147273_a(S35PacketUpdateTileEntity p_147273_1_) {
      PacketThreadUtil.func_180031_a(p_147273_1_, this, this.field_147299_f);
      if (this.field_147299_f.field_71441_e.func_175667_e(p_147273_1_.func_179823_a())) {
         TileEntity lvt_2_1_ = this.field_147299_f.field_71441_e.func_175625_s(p_147273_1_.func_179823_a());
         int lvt_3_1_ = p_147273_1_.func_148853_f();
         if (lvt_3_1_ == 1 && lvt_2_1_ instanceof TileEntityMobSpawner || lvt_3_1_ == 2 && lvt_2_1_ instanceof TileEntityCommandBlock || lvt_3_1_ == 3 && lvt_2_1_ instanceof TileEntityBeacon || lvt_3_1_ == 4 && lvt_2_1_ instanceof TileEntitySkull || lvt_3_1_ == 5 && lvt_2_1_ instanceof TileEntityFlowerPot || lvt_3_1_ == 6 && lvt_2_1_ instanceof TileEntityBanner) {
            lvt_2_1_.func_145839_a(p_147273_1_.func_148857_g());
         }
      }

   }

   public void func_147245_a(S31PacketWindowProperty p_147245_1_) {
      PacketThreadUtil.func_180031_a(p_147245_1_, this, this.field_147299_f);
      EntityPlayer lvt_2_1_ = this.field_147299_f.field_71439_g;
      if (lvt_2_1_.field_71070_bA != null && lvt_2_1_.field_71070_bA.field_75152_c == p_147245_1_.func_149182_c()) {
         lvt_2_1_.field_71070_bA.func_75137_b(p_147245_1_.func_149181_d(), p_147245_1_.func_149180_e());
      }

   }

   public void func_147242_a(S04PacketEntityEquipment p_147242_1_) {
      PacketThreadUtil.func_180031_a(p_147242_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147242_1_.func_149389_d());
      if (lvt_2_1_ != null) {
         lvt_2_1_.func_70062_b(p_147242_1_.func_149388_e(), p_147242_1_.func_149390_c());
      }

   }

   public void func_147276_a(S2EPacketCloseWindow p_147276_1_) {
      PacketThreadUtil.func_180031_a(p_147276_1_, this, this.field_147299_f);
      this.field_147299_f.field_71439_g.func_175159_q();
   }

   public void func_147261_a(S24PacketBlockAction p_147261_1_) {
      PacketThreadUtil.func_180031_a(p_147261_1_, this, this.field_147299_f);
      this.field_147299_f.field_71441_e.func_175641_c(p_147261_1_.func_179825_a(), p_147261_1_.func_148868_c(), p_147261_1_.func_148869_g(), p_147261_1_.func_148864_h());
   }

   public void func_147294_a(S25PacketBlockBreakAnim p_147294_1_) {
      PacketThreadUtil.func_180031_a(p_147294_1_, this, this.field_147299_f);
      this.field_147299_f.field_71441_e.func_175715_c(p_147294_1_.func_148845_c(), p_147294_1_.func_179821_b(), p_147294_1_.func_148846_g());
   }

   public void func_147269_a(S26PacketMapChunkBulk p_147269_1_) {
      PacketThreadUtil.func_180031_a(p_147269_1_, this, this.field_147299_f);

      for(int lvt_2_1_ = 0; lvt_2_1_ < p_147269_1_.func_149254_d(); ++lvt_2_1_) {
         int lvt_3_1_ = p_147269_1_.func_149255_a(lvt_2_1_);
         int lvt_4_1_ = p_147269_1_.func_149253_b(lvt_2_1_);
         this.field_147300_g.func_73025_a(lvt_3_1_, lvt_4_1_, true);
         this.field_147300_g.func_73031_a(lvt_3_1_ << 4, 0, lvt_4_1_ << 4, (lvt_3_1_ << 4) + 15, 256, (lvt_4_1_ << 4) + 15);
         Chunk lvt_5_1_ = this.field_147300_g.func_72964_e(lvt_3_1_, lvt_4_1_);
         lvt_5_1_.func_177439_a(p_147269_1_.func_149256_c(lvt_2_1_), p_147269_1_.func_179754_d(lvt_2_1_), true);
         this.field_147300_g.func_147458_c(lvt_3_1_ << 4, 0, lvt_4_1_ << 4, (lvt_3_1_ << 4) + 15, 256, (lvt_4_1_ << 4) + 15);
         if (!(this.field_147300_g.field_73011_w instanceof WorldProviderSurface)) {
            lvt_5_1_.func_76613_n();
         }
      }

   }

   public void func_147252_a(S2BPacketChangeGameState p_147252_1_) {
      PacketThreadUtil.func_180031_a(p_147252_1_, this, this.field_147299_f);
      EntityPlayer lvt_2_1_ = this.field_147299_f.field_71439_g;
      int lvt_3_1_ = p_147252_1_.func_149138_c();
      float lvt_4_1_ = p_147252_1_.func_149137_d();
      int lvt_5_1_ = MathHelper.func_76141_d(lvt_4_1_ + 0.5F);
      if (lvt_3_1_ >= 0 && lvt_3_1_ < S2BPacketChangeGameState.field_149142_a.length && S2BPacketChangeGameState.field_149142_a[lvt_3_1_] != null) {
         lvt_2_1_.func_146105_b(new ChatComponentTranslation(S2BPacketChangeGameState.field_149142_a[lvt_3_1_], new Object[0]));
      }

      if (lvt_3_1_ == 1) {
         this.field_147300_g.func_72912_H().func_76084_b(true);
         this.field_147300_g.func_72894_k(0.0F);
      } else if (lvt_3_1_ == 2) {
         this.field_147300_g.func_72912_H().func_76084_b(false);
         this.field_147300_g.func_72894_k(1.0F);
      } else if (lvt_3_1_ == 3) {
         this.field_147299_f.field_71442_b.func_78746_a(WorldSettings.GameType.func_77146_a(lvt_5_1_));
      } else if (lvt_3_1_ == 4) {
         this.field_147299_f.func_147108_a(new GuiWinGame());
      } else if (lvt_3_1_ == 5) {
         GameSettings lvt_6_1_ = this.field_147299_f.field_71474_y;
         if (lvt_4_1_ == 0.0F) {
            this.field_147299_f.func_147108_a(new GuiScreenDemo());
         } else if (lvt_4_1_ == 101.0F) {
            this.field_147299_f.field_71456_v.func_146158_b().func_146227_a(new ChatComponentTranslation("demo.help.movement", new Object[]{GameSettings.func_74298_c(lvt_6_1_.field_74370_x.func_151463_i()), GameSettings.func_74298_c(lvt_6_1_.field_74368_y.func_151463_i()), GameSettings.func_74298_c(lvt_6_1_.field_74366_z.func_151463_i()), GameSettings.func_74298_c(lvt_6_1_.field_74314_A.func_151463_i())}));
         } else if (lvt_4_1_ == 102.0F) {
            this.field_147299_f.field_71456_v.func_146158_b().func_146227_a(new ChatComponentTranslation("demo.help.jump", new Object[]{GameSettings.func_74298_c(lvt_6_1_.field_74311_E.func_151463_i())}));
         } else if (lvt_4_1_ == 103.0F) {
            this.field_147299_f.field_71456_v.func_146158_b().func_146227_a(new ChatComponentTranslation("demo.help.inventory", new Object[]{GameSettings.func_74298_c(lvt_6_1_.field_74313_G.func_151463_i())}));
         }
      } else if (lvt_3_1_ == 6) {
         this.field_147300_g.func_72980_b(lvt_2_1_.field_70165_t, lvt_2_1_.field_70163_u + (double)lvt_2_1_.func_70047_e(), lvt_2_1_.field_70161_v, "random.successful_hit", 0.18F, 0.45F, false);
      } else if (lvt_3_1_ == 7) {
         this.field_147300_g.func_72894_k(lvt_4_1_);
      } else if (lvt_3_1_ == 8) {
         this.field_147300_g.func_147442_i(lvt_4_1_);
      } else if (lvt_3_1_ == 10) {
         this.field_147300_g.func_175688_a(EnumParticleTypes.MOB_APPEARANCE, lvt_2_1_.field_70165_t, lvt_2_1_.field_70163_u, lvt_2_1_.field_70161_v, 0.0D, 0.0D, 0.0D, new int[0]);
         this.field_147300_g.func_72980_b(lvt_2_1_.field_70165_t, lvt_2_1_.field_70163_u, lvt_2_1_.field_70161_v, "mob.guardian.curse", 1.0F, 1.0F, false);
      }

   }

   public void func_147264_a(S34PacketMaps p_147264_1_) {
      PacketThreadUtil.func_180031_a(p_147264_1_, this, this.field_147299_f);
      MapData lvt_2_1_ = ItemMap.func_150912_a(p_147264_1_.func_149188_c(), this.field_147299_f.field_71441_e);
      p_147264_1_.func_179734_a(lvt_2_1_);
      this.field_147299_f.field_71460_t.func_147701_i().func_148246_a(lvt_2_1_);
   }

   public void func_147277_a(S28PacketEffect p_147277_1_) {
      PacketThreadUtil.func_180031_a(p_147277_1_, this, this.field_147299_f);
      if (p_147277_1_.func_149244_c()) {
         this.field_147299_f.field_71441_e.func_175669_a(p_147277_1_.func_149242_d(), p_147277_1_.func_179746_d(), p_147277_1_.func_149241_e());
      } else {
         this.field_147299_f.field_71441_e.func_175718_b(p_147277_1_.func_149242_d(), p_147277_1_.func_179746_d(), p_147277_1_.func_149241_e());
      }

   }

   public void func_147293_a(S37PacketStatistics p_147293_1_) {
      PacketThreadUtil.func_180031_a(p_147293_1_, this, this.field_147299_f);
      boolean lvt_2_1_ = false;

      StatBase lvt_5_1_;
      int lvt_6_1_;
      for(Iterator lvt_3_1_ = p_147293_1_.func_148974_c().entrySet().iterator(); lvt_3_1_.hasNext(); this.field_147299_f.field_71439_g.func_146107_m().func_150873_a(this.field_147299_f.field_71439_g, lvt_5_1_, lvt_6_1_)) {
         Entry<StatBase, Integer> lvt_4_1_ = (Entry)lvt_3_1_.next();
         lvt_5_1_ = (StatBase)lvt_4_1_.getKey();
         lvt_6_1_ = (Integer)lvt_4_1_.getValue();
         if (lvt_5_1_.func_75967_d() && lvt_6_1_ > 0) {
            if (this.field_147308_k && this.field_147299_f.field_71439_g.func_146107_m().func_77444_a(lvt_5_1_) == 0) {
               Achievement lvt_7_1_ = (Achievement)lvt_5_1_;
               this.field_147299_f.field_71458_u.func_146256_a(lvt_7_1_);
               this.field_147299_f.func_152346_Z().func_152911_a(new MetadataAchievement(lvt_7_1_), 0L);
               if (lvt_5_1_ == AchievementList.field_76004_f) {
                  this.field_147299_f.field_71474_y.field_151441_H = false;
                  this.field_147299_f.field_71474_y.func_74303_b();
               }
            }

            lvt_2_1_ = true;
         }
      }

      if (!this.field_147308_k && !lvt_2_1_ && this.field_147299_f.field_71474_y.field_151441_H) {
         this.field_147299_f.field_71458_u.func_146255_b(AchievementList.field_76004_f);
      }

      this.field_147308_k = true;
      if (this.field_147299_f.field_71462_r instanceof IProgressMeter) {
         ((IProgressMeter)this.field_147299_f.field_71462_r).func_146509_g();
      }

   }

   public void func_147260_a(S1DPacketEntityEffect p_147260_1_) {
      PacketThreadUtil.func_180031_a(p_147260_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147260_1_.func_149426_d());
      if (lvt_2_1_ instanceof EntityLivingBase) {
         PotionEffect lvt_3_1_ = new PotionEffect(p_147260_1_.func_149427_e(), p_147260_1_.func_180755_e(), p_147260_1_.func_149428_f(), false, p_147260_1_.func_179707_f());
         lvt_3_1_.func_100012_b(p_147260_1_.func_149429_c());
         ((EntityLivingBase)lvt_2_1_).func_70690_d(lvt_3_1_);
      }
   }

   public void func_175098_a(S42PacketCombatEvent p_175098_1_) {
      PacketThreadUtil.func_180031_a(p_175098_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_175098_1_.field_179775_c);
      EntityLivingBase lvt_3_1_ = lvt_2_1_ instanceof EntityLivingBase ? (EntityLivingBase)lvt_2_1_ : null;
      if (p_175098_1_.field_179776_a == S42PacketCombatEvent.Event.END_COMBAT) {
         long lvt_4_1_ = (long)(1000 * p_175098_1_.field_179772_d / 20);
         MetadataCombat lvt_6_1_ = new MetadataCombat(this.field_147299_f.field_71439_g, lvt_3_1_);
         this.field_147299_f.func_152346_Z().func_176026_a(lvt_6_1_, 0L - lvt_4_1_, 0L);
      } else if (p_175098_1_.field_179776_a == S42PacketCombatEvent.Event.ENTITY_DIED) {
         Entity lvt_4_2_ = this.field_147300_g.func_73045_a(p_175098_1_.field_179774_b);
         if (lvt_4_2_ instanceof EntityPlayer) {
            MetadataPlayerDeath lvt_5_1_ = new MetadataPlayerDeath((EntityPlayer)lvt_4_2_, lvt_3_1_);
            lvt_5_1_.func_152807_a(p_175098_1_.field_179773_e);
            this.field_147299_f.func_152346_Z().func_152911_a(lvt_5_1_, 0L);
         }
      }

   }

   public void func_175101_a(S41PacketServerDifficulty p_175101_1_) {
      PacketThreadUtil.func_180031_a(p_175101_1_, this, this.field_147299_f);
      this.field_147299_f.field_71441_e.func_72912_H().func_176144_a(p_175101_1_.func_179831_b());
      this.field_147299_f.field_71441_e.func_72912_H().func_180783_e(p_175101_1_.func_179830_a());
   }

   public void func_175094_a(S43PacketCamera p_175094_1_) {
      PacketThreadUtil.func_180031_a(p_175094_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = p_175094_1_.func_179780_a(this.field_147300_g);
      if (lvt_2_1_ != null) {
         this.field_147299_f.func_175607_a(lvt_2_1_);
      }

   }

   public void func_175093_a(S44PacketWorldBorder p_175093_1_) {
      PacketThreadUtil.func_180031_a(p_175093_1_, this, this.field_147299_f);
      p_175093_1_.func_179788_a(this.field_147300_g.func_175723_af());
   }

   public void func_175099_a(S45PacketTitle p_175099_1_) {
      PacketThreadUtil.func_180031_a(p_175099_1_, this, this.field_147299_f);
      S45PacketTitle.Type lvt_2_1_ = p_175099_1_.func_179807_a();
      String lvt_3_1_ = null;
      String lvt_4_1_ = null;
      String lvt_5_1_ = p_175099_1_.func_179805_b() != null ? p_175099_1_.func_179805_b().func_150254_d() : "";
      switch(lvt_2_1_) {
      case TITLE:
         lvt_3_1_ = lvt_5_1_;
         break;
      case SUBTITLE:
         lvt_4_1_ = lvt_5_1_;
         break;
      case RESET:
         this.field_147299_f.field_71456_v.func_175178_a("", "", -1, -1, -1);
         this.field_147299_f.field_71456_v.func_175177_a();
         return;
      }

      this.field_147299_f.field_71456_v.func_175178_a(lvt_3_1_, lvt_4_1_, p_175099_1_.func_179806_c(), p_175099_1_.func_179804_d(), p_175099_1_.func_179803_e());
   }

   public void func_175100_a(S46PacketSetCompressionLevel p_175100_1_) {
      if (!this.field_147302_e.func_150731_c()) {
         this.field_147302_e.func_179289_a(p_175100_1_.func_179760_a());
      }

   }

   public void func_175096_a(S47PacketPlayerListHeaderFooter p_175096_1_) {
      this.field_147299_f.field_71456_v.func_175181_h().func_175244_b(p_175096_1_.func_179700_a().func_150254_d().length() == 0 ? null : p_175096_1_.func_179700_a());
      this.field_147299_f.field_71456_v.func_175181_h().func_175248_a(p_175096_1_.func_179701_b().func_150254_d().length() == 0 ? null : p_175096_1_.func_179701_b());
   }

   public void func_147262_a(S1EPacketRemoveEntityEffect p_147262_1_) {
      PacketThreadUtil.func_180031_a(p_147262_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147262_1_.func_149076_c());
      if (lvt_2_1_ instanceof EntityLivingBase) {
         ((EntityLivingBase)lvt_2_1_).func_70618_n(p_147262_1_.func_149075_d());
      }

   }

   public void func_147256_a(S38PacketPlayerListItem p_147256_1_) {
      PacketThreadUtil.func_180031_a(p_147256_1_, this, this.field_147299_f);
      Iterator lvt_2_1_ = p_147256_1_.func_179767_a().iterator();

      while(lvt_2_1_.hasNext()) {
         S38PacketPlayerListItem.AddPlayerData lvt_3_1_ = (S38PacketPlayerListItem.AddPlayerData)lvt_2_1_.next();
         if (p_147256_1_.func_179768_b() == S38PacketPlayerListItem.Action.REMOVE_PLAYER) {
            this.field_147310_i.remove(lvt_3_1_.func_179962_a().getId());
         } else {
            NetworkPlayerInfo lvt_4_1_ = (NetworkPlayerInfo)this.field_147310_i.get(lvt_3_1_.func_179962_a().getId());
            if (p_147256_1_.func_179768_b() == S38PacketPlayerListItem.Action.ADD_PLAYER) {
               lvt_4_1_ = new NetworkPlayerInfo(lvt_3_1_);
               this.field_147310_i.put(lvt_4_1_.func_178845_a().getId(), lvt_4_1_);
            }

            if (lvt_4_1_ != null) {
               switch(p_147256_1_.func_179768_b()) {
               case ADD_PLAYER:
                  lvt_4_1_.func_178839_a(lvt_3_1_.func_179960_c());
                  lvt_4_1_.func_178838_a(lvt_3_1_.func_179963_b());
                  break;
               case UPDATE_GAME_MODE:
                  lvt_4_1_.func_178839_a(lvt_3_1_.func_179960_c());
                  break;
               case UPDATE_LATENCY:
                  lvt_4_1_.func_178838_a(lvt_3_1_.func_179963_b());
                  break;
               case UPDATE_DISPLAY_NAME:
                  lvt_4_1_.func_178859_a(lvt_3_1_.func_179961_d());
               }
            }
         }
      }

   }

   public void func_147272_a(S00PacketKeepAlive p_147272_1_) {
      this.func_147297_a(new C00PacketKeepAlive(p_147272_1_.func_149134_c()));
   }

   public void func_147270_a(S39PacketPlayerAbilities p_147270_1_) {
      PacketThreadUtil.func_180031_a(p_147270_1_, this, this.field_147299_f);
      EntityPlayer lvt_2_1_ = this.field_147299_f.field_71439_g;
      lvt_2_1_.field_71075_bZ.field_75100_b = p_147270_1_.func_149106_d();
      lvt_2_1_.field_71075_bZ.field_75098_d = p_147270_1_.func_149103_f();
      lvt_2_1_.field_71075_bZ.field_75102_a = p_147270_1_.func_149112_c();
      lvt_2_1_.field_71075_bZ.field_75101_c = p_147270_1_.func_149105_e();
      lvt_2_1_.field_71075_bZ.func_75092_a(p_147270_1_.func_149101_g());
      lvt_2_1_.field_71075_bZ.func_82877_b(p_147270_1_.func_149107_h());
   }

   public void func_147274_a(S3APacketTabComplete p_147274_1_) {
      PacketThreadUtil.func_180031_a(p_147274_1_, this, this.field_147299_f);
      String[] lvt_2_1_ = p_147274_1_.func_149630_c();
      if (this.field_147299_f.field_71462_r instanceof GuiChat) {
         GuiChat lvt_3_1_ = (GuiChat)this.field_147299_f.field_71462_r;
         lvt_3_1_.func_146406_a(lvt_2_1_);
      }

   }

   public void func_147255_a(S29PacketSoundEffect p_147255_1_) {
      PacketThreadUtil.func_180031_a(p_147255_1_, this, this.field_147299_f);
      this.field_147299_f.field_71441_e.func_72980_b(p_147255_1_.func_149207_d(), p_147255_1_.func_149211_e(), p_147255_1_.func_149210_f(), p_147255_1_.func_149212_c(), p_147255_1_.func_149208_g(), p_147255_1_.func_149209_h(), false);
   }

   public void func_175095_a(S48PacketResourcePackSend p_175095_1_) {
      final String lvt_2_1_ = p_175095_1_.func_179783_a();
      final String lvt_3_1_ = p_175095_1_.func_179784_b();
      if (lvt_2_1_.startsWith("level://")) {
         String lvt_4_1_ = lvt_2_1_.substring("level://".length());
         File lvt_5_1_ = new File(this.field_147299_f.field_71412_D, "saves");
         File lvt_6_1_ = new File(lvt_5_1_, lvt_4_1_);
         if (lvt_6_1_.isFile()) {
            this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.ACCEPTED));
            Futures.addCallback(this.field_147299_f.func_110438_M().func_177319_a(lvt_6_1_), new FutureCallback<Object>() {
               public void onSuccess(Object p_onSuccess_1_) {
                  NetHandlerPlayClient.this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
               }

               public void onFailure(Throwable p_onFailure_1_) {
                  NetHandlerPlayClient.this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
               }
            });
         } else {
            this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
         }

      } else {
         if (this.field_147299_f.func_147104_D() != null && this.field_147299_f.func_147104_D().func_152586_b() == ServerData.ServerResourceMode.ENABLED) {
            this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.ACCEPTED));
            Futures.addCallback(this.field_147299_f.func_110438_M().func_180601_a(lvt_2_1_, lvt_3_1_), new FutureCallback<Object>() {
               public void onSuccess(Object p_onSuccess_1_) {
                  NetHandlerPlayClient.this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
               }

               public void onFailure(Throwable p_onFailure_1_) {
                  NetHandlerPlayClient.this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
               }
            });
         } else if (this.field_147299_f.func_147104_D() != null && this.field_147299_f.func_147104_D().func_152586_b() != ServerData.ServerResourceMode.PROMPT) {
            this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.DECLINED));
         } else {
            this.field_147299_f.func_152344_a(new Runnable() {
               public void run() {
                  NetHandlerPlayClient.this.field_147299_f.func_147108_a(new GuiYesNo(new GuiYesNoCallback() {
                     public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {
                        NetHandlerPlayClient.this.field_147299_f = Minecraft.func_71410_x();
                        if (p_73878_1_) {
                           if (NetHandlerPlayClient.this.field_147299_f.func_147104_D() != null) {
                              NetHandlerPlayClient.this.field_147299_f.func_147104_D().func_152584_a(ServerData.ServerResourceMode.ENABLED);
                           }

                           NetHandlerPlayClient.this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.ACCEPTED));
                           Futures.addCallback(NetHandlerPlayClient.this.field_147299_f.func_110438_M().func_180601_a(lvt_2_1_, lvt_3_1_), new FutureCallback<Object>() {
                              public void onSuccess(Object p_onSuccess_1_) {
                                 NetHandlerPlayClient.this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
                              }

                              public void onFailure(Throwable p_onFailure_1_) {
                                 NetHandlerPlayClient.this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
                              }
                           });
                        } else {
                           if (NetHandlerPlayClient.this.field_147299_f.func_147104_D() != null) {
                              NetHandlerPlayClient.this.field_147299_f.func_147104_D().func_152584_a(ServerData.ServerResourceMode.DISABLED);
                           }

                           NetHandlerPlayClient.this.field_147302_e.func_179290_a(new C19PacketResourcePackStatus(lvt_3_1_, C19PacketResourcePackStatus.Action.DECLINED));
                        }

                        ServerList.func_147414_b(NetHandlerPlayClient.this.field_147299_f.func_147104_D());
                        NetHandlerPlayClient.this.field_147299_f.func_147108_a((GuiScreen)null);
                     }
                  }, I18n.func_135052_a("multiplayer.texturePrompt.line1"), I18n.func_135052_a("multiplayer.texturePrompt.line2"), 0));
               }
            });
         }

      }
   }

   public void func_175097_a(S49PacketUpdateEntityNBT p_175097_1_) {
      PacketThreadUtil.func_180031_a(p_175097_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = p_175097_1_.func_179764_a(this.field_147300_g);
      if (lvt_2_1_ != null) {
         lvt_2_1_.func_174834_g(p_175097_1_.func_179763_a());
      }

   }

   public void func_147240_a(S3FPacketCustomPayload p_147240_1_) {
      PacketThreadUtil.func_180031_a(p_147240_1_, this, this.field_147299_f);
      if ("MC|TrList".equals(p_147240_1_.func_149169_c())) {
         PacketBuffer lvt_2_1_ = p_147240_1_.func_180735_b();

         try {
            int lvt_3_1_ = lvt_2_1_.readInt();
            GuiScreen lvt_4_1_ = this.field_147299_f.field_71462_r;
            if (lvt_4_1_ != null && lvt_4_1_ instanceof GuiMerchant && lvt_3_1_ == this.field_147299_f.field_71439_g.field_71070_bA.field_75152_c) {
               IMerchant lvt_5_1_ = ((GuiMerchant)lvt_4_1_).func_147035_g();
               MerchantRecipeList lvt_6_1_ = MerchantRecipeList.func_151390_b(lvt_2_1_);
               lvt_5_1_.func_70930_a(lvt_6_1_);
            }
         } catch (IOException var10) {
            field_147301_d.error((String)"Couldn't load trade info", (Throwable)var10);
         } finally {
            lvt_2_1_.release();
         }
      } else if ("MC|Brand".equals(p_147240_1_.func_149169_c())) {
         this.field_147299_f.field_71439_g.func_175158_f(p_147240_1_.func_180735_b().func_150789_c(32767));
      } else if ("MC|BOpen".equals(p_147240_1_.func_149169_c())) {
         ItemStack lvt_2_2_ = this.field_147299_f.field_71439_g.func_71045_bC();
         if (lvt_2_2_ != null && lvt_2_2_.func_77973_b() == Items.field_151164_bB) {
            this.field_147299_f.func_147108_a(new GuiScreenBook(this.field_147299_f.field_71439_g, lvt_2_2_, false));
         }
      }

   }

   public void func_147291_a(S3BPacketScoreboardObjective p_147291_1_) {
      PacketThreadUtil.func_180031_a(p_147291_1_, this, this.field_147299_f);
      Scoreboard lvt_2_1_ = this.field_147300_g.func_96441_U();
      ScoreObjective lvt_3_2_;
      if (p_147291_1_.func_149338_e() == 0) {
         lvt_3_2_ = lvt_2_1_.func_96535_a(p_147291_1_.func_149339_c(), IScoreObjectiveCriteria.field_96641_b);
         lvt_3_2_.func_96681_a(p_147291_1_.func_149337_d());
         lvt_3_2_.func_178767_a(p_147291_1_.func_179817_d());
      } else {
         lvt_3_2_ = lvt_2_1_.func_96518_b(p_147291_1_.func_149339_c());
         if (p_147291_1_.func_149338_e() == 1) {
            lvt_2_1_.func_96519_k(lvt_3_2_);
         } else if (p_147291_1_.func_149338_e() == 2) {
            lvt_3_2_.func_96681_a(p_147291_1_.func_149337_d());
            lvt_3_2_.func_178767_a(p_147291_1_.func_179817_d());
         }
      }

   }

   public void func_147250_a(S3CPacketUpdateScore p_147250_1_) {
      PacketThreadUtil.func_180031_a(p_147250_1_, this, this.field_147299_f);
      Scoreboard lvt_2_1_ = this.field_147300_g.func_96441_U();
      ScoreObjective lvt_3_1_ = lvt_2_1_.func_96518_b(p_147250_1_.func_149321_d());
      if (p_147250_1_.func_180751_d() == S3CPacketUpdateScore.Action.CHANGE) {
         Score lvt_4_1_ = lvt_2_1_.func_96529_a(p_147250_1_.func_149324_c(), lvt_3_1_);
         lvt_4_1_.func_96647_c(p_147250_1_.func_149323_e());
      } else if (p_147250_1_.func_180751_d() == S3CPacketUpdateScore.Action.REMOVE) {
         if (StringUtils.func_151246_b(p_147250_1_.func_149321_d())) {
            lvt_2_1_.func_178822_d(p_147250_1_.func_149324_c(), (ScoreObjective)null);
         } else if (lvt_3_1_ != null) {
            lvt_2_1_.func_178822_d(p_147250_1_.func_149324_c(), lvt_3_1_);
         }
      }

   }

   public void func_147254_a(S3DPacketDisplayScoreboard p_147254_1_) {
      PacketThreadUtil.func_180031_a(p_147254_1_, this, this.field_147299_f);
      Scoreboard lvt_2_1_ = this.field_147300_g.func_96441_U();
      if (p_147254_1_.func_149370_d().length() == 0) {
         lvt_2_1_.func_96530_a(p_147254_1_.func_149371_c(), (ScoreObjective)null);
      } else {
         ScoreObjective lvt_3_1_ = lvt_2_1_.func_96518_b(p_147254_1_.func_149370_d());
         lvt_2_1_.func_96530_a(p_147254_1_.func_149371_c(), lvt_3_1_);
      }

   }

   public void func_147247_a(S3EPacketTeams p_147247_1_) {
      PacketThreadUtil.func_180031_a(p_147247_1_, this, this.field_147299_f);
      Scoreboard lvt_2_1_ = this.field_147300_g.func_96441_U();
      ScorePlayerTeam lvt_3_2_;
      if (p_147247_1_.func_149307_h() == 0) {
         lvt_3_2_ = lvt_2_1_.func_96527_f(p_147247_1_.func_149312_c());
      } else {
         lvt_3_2_ = lvt_2_1_.func_96508_e(p_147247_1_.func_149312_c());
      }

      if (p_147247_1_.func_149307_h() == 0 || p_147247_1_.func_149307_h() == 2) {
         lvt_3_2_.func_96664_a(p_147247_1_.func_149306_d());
         lvt_3_2_.func_96666_b(p_147247_1_.func_149311_e());
         lvt_3_2_.func_96662_c(p_147247_1_.func_149309_f());
         lvt_3_2_.func_178774_a(EnumChatFormatting.func_175744_a(p_147247_1_.func_179813_h()));
         lvt_3_2_.func_98298_a(p_147247_1_.func_149308_i());
         Team.EnumVisible lvt_4_1_ = Team.EnumVisible.func_178824_a(p_147247_1_.func_179814_i());
         if (lvt_4_1_ != null) {
            lvt_3_2_.func_178772_a(lvt_4_1_);
         }
      }

      String lvt_5_2_;
      Iterator lvt_4_3_;
      if (p_147247_1_.func_149307_h() == 0 || p_147247_1_.func_149307_h() == 3) {
         lvt_4_3_ = p_147247_1_.func_149310_g().iterator();

         while(lvt_4_3_.hasNext()) {
            lvt_5_2_ = (String)lvt_4_3_.next();
            lvt_2_1_.func_151392_a(lvt_5_2_, p_147247_1_.func_149312_c());
         }
      }

      if (p_147247_1_.func_149307_h() == 4) {
         lvt_4_3_ = p_147247_1_.func_149310_g().iterator();

         while(lvt_4_3_.hasNext()) {
            lvt_5_2_ = (String)lvt_4_3_.next();
            lvt_2_1_.func_96512_b(lvt_5_2_, lvt_3_2_);
         }
      }

      if (p_147247_1_.func_149307_h() == 1) {
         lvt_2_1_.func_96511_d(lvt_3_2_);
      }

   }

   public void func_147289_a(S2APacketParticles p_147289_1_) {
      PacketThreadUtil.func_180031_a(p_147289_1_, this, this.field_147299_f);
      if (p_147289_1_.func_149222_k() == 0) {
         double lvt_2_1_ = (double)(p_147289_1_.func_149227_j() * p_147289_1_.func_149221_g());
         double lvt_4_1_ = (double)(p_147289_1_.func_149227_j() * p_147289_1_.func_149224_h());
         double lvt_6_1_ = (double)(p_147289_1_.func_149227_j() * p_147289_1_.func_149223_i());

         try {
            this.field_147300_g.func_175682_a(p_147289_1_.func_179749_a(), p_147289_1_.func_179750_b(), p_147289_1_.func_149220_d(), p_147289_1_.func_149226_e(), p_147289_1_.func_149225_f(), lvt_2_1_, lvt_4_1_, lvt_6_1_, p_147289_1_.func_179748_k());
         } catch (Throwable var17) {
            field_147301_d.warn("Could not spawn particle effect " + p_147289_1_.func_179749_a());
         }
      } else {
         for(int lvt_2_2_ = 0; lvt_2_2_ < p_147289_1_.func_149222_k(); ++lvt_2_2_) {
            double lvt_3_1_ = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149221_g();
            double lvt_5_1_ = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149224_h();
            double lvt_7_1_ = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149223_i();
            double lvt_9_1_ = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149227_j();
            double lvt_11_1_ = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149227_j();
            double lvt_13_1_ = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149227_j();

            try {
               this.field_147300_g.func_175682_a(p_147289_1_.func_179749_a(), p_147289_1_.func_179750_b(), p_147289_1_.func_149220_d() + lvt_3_1_, p_147289_1_.func_149226_e() + lvt_5_1_, p_147289_1_.func_149225_f() + lvt_7_1_, lvt_9_1_, lvt_11_1_, lvt_13_1_, p_147289_1_.func_179748_k());
            } catch (Throwable var16) {
               field_147301_d.warn("Could not spawn particle effect " + p_147289_1_.func_179749_a());
               return;
            }
         }
      }

   }

   public void func_147290_a(S20PacketEntityProperties p_147290_1_) {
      PacketThreadUtil.func_180031_a(p_147290_1_, this, this.field_147299_f);
      Entity lvt_2_1_ = this.field_147300_g.func_73045_a(p_147290_1_.func_149442_c());
      if (lvt_2_1_ != null) {
         if (!(lvt_2_1_ instanceof EntityLivingBase)) {
            throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + lvt_2_1_ + ")");
         } else {
            BaseAttributeMap lvt_3_1_ = ((EntityLivingBase)lvt_2_1_).func_110140_aT();
            Iterator lvt_4_1_ = p_147290_1_.func_149441_d().iterator();

            while(lvt_4_1_.hasNext()) {
               S20PacketEntityProperties.Snapshot lvt_5_1_ = (S20PacketEntityProperties.Snapshot)lvt_4_1_.next();
               IAttributeInstance lvt_6_1_ = lvt_3_1_.func_111152_a(lvt_5_1_.func_151409_a());
               if (lvt_6_1_ == null) {
                  lvt_6_1_ = lvt_3_1_.func_111150_b(new RangedAttribute((IAttribute)null, lvt_5_1_.func_151409_a(), 0.0D, 2.2250738585072014E-308D, Double.MAX_VALUE));
               }

               lvt_6_1_.func_111128_a(lvt_5_1_.func_151410_b());
               lvt_6_1_.func_142049_d();
               Iterator lvt_7_1_ = lvt_5_1_.func_151408_c().iterator();

               while(lvt_7_1_.hasNext()) {
                  AttributeModifier lvt_8_1_ = (AttributeModifier)lvt_7_1_.next();
                  lvt_6_1_.func_111121_a(lvt_8_1_);
               }
            }

         }
      }
   }

   public NetworkManager func_147298_b() {
      return this.field_147302_e;
   }

   public Collection<NetworkPlayerInfo> func_175106_d() {
      return this.field_147310_i.values();
   }

   public NetworkPlayerInfo func_175102_a(UUID p_175102_1_) {
      return (NetworkPlayerInfo)this.field_147310_i.get(p_175102_1_);
   }

   public NetworkPlayerInfo func_175104_a(String p_175104_1_) {
      Iterator lvt_2_1_ = this.field_147310_i.values().iterator();

      NetworkPlayerInfo lvt_3_1_;
      do {
         if (!lvt_2_1_.hasNext()) {
            return null;
         }

         lvt_3_1_ = (NetworkPlayerInfo)lvt_2_1_.next();
      } while(!lvt_3_1_.func_178845_a().getName().equals(p_175104_1_));

      return lvt_3_1_;
   }

   public GameProfile func_175105_e() {
      return this.field_175107_d;
   }
}
