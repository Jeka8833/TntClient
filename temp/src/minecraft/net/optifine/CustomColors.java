package net.optifine;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStem;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.reflect.Reflector;
import net.optifine.render.RenderEnv;
import net.optifine.util.EntityUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class CustomColors {
   private static String paletteFormatDefault = "vanilla";
   private static CustomColormap waterColors = null;
   private static CustomColormap foliagePineColors = null;
   private static CustomColormap foliageBirchColors = null;
   private static CustomColormap swampFoliageColors = null;
   private static CustomColormap swampGrassColors = null;
   private static CustomColormap[] colorsBlockColormaps = null;
   private static CustomColormap[][] blockColormaps = (CustomColormap[][])null;
   private static CustomColormap skyColors = null;
   private static CustomColorFader skyColorFader = new CustomColorFader();
   private static CustomColormap fogColors = null;
   private static CustomColorFader fogColorFader = new CustomColorFader();
   private static CustomColormap underwaterColors = null;
   private static CustomColorFader underwaterColorFader = new CustomColorFader();
   private static CustomColormap underlavaColors = null;
   private static CustomColorFader underlavaColorFader = new CustomColorFader();
   private static LightMapPack[] lightMapPacks = null;
   private static int lightmapMinDimensionId = 0;
   private static CustomColormap redstoneColors = null;
   private static CustomColormap xpOrbColors = null;
   private static int xpOrbTime = -1;
   private static CustomColormap durabilityColors = null;
   private static CustomColormap stemColors = null;
   private static CustomColormap stemMelonColors = null;
   private static CustomColormap stemPumpkinColors = null;
   private static CustomColormap myceliumParticleColors = null;
   private static boolean useDefaultGrassFoliageColors = true;
   private static int particleWaterColor = -1;
   private static int particlePortalColor = -1;
   private static int lilyPadColor = -1;
   private static int expBarTextColor = -1;
   private static int bossTextColor = -1;
   private static int signTextColor = -1;
   private static Vec3 fogColorNether = null;
   private static Vec3 fogColorEnd = null;
   private static Vec3 skyColorEnd = null;
   private static int[] spawnEggPrimaryColors = null;
   private static int[] spawnEggSecondaryColors = null;
   private static float[][] wolfCollarColors = (float[][])null;
   private static float[][] sheepColors = (float[][])null;
   private static int[] textColors = null;
   private static int[] mapColorsOriginal = null;
   private static int[] potionColors = null;
   private static final IBlockState BLOCK_STATE_DIRT;
   private static final IBlockState BLOCK_STATE_WATER;
   public static Random random;
   private static final CustomColors.IColorizer COLORIZER_GRASS;
   private static final CustomColors.IColorizer COLORIZER_FOLIAGE;
   private static final CustomColors.IColorizer COLORIZER_FOLIAGE_PINE;
   private static final CustomColors.IColorizer COLORIZER_FOLIAGE_BIRCH;
   private static final CustomColors.IColorizer COLORIZER_WATER;

   public static void update() {
      paletteFormatDefault = "vanilla";
      waterColors = null;
      foliageBirchColors = null;
      foliagePineColors = null;
      swampGrassColors = null;
      swampFoliageColors = null;
      skyColors = null;
      fogColors = null;
      underwaterColors = null;
      underlavaColors = null;
      redstoneColors = null;
      xpOrbColors = null;
      xpOrbTime = -1;
      durabilityColors = null;
      stemColors = null;
      myceliumParticleColors = null;
      lightMapPacks = null;
      particleWaterColor = -1;
      particlePortalColor = -1;
      lilyPadColor = -1;
      expBarTextColor = -1;
      bossTextColor = -1;
      signTextColor = -1;
      fogColorNether = null;
      fogColorEnd = null;
      skyColorEnd = null;
      colorsBlockColormaps = null;
      blockColormaps = (CustomColormap[][])null;
      useDefaultGrassFoliageColors = true;
      spawnEggPrimaryColors = null;
      spawnEggSecondaryColors = null;
      wolfCollarColors = (float[][])null;
      sheepColors = (float[][])null;
      textColors = null;
      setMapColors(mapColorsOriginal);
      potionColors = null;
      paletteFormatDefault = getValidProperty("mcpatcher/color.properties", "palette.format", CustomColormap.FORMAT_STRINGS, "vanilla");
      String mcpColormap = "mcpatcher/colormap/";
      String[] waterPaths = new String[]{"water.png", "watercolorX.png"};
      waterColors = getCustomColors(mcpColormap, waterPaths, 256, 256);
      updateUseDefaultGrassFoliageColors();
      if (Config.isCustomColors()) {
         String[] pinePaths = new String[]{"pine.png", "pinecolor.png"};
         foliagePineColors = getCustomColors(mcpColormap, pinePaths, 256, 256);
         String[] birchPaths = new String[]{"birch.png", "birchcolor.png"};
         foliageBirchColors = getCustomColors(mcpColormap, birchPaths, 256, 256);
         String[] swampGrassPaths = new String[]{"swampgrass.png", "swampgrasscolor.png"};
         swampGrassColors = getCustomColors(mcpColormap, swampGrassPaths, 256, 256);
         String[] swampFoliagePaths = new String[]{"swampfoliage.png", "swampfoliagecolor.png"};
         swampFoliageColors = getCustomColors(mcpColormap, swampFoliagePaths, 256, 256);
         String[] sky0Paths = new String[]{"sky0.png", "skycolor0.png"};
         skyColors = getCustomColors(mcpColormap, sky0Paths, 256, 256);
         String[] fog0Paths = new String[]{"fog0.png", "fogcolor0.png"};
         fogColors = getCustomColors(mcpColormap, fog0Paths, 256, 256);
         String[] underwaterPaths = new String[]{"underwater.png", "underwatercolor.png"};
         underwaterColors = getCustomColors(mcpColormap, underwaterPaths, 256, 256);
         String[] underlavaPaths = new String[]{"underlava.png", "underlavacolor.png"};
         underlavaColors = getCustomColors(mcpColormap, underlavaPaths, 256, 256);
         String[] redstonePaths = new String[]{"redstone.png", "redstonecolor.png"};
         redstoneColors = getCustomColors(mcpColormap, redstonePaths, 16, 1);
         xpOrbColors = getCustomColors(mcpColormap + "xporb.png", -1, -1);
         durabilityColors = getCustomColors(mcpColormap + "durability.png", -1, -1);
         String[] stemPaths = new String[]{"stem.png", "stemcolor.png"};
         stemColors = getCustomColors(mcpColormap, stemPaths, 8, 1);
         stemPumpkinColors = getCustomColors(mcpColormap + "pumpkinstem.png", 8, 1);
         stemMelonColors = getCustomColors(mcpColormap + "melonstem.png", 8, 1);
         String[] myceliumPaths = new String[]{"myceliumparticle.png", "myceliumparticlecolor.png"};
         myceliumParticleColors = getCustomColors(mcpColormap, myceliumPaths, -1, -1);
         Pair<LightMapPack[], Integer> lightMaps = parseLightMapPacks();
         lightMapPacks = (LightMapPack[])lightMaps.getLeft();
         lightmapMinDimensionId = (Integer)lightMaps.getRight();
         readColorProperties("mcpatcher/color.properties");
         blockColormaps = readBlockColormaps(new String[]{mcpColormap + "custom/", mcpColormap + "blocks/"}, colorsBlockColormaps, 256, 256);
         updateUseDefaultGrassFoliageColors();
      }
   }

   private static String getValidProperty(String fileName, String key, String[] validValues, String valDef) {
      try {
         ResourceLocation loc = new ResourceLocation(fileName);
         InputStream in = Config.getResourceStream(loc);
         if (in == null) {
            return valDef;
         } else {
            Properties props = new PropertiesOrdered();
            props.load(in);
            in.close();
            String val = props.getProperty(key);
            if (val == null) {
               return valDef;
            } else {
               List<String> listValidValues = Arrays.asList(validValues);
               if (!listValidValues.contains(val)) {
                  warn("Invalid value: " + key + "=" + val);
                  warn("Expected values: " + Config.arrayToString((Object[])validValues));
                  return valDef;
               } else {
                  dbg("" + key + "=" + val);
                  return val;
               }
            }
         }
      } catch (FileNotFoundException var9) {
         return valDef;
      } catch (IOException var10) {
         var10.printStackTrace();
         return valDef;
      }
   }

   private static Pair<LightMapPack[], Integer> parseLightMapPacks() {
      String lightmapPrefix = "mcpatcher/lightmap/world";
      String lightmapSuffix = ".png";
      String[] pathsLightmap = ResUtils.collectFiles(lightmapPrefix, lightmapSuffix);
      Map<Integer, String> mapLightmaps = new HashMap();

      int maxDimId;
      for(int i = 0; i < pathsLightmap.length; ++i) {
         String path = pathsLightmap[i];
         String dimIdStr = StrUtils.removePrefixSuffix(path, lightmapPrefix, lightmapSuffix);
         maxDimId = Config.parseInt(dimIdStr, Integer.MIN_VALUE);
         if (maxDimId == Integer.MIN_VALUE) {
            warn("Invalid dimension ID: " + dimIdStr + ", path: " + path);
         } else {
            mapLightmaps.put(maxDimId, path);
         }
      }

      Set<Integer> setDimIds = mapLightmaps.keySet();
      Integer[] dimIds = (Integer[])setDimIds.toArray(new Integer[setDimIds.size()]);
      Arrays.sort(dimIds);
      if (dimIds.length <= 0) {
         return new ImmutablePair((Object)null, 0);
      } else {
         int minDimId = dimIds[0];
         maxDimId = dimIds[dimIds.length - 1];
         int countDim = maxDimId - minDimId + 1;
         CustomColormap[] colormaps = new CustomColormap[countDim];

         for(int i = 0; i < dimIds.length; ++i) {
            Integer dimId = dimIds[i];
            String path = (String)mapLightmaps.get(dimId);
            CustomColormap colors = getCustomColors(path, -1, -1);
            if (colors != null) {
               if (colors.getWidth() < 16) {
                  warn("Invalid lightmap width: " + colors.getWidth() + ", path: " + path);
               } else {
                  int lightmapIndex = dimId - minDimId;
                  colormaps[lightmapIndex] = colors;
               }
            }
         }

         LightMapPack[] lmps = new LightMapPack[colormaps.length];

         for(int i = 0; i < colormaps.length; ++i) {
            CustomColormap cm = colormaps[i];
            if (cm != null) {
               String name = cm.name;
               String basePath = cm.basePath;
               CustomColormap cmRain = getCustomColors(basePath + "/" + name + "_rain.png", -1, -1);
               CustomColormap cmThunder = getCustomColors(basePath + "/" + name + "_thunder.png", -1, -1);
               LightMap lm = new LightMap(cm);
               LightMap lmRain = cmRain != null ? new LightMap(cmRain) : null;
               LightMap lmThunder = cmThunder != null ? new LightMap(cmThunder) : null;
               LightMapPack lmp = new LightMapPack(lm, lmRain, lmThunder);
               lmps[i] = lmp;
            }
         }

         return new ImmutablePair(lmps, minDimId);
      }
   }

   private static int getTextureHeight(String path, int defHeight) {
      try {
         InputStream in = Config.getResourceStream(new ResourceLocation(path));
         if (in == null) {
            return defHeight;
         } else {
            BufferedImage bi = ImageIO.read(in);
            in.close();
            return bi == null ? defHeight : bi.getHeight();
         }
      } catch (IOException var4) {
         return defHeight;
      }
   }

   private static void readColorProperties(String fileName) {
      try {
         ResourceLocation loc = new ResourceLocation(fileName);
         InputStream in = Config.getResourceStream(loc);
         if (in == null) {
            return;
         }

         dbg("Loading " + fileName);
         Properties props = new PropertiesOrdered();
         props.load(in);
         in.close();
         particleWaterColor = readColor(props, (String[])(new String[]{"particle.water", "drop.water"}));
         particlePortalColor = readColor(props, (String)"particle.portal");
         lilyPadColor = readColor(props, (String)"lilypad");
         expBarTextColor = readColor(props, (String)"text.xpbar");
         bossTextColor = readColor(props, (String)"text.boss");
         signTextColor = readColor(props, (String)"text.sign");
         fogColorNether = readColorVec3(props, "fog.nether");
         fogColorEnd = readColorVec3(props, "fog.end");
         skyColorEnd = readColorVec3(props, "sky.end");
         colorsBlockColormaps = readCustomColormaps(props, fileName);
         spawnEggPrimaryColors = readSpawnEggColors(props, fileName, "egg.shell.", "Spawn egg shell");
         spawnEggSecondaryColors = readSpawnEggColors(props, fileName, "egg.spots.", "Spawn egg spot");
         wolfCollarColors = readDyeColors(props, fileName, "collar.", "Wolf collar");
         sheepColors = readDyeColors(props, fileName, "sheep.", "Sheep");
         textColors = readTextColors(props, fileName, "text.code.", "Text");
         int[] mapColors = readMapColors(props, fileName, "map.", "Map");
         if (mapColors != null) {
            if (mapColorsOriginal == null) {
               mapColorsOriginal = getMapColors();
            }

            setMapColors(mapColors);
         }

         potionColors = readPotionColors(props, fileName, "potion.", "Potion");
         xpOrbTime = Config.parseInt(props.getProperty("xporb.time"), -1);
      } catch (FileNotFoundException var5) {
         return;
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }

   private static CustomColormap[] readCustomColormaps(Properties props, String fileName) {
      List list = new ArrayList();
      String palettePrefix = "palette.block.";
      Map map = new HashMap();
      Set keys = props.keySet();
      Iterator iter = keys.iterator();

      String name;
      while(iter.hasNext()) {
         String key = (String)iter.next();
         name = props.getProperty(key);
         if (key.startsWith(palettePrefix)) {
            map.put(key, name);
         }
      }

      String[] propNames = (String[])((String[])map.keySet().toArray(new String[map.size()]));

      for(int i = 0; i < propNames.length; ++i) {
         name = propNames[i];
         String value = props.getProperty(name);
         dbg("Block palette: " + name + " = " + value);
         String path = name.substring(palettePrefix.length());
         String basePath = TextureUtils.getBasePath(fileName);
         path = TextureUtils.fixResourcePath(path, basePath);
         CustomColormap colors = getCustomColors(path, 256, 256);
         if (colors == null) {
            warn("Colormap not found: " + path);
         } else {
            ConnectedParser cp = new ConnectedParser("CustomColors");
            MatchBlock[] mbs = cp.parseMatchBlocks(value);
            if (mbs != null && mbs.length > 0) {
               for(int m = 0; m < mbs.length; ++m) {
                  MatchBlock mb = mbs[m];
                  colors.addMatchBlock(mb);
               }

               list.add(colors);
            } else {
               warn("Invalid match blocks: " + value);
            }
         }
      }

      if (list.size() <= 0) {
         return null;
      } else {
         CustomColormap[] cms = (CustomColormap[])((CustomColormap[])list.toArray(new CustomColormap[list.size()]));
         return cms;
      }
   }

   private static CustomColormap[][] readBlockColormaps(String[] basePaths, CustomColormap[] basePalettes, int width, int height) {
      String[] paths = ResUtils.collectFiles(basePaths, new String[]{".properties"});
      Arrays.sort(paths);
      List blockList = new ArrayList();

      int i;
      for(i = 0; i < paths.length; ++i) {
         String path = paths[i];
         dbg("Block colormap: " + path);

         try {
            ResourceLocation locFile = new ResourceLocation("minecraft", path);
            InputStream in = Config.getResourceStream(locFile);
            if (in == null) {
               warn("File not found: " + path);
            } else {
               Properties props = new PropertiesOrdered();
               props.load(in);
               CustomColormap cm = new CustomColormap(props, path, width, height, paletteFormatDefault);
               if (cm.isValid(path) && cm.isValidMatchBlocks(path)) {
                  addToBlockList(cm, blockList);
               }
            }
         } catch (FileNotFoundException var12) {
            warn("File not found: " + path);
         } catch (Exception var13) {
            var13.printStackTrace();
         }
      }

      if (basePalettes != null) {
         for(i = 0; i < basePalettes.length; ++i) {
            CustomColormap cm = basePalettes[i];
            addToBlockList(cm, blockList);
         }
      }

      if (blockList.size() <= 0) {
         return (CustomColormap[][])null;
      } else {
         CustomColormap[][] cmArr = blockListToArray(blockList);
         return cmArr;
      }
   }

   private static void addToBlockList(CustomColormap cm, List blockList) {
      int[] ids = cm.getMatchBlockIds();
      if (ids != null && ids.length > 0) {
         for(int i = 0; i < ids.length; ++i) {
            int blockId = ids[i];
            if (blockId < 0) {
               warn("Invalid block ID: " + blockId);
            } else {
               addToList(cm, blockList, blockId);
            }
         }

      } else {
         warn("No match blocks: " + Config.arrayToString(ids));
      }
   }

   private static void addToList(CustomColormap cm, List list, int id) {
      while(id >= list.size()) {
         list.add((Object)null);
      }

      List subList = (List)list.get(id);
      if (subList == null) {
         subList = new ArrayList();
         list.set(id, subList);
      }

      ((List)subList).add(cm);
   }

   private static CustomColormap[][] blockListToArray(List list) {
      CustomColormap[][] colArr = new CustomColormap[list.size()][];

      for(int i = 0; i < list.size(); ++i) {
         List subList = (List)list.get(i);
         if (subList != null) {
            CustomColormap[] subArr = (CustomColormap[])((CustomColormap[])subList.toArray(new CustomColormap[subList.size()]));
            colArr[i] = subArr;
         }
      }

      return colArr;
   }

   private static int readColor(Properties props, String[] names) {
      for(int i = 0; i < names.length; ++i) {
         String name = names[i];
         int col = readColor(props, name);
         if (col >= 0) {
            return col;
         }
      }

      return -1;
   }

   private static int readColor(Properties props, String name) {
      String str = props.getProperty(name);
      if (str == null) {
         return -1;
      } else {
         str = str.trim();
         int color = parseColor(str);
         if (color < 0) {
            warn("Invalid color: " + name + " = " + str);
            return color;
         } else {
            dbg(name + " = " + str);
            return color;
         }
      }
   }

   private static int parseColor(String str) {
      if (str == null) {
         return -1;
      } else {
         str = str.trim();

         try {
            int val = Integer.parseInt(str, 16) & 16777215;
            return val;
         } catch (NumberFormatException var2) {
            return -1;
         }
      }
   }

   private static Vec3 readColorVec3(Properties props, String name) {
      int col = readColor(props, name);
      if (col < 0) {
         return null;
      } else {
         int red = col >> 16 & 255;
         int green = col >> 8 & 255;
         int blue = col & 255;
         float redF = (float)red / 255.0F;
         float greenF = (float)green / 255.0F;
         float blueF = (float)blue / 255.0F;
         return new Vec3((double)redF, (double)greenF, (double)blueF);
      }
   }

   private static CustomColormap getCustomColors(String basePath, String[] paths, int width, int height) {
      for(int i = 0; i < paths.length; ++i) {
         String path = paths[i];
         path = basePath + path;
         CustomColormap cols = getCustomColors(path, width, height);
         if (cols != null) {
            return cols;
         }
      }

      return null;
   }

   public static CustomColormap getCustomColors(String pathImage, int width, int height) {
      try {
         ResourceLocation loc = new ResourceLocation(pathImage);
         if (!Config.hasResource(loc)) {
            return null;
         } else {
            dbg("Colormap " + pathImage);
            Properties props = new PropertiesOrdered();
            String pathProps = StrUtils.replaceSuffix(pathImage, ".png", ".properties");
            ResourceLocation locProps = new ResourceLocation(pathProps);
            if (Config.hasResource(locProps)) {
               InputStream in = Config.getResourceStream(locProps);
               props.load(in);
               in.close();
               dbg("Colormap properties: " + pathProps);
            } else {
               props.put("format", paletteFormatDefault);
               props.put("source", pathImage);
               pathProps = pathImage;
            }

            CustomColormap cm = new CustomColormap(props, pathProps, width, height, paletteFormatDefault);
            return !cm.isValid(pathProps) ? null : cm;
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         return null;
      }
   }

   public static void updateUseDefaultGrassFoliageColors() {
      useDefaultGrassFoliageColors = foliageBirchColors == null && foliagePineColors == null && swampGrassColors == null && swampFoliageColors == null && Config.isSwampColors() && Config.isSmoothBiomes();
   }

   public static int getColorMultiplier(BakedQuad quad, IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos, RenderEnv renderEnv) {
      Block block = blockState.func_177230_c();
      IBlockState bs = renderEnv.getBlockState();
      if (blockColormaps != null) {
         if (!quad.func_178212_b()) {
            if (block == Blocks.field_150349_c) {
               bs = BLOCK_STATE_DIRT;
            }

            if (block == Blocks.field_150488_af) {
               return -1;
            }
         }

         if (block == Blocks.field_150398_cm && renderEnv.getMetadata() >= 8) {
            blockPos = blockPos.func_177977_b();
            bs = blockAccess.func_180495_p(blockPos);
         }

         CustomColormap cm = getBlockColormap(bs);
         if (cm != null) {
            if (Config.isSmoothBiomes() && !cm.isColorConstant()) {
               return getSmoothColorMultiplier(blockState, blockAccess, blockPos, cm, renderEnv.getColorizerBlockPosM());
            }

            return cm.getColor(blockAccess, blockPos);
         }
      }

      if (!quad.func_178212_b()) {
         return -1;
      } else if (block == Blocks.field_150392_bi) {
         return getLilypadColorMultiplier(blockAccess, blockPos);
      } else if (block == Blocks.field_150488_af) {
         return getRedstoneColor(renderEnv.getBlockState());
      } else if (block instanceof BlockStem) {
         return getStemColorMultiplier(block, blockAccess, blockPos, renderEnv);
      } else if (useDefaultGrassFoliageColors) {
         return -1;
      } else {
         int metadata = renderEnv.getMetadata();
         CustomColors.IColorizer colorizer;
         if (block != Blocks.field_150349_c && block != Blocks.field_150329_H && block != Blocks.field_150398_cm) {
            if (block == Blocks.field_150398_cm) {
               colorizer = COLORIZER_GRASS;
               if (metadata >= 8) {
                  blockPos = blockPos.func_177977_b();
               }
            } else if (block == Blocks.field_150362_t) {
               switch(metadata & 3) {
               case 0:
                  colorizer = COLORIZER_FOLIAGE;
                  break;
               case 1:
                  colorizer = COLORIZER_FOLIAGE_PINE;
                  break;
               case 2:
                  colorizer = COLORIZER_FOLIAGE_BIRCH;
                  break;
               default:
                  colorizer = COLORIZER_FOLIAGE;
               }
            } else if (block == Blocks.field_150361_u) {
               colorizer = COLORIZER_FOLIAGE;
            } else {
               if (block != Blocks.field_150395_bd) {
                  return -1;
               }

               colorizer = COLORIZER_FOLIAGE;
            }
         } else {
            colorizer = COLORIZER_GRASS;
         }

         return Config.isSmoothBiomes() && !colorizer.isColorConstant() ? getSmoothColorMultiplier(blockState, blockAccess, blockPos, colorizer, renderEnv.getColorizerBlockPosM()) : colorizer.getColor(bs, blockAccess, blockPos);
      }
   }

   protected static BiomeGenBase getColorBiome(IBlockAccess blockAccess, BlockPos blockPos) {
      BiomeGenBase biome = blockAccess.func_180494_b(blockPos);
      if (biome == BiomeGenBase.field_76780_h && !Config.isSwampColors()) {
         biome = BiomeGenBase.field_76772_c;
      }

      return biome;
   }

   private static CustomColormap getBlockColormap(IBlockState blockState) {
      if (blockColormaps == null) {
         return null;
      } else if (!(blockState instanceof BlockStateBase)) {
         return null;
      } else {
         BlockStateBase bs = (BlockStateBase)blockState;
         int blockId = bs.getBlockId();
         if (blockId >= 0 && blockId < blockColormaps.length) {
            CustomColormap[] cms = blockColormaps[blockId];
            if (cms == null) {
               return null;
            } else {
               for(int i = 0; i < cms.length; ++i) {
                  CustomColormap cm = cms[i];
                  if (cm.matchesBlock(bs)) {
                     return cm;
                  }
               }

               return null;
            }
         } else {
            return null;
         }
      }
   }

   private static int getSmoothColorMultiplier(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos, CustomColors.IColorizer colorizer, BlockPosM blockPosM) {
      int sumRed = 0;
      int sumGreen = 0;
      int sumBlue = 0;
      int x = blockPos.func_177958_n();
      int y = blockPos.func_177956_o();
      int z = blockPos.func_177952_p();
      BlockPosM posM = blockPosM;

      int ix;
      int iz;
      int col;
      for(ix = x - 1; ix <= x + 1; ++ix) {
         for(iz = z - 1; iz <= z + 1; ++iz) {
            posM.setXyz(ix, y, iz);
            col = colorizer.getColor(blockState, blockAccess, posM);
            sumRed += col >> 16 & 255;
            sumGreen += col >> 8 & 255;
            sumBlue += col & 255;
         }
      }

      ix = sumRed / 9;
      iz = sumGreen / 9;
      col = sumBlue / 9;
      return ix << 16 | iz << 8 | col;
   }

   public static int getFluidColor(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, RenderEnv renderEnv) {
      Block block = blockState.func_177230_c();
      CustomColors.IColorizer colorizer = getBlockColormap(blockState);
      if (colorizer == null && blockState.func_177230_c().func_149688_o() == Material.field_151586_h) {
         colorizer = COLORIZER_WATER;
      }

      if (colorizer == null) {
         return block.func_180662_a(blockAccess, blockPos, 0);
      } else {
         return Config.isSmoothBiomes() && !((CustomColors.IColorizer)colorizer).isColorConstant() ? getSmoothColorMultiplier(blockState, blockAccess, blockPos, (CustomColors.IColorizer)colorizer, renderEnv.getColorizerBlockPosM()) : ((CustomColors.IColorizer)colorizer).getColor(blockState, blockAccess, blockPos);
      }
   }

   public static void updatePortalFX(EntityFX fx) {
      if (particlePortalColor >= 0) {
         int col = particlePortalColor;
         int red = col >> 16 & 255;
         int green = col >> 8 & 255;
         int blue = col & 255;
         float redF = (float)red / 255.0F;
         float greenF = (float)green / 255.0F;
         float blueF = (float)blue / 255.0F;
         fx.func_70538_b(redF, greenF, blueF);
      }
   }

   public static void updateMyceliumFX(EntityFX fx) {
      if (myceliumParticleColors != null) {
         int col = myceliumParticleColors.getColorRandom();
         int red = col >> 16 & 255;
         int green = col >> 8 & 255;
         int blue = col & 255;
         float redF = (float)red / 255.0F;
         float greenF = (float)green / 255.0F;
         float blueF = (float)blue / 255.0F;
         fx.func_70538_b(redF, greenF, blueF);
      }
   }

   private static int getRedstoneColor(IBlockState blockState) {
      if (redstoneColors == null) {
         return -1;
      } else {
         int level = getRedstoneLevel(blockState, 15);
         int col = redstoneColors.getColor(level);
         return col;
      }
   }

   public static void updateReddustFX(EntityFX fx, IBlockAccess blockAccess, double x, double y, double z) {
      if (redstoneColors != null) {
         IBlockState state = blockAccess.func_180495_p(new BlockPos(x, y, z));
         int level = getRedstoneLevel(state, 15);
         int col = redstoneColors.getColor(level);
         int red = col >> 16 & 255;
         int green = col >> 8 & 255;
         int blue = col & 255;
         float redF = (float)red / 255.0F;
         float greenF = (float)green / 255.0F;
         float blueF = (float)blue / 255.0F;
         fx.func_70538_b(redF, greenF, blueF);
      }
   }

   private static int getRedstoneLevel(IBlockState state, int def) {
      Block block = state.func_177230_c();
      if (!(block instanceof BlockRedstoneWire)) {
         return def;
      } else {
         Object val = state.func_177229_b(BlockRedstoneWire.field_176351_O);
         if (!(val instanceof Integer)) {
            return def;
         } else {
            Integer valInt = (Integer)val;
            return valInt;
         }
      }
   }

   public static float getXpOrbTimer(float timer) {
      if (xpOrbTime <= 0) {
         return timer;
      } else {
         float kt = 628.0F / (float)xpOrbTime;
         return timer * kt;
      }
   }

   public static int getXpOrbColor(float timer) {
      if (xpOrbColors == null) {
         return -1;
      } else {
         int index = (int)Math.round((double)((MathHelper.func_76126_a(timer) + 1.0F) * (float)(xpOrbColors.getLength() - 1)) / 2.0D);
         int col = xpOrbColors.getColor(index);
         return col;
      }
   }

   public static int getDurabilityColor(int dur255) {
      if (durabilityColors == null) {
         return -1;
      } else {
         int index = dur255 * durabilityColors.getLength() / 255;
         int col = durabilityColors.getColor(index);
         return col;
      }
   }

   public static void updateWaterFX(EntityFX fx, IBlockAccess blockAccess, double x, double y, double z, RenderEnv renderEnv) {
      if (waterColors != null || blockColormaps != null || particleWaterColor >= 0) {
         BlockPos blockPos = new BlockPos(x, y, z);
         renderEnv.reset(BLOCK_STATE_WATER, blockPos);
         int col = getFluidColor(blockAccess, BLOCK_STATE_WATER, blockPos, renderEnv);
         int red = col >> 16 & 255;
         int green = col >> 8 & 255;
         int blue = col & 255;
         float redF = (float)red / 255.0F;
         float greenF = (float)green / 255.0F;
         float blueF = (float)blue / 255.0F;
         if (particleWaterColor >= 0) {
            int redDrop = particleWaterColor >> 16 & 255;
            int greenDrop = particleWaterColor >> 8 & 255;
            int blueDrop = particleWaterColor & 255;
            redF *= (float)redDrop / 255.0F;
            greenF *= (float)greenDrop / 255.0F;
            blueF *= (float)blueDrop / 255.0F;
         }

         fx.func_70538_b(redF, greenF, blueF);
      }
   }

   private static int getLilypadColorMultiplier(IBlockAccess blockAccess, BlockPos blockPos) {
      return lilyPadColor < 0 ? Blocks.field_150392_bi.func_176202_d(blockAccess, blockPos) : lilyPadColor;
   }

   private static Vec3 getFogColorNether(Vec3 col) {
      return fogColorNether == null ? col : fogColorNether;
   }

   private static Vec3 getFogColorEnd(Vec3 col) {
      return fogColorEnd == null ? col : fogColorEnd;
   }

   private static Vec3 getSkyColorEnd(Vec3 col) {
      return skyColorEnd == null ? col : skyColorEnd;
   }

   public static Vec3 getSkyColor(Vec3 skyColor3d, IBlockAccess blockAccess, double x, double y, double z) {
      if (skyColors == null) {
         return skyColor3d;
      } else {
         int col = skyColors.getColorSmooth(blockAccess, x, y, z, 3);
         int red = col >> 16 & 255;
         int green = col >> 8 & 255;
         int blue = col & 255;
         float redF = (float)red / 255.0F;
         float greenF = (float)green / 255.0F;
         float blueF = (float)blue / 255.0F;
         float cRed = (float)skyColor3d.field_72450_a / 0.5F;
         float cGreen = (float)skyColor3d.field_72448_b / 0.66275F;
         float cBlue = (float)skyColor3d.field_72449_c;
         redF *= cRed;
         greenF *= cGreen;
         blueF *= cBlue;
         Vec3 newCol = skyColorFader.getColor((double)redF, (double)greenF, (double)blueF);
         return newCol;
      }
   }

   private static Vec3 getFogColor(Vec3 fogColor3d, IBlockAccess blockAccess, double x, double y, double z) {
      if (fogColors == null) {
         return fogColor3d;
      } else {
         int col = fogColors.getColorSmooth(blockAccess, x, y, z, 3);
         int red = col >> 16 & 255;
         int green = col >> 8 & 255;
         int blue = col & 255;
         float redF = (float)red / 255.0F;
         float greenF = (float)green / 255.0F;
         float blueF = (float)blue / 255.0F;
         float cRed = (float)fogColor3d.field_72450_a / 0.753F;
         float cGreen = (float)fogColor3d.field_72448_b / 0.8471F;
         float cBlue = (float)fogColor3d.field_72449_c;
         redF *= cRed;
         greenF *= cGreen;
         blueF *= cBlue;
         Vec3 newCol = fogColorFader.getColor((double)redF, (double)greenF, (double)blueF);
         return newCol;
      }
   }

   public static Vec3 getUnderwaterColor(IBlockAccess blockAccess, double x, double y, double z) {
      return getUnderFluidColor(blockAccess, x, y, z, underwaterColors, underwaterColorFader);
   }

   public static Vec3 getUnderlavaColor(IBlockAccess blockAccess, double x, double y, double z) {
      return getUnderFluidColor(blockAccess, x, y, z, underlavaColors, underlavaColorFader);
   }

   public static Vec3 getUnderFluidColor(IBlockAccess blockAccess, double x, double y, double z, CustomColormap underFluidColors, CustomColorFader underFluidColorFader) {
      if (underFluidColors == null) {
         return null;
      } else {
         int col = underFluidColors.getColorSmooth(blockAccess, x, y, z, 3);
         int red = col >> 16 & 255;
         int green = col >> 8 & 255;
         int blue = col & 255;
         float redF = (float)red / 255.0F;
         float greenF = (float)green / 255.0F;
         float blueF = (float)blue / 255.0F;
         Vec3 newCol = underFluidColorFader.getColor((double)redF, (double)greenF, (double)blueF);
         return newCol;
      }
   }

   private static int getStemColorMultiplier(Block blockStem, IBlockAccess blockAccess, BlockPos blockPos, RenderEnv renderEnv) {
      CustomColormap colors = stemColors;
      if (blockStem == Blocks.field_150393_bb && stemPumpkinColors != null) {
         colors = stemPumpkinColors;
      }

      if (blockStem == Blocks.field_150394_bc && stemMelonColors != null) {
         colors = stemMelonColors;
      }

      if (colors == null) {
         return -1;
      } else {
         int level = renderEnv.getMetadata();
         return colors.getColor(level);
      }
   }

   public static boolean updateLightmap(World world, float torchFlickerX, int[] lmColors, boolean nightvision, float partialTicks) {
      if (world == null) {
         return false;
      } else if (lightMapPacks == null) {
         return false;
      } else {
         int dimensionId = world.field_73011_w.func_177502_q();
         int lightMapIndex = dimensionId - lightmapMinDimensionId;
         if (lightMapIndex >= 0 && lightMapIndex < lightMapPacks.length) {
            LightMapPack lightMapPack = lightMapPacks[lightMapIndex];
            return lightMapPack == null ? false : lightMapPack.updateLightmap(world, torchFlickerX, lmColors, nightvision, partialTicks);
         } else {
            return false;
         }
      }
   }

   public static Vec3 getWorldFogColor(Vec3 fogVec, World world, Entity renderViewEntity, float partialTicks) {
      int worldType = world.field_73011_w.func_177502_q();
      switch(worldType) {
      case -1:
         fogVec = getFogColorNether(fogVec);
         break;
      case 0:
         Minecraft mc = Minecraft.func_71410_x();
         fogVec = getFogColor(fogVec, mc.field_71441_e, renderViewEntity.field_70165_t, renderViewEntity.field_70163_u + 1.0D, renderViewEntity.field_70161_v);
         break;
      case 1:
         fogVec = getFogColorEnd(fogVec);
      }

      return fogVec;
   }

   public static Vec3 getWorldSkyColor(Vec3 skyVec, World world, Entity renderViewEntity, float partialTicks) {
      int worldType = world.field_73011_w.func_177502_q();
      switch(worldType) {
      case 0:
         Minecraft mc = Minecraft.func_71410_x();
         skyVec = getSkyColor(skyVec, mc.field_71441_e, renderViewEntity.field_70165_t, renderViewEntity.field_70163_u + 1.0D, renderViewEntity.field_70161_v);
         break;
      case 1:
         skyVec = getSkyColorEnd(skyVec);
      }

      return skyVec;
   }

   private static int[] readSpawnEggColors(Properties props, String fileName, String prefix, String logName) {
      List<Integer> list = new ArrayList();
      Set keys = props.keySet();
      int countColors = 0;
      Iterator iter = keys.iterator();

      while(true) {
         while(true) {
            String key;
            String value;
            do {
               if (!iter.hasNext()) {
                  if (countColors <= 0) {
                     return null;
                  }

                  dbg(logName + " colors: " + countColors);
                  int[] colors = new int[list.size()];

                  for(int i = 0; i < colors.length; ++i) {
                     colors[i] = (Integer)list.get(i);
                  }

                  return colors;
               }

               key = (String)iter.next();
               value = props.getProperty(key);
            } while(!key.startsWith(prefix));

            String name = StrUtils.removePrefix(key, prefix);
            int id = EntityUtils.getEntityIdByName(name);
            if (id < 0) {
               warn("Invalid spawn egg name: " + key);
            } else {
               int color = parseColor(value);
               if (color < 0) {
                  warn("Invalid spawn egg color: " + key + " = " + value);
               } else {
                  while(list.size() <= id) {
                     list.add(-1);
                  }

                  list.set(id, color);
                  ++countColors;
               }
            }
         }
      }
   }

   private static int getSpawnEggColor(ItemMonsterPlacer item, ItemStack itemStack, int layer, int color) {
      int id = itemStack.func_77960_j();
      int[] eggColors = layer == 0 ? spawnEggPrimaryColors : spawnEggSecondaryColors;
      if (eggColors == null) {
         return color;
      } else if (id >= 0 && id < eggColors.length) {
         int eggColor = eggColors[id];
         return eggColor < 0 ? color : eggColor;
      } else {
         return color;
      }
   }

   public static int getColorFromItemStack(ItemStack itemStack, int layer, int color) {
      if (itemStack == null) {
         return color;
      } else {
         Item item = itemStack.func_77973_b();
         if (item == null) {
            return color;
         } else {
            return item instanceof ItemMonsterPlacer ? getSpawnEggColor((ItemMonsterPlacer)item, itemStack, layer, color) : color;
         }
      }
   }

   private static float[][] readDyeColors(Properties props, String fileName, String prefix, String logName) {
      EnumDyeColor[] dyeValues = EnumDyeColor.values();
      Map<String, EnumDyeColor> mapDyes = new HashMap();

      for(int i = 0; i < dyeValues.length; ++i) {
         EnumDyeColor dye = dyeValues[i];
         mapDyes.put(dye.func_176610_l(), dye);
      }

      float[][] colors = new float[dyeValues.length][];
      int countColors = 0;
      Set keys = props.keySet();
      Iterator iter = keys.iterator();

      while(true) {
         while(true) {
            String key;
            String value;
            do {
               if (!iter.hasNext()) {
                  if (countColors <= 0) {
                     return (float[][])null;
                  }

                  dbg(logName + " colors: " + countColors);
                  return colors;
               }

               key = (String)iter.next();
               value = props.getProperty(key);
            } while(!key.startsWith(prefix));

            String name = StrUtils.removePrefix(key, prefix);
            if (name.equals("lightBlue")) {
               name = "light_blue";
            }

            EnumDyeColor dye = (EnumDyeColor)mapDyes.get(name);
            int color = parseColor(value);
            if (dye != null && color >= 0) {
               float[] rgb = new float[]{(float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F};
               colors[dye.ordinal()] = rgb;
               ++countColors;
            } else {
               warn("Invalid color: " + key + " = " + value);
            }
         }
      }
   }

   private static float[] getDyeColors(EnumDyeColor dye, float[][] dyeColors, float[] colors) {
      if (dyeColors == null) {
         return colors;
      } else if (dye == null) {
         return colors;
      } else {
         float[] customColors = dyeColors[dye.ordinal()];
         return customColors == null ? colors : customColors;
      }
   }

   public static float[] getWolfCollarColors(EnumDyeColor dye, float[] colors) {
      return getDyeColors(dye, wolfCollarColors, colors);
   }

   public static float[] getSheepColors(EnumDyeColor dye, float[] colors) {
      return getDyeColors(dye, sheepColors, colors);
   }

   private static int[] readTextColors(Properties props, String fileName, String prefix, String logName) {
      int[] colors = new int[32];
      Arrays.fill(colors, -1);
      int countColors = 0;
      Set keys = props.keySet();
      Iterator iter = keys.iterator();

      while(true) {
         while(true) {
            String key;
            String value;
            do {
               if (!iter.hasNext()) {
                  if (countColors <= 0) {
                     return null;
                  }

                  dbg(logName + " colors: " + countColors);
                  return colors;
               }

               key = (String)iter.next();
               value = props.getProperty(key);
            } while(!key.startsWith(prefix));

            String name = StrUtils.removePrefix(key, prefix);
            int code = Config.parseInt(name, -1);
            int color = parseColor(value);
            if (code >= 0 && code < colors.length && color >= 0) {
               colors[code] = color;
               ++countColors;
            } else {
               warn("Invalid color: " + key + " = " + value);
            }
         }
      }
   }

   public static int getTextColor(int index, int color) {
      if (textColors == null) {
         return color;
      } else if (index >= 0 && index < textColors.length) {
         int customColor = textColors[index];
         return customColor < 0 ? color : customColor;
      } else {
         return color;
      }
   }

   private static int[] readMapColors(Properties props, String fileName, String prefix, String logName) {
      int[] colors = new int[MapColor.field_76281_a.length];
      Arrays.fill(colors, -1);
      int countColors = 0;
      Set keys = props.keySet();
      Iterator iter = keys.iterator();

      while(true) {
         while(true) {
            String key;
            String value;
            do {
               if (!iter.hasNext()) {
                  if (countColors <= 0) {
                     return null;
                  }

                  dbg(logName + " colors: " + countColors);
                  return colors;
               }

               key = (String)iter.next();
               value = props.getProperty(key);
            } while(!key.startsWith(prefix));

            String name = StrUtils.removePrefix(key, prefix);
            int index = getMapColorIndex(name);
            int color = parseColor(value);
            if (index >= 0 && index < colors.length && color >= 0) {
               colors[index] = color;
               ++countColors;
            } else {
               warn("Invalid color: " + key + " = " + value);
            }
         }
      }
   }

   private static int[] readPotionColors(Properties props, String fileName, String prefix, String logName) {
      int[] colors = new int[Potion.field_76425_a.length];
      Arrays.fill(colors, -1);
      int countColors = 0;
      Set keys = props.keySet();
      Iterator iter = keys.iterator();

      while(true) {
         while(true) {
            String key;
            String value;
            do {
               if (!iter.hasNext()) {
                  if (countColors <= 0) {
                     return null;
                  }

                  dbg(logName + " colors: " + countColors);
                  return colors;
               }

               key = (String)iter.next();
               value = props.getProperty(key);
            } while(!key.startsWith(prefix));

            int index = getPotionId(key);
            int color = parseColor(value);
            if (index >= 0 && index < colors.length && color >= 0) {
               colors[index] = color;
               ++countColors;
            } else {
               warn("Invalid color: " + key + " = " + value);
            }
         }
      }
   }

   private static int getPotionId(String name) {
      if (name.equals("potion.water")) {
         return 0;
      } else {
         Potion[] potions = Potion.field_76425_a;

         for(int i = 0; i < potions.length; ++i) {
            Potion potion = potions[i];
            if (potion != null && potion.func_76393_a().equals(name)) {
               return potion.func_76396_c();
            }
         }

         return -1;
      }
   }

   public static int getPotionColor(int potionId, int color) {
      if (potionColors == null) {
         return color;
      } else if (potionId >= 0 && potionId < potionColors.length) {
         int potionColor = potionColors[potionId];
         return potionColor < 0 ? color : potionColor;
      } else {
         return color;
      }
   }

   private static int getMapColorIndex(String name) {
      if (name == null) {
         return -1;
      } else if (name.equals("air")) {
         return MapColor.field_151660_b.field_76290_q;
      } else if (name.equals("grass")) {
         return MapColor.field_151661_c.field_76290_q;
      } else if (name.equals("sand")) {
         return MapColor.field_151658_d.field_76290_q;
      } else if (name.equals("cloth")) {
         return MapColor.field_151659_e.field_76290_q;
      } else if (name.equals("tnt")) {
         return MapColor.field_151656_f.field_76290_q;
      } else if (name.equals("ice")) {
         return MapColor.field_151657_g.field_76290_q;
      } else if (name.equals("iron")) {
         return MapColor.field_151668_h.field_76290_q;
      } else if (name.equals("foliage")) {
         return MapColor.field_151669_i.field_76290_q;
      } else if (name.equals("clay")) {
         return MapColor.field_151667_k.field_76290_q;
      } else if (name.equals("dirt")) {
         return MapColor.field_151664_l.field_76290_q;
      } else if (name.equals("stone")) {
         return MapColor.field_151665_m.field_76290_q;
      } else if (name.equals("water")) {
         return MapColor.field_151662_n.field_76290_q;
      } else if (name.equals("wood")) {
         return MapColor.field_151663_o.field_76290_q;
      } else if (name.equals("quartz")) {
         return MapColor.field_151677_p.field_76290_q;
      } else if (name.equals("gold")) {
         return MapColor.field_151647_F.field_76290_q;
      } else if (name.equals("diamond")) {
         return MapColor.field_151648_G.field_76290_q;
      } else if (name.equals("lapis")) {
         return MapColor.field_151652_H.field_76290_q;
      } else if (name.equals("emerald")) {
         return MapColor.field_151653_I.field_76290_q;
      } else if (name.equals("podzol")) {
         return MapColor.field_151654_J.field_76290_q;
      } else if (name.equals("netherrack")) {
         return MapColor.field_151655_K.field_76290_q;
      } else if (!name.equals("snow") && !name.equals("white")) {
         if (!name.equals("adobe") && !name.equals("orange")) {
            if (name.equals("magenta")) {
               return MapColor.field_151675_r.field_76290_q;
            } else if (!name.equals("light_blue") && !name.equals("lightBlue")) {
               if (name.equals("yellow")) {
                  return MapColor.field_151673_t.field_76290_q;
               } else if (name.equals("lime")) {
                  return MapColor.field_151672_u.field_76290_q;
               } else if (name.equals("pink")) {
                  return MapColor.field_151671_v.field_76290_q;
               } else if (name.equals("gray")) {
                  return MapColor.field_151670_w.field_76290_q;
               } else if (name.equals("silver")) {
                  return MapColor.field_151680_x.field_76290_q;
               } else if (name.equals("cyan")) {
                  return MapColor.field_151679_y.field_76290_q;
               } else if (name.equals("purple")) {
                  return MapColor.field_151678_z.field_76290_q;
               } else if (name.equals("blue")) {
                  return MapColor.field_151649_A.field_76290_q;
               } else if (name.equals("brown")) {
                  return MapColor.field_151650_B.field_76290_q;
               } else if (name.equals("green")) {
                  return MapColor.field_151651_C.field_76290_q;
               } else if (name.equals("red")) {
                  return MapColor.field_151645_D.field_76290_q;
               } else {
                  return name.equals("black") ? MapColor.field_151646_E.field_76290_q : -1;
               }
            } else {
               return MapColor.field_151674_s.field_76290_q;
            }
         } else {
            return MapColor.field_151676_q.field_76290_q;
         }
      } else {
         return MapColor.field_151666_j.field_76290_q;
      }
   }

   private static int[] getMapColors() {
      MapColor[] mapColors = MapColor.field_76281_a;
      int[] colors = new int[mapColors.length];
      Arrays.fill(colors, -1);

      for(int i = 0; i < mapColors.length && i < colors.length; ++i) {
         MapColor mapColor = mapColors[i];
         if (mapColor != null) {
            colors[i] = mapColor.field_76291_p;
         }
      }

      return colors;
   }

   private static void setMapColors(int[] colors) {
      if (colors != null) {
         MapColor[] mapColors = MapColor.field_76281_a;
         boolean changed = false;

         for(int i = 0; i < mapColors.length && i < colors.length; ++i) {
            MapColor mapColor = mapColors[i];
            if (mapColor != null) {
               int color = colors[i];
               if (color >= 0 && mapColor.field_76291_p != color) {
                  mapColor.field_76291_p = color;
                  changed = true;
               }
            }
         }

         if (changed) {
            Minecraft.func_71410_x().func_110434_K().reloadBannerTextures();
         }

      }
   }

   private static void dbg(String str) {
      Config.dbg("CustomColors: " + str);
   }

   private static void warn(String str) {
      Config.warn("CustomColors: " + str);
   }

   public static int getExpBarTextColor(int color) {
      return expBarTextColor < 0 ? color : expBarTextColor;
   }

   public static int getBossTextColor(int color) {
      return bossTextColor < 0 ? color : bossTextColor;
   }

   public static int getSignTextColor(int color) {
      return signTextColor < 0 ? color : signTextColor;
   }

   static {
      BLOCK_STATE_DIRT = Blocks.field_150346_d.func_176223_P();
      BLOCK_STATE_WATER = Blocks.field_150355_j.func_176223_P();
      random = new Random();
      COLORIZER_GRASS = new CustomColors.IColorizer() {
         public int getColor(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos) {
            BiomeGenBase biome = CustomColors.getColorBiome(blockAccess, blockPos);
            return CustomColors.swampGrassColors != null && biome == BiomeGenBase.field_76780_h ? CustomColors.swampGrassColors.getColor(biome, blockPos) : biome.func_180627_b(blockPos);
         }

         public boolean isColorConstant() {
            return false;
         }
      };
      COLORIZER_FOLIAGE = new CustomColors.IColorizer() {
         public int getColor(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos) {
            BiomeGenBase biome = CustomColors.getColorBiome(blockAccess, blockPos);
            return CustomColors.swampFoliageColors != null && biome == BiomeGenBase.field_76780_h ? CustomColors.swampFoliageColors.getColor(biome, blockPos) : biome.func_180625_c(blockPos);
         }

         public boolean isColorConstant() {
            return false;
         }
      };
      COLORIZER_FOLIAGE_PINE = new CustomColors.IColorizer() {
         public int getColor(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos) {
            return CustomColors.foliagePineColors != null ? CustomColors.foliagePineColors.getColor(blockAccess, blockPos) : ColorizerFoliage.func_77466_a();
         }

         public boolean isColorConstant() {
            return CustomColors.foliagePineColors == null;
         }
      };
      COLORIZER_FOLIAGE_BIRCH = new CustomColors.IColorizer() {
         public int getColor(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos) {
            return CustomColors.foliageBirchColors != null ? CustomColors.foliageBirchColors.getColor(blockAccess, blockPos) : ColorizerFoliage.func_77469_b();
         }

         public boolean isColorConstant() {
            return CustomColors.foliageBirchColors == null;
         }
      };
      COLORIZER_WATER = new CustomColors.IColorizer() {
         public int getColor(IBlockState blockState, IBlockAccess blockAccess, BlockPos blockPos) {
            BiomeGenBase biome = CustomColors.getColorBiome(blockAccess, blockPos);
            if (CustomColors.waterColors != null) {
               return CustomColors.waterColors.getColor(biome, blockPos);
            } else {
               return Reflector.ForgeBiome_getWaterColorMultiplier.exists() ? Reflector.callInt(biome, Reflector.ForgeBiome_getWaterColorMultiplier) : biome.field_76759_H;
            }
         }

         public boolean isColorConstant() {
            return false;
         }
      };
   }

   public interface IColorizer {
      int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3);

      boolean isColorConstant();
   }
}
