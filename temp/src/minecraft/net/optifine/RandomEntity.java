package net.optifine;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;

public class RandomEntity implements IRandomEntity {
   private Entity entity;

   public int getId() {
      UUID uuid = this.entity.func_110124_au();
      long uuidLow = uuid.getLeastSignificantBits();
      int id = (int)(uuidLow & 2147483647L);
      return id;
   }

   public BlockPos getSpawnPosition() {
      return this.entity.func_70096_w().spawnPosition;
   }

   public BiomeGenBase getSpawnBiome() {
      return this.entity.func_70096_w().spawnBiome;
   }

   public String getName() {
      return this.entity.func_145818_k_() ? this.entity.func_95999_t() : null;
   }

   public int getHealth() {
      if (!(this.entity instanceof EntityLiving)) {
         return 0;
      } else {
         EntityLiving el = (EntityLiving)this.entity;
         return (int)el.func_110143_aJ();
      }
   }

   public int getMaxHealth() {
      if (!(this.entity instanceof EntityLiving)) {
         return 0;
      } else {
         EntityLiving el = (EntityLiving)this.entity;
         return (int)el.func_110138_aP();
      }
   }

   public Entity getEntity() {
      return this.entity;
   }

   public void setEntity(Entity entity) {
      this.entity = entity;
   }
}
