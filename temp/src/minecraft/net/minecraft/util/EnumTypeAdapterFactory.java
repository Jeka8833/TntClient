package net.minecraft.util;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class EnumTypeAdapterFactory implements TypeAdapterFactory {
   public <T> TypeAdapter<T> create(Gson p_create_1_, TypeToken<T> p_create_2_) {
      Class<T> lvt_3_1_ = p_create_2_.getRawType();
      if (!lvt_3_1_.isEnum()) {
         return null;
      } else {
         final Map<String, T> lvt_4_1_ = Maps.newHashMap();
         Object[] lvt_5_1_ = lvt_3_1_.getEnumConstants();
         int lvt_6_1_ = lvt_5_1_.length;

         for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_; ++lvt_7_1_) {
            T lvt_8_1_ = lvt_5_1_[lvt_7_1_];
            lvt_4_1_.put(this.func_151232_a(lvt_8_1_), lvt_8_1_);
         }

         return new TypeAdapter<T>() {
            public void write(JsonWriter p_write_1_, T p_write_2_) throws IOException {
               if (p_write_2_ == null) {
                  p_write_1_.nullValue();
               } else {
                  p_write_1_.value(EnumTypeAdapterFactory.this.func_151232_a(p_write_2_));
               }

            }

            public T read(JsonReader p_read_1_) throws IOException {
               if (p_read_1_.peek() == JsonToken.NULL) {
                  p_read_1_.nextNull();
                  return null;
               } else {
                  return lvt_4_1_.get(p_read_1_.nextString());
               }
            }
         };
      }
   }

   private String func_151232_a(Object p_151232_1_) {
      return p_151232_1_ instanceof Enum ? ((Enum)p_151232_1_).name().toLowerCase(Locale.US) : p_151232_1_.toString().toLowerCase(Locale.US);
   }
}
