package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterGhast extends ModelAdapter {
   public ModelAdapterGhast() {
      super(EntityGhast.class, "ghast", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelGhast();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelGhast)) {
         return null;
      } else {
         ModelGhast modelGhast = (ModelGhast)model;
         if (modelPart.equals("body")) {
            return (ModelRenderer)Reflector.getFieldValue(modelGhast, Reflector.ModelGhast_body);
         } else {
            String PREFIX_TENTACLE = "tentacle";
            if (modelPart.startsWith(PREFIX_TENTACLE)) {
               ModelRenderer[] tentacles = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelGhast, Reflector.ModelGhast_tentacles));
               if (tentacles == null) {
                  return null;
               } else {
                  String numStr = modelPart.substring(PREFIX_TENTACLE.length());
                  int index = Config.parseInt(numStr, -1);
                  --index;
                  return index >= 0 && index < tentacles.length ? tentacles[index] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8", "tentacle9"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderGhast render = new RenderGhast(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
