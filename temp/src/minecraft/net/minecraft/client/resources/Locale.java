package net.minecraft.client.resources;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class Locale {
   private static final Splitter field_135030_b = Splitter.on('=').limit(2);
   private static final Pattern field_135031_c = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
   Map<String, String> field_135032_a = Maps.newHashMap();
   private boolean field_135029_d;

   public synchronized void func_135022_a(IResourceManager p_135022_1_, List<String> p_135022_2_) {
      this.field_135032_a.clear();
      Iterator lvt_3_1_ = p_135022_2_.iterator();

      while(lvt_3_1_.hasNext()) {
         String lvt_4_1_ = (String)lvt_3_1_.next();
         String lvt_5_1_ = String.format("lang/%s.lang", lvt_4_1_);
         Iterator lvt_6_1_ = p_135022_1_.func_135055_a().iterator();

         while(lvt_6_1_.hasNext()) {
            String lvt_7_1_ = (String)lvt_6_1_.next();

            try {
               this.func_135028_a(p_135022_1_.func_135056_b(new ResourceLocation(lvt_7_1_, lvt_5_1_)));
            } catch (IOException var9) {
            }
         }
      }

      this.func_135024_b();
   }

   public boolean func_135025_a() {
      return this.field_135029_d;
   }

   private void func_135024_b() {
      this.field_135029_d = false;
      int lvt_1_1_ = 0;
      int lvt_2_1_ = 0;
      Iterator lvt_3_1_ = this.field_135032_a.values().iterator();

      while(lvt_3_1_.hasNext()) {
         String lvt_4_1_ = (String)lvt_3_1_.next();
         int lvt_5_1_ = lvt_4_1_.length();
         lvt_2_1_ += lvt_5_1_;

         for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_; ++lvt_6_1_) {
            if (lvt_4_1_.charAt(lvt_6_1_) >= 256) {
               ++lvt_1_1_;
            }
         }
      }

      float lvt_3_2_ = (float)lvt_1_1_ / (float)lvt_2_1_;
      this.field_135029_d = (double)lvt_3_2_ > 0.1D;
   }

   private void func_135028_a(List<IResource> p_135028_1_) throws IOException {
      Iterator lvt_2_1_ = p_135028_1_.iterator();

      while(lvt_2_1_.hasNext()) {
         IResource lvt_3_1_ = (IResource)lvt_2_1_.next();
         InputStream lvt_4_1_ = lvt_3_1_.func_110527_b();

         try {
            this.func_135021_a(lvt_4_1_);
         } finally {
            IOUtils.closeQuietly(lvt_4_1_);
         }
      }

   }

   private void func_135021_a(InputStream p_135021_1_) throws IOException {
      Iterator lvt_2_1_ = IOUtils.readLines(p_135021_1_, Charsets.UTF_8).iterator();

      while(lvt_2_1_.hasNext()) {
         String lvt_3_1_ = (String)lvt_2_1_.next();
         if (!lvt_3_1_.isEmpty() && lvt_3_1_.charAt(0) != '#') {
            String[] lvt_4_1_ = (String[])Iterables.toArray(field_135030_b.split(lvt_3_1_), String.class);
            if (lvt_4_1_ != null && lvt_4_1_.length == 2) {
               String lvt_5_1_ = lvt_4_1_[0];
               String lvt_6_1_ = field_135031_c.matcher(lvt_4_1_[1]).replaceAll("%$1s");
               this.field_135032_a.put(lvt_5_1_, lvt_6_1_);
            }
         }
      }

   }

   private String func_135026_c(String p_135026_1_) {
      String lvt_2_1_ = (String)this.field_135032_a.get(p_135026_1_);
      return lvt_2_1_ == null ? p_135026_1_ : lvt_2_1_;
   }

   public String func_135023_a(String p_135023_1_, Object[] p_135023_2_) {
      String lvt_3_1_ = this.func_135026_c(p_135023_1_);

      try {
         return String.format(lvt_3_1_, p_135023_2_);
      } catch (IllegalFormatException var5) {
         return "Format error: " + lvt_3_1_;
      }
   }
}
