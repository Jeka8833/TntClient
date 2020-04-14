package net.minecraft.server.management;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;

public class PlayerProfileCache {
   public static final SimpleDateFormat field_152659_a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
   private final Map<String, PlayerProfileCache.ProfileEntry> field_152661_c = Maps.newHashMap();
   private final Map<UUID, PlayerProfileCache.ProfileEntry> field_152662_d = Maps.newHashMap();
   private final LinkedList<GameProfile> field_152663_e = Lists.newLinkedList();
   private final MinecraftServer field_152664_f;
   protected final Gson field_152660_b;
   private final File field_152665_g;
   private static final ParameterizedType field_152666_h = new ParameterizedType() {
      public Type[] getActualTypeArguments() {
         return new Type[]{PlayerProfileCache.ProfileEntry.class};
      }

      public Type getRawType() {
         return List.class;
      }

      public Type getOwnerType() {
         return null;
      }
   };

   public PlayerProfileCache(MinecraftServer p_i1171_1_, File p_i1171_2_) {
      this.field_152664_f = p_i1171_1_;
      this.field_152665_g = p_i1171_2_;
      GsonBuilder lvt_3_1_ = new GsonBuilder();
      lvt_3_1_.registerTypeHierarchyAdapter(PlayerProfileCache.ProfileEntry.class, new PlayerProfileCache.Serializer());
      this.field_152660_b = lvt_3_1_.create();
      this.func_152657_b();
   }

