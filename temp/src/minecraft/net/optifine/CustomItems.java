package net.optifine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.NbtTagValue;
import net.optifine.render.Blender;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;

public class CustomItems {
   private static CustomItemProperties[][] itemProperties = (CustomItemProperties[][])null;
   private static CustomItemProperties[][] enchantmentProperties = (CustomItemProperties[][])null;
   private static Map mapPotionIds = null;
   private static ItemModelGenerator itemModelGenerator = new ItemModelGenerator();
   private static boolean useGlint = true;
   private static boolean renderOffHand = false;
   public static final int MASK_POTION_SPLASH = 16384;
   public static final int MASK_POTION_NAME = 63;
   public static final int MASK_POTION_EXTENDED = 64;
   public static final String KEY_TEXTURE_OVERLAY = "texture.potion_overlay";
   public static final String KEY_TEXTURE_SPLASH = "texture.potion_bottle_splash";
   public static final String KEY_TEXTURE_DRINKABLE = "texture.potion_bottle_drinkable";
   public static final String DEFAULT_TEXTURE_OVERLAY = "items/potion_overlay";
   public static final String DEFAULT_TEXTURE_SPLASH = "items/potion_bottle_splash";
   public static final String DEFAULT_TEXTURE_DRINKABLE = "items/potion_bottle_drinkable";
   private static final int[][] EMPTY_INT2_ARRAY = new int[0][];
   private static final String TYPE_POTION_NORMAL = "normal";
   private static final String TYPE_POTION_SPLASH = "splash";
   private static final String TYPE_POTION_LINGER = "linger";

   public static void update() {
      itemProperties = (CustomItemProperties[][])null;
      enchantmentProperties = (CustomItemProperties[][])null;
      useGlint = true;
      if (Config.isCustomItems()) {
         readCitProperties("mcpatcher/cit.properties");
         IResourcePack[] rps = Config.getResourcePacks();

         for(int i = rps.length - 1; i >= 0; --i) {
            IResourcePack rp = rps[i];
            update(rp);
         }

         update(Config.getDefaultResourcePack());
         if (itemProperties.length <= 0) {
            itemProperties = (CustomItemProperties[][])null;
         }

         if (enchantmentProperties.length <= 0) {
            enchantmentProperties = (CustomItemProperties[][])null;
         }

      }
   }

   private static void readCitProperties(String fileName) {
      try {
         ResourceLocation loc = new ResourceLocation(fileName);
         InputStream in = Config.getResourceStream(loc);
         if (in == null) {
            return;
         }

         Config.dbg("CustomItems: Loading " + fileName);
         Properties props = new PropertiesOrdered();
         props.load(in);
         in.close();
         useGlint = Config.parseBoolean(props.getProperty("useGlint"), true);
      } catch (FileNotFoundException var4) {
         return;
      } catch (IOException var5) {
         var5.printStackTrace();
      }

   }

   private static void update(IResourcePack rp) {
      String[] names = ResUtils.collectFiles(rp, (String)"mcpatcher/cit/", (String)".properties", (String[])null);
      Map mapAutoProperties = makeAutoImageProperties(rp);
      if (mapAutoProperties.size() > 0) {
         Set keySetAuto = mapAutoProperties.keySet();
         String[] keysAuto = (String[])((String[])keySetAuto.toArray(new String[keySetAuto.size()]));
         names = (String[])((String[])Config.addObjectsToArray(names, keysAuto));
      }

      Arrays.sort(names);
      List itemList = makePropertyList(itemProperties);
      List enchantmentList = makePropertyList(enchantmentProperties);

      for(int i = 0; i < names.length; ++i) {
         String name = names[i];
         Config.dbg("CustomItems: " + name);

         try {
            CustomItemProperties cip = null;
            if (mapAutoProperties.containsKey(name)) {
               cip = (CustomItemProperties)mapAutoProperties.get(name);
            }

            if (cip == null) {
               ResourceLocation locFile = new ResourceLocation(name);
               InputStream in = rp.func_110590_a(locFile);
               if (in == null) {
                  Config.warn("CustomItems file not found: " + name);
                  continue;
               }

               Properties props = new PropertiesOrdered();
               props.load(in);
               cip = new CustomItemProperties(props, name);
            }

            if (cip.isValid(name)) {
               addToItemList(cip, itemList);
               addToEnchantmentList(cip, enchantmentList);
            }
         } catch (FileNotFoundException var11) {
            Config.warn("CustomItems file not found: " + name);
         } catch (Exception var12) {
            var12.printStackTrace();
         }
      }

      itemProperties = propertyListToArray(itemList);
      enchantmentProperties = propertyListToArray(enchantmentList);
      Comparator comp = getPropertiesComparator();

      int i;
      CustomItemProperties[] cips;
      for(i = 0; i < itemProperties.length; ++i) {
         cips = itemProperties[i];
         if (cips != null) {
            Arrays.sort(cips, comp);
         }
      }

      for(i = 0; i < enchantmentProperties.length; ++i) {
         cips = enchantmentProperties[i];
         if (cips != null) {
            Arrays.sort(cips, comp);
         }
      }

   }

