package net.minecraft.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;

public class ServerStatusResponse {
   private IChatComponent field_151326_a;
   private ServerStatusResponse.PlayerCountData field_151324_b;
   private ServerStatusResponse.MinecraftProtocolVersionIdentifier field_151325_c;
   private String field_151323_d;

   public IChatComponent func_151317_a() {
      return this.field_151326_a;
   }

   public void func_151315_a(IChatComponent p_151315_1_) {
      this.field_151326_a = p_151315_1_;
   }

   public ServerStatusResponse.PlayerCountData func_151318_b() {
      return this.field_151324_b;
   }

   public void func_151319_a(ServerStatusResponse.PlayerCountData p_151319_1_) {
      this.field_151324_b = p_151319_1_;
   }

   public ServerStatusResponse.MinecraftProtocolVersionIdentifier func_151322_c() {
      return this.field_151325_c;
   }

   public void func_151321_a(ServerStatusResponse.MinecraftProtocolVersionIdentifier p_151321_1_) {
      this.field_151325_c = p_151321_1_;
   }

   public void func_151320_a(String p_151320_1_) {
      this.field_151323_d = p_151320_1_;
   }

   public String func_151316_d() {
      return this.field_151323_d;
   }

   public static class Serializer implements JsonDeserializer<ServerStatusResponse>, JsonSerializer<ServerStatusResponse> {
      public ServerStatusResponse deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject lvt_4_1_ = JsonUtils.func_151210_l(p_deserialize_1_, "status");
         ServerStatusResponse lvt_5_1_ = new ServerStatusResponse();
         if (lvt_4_1_.has("description")) {
            lvt_5_1_.func_151315_a((IChatComponent)p_deserialize_3_.deserialize(lvt_4_1_.get("description"), IChatComponent.class));
         }

         if (lvt_4_1_.has("players")) {
            lvt_5_1_.func_151319_a((ServerStatusResponse.PlayerCountData)p_deserialize_3_.deserialize(lvt_4_1_.get("players"), ServerStatusResponse.PlayerCountData.class));
         }

         if (lvt_4_1_.has("version")) {
            lvt_5_1_.func_151321_a((ServerStatusResponse.MinecraftProtocolVersionIdentifier)p_deserialize_3_.deserialize(lvt_4_1_.get("version"), ServerStatusResponse.MinecraftProtocolVersionIdentifier.class));
         }

         if (lvt_4_1_.has("favicon")) {
            lvt_5_1_.func_151320_a(JsonUtils.func_151200_h(lvt_4_1_, "favicon"));
         }

         return lvt_5_1_;
      }

