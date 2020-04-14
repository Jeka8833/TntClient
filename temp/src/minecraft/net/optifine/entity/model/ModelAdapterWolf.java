package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.optifine.reflect.Reflector;

public class ModelAdapterWolf extends ModelAdapter {
   public ModelAdapterWolf() {
      super(EntityWolf.class, "wolf", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelWolf();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelWolf)) {
         return null;
      } else {
         ModelWolf modelWolf = (ModelWolf)model;
         if (modelPart.equals("head")) {
            return modelWolf.field_78185_a;
         } else if (modelPart.equals("body")) {
            return modelWolf.field_78183_b;
         } else if (modelPart.equals("leg1")) {
            return modelWolf.field_78184_c;
         } else if (modelPart.equals("leg2")) {
            return modelWolf.field_78181_d;
         } else if (modelPart.equals("leg3")) {
            return modelWolf.field_78182_e;
         } else if (modelPart.equals("leg4")) {
            return modelWolf.field_78179_f;
         } else if (modelPart.equals("tail")) {
            return (ModelRenderer)Reflector.getFieldValue(modelWolf, Reflector.ModelWolf_tail);
         } else {
            return modelPart.equals("mane") ? (ModelRenderer)Reflector.getFieldValue(modelWolf, Reflector.ModelWolf_mane) : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4", "tail", "mane"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderWolf render = new RenderWolf(renderManager, modelBase, shadowSize);
      return render;
   }
}
