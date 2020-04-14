package net.minecraft.client.resources.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;

public class PackMetadataSectionSerializer extends BaseMetadataSectionSerializer<PackMetadataSection> implements JsonSerializer<PackMetadataSection> {
   public PackMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
      IChatComponent lvt_5_1_ = (IChatComponent)p_deserialize_3_.deserialize(lvt_4_1_.get("description"), IChatComponent.class);
      if (lvt_5_1_ == null) {
         throw new JsonParseException("Invalid/missing description!");
      } else {
         int lvt_6_1_ = JsonUtils.func_151203_m(lvt_4_1_, "pack_format");
         return new PackMetadataSection(lvt_5_1_, lvt_6_1_);
      }
   }

   public JsonElement serialize(PackMetadataSection p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject lvt_4_1_ = new JsonObject();
      lvt_4_1_.addProperty("pack_format", (Number)p_serialize_1_.func_110462_b());
      lvt_4_1_.add("description", p_serialize_3_.serialize(p_serialize_1_.func_152805_a()));
      return lvt_4_1_;
   }

   public String func_110483_a() {
      return "pack";
   }
}
