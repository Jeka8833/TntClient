package net.optifine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.src.Config;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.IParserInt;
import net.optifine.config.NbtTagValue;
import net.optifine.config.ParserEnchantmentId;
import net.optifine.config.RangeInt;
import net.optifine.config.RangeListInt;
import net.optifine.reflect.Reflector;
import net.optifine.render.Blender;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.lwjgl.opengl.GL11;

public class CustomItemProperties {
   public String name = null;
   public String basePath = null;
   public int type = 1;
   public int[] items = null;
   public String texture = null;
   public Map<String, String> mapTextures = null;
   public String model = null;
   public Map<String, String> mapModels = null;
   public RangeListInt damage = null;
   public boolean damagePercent = false;
   public int damageMask = 0;
   public RangeListInt stackSize = null;
   public RangeListInt enchantmentIds = null;
   public RangeListInt enchantmentLevels = null;
   public NbtTagValue[] nbtTagValues = null;
   public int hand = 0;
   public int blend = 1;
   public float speed = 0.0F;
   public float rotation = 0.0F;
   public int layer = 0;
   public float duration = 1.0F;
   public int weight = 0;
   public ResourceLocation textureLocation = null;
   public Map mapTextureLocations = null;
   public TextureAtlasSprite sprite = null;
   public Map mapSprites = null;
   public IBakedModel bakedModelTexture = null;
   public Map<String, IBakedModel> mapBakedModelsTexture = null;
   public IBakedModel bakedModelFull = null;
   public Map<String, IBakedModel> mapBakedModelsFull = null;
   private int textureWidth = 0;
   private int textureHeight = 0;
   public static final int TYPE_UNKNOWN = 0;
   public static final int TYPE_ITEM = 1;
   public static final int TYPE_ENCHANTMENT = 2;
   public static final int TYPE_ARMOR = 3;
   public static final int HAND_ANY = 0;
   public static final int HAND_MAIN = 1;
   public static final int HAND_OFF = 2;
   public static final String INVENTORY = "inventory";

   public CustomItemProperties(Properties props, String path) {
      this.name = parseName(path);
      this.basePath = parseBasePath(path);
      this.type = this.parseType(props.getProperty("type"));
      this.items = this.parseItems(props.getProperty("items"), props.getProperty("matchItems"));
      this.mapModels = parseModels(props, this.basePath);
      this.model = parseModel(props.getProperty("model"), path, this.basePath, this.type, this.mapModels);
      this.mapTextures = parseTextures(props, this.basePath);
      boolean textureFromPath = this.mapModels == null && this.model == null;
      this.texture = parseTexture(props.getProperty("texture"), props.getProperty("tile"), props.getProperty("source"), path, this.basePath, this.type, this.mapTextures, textureFromPath);
      String damageStr = props.getProperty("damage");
      if (damageStr != null) {
         this.damagePercent = damageStr.contains("%");
         damageStr = damageStr.replace("%", "");
         this.damage = this.parseRangeListInt(damageStr);
         this.damageMask = this.parseInt(props.getProperty("damageMask"), 0);
      }

      this.stackSize = this.parseRangeListInt(props.getProperty("stackSize"));
      this.enchantmentIds = this.parseRangeListInt(props.getProperty("enchantmentIDs"), new ParserEnchantmentId());
      this.enchantmentLevels = this.parseRangeListInt(props.getProperty("enchantmentLevels"));
      this.nbtTagValues = this.parseNbtTagValues(props);
      this.hand = this.parseHand(props.getProperty("hand"));
      this.blend = Blender.parseBlend(props.getProperty("blend"));
      this.speed = this.parseFloat(props.getProperty("speed"), 0.0F);
      this.rotation = this.parseFloat(props.getProperty("rotation"), 0.0F);
      this.layer = this.parseInt(props.getProperty("layer"), 0);
      this.weight = this.parseInt(props.getProperty("weight"), 0);
      this.duration = this.parseFloat(props.getProperty("duration"), 1.0F);
   }