   private static Comparator getPropertiesComparator() {
      Comparator comp = new Comparator() {
         public int compare(Object o1, Object o2) {
            CustomItemProperties cip1 = (CustomItemProperties)o1;
            CustomItemProperties cip2 = (CustomItemProperties)o2;
            if (cip1.layer != cip2.layer) {
               return cip1.layer - cip2.layer;
            } else if (cip1.weight != cip2.weight) {
               return cip2.weight - cip1.weight;
            } else {
               return !cip1.basePath.equals(cip2.basePath) ? cip1.basePath.compareTo(cip2.basePath) : cip1.name.compareTo(cip2.name);
            }
         }
      };
      return comp;
   }

   public static void updateIcons(TextureMap textureMap) {
      List<CustomItemProperties> cips = getAllProperties();
      Iterator it = cips.iterator();

      while(it.hasNext()) {
         CustomItemProperties cip = (CustomItemProperties)it.next();
         cip.updateIcons(textureMap);
      }

   }

   public static void loadModels(ModelBakery modelBakery) {
      List<CustomItemProperties> cips = getAllProperties();
      Iterator it = cips.iterator();

      while(it.hasNext()) {
         CustomItemProperties cip = (CustomItemProperties)it.next();
         cip.loadModels(modelBakery);
      }

   }

   public static void updateModels() {
      List<CustomItemProperties> cips = getAllProperties();
      Iterator it = cips.iterator();

      while(it.hasNext()) {
         CustomItemProperties cip = (CustomItemProperties)it.next();
         if (cip.type == 1) {
            TextureMap textureMap = Minecraft.func_71410_x().func_147117_R();
            cip.updateModelTexture(textureMap, itemModelGenerator);
            cip.updateModelsFull();
         }
      }

   }

   private static List<CustomItemProperties> getAllProperties() {
      List<CustomItemProperties> list = new ArrayList();
      addAll(itemProperties, list);
      addAll(enchantmentProperties, list);
      return list;
   }

   private static void addAll(CustomItemProperties[][] cipsArr, List<CustomItemProperties> list) {
      if (cipsArr != null) {
         for(int i = 0; i < cipsArr.length; ++i) {
            CustomItemProperties[] cips = cipsArr[i];
            if (cips != null) {
               for(int k = 0; k < cips.length; ++k) {
                  CustomItemProperties cip = cips[k];
                  if (cip != null) {
                     list.add(cip);
                  }
               }
            }
         }

      }
   }

   private static Map makeAutoImageProperties(IResourcePack rp) {
      Map map = new HashMap();
      map.putAll(makePotionImageProperties(rp, "normal", Item.func_150891_b(Items.field_151068_bn)));
      map.putAll(makePotionImageProperties(rp, "splash", Item.func_150891_b(Items.field_151068_bn)));
      map.putAll(makePotionImageProperties(rp, "linger", Item.func_150891_b(Items.field_151068_bn)));
      return map;
   }

   private static Map makePotionImageProperties(IResourcePack rp, String type, int itemId) {
      Map map = new HashMap();
      String typePrefix = type + "/";
      String[] prefixes = new String[]{"mcpatcher/cit/potion/" + typePrefix, "mcpatcher/cit/Potion/" + typePrefix};
      String[] suffixes = new String[]{".png"};
      String[] names = ResUtils.collectFiles(rp, prefixes, suffixes);

      for(int i = 0; i < names.length; ++i) {
         String path = names[i];
         String name = StrUtils.removePrefixSuffix(path, prefixes, suffixes);
         Properties props = makePotionProperties(name, type, itemId, path);
         if (props != null) {
            String pathProp = StrUtils.removeSuffix(path, suffixes) + ".properties";
            CustomItemProperties cip = new CustomItemProperties(props, pathProp);
            map.put(pathProp, cip);
         }
      }

      return map;
   }

