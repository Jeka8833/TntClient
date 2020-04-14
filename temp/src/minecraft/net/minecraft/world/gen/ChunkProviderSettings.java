package net.minecraft.world.gen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.world.biome.BiomeGenBase;

public class ChunkProviderSettings {
   public final float field_177811_a;
   public final float field_177809_b;
   public final float field_177810_c;
   public final float field_177806_d;
   public final float field_177808_e;
   public final float field_177803_f;
   public final float field_177804_g;
   public final float field_177825_h;
   public final float field_177827_i;
   public final float field_177821_j;
   public final float field_177823_k;
   public final float field_177817_l;
   public final float field_177819_m;
   public final float field_177813_n;
   public final float field_177815_o;
   public final float field_177843_p;
   public final int field_177841_q;
   public final boolean field_177839_r;
   public final boolean field_177837_s;
   public final int field_177835_t;
   public final boolean field_177833_u;
   public final boolean field_177831_v;
   public final boolean field_177829_w;
   public final boolean field_177854_x;
   public final boolean field_177852_y;
   public final boolean field_177850_z;
   public final boolean field_177781_A;
   public final int field_177782_B;
   public final boolean field_177783_C;
   public final int field_177777_D;
   public final boolean field_177778_E;
   public final int field_177779_F;
   public final int field_177780_G;
   public final int field_177788_H;
   public final int field_177789_I;
   public final int field_177790_J;
   public final int field_177791_K;
   public final int field_177784_L;
   public final int field_177785_M;
   public final int field_177786_N;
   public final int field_177787_O;
   public final int field_177797_P;
   public final int field_177796_Q;
   public final int field_177799_R;
   public final int field_177798_S;
   public final int field_177793_T;
   public final int field_177792_U;
   public final int field_177795_V;
   public final int field_177794_W;
   public final int field_177801_X;
   public final int field_177800_Y;
   public final int field_177802_Z;
   public final int field_177846_aa;
   public final int field_177847_ab;
   public final int field_177844_ac;
   public final int field_177845_ad;
   public final int field_177851_ae;
   public final int field_177853_af;
   public final int field_177848_ag;
   public final int field_177849_ah;
   public final int field_177832_ai;
   public final int field_177834_aj;
   public final int field_177828_ak;
   public final int field_177830_al;
   public final int field_177840_am;
   public final int field_177842_an;
   public final int field_177836_ao;
   public final int field_177838_ap;
   public final int field_177818_aq;
   public final int field_177816_ar;
   public final int field_177814_as;
   public final int field_177812_at;
   public final int field_177826_au;
   public final int field_177824_av;
   public final int field_177822_aw;
   public final int field_177820_ax;
   public final int field_177807_ay;
   public final int field_177805_az;

   private ChunkProviderSettings(ChunkProviderSettings.Factory p_i45639_1_) {
      this.field_177811_a = p_i45639_1_.field_177899_b;
      this.field_177809_b = p_i45639_1_.field_177900_c;
      this.field_177810_c = p_i45639_1_.field_177896_d;
      this.field_177806_d = p_i45639_1_.field_177898_e;
      this.field_177808_e = p_i45639_1_.field_177893_f;
      this.field_177803_f = p_i45639_1_.field_177894_g;
      this.field_177804_g = p_i45639_1_.field_177915_h;
      this.field_177825_h = p_i45639_1_.field_177917_i;
      this.field_177827_i = p_i45639_1_.field_177911_j;
      this.field_177821_j = p_i45639_1_.field_177913_k;
      this.field_177823_k = p_i45639_1_.field_177907_l;
      this.field_177817_l = p_i45639_1_.field_177909_m;
      this.field_177819_m = p_i45639_1_.field_177903_n;
      this.field_177813_n = p_i45639_1_.field_177905_o;
      this.field_177815_o = p_i45639_1_.field_177933_p;
      this.field_177843_p = p_i45639_1_.field_177931_q;
      this.field_177841_q = p_i45639_1_.field_177929_r;
      this.field_177839_r = p_i45639_1_.field_177927_s;
      this.field_177837_s = p_i45639_1_.field_177925_t;
      this.field_177835_t = p_i45639_1_.field_177923_u;
      this.field_177833_u = p_i45639_1_.field_177921_v;
      this.field_177831_v = p_i45639_1_.field_177919_w;
      this.field_177829_w = p_i45639_1_.field_177944_x;
      this.field_177854_x = p_i45639_1_.field_177942_y;
      this.field_177852_y = p_i45639_1_.field_177940_z;
      this.field_177850_z = p_i45639_1_.field_177870_A;
      this.field_177781_A = p_i45639_1_.field_177871_B;
      this.field_177782_B = p_i45639_1_.field_177872_C;
      this.field_177783_C = p_i45639_1_.field_177866_D;
      this.field_177777_D = p_i45639_1_.field_177867_E;
      this.field_177778_E = p_i45639_1_.field_177868_F;
      this.field_177779_F = p_i45639_1_.field_177869_G;
      this.field_177780_G = p_i45639_1_.field_177877_H;
      this.field_177788_H = p_i45639_1_.field_177878_I;
      this.field_177789_I = p_i45639_1_.field_177879_J;
      this.field_177790_J = p_i45639_1_.field_177880_K;
      this.field_177791_K = p_i45639_1_.field_177873_L;
      this.field_177784_L = p_i45639_1_.field_177874_M;
      this.field_177785_M = p_i45639_1_.field_177875_N;
      this.field_177786_N = p_i45639_1_.field_177876_O;
      this.field_177787_O = p_i45639_1_.field_177886_P;
      this.field_177797_P = p_i45639_1_.field_177885_Q;
      this.field_177796_Q = p_i45639_1_.field_177888_R;
      this.field_177799_R = p_i45639_1_.field_177887_S;
      this.field_177798_S = p_i45639_1_.field_177882_T;
      this.field_177793_T = p_i45639_1_.field_177881_U;
      this.field_177792_U = p_i45639_1_.field_177884_V;
      this.field_177795_V = p_i45639_1_.field_177883_W;
      this.field_177794_W = p_i45639_1_.field_177891_X;
      this.field_177801_X = p_i45639_1_.field_177890_Y;
      this.field_177800_Y = p_i45639_1_.field_177892_Z;
      this.field_177802_Z = p_i45639_1_.field_177936_aa;
      this.field_177846_aa = p_i45639_1_.field_177937_ab;
      this.field_177847_ab = p_i45639_1_.field_177934_ac;
      this.field_177844_ac = p_i45639_1_.field_177935_ad;
      this.field_177845_ad = p_i45639_1_.field_177941_ae;
      this.field_177851_ae = p_i45639_1_.field_177943_af;
      this.field_177853_af = p_i45639_1_.field_177938_ag;
      this.field_177848_ag = p_i45639_1_.field_177939_ah;
      this.field_177849_ah = p_i45639_1_.field_177922_ai;
      this.field_177832_ai = p_i45639_1_.field_177924_aj;
      this.field_177834_aj = p_i45639_1_.field_177918_ak;
      this.field_177828_ak = p_i45639_1_.field_177920_al;
      this.field_177830_al = p_i45639_1_.field_177930_am;
      this.field_177840_am = p_i45639_1_.field_177932_an;
      this.field_177842_an = p_i45639_1_.field_177926_ao;
      this.field_177836_ao = p_i45639_1_.field_177928_ap;
      this.field_177838_ap = p_i45639_1_.field_177908_aq;
      this.field_177818_aq = p_i45639_1_.field_177906_ar;
      this.field_177816_ar = p_i45639_1_.field_177904_as;
      this.field_177814_as = p_i45639_1_.field_177902_at;
      this.field_177812_at = p_i45639_1_.field_177916_au;
      this.field_177826_au = p_i45639_1_.field_177914_av;
      this.field_177824_av = p_i45639_1_.field_177912_aw;
      this.field_177822_aw = p_i45639_1_.field_177910_ax;
      this.field_177820_ax = p_i45639_1_.field_177897_ay;
      this.field_177807_ay = p_i45639_1_.field_177895_az;
      this.field_177805_az = p_i45639_1_.field_177889_aA;
   }

