package net.minecraft.server.management;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class LowerStringMap<V> implements Map<String, V> {
   private final Map<String, V> field_76117_a = Maps.newLinkedHashMap();

   public int size() {
      return this.field_76117_a.size();
   }

   public boolean isEmpty() {
      return this.field_76117_a.isEmpty();
   }

   public boolean containsKey(Object p_containsKey_1_) {
      return this.field_76117_a.containsKey(p_containsKey_1_.toString().toLowerCase());
   }

   public boolean containsValue(Object p_containsValue_1_) {
      return this.field_76117_a.containsKey(p_containsValue_1_);
   }

   public V get(Object p_get_1_) {
      return this.field_76117_a.get(p_get_1_.toString().toLowerCase());
   }

   public V put(String p_put_1_, V p_put_2_) {
      return this.field_76117_a.put(p_put_1_.toLowerCase(), p_put_2_);
   }

   public V remove(Object p_remove_1_) {
      return this.field_76117_a.remove(p_remove_1_.toString().toLowerCase());
   }

   public void putAll(Map<? extends String, ? extends V> p_putAll_1_) {
      Iterator lvt_2_1_ = p_putAll_1_.entrySet().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<? extends String, ? extends V> lvt_3_1_ = (Entry)lvt_2_1_.next();
         this.put((String)lvt_3_1_.getKey(), lvt_3_1_.getValue());
      }

   }

   public void clear() {
      this.field_76117_a.clear();
   }

   public Set<String> keySet() {
      return this.field_76117_a.keySet();
   }

   public Collection<V> values() {
      return this.field_76117_a.values();
   }

   public Set<Entry<String, V>> entrySet() {
      return this.field_76117_a.entrySet();
   }
}
