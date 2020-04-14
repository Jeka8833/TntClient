package net.minecraft.client.resources.data;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;
import net.minecraft.util.JsonUtils;

public class TextureMetadataSectionSerializer extends BaseMetadataSectionSerializer<TextureMetadataSection> {
   public TextureMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
      boolean lvt_5_1_ = JsonUtils.func_151209_a(lvt_4_1_, "blur", false);
      boolean lvt_6_1_ = JsonUtils.func_151209_a(lvt_4_1_, "clamp", false);
      List<Integer> lvt_7_1_ = Lists.newArrayList();
      if (lvt_4_1_.has("mipmaps")) {
         try {
            JsonArray lvt_8_1_ = lvt_4_1_.getAsJsonArray("mipmaps");

            for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_.size(); ++lvt_9_1_) {
               JsonElement lvt_10_1_ = lvt_8_1_.get(lvt_9_1_);
               if (lvt_10_1_.isJsonPrimitive()) {
                  try {
                     lvt_7_1_.add(lvt_10_1_.getAsInt());
                  } catch (NumberFormatException var12) {
                     throw new JsonParseException("Invalid texture->mipmap->" + lvt_9_1_ + ": expected number, was " + lvt_10_1_, var12);
                  }
               } else if (lvt_10_1_.isJsonObject()) {
                  throw new JsonParseException("Invalid texture->mipmap->" + lvt_9_1_ + ": expected number, was " + lvt_10_1_);
               }
            }
         } catch (ClassCastException var13) {
            throw new JsonParseException("Invalid texture->mipmaps: expected array, was " + lvt_4_1_.get("mipmaps"), var13);
         }
      }

      return new TextureMetadataSection(lvt_5_1_, lvt_6_1_, lvt_7_1_);
   }

   public String func_110483_a() {
      return "texture";
   }
}
