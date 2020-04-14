package net.minecraft.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;

public class ChatStyle {
   private ChatStyle field_150249_a;
   private EnumChatFormatting field_150247_b;
   private Boolean field_150248_c;
   private Boolean field_150245_d;
   private Boolean field_150246_e;
   private Boolean field_150243_f;
   private Boolean field_150244_g;
   private ClickEvent field_150251_h;
   private HoverEvent field_150252_i;
   private String field_179990_j;
   private static final ChatStyle field_150250_j = new ChatStyle() {
      public EnumChatFormatting func_150215_a() {
         return null;
      }

      public boolean func_150223_b() {
         return false;
      }

      public boolean func_150242_c() {
         return false;
      }

      public boolean func_150236_d() {
         return false;
      }

      public boolean func_150234_e() {
         return false;
      }

      public boolean func_150233_f() {
         return false;
      }

      public ClickEvent func_150235_h() {
         return null;
      }

      public HoverEvent func_150210_i() {
         return null;
      }

      public String func_179986_j() {
         return null;
      }

      public ChatStyle func_150238_a(EnumChatFormatting p_150238_1_) {
         throw new UnsupportedOperationException();
      }

      public ChatStyle func_150227_a(Boolean p_150227_1_) {
         throw new UnsupportedOperationException();
      }

      public ChatStyle func_150217_b(Boolean p_150217_1_) {
         throw new UnsupportedOperationException();
      }

      public ChatStyle func_150225_c(Boolean p_150225_1_) {
         throw new UnsupportedOperationException();
      }

      public ChatStyle func_150228_d(Boolean p_150228_1_) {
         throw new UnsupportedOperationException();
      }

      public ChatStyle func_150237_e(Boolean p_150237_1_) {
         throw new UnsupportedOperationException();
      }

      public ChatStyle func_150241_a(ClickEvent p_150241_1_) {
         throw new UnsupportedOperationException();
      }

      public ChatStyle func_150209_a(HoverEvent p_150209_1_) {
         throw new UnsupportedOperationException();
      }

      public ChatStyle func_150221_a(ChatStyle p_150221_1_) {
         throw new UnsupportedOperationException();
      }

      public String toString() {
         return "Style.ROOT";
      }

      public ChatStyle func_150232_l() {
         return this;
      }

      public ChatStyle func_150206_m() {
         return this;
      }

      public String func_150218_j() {
         return "";
      }
   };

   public EnumChatFormatting func_150215_a() {
      return this.field_150247_b == null ? this.func_150224_n().func_150215_a() : this.field_150247_b;
   }

   public boolean func_150223_b() {
      return this.field_150248_c == null ? this.func_150224_n().func_150223_b() : this.field_150248_c;
   }

   public boolean func_150242_c() {
      return this.field_150245_d == null ? this.func_150224_n().func_150242_c() : this.field_150245_d;
   }

   public boolean func_150236_d() {
      return this.field_150243_f == null ? this.func_150224_n().func_150236_d() : this.field_150243_f;
   }

   public boolean func_150234_e() {
      return this.field_150246_e == null ? this.func_150224_n().func_150234_e() : this.field_150246_e;
   }

   public boolean func_150233_f() {
      return this.field_150244_g == null ? this.func_150224_n().func_150233_f() : this.field_150244_g;
   }

   public boolean func_150229_g() {
      return this.field_150248_c == null && this.field_150245_d == null && this.field_150243_f == null && this.field_150246_e == null && this.field_150244_g == null && this.field_150247_b == null && this.field_150251_h == null && this.field_150252_i == null;
   }

   public ClickEvent func_150235_h() {
      return this.field_150251_h == null ? this.func_150224_n().func_150235_h() : this.field_150251_h;
   }

   public HoverEvent func_150210_i() {
      return this.field_150252_i == null ? this.func_150224_n().func_150210_i() : this.field_150252_i;
   }

   public String func_179986_j() {
      return this.field_179990_j == null ? this.func_150224_n().func_179986_j() : this.field_179990_j;
   }

