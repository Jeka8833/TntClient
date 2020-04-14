package net.minecraft.client.resources;

import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class FolderResourcePack extends AbstractResourcePack {
   public FolderResourcePack(File p_i1291_1_) {
      super(p_i1291_1_);
   }

   protected InputStream func_110591_a(String p_110591_1_) throws IOException {
      return new BufferedInputStream(new FileInputStream(new File(this.field_110597_b, p_110591_1_)));
   }

   protected boolean func_110593_b(String p_110593_1_) {
      return (new File(this.field_110597_b, p_110593_1_)).isFile();
   }

   public Set<String> func_110587_b() {
      Set<String> lvt_1_1_ = Sets.newHashSet();
      File lvt_2_1_ = new File(this.field_110597_b, "assets/");
      if (lvt_2_1_.isDirectory()) {
         File[] lvt_3_1_ = lvt_2_1_.listFiles(DirectoryFileFilter.DIRECTORY);
         int lvt_4_1_ = lvt_3_1_.length;

         for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_; ++lvt_5_1_) {
            File lvt_6_1_ = lvt_3_1_[lvt_5_1_];
            String lvt_7_1_ = func_110595_a(lvt_2_1_, lvt_6_1_);
            if (!lvt_7_1_.equals(lvt_7_1_.toLowerCase())) {
               this.func_110594_c(lvt_7_1_);
            } else {
               lvt_1_1_.add(lvt_7_1_.substring(0, lvt_7_1_.length() - 1));
            }
         }
      }

      return lvt_1_1_;
   }
}
