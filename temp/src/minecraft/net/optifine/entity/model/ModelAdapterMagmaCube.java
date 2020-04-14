package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterMagmaCube extends ModelAdapter {
   public ModelAdapterMagmaCube() {
      super(EntityMagmaCube.class, "magma_cube", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelMagmaCube();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelMagmaCube)) {
         return null;
      } else {
         ModelMagmaCube modelMagmaCube = (ModelMagmaCube)model;
         if (modelPart.equals("core")) {
            return (ModelRenderer)Reflector.getFieldValue(modelMagmaCube, Reflector.ModelMagmaCube_core);
         } else {
            String PREFIX_SEGMENTS = "segment";
            if (modelPart.startsWith(PREFIX_SEGMENTS)) {
               ModelRenderer[] segments = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelMagmaCube, Reflector.ModelMagmaCube_segments));
               if (segments == null) {
                  return null;
               } else {
                  String numStr = modelPart.substring(PREFIX_SEGMENTS.length());
                  int index = Config.parseInt(numStr, -1);
                  --index;
                  return index >= 0 && index < segments.length ? segments[index] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"core", "segment1", "segment2", "segment3", "segment4", "segment5", "segment6", "segment7", "segment8"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderMagmaCube render = new RenderMagmaCube(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
