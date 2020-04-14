package net.minecraft.client.resources;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileResourcePack extends AbstractResourcePack implements Closeable {
   public static final Splitter field_110601_c = Splitter.on('/').omitEmptyStrings().limit(3);
   private ZipFile field_110600_d;

   public FileResourcePack(File p_i1290_1_) {
      super(p_i1290_1_);
   }

   private ZipFile func_110599_c() throws IOException {
      if (this.field_110600_d == null) {
         this.field_110600_d = new ZipFile(this.field_110597_b);
      }

      return this.field_110600_d;
   }

   protected InputStream func_110591_a(String p_110591_1_) throws IOException {
      ZipFile lvt_2_1_ = this.func_110599_c();
      ZipEntry lvt_3_1_ = lvt_2_1_.getEntry(p_110591_1_);
      if (lvt_3_1_ == null) {
         throw new ResourcePackFileNotFoundException(this.field_110597_b, p_110591_1_);
      } else {
         return lvt_2_1_.getInputStream(lvt_3_1_);
      }
   }

   public boolean func_110593_b(String p_110593_1_) {
      try {
         return this.func_110599_c().getEntry(p_110593_1_) != null;
      } catch (IOException var3) {
         return false;
      }
   }

   public Set<String> func_110587_b() {
      ZipFile lvt_1_2_;
      try {
         lvt_1_2_ = this.func_110599_c();
      } catch (IOException var8) {
         return Collections.emptySet();
      }

      Enumeration<? extends ZipEntry> lvt_2_2_ = lvt_1_2_.entries();
      HashSet lvt_3_1_ = Sets.newHashSet();

      while(lvt_2_2_.hasMoreElements()) {
         ZipEntry lvt_4_1_ = (ZipEntry)lvt_2_2_.nextElement();
         String lvt_5_1_ = lvt_4_1_.getName();
         if (lvt_5_1_.startsWith("assets/")) {
            List<String> lvt_6_1_ = Lists.newArrayList(field_110601_c.split(lvt_5_1_));
            if (lvt_6_1_.size() > 1) {
               String lvt_7_1_ = (String)lvt_6_1_.get(1);
               if (!lvt_7_1_.equals(lvt_7_1_.toLowerCase())) {
                  this.func_110594_c(lvt_7_1_);
               } else {
                  lvt_3_1_.add(lvt_7_1_);
               }
            }
         }
      }

      return lvt_3_1_;
   }

   protected void finalize() throws Throwable {
      this.close();
      super.finalize();
   }

   public void close() throws IOException {
      if (this.field_110600_d != null) {
         this.field_110600_d.close();
         this.field_110600_d = null;
      }

   }
}
