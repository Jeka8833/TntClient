package net.optifine.util;

public class NumUtils {
   public static float limit(float val, float min, float max) {
      if (val < min) {
         return min;
      } else {
         return val > max ? max : val;
      }
   }

   public static int mod(int x, int y) {
      int result = x % y;
      if (result < 0) {
         result += y;
      }

      return result;
   }
}
