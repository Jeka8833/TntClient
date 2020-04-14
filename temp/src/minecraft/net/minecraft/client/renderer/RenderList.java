package net.minecraft.client.renderer;

import java.nio.IntBuffer;
import java.util.Iterator;
import net.minecraft.client.renderer.chunk.ListedRenderChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import org.lwjgl.opengl.GL11;

public class RenderList extends ChunkRenderContainer {
   private double viewEntityX;
   private double viewEntityY;
   private double viewEntityZ;
   IntBuffer bufferLists = GLAllocation.func_74527_f(16);

   public void func_178001_a(EnumWorldBlockLayer p_178001_1_) {
      if (this.field_178007_b) {
         if (!Config.isRenderRegions()) {
            Iterator i$ = this.field_178009_a.iterator();

            while(i$.hasNext()) {
               RenderChunk renderchunk = (RenderChunk)i$.next();
               ListedRenderChunk listedrenderchunk = (ListedRenderChunk)renderchunk;
               GlStateManager.func_179094_E();
               this.func_178003_a(renderchunk);
               GL11.glCallList(listedrenderchunk.func_178600_a(p_178001_1_, listedrenderchunk.func_178571_g()));
               GlStateManager.func_179121_F();
            }
         } else {
            int regionX = Integer.MIN_VALUE;
            int regionZ = Integer.MIN_VALUE;
            Iterator i$ = this.field_178009_a.iterator();

            while(true) {
               if (!i$.hasNext()) {
                  if (this.bufferLists.position() > 0) {
                     this.drawRegion(regionX, regionZ, this.bufferLists);
                  }
                  break;
               }

               RenderChunk renderchunk = (RenderChunk)i$.next();
               ListedRenderChunk listedrenderchunk = (ListedRenderChunk)renderchunk;
               if (regionX != renderchunk.regionX || regionZ != renderchunk.regionZ) {
                  if (this.bufferLists.position() > 0) {
                     this.drawRegion(regionX, regionZ, this.bufferLists);
                  }

                  regionX = renderchunk.regionX;
                  regionZ = renderchunk.regionZ;
               }

               if (this.bufferLists.position() >= this.bufferLists.capacity()) {
                  IntBuffer bufferListsNew = GLAllocation.func_74527_f(this.bufferLists.capacity() * 2);
                  this.bufferLists.flip();
                  bufferListsNew.put(this.bufferLists);
                  this.bufferLists = bufferListsNew;
               }

               this.bufferLists.put(listedrenderchunk.func_178600_a(p_178001_1_, listedrenderchunk.func_178571_g()));
            }
         }

         if (Config.isMultiTexture()) {
            GlStateManager.bindCurrentTexture();
         }

         GlStateManager.func_179117_G();
         this.field_178009_a.clear();
      }

   }

   public void func_178004_a(double p_178004_1_, double p_178004_3_, double p_178004_5_) {
      this.viewEntityX = p_178004_1_;
      this.viewEntityY = p_178004_3_;
      this.viewEntityZ = p_178004_5_;
      super.func_178004_a(p_178004_1_, p_178004_3_, p_178004_5_);
   }

   private void drawRegion(int p_drawRegion_1_, int p_drawRegion_2_, IntBuffer p_drawRegion_3_) {
      GlStateManager.func_179094_E();
      this.preRenderRegion(p_drawRegion_1_, 0, p_drawRegion_2_);
      p_drawRegion_3_.flip();
      GlStateManager.callLists(p_drawRegion_3_);
      p_drawRegion_3_.clear();
      GlStateManager.func_179121_F();
   }

   public void preRenderRegion(int p_preRenderRegion_1_, int p_preRenderRegion_2_, int p_preRenderRegion_3_) {
      GlStateManager.func_179109_b((float)((double)p_preRenderRegion_1_ - this.viewEntityX), (float)((double)p_preRenderRegion_2_ - this.viewEntityY), (float)((double)p_preRenderRegion_3_ - this.viewEntityZ));
   }
}
