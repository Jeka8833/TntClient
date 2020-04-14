package net.minecraft.client.shader;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderManager {
   private static final Logger field_148003_a = LogManager.getLogger();
   private static final ShaderDefault field_148001_b = new ShaderDefault();
   private static ShaderManager field_148002_c = null;
   private static int field_147999_d = -1;
   private static boolean field_148000_e = true;
   private final Map<String, Object> field_147997_f = Maps.newHashMap();
   private final List<String> field_147998_g = Lists.newArrayList();
   private final List<Integer> field_148010_h = Lists.newArrayList();
   private final List<ShaderUniform> field_148011_i = Lists.newArrayList();
   private final List<Integer> field_148008_j = Lists.newArrayList();
   private final Map<String, ShaderUniform> field_148009_k = Maps.newHashMap();
   private final int field_148006_l;
   private final String field_148007_m;
   private final boolean field_148004_n;
   private boolean field_148005_o;
   private final JsonBlendingMode field_148016_p;
   private final List<Integer> field_148015_q;
   private final List<String> field_148014_r;
   private final ShaderLoader field_148013_s;
   private final ShaderLoader field_148012_t;

   public ShaderManager(IResourceManager p_i45087_1_, String p_i45087_2_) throws JsonException, IOException {
      JsonParser lvt_3_1_ = new JsonParser();
      ResourceLocation lvt_4_1_ = new ResourceLocation("shaders/program/" + p_i45087_2_ + ".json");
      this.field_148007_m = p_i45087_2_;
      InputStream lvt_5_1_ = null;

      try {
         lvt_5_1_ = p_i45087_1_.func_110536_a(lvt_4_1_).func_110527_b();
         JsonObject lvt_6_1_ = lvt_3_1_.parse(IOUtils.toString(lvt_5_1_, Charsets.UTF_8)).getAsJsonObject();
         String lvt_7_1_ = JsonUtils.func_151200_h(lvt_6_1_, "vertex");
         String lvt_8_1_ = JsonUtils.func_151200_h(lvt_6_1_, "fragment");
         JsonArray lvt_9_1_ = JsonUtils.func_151213_a(lvt_6_1_, "samplers", (JsonArray)null);
         if (lvt_9_1_ != null) {
            int lvt_10_1_ = 0;

            for(Iterator lvt_11_1_ = lvt_9_1_.iterator(); lvt_11_1_.hasNext(); ++lvt_10_1_) {
               JsonElement lvt_12_1_ = (JsonElement)lvt_11_1_.next();

               try {
                  this.func_147996_a(lvt_12_1_);
               } catch (Exception var25) {
                  JsonException lvt_14_1_ = JsonException.func_151379_a(var25);
                  lvt_14_1_.func_151380_a("samplers[" + lvt_10_1_ + "]");
                  throw lvt_14_1_;
               }
            }
         }

         JsonArray lvt_10_2_ = JsonUtils.func_151213_a(lvt_6_1_, "attributes", (JsonArray)null);
         Iterator lvt_12_4_;
         if (lvt_10_2_ != null) {
            int lvt_11_2_ = 0;
            this.field_148015_q = Lists.newArrayListWithCapacity(lvt_10_2_.size());
            this.field_148014_r = Lists.newArrayListWithCapacity(lvt_10_2_.size());

            for(lvt_12_4_ = lvt_10_2_.iterator(); lvt_12_4_.hasNext(); ++lvt_11_2_) {
               JsonElement lvt_13_2_ = (JsonElement)lvt_12_4_.next();

               try {
                  this.field_148014_r.add(JsonUtils.func_151206_a(lvt_13_2_, "attribute"));
               } catch (Exception var24) {
                  JsonException lvt_15_1_ = JsonException.func_151379_a(var24);
                  lvt_15_1_.func_151380_a("attributes[" + lvt_11_2_ + "]");
                  throw lvt_15_1_;
               }
            }
         } else {
            this.field_148015_q = null;
            this.field_148014_r = null;
         }

         JsonArray lvt_11_3_ = JsonUtils.func_151213_a(lvt_6_1_, "uniforms", (JsonArray)null);
         if (lvt_11_3_ != null) {
            int lvt_12_3_ = 0;

            for(Iterator lvt_13_3_ = lvt_11_3_.iterator(); lvt_13_3_.hasNext(); ++lvt_12_3_) {
               JsonElement lvt_14_3_ = (JsonElement)lvt_13_3_.next();

               try {
                  this.func_147987_b(lvt_14_3_);
               } catch (Exception var23) {
                  JsonException lvt_16_1_ = JsonException.func_151379_a(var23);
                  lvt_16_1_.func_151380_a("uniforms[" + lvt_12_3_ + "]");
                  throw lvt_16_1_;
               }
            }
         }

         this.field_148016_p = JsonBlendingMode.func_148110_a(JsonUtils.func_151218_a(lvt_6_1_, "blend", (JsonObject)null));
         this.field_148004_n = JsonUtils.func_151209_a(lvt_6_1_, "cull", true);
         this.field_148013_s = ShaderLoader.func_148057_a(p_i45087_1_, ShaderLoader.ShaderType.VERTEX, lvt_7_1_);
         this.field_148012_t = ShaderLoader.func_148057_a(p_i45087_1_, ShaderLoader.ShaderType.FRAGMENT, lvt_8_1_);
         this.field_148006_l = ShaderLinkHelper.func_148074_b().func_148078_c();
         ShaderLinkHelper.func_148074_b().func_148075_b(this);
         this.func_147990_i();
         if (this.field_148014_r != null) {
            lvt_12_4_ = this.field_148014_r.iterator();

            while(lvt_12_4_.hasNext()) {
               String lvt_13_4_ = (String)lvt_12_4_.next();
               int lvt_14_4_ = OpenGlHelper.func_153164_b(this.field_148006_l, lvt_13_4_);
               this.field_148015_q.add(lvt_14_4_);
            }
         }
      } catch (Exception var26) {
         JsonException lvt_8_2_ = JsonException.func_151379_a(var26);
         lvt_8_2_.func_151381_b(lvt_4_1_.func_110623_a());
         throw lvt_8_2_;
      } finally {
         IOUtils.closeQuietly(lvt_5_1_);
      }

      this.func_147985_d();
   }

   public void func_147988_a() {
      ShaderLinkHelper.func_148074_b().func_148077_a(this);
   }

   public void func_147993_b() {
      OpenGlHelper.func_153161_d(0);
      field_147999_d = -1;
      field_148002_c = null;
      field_148000_e = true;

      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_148010_h.size(); ++lvt_1_1_) {
         if (this.field_147997_f.get(this.field_147998_g.get(lvt_1_1_)) != null) {
            GlStateManager.func_179138_g(OpenGlHelper.field_77478_a + lvt_1_1_);
            GlStateManager.func_179144_i(0);
         }
      }

   }

   public void func_147995_c() {
      this.field_148005_o = false;
      field_148002_c = this;
      this.field_148016_p.func_148109_a();
      if (this.field_148006_l != field_147999_d) {
         OpenGlHelper.func_153161_d(this.field_148006_l);
         field_147999_d = this.field_148006_l;
      }

      if (this.field_148004_n) {
         GlStateManager.func_179089_o();
      } else {
         GlStateManager.func_179129_p();
      }

      for(int lvt_1_1_ = 0; lvt_1_1_ < this.field_148010_h.size(); ++lvt_1_1_) {
         if (this.field_147997_f.get(this.field_147998_g.get(lvt_1_1_)) != null) {
            GlStateManager.func_179138_g(OpenGlHelper.field_77478_a + lvt_1_1_);
            GlStateManager.func_179098_w();
            Object lvt_2_1_ = this.field_147997_f.get(this.field_147998_g.get(lvt_1_1_));
            int lvt_3_1_ = -1;
            if (lvt_2_1_ instanceof Framebuffer) {
               lvt_3_1_ = ((Framebuffer)lvt_2_1_).field_147617_g;
            } else if (lvt_2_1_ instanceof ITextureObject) {
               lvt_3_1_ = ((ITextureObject)lvt_2_1_).func_110552_b();
            } else if (lvt_2_1_ instanceof Integer) {
               lvt_3_1_ = (Integer)lvt_2_1_;
            }

            if (lvt_3_1_ != -1) {
               GlStateManager.func_179144_i(lvt_3_1_);
               OpenGlHelper.func_153163_f(OpenGlHelper.func_153194_a(this.field_148006_l, (CharSequence)this.field_147998_g.get(lvt_1_1_)), lvt_1_1_);
            }
         }
      }

      Iterator lvt_1_2_ = this.field_148011_i.iterator();

      while(lvt_1_2_.hasNext()) {
         ShaderUniform lvt_2_2_ = (ShaderUniform)lvt_1_2_.next();
         lvt_2_2_.func_148093_b();
      }

   }

   public void func_147985_d() {
      this.field_148005_o = true;
   }

   public ShaderUniform func_147991_a(String p_147991_1_) {
      return this.field_148009_k.containsKey(p_147991_1_) ? (ShaderUniform)this.field_148009_k.get(p_147991_1_) : null;
   }

   public ShaderUniform func_147984_b(String p_147984_1_) {
      return (ShaderUniform)(this.field_148009_k.containsKey(p_147984_1_) ? (ShaderUniform)this.field_148009_k.get(p_147984_1_) : field_148001_b);
   }

   private void func_147990_i() {
      int lvt_1_1_ = 0;

      String lvt_3_2_;
      int lvt_4_2_;
      for(int lvt_2_1_ = 0; lvt_1_1_ < this.field_147998_g.size(); ++lvt_2_1_) {
         lvt_3_2_ = (String)this.field_147998_g.get(lvt_1_1_);
         lvt_4_2_ = OpenGlHelper.func_153194_a(this.field_148006_l, lvt_3_2_);
         if (lvt_4_2_ == -1) {
            field_148003_a.warn("Shader " + this.field_148007_m + "could not find sampler named " + lvt_3_2_ + " in the specified shader program.");
            this.field_147997_f.remove(lvt_3_2_);
            this.field_147998_g.remove(lvt_2_1_);
            --lvt_2_1_;
         } else {
            this.field_148010_h.add(lvt_4_2_);
         }

         ++lvt_1_1_;
      }

      Iterator lvt_1_2_ = this.field_148011_i.iterator();

      while(lvt_1_2_.hasNext()) {
         ShaderUniform lvt_2_2_ = (ShaderUniform)lvt_1_2_.next();
         lvt_3_2_ = lvt_2_2_.func_148086_a();
         lvt_4_2_ = OpenGlHelper.func_153194_a(this.field_148006_l, lvt_3_2_);
         if (lvt_4_2_ == -1) {
            field_148003_a.warn("Could not find uniform named " + lvt_3_2_ + " in the specified" + " shader program.");
         } else {
            this.field_148008_j.add(lvt_4_2_);
            lvt_2_2_.func_148084_b(lvt_4_2_);
            this.field_148009_k.put(lvt_3_2_, lvt_2_2_);
         }
      }

   }

   private void func_147996_a(JsonElement p_147996_1_) throws JsonException {
      JsonObject lvt_2_1_ = JsonUtils.func_151210_l(p_147996_1_, "sampler");
      String lvt_3_1_ = JsonUtils.func_151200_h(lvt_2_1_, "name");
      if (!JsonUtils.func_151205_a(lvt_2_1_, "file")) {
         this.field_147997_f.put(lvt_3_1_, (Object)null);
         this.field_147998_g.add(lvt_3_1_);
      } else {
         this.field_147998_g.add(lvt_3_1_);
      }
   }

   public void func_147992_a(String p_147992_1_, Object p_147992_2_) {
      if (this.field_147997_f.containsKey(p_147992_1_)) {
         this.field_147997_f.remove(p_147992_1_);
      }

      this.field_147997_f.put(p_147992_1_, p_147992_2_);
      this.func_147985_d();
   }

   private void func_147987_b(JsonElement p_147987_1_) throws JsonException {
      JsonObject lvt_2_1_ = JsonUtils.func_151210_l(p_147987_1_, "uniform");
      String lvt_3_1_ = JsonUtils.func_151200_h(lvt_2_1_, "name");
      int lvt_4_1_ = ShaderUniform.func_148085_a(JsonUtils.func_151200_h(lvt_2_1_, "type"));
      int lvt_5_1_ = JsonUtils.func_151203_m(lvt_2_1_, "count");
      float[] lvt_6_1_ = new float[Math.max(lvt_5_1_, 16)];
      JsonArray lvt_7_1_ = JsonUtils.func_151214_t(lvt_2_1_, "values");
      if (lvt_7_1_.size() != lvt_5_1_ && lvt_7_1_.size() > 1) {
         throw new JsonException("Invalid amount of values specified (expected " + lvt_5_1_ + ", found " + lvt_7_1_.size() + ")");
      } else {
         int lvt_8_1_ = 0;

         for(Iterator lvt_9_1_ = lvt_7_1_.iterator(); lvt_9_1_.hasNext(); ++lvt_8_1_) {
            JsonElement lvt_10_1_ = (JsonElement)lvt_9_1_.next();

            try {
               lvt_6_1_[lvt_8_1_] = JsonUtils.func_151220_d(lvt_10_1_, "value");
            } catch (Exception var13) {
               JsonException lvt_12_1_ = JsonException.func_151379_a(var13);
               lvt_12_1_.func_151380_a("values[" + lvt_8_1_ + "]");
               throw lvt_12_1_;
            }
         }

         if (lvt_5_1_ > 1 && lvt_7_1_.size() == 1) {
            while(lvt_8_1_ < lvt_5_1_) {
               lvt_6_1_[lvt_8_1_] = lvt_6_1_[0];
               ++lvt_8_1_;
            }
         }

         int lvt_9_2_ = lvt_5_1_ > 1 && lvt_5_1_ <= 4 && lvt_4_1_ < 8 ? lvt_5_1_ - 1 : 0;
         ShaderUniform lvt_10_2_ = new ShaderUniform(lvt_3_1_, lvt_4_1_ + lvt_9_2_, lvt_5_1_, this);
         if (lvt_4_1_ <= 3) {
            lvt_10_2_.func_148083_a((int)lvt_6_1_[0], (int)lvt_6_1_[1], (int)lvt_6_1_[2], (int)lvt_6_1_[3]);
         } else if (lvt_4_1_ <= 7) {
            lvt_10_2_.func_148092_b(lvt_6_1_[0], lvt_6_1_[1], lvt_6_1_[2], lvt_6_1_[3]);
         } else {
            lvt_10_2_.func_148097_a(lvt_6_1_);
         }

         this.field_148011_i.add(lvt_10_2_);
      }
   }

   public ShaderLoader func_147989_e() {
      return this.field_148013_s;
   }

   public ShaderLoader func_147994_f() {
      return this.field_148012_t;
   }

   public int func_147986_h() {
      return this.field_148006_l;
   }
}
