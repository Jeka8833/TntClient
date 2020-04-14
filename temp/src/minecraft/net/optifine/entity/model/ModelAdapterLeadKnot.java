package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterLeadKnot extends ModelAdapter {
   public ModelAdapterLeadKnot() {
      super(EntityLeashKnot.class, "lead_knot", 0.0F);
   }

   public ModelBase makeModel() {
      return new ModelLeashKnot();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelLeashKnot)) {
         return null;
      } else {
         ModelLeashKnot modelLeashKnot = (ModelLeashKnot)model;
         return modelPart.equals("knot") ? modelLeashKnot.field_110723_a : null;
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"knot"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderLeashKnot render = new RenderLeashKnot(renderManager);
      if (!Reflector.RenderLeashKnot_leashKnotModel.exists()) {
         Config.warn("Field not found: RenderLeashKnot.leashKnotModel");
         return null;
      } else {
         Reflector.setFieldValue(render, Reflector.RenderLeashKnot_leashKnotModel, modelBase);
         render.field_76989_e = shadowSize;
         return render;
      }
   }
}
