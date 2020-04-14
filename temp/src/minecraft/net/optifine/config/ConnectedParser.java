package net.optifine.config;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.src.Config;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.ConnectedProperties;
import net.optifine.util.EntityUtils;

public class ConnectedParser {
   private String context = null;
   public static final VillagerProfession[] PROFESSIONS_INVALID = new VillagerProfession[0];
   public static final EnumDyeColor[] DYE_COLORS_INVALID = new EnumDyeColor[0];
   private static final INameGetter<Enum> NAME_GETTER_ENUM = new INameGetter<Enum>() {
      public String getName(Enum en) {
         return en.name();
      }
   };
   private static final INameGetter<EnumDyeColor> NAME_GETTER_DYE_COLOR = new INameGetter<EnumDyeColor>() {
      public String getName(EnumDyeColor col) {
         return col.func_176610_l();
      }
   };

   public ConnectedParser(String context) {
      this.context = context;
   }

   public String parseName(String path) {
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

   public String parseBasePath(String path) {
      int pos = path.lastIndexOf(47);
      return pos < 0 ? "" : path.substring(0, pos);
   }

   public MatchBlock[] parseMatchBlocks(String propMatchBlocks) {
      if (propMatchBlocks == null) {
         return null;
      } else {
         List list = new ArrayList();
         String[] blockStrs = Config.tokenize(propMatchBlocks, " ");

         for(int i = 0; i < blockStrs.length; ++i) {
            String blockStr = blockStrs[i];
            MatchBlock[] mbs = this.parseMatchBlock(blockStr);
            if (mbs != null) {
               list.addAll(Arrays.asList(mbs));
            }
         }

         MatchBlock[] mbs = (MatchBlock[])((MatchBlock[])list.toArray(new MatchBlock[list.size()]));
         return mbs;
      }
   }

   public IBlockState parseBlockState(String str, IBlockState def) {
      MatchBlock[] mbs = this.parseMatchBlock(str);
      if (mbs == null) {
         return def;
      } else if (mbs.length != 1) {
         return def;
      } else {
         MatchBlock mb = mbs[0];
         int blockId = mb.getBlockId();
         Block block = Block.func_149729_e(blockId);
         return block.func_176223_P();
      }
   }

   public MatchBlock[] parseMatchBlock(String blockStr) {
      if (blockStr == null) {
         return null;
      } else {
         blockStr = blockStr.trim();
         if (blockStr.length() <= 0) {
            return null;
         } else {
            String[] parts = Config.tokenize(blockStr, ":");
            String domain = "minecraft";
            int blockIndex = false;
            byte blockIndex;
            if (parts.length > 1 && this.isFullBlockName(parts)) {
               domain = parts[0];
               blockIndex = 1;
            } else {
               domain = "minecraft";
               blockIndex = 0;
            }

            String blockPart = parts[blockIndex];
            String[] params = (String[])Arrays.copyOfRange(parts, blockIndex + 1, parts.length);
            Block[] blocks = this.parseBlockPart(domain, blockPart);
            if (blocks == null) {
               return null;
            } else {
               MatchBlock[] datas = new MatchBlock[blocks.length];

               for(int i = 0; i < blocks.length; ++i) {
                  Block block = blocks[i];
                  int blockId = Block.func_149682_b(block);
                  int[] metadatas = null;
                  if (params.length > 0) {
                     metadatas = this.parseBlockMetadatas(block, params);
                     if (metadatas == null) {
                        return null;
                     }
                  }

                  MatchBlock bd = new MatchBlock(blockId, metadatas);
                  datas[i] = bd;
               }

               return datas;
            }
         }
      }
   }

   public boolean isFullBlockName(String[] parts) {
      if (parts.length < 2) {
         return false;
      } else {
         String part1 = parts[1];
         if (part1.length() < 1) {
            return false;
         } else if (this.startsWithDigit(part1)) {
            return false;
         } else {
            return !part1.contains("=");
         }
      }
   }

   public boolean startsWithDigit(String str) {
      if (str == null) {
         return false;
      } else if (str.length() < 1) {
         return false;
      } else {
         char ch = str.charAt(0);
         return Character.isDigit(ch);
      }
   }

   public Block[] parseBlockPart(String domain, String blockPart) {
      if (this.startsWithDigit(blockPart)) {
         int[] ids = this.parseIntList(blockPart);
         if (ids == null) {
            return null;
         } else {
            Block[] blocks = new Block[ids.length];

            for(int i = 0; i < ids.length; ++i) {
               int id = ids[i];
               Block block = Block.func_149729_e(id);
               if (block == null) {
                  this.warn("Block not found for id: " + id);
                  return null;
               }

               blocks[i] = block;
            }

            return blocks;
         }
      } else {
         String fullName = domain + ":" + blockPart;
         Block block = Block.func_149684_b(fullName);
         if (block == null) {
            this.warn("Block not found for name: " + fullName);
            return null;
         } else {
            Block[] blocks = new Block[]{block};
            return blocks;
         }
      }
   }

   public int[] parseBlockMetadatas(Block block, String[] params) {
      if (params.length <= 0) {
         return null;
      } else {
         String param0 = params[0];
         if (this.startsWithDigit(param0)) {
            int[] mds = this.parseIntList(param0);
            return mds;
         } else {
            IBlockState stateDefault = block.func_176223_P();
            Collection properties = stateDefault.func_177227_a();
            Map<IProperty, List<Comparable>> mapPropValues = new HashMap();

            for(int i = 0; i < params.length; ++i) {
               String param = params[i];
               if (param.length() > 0) {
                  String[] parts = Config.tokenize(param, "=");
                  if (parts.length != 2) {
                     this.warn("Invalid block property: " + param);
                     return null;
                  }

                  String key = parts[0];
                  String valStr = parts[1];
                  IProperty prop = ConnectedProperties.getProperty(key, properties);
                  if (prop == null) {
                     this.warn("Property not found: " + key + ", block: " + block);
                     return null;
                  }

                  List<Comparable> list = (List)mapPropValues.get(key);
                  if (list == null) {
                     list = new ArrayList();
                     mapPropValues.put(prop, list);
                  }

                  String[] vals = Config.tokenize(valStr, ",");

                  for(int v = 0; v < vals.length; ++v) {
                     String val = vals[v];
                     Comparable propVal = parsePropertyValue(prop, val);
                     if (propVal == null) {
                        this.warn("Property value not found: " + val + ", property: " + key + ", block: " + block);
                        return null;
                     }

                     ((List)list).add(propVal);
                  }
               }
            }

            if (mapPropValues.isEmpty()) {
               return null;
            } else {
               List<Integer> listMetadatas = new ArrayList();

               int i;
               for(int i = 0; i < 16; ++i) {
                  i = i;

                  try {
                     IBlockState bs = this.getStateFromMeta(block, i);
                     if (this.matchState(bs, mapPropValues)) {
                        listMetadatas.add(i);
                     }
                  } catch (IllegalArgumentException var18) {
                  }
               }

               if (listMetadatas.size() == 16) {
                  return null;
               } else {
                  int[] metadatas = new int[listMetadatas.size()];

                  for(i = 0; i < metadatas.length; ++i) {
                     metadatas[i] = (Integer)listMetadatas.get(i);
                  }

                  return metadatas;
               }
            }
         }
      }
   }

   private IBlockState getStateFromMeta(Block block, int md) {
      try {
         IBlockState bs = block.func_176203_a(md);
         if (block == Blocks.field_150398_cm && md > 7) {
            IBlockState bsLow = block.func_176203_a(md & 7);
            bs = bs.func_177226_a(BlockDoublePlant.field_176493_a, bsLow.func_177229_b(BlockDoublePlant.field_176493_a));
         }

         return bs;
      } catch (IllegalArgumentException var5) {
         return block.func_176223_P();
      }
   }

   public static Comparable parsePropertyValue(IProperty prop, String valStr) {
      Class valueClass = prop.func_177699_b();
      Comparable valueObj = parseValue(valStr, valueClass);
      if (valueObj == null) {
         Collection propertyValues = prop.func_177700_c();
         valueObj = getPropertyValue(valStr, propertyValues);
      }

      return valueObj;
   }

   public static Comparable getPropertyValue(String value, Collection propertyValues) {
      Iterator it = propertyValues.iterator();

      Comparable obj;
      do {
         if (!it.hasNext()) {
            return null;
         }

         obj = (Comparable)it.next();
      } while(!getValueName(obj).equals(value));

      return obj;
   }

   private static Object getValueName(Comparable obj) {
      if (obj instanceof IStringSerializable) {
         IStringSerializable iss = (IStringSerializable)obj;
         return iss.func_176610_l();
      } else {
         return obj.toString();
      }
   }

   public static Comparable parseValue(String str, Class cls) {
      if (cls == String.class) {
         return str;
      } else if (cls == Boolean.class) {
         return Boolean.valueOf(str);
      } else if (cls == Float.class) {
         return Float.valueOf(str);
      } else if (cls == Double.class) {
         return Double.valueOf(str);
      } else if (cls == Integer.class) {
         return Integer.valueOf(str);
      } else {
         return cls == Long.class ? Long.valueOf(str) : null;
      }
   }

   public boolean matchState(IBlockState bs, Map<IProperty, List<Comparable>> mapPropValues) {
      Set<IProperty> keys = mapPropValues.keySet();
      Iterator it = keys.iterator();

      List vals;
      Comparable bsVal;
      do {
         if (!it.hasNext()) {
            return true;
         }

         IProperty prop = (IProperty)it.next();
         vals = (List)mapPropValues.get(prop);
         bsVal = bs.func_177229_b(prop);
         if (bsVal == null) {
            return false;
         }
      } while(vals.contains(bsVal));

      return false;
   }

   public BiomeGenBase[] parseBiomes(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.trim();
         boolean negative = false;
         if (str.startsWith("!")) {
            negative = true;
            str = str.substring(1);
         }

         String[] biomeNames = Config.tokenize(str, " ");
         List list = new ArrayList();

         for(int i = 0; i < biomeNames.length; ++i) {
            String biomeName = biomeNames[i];
            BiomeGenBase biome = this.findBiome(biomeName);
            if (biome == null) {
               this.warn("Biome not found: " + biomeName);
            } else {
               list.add(biome);
            }
         }

         if (negative) {
            List<BiomeGenBase> listAllBiomes = new ArrayList(Arrays.asList(BiomeGenBase.func_150565_n()));
            listAllBiomes.removeAll(list);
            list = listAllBiomes;
         }

         BiomeGenBase[] biomeArr = (BiomeGenBase[])((BiomeGenBase[])list.toArray(new BiomeGenBase[list.size()]));
         return biomeArr;
      }
   }

