package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;

public abstract class ModelAdapterQuadruped extends ModelAdapter {
   public ModelAdapterQuadruped(Class entityClass, String name, float shadowSize) {
      super(entityClass, name, shadowSize);
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelQuadruped)) {
         return null;
      } else {
         ModelQuadruped modelQuadruped = (ModelQuadruped)model;
         if (modelPart.equals("head")) {
            return modelQuadruped.field_78150_a;
         } else if (modelPart.equals("body")) {
            return modelQuadruped.field_78148_b;
         } else if (modelPart.equals("leg1")) {
            return modelQuadruped.field_78149_c;
         } else if (modelPart.equals("leg2")) {
            return modelQuadruped.field_78146_d;
         } else if (modelPart.equals("leg3")) {
            return modelQuadruped.field_78147_e;
         } else {
            return modelPart.equals("leg4") ? modelQuadruped.field_78144_f : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4"};
   }
}
