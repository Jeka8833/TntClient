package net.optifine.override;

import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.DynamicLights;
import net.optifine.reflect.Reflector;
import net.optifine.util.ArrayCache;

public class ChunkCacheOF implements IBlockAccess {
   private final ChunkCache chunkCache;
   private final int posX;
   private final int posY;
   private final int posZ;
   private final int sizeX;
   private final int sizeY;
   private final int sizeZ;
   private final int sizeXY;
   private int[] combinedLights;
   private IBlockState[] blockStates;
   private final int arraySize;
   private final boolean dynamicLights = Config.isDynamicLights();
   private static final ArrayCache cacheCombinedLights;
   private static final ArrayCache cacheBlockStates;

   public ChunkCacheOF(ChunkCache chunkCache, BlockPos posFromIn, BlockPos posToIn, int subIn) {
      this.chunkCache = chunkCache;
      int minChunkX = posFromIn.func_177958_n() - subIn >> 4;
      int minChunkY = posFromIn.func_177956_o() - subIn >> 4;
      int minChunkZ = posFromIn.func_177952_p() - subIn >> 4;
      int maxChunkX = posToIn.func_177958_n() + subIn >> 4;
      int maxChunkY = posToIn.func_177956_o() + subIn >> 4;
      int maxChunkZ = posToIn.func_177952_p() + subIn >> 4;
      this.sizeX = maxChunkX - minChunkX + 1 << 4;
      this.sizeY = maxChunkY - minChunkY + 1 << 4;
      this.sizeZ = maxChunkZ - minChunkZ + 1 << 4;
      this.sizeXY = this.sizeX * this.sizeY;
      this.arraySize = this.sizeX * this.sizeY * this.sizeZ;
      this.posX = minChunkX << 4;
      this.posY = minChunkY << 4;
      this.posZ = minChunkZ << 4;
   }

   private int getPositionIndex(BlockPos pos) {
      int dx = pos.func_177958_n() - this.posX;
      if (dx >= 0 && dx < this.sizeX) {
         int dy = pos.func_177956_o() - this.posY;
         if (dy >= 0 && dy < this.sizeY) {
            int dz = pos.func_177952_p() - this.posZ;
            return dz >= 0 && dz < this.sizeZ ? dz * this.sizeXY + dy * this.sizeX + dx : -1;
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   public int func_175626_b(BlockPos pos, int lightValue) {
      int index = this.getPositionIndex(pos);
      if (index >= 0 && index < this.arraySize && this.combinedLights != null) {
         int light = this.combinedLights[index];
         if (light == -1) {
            light = this.getCombinedLightRaw(pos, lightValue);
            this.combinedLights[index] = light;
         }

         return light;
      } else {
         return this.getCombinedLightRaw(pos, lightValue);
      }
   }

   private int getCombinedLightRaw(BlockPos pos, int lightValue) {
      int light = this.chunkCache.func_175626_b(pos, lightValue);
      if (this.dynamicLights && !this.func_180495_p(pos).func_177230_c().func_149662_c()) {
         light = DynamicLights.getCombinedLight(pos, light);
      }

      return light;
   }

   public IBlockState func_180495_p(BlockPos pos) {
      int index = this.getPositionIndex(pos);
      if (index >= 0 && index < this.arraySize && this.blockStates != null) {
         IBlockState iblockstate = this.blockStates[index];
         if (iblockstate == null) {
            iblockstate = this.chunkCache.func_180495_p(pos);
            this.blockStates[index] = iblockstate;
         }

         return iblockstate;
      } else {
         return this.chunkCache.func_180495_p(pos);
      }
   }

   public void renderStart() {
      if (this.combinedLights == null) {
         this.combinedLights = (int[])((int[])cacheCombinedLights.allocate(this.arraySize));
      }

      Arrays.fill(this.combinedLights, -1);
      if (this.blockStates == null) {
         this.blockStates = (IBlockState[])((IBlockState[])cacheBlockStates.allocate(this.arraySize));
      }

      Arrays.fill(this.blockStates, (Object)null);
   }

   public void renderFinish() {
      cacheCombinedLights.free(this.combinedLights);
      this.combinedLights = null;
      cacheBlockStates.free(this.blockStates);
      this.blockStates = null;
   }

   public boolean func_72806_N() {
      return this.chunkCache.func_72806_N();
   }

   public BiomeGenBase func_180494_b(BlockPos pos) {
      return this.chunkCache.func_180494_b(pos);
   }

   public int func_175627_a(BlockPos pos, EnumFacing direction) {
      return this.chunkCache.func_175627_a(pos, direction);
   }

   public TileEntity func_175625_s(BlockPos pos) {
      return this.chunkCache.func_175625_s(pos);
   }

   public WorldType func_175624_G() {
      return this.chunkCache.func_175624_G();
   }

   public boolean func_175623_d(BlockPos pos) {
      return this.chunkCache.func_175623_d(pos);
   }

   public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
      return Reflector.callBoolean(this.chunkCache, Reflector.ForgeChunkCache_isSideSolid, pos, side, _default);
   }

   static {
      cacheCombinedLights = new ArrayCache(Integer.TYPE, 16);
      cacheBlockStates = new ArrayCache(IBlockState.class, 16);
   }
}
