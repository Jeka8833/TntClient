package net.minecraft.client.resources.data;

import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.client.resources.Language;
import net.minecraft.util.JsonUtils;

public class LanguageMetadataSectionSerializer extends BaseMetadataSectionSerializer<LanguageMetadataSection> {
   public LanguageMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
      Set<Language> lvt_5_1_ = Sets.newHashSet();
      Iterator lvt_6_1_ = lvt_4_1_.entrySet().iterator();

      String lvt_8_1_;
      String lvt_10_1_;
      String lvt_11_1_;
      boolean lvt_12_1_;
      do {
         if (!lvt_6_1_.hasNext()) {
            return new LanguageMetadataSection(lvt_5_1_);
         }

         Entry<String, JsonElement> lvt_7_1_ = (Entry)lvt_6_1_.next();
         lvt_8_1_ = (String)lvt_7_1_.getKey();
         JsonObject lvt_9_1_ = JsonUtils.func_151210_l((JsonElement)lvt_7_1_.getValue(), "language");
         lvt_10_1_ = JsonUtils.func_151200_h(lvt_9_1_, "region");
         lvt_11_1_ = JsonUtils.func_151200_h(lvt_9_1_, "name");
         lvt_12_1_ = JsonUtils.func_151209_a(lvt_9_1_, "bidirectional", false);
         if (lvt_10_1_.isEmpty()) {
            throw new JsonParseException("Invalid language->'" + lvt_8_1_ + "'->region: empty value");
         }

         if (lvt_11_1_.isEmpty()) {
            throw new JsonParseException("Invalid language->'" + lvt_8_1_ + "'->name: empty value");
         }
      } while(lvt_5_1_.add(new Language(lvt_8_1_, lvt_10_1_, lvt_11_1_, lvt_12_1_)));

      throw new JsonParseException("Duplicate language->'" + lvt_8_1_ + "' defined");
   }

   public String func_110483_a() {
      return "language";
   }
}
