package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorRaw;
import net.optifine.util.IntegratedServerUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;

public class RandomEntities {
   private static Map<String, RandomEntityProperties> mapProperties = new HashMap();
   private static boolean active = false;
   private static RenderGlobal renderGlobal;
   private static RandomEntity randomEntity = new RandomEntity();
   private static TileEntityRendererDispatcher tileEntityRendererDispatcher;
   private static RandomTileEntity randomTileEntity = new RandomTileEntity();
   private static boolean working = false;
   public static final String SUFFIX_PNG = ".png";
   public static final String SUFFIX_PROPERTIES = ".properties";
   public static final String PREFIX_TEXTURES_ENTITY = "textures/entity/";
   public static final String PREFIX_TEXTURES_PAINTING = "textures/painting/";
   public static final String PREFIX_TEXTURES = "textures/";
   public static final String PREFIX_OPTIFINE_RANDOM = "optifine/random/";
   public static final String PREFIX_MCPATCHER_MOB = "mcpatcher/mob/";
   private static final String[] DEPENDANT_SUFFIXES = new String[]{"_armor", "_eyes", "_exploding", "_shooting", "_fur", "_eyes", "_invulnerable", "_angry", "_tame", "_collar"};
   private static final String PREFIX_DYNAMIC_TEXTURE_HORSE = "horse/";
   private static final String[] HORSE_TEXTURES = (String[])((String[])ReflectorRaw.getFieldValue((Object)null, EntityHorse.class, String[].class, 0));
   private static final String[] HORSE_TEXTURES_ABBR = (String[])((String[])ReflectorRaw.getFieldValue((Object)null, EntityHorse.class, String[].class, 1));

   public static void entityLoaded(Entity entity, World world) {
      if (world != null) {
         DataWatcher edm = entity.func_70096_w();
         edm.spawnPosition = entity.func_180425_c();
         edm.spawnBiome = world.func_180494_b(edm.spawnPosition);
         UUID uuid = entity.func_110124_au();
         if (entity instanceof EntityVillager) {
            updateEntityVillager(uuid, (EntityVillager)entity);
         }

      }
   }

   public static void entityUnloaded(Entity entity, World world) {
   }

   private static void updateEntityVillager(UUID uuid, EntityVillager ev) {
      Entity se = IntegratedServerUtils.getEntity(uuid);
      if (se instanceof EntityVillager) {
         EntityVillager sev = (EntityVillager)se;
         int profSev = sev.func_70946_n();
         ev.func_70938_b(profSev);
         int careerId = Reflector.getFieldValueInt(sev, Reflector.EntityVillager_careerId, 0);
         Reflector.setFieldValueInt(ev, Reflector.EntityVillager_careerId, careerId);
         int careerLevel = Reflector.getFieldValueInt(sev, Reflector.EntityVillager_careerLevel, 0);
         Reflector.setFieldValueInt(ev, Reflector.EntityVillager_careerLevel, careerLevel);
      }
   }

   public static void worldChanged(World oldWorld, World newWorld) {
      if (newWorld != null) {
         List entityList = newWorld.func_72910_y();

         for(int e = 0; e < entityList.size(); ++e) {
            Entity entity = (Entity)entityList.get(e);
            entityLoaded(entity, newWorld);
         }
      }

      randomEntity.setEntity((Entity)null);
      randomTileEntity.setTileEntity((TileEntity)null);
   }

   public static ResourceLocation getTextureLocation(ResourceLocation loc) {
      if (!active) {
         return loc;
      } else if (working) {
         return loc;
      } else {
         ResourceLocation var2;
         try {
            working = true;
            IRandomEntity re = getRandomEntityRendered();
            if (re != null) {
               String name = loc.func_110623_a();
               if (name.startsWith("horse/")) {
                  name = getHorseTexturePath(name, "horse/".length());
               }

               if (!name.startsWith("textures/entity/") && !name.startsWith("textures/painting/")) {
                  ResourceLocation var9 = loc;
                  return var9;
               }

               RandomEntityProperties props = (RandomEntityProperties)mapProperties.get(name);
               ResourceLocation var4;
               if (props == null) {
                  var4 = loc;
                  return var4;
               }

               var4 = props.getTextureLocation(loc, re);
               return var4;
            }

            var2 = loc;
         } finally {
            working = false;
         }

         return var2;
      }
   }