   private static Properties makePotionProperties(String name, String type, int itemId, String path) {
      if (StrUtils.endsWith(name, new String[]{"_n", "_s"})) {
         return null;
      } else if (name.equals("empty") && type.equals("normal")) {
         itemId = Item.func_150891_b(Items.field_151069_bo);
         Properties props = new PropertiesOrdered();
         props.put("type", "item");
         props.put("items", "" + itemId);
         return props;
      } else {
         int[] damages = (int[])((int[])getMapPotionIds().get(name));
         if (damages == null) {
            Config.warn("Potion not found for image: " + path);
            return null;
         } else {
            StringBuffer bufDamage = new StringBuffer();

            int i;
            for(i = 0; i < damages.length; ++i) {
               int damage = damages[i];
               if (type.equals("splash")) {
                  damage |= 16384;
               }

               if (i > 0) {
                  bufDamage.append(" ");
               }

               bufDamage.append(damage);
            }

            i = 16447;
            if (name.equals("water") || name.equals("mundane")) {
               i |= 64;
            }

            Properties props = new PropertiesOrdered();
            props.put("type", "item");
            props.put("items", "" + itemId);
            props.put("damage", "" + bufDamage.toString());
            props.put("damageMask", "" + i);
            if (type.equals("splash")) {
               props.put("texture.potion_bottle_splash", name);
            } else {
               props.put("texture.potion_bottle_drinkable", name);
            }

            return props;
         }
      }
   }

   private static Map getMapPotionIds() {
      if (mapPotionIds == null) {
         mapPotionIds = new LinkedHashMap();
         mapPotionIds.put("water", getPotionId(0, 0));
         mapPotionIds.put("awkward", getPotionId(0, 1));
         mapPotionIds.put("thick", getPotionId(0, 2));
         mapPotionIds.put("potent", getPotionId(0, 3));
         mapPotionIds.put("regeneration", getPotionIds(1));
         mapPotionIds.put("movespeed", getPotionIds(2));
         mapPotionIds.put("fireresistance", getPotionIds(3));
         mapPotionIds.put("poison", getPotionIds(4));
         mapPotionIds.put("heal", getPotionIds(5));
         mapPotionIds.put("nightvision", getPotionIds(6));
         mapPotionIds.put("clear", getPotionId(7, 0));
         mapPotionIds.put("bungling", getPotionId(7, 1));
         mapPotionIds.put("charming", getPotionId(7, 2));
         mapPotionIds.put("rank", getPotionId(7, 3));
         mapPotionIds.put("weakness", getPotionIds(8));
         mapPotionIds.put("damageboost", getPotionIds(9));
         mapPotionIds.put("moveslowdown", getPotionIds(10));
         mapPotionIds.put("leaping", getPotionIds(11));
         mapPotionIds.put("harm", getPotionIds(12));
         mapPotionIds.put("waterbreathing", getPotionIds(13));
         mapPotionIds.put("invisibility", getPotionIds(14));
         mapPotionIds.put("thin", getPotionId(15, 0));
         mapPotionIds.put("debonair", getPotionId(15, 1));
         mapPotionIds.put("sparkling", getPotionId(15, 2));
         mapPotionIds.put("stinky", getPotionId(15, 3));
         mapPotionIds.put("mundane", getPotionId(0, 4));
         mapPotionIds.put("speed", mapPotionIds.get("movespeed"));
         mapPotionIds.put("fire_resistance", mapPotionIds.get("fireresistance"));
         mapPotionIds.put("instant_health", mapPotionIds.get("heal"));
         mapPotionIds.put("night_vision", mapPotionIds.get("nightvision"));
         mapPotionIds.put("strength", mapPotionIds.get("damageboost"));
         mapPotionIds.put("slowness", mapPotionIds.get("moveslowdown"));
         mapPotionIds.put("instant_damage", mapPotionIds.get("harm"));
         mapPotionIds.put("water_breathing", mapPotionIds.get("waterbreathing"));
      }

      return mapPotionIds;
   }

   private static int[] getPotionIds(int baseId) {
      return new int[]{baseId, baseId + 16, baseId + 32, baseId + 48};
   }

   private static int[] getPotionId(int baseId, int subId) {
      return new int[]{baseId + subId * 16};
   }

   private static int getPotionNameDamage(String name) {
      String fullName = "potion." + name;
      Potion[] effectPotions = Potion.field_76425_a;

      for(int i = 0; i < effectPotions.length; ++i) {
         Potion potion = effectPotions[i];
         if (potion != null) {
            String potionName = potion.func_76393_a();
            if (fullName.equals(potionName)) {
               return potion.func_76396_c();
            }
         }
      }

      return -1;
   }

