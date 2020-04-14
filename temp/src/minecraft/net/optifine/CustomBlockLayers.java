package net.optifine;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.shaders.BlockAliases;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;

public class CustomBlockLayers {
   private static EnumWorldBlockLayer[] renderLayers = null;
   public static boolean active = false;

   public static EnumWorldBlockLayer getRenderLayer(IBlockState blockState) {
      if (renderLayers == null) {
         return null;
      } else if (blockState.func_177230_c().func_149662_c()) {
         return null;
      } else if (!(blockState instanceof BlockStateBase)) {
         return null;
      } else {
         BlockStateBase bsb = (BlockStateBase)blockState;
         int id = bsb.getBlockId();
         return id > 0 && id < renderLayers.length ? renderLayers[id] : null;
      }
   }

   public static void update() {
      renderLayers = null;
      active = false;
      List<EnumWorldBlockLayer> list = new ArrayList();
      String pathProps = "optifine/block.properties";
      Properties props = ResUtils.readProperties(pathProps, "CustomBlockLayers");
      if (props != null) {
         readLayers(pathProps, props, list);
      }

      if (Config.isShaders()) {
         PropertiesOrdered propsShaders = BlockAliases.getBlockLayerPropertes();
         if (propsShaders != null) {
            String pathPropsShaders = "shaders/block.properties";
            readLayers(pathPropsShaders, propsShaders, list);
         }
      }

      if (!list.isEmpty()) {
         renderLayers = (EnumWorldBlockLayer[])list.toArray(new EnumWorldBlockLayer[list.size()]);
         active = true;
      }
   }

   private static void readLayers(String pathProps, Properties props, List<EnumWorldBlockLayer> list) {
      Config.dbg("CustomBlockLayers: " + pathProps);
      readLayer("solid", EnumWorldBlockLayer.SOLID, props, list);
      readLayer("cutout", EnumWorldBlockLayer.CUTOUT, props, list);
      readLayer("cutout_mipped", EnumWorldBlockLayer.CUTOUT_MIPPED, props, list);
      readLayer("translucent", EnumWorldBlockLayer.TRANSLUCENT, props, list);
   }

   private static void readLayer(String name, EnumWorldBlockLayer layer, Properties props, List<EnumWorldBlockLayer> listLayers) {
      String key = "layer." + name;
      String val = props.getProperty(key);
      if (val != null) {
         ConnectedParser cp = new ConnectedParser("CustomBlockLayers");
         MatchBlock[] mbs = cp.parseMatchBlocks(val);
         if (mbs != null) {
            for(int i = 0; i < mbs.length; ++i) {
               MatchBlock mb = mbs[i];
               int blockId = mb.getBlockId();
               if (blockId > 0) {
                  while(listLayers.size() < blockId + 1) {
                     listLayers.add((Object)null);
                  }

                  if (listLayers.get(blockId) != null) {
                     Config.warn("CustomBlockLayers: Block layer is already set, block: " + blockId + ", layer: " + name);
                  }

                  listLayers.set(blockId, layer);
               }
            }

         }
      }
   }

   public static boolean isActive() {
      return active;
   }
}