   private static String getHorseTexturePath(String path, int pos) {
      if (HORSE_TEXTURES != null && HORSE_TEXTURES_ABBR != null) {
         for(int i = 0; i < HORSE_TEXTURES_ABBR.length; ++i) {
            String abbr = HORSE_TEXTURES_ABBR[i];
            if (path.startsWith(abbr, pos)) {
               return HORSE_TEXTURES[i];
            }
         }

         return path;
      } else {
         return path;
      }
   }

   private static IRandomEntity getRandomEntityRendered() {
      if (renderGlobal.renderedEntity != null) {
         randomEntity.setEntity(renderGlobal.renderedEntity);
         return randomEntity;
      } else {
         if (tileEntityRendererDispatcher.tileEntityRendered != null) {
            TileEntity te = tileEntityRendererDispatcher.tileEntityRendered;
            if (te.func_145831_w() != null) {
               randomTileEntity.setTileEntity(te);
               return randomTileEntity;
            }
         }

         return null;
      }
   }

   private static RandomEntityProperties makeProperties(ResourceLocation loc, boolean mcpatcher) {
      String path = loc.func_110623_a();
      ResourceLocation locProps = getLocationProperties(loc, mcpatcher);
      if (locProps != null) {
         RandomEntityProperties props = parseProperties(locProps, loc);
         if (props != null) {
            return props;
         }
      }

      ResourceLocation[] variants = getLocationsVariants(loc, mcpatcher);
      return variants == null ? null : new RandomEntityProperties(path, variants);
   }

   private static RandomEntityProperties parseProperties(ResourceLocation propLoc, ResourceLocation resLoc) {
      try {
         String path = propLoc.func_110623_a();
         dbg(resLoc.func_110623_a() + ", properties: " + path);
         InputStream in = Config.getResourceStream(propLoc);
         if (in == null) {
            warn("Properties not found: " + path);
            return null;
         } else {
            Properties props = new PropertiesOrdered();
            props.load(in);
            in.close();
            RandomEntityProperties rmp = new RandomEntityProperties(props, path, resLoc);
            return !rmp.isValid(path) ? null : rmp;
         }
      } catch (FileNotFoundException var6) {
         warn("File not found: " + resLoc.func_110623_a());
         return null;
      } catch (IOException var7) {
         var7.printStackTrace();
         return null;
      }
   }

   private static ResourceLocation getLocationProperties(ResourceLocation loc, boolean mcpatcher) {
      ResourceLocation locMcp = getLocationRandom(loc, mcpatcher);
      if (locMcp == null) {
         return null;
      } else {
         String domain = locMcp.func_110624_b();
         String path = locMcp.func_110623_a();
         String pathBase = StrUtils.removeSuffix(path, ".png");
         String pathProps = pathBase + ".properties";
         ResourceLocation locProps = new ResourceLocation(domain, pathProps);
         if (Config.hasResource(locProps)) {
            return locProps;
         } else {
            String pathParent = getParentTexturePath(pathBase);
            if (pathParent == null) {
               return null;
            } else {
               ResourceLocation locParentProps = new ResourceLocation(domain, pathParent + ".properties");
               return Config.hasResource(locParentProps) ? locParentProps : null;
            }
         }
      }
   }

   protected static ResourceLocation getLocationRandom(ResourceLocation loc, boolean mcpatcher) {
      String domain = loc.func_110624_b();
      String path = loc.func_110623_a();
      String prefixTextures = "textures/";
      String prefixRandom = "optifine/random/";
      if (mcpatcher) {
         prefixTextures = "textures/entity/";
         prefixRandom = "mcpatcher/mob/";
      }

      if (!path.startsWith(prefixTextures)) {
         return null;
      } else {
         String pathRandom = StrUtils.replacePrefix(path, prefixTextures, prefixRandom);
         return new ResourceLocation(domain, pathRandom);
      }
   }

