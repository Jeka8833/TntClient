package net.optifine;

import net.minecraft.src.Config;
import net.minecraft.util.Vec3;

public class CustomColorFader {
   private Vec3 color = null;
   private long timeUpdate = System.currentTimeMillis();

   public Vec3 getColor(double x, double y, double z) {
      if (this.color == null) {
         this.color = new Vec3(x, y, z);
         return this.color;
      } else {
         long timeNow = System.currentTimeMillis();
         long timeDiff = timeNow - this.timeUpdate;
         if (timeDiff == 0L) {
            return this.color;
         } else {
            this.timeUpdate = timeNow;
            if (Math.abs(x - this.color.field_72450_a) < 0.004D && Math.abs(y - this.color.field_72448_b) < 0.004D && Math.abs(z - this.color.field_72449_c) < 0.004D) {
               return this.color;
            } else {
               double k = (double)timeDiff * 0.001D;
               k = Config.limit(k, 0.0D, 1.0D);
               double dx = x - this.color.field_72450_a;
               double dy = y - this.color.field_72448_b;
               double dz = z - this.color.field_72449_c;
               double xn = this.color.field_72450_a + dx * k;
               double yn = this.color.field_72448_b + dy * k;
               double zn = this.color.field_72449_c + dz * k;
               this.color = new Vec3(xn, yn, zn);
               return this.color;
            }
         }
      }
   }
}