   private static List makePropertyList(CustomItemProperties[][] propsArr) {
      List list = new ArrayList();
      if (propsArr != null) {
         for(int i = 0; i < propsArr.length; ++i) {
            CustomItemProperties[] props = propsArr[i];
            List propList = null;
            if (props != null) {
               propList = new ArrayList(Arrays.asList(props));
            }

            list.add(propList);
         }
      }

      return list;
   }

   private static CustomItemProperties[][] propertyListToArray(List list) {
      CustomItemProperties[][] propArr = new CustomItemProperties[list.size()][];

      for(int i = 0; i < list.size(); ++i) {
         List subList = (List)list.get(i);
         if (subList != null) {
            CustomItemProperties[] subArr = (CustomItemProperties[])((CustomItemProperties[])subList.toArray(new CustomItemProperties[subList.size()]));
            Arrays.sort(subArr, new CustomItemsComparator());
            propArr[i] = subArr;
         }
      }

      return propArr;
   }

   private static void addToItemList(CustomItemProperties cp, List itemList) {
      if (cp.items != null) {
         for(int i = 0; i < cp.items.length; ++i) {
            int itemId = cp.items[i];
            if (itemId <= 0) {
               Config.warn("Invalid item ID: " + itemId);
            } else {
               addToList(cp, itemList, itemId);
            }
         }

      }
   }

   private static void addToEnchantmentList(CustomItemProperties cp, List enchantmentList) {
      if (cp.type == 2) {
         if (cp.enchantmentIds != null) {
            for(int i = 0; i < 256; ++i) {
               if (cp.enchantmentIds.isInRange(i)) {
                  addToList(cp, enchantmentList, i);
               }
            }

         }
      }
   }

   private static void addToList(CustomItemProperties cp, List list, int id) {
      while(id >= list.size()) {
         list.add((Object)null);
      }

      List subList = (List)list.get(id);
      if (subList == null) {
         subList = new ArrayList();
         list.set(id, subList);
      }

      ((List)subList).add(cp);
   }

   public static IBakedModel getCustomItemModel(ItemStack itemStack, IBakedModel model, ResourceLocation modelLocation, boolean fullModel) {
      if (!fullModel && model.func_177556_c()) {
         return model;
      } else if (itemProperties == null) {
         return model;
      } else {
         CustomItemProperties props = getCustomItemProperties(itemStack, 1);
         if (props == null) {
            return model;
         } else {
            IBakedModel customModel = props.getBakedModel(modelLocation, fullModel);
            return customModel != null ? customModel : model;
         }
      }
   }

   public static boolean bindCustomArmorTexture(ItemStack itemStack, int layer, String overlay) {
      if (itemProperties == null) {
         return false;
      } else {
         ResourceLocation loc = getCustomArmorLocation(itemStack, layer, overlay);
         if (loc == null) {
            return false;
         } else {
            Config.getTextureManager().func_110577_a(loc);
            return true;
         }
      }
   }

   private static ResourceLocation getCustomArmorLocation(ItemStack itemStack, int layer, String overlay) {
      CustomItemProperties props = getCustomItemProperties(itemStack, 3);
      if (props == null) {
         return null;
      } else if (props.mapTextureLocations == null) {
         return props.textureLocation;
      } else {
         Item item = itemStack.func_77973_b();
         if (!(item instanceof ItemArmor)) {
            return null;
         } else {
            ItemArmor itemArmor = (ItemArmor)item;
            String material = itemArmor.func_82812_d().func_179242_c();
            StringBuffer sb = new StringBuffer();
            sb.append("texture.");
            sb.append(material);
            sb.append("_layer_");
            sb.append(layer);
            if (overlay != null) {
               sb.append("_");
               sb.append(overlay);
            }

            String key = sb.toString();
            ResourceLocation loc = (ResourceLocation)props.mapTextureLocations.get(key);
            return loc == null ? props.textureLocation : loc;
         }
      }
   }

   private static CustomItemProperties getCustomItemProperties(ItemStack itemStack, int type) {
      if (itemProperties == null) {
         return null;
      } else if (itemStack == null) {
         return null;
      } else {
         Item item = itemStack.func_77973_b();
         int itemId = Item.func_150891_b(item);
         if (itemId >= 0 && itemId < itemProperties.length) {
            CustomItemProperties[] cips = itemProperties[itemId];
            if (cips != null) {
               for(int i = 0; i < cips.length; ++i) {
                  CustomItemProperties cip = cips[i];
                  if (cip.type == type && matchesProperties(cip, itemStack, (int[][])null)) {
                     return cip;
                  }
               }
            }
         }

         return null;
      }
   }

