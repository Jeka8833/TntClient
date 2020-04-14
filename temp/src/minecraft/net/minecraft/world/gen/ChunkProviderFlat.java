package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class ChunkProviderFlat implements IChunkProvider {
   private World field_73163_a;
   private Random field_73161_b;
   private final IBlockState[] field_82700_c = new IBlockState[256];
   private final FlatGeneratorInfo field_82699_e;
   private final List<MapGenStructure> field_82696_f = Lists.newArrayList();
   private final boolean field_82697_g;
   private final boolean field_82702_h;
   private WorldGenLakes field_82703_i;
   private WorldGenLakes field_82701_j;

   public ChunkProviderFlat(World p_i2004_1_, long p_i2004_2_, boolean p_i2004_4_, String p_i2004_5_) {
      this.field_73163_a = p_i2004_1_;
      this.field_73161_b = new Random(p_i2004_2_);
      this.field_82699_e = FlatGeneratorInfo.func_82651_a(p_i2004_5_);
      if (p_i2004_4_) {
         Map<String, Map<String, String>> lvt_6_1_ = this.field_82699_e.func_82644_b();
         if (lvt_6_1_.containsKey("village")) {
            Map<String, String> lvt_7_1_ = (Map)lvt_6_1_.get("village");
            if (!lvt_7_1_.containsKey("size")) {
               lvt_7_1_.put("size", "1");
            }

            this.field_82696_f.add(new MapGenVillage(lvt_7_1_));
         }

         if (lvt_6_1_.containsKey("biome_1")) {
            this.field_82696_f.add(new MapGenScatteredFeature((Map)lvt_6_1_.get("biome_1")));
         }

         if (lvt_6_1_.containsKey("mineshaft")) {
            this.field_82696_f.add(new MapGenMineshaft((Map)lvt_6_1_.get("mineshaft")));
         }

         if (lvt_6_1_.containsKey("stronghold")) {
            this.field_82696_f.add(new MapGenStronghold((Map)lvt_6_1_.get("stronghold")));
         }

         if (lvt_6_1_.containsKey("oceanmonument")) {
            this.field_82696_f.add(new StructureOceanMonument((Map)lvt_6_1_.get("oceanmonument")));
         }
      }

      if (this.field_82699_e.func_82644_b().containsKey("lake")) {
         this.field_82703_i = new WorldGenLakes(Blocks.field_150355_j);
      }

      if (this.field_82699_e.func_82644_b().containsKey("lava_lake")) {
         this.field_82701_j = new WorldGenLakes(Blocks.field_150353_l);
      }

      this.field_82702_h = this.field_82699_e.func_82644_b().containsKey("dungeon");
      int lvt_6_2_ = 0;
      int lvt_7_2_ = 0;
      boolean lvt_8_1_ = true;
      Iterator lvt_9_1_ = this.field_82699_e.func_82650_c().iterator();

      while(lvt_9_1_.hasNext()) {
         FlatLayerInfo lvt_10_1_ = (FlatLayerInfo)lvt_9_1_.next();

         for(int lvt_11_1_ = lvt_10_1_.func_82656_d(); lvt_11_1_ < lvt_10_1_.func_82656_d() + lvt_10_1_.func_82657_a(); ++lvt_11_1_) {
            IBlockState lvt_12_1_ = lvt_10_1_.func_175900_c();
            if (lvt_12_1_.func_177230_c() != Blocks.field_150350_a) {
               lvt_8_1_ = false;
               this.field_82700_c[lvt_11_1_] = lvt_12_1_;
            }
         }

         if (lvt_10_1_.func_175900_c().func_177230_c() == Blocks.field_150350_a) {
            lvt_7_2_ += lvt_10_1_.func_82657_a();
         } else {
            lvt_6_2_ += lvt_10_1_.func_82657_a() + lvt_7_2_;
            lvt_7_2_ = 0;
         }
      }

      p_i2004_1_.func_181544_b(lvt_6_2_);
      this.field_82697_g = lvt_8_1_ ? false : this.field_82699_e.func_82644_b().containsKey("decoration");
   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      ChunkPrimer lvt_3_1_ = new ChunkPrimer();

      int lvt_7_1_;
      for(int lvt_4_1_ = 0; lvt_4_1_ < this.field_82700_c.length; ++lvt_4_1_) {
         IBlockState lvt_5_1_ = this.field_82700_c[lvt_4_1_];
         if (lvt_5_1_ != null) {
            for(int lvt_6_1_ = 0; lvt_6_1_ < 16; ++lvt_6_1_) {
               for(lvt_7_1_ = 0; lvt_7_1_ < 16; ++lvt_7_1_) {
                  lvt_3_1_.func_177855_a(lvt_6_1_, lvt_4_1_, lvt_7_1_, lvt_5_1_);
               }
            }
         }
      }

      Iterator lvt_4_2_ = this.field_82696_f.iterator();

      while(lvt_4_2_.hasNext()) {
         MapGenBase lvt_5_2_ = (MapGenBase)lvt_4_2_.next();
         lvt_5_2_.func_175792_a(this, this.field_73163_a, p_73154_1_, p_73154_2_, lvt_3_1_);
      }

      Chunk lvt_4_3_ = new Chunk(this.field_73163_a, lvt_3_1_, p_73154_1_, p_73154_2_);
      BiomeGenBase[] lvt_5_3_ = this.field_73163_a.func_72959_q().func_76933_b((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      byte[] lvt_6_2_ = lvt_4_3_.func_76605_m();

      for(lvt_7_1_ = 0; lvt_7_1_ < lvt_6_2_.length; ++lvt_7_1_) {
         lvt_6_2_[lvt_7_1_] = (byte)lvt_5_3_[lvt_7_1_].field_76756_M;
      }

      lvt_4_3_.func_76603_b();
      return lvt_4_3_;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      int lvt_4_1_ = p_73153_2_ * 16;
      int lvt_5_1_ = p_73153_3_ * 16;
      BlockPos lvt_6_1_ = new BlockPos(lvt_4_1_, 0, lvt_5_1_);
      BiomeGenBase lvt_7_1_ = this.field_73163_a.func_180494_b(new BlockPos(lvt_4_1_ + 16, 0, lvt_5_1_ + 16));
      boolean lvt_8_1_ = false;
      this.field_73161_b.setSeed(this.field_73163_a.func_72905_C());
      long lvt_9_1_ = this.field_73161_b.nextLong() / 2L * 2L + 1L;
      long lvt_11_1_ = this.field_73161_b.nextLong() / 2L * 2L + 1L;
      this.field_73161_b.setSeed((long)p_73153_2_ * lvt_9_1_ + (long)p_73153_3_ * lvt_11_1_ ^ this.field_73163_a.func_72905_C());
      ChunkCoordIntPair lvt_13_1_ = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
      Iterator lvt_14_1_ = this.field_82696_f.iterator();

      while(lvt_14_1_.hasNext()) {
         MapGenStructure lvt_15_1_ = (MapGenStructure)lvt_14_1_.next();
         boolean lvt_16_1_ = lvt_15_1_.func_175794_a(this.field_73163_a, this.field_73161_b, lvt_13_1_);
         if (lvt_15_1_ instanceof MapGenVillage) {
            lvt_8_1_ |= lvt_16_1_;
         }
      }

      if (this.field_82703_i != null && !lvt_8_1_ && this.field_73161_b.nextInt(4) == 0) {
         this.field_82703_i.func_180709_b(this.field_73163_a, this.field_73161_b, lvt_6_1_.func_177982_a(this.field_73161_b.nextInt(16) + 8, this.field_73161_b.nextInt(256), this.field_73161_b.nextInt(16) + 8));
      }

      if (this.field_82701_j != null && !lvt_8_1_ && this.field_73161_b.nextInt(8) == 0) {
         BlockPos lvt_14_2_ = lvt_6_1_.func_177982_a(this.field_73161_b.nextInt(16) + 8, this.field_73161_b.nextInt(this.field_73161_b.nextInt(248) + 8), this.field_73161_b.nextInt(16) + 8);
         if (lvt_14_2_.func_177956_o() < this.field_73163_a.func_181545_F() || this.field_73161_b.nextInt(10) == 0) {
            this.field_82701_j.func_180709_b(this.field_73163_a, this.field_73161_b, lvt_14_2_);
         }
      }

      if (this.field_82702_h) {
         for(int lvt_14_3_ = 0; lvt_14_3_ < 8; ++lvt_14_3_) {
            (new WorldGenDungeons()).func_180709_b(this.field_73163_a, this.field_73161_b, lvt_6_1_.func_177982_a(this.field_73161_b.nextInt(16) + 8, this.field_73161_b.nextInt(256), this.field_73161_b.nextInt(16) + 8));
         }
      }

      if (this.field_82697_g) {
         lvt_7_1_.func_180624_a(this.field_73163_a, this.field_73161_b, lvt_6_1_);
      }

   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      return false;
   }

   public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
      return true;
   }

   public void func_104112_b() {
   }

   public boolean func_73156_b() {
      return false;
   }

   public boolean func_73157_c() {
      return true;
   }

   public String func_73148_d() {
      return "FlatLevelSource";
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      BiomeGenBase lvt_3_1_ = this.field_73163_a.func_180494_b(p_177458_2_);
      return lvt_3_1_.func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      if ("Stronghold".equals(p_180513_2_)) {
         Iterator lvt_4_1_ = this.field_82696_f.iterator();

         while(lvt_4_1_.hasNext()) {
            MapGenStructure lvt_5_1_ = (MapGenStructure)lvt_4_1_.next();
            if (lvt_5_1_ instanceof MapGenStronghold) {
               return lvt_5_1_.func_180706_b(p_180513_1_, p_180513_3_);
            }
         }
      }

      return null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
      Iterator lvt_4_1_ = this.field_82696_f.iterator();

      while(lvt_4_1_.hasNext()) {
         MapGenStructure lvt_5_1_ = (MapGenStructure)lvt_4_1_.next();
         lvt_5_1_.func_175792_a(this, this.field_73163_a, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