   public BiomeGenBase findBiome(String biomeName) {
      biomeName = biomeName.toLowerCase();
      if (biomeName.equals("nether")) {
         return BiomeGenBase.field_76778_j;
      } else {
         BiomeGenBase[] biomeList = BiomeGenBase.func_150565_n();

         for(int i = 0; i < biomeList.length; ++i) {
            BiomeGenBase biome = biomeList[i];
            if (biome != null) {
               String name = biome.field_76791_y.replace(" ", "").toLowerCase();
               if (name.equals(biomeName)) {
                  return biome;
               }
            }
         }

         return null;
      }
   }

   public int parseInt(String str, int defVal) {
      if (str == null) {
         return defVal;
      } else {
         str = str.trim();
         int num = Config.parseInt(str, -1);
         if (num < 0) {
            this.warn("Invalid number: " + str);
            return defVal;
         } else {
            return num;
         }
      }
   }

   public int[] parseIntList(String str) {
      if (str == null) {
         return null;
      } else {
         List<Integer> list = new ArrayList();
         String[] intStrs = Config.tokenize(str, " ,");

         for(int i = 0; i < intStrs.length; ++i) {
            String intStr = intStrs[i];
            if (intStr.contains("-")) {
               String[] subStrs = Config.tokenize(intStr, "-");
               if (subStrs.length != 2) {
                  this.warn("Invalid interval: " + intStr + ", when parsing: " + str);
               } else {
                  int min = Config.parseInt(subStrs[0], -1);
                  int max = Config.parseInt(subStrs[1], -1);
                  if (min >= 0 && max >= 0 && min <= max) {
                     for(int n = min; n <= max; ++n) {
                        list.add(n);
                     }
                  } else {
                     this.warn("Invalid interval: " + intStr + ", when parsing: " + str);
                  }
               }
            } else {
               int val = Config.parseInt(intStr, -1);
               if (val < 0) {
                  this.warn("Invalid number: " + intStr + ", when parsing: " + str);
               } else {
                  list.add(val);
               }
            }
         }

         int[] ints = new int[list.size()];

         for(int i = 0; i < ints.length; ++i) {
            ints[i] = (Integer)list.get(i);
         }

         return ints;
      }
   }

