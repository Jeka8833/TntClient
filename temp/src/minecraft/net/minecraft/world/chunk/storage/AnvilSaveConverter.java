package net.minecraft.world.chunk.storage;

import com.google.common.collect.Lists;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.SaveFormatOld;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilSaveConverter extends SaveFormatOld {
   private static final Logger field_151480_b = LogManager.getLogger();

   public AnvilSaveConverter(File p_i2144_1_) {
      super(p_i2144_1_);
   }

   public String func_154333_a() {
      return "Anvil";
   }

   public List<SaveFormatComparator> func_75799_b() throws AnvilConverterException {
      if (this.field_75808_a != null && this.field_75808_a.exists() && this.field_75808_a.isDirectory()) {
         List<SaveFormatComparator> lvt_1_1_ = Lists.newArrayList();
         File[] lvt_2_1_ = this.field_75808_a.listFiles();
         File[] lvt_3_1_ = lvt_2_1_;
         int lvt_4_1_ = lvt_2_1_.length;

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
            File lvt_6_1_ = lvt_3_1_[lvt_5_1_];
            if (lvt_6_1_.isDirectory()) {
               String lvt_7_1_ = lvt_6_1_.getName();
               WorldInfo lvt_8_1_ = this.func_75803_c(lvt_7_1_);
               if (lvt_8_1_ != null && (lvt_8_1_.func_76088_k() == 19132 || lvt_8_1_.func_76088_k() == 19133)) {
                  boolean lvt_9_1_ = lvt_8_1_.func_76088_k() != this.func_75812_c();
                  String lvt_10_1_ = lvt_8_1_.func_76065_j();
                  if (StringUtils.isEmpty(lvt_10_1_)) {
                     lvt_10_1_ = lvt_7_1_;
                  }

                  long lvt_11_1_ = 0L;
                  lvt_1_1_.add(new SaveFormatComparator(lvt_7_1_, lvt_10_1_, lvt_8_1_.func_76057_l(), lvt_11_1_, lvt_8_1_.func_76077_q(), lvt_9_1_, lvt_8_1_.func_76093_s(), lvt_8_1_.func_76086_u()));
               }
            }
         }

         return lvt_1_1_;
      } else {
         throw new AnvilConverterException("Unable to read or access folder where game worlds are saved!");
      }
   }

   protected int func_75812_c() {
      return 19133;
   }

   public void func_75800_d() {
      RegionFileCache.func_76551_a();
   }

   public ISaveHandler func_75804_a(String p_75804_1_, boolean p_75804_2_) {
      return new AnvilSaveHandler(this.field_75808_a, p_75804_1_, p_75804_2_);
   }

   public boolean func_154334_a(String p_154334_1_) {
      WorldInfo lvt_2_1_ = this.func_75803_c(p_154334_1_);
      return lvt_2_1_ != null && lvt_2_1_.func_76088_k() == 19132;
   }

   public boolean func_75801_b(String p_75801_1_) {
      WorldInfo lvt_2_1_ = this.func_75803_c(p_75801_1_);
      return lvt_2_1_ != null && lvt_2_1_.func_76088_k() != this.func_75812_c();
   }

   public boolean func_75805_a(String p_75805_1_, IProgressUpdate p_75805_2_) {
      p_75805_2_.func_73718_a(0);
      List<File> lvt_3_1_ = Lists.newArrayList();
      List<File> lvt_4_1_ = Lists.newArrayList();
      List<File> lvt_5_1_ = Lists.newArrayList();
      File lvt_6_1_ = new File(this.field_75808_a, p_75805_1_);
      File lvt_7_1_ = new File(lvt_6_1_, "DIM-1");
      File lvt_8_1_ = new File(lvt_6_1_, "DIM1");
      field_151480_b.info("Scanning folders...");
      this.func_75810_a(lvt_6_1_, lvt_3_1_);
      if (lvt_7_1_.exists()) {
         this.func_75810_a(lvt_7_1_, lvt_4_1_);
      }

      if (lvt_8_1_.exists()) {
         this.func_75810_a(lvt_8_1_, lvt_5_1_);
      }

      int lvt_9_1_ = lvt_3_1_.size() + lvt_4_1_.size() + lvt_5_1_.size();
      field_151480_b.info("Total conversion count is " + lvt_9_1_);
      WorldInfo lvt_10_1_ = this.func_75803_c(p_75805_1_);
      WorldChunkManager lvt_11_1_ = null;
      if (lvt_10_1_.func_76067_t() == WorldType.field_77138_c) {
         lvt_11_1_ = new WorldChunkManagerHell(BiomeGenBase.field_76772_c, 0.5F);
      } else {
         lvt_11_1_ = new WorldChunkManager(lvt_10_1_.func_76063_b(), lvt_10_1_.func_76067_t(), lvt_10_1_.func_82571_y());
      }

      this.func_75813_a(new File(lvt_6_1_, "region"), lvt_3_1_, (WorldChunkManager)lvt_11_1_, 0, lvt_9_1_, p_75805_2_);
      this.func_75813_a(new File(lvt_7_1_, "region"), lvt_4_1_, new WorldChunkManagerHell(BiomeGenBase.field_76778_j, 0.0F), lvt_3_1_.size(), lvt_9_1_, p_75805_2_);
      this.func_75813_a(new File(lvt_8_1_, "region"), lvt_5_1_, new WorldChunkManagerHell(BiomeGenBase.field_76779_k, 0.0F), lvt_3_1_.size() + lvt_4_1_.size(), lvt_9_1_, p_75805_2_);
      lvt_10_1_.func_76078_e(19133);
      if (lvt_10_1_.func_76067_t() == WorldType.field_77136_e) {
         lvt_10_1_.func_76085_a(WorldType.field_77137_b);
      }

      this.func_75809_f(p_75805_1_);
      ISaveHandler lvt_12_1_ = this.func_75804_a(p_75805_1_, false);
      lvt_12_1_.func_75761_a(lvt_10_1_);
      return true;
   }

   private void func_75809_f(String p_75809_1_) {
      File lvt_2_1_ = new File(this.field_75808_a, p_75809_1_);
      if (!lvt_2_1_.exists()) {
         field_151480_b.warn("Unable to create level.dat_mcr backup");
      } else {
         File lvt_3_1_ = new File(lvt_2_1_, "level.dat");
         if (!lvt_3_1_.exists()) {
            field_151480_b.warn("Unable to create level.dat_mcr backup");
         } else {
            File lvt_4_1_ = new File(lvt_2_1_, "level.dat_mcr");
            if (!lvt_3_1_.renameTo(lvt_4_1_)) {
               field_151480_b.warn("Unable to create level.dat_mcr backup");
            }

         }
      }
   }

   private void func_75813_a(File p_75813_1_, Iterable<File> p_75813_2_, WorldChunkManager p_75813_3_, int p_75813_4_, int p_75813_5_, IProgressUpdate p_75813_6_) {
      Iterator lvt_7_1_ = p_75813_2_.iterator();

      while(lvt_7_1_.hasNext()) {
         File lvt_8_1_ = (File)lvt_7_1_.next();
         this.func_75811_a(p_75813_1_, lvt_8_1_, p_75813_3_, p_75813_4_, p_75813_5_, p_75813_6_);
         ++p_75813_4_;
         int lvt_9_1_ = (int)Math.round(100.0D * (double)p_75813_4_ / (double)p_75813_5_);
         p_75813_6_.func_73718_a(lvt_9_1_);
      }

   }

   private void func_75811_a(File p_75811_1_, File p_75811_2_, WorldChunkManager p_75811_3_, int p_75811_4_, int p_75811_5_, IProgressUpdate p_75811_6_) {
      try {
         String lvt_7_1_ = p_75811_2_.getName();
         RegionFile lvt_8_1_ = new RegionFile(p_75811_2_);
         RegionFile lvt_9_1_ = new RegionFile(new File(p_75811_1_, lvt_7_1_.substring(0, lvt_7_1_.length() - ".mcr".length()) + ".mca"));

         for(int lvt_10_1_ = 0; lvt_10_1_ < 32; ++lvt_10_1_) {
            int lvt_11_1_;
            for(lvt_11_1_ = 0; lvt_11_1_ < 32; ++lvt_11_1_) {
               if (lvt_8_1_.func_76709_c(lvt_10_1_, lvt_11_1_) && !lvt_9_1_.func_76709_c(lvt_10_1_, lvt_11_1_)) {
                  DataInputStream lvt_12_1_ = lvt_8_1_.func_76704_a(lvt_10_1_, lvt_11_1_);
                  if (lvt_12_1_ == null) {
                     field_151480_b.warn("Failed to fetch input stream");
                  } else {
                     NBTTagCompound lvt_13_1_ = CompressedStreamTools.func_74794_a(lvt_12_1_);
                     lvt_12_1_.close();
                     NBTTagCompound lvt_14_1_ = lvt_13_1_.func_74775_l("Level");
                     ChunkLoader.AnvilConverterData lvt_15_1_ = ChunkLoader.func_76691_a(lvt_14_1_);
                     NBTTagCompound lvt_16_1_ = new NBTTagCompound();
                     NBTTagCompound lvt_17_1_ = new NBTTagCompound();
                     lvt_16_1_.func_74782_a("Level", lvt_17_1_);
                     ChunkLoader.func_76690_a(lvt_15_1_, lvt_17_1_, p_75811_3_);
                     DataOutputStream lvt_18_1_ = lvt_9_1_.func_76710_b(lvt_10_1_, lvt_11_1_);
                     CompressedStreamTools.func_74800_a(lvt_16_1_, lvt_18_1_);
                     lvt_18_1_.close();
                  }
               }
            }

            lvt_11_1_ = (int)Math.round(100.0D * (double)(p_75811_4_ * 1024) / (double)(p_75811_5_ * 1024));
            int lvt_12_2_ = (int)Math.round(100.0D * (double)((lvt_10_1_ + 1) * 32 + p_75811_4_ * 1024) / (double)(p_75811_5_ * 1024));
            if (lvt_12_2_ > lvt_11_1_) {
               p_75811_6_.func_73718_a(lvt_12_2_);
            }
         }

         lvt_8_1_.func_76708_c();
         lvt_9_1_.func_76708_c();
      } catch (IOException var19) {
         var19.printStackTrace();
      }

   }

   private void func_75810_a(File p_75810_1_, Collection<File> p_75810_2_) {
      File lvt_3_1_ = new File(p_75810_1_, "region");
      File[] lvt_4_1_ = lvt_3_1_.listFiles(new FilenameFilter() {
         public boolean accept(File p_accept_1_, String p_accept_2_) {
            return p_accept_2_.endsWith(".mcr");
         }
      });
      if (lvt_4_1_ != null) {
         Collections.addAll(p_75810_2_, lvt_4_1_);
      }

   }
}