   private static boolean matchesProperties(CustomItemProperties cip, ItemStack itemStack, int[][] enchantmentIdLevels) {
      Item item = itemStack.func_77973_b();
      if (cip.damage != null) {
         int damage = itemStack.func_77952_i();
         if (cip.damageMask != 0) {
            damage &= cip.damageMask;
         }

         if (cip.damagePercent) {
            int damageMax = item.func_77612_l();
            damage = (int)((double)(damage * 100) / (double)damageMax);
         }

         if (!cip.damage.isInRange(damage)) {
            return false;
         }
      }

      if (cip.stackSize != null && !cip.stackSize.isInRange(itemStack.field_77994_a)) {
         return false;
      } else {
         int[][] idLevels = enchantmentIdLevels;
         int i;
         int level;
         boolean levelMatch;
         if (cip.enchantmentIds != null) {
            if (enchantmentIdLevels == null) {
               idLevels = getEnchantmentIdLevels(itemStack);
            }

            levelMatch = false;

            for(i = 0; i < idLevels.length; ++i) {
               level = idLevels[i][0];
               if (cip.enchantmentIds.isInRange(level)) {
                  levelMatch = true;
                  break;
               }
            }

            if (!levelMatch) {
               return false;
            }
         }

         if (cip.enchantmentLevels != null) {
            if (idLevels == null) {
               idLevels = getEnchantmentIdLevels(itemStack);
            }

            levelMatch = false;

            for(i = 0; i < idLevels.length; ++i) {
               level = idLevels[i][1];
               if (cip.enchantmentLevels.isInRange(level)) {
                  levelMatch = true;
                  break;
               }
            }

            if (!levelMatch) {
               return false;
            }
         }

         if (cip.nbtTagValues != null) {
            NBTTagCompound nbt = itemStack.func_77978_p();

            for(i = 0; i < cip.nbtTagValues.length; ++i) {
               NbtTagValue ntv = cip.nbtTagValues[i];
               if (!ntv.matches(nbt)) {
                  return false;
               }
            }
         }

         if (cip.hand != 0) {
            if (cip.hand == 1 && renderOffHand) {
               return false;
            }

            if (cip.hand == 2 && !renderOffHand) {
               return false;
            }
         }

         return true;
      }
   }

   private static int[][] getEnchantmentIdLevels(ItemStack itemStack) {
      Item item = itemStack.func_77973_b();
      NBTTagList nbt = item == Items.field_151134_bR ? Items.field_151134_bR.func_92110_g(itemStack) : itemStack.func_77986_q();
      if (nbt != null && nbt.func_74745_c() > 0) {
         int[][] arr = new int[nbt.func_74745_c()][2];

         for(int i = 0; i < nbt.func_74745_c(); ++i) {
            NBTTagCompound tag = nbt.func_150305_b(i);
            int id = tag.func_74765_d("id");
            int lvl = tag.func_74765_d("lvl");
            arr[i][0] = id;
            arr[i][1] = lvl;
         }

         return arr;
      } else {
         return EMPTY_INT2_ARRAY;
      }
   }

