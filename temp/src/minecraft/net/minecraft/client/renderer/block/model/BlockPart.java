package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.vector.Vector3f;

public class BlockPart {
   public final Vector3f field_178241_a;
   public final Vector3f field_178239_b;
   public final Map<EnumFacing, BlockPartFace> field_178240_c;
   public final BlockPartRotation field_178237_d;
   public final boolean field_178238_e;

   public BlockPart(Vector3f p_i46231_1_, Vector3f p_i46231_2_, Map<EnumFacing, BlockPartFace> p_i46231_3_, BlockPartRotation p_i46231_4_, boolean p_i46231_5_) {
      this.field_178241_a = p_i46231_1_;
      this.field_178239_b = p_i46231_2_;
      this.field_178240_c = p_i46231_3_;
      this.field_178237_d = p_i46231_4_;
      this.field_178238_e = p_i46231_5_;
      this.func_178235_a();
   }

   private void func_178235_a() {
      Iterator lvt_1_1_ = this.field_178240_c.entrySet().iterator();

      while(lvt_1_1_.hasNext()) {
         Entry<EnumFacing, BlockPartFace> lvt_2_1_ = (Entry)lvt_1_1_.next();
         float[] lvt_3_1_ = this.func_178236_a((EnumFacing)lvt_2_1_.getKey());
         ((BlockPartFace)lvt_2_1_.getValue()).field_178243_e.func_178349_a(lvt_3_1_);
      }

   }

   private float[] func_178236_a(EnumFacing p_178236_1_) {
      float[] lvt_2_4_;
      switch(p_178236_1_) {
      case DOWN:
      case UP:
         lvt_2_4_ = new float[]{this.field_178241_a.x, this.field_178241_a.z, this.field_178239_b.x, this.field_178239_b.z};
         break;
      case NORTH:
      case SOUTH:
         lvt_2_4_ = new float[]{this.field_178241_a.x, 16.0F - this.field_178239_b.y, this.field_178239_b.x, 16.0F - this.field_178241_a.y};
         break;
      case WEST:
      case EAST:
         lvt_2_4_ = new float[]{this.field_178241_a.z, 16.0F - this.field_178239_b.y, this.field_178239_b.z, 16.0F - this.field_178241_a.y};
         break;
      default:
         throw new NullPointerException();
      }

      return lvt_2_4_;
   }

   static class Deserializer implements JsonDeserializer<BlockPart> {
      public BlockPart deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
         Vector3f lvt_5_1_ = this.func_178249_e(lvt_4_1_);
         Vector3f lvt_6_1_ = this.func_178247_d(lvt_4_1_);
         BlockPartRotation lvt_7_1_ = this.func_178256_a(lvt_4_1_);
         Map<EnumFacing, BlockPartFace> lvt_8_1_ = this.func_178250_a(p_deserialize_3_, lvt_4_1_);
         if (lvt_4_1_.has("shade") && !JsonUtils.func_180199_c(lvt_4_1_, "shade")) {
            throw new JsonParseException("Expected shade to be a Boolean");
         } else {
            boolean lvt_9_1_ = JsonUtils.func_151209_a(lvt_4_1_, "shade", true);
            return new BlockPart(lvt_5_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, lvt_9_1_);
         }
      }

      private BlockPartRotation func_178256_a(JsonObject p_178256_1_) {
         BlockPartRotation lvt_2_1_ = null;
         if (p_178256_1_.has("rotation")) {
            JsonObject lvt_3_1_ = JsonUtils.func_152754_s(p_178256_1_, "rotation");
            Vector3f lvt_4_1_ = this.func_178251_a(lvt_3_1_, "origin");
            lvt_4_1_.scale(0.0625F);
            EnumFacing.Axis lvt_5_1_ = this.func_178252_c(lvt_3_1_);
            float lvt_6_1_ = this.func_178255_b(lvt_3_1_);
            boolean lvt_7_1_ = JsonUtils.func_151209_a(lvt_3_1_, "rescale", false);
            lvt_2_1_ = new BlockPartRotation(lvt_4_1_, lvt_5_1_, lvt_6_1_, lvt_7_1_);
         }

         return lvt_2_1_;
      }

