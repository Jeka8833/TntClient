package net.optifine.shaders;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class ShadowUtils {
   public static Iterator<RenderChunk> makeShadowChunkIterator(WorldClient world, double partialTicks, Entity viewEntity, int renderDistanceChunks, ViewFrustum viewFrustum) {
      float shadowRenderDistance = Shaders.getShadowRenderDistance();
      if (shadowRenderDistance > 0.0F && shadowRenderDistance < (float)((renderDistanceChunks - 1) * 16)) {
         int shadowDistanceChunks = MathHelper.func_76123_f(shadowRenderDistance / 16.0F) + 1;
         float car = world.func_72929_e((float)partialTicks);
         float sunTiltRad = Shaders.sunPathRotation * MathHelper.deg2Rad;
         float sar = car > MathHelper.PId2 && car < 3.0F * MathHelper.PId2 ? car + MathHelper.PI : car;
         float dx = -MathHelper.func_76126_a(sar);
         float dy = MathHelper.func_76134_b(sar) * MathHelper.func_76134_b(sunTiltRad);
         float dz = -MathHelper.func_76134_b(sar) * MathHelper.func_76126_a(sunTiltRad);
         BlockPos posEntity = new BlockPos(MathHelper.func_76128_c(viewEntity.field_70165_t) >> 4, MathHelper.func_76128_c(viewEntity.field_70163_u) >> 4, MathHelper.func_76128_c(viewEntity.field_70161_v) >> 4);
         BlockPos posStart = posEntity.func_177963_a((double)(-dx * (float)shadowDistanceChunks), (double)(-dy * (float)shadowDistanceChunks), (double)(-dz * (float)shadowDistanceChunks));
         BlockPos posEnd = posEntity.func_177963_a((double)(dx * (float)renderDistanceChunks), (double)(dy * (float)renderDistanceChunks), (double)(dz * (float)renderDistanceChunks));
         IteratorRenderChunks it = new IteratorRenderChunks(viewFrustum, posStart, posEnd, shadowDistanceChunks, shadowDistanceChunks);
         return it;
      } else {
         List<RenderChunk> listChunks = Arrays.asList(viewFrustum.field_178164_f);
         Iterator<RenderChunk> it = listChunks.iterator();
         return it;
      }
   }
}