   public ChatStyle func_150238_a(EnumChatFormatting p_150238_1_) {
      this.field_150247_b = p_150238_1_;
      return this;
   }

   public ChatStyle func_150227_a(Boolean p_150227_1_) {
      this.field_150248_c = p_150227_1_;
      return this;
   }

   public ChatStyle func_150217_b(Boolean p_150217_1_) {
      this.field_150245_d = p_150217_1_;
      return this;
   }

   public ChatStyle func_150225_c(Boolean p_150225_1_) {
      this.field_150243_f = p_150225_1_;
      return this;
   }

   public ChatStyle func_150228_d(Boolean p_150228_1_) {
      this.field_150246_e = p_150228_1_;
      return this;
   }

   public ChatStyle func_150237_e(Boolean p_150237_1_) {
      this.field_150244_g = p_150237_1_;
      return this;
   }

   public ChatStyle func_150241_a(ClickEvent p_150241_1_) {
      this.field_150251_h = p_150241_1_;
      return this;
   }

   public ChatStyle func_150209_a(HoverEvent p_150209_1_) {
      this.field_150252_i = p_150209_1_;
      return this;
   }

   public ChatStyle func_179989_a(String p_179989_1_) {
      this.field_179990_j = p_179989_1_;
      return this;
   }

   public ChatStyle func_150221_a(ChatStyle p_150221_1_) {
      this.field_150249_a = p_150221_1_;
      return this;
   }

   public String func_150218_j() {
      if (this.func_150229_g()) {
         return this.field_150249_a != null ? this.field_150249_a.func_150218_j() : "";
      } else {
         StringBuilder lvt_1_1_ = new StringBuilder();
         if (this.func_150215_a() != null) {
            lvt_1_1_.append(this.func_150215_a());
         }

         if (this.func_150223_b()) {
            lvt_1_1_.append(EnumChatFormatting.BOLD);
         }

         if (this.func_150242_c()) {
            lvt_1_1_.append(EnumChatFormatting.ITALIC);
         }

         if (this.func_150234_e()) {
            lvt_1_1_.append(EnumChatFormatting.UNDERLINE);
         }

         if (this.func_150233_f()) {
            lvt_1_1_.append(EnumChatFormatting.OBFUSCATED);
         }

         if (this.func_150236_d()) {
            lvt_1_1_.append(EnumChatFormatting.STRIKETHROUGH);
         }

         return lvt_1_1_.toString();
      }
   }

   private ChatStyle func_150224_n() {
      return this.field_150249_a == null ? field_150250_j : this.field_150249_a;
   }

