package net.minecraft.world;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEventData;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.INpc;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.village.VillageCollection;
import net.minecraft.village.VillageSiege;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldServer extends World implements IThreadListener {
   private static final Logger field_147491_a = LogManager.getLogger();
   private final MinecraftServer field_73061_a;
   private final EntityTracker field_73062_L;
   private final PlayerManager field_73063_M;
   private final Set<NextTickListEntry> field_73064_N = Sets.newHashSet();
   private final TreeSet<NextTickListEntry> field_73065_O = new TreeSet();
   private final Map<UUID, Entity> field_175741_N = Maps.newHashMap();
   public ChunkProviderServer field_73059_b;
   public boolean field_73058_d;
   private boolean field_73068_P;
   private int field_80004_Q;
   private final Teleporter field_85177_Q;
   private final SpawnerAnimals field_175742_R = new SpawnerAnimals();
   protected final VillageSiege field_175740_d = new VillageSiege(this);
   private WorldServer.ServerBlockEventList[] field_147490_S = new WorldServer.ServerBlockEventList[]{new WorldServer.ServerBlockEventList(), new WorldServer.ServerBlockEventList()};
   private int field_147489_T;
   private static final List<WeightedRandomChestContent> field_73069_S;
   private List<NextTickListEntry> field_94579_S = Lists.newArrayList();

   public WorldServer(MinecraftServer p_i45921_1_, ISaveHandler p_i45921_2_, WorldInfo p_i45921_3_, int p_i45921_4_, Profiler p_i45921_5_) {
      super(p_i45921_2_, p_i45921_3_, WorldProvider.func_76570_a(p_i45921_4_), p_i45921_5_, false);
      this.field_73061_a = p_i45921_1_;
      this.field_73062_L = new EntityTracker(this);
      this.field_73063_M = new PlayerManager(this);
      this.field_73011_w.func_76558_a(this);
      this.field_73020_y = this.func_72970_h();
      this.field_85177_Q = new Teleporter(this);
      this.func_72966_v();
      this.func_72947_a();
      this.func_175723_af().func_177725_a(p_i45921_1_.func_175580_aG());
   }

   public World func_175643_b() {
      this.field_72988_C = new MapStorage(this.field_73019_z);
      String lvt_1_1_ = VillageCollection.func_176062_a(this.field_73011_w);
      VillageCollection lvt_2_1_ = (VillageCollection)this.field_72988_C.func_75742_a(VillageCollection.class, lvt_1_1_);
      if (lvt_2_1_ == null) {
         this.field_72982_D = new VillageCollection(this);
         this.field_72988_C.func_75745_a(lvt_1_1_, this.field_72982_D);
      } else {
         this.field_72982_D = lvt_2_1_;
         this.field_72982_D.func_82566_a(this);
      }

      this.field_96442_D = new ServerScoreboard(this.field_73061_a);
      ScoreboardSaveData lvt_3_1_ = (ScoreboardSaveData)this.field_72988_C.func_75742_a(ScoreboardSaveData.class, "scoreboard");
      if (lvt_3_1_ == null) {
         lvt_3_1_ = new ScoreboardSaveData();
         this.field_72988_C.func_75745_a("scoreboard", lvt_3_1_);
      }

      lvt_3_1_.func_96499_a(this.field_96442_D);
      ((ServerScoreboard)this.field_96442_D).func_96547_a(lvt_3_1_);
      this.func_175723_af().func_177739_c(this.field_72986_A.func_176120_C(), this.field_72986_A.func_176126_D());
      this.func_175723_af().func_177744_c(this.field_72986_A.func_176140_I());
      this.func_175723_af().func_177724_b(this.field_72986_A.func_176138_H());
      this.func_175723_af().func_177747_c(this.field_72986_A.func_176131_J());
      this.func_175723_af().func_177723_b(this.field_72986_A.func_176139_K());
      if (this.field_72986_A.func_176134_F() > 0L) {
         this.func_175723_af().func_177738_a(this.field_72986_A.func_176137_E(), this.field_72986_A.func_176132_G(), this.field_72986_A.func_176134_F());
      } else {
         this.func_175723_af().func_177750_a(this.field_72986_A.func_176137_E());
      }

      return this;
   }

   public void func_72835_b() {
      super.func_72835_b();
      if (this.func_72912_H().func_76093_s() && this.func_175659_aa() != EnumDifficulty.HARD) {
         this.func_72912_H().func_176144_a(EnumDifficulty.HARD);
      }

      this.field_73011_w.func_177499_m().func_76938_b();
      if (this.func_73056_e()) {
         if (this.func_82736_K().func_82766_b("doDaylightCycle")) {
            long lvt_1_1_ = this.field_72986_A.func_76073_f() + 24000L;
            this.field_72986_A.func_76068_b(lvt_1_1_ - lvt_1_1_ % 24000L);
         }

         this.func_73053_d();
      }

      this.field_72984_F.func_76320_a("mobSpawner");
      if (this.func_82736_K().func_82766_b("doMobSpawning") && this.field_72986_A.func_76067_t() != WorldType.field_180272_g) {
         this.field_175742_R.func_77192_a(this, this.field_72985_G, this.field_72992_H, this.field_72986_A.func_82573_f() % 400L == 0L);
      }

      this.field_72984_F.func_76318_c("chunkSource");
      this.field_73020_y.func_73156_b();
      int lvt_1_2_ = this.func_72967_a(1.0F);
      if (lvt_1_2_ != this.func_175657_ab()) {
         this.func_175692_b(lvt_1_2_);
      }

      this.field_72986_A.func_82572_b(this.field_72986_A.func_82573_f() + 1L);
      if (this.func_82736_K().func_82766_b("doDaylightCycle")) {
         this.field_72986_A.func_76068_b(this.field_72986_A.func_76073_f() + 1L);
      }

      this.field_72984_F.func_76318_c("tickPending");
      this.func_72955_a(false);
      this.field_72984_F.func_76318_c("tickBlocks");
      this.func_147456_g();
      this.field_72984_F.func_76318_c("chunkMap");
      this.field_73063_M.func_72693_b();
      this.field_72984_F.func_76318_c("village");
      this.field_72982_D.func_75544_a();
      this.field_175740_d.func_75528_a();
      this.field_72984_F.func_76318_c("portalForcer");
      this.field_85177_Q.func_85189_a(this.func_82737_E());
      this.field_72984_F.func_76319_b();
      this.func_147488_Z();
   }

   public BiomeGenBase.SpawnListEntry func_175734_a(EnumCreatureType p_175734_1_, BlockPos p_175734_2_) {
      List<BiomeGenBase.SpawnListEntry> lvt_3_1_ = this.func_72863_F().func_177458_a(p_175734_1_, p_175734_2_);
      return lvt_3_1_ != null && !lvt_3_1_.isEmpty() ? (BiomeGenBase.SpawnListEntry)WeightedRandom.func_76271_a(this.field_73012_v, lvt_3_1_) : null;
   }

   public boolean func_175732_a(EnumCreatureType p_175732_1_, BiomeGenBase.SpawnListEntry p_175732_2_, BlockPos p_175732_3_) {
      List<BiomeGenBase.SpawnListEntry> lvt_4_1_ = this.func_72863_F().func_177458_a(p_175732_1_, p_175732_3_);
      return lvt_4_1_ != null && !lvt_4_1_.isEmpty() ? lvt_4_1_.contains(p_175732_2_) : false;
   }

   public void func_72854_c() {
      this.field_73068_P = false;
      if (!this.field_73010_i.isEmpty()) {
         int lvt_1_1_ = 0;
         int lvt_2_1_ = 0;
         Iterator lvt_3_1_ = this.field_73010_i.iterator();

         while(lvt_3_1_.hasNext()) {
            EntityPlayer lvt_4_1_ = (EntityPlayer)lvt_3_1_.next();
            if (lvt_4_1_.func_175149_v()) {
               ++lvt_1_1_;
            } else if (lvt_4_1_.func_70608_bn()) {
               ++lvt_2_1_;
            }
         }

         this.field_73068_P = lvt_2_1_ > 0 && lvt_2_1_ >= this.field_73010_i.size() - lvt_1_1_;
      }

   }

   protected void func_73053_d() {
      this.field_73068_P = false;
      Iterator lvt_1_1_ = this.field_73010_i.iterator();

      while(lvt_1_1_.hasNext()) {
         EntityPlayer lvt_2_1_ = (EntityPlayer)lvt_1_1_.next();
         if (lvt_2_1_.func_70608_bn()) {
            lvt_2_1_.func_70999_a(false, false, true);
         }
      }

      this.func_73051_P();
   }

   private void func_73051_P() {
      this.field_72986_A.func_76080_g(0);
      this.field_72986_A.func_76084_b(false);
      this.field_72986_A.func_76090_f(0);
      this.field_72986_A.func_76069_a(false);
   }

   public boolean func_73056_e() {
      if (this.field_73068_P && !this.field_72995_K) {
         Iterator lvt_1_1_ = this.field_73010_i.iterator();

         EntityPlayer lvt_2_1_;
         do {
            if (!lvt_1_1_.hasNext()) {
               return true;
            }

            lvt_2_1_ = (EntityPlayer)lvt_1_1_.next();
         } while(!lvt_2_1_.func_175149_v() && lvt_2_1_.func_71026_bH());

         return false;
      } else {
         return false;
      }
   }

   public void func_72974_f() {
      if (this.field_72986_A.func_76075_d() <= 0) {
         this.field_72986_A.func_76056_b(this.func_181545_F() + 1);
      }

      int lvt_1_1_ = this.field_72986_A.func_76079_c();
      int lvt_2_1_ = this.field_72986_A.func_76074_e();
      int lvt_3_1_ = 0;

      while(this.func_175703_c(new BlockPos(lvt_1_1_, 0, lvt_2_1_)).func_149688_o() == Material.field_151579_a) {
         lvt_1_1_ += this.field_73012_v.nextInt(8) - this.field_73012_v.nextInt(8);
         lvt_2_1_ += this.field_73012_v.nextInt(8) - this.field_73012_v.nextInt(8);
         ++lvt_3_1_;
         if (lvt_3_1_ == 10000) {
            break;
         }
      }

      this.field_72986_A.func_76058_a(lvt_1_1_);
      this.field_72986_A.func_76087_c(lvt_2_1_);
   }

   protected void func_147456_g() {
      super.func_147456_g();
      if (this.field_72986_A.func_76067_t() == WorldType.field_180272_g) {
         Iterator lvt_1_1_ = this.field_72993_I.iterator();

         while(lvt_1_1_.hasNext()) {
            ChunkCoordIntPair lvt_2_1_ = (ChunkCoordIntPair)lvt_1_1_.next();
            this.func_72964_e(lvt_2_1_.field_77276_a, lvt_2_1_.field_77275_b).func_150804_b(false);
         }

      } else {
         int lvt_1_2_ = 0;
         int lvt_2_2_ = 0;

         for(Iterator lvt_3_1_ = this.field_72993_I.iterator(); lvt_3_1_.hasNext(); this.field_72984_F.func_76319_b()) {
            ChunkCoordIntPair lvt_4_1_ = (ChunkCoordIntPair)lvt_3_1_.next();
            int lvt_5_1_ = lvt_4_1_.field_77276_a * 16;
            int lvt_6_1_ = lvt_4_1_.field_77275_b * 16;
            this.field_72984_F.func_76320_a("getChunk");
            Chunk lvt_7_1_ = this.func_72964_e(lvt_4_1_.field_77276_a, lvt_4_1_.field_77275_b);
            this.func_147467_a(lvt_5_1_, lvt_6_1_, lvt_7_1_);
            this.field_72984_F.func_76318_c("tickChunk");
            lvt_7_1_.func_150804_b(false);
            this.field_72984_F.func_76318_c("thunder");
            int lvt_8_3_;
            BlockPos lvt_9_2_;
            if (this.field_73012_v.nextInt(100000) == 0 && this.func_72896_J() && this.func_72911_I()) {
               this.field_73005_l = this.field_73005_l * 3 + 1013904223;
               lvt_8_3_ = this.field_73005_l >> 2;
               lvt_9_2_ = this.func_175736_a(new BlockPos(lvt_5_1_ + (lvt_8_3_ & 15), 0, lvt_6_1_ + (lvt_8_3_ >> 8 & 15)));
               if (this.func_175727_C(lvt_9_2_)) {
                  this.func_72942_c(new EntityLightningBolt(this, (double)lvt_9_2_.func_177958_n(), (double)lvt_9_2_.func_177956_o(), (double)lvt_9_2_.func_177952_p()));
               }
            }

            this.field_72984_F.func_76318_c("iceandsnow");
            if (this.field_73012_v.nextInt(16) == 0) {
               this.field_73005_l = this.field_73005_l * 3 + 1013904223;
               lvt_8_3_ = this.field_73005_l >> 2;
               lvt_9_2_ = this.func_175725_q(new BlockPos(lvt_5_1_ + (lvt_8_3_ & 15), 0, lvt_6_1_ + (lvt_8_3_ >> 8 & 15)));
               BlockPos lvt_10_1_ = lvt_9_2_.func_177977_b();
               if (this.func_175662_w(lvt_10_1_)) {
                  this.func_175656_a(lvt_10_1_, Blocks.field_150432_aD.func_176223_P());
               }

               if (this.func_72896_J() && this.func_175708_f(lvt_9_2_, true)) {
                  this.func_175656_a(lvt_9_2_, Blocks.field_150431_aC.func_176223_P());
               }

               if (this.func_72896_J() && this.func_180494_b(lvt_10_1_).func_76738_d()) {
                  this.func_180495_p(lvt_10_1_).func_177230_c().func_176224_k(this, lvt_10_1_);
               }
            }

            this.field_72984_F.func_76318_c("tickBlocks");
            lvt_8_3_ = this.func_82736_K().func_180263_c("randomTickSpeed");
            if (lvt_8_3_ > 0) {
               ExtendedBlockStorage[] lvt_9_3_ = lvt_7_1_.func_76587_i();
               int lvt_10_2_ = lvt_9_3_.length;

               for(int lvt_11_1_ = 0; lvt_11_1_ < lvt_10_2_; ++lvt_11_1_) {
                  ExtendedBlockStorage lvt_12_1_ = lvt_9_3_[lvt_11_1_];
                  if (lvt_12_1_ != null && lvt_12_1_.func_76675_b()) {
                     for(int lvt_13_1_ = 0; lvt_13_1_ < lvt_8_3_; ++lvt_13_1_) {
                        this.field_73005_l = this.field_73005_l * 3 + 1013904223;
                        int lvt_14_1_ = this.field_73005_l >> 2;
                        int lvt_15_1_ = lvt_14_1_ & 15;
                        int lvt_16_1_ = lvt_14_1_ >> 8 & 15;
                        int lvt_17_1_ = lvt_14_1_ >> 16 & 15;
                        ++lvt_2_2_;
                        IBlockState lvt_18_1_ = lvt_12_1_.func_177485_a(lvt_15_1_, lvt_17_1_, lvt_16_1_);
                        Block lvt_19_1_ = lvt_18_1_.func_177230_c();
                        if (lvt_19_1_.func_149653_t()) {
                           ++lvt_1_2_;
                           lvt_19_1_.func_180645_a(this, new BlockPos(lvt_15_1_ + lvt_5_1_, lvt_17_1_ + lvt_12_1_.func_76662_d(), lvt_16_1_ + lvt_6_1_), lvt_18_1_, this.field_73012_v);
                        }
                     }
                  }
               }
            }
         }

      }
   }

   protected BlockPos func_175736_a(BlockPos p_175736_1_) {
      BlockPos lvt_2_1_ = this.func_175725_q(p_175736_1_);
      AxisAlignedBB lvt_3_1_ = (new AxisAlignedBB(lvt_2_1_, new BlockPos(lvt_2_1_.func_177958_n(), this.func_72800_K(), lvt_2_1_.func_177952_p()))).func_72314_b(3.0D, 3.0D, 3.0D);
      List<EntityLivingBase> lvt_4_1_ = this.func_175647_a(EntityLivingBase.class, lvt_3_1_, new Predicate<EntityLivingBase>() {
         public boolean apply(EntityLivingBase p_apply_1_) {
            return p_apply_1_ != null && p_apply_1_.func_70089_S() && WorldServer.this.func_175678_i(p_apply_1_.func_180425_c());
         }
      });
      return !lvt_4_1_.isEmpty() ? ((EntityLivingBase)lvt_4_1_.get(this.field_73012_v.nextInt(lvt_4_1_.size()))).func_180425_c() : lvt_2_1_;
   }

   public boolean func_175691_a(BlockPos p_175691_1_, Block p_175691_2_) {
      NextTickListEntry lvt_3_1_ = new NextTickListEntry(p_175691_1_, p_175691_2_);
      return this.field_94579_S.contains(lvt_3_1_);
   }

   public void func_175684_a(BlockPos p_175684_1_, Block p_175684_2_, int p_175684_3_) {
      this.func_175654_a(p_175684_1_, p_175684_2_, p_175684_3_, 0);
   }

   public void func_175654_a(BlockPos p_175654_1_, Block p_175654_2_, int p_175654_3_, int p_175654_4_) {
      NextTickListEntry lvt_5_1_ = new NextTickListEntry(p_175654_1_, p_175654_2_);
      int lvt_6_1_ = 0;
      if (this.field_72999_e && p_175654_2_.func_149688_o() != Material.field_151579_a) {
         if (p_175654_2_.func_149698_L()) {
            lvt_6_1_ = 8;
            if (this.func_175707_a(lvt_5_1_.field_180282_a.func_177982_a(-lvt_6_1_, -lvt_6_1_, -lvt_6_1_), lvt_5_1_.field_180282_a.func_177982_a(lvt_6_1_, lvt_6_1_, lvt_6_1_))) {
               IBlockState lvt_7_1_ = this.func_180495_p(lvt_5_1_.field_180282_a);
               if (lvt_7_1_.func_177230_c().func_149688_o() != Material.field_151579_a && lvt_7_1_.func_177230_c() == lvt_5_1_.func_151351_a()) {
                  lvt_7_1_.func_177230_c().func_180650_b(this, lvt_5_1_.field_180282_a, lvt_7_1_, this.field_73012_v);
               }
            }

            return;
         }

         p_175654_3_ = 1;
      }

      if (this.func_175707_a(p_175654_1_.func_177982_a(-lvt_6_1_, -lvt_6_1_, -lvt_6_1_), p_175654_1_.func_177982_a(lvt_6_1_, lvt_6_1_, lvt_6_1_))) {
         if (p_175654_2_.func_149688_o() != Material.field_151579_a) {
            lvt_5_1_.func_77176_a((long)p_175654_3_ + this.field_72986_A.func_82573_f());
            lvt_5_1_.func_82753_a(p_175654_4_);
         }

         if (!this.field_73064_N.contains(lvt_5_1_)) {
            this.field_73064_N.add(lvt_5_1_);
            this.field_73065_O.add(lvt_5_1_);
         }
      }

   }

   public void func_180497_b(BlockPos p_180497_1_, Block p_180497_2_, int p_180497_3_, int p_180497_4_) {
      NextTickListEntry lvt_5_1_ = new NextTickListEntry(p_180497_1_, p_180497_2_);
      lvt_5_1_.func_82753_a(p_180497_4_);
      if (p_180497_2_.func_149688_o() != Material.field_151579_a) {
         lvt_5_1_.func_77176_a((long)p_180497_3_ + this.field_72986_A.func_82573_f());
      }

      if (!this.field_73064_N.contains(lvt_5_1_)) {
         this.field_73064_N.add(lvt_5_1_);
         this.field_73065_O.add(lvt_5_1_);
      }

   }

   public void func_72939_s() {
      if (this.field_73010_i.isEmpty()) {
         if (this.field_80004_Q++ >= 1200) {
            return;
         }
      } else {
         this.func_82742_i();
      }

      super.func_72939_s();
   }

   public void func_82742_i() {
      this.field_80004_Q = 0;
   }

   public boolean func_72955_a(boolean p_72955_1_) {
      if (this.field_72986_A.func_76067_t() == WorldType.field_180272_g) {
         return false;
      } else {
         int lvt_2_1_ = this.field_73065_O.size();
         if (lvt_2_1_ != this.field_73064_N.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
         } else {
            if (lvt_2_1_ > 1000) {
               lvt_2_1_ = 1000;
            }

            this.field_72984_F.func_76320_a("cleaning");

            NextTickListEntry lvt_4_2_;
            for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
               lvt_4_2_ = (NextTickListEntry)this.field_73065_O.first();
               if (!p_72955_1_ && lvt_4_2_.field_77180_e > this.field_72986_A.func_82573_f()) {
                  break;
               }

               this.field_73065_O.remove(lvt_4_2_);
               this.field_73064_N.remove(lvt_4_2_);
               this.field_94579_S.add(lvt_4_2_);
            }

            this.field_72984_F.func_76319_b();
            this.field_72984_F.func_76320_a("ticking");
            Iterator lvt_3_2_ = this.field_94579_S.iterator();

            while(lvt_3_2_.hasNext()) {
               lvt_4_2_ = (NextTickListEntry)lvt_3_2_.next();
               lvt_3_2_.remove();
               int lvt_5_1_ = 0;
               if (this.func_175707_a(lvt_4_2_.field_180282_a.func_177982_a(-lvt_5_1_, -lvt_5_1_, -lvt_5_1_), lvt_4_2_.field_180282_a.func_177982_a(lvt_5_1_, lvt_5_1_, lvt_5_1_))) {
                  IBlockState lvt_6_1_ = this.func_180495_p(lvt_4_2_.field_180282_a);
                  if (lvt_6_1_.func_177230_c().func_149688_o() != Material.field_151579_a && Block.func_149680_a(lvt_6_1_.func_177230_c(), lvt_4_2_.func_151351_a())) {
                     try {
                        lvt_6_1_.func_177230_c().func_180650_b(this, lvt_4_2_.field_180282_a, lvt_6_1_, this.field_73012_v);
                     } catch (Throwable var10) {
                        CrashReport lvt_8_1_ = CrashReport.func_85055_a(var10, "Exception while ticking a block");
                        CrashReportCategory lvt_9_1_ = lvt_8_1_.func_85058_a("Block being ticked");
                        CrashReportCategory.func_175750_a(lvt_9_1_, lvt_4_2_.field_180282_a, lvt_6_1_);
                        throw new ReportedException(lvt_8_1_);
                     }
                  }
               } else {
                  this.func_175684_a(lvt_4_2_.field_180282_a, lvt_4_2_.func_151351_a(), 0);
               }
            }

            this.field_72984_F.func_76319_b();
            this.field_94579_S.clear();
            return !this.field_73065_O.isEmpty();
         }
      }
   }

   public List<NextTickListEntry> func_72920_a(Chunk p_72920_1_, boolean p_72920_2_) {
      ChunkCoordIntPair lvt_3_1_ = p_72920_1_.func_76632_l();
      int lvt_4_1_ = (lvt_3_1_.field_77276_a << 4) - 2;
      int lvt_5_1_ = lvt_4_1_ + 16 + 2;
      int lvt_6_1_ = (lvt_3_1_.field_77275_b << 4) - 2;
      int lvt_7_1_ = lvt_6_1_ + 16 + 2;
      return this.func_175712_a(new StructureBoundingBox(lvt_4_1_, 0, lvt_6_1_, lvt_5_1_, 256, lvt_7_1_), p_72920_2_);
   }

   public List<NextTickListEntry> func_175712_a(StructureBoundingBox p_175712_1_, boolean p_175712_2_) {
      List<NextTickListEntry> lvt_3_1_ = null;

      for(int lvt_4_1_ = 0; lvt_4_1_ < 2; ++lvt_4_1_) {
         Iterator lvt_5_2_;
         if (lvt_4_1_ == 0) {
            lvt_5_2_ = this.field_73065_O.iterator();
         } else {
            lvt_5_2_ = this.field_94579_S.iterator();
         }

         while(lvt_5_2_.hasNext()) {
            NextTickListEntry lvt_6_1_ = (NextTickListEntry)lvt_5_2_.next();
            BlockPos lvt_7_1_ = lvt_6_1_.field_180282_a;
            if (lvt_7_1_.func_177958_n() >= p_175712_1_.field_78897_a && lvt_7_1_.func_177958_n() < p_175712_1_.field_78893_d && lvt_7_1_.func_177952_p() >= p_175712_1_.field_78896_c && lvt_7_1_.func_177952_p() < p_175712_1_.field_78892_f) {
               if (p_175712_2_) {
                  this.field_73064_N.remove(lvt_6_1_);
                  lvt_5_2_.remove();
               }

               if (lvt_3_1_ == null) {
                  lvt_3_1_ = Lists.newArrayList();
               }

               lvt_3_1_.add(lvt_6_1_);
            }
         }
      }

      return lvt_3_1_;
   }

   public void func_72866_a(Entity p_72866_1_, boolean p_72866_2_) {
      if (!this.func_175735_ai() && (p_72866_1_ instanceof EntityAnimal || p_72866_1_ instanceof EntityWaterMob)) {
         p_72866_1_.func_70106_y();
      }

      if (!this.func_175738_ah() && p_72866_1_ instanceof INpc) {
         p_72866_1_.func_70106_y();
      }

      super.func_72866_a(p_72866_1_, p_72866_2_);
   }

   private boolean func_175738_ah() {
      return this.field_73061_a.func_71220_V();
   }

   private boolean func_175735_ai() {
      return this.field_73061_a.func_71268_U();
   }

   protected IChunkProvider func_72970_h() {
      IChunkLoader lvt_1_1_ = this.field_73019_z.func_75763_a(this.field_73011_w);
      this.field_73059_b = new ChunkProviderServer(this, lvt_1_1_, this.field_73011_w.func_76555_c());
      return this.field_73059_b;
   }

   public List<TileEntity> func_147486_a(int p_147486_1_, int p_147486_2_, int p_147486_3_, int p_147486_4_, int p_147486_5_, int p_147486_6_) {
      List<TileEntity> lvt_7_1_ = Lists.newArrayList();

      for(int lvt_8_1_ = 0; lvt_8_1_ < this.field_147482_g.size(); ++lvt_8_1_) {
         TileEntity lvt_9_1_ = (TileEntity)this.field_147482_g.get(lvt_8_1_);
         BlockPos lvt_10_1_ = lvt_9_1_.func_174877_v();
         if (lvt_10_1_.func_177958_n() >= p_147486_1_ && lvt_10_1_.func_177956_o() >= p_147486_2_ && lvt_10_1_.func_177952_p() >= p_147486_3_ && lvt_10_1_.func_177958_n() < p_147486_4_ && lvt_10_1_.func_177956_o() < p_147486_5_ && lvt_10_1_.func_177952_p() < p_147486_6_) {
            lvt_7_1_.add(lvt_9_1_);
         }
      }

      return lvt_7_1_;
   }

   public boolean func_175660_a(EntityPlayer p_175660_1_, BlockPos p_175660_2_) {
      return !this.field_73061_a.func_175579_a(this, p_175660_2_, p_175660_1_) && this.func_175723_af().func_177746_a(p_175660_2_);
   }

   public void func_72963_a(WorldSettings p_72963_1_) {
      if (!this.field_72986_A.func_76070_v()) {
         try {
            this.func_73052_b(p_72963_1_);
            if (this.field_72986_A.func_76067_t() == WorldType.field_180272_g) {
               this.func_175737_aj();
            }

            super.func_72963_a(p_72963_1_);
         } catch (Throwable var6) {
            CrashReport lvt_3_1_ = CrashReport.func_85055_a(var6, "Exception initializing level");

            try {
               this.func_72914_a(lvt_3_1_);
            } catch (Throwable var5) {
            }

            throw new ReportedException(lvt_3_1_);
         }

         this.field_72986_A.func_76091_d(true);
      }

   }

   private void func_175737_aj() {
      this.field_72986_A.func_176128_f(false);
      this.field_72986_A.func_176121_c(true);
      this.field_72986_A.func_76084_b(false);
      this.field_72986_A.func_76069_a(false);
      this.field_72986_A.func_176142_i(1000000000);
      this.field_72986_A.func_76068_b(6000L);
      this.field_72986_A.func_76060_a(WorldSettings.GameType.SPECTATOR);
      this.field_72986_A.func_176119_g(false);
      this.field_72986_A.func_176144_a(EnumDifficulty.PEACEFUL);
      this.field_72986_A.func_180783_e(true);
      this.func_82736_K().func_82764_b("doDaylightCycle", "false");
   }

   private void func_73052_b(WorldSettings p_73052_1_) {
      if (!this.field_73011_w.func_76567_e()) {
         this.field_72986_A.func_176143_a(BlockPos.field_177992_a.func_177981_b(this.field_73011_w.func_76557_i()));
      } else if (this.field_72986_A.func_76067_t() == WorldType.field_180272_g) {
         this.field_72986_A.func_176143_a(BlockPos.field_177992_a.func_177984_a());
      } else {
         this.field_72987_B = true;
         WorldChunkManager lvt_2_1_ = this.field_73011_w.func_177499_m();
         List<BiomeGenBase> lvt_3_1_ = lvt_2_1_.func_76932_a();
         Random lvt_4_1_ = new Random(this.func_72905_C());
         BlockPos lvt_5_1_ = lvt_2_1_.func_180630_a(0, 0, 256, lvt_3_1_, lvt_4_1_);
         int lvt_6_1_ = 0;
         int lvt_7_1_ = this.field_73011_w.func_76557_i();
         int lvt_8_1_ = 0;
         if (lvt_5_1_ != null) {
            lvt_6_1_ = lvt_5_1_.func_177958_n();
            lvt_8_1_ = lvt_5_1_.func_177952_p();
         } else {
            field_147491_a.warn("Unable to find spawn biome");
         }

         int lvt_9_1_ = 0;

         while(!this.field_73011_w.func_76566_a(lvt_6_1_, lvt_8_1_)) {
            lvt_6_1_ += lvt_4_1_.nextInt(64) - lvt_4_1_.nextInt(64);
            lvt_8_1_ += lvt_4_1_.nextInt(64) - lvt_4_1_.nextInt(64);
            ++lvt_9_1_;
            if (lvt_9_1_ == 1000) {
               break;
            }
         }

         this.field_72986_A.func_176143_a(new BlockPos(lvt_6_1_, lvt_7_1_, lvt_8_1_));
         this.field_72987_B = false;
         if (p_73052_1_.func_77167_c()) {
            this.func_73047_i();
         }

      }
   }

   protected void func_73047_i() {
      WorldGeneratorBonusChest lvt_1_1_ = new WorldGeneratorBonusChest(field_73069_S, 10);

      for(int lvt_2_1_ = 0; lvt_2_1_ < 10; ++lvt_2_1_) {
         int lvt_3_1_ = this.field_72986_A.func_76079_c() + this.field_73012_v.nextInt(6) - this.field_73012_v.nextInt(6);
         int lvt_4_1_ = this.field_72986_A.func_76074_e() + this.field_73012_v.nextInt(6) - this.field_73012_v.nextInt(6);
         BlockPos lvt_5_1_ = this.func_175672_r(new BlockPos(lvt_3_1_, 0, lvt_4_1_)).func_177984_a();
         if (lvt_1_1_.func_180709_b(this, this.field_73012_v, lvt_5_1_)) {
            break;
         }
      }

   }

   public BlockPos func_180504_m() {
      return this.field_73011_w.func_177496_h();
   }

   public void func_73044_a(boolean p_73044_1_, IProgressUpdate p_73044_2_) throws MinecraftException {
      if (this.field_73020_y.func_73157_c()) {
         if (p_73044_2_ != null) {
            p_73044_2_.func_73720_a("Saving level");
         }

         this.func_73042_a();
         if (p_73044_2_ != null) {
            p_73044_2_.func_73719_c("Saving chunks");
         }

         this.field_73020_y.func_73151_a(p_73044_1_, p_73044_2_);
         List<Chunk> lvt_3_1_ = Lists.newArrayList((Iterable)this.field_73059_b.func_152380_a());
         Iterator lvt_4_1_ = lvt_3_1_.iterator();

         while(lvt_4_1_.hasNext()) {
            Chunk lvt_5_1_ = (Chunk)lvt_4_1_.next();
            if (lvt_5_1_ != null && !this.field_73063_M.func_152621_a(lvt_5_1_.field_76635_g, lvt_5_1_.field_76647_h)) {
               this.field_73059_b.func_73241_b(lvt_5_1_.field_76635_g, lvt_5_1_.field_76647_h);
            }
         }

      }
   }

   public void func_104140_m() {
      if (this.field_73020_y.func_73157_c()) {
         this.field_73020_y.func_104112_b();
      }
   }

   protected void func_73042_a() throws MinecraftException {
      this.func_72906_B();
      this.field_72986_A.func_176145_a(this.func_175723_af().func_177741_h());
      this.field_72986_A.func_176124_d(this.func_175723_af().func_177731_f());
      this.field_72986_A.func_176141_c(this.func_175723_af().func_177721_g());
      this.field_72986_A.func_176129_e(this.func_175723_af().func_177742_m());
      this.field_72986_A.func_176125_f(this.func_175723_af().func_177727_n());
      this.field_72986_A.func_176122_j(this.func_175723_af().func_177748_q());
      this.field_72986_A.func_176136_k(this.func_175723_af().func_177740_p());
      this.field_72986_A.func_176118_b(this.func_175723_af().func_177751_j());
      this.field_72986_A.func_176135_e(this.func_175723_af().func_177732_i());
      this.field_73019_z.func_75755_a(this.field_72986_A, this.field_73061_a.func_71203_ab().func_72378_q());
      this.field_72988_C.func_75744_a();
   }

   protected void func_72923_a(Entity p_72923_1_) {
      super.func_72923_a(p_72923_1_);
      this.field_175729_l.func_76038_a(p_72923_1_.func_145782_y(), p_72923_1_);
      this.field_175741_N.put(p_72923_1_.func_110124_au(), p_72923_1_);
      Entity[] lvt_2_1_ = p_72923_1_.func_70021_al();
      if (lvt_2_1_ != null) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.length; ++lvt_3_1_) {
            this.field_175729_l.func_76038_a(lvt_2_1_[lvt_3_1_].func_145782_y(), lvt_2_1_[lvt_3_1_]);
         }
      }

   }

   protected void func_72847_b(Entity p_72847_1_) {
      super.func_72847_b(p_72847_1_);
      this.field_175729_l.func_76049_d(p_72847_1_.func_145782_y());
      this.field_175741_N.remove(p_72847_1_.func_110124_au());
      Entity[] lvt_2_1_ = p_72847_1_.func_70021_al();
      if (lvt_2_1_ != null) {
         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.length; ++lvt_3_1_) {
            this.field_175729_l.func_76049_d(lvt_2_1_[lvt_3_1_].func_145782_y());
         }
      }

   }

   public boolean func_72942_c(Entity p_72942_1_) {
      if (super.func_72942_c(p_72942_1_)) {
         this.field_73061_a.func_71203_ab().func_148541_a(p_72942_1_.field_70165_t, p_72942_1_.field_70163_u, p_72942_1_.field_70161_v, 512.0D, this.field_73011_w.func_177502_q(), new S2CPacketSpawnGlobalEntity(p_72942_1_));
         return true;
      } else {
         return false;
      }
   }

   public void func_72960_a(Entity p_72960_1_, byte p_72960_2_) {
      this.func_73039_n().func_151248_b(p_72960_1_, new S19PacketEntityStatus(p_72960_1_, p_72960_2_));
   }

   public Explosion func_72885_a(Entity p_72885_1_, double p_72885_2_, double p_72885_4_, double p_72885_6_, float p_72885_8_, boolean p_72885_9_, boolean p_72885_10_) {
      Explosion lvt_11_1_ = new Explosion(this, p_72885_1_, p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_, p_72885_9_, p_72885_10_);
      lvt_11_1_.func_77278_a();
      lvt_11_1_.func_77279_a(false);
      if (!p_72885_10_) {
         lvt_11_1_.func_180342_d();
      }

      Iterator lvt_12_1_ = this.field_73010_i.iterator();

      while(lvt_12_1_.hasNext()) {
         EntityPlayer lvt_13_1_ = (EntityPlayer)lvt_12_1_.next();
         if (lvt_13_1_.func_70092_e(p_72885_2_, p_72885_4_, p_72885_6_) < 4096.0D) {
            ((EntityPlayerMP)lvt_13_1_).field_71135_a.func_147359_a(new S27PacketExplosion(p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_, lvt_11_1_.func_180343_e(), (Vec3)lvt_11_1_.func_77277_b().get(lvt_13_1_)));
         }
      }

      return lvt_11_1_;
   }

   public void func_175641_c(BlockPos p_175641_1_, Block p_175641_2_, int p_175641_3_, int p_175641_4_) {
      BlockEventData lvt_5_1_ = new BlockEventData(p_175641_1_, p_175641_2_, p_175641_3_, p_175641_4_);
      Iterator lvt_6_1_ = this.field_147490_S[this.field_147489_T].iterator();

      BlockEventData lvt_7_1_;
      do {
         if (!lvt_6_1_.hasNext()) {
            this.field_147490_S[this.field_147489_T].add(lvt_5_1_);
            return;
         }

         lvt_7_1_ = (BlockEventData)lvt_6_1_.next();
      } while(!lvt_7_1_.equals(lvt_5_1_));

   }

   private void func_147488_Z() {
      while(!this.field_147490_S[this.field_147489_T].isEmpty()) {
         int lvt_1_1_ = this.field_147489_T;
         this.field_147489_T ^= 1;
         Iterator lvt_2_1_ = this.field_147490_S[lvt_1_1_].iterator();

         while(lvt_2_1_.hasNext()) {
            BlockEventData lvt_3_1_ = (BlockEventData)lvt_2_1_.next();
            if (this.func_147485_a(lvt_3_1_)) {
               this.field_73061_a.func_71203_ab().func_148541_a((double)lvt_3_1_.func_180328_a().func_177958_n(), (double)lvt_3_1_.func_180328_a().func_177956_o(), (double)lvt_3_1_.func_180328_a().func_177952_p(), 64.0D, this.field_73011_w.func_177502_q(), new S24PacketBlockAction(lvt_3_1_.func_180328_a(), lvt_3_1_.func_151337_f(), lvt_3_1_.func_151339_d(), lvt_3_1_.func_151338_e()));
            }
         }

         this.field_147490_S[lvt_1_1_].clear();
      }

   }

   private boolean func_147485_a(BlockEventData p_147485_1_) {
      IBlockState lvt_2_1_ = this.func_180495_p(p_147485_1_.func_180328_a());
      return lvt_2_1_.func_177230_c() == p_147485_1_.func_151337_f() ? lvt_2_1_.func_177230_c().func_180648_a(this, p_147485_1_.func_180328_a(), lvt_2_1_, p_147485_1_.func_151339_d(), p_147485_1_.func_151338_e()) : false;
   }

   public void func_73041_k() {
      this.field_73019_z.func_75759_a();
   }

   protected void func_72979_l() {
      boolean lvt_1_1_ = this.func_72896_J();
      super.func_72979_l();
      if (this.field_73003_n != this.field_73004_o) {
         this.field_73061_a.func_71203_ab().func_148537_a(new S2BPacketChangeGameState(7, this.field_73004_o), this.field_73011_w.func_177502_q());
      }

      if (this.field_73018_p != this.field_73017_q) {
         this.field_73061_a.func_71203_ab().func_148537_a(new S2BPacketChangeGameState(8, this.field_73017_q), this.field_73011_w.func_177502_q());
      }

      if (lvt_1_1_ != this.func_72896_J()) {
         if (lvt_1_1_) {
            this.field_73061_a.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(2, 0.0F));
         } else {
            this.field_73061_a.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(1, 0.0F));
         }

         this.field_73061_a.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(7, this.field_73004_o));
         this.field_73061_a.func_71203_ab().func_148540_a(new S2BPacketChangeGameState(8, this.field_73017_q));
      }

   }

   protected int func_152379_p() {
      return this.field_73061_a.func_71203_ab().func_72395_o();
   }

   public MinecraftServer func_73046_m() {
      return this.field_73061_a;
   }

   public EntityTracker func_73039_n() {
      return this.field_73062_L;
   }

   public PlayerManager func_73040_p() {
      return this.field_73063_M;
   }

   public Teleporter func_85176_s() {
      return this.field_85177_Q;
   }

   public void func_175739_a(EnumParticleTypes p_175739_1_, double p_175739_2_, double p_175739_4_, double p_175739_6_, int p_175739_8_, double p_175739_9_, double p_175739_11_, double p_175739_13_, double p_175739_15_, int... p_175739_17_) {
      this.func_180505_a(p_175739_1_, false, p_175739_2_, p_175739_4_, p_175739_6_, p_175739_8_, p_175739_9_, p_175739_11_, p_175739_13_, p_175739_15_, p_175739_17_);
   }

   public void func_180505_a(EnumParticleTypes p_180505_1_, boolean p_180505_2_, double p_180505_3_, double p_180505_5_, double p_180505_7_, int p_180505_9_, double p_180505_10_, double p_180505_12_, double p_180505_14_, double p_180505_16_, int... p_180505_18_) {
      Packet lvt_19_1_ = new S2APacketParticles(p_180505_1_, p_180505_2_, (float)p_180505_3_, (float)p_180505_5_, (float)p_180505_7_, (float)p_180505_10_, (float)p_180505_12_, (float)p_180505_14_, (float)p_180505_16_, p_180505_9_, p_180505_18_);

      for(int lvt_20_1_ = 0; lvt_20_1_ < this.field_73010_i.size(); ++lvt_20_1_) {
         EntityPlayerMP lvt_21_1_ = (EntityPlayerMP)this.field_73010_i.get(lvt_20_1_);
         BlockPos lvt_22_1_ = lvt_21_1_.func_180425_c();
         double lvt_23_1_ = lvt_22_1_.func_177954_c(p_180505_3_, p_180505_5_, p_180505_7_);
         if (lvt_23_1_ <= 256.0D || p_180505_2_ && lvt_23_1_ <= 65536.0D) {
            lvt_21_1_.field_71135_a.func_147359_a(lvt_19_1_);
         }
      }

   }

   public Entity func_175733_a(UUID p_175733_1_) {
      return (Entity)this.field_175741_N.get(p_175733_1_);
   }

   public ListenableFuture<Object> func_152344_a(Runnable p_152344_1_) {
      return this.field_73061_a.func_152344_a(p_152344_1_);
   }

   public boolean func_152345_ab() {
      return this.field_73061_a.func_152345_ab();
   }

   static {
      field_73069_S = Lists.newArrayList((Object[])(new WeightedRandomChestContent(Items.field_151055_y, 0, 1, 3, 10), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150344_f), 0, 1, 3, 10), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150364_r), 0, 1, 3, 10), new WeightedRandomChestContent(Items.field_151049_t, 0, 1, 1, 3), new WeightedRandomChestContent(Items.field_151053_p, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151050_s, 0, 1, 1, 3), new WeightedRandomChestContent(Items.field_151039_o, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151034_e, 0, 2, 3, 5), new WeightedRandomChestContent(Items.field_151025_P, 0, 2, 3, 3), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150363_s), 0, 1, 3, 10)));
   }

   static class ServerBlockEventList extends ArrayList<BlockEventData> {
      private ServerBlockEventList() {
      }
   }
}
