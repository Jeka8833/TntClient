package net.optifine.util;

import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class IntegratedServerUtils {
   public static WorldServer getWorldServer() {
      Minecraft mc = Config.getMinecraft();
      World world = mc.field_71441_e;
      if (world == null) {
         return null;
      } else if (!mc.func_71387_A()) {
         return null;
      } else {
         IntegratedServer is = mc.func_71401_C();
         if (is == null) {
            return null;
         } else {
            WorldProvider wp = world.field_73011_w;
            if (wp == null) {
               return null;
            } else {
               int wd = wp.func_177502_q();

               try {
                  WorldServer ws = is.func_71218_a(wd);
                  return ws;
               } catch (NullPointerException var6) {
                  return null;
               }
            }
         }
      }
   }

   public static Entity getEntity(UUID uuid) {
      WorldServer ws = getWorldServer();
      if (ws == null) {
         return null;
      } else {
         Entity e = ws.func_175733_a(uuid);
         return e;
      }
   }

   public static TileEntity getTileEntity(BlockPos pos) {
      WorldServer ws = getWorldServer();
      if (ws == null) {
         return null;
      } else {
         Chunk chunk = ws.func_72863_F().func_73154_d(pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
         if (chunk == null) {
            return null;
         } else {
            TileEntity te = chunk.func_177424_a(pos, Chunk.EnumCreateEntityType.CHECK);
            return te;
         }
      }
   }
}