   private static GameProfile func_152650_a(MinecraftServer p_152650_0_, String p_152650_1_) {
      final GameProfile[] lvt_2_1_ = new GameProfile[1];
      ProfileLookupCallback lvt_3_1_ = new ProfileLookupCallback() {
         public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
            lvt_2_1_[0] = p_onProfileLookupSucceeded_1_;
         }

         public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
            lvt_2_1_[0] = null;
         }
      };
      p_152650_0_.func_152359_aw().findProfilesByNames(new String[]{p_152650_1_}, Agent.MINECRAFT, lvt_3_1_);
      if (!p_152650_0_.func_71266_T() && lvt_2_1_[0] == null) {
         UUID lvt_4_1_ = EntityPlayer.func_146094_a(new GameProfile((UUID)null, p_152650_1_));
         GameProfile lvt_5_1_ = new GameProfile(lvt_4_1_, p_152650_1_);
         lvt_3_1_.onProfileLookupSucceeded(lvt_5_1_);
      }

      return lvt_2_1_[0];
   }

   public void func_152649_a(GameProfile p_152649_1_) {
      this.func_152651_a(p_152649_1_, (Date)null);
   }

   private void func_152651_a(GameProfile p_152651_1_, Date p_152651_2_) {
      UUID lvt_3_1_ = p_152651_1_.getId();
      if (p_152651_2_ == null) {
         Calendar lvt_4_1_ = Calendar.getInstance();
         lvt_4_1_.setTime(new Date());
         lvt_4_1_.add(2, 1);
         p_152651_2_ = lvt_4_1_.getTime();
      }

      String lvt_4_2_ = p_152651_1_.getName().toLowerCase(Locale.ROOT);
      PlayerProfileCache.ProfileEntry lvt_5_1_ = new PlayerProfileCache.ProfileEntry(p_152651_1_, p_152651_2_);
      if (this.field_152662_d.containsKey(lvt_3_1_)) {
         PlayerProfileCache.ProfileEntry lvt_6_1_ = (PlayerProfileCache.ProfileEntry)this.field_152662_d.get(lvt_3_1_);
         this.field_152661_c.remove(lvt_6_1_.func_152668_a().getName().toLowerCase(Locale.ROOT));
         this.field_152663_e.remove(p_152651_1_);
      }

      this.field_152661_c.put(p_152651_1_.getName().toLowerCase(Locale.ROOT), lvt_5_1_);
      this.field_152662_d.put(lvt_3_1_, lvt_5_1_);
      this.field_152663_e.addFirst(p_152651_1_);
      this.func_152658_c();
   }

   public GameProfile func_152655_a(String p_152655_1_) {
      String lvt_2_1_ = p_152655_1_.toLowerCase(Locale.ROOT);
      PlayerProfileCache.ProfileEntry lvt_3_1_ = (PlayerProfileCache.ProfileEntry)this.field_152661_c.get(lvt_2_1_);
      if (lvt_3_1_ != null && (new Date()).getTime() >= lvt_3_1_.field_152673_c.getTime()) {
         this.field_152662_d.remove(lvt_3_1_.func_152668_a().getId());
         this.field_152661_c.remove(lvt_3_1_.func_152668_a().getName().toLowerCase(Locale.ROOT));
         this.field_152663_e.remove(lvt_3_1_.func_152668_a());
         lvt_3_1_ = null;
      }

      GameProfile lvt_4_2_;
      if (lvt_3_1_ != null) {
         lvt_4_2_ = lvt_3_1_.func_152668_a();
         this.field_152663_e.remove(lvt_4_2_);
         this.field_152663_e.addFirst(lvt_4_2_);
      } else {
         lvt_4_2_ = func_152650_a(this.field_152664_f, lvt_2_1_);
         if (lvt_4_2_ != null) {
            this.func_152649_a(lvt_4_2_);
            lvt_3_1_ = (PlayerProfileCache.ProfileEntry)this.field_152661_c.get(lvt_2_1_);
         }
      }

      this.func_152658_c();
      return lvt_3_1_ == null ? null : lvt_3_1_.func_152668_a();
   }

   public String[] func_152654_a() {
      List<String> lvt_1_1_ = Lists.newArrayList((Iterable)this.field_152661_c.keySet());
      return (String[])lvt_1_1_.toArray(new String[lvt_1_1_.size()]);
   }

   public GameProfile func_152652_a(UUID p_152652_1_) {
      PlayerProfileCache.ProfileEntry lvt_2_1_ = (PlayerProfileCache.ProfileEntry)this.field_152662_d.get(p_152652_1_);
      return lvt_2_1_ == null ? null : lvt_2_1_.func_152668_a();
   }

   private PlayerProfileCache.ProfileEntry func_152653_b(UUID p_152653_1_) {
      PlayerProfileCache.ProfileEntry lvt_2_1_ = (PlayerProfileCache.ProfileEntry)this.field_152662_d.get(p_152653_1_);
      if (lvt_2_1_ != null) {
         GameProfile lvt_3_1_ = lvt_2_1_.func_152668_a();
         this.field_152663_e.remove(lvt_3_1_);
         this.field_152663_e.addFirst(lvt_3_1_);
      }

      return lvt_2_1_;
   }

   public void func_152657_b() {
      BufferedReader lvt_1_1_ = null;

      try {
         lvt_1_1_ = Files.newReader(this.field_152665_g, Charsets.UTF_8);
         List<PlayerProfileCache.ProfileEntry> lvt_2_1_ = (List)this.field_152660_b.fromJson((Reader)lvt_1_1_, (Type)field_152666_h);
         this.field_152661_c.clear();
         this.field_152662_d.clear();
         this.field_152663_e.clear();
         Iterator lvt_3_1_ = Lists.reverse(lvt_2_1_).iterator();

         while(lvt_3_1_.hasNext()) {
            PlayerProfileCache.ProfileEntry lvt_4_1_ = (PlayerProfileCache.ProfileEntry)lvt_3_1_.next();
            if (lvt_4_1_ != null) {
               this.func_152651_a(lvt_4_1_.func_152668_a(), lvt_4_1_.func_152670_b());
            }
         }
      } catch (FileNotFoundException var9) {
      } catch (JsonParseException var10) {
      } finally {
         IOUtils.closeQuietly((Reader)lvt_1_1_);
      }

   }

   public void func_152658_c() {
      String lvt_1_1_ = this.field_152660_b.toJson((Object)this.func_152656_a(1000));
      BufferedWriter lvt_2_1_ = null;

      try {
         lvt_2_1_ = Files.newWriter(this.field_152665_g, Charsets.UTF_8);
         lvt_2_1_.write(lvt_1_1_);
         return;
      } catch (FileNotFoundException var8) {
         return;
      } catch (IOException var9) {
      } finally {
         IOUtils.closeQuietly((Writer)lvt_2_1_);
      }

   }

   private List<PlayerProfileCache.ProfileEntry> func_152656_a(int p_152656_1_) {
      ArrayList<PlayerProfileCache.ProfileEntry> lvt_2_1_ = Lists.newArrayList();
      List<GameProfile> lvt_3_1_ = Lists.newArrayList(Iterators.limit(this.field_152663_e.iterator(), p_152656_1_));
      Iterator lvt_4_1_ = lvt_3_1_.iterator();

      while(lvt_4_1_.hasNext()) {
         GameProfile lvt_5_1_ = (GameProfile)lvt_4_1_.next();
         PlayerProfileCache.ProfileEntry lvt_6_1_ = this.func_152653_b(lvt_5_1_.getId());
         if (lvt_6_1_ != null) {
            lvt_2_1_.add(lvt_6_1_);
         }
      }

      return lvt_2_1_;
   }

   class ProfileEntry {
      private final GameProfile field_152672_b;
      private final Date field_152673_c;

      private ProfileEntry(GameProfile p_i46333_2_, Date p_i46333_3_) {
         this.field_152672_b = p_i46333_2_;
         this.field_152673_c = p_i46333_3_;
      }

      public GameProfile func_152668_a() {
         return this.field_152672_b;
      }

      public Date func_152670_b() {
         return this.field_152673_c;
      }
   }

   class Serializer implements JsonDeserializer<PlayerProfileCache.ProfileEntry>, JsonSerializer<PlayerProfileCache.ProfileEntry> {
      private Serializer() {
      }

      public JsonElement serialize(PlayerProfileCache.ProfileEntry p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
         JsonObject lvt_4_1_ = new JsonObject();
         lvt_4_1_.addProperty("name", p_serialize_1_.func_152668_a().getName());
         UUID lvt_5_1_ = p_serialize_1_.func_152668_a().getId();
         lvt_4_1_.addProperty("uuid", lvt_5_1_ == null ? "" : lvt_5_1_.toString());
         lvt_4_1_.addProperty("expiresOn", PlayerProfileCache.field_152659_a.format(p_serialize_1_.func_152670_b()));
         return lvt_4_1_;
      }

      public PlayerProfileCache.ProfileEntry deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         if (p_deserialize_1_.isJsonObject()) {
            JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
            JsonElement lvt_5_1_ = lvt_4_1_.get("name");
            JsonElement lvt_6_1_ = lvt_4_1_.get("uuid");
            JsonElement lvt_7_1_ = lvt_4_1_.get("expiresOn");
            if (lvt_5_1_ != null && lvt_6_1_ != null) {
               String lvt_8_1_ = lvt_6_1_.getAsString();
               String lvt_9_1_ = lvt_5_1_.getAsString();
               Date lvt_10_1_ = null;
               if (lvt_7_1_ != null) {
                  try {
                     lvt_10_1_ = PlayerProfileCache.field_152659_a.parse(lvt_7_1_.getAsString());
                  } catch (ParseException var14) {
                     lvt_10_1_ = null;
                  }
               }

               if (lvt_9_1_ != null && lvt_8_1_ != null) {
                  UUID lvt_11_3_;
                  try {
                     lvt_11_3_ = UUID.fromString(lvt_8_1_);
                  } catch (Throwable var13) {
                     return null;
                  }

                  PlayerProfileCache.ProfileEntry lvt_12_2_ = PlayerProfileCache.this.new ProfileEntry(new GameProfile(lvt_11_3_, lvt_9_1_), lvt_10_1_);
                  return lvt_12_2_;
               } else {
                  return null;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }
}
