package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterSilverfish extends ModelAdapter {
   public ModelAdapterSilverfish() {
      super(EntitySilverfish.class, "silverfish", 0.3F);
   }

   public ModelBase makeModel() {
      return new ModelSilverfish();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelSilverfish)) {
         return null;
      } else {
         ModelSilverfish modelSilverfish = (ModelSilverfish)model;
         String PREFIX_BODY = "body";
         if (modelPart.startsWith(PREFIX_BODY)) {
            ModelRenderer[] bodyParts = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelSilverfish, Reflector.ModelSilverfish_bodyParts));
            if (bodyParts == null) {
               return null;
            } else {
               String numStr = modelPart.substring(PREFIX_BODY.length());
               int index = Config.parseInt(numStr, -1);
               --index;
               return index >= 0 && index < bodyParts.length ? bodyParts[index] : null;
            }
         } else {
            String PREFIX_WINGS = "wing";
            if (modelPart.startsWith(PREFIX_WINGS)) {
               ModelRenderer[] wings = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelSilverfish, Reflector.ModelSilverfish_wingParts));
               if (wings == null) {
                  return null;
               } else {
                  String numStr = modelPart.substring(PREFIX_WINGS.length());
                  int index = Config.parseInt(numStr, -1);
                  --index;
                  return index >= 0 && index < wings.length ? wings[index] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "body4", "body5", "body6", "body7", "wing1", "wing2", "wing3"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderSilverfish render = new RenderSilverfish(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