   public boolean[] parseFaces(String str, boolean[] defVal) {
      if (str == null) {
         return defVal;
      } else {
         EnumSet setFaces = EnumSet.allOf(EnumFacing.class);
         String[] faceStrs = Config.tokenize(str, " ,");

         for(int i = 0; i < faceStrs.length; ++i) {
            String faceStr = faceStrs[i];
            if (faceStr.equals("sides")) {
               setFaces.add(EnumFacing.NORTH);
               setFaces.add(EnumFacing.SOUTH);
               setFaces.add(EnumFacing.WEST);
               setFaces.add(EnumFacing.EAST);
            } else if (faceStr.equals("all")) {
               setFaces.addAll(Arrays.asList(EnumFacing.field_82609_l));
            } else {
               EnumFacing face = this.parseFace(faceStr);
               if (face != null) {
                  setFaces.add(face);
               }
            }
         }

         boolean[] faces = new boolean[EnumFacing.field_82609_l.length];

         for(int i = 0; i < faces.length; ++i) {
            faces[i] = setFaces.contains(EnumFacing.field_82609_l[i]);
         }

         return faces;
      }
   }

   public EnumFacing parseFace(String str) {
      str = str.toLowerCase();
      if (!str.equals("bottom") && !str.equals("down")) {
         if (!str.equals("top") && !str.equals("up")) {
            if (str.equals("north")) {
               return EnumFacing.NORTH;
            } else if (str.equals("south")) {
               return EnumFacing.SOUTH;
            } else if (str.equals("east")) {
               return EnumFacing.EAST;
            } else if (str.equals("west")) {
               return EnumFacing.WEST;
            } else {
               Config.warn("Unknown face: " + str);
               return null;
            }
         } else {
            return EnumFacing.UP;
         }
      } else {
         return EnumFacing.DOWN;
      }
   }

