package net.minecraft.client.audio;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.Validate;

public class SoundListSerializer implements JsonDeserializer<SoundList> {
   public SoundList deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject lvt_4_1_ = JsonUtils.func_151210_l(p_deserialize_1_, "entry");
      SoundList lvt_5_1_ = new SoundList();
      lvt_5_1_.func_148572_a(JsonUtils.func_151209_a(lvt_4_1_, "replace", false));
      SoundCategory lvt_6_1_ = SoundCategory.func_147154_a(JsonUtils.func_151219_a(lvt_4_1_, "category", SoundCategory.MASTER.func_147155_a()));
      lvt_5_1_.func_148571_a(lvt_6_1_);
      Validate.notNull(lvt_6_1_, "Invalid category");
      if (lvt_4_1_.has("sounds")) {
         JsonArray lvt_7_1_ = JsonUtils.func_151214_t(lvt_4_1_, "sounds");

         for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_.size(); ++lvt_8_1_) {
            JsonElement lvt_9_1_ = lvt_7_1_.get(lvt_8_1_);
            SoundList.SoundEntry lvt_10_1_ = new SoundList.SoundEntry();
            if (JsonUtils.func_151211_a(lvt_9_1_)) {
               lvt_10_1_.func_148561_a(JsonUtils.func_151206_a(lvt_9_1_, "sound"));
            } else {
               JsonObject lvt_11_1_ = JsonUtils.func_151210_l(lvt_9_1_, "sound");
               lvt_10_1_.func_148561_a(JsonUtils.func_151200_h(lvt_11_1_, "name"));
               if (lvt_11_1_.has("type")) {
                  SoundList.SoundEntry.Type lvt_12_1_ = SoundList.SoundEntry.Type.func_148580_a(JsonUtils.func_151200_h(lvt_11_1_, "type"));
                  Validate.notNull(lvt_12_1_, "Invalid type");
                  lvt_10_1_.func_148562_a(lvt_12_1_);
               }

               float lvt_12_3_;
               if (lvt_11_1_.has("volume")) {
                  lvt_12_3_ = JsonUtils.func_151217_k(lvt_11_1_, "volume");
                  Validate.isTrue(lvt_12_3_ > 0.0F, "Invalid volume");
                  lvt_10_1_.func_148553_a(lvt_12_3_);
               }

               if (lvt_11_1_.has("pitch")) {
                  lvt_12_3_ = JsonUtils.func_151217_k(lvt_11_1_, "pitch");
                  Validate.isTrue(lvt_12_3_ > 0.0F, "Invalid pitch");
                  lvt_10_1_.func_148559_b(lvt_12_3_);
               }

               if (lvt_11_1_.has("weight")) {
                  int lvt_12_4_ = JsonUtils.func_151203_m(lvt_11_1_, "weight");
                  Validate.isTrue(lvt_12_4_ > 0, "Invalid weight");
                  lvt_10_1_.func_148554_a(lvt_12_4_);
               }

               if (lvt_11_1_.has("stream")) {
                  lvt_10_1_.func_148557_a(JsonUtils.func_151212_i(lvt_11_1_, "stream"));
               }
            }

            lvt_5_1_.func_148570_a().add(lvt_10_1_);
         }
      }

      return lvt_5_1_;
   }
}
