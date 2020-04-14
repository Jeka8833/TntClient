package net.optifine.util;

import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class RenderChunkUtils {
   public static int getCountBlocks(RenderChunk renderChunk) {
      ExtendedBlockStorage[] ebss = renderChunk.getChunk().func_76587_i();
      if (ebss == null) {
         return 0;
      } else {
         int indexEbs = renderChunk.func_178568_j().func_177956_o() >> 4;
         ExtendedBlockStorage ebs = ebss[indexEbs];
         return ebs == null ? 0 : ebs.getBlockRefCount();
      }
   }

   public static double getRelativeBufferSize(RenderChunk renderChunk) {
      int blockCount = getCountBlocks(renderChunk);
      double vertexCountRel = getRelativeBufferSize(blockCount);
      return vertexCountRel;
   }

   public static double getRelativeBufferSize(int blockCount) {
      double countRel = (double)blockCount / 4096.0D;
      countRel *= 0.995D;
      double weight = countRel * 2.0D - 1.0D;
      weight = MathHelper.func_151237_a(weight, -1.0D, 1.0D);
      return (double)MathHelper.func_76133_a(1.0D - weight * weight);
   }
}