   public void dbg(String str) {
      Config.dbg("" + this.context + ": " + str);
   }

   public void warn(String str) {
      Config.warn("" + this.context + ": " + str);
   }

   public RangeListInt parseRangeListInt(String str) {
      if (str == null) {
         return null;
      } else {
         RangeListInt list = new RangeListInt();
         String[] parts = Config.tokenize(str, " ,");

         for(int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            RangeInt ri = this.parseRangeInt(part);
            if (ri == null) {
               return null;
            }

            list.addRange(ri);
         }

         return list;
      }
   }

   private RangeInt parseRangeInt(String str) {
      if (str == null) {
         return null;
      } else if (str.indexOf(45) >= 0) {
         String[] parts = Config.tokenize(str, "-");
         if (parts.length != 2) {
            this.warn("Invalid range: " + str);
            return null;
         } else {
            int min = Config.parseInt(parts[0], -1);
            int max = Config.parseInt(parts[1], -1);
            if (min >= 0 && max >= 0) {
               return new RangeInt(min, max);
            } else {
               this.warn("Invalid range: " + str);
               return null;
            }
         }
      } else {
         int val = Config.parseInt(str, -1);
         if (val < 0) {
            this.warn("Invalid integer: " + str);
            return null;
         } else {
            return new RangeInt(val, val);
         }
      }
   }

   public boolean parseBoolean(String str, boolean defVal) {
      if (str == null) {
         return defVal;
      } else {
         String strLower = str.toLowerCase().trim();
         if (strLower.equals("true")) {
            return true;
         } else if (strLower.equals("false")) {
            return false;
         } else {
            this.warn("Invalid boolean: " + str);
            return defVal;
         }
      }
   }

   public Boolean parseBooleanObject(String str) {
      if (str == null) {
         return null;
      } else {
         String strLower = str.toLowerCase().trim();
         if (strLower.equals("true")) {
            return Boolean.TRUE;
         } else if (strLower.equals("false")) {
            return Boolean.FALSE;
         } else {
            this.warn("Invalid boolean: " + str);
            return null;
         }
      }
   }