      private float func_178255_b(JsonObject p_178255_1_) {
         float lvt_2_1_ = JsonUtils.func_151217_k(p_178255_1_, "angle");
         if (lvt_2_1_ != 0.0F && MathHelper.func_76135_e(lvt_2_1_) != 22.5F && MathHelper.func_76135_e(lvt_2_1_) != 45.0F) {
            throw new JsonParseException("Invalid rotation " + lvt_2_1_ + " found, only -45/-22.5/0/22.5/45 allowed");
         } else {
            return lvt_2_1_;
         }
      }

      private EnumFacing.Axis func_178252_c(JsonObject p_178252_1_) {
         String lvt_2_1_ = JsonUtils.func_151200_h(p_178252_1_, "axis");
         EnumFacing.Axis lvt_3_1_ = EnumFacing.Axis.func_176717_a(lvt_2_1_.toLowerCase());
         if (lvt_3_1_ == null) {
            throw new JsonParseException("Invalid rotation axis: " + lvt_2_1_);
         } else {
            return lvt_3_1_;
         }
      }

      private Map<EnumFacing, BlockPartFace> func_178250_a(JsonDeserializationContext p_178250_1_, JsonObject p_178250_2_) {
         Map<EnumFacing, BlockPartFace> lvt_3_1_ = this.func_178253_b(p_178250_1_, p_178250_2_);
         if (lvt_3_1_.isEmpty()) {
            throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
         } else {
            return lvt_3_1_;
         }
      }

      private Map<EnumFacing, BlockPartFace> func_178253_b(JsonDeserializationContext p_178253_1_, JsonObject p_178253_2_) {
         Map<EnumFacing, BlockPartFace> lvt_3_1_ = Maps.newEnumMap(EnumFacing.class);
         JsonObject lvt_4_1_ = JsonUtils.func_152754_s(p_178253_2_, "faces");
         Iterator lvt_5_1_ = lvt_4_1_.entrySet().iterator();

         while(lvt_5_1_.hasNext()) {
            Entry<String, JsonElement> lvt_6_1_ = (Entry)lvt_5_1_.next();
            EnumFacing lvt_7_1_ = this.func_178248_a((String)lvt_6_1_.getKey());
            lvt_3_1_.put(lvt_7_1_, (BlockPartFace)p_178253_1_.deserialize((JsonElement)lvt_6_1_.getValue(), BlockPartFace.class));
         }

         return lvt_3_1_;
      }

      private EnumFacing func_178248_a(String p_178248_1_) {
         EnumFacing lvt_2_1_ = EnumFacing.func_176739_a(p_178248_1_);
         if (lvt_2_1_ == null) {
            throw new JsonParseException("Unknown facing: " + p_178248_1_);
         } else {
            return lvt_2_1_;
         }
      }

      private Vector3f func_178247_d(JsonObject p_178247_1_) {
         Vector3f lvt_2_1_ = this.func_178251_a(p_178247_1_, "to");
         if (lvt_2_1_.x >= -16.0F && lvt_2_1_.y >= -16.0F && lvt_2_1_.z >= -16.0F && lvt_2_1_.x <= 32.0F && lvt_2_1_.y <= 32.0F && lvt_2_1_.z <= 32.0F) {
            return lvt_2_1_;
         } else {
            throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + lvt_2_1_);
         }
      }

      private Vector3f func_178249_e(JsonObject p_178249_1_) {
         Vector3f lvt_2_1_ = this.func_178251_a(p_178249_1_, "from");
         if (lvt_2_1_.x >= -16.0F && lvt_2_1_.y >= -16.0F && lvt_2_1_.z >= -16.0F && lvt_2_1_.x <= 32.0F && lvt_2_1_.y <= 32.0F && lvt_2_1_.z <= 32.0F) {
            return lvt_2_1_;
         } else {
            throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + lvt_2_1_);
         }
      }

      private Vector3f func_178251_a(JsonObject p_178251_1_, String p_178251_2_) {
         JsonArray lvt_3_1_ = JsonUtils.func_151214_t(p_178251_1_, p_178251_2_);
         if (lvt_3_1_.size() != 3) {
            throw new JsonParseException("Expected 3 " + p_178251_2_ + " values, found: " + lvt_3_1_.size());
         } else {
            float[] lvt_4_1_ = new float[3];

            for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.length; ++lvt_5_1_) {
               lvt_4_1_[lvt_5_1_] = JsonUtils.func_151220_d(lvt_3_1_.get(lvt_5_1_), p_178251_2_ + "[" + lvt_5_1_ + "]");
            }

            return new Vector3f(lvt_4_1_[0], lvt_4_1_[1], lvt_4_1_[2]);
         }
      }
   }
}
