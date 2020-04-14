package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveFormatOld implements ISaveFormat {
   private static final Logger field_151479_b = LogManager.getLogger();
   protected final File field_75808_a;

   public SaveFormatOld(File p_i2147_1_) {
      if (!p_i2147_1_.exists()) {
         p_i2147_1_.mkdirs();
      }

      this.field_75808_a = p_i2147_1_;
   }

   public String func_154333_a() {
      return "Old Format";
   }

   public List<SaveFormatComparator> func_75799_b() throws AnvilConverterException {
      List<SaveFormatComparator> lvt_1_1_ = Lists.newArrayList();

      for(int lvt_2_1_ = 0; lvt_2_1_ < 5; ++lvt_2_1_) {
         String lvt_3_1_ = "World" + (lvt_2_1_ + 1);
         WorldInfo lvt_4_1_ = this.func_75803_c(lvt_3_1_);
         if (lvt_4_1_ != null) {
            lvt_1_1_.add(new SaveFormatComparator(lvt_3_1_, "", lvt_4_1_.func_76057_l(), lvt_4_1_.func_76092_g(), lvt_4_1_.func_76077_q(), false, lvt_4_1_.func_76093_s(), lvt_4_1_.func_76086_u()));
         }
      }

      return lvt_1_1_;
   }

   public void func_75800_d() {
   }

   public WorldInfo func_75803_c(String p_75803_1_) {
      File lvt_2_1_ = new File(this.field_75808_a, p_75803_1_);
      if (!lvt_2_1_.exists()) {
         return null;
      } else {
         File lvt_3_1_ = new File(lvt_2_1_, "level.dat");
         NBTTagCompound lvt_4_3_;
         NBTTagCompound lvt_5_2_;
         if (lvt_3_1_.exists()) {
            try {
               lvt_4_3_ = CompressedStreamTools.func_74796_a(new FileInputStream(lvt_3_1_));
               lvt_5_2_ = lvt_4_3_.func_74775_l("Data");
               return new WorldInfo(lvt_5_2_);
            } catch (Exception var7) {
               field_151479_b.error((String)("Exception reading " + lvt_3_1_), (Throwable)var7);
            }
         }

         lvt_3_1_ = new File(lvt_2_1_, "level.dat_old");
         if (lvt_3_1_.exists()) {
            try {
               lvt_4_3_ = CompressedStreamTools.func_74796_a(new FileInputStream(lvt_3_1_));
               lvt_5_2_ = lvt_4_3_.func_74775_l("Data");
               return new WorldInfo(lvt_5_2_);
            } catch (Exception var6) {
               field_151479_b.error((String)("Exception reading " + lvt_3_1_), (Throwable)var6);
            }
         }

         return null;
      }
   }

   public void func_75806_a(String p_75806_1_, String p_75806_2_) {
      File lvt_3_1_ = new File(this.field_75808_a, p_75806_1_);
      if (lvt_3_1_.exists()) {
         File lvt_4_1_ = new File(lvt_3_1_, "level.dat");
         if (lvt_4_1_.exists()) {
            try {
               NBTTagCompound lvt_5_1_ = CompressedStreamTools.func_74796_a(new FileInputStream(lvt_4_1_));
               NBTTagCompound lvt_6_1_ = lvt_5_1_.func_74775_l("Data");
               lvt_6_1_.func_74778_a("LevelName", p_75806_2_);
               CompressedStreamTools.func_74799_a(lvt_5_1_, new FileOutputStream(lvt_4_1_));
            } catch (Exception var7) {
               var7.printStackTrace();
            }
         }

      }
   }

   public boolean func_154335_d(String p_154335_1_) {
      File lvt_2_1_ = new File(this.field_75808_a, p_154335_1_);
      if (lvt_2_1_.exists()) {
         return false;
      } else {
         try {
            lvt_2_1_.mkdir();
            lvt_2_1_.delete();
            return true;
         } catch (Throwable var4) {
            field_151479_b.warn("Couldn't make new level", var4);
            return false;
         }
      }
   }

   public boolean func_75802_e(String p_75802_1_) {
      File lvt_2_1_ = new File(this.field_75808_a, p_75802_1_);
      if (!lvt_2_1_.exists()) {
         return true;
      } else {
         field_151479_b.info("Deleting level " + p_75802_1_);

         for(int lvt_3_1_ = 1; lvt_3_1_ <= 5; ++lvt_3_1_) {
            field_151479_b.info("Attempt " + lvt_3_1_ + "...");
            if (func_75807_a(lvt_2_1_.listFiles())) {
               break;
            }

            field_151479_b.warn("Unsuccessful in deleting contents.");
            if (lvt_3_1_ < 5) {
               try {
                  Thread.sleep(500L);
               } catch (InterruptedException var5) {
               }
            }
         }

         return lvt_2_1_.delete();
      }
   }

   protected static boolean func_75807_a(File[] p_75807_0_) {
      for(int lvt_1_1_ = 0; lvt_1_1_ < p_75807_0_.length; ++lvt_1_1_) {
         File lvt_2_1_ = p_75807_0_[lvt_1_1_];
         field_151479_b.debug("Deleting " + lvt_2_1_);
         if (lvt_2_1_.isDirectory() && !func_75807_a(lvt_2_1_.listFiles())) {
            field_151479_b.warn("Couldn't delete directory " + lvt_2_1_);
            return false;
         }

         if (!lvt_2_1_.delete()) {
            field_151479_b.warn("Couldn't delete file " + lvt_2_1_);
            return false;
         }
      }

      return true;
   }

   public ISaveHandler func_75804_a(String p_75804_1_, boolean p_75804_2_) {
      return new SaveHandler(this.field_75808_a, p_75804_1_, p_75804_2_);
   }

   public boolean func_154334_a(String p_154334_1_) {
      return false;
   }

   public boolean func_75801_b(String p_75801_1_) {
      return false;
   }

   public boolean func_75805_a(String p_75805_1_, IProgressUpdate p_75805_2_) {
      return false;
   }

   public boolean func_90033_f(String p_90033_1_) {
      File lvt_2_1_ = new File(this.field_75808_a, p_90033_1_);
      return lvt_2_1_.isDirectory();
   }
}
