package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class ModelBlockDefinition {
   static final Gson field_178333_a = (new GsonBuilder()).registerTypeAdapter(ModelBlockDefinition.class, new ModelBlockDefinition.Deserializer()).registerTypeAdapter(ModelBlockDefinition.Variant.class, new ModelBlockDefinition.Variant.Deserializer()).create();
   private final Map<String, ModelBlockDefinition.Variants> field_178332_b = Maps.newHashMap();

   public static ModelBlockDefinition func_178331_a(Reader p_178331_0_) {
      return (ModelBlockDefinition)field_178333_a.fromJson(p_178331_0_, ModelBlockDefinition.class);
   }

   public ModelBlockDefinition(Collection<ModelBlockDefinition.Variants> p_i46221_1_) {
      Iterator lvt_2_1_ = p_i46221_1_.iterator();

      while(lvt_2_1_.hasNext()) {
         ModelBlockDefinition.Variants lvt_3_1_ = (ModelBlockDefinition.Variants)lvt_2_1_.next();
         this.field_178332_b.put(lvt_3_1_.field_178423_a, lvt_3_1_);
      }

   }

   public ModelBlockDefinition(List<ModelBlockDefinition> p_i46222_1_) {
      Iterator lvt_2_1_ = p_i46222_1_.iterator();

      while(lvt_2_1_.hasNext()) {
         ModelBlockDefinition lvt_3_1_ = (ModelBlockDefinition)lvt_2_1_.next();
         this.field_178332_b.putAll(lvt_3_1_.field_178332_b);
      }

   }

   public ModelBlockDefinition.Variants func_178330_b(String p_178330_1_) {
      ModelBlockDefinition.Variants lvt_2_1_ = (ModelBlockDefinition.Variants)this.field_178332_b.get(p_178330_1_);
      if (lvt_2_1_ == null) {
         throw new ModelBlockDefinition.MissingVariantException();
      } else {
         return lvt_2_1_;
      }
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ instanceof ModelBlockDefinition) {
         ModelBlockDefinition lvt_2_1_ = (ModelBlockDefinition)p_equals_1_;
         return this.field_178332_b.equals(lvt_2_1_.field_178332_b);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.field_178332_b.hashCode();
   }

   public class MissingVariantException extends RuntimeException {
      protected MissingVariantException() {
      }
   }

   public static class Deserializer implements JsonDeserializer<ModelBlockDefinition> {
      public ModelBlockDefinition deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
         List<ModelBlockDefinition.Variants> lvt_5_1_ = this.func_178334_a(p_deserialize_3_, lvt_4_1_);
         return new ModelBlockDefinition(lvt_5_1_);
      }

      protected List<ModelBlockDefinition.Variants> func_178334_a(JsonDeserializationContext p_178334_1_, JsonObject p_178334_2_) {
         JsonObject lvt_3_1_ = JsonUtils.func_152754_s(p_178334_2_, "variants");
         List<ModelBlockDefinition.Variants> lvt_4_1_ = Lists.newArrayList();
         Iterator lvt_5_1_ = lvt_3_1_.entrySet().iterator();

         while(lvt_5_1_.hasNext()) {
            Entry<String, JsonElement> lvt_6_1_ = (Entry)lvt_5_1_.next();
            lvt_4_1_.add(this.func_178335_a(p_178334_1_, lvt_6_1_));
         }

         return lvt_4_1_;
      }

      protected ModelBlockDefinition.Variants func_178335_a(JsonDeserializationContext p_178335_1_, Entry<String, JsonElement> p_178335_2_) {
         String lvt_3_1_ = (String)p_178335_2_.getKey();
         List<ModelBlockDefinition.Variant> lvt_4_1_ = Lists.newArrayList();
         JsonElement lvt_5_1_ = (JsonElement)p_178335_2_.getValue();
         if (lvt_5_1_.isJsonArray()) {
            Iterator lvt_6_1_ = lvt_5_1_.getAsJsonArray().iterator();

            while(lvt_6_1_.hasNext()) {
               JsonElement lvt_7_1_ = (JsonElement)lvt_6_1_.next();
               lvt_4_1_.add((ModelBlockDefinition.Variant)p_178335_1_.deserialize(lvt_7_1_, ModelBlockDefinition.Variant.class));
            }
         } else {
            lvt_4_1_.add((ModelBlockDefinition.Variant)p_178335_1_.deserialize(lvt_5_1_, ModelBlockDefinition.Variant.class));
         }

         return new ModelBlockDefinition.Variants(lvt_3_1_, lvt_4_1_);
      }
   }

   public static class Variant {
      private final ResourceLocation field_178437_a;
      private final ModelRotation field_178435_b;
      private final boolean field_178436_c;
      private final int field_178434_d;

      public Variant(ResourceLocation p_i46219_1_, ModelRotation p_i46219_2_, boolean p_i46219_3_, int p_i46219_4_) {
         this.field_178437_a = p_i46219_1_;
         this.field_178435_b = p_i46219_2_;
         this.field_178436_c = p_i46219_3_;
         this.field_178434_d = p_i46219_4_;
      }

      public ResourceLocation func_178431_a() {
         return this.field_178437_a;
      }

      public ModelRotation func_178432_b() {
         return this.field_178435_b;
      }

      public boolean func_178433_c() {
         return this.field_178436_c;
      }

      public int func_178430_d() {
         return this.field_178434_d;
      }

      public boolean equals(Object p_equals_1_) {
         if (this == p_equals_1_) {
            return true;
         } else if (!(p_equals_1_ instanceof ModelBlockDefinition.Variant)) {
            return false;
         } else {
            ModelBlockDefinition.Variant lvt_2_1_ = (ModelBlockDefinition.Variant)p_equals_1_;
            return this.field_178437_a.equals(lvt_2_1_.field_178437_a) && this.field_178435_b == lvt_2_1_.field_178435_b && this.field_178436_c == lvt_2_1_.field_178436_c;
         }
      }

      public int hashCode() {
         int lvt_1_1_ = this.field_178437_a.hashCode();
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_178435_b != null ? this.field_178435_b.hashCode() : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_178436_c ? 1 : 0);
         return lvt_1_1_;
      }

      public static class Deserializer implements JsonDeserializer<ModelBlockDefinition.Variant> {
         public ModelBlockDefinition.Variant deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
            String lvt_5_1_ = this.func_178424_b(lvt_4_1_);
            ModelRotation lvt_6_1_ = this.func_178428_a(lvt_4_1_);
            boolean lvt_7_1_ = this.func_178429_d(lvt_4_1_);
            int lvt_8_1_ = this.func_178427_c(lvt_4_1_);
            return new ModelBlockDefinition.Variant(this.func_178426_a(lvt_5_1_), lvt_6_1_, lvt_7_1_, lvt_8_1_);
         }

         private ResourceLocation func_178426_a(String p_178426_1_) {
            ResourceLocation lvt_2_1_ = new ResourceLocation(p_178426_1_);
            lvt_2_1_ = new ResourceLocation(lvt_2_1_.func_110624_b(), "block/" + lvt_2_1_.func_110623_a());
            return lvt_2_1_;
         }

         private boolean func_178429_d(JsonObject p_178429_1_) {
            return JsonUtils.func_151209_a(p_178429_1_, "uvlock", false);
         }

         protected ModelRotation func_178428_a(JsonObject p_178428_1_) {
            int lvt_2_1_ = JsonUtils.func_151208_a(p_178428_1_, "x", 0);
            int lvt_3_1_ = JsonUtils.func_151208_a(p_178428_1_, "y", 0);
            ModelRotation lvt_4_1_ = ModelRotation.func_177524_a(lvt_2_1_, lvt_3_1_);
            if (lvt_4_1_ == null) {
               throw new JsonParseException("Invalid BlockModelRotation x: " + lvt_2_1_ + ", y: " + lvt_3_1_);
            } else {
               return lvt_4_1_;
            }
         }

         protected String func_178424_b(JsonObject p_178424_1_) {
            return JsonUtils.func_151200_h(p_178424_1_, "model");
         }

         protected int func_178427_c(JsonObject p_178427_1_) {
            return JsonUtils.func_151208_a(p_178427_1_, "weight", 1);
         }
      }
   }

   public static class Variants {
      private final String field_178423_a;
      private final List<ModelBlockDefinition.Variant> field_178422_b;

      public Variants(String p_i46218_1_, List<ModelBlockDefinition.Variant> p_i46218_2_) {
         this.field_178423_a = p_i46218_1_;
         this.field_178422_b = p_i46218_2_;
      }

      public List<ModelBlockDefinition.Variant> func_178420_b() {
         return this.field_178422_b;
      }

      public boolean equals(Object p_equals_1_) {
         if (this == p_equals_1_) {
            return true;
         } else if (!(p_equals_1_ instanceof ModelBlockDefinition.Variants)) {
            return false;
         } else {
            ModelBlockDefinition.Variants lvt_2_1_ = (ModelBlockDefinition.Variants)p_equals_1_;
            if (!this.field_178423_a.equals(lvt_2_1_.field_178423_a)) {
               return false;
            } else {
               return this.field_178422_b.equals(lvt_2_1_.field_178422_b);
            }
         }
      }

      public int hashCode() {
         int lvt_1_1_ = this.field_178423_a.hashCode();
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_178422_b.hashCode();
         return lvt_1_1_;
      }
   }
}
