package net.minecraft.util;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.Map;
import java.util.UUID;

public class Session {
   private final String field_74286_b;
   private final String field_148257_b;
   private final String field_148258_c;
   private final Session.Type field_152429_d;

   public Session(String p_i1098_1_, String p_i1098_2_, String p_i1098_3_, String p_i1098_4_) {
      this.field_74286_b = p_i1098_1_;
      this.field_148257_b = p_i1098_2_;
      this.field_148258_c = p_i1098_3_;
      this.field_152429_d = Session.Type.func_152421_a(p_i1098_4_);
   }

   public String func_111286_b() {
      return "token:" + this.field_148258_c + ":" + this.field_148257_b;
   }

   public String func_148255_b() {
      return this.field_148257_b;
   }

   public String func_111285_a() {
      return this.field_74286_b;
   }

   public String func_148254_d() {
      return this.field_148258_c;
   }

   public GameProfile func_148256_e() {
      try {
         UUID lvt_1_1_ = UUIDTypeAdapter.fromString(this.func_148255_b());
         return new GameProfile(lvt_1_1_, this.func_111285_a());
      } catch (IllegalArgumentException var2) {
         return new GameProfile((UUID)null, this.func_111285_a());
      }
   }

   public Session.Type func_152428_f() {
      return this.field_152429_d;
   }

   public static enum Type {
      LEGACY("legacy"),
      MOJANG("mojang");

      private static final Map<String, Session.Type> field_152425_c = Maps.newHashMap();
      private final String field_152426_d;

      private Type(String p_i1096_3_) {
         this.field_152426_d = p_i1096_3_;
      }

      public static Session.Type func_152421_a(String p_152421_0_) {
         return (Session.Type)field_152425_c.get(p_152421_0_.toLowerCase());
      }

      static {
         Session.Type[] lvt_0_1_ = values();
         int lvt_1_1_ = lvt_0_1_.length;

         for(int lvt_2_1_ = 0; lvt_2_1_ < lvt_1_1_; ++lvt_2_1_) {
            Session.Type lvt_3_1_ = lvt_0_1_[lvt_2_1_];
            field_152425_c.put(lvt_3_1_.field_152426_d, lvt_3_1_);
         }

      }
   }
}
