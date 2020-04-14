package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderGuardian;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterGuardian extends ModelAdapter {
   public ModelAdapterGuardian() {
      super(EntityGuardian.class, "guardian", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelGuardian();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelGuardian)) {
         return null;
      } else {
         ModelGuardian modelGuardian = (ModelGuardian)model;
         if (modelPart.equals("body")) {
            return (ModelRenderer)Reflector.getFieldValue(modelGuardian, Reflector.ModelGuardian_body);
         } else if (modelPart.equals("eye")) {
            return (ModelRenderer)Reflector.getFieldValue(modelGuardian, Reflector.ModelGuardian_eye);
         } else {
            String PREFIX_SPINE = "spine";
            if (modelPart.startsWith(PREFIX_SPINE)) {
               ModelRenderer[] spines = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelGuardian, Reflector.ModelGuardian_spines));
               if (spines == null) {
                  return null;
               } else {
                  String numStr = modelPart.substring(PREFIX_SPINE.length());
                  int index = Config.parseInt(numStr, -1);
                  --index;
                  return index >= 0 && index < spines.length ? spines[index] : null;
               }
            } else {
               String PREFIX_TAIL = "tail";
               if (modelPart.startsWith(PREFIX_TAIL)) {
                  ModelRenderer[] tails = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelGuardian, Reflector.ModelGuardian_tail));
                  if (tails == null) {
                     return null;
                  } else {
                     String numStr = modelPart.substring(PREFIX_TAIL.length());
                     int index = Config.parseInt(numStr, -1);
                     --index;
                     return index >= 0 && index < tails.length ? tails[index] : null;
                  }
               } else {
                  return null;
               }
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body", "eye", "spine1", "spine2", "spine3", "spine4", "spine5", "spine6", "spine7", "spine8", "spine9", "spine10", "spine11", "spine12", "tail1", "tail2", "tail3"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderGuardian render = new RenderGuardian(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
