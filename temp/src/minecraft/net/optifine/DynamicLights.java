package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.optifine.config.ConnectedParser;
import net.optifine.config.EntityClassLocator;
import net.optifine.config.IObjectLocator;
import net.optifine.config.ItemLocator;
import net.optifine.reflect.ReflectorForge;
import net.optifine.util.PropertiesOrdered;

public class DynamicLights {
   private static DynamicLightsMap mapDynamicLights = new DynamicLightsMap();
   private static Map<Class, Integer> mapEntityLightLevels = new HashMap();
   private static Map<Item, Integer> mapItemLightLevels = new HashMap();
   private static long timeUpdateMs = 0L;
   private static final double MAX_DIST = 7.5D;
   private static final double MAX_DIST_SQ = 56.25D;
   private static final int LIGHT_LEVEL_MAX = 15;
   private static final int LIGHT_LEVEL_FIRE = 15;
   private static final int LIGHT_LEVEL_BLAZE = 10;
   private static final int LIGHT_LEVEL_MAGMA_CUBE = 8;
   private static final int LIGHT_LEVEL_MAGMA_CUBE_CORE = 13;
   private static final int LIGHT_LEVEL_GLOWSTONE_DUST = 8;
   private static final int LIGHT_LEVEL_PRISMARINE_CRYSTALS = 8;
   private static boolean initialized;

   public static void entityAdded(Entity entityIn, RenderGlobal renderGlobal) {
   }

   public static void entityRemoved(Entity entityIn, RenderGlobal renderGlobal) {
      synchronized(mapDynamicLights) {
         DynamicLight dynamicLight = mapDynamicLights.remove(entityIn.func_145782_y());
         if (dynamicLight != null) {
            dynamicLight.updateLitChunks(renderGlobal);
         }

      }
   }

   public static void update(RenderGlobal renderGlobal) {
      long timeNowMs = System.currentTimeMillis();
      if (timeNowMs >= timeUpdateMs + 50L) {
         timeUpdateMs = timeNowMs;
         if (!initialized) {
            initialize();
         }

         synchronized(mapDynamicLights) {
            updateMapDynamicLights(renderGlobal);
            if (mapDynamicLights.size() > 0) {
               List<DynamicLight> dynamicLights = mapDynamicLights.valueList();

               for(int i = 0; i < dynamicLights.size(); ++i) {
                  DynamicLight dynamicLight = (DynamicLight)dynamicLights.get(i);
                  dynamicLight.update(renderGlobal);
               }

            }
         }
      }
   }

   private static void initialize() {
      initialized = true;
      mapEntityLightLevels.clear();
      mapItemLightLevels.clear();
      String[] modIds = ReflectorForge.getForgeModIds();

      for(int i = 0; i < modIds.length; ++i) {
         String modId = modIds[i];

         try {
            ResourceLocation loc = new ResourceLocation(modId, "optifine/dynamic_lights.properties");
            InputStream in = Config.getResourceStream(loc);
            loadModConfiguration(in, loc.toString(), modId);
         } catch (IOException var5) {
         }
      }

      if (mapEntityLightLevels.size() > 0) {
         Config.dbg("DynamicLights entities: " + mapEntityLightLevels.size());
      }

      if (mapItemLightLevels.size() > 0) {
         Config.dbg("DynamicLights items: " + mapItemLightLevels.size());
      }

   }

   private static void loadModConfiguration(InputStream in, String path, String modId) {
      if (in != null) {
         try {
            Properties props = new PropertiesOrdered();
            props.load(in);
            in.close();
            Config.dbg("DynamicLights: Parsing " + path);
            ConnectedParser cp = new ConnectedParser("DynamicLights");
            loadModLightLevels(props.getProperty("entities"), mapEntityLightLevels, new EntityClassLocator(), cp, path, modId);
            loadModLightLevels(props.getProperty("items"), mapItemLightLevels, new ItemLocator(), cp, path, modId);
         } catch (IOException var5) {
            Config.warn("DynamicLights: Error reading " + path);
         }

      }
   }

