package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.entity.passive.EntityVillager;

public class ModelAdapterVillager extends ModelAdapter {
   public ModelAdapterVillager() {
      super(EntityVillager.class, "villager", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelVillager(0.0F);
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelVillager)) {
         return null;
      } else {
         ModelVillager modelVillager = (ModelVillager)model;
         if (modelPart.equals("head")) {
            return modelVillager.field_78191_a;
         } else if (modelPart.equals("body")) {
            return modelVillager.field_78189_b;
         } else if (modelPart.equals("arms")) {
            return modelVillager.field_78190_c;
         } else if (modelPart.equals("left_leg")) {
            return modelVillager.field_78188_e;
         } else if (modelPart.equals("right_leg")) {
            return modelVillager.field_78187_d;
         } else {
            return modelPart.equals("nose") ? modelVillager.field_82898_f : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "arms", "right_leg", "left_leg", "nose"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderVillager render = new RenderVillager(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
