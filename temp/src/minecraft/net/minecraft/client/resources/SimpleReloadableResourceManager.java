package net.minecraft.client.resources;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleReloadableResourceManager implements IReloadableResourceManager {
   private static final Logger field_147967_a = LogManager.getLogger();
   private static final Joiner field_130074_a = Joiner.on(", ");
   private final Map<String, FallbackResourceManager> field_110548_a = Maps.newHashMap();
   private final List<IResourceManagerReloadListener> field_110546_b = Lists.newArrayList();
   private final Set<String> field_135057_d = Sets.newLinkedHashSet();
   private final IMetadataSerializer field_110547_c;

   public SimpleReloadableResourceManager(IMetadataSerializer p_i1299_1_) {
      this.field_110547_c = p_i1299_1_;
   }

   public void func_110545_a(IResourcePack p_110545_1_) {
      FallbackResourceManager lvt_4_1_;
      for(Iterator lvt_2_1_ = p_110545_1_.func_110587_b().iterator(); lvt_2_1_.hasNext(); lvt_4_1_.func_110538_a(p_110545_1_)) {
         String lvt_3_1_ = (String)lvt_2_1_.next();
         this.field_135057_d.add(lvt_3_1_);
         lvt_4_1_ = (FallbackResourceManager)this.field_110548_a.get(lvt_3_1_);
         if (lvt_4_1_ == null) {
            lvt_4_1_ = new FallbackResourceManager(this.field_110547_c);
            this.field_110548_a.put(lvt_3_1_, lvt_4_1_);
         }
      }

   }

   public Set<String> func_135055_a() {
      return this.field_135057_d;
   }

   public IResource func_110536_a(ResourceLocation p_110536_1_) throws IOException {
      IResourceManager lvt_2_1_ = (IResourceManager)this.field_110548_a.get(p_110536_1_.func_110624_b());
      if (lvt_2_1_ != null) {
         return lvt_2_1_.func_110536_a(p_110536_1_);
      } else {
         throw new FileNotFoundException(p_110536_1_.toString());
      }
   }

   public List<IResource> func_135056_b(ResourceLocation p_135056_1_) throws IOException {
      IResourceManager lvt_2_1_ = (IResourceManager)this.field_110548_a.get(p_135056_1_.func_110624_b());
      if (lvt_2_1_ != null) {
         return lvt_2_1_.func_135056_b(p_135056_1_);
      } else {
         throw new FileNotFoundException(p_135056_1_.toString());
      }
   }

   private void func_110543_a() {
      this.field_110548_a.clear();
      this.field_135057_d.clear();
   }

   public void func_110541_a(List<IResourcePack> p_110541_1_) {
      this.func_110543_a();
      field_147967_a.info("Reloading ResourceManager: " + field_130074_a.join(Iterables.transform(p_110541_1_, new Function<IResourcePack, String>() {
         public String apply(IResourcePack p_apply_1_) {
            return p_apply_1_.func_130077_b();
         }
      })));
      Iterator lvt_2_1_ = p_110541_1_.iterator();

      while(lvt_2_1_.hasNext()) {
         IResourcePack lvt_3_1_ = (IResourcePack)lvt_2_1_.next();
         this.func_110545_a(lvt_3_1_);
      }

      this.func_110544_b();
   }

   public void func_110542_a(IResourceManagerReloadListener p_110542_1_) {
      this.field_110546_b.add(p_110542_1_);
      p_110542_1_.func_110549_a(this);
   }

   private void func_110544_b() {
      Iterator lvt_1_1_ = this.field_110546_b.iterator();

      while(lvt_1_1_.hasNext()) {
         IResourceManagerReloadListener lvt_2_1_ = (IResourceManagerReloadListener)lvt_1_1_.next();
         lvt_2_1_.func_110549_a(this);
      }

   }
}