   private static void loadModLightLevels(String prop, Map mapLightLevels, IObjectLocator ol, ConnectedParser cp, String path, String modId) {
      if (prop != null) {
         String[] parts = Config.tokenize(prop, " ");

         for(int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            String[] tokens = Config.tokenize(part, ":");
            if (tokens.length != 2) {
               cp.warn("Invalid entry: " + part + ", in:" + path);
            } else {
               String name = tokens[0];
               String light = tokens[1];
               String nameFull = modId + ":" + name;
               ResourceLocation loc = new ResourceLocation(nameFull);
               Object obj = ol.getObject(loc);
               if (obj == null) {
                  cp.warn("Object not found: " + nameFull);
               } else {
                  int lightLevel = cp.parseInt(light, -1);
                  if (lightLevel >= 0 && lightLevel <= 15) {
                     mapLightLevels.put(obj, new Integer(lightLevel));
                  } else {
                     cp.warn("Invalid light level: " + part);
                  }
               }
            }
         }

      }
   }

   private static void updateMapDynamicLights(RenderGlobal renderGlobal) {
      World world = renderGlobal.getWorld();
      if (world != null) {
         List<Entity> entities = world.func_72910_y();
         Iterator it = entities.iterator();

         while(it.hasNext()) {
            Entity entity = (Entity)it.next();
            int lightLevel = getLightLevel(entity);
            int key;
            DynamicLight dynamicLight;
            if (lightLevel > 0) {
               key = entity.func_145782_y();
               dynamicLight = mapDynamicLights.get(key);
               if (dynamicLight == null) {
                  dynamicLight = new DynamicLight(entity);
                  mapDynamicLights.put(key, dynamicLight);
               }
            } else {
               key = entity.func_145782_y();
               dynamicLight = mapDynamicLights.remove(key);
               if (dynamicLight != null) {
                  dynamicLight.updateLitChunks(renderGlobal);
               }
            }
         }

      }
   }

   public static int getCombinedLight(BlockPos pos, int combinedLight) {
      double lightPlayer = getLightLevel(pos);
      combinedLight = getCombinedLight(lightPlayer, combinedLight);
      return combinedLight;
   }

   public static int getCombinedLight(Entity entity, int combinedLight) {
      double lightPlayer = (double)getLightLevel(entity);
      combinedLight = getCombinedLight(lightPlayer, combinedLight);
      return combinedLight;
   }

   public static int getCombinedLight(double lightPlayer, int combinedLight) {
      if (lightPlayer > 0.0D) {
         int lightPlayerFF = (int)(lightPlayer * 16.0D);
         int lightBlockFF = combinedLight & 255;
         if (lightPlayerFF > lightBlockFF) {
            combinedLight &= -256;
            combinedLight |= lightPlayerFF;
         }
      }

      return combinedLight;
   }

   public static double getLightLevel(BlockPos pos) {
      double lightLevelMax = 0.0D;
      synchronized(mapDynamicLights) {
         List<DynamicLight> dynamicLights = mapDynamicLights.valueList();

         for(int i = 0; i < dynamicLights.size(); ++i) {
            DynamicLight dynamicLight = (DynamicLight)dynamicLights.get(i);
            int dynamicLightLevel = dynamicLight.getLastLightLevel();
            if (dynamicLightLevel > 0) {
               double px = dynamicLight.getLastPosX();
               double py = dynamicLight.getLastPosY();
               double pz = dynamicLight.getLastPosZ();
               double dx = (double)pos.func_177958_n() - px;
               double dy = (double)pos.func_177956_o() - py;
               double dz = (double)pos.func_177952_p() - pz;
               double distSq = dx * dx + dy * dy + dz * dz;
               if (dynamicLight.isUnderwater() && !Config.isClearWater()) {
                  dynamicLightLevel = Config.limit(dynamicLightLevel - 2, 0, 15);
                  distSq *= 2.0D;
               }

               if (distSq <= 56.25D) {
                  double dist = Math.sqrt(distSq);
                  double light = 1.0D - dist / 7.5D;
                  double lightLevel = light * (double)dynamicLightLevel;
                  if (lightLevel > lightLevelMax) {
                     lightLevelMax = lightLevel;
                  }
               }
            }
         }
      }

      double lightPlayer = Config.limit(lightLevelMax, 0.0D, 15.0D);
      return lightPlayer;
   }

