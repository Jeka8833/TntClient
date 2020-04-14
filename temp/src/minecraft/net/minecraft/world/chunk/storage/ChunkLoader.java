package net.minecraft.world.chunk.storage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.NibbleArray;

public class ChunkLoader {
   public static ChunkLoader.AnvilConverterData func_76691_a(NBTTagCompound p_76691_0_) {
      int lvt_1_1_ = p_76691_0_.func_74762_e("xPos");
      int lvt_2_1_ = p_76691_0_.func_74762_e("zPos");
      ChunkLoader.AnvilConverterData lvt_3_1_ = new ChunkLoader.AnvilConverterData(lvt_1_1_, lvt_2_1_);
      lvt_3_1_.field_76693_g = p_76691_0_.func_74770_j("Blocks");
      lvt_3_1_.field_76692_f = new NibbleArrayReader(p_76691_0_.func_74770_j("Data"), 7);
      lvt_3_1_.field_76695_e = new NibbleArrayReader(p_76691_0_.func_74770_j("SkyLight"), 7);
      lvt_3_1_.field_76694_d = new NibbleArrayReader(p_76691_0_.func_74770_j("BlockLight"), 7);
      lvt_3_1_.field_76697_c = p_76691_0_.func_74770_j("HeightMap");
      lvt_3_1_.field_76696_b = p_76691_0_.func_74767_n("TerrainPopulated");
      lvt_3_1_.field_76702_h = p_76691_0_.func_150295_c("Entities", 10);
      lvt_3_1_.field_151564_i = p_76691_0_.func_150295_c("TileEntities", 10);
      lvt_3_1_.field_151563_j = p_76691_0_.func_150295_c("TileTicks", 10);

      try {
         lvt_3_1_.field_76698_a = p_76691_0_.func_74763_f("LastUpdate");
      } catch (ClassCastException var5) {
         lvt_3_1_.field_76698_a = (long)p_76691_0_.func_74762_e("LastUpdate");
      }

      return lvt_3_1_;
   }

