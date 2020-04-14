package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityChicken;

public class ModelAdapterChicken extends ModelAdapter {
   public ModelAdapterChicken() {
      super(EntityChicken.class, "chicken", 0.3F);
   }

   public ModelBase makeModel() {
      return new ModelChicken();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelChicken)) {
         return null;
      } else {
         ModelChicken modelChicken = (ModelChicken)model;
         if (modelPart.equals("head")) {
            return modelChicken.field_78142_a;
         } else if (modelPart.equals("body")) {
            return modelChicken.field_78140_b;
         } else if (modelPart.equals("right_leg")) {
            return modelChicken.field_78141_c;
         } else if (modelPart.equals("left_leg")) {
            return modelChicken.field_78138_d;
         } else if (modelPart.equals("right_wing")) {
            return modelChicken.field_78139_e;
         } else if (modelPart.equals("left_wing")) {
            return modelChicken.field_78136_f;
         } else if (modelPart.equals("bill")) {
            return modelChicken.field_78137_g;
         } else {
            return modelPart.equals("chin") ? modelChicken.field_78143_h : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_leg", "left_leg", "right_wing", "left_wing", "bill", "chin"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderChicken render = new RenderChicken(renderManager, modelBase, shadowSize);
      return render;
   }
}
