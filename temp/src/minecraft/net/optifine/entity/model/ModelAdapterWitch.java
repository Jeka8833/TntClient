package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.entity.monster.EntityWitch;
import net.optifine.reflect.Reflector;

public class ModelAdapterWitch extends ModelAdapter {
   public ModelAdapterWitch() {
      super(EntityWitch.class, "witch", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelWitch(0.0F);
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelWitch)) {
         return null;
      } else {
         ModelWitch modelWitch = (ModelWitch)model;
         if (modelPart.equals("mole")) {
            return (ModelRenderer)Reflector.getFieldValue(modelWitch, Reflector.ModelWitch_mole);
         } else if (modelPart.equals("hat")) {
            return (ModelRenderer)Reflector.getFieldValue(modelWitch, Reflector.ModelWitch_hat);
         } else if (modelPart.equals("head")) {
            return modelWitch.field_78191_a;
         } else if (modelPart.equals("body")) {
            return modelWitch.field_78189_b;
         } else if (modelPart.equals("arms")) {
            return modelWitch.field_78190_c;
         } else if (modelPart.equals("left_leg")) {
            return modelWitch.field_78188_e;
         } else if (modelPart.equals("right_leg")) {
            return modelWitch.field_78187_d;
         } else {
            return modelPart.equals("nose") ? modelWitch.field_82898_f : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"mole", "head", "body", "arms", "right_leg", "left_leg", "nose"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderWitch render = new RenderWitch(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