   public static void func_76690_a(ChunkLoader.AnvilConverterData p_76690_0_, NBTTagCompound p_76690_1_, WorldChunkManager p_76690_2_) {
      p_76690_1_.func_74768_a("xPos", p_76690_0_.field_76701_k);
      p_76690_1_.func_74768_a("zPos", p_76690_0_.field_76699_l);
      p_76690_1_.func_74772_a("LastUpdate", p_76690_0_.field_76698_a);
      int[] lvt_3_1_ = new int[p_76690_0_.field_76697_c.length];

      for(int lvt_4_1_ = 0; lvt_4_1_ < p_76690_0_.field_76697_c.length; ++lvt_4_1_) {
         lvt_3_1_[lvt_4_1_] = p_76690_0_.field_76697_c[lvt_4_1_];
      }

      p_76690_1_.func_74783_a("HeightMap", lvt_3_1_);
      p_76690_1_.func_74757_a("TerrainPopulated", p_76690_0_.field_76696_b);
      NBTTagList lvt_4_2_ = new NBTTagList();

      int lvt_7_1_;
      int lvt_8_1_;
      for(int lvt_5_1_ = 0; lvt_5_1_ < 8; ++lvt_5_1_) {
         boolean lvt_6_1_ = true;

         for(lvt_7_1_ = 0; lvt_7_1_ < 16 && lvt_6_1_; ++lvt_7_1_) {
            for(lvt_8_1_ = 0; lvt_8_1_ < 16 && lvt_6_1_; ++lvt_8_1_) {
               for(int lvt_9_1_ = 0; lvt_9_1_ < 16; ++lvt_9_1_) {
                  int lvt_10_1_ = lvt_7_1_ << 11 | lvt_9_1_ << 7 | lvt_8_1_ + (lvt_5_1_ << 4);
                  int lvt_11_1_ = p_76690_0_.field_76693_g[lvt_10_1_];
                  if (lvt_11_1_ != 0) {
                     lvt_6_1_ = false;
                     break;
                  }
               }
            }
         }

         if (!lvt_6_1_) {
            byte[] lvt_7_2_ = new byte[4096];
            NibbleArray lvt_8_2_ = new NibbleArray();
            NibbleArray lvt_9_2_ = new NibbleArray();
            NibbleArray lvt_10_2_ = new NibbleArray();

            for(int lvt_11_2_ = 0; lvt_11_2_ < 16; ++lvt_11_2_) {
               for(int lvt_12_1_ = 0; lvt_12_1_ < 16; ++lvt_12_1_) {
                  for(int lvt_13_1_ = 0; lvt_13_1_ < 16; ++lvt_13_1_) {
                     int lvt_14_1_ = lvt_11_2_ << 11 | lvt_13_1_ << 7 | lvt_12_1_ + (lvt_5_1_ << 4);
                     int lvt_15_1_ = p_76690_0_.field_76693_g[lvt_14_1_];
                     lvt_7_2_[lvt_12_1_ << 8 | lvt_13_1_ << 4 | lvt_11_2_] = (byte)(lvt_15_1_ & 255);
                     lvt_8_2_.func_76581_a(lvt_11_2_, lvt_12_1_, lvt_13_1_, p_76690_0_.field_76692_f.func_76686_a(lvt_11_2_, lvt_12_1_ + (lvt_5_1_ << 4), lvt_13_1_));
                     lvt_9_2_.func_76581_a(lvt_11_2_, lvt_12_1_, lvt_13_1_, p_76690_0_.field_76695_e.func_76686_a(lvt_11_2_, lvt_12_1_ + (lvt_5_1_ << 4), lvt_13_1_));
                     lvt_10_2_.func_76581_a(lvt_11_2_, lvt_12_1_, lvt_13_1_, p_76690_0_.field_76694_d.func_76686_a(lvt_11_2_, lvt_12_1_ + (lvt_5_1_ << 4), lvt_13_1_));
                  }
               }
            }

            NBTTagCompound lvt_11_3_ = new NBTTagCompound();
            lvt_11_3_.func_74774_a("Y", (byte)(lvt_5_1_ & 255));
            lvt_11_3_.func_74773_a("Blocks", lvt_7_2_);
            lvt_11_3_.func_74773_a("Data", lvt_8_2_.func_177481_a());
            lvt_11_3_.func_74773_a("SkyLight", lvt_9_2_.func_177481_a());
            lvt_11_3_.func_74773_a("BlockLight", lvt_10_2_.func_177481_a());
            lvt_4_2_.func_74742_a(lvt_11_3_);
         }
      }

      p_76690_1_.func_74782_a("Sections", lvt_4_2_);
      byte[] lvt_5_2_ = new byte[256];
      BlockPos.MutableBlockPos lvt_6_2_ = new BlockPos.MutableBlockPos();

      for(lvt_7_1_ = 0; lvt_7_1_ < 16; ++lvt_7_1_) {
         for(lvt_8_1_ = 0; lvt_8_1_ < 16; ++lvt_8_1_) {
            lvt_6_2_.func_181079_c(p_76690_0_.field_76701_k << 4 | lvt_7_1_, 0, p_76690_0_.field_76699_l << 4 | lvt_8_1_);
            lvt_5_2_[lvt_8_1_ << 4 | lvt_7_1_] = (byte)(p_76690_2_.func_180300_a(lvt_6_2_, BiomeGenBase.field_180279_ad).field_76756_M & 255);
         }
      }

      p_76690_1_.func_74773_a("Biomes", lvt_5_2_);
      p_76690_1_.func_74782_a("Entities", p_76690_0_.field_76702_h);
      p_76690_1_.func_74782_a("TileEntities", p_76690_0_.field_151564_i);
      if (p_76690_0_.field_151563_j != null) {
         p_76690_1_.func_74782_a("TileTicks", p_76690_0_.field_151563_j);
      }

   }

   public static class AnvilConverterData {
      public long field_76698_a;
      public boolean field_76696_b;
      public byte[] field_76697_c;
      public NibbleArrayReader field_76694_d;
      public NibbleArrayReader field_76695_e;
      public NibbleArrayReader field_76692_f;
      public byte[] field_76693_g;
      public NBTTagList field_76702_h;
      public NBTTagList field_151564_i;
      public NBTTagList field_151563_j;
      public final int field_76701_k;
      public final int field_76699_l;

      public AnvilConverterData(int p_i1999_1_, int p_i1999_2_) {
         this.field_76701_k = p_i1999_1_;
         this.field_76699_l = p_i1999_2_;
      }
   }
}
