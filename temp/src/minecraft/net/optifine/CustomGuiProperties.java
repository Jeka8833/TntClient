package net.optifine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.config.ConnectedParser;
import net.optifine.config.Matches;
import net.optifine.config.NbtTagValue;
import net.optifine.config.RangeListInt;
import net.optifine.config.VillagerProfession;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorField;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;

public class CustomGuiProperties {
   private String fileName = null;
   private String basePath = null;
   private CustomGuiProperties.EnumContainer container = null;
   private Map<ResourceLocation, ResourceLocation> textureLocations = null;
   private NbtTagValue nbtName = null;
   private BiomeGenBase[] biomes = null;
   private RangeListInt heights = null;
   private Boolean large = null;
   private Boolean trapped = null;
   private Boolean christmas = null;
   private Boolean ender = null;
   private RangeListInt levels = null;
   private VillagerProfession[] professions = null;
   private CustomGuiProperties.EnumVariant[] variants = null;
   private EnumDyeColor[] colors = null;
   private static final CustomGuiProperties.EnumVariant[] VARIANTS_HORSE;
   private static final CustomGuiProperties.EnumVariant[] VARIANTS_DISPENSER;
   private static final CustomGuiProperties.EnumVariant[] VARIANTS_INVALID;
   private static final EnumDyeColor[] COLORS_INVALID;
   private static final ResourceLocation ANVIL_GUI_TEXTURE;
   private static final ResourceLocation BEACON_GUI_TEXTURE;
   private static final ResourceLocation BREWING_STAND_GUI_TEXTURE;
   private static final ResourceLocation CHEST_GUI_TEXTURE;
   private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURE;
   private static final ResourceLocation HORSE_GUI_TEXTURE;
   private static final ResourceLocation DISPENSER_GUI_TEXTURE;
   private static final ResourceLocation ENCHANTMENT_TABLE_GUI_TEXTURE;
   private static final ResourceLocation FURNACE_GUI_TEXTURE;
   private static final ResourceLocation HOPPER_GUI_TEXTURE;
   private static final ResourceLocation INVENTORY_GUI_TEXTURE;
   private static final ResourceLocation SHULKER_BOX_GUI_TEXTURE;
   private static final ResourceLocation VILLAGER_GUI_TEXTURE;

   public CustomGuiProperties(Properties props, String path) {
      ConnectedParser cp = new ConnectedParser("CustomGuis");
      this.fileName = cp.parseName(path);
      this.basePath = cp.parseBasePath(path);
      this.container = (CustomGuiProperties.EnumContainer)cp.parseEnum(props.getProperty("container"), CustomGuiProperties.EnumContainer.values(), "container");
      this.textureLocations = parseTextureLocations(props, "texture", this.container, "textures/gui/", this.basePath);
      this.nbtName = cp.parseNbtTagValue("name", props.getProperty("name"));
      this.biomes = cp.parseBiomes(props.getProperty("biomes"));
      this.heights = cp.parseRangeListInt(props.getProperty("heights"));
      this.large = cp.parseBooleanObject(props.getProperty("large"));
      this.trapped = cp.parseBooleanObject(props.getProperty("trapped"));
      this.christmas = cp.parseBooleanObject(props.getProperty("christmas"));
      this.ender = cp.parseBooleanObject(props.getProperty("ender"));
      this.levels = cp.parseRangeListInt(props.getProperty("levels"));
      this.professions = cp.parseProfessions(props.getProperty("professions"));
      CustomGuiProperties.EnumVariant[] vars = getContainerVariants(this.container);
      this.variants = (CustomGuiProperties.EnumVariant[])((CustomGuiProperties.EnumVariant[])cp.parseEnums(props.getProperty("variants"), vars, "variants", VARIANTS_INVALID));
      this.colors = parseEnumDyeColors(props.getProperty("colors"));
   }