   public String toString() {
      return "Style{hasParent=" + (this.field_150249_a != null) + ", color=" + this.field_150247_b + ", bold=" + this.field_150248_c + ", italic=" + this.field_150245_d + ", underlined=" + this.field_150246_e + ", obfuscated=" + this.field_150244_g + ", clickEvent=" + this.func_150235_h() + ", hoverEvent=" + this.func_150210_i() + ", insertion=" + this.func_179986_j() + '}';
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof ChatStyle)) {
         return false;
      } else {
         boolean var10000;
         label77: {
            ChatStyle lvt_2_1_ = (ChatStyle)p_equals_1_;
            if (this.func_150223_b() == lvt_2_1_.func_150223_b() && this.func_150215_a() == lvt_2_1_.func_150215_a() && this.func_150242_c() == lvt_2_1_.func_150242_c() && this.func_150233_f() == lvt_2_1_.func_150233_f() && this.func_150236_d() == lvt_2_1_.func_150236_d() && this.func_150234_e() == lvt_2_1_.func_150234_e()) {
               label71: {
                  if (this.func_150235_h() != null) {
                     if (!this.func_150235_h().equals(lvt_2_1_.func_150235_h())) {
                        break label71;
                     }
                  } else if (lvt_2_1_.func_150235_h() != null) {
                     break label71;
                  }

                  if (this.func_150210_i() != null) {
                     if (!this.func_150210_i().equals(lvt_2_1_.func_150210_i())) {
                        break label71;
                     }
                  } else if (lvt_2_1_.func_150210_i() != null) {
                     break label71;
                  }

                  if (this.func_179986_j() != null) {
                     if (this.func_179986_j().equals(lvt_2_1_.func_179986_j())) {
                        break label77;
                     }
                  } else if (lvt_2_1_.func_179986_j() == null) {
                     break label77;
                  }
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public int hashCode() {
      int lvt_1_1_ = this.field_150247_b.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_150248_c.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_150245_d.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_150246_e.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_150243_f.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_150244_g.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_150251_h.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_150252_i.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_179990_j.hashCode();
      return lvt_1_1_;
   }

   public ChatStyle func_150232_l() {
      ChatStyle lvt_1_1_ = new ChatStyle();
      lvt_1_1_.field_150248_c = this.field_150248_c;
      lvt_1_1_.field_150245_d = this.field_150245_d;
      lvt_1_1_.field_150243_f = this.field_150243_f;
      lvt_1_1_.field_150246_e = this.field_150246_e;
      lvt_1_1_.field_150244_g = this.field_150244_g;
      lvt_1_1_.field_150247_b = this.field_150247_b;
      lvt_1_1_.field_150251_h = this.field_150251_h;
      lvt_1_1_.field_150252_i = this.field_150252_i;
      lvt_1_1_.field_150249_a = this.field_150249_a;
      lvt_1_1_.field_179990_j = this.field_179990_j;
      return lvt_1_1_;
   }

   public ChatStyle func_150206_m() {
      ChatStyle lvt_1_1_ = new ChatStyle();
      lvt_1_1_.func_150227_a(this.func_150223_b());
      lvt_1_1_.func_150217_b(this.func_150242_c());
      lvt_1_1_.func_150225_c(this.func_150236_d());
      lvt_1_1_.func_150228_d(this.func_150234_e());
      lvt_1_1_.func_150237_e(this.func_150233_f());
      lvt_1_1_.func_150238_a(this.func_150215_a());
      lvt_1_1_.func_150241_a(this.func_150235_h());
      lvt_1_1_.func_150209_a(this.func_150210_i());
      lvt_1_1_.func_179989_a(this.func_179986_j());
      return lvt_1_1_;
   }

   public static class Serializer implements JsonDeserializer<ChatStyle>, JsonSerializer<ChatStyle> {
      public ChatStyle deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         if (p_deserialize_1_.isJsonObject()) {
            ChatStyle lvt_4_1_ = new ChatStyle();
            JsonObject lvt_5_1_ = p_deserialize_1_.getAsJsonObject();
            if (lvt_5_1_ == null) {
               return null;
            } else {
               if (lvt_5_1_.has("bold")) {
                  lvt_4_1_.field_150248_c = lvt_5_1_.get("bold").getAsBoolean();
               }

               if (lvt_5_1_.has("italic")) {
                  lvt_4_1_.field_150245_d = lvt_5_1_.get("italic").getAsBoolean();
               }

               if (lvt_5_1_.has("underlined")) {
                  lvt_4_1_.field_150246_e = lvt_5_1_.get("underlined").getAsBoolean();
               }

               if (lvt_5_1_.has("strikethrough")) {
                  lvt_4_1_.field_150243_f = lvt_5_1_.get("strikethrough").getAsBoolean();
               }

               if (lvt_5_1_.has("obfuscated")) {
                  lvt_4_1_.field_150244_g = lvt_5_1_.get("obfuscated").getAsBoolean();
               }

               if (lvt_5_1_.has("color")) {
                  lvt_4_1_.field_150247_b = (EnumChatFormatting)p_deserialize_3_.deserialize(lvt_5_1_.get("color"), EnumChatFormatting.class);
               }

               if (lvt_5_1_.has("insertion")) {
                  lvt_4_1_.field_179990_j = lvt_5_1_.get("insertion").getAsString();
               }

               JsonObject lvt_6_2_;
               JsonPrimitive lvt_7_2_;
               if (lvt_5_1_.has("clickEvent")) {
                  lvt_6_2_ = lvt_5_1_.getAsJsonObject("clickEvent");
                  if (lvt_6_2_ != null) {
                     lvt_7_2_ = lvt_6_2_.getAsJsonPrimitive("action");
                     ClickEvent.Action lvt_8_1_ = lvt_7_2_ == null ? null : ClickEvent.Action.func_150672_a(lvt_7_2_.getAsString());
                     JsonPrimitive lvt_9_1_ = lvt_6_2_.getAsJsonPrimitive("value");
                     String lvt_10_1_ = lvt_9_1_ == null ? null : lvt_9_1_.getAsString();
                     if (lvt_8_1_ != null && lvt_10_1_ != null && lvt_8_1_.func_150674_a()) {
                        lvt_4_1_.field_150251_h = new ClickEvent(lvt_8_1_, lvt_10_1_);
                     }
                  }
               }

               if (lvt_5_1_.has("hoverEvent")) {
                  lvt_6_2_ = lvt_5_1_.getAsJsonObject("hoverEvent");
                  if (lvt_6_2_ != null) {
                     lvt_7_2_ = lvt_6_2_.getAsJsonPrimitive("action");
                     HoverEvent.Action lvt_8_2_ = lvt_7_2_ == null ? null : HoverEvent.Action.func_150684_a(lvt_7_2_.getAsString());
                     IChatComponent lvt_9_2_ = (IChatComponent)p_deserialize_3_.deserialize(lvt_6_2_.get("value"), IChatComponent.class);
                     if (lvt_8_2_ != null && lvt_9_2_ != null && lvt_8_2_.func_150686_a()) {
                        lvt_4_1_.field_150252_i = new HoverEvent(lvt_8_2_, lvt_9_2_);
                     }
                  }
               }

               return lvt_4_1_;
            }
         } else {
            return null;
         }
      }

      public JsonElement serialize(ChatStyle p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
         if (p_serialize_1_.func_150229_g()) {
            return null;
         } else {
            JsonObject lvt_4_1_ = new JsonObject();
            if (p_serialize_1_.field_150248_c != null) {
               lvt_4_1_.addProperty("bold", p_serialize_1_.field_150248_c);
            }

            if (p_serialize_1_.field_150245_d != null) {
               lvt_4_1_.addProperty("italic", p_serialize_1_.field_150245_d);
            }

            if (p_serialize_1_.field_150246_e != null) {
               lvt_4_1_.addProperty("underlined", p_serialize_1_.field_150246_e);
            }

            if (p_serialize_1_.field_150243_f != null) {
               lvt_4_1_.addProperty("strikethrough", p_serialize_1_.field_150243_f);
            }

            if (p_serialize_1_.field_150244_g != null) {
               lvt_4_1_.addProperty("obfuscated", p_serialize_1_.field_150244_g);
            }

            if (p_serialize_1_.field_150247_b != null) {
               lvt_4_1_.add("color", p_serialize_3_.serialize(p_serialize_1_.field_150247_b));
            }

            if (p_serialize_1_.field_179990_j != null) {
               lvt_4_1_.add("insertion", p_serialize_3_.serialize(p_serialize_1_.field_179990_j));
            }

            JsonObject lvt_5_2_;
            if (p_serialize_1_.field_150251_h != null) {
               lvt_5_2_ = new JsonObject();
               lvt_5_2_.addProperty("action", p_serialize_1_.field_150251_h.func_150669_a().func_150673_b());
               lvt_5_2_.addProperty("value", p_serialize_1_.field_150251_h.func_150668_b());
               lvt_4_1_.add("clickEvent", lvt_5_2_);
            }

            if (p_serialize_1_.field_150252_i != null) {
               lvt_5_2_ = new JsonObject();
               lvt_5_2_.addProperty("action", p_serialize_1_.field_150252_i.func_150701_a().func_150685_b());
               lvt_5_2_.add("value", p_serialize_3_.serialize(p_serialize_1_.field_150252_i.func_150702_b()));
               lvt_4_1_.add("hoverEvent", lvt_5_2_);
            }

            return lvt_4_1_;
         }
      }
   }
}
