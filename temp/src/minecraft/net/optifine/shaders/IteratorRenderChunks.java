package net.optifine.shaders;

import java.util.Iterator;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.optifine.BlockPosM;

public class IteratorRenderChunks implements Iterator<RenderChunk> {
   private ViewFrustum viewFrustum;
   private Iterator3d Iterator3d;
   private BlockPosM posBlock = new BlockPosM(0, 0, 0);

   public IteratorRenderChunks(ViewFrustum viewFrustum, BlockPos posStart, BlockPos posEnd, int width, int height) {
      this.viewFrustum = viewFrustum;
      this.Iterator3d = new Iterator3d(posStart, posEnd, width, height);
   }

   public boolean hasNext() {
      return this.Iterator3d.hasNext();
   }

   public RenderChunk next() {
      BlockPos pos = this.Iterator3d.next();
      this.posBlock.setXyz(pos.func_177958_n() << 4, pos.func_177956_o() << 4, pos.func_177952_p() << 4);
      RenderChunk rc = this.viewFrustum.func_178161_a(this.posBlock);
      return rc;
   }

   public void remove() {
      throw new RuntimeException("Not implemented");
   }
}