   private static String parseName(String path) {
      String str = path;
      int pos = path.lastIndexOf(47);
      if (pos >= 0) {
         str = path.substring(pos + 1);
      }

      int pos2 = str.lastIndexOf(46);
      if (pos2 >= 0) {
         str = str.substring(0, pos2);
      }

      return str;
   }

   private static String parseBasePath(String path) {
      int pos = path.lastIndexOf(47);
      return pos < 0 ? "" : path.substring(0, pos);
   }

   private int parseType(String str) {
      if (str == null) {
         return 1;
      } else if (str.equals("item")) {
         return 1;
      } else if (str.equals("enchantment")) {
         return 2;
      } else if (str.equals("armor")) {
         return 3;
      } else {
         Config.warn("Unknown method: " + str);
         return 0;
      }
   }

   private int[] parseItems(String str, String str2) {
      if (str == null) {
         str = str2;
      }

      if (str == null) {
         return null;
      } else {
         str = str.trim();
         Set setItemIds = new TreeSet();
         String[] tokens = Config.tokenize(str, " ");

         int i;
         label57:
         for(int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            i = Config.parseInt(token, -1);
            if (i >= 0) {
               setItemIds.add(new Integer(i));
            } else {
               int id;
               if (token.contains("-")) {
                  String[] parts = Config.tokenize(token, "-");
                  if (parts.length == 2) {
                     id = Config.parseInt(parts[0], -1);
                     int val2 = Config.parseInt(parts[1], -1);
                     if (id >= 0 && val2 >= 0) {
                        int min = Math.min(id, val2);
                        int max = Math.max(id, val2);
                        int x = min;

                        while(true) {
                           if (x > max) {
                              continue label57;
                           }

                           setItemIds.add(new Integer(x));
                           ++x;
                        }
                     }
                  }
               }

               Item item = Item.func_111206_d(token);
               if (item == null) {
                  Config.warn("Item not found: " + token);
               } else {
                  id = Item.func_150891_b(item);
                  if (id <= 0) {
                     Config.warn("Item not found: " + token);
                  } else {
                     setItemIds.add(new Integer(id));
                  }
               }
            }
         }

         Integer[] integers = (Integer[])((Integer[])setItemIds.toArray(new Integer[setItemIds.size()]));
         int[] ints = new int[integers.length];

         for(i = 0; i < ints.length; ++i) {
            ints[i] = integers[i];
         }

         return ints;
      }
   }

   private static String parseTexture(String texStr, String texStr2, String texStr3, String path, String basePath, int type, Map<String, String> mapTexs, boolean textureFromPath) {
      if (texStr == null) {
         texStr = texStr2;
      }

      if (texStr == null) {
         texStr = texStr3;
      }

      String str;
      if (texStr != null) {
         str = ".png";
         if (texStr.endsWith(str)) {
            texStr = texStr.substring(0, texStr.length() - str.length());
         }

         texStr = fixTextureName(texStr, basePath);
         return texStr;
      } else if (type == 3) {
         return null;
      } else {
         if (mapTexs != null) {
            str = (String)mapTexs.get("texture.bow_standby");
            if (str != null) {
               return str;
            }
         }

         if (!textureFromPath) {
            return null;
         } else {
            str = path;
            int pos = path.lastIndexOf(47);
            if (pos >= 0) {
               str = path.substring(pos + 1);
            }

            int pos2 = str.lastIndexOf(46);
            if (pos2 >= 0) {
               str = str.substring(0, pos2);
            }

            str = fixTextureName(str, basePath);
            return str;
         }
      }
   }

   private static Map parseTextures(Properties props, String basePath) {
      String prefix = "texture.";
      Map mapProps = getMatchingProperties(props, prefix);
      if (mapProps.size() <= 0) {
         return null;
      } else {
         Set keySet = mapProps.keySet();
         Map mapTex = new LinkedHashMap();
         Iterator it = keySet.iterator();

         while(it.hasNext()) {
            String key = (String)it.next();
            String val = (String)mapProps.get(key);
            val = fixTextureName(val, basePath);
            mapTex.put(key, val);
         }

         return mapTex;
      }
   }

