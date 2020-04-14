package net.optifine;

import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.util.TileEntityUtils;

public class RandomTileEntity implements IRandomEntity {
   private TileEntity tileEntity;

   public int getId() {
      return Config.getRandom(this.tileEntity.func_174877_v(), this.tileEntity.func_145832_p());
   }

   public BlockPos getSpawnPosition() {
      return this.tileEntity.func_174877_v();
   }

   public String getName() {
      String name = TileEntityUtils.getTileEntityName(this.tileEntity);
      return name;
   }

   public BiomeGenBase getSpawnBiome() {
      return this.tileEntity.func_145831_w().func_180494_b(this.tileEntity.func_174877_v());
   }

   public int getHealth() {
      return -1;
   }

   public int getMaxHealth() {
      return -1;
   }

   public TileEntity getTileEntity() {
      return this.tileEntity;
   }

   public void setTileEntity(TileEntity tileEntity) {
      this.tileEntity = tileEntity;
   }
}
