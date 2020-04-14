package net.minecraft.client.resources.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.Validate;

public class FontMetadataSectionSerializer extends BaseMetadataSectionSerializer<FontMetadataSection> {
   public FontMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
      float[] lvt_5_1_ = new float[256];
      float[] lvt_6_1_ = new float[256];
      float[] lvt_7_1_ = new float[256];
      float lvt_8_1_ = 1.0F;
      float lvt_9_1_ = 0.0F;
      float lvt_10_1_ = 0.0F;
      if (lvt_4_1_.has("characters")) {
         if (!lvt_4_1_.get("characters").isJsonObject()) {
            throw new JsonParseException("Invalid font->characters: expected object, was " + lvt_4_1_.get("characters"));
         }

         JsonObject lvt_11_1_ = lvt_4_1_.getAsJsonObject("characters");
         if (lvt_11_1_.has("default")) {
            if (!lvt_11_1_.get("default").isJsonObject()) {
               throw new JsonParseException("Invalid font->characters->default: expected object, was " + lvt_11_1_.get("default"));
            }

            JsonObject lvt_12_1_ = lvt_11_1_.getAsJsonObject("default");
            lvt_8_1_ = JsonUtils.func_151221_a(lvt_12_1_, "width", lvt_8_1_);
            Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, (double)lvt_8_1_, "Invalid default width");
            lvt_9_1_ = JsonUtils.func_151221_a(lvt_12_1_, "spacing", lvt_9_1_);
            Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, (double)lvt_9_1_, "Invalid default spacing");
            lvt_10_1_ = JsonUtils.func_151221_a(lvt_12_1_, "left", lvt_9_1_);
            Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, (double)lvt_10_1_, "Invalid default left");
         }

         for(int lvt_12_2_ = 0; lvt_12_2_ < 256; ++lvt_12_2_) {
            JsonElement lvt_13_1_ = lvt_11_1_.get(Integer.toString(lvt_12_2_));
            float lvt_14_1_ = lvt_8_1_;
            float lvt_15_1_ = lvt_9_1_;
            float lvt_16_1_ = lvt_10_1_;
            if (lvt_13_1_ != null) {
               JsonObject lvt_17_1_ = JsonUtils.func_151210_l(lvt_13_1_, "characters[" + lvt_12_2_ + "]");
               lvt_14_1_ = JsonUtils.func_151221_a(lvt_17_1_, "width", lvt_8_1_);
               Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, (double)lvt_14_1_, "Invalid width");
               lvt_15_1_ = JsonUtils.func_151221_a(lvt_17_1_, "spacing", lvt_9_1_);
               Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, (double)lvt_15_1_, "Invalid spacing");
               lvt_16_1_ = JsonUtils.func_151221_a(lvt_17_1_, "left", lvt_10_1_);
               Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, (double)lvt_16_1_, "Invalid left");
            }

            lvt_5_1_[lvt_12_2_] = lvt_14_1_;
            lvt_6_1_[lvt_12_2_] = lvt_15_1_;
            lvt_7_1_[lvt_12_2_] = lvt_16_1_;
         }
      }

      return new FontMetadataSection(lvt_5_1_, lvt_7_1_, lvt_6_1_);
   }

   public String func_110483_a() {
      return "font";
   }
}