   private static String fixTextureName(String iconName, String basePath) {
      iconName = TextureUtils.fixResourcePath(iconName, basePath);
      if (!iconName.startsWith(basePath) && !iconName.startsWith("textures/") && !iconName.startsWith("mcpatcher/")) {
         iconName = basePath + "/" + iconName;
      }

      if (iconName.endsWith(".png")) {
         iconName = iconName.substring(0, iconName.length() - 4);
      }

      if (iconName.startsWith("/")) {
         iconName = iconName.substring(1);
      }

      return iconName;
   }

   private static String parseModel(String modelStr, String path, String basePath, int type, Map<String, String> mapModelNames) {
      String bowStandbyModel;
      if (modelStr != null) {
         bowStandbyModel = ".json";
         if (modelStr.endsWith(bowStandbyModel)) {
            modelStr = modelStr.substring(0, modelStr.length() - bowStandbyModel.length());
         }

         modelStr = fixModelName(modelStr, basePath);
         return modelStr;
      } else if (type == 3) {
         return null;
      } else {
         if (mapModelNames != null) {
            bowStandbyModel = (String)mapModelNames.get("model.bow_standby");
            if (bowStandbyModel != null) {
               return bowStandbyModel;
            }
         }

         return modelStr;
      }
   }

   private static Map parseModels(Properties props, String basePath) {
      String prefix = "model.";
      Map mapProps = getMatchingProperties(props, prefix);
      if (mapProps.size() <= 0) {
         return null;
      } else {
         Set keySet = mapProps.keySet();
         Map mapTex = new LinkedHashMap();
         Iterator it = keySet.iterator();

         while(it.hasNext()) {
            String key = (String)it.next();
            String val = (String)mapProps.get(key);
            val = fixModelName(val, basePath);
            mapTex.put(key, val);
         }

         return mapTex;
      }
   }

   private static String fixModelName(String modelName, String basePath) {
      modelName = TextureUtils.fixResourcePath(modelName, basePath);
      boolean isVanilla = modelName.startsWith("block/") || modelName.startsWith("item/");
      if (!modelName.startsWith(basePath) && !isVanilla && !modelName.startsWith("mcpatcher/")) {
         modelName = basePath + "/" + modelName;
      }

      String json = ".json";
      if (modelName.endsWith(json)) {
         modelName = modelName.substring(0, modelName.length() - json.length());
      }

      if (modelName.startsWith("/")) {
         modelName = modelName.substring(1);
      }

      return modelName;
   }

   private int parseInt(String str, int defVal) {
      if (str == null) {
         return defVal;
      } else {
         str = str.trim();
         int val = Config.parseInt(str, Integer.MIN_VALUE);
         if (val == Integer.MIN_VALUE) {
            Config.warn("Invalid integer: " + str);
            return defVal;
         } else {
            return val;
         }
      }
   }

   private float parseFloat(String str, float defVal) {
      if (str == null) {
         return defVal;
      } else {
         str = str.trim();
         float val = Config.parseFloat(str, Float.MIN_VALUE);
         if (val == Float.MIN_VALUE) {
            Config.warn("Invalid float: " + str);
            return defVal;
         } else {
            return val;
         }
      }
   }

   private RangeListInt parseRangeListInt(String str) {
      return this.parseRangeListInt(str, (IParserInt)null);
   }

   private RangeListInt parseRangeListInt(String str, IParserInt parser) {
      if (str == null) {
         return null;
      } else {
         String[] tokens = Config.tokenize(str, " ");
         RangeListInt rangeList = new RangeListInt();

         for(int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            if (parser != null) {
               int val = parser.parse(token, Integer.MIN_VALUE);
               if (val != Integer.MIN_VALUE) {
                  rangeList.addRange(new RangeInt(val, val));
                  continue;
               }
            }

            RangeInt range = this.parseRangeInt(token);
            if (range == null) {
               Config.warn("Invalid range list: " + str);
               return null;
            }

            rangeList.addRange(range);
         }

         return rangeList;
      }
   }

