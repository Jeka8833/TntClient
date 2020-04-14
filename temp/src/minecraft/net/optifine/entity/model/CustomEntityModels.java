package net.optifine.entity.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.anim.ModelResolver;
import net.optifine.entity.model.anim.ModelUpdater;

public class CustomEntityModels {
   private static boolean active = false;
   private static Map<Class, Render> originalEntityRenderMap = null;
   private static Map<Class, TileEntitySpecialRenderer> originalTileEntityRenderMap = null;

   public static void update() {
      Map<Class, Render> entityRenderMap = getEntityRenderMap();
      Map<Class, TileEntitySpecialRenderer> tileEntityRenderMap = getTileEntityRenderMap();
      if (entityRenderMap == null) {
         Config.warn("Entity render map not found, custom entity models are DISABLED.");
      } else if (tileEntityRenderMap == null) {
         Config.warn("Tile entity render map not found, custom entity models are DISABLED.");
      } else {
         active = false;
         entityRenderMap.clear();
         tileEntityRenderMap.clear();
         entityRenderMap.putAll(originalEntityRenderMap);
         tileEntityRenderMap.putAll(originalTileEntityRenderMap);
         if (Config.isCustomEntityModels()) {
            ResourceLocation[] locs = getModelLocations();

            for(int i = 0; i < locs.length; ++i) {
               ResourceLocation loc = locs[i];
               Config.dbg("CustomEntityModel: " + loc.func_110623_a());
               IEntityRenderer rc = parseEntityRender(loc);
               if (rc != null) {
                  Class entityClass = rc.getEntityClass();
                  if (entityClass != null) {
                     if (rc instanceof Render) {
                        entityRenderMap.put(entityClass, (Render)rc);
                     } else if (rc instanceof TileEntitySpecialRenderer) {
                        tileEntityRenderMap.put(entityClass, (TileEntitySpecialRenderer)rc);
                     } else {
                        Config.warn("Unknown renderer type: " + rc.getClass().getName());
                     }

                     active = true;
                  }
               }
            }

         }
      }
   }

   private static Map<Class, Render> getEntityRenderMap() {
      RenderManager rm = Minecraft.func_71410_x().func_175598_ae();
      Map<Class, Render> entityRenderMap = rm.getEntityRenderMap();
      if (entityRenderMap == null) {
         return null;
      } else {
         if (originalEntityRenderMap == null) {
            originalEntityRenderMap = new HashMap(entityRenderMap);
         }

         return entityRenderMap;
      }
   }

   private static Map<Class, TileEntitySpecialRenderer> getTileEntityRenderMap() {
      Map<Class, TileEntitySpecialRenderer> tileEntityRenderMap = TileEntityRendererDispatcher.field_147556_a.field_147559_m;
      if (originalTileEntityRenderMap == null) {
         originalTileEntityRenderMap = new HashMap(tileEntityRenderMap);
      }

      return tileEntityRenderMap;
   }

   private static ResourceLocation[] getModelLocations() {
      String prefix = "optifine/cem/";
      String suffix = ".jem";
      List<ResourceLocation> resourceLocations = new ArrayList();
      String[] names = CustomModelRegistry.getModelNames();

      for(int i = 0; i < names.length; ++i) {
         String name = names[i];
         String path = prefix + name + suffix;
         ResourceLocation loc = new ResourceLocation(path);
         if (Config.hasResource(loc)) {
            resourceLocations.add(loc);
         }
      }

      ResourceLocation[] locs = (ResourceLocation[])((ResourceLocation[])resourceLocations.toArray(new ResourceLocation[resourceLocations.size()]));
      return locs;
   }

   private static IEntityRenderer parseEntityRender(ResourceLocation location) {
      try {
         JsonObject jo = CustomEntityModelParser.loadJson(location);
         IEntityRenderer render = parseEntityRender(jo, location.func_110623_a());
         return render;
      } catch (IOException var3) {
         Config.error("" + var3.getClass().getName() + ": " + var3.getMessage());
         return null;
      } catch (JsonParseException var4) {
         Config.error("" + var4.getClass().getName() + ": " + var4.getMessage());
         return null;
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }
   }

