package net.optifine.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.src.Config;

public class EntityUtils {
   private static final Map<Class, Integer> mapIdByClass = new HashMap();
   private static final Map<String, Integer> mapIdByName = new HashMap();
   private static final Map<String, Class> mapClassByName = new HashMap();

   public static int getEntityIdByClass(Entity entity) {
      return entity == null ? -1 : getEntityIdByClass(entity.getClass());
   }

   public static int getEntityIdByClass(Class cls) {
      Integer id = (Integer)mapIdByClass.get(cls);
      return id == null ? -1 : id;
   }

   public static int getEntityIdByName(String name) {
      Integer id = (Integer)mapIdByName.get(name);
      return id == null ? -1 : id;
   }

   public static Class getEntityClassByName(String name) {
      Class cls = (Class)mapClassByName.get(name);
      return cls;
   }

   static {
      for(int i = 0; i < 1000; ++i) {
         Class cls = EntityList.func_90035_a(i);
         if (cls != null) {
            String name = EntityList.func_75617_a(i);
            if (name != null) {
               if (mapIdByClass.containsKey(cls)) {
                  Config.warn("Duplicate entity class: " + cls + ", id1: " + mapIdByClass.get(cls) + ", id2: " + i);
               }

               if (mapIdByName.containsKey(name)) {
                  Config.warn("Duplicate entity name: " + name + ", id1: " + mapIdByName.get(name) + ", id2: " + i);
               }

               if (mapClassByName.containsKey(name)) {
                  Config.warn("Duplicate entity name: " + name + ", class1: " + mapClassByName.get(name) + ", class2: " + cls);
               }

               mapIdByClass.put(cls, i);
               mapIdByName.put(name, i);
               mapClassByName.put(name, cls);
            }
         }
      }

   }
}