   public static int parseColor(String str, int defVal) {
      if (str == null) {
         return defVal;
      } else {
         str = str.trim();

         try {
            int val = Integer.parseInt(str, 16) & 16777215;
            return val;
         } catch (NumberFormatException var3) {
            return defVal;
         }
      }
   }

   public static int parseColor4(String str, int defVal) {
      if (str == null) {
         return defVal;
      } else {
         str = str.trim();

         try {
            int val = (int)(Long.parseLong(str, 16) & -1L);
            return val;
         } catch (NumberFormatException var3) {
            return defVal;
         }
      }
   }

   public EnumWorldBlockLayer parseBlockRenderLayer(String str, EnumWorldBlockLayer def) {
      if (str == null) {
         return def;
      } else {
         str = str.toLowerCase().trim();
         EnumWorldBlockLayer[] layers = EnumWorldBlockLayer.values();

         for(int i = 0; i < layers.length; ++i) {
            EnumWorldBlockLayer layer = layers[i];
            if (str.equals(layer.name().toLowerCase())) {
               return layer;
            }
         }

         return def;
      }
   }

   public <T> T parseObject(String str, T[] objs, INameGetter nameGetter, String property) {
      if (str == null) {
         return null;
      } else {
         String strLower = str.toLowerCase().trim();

         for(int i = 0; i < objs.length; ++i) {
            T obj = objs[i];
            String name = nameGetter.getName(obj);
            if (name != null && name.toLowerCase().equals(strLower)) {
               return obj;
            }
         }

         this.warn("Invalid " + property + ": " + str);
         return null;
      }
   }

   public <T> T[] parseObjects(String str, T[] objs, INameGetter nameGetter, String property, T[] errValue) {
      if (str == null) {
         return null;
      } else {
         str = str.toLowerCase().trim();
         String[] parts = Config.tokenize(str, " ");
         T[] arr = (Object[])((Object[])Array.newInstance(objs.getClass().getComponentType(), parts.length));

         for(int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            T obj = this.parseObject(part, objs, nameGetter, property);
            if (obj == null) {
               return errValue;
            }

            arr[i] = obj;
         }

         return arr;
      }
   }

   public Enum parseEnum(String str, Enum[] enums, String property) {
      return (Enum)this.parseObject(str, enums, NAME_GETTER_ENUM, property);
   }

   public Enum[] parseEnums(String str, Enum[] enums, String property, Enum[] errValue) {
      return (Enum[])this.parseObjects(str, enums, NAME_GETTER_ENUM, property, errValue);
   }

   public EnumDyeColor[] parseDyeColors(String str, String property, EnumDyeColor[] errValue) {
      return (EnumDyeColor[])this.parseObjects(str, EnumDyeColor.values(), NAME_GETTER_DYE_COLOR, property, errValue);
   }

   public Weather[] parseWeather(String str, String property, Weather[] errValue) {
      return (Weather[])this.parseObjects(str, Weather.values(), NAME_GETTER_ENUM, property, errValue);
   }

   public NbtTagValue parseNbtTagValue(String path, String value) {
      return path != null && value != null ? new NbtTagValue(path, value) : null;
   }

   public VillagerProfession[] parseProfessions(String profStr) {
      if (profStr == null) {
         return null;
      } else {
         List<VillagerProfession> list = new ArrayList();
         String[] tokens = Config.tokenize(profStr, " ");

         for(int i = 0; i < tokens.length; ++i) {
            String str = tokens[i];
            VillagerProfession prof = this.parseProfession(str);
            if (prof == null) {
               this.warn("Invalid profession: " + str);
               return PROFESSIONS_INVALID;
            }

            list.add(prof);
         }

         if (list.isEmpty()) {
            return null;
         } else {
            VillagerProfession[] arr = (VillagerProfession[])((VillagerProfession[])list.toArray(new VillagerProfession[list.size()]));
            return arr;
         }
      }
   }

