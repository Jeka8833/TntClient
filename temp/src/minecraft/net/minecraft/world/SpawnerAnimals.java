package net.minecraft.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.optifine.BlockPosM;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;

public final class SpawnerAnimals {
   private static final int field_180268_a = (int)Math.pow(17.0D, 2.0D);
   private final Set<ChunkCoordIntPair> field_77193_b = Sets.newHashSet();
   private Map<Class, EntityLiving> mapSampleEntitiesByClass = new HashMap();
   private int lastPlayerChunkX = Integer.MAX_VALUE;
   private int lastPlayerChunkZ = Integer.MAX_VALUE;
   private int countChunkPos;

   public int func_77192_a(WorldServer p_77192_1_, boolean p_77192_2_, boolean p_77192_3_, boolean p_77192_4_) {
      if (!p_77192_2_ && !p_77192_3_) {
         return 0;
      } else {
         boolean updateEligibleChunks = true;
         EntityPlayer player = null;
         if (p_77192_1_.field_73010_i.size() == 1) {
            player = (EntityPlayer)p_77192_1_.field_73010_i.get(0);
            if (this.field_77193_b.size() > 0 && player != null && player.field_70176_ah == this.lastPlayerChunkX && player.field_70164_aj == this.lastPlayerChunkZ) {
               updateEligibleChunks = false;
            }
         }

         int i4;
         int i1;
         if (updateEligibleChunks) {
            this.field_77193_b.clear();
            i4 = 0;
            Iterator i$ = p_77192_1_.field_73010_i.iterator();

            label199:
            while(true) {
               EntityPlayer entityplayer;
               do {
                  if (!i$.hasNext()) {
                     this.countChunkPos = i4;
                     if (player != null) {
                        this.lastPlayerChunkX = player.field_70176_ah;
                        this.lastPlayerChunkZ = player.field_70164_aj;
                     }
                     break label199;
                  }

                  entityplayer = (EntityPlayer)i$.next();
               } while(entityplayer.func_175149_v());

               int j = MathHelper.func_76128_c(entityplayer.field_70165_t / 16.0D);
               int k = MathHelper.func_76128_c(entityplayer.field_70161_v / 16.0D);
               int l = 8;

               for(i1 = -l; i1 <= l; ++i1) {
                  for(int j1 = -l; j1 <= l; ++j1) {
                     boolean flag = i1 == -l || i1 == l || j1 == -l || j1 == l;
                     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i1 + j, j1 + k);
                     if (!this.field_77193_b.contains(chunkcoordintpair)) {
                        ++i4;
                        if (!flag && p_77192_1_.func_175723_af().func_177730_a(chunkcoordintpair)) {
                           this.field_77193_b.add(chunkcoordintpair);
                        }
                     }
                  }
               }
            }
         }

         i4 = 0;
         BlockPos blockpos2 = p_77192_1_.func_175694_M();
         BlockPosM blockPosM = new BlockPosM(0, 0, 0);
         new BlockPos.MutableBlockPos();
         EnumCreatureType[] arr$ = EnumCreatureType.values();
         int len$ = arr$.length;

         label172:
         for(i1 = 0; i1 < len$; ++i1) {
            EnumCreatureType enumcreaturetype = arr$[i1];
            if ((!enumcreaturetype.func_75599_d() || p_77192_3_) && (enumcreaturetype.func_75599_d() || p_77192_2_) && (!enumcreaturetype.func_82705_e() || p_77192_4_)) {
               int j4 = Reflector.ForgeWorld_countEntities.exists() ? Reflector.callInt(p_77192_1_, Reflector.ForgeWorld_countEntities, enumcreaturetype, true) : p_77192_1_.func_72907_a(enumcreaturetype.func_75598_a());
               int k4 = enumcreaturetype.func_75601_b() * this.countChunkPos / field_180268_a;
               if (j4 <= k4) {
                  Collection<ChunkCoordIntPair> chunksForSpawning = this.field_77193_b;
                  if (Reflector.ForgeHooksClient.exists()) {
                     ArrayList<ChunkCoordIntPair> shuffled = Lists.newArrayList((Iterable)chunksForSpawning);
                     Collections.shuffle(shuffled);
                     chunksForSpawning = shuffled;
                  }

                  Iterator i$ = ((Collection)chunksForSpawning).iterator();

                  label169:
                  while(true) {
                     int k1;
                     int l1;
                     int i2;
                     Block block;
                     do {
                        if (!i$.hasNext()) {
                           continue label172;
                        }

                        ChunkCoordIntPair chunkcoordintpair1 = (ChunkCoordIntPair)i$.next();
                        BlockPos blockpos = getRandomChunkPosition(p_77192_1_, chunkcoordintpair1.field_77276_a, chunkcoordintpair1.field_77275_b, blockPosM);
                        k1 = blockpos.func_177958_n();
                        l1 = blockpos.func_177956_o();
                        i2 = blockpos.func_177952_p();
                        block = p_77192_1_.func_180495_p(blockpos).func_177230_c();
                     } while(block.func_149721_r());

                     int j2 = 0;

                     for(int k2 = 0; k2 < 3; ++k2) {
                        int l2 = k1;
                        int i3 = l1;
                        int j3 = i2;
                        int k3 = 6;
                        BiomeGenBase.SpawnListEntry biomegenbase$spawnlistentry = null;
                        IEntityLivingData ientitylivingdata = null;

                        for(int l3 = 0; l3 < 4; ++l3) {
                           l2 += p_77192_1_.field_73012_v.nextInt(k3) - p_77192_1_.field_73012_v.nextInt(k3);
                           i3 += p_77192_1_.field_73012_v.nextInt(1) - p_77192_1_.field_73012_v.nextInt(1);
                           j3 += p_77192_1_.field_73012_v.nextInt(k3) - p_77192_1_.field_73012_v.nextInt(k3);
                           BlockPos blockpos1 = new BlockPos(l2, i3, j3);
                           float f = (float)l2 + 0.5F;
                           float f1 = (float)j3 + 0.5F;
                           if (!p_77192_1_.func_175636_b((double)f, (double)i3, (double)f1, 24.0D) && blockpos2.func_177954_c((double)f, (double)i3, (double)f1) >= 576.0D) {
                              if (biomegenbase$spawnlistentry == null) {
                                 biomegenbase$spawnlistentry = p_77192_1_.func_175734_a(enumcreaturetype, blockpos1);
                                 if (biomegenbase$spawnlistentry == null) {
                                    break;
                                 }
                              }

                              if (p_77192_1_.func_175732_a(enumcreaturetype, biomegenbase$spawnlistentry, blockpos1) && func_180267_a(EntitySpawnPlacementRegistry.func_180109_a(biomegenbase$spawnlistentry.field_76300_b), p_77192_1_, blockpos1)) {
                                 EntityLiving entityliving;
                                 try {
                                    entityliving = (EntityLiving)this.mapSampleEntitiesByClass.get(biomegenbase$spawnlistentry.field_76300_b);
                                    if (entityliving == null) {
                                       entityliving = (EntityLiving)biomegenbase$spawnlistentry.field_76300_b.getConstructor(World.class).newInstance(p_77192_1_);
                                       this.mapSampleEntitiesByClass.put(biomegenbase$spawnlistentry.field_76300_b, entityliving);
                                    }
                                 } catch (Exception var40) {
                                    var40.printStackTrace();
                                    return i4;
                                 }

                                 entityliving.func_70012_b((double)f, (double)i3, (double)f1, p_77192_1_.field_73012_v.nextFloat() * 360.0F, 0.0F);
                                 boolean canSpawn = Reflector.ForgeEventFactory_canEntitySpawn.exists() ? ReflectorForge.canEntitySpawn(entityliving, p_77192_1_, f, (float)i3, f1) : entityliving.func_70601_bi() && entityliving.func_70058_J();
                                 if (canSpawn) {
                                    this.mapSampleEntitiesByClass.remove(biomegenbase$spawnlistentry.field_76300_b);
                                    if (!ReflectorForge.doSpecialSpawn(entityliving, p_77192_1_, f, i3, f1)) {
                                       ientitylivingdata = entityliving.func_180482_a(p_77192_1_.func_175649_E(new BlockPos(entityliving)), ientitylivingdata);
                                    }

                                    if (entityliving.func_70058_J()) {
                                       ++j2;
                                       p_77192_1_.func_72838_d(entityliving);
                                    }

                                    int maxSpawnedInChunk = Reflector.ForgeEventFactory_getMaxSpawnPackSize.exists() ? Reflector.callInt(Reflector.ForgeEventFactory_getMaxSpawnPackSize, entityliving) : entityliving.func_70641_bl();
                                    if (j2 >= maxSpawnedInChunk) {
                                       continue label169;
                                    }
                                 }

                                 i4 += j2;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return i4;
      }
   }

   protected static BlockPos func_180621_a(World p_180621_0_, int p_180621_1_, int p_180621_2_) {
      Chunk chunk = p_180621_0_.func_72964_e(p_180621_1_, p_180621_2_);
      int i = p_180621_1_ * 16 + p_180621_0_.field_73012_v.nextInt(16);
      int j = p_180621_2_ * 16 + p_180621_0_.field_73012_v.nextInt(16);
      int k = MathHelper.func_154354_b(chunk.func_177433_f(new BlockPos(i, 0, j)) + 1, 16);
      int l = p_180621_0_.field_73012_v.nextInt(k > 0 ? k : chunk.func_76625_h() + 16 - 1);
      return new BlockPos(i, l, j);
   }

   private static BlockPosM getRandomChunkPosition(World p_getRandomChunkPosition_0_, int p_getRandomChunkPosition_1_, int p_getRandomChunkPosition_2_, BlockPosM p_getRandomChunkPosition_3_) {
      Chunk chunk = p_getRandomChunkPosition_0_.func_72964_e(p_getRandomChunkPosition_1_, p_getRandomChunkPosition_2_);
      int px = p_getRandomChunkPosition_1_ * 16 + p_getRandomChunkPosition_0_.field_73012_v.nextInt(16);
      int pz = p_getRandomChunkPosition_2_ * 16 + p_getRandomChunkPosition_0_.field_73012_v.nextInt(16);
      int k = MathHelper.func_154354_b(chunk.func_76611_b(px & 15, pz & 15) + 1, 16);
      int py = p_getRandomChunkPosition_0_.field_73012_v.nextInt(k > 0 ? k : chunk.func_76625_h() + 16 - 1);
      p_getRandomChunkPosition_3_.setXyz(px, py, pz);
      return p_getRandomChunkPosition_3_;
   }

   public static boolean func_180267_a(EntityLiving.SpawnPlacementType p_180267_0_, World p_180267_1_, BlockPos p_180267_2_) {
      if (!p_180267_1_.func_175723_af().func_177746_a(p_180267_2_)) {
         return false;
      } else if (p_180267_0_ == null) {
         return false;
      } else {
         Block block = p_180267_1_.func_180495_p(p_180267_2_).func_177230_c();
         if (p_180267_0_ == EntityLiving.SpawnPlacementType.IN_WATER) {
            return block.func_149688_o().func_76224_d() && p_180267_1_.func_180495_p(p_180267_2_.func_177977_b()).func_177230_c().func_149688_o().func_76224_d() && !p_180267_1_.func_180495_p(p_180267_2_.func_177984_a()).func_177230_c().func_149721_r();
         } else {
            BlockPos blockpos = p_180267_2_.func_177977_b();
            IBlockState state = p_180267_1_.func_180495_p(blockpos);
            boolean canSpawn = Reflector.ForgeBlock_canCreatureSpawn.exists() ? Reflector.callBoolean(state.func_177230_c(), Reflector.ForgeBlock_canCreatureSpawn, p_180267_1_, blockpos, p_180267_0_) : World.func_175683_a(p_180267_1_, blockpos);
            if (!canSpawn) {
               return false;
            } else {
               Block block1 = p_180267_1_.func_180495_p(blockpos).func_177230_c();
               boolean flag = block1 != Blocks.field_150357_h && block1 != Blocks.field_180401_cv;
               return flag && !block.func_149721_r() && !block.func_149688_o().func_76224_d() && !p_180267_1_.func_180495_p(p_180267_2_.func_177984_a()).func_177230_c().func_149721_r();
            }
         }
      }
   }

   public static void func_77191_a(World p_77191_0_, BiomeGenBase p_77191_1_, int p_77191_2_, int p_77191_3_, int p_77191_4_, int p_77191_5_, Random p_77191_6_) {
      List<BiomeGenBase.SpawnListEntry> list = p_77191_1_.func_76747_a(EnumCreatureType.CREATURE);
      if (!list.isEmpty()) {
         while(p_77191_6_.nextFloat() < p_77191_1_.func_76741_f()) {
            BiomeGenBase.SpawnListEntry biomegenbase$spawnlistentry = (BiomeGenBase.SpawnListEntry)WeightedRandom.func_76271_a(p_77191_0_.field_73012_v, list);
            int i = biomegenbase$spawnlistentry.field_76301_c + p_77191_6_.nextInt(1 + biomegenbase$spawnlistentry.field_76299_d - biomegenbase$spawnlistentry.field_76301_c);
            IEntityLivingData ientitylivingdata = null;
            int j = p_77191_2_ + p_77191_6_.nextInt(p_77191_4_);
            int k = p_77191_3_ + p_77191_6_.nextInt(p_77191_5_);
            int l = j;
            int i1 = k;

            for(int j1 = 0; j1 < i; ++j1) {
               boolean flag = false;

               for(int k1 = 0; !flag && k1 < 4; ++k1) {
                  BlockPos blockpos = p_77191_0_.func_175672_r(new BlockPos(j, 0, k));
                  if (func_180267_a(EntityLiving.SpawnPlacementType.ON_GROUND, p_77191_0_, blockpos)) {
                     EntityLiving entityliving;
                     try {
                        entityliving = (EntityLiving)biomegenbase$spawnlistentry.field_76300_b.getConstructor(World.class).newInstance(p_77191_0_);
                     } catch (Exception var21) {
                        var21.printStackTrace();
                        continue;
                     }

                     if (Reflector.ForgeEventFactory_canEntitySpawn.exists()) {
                        Object canSpawn = Reflector.call(Reflector.ForgeEventFactory_canEntitySpawn, entityliving, p_77191_0_, (float)j + 0.5F, blockpos.func_177956_o(), (float)k + 0.5F);
                        if (canSpawn == ReflectorForge.EVENT_RESULT_DENY) {
                           continue;
                        }
                     }

                     entityliving.func_70012_b((double)((float)j + 0.5F), (double)blockpos.func_177956_o(), (double)((float)k + 0.5F), p_77191_6_.nextFloat() * 360.0F, 0.0F);
                     p_77191_0_.func_72838_d(entityliving);
                     ientitylivingdata = entityliving.func_180482_a(p_77191_0_.func_175649_E(new BlockPos(entityliving)), ientitylivingdata);
                     flag = true;
                  }

                  j += p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5);

                  for(k += p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5); j < p_77191_2_ || j >= p_77191_2_ + p_77191_4_ || k < p_77191_3_ || k >= p_77191_3_ + p_77191_4_; k = i1 + p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5)) {
                     j = l + p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5);
                  }
               }
            }
         }
      }

   }
}
