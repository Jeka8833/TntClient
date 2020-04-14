package net.optifine;

import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ClearWater {
   public static void updateWaterOpacity(GameSettings settings, World world) {
      if (settings != null) {
         int opacity = 3;
         if (settings.ofClearWater) {
            opacity = 1;
         }

         BlockAir.setLightOpacity(Blocks.field_150355_j, opacity);
         BlockAir.setLightOpacity(Blocks.field_150358_i, opacity);
      }

      if (world != null) {
         IChunkProvider cp = world.func_72863_F();
         if (cp != null) {
            Entity rve = Config.getMinecraft().func_175606_aa();
            if (rve != null) {
               int cViewX = (int)rve.field_70165_t / 16;
               int cViewZ = (int)rve.field_70161_v / 16;
               int cXMin = cViewX - 512;
               int cXMax = cViewX + 512;
               int cZMin = cViewZ - 512;
               int cZMax = cViewZ + 512;
               int countUpdated = 0;

               for(int cx = cXMin; cx < cXMax; ++cx) {
                  for(int cz = cZMin; cz < cZMax; ++cz) {
                     if (cp.func_73149_a(cx, cz)) {
                        Chunk c = cp.func_73154_d(cx, cz);
                        if (c != null && !(c instanceof EmptyChunk)) {
                           int x0 = cx << 4;
                           int z0 = cz << 4;
                           int x1 = x0 + 16;
                           int z1 = z0 + 16;
                           BlockPosM posXZ = new BlockPosM(0, 0, 0);
                           BlockPosM posXYZ = new BlockPosM(0, 0, 0);

                           for(int x = x0; x < x1; ++x) {
                              for(int z = z0; z < z1; ++z) {
                                 posXZ.setXyz(x, 0, z);
                                 BlockPos posH = world.func_175725_q(posXZ);

                                 for(int y = 0; y < posH.func_177956_o(); ++y) {
                                    posXYZ.setXyz(x, y, z);
                                    IBlockState bs = world.func_180495_p(posXYZ);
                                    if (bs.func_177230_c().func_149688_o() == Material.field_151586_h) {
                                       world.func_72975_g(x, z, posXYZ.func_177956_o(), posH.func_177956_o());
                                       ++countUpdated;
                                       break;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (countUpdated > 0) {
                  String threadName = "server";
                  if (Config.isMinecraftThread()) {
                     threadName = "client";
                  }

                  Config.dbg("ClearWater (" + threadName + ") relighted " + countUpdated + " chunks");
               }

            }
         }
      }
   }
}
