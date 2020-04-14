package net.minecraft.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public interface IChatComponent extends Iterable<IChatComponent> {
   IChatComponent func_150255_a(ChatStyle var1);

   ChatStyle func_150256_b();

   IChatComponent func_150258_a(String var1);

   IChatComponent func_150257_a(IChatComponent var1);

   String func_150261_e();

   String func_150260_c();

   String func_150254_d();

   List<IChatComponent> func_150253_a();

   IChatComponent func_150259_f();

   public static class Serializer implements JsonDeserializer<IChatComponent>, JsonSerializer<IChatComponent> {
      private static final Gson field_150700_a;

      public IChatComponent deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         if (p_deserialize_1_.isJsonPrimitive()) {
            return new ChatComponentText(p_deserialize_1_.getAsString());
         } else if (!p_deserialize_1_.isJsonObject()) {
            if (p_deserialize_1_.isJsonArray()) {
               JsonArray lvt_4_2_ = p_deserialize_1_.getAsJsonArray();
               IChatComponent lvt_5_8_ = null;
               Iterator lvt_6_4_ = lvt_4_2_.iterator();

               while(lvt_6_4_.hasNext()) {
                  JsonElement lvt_7_3_ = (JsonElement)lvt_6_4_.next();
                  IChatComponent lvt_8_2_ = this.deserialize(lvt_7_3_, lvt_7_3_.getClass(), p_deserialize_3_);
                  if (lvt_5_8_ == null) {
                     lvt_5_8_ = lvt_8_2_;
                  } else {
                     lvt_5_8_.func_150257_a(lvt_8_2_);
                  }
               }

               return lvt_5_8_;
            } else {
               throw new JsonParseException("Don't know how to turn " + p_deserialize_1_.toString() + " into a Component");
            }
         } else {
            JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
            Object lvt_5_4_;
            if (lvt_4_1_.has("text")) {
               lvt_5_4_ = new ChatComponentText(lvt_4_1_.get("text").getAsString());
            } else if (lvt_4_1_.has("translate")) {
               String lvt_6_1_ = lvt_4_1_.get("translate").getAsString();
               if (lvt_4_1_.has("with")) {
                  JsonArray lvt_7_1_ = lvt_4_1_.getAsJsonArray("with");
                  Object[] lvt_8_1_ = new Object[lvt_7_1_.size()];

                  for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_.length; ++lvt_9_1_) {
                     lvt_8_1_[lvt_9_1_] = this.deserialize(lvt_7_1_.get(lvt_9_1_), p_deserialize_2_, p_deserialize_3_);
                     if (lvt_8_1_[lvt_9_1_] instanceof ChatComponentText) {
                        ChatComponentText lvt_10_1_ = (ChatComponentText)lvt_8_1_[lvt_9_1_];
                        if (lvt_10_1_.func_150256_b().func_150229_g() && lvt_10_1_.func_150253_a().isEmpty()) {
                           lvt_8_1_[lvt_9_1_] = lvt_10_1_.func_150265_g();
                        }
                     }
                  }

                  lvt_5_4_ = new ChatComponentTranslation(lvt_6_1_, lvt_8_1_);
               } else {
                  lvt_5_4_ = new ChatComponentTranslation(lvt_6_1_, new Object[0]);
               }
            } else if (lvt_4_1_.has("score")) {
               JsonObject lvt_6_2_ = lvt_4_1_.getAsJsonObject("score");
               if (!lvt_6_2_.has("name") || !lvt_6_2_.has("objective")) {
                  throw new JsonParseException("A score component needs a least a name and an objective");
               }

               lvt_5_4_ = new ChatComponentScore(JsonUtils.func_151200_h(lvt_6_2_, "name"), JsonUtils.func_151200_h(lvt_6_2_, "objective"));
               if (lvt_6_2_.has("value")) {
                  ((ChatComponentScore)lvt_5_4_).func_179997_b(JsonUtils.func_151200_h(lvt_6_2_, "value"));
               }
            } else {
               if (!lvt_4_1_.has("selector")) {
                  throw new JsonParseException("Don't know how to turn " + p_deserialize_1_.toString() + " into a Component");
               }

               lvt_5_4_ = new ChatComponentSelector(JsonUtils.func_151200_h(lvt_4_1_, "selector"));
            }

            if (lvt_4_1_.has("extra")) {
               JsonArray lvt_6_3_ = lvt_4_1_.getAsJsonArray("extra");
               if (lvt_6_3_.size() <= 0) {
                  throw new JsonParseException("Unexpected empty array of components");
               }

               for(int lvt_7_2_ = 0; lvt_7_2_ < lvt_6_3_.size(); ++lvt_7_2_) {
                  ((IChatComponent)lvt_5_4_).func_150257_a(this.deserialize(lvt_6_3_.get(lvt_7_2_), p_deserialize_2_, p_deserialize_3_));
               }
            }

            ((IChatComponent)lvt_5_4_).func_150255_a((ChatStyle)p_deserialize_3_.deserialize(p_deserialize_1_, ChatStyle.class));
            return (IChatComponent)lvt_5_4_;
         }
      }

      private void func_150695_a(ChatStyle p_150695_1_, JsonObject p_150695_2_, JsonSerializationContext p_150695_3_) {
         JsonElement lvt_4_1_ = p_150695_3_.serialize(p_150695_1_);
         if (lvt_4_1_.isJsonObject()) {
            JsonObject lvt_5_1_ = (JsonObject)lvt_4_1_;
            Iterator lvt_6_1_ = lvt_5_1_.entrySet().iterator();

            while(lvt_6_1_.hasNext()) {
               Entry<String, JsonElement> lvt_7_1_ = (Entry)lvt_6_1_.next();
               p_150695_2_.add((String)lvt_7_1_.getKey(), (JsonElement)lvt_7_1_.getValue());
            }
         }

      }

      public JsonElement serialize(IChatComponent p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
         if (p_serialize_1_ instanceof ChatComponentText && p_serialize_1_.func_150256_b().func_150229_g() && p_serialize_1_.func_150253_a().isEmpty()) {
            return new JsonPrimitive(((ChatComponentText)p_serialize_1_).func_150265_g());
         } else {
            JsonObject lvt_4_1_ = new JsonObject();
            if (!p_serialize_1_.func_150256_b().func_150229_g()) {
               this.func_150695_a(p_serialize_1_.func_150256_b(), lvt_4_1_, p_serialize_3_);
            }

            if (!p_serialize_1_.func_150253_a().isEmpty()) {
               JsonArray lvt_5_1_ = new JsonArray();
               Iterator lvt_6_1_ = p_serialize_1_.func_150253_a().iterator();

               while(lvt_6_1_.hasNext()) {
                  IChatComponent lvt_7_1_ = (IChatComponent)lvt_6_1_.next();
                  lvt_5_1_.add(this.serialize((IChatComponent)lvt_7_1_, lvt_7_1_.getClass(), p_serialize_3_));
               }

               lvt_4_1_.add("extra", lvt_5_1_);
            }

            if (p_serialize_1_ instanceof ChatComponentText) {
               lvt_4_1_.addProperty("text", ((ChatComponentText)p_serialize_1_).func_150265_g());
            } else if (p_serialize_1_ instanceof ChatComponentTranslation) {
               ChatComponentTranslation lvt_5_2_ = (ChatComponentTranslation)p_serialize_1_;
               lvt_4_1_.addProperty("translate", lvt_5_2_.func_150268_i());
               if (lvt_5_2_.func_150271_j() != null && lvt_5_2_.func_150271_j().length > 0) {
                  JsonArray lvt_6_2_ = new JsonArray();
                  Object[] lvt_7_2_ = lvt_5_2_.func_150271_j();
                  int lvt_8_1_ = lvt_7_2_.length;

                  for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
                     Object lvt_10_1_ = lvt_7_2_[lvt_9_1_];
                     if (lvt_10_1_ instanceof IChatComponent) {
                        lvt_6_2_.add(this.serialize((IChatComponent)((IChatComponent)lvt_10_1_), lvt_10_1_.getClass(), p_serialize_3_));
                     } else {
                        lvt_6_2_.add(new JsonPrimitive(String.valueOf(lvt_10_1_)));
                     }
                  }

                  lvt_4_1_.add("with", lvt_6_2_);
               }
            } else if (p_serialize_1_ instanceof ChatComponentScore) {
               ChatComponentScore lvt_5_3_ = (ChatComponentScore)p_serialize_1_;
               JsonObject lvt_6_3_ = new JsonObject();
               lvt_6_3_.addProperty("name", lvt_5_3_.func_179995_g());
               lvt_6_3_.addProperty("objective", lvt_5_3_.func_179994_h());
               lvt_6_3_.addProperty("value", lvt_5_3_.func_150261_e());
               lvt_4_1_.add("score", lvt_6_3_);
            } else {
               if (!(p_serialize_1_ instanceof ChatComponentSelector)) {
                  throw new IllegalArgumentException("Don't know how to serialize " + p_serialize_1_ + " as a Component");
               }

               ChatComponentSelector lvt_5_4_ = (ChatComponentSelector)p_serialize_1_;
               lvt_4_1_.addProperty("selector", lvt_5_4_.func_179992_g());
            }

            return lvt_4_1_;
         }
      }

      public static String func_150696_a(IChatComponent p_150696_0_) {
         return field_150700_a.toJson((Object)p_150696_0_);
      }

      public static IChatComponent func_150699_a(String p_150699_0_) {
         return (IChatComponent)field_150700_a.fromJson(p_150699_0_, IChatComponent.class);
      }

      static {
         GsonBuilder lvt_0_1_ = new GsonBuilder();
         lvt_0_1_.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent.Serializer());
         lvt_0_1_.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
         lvt_0_1_.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
         field_150700_a = lvt_0_1_.create();
      }
   }
}
