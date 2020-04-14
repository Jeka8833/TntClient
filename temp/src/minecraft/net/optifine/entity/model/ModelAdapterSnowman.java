package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.monster.EntitySnowman;

public class ModelAdapterSnowman extends ModelAdapter {
   public ModelAdapterSnowman() {
      super(EntitySnowman.class, "snow_golem", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelSnowMan();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelSnowMan)) {
         return null;
      } else {
         ModelSnowMan modelSnowman = (ModelSnowMan)model;
         if (modelPart.equals("body")) {
            return modelSnowman.field_78196_a;
         } else if (modelPart.equals("body_bottom")) {
            return modelSnowman.field_78194_b;
         } else if (modelPart.equals("head")) {
            return modelSnowman.field_78195_c;
         } else if (modelPart.equals("left_hand")) {
            return modelSnowman.field_78193_e;
         } else {
            return modelPart.equals("right_hand") ? modelSnowman.field_78192_d : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"body", "body_bottom", "head", "right_hand", "left_hand"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderSnowMan render = new RenderSnowMan(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
