package net.minecraft.world.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveHandler implements ISaveHandler, IPlayerFileData {
   private static final Logger field_151478_a = LogManager.getLogger();
   private final File field_75770_b;
   private final File field_75771_c;
   private final File field_75768_d;
   private final long field_75769_e = MinecraftServer.func_130071_aq();
   private final String field_75767_f;

   public SaveHandler(File p_i2146_1_, String p_i2146_2_, boolean p_i2146_3_) {
      this.field_75770_b = new File(p_i2146_1_, p_i2146_2_);
      this.field_75770_b.mkdirs();
      this.field_75771_c = new File(this.field_75770_b, "playerdata");
      this.field_75768_d = new File(this.field_75770_b, "data");
      this.field_75768_d.mkdirs();
      this.field_75767_f = p_i2146_2_;
      if (p_i2146_3_) {
         this.field_75771_c.mkdirs();
      }

      this.func_75766_h();
   }

   private void func_75766_h() {
      try {
         File lvt_1_1_ = new File(this.field_75770_b, "session.lock");
         DataOutputStream lvt_2_1_ = new DataOutputStream(new FileOutputStream(lvt_1_1_));

         try {
            lvt_2_1_.writeLong(this.field_75769_e);
         } finally {
            lvt_2_1_.close();
         }

      } catch (IOException var7) {
         var7.printStackTrace();
         throw new RuntimeException("Failed to check session lock, aborting");
      }
   }

   public File func_75765_b() {
      return this.field_75770_b;
   }

   public void func_75762_c() throws MinecraftException {
      try {
         File lvt_1_1_ = new File(this.field_75770_b, "session.lock");
         DataInputStream lvt_2_1_ = new DataInputStream(new FileInputStream(lvt_1_1_));

         try {
            if (lvt_2_1_.readLong() != this.field_75769_e) {
               throw new MinecraftException("The save is being accessed from another location, aborting");
            }
         } finally {
            lvt_2_1_.close();
         }

      } catch (IOException var7) {
         throw new MinecraftException("Failed to check session lock, aborting");
      }
   }

   public IChunkLoader func_75763_a(WorldProvider p_75763_1_) {
      throw new RuntimeException("Old Chunk Storage is no longer supported.");
   }

   public WorldInfo func_75757_d() {
      File lvt_1_1_ = new File(this.field_75770_b, "level.dat");
      NBTTagCompound lvt_2_3_;
      NBTTagCompound lvt_3_2_;
      if (lvt_1_1_.exists()) {
         try {
            lvt_2_3_ = CompressedStreamTools.func_74796_a(new FileInputStream(lvt_1_1_));
            lvt_3_2_ = lvt_2_3_.func_74775_l("Data");
            return new WorldInfo(lvt_3_2_);
         } catch (Exception var5) {
            var5.printStackTrace();
         }
      }

      lvt_1_1_ = new File(this.field_75770_b, "level.dat_old");
      if (lvt_1_1_.exists()) {
         try {
            lvt_2_3_ = CompressedStreamTools.func_74796_a(new FileInputStream(lvt_1_1_));
            lvt_3_2_ = lvt_2_3_.func_74775_l("Data");
            return new WorldInfo(lvt_3_2_);
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

      return null;
   }

   public void func_75755_a(WorldInfo p_75755_1_, NBTTagCompound p_75755_2_) {
      NBTTagCompound lvt_3_1_ = p_75755_1_.func_76082_a(p_75755_2_);
      NBTTagCompound lvt_4_1_ = new NBTTagCompound();
      lvt_4_1_.func_74782_a("Data", lvt_3_1_);

      try {
         File lvt_5_1_ = new File(this.field_75770_b, "level.dat_new");
         File lvt_6_1_ = new File(this.field_75770_b, "level.dat_old");
         File lvt_7_1_ = new File(this.field_75770_b, "level.dat");
         CompressedStreamTools.func_74799_a(lvt_4_1_, new FileOutputStream(lvt_5_1_));
         if (lvt_6_1_.exists()) {
            lvt_6_1_.delete();
         }

         lvt_7_1_.renameTo(lvt_6_1_);
         if (lvt_7_1_.exists()) {
            lvt_7_1_.delete();
         }

         lvt_5_1_.renameTo(lvt_7_1_);
         if (lvt_5_1_.exists()) {
            lvt_5_1_.delete();
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   public void func_75761_a(WorldInfo p_75761_1_) {
      NBTTagCompound lvt_2_1_ = p_75761_1_.func_76066_a();
      NBTTagCompound lvt_3_1_ = new NBTTagCompound();
      lvt_3_1_.func_74782_a("Data", lvt_2_1_);

      try {
         File lvt_4_1_ = new File(this.field_75770_b, "level.dat_new");
         File lvt_5_1_ = new File(this.field_75770_b, "level.dat_old");
         File lvt_6_1_ = new File(this.field_75770_b, "level.dat");
         CompressedStreamTools.func_74799_a(lvt_3_1_, new FileOutputStream(lvt_4_1_));
         if (lvt_5_1_.exists()) {
            lvt_5_1_.delete();
         }

         lvt_6_1_.renameTo(lvt_5_1_);
         if (lvt_6_1_.exists()) {
            lvt_6_1_.delete();
         }

         lvt_4_1_.renameTo(lvt_6_1_);
         if (lvt_4_1_.exists()) {
            lvt_4_1_.delete();
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   public void func_75753_a(EntityPlayer p_75753_1_) {
      try {
         NBTTagCompound lvt_2_1_ = new NBTTagCompound();
         p_75753_1_.func_70109_d(lvt_2_1_);
         File lvt_3_1_ = new File(this.field_75771_c, p_75753_1_.func_110124_au().toString() + ".dat.tmp");
         File lvt_4_1_ = new File(this.field_75771_c, p_75753_1_.func_110124_au().toString() + ".dat");
         CompressedStreamTools.func_74799_a(lvt_2_1_, new FileOutputStream(lvt_3_1_));
         if (lvt_4_1_.exists()) {
            lvt_4_1_.delete();
         }

         lvt_3_1_.renameTo(lvt_4_1_);
      } catch (Exception var5) {
         field_151478_a.warn("Failed to save player data for " + p_75753_1_.func_70005_c_());
      }

   }

   public NBTTagCompound func_75752_b(EntityPlayer p_75752_1_) {
      NBTTagCompound lvt_2_1_ = null;

      try {
         File lvt_3_1_ = new File(this.field_75771_c, p_75752_1_.func_110124_au().toString() + ".dat");
         if (lvt_3_1_.exists() && lvt_3_1_.isFile()) {
            lvt_2_1_ = CompressedStreamTools.func_74796_a(new FileInputStream(lvt_3_1_));
         }
      } catch (Exception var4) {
         field_151478_a.warn("Failed to load player data for " + p_75752_1_.func_70005_c_());
      }

      if (lvt_2_1_ != null) {
         p_75752_1_.func_70020_e(lvt_2_1_);
      }

      return lvt_2_1_;
   }

   public IPlayerFileData func_75756_e() {
      return this;
   }

   public String[] func_75754_f() {
      String[] lvt_1_1_ = this.field_75771_c.list();
      if (lvt_1_1_ == null) {
         lvt_1_1_ = new String[0];
      }

      for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_.length; ++lvt_2_1_) {
         if (lvt_1_1_[lvt_2_1_].endsWith(".dat")) {
            lvt_1_1_[lvt_2_1_] = lvt_1_1_[lvt_2_1_].substring(0, lvt_1_1_[lvt_2_1_].length() - 4);
         }
      }

      return lvt_1_1_;
   }

   public void func_75759_a() {
   }

   public File func_75758_b(String p_75758_1_) {
      return new File(this.field_75768_d, p_75758_1_ + ".dat");
   }

   public String func_75760_g() {
      return this.field_75767_f;
   }
}