   public static int getLightLevel(ItemStack itemStack) {
      if (itemStack == null) {
         return 0;
      } else {
         Item item = itemStack.func_77973_b();
         if (item instanceof ItemBlock) {
            ItemBlock itemBlock = (ItemBlock)item;
            Block block = itemBlock.func_179223_d();
            if (block != null) {
               return block.func_149750_m();
            }
         }

         if (item == Items.field_151129_at) {
            return Blocks.field_150353_l.func_149750_m();
         } else if (item != Items.field_151072_bj && item != Items.field_151065_br) {
            if (item == Items.field_151114_aO) {
               return 8;
            } else if (item == Items.field_179563_cD) {
               return 8;
            } else if (item == Items.field_151064_bs) {
               return 8;
            } else if (item == Items.field_151156_bN) {
               return Blocks.field_150461_bJ.func_149750_m() / 2;
            } else {
               if (!mapItemLightLevels.isEmpty()) {
                  Integer level = (Integer)mapItemLightLevels.get(item);
                  if (level != null) {
                     return level;
                  }
               }

               return 0;
            }
         } else {
            return 10;
         }
      }
   }

   public static int getLightLevel(Entity entity) {
      if (entity == Config.getMinecraft().func_175606_aa() && !Config.isDynamicHandLight()) {
         return 0;
      } else {
         if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (player.func_175149_v()) {
               return 0;
            }
         }

         if (entity.func_70027_ad()) {
            return 15;
         } else {
            if (!mapEntityLightLevels.isEmpty()) {
               Integer level = (Integer)mapEntityLightLevels.get(entity.getClass());
               if (level != null) {
                  return level;
               }
            }

            if (entity instanceof EntityFireball) {
               return 15;
            } else if (entity instanceof EntityTNTPrimed) {
               return 15;
            } else if (entity instanceof EntityBlaze) {
               EntityBlaze entityBlaze = (EntityBlaze)entity;
               return entityBlaze.func_70845_n() ? 15 : 10;
            } else if (entity instanceof EntityMagmaCube) {
               EntityMagmaCube emc = (EntityMagmaCube)entity;
               return (double)emc.field_70811_b > 0.6D ? 13 : 8;
            } else {
               if (entity instanceof EntityCreeper) {
                  EntityCreeper entityCreeper = (EntityCreeper)entity;
                  if ((double)entityCreeper.func_70831_j(0.0F) > 0.001D) {
                     return 15;
                  }
               }

               ItemStack itemStack;
               if (entity instanceof EntityLivingBase) {
                  EntityLivingBase player = (EntityLivingBase)entity;
                  itemStack = player.func_70694_bm();
                  int levelMain = getLightLevel(itemStack);
                  ItemStack stackHead = player.func_71124_b(4);
                  int levelHead = getLightLevel(stackHead);
                  return Math.max(levelMain, levelHead);
               } else if (entity instanceof EntityItem) {
                  EntityItem entityItem = (EntityItem)entity;
                  itemStack = getItemStack(entityItem);
                  return getLightLevel(itemStack);
               } else {
                  return 0;
               }
            }
         }
      }
   }

   public static void removeLights(RenderGlobal renderGlobal) {
      synchronized(mapDynamicLights) {
         List<DynamicLight> dynamicLights = mapDynamicLights.valueList();

         for(int i = 0; i < dynamicLights.size(); ++i) {
            DynamicLight dynamicLight = (DynamicLight)dynamicLights.get(i);
            dynamicLight.updateLitChunks(renderGlobal);
         }

         mapDynamicLights.clear();
      }
   }

   public static void clear() {
      synchronized(mapDynamicLights) {
         mapDynamicLights.clear();
      }
   }

   public static int getCount() {
      synchronized(mapDynamicLights) {
         return mapDynamicLights.size();
      }
   }

   public static ItemStack getItemStack(EntityItem entityItem) {
      ItemStack itemstack = entityItem.func_70096_w().func_82710_f(10);
      return itemstack;
   }
}
