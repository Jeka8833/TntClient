package net.optifine.util;

public class ArrayUtils {
   public static boolean contains(Object[] arr, Object val) {
      if (arr == null) {
         return false;
      } else {
         for(int i = 0; i < arr.length; ++i) {
            Object obj = arr[i];
            if (obj == val) {
               return true;
            }
         }

         return false;
      }
   }
}