      public JsonElement serialize(ServerStatusResponse p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
         JsonObject lvt_4_1_ = new JsonObject();
         if (p_serialize_1_.func_151317_a() != null) {
            lvt_4_1_.add("description", p_serialize_3_.serialize(p_serialize_1_.func_151317_a()));
         }

         if (p_serialize_1_.func_151318_b() != null) {
            lvt_4_1_.add("players", p_serialize_3_.serialize(p_serialize_1_.func_151318_b()));
         }

         if (p_serialize_1_.func_151322_c() != null) {
            lvt_4_1_.add("version", p_serialize_3_.serialize(p_serialize_1_.func_151322_c()));
         }

         if (p_serialize_1_.func_151316_d() != null) {
            lvt_4_1_.addProperty("favicon", p_serialize_1_.func_151316_d());
         }

         return lvt_4_1_;
      }
   }

   public static class MinecraftProtocolVersionIdentifier {
      private final String field_151306_a;
      private final int field_151305_b;

      public MinecraftProtocolVersionIdentifier(String p_i45275_1_, int p_i45275_2_) {
         this.field_151306_a = p_i45275_1_;
         this.field_151305_b = p_i45275_2_;
      }

      public String func_151303_a() {
         return this.field_151306_a;
      }

      public int func_151304_b() {
         return this.field_151305_b;
      }

      public static class Serializer implements JsonDeserializer<ServerStatusResponse.MinecraftProtocolVersionIdentifier>, JsonSerializer<ServerStatusResponse.MinecraftProtocolVersionIdentifier> {
         public ServerStatusResponse.MinecraftProtocolVersionIdentifier deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject lvt_4_1_ = JsonUtils.func_151210_l(p_deserialize_1_, "version");
            return new ServerStatusResponse.MinecraftProtocolVersionIdentifier(JsonUtils.func_151200_h(lvt_4_1_, "name"), JsonUtils.func_151203_m(lvt_4_1_, "protocol"));
         }

         public JsonElement serialize(ServerStatusResponse.MinecraftProtocolVersionIdentifier p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject lvt_4_1_ = new JsonObject();
            lvt_4_1_.addProperty("name", p_serialize_1_.func_151303_a());
            lvt_4_1_.addProperty("protocol", (Number)p_serialize_1_.func_151304_b());
            return lvt_4_1_;
         }
      }
   }

   public static class PlayerCountData {
      private final int field_151336_a;
      private final int field_151334_b;
      private GameProfile[] field_151335_c;

      public PlayerCountData(int p_i45274_1_, int p_i45274_2_) {
         this.field_151336_a = p_i45274_1_;
         this.field_151334_b = p_i45274_2_;
      }

      public int func_151332_a() {
         return this.field_151336_a;
      }

      public int func_151333_b() {
         return this.field_151334_b;
      }

      public GameProfile[] func_151331_c() {
         return this.field_151335_c;
      }

      public void func_151330_a(GameProfile[] p_151330_1_) {
         this.field_151335_c = p_151330_1_;
      }

      public static class Serializer implements JsonDeserializer<ServerStatusResponse.PlayerCountData>, JsonSerializer<ServerStatusResponse.PlayerCountData> {
         public ServerStatusResponse.PlayerCountData deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject lvt_4_1_ = JsonUtils.func_151210_l(p_deserialize_1_, "players");
            ServerStatusResponse.PlayerCountData lvt_5_1_ = new ServerStatusResponse.PlayerCountData(JsonUtils.func_151203_m(lvt_4_1_, "max"), JsonUtils.func_151203_m(lvt_4_1_, "online"));
            if (JsonUtils.func_151202_d(lvt_4_1_, "sample")) {
               JsonArray lvt_6_1_ = JsonUtils.func_151214_t(lvt_4_1_, "sample");
               if (lvt_6_1_.size() > 0) {
                  GameProfile[] lvt_7_1_ = new GameProfile[lvt_6_1_.size()];

                  for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_7_1_.length; ++lvt_8_1_) {
                     JsonObject lvt_9_1_ = JsonUtils.func_151210_l(lvt_6_1_.get(lvt_8_1_), "player[" + lvt_8_1_ + "]");
                     String lvt_10_1_ = JsonUtils.func_151200_h(lvt_9_1_, "id");
                     lvt_7_1_[lvt_8_1_] = new GameProfile(UUID.fromString(lvt_10_1_), JsonUtils.func_151200_h(lvt_9_1_, "name"));
                  }

                  lvt_5_1_.func_151330_a(lvt_7_1_);
               }
            }

            return lvt_5_1_;
         }

         public JsonElement serialize(ServerStatusResponse.PlayerCountData p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject lvt_4_1_ = new JsonObject();
            lvt_4_1_.addProperty("max", (Number)p_serialize_1_.func_151332_a());
            lvt_4_1_.addProperty("online", (Number)p_serialize_1_.func_151333_b());
            if (p_serialize_1_.func_151331_c() != null && p_serialize_1_.func_151331_c().length > 0) {
               JsonArray lvt_5_1_ = new JsonArray();

               for(int lvt_6_1_ = 0; lvt_6_1_ < p_serialize_1_.func_151331_c().length; ++lvt_6_1_) {
                  JsonObject lvt_7_1_ = new JsonObject();
                  UUID lvt_8_1_ = p_serialize_1_.func_151331_c()[lvt_6_1_].getId();
                  lvt_7_1_.addProperty("id", lvt_8_1_ == null ? "" : lvt_8_1_.toString());
                  lvt_7_1_.addProperty("name", p_serialize_1_.func_151331_c()[lvt_6_1_].getName());
                  lvt_5_1_.add(lvt_7_1_);
               }

               lvt_4_1_.add("sample", lvt_5_1_);
            }

            return lvt_4_1_;
         }
      }
   }
}
