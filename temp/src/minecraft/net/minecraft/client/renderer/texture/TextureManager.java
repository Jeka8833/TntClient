package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomGuis;
import net.optifine.EmissiveTextures;
import net.optifine.RandomEntities;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureManager implements ITickable, IResourceManagerReloadListener {
   private static final Logger field_147646_a = LogManager.getLogger();
   private final Map<ResourceLocation, ITextureObject> field_110585_a = Maps.newHashMap();
   private final List<ITickable> field_110583_b = Lists.newArrayList();
   private final Map<String, Integer> field_110584_c = Maps.newHashMap();
   private IResourceManager field_110582_d;

   public TextureManager(IResourceManager p_i1284_1_) {
      this.field_110582_d = p_i1284_1_;
   }

   public void func_110577_a(ResourceLocation p_110577_1_) {
      if (Config.isRandomEntities()) {
         p_110577_1_ = RandomEntities.getTextureLocation(p_110577_1_);
      }

      if (Config.isCustomGuis()) {
         p_110577_1_ = CustomGuis.getTextureLocation(p_110577_1_);
      }

      ITextureObject itextureobject = (ITextureObject)this.field_110585_a.get(p_110577_1_);
      if (EmissiveTextures.isActive()) {
         itextureobject = EmissiveTextures.getEmissiveTexture((ITextureObject)itextureobject, this.field_110585_a);
      }

      if (itextureobject == null) {
         itextureobject = new SimpleTexture(p_110577_1_);
         this.func_110579_a(p_110577_1_, (ITextureObject)itextureobject);
      }

      if (Config.isShaders()) {
         ShadersTex.bindTexture((ITextureObject)itextureobject);
      } else {
         TextureUtil.func_94277_a(((ITextureObject)itextureobject).func_110552_b());
      }

   }

   public boolean func_110580_a(ResourceLocation p_110580_1_, ITickableTextureObject p_110580_2_) {
      if (this.func_110579_a(p_110580_1_, p_110580_2_)) {
         this.field_110583_b.add(p_110580_2_);
         return true;
      } else {
         return false;
      }
   }

   public boolean func_110579_a(ResourceLocation p_110579_1_, final ITextureObject p_110579_2_) {
      boolean flag = true;

      try {
         ((ITextureObject)p_110579_2_).func_110551_a(this.field_110582_d);
      } catch (IOException var8) {
         field_147646_a.warn((String)("Failed to load texture: " + p_110579_1_), (Throwable)var8);
         p_110579_2_ = TextureUtil.field_111001_a;
         this.field_110585_a.put(p_110579_1_, p_110579_2_);
         flag = false;
      } catch (Throwable var9) {
         CrashReport crashreport = CrashReport.func_85055_a(var9, "Registering texture");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Resource location being registered");
         crashreportcategory.func_71507_a("Resource location", p_110579_1_);
         crashreportcategory.func_71500_a("Texture object class", new Callable<String>() {
            public String call() throws Exception {
               return p_110579_2_.getClass().getName();
            }
         });
         throw new ReportedException(crashreport);
      }

      this.field_110585_a.put(p_110579_1_, p_110579_2_);
      return flag;
   }

   public ITextureObject func_110581_b(ResourceLocation p_110581_1_) {
      return (ITextureObject)this.field_110585_a.get(p_110581_1_);
   }

   public ResourceLocation func_110578_a(String p_110578_1_, DynamicTexture p_110578_2_) {
      if (p_110578_1_.equals("logo")) {
         p_110578_2_ = Config.getMojangLogoTexture(p_110578_2_);
      }

      Integer integer = (Integer)this.field_110584_c.get(p_110578_1_);
      if (integer == null) {
         integer = 1;
      } else {
         integer = integer + 1;
      }

      this.field_110584_c.put(p_110578_1_, integer);
      ResourceLocation resourcelocation = new ResourceLocation(String.format("dynamic/%s_%d", p_110578_1_, integer));
      this.func_110579_a(resourcelocation, p_110578_2_);
      return resourcelocation;
   }

   public void func_110550_d() {
      Iterator i$ = this.field_110583_b.iterator();

      while(i$.hasNext()) {
         ITickable itickable = (ITickable)i$.next();
         itickable.func_110550_d();
      }

   }

   public void func_147645_c(ResourceLocation p_147645_1_) {
      ITextureObject itextureobject = this.func_110581_b(p_147645_1_);
      if (itextureobject != null) {
         this.field_110585_a.remove(p_147645_1_);
         TextureUtil.func_147942_a(itextureobject.func_110552_b());
      }

   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      Config.dbg("*** Reloading textures ***");
      Config.log("Resource packs: " + Config.getResourcePackNames());
      Iterator it = this.field_110585_a.keySet().iterator();

      while(true) {
         ResourceLocation loc;
         String path;
         do {
            if (!it.hasNext()) {
               EmissiveTextures.update();
               Set<Entry<ResourceLocation, ITextureObject>> entries = new HashSet(this.field_110585_a.entrySet());
               Iterator i$ = entries.iterator();

               while(i$.hasNext()) {
                  Entry<ResourceLocation, ITextureObject> entry = (Entry)i$.next();
                  this.func_110579_a((ResourceLocation)entry.getKey(), (ITextureObject)entry.getValue());
               }

               return;
            }

            loc = (ResourceLocation)it.next();
            path = loc.func_110623_a();
         } while(!path.startsWith("mcpatcher/") && !path.startsWith("optifine/") && !EmissiveTextures.isEmissive(loc));

         ITextureObject tex = (ITextureObject)this.field_110585_a.get(loc);
         if (tex instanceof AbstractTexture) {
            AbstractTexture at = (AbstractTexture)tex;
            at.func_147631_c();
         }

         it.remove();
      }
   }

   public void reloadBannerTextures() {
      Set<Entry<ResourceLocation, ITextureObject>> entries = new HashSet(this.field_110585_a.entrySet());
      Iterator i$ = entries.iterator();

      while(i$.hasNext()) {
         Entry<ResourceLocation, ITextureObject> entry = (Entry)i$.next();
         ResourceLocation loc = (ResourceLocation)entry.getKey();
         ITextureObject tex = (ITextureObject)entry.getValue();
         if (tex instanceof LayeredColorMaskTexture) {
            this.func_110579_a(loc, tex);
         }
      }

   }
}