package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.LongHashMap;

public class BiomeCache {
   private final WorldChunkManager field_76844_a;
   private long field_76842_b;
   private LongHashMap<BiomeCache.Block> field_76843_c = new LongHashMap();
   private List<BiomeCache.Block> field_76841_d = Lists.newArrayList();

   public BiomeCache(WorldChunkManager p_i1973_1_) {
      this.field_76844_a = p_i1973_1_;
   }

   public BiomeCache.Block func_76840_a(int p_76840_1_, int p_76840_2_) {
      p_76840_1_ >>= 4;
      p_76840_2_ >>= 4;
      long lvt_3_1_ = (long)p_76840_1_ & 4294967295L | ((long)p_76840_2_ & 4294967295L) << 32;
      BiomeCache.Block lvt_5_1_ = (BiomeCache.Block)this.field_76843_c.func_76164_a(lvt_3_1_);
      if (lvt_5_1_ == null) {
         lvt_5_1_ = new BiomeCache.Block(p_76840_1_, p_76840_2_);
         this.field_76843_c.func_76163_a(lvt_3_1_, lvt_5_1_);
         this.field_76841_d.add(lvt_5_1_);
      }

      lvt_5_1_.field_76886_f = MinecraftServer.func_130071_aq();
      return lvt_5_1_;
   }

   public BiomeGenBase func_180284_a(int p_180284_1_, int p_180284_2_, BiomeGenBase p_180284_3_) {
      BiomeGenBase lvt_4_1_ = this.func_76840_a(p_180284_1_, p_180284_2_).func_76885_a(p_180284_1_, p_180284_2_);
      return lvt_4_1_ == null ? p_180284_3_ : lvt_4_1_;
   }

   public void func_76838_a() {
      long lvt_1_1_ = MinecraftServer.func_130071_aq();
      long lvt_3_1_ = lvt_1_1_ - this.field_76842_b;
      if (lvt_3_1_ > 7500L || lvt_3_1_ < 0L) {
         this.field_76842_b = lvt_1_1_;

         for(int lvt_5_1_ = 0; lvt_5_1_ < this.field_76841_d.size(); ++lvt_5_1_) {
            BiomeCache.Block lvt_6_1_ = (BiomeCache.Block)this.field_76841_d.get(lvt_5_1_);
            long lvt_7_1_ = lvt_1_1_ - lvt_6_1_.field_76886_f;
            if (lvt_7_1_ > 30000L || lvt_7_1_ < 0L) {
               this.field_76841_d.remove(lvt_5_1_--);
               long lvt_9_1_ = (long)lvt_6_1_.field_76888_d & 4294967295L | ((long)lvt_6_1_.field_76889_e & 4294967295L) << 32;
               this.field_76843_c.func_76159_d(lvt_9_1_);
            }
         }
      }

   }

   public BiomeGenBase[] func_76839_e(int p_76839_1_, int p_76839_2_) {
      return this.func_76840_a(p_76839_1_, p_76839_2_).field_76891_c;
   }

   public class Block {
      public float[] field_76890_b = new float[256];
      public BiomeGenBase[] field_76891_c = new BiomeGenBase[256];
      public int field_76888_d;
      public int field_76889_e;
      public long field_76886_f;

      public Block(int p_i1972_2_, int p_i1972_3_) {
         this.field_76888_d = p_i1972_2_;
         this.field_76889_e = p_i1972_3_;
         BiomeCache.this.field_76844_a.func_76936_a(this.field_76890_b, p_i1972_2_ << 4, p_i1972_3_ << 4, 16, 16);
         BiomeCache.this.field_76844_a.func_76931_a(this.field_76891_c, p_i1972_2_ << 4, p_i1972_3_ << 4, 16, 16, false);
      }

      public BiomeGenBase func_76885_a(int p_76885_1_, int p_76885_2_) {
         return this.field_76891_c[p_76885_1_ & 15 | (p_76885_2_ & 15) << 4];
      }
   }
}
