package net.minecraft.world.chunk.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.storage.IThreadedFileIO;
import net.minecraft.world.storage.ThreadedFileIOBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilChunkLoader implements IChunkLoader, IThreadedFileIO {
   private static final Logger field_151505_a = LogManager.getLogger();
   private Map<ChunkCoordIntPair, NBTTagCompound> field_75828_a = new ConcurrentHashMap();
   private Set<ChunkCoordIntPair> field_75826_b = Collections.newSetFromMap(new ConcurrentHashMap());
   private final File field_75825_d;
   private boolean field_183014_e = false;

   public AnvilChunkLoader(File p_i2003_1_) {
      this.field_75825_d = p_i2003_1_;
   }

   public Chunk func_75815_a(World p_75815_1_, int p_75815_2_, int p_75815_3_) throws IOException {
      ChunkCoordIntPair lvt_4_1_ = new ChunkCoordIntPair(p_75815_2_, p_75815_3_);
      NBTTagCompound lvt_5_1_ = (NBTTagCompound)this.field_75828_a.get(lvt_4_1_);
      if (lvt_5_1_ == null) {
         DataInputStream lvt_6_1_ = RegionFileCache.func_76549_c(this.field_75825_d, p_75815_2_, p_75815_3_);
         if (lvt_6_1_ == null) {
            return null;
         }

         lvt_5_1_ = CompressedStreamTools.func_74794_a(lvt_6_1_);
      }

      return this.func_75822_a(p_75815_1_, p_75815_2_, p_75815_3_, lvt_5_1_);
   }

   protected Chunk func_75822_a(World p_75822_1_, int p_75822_2_, int p_75822_3_, NBTTagCompound p_75822_4_) {
      if (!p_75822_4_.func_150297_b("Level", 10)) {
         field_151505_a.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is missing level data, skipping");
         return null;
      } else {
         NBTTagCompound lvt_5_1_ = p_75822_4_.func_74775_l("Level");
         if (!lvt_5_1_.func_150297_b("Sections", 9)) {
            field_151505_a.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is missing block data, skipping");
            return null;
         } else {
            Chunk lvt_6_1_ = this.func_75823_a(p_75822_1_, lvt_5_1_);
            if (!lvt_6_1_.func_76600_a(p_75822_2_, p_75822_3_)) {
               field_151505_a.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is in the wrong location; relocating. (Expected " + p_75822_2_ + ", " + p_75822_3_ + ", got " + lvt_6_1_.field_76635_g + ", " + lvt_6_1_.field_76647_h + ")");
               lvt_5_1_.func_74768_a("xPos", p_75822_2_);
               lvt_5_1_.func_74768_a("zPos", p_75822_3_);
               lvt_6_1_ = this.func_75823_a(p_75822_1_, lvt_5_1_);
            }

            return lvt_6_1_;
         }
      }
   }

   public void func_75816_a(World p_75816_1_, Chunk p_75816_2_) throws MinecraftException, IOException {
      p_75816_1_.func_72906_B();

      try {
         NBTTagCompound lvt_3_1_ = new NBTTagCompound();
         NBTTagCompound lvt_4_1_ = new NBTTagCompound();
         lvt_3_1_.func_74782_a("Level", lvt_4_1_);
         this.func_75820_a(p_75816_2_, p_75816_1_, lvt_4_1_);
         this.func_75824_a(p_75816_2_.func_76632_l(), lvt_3_1_);
      } catch (Exception var5) {
         field_151505_a.error((String)"Failed to save chunk", (Throwable)var5);
      }

   }

   protected void func_75824_a(ChunkCoordIntPair p_75824_1_, NBTTagCompound p_75824_2_) {
      if (!this.field_75826_b.contains(p_75824_1_)) {
         this.field_75828_a.put(p_75824_1_, p_75824_2_);
      }

      ThreadedFileIOBase.func_178779_a().func_75735_a(this);
   }

   public boolean func_75814_c() {
      if (this.field_75828_a.isEmpty()) {
         if (this.field_183014_e) {
            field_151505_a.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", this.field_75825_d.getName());
         }

         return false;
      } else {
         ChunkCoordIntPair lvt_1_1_ = (ChunkCoordIntPair)this.field_75828_a.keySet().iterator().next();

         boolean var3;
         try {
            this.field_75826_b.add(lvt_1_1_);
            NBTTagCompound lvt_2_1_ = (NBTTagCompound)this.field_75828_a.remove(lvt_1_1_);
            if (lvt_2_1_ != null) {
               try {
                  this.func_183013_b(lvt_1_1_, lvt_2_1_);
               } catch (Exception var7) {
                  field_151505_a.error((String)"Failed to save chunk", (Throwable)var7);
               }
            }

            var3 = true;
         } finally {
            this.field_75826_b.remove(lvt_1_1_);
         }

         return var3;
      }
   }

   private void func_183013_b(ChunkCoordIntPair p_183013_1_, NBTTagCompound p_183013_2_) throws IOException {
      DataOutputStream lvt_3_1_ = RegionFileCache.func_76552_d(this.field_75825_d, p_183013_1_.field_77276_a, p_183013_1_.field_77275_b);
      CompressedStreamTools.func_74800_a(p_183013_2_, lvt_3_1_);
      lvt_3_1_.close();
   }

   public void func_75819_b(World p_75819_1_, Chunk p_75819_2_) throws IOException {
   }

   public void func_75817_a() {
   }

   public void func_75818_b() {
      try {
         this.field_183014_e = true;

         while(true) {
            if (this.func_75814_c()) {
               continue;
            }
         }
      } finally {
         this.field_183014_e = false;
      }

   }

   private void func_75820_a(Chunk p_75820_1_, World p_75820_2_, NBTTagCompound p_75820_3_) {
      p_75820_3_.func_74774_a("V", (byte)1);
      p_75820_3_.func_74768_a("xPos", p_75820_1_.field_76635_g);
      p_75820_3_.func_74768_a("zPos", p_75820_1_.field_76647_h);
      p_75820_3_.func_74772_a("LastUpdate", p_75820_2_.func_82737_E());
      p_75820_3_.func_74783_a("HeightMap", p_75820_1_.func_177445_q());
      p_75820_3_.func_74757_a("TerrainPopulated", p_75820_1_.func_177419_t());
      p_75820_3_.func_74757_a("LightPopulated", p_75820_1_.func_177423_u());
      p_75820_3_.func_74772_a("InhabitedTime", p_75820_1_.func_177416_w());
      ExtendedBlockStorage[] lvt_4_1_ = p_75820_1_.func_76587_i();
      NBTTagList lvt_5_1_ = new NBTTagList();
      boolean lvt_6_1_ = !p_75820_2_.field_73011_w.func_177495_o();
      ExtendedBlockStorage[] lvt_7_1_ = lvt_4_1_;
      int lvt_8_2_ = lvt_4_1_.length;

      NBTTagCompound lvt_11_1_;
      for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_2_; ++lvt_9_1_) {
         ExtendedBlockStorage lvt_10_1_ = lvt_7_1_[lvt_9_1_];
         if (lvt_10_1_ != null) {
            lvt_11_1_ = new NBTTagCompound();
            lvt_11_1_.func_74774_a("Y", (byte)(lvt_10_1_.func_76662_d() >> 4 & 255));
            byte[] lvt_12_1_ = new byte[lvt_10_1_.func_177487_g().length];
            NibbleArray lvt_13_1_ = new NibbleArray();
            NibbleArray lvt_14_1_ = null;

            for(int lvt_15_1_ = 0; lvt_15_1_ < lvt_10_1_.func_177487_g().length; ++lvt_15_1_) {
               char lvt_16_1_ = lvt_10_1_.func_177487_g()[lvt_15_1_];
               int lvt_17_1_ = lvt_15_1_ & 15;
               int lvt_18_1_ = lvt_15_1_ >> 8 & 15;
               int lvt_19_1_ = lvt_15_1_ >> 4 & 15;
               if (lvt_16_1_ >> 12 != 0) {
                  if (lvt_14_1_ == null) {
                     lvt_14_1_ = new NibbleArray();
                  }

                  lvt_14_1_.func_76581_a(lvt_17_1_, lvt_18_1_, lvt_19_1_, lvt_16_1_ >> 12);
               }

               lvt_12_1_[lvt_15_1_] = (byte)(lvt_16_1_ >> 4 & 255);
               lvt_13_1_.func_76581_a(lvt_17_1_, lvt_18_1_, lvt_19_1_, lvt_16_1_ & 15);
            }

            lvt_11_1_.func_74773_a("Blocks", lvt_12_1_);
            lvt_11_1_.func_74773_a("Data", lvt_13_1_.func_177481_a());
            if (lvt_14_1_ != null) {
               lvt_11_1_.func_74773_a("Add", lvt_14_1_.func_177481_a());
            }

            lvt_11_1_.func_74773_a("BlockLight", lvt_10_1_.func_76661_k().func_177481_a());
            if (lvt_6_1_) {
               lvt_11_1_.func_74773_a("SkyLight", lvt_10_1_.func_76671_l().func_177481_a());
            } else {
               lvt_11_1_.func_74773_a("SkyLight", new byte[lvt_10_1_.func_76661_k().func_177481_a().length]);
            }

            lvt_5_1_.func_74742_a(lvt_11_1_);
         }
      }

      p_75820_3_.func_74782_a("Sections", lvt_5_1_);
      p_75820_3_.func_74773_a("Biomes", p_75820_1_.func_76605_m());
      p_75820_1_.func_177409_g(false);
      NBTTagList lvt_7_2_ = new NBTTagList();

      Iterator lvt_9_3_;
      for(lvt_8_2_ = 0; lvt_8_2_ < p_75820_1_.func_177429_s().length; ++lvt_8_2_) {
         lvt_9_3_ = p_75820_1_.func_177429_s()[lvt_8_2_].iterator();

         while(lvt_9_3_.hasNext()) {
            Entity lvt_10_2_ = (Entity)lvt_9_3_.next();
            lvt_11_1_ = new NBTTagCompound();
            if (lvt_10_2_.func_70039_c(lvt_11_1_)) {
               p_75820_1_.func_177409_g(true);
               lvt_7_2_.func_74742_a(lvt_11_1_);
            }
         }
      }

      p_75820_3_.func_74782_a("Entities", lvt_7_2_);
      NBTTagList lvt_8_3_ = new NBTTagList();
      lvt_9_3_ = p_75820_1_.func_177434_r().values().iterator();

      while(lvt_9_3_.hasNext()) {
         TileEntity lvt_10_3_ = (TileEntity)lvt_9_3_.next();
         lvt_11_1_ = new NBTTagCompound();
         lvt_10_3_.func_145841_b(lvt_11_1_);
         lvt_8_3_.func_74742_a(lvt_11_1_);
      }

      p_75820_3_.func_74782_a("TileEntities", lvt_8_3_);
      List<NextTickListEntry> lvt_9_4_ = p_75820_2_.func_72920_a(p_75820_1_, false);
      if (lvt_9_4_ != null) {
         long lvt_10_4_ = p_75820_2_.func_82737_E();
         NBTTagList lvt_12_2_ = new NBTTagList();
         Iterator lvt_13_2_ = lvt_9_4_.iterator();

         while(lvt_13_2_.hasNext()) {
            NextTickListEntry lvt_14_2_ = (NextTickListEntry)lvt_13_2_.next();
            NBTTagCompound lvt_15_2_ = new NBTTagCompound();
            ResourceLocation lvt_16_2_ = (ResourceLocation)Block.field_149771_c.func_177774_c(lvt_14_2_.func_151351_a());
            lvt_15_2_.func_74778_a("i", lvt_16_2_ == null ? "" : lvt_16_2_.toString());
            lvt_15_2_.func_74768_a("x", lvt_14_2_.field_180282_a.func_177958_n());
            lvt_15_2_.func_74768_a("y", lvt_14_2_.field_180282_a.func_177956_o());
            lvt_15_2_.func_74768_a("z", lvt_14_2_.field_180282_a.func_177952_p());
            lvt_15_2_.func_74768_a("t", (int)(lvt_14_2_.field_77180_e - lvt_10_4_));
            lvt_15_2_.func_74768_a("p", lvt_14_2_.field_82754_f);
            lvt_12_2_.func_74742_a(lvt_15_2_);
         }

         p_75820_3_.func_74782_a("TileTicks", lvt_12_2_);
      }

   }

   private Chunk func_75823_a(World p_75823_1_, NBTTagCompound p_75823_2_) {
      int lvt_3_1_ = p_75823_2_.func_74762_e("xPos");
      int lvt_4_1_ = p_75823_2_.func_74762_e("zPos");
      Chunk lvt_5_1_ = new Chunk(p_75823_1_, lvt_3_1_, lvt_4_1_);
      lvt_5_1_.func_177420_a(p_75823_2_.func_74759_k("HeightMap"));
      lvt_5_1_.func_177446_d(p_75823_2_.func_74767_n("TerrainPopulated"));
      lvt_5_1_.func_177421_e(p_75823_2_.func_74767_n("LightPopulated"));
      lvt_5_1_.func_177415_c(p_75823_2_.func_74763_f("InhabitedTime"));
      NBTTagList lvt_6_1_ = p_75823_2_.func_150295_c("Sections", 10);
      int lvt_7_1_ = 16;
      ExtendedBlockStorage[] lvt_8_1_ = new ExtendedBlockStorage[lvt_7_1_];
      boolean lvt_9_1_ = !p_75823_1_.field_73011_w.func_177495_o();

      for(int lvt_10_1_ = 0; lvt_10_1_ < lvt_6_1_.func_74745_c(); ++lvt_10_1_) {
         NBTTagCompound lvt_11_1_ = lvt_6_1_.func_150305_b(lvt_10_1_);
         int lvt_12_1_ = lvt_11_1_.func_74771_c("Y");
         ExtendedBlockStorage lvt_13_1_ = new ExtendedBlockStorage(lvt_12_1_ << 4, lvt_9_1_);
         byte[] lvt_14_1_ = lvt_11_1_.func_74770_j("Blocks");
         NibbleArray lvt_15_1_ = new NibbleArray(lvt_11_1_.func_74770_j("Data"));
         NibbleArray lvt_16_1_ = lvt_11_1_.func_150297_b("Add", 7) ? new NibbleArray(lvt_11_1_.func_74770_j("Add")) : null;
         char[] lvt_17_1_ = new char[lvt_14_1_.length];

         for(int lvt_18_1_ = 0; lvt_18_1_ < lvt_17_1_.length; ++lvt_18_1_) {
            int lvt_19_1_ = lvt_18_1_ & 15;
            int lvt_20_1_ = lvt_18_1_ >> 8 & 15;
            int lvt_21_1_ = lvt_18_1_ >> 4 & 15;
            int lvt_22_1_ = lvt_16_1_ != null ? lvt_16_1_.func_76582_a(lvt_19_1_, lvt_20_1_, lvt_21_1_) : 0;
            lvt_17_1_[lvt_18_1_] = (char)(lvt_22_1_ << 12 | (lvt_14_1_[lvt_18_1_] & 255) << 4 | lvt_15_1_.func_76582_a(lvt_19_1_, lvt_20_1_, lvt_21_1_));
         }

         lvt_13_1_.func_177486_a(lvt_17_1_);
         lvt_13_1_.func_76659_c(new NibbleArray(lvt_11_1_.func_74770_j("BlockLight")));
         if (lvt_9_1_) {
            lvt_13_1_.func_76666_d(new NibbleArray(lvt_11_1_.func_74770_j("SkyLight")));
         }

         lvt_13_1_.func_76672_e();
         lvt_8_1_[lvt_12_1_] = lvt_13_1_;
      }

      lvt_5_1_.func_76602_a(lvt_8_1_);
      if (p_75823_2_.func_150297_b("Biomes", 7)) {
         lvt_5_1_.func_76616_a(p_75823_2_.func_74770_j("Biomes"));
      }

      NBTTagList lvt_10_2_ = p_75823_2_.func_150295_c("Entities", 10);
      if (lvt_10_2_ != null) {
         for(int lvt_11_2_ = 0; lvt_11_2_ < lvt_10_2_.func_74745_c(); ++lvt_11_2_) {
            NBTTagCompound lvt_12_2_ = lvt_10_2_.func_150305_b(lvt_11_2_);
            Entity lvt_13_2_ = EntityList.func_75615_a(lvt_12_2_, p_75823_1_);
            lvt_5_1_.func_177409_g(true);
            if (lvt_13_2_ != null) {
               lvt_5_1_.func_76612_a(lvt_13_2_);
               Entity lvt_14_2_ = lvt_13_2_;

               for(NBTTagCompound lvt_15_2_ = lvt_12_2_; lvt_15_2_.func_150297_b("Riding", 10); lvt_15_2_ = lvt_15_2_.func_74775_l("Riding")) {
                  Entity lvt_16_2_ = EntityList.func_75615_a(lvt_15_2_.func_74775_l("Riding"), p_75823_1_);
                  if (lvt_16_2_ != null) {
                     lvt_5_1_.func_76612_a(lvt_16_2_);
                     lvt_14_2_.func_70078_a(lvt_16_2_);
                  }

                  lvt_14_2_ = lvt_16_2_;
               }
            }
         }
      }

      NBTTagList lvt_11_3_ = p_75823_2_.func_150295_c("TileEntities", 10);
      if (lvt_11_3_ != null) {
         for(int lvt_12_3_ = 0; lvt_12_3_ < lvt_11_3_.func_74745_c(); ++lvt_12_3_) {
            NBTTagCompound lvt_13_3_ = lvt_11_3_.func_150305_b(lvt_12_3_);
            TileEntity lvt_14_3_ = TileEntity.func_145827_c(lvt_13_3_);
            if (lvt_14_3_ != null) {
               lvt_5_1_.func_150813_a(lvt_14_3_);
            }
         }
      }

      if (p_75823_2_.func_150297_b("TileTicks", 9)) {
         NBTTagList lvt_12_4_ = p_75823_2_.func_150295_c("TileTicks", 10);
         if (lvt_12_4_ != null) {
            for(int lvt_13_4_ = 0; lvt_13_4_ < lvt_12_4_.func_74745_c(); ++lvt_13_4_) {
               NBTTagCompound lvt_14_4_ = lvt_12_4_.func_150305_b(lvt_13_4_);
               Block lvt_15_4_;
               if (lvt_14_4_.func_150297_b("i", 8)) {
                  lvt_15_4_ = Block.func_149684_b(lvt_14_4_.func_74779_i("i"));
               } else {
                  lvt_15_4_ = Block.func_149729_e(lvt_14_4_.func_74762_e("i"));
               }

               p_75823_1_.func_180497_b(new BlockPos(lvt_14_4_.func_74762_e("x"), lvt_14_4_.func_74762_e("y"), lvt_14_4_.func_74762_e("z")), lvt_15_4_, lvt_14_4_.func_74762_e("t"), lvt_14_4_.func_74762_e("p"));
            }
         }
      }

      return lvt_5_1_;
   }
}
