package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.optifine.model.BlockModelUtils;
import net.optifine.util.PropertiesOrdered;

public class BetterGrass {
   private static boolean betterGrass = true;
   private static boolean betterMycelium = true;
   private static boolean betterPodzol = true;
   private static boolean betterGrassSnow = true;
   private static boolean betterMyceliumSnow = true;
   private static boolean betterPodzolSnow = true;
   private static boolean grassMultilayer = false;
   private static TextureAtlasSprite spriteGrass = null;
   private static TextureAtlasSprite spriteGrassSide = null;
   private static TextureAtlasSprite spriteMycelium = null;
   private static TextureAtlasSprite spritePodzol = null;
   private static TextureAtlasSprite spriteSnow = null;
   private static boolean spritesLoaded = false;
   private static IBakedModel modelCubeGrass = null;
   private static IBakedModel modelCubeMycelium = null;
   private static IBakedModel modelCubePodzol = null;
   private static IBakedModel modelCubeSnow = null;
   private static boolean modelsLoaded = false;
   private static final String TEXTURE_GRASS_DEFAULT = "blocks/grass_top";
   private static final String TEXTURE_GRASS_SIDE_DEFAULT = "blocks/grass_side";
   private static final String TEXTURE_MYCELIUM_DEFAULT = "blocks/mycelium_top";
   private static final String TEXTURE_PODZOL_DEFAULT = "blocks/dirt_podzol_top";
   private static final String TEXTURE_SNOW_DEFAULT = "blocks/snow";

   public static void updateIcons(TextureMap textureMap) {
      spritesLoaded = false;
      modelsLoaded = false;
      loadProperties(textureMap);
   }

   public static void update() {
      if (spritesLoaded) {
         modelCubeGrass = BlockModelUtils.makeModelCube((TextureAtlasSprite)spriteGrass, 0);
         if (grassMultilayer) {
            IBakedModel modelCubeGrassSide = BlockModelUtils.makeModelCube((TextureAtlasSprite)spriteGrassSide, -1);
            modelCubeGrass = BlockModelUtils.joinModelsCube(modelCubeGrassSide, modelCubeGrass);
         }

         modelCubeMycelium = BlockModelUtils.makeModelCube((TextureAtlasSprite)spriteMycelium, -1);
         modelCubePodzol = BlockModelUtils.makeModelCube((TextureAtlasSprite)spritePodzol, 0);
         modelCubeSnow = BlockModelUtils.makeModelCube((TextureAtlasSprite)spriteSnow, -1);
         modelsLoaded = true;
      }
   }

   private static void loadProperties(TextureMap textureMap) {
      betterGrass = true;
      betterMycelium = true;
      betterPodzol = true;
      betterGrassSnow = true;
      betterMyceliumSnow = true;
      betterPodzolSnow = true;
      spriteGrass = textureMap.func_174942_a(new ResourceLocation("blocks/grass_top"));
      spriteGrassSide = textureMap.func_174942_a(new ResourceLocation("blocks/grass_side"));
      spriteMycelium = textureMap.func_174942_a(new ResourceLocation("blocks/mycelium_top"));
      spritePodzol = textureMap.func_174942_a(new ResourceLocation("blocks/dirt_podzol_top"));
      spriteSnow = textureMap.func_174942_a(new ResourceLocation("blocks/snow"));
      spritesLoaded = true;
      String name = "optifine/bettergrass.properties";

      try {
         ResourceLocation locFile = new ResourceLocation(name);
         if (!Config.hasResource(locFile)) {
            return;
         }

         InputStream in = Config.getResourceStream(locFile);
         if (in == null) {
            return;
         }

         boolean defaultConfig = Config.isFromDefaultResourcePack(locFile);
         if (defaultConfig) {
            Config.dbg("BetterGrass: Parsing default configuration " + name);
         } else {
            Config.dbg("BetterGrass: Parsing configuration " + name);
         }

         Properties props = new PropertiesOrdered();
         props.load(in);
         betterGrass = getBoolean(props, "grass", true);
         betterMycelium = getBoolean(props, "mycelium", true);
         betterPodzol = getBoolean(props, "podzol", true);
         betterGrassSnow = getBoolean(props, "grass.snow", true);
         betterMyceliumSnow = getBoolean(props, "mycelium.snow", true);
         betterPodzolSnow = getBoolean(props, "podzol.snow", true);
         grassMultilayer = getBoolean(props, "grass.multilayer", false);
         spriteGrass = registerSprite(props, "texture.grass", "blocks/grass_top", textureMap);
         spriteGrassSide = registerSprite(props, "texture.grass_side", "blocks/grass_side", textureMap);
         spriteMycelium = registerSprite(props, "texture.mycelium", "blocks/mycelium_top", textureMap);
         spritePodzol = registerSprite(props, "texture.podzol", "blocks/dirt_podzol_top", textureMap);
         spriteSnow = registerSprite(props, "texture.snow", "blocks/snow", textureMap);
      } catch (IOException var6) {
         Config.warn("Error reading: " + name + ", " + var6.getClass().getName() + ": " + var6.getMessage());
      }

   }

