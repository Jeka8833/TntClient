package net.optifine.render;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class ChunkVisibility {
   public static final int MASK_FACINGS = 63;
   public static final EnumFacing[][] enumFacingArrays = makeEnumFacingArrays(false);
   public static final EnumFacing[][] enumFacingOppositeArrays = makeEnumFacingArrays(true);
   private static int counter = 0;
   private static int iMaxStatic = -1;
   private static int iMaxStaticFinal = 16;
   private static World worldLast = null;
   private static int pcxLast = Integer.MIN_VALUE;
   private static int pczLast = Integer.MIN_VALUE;

   public static int getMaxChunkY(World world, Entity viewEntity, int renderDistanceChunks) {
      int pcx = MathHelper.func_76128_c(viewEntity.field_70165_t) >> 4;
      int pcy = MathHelper.func_76128_c(viewEntity.field_70163_u) >> 4;
      int pcz = MathHelper.func_76128_c(viewEntity.field_70161_v) >> 4;
      Chunk playerChunk = world.func_72964_e(pcx, pcz);
      int cxStart = pcx - renderDistanceChunks;
      int cxEnd = pcx + renderDistanceChunks;
      int czStart = pcz - renderDistanceChunks;
      int czEnd = pcz + renderDistanceChunks;
      if (world != worldLast || pcx != pcxLast || pcz != pczLast) {
         counter = 0;
         iMaxStaticFinal = 16;
         worldLast = world;
         pcxLast = pcx;
         pczLast = pcz;
      }

      if (counter == 0) {
         iMaxStatic = -1;
      }

      int iMax = iMaxStatic;
      switch(counter) {
      case 0:
         cxEnd = pcx;
         czEnd = pcz;
         break;
      case 1:
         cxStart = pcx;
         czEnd = pcz;
         break;
      case 2:
         cxEnd = pcx;
         czStart = pcz;
         break;
      case 3:
         cxStart = pcx;
         czStart = pcz;
      }

      for(int cx = cxStart; cx < cxEnd; ++cx) {
         for(int cz = czStart; cz < czEnd; ++cz) {
            Chunk chunk = world.func_72964_e(cx, cz);
            if (!chunk.func_76621_g()) {
               ExtendedBlockStorage[] ebss = chunk.func_76587_i();

               for(int i = ebss.length - 1; i > iMax; --i) {
                  ExtendedBlockStorage ebs = ebss[i];
                  if (ebs != null && !ebs.func_76663_a()) {
                     if (i > iMax) {
                        iMax = i;
                     }
                     break;
                  }
               }

               try {
                  Map<BlockPos, TileEntity> mapTileEntities = chunk.func_177434_r();
                  if (!mapTileEntities.isEmpty()) {
                     Set<BlockPos> keys = mapTileEntities.keySet();
                     Iterator it = keys.iterator();

                     while(it.hasNext()) {
                        BlockPos pos = (BlockPos)it.next();
                        int i = pos.func_177956_o() >> 4;
                        if (i > iMax) {
                           iMax = i;
                        }
                     }
                  }
               } catch (ConcurrentModificationException var21) {
               }

               ClassInheritanceMultiMap<Entity>[] cimms = chunk.func_177429_s();

               for(int i = cimms.length - 1; i > iMax; --i) {
                  ClassInheritanceMultiMap<Entity> cimm = cimms[i];
                  if (!cimm.isEmpty() && (chunk != playerChunk || i != pcy || cimm.size() != 1)) {
                     if (i > iMax) {
                        iMax = i;
                     }
                     break;
                  }
               }
            }
         }
      }

      if (counter < 3) {
         iMaxStatic = iMax;
         iMax = iMaxStaticFinal;
      } else {
         iMaxStaticFinal = iMax;
         iMaxStatic = -1;
      }

      counter = (counter + 1) % 4;
      return iMax << 4;
   }

   public static boolean isFinished() {
      return counter == 0;
   }

   private static EnumFacing[][] makeEnumFacingArrays(boolean opposite) {
      int count = 64;
      EnumFacing[][] arrs = new EnumFacing[count][];

      for(int i = 0; i < count; ++i) {
         List<EnumFacing> list = new ArrayList();

         for(int ix = 0; ix < EnumFacing.field_82609_l.length; ++ix) {
            EnumFacing facing = EnumFacing.field_82609_l[ix];
            EnumFacing facingMask = opposite ? facing.func_176734_d() : facing;
            int mask = 1 << facingMask.ordinal();
            if ((i & mask) != 0) {
               list.add(facing);
            }
         }

         EnumFacing[] fs = (EnumFacing[])list.toArray(new EnumFacing[list.size()]);
         arrs[i] = fs;
      }

      return arrs;
   }

   public static EnumFacing[] getFacingsNotOpposite(int setDisabled) {
      int index = ~setDisabled & 63;
      return enumFacingOppositeArrays[index];
   }

   public static void reset() {
      worldLast = null;
   }
}
