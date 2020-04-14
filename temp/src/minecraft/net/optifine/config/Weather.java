package net.optifine.config;

import net.minecraft.world.World;

public enum Weather {
   CLEAR,
   RAIN,
   THUNDER;

   public static Weather getWeather(World world, float partialTicks) {
      float thunderStrength = world.func_72819_i(partialTicks);
      if (thunderStrength > 0.5F) {
         return THUNDER;
      } else {
         float rainStrength = world.func_72867_j(partialTicks);
         return rainStrength > 0.5F ? RAIN : CLEAR;
      }
   }
}
