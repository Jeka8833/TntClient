package net.minecraft.client.shader;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class ShaderGroup {
   private Framebuffer field_148035_a;
   private IResourceManager field_148033_b;
   private String field_148034_c;
   private final List<Shader> field_148031_d = Lists.newArrayList();
   private final Map<String, Framebuffer> field_148032_e = Maps.newHashMap();
   private final List<Framebuffer> field_148029_f = Lists.newArrayList();
   private Matrix4f field_148030_g;
   private int field_148038_h;
   private int field_148039_i;
   private float field_148036_j;
   private float field_148037_k;

   public ShaderGroup(TextureManager p_i1050_1_, IResourceManager p_i1050_2_, Framebuffer p_i1050_3_, ResourceLocation p_i1050_4_) throws JsonException, IOException, JsonSyntaxException {
      this.field_148033_b = p_i1050_2_;
      this.field_148035_a = p_i1050_3_;
      this.field_148036_j = 0.0F;
      this.field_148037_k = 0.0F;
      this.field_148038_h = p_i1050_3_.field_147621_c;
      this.field_148039_i = p_i1050_3_.field_147618_d;
      this.field_148034_c = p_i1050_4_.toString();
      this.func_148024_c();
      this.func_152765_a(p_i1050_1_, p_i1050_4_);
   }

   public void func_152765_a(TextureManager p_152765_1_, ResourceLocation p_152765_2_) throws JsonException, IOException, JsonSyntaxException {
      JsonParser lvt_3_1_ = new JsonParser();
      InputStream lvt_4_1_ = null;

      try {
         IResource lvt_5_1_ = this.field_148033_b.func_110536_a(p_152765_2_);
         lvt_4_1_ = lvt_5_1_.func_110527_b();
         JsonObject lvt_6_1_ = lvt_3_1_.parse(IOUtils.toString(lvt_4_1_, Charsets.UTF_8)).getAsJsonObject();
         JsonArray lvt_7_2_;
         int lvt_8_2_;
         Iterator lvt_9_2_;
         JsonElement lvt_10_2_;
         JsonException lvt_12_2_;
         if (JsonUtils.func_151202_d(lvt_6_1_, "targets")) {
            lvt_7_2_ = lvt_6_1_.getAsJsonArray("targets");
            lvt_8_2_ = 0;

            for(lvt_9_2_ = lvt_7_2_.iterator(); lvt_9_2_.hasNext(); ++lvt_8_2_) {
               lvt_10_2_ = (JsonElement)lvt_9_2_.next();

               try {
                  this.func_148027_a(lvt_10_2_);
               } catch (Exception var19) {
                  lvt_12_2_ = JsonException.func_151379_a(var19);
                  lvt_12_2_.func_151380_a("targets[" + lvt_8_2_ + "]");
                  throw lvt_12_2_;
               }
            }
         }

         if (JsonUtils.func_151202_d(lvt_6_1_, "passes")) {
            lvt_7_2_ = lvt_6_1_.getAsJsonArray("passes");
            lvt_8_2_ = 0;

            for(lvt_9_2_ = lvt_7_2_.iterator(); lvt_9_2_.hasNext(); ++lvt_8_2_) {
               lvt_10_2_ = (JsonElement)lvt_9_2_.next();

               try {
                  this.func_152764_a(p_152765_1_, lvt_10_2_);
               } catch (Exception var18) {
                  lvt_12_2_ = JsonException.func_151379_a(var18);
                  lvt_12_2_.func_151380_a("passes[" + lvt_8_2_ + "]");
                  throw lvt_12_2_;
               }
            }
         }
      } catch (Exception var20) {
         JsonException lvt_6_2_ = JsonException.func_151379_a(var20);
         lvt_6_2_.func_151381_b(p_152765_2_.func_110623_a());
         throw lvt_6_2_;
      } finally {
         IOUtils.closeQuietly(lvt_4_1_);
      }

   }

   private void func_148027_a(JsonElement p_148027_1_) throws JsonException {
      if (JsonUtils.func_151211_a(p_148027_1_)) {
         this.func_148020_a(p_148027_1_.getAsString(), this.field_148038_h, this.field_148039_i);
      } else {
         JsonObject lvt_2_1_ = JsonUtils.func_151210_l(p_148027_1_, "target");
         String lvt_3_1_ = JsonUtils.func_151200_h(lvt_2_1_, "name");
         int lvt_4_1_ = JsonUtils.func_151208_a(lvt_2_1_, "width", this.field_148038_h);
         int lvt_5_1_ = JsonUtils.func_151208_a(lvt_2_1_, "height", this.field_148039_i);
         if (this.field_148032_e.containsKey(lvt_3_1_)) {
            throw new JsonException(lvt_3_1_ + " is already defined");
         }

         this.func_148020_a(lvt_3_1_, lvt_4_1_, lvt_5_1_);
      }

   }

   private void func_152764_a(TextureManager p_152764_1_, JsonElement p_152764_2_) throws JsonException, IOException {
      JsonObject lvt_3_1_ = JsonUtils.func_151210_l(p_152764_2_, "pass");
      String lvt_4_1_ = JsonUtils.func_151200_h(lvt_3_1_, "name");
      String lvt_5_1_ = JsonUtils.func_151200_h(lvt_3_1_, "intarget");
      String lvt_6_1_ = JsonUtils.func_151200_h(lvt_3_1_, "outtarget");
      Framebuffer lvt_7_1_ = this.func_148017_a(lvt_5_1_);
      Framebuffer lvt_8_1_ = this.func_148017_a(lvt_6_1_);
      if (lvt_7_1_ == null) {
         throw new JsonException("Input target '" + lvt_5_1_ + "' does not exist");
      } else if (lvt_8_1_ == null) {
         throw new JsonException("Output target '" + lvt_6_1_ + "' does not exist");
      } else {
         Shader lvt_9_1_ = this.func_148023_a(lvt_4_1_, lvt_7_1_, lvt_8_1_);
         JsonArray lvt_10_1_ = JsonUtils.func_151213_a(lvt_3_1_, "auxtargets", (JsonArray)null);
         if (lvt_10_1_ != null) {
            int lvt_11_1_ = 0;

            for(Iterator lvt_12_1_ = lvt_10_1_.iterator(); lvt_12_1_.hasNext(); ++lvt_11_1_) {
               JsonElement lvt_13_1_ = (JsonElement)lvt_12_1_.next();

               try {
                  JsonObject lvt_14_1_ = JsonUtils.func_151210_l(lvt_13_1_, "auxtarget");
                  String lvt_15_1_ = JsonUtils.func_151200_h(lvt_14_1_, "name");
                  String lvt_16_1_ = JsonUtils.func_151200_h(lvt_14_1_, "id");
                  Framebuffer lvt_17_1_ = this.func_148017_a(lvt_16_1_);
                  if (lvt_17_1_ == null) {
                     ResourceLocation lvt_18_1_ = new ResourceLocation("textures/effect/" + lvt_16_1_ + ".png");

                     try {
                        this.field_148033_b.func_110536_a(lvt_18_1_);
                     } catch (FileNotFoundException var24) {
                        throw new JsonException("Render target or texture '" + lvt_16_1_ + "' does not exist");
                     }

                     p_152764_1_.func_110577_a(lvt_18_1_);
                     ITextureObject lvt_19_2_ = p_152764_1_.func_110581_b(lvt_18_1_);
                     int lvt_20_1_ = JsonUtils.func_151203_m(lvt_14_1_, "width");
                     int lvt_21_1_ = JsonUtils.func_151203_m(lvt_14_1_, "height");
                     boolean lvt_22_1_ = JsonUtils.func_151212_i(lvt_14_1_, "bilinear");
                     if (lvt_22_1_) {
                        GL11.glTexParameteri(3553, 10241, 9729);
                        GL11.glTexParameteri(3553, 10240, 9729);
                     } else {
                        GL11.glTexParameteri(3553, 10241, 9728);
                        GL11.glTexParameteri(3553, 10240, 9728);
                     }

                     lvt_9_1_.func_148041_a(lvt_15_1_, lvt_19_2_.func_110552_b(), lvt_20_1_, lvt_21_1_);
                  } else {
                     lvt_9_1_.func_148041_a(lvt_15_1_, lvt_17_1_, lvt_17_1_.field_147622_a, lvt_17_1_.field_147620_b);
                  }
               } catch (Exception var25) {
                  JsonException lvt_15_2_ = JsonException.func_151379_a(var25);
                  lvt_15_2_.func_151380_a("auxtargets[" + lvt_11_1_ + "]");
                  throw lvt_15_2_;
               }
            }
         }

         JsonArray lvt_11_2_ = JsonUtils.func_151213_a(lvt_3_1_, "uniforms", (JsonArray)null);
         if (lvt_11_2_ != null) {
            int lvt_12_2_ = 0;

            for(Iterator lvt_13_2_ = lvt_11_2_.iterator(); lvt_13_2_.hasNext(); ++lvt_12_2_) {
               JsonElement lvt_14_3_ = (JsonElement)lvt_13_2_.next();

               try {
                  this.func_148028_c(lvt_14_3_);
               } catch (Exception var23) {
                  JsonException lvt_16_2_ = JsonException.func_151379_a(var23);
                  lvt_16_2_.func_151380_a("uniforms[" + lvt_12_2_ + "]");
                  throw lvt_16_2_;
               }
            }
         }

      }
   }

   private void func_148028_c(JsonElement p_148028_1_) throws JsonException {
      JsonObject lvt_2_1_ = JsonUtils.func_151210_l(p_148028_1_, "uniform");
      String lvt_3_1_ = JsonUtils.func_151200_h(lvt_2_1_, "name");
      ShaderUniform lvt_4_1_ = ((Shader)this.field_148031_d.get(this.field_148031_d.size() - 1)).func_148043_c().func_147991_a(lvt_3_1_);
      if (lvt_4_1_ == null) {
         throw new JsonException("Uniform '" + lvt_3_1_ + "' does not exist");
      } else {
         float[] lvt_5_1_ = new float[4];
         int lvt_6_1_ = 0;
         JsonArray lvt_7_1_ = JsonUtils.func_151214_t(lvt_2_1_, "values");

         for(Iterator lvt_8_1_ = lvt_7_1_.iterator(); lvt_8_1_.hasNext(); ++lvt_6_1_) {
            JsonElement lvt_9_1_ = (JsonElement)lvt_8_1_.next();

            try {
               lvt_5_1_[lvt_6_1_] = JsonUtils.func_151220_d(lvt_9_1_, "value");
            } catch (Exception var12) {
               JsonException lvt_11_1_ = JsonException.func_151379_a(var12);
               lvt_11_1_.func_151380_a("values[" + lvt_6_1_ + "]");
               throw lvt_11_1_;
            }
         }

         switch(lvt_6_1_) {
         case 0:
         default:
            break;
         case 1:
            lvt_4_1_.func_148090_a(lvt_5_1_[0]);
            break;
         case 2:
            lvt_4_1_.func_148087_a(lvt_5_1_[0], lvt_5_1_[1]);
            break;
         case 3:
            lvt_4_1_.func_148095_a(lvt_5_1_[0], lvt_5_1_[1], lvt_5_1_[2]);
            break;
         case 4:
            lvt_4_1_.func_148081_a(lvt_5_1_[0], lvt_5_1_[1], lvt_5_1_[2], lvt_5_1_[3]);
         }

      }
   }

   public Framebuffer func_177066_a(String p_177066_1_) {
      return (Framebuffer)this.field_148032_e.get(p_177066_1_);
   }

   public void func_148020_a(String p_148020_1_, int p_148020_2_, int p_148020_3_) {
      Framebuffer lvt_4_1_ = new Framebuffer(p_148020_2_, p_148020_3_, true);
      lvt_4_1_.func_147604_a(0.0F, 0.0F, 0.0F, 0.0F);
      this.field_148032_e.put(p_148020_1_, lvt_4_1_);
      if (p_148020_2_ == this.field_148038_h && p_148020_3_ == this.field_148039_i) {
         this.field_148029_f.add(lvt_4_1_);
      }

   }

   public void func_148021_a() {
      Iterator lvt_1_2_ = this.field_148032_e.values().iterator();

      while(lvt_1_2_.hasNext()) {
         Framebuffer lvt_2_1_ = (Framebuffer)lvt_1_2_.next();
         lvt_2_1_.func_147608_a();
      }

      lvt_1_2_ = this.field_148031_d.iterator();

      while(lvt_1_2_.hasNext()) {
         Shader lvt_2_2_ = (Shader)lvt_1_2_.next();
         lvt_2_2_.func_148044_b();
      }

      this.field_148031_d.clear();
   }

   public Shader func_148023_a(String p_148023_1_, Framebuffer p_148023_2_, Framebuffer p_148023_3_) throws JsonException, IOException {
      Shader lvt_4_1_ = new Shader(this.field_148033_b, p_148023_1_, p_148023_2_, p_148023_3_);
      this.field_148031_d.add(this.field_148031_d.size(), lvt_4_1_);
      return lvt_4_1_;
   }

   private void func_148024_c() {
      this.field_148030_g = new Matrix4f();
      this.field_148030_g.setIdentity();
      this.field_148030_g.m00 = 2.0F / (float)this.field_148035_a.field_147622_a;
      this.field_148030_g.m11 = 2.0F / (float)(-this.field_148035_a.field_147620_b);
      this.field_148030_g.m22 = -0.0020001999F;
      this.field_148030_g.m33 = 1.0F;
      this.field_148030_g.m03 = -1.0F;
      this.field_148030_g.m13 = 1.0F;
      this.field_148030_g.m23 = -1.0001999F;
   }

   public void func_148026_a(int p_148026_1_, int p_148026_2_) {
      this.field_148038_h = this.field_148035_a.field_147622_a;
      this.field_148039_i = this.field_148035_a.field_147620_b;
      this.func_148024_c();
      Iterator lvt_3_2_ = this.field_148031_d.iterator();

      while(lvt_3_2_.hasNext()) {
         Shader lvt_4_1_ = (Shader)lvt_3_2_.next();
         lvt_4_1_.func_148045_a(this.field_148030_g);
      }

      lvt_3_2_ = this.field_148029_f.iterator();

      while(lvt_3_2_.hasNext()) {
         Framebuffer lvt_4_2_ = (Framebuffer)lvt_3_2_.next();
         lvt_4_2_.func_147613_a(p_148026_1_, p_148026_2_);
      }

   }

   public void func_148018_a(float p_148018_1_) {
      if (p_148018_1_ < this.field_148037_k) {
         this.field_148036_j += 1.0F - this.field_148037_k;
         this.field_148036_j += p_148018_1_;
      } else {
         this.field_148036_j += p_148018_1_ - this.field_148037_k;
      }

      for(this.field_148037_k = p_148018_1_; this.field_148036_j > 20.0F; this.field_148036_j -= 20.0F) {
      }

      Iterator lvt_2_1_ = this.field_148031_d.iterator();

      while(lvt_2_1_.hasNext()) {
         Shader lvt_3_1_ = (Shader)lvt_2_1_.next();
         lvt_3_1_.func_148042_a(this.field_148036_j / 20.0F);
      }

   }

   public final String func_148022_b() {
      return this.field_148034_c;
   }

   private Framebuffer func_148017_a(String p_148017_1_) {
      if (p_148017_1_ == null) {
         return null;
      } else {
         return p_148017_1_.equals("minecraft:main") ? this.field_148035_a : (Framebuffer)this.field_148032_e.get(p_148017_1_);
      }
   }
}
