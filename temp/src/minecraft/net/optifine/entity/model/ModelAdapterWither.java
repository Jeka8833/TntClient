package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterWither extends ModelAdapter {
   public ModelAdapterWither() {
      super(EntityWither.class, "wither", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelWither(0.0F);
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelWither)) {
         return null;
      } else {
         ModelWither modelWither = (ModelWither)model;
         String PREFIX_BODY = "body";
         if (modelPart.startsWith(PREFIX_BODY)) {
            ModelRenderer[] bodyParts = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelWither, Reflector.ModelWither_bodyParts));
            if (bodyParts == null) {
               return null;
            } else {
               String numStr = modelPart.substring(PREFIX_BODY.length());
               int index = Config.parseInt(numStr, -1);
               --index;
               return index >= 0 && index < bodyParts.length ? bodyParts[index] : null;
            }
         } else {
            String PREFIX_HEAD = "head";
            if (modelPart.startsWith(PREFIX_HEAD)) {
               ModelRenderer[] heads = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelWither, Reflector.ModelWither_heads));
               if (heads == null) {
                  return null;
               } else {
                  String numStr = modelPart.substring(PREFIX_HEAD.length());
                  int index = Config.parseInt(numStr, -1);
                  --index;
                  return index >= 0 && index < heads.length ? heads[index] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "head1", "head2", "head3"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderWither render = new RenderWither(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