   private static IEntityRenderer parseEntityRender(JsonObject obj, String path) {
      CustomEntityRenderer cer = CustomEntityModelParser.parseEntityRender(obj, path);
      String name = cer.getName();
      ModelAdapter modelAdapter = CustomModelRegistry.getModelAdapter(name);
      checkNull(modelAdapter, "Entity not found: " + name);
      Class entityClass = modelAdapter.getEntityClass();
      checkNull(entityClass, "Entity class not found: " + name);
      IEntityRenderer render = makeEntityRender(modelAdapter, cer);
      if (render == null) {
         return null;
      } else {
         render.setEntityClass(entityClass);
         return render;
      }
   }

   private static IEntityRenderer makeEntityRender(ModelAdapter modelAdapter, CustomEntityRenderer cer) {
      ResourceLocation textureLocation = cer.getTextureLocation();
      CustomModelRenderer[] modelRenderers = cer.getCustomModelRenderers();
      float shadowSize = cer.getShadowSize();
      if (shadowSize < 0.0F) {
         shadowSize = modelAdapter.getShadowSize();
      }

      ModelBase model = modelAdapter.makeModel();
      if (model == null) {
         return null;
      } else {
         ModelResolver mr = new ModelResolver(modelAdapter, model, modelRenderers);
         if (!modifyModel(modelAdapter, model, modelRenderers, mr)) {
            return null;
         } else {
            IEntityRenderer r = modelAdapter.makeEntityRender(model, shadowSize);
            if (r == null) {
               throw new JsonParseException("Entity renderer is null, model: " + modelAdapter.getName() + ", adapter: " + modelAdapter.getClass().getName());
            } else {
               if (textureLocation != null) {
                  r.setLocationTextureCustom(textureLocation);
               }

               return r;
            }
         }
      }
   }

   private static boolean modifyModel(ModelAdapter modelAdapter, ModelBase model, CustomModelRenderer[] modelRenderers, ModelResolver mr) {
      for(int i = 0; i < modelRenderers.length; ++i) {
         CustomModelRenderer cmr = modelRenderers[i];
         if (!modifyModel(modelAdapter, model, cmr, mr)) {
            return false;
         }
      }

      return true;
   }

   private static boolean modifyModel(ModelAdapter modelAdapter, ModelBase model, CustomModelRenderer customModelRenderer, ModelResolver modelResolver) {
      String modelPart = customModelRenderer.getModelPart();
      ModelRenderer parent = modelAdapter.getModelRenderer(model, modelPart);
      if (parent == null) {
         Config.warn("Model part not found: " + modelPart + ", model: " + model);
         return false;
      } else {
         if (!customModelRenderer.isAttach()) {
            if (parent.field_78804_l != null) {
               parent.field_78804_l.clear();
            }

            if (parent.spriteList != null) {
               parent.spriteList.clear();
            }

            if (parent.field_78805_m != null) {
               ModelRenderer[] mrs = modelAdapter.getModelRenderers(model);
               Set<ModelRenderer> setMrs = Collections.newSetFromMap(new IdentityHashMap());
               setMrs.addAll(Arrays.asList(mrs));
               List<ModelRenderer> childModels = parent.field_78805_m;
               Iterator it = childModels.iterator();

               while(it.hasNext()) {
                  ModelRenderer mr = (ModelRenderer)it.next();
                  if (!setMrs.contains(mr)) {
                     it.remove();
                  }
               }
            }
         }

         parent.func_78792_a(customModelRenderer.getModelRenderer());
         ModelUpdater mu = customModelRenderer.getModelUpdater();
         if (mu != null) {
            modelResolver.setThisModelRenderer(customModelRenderer.getModelRenderer());
            modelResolver.setPartModelRenderer(parent);
            if (!mu.initialize(modelResolver)) {
               return false;
            }

            customModelRenderer.getModelRenderer().setModelUpdater(mu);
         }

         return true;
      }
   }

   private static void checkNull(Object obj, String msg) {
      if (obj == null) {
         throw new JsonParseException(msg);
      }
   }

   public static boolean isActive() {
      return active;
   }
}
