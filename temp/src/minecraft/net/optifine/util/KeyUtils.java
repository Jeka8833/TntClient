package net.optifine.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.client.settings.KeyBinding;

public class KeyUtils {
   public static void fixKeyConflicts(KeyBinding[] keys, KeyBinding[] keysPrio) {
      Set<Integer> keyPrioCodes = new HashSet();

      for(int i = 0; i < keysPrio.length; ++i) {
         KeyBinding keyPrio = keysPrio[i];
         keyPrioCodes.add(keyPrio.func_151463_i());
      }

      Set<KeyBinding> setKeys = new HashSet(Arrays.asList(keys));
      setKeys.removeAll(Arrays.asList(keysPrio));
      Iterator iterator = setKeys.iterator();

      while(iterator.hasNext()) {
         KeyBinding key = (KeyBinding)iterator.next();
         Integer code = key.func_151463_i();
         if (keyPrioCodes.contains(code)) {
            key.func_151462_b(0);
         }
      }

   }
}