   public static class Serializer implements JsonDeserializer<ChunkProviderSettings.Factory>, JsonSerializer<ChunkProviderSettings.Factory> {
      public ChunkProviderSettings.Factory deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject lvt_4_1_ = p_deserialize_1_.getAsJsonObject();
         ChunkProviderSettings.Factory lvt_5_1_ = new ChunkProviderSettings.Factory();

         try {
            lvt_5_1_.field_177899_b = JsonUtils.func_151221_a(lvt_4_1_, "coordinateScale", lvt_5_1_.field_177899_b);
            lvt_5_1_.field_177900_c = JsonUtils.func_151221_a(lvt_4_1_, "heightScale", lvt_5_1_.field_177900_c);
            lvt_5_1_.field_177898_e = JsonUtils.func_151221_a(lvt_4_1_, "lowerLimitScale", lvt_5_1_.field_177898_e);
            lvt_5_1_.field_177896_d = JsonUtils.func_151221_a(lvt_4_1_, "upperLimitScale", lvt_5_1_.field_177896_d);
            lvt_5_1_.field_177893_f = JsonUtils.func_151221_a(lvt_4_1_, "depthNoiseScaleX", lvt_5_1_.field_177893_f);
            lvt_5_1_.field_177894_g = JsonUtils.func_151221_a(lvt_4_1_, "depthNoiseScaleZ", lvt_5_1_.field_177894_g);
            lvt_5_1_.field_177915_h = JsonUtils.func_151221_a(lvt_4_1_, "depthNoiseScaleExponent", lvt_5_1_.field_177915_h);
            lvt_5_1_.field_177917_i = JsonUtils.func_151221_a(lvt_4_1_, "mainNoiseScaleX", lvt_5_1_.field_177917_i);
            lvt_5_1_.field_177911_j = JsonUtils.func_151221_a(lvt_4_1_, "mainNoiseScaleY", lvt_5_1_.field_177911_j);
            lvt_5_1_.field_177913_k = JsonUtils.func_151221_a(lvt_4_1_, "mainNoiseScaleZ", lvt_5_1_.field_177913_k);
            lvt_5_1_.field_177907_l = JsonUtils.func_151221_a(lvt_4_1_, "baseSize", lvt_5_1_.field_177907_l);
            lvt_5_1_.field_177909_m = JsonUtils.func_151221_a(lvt_4_1_, "stretchY", lvt_5_1_.field_177909_m);
            lvt_5_1_.field_177903_n = JsonUtils.func_151221_a(lvt_4_1_, "biomeDepthWeight", lvt_5_1_.field_177903_n);
            lvt_5_1_.field_177905_o = JsonUtils.func_151221_a(lvt_4_1_, "biomeDepthOffset", lvt_5_1_.field_177905_o);
            lvt_5_1_.field_177933_p = JsonUtils.func_151221_a(lvt_4_1_, "biomeScaleWeight", lvt_5_1_.field_177933_p);
            lvt_5_1_.field_177931_q = JsonUtils.func_151221_a(lvt_4_1_, "biomeScaleOffset", lvt_5_1_.field_177931_q);
            lvt_5_1_.field_177929_r = JsonUtils.func_151208_a(lvt_4_1_, "seaLevel", lvt_5_1_.field_177929_r);
            lvt_5_1_.field_177927_s = JsonUtils.func_151209_a(lvt_4_1_, "useCaves", lvt_5_1_.field_177927_s);
            lvt_5_1_.field_177925_t = JsonUtils.func_151209_a(lvt_4_1_, "useDungeons", lvt_5_1_.field_177925_t);
            lvt_5_1_.field_177923_u = JsonUtils.func_151208_a(lvt_4_1_, "dungeonChance", lvt_5_1_.field_177923_u);
            lvt_5_1_.field_177921_v = JsonUtils.func_151209_a(lvt_4_1_, "useStrongholds", lvt_5_1_.field_177921_v);
            lvt_5_1_.field_177919_w = JsonUtils.func_151209_a(lvt_4_1_, "useVillages", lvt_5_1_.field_177919_w);
            lvt_5_1_.field_177944_x = JsonUtils.func_151209_a(lvt_4_1_, "useMineShafts", lvt_5_1_.field_177944_x);
            lvt_5_1_.field_177942_y = JsonUtils.func_151209_a(lvt_4_1_, "useTemples", lvt_5_1_.field_177942_y);
            lvt_5_1_.field_177940_z = JsonUtils.func_151209_a(lvt_4_1_, "useMonuments", lvt_5_1_.field_177940_z);
            lvt_5_1_.field_177870_A = JsonUtils.func_151209_a(lvt_4_1_, "useRavines", lvt_5_1_.field_177870_A);
            lvt_5_1_.field_177871_B = JsonUtils.func_151209_a(lvt_4_1_, "useWaterLakes", lvt_5_1_.field_177871_B);
            lvt_5_1_.field_177872_C = JsonUtils.func_151208_a(lvt_4_1_, "waterLakeChance", lvt_5_1_.field_177872_C);
            lvt_5_1_.field_177866_D = JsonUtils.func_151209_a(lvt_4_1_, "useLavaLakes", lvt_5_1_.field_177866_D);
            lvt_5_1_.field_177867_E = JsonUtils.func_151208_a(lvt_4_1_, "lavaLakeChance", lvt_5_1_.field_177867_E);
            lvt_5_1_.field_177868_F = JsonUtils.func_151209_a(lvt_4_1_, "useLavaOceans", lvt_5_1_.field_177868_F);
            lvt_5_1_.field_177869_G = JsonUtils.func_151208_a(lvt_4_1_, "fixedBiome", lvt_5_1_.field_177869_G);
            if (lvt_5_1_.field_177869_G < 38 && lvt_5_1_.field_177869_G >= -1) {
               if (lvt_5_1_.field_177869_G >= BiomeGenBase.field_76778_j.field_76756_M) {
                  lvt_5_1_.field_177869_G += 2;
               }
            } else {
               lvt_5_1_.field_177869_G = -1;
            }

            lvt_5_1_.field_177877_H = JsonUtils.func_151208_a(lvt_4_1_, "biomeSize", lvt_5_1_.field_177877_H);
            lvt_5_1_.field_177878_I = JsonUtils.func_151208_a(lvt_4_1_, "riverSize", lvt_5_1_.field_177878_I);
            lvt_5_1_.field_177879_J = JsonUtils.func_151208_a(lvt_4_1_, "dirtSize", lvt_5_1_.field_177879_J);
            lvt_5_1_.field_177880_K = JsonUtils.func_151208_a(lvt_4_1_, "dirtCount", lvt_5_1_.field_177880_K);
            lvt_5_1_.field_177873_L = JsonUtils.func_151208_a(lvt_4_1_, "dirtMinHeight", lvt_5_1_.field_177873_L);
            lvt_5_1_.field_177874_M = JsonUtils.func_151208_a(lvt_4_1_, "dirtMaxHeight", lvt_5_1_.field_177874_M);
            lvt_5_1_.field_177875_N = JsonUtils.func_151208_a(lvt_4_1_, "gravelSize", lvt_5_1_.field_177875_N);
            lvt_5_1_.field_177876_O = JsonUtils.func_151208_a(lvt_4_1_, "gravelCount", lvt_5_1_.field_177876_O);
            lvt_5_1_.field_177886_P = JsonUtils.func_151208_a(lvt_4_1_, "gravelMinHeight", lvt_5_1_.field_177886_P);
            lvt_5_1_.field_177885_Q = JsonUtils.func_151208_a(lvt_4_1_, "gravelMaxHeight", lvt_5_1_.field_177885_Q);
            lvt_5_1_.field_177888_R = JsonUtils.func_151208_a(lvt_4_1_, "graniteSize", lvt_5_1_.field_177888_R);
            lvt_5_1_.field_177887_S = JsonUtils.func_151208_a(lvt_4_1_, "graniteCount", lvt_5_1_.field_177887_S);
            lvt_5_1_.field_177882_T = JsonUtils.func_151208_a(lvt_4_1_, "graniteMinHeight", lvt_5_1_.field_177882_T);
            lvt_5_1_.field_177881_U = JsonUtils.func_151208_a(lvt_4_1_, "graniteMaxHeight", lvt_5_1_.field_177881_U);
            lvt_5_1_.field_177884_V = JsonUtils.func_151208_a(lvt_4_1_, "dioriteSize", lvt_5_1_.field_177884_V);
            lvt_5_1_.field_177883_W = JsonUtils.func_151208_a(lvt_4_1_, "dioriteCount", lvt_5_1_.field_177883_W);
            lvt_5_1_.field_177891_X = JsonUtils.func_151208_a(lvt_4_1_, "dioriteMinHeight", lvt_5_1_.field_177891_X);
            lvt_5_1_.field_177890_Y = JsonUtils.func_151208_a(lvt_4_1_, "dioriteMaxHeight", lvt_5_1_.field_177890_Y);
            lvt_5_1_.field_177892_Z = JsonUtils.func_151208_a(lvt_4_1_, "andesiteSize", lvt_5_1_.field_177892_Z);
            lvt_5_1_.field_177936_aa = JsonUtils.func_151208_a(lvt_4_1_, "andesiteCount", lvt_5_1_.field_177936_aa);
            lvt_5_1_.field_177937_ab = JsonUtils.func_151208_a(lvt_4_1_, "andesiteMinHeight", lvt_5_1_.field_177937_ab);
            lvt_5_1_.field_177934_ac = JsonUtils.func_151208_a(lvt_4_1_, "andesiteMaxHeight", lvt_5_1_.field_177934_ac);
            lvt_5_1_.field_177935_ad = JsonUtils.func_151208_a(lvt_4_1_, "coalSize", lvt_5_1_.field_177935_ad);
            lvt_5_1_.field_177941_ae = JsonUtils.func_151208_a(lvt_4_1_, "coalCount", lvt_5_1_.field_177941_ae);
            lvt_5_1_.field_177943_af = JsonUtils.func_151208_a(lvt_4_1_, "coalMinHeight", lvt_5_1_.field_177943_af);
            lvt_5_1_.field_177938_ag = JsonUtils.func_151208_a(lvt_4_1_, "coalMaxHeight", lvt_5_1_.field_177938_ag);
            lvt_5_1_.field_177939_ah = JsonUtils.func_151208_a(lvt_4_1_, "ironSize", lvt_5_1_.field_177939_ah);
            lvt_5_1_.field_177922_ai = JsonUtils.func_151208_a(lvt_4_1_, "ironCount", lvt_5_1_.field_177922_ai);
            lvt_5_1_.field_177924_aj = JsonUtils.func_151208_a(lvt_4_1_, "ironMinHeight", lvt_5_1_.field_177924_aj);
            lvt_5_1_.field_177918_ak = JsonUtils.func_151208_a(lvt_4_1_, "ironMaxHeight", lvt_5_1_.field_177918_ak);
            lvt_5_1_.field_177920_al = JsonUtils.func_151208_a(lvt_4_1_, "goldSize", lvt_5_1_.field_177920_al);
            lvt_5_1_.field_177930_am = JsonUtils.func_151208_a(lvt_4_1_, "goldCount", lvt_5_1_.field_177930_am);
            lvt_5_1_.field_177932_an = JsonUtils.func_151208_a(lvt_4_1_, "goldMinHeight", lvt_5_1_.field_177932_an);
            lvt_5_1_.field_177926_ao = JsonUtils.func_151208_a(lvt_4_1_, "goldMaxHeight", lvt_5_1_.field_177926_ao);
            lvt_5_1_.field_177928_ap = JsonUtils.func_151208_a(lvt_4_1_, "redstoneSize", lvt_5_1_.field_177928_ap);
            lvt_5_1_.field_177908_aq = JsonUtils.func_151208_a(lvt_4_1_, "redstoneCount", lvt_5_1_.field_177908_aq);
            lvt_5_1_.field_177906_ar = JsonUtils.func_151208_a(lvt_4_1_, "redstoneMinHeight", lvt_5_1_.field_177906_ar);
            lvt_5_1_.field_177904_as = JsonUtils.func_151208_a(lvt_4_1_, "redstoneMaxHeight", lvt_5_1_.field_177904_as);
            lvt_5_1_.field_177902_at = JsonUtils.func_151208_a(lvt_4_1_, "diamondSize", lvt_5_1_.field_177902_at);
            lvt_5_1_.field_177916_au = JsonUtils.func_151208_a(lvt_4_1_, "diamondCount", lvt_5_1_.field_177916_au);
            lvt_5_1_.field_177914_av = JsonUtils.func_151208_a(lvt_4_1_, "diamondMinHeight", lvt_5_1_.field_177914_av);
            lvt_5_1_.field_177912_aw = JsonUtils.func_151208_a(lvt_4_1_, "diamondMaxHeight", lvt_5_1_.field_177912_aw);
            lvt_5_1_.field_177910_ax = JsonUtils.func_151208_a(lvt_4_1_, "lapisSize", lvt_5_1_.field_177910_ax);
            lvt_5_1_.field_177897_ay = JsonUtils.func_151208_a(lvt_4_1_, "lapisCount", lvt_5_1_.field_177897_ay);
            lvt_5_1_.field_177895_az = JsonUtils.func_151208_a(lvt_4_1_, "lapisCenterHeight", lvt_5_1_.field_177895_az);
            lvt_5_1_.field_177889_aA = JsonUtils.func_151208_a(lvt_4_1_, "lapisSpread", lvt_5_1_.field_177889_aA);
         } catch (Exception var7) {
         }

