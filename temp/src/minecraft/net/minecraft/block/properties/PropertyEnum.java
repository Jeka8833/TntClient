package net.minecraft.block.properties;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.IStringSerializable;

public class PropertyEnum<T extends Enum<T> & IStringSerializable> extends PropertyHelper<T> {
   private final ImmutableSet<T> field_177711_a;
   private final Map<String, T> field_177710_b = Maps.newHashMap();

   protected PropertyEnum(String p_i45649_1_, Class<T> p_i45649_2_, Collection<T> p_i45649_3_) {
      super(p_i45649_1_, p_i45649_2_);
      this.field_177711_a = ImmutableSet.copyOf(p_i45649_3_);
      Iterator lvt_4_1_ = p_i45649_3_.iterator();

      while(lvt_4_1_.hasNext()) {
         T lvt_5_1_ = (Enum)lvt_4_1_.next();
         String lvt_6_1_ = ((IStringSerializable)lvt_5_1_).func_176610_l();
         if (this.field_177710_b.containsKey(lvt_6_1_)) {
            throw new IllegalArgumentException("Multiple values have the same name '" + lvt_6_1_ + "'");
         }

         this.field_177710_b.put(lvt_6_1_, lvt_5_1_);
      }

   }

   public Collection<T> func_177700_c() {
      return this.field_177711_a;
   }

   public String func_177702_a(T p_177702_1_) {
      return ((IStringSerializable)p_177702_1_).func_176610_l();
   }

   public static <T extends Enum<T> & IStringSerializable> PropertyEnum<T> func_177709_a(String p_177709_0_, Class<T> p_177709_1_) {
      return func_177708_a(p_177709_0_, p_177709_1_, Predicates.alwaysTrue());
   }

   public static <T extends Enum<T> & IStringSerializable> PropertyEnum<T> func_177708_a(String p_177708_0_, Class<T> p_177708_1_, Predicate<T> p_177708_2_) {
      return func_177707_a(p_177708_0_, p_177708_1_, Collections2.filter(Lists.newArrayList(p_177708_1_.getEnumConstants()), p_177708_2_));
   }

   public static <T extends Enum<T> & IStringSerializable> PropertyEnum<T> func_177706_a(String p_177706_0_, Class<T> p_177706_1_, T... p_177706_2_) {
      return func_177707_a(p_177706_0_, p_177706_1_, Lists.newArrayList((Object[])p_177706_2_));
   }

   public static <T extends Enum<T> & IStringSerializable> PropertyEnum<T> func_177707_a(String p_177707_0_, Class<T> p_177707_1_, Collection<T> p_177707_2_) {
      return new PropertyEnum(p_177707_0_, p_177707_1_, p_177707_2_);
   }
}
