package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderServer implements IChunkProvider {
   private static final Logger field_147417_b = LogManager.getLogger();
   private Set<Long> field_73248_b = Collections.newSetFromMap(new ConcurrentHashMap());
   private Chunk field_73249_c;
   private IChunkProvider field_73246_d;
   private IChunkLoader field_73247_e;
   public boolean field_73250_a = true;
   private LongHashMap<Chunk> field_73244_f = new LongHashMap();
   private List<Chunk> field_73245_g = Lists.newArrayList();
   private WorldServer field_73251_h;

   public ChunkProviderServer(WorldServer p_i1520_1_, IChunkLoader p_i1520_2_, IChunkProvider p_i1520_3_) {
      this.field_73249_c = new EmptyChunk(p_i1520_1_, 0, 0);
      this.field_73251_h = p_i1520_1_;
      this.field_73247_e = p_i1520_2_;
      this.field_73246_d = p_i1520_3_;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return this.field_73244_f.func_76161_b(ChunkCoordIntPair.func_77272_a(p_73149_1_, p_73149_2_));
   }

   public List<Chunk> func_152380_a() {
      return this.field_73245_g;
   }

   public void func_73241_b(int p_73241_1_, int p_73241_2_) {
      if (this.field_73251_h.field_73011_w.func_76567_e()) {
         if (!this.field_73251_h.func_72916_c(p_73241_1_, p_73241_2_)) {
            this.field_73248_b.add(ChunkCoordIntPair.func_77272_a(p_73241_1_, p_73241_2_));
         }
      } else {
         this.field_73248_b.add(ChunkCoordIntPair.func_77272_a(p_73241_1_, p_73241_2_));
      }

   }

   public void func_73240_a() {
      Iterator lvt_1_1_ = this.field_73245_g.iterator();

      while(lvt_1_1_.hasNext()) {
         Chunk lvt_2_1_ = (Chunk)lvt_1_1_.next();
         this.func_73241_b(lvt_2_1_.field_76635_g, lvt_2_1_.field_76647_h);
      }

   }

   public Chunk func_73158_c(int p_73158_1_, int p_73158_2_) {
      long lvt_3_1_ = ChunkCoordIntPair.func_77272_a(p_73158_1_, p_73158_2_);
      this.field_73248_b.remove(lvt_3_1_);
      Chunk lvt_5_1_ = (Chunk)this.field_73244_f.func_76164_a(lvt_3_1_);
      if (lvt_5_1_ == null) {
         lvt_5_1_ = this.func_73239_e(p_73158_1_, p_73158_2_);
         if (lvt_5_1_ == null) {
            if (this.field_73246_d == null) {
               lvt_5_1_ = this.field_73249_c;
            } else {
               try {
                  lvt_5_1_ = this.field_73246_d.func_73154_d(p_73158_1_, p_73158_2_);
               } catch (Throwable var9) {
                  CrashReport lvt_7_1_ = CrashReport.func_85055_a(var9, "Exception generating new chunk");
                  CrashReportCategory lvt_8_1_ = lvt_7_1_.func_85058_a("Chunk to be generated");
                  lvt_8_1_.func_71507_a("Location", String.format("%d,%d", p_73158_1_, p_73158_2_));
                  lvt_8_1_.func_71507_a("Position hash", lvt_3_1_);
                  lvt_8_1_.func_71507_a("Generator", this.field_73246_d.func_73148_d());
                  throw new ReportedException(lvt_7_1_);
               }
            }
         }

         this.field_73244_f.func_76163_a(lvt_3_1_, lvt_5_1_);
         this.field_73245_g.add(lvt_5_1_);
         lvt_5_1_.func_76631_c();
         lvt_5_1_.func_76624_a(this, this, p_73158_1_, p_73158_2_);
      }

      return lvt_5_1_;
   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      Chunk lvt_3_1_ = (Chunk)this.field_73244_f.func_76164_a(ChunkCoordIntPair.func_77272_a(p_73154_1_, p_73154_2_));
      if (lvt_3_1_ == null) {
         return !this.field_73251_h.func_175686_ad() && !this.field_73250_a ? this.field_73249_c : this.func_73158_c(p_73154_1_, p_73154_2_);
      } else {
         return lvt_3_1_;
      }
   }

   private Chunk func_73239_e(int p_73239_1_, int p_73239_2_) {
      if (this.field_73247_e == null) {
         return null;
      } else {
         try {
            Chunk lvt_3_1_ = this.field_73247_e.func_75815_a(this.field_73251_h, p_73239_1_, p_73239_2_);
            if (lvt_3_1_ != null) {
               lvt_3_1_.func_177432_b(this.field_73251_h.func_82737_E());
               if (this.field_73246_d != null) {
                  this.field_73246_d.func_180514_a(lvt_3_1_, p_73239_1_, p_73239_2_);
               }
            }

            return lvt_3_1_;
         } catch (Exception var4) {
            field_147417_b.error((String)"Couldn't load chunk", (Throwable)var4);
            return null;
         }
      }
   }

   private void func_73243_a(Chunk p_73243_1_) {
      if (this.field_73247_e != null) {
         try {
            this.field_73247_e.func_75819_b(this.field_73251_h, p_73243_1_);
         } catch (Exception var3) {
            field_147417_b.error((String)"Couldn't save entities", (Throwable)var3);
         }

      }
   }

   private void func_73242_b(Chunk p_73242_1_) {
      if (this.field_73247_e != null) {
         try {
            p_73242_1_.func_177432_b(this.field_73251_h.func_82737_E());
            this.field_73247_e.func_75816_a(this.field_73251_h, p_73242_1_);
         } catch (IOException var3) {
            field_147417_b.error((String)"Couldn't save chunk", (Throwable)var3);
         } catch (MinecraftException var4) {
            field_147417_b.error((String)"Couldn't save chunk; already in use by another instance of Minecraft?", (Throwable)var4);
         }

      }
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      Chunk lvt_4_1_ = this.func_73154_d(p_73153_2_, p_73153_3_);
      if (!lvt_4_1_.func_177419_t()) {
         lvt_4_1_.func_150809_p();
         if (this.field_73246_d != null) {
            this.field_73246_d.func_73153_a(p_73153_1_, p_73153_2_, p_73153_3_);
            lvt_4_1_.func_76630_e();
         }
      }

   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      if (this.field_73246_d != null && this.field_73246_d.func_177460_a(p_177460_1_, p_177460_2_, p_177460_3_, p_177460_4_)) {
         Chunk lvt_5_1_ = this.func_73154_d(p_177460_3_, p_177460_4_);
         lvt_5_1_.func_76630_e();
         return true;
      } else {
         return false;
      }
   }

   public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
      int lvt_3_1_ = 0;
      List<Chunk> lvt_4_1_ = Lists.newArrayList((Iterable)this.field_73245_g);

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.size(); ++lvt_5_1_) {
         Chunk lvt_6_1_ = (Chunk)lvt_4_1_.get(lvt_5_1_);
         if (p_73151_1_) {
            this.func_73243_a(lvt_6_1_);
         }

         if (lvt_6_1_.func_76601_a(p_73151_1_)) {
            this.func_73242_b(lvt_6_1_);
            lvt_6_1_.func_177427_f(false);
            ++lvt_3_1_;
            if (lvt_3_1_ == 24 && !p_73151_1_) {
               return false;
            }
         }
      }

      return true;
   }

   public void func_104112_b() {
      if (this.field_73247_e != null) {
         this.field_73247_e.func_75818_b();
      }

   }

   public boolean func_73156_b() {
      if (!this.field_73251_h.field_73058_d) {
         for(int lvt_1_1_ = 0; lvt_1_1_ < 100; ++lvt_1_1_) {
            if (!this.field_73248_b.isEmpty()) {
               Long lvt_2_1_ = (Long)this.field_73248_b.iterator().next();
               Chunk lvt_3_1_ = (Chunk)this.field_73244_f.func_76164_a(lvt_2_1_);
               if (lvt_3_1_ != null) {
                  lvt_3_1_.func_76623_d();
                  this.func_73242_b(lvt_3_1_);
                  this.func_73243_a(lvt_3_1_);
                  this.field_73244_f.func_76159_d(lvt_2_1_);
                  this.field_73245_g.remove(lvt_3_1_);
               }

               this.field_73248_b.remove(lvt_2_1_);
            }
         }

         if (this.field_73247_e != null) {
            this.field_73247_e.func_75817_a();
         }
      }

      return this.field_73246_d.func_73156_b();
   }

   public boolean func_73157_c() {
      return !this.field_73251_h.field_73058_d;
   }

   public String func_73148_d() {
      return "ServerChunkCache: " + this.field_73244_f.func_76162_a() + " Drop: " + this.field_73248_b.size();
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      return this.field_73246_d.func_177458_a(p_177458_1_, p_177458_2_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return this.field_73246_d.func_180513_a(p_180513_1_, p_180513_2_, p_180513_3_);
   }

   public int func_73152_e() {
      return this.field_73244_f.func_76162_a();
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