         return lvt_5_1_;
      }

      public JsonElement serialize(ChunkProviderSettings.Factory p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
         JsonObject lvt_4_1_ = new JsonObject();
         lvt_4_1_.addProperty("coordinateScale", (Number)p_serialize_1_.field_177899_b);
         lvt_4_1_.addProperty("heightScale", (Number)p_serialize_1_.field_177900_c);
         lvt_4_1_.addProperty("lowerLimitScale", (Number)p_serialize_1_.field_177898_e);
         lvt_4_1_.addProperty("upperLimitScale", (Number)p_serialize_1_.field_177896_d);
         lvt_4_1_.addProperty("depthNoiseScaleX", (Number)p_serialize_1_.field_177893_f);
         lvt_4_1_.addProperty("depthNoiseScaleZ", (Number)p_serialize_1_.field_177894_g);
         lvt_4_1_.addProperty("depthNoiseScaleExponent", (Number)p_serialize_1_.field_177915_h);
         lvt_4_1_.addProperty("mainNoiseScaleX", (Number)p_serialize_1_.field_177917_i);
         lvt_4_1_.addProperty("mainNoiseScaleY", (Number)p_serialize_1_.field_177911_j);
         lvt_4_1_.addProperty("mainNoiseScaleZ", (Number)p_serialize_1_.field_177913_k);
         lvt_4_1_.addProperty("baseSize", (Number)p_serialize_1_.field_177907_l);
         lvt_4_1_.addProperty("stretchY", (Number)p_serialize_1_.field_177909_m);
         lvt_4_1_.addProperty("biomeDepthWeight", (Number)p_serialize_1_.field_177903_n);
         lvt_4_1_.addProperty("biomeDepthOffset", (Number)p_serialize_1_.field_177905_o);
         lvt_4_1_.addProperty("biomeScaleWeight", (Number)p_serialize_1_.field_177933_p);
         lvt_4_1_.addProperty("biomeScaleOffset", (Number)p_serialize_1_.field_177931_q);
         lvt_4_1_.addProperty("seaLevel", (Number)p_serialize_1_.field_177929_r);
         lvt_4_1_.addProperty("useCaves", p_serialize_1_.field_177927_s);
         lvt_4_1_.addProperty("useDungeons", p_serialize_1_.field_177925_t);
         lvt_4_1_.addProperty("dungeonChance", (Number)p_serialize_1_.field_177923_u);
         lvt_4_1_.addProperty("useStrongholds", p_serialize_1_.field_177921_v);
         lvt_4_1_.addProperty("useVillages", p_serialize_1_.field_177919_w);
         lvt_4_1_.addProperty("useMineShafts", p_serialize_1_.field_177944_x);
         lvt_4_1_.addProperty("useTemples", p_serialize_1_.field_177942_y);
         lvt_4_1_.addProperty("useMonuments", p_serialize_1_.field_177940_z);
         lvt_4_1_.addProperty("useRavines", p_serialize_1_.field_177870_A);
         lvt_4_1_.addProperty("useWaterLakes", p_serialize_1_.field_177871_B);
         lvt_4_1_.addProperty("waterLakeChance", (Number)p_serialize_1_.field_177872_C);
         lvt_4_1_.addProperty("useLavaLakes", p_serialize_1_.field_177866_D);
         lvt_4_1_.addProperty("lavaLakeChance", (Number)p_serialize_1_.field_177867_E);
         lvt_4_1_.addProperty("useLavaOceans", p_serialize_1_.field_177868_F);
         lvt_4_1_.addProperty("fixedBiome", (Number)p_serialize_1_.field_177869_G);
         lvt_4_1_.addProperty("biomeSize", (Number)p_serialize_1_.field_177877_H);
         lvt_4_1_.addProperty("riverSize", (Number)p_serialize_1_.field_177878_I);
         lvt_4_1_.addProperty("dirtSize", (Number)p_serialize_1_.field_177879_J);
         lvt_4_1_.addProperty("dirtCount", (Number)p_serialize_1_.field_177880_K);
         lvt_4_1_.addProperty("dirtMinHeight", (Number)p_serialize_1_.field_177873_L);
         lvt_4_1_.addProperty("dirtMaxHeight", (Number)p_serialize_1_.field_177874_M);
         lvt_4_1_.addProperty("gravelSize", (Number)p_serialize_1_.field_177875_N);
         lvt_4_1_.addProperty("gravelCount", (Number)p_serialize_1_.field_177876_O);
         lvt_4_1_.addProperty("gravelMinHeight", (Number)p_serialize_1_.field_177886_P);
         lvt_4_1_.addProperty("gravelMaxHeight", (Number)p_serialize_1_.field_177885_Q);
         lvt_4_1_.addProperty("graniteSize", (Number)p_serialize_1_.field_177888_R);
         lvt_4_1_.addProperty("graniteCount", (Number)p_serialize_1_.field_177887_S);
         lvt_4_1_.addProperty("graniteMinHeight", (Number)p_serialize_1_.field_177882_T);
         lvt_4_1_.addProperty("graniteMaxHeight", (Number)p_serialize_1_.field_177881_U);
         lvt_4_1_.addProperty("dioriteSize", (Number)p_serialize_1_.field_177884_V);
         lvt_4_1_.addProperty("dioriteCount", (Number)p_serialize_1_.field_177883_W);
         lvt_4_1_.addProperty("dioriteMinHeight", (Number)p_serialize_1_.field_177891_X);
         lvt_4_1_.addProperty("dioriteMaxHeight", (Number)p_serialize_1_.field_177890_Y);
         lvt_4_1_.addProperty("andesiteSize", (Number)p_serialize_1_.field_177892_Z);
         lvt_4_1_.addProperty("andesiteCount", (Number)p_serialize_1_.field_177936_aa);
         lvt_4_1_.addProperty("andesiteMinHeight", (Number)p_serialize_1_.field_177937_ab);
         lvt_4_1_.addProperty("andesiteMaxHeight", (Number)p_serialize_1_.field_177934_ac);
         lvt_4_1_.addProperty("coalSize", (Number)p_serialize_1_.field_177935_ad);
         lvt_4_1_.addProperty("coalCount", (Number)p_serialize_1_.field_177941_ae);
         lvt_4_1_.addProperty("coalMinHeight", (Number)p_serialize_1_.field_177943_af);
         lvt_4_1_.addProperty("coalMaxHeight", (Number)p_serialize_1_.field_177938_ag);
         lvt_4_1_.addProperty("ironSize", (Number)p_serialize_1_.field_177939_ah);
         lvt_4_1_.addProperty("ironCount", (Number)p_serialize_1_.field_177922_ai);
         lvt_4_1_.addProperty("ironMinHeight", (Number)p_serialize_1_.field_177924_aj);
         lvt_4_1_.addProperty("ironMaxHeight", (Number)p_serialize_1_.field_177918_ak);
         lvt_4_1_.addProperty("goldSize", (Number)p_serialize_1_.field_177920_al);
         lvt_4_1_.addProperty("goldCount", (Number)p_serialize_1_.field_177930_am);
         lvt_4_1_.addProperty("goldMinHeight", (Number)p_serialize_1_.field_177932_an);
         lvt_4_1_.addProperty("goldMaxHeight", (Number)p_serialize_1_.field_177926_ao);
         lvt_4_1_.addProperty("redstoneSize", (Number)p_serialize_1_.field_177928_ap);
         lvt_4_1_.addProperty("redstoneCount", (Number)p_serialize_1_.field_177908_aq);
         lvt_4_1_.addProperty("redstoneMinHeight", (Number)p_serialize_1_.field_177906_ar);
         lvt_4_1_.addProperty("redstoneMaxHeight", (Number)p_serialize_1_.field_177904_as);
         lvt_4_1_.addProperty("diamondSize", (Number)p_serialize_1_.field_177902_at);
         lvt_4_1_.addProperty("diamondCount", (Number)p_serialize_1_.field_177916_au);
         lvt_4_1_.addProperty("diamondMinHeight", (Number)p_serialize_1_.field_177914_av);
         lvt_4_1_.addProperty("diamondMaxHeight", (Number)p_serialize_1_.field_177912_aw);
         lvt_4_1_.addProperty("lapisSize", (Number)p_serialize_1_.field_177910_ax);
         lvt_4_1_.addProperty("lapisCount", (Number)p_serialize_1_.field_177897_ay);
         lvt_4_1_.addProperty("lapisCenterHeight", (Number)p_serialize_1_.field_177895_az);
         lvt_4_1_.addProperty("lapisSpread", (Number)p_serialize_1_.field_177889_aA);
         return lvt_4_1_;
      }
   }

   public static class Factory {
      static final Gson field_177901_a = (new GsonBuilder()).registerTypeAdapter(ChunkProviderSettings.Factory.class, new ChunkProviderSettings.Serializer()).create();
      public float field_177899_b = 684.412F;
      public float field_177900_c = 684.412F;
      public float field_177896_d = 512.0F;
      public float field_177898_e = 512.0F;
      public float field_177893_f = 200.0F;
      public float field_177894_g = 200.0F;
      public float field_177915_h = 0.5F;
      public float field_177917_i = 80.0F;
      public float field_177911_j = 160.0F;
      public float field_177913_k = 80.0F;
      public float field_177907_l = 8.5F;
      public float field_177909_m = 12.0F;
      public float field_177903_n = 1.0F;
      public float field_177905_o = 0.0F;
      public float field_177933_p = 1.0F;
      public float field_177931_q = 0.0F;
      public int field_177929_r = 63;
      public boolean field_177927_s = true;
      public boolean field_177925_t = true;
      public int field_177923_u = 8;
      public boolean field_177921_v = true;
      public boolean field_177919_w = true;
      public boolean field_177944_x = true;
      public boolean field_177942_y = true;
      public boolean field_177940_z = true;
      public boolean field_177870_A = true;
      public boolean field_177871_B = true;
      public int field_177872_C = 4;
      public boolean field_177866_D = true;
      public int field_177867_E = 80;
      public boolean field_177868_F = false;
      public int field_177869_G = -1;
      public int field_177877_H = 4;
      public int field_177878_I = 4;
      public int field_177879_J = 33;
      public int field_177880_K = 10;
      public int field_177873_L = 0;
      public int field_177874_M = 256;
      public int field_177875_N = 33;
      public int field_177876_O = 8;
      public int field_177886_P = 0;
      public int field_177885_Q = 256;
      public int field_177888_R = 33;
      public int field_177887_S = 10;
      public int field_177882_T = 0;
      public int field_177881_U = 80;
      public int field_177884_V = 33;
      public int field_177883_W = 10;
      public int field_177891_X = 0;
      public int field_177890_Y = 80;
      public int field_177892_Z = 33;
      public int field_177936_aa = 10;
      public int field_177937_ab = 0;
      public int field_177934_ac = 80;
      public int field_177935_ad = 17;
      public int field_177941_ae = 20;
      public int field_177943_af = 0;
      public int field_177938_ag = 128;
      public int field_177939_ah = 9;
      public int field_177922_ai = 20;
      public int field_177924_aj = 0;
      public int field_177918_ak = 64;
      public int field_177920_al = 9;
      public int field_177930_am = 2;
      public int field_177932_an = 0;
      public int field_177926_ao = 32;
      public int field_177928_ap = 8;
      public int field_177908_aq = 8;
      public int field_177906_ar = 0;
      public int field_177904_as = 16;
      public int field_177902_at = 8;
      public int field_177916_au = 1;
      public int field_177914_av = 0;
      public int field_177912_aw = 16;
      public int field_177910_ax = 7;
      public int field_177897_ay = 1;
      public int field_177895_az = 16;
      public int field_177889_aA = 16;

      public static ChunkProviderSettings.Factory func_177865_a(String p_177865_0_) {
         if (p_177865_0_.length() == 0) {
            return new ChunkProviderSettings.Factory();
         } else {
            try {
               return (ChunkProviderSettings.Factory)field_177901_a.fromJson(p_177865_0_, ChunkProviderSettings.Factory.class);
            } catch (Exception var2) {
               return new ChunkProviderSettings.Factory();
            }
         }
      }

      public String toString() {
         return field_177901_a.toJson((Object)this);
      }

      public Factory() {
         this.func_177863_a();
      }

      public void func_177863_a() {
         this.field_177899_b = 684.412F;
         this.field_177900_c = 684.412F;
         this.field_177896_d = 512.0F;
         this.field_177898_e = 512.0F;
         this.field_177893_f = 200.0F;
         this.field_177894_g = 200.0F;
         this.field_177915_h = 0.5F;
         this.field_177917_i = 80.0F;
         this.field_177911_j = 160.0F;
         this.field_177913_k = 80.0F;
         this.field_177907_l = 8.5F;
         this.field_177909_m = 12.0F;
         this.field_177903_n = 1.0F;
         this.field_177905_o = 0.0F;
         this.field_177933_p = 1.0F;
         this.field_177931_q = 0.0F;
         this.field_177929_r = 63;
         this.field_177927_s = true;
         this.field_177925_t = true;
         this.field_177923_u = 8;
         this.field_177921_v = true;
         this.field_177919_w = true;
         this.field_177944_x = true;
         this.field_177942_y = true;
         this.field_177940_z = true;
         this.field_177870_A = true;
         this.field_177871_B = true;
         this.field_177872_C = 4;
         this.field_177866_D = true;
         this.field_177867_E = 80;
         this.field_177868_F = false;
         this.field_177869_G = -1;
         this.field_177877_H = 4;
         this.field_177878_I = 4;
         this.field_177879_J = 33;
         this.field_177880_K = 10;
         this.field_177873_L = 0;
         this.field_177874_M = 256;
         this.field_177875_N = 33;
         this.field_177876_O = 8;
         this.field_177886_P = 0;
         this.field_177885_Q = 256;
         this.field_177888_R = 33;
         this.field_177887_S = 10;
         this.field_177882_T = 0;
         this.field_177881_U = 80;
         this.field_177884_V = 33;
         this.field_177883_W = 10;
         this.field_177891_X = 0;
         this.field_177890_Y = 80;
         this.field_177892_Z = 33;
         this.field_177936_aa = 10;
         this.field_177937_ab = 0;
         this.field_177934_ac = 80;
         this.field_177935_ad = 17;
         this.field_177941_ae = 20;
         this.field_177943_af = 0;
         this.field_177938_ag = 128;
         this.field_177939_ah = 9;
         this.field_177922_ai = 20;
         this.field_177924_aj = 0;
         this.field_177918_ak = 64;
         this.field_177920_al = 9;
         this.field_177930_am = 2;
         this.field_177932_an = 0;
         this.field_177926_ao = 32;
         this.field_177928_ap = 8;
         this.field_177908_aq = 8;
         this.field_177906_ar = 0;
         this.field_177904_as = 16;
         this.field_177902_at = 8;
         this.field_177916_au = 1;
         this.field_177914_av = 0;
         this.field_177912_aw = 16;
         this.field_177910_ax = 7;
         this.field_177897_ay = 1;
         this.field_177895_az = 16;
         this.field_177889_aA = 16;
      }

      public boolean equals(Object p_equals_1_) {
         if (this == p_equals_1_) {
            return true;
         } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            ChunkProviderSettings.Factory lvt_2_1_ = (ChunkProviderSettings.Factory)p_equals_1_;
            if (this.field_177936_aa != lvt_2_1_.field_177936_aa) {
               return false;
            } else if (this.field_177934_ac != lvt_2_1_.field_177934_ac) {
               return false;
            } else if (this.field_177937_ab != lvt_2_1_.field_177937_ab) {
               return false;
            } else if (this.field_177892_Z != lvt_2_1_.field_177892_Z) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177907_l, this.field_177907_l) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177905_o, this.field_177905_o) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177903_n, this.field_177903_n) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177931_q, this.field_177931_q) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177933_p, this.field_177933_p) != 0) {
               return false;
            } else if (this.field_177877_H != lvt_2_1_.field_177877_H) {
               return false;
            } else if (this.field_177941_ae != lvt_2_1_.field_177941_ae) {
               return false;
            } else if (this.field_177938_ag != lvt_2_1_.field_177938_ag) {
               return false;
            } else if (this.field_177943_af != lvt_2_1_.field_177943_af) {
               return false;
            } else if (this.field_177935_ad != lvt_2_1_.field_177935_ad) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177899_b, this.field_177899_b) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177915_h, this.field_177915_h) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177893_f, this.field_177893_f) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177894_g, this.field_177894_g) != 0) {
               return false;
            } else if (this.field_177916_au != lvt_2_1_.field_177916_au) {
               return false;
            } else if (this.field_177912_aw != lvt_2_1_.field_177912_aw) {
               return false;
            } else if (this.field_177914_av != lvt_2_1_.field_177914_av) {
               return false;
            } else if (this.field_177902_at != lvt_2_1_.field_177902_at) {
               return false;
            } else if (this.field_177883_W != lvt_2_1_.field_177883_W) {
               return false;
            } else if (this.field_177890_Y != lvt_2_1_.field_177890_Y) {
               return false;
            } else if (this.field_177891_X != lvt_2_1_.field_177891_X) {
               return false;
            } else if (this.field_177884_V != lvt_2_1_.field_177884_V) {
               return false;
            } else if (this.field_177880_K != lvt_2_1_.field_177880_K) {
               return false;
            } else if (this.field_177874_M != lvt_2_1_.field_177874_M) {
               return false;
            } else if (this.field_177873_L != lvt_2_1_.field_177873_L) {
               return false;
            } else if (this.field_177879_J != lvt_2_1_.field_177879_J) {
               return false;
            } else if (this.field_177923_u != lvt_2_1_.field_177923_u) {
               return false;
            } else if (this.field_177869_G != lvt_2_1_.field_177869_G) {
               return false;
            } else if (this.field_177930_am != lvt_2_1_.field_177930_am) {
               return false;
            } else if (this.field_177926_ao != lvt_2_1_.field_177926_ao) {
               return false;
            } else if (this.field_177932_an != lvt_2_1_.field_177932_an) {
               return false;
            } else if (this.field_177920_al != lvt_2_1_.field_177920_al) {
               return false;
            } else if (this.field_177887_S != lvt_2_1_.field_177887_S) {
               return false;
            } else if (this.field_177881_U != lvt_2_1_.field_177881_U) {
               return false;
            } else if (this.field_177882_T != lvt_2_1_.field_177882_T) {
               return false;
            } else if (this.field_177888_R != lvt_2_1_.field_177888_R) {
               return false;
            } else if (this.field_177876_O != lvt_2_1_.field_177876_O) {
               return false;
            } else if (this.field_177885_Q != lvt_2_1_.field_177885_Q) {
               return false;
            } else if (this.field_177886_P != lvt_2_1_.field_177886_P) {
               return false;
            } else if (this.field_177875_N != lvt_2_1_.field_177875_N) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177900_c, this.field_177900_c) != 0) {
               return false;
            } else if (this.field_177922_ai != lvt_2_1_.field_177922_ai) {
               return false;
            } else if (this.field_177918_ak != lvt_2_1_.field_177918_ak) {
               return false;
            } else if (this.field_177924_aj != lvt_2_1_.field_177924_aj) {
               return false;
            } else if (this.field_177939_ah != lvt_2_1_.field_177939_ah) {
               return false;
            } else if (this.field_177895_az != lvt_2_1_.field_177895_az) {
               return false;
            } else if (this.field_177897_ay != lvt_2_1_.field_177897_ay) {
               return false;
            } else if (this.field_177910_ax != lvt_2_1_.field_177910_ax) {
               return false;
            } else if (this.field_177889_aA != lvt_2_1_.field_177889_aA) {
               return false;
            } else if (this.field_177867_E != lvt_2_1_.field_177867_E) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177898_e, this.field_177898_e) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177917_i, this.field_177917_i) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177911_j, this.field_177911_j) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177913_k, this.field_177913_k) != 0) {
               return false;
            } else if (this.field_177908_aq != lvt_2_1_.field_177908_aq) {
               return false;
            } else if (this.field_177904_as != lvt_2_1_.field_177904_as) {
               return false;
            } else if (this.field_177906_ar != lvt_2_1_.field_177906_ar) {
               return false;
            } else if (this.field_177928_ap != lvt_2_1_.field_177928_ap) {
               return false;
            } else if (this.field_177878_I != lvt_2_1_.field_177878_I) {
               return false;
            } else if (this.field_177929_r != lvt_2_1_.field_177929_r) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177909_m, this.field_177909_m) != 0) {
               return false;
            } else if (Float.compare(lvt_2_1_.field_177896_d, this.field_177896_d) != 0) {
               return false;
            } else if (this.field_177927_s != lvt_2_1_.field_177927_s) {
               return false;
            } else if (this.field_177925_t != lvt_2_1_.field_177925_t) {
               return false;
            } else if (this.field_177866_D != lvt_2_1_.field_177866_D) {
               return false;
            } else if (this.field_177868_F != lvt_2_1_.field_177868_F) {
               return false;
            } else if (this.field_177944_x != lvt_2_1_.field_177944_x) {
               return false;
            } else if (this.field_177870_A != lvt_2_1_.field_177870_A) {
               return false;
            } else if (this.field_177921_v != lvt_2_1_.field_177921_v) {
               return false;
            } else if (this.field_177942_y != lvt_2_1_.field_177942_y) {
               return false;
            } else if (this.field_177940_z != lvt_2_1_.field_177940_z) {
               return false;
            } else if (this.field_177919_w != lvt_2_1_.field_177919_w) {
               return false;
            } else if (this.field_177871_B != lvt_2_1_.field_177871_B) {
               return false;
            } else {
               return this.field_177872_C == lvt_2_1_.field_177872_C;
            }
         } else {
            return false;
         }
      }

      public int hashCode() {
         int lvt_1_1_ = this.field_177899_b != 0.0F ? Float.floatToIntBits(this.field_177899_b) : 0;
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177900_c != 0.0F ? Float.floatToIntBits(this.field_177900_c) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177896_d != 0.0F ? Float.floatToIntBits(this.field_177896_d) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177898_e != 0.0F ? Float.floatToIntBits(this.field_177898_e) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177893_f != 0.0F ? Float.floatToIntBits(this.field_177893_f) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177894_g != 0.0F ? Float.floatToIntBits(this.field_177894_g) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177915_h != 0.0F ? Float.floatToIntBits(this.field_177915_h) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177917_i != 0.0F ? Float.floatToIntBits(this.field_177917_i) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177911_j != 0.0F ? Float.floatToIntBits(this.field_177911_j) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177913_k != 0.0F ? Float.floatToIntBits(this.field_177913_k) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177907_l != 0.0F ? Float.floatToIntBits(this.field_177907_l) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177909_m != 0.0F ? Float.floatToIntBits(this.field_177909_m) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177903_n != 0.0F ? Float.floatToIntBits(this.field_177903_n) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177905_o != 0.0F ? Float.floatToIntBits(this.field_177905_o) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177933_p != 0.0F ? Float.floatToIntBits(this.field_177933_p) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177931_q != 0.0F ? Float.floatToIntBits(this.field_177931_q) : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177929_r;
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177927_s ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177925_t ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177923_u;
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177921_v ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177919_w ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177944_x ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177942_y ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177940_z ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177870_A ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177871_B ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177872_C;
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177866_D ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177867_E;
         lvt_1_1_ = 31 * lvt_1_1_ + (this.field_177868_F ? 1 : 0);
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177869_G;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177877_H;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177878_I;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177879_J;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177880_K;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177873_L;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177874_M;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177875_N;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177876_O;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177886_P;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177885_Q;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177888_R;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177887_S;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177882_T;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177881_U;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177884_V;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177883_W;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177891_X;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177890_Y;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177892_Z;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177936_aa;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177937_ab;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177934_ac;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177935_ad;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177941_ae;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177943_af;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177938_ag;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177939_ah;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177922_ai;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177924_aj;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177918_ak;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177920_al;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177930_am;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177932_an;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177926_ao;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177928_ap;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177908_aq;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177906_ar;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177904_as;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177902_at;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177916_au;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177914_av;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177912_aw;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177910_ax;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177897_ay;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177895_az;
         lvt_1_1_ = 31 * lvt_1_1_ + this.field_177889_aA;
         return lvt_1_1_;
      }

      public ChunkProviderSettings func_177864_b() {
         return new ChunkProviderSettings(this);
      }
   }
}
