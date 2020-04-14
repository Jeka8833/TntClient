package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterBoat extends ModelAdapter {
   public ModelAdapterBoat() {
      super(EntityBoat.class, "boat", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelBoat();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelBoat)) {
         return null;
      } else {
         ModelBoat modelBoat = (ModelBoat)model;
         if (modelPart.equals("bottom")) {
            return modelBoat.field_78103_a[0];
         } else if (modelPart.equals("back")) {
            return modelBoat.field_78103_a[1];
         } else if (modelPart.equals("front")) {
            return modelBoat.field_78103_a[2];
         } else if (modelPart.equals("right")) {
            return modelBoat.field_78103_a[3];
         } else {
            return modelPart.equals("left") ? modelBoat.field_78103_a[4] : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"bottom", "back", "front", "right", "left"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderBoat render = new RenderBoat(renderManager);
      if (!Reflector.RenderBoat_modelBoat.exists()) {
         Config.warn("Field not found: RenderBoat.modelBoat");
         return null;
      } else {
         Reflector.setFieldValue(render, Reflector.RenderBoat_modelBoat, modelBase);
         render.field_76989_e = shadowSize;
         return render;
      }
   }
}
