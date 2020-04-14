package net.optifine;

import java.util.Comparator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;

public class ChunkPosComparator implements Comparator<ChunkCoordIntPair> {
   private int chunkPosX;
   private int chunkPosZ;
   private double yawRad;
   private double pitchNorm;

   public ChunkPosComparator(int chunkPosX, int chunkPosZ, double yawRad, double pitchRad) {
      this.chunkPosX = chunkPosX;
      this.chunkPosZ = chunkPosZ;
      this.yawRad = yawRad;
      this.pitchNorm = 1.0D - MathHelper.func_151237_a(Math.abs(pitchRad) / 1.5707963267948966D, 0.0D, 1.0D);
   }

   public int compare(ChunkCoordIntPair cp1, ChunkCoordIntPair cp2) {
      int distSq1 = this.getDistSq(cp1);
      int distSq2 = this.getDistSq(cp2);
      return distSq1 - distSq2;
   }

   private int getDistSq(ChunkCoordIntPair cp) {
      int dx = cp.field_77276_a - this.chunkPosX;
      int dz = cp.field_77275_b - this.chunkPosZ;
      int distSq = dx * dx + dz * dz;
      double yaw = MathHelper.func_181159_b((double)dz, (double)dx);
      double dYaw = Math.abs(yaw - this.yawRad);
      if (dYaw > 3.141592653589793D) {
         dYaw = 6.283185307179586D - dYaw;
      }

      distSq = (int)((double)distSq * 1000.0D * this.pitchNorm * dYaw * dYaw);
      return distSq;
   }
}
