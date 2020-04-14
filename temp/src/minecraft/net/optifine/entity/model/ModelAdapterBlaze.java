package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterBlaze extends ModelAdapter {
   public ModelAdapterBlaze() {
      super(EntityBlaze.class, "blaze", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelBlaze();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelBlaze)) {
         return null;
      } else {
         ModelBlaze modelBlaze = (ModelBlaze)model;
         if (modelPart.equals("head")) {
            return (ModelRenderer)Reflector.getFieldValue(modelBlaze, Reflector.ModelBlaze_blazeHead);
         } else {
            String PREFIX_STICK = "stick";
            if (modelPart.startsWith(PREFIX_STICK)) {
               ModelRenderer[] sticks = (ModelRenderer[])((ModelRenderer[])Reflector.getFieldValue(modelBlaze, Reflector.ModelBlaze_blazeSticks));
               if (sticks == null) {
                  return null;
               } else {
                  String numStr = modelPart.substring(PREFIX_STICK.length());
                  int index = Config.parseInt(numStr, -1);
                  --index;
                  return index >= 0 && index < sticks.length ? sticks[index] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "stick1", "stick2", "stick3", "stick4", "stick5", "stick6", "stick7", "stick8", "stick9", "stick10", "stick11", "stick12"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderBlaze render = new RenderBlaze(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