   private VillagerProfession parseProfession(String str) {
      str = str.toLowerCase();
      String[] parts = Config.tokenize(str, ":");
      if (parts.length > 2) {
         return null;
      } else {
         String profStr = parts[0];
         String carStr = null;
         if (parts.length > 1) {
            carStr = parts[1];
         }

         int prof = parseProfessionId(profStr);
         if (prof < 0) {
            return null;
         } else {
            int[] cars = null;
            if (carStr != null) {
               cars = parseCareerIds(prof, carStr);
               if (cars == null) {
                  return null;
               }
            }

            return new VillagerProfession(prof, cars);
         }
      }
   }

   private static int parseProfessionId(String str) {
      int id = Config.parseInt(str, -1);
      if (id >= 0) {
         return id;
      } else if (str.equals("farmer")) {
         return 0;
      } else if (str.equals("librarian")) {
         return 1;
      } else if (str.equals("priest")) {
         return 2;
      } else if (str.equals("blacksmith")) {
         return 3;
      } else if (str.equals("butcher")) {
         return 4;
      } else {
         return str.equals("nitwit") ? 5 : -1;
      }
   }

   private static int[] parseCareerIds(int prof, String str) {
      Set<Integer> set = new HashSet();
      String[] parts = Config.tokenize(str, ",");

      int i;
      for(int i = 0; i < parts.length; ++i) {
         String part = parts[i];
         i = parseCareerId(prof, part);
         if (i < 0) {
            return null;
         }

         set.add(i);
      }

      Integer[] integerArr = (Integer[])((Integer[])set.toArray(new Integer[set.size()]));
      int[] arr = new int[integerArr.length];

      for(i = 0; i < arr.length; ++i) {
         arr[i] = integerArr[i];
      }

      return arr;
   }

   private static int parseCareerId(int prof, String str) {
      int id = Config.parseInt(str, -1);
      if (id >= 0) {
         return id;
      } else {
         if (prof == 0) {
            if (str.equals("farmer")) {
               return 1;
            }

            if (str.equals("fisherman")) {
               return 2;
            }

            if (str.equals("shepherd")) {
               return 3;
            }

            if (str.equals("fletcher")) {
               return 4;
            }
         }

         if (prof == 1) {
            if (str.equals("librarian")) {
               return 1;
            }

            if (str.equals("cartographer")) {
               return 2;
            }
         }

         if (prof == 2 && str.equals("cleric")) {
            return 1;
         } else {
            if (prof == 3) {
               if (str.equals("armor")) {
                  return 1;
               }

               if (str.equals("weapon")) {
                  return 2;
               }

               if (str.equals("tool")) {
                  return 3;
               }
            }

            if (prof == 4) {
               if (str.equals("butcher")) {
                  return 1;
               }

               if (str.equals("leather")) {
                  return 2;
               }
            }

            return prof == 5 && str.equals("nitwit") ? 1 : -1;
         }
      }
   }

   public int[] parseItems(String str) {
      str = str.trim();
      Set<Integer> setIds = new TreeSet();
      String[] tokens = Config.tokenize(str, " ");

      for(int i = 0; i < tokens.length; ++i) {
         String token = tokens[i];
         ResourceLocation loc = new ResourceLocation(token);
         Item item = (Item)Item.field_150901_e.func_82594_a(loc);
         if (item == null) {
            this.warn("Item not found: " + token);
         } else {
            int id = Item.func_150891_b(item);
            if (id < 0) {
               this.warn("Item has no ID: " + item + ", name: " + token);
            } else {
               setIds.add(new Integer(id));
            }
         }
      }

      Integer[] integers = (Integer[])((Integer[])setIds.toArray(new Integer[setIds.size()]));
      int[] ints = Config.toPrimitive(integers);
      return ints;
   }

   public int[] parseEntities(String str) {
      str = str.trim();
      Set<Integer> setIds = new TreeSet();
      String[] tokens = Config.tokenize(str, " ");

      for(int i = 0; i < tokens.length; ++i) {
         String token = tokens[i];
         int id = EntityUtils.getEntityIdByName(token);
         if (id < 0) {
            this.warn("Entity not found: " + token);
         } else {
            setIds.add(new Integer(id));
         }
      }

      Integer[] integers = (Integer[])((Integer[])setIds.toArray(new Integer[setIds.size()]));
      int[] ints = Config.toPrimitive(integers);
      return ints;
   }
}
