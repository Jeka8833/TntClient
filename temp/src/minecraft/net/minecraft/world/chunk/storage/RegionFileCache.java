package net.minecraft.world.chunk.storage;

import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class RegionFileCache {
   private static final Map<File, RegionFile> field_76553_a = Maps.newHashMap();

   public static synchronized RegionFile func_76550_a(File p_76550_0_, int p_76550_1_, int p_76550_2_) {
      File lvt_3_1_ = new File(p_76550_0_, "region");
      File lvt_4_1_ = new File(lvt_3_1_, "r." + (p_76550_1_ >> 5) + "." + (p_76550_2_ >> 5) + ".mca");
      RegionFile lvt_5_1_ = (RegionFile)field_76553_a.get(lvt_4_1_);
      if (lvt_5_1_ != null) {
         return lvt_5_1_;
      } else {
         if (!lvt_3_1_.exists()) {
            lvt_3_1_.mkdirs();
         }

         if (field_76553_a.size() >= 256) {
            func_76551_a();
         }

         RegionFile lvt_6_1_ = new RegionFile(lvt_4_1_);
         field_76553_a.put(lvt_4_1_, lvt_6_1_);
         return lvt_6_1_;
      }
   }

   public static synchronized void func_76551_a() {
      Iterator lvt_0_1_ = field_76553_a.values().iterator();

      while(lvt_0_1_.hasNext()) {
         RegionFile lvt_1_1_ = (RegionFile)lvt_0_1_.next();

         try {
            if (lvt_1_1_ != null) {
               lvt_1_1_.func_76708_c();
            }
         } catch (IOException var3) {
            var3.printStackTrace();
         }
      }

      field_76553_a.clear();
   }

   public static DataInputStream func_76549_c(File p_76549_0_, int p_76549_1_, int p_76549_2_) {
      RegionFile lvt_3_1_ = func_76550_a(p_76549_0_, p_76549_1_, p_76549_2_);
      return lvt_3_1_.func_76704_a(p_76549_1_ & 31, p_76549_2_ & 31);
   }

   public static DataOutputStream func_76552_d(File p_76552_0_, int p_76552_1_, int p_76552_2_) {
      RegionFile lvt_3_1_ = func_76550_a(p_76552_0_, p_76552_1_, p_76552_2_);
      return lvt_3_1_.func_76710_b(p_76552_1_ & 31, p_76552_2_ & 31);
   }
}