   private static TextureAtlasSprite registerSprite(Properties props, String key, String textureDefault, TextureMap textureMap) {
      String texture = props.getProperty(key);
      if (texture == null) {
         texture = textureDefault;
      }

      ResourceLocation locPng = new ResourceLocation("textures/" + texture + ".png");
      if (!Config.hasResource(locPng)) {
         Config.warn("BetterGrass texture not found: " + locPng);
         texture = textureDefault;
      }

      ResourceLocation locSprite = new ResourceLocation(texture);
      TextureAtlasSprite sprite = textureMap.func_174942_a(locSprite);
      return sprite;
   }

   public static List getFaceQuads(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
         if (!modelsLoaded) {
            return quads;
         } else {
            Block block = blockState.func_177230_c();
            if (block instanceof BlockMycelium) {
               return getFaceQuadsMycelium(blockAccess, blockState, blockPos, facing, quads);
            } else if (block instanceof BlockDirt) {
               return getFaceQuadsDirt(blockAccess, blockState, blockPos, facing, quads);
            } else {
               return block instanceof BlockGrass ? getFaceQuadsGrass(blockAccess, blockState, blockPos, facing, quads) : quads;
            }
         }
      } else {
         return quads;
      }
   }

   private static List getFaceQuadsMycelium(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      Block blockUp = blockAccess.func_180495_p(blockPos.func_177984_a()).func_177230_c();
      boolean snowy = blockUp == Blocks.field_150433_aE || blockUp == Blocks.field_150431_aC;
      if (Config.isBetterGrassFancy()) {
         if (snowy) {
            if (betterMyceliumSnow && getBlockAt(blockPos, facing, blockAccess) == Blocks.field_150431_aC) {
               return modelCubeSnow.func_177551_a(facing);
            }
         } else if (betterMycelium && getBlockAt(blockPos.func_177977_b(), facing, blockAccess) == Blocks.field_150391_bh) {
            return modelCubeMycelium.func_177551_a(facing);
         }
      } else if (snowy) {
         if (betterMyceliumSnow) {
            return modelCubeSnow.func_177551_a(facing);
         }
      } else if (betterMycelium) {
         return modelCubeMycelium.func_177551_a(facing);
      }

      return quads;
   }

   private static List getFaceQuadsDirt(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      Block blockTop = getBlockAt(blockPos, EnumFacing.UP, blockAccess);
      if (blockState.func_177229_b(BlockDirt.field_176386_a) != BlockDirt.DirtType.PODZOL) {
         return quads;
      } else {
         boolean snowy = blockTop == Blocks.field_150433_aE || blockTop == Blocks.field_150431_aC;
         if (Config.isBetterGrassFancy()) {
            if (snowy) {
               if (betterPodzolSnow && getBlockAt(blockPos, facing, blockAccess) == Blocks.field_150431_aC) {
                  return modelCubeSnow.func_177551_a(facing);
               }
            } else if (betterPodzol) {
               BlockPos posSide = blockPos.func_177977_b().func_177972_a(facing);
               IBlockState stateSide = blockAccess.func_180495_p(posSide);
               if (stateSide.func_177230_c() == Blocks.field_150346_d && stateSide.func_177229_b(BlockDirt.field_176386_a) == BlockDirt.DirtType.PODZOL) {
                  return modelCubePodzol.func_177551_a(facing);
               }
            }
         } else if (snowy) {
            if (betterPodzolSnow) {
               return modelCubeSnow.func_177551_a(facing);
            }
         } else if (betterPodzol) {
            return modelCubePodzol.func_177551_a(facing);
         }

         return quads;
      }
   }

   private static List getFaceQuadsGrass(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      Block blockUp = blockAccess.func_180495_p(blockPos.func_177984_a()).func_177230_c();
      boolean snowy = blockUp == Blocks.field_150433_aE || blockUp == Blocks.field_150431_aC;
      if (Config.isBetterGrassFancy()) {
         if (snowy) {
            if (betterGrassSnow && getBlockAt(blockPos, facing, blockAccess) == Blocks.field_150431_aC) {
               return modelCubeSnow.func_177551_a(facing);
            }
         } else if (betterGrass && getBlockAt(blockPos.func_177977_b(), facing, blockAccess) == Blocks.field_150349_c) {
            return modelCubeGrass.func_177551_a(facing);
         }
      } else if (snowy) {
         if (betterGrassSnow) {
            return modelCubeSnow.func_177551_a(facing);
         }
      } else if (betterGrass) {
         return modelCubeGrass.func_177551_a(facing);
      }

      return quads;
   }

   private static Block getBlockAt(BlockPos blockPos, EnumFacing facing, IBlockAccess blockAccess) {
      BlockPos pos = blockPos.func_177972_a(facing);
      Block block = blockAccess.func_180495_p(pos).func_177230_c();
      return block;
   }

   private static boolean getBoolean(Properties props, String key, boolean def) {
      String str = props.getProperty(key);
      return str == null ? def : Boolean.parseBoolean(str);
   }
}