   private static CustomGuiProperties.EnumVariant[] getContainerVariants(CustomGuiProperties.EnumContainer cont) {
      if (cont == CustomGuiProperties.EnumContainer.HORSE) {
         return VARIANTS_HORSE;
      } else {
         return cont == CustomGuiProperties.EnumContainer.DISPENSER ? VARIANTS_DISPENSER : new CustomGuiProperties.EnumVariant[0];
      }
   }

   private static EnumDyeColor[] parseEnumDyeColors(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.toLowerCase();
         String[] tokens = Config.tokenize(str, " ");
         EnumDyeColor[] cols = new EnumDyeColor[tokens.length];

         for(int i = 0; i < tokens.length; ++i) {
            String token = tokens[i];
            EnumDyeColor col = parseEnumDyeColor(token);
            if (col == null) {
               warn("Invalid color: " + token);
               return COLORS_INVALID;
            }

            cols[i] = col;
         }

         return cols;
      }
   }

   private static EnumDyeColor parseEnumDyeColor(String str) {
      if (str == null) {
         return null;
      } else {
         EnumDyeColor[] colors = EnumDyeColor.values();

         for(int i = 0; i < colors.length; ++i) {
            EnumDyeColor enumDyeColor = colors[i];
            if (enumDyeColor.func_176610_l().equals(str)) {
               return enumDyeColor;
            }

            if (enumDyeColor.func_176762_d().equals(str)) {
               return enumDyeColor;
            }
         }

         return null;
      }
   }

   private static ResourceLocation parseTextureLocation(String str, String basePath) {
      if (str == null) {
         return null;
      } else {
         str = str.trim();
         String tex = TextureUtils.fixResourcePath(str, basePath);
         if (!tex.endsWith(".png")) {
            tex = tex + ".png";
         }

         return new ResourceLocation(basePath + "/" + tex);
      }
   }

   private static Map<ResourceLocation, ResourceLocation> parseTextureLocations(Properties props, String property, CustomGuiProperties.EnumContainer container, String pathPrefix, String basePath) {
      Map<ResourceLocation, ResourceLocation> map = new HashMap();
      String propVal = props.getProperty(property);
      if (propVal != null) {
         ResourceLocation locKey = getGuiTextureLocation(container);
         ResourceLocation locVal = parseTextureLocation(propVal, basePath);
         if (locKey != null && locVal != null) {
            map.put(locKey, locVal);
         }
      }

      String keyPrefix = property + ".";
      Set keys = props.keySet();
      Iterator it = keys.iterator();

      while(it.hasNext()) {
         String key = (String)it.next();
         if (key.startsWith(keyPrefix)) {
            String pathRel = key.substring(keyPrefix.length());
            pathRel = pathRel.replace('\\', '/');
            pathRel = StrUtils.removePrefixSuffix(pathRel, "/", ".png");
            String path = pathPrefix + pathRel + ".png";
            String val = props.getProperty(key);
            ResourceLocation locKey = new ResourceLocation(path);
            ResourceLocation locVal = parseTextureLocation(val, basePath);
            map.put(locKey, locVal);
         }
      }

      return map;
   }

   private static ResourceLocation getGuiTextureLocation(CustomGuiProperties.EnumContainer container) {
      switch(container) {
      case ANVIL:
         return ANVIL_GUI_TEXTURE;
      case BEACON:
         return BEACON_GUI_TEXTURE;
      case BREWING_STAND:
         return BREWING_STAND_GUI_TEXTURE;
      case CHEST:
         return CHEST_GUI_TEXTURE;
      case CRAFTING:
         return CRAFTING_TABLE_GUI_TEXTURE;
      case CREATIVE:
         return null;
      case DISPENSER:
         return DISPENSER_GUI_TEXTURE;
      case ENCHANTMENT:
         return ENCHANTMENT_TABLE_GUI_TEXTURE;
      case FURNACE:
         return FURNACE_GUI_TEXTURE;
      case HOPPER:
         return HOPPER_GUI_TEXTURE;
      case HORSE:
         return HORSE_GUI_TEXTURE;
      case INVENTORY:
         return INVENTORY_GUI_TEXTURE;
      case SHULKER_BOX:
         return SHULKER_BOX_GUI_TEXTURE;
      case VILLAGER:
         return VILLAGER_GUI_TEXTURE;
      default:
         return null;
      }
   }

   public boolean isValid(String path) {
      if (this.fileName != null && this.fileName.length() > 0) {
         if (this.basePath == null) {
            warn("No base path found: " + path);
            return false;
         } else if (this.container == null) {
            warn("No container found: " + path);
            return false;
         } else if (this.textureLocations.isEmpty()) {
            warn("No texture found: " + path);
            return false;
         } else if (this.professions == ConnectedParser.PROFESSIONS_INVALID) {
            warn("Invalid professions or careers: " + path);
            return false;
         } else if (this.variants == VARIANTS_INVALID) {
            warn("Invalid variants: " + path);
            return false;
         } else if (this.colors == COLORS_INVALID) {
            warn("Invalid colors: " + path);
            return false;
         } else {
            return true;
         }
      } else {
         warn("No name found: " + path);
         return false;
      }
   }

   private static void warn(String str) {
      Config.warn("[CustomGuis] " + str);
   }

   private boolean matchesGeneral(CustomGuiProperties.EnumContainer ec, BlockPos pos, IBlockAccess blockAccess) {
      if (this.container != ec) {
         return false;
      } else {
         if (this.biomes != null) {
            BiomeGenBase biome = blockAccess.func_180494_b(pos);
            if (!Matches.biome(biome, this.biomes)) {
               return false;
            }
         }

         return this.heights == null || this.heights.isInRange(pos.func_177956_o());
      }
   }

   public boolean matchesPos(CustomGuiProperties.EnumContainer ec, BlockPos pos, IBlockAccess blockAccess, GuiScreen screen) {
      if (!this.matchesGeneral(ec, pos, blockAccess)) {
         return false;
      } else {
         if (this.nbtName != null) {
            String name = getName(screen);
            if (!this.nbtName.matchesValue(name)) {
               return false;
            }
         }

         switch(ec) {
         case BEACON:
            return this.matchesBeacon(pos, blockAccess);
         case CHEST:
            return this.matchesChest(pos, blockAccess);
         case DISPENSER:
            return this.matchesDispenser(pos, blockAccess);
         default:
            return true;
         }
      }
   }

   public static String getName(GuiScreen screen) {
      IWorldNameable nameable = getWorldNameable(screen);
      return nameable == null ? null : nameable.func_145748_c_().func_150260_c();
   }

   private static IWorldNameable getWorldNameable(GuiScreen screen) {
      if (screen instanceof GuiBeacon) {
         return getWorldNameable(screen, Reflector.GuiBeacon_tileBeacon);
      } else if (screen instanceof GuiBrewingStand) {
         return getWorldNameable(screen, Reflector.GuiBrewingStand_tileBrewingStand);
      } else if (screen instanceof GuiChest) {
         return getWorldNameable(screen, Reflector.GuiChest_lowerChestInventory);
      } else if (screen instanceof GuiDispenser) {
         return ((GuiDispenser)screen).field_175377_u;
      } else if (screen instanceof GuiEnchantment) {
         return getWorldNameable(screen, Reflector.GuiEnchantment_nameable);
      } else if (screen instanceof GuiFurnace) {
         return getWorldNameable(screen, Reflector.GuiFurnace_tileFurnace);
      } else {
         return screen instanceof GuiHopper ? getWorldNameable(screen, Reflector.GuiHopper_hopperInventory) : null;
      }
   }

   private static IWorldNameable getWorldNameable(GuiScreen screen, ReflectorField fieldInventory) {
      Object obj = Reflector.getFieldValue(screen, fieldInventory);
      return !(obj instanceof IWorldNameable) ? null : (IWorldNameable)obj;
   }

   private boolean matchesBeacon(BlockPos pos, IBlockAccess blockAccess) {
      TileEntity te = blockAccess.func_175625_s(pos);
      if (!(te instanceof TileEntityBeacon)) {
         return false;
      } else {
         TileEntityBeacon teb = (TileEntityBeacon)te;
         if (this.levels != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            teb.func_145841_b(nbt);
            int l = nbt.func_74762_e("Levels");
            if (!this.levels.isInRange(l)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean matchesChest(BlockPos pos, IBlockAccess blockAccess) {
      TileEntity te = blockAccess.func_175625_s(pos);
      if (te instanceof TileEntityChest) {
         TileEntityChest tec = (TileEntityChest)te;
         return this.matchesChest(tec, pos, blockAccess);
      } else if (te instanceof TileEntityEnderChest) {
         TileEntityEnderChest teec = (TileEntityEnderChest)te;
         return this.matchesEnderChest(teec, pos, blockAccess);
      } else {
         return false;
      }
   }

   private boolean matchesChest(TileEntityChest tec, BlockPos pos, IBlockAccess blockAccess) {
      boolean isLarge = tec.field_145991_k != null || tec.field_145990_j != null || tec.field_145992_i != null || tec.field_145988_l != null;
      boolean isTrapped = tec.func_145980_j() == 1;
      boolean isChristmas = CustomGuis.isChristmas;
      boolean isEnder = false;
      return this.matchesChest(isLarge, isTrapped, isChristmas, isEnder);
   }

   private boolean matchesEnderChest(TileEntityEnderChest teec, BlockPos pos, IBlockAccess blockAccess) {
      return this.matchesChest(false, false, false, true);
   }

   private boolean matchesChest(boolean isLarge, boolean isTrapped, boolean isChristmas, boolean isEnder) {
      if (this.large != null && this.large != isLarge) {
         return false;
      } else if (this.trapped != null && this.trapped != isTrapped) {
         return false;
      } else if (this.christmas != null && this.christmas != isChristmas) {
         return false;
      } else {
         return this.ender == null || this.ender == isEnder;
      }
   }

   private boolean matchesDispenser(BlockPos pos, IBlockAccess blockAccess) {
      TileEntity te = blockAccess.func_175625_s(pos);
      if (!(te instanceof TileEntityDispenser)) {
         return false;
      } else {
         TileEntityDispenser ted = (TileEntityDispenser)te;
         if (this.variants != null) {
            CustomGuiProperties.EnumVariant var = this.getDispenserVariant(ted);
            if (!Config.equalsOne(var, this.variants)) {
               return false;
            }
         }

         return true;
      }
   }

   private CustomGuiProperties.EnumVariant getDispenserVariant(TileEntityDispenser ted) {
      return ted instanceof TileEntityDropper ? CustomGuiProperties.EnumVariant.DROPPER : CustomGuiProperties.EnumVariant.DISPENSER;
   }

   public boolean matchesEntity(CustomGuiProperties.EnumContainer ec, Entity entity, IBlockAccess blockAccess) {
      if (!this.matchesGeneral(ec, entity.func_180425_c(), blockAccess)) {
         return false;
      } else {
         if (this.nbtName != null) {
            String entityName = entity.func_70005_c_();
            if (!this.nbtName.matchesValue(entityName)) {
               return false;
            }
         }

         switch(ec) {
         case HORSE:
            return this.matchesHorse(entity, blockAccess);
         case VILLAGER:
            return this.matchesVillager(entity, blockAccess);
         default:
            return true;
         }
      }
   }

   private boolean matchesVillager(Entity entity, IBlockAccess blockAccess) {
      if (!(entity instanceof EntityVillager)) {
         return false;
      } else {
         EntityVillager entityVillager = (EntityVillager)entity;
         if (this.professions != null) {
            int profInt = entityVillager.func_70946_n();
            int careerInt = Reflector.getFieldValueInt(entityVillager, Reflector.EntityVillager_careerId, -1);
            if (careerInt < 0) {
               return false;
            }

            boolean matchProfession = false;

            for(int i = 0; i < this.professions.length; ++i) {
               VillagerProfession prof = this.professions[i];
               if (prof.matches(profInt, careerInt)) {
                  matchProfession = true;
                  break;
               }
            }

            if (!matchProfession) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean matchesHorse(Entity entity, IBlockAccess blockAccess) {
      if (!(entity instanceof EntityHorse)) {
         return false;
      } else {
         EntityHorse ah = (EntityHorse)entity;
         if (this.variants != null) {
            CustomGuiProperties.EnumVariant var = this.getHorseVariant(ah);
            if (!Config.equalsOne(var, this.variants)) {
               return false;
            }
         }

         return true;
      }
   }

   private CustomGuiProperties.EnumVariant getHorseVariant(EntityHorse entity) {
      int type = entity.func_110265_bP();
      switch(type) {
      case 0:
         return CustomGuiProperties.EnumVariant.HORSE;
      case 1:
         return CustomGuiProperties.EnumVariant.DONKEY;
      case 2:
         return CustomGuiProperties.EnumVariant.MULE;
      default:
         return null;
      }
   }

   public CustomGuiProperties.EnumContainer getContainer() {
      return this.container;
   }

   public ResourceLocation getTextureLocation(ResourceLocation loc) {
      ResourceLocation locNew = (ResourceLocation)this.textureLocations.get(loc);
      return locNew == null ? loc : locNew;
   }

   public String toString() {
      return "name: " + this.fileName + ", container: " + this.container + ", textures: " + this.textureLocations;
   }

   static {
      VARIANTS_HORSE = new CustomGuiProperties.EnumVariant[]{CustomGuiProperties.EnumVariant.HORSE, CustomGuiProperties.EnumVariant.DONKEY, CustomGuiProperties.EnumVariant.MULE, CustomGuiProperties.EnumVariant.LLAMA};
      VARIANTS_DISPENSER = new CustomGuiProperties.EnumVariant[]{CustomGuiProperties.EnumVariant.DISPENSER, CustomGuiProperties.EnumVariant.DROPPER};
      VARIANTS_INVALID = new CustomGuiProperties.EnumVariant[0];
      COLORS_INVALID = new EnumDyeColor[0];
      ANVIL_GUI_TEXTURE = new ResourceLocation("textures/gui/container/anvil.png");
      BEACON_GUI_TEXTURE = new ResourceLocation("textures/gui/container/beacon.png");
      BREWING_STAND_GUI_TEXTURE = new ResourceLocation("textures/gui/container/brewing_stand.png");
      CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
      CRAFTING_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/crafting_table.png");
      HORSE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/horse.png");
      DISPENSER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
      ENCHANTMENT_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/enchanting_table.png");
      FURNACE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");
      HOPPER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");
      INVENTORY_GUI_TEXTURE = new ResourceLocation("textures/gui/container/inventory.png");
      SHULKER_BOX_GUI_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
      VILLAGER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/villager.png");
   }

   private static enum EnumVariant {
      HORSE,
      DONKEY,
      MULE,
      LLAMA,
      DISPENSER,
      DROPPER;
   }

   public static enum EnumContainer {
      ANVIL,
      BEACON,
      BREWING_STAND,
      CHEST,
      CRAFTING,
      DISPENSER,
      ENCHANTMENT,
      FURNACE,
      HOPPER,
      HORSE,
      VILLAGER,
      SHULKER_BOX,
      CREATIVE,
      INVENTORY;

      public static final CustomGuiProperties.EnumContainer[] VALUES = values();
   }
}
