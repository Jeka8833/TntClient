package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class MapPopulator {
   public static <K, V> Map<K, V> func_179400_b(Iterable<K> p_179400_0_, Iterable<V> p_179400_1_) {
      return func_179399_a(p_179400_0_, p_179400_1_, Maps.newLinkedHashMap());
   }

   public static <K, V> Map<K, V> func_179399_a(Iterable<K> p_179399_0_, Iterable<V> p_179399_1_, Map<K, V> p_179399_2_) {
      Iterator<V> lvt_3_1_ = p_179399_1_.iterator();
      Iterator lvt_4_1_ = p_179399_0_.iterator();

      while(lvt_4_1_.hasNext()) {
         K lvt_5_1_ = lvt_4_1_.next();
         p_179399_2_.put(lvt_5_1_, lvt_3_1_.next());
      }

      if (lvt_3_1_.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return p_179399_2_;
      }
   }
}
