package net.optifine.entity.model.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public enum RenderEntityParameterBool implements IExpressionBool {
   IS_ALIVE("is_alive"),
   IS_BURNING("is_burning"),
   IS_CHILD("is_child"),
   IS_GLOWING("is_glowing"),
   IS_HURT("is_hurt"),
   IS_IN_LAVA("is_in_lava"),
   IS_IN_WATER("is_in_water"),
   IS_INVISIBLE("is_invisible"),
   IS_ON_GROUND("is_on_ground"),
   IS_RIDDEN("is_ridden"),
   IS_RIDING("is_riding"),
   IS_SNEAKING("is_sneaking"),
   IS_SPRINTING("is_sprinting"),
   IS_WET("is_wet");

   private String name;
   private RenderManager renderManager;
   private static final RenderEntityParameterBool[] VALUES = values();

   private RenderEntityParameterBool(String name) {
      this.name = name;
      this.renderManager = Minecraft.func_71410_x().func_175598_ae();
   }

   public String getName() {
      return this.name;
   }

   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }

   public boolean eval() {
      Render render = this.renderManager.renderRender;
      if (render == null) {
         return false;
      } else {
         if (render instanceof RendererLivingEntity) {
            RendererLivingEntity rlb = (RendererLivingEntity)render;
            EntityLivingBase entity = rlb.renderEntity;
            if (entity == null) {
               return false;
            }

            switch(this) {
            case IS_ALIVE:
               return entity.func_70089_S();
            case IS_BURNING:
               return entity.func_70027_ad();
            case IS_CHILD:
               return entity.func_70631_g_();
            case IS_HURT:
               return entity.field_70737_aN > 0;
            case IS_IN_LAVA:
               return entity.func_180799_ab();
            case IS_IN_WATER:
               return entity.func_70090_H();
            case IS_INVISIBLE:
               return entity.func_82150_aj();
            case IS_ON_GROUND:
               return entity.field_70122_E;
            case IS_RIDDEN:
               return entity.field_70153_n != null;
            case IS_RIDING:
               return entity.func_70115_ae();
            case IS_SNEAKING:
               return entity.func_70093_af();
            case IS_SPRINTING:
               return entity.func_70051_ag();
            case IS_WET:
               return entity.func_70026_G();
            }
         }

         return false;
      }
   }

   public static RenderEntityParameterBool parse(String str) {
      if (str == null) {
         return null;
      } else {
         for(int i = 0; i < VALUES.length; ++i) {
            RenderEntityParameterBool type = VALUES[i];
            if (type.getName().equals(str)) {
               return type;
            }
         }

         return null;
      }
   }
}