   private static String getPathBase(String pathRandom) {
      if (pathRandom.startsWith("optifine/random/")) {
         return StrUtils.replacePrefix(pathRandom, "optifine/random/", "textures/");
      } else {
         return pathRandom.startsWith("mcpatcher/mob/") ? StrUtils.replacePrefix(pathRandom, "mcpatcher/mob/", "textures/entity/") : null;
      }
   }

   protected static ResourceLocation getLocationIndexed(ResourceLocation loc, int index) {
      if (loc == null) {
         return null;
      } else {
         String path = loc.func_110623_a();
         int pos = path.lastIndexOf(46);
         if (pos < 0) {
            return null;
         } else {
            String prefix = path.substring(0, pos);
            String suffix = path.substring(pos);
            String pathNew = prefix + index + suffix;
            ResourceLocation locNew = new ResourceLocation(loc.func_110624_b(), pathNew);
            return locNew;
         }
      }
   }

   private static String getParentTexturePath(String path) {
      for(int i = 0; i < DEPENDANT_SUFFIXES.length; ++i) {
         String suffix = DEPENDANT_SUFFIXES[i];
         if (path.endsWith(suffix)) {
            String pathParent = StrUtils.removeSuffix(path, suffix);
            return pathParent;
         }
      }

      return null;
   }

   private static ResourceLocation[] getLocationsVariants(ResourceLocation loc, boolean mcpatcher) {
      List list = new ArrayList();
      list.add(loc);
      ResourceLocation locRandom = getLocationRandom(loc, mcpatcher);
      if (locRandom == null) {
         return null;
      } else {
         for(int i = 1; i < list.size() + 10; ++i) {
            int index = i + 1;
            ResourceLocation locIndex = getLocationIndexed(locRandom, index);
            if (Config.hasResource(locIndex)) {
               list.add(locIndex);
            }
         }

         if (list.size() <= 1) {
            return null;
         } else {
            ResourceLocation[] locs = (ResourceLocation[])((ResourceLocation[])list.toArray(new ResourceLocation[list.size()]));
            dbg(loc.func_110623_a() + ", variants: " + locs.length);
            return locs;
         }
      }
   }

   public static void update() {
      mapProperties.clear();
      active = false;
      if (Config.isRandomEntities()) {
         initialize();
      }
   }

   private static void initialize() {
      renderGlobal = Config.getRenderGlobal();
      tileEntityRendererDispatcher = TileEntityRendererDispatcher.field_147556_a;
      String[] prefixes = new String[]{"optifine/random/", "mcpatcher/mob/"};
      String[] suffixes = new String[]{".png", ".properties"};
      String[] pathsRandom = ResUtils.collectFiles(prefixes, suffixes);
      Set basePathsChecked = new HashSet();

      for(int i = 0; i < pathsRandom.length; ++i) {
         String path = pathsRandom[i];
         path = StrUtils.removeSuffix(path, suffixes);
         path = StrUtils.trimTrailing(path, "0123456789");
         path = path + ".png";
         String pathBase = getPathBase(path);
         if (!basePathsChecked.contains(pathBase)) {
            basePathsChecked.add(pathBase);
            ResourceLocation locBase = new ResourceLocation(pathBase);
            if (Config.hasResource(locBase)) {
               RandomEntityProperties props = (RandomEntityProperties)mapProperties.get(pathBase);
               if (props == null) {
                  props = makeProperties(locBase, false);
                  if (props == null) {
                     props = makeProperties(locBase, true);
                  }

                  if (props != null) {
                     mapProperties.put(pathBase, props);
                  }
               }
            }
         }
      }

      active = !mapProperties.isEmpty();
   }

   public static void dbg(String str) {
      Config.dbg("RandomEntities: " + str);
   }

   public static void warn(String str) {
      Config.warn("RandomEntities: " + str);
   }
}