   private RangeInt parseRangeInt(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.trim();
         int countMinus = str.length() - str.replace("-", "").length();
         if (countMinus > 1) {
            Config.warn("Invalid range: " + str);
            return null;
         } else {
            String[] tokens = Config.tokenize(str, "- ");
            int[] vals = new int[tokens.length];

            int val;
            for(val = 0; val < tokens.length; ++val) {
               String token = tokens[val];
               int val = Config.parseInt(token, -1);
               if (val < 0) {
                  Config.warn("Invalid range: " + str);
                  return null;
               }

               vals[val] = val;
            }

            if (vals.length == 1) {
               val = vals[0];
               if (str.startsWith("-")) {
                  return new RangeInt(0, val);
               } else if (str.endsWith("-")) {
                  return new RangeInt(val, 65535);
               } else {
                  return new RangeInt(val, val);
               }
            } else if (vals.length == 2) {
               val = Math.min(vals[0], vals[1]);
               int max = Math.max(vals[0], vals[1]);
               return new RangeInt(val, max);
            } else {
               Config.warn("Invalid range: " + str);
               return null;
            }
         }
      }
   }

   private NbtTagValue[] parseNbtTagValues(Properties props) {
      String PREFIX_NBT = "nbt.";
      Map mapNbt = getMatchingProperties(props, PREFIX_NBT);
      if (mapNbt.size() <= 0) {
         return null;
      } else {
         List listNbts = new ArrayList();
         Set keySet = mapNbt.keySet();
         Iterator it = keySet.iterator();

         while(it.hasNext()) {
            String key = (String)it.next();
            String val = (String)mapNbt.get(key);
            String id = key.substring(PREFIX_NBT.length());
            NbtTagValue nbt = new NbtTagValue(id, val);
            listNbts.add(nbt);
         }

         NbtTagValue[] nbts = (NbtTagValue[])((NbtTagValue[])listNbts.toArray(new NbtTagValue[listNbts.size()]));
         return nbts;
      }
   }

   private static Map getMatchingProperties(Properties props, String keyPrefix) {
      Map map = new LinkedHashMap();
      Set keySet = props.keySet();
      Iterator it = keySet.iterator();

      while(it.hasNext()) {
         String key = (String)it.next();
         String val = props.getProperty(key);
         if (key.startsWith(keyPrefix)) {
            map.put(key, val);
         }
      }

      return map;
   }

   private int parseHand(String str) {
      if (str == null) {
         return 0;
      } else {
         str = str.toLowerCase();
         if (str.equals("any")) {
            return 0;
         } else if (str.equals("main")) {
            return 1;
         } else if (str.equals("off")) {
            return 2;
         } else {
            Config.warn("Invalid hand: " + str);
            return 0;
         }
      }
   }

   public boolean isValid(String path) {
      if (this.name != null && this.name.length() > 0) {
         if (this.basePath == null) {
            Config.warn("No base path found: " + path);
            return false;
         } else if (this.type == 0) {
            Config.warn("No type defined: " + path);
            return false;
         } else {
            if (this.type == 1 || this.type == 3) {
               if (this.items == null) {
                  this.items = this.detectItems();
               }

               if (this.items == null) {
                  Config.warn("No items defined: " + path);
                  return false;
               }
            }

            if (this.texture == null && this.mapTextures == null && this.model == null && this.mapModels == null) {
               Config.warn("No texture or model specified: " + path);
               return false;
            } else if (this.type == 2 && this.enchantmentIds == null) {
               Config.warn("No enchantmentIDs specified: " + path);
               return false;
            } else {
               return true;
            }
         }
      } else {
         Config.warn("No name found: " + path);
         return false;
      }
   }

   private int[] detectItems() {
      Item item = Item.func_111206_d(this.name);
      if (item == null) {
         return null;
      } else {
         int id = Item.func_150891_b(item);
         return id <= 0 ? null : new int[]{id};
      }
   }

   public void updateIcons(TextureMap textureMap) {
      if (this.texture != null) {
         this.textureLocation = this.getTextureLocation(this.texture);
         if (this.type == 1) {
            ResourceLocation spriteLocation = this.getSpriteLocation(this.textureLocation);
            this.sprite = textureMap.func_174942_a(spriteLocation);
         }
      }

      if (this.mapTextures != null) {
         this.mapTextureLocations = new HashMap();
         this.mapSprites = new HashMap();
         Set keySet = this.mapTextures.keySet();
         Iterator it = keySet.iterator();

         while(it.hasNext()) {
            String key = (String)it.next();
            String val = (String)this.mapTextures.get(key);
            ResourceLocation locTex = this.getTextureLocation(val);
            this.mapTextureLocations.put(key, locTex);
            if (this.type == 1) {
               ResourceLocation locSprite = this.getSpriteLocation(locTex);
               TextureAtlasSprite icon = textureMap.func_174942_a(locSprite);
               this.mapSprites.put(key, icon);
            }
         }
      }

   }

   private ResourceLocation getTextureLocation(String texName) {
      if (texName == null) {
         return null;
      } else {
         ResourceLocation resLoc = new ResourceLocation(texName);
         String domain = resLoc.func_110624_b();
         String path = resLoc.func_110623_a();
         if (!path.contains("/")) {
            path = "textures/items/" + path;
         }

         String filePath = path + ".png";
         ResourceLocation locFile = new ResourceLocation(domain, filePath);
         boolean exists = Config.hasResource(locFile);
         if (!exists) {
            Config.warn("File not found: " + filePath);
         }

         return locFile;
      }
   }

   private ResourceLocation getSpriteLocation(ResourceLocation resLoc) {
      String pathTex = resLoc.func_110623_a();
      pathTex = StrUtils.removePrefix(pathTex, "textures/");
      pathTex = StrUtils.removeSuffix(pathTex, ".png");
      ResourceLocation locTex = new ResourceLocation(resLoc.func_110624_b(), pathTex);
      return locTex;
   }

   public void updateModelTexture(TextureMap textureMap, ItemModelGenerator itemModelGenerator) {
      if (this.texture != null || this.mapTextures != null) {
         String[] textures = this.getModelTextures();
         boolean useTint = this.isUseTint();
         this.bakedModelTexture = makeBakedModel(textureMap, itemModelGenerator, textures, useTint);
         if (this.type == 1 && this.mapTextures != null) {
            Set<String> keySet = this.mapTextures.keySet();
            Iterator it = keySet.iterator();

            while(true) {
               String tex;
               String path;
               do {
                  if (!it.hasNext()) {
                     return;
                  }

                  String key = (String)it.next();
                  tex = (String)this.mapTextures.get(key);
                  path = StrUtils.removePrefix(key, "texture.");
               } while(!path.startsWith("bow") && !path.startsWith("fishing_rod") && !path.startsWith("shield"));

               String[] texNames = new String[]{tex};
               IBakedModel modelTex = makeBakedModel(textureMap, itemModelGenerator, texNames, useTint);
               if (this.mapBakedModelsTexture == null) {
                  this.mapBakedModelsTexture = new HashMap();
               }

               String location = "item/" + path;
               this.mapBakedModelsTexture.put(location, modelTex);
            }
         }
      }
   }

   private boolean isUseTint() {
      return true;
   }

   private static IBakedModel makeBakedModel(TextureMap textureMap, ItemModelGenerator itemModelGenerator, String[] textures, boolean useTint) {
      String[] spriteNames = new String[textures.length];

      for(int i = 0; i < spriteNames.length; ++i) {
         String texture = textures[i];
         spriteNames[i] = StrUtils.removePrefix(texture, "textures/");
      }

      ModelBlock modelBlockBase = makeModelBlock(spriteNames);
      ModelBlock modelBlock = itemModelGenerator.func_178392_a(textureMap, modelBlockBase);
      IBakedModel model = bakeModel(textureMap, modelBlock, useTint);
      return model;
   }

   private String[] getModelTextures() {
      if (this.type == 1 && this.items.length == 1) {
         Item item = Item.func_150899_d(this.items[0]);
         String key;
         String texMain;
         if (item == Items.field_151068_bn && this.damage != null && this.damage.getCountRanges() > 0) {
            RangeInt range = this.damage.getRange(0);
            int valDamage = range.getMin();
            boolean splash = (valDamage & 16384) != 0;
            key = this.getMapTexture(this.mapTextures, "texture.potion_overlay", "items/potion_overlay");
            texMain = null;
            if (splash) {
               texMain = this.getMapTexture(this.mapTextures, "texture.potion_bottle_splash", "items/potion_bottle_splash");
            } else {
               texMain = this.getMapTexture(this.mapTextures, "texture.potion_bottle_drinkable", "items/potion_bottle_drinkable");
            }

            return new String[]{key, texMain};
         }

         if (item instanceof ItemArmor) {
            ItemArmor itemArmor = (ItemArmor)item;
            if (itemArmor.func_82812_d() == ItemArmor.ArmorMaterial.LEATHER) {
               String material = "leather";
               String type = "helmet";
               if (itemArmor.field_77881_a == 0) {
                  type = "helmet";
               }

               if (itemArmor.field_77881_a == 1) {
                  type = "chestplate";
               }

               if (itemArmor.field_77881_a == 2) {
                  type = "leggings";
               }

               if (itemArmor.field_77881_a == 3) {
                  type = "boots";
               }

               key = material + "_" + type;
               texMain = this.getMapTexture(this.mapTextures, "texture." + key, "items/" + key);
               String texOverlay = this.getMapTexture(this.mapTextures, "texture." + key + "_overlay", "items/" + key + "_overlay");
               return new String[]{texMain, texOverlay};
            }
         }
      }

      return new String[]{this.texture};
   }

   private String getMapTexture(Map<String, String> map, String key, String def) {
      if (map == null) {
         return def;
      } else {
         String str = (String)map.get(key);
         return str == null ? def : str;
      }
   }

   private static ModelBlock makeModelBlock(String[] modelTextures) {
      StringBuffer sb = new StringBuffer();
      sb.append("{\"parent\": \"builtin/generated\",\"textures\": {");

      for(int i = 0; i < modelTextures.length; ++i) {
         String modelTexture = modelTextures[i];
         if (i > 0) {
            sb.append(", ");
         }

         sb.append("\"layer" + i + "\": \"" + modelTexture + "\"");
      }

      sb.append("}}");
      String modelStr = sb.toString();
      ModelBlock model = ModelBlock.func_178294_a(modelStr);
      return model;
   }

   private static IBakedModel bakeModel(TextureMap textureMap, ModelBlock modelBlockIn, boolean useTint) {
      ModelRotation modelRotationIn = ModelRotation.X0_Y0;
      boolean uvLocked = false;
      String spriteParticleName = modelBlockIn.func_178308_c("particle");
      TextureAtlasSprite var4 = textureMap.func_110572_b((new ResourceLocation(spriteParticleName)).toString());
      SimpleBakedModel.Builder var5 = (new SimpleBakedModel.Builder(modelBlockIn)).func_177646_a(var4);
      Iterator var6 = modelBlockIn.func_178298_a().iterator();

      while(var6.hasNext()) {
         BlockPart var7 = (BlockPart)var6.next();
         Iterator var8 = var7.field_178240_c.keySet().iterator();

         while(var8.hasNext()) {
            EnumFacing var9 = (EnumFacing)var8.next();
            BlockPartFace var10 = (BlockPartFace)var7.field_178240_c.get(var9);
            if (!useTint) {
               var10 = new BlockPartFace(var10.field_178244_b, -1, var10.field_178242_d, var10.field_178243_e);
            }

            String spriteName = modelBlockIn.func_178308_c(var10.field_178242_d);
            TextureAtlasSprite var11 = textureMap.func_110572_b((new ResourceLocation(spriteName)).toString());
            BakedQuad quad = makeBakedQuad(var7, var10, var11, var9, modelRotationIn, uvLocked);
            if (var10.field_178244_b == null) {
               var5.func_177648_a(quad);
            } else {
               var5.func_177650_a(modelRotationIn.func_177523_a(var10.field_178244_b), quad);
            }
         }
      }

      return var5.func_177645_b();
   }

   private static BakedQuad makeBakedQuad(BlockPart blockPart, BlockPartFace blockPartFace, TextureAtlasSprite textureAtlasSprite, EnumFacing enumFacing, ModelRotation modelRotation, boolean uvLocked) {
      FaceBakery faceBakery = new FaceBakery();
      return faceBakery.func_178414_a(blockPart.field_178241_a, blockPart.field_178239_b, blockPartFace, textureAtlasSprite, enumFacing, modelRotation, blockPart.field_178237_d, uvLocked, blockPart.field_178238_e);
   }

   public String toString() {
      return "" + this.basePath + "/" + this.name + ", type: " + this.type + ", items: [" + Config.arrayToString(this.items) + "], textture: " + this.texture;
   }

   public float getTextureWidth(TextureManager textureManager) {
      if (this.textureWidth <= 0) {
         if (this.textureLocation != null) {
            ITextureObject tex = textureManager.func_110581_b(this.textureLocation);
            int texId = tex.func_110552_b();
            int prevTexId = GlStateManager.getBoundTexture();
            GlStateManager.func_179144_i(texId);
            this.textureWidth = GL11.glGetTexLevelParameteri(3553, 0, 4096);
            GlStateManager.func_179144_i(prevTexId);
         }

         if (this.textureWidth <= 0) {
            this.textureWidth = 16;
         }
      }

      return (float)this.textureWidth;
   }

   public float getTextureHeight(TextureManager textureManager) {
      if (this.textureHeight <= 0) {
         if (this.textureLocation != null) {
            ITextureObject tex = textureManager.func_110581_b(this.textureLocation);
            int texId = tex.func_110552_b();
            int prevTexId = GlStateManager.getBoundTexture();
            GlStateManager.func_179144_i(texId);
            this.textureHeight = GL11.glGetTexLevelParameteri(3553, 0, 4097);
            GlStateManager.func_179144_i(prevTexId);
         }

         if (this.textureHeight <= 0) {
            this.textureHeight = 16;
         }
      }

      return (float)this.textureHeight;
   }

   public IBakedModel getBakedModel(ResourceLocation modelLocation, boolean fullModel) {
      IBakedModel bakedModel;
      Map mapBakedModels;
      if (fullModel) {
         bakedModel = this.bakedModelFull;
         mapBakedModels = this.mapBakedModelsFull;
      } else {
         bakedModel = this.bakedModelTexture;
         mapBakedModels = this.mapBakedModelsTexture;
      }

      if (modelLocation != null && mapBakedModels != null) {
         String modelPath = modelLocation.func_110623_a();
         IBakedModel customModel = (IBakedModel)mapBakedModels.get(modelPath);
         if (customModel != null) {
            return customModel;
         }
      }

      return bakedModel;
   }

   public void loadModels(ModelBakery modelBakery) {
      if (this.model != null) {
         loadItemModel(modelBakery, this.model);
      }

      if (this.type == 1 && this.mapModels != null) {
         Set<String> keySet = this.mapModels.keySet();
         Iterator it = keySet.iterator();

         while(true) {
            String mod;
            String path;
            do {
               if (!it.hasNext()) {
                  return;
               }

               String key = (String)it.next();
               mod = (String)this.mapModels.get(key);
               path = StrUtils.removePrefix(key, "model.");
            } while(!path.startsWith("bow") && !path.startsWith("fishing_rod") && !path.startsWith("shield"));

            loadItemModel(modelBakery, mod);
         }
      }
   }

   public void updateModelsFull() {
      ModelManager modelManager = Config.getModelManager();
      IBakedModel missingModel = modelManager.func_174951_a();
      if (this.model != null) {
         ResourceLocation locItem = getModelLocation(this.model);
         ModelResourceLocation mrl = new ModelResourceLocation(locItem, "inventory");
         this.bakedModelFull = modelManager.func_174953_a(mrl);
         if (this.bakedModelFull == missingModel) {
            Config.warn("Custom Items: Model not found " + mrl.func_110623_a());
            this.bakedModelFull = null;
         }
      }

      if (this.type == 1 && this.mapModels != null) {
         Set<String> keySet = this.mapModels.keySet();
         Iterator it = keySet.iterator();

         while(true) {
            String mod;
            String path;
            do {
               if (!it.hasNext()) {
                  return;
               }

               String key = (String)it.next();
               mod = (String)this.mapModels.get(key);
               path = StrUtils.removePrefix(key, "model.");
            } while(!path.startsWith("bow") && !path.startsWith("fishing_rod") && !path.startsWith("shield"));

            ResourceLocation locItem = getModelLocation(mod);
            ModelResourceLocation mrl = new ModelResourceLocation(locItem, "inventory");
            IBakedModel bm = modelManager.func_174953_a(mrl);
            if (bm == missingModel) {
               Config.warn("Custom Items: Model not found " + mrl.func_110623_a());
            } else {
               if (this.mapBakedModelsFull == null) {
                  this.mapBakedModelsFull = new HashMap();
               }

               String location = "item/" + path;
               this.mapBakedModelsFull.put(location, bm);
            }
         }
      }
   }

   private static void loadItemModel(ModelBakery modelBakery, String model) {
      ResourceLocation locItem = getModelLocation(model);
      ModelResourceLocation mrl = new ModelResourceLocation(locItem, "inventory");
      if (Reflector.ModelLoader.exists()) {
         try {
            Object vanillaLoader = Reflector.ModelLoader_VanillaLoader_INSTANCE.getValue();
            checkNull(vanillaLoader, "vanillaLoader is null");
            Object iModel = Reflector.call(vanillaLoader, Reflector.ModelLoader_VanillaLoader_loadModel, mrl);
            checkNull(iModel, "iModel is null");
            Map stateModels = (Map)Reflector.getFieldValue(modelBakery, Reflector.ModelLoader_stateModels);
            checkNull(stateModels, "stateModels is null");
            stateModels.put(mrl, iModel);
            Set registryTextures = (Set)Reflector.getFieldValue(modelBakery, Reflector.ModelLoader_textures);
            checkNull(registryTextures, "registryTextures is null");
            Collection modelTextures = (Collection)Reflector.call(iModel, Reflector.IModel_getTextures);
            checkNull(modelTextures, "modelTextures is null");
            registryTextures.addAll(modelTextures);
         } catch (Exception var9) {
            Config.warn("Error registering model with ModelLoader: " + mrl + ", " + var9.getClass().getName() + ": " + var9.getMessage());
         }

      } else {
         modelBakery.loadItemModel(locItem.toString(), mrl, locItem);
      }
   }

   private static void checkNull(Object obj, String msg) throws NullPointerException {
      if (obj == null) {
         throw new NullPointerException(msg);
      }
   }

   private static ResourceLocation getModelLocation(String modelName) {
      return Reflector.ModelLoader.exists() && !modelName.startsWith("mcpatcher/") && !modelName.startsWith("optifine/") ? new ResourceLocation("models/" + modelName) : new ResourceLocation(modelName);
   }
}
