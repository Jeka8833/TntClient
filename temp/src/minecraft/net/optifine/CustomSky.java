package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.optifine.render.Blender;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.TextureUtils;

public class CustomSky {
   private static CustomSkyLayer[][] worldSkyLayers = (CustomSkyLayer[][])null;

   public static void reset() {
      worldSkyLayers = (CustomSkyLayer[][])null;
   }

   public static void update() {
      reset();
      if (Config.isCustomSky()) {
         worldSkyLayers = readCustomSkies();
      }
   }

   private static CustomSkyLayer[][] readCustomSkies() {
      CustomSkyLayer[][] wsls = new CustomSkyLayer[10][0];
      String prefix = "mcpatcher/sky/world";
      int lastWorldId = -1;

      int w;
      for(w = 0; w < wsls.length; ++w) {
         String worldPrefix = prefix + w + "/sky";
         List listSkyLayers = new ArrayList();

         for(int i = 1; i < 1000; ++i) {
            String path = worldPrefix + i + ".properties";

            try {
               ResourceLocation locPath = new ResourceLocation(path);
               InputStream in = Config.getResourceStream(locPath);
               if (in == null) {
                  break;
               }

               Properties props = new PropertiesOrdered();
               props.load(in);
               in.close();
               Config.dbg("CustomSky properties: " + path);
               String defSource = worldPrefix + i + ".png";
               CustomSkyLayer sl = new CustomSkyLayer(props, defSource);
               if (sl.isValid(path)) {
                  ResourceLocation locSource = new ResourceLocation(sl.source);
                  ITextureObject tex = TextureUtils.getTexture(locSource);
                  if (tex == null) {
                     Config.log("CustomSky: Texture not found: " + locSource);
                  } else {
                     sl.textureId = tex.func_110552_b();
                     listSkyLayers.add(sl);
                     in.close();
                  }
               }
            } catch (FileNotFoundException var15) {
               break;
            } catch (IOException var16) {
               var16.printStackTrace();
            }
         }

         if (listSkyLayers.size() > 0) {
            CustomSkyLayer[] sls = (CustomSkyLayer[])((CustomSkyLayer[])listSkyLayers.toArray(new CustomSkyLayer[listSkyLayers.size()]));
            wsls[w] = sls;
            lastWorldId = w;
         }
      }

      if (lastWorldId < 0) {
         return (CustomSkyLayer[][])null;
      } else {
         w = lastWorldId + 1;
         CustomSkyLayer[][] wslsTrim = new CustomSkyLayer[w][0];

         for(int i = 0; i < wslsTrim.length; ++i) {
            wslsTrim[i] = wsls[i];
         }

         return wslsTrim;
      }
   }

   public static void renderSky(World world, TextureManager re, float partialTicks) {
      if (worldSkyLayers != null) {
         int dimId = world.field_73011_w.func_177502_q();
         if (dimId >= 0 && dimId < worldSkyLayers.length) {
            CustomSkyLayer[] sls = worldSkyLayers[dimId];
            if (sls != null) {
               long time = world.func_72820_D();
               int timeOfDay = (int)(time % 24000L);
               float celestialAngle = world.func_72826_c(partialTicks);
               float rainStrength = world.func_72867_j(partialTicks);
               float thunderStrength = world.func_72819_i(partialTicks);
               if (rainStrength > 0.0F) {
                  thunderStrength /= rainStrength;
               }

               for(int i = 0; i < sls.length; ++i) {
                  CustomSkyLayer sl = sls[i];
                  if (sl.isActive(world, timeOfDay)) {
                     sl.render(world, timeOfDay, celestialAngle, rainStrength, thunderStrength);
                  }
               }

               float rainBrightness = 1.0F - rainStrength;
               Blender.clearBlend(rainBrightness);
            }
         }
      }
   }

   public static boolean hasSkyLayers(World world) {
      if (worldSkyLayers == null) {
         return false;
      } else {
         int dimId = world.field_73011_w.func_177502_q();
         if (dimId >= 0 && dimId < worldSkyLayers.length) {
            CustomSkyLayer[] sls = worldSkyLayers[dimId];
            if (sls == null) {
               return false;
            } else {
               return sls.length > 0;
            }
         } else {
            return false;
         }
      }
   }
}