   public static boolean renderCustomEffect(RenderItem renderItem, ItemStack itemStack, IBakedModel model) {
      if (enchantmentProperties == null) {
         return false;
      } else if (itemStack == null) {
         return false;
      } else {
         int[][] idLevels = getEnchantmentIdLevels(itemStack);
         if (idLevels.length <= 0) {
            return false;
         } else {
            Set layersRendered = null;
            boolean rendered = false;
            TextureManager textureManager = Config.getTextureManager();

            for(int i = 0; i < idLevels.length; ++i) {
               int id = idLevels[i][0];
               if (id >= 0 && id < enchantmentProperties.length) {
                  CustomItemProperties[] cips = enchantmentProperties[id];
                  if (cips != null) {
                     for(int p = 0; p < cips.length; ++p) {
                        CustomItemProperties cip = cips[p];
                        if (layersRendered == null) {
                           layersRendered = new HashSet();
                        }

                        if (layersRendered.add(id) && matchesProperties(cip, itemStack, idLevels) && cip.textureLocation != null) {
                           textureManager.func_110577_a(cip.textureLocation);
                           float width = cip.getTextureWidth(textureManager);
                           if (!rendered) {
                              rendered = true;
                              GlStateManager.func_179132_a(false);
                              GlStateManager.func_179143_c(514);
                              GlStateManager.func_179140_f();
                              GlStateManager.func_179128_n(5890);
                           }

                           Blender.setupBlend(cip.blend, 1.0F);
                           GlStateManager.func_179094_E();
                           GlStateManager.func_179152_a(width / 2.0F, width / 2.0F, width / 2.0F);
                           float offset = cip.speed * (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F / 8.0F;
                           GlStateManager.func_179109_b(offset, 0.0F, 0.0F);
                           GlStateManager.func_179114_b(cip.rotation, 0.0F, 0.0F, 1.0F);
                           renderItem.func_175035_a(model, -1);
                           GlStateManager.func_179121_F();
                        }
                     }
                  }
               }
            }

            if (rendered) {
               GlStateManager.func_179141_d();
               GlStateManager.func_179147_l();
               GlStateManager.func_179112_b(770, 771);
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.func_179128_n(5888);
               GlStateManager.func_179145_e();
               GlStateManager.func_179143_c(515);
               GlStateManager.func_179132_a(true);
               textureManager.func_110577_a(TextureMap.field_110575_b);
            }

            return rendered;
         }
      }
   }

   public static boolean renderCustomArmorEffect(EntityLivingBase entity, ItemStack itemStack, ModelBase model, float limbSwing, float prevLimbSwing, float partialTicks, float timeLimbSwing, float yaw, float pitch, float scale) {
      if (enchantmentProperties == null) {
         return false;
      } else if (Config.isShaders() && Shaders.isShadowPass) {
         return false;
      } else if (itemStack == null) {
         return false;
      } else {
         int[][] idLevels = getEnchantmentIdLevels(itemStack);
         if (idLevels.length <= 0) {
            return false;
         } else {
            Set layersRendered = null;
            boolean rendered = false;
            TextureManager textureManager = Config.getTextureManager();

            for(int i = 0; i < idLevels.length; ++i) {
               int id = idLevels[i][0];
               if (id >= 0 && id < enchantmentProperties.length) {
                  CustomItemProperties[] cips = enchantmentProperties[id];
                  if (cips != null) {
                     for(int p = 0; p < cips.length; ++p) {
                        CustomItemProperties cip = cips[p];
                        if (layersRendered == null) {
                           layersRendered = new HashSet();
                        }

                        if (layersRendered.add(id) && matchesProperties(cip, itemStack, idLevels) && cip.textureLocation != null) {
                           textureManager.func_110577_a(cip.textureLocation);
                           float width = cip.getTextureWidth(textureManager);
                           if (!rendered) {
                              rendered = true;
                              if (Config.isShaders()) {
                                 ShadersRender.renderEnchantedGlintBegin();
                              }

                              GlStateManager.func_179147_l();
                              GlStateManager.func_179143_c(514);
                              GlStateManager.func_179132_a(false);
                           }

                           Blender.setupBlend(cip.blend, 1.0F);
                           GlStateManager.func_179140_f();
                           GlStateManager.func_179128_n(5890);
                           GlStateManager.func_179096_D();
                           GlStateManager.func_179114_b(cip.rotation, 0.0F, 0.0F, 1.0F);
                           float texScale = width / 8.0F;
                           GlStateManager.func_179152_a(texScale, texScale / 2.0F, texScale);
                           float offset = cip.speed * (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F / 8.0F;
                           GlStateManager.func_179109_b(0.0F, offset, 0.0F);
                           GlStateManager.func_179128_n(5888);
                           model.func_78088_a(entity, limbSwing, prevLimbSwing, timeLimbSwing, yaw, pitch, scale);
                        }
                     }
                  }
               }
            }

            if (rendered) {
               GlStateManager.func_179141_d();
               GlStateManager.func_179147_l();
               GlStateManager.func_179112_b(770, 771);
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               GlStateManager.func_179128_n(5890);
               GlStateManager.func_179096_D();
               GlStateManager.func_179128_n(5888);
               GlStateManager.func_179145_e();
               GlStateManager.func_179132_a(true);
               GlStateManager.func_179143_c(515);
               GlStateManager.func_179084_k();
               if (Config.isShaders()) {
                  ShadersRender.renderEnchantedGlintEnd();
               }
            }

            return rendered;
         }
      }
   }

   public static boolean isUseGlint() {
      return useGlint;
   }
}
