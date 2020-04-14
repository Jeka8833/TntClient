package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelBlock {
   private static final Logger field_178313_f = LogManager.getLogger();
   static final Gson field_178319_a = (new GsonBuilder()).registerTypeAdapter(ModelBlock.class, new ModelBlock.Deserializer()).registerTypeAdapter(BlockPart.class, new BlockPart.Deserializer()).registerTypeAdapter(BlockPartFace.class, new BlockPartFace.Deserializer()).registerTypeAdapter(BlockFaceUV.class, new BlockFaceUV.Deserializer()).registerTypeAdapter(ItemTransformVec3f.class, new ItemTransformVec3f.Deserializer()).registerTypeAdapter(ItemCameraTransforms.class, new ItemCameraTransforms.Deserializer()).create();
   private final List<BlockPart> field_178314_g;
   private final boolean field_178321_h;
   private final boolean field_178322_i;
   private ItemCameraTransforms field_178320_j;
   public String field_178317_b;
   protected final Map<String, String> field_178318_c;
   protected ModelBlock field_178315_d;
   protected ResourceLocation field_178316_e;

   public static ModelBlock func_178307_a(Reader p_178307_0_) {
      return (ModelBlock)field_178319_a.fromJson(p_178307_0_, ModelBlock.class);
   }

   public static ModelBlock func_178294_a(String p_178294_0_) {
      return func_178307_a(new StringReader(p_178294_0_));
   }

   protected ModelBlock(List<BlockPart> p_i46225_1_, Map<String, String> p_i46225_2_, boolean p_i46225_3_, boolean p_i46225_4_, ItemCameraTransforms p_i46225_5_) {
      this((ResourceLocation)null, p_i46225_1_, p_i46225_2_, p_i46225_3_, p_i46225_4_, p_i46225_5_);
   }

   protected ModelBlock(ResourceLocation p_i46226_1_, Map<String, String> p_i46226_2_, boolean p_i46226_3_, boolean p_i46226_4_, ItemCameraTransforms p_i46226_5_) {
      this(p_i46226_1_, Collections.emptyList(), p_i46226_2_, p_i46226_3_, p_i46226_4_, p_i46226_5_);
   }

   private ModelBlock(ResourceLocation p_i46227_1_, List<BlockPart> p_i46227_2_, Map<String, String> p_i46227_3_, boolean p_i46227_4_, boolean p_i46227_5_, ItemCameraTransforms p_i46227_6_) {
      this.field_178317_b = "";
      this.field_178314_g = p_i46227_2_;
      this.field_178322_i = p_i46227_4_;
      this.field_178321_h = p_i46227_5_;
      this.field_178318_c = p_i46227_3_;
      this.field_178316_e = p_i46227_1_;
      this.field_178320_j = p_i46227_6_;
   }

   public List<BlockPart> func_178298_a() {
      return this.func_178295_k() ? this.field_178315_d.func_178298_a() : this.field_178314_g;
   }

   private boolean func_178295_k() {
      return this.field_178315_d != null;
   }

   public boolean func_178309_b() {
      return this.func_178295_k() ? this.field_178315_d.func_178309_b() : this.field_178322_i;
   }

   public boolean func_178311_c() {
      return this.field_178321_h;
   }

   public boolean func_178303_d() {
      return this.field_178316_e == null || this.field_178315_d != null && this.field_178315_d.func_178303_d();
   }

   public void func_178299_a(Map<ResourceLocation, ModelBlock> p_178299_1_) {
      if (this.field_178316_e != null) {
         this.field_178315_d = (ModelBlock)p_178299_1_.get(this.field_178316_e);
      }

   }

   public boolean func_178300_b(String p_178300_1_) {
      return !"missingno".equals(this.func_178308_c(p_178300_1_));
   }

   public String func_178308_c(String p_178308_1_) {
      if (!this.func_178304_d(p_178308_1_)) {
         p_178308_1_ = '#' + p_178308_1_;
      }

      return this.func_178302_a(p_178308_1_, new ModelBlock.Bookkeep(this));
   }

   private String func_178302_a(String p_178302_1_, ModelBlock.Bookkeep p_178302_2_) {
      if (this.func_178304_d(p_178302_1_)) {
         if (this == p_178302_2_.field_178323_b) {
            field_178313_f.warn("Unable to resolve texture due to upward reference: " + p_178302_1_ + " in " + this.field_178317_b);
            return "missingno";
         } else {
            String lvt_3_1_ = (String)this.field_178318_c.get(p_178302_1_.substring(1));
            if (lvt_3_1_ == null && this.func_178295_k()) {
               lvt_3_1_ = this.field_178315_d.func_178302_a(p_178302_1_, p_178302_2_);
            }

            p_178302_2_.field_178323_b = this;
            if (lvt_3_1_ != null && this.func_178304_d(lvt_3_1_)) {
               lvt_3_1_ = p_178302_2_.field_178324_a.func_178302_a(lvt_3_1_, p_178302_2_);
            }

            return lvt_3_1_ != null && !this.func_178304_d(lvt_3_1_) ? lvt_3_1_ : "missingno";
         }
      } else {
         return p_178302_1_;
      }
   }

   private boolean func_178304_d(String p_178304_1_) {
      return p_178304_1_.charAt(0) == '#';
   }

   public ResourceLocation func_178305_e() {
      return this.field_178316_e;
   }

   public ModelBlock func_178310_f() {
      return this.func_178295_k() ? this.field_178315_d.func_178310_f() : this;
   }

   public ItemCameraTransforms func_181682_g() {
      ItemTransformVec3f lvt_1_1_ = this.func_181681_a(ItemCameraTransforms.TransformType.THIRD_PERSON);
      ItemTransformVec3f lvt_2_1_ = this.func_181681_a(ItemCameraTransforms.TransformType.FIRST_PERSON);
      ItemTransformVec3f lvt_3_1_ = this.func_181681_a(ItemCameraTransforms.TransformType.HEAD);
      ItemTransformVec3f lvt_4_1_ = this.func_181681_a(ItemCameraTransforms.TransformType.GUI);
      ItemTransformVec3f lvt_5_1_ = this.func_181681_a(ItemCameraTransforms.TransformType.GROUND);
      ItemTransformVec3f lvt_6_1_ = this.func_181681_a(ItemCameraTransforms.TransformType.FIXED);
      return new ItemCameraTransforms(lvt_1_1_, lvt_2_1_, lvt_3_1_, lvt_4_1_, lvt_5_1_, lvt_6_1_);
   }

   private ItemTransformVec3f func_181681_a(ItemCameraTransforms.TransformType p_181681_1_) {
      return this.field_178315_d != null && !this.field_178320_j.func_181687_c(p_181681_1_) ? this.field_178315_d.func_181681_a(p_181681_1_) : this.field_178320_j.func_181688_b(p_181681_1_);
   }

   public static void func_178312_b(Map<ResourceLocation, ModelBlock> p_178312_0_) {
      Iterator lvt_1_1_ = p_178312_0_.values().iterator();

      while(lvt_1_1_.hasNext()) {
         ModelBlock lvt_2_1_ = (ModelBlock)lvt_1_1_.next();

         try {
            ModelBlock lvt_3_1_ = lvt_2_1_.field_178315_d;

            for(ModelBlock lvt_4_1_ = lvt_3_1_.field_178315_d; lvt_3_1_ != lvt_4_1_; lvt_4_1_ = lvt_4_1_.field_178315_d.field_178315_d) {
               lvt_3_1_ = lvt_3_1_.field_178315_d;
            }

            throw new ModelBlock.LoopException();
         } catch (NullPointerException var5) {
         }
      }

   }

   public static class LoopException extends RuntimeException {
   }

   public static class Deserializer implements JsonDeserializer<ModelBlock> {
      public ModelBlock deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
         List<BlockPart> lvt_5_1_ = this.func_178325_a(p_deserialize_3_, lvt_4_1_);
         String lvt_6_1_ = this.func_178326_c(lvt_4_1_);
         boolean lvt_7_1_ = StringUtils.isEmpty(lvt_6_1_);
         boolean lvt_8_1_ = lvt_5_1_.isEmpty();
         if (lvt_8_1_ && lvt_7_1_) {
            throw new JsonParseException("BlockModel requires either elements or parent, found neither");
         } else if (!lvt_7_1_ && !lvt_8_1_) {
            throw new JsonParseException("BlockModel requires either elements or parent, found both");
         } else {
            Map<String, String> lvt_9_1_ = this.func_178329_b(lvt_4_1_);
            boolean lvt_10_1_ = this.func_178328_a(lvt_4_1_);
            ItemCameraTransforms lvt_11_1_ = ItemCameraTransforms.field_178357_a;
            if (lvt_4_1_.has("display")) {
               JsonObject lvt_12_1_ = JsonUtils.func_152754_s(lvt_4_1_, "display");
               lvt_11_1_ = (ItemCameraTransforms)p_deserialize_3_.deserialize(lvt_12_1_, ItemCameraTransforms.class);
            }

            return lvt_8_1_ ? new ModelBlock(new ResourceLocation(lvt_6_1_), lvt_9_1_, lvt_10_1_, true, lvt_11_1_) : new ModelBlock(lvt_5_1_, lvt_9_1_, lvt_10_1_, true, lvt_11_1_);
         }
      }

      private Map<String, String> func_178329_b(JsonObject p_178329_1_) {
         Map<String, String> lvt_2_1_ = Maps.newHashMap();
         if (p_178329_1_.has("textures")) {
            JsonObject lvt_3_1_ = p_178329_1_.getAsJsonObject("textures");
            Iterator lvt_4_1_ = lvt_3_1_.entrySet().iterator();

            while(lvt_4_1_.hasNext()) {
               Entry<String, JsonElement> lvt_5_1_ = (Entry)lvt_4_1_.next();
               lvt_2_1_.put(lvt_5_1_.getKey(), ((JsonElement)lvt_5_1_.getValue()).getAsString());
            }
         }

         return lvt_2_1_;
      }

      private String func_178326_c(JsonObject p_178326_1_) {
         return JsonUtils.func_151219_a(p_178326_1_, "parent", "");
      }

      protected boolean func_178328_a(JsonObject p_178328_1_) {
         return JsonUtils.func_151209_a(p_178328_1_, "ambientocclusion", true);
      }

      protected List<BlockPart> func_178325_a(JsonDeserializationContext p_178325_1_, JsonObject p_178325_2_) {
         List<BlockPart> lvt_3_1_ = Lists.newArrayList();
         if (p_178325_2_.has("elements")) {
            Iterator lvt_4_1_ = JsonUtils.func_151214_t(p_178325_2_, "elements").iterator();

            while(lvt_4_1_.hasNext()) {
               JsonElement lvt_5_1_ = (JsonElement)lvt_4_1_.next();
               lvt_3_1_.add((BlockPart)p_178325_1_.deserialize(lvt_5_1_, BlockPart.class));
            }
         }

         return lvt_3_1_;
      }
   }

   static final class Bookkeep {
      public final ModelBlock field_178324_a;
      public ModelBlock field_178323_b;

      private Bookkeep(ModelBlock p_i46223_1_) {
         this.field_178324_a = p_i46223_1_;
      }
   }
}
