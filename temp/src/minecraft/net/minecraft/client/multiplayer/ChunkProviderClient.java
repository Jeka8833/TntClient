package net.minecraft.client.multiplayer;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderClient implements IChunkProvider {
   private static final Logger field_147436_a = LogManager.getLogger();
   private Chunk field_73238_a;
   private LongHashMap<Chunk> field_73236_b = new LongHashMap();
   private List<Chunk> field_73237_c = Lists.newArrayList();
   private World field_73235_d;

   public ChunkProviderClient(World p_i1184_1_) {
      this.field_73238_a = new EmptyChunk(p_i1184_1_, 0, 0);
      this.field_73235_d = p_i1184_1_;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73234_b(int p_73234_1_, int p_73234_2_) {
      Chunk lvt_3_1_ = this.func_73154_d(p_73234_1_, p_73234_2_);
      if (!lvt_3_1_.func_76621_g()) {
         lvt_3_1_.func_76623_d();
      }

      this.field_73236_b.func_76159_d(ChunkCoordIntPair.func_77272_a(p_73234_1_, p_73234_2_));
      this.field_73237_c.remove(lvt_3_1_);
   }

   public Chunk func_73158_c(int p_73158_1_, int p_73158_2_) {
      Chunk lvt_3_1_ = new Chunk(this.field_73235_d, p_73158_1_, p_73158_2_);
      this.field_73236_b.func_76163_a(ChunkCoordIntPair.func_77272_a(p_73158_1_, p_73158_2_), lvt_3_1_);
      this.field_73237_c.add(lvt_3_1_);
      lvt_3_1_.func_177417_c(true);
      return lvt_3_1_;
   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      Chunk lvt_3_1_ = (Chunk)this.field_73236_b.func_76164_a(ChunkCoordIntPair.func_77272_a(p_73154_1_, p_73154_2_));
      return lvt_3_1_ == null ? this.field_73238_a : lvt_3_1_;
   }

   public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
      return true;
   }

   public void func_104112_b() {
   }

   public boolean func_73156_b() {
      long lvt_1_1_ = System.currentTimeMillis();
      Iterator lvt_3_1_ = this.field_73237_c.iterator();

      while(lvt_3_1_.hasNext()) {
         Chunk lvt_4_1_ = (Chunk)lvt_3_1_.next();
         lvt_4_1_.func_150804_b(System.currentTimeMillis() - lvt_1_1_ > 5L);
      }

      if (System.currentTimeMillis() - lvt_1_1_ > 100L) {
         field_147436_a.info("Warning: Clientside chunk ticking took {} ms", System.currentTimeMillis() - lvt_1_1_);
      }

      return false;
   }

   public boolean func_73157_c() {
      return false;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      return false;
   }

   public String func_73148_d() {
      return "MultiplayerChunkCache: " + this.field_73236_b.func_76162_a() + ", " + this.field_73237_c.size();
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      return null;
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return null;
   }

   public int func_73152_e() {
      return this.field_73237_c.size();
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
