package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityIronGolem;

public class ModelAdapterIronGolem extends ModelAdapter {
   public ModelAdapterIronGolem() {
      super(EntityIronGolem.class, "iron_golem", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelIronGolem();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelIronGolem)) {
         return null;
      } else {
         ModelIronGolem modelIronGolem = (ModelIronGolem)model;
         if (modelPart.equals("head")) {
            return modelIronGolem.field_78178_a;
         } else if (modelPart.equals("body")) {
            return modelIronGolem.field_78176_b;
         } else if (modelPart.equals("left_arm")) {
            return modelIronGolem.field_78174_d;
         } else if (modelPart.equals("right_arm")) {
            return modelIronGolem.field_78177_c;
         } else if (modelPart.equals("left_leg")) {
            return modelIronGolem.field_78175_e;
         } else {
            return modelPart.equals("right_leg") ? modelIronGolem.field_78173_f : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_arm", "left_arm", "left_leg", "right_leg"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderIronGolem render = new RenderIronGolem(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
