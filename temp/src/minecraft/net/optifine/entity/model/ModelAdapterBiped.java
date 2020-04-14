package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public abstract class ModelAdapterBiped extends ModelAdapter {
   public ModelAdapterBiped(Class entityClass, String name, float shadowSize) {
      super(entityClass, name, shadowSize);
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelBiped)) {
         return null;
      } else {
         ModelBiped modelBiped = (ModelBiped)model;
         if (modelPart.equals("head")) {
            return modelBiped.field_78116_c;
         } else if (modelPart.equals("headwear")) {
            return modelBiped.field_178720_f;
         } else if (modelPart.equals("body")) {
            return modelBiped.field_78115_e;
         } else if (modelPart.equals("left_arm")) {
            return modelBiped.field_178724_i;
         } else if (modelPart.equals("right_arm")) {
            return modelBiped.field_178723_h;
         } else if (modelPart.equals("left_leg")) {
            return modelBiped.field_178722_k;
         } else {
            return modelPart.equals("right_leg") ? modelBiped.field_178721_j : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "headwear", "body", "left_arm", "right_arm", "left_leg", "right_leg"};
   }
}
