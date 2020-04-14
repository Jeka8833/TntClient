package net.optifine.entity.model.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionFloat;

public enum RenderEntityParameterFloat implements IExpressionFloat {
   LIMB_SWING("limb_swing"),
   LIMB_SWING_SPEED("limb_speed"),
   AGE("age"),
   HEAD_YAW("head_yaw"),
   HEAD_PITCH("head_pitch"),
   SCALE("scale"),
   HEALTH("health"),
   HURT_TIME("hurt_time"),
   IDLE_TIME("idle_time"),
   MAX_HEALTH("max_health"),
   MOVE_FORWARD("move_forward"),
   MOVE_STRAFING("move_strafing"),
   PARTIAL_TICKS("partial_ticks"),
   POS_X("pos_x"),
   POS_Y("pos_Y"),
   POS_Z("pos_Z"),
   REVENGE_TIME("revenge_time"),
   SWING_PROGRESS("swing_progress");

   private String name;
   private RenderManager renderManager;
   private static final RenderEntityParameterFloat[] VALUES = values();

   private RenderEntityParameterFloat(String name) {
      this.name = name;
      this.renderManager = Minecraft.func_71410_x().func_175598_ae();
   }

   public String getName() {
      return this.name;
   }

   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   public float eval() {
      Render render = this.renderManager.renderRender;
      if (render == null) {
         return 0.0F;
      } else {
         if (render instanceof RendererLivingEntity) {
            RendererLivingEntity rlb = (RendererLivingEntity)render;
            switch(this) {
            case LIMB_SWING:
               return rlb.renderLimbSwing;
            case LIMB_SWING_SPEED:
               return rlb.renderLimbSwingAmount;
            case AGE:
               return rlb.renderAgeInTicks;
            case HEAD_YAW:
               return rlb.renderHeadYaw;
            case HEAD_PITCH:
               return rlb.renderHeadPitch;
            case SCALE:
               return rlb.renderScaleFactor;
            default:
               EntityLivingBase entity = rlb.renderEntity;
               if (entity == null) {
                  return 0.0F;
               }

               switch(this) {
               case HEALTH:
                  return entity.func_110143_aJ();
               case HURT_TIME:
                  return (float)entity.field_70737_aN;
               case IDLE_TIME:
                  return (float)entity.func_70654_ax();
               case MAX_HEALTH:
                  return entity.func_110138_aP();
               case MOVE_FORWARD:
                  return entity.field_70701_bs;
               case MOVE_STRAFING:
                  return entity.field_70702_br;
               case POS_X:
                  return (float)entity.field_70165_t;
               case POS_Y:
                  return (float)entity.field_70163_u;
               case POS_Z:
                  return (float)entity.field_70161_v;
               case REVENGE_TIME:
                  return (float)entity.func_142015_aE();
               case SWING_PROGRESS:
                  return entity.func_70678_g(rlb.renderPartialTicks);
               }
            }
         }

         return 0.0F;
      }
   }

   public static RenderEntityParameterFloat parse(String str) {
      if (str == null) {
         return null;
      } else {
         for(int i = 0; i < VALUES.length; ++i) {
            RenderEntityParameterFloat type = VALUES[i];
            if (type.getName().equals(str)) {
               return type;
            }
         }

         return null;
      }
   }
}
