package net.minecraft.client.renderer;

import java.util.Iterator;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.render.VboRegion;
import net.optifine.shaders.ShadersRender;
import org.lwjgl.opengl.GL11;

public class VboRenderList extends ChunkRenderContainer {
   private double viewEntityX;
   private double viewEntityY;
   private double viewEntityZ;

   public void func_178001_a(EnumWorldBlockLayer p_178001_1_) {
      if (this.field_178007_b) {
         if (!Config.isRenderRegions()) {
            Iterator i$ = this.field_178009_a.iterator();

            while(i$.hasNext()) {
               RenderChunk renderchunk = (RenderChunk)i$.next();
               VertexBuffer vertexbuffer = renderchunk.func_178565_b(p_178001_1_.ordinal());
               GlStateManager.func_179094_E();
               this.func_178003_a(renderchunk);
               renderchunk.func_178572_f();
               vertexbuffer.func_177359_a();
               this.func_178010_a();
               vertexbuffer.func_177358_a(7);
               GlStateManager.func_179121_F();
            }
         } else {
            int regionX = Integer.MIN_VALUE;
            int regionZ = Integer.MIN_VALUE;
            VboRegion lastVboRegion = null;
            Iterator i$ = this.field_178009_a.iterator();

            while(true) {
               if (!i$.hasNext()) {
                  if (lastVboRegion != null) {
                     this.drawRegion(regionX, regionZ, lastVboRegion);
                  }
                  break;
               }

               RenderChunk renderchunk = (RenderChunk)i$.next();
               VertexBuffer vertexbuffer = renderchunk.func_178565_b(p_178001_1_.ordinal());
               VboRegion vboRegion = vertexbuffer.getVboRegion();
               if (vboRegion != lastVboRegion || regionX != renderchunk.regionX || regionZ != renderchunk.regionZ) {
                  if (lastVboRegion != null) {
                     this.drawRegion(regionX, regionZ, lastVboRegion);
                  }

                  regionX = renderchunk.regionX;
                  regionZ = renderchunk.regionZ;
                  lastVboRegion = vboRegion;
               }

               vertexbuffer.func_177358_a(7);
            }
         }

         OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, 0);
         GlStateManager.func_179117_G();
         this.field_178009_a.clear();
      }

   }

   public void func_178010_a() {
      if (Config.isShaders()) {
         ShadersRender.setupArrayPointersVbo();
      } else {
         GL11.glVertexPointer(3, 5126, 28, 0L);
         GL11.glColorPointer(4, 5121, 28, 12L);
         GL11.glTexCoordPointer(2, 5126, 28, 16L);
         OpenGlHelper.func_77472_b(OpenGlHelper.field_77476_b);
         GL11.glTexCoordPointer(2, 5122, 28, 24L);
         OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
      }
   }

   public void func_178004_a(double p_178004_1_, double p_178004_3_, double p_178004_5_) {
      this.viewEntityX = p_178004_1_;
      this.viewEntityY = p_178004_3_;
      this.viewEntityZ = p_178004_5_;
      super.func_178004_a(p_178004_1_, p_178004_3_, p_178004_5_);
   }

   private void drawRegion(int p_drawRegion_1_, int p_drawRegion_2_, VboRegion p_drawRegion_3_) {
      GlStateManager.func_179094_E();
      this.preRenderRegion(p_drawRegion_1_, 0, p_drawRegion_2_);
      p_drawRegion_3_.finishDraw(this);
      GlStateManager.func_179121_F();
   }

   public void preRenderRegion(int p_preRenderRegion_1_, int p_preRenderRegion_2_, int p_preRenderRegion_3_) {
      GlStateManager.func_179109_b((float)((double)p_preRenderRegion_1_ - this.viewEntityX), (float)((double)p_preRenderRegion_2_ - this.viewEntityY), (float)((double)p_preRenderRegion_3_ - this.viewEntityZ));
   }
}
