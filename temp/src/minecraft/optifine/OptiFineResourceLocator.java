package optifine;

import java.io.InputStream;

public class OptiFineResourceLocator {
   private static IOptiFineResourceLocator resourceLocator;

   public static void setResourceLocator(IOptiFineResourceLocator resourceLocator) {
      OptiFineResourceLocator.resourceLocator = resourceLocator;
      Class cls = OptiFineResourceLocator.class;
      System.getProperties().put(cls.getName() + ".class", cls);
   }

   public static InputStream getOptiFineResourceStream(String name) {
      return resourceLocator == null ? null : resourceLocator.getOptiFineResourceStream(name);
   }
}
