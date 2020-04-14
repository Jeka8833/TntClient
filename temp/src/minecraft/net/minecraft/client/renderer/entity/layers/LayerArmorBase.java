package net.minecraft.client.renderer.entity.layers;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItems;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;

public abstract class LayerArmorBase<T extends ModelBase> implements LayerRenderer<EntityLivingBase> {
   protected static final ResourceLocation field_177188_b = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   protected T field_177189_c;
   protected T field_177186_d;
   private final RendererLivingEntity<?> field_177190_a;
   private float field_177187_e = 1.0F;
   private float field_177184_f = 1.0F;
   private float field_177185_g = 1.0F;
   private float field_177192_h = 1.0F;
   private boolean field_177193_i;
   private static final Map<String, ResourceLocation> field_177191_j = Maps.newHashMap();

   public LayerArmorBase(RendererLivingEntity<?> p_i46125_1_) {
      this.field_177190_a = p_i46125_1_;
      this.func_177177_a();
   }

   public void func_177141_a(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
      this.func_177182_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, 4);
      this.func_177182_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, 3);
      this.func_177182_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, 2);
      this.func_177182_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, 1);
   }

   public boolean func_177142_b() {
      return false;
   }

   private void func_177182_a(EntityLivingBase p_177182_1_, float p_177182_2_, float p_177182_3_, float p_177182_4_, float p_177182_5_, float p_177182_6_, float p_177182_7_, float p_177182_8_, int p_177182_9_) {
      ItemStack itemstack = this.func_177176_a(p_177182_1_, p_177182_9_);
      if (itemstack != null && itemstack.func_77973_b() instanceof ItemArmor) {
         ItemArmor itemarmor = (ItemArmor)itemstack.func_77973_b();
         T t = this.func_177175_a(p_177182_9_);
         t.func_178686_a(this.field_177190_a.func_177087_b());
         t.func_78086_a(p_177182_1_, p_177182_2_, p_177182_3_, p_177182_4_);
         if (Reflector.ForgeHooksClient.exists()) {
            t = this.getArmorModelHook(p_177182_1_, itemstack, p_177182_9_, t);
         }

         this.func_177179_a(t, p_177182_9_);
         boolean flag = this.func_177180_b(p_177182_9_);
         if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(itemstack, flag ? 2 : 1, (String)null)) {
            if (Reflector.ForgeHooksClient_getArmorTexture.exists()) {
               this.field_177190_a.func_110776_a(this.getArmorResource(p_177182_1_, itemstack, flag ? 2 : 1, (String)null));
            } else {
               this.field_177190_a.func_110776_a(this.func_177181_a(itemarmor, flag));
            }
         }

         int i;
         float f;
         float f1;
         float f2;
         if (Reflector.ForgeHooksClient_getArmorTexture.exists()) {
            if (ReflectorForge.armorHasOverlay(itemarmor, itemstack)) {
               i = itemarmor.func_82814_b(itemstack);
               f = (float)(i >> 16 & 255) / 255.0F;
               f1 = (float)(i >> 8 & 255) / 255.0F;
               f2 = (float)(i & 255) / 255.0F;
               GlStateManager.func_179131_c(this.field_177184_f * f, this.field_177185_g * f1, this.field_177192_h * f2, this.field_177187_e);
               t.func_78088_a(p_177182_1_, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
               if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(itemstack, flag ? 2 : 1, "overlay")) {
                  this.field_177190_a.func_110776_a(this.getArmorResource(p_177182_1_, itemstack, flag ? 2 : 1, "overlay"));
               }
            }

            GlStateManager.func_179131_c(this.field_177184_f, this.field_177185_g, this.field_177192_h, this.field_177187_e);
            t.func_78088_a(p_177182_1_, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
            if (!this.field_177193_i && itemstack.func_77962_s() && (!Config.isCustomItems() || !CustomItems.renderCustomArmorEffect(p_177182_1_, itemstack, t, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_))) {
               this.func_177183_a(p_177182_1_, t, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
            }

            return;
         }

         switch(itemarmor.func_82812_d()) {
         case LEATHER:
            i = itemarmor.func_82814_b(itemstack);
            f = (float)(i >> 16 & 255) / 255.0F;
            f1 = (float)(i >> 8 & 255) / 255.0F;
            f2 = (float)(i & 255) / 255.0F;
            GlStateManager.func_179131_c(this.field_177184_f * f, this.field_177185_g * f1, this.field_177192_h * f2, this.field_177187_e);
            t.func_78088_a(p_177182_1_, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
            if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(itemstack, flag ? 2 : 1, "overlay")) {
               this.field_177190_a.func_110776_a(this.func_177178_a(itemarmor, flag, "overlay"));
            }
         case CHAIN:
         case IRON:
         case GOLD:
         case DIAMOND:
            GlStateManager.func_179131_c(this.field_177184_f, this.field_177185_g, this.field_177192_h, this.field_177187_e);
            t.func_78088_a(p_177182_1_, p_177182_2_, p_177182_3_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
         }

         if (!this.field_177193_i && itemstack.func_77948_v() && (!Config.isCustomItems() || !CustomItems.renderCustomArmorEffect(p_177182_1_, itemstack, t, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_))) {
            this.func_177183_a(p_177182_1_, t, p_177182_2_, p_177182_3_, p_177182_4_, p_177182_5_, p_177182_6_, p_177182_7_, p_177182_8_);
         }
      }

   }

   public ItemStack func_177176_a(EntityLivingBase p_177176_1_, int p_177176_2_) {
      return p_177176_1_.func_82169_q(p_177176_2_ - 1);
   }

   public T func_177175_a(int p_177175_1_) {
      return this.func_177180_b(p_177175_1_) ? this.field_177189_c : this.field_177186_d;
   }

   private boolean func_177180_b(int p_177180_1_) {
      return p_177180_1_ == 2;
   }

   private void func_177183_a(EntityLivingBase p_177183_1_, T p_177183_2_, float p_177183_3_, float p_177183_4_, float p_177183_5_, float p_177183_6_, float p_177183_7_, float p_177183_8_, float p_177183_9_) {
      if (!Config.isShaders() || !Shaders.isShadowPass) {
         float f = (float)p_177183_1_.field_70173_aa + p_177183_5_;
         this.field_177190_a.func_110776_a(field_177188_b);
         if (Config.isShaders()) {
            ShadersRender.renderEnchantedGlintBegin();
         }

         GlStateManager.func_179147_l();
         GlStateManager.func_179143_c(514);
         GlStateManager.func_179132_a(false);
         float f1 = 0.5F;
         GlStateManager.func_179131_c(f1, f1, f1, 1.0F);

         for(int i = 0; i < 2; ++i) {
            GlStateManager.func_179140_f();
            GlStateManager.func_179112_b(768, 1);
            float f2 = 0.76F;
            GlStateManager.func_179131_c(0.5F * f2, 0.25F * f2, 0.8F * f2, 1.0F);
            GlStateManager.func_179128_n(5890);
            GlStateManager.func_179096_D();
            float f3 = 0.33333334F;
            GlStateManager.func_179152_a(f3, f3, f3);
            GlStateManager.func_179114_b(30.0F - (float)i * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179109_b(0.0F, f * (0.001F + (float)i * 0.003F) * 20.0F, 0.0F);
            GlStateManager.func_179128_n(5888);
            p_177183_2_.func_78088_a(p_177183_1_, p_177183_3_, p_177183_4_, p_177183_6_, p_177183_7_, p_177183_8_, p_177183_9_);
         }

         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179096_D();
         GlStateManager.func_179128_n(5888);
         GlStateManager.func_179145_e();
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179143_c(515);
         GlStateManager.func_179084_k();
         if (Config.isShaders()) {
            ShadersRender.renderEnchantedGlintEnd();
         }

      }
   }

   private ResourceLocation func_177181_a(ItemArmor p_177181_1_, boolean p_177181_2_) {
      return this.func_177178_a(p_177181_1_, p_177181_2_, (String)null);
   }

   private ResourceLocation func_177178_a(ItemArmor p_177178_1_, boolean p_177178_2_, String p_177178_3_) {
      String s = String.format("textures/models/armor/%s_layer_%d%s.png", p_177178_1_.func_82812_d().func_179242_c(), p_177178_2_ ? 2 : 1, p_177178_3_ == null ? "" : String.format("_%s", p_177178_3_));
      ResourceLocation resourcelocation = (ResourceLocation)field_177191_j.get(s);
      if (resourcelocation == null) {
         resourcelocation = new ResourceLocation(s);
         field_177191_j.put(s, resourcelocation);
      }

      return resourcelocation;
   }

   protected abstract void func_177177_a();

   protected abstract void func_177179_a(T var1, int var2);

   protected T getArmorModelHook(EntityLivingBase p_getArmorModelHook_1_, ItemStack p_getArmorModelHook_2_, int p_getArmorModelHook_3_, T p_getArmorModelHook_4_) {
      return p_getArmorModelHook_4_;
   }

   public ResourceLocation getArmorResource(Entity p_getArmorResource_1_, ItemStack p_getArmorResource_2_, int p_getArmorResource_3_, String p_getArmorResource_4_) {
      ItemArmor item = (ItemArmor)p_getArmorResource_2_.func_77973_b();
      String texture = item.func_82812_d().func_179242_c();
      String domain = "minecraft";
      int idx = texture.indexOf(58);
      if (idx != -1) {
         domain = texture.substring(0, idx);
         texture = texture.substring(idx + 1);
      }

      String s1 = String.format("%s:textures/models/armor/%s_layer_%d%s.png", domain, texture, this.func_177180_b(p_getArmorResource_3_) ? 2 : 1, p_getArmorResource_4_ == null ? "" : String.format("_%s", p_getArmorResource_4_));
      s1 = Reflector.callString(Reflector.ForgeHooksClient_getArmorTexture, p_getArmorResource_1_, p_getArmorResource_2_, s1, p_getArmorResource_3_, p_getArmorResource_4_);
      ResourceLocation resourcelocation = (ResourceLocation)field_177191_j.get(s1);
      if (resourcelocation == null) {
         resourcelocation = new ResourceLocation(s1);
         field_177191_j.put(s1, resourcelocation);
      }

      return resourcelocation;
   }
}
