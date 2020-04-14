package net.minecraft.client.resources;

import com.google.common.collect.Lists;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FallbackResourceManager implements IResourceManager {
   private static final Logger field_177246_b = LogManager.getLogger();
   protected final List<IResourcePack> field_110540_a = Lists.newArrayList();
   private final IMetadataSerializer field_110539_b;

   public FallbackResourceManager(IMetadataSerializer p_i1289_1_) {
      this.field_110539_b = p_i1289_1_;
   }

   public void func_110538_a(IResourcePack p_110538_1_) {
      this.field_110540_a.add(p_110538_1_);
   }

   public Set<String> func_135055_a() {
      return null;
   }

   public IResource func_110536_a(ResourceLocation p_110536_1_) throws IOException {
      IResourcePack lvt_2_1_ = null;
      ResourceLocation lvt_3_1_ = func_110537_b(p_110536_1_);

      for(int lvt_4_1_ = this.field_110540_a.size() - 1; lvt_4_1_ >= 0; --lvt_4_1_) {
         IResourcePack lvt_5_1_ = (IResourcePack)this.field_110540_a.get(lvt_4_1_);
         if (lvt_2_1_ == null && lvt_5_1_.func_110589_b(lvt_3_1_)) {
            lvt_2_1_ = lvt_5_1_;
         }

         if (lvt_5_1_.func_110589_b(p_110536_1_)) {
            InputStream lvt_6_1_ = null;
            if (lvt_2_1_ != null) {
               lvt_6_1_ = this.func_177245_a(lvt_3_1_, lvt_2_1_);
            }

            return new SimpleResource(lvt_5_1_.func_130077_b(), p_110536_1_, this.func_177245_a(p_110536_1_, lvt_5_1_), lvt_6_1_, this.field_110539_b);
         }
      }

      throw new FileNotFoundException(p_110536_1_.toString());
   }

   protected InputStream func_177245_a(ResourceLocation p_177245_1_, IResourcePack p_177245_2_) throws IOException {
      InputStream lvt_3_1_ = p_177245_2_.func_110590_a(p_177245_1_);
      return (InputStream)(field_177246_b.isDebugEnabled() ? new FallbackResourceManager.InputStreamLeakedResourceLogger(lvt_3_1_, p_177245_1_, p_177245_2_.func_130077_b()) : lvt_3_1_);
   }

   public List<IResource> func_135056_b(ResourceLocation p_135056_1_) throws IOException {
      List<IResource> lvt_2_1_ = Lists.newArrayList();
      ResourceLocation lvt_3_1_ = func_110537_b(p_135056_1_);
      Iterator lvt_4_1_ = this.field_110540_a.iterator();

      while(lvt_4_1_.hasNext()) {
         IResourcePack lvt_5_1_ = (IResourcePack)lvt_4_1_.next();
         if (lvt_5_1_.func_110589_b(p_135056_1_)) {
            InputStream lvt_6_1_ = lvt_5_1_.func_110589_b(lvt_3_1_) ? this.func_177245_a(lvt_3_1_, lvt_5_1_) : null;
            lvt_2_1_.add(new SimpleResource(lvt_5_1_.func_130077_b(), p_135056_1_, this.func_177245_a(p_135056_1_, lvt_5_1_), lvt_6_1_, this.field_110539_b));
         }
      }

      if (lvt_2_1_.isEmpty()) {
         throw new FileNotFoundException(p_135056_1_.toString());
      } else {
         return lvt_2_1_;
      }
   }

   static ResourceLocation func_110537_b(ResourceLocation p_110537_0_) {
      return new ResourceLocation(p_110537_0_.func_110624_b(), p_110537_0_.func_110623_a() + ".mcmeta");
   }

   static class InputStreamLeakedResourceLogger extends InputStream {
      private final InputStream field_177330_a;
      private final String field_177328_b;
      private boolean field_177329_c = false;

      public InputStreamLeakedResourceLogger(InputStream p_i46093_1_, ResourceLocation p_i46093_2_, String p_i46093_3_) {
         this.field_177330_a = p_i46093_1_;
         ByteArrayOutputStream lvt_4_1_ = new ByteArrayOutputStream();
         (new Exception()).printStackTrace(new PrintStream(lvt_4_1_));
         this.field_177328_b = "Leaked resource: '" + p_i46093_2_ + "' loaded from pack: '" + p_i46093_3_ + "'\n" + lvt_4_1_.toString();
      }

      public void close() throws IOException {
         this.field_177330_a.close();
         this.field_177329_c = true;
      }

      protected void finalize() throws Throwable {
         if (!this.field_177329_c) {
            FallbackResourceManager.field_177246_b.warn(this.field_177328_b);
         }

         super.finalize();
      }

      public int read() throws IOException {
         return this.field_177330_a.read();
      }
   }
}
