package net.minecraft.client.renderer.block.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;

public class ItemTransformVec3f {
   public static final ItemTransformVec3f field_178366_a = new ItemTransformVec3f(new Vector3f(), new Vector3f(), new Vector3f(1.0F, 1.0F, 1.0F));
   public final Vector3f field_178364_b;
   public final Vector3f field_178365_c;
   public final Vector3f field_178363_d;

   public ItemTransformVec3f(Vector3f p_i46214_1_, Vector3f p_i46214_2_, Vector3f p_i46214_3_) {
      this.field_178364_b = new Vector3f(p_i46214_1_);
      this.field_178365_c = new Vector3f(p_i46214_2_);
      this.field_178363_d = new Vector3f(p_i46214_3_);
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (this.getClass() != p_equals_1_.getClass()) {
         return false;
      } else {
         ItemTransformVec3f lvt_2_1_ = (ItemTransformVec3f)p_equals_1_;
         if (!this.field_178364_b.equals(lvt_2_1_.field_178364_b)) {
            return false;
         } else if (!this.field_178363_d.equals(lvt_2_1_.field_178363_d)) {
            return false;
         } else {
            return this.field_178365_c.equals(lvt_2_1_.field_178365_c);
         }
      }
   }

   public int hashCode() {
      int lvt_1_1_ = this.field_178364_b.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_178365_c.hashCode();
      lvt_1_1_ = 31 * lvt_1_1_ + this.field_178363_d.hashCode();
      return lvt_1_1_;
   }

   static class Deserializer implements JsonDeserializer<ItemTransformVec3f> {
      private static final Vector3f field_178362_a = new Vector3f(0.0F, 0.0F, 0.0F);
      private static final Vector3f field_178360_b = new Vector3f(0.0F, 0.0F, 0.0F);
      private static final Vector3f field_178361_c = new Vector3f(1.0F, 1.0F, 1.0F);

      public ItemTransformVec3f deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
         Vector3f lvt_5_1_ = this.func_178358_a(lvt_4_1_, "rotation", field_178362_a);
         Vector3f lvt_6_1_ = this.func_178358_a(lvt_4_1_, "translation", field_178360_b);
         lvt_6_1_.scale(0.0625F);
         lvt_6_1_.x = MathHelper.func_76131_a(lvt_6_1_.x, -1.5F, 1.5F);
         lvt_6_1_.y = MathHelper.func_76131_a(lvt_6_1_.y, -1.5F, 1.5F);
         lvt_6_1_.z = MathHelper.func_76131_a(lvt_6_1_.z, -1.5F, 1.5F);
         Vector3f lvt_7_1_ = this.func_178358_a(lvt_4_1_, "scale", field_178361_c);
         lvt_7_1_.x = MathHelper.func_76131_a(lvt_7_1_.x, -4.0F, 4.0F);
         lvt_7_1_.y = MathHelper.func_76131_a(lvt_7_1_.y, -4.0F, 4.0F);
         lvt_7_1_.z = MathHelper.func_76131_a(lvt_7_1_.z, -4.0F, 4.0F);
         return new ItemTransformVec3f(lvt_5_1_, lvt_6_1_, lvt_7_1_);
      }

      private Vector3f func_178358_a(JsonObject p_178358_1_, String p_178358_2_, Vector3f p_178358_3_) {
         if (!p_178358_1_.has(p_178358_2_)) {
            return p_178358_3_;
         } else {
            JsonArray lvt_4_1_ = JsonUtils.func_151214_t(p_178358_1_, p_178358_2_);
            if (lvt_4_1_.size() != 3) {
               throw new JsonParseException("Expected 3 " + p_178358_2_ + " values, found: " + lvt_4_1_.size());
            } else {
               float[] lvt_5_1_ = new float[3];

               for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_.length; ++lvt_6_1_) {
                  lvt_5_1_[lvt_6_1_] = JsonUtils.func_151220_d(lvt_4_1_.get(lvt_6_1_), p_178358_2_ + "[" + lvt_6_1_ + "]");
               }

               return new Vector3f(lvt_5_1_[0], lvt_5_1_[1], lvt_5_1_[2]);
            }
         }
      }
   }
}
