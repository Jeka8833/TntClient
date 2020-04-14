package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;

public class ModelAdapterCreeper extends ModelAdapter {
   public ModelAdapterCreeper() {
      super(EntityCreeper.class, "creeper", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelCreeper();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelCreeper)) {
         return null;
      } else {
         ModelCreeper modelCreeper = (ModelCreeper)model;
         if (modelPart.equals("head")) {
            return modelCreeper.field_78135_a;
         } else if (modelPart.equals("armor")) {
            return modelCreeper.field_78133_b;
         } else if (modelPart.equals("body")) {
            return modelCreeper.field_78134_c;
         } else if (modelPart.equals("leg1")) {
            return modelCreeper.field_78131_d;
         } else if (modelPart.equals("leg2")) {
            return modelCreeper.field_78132_e;
         } else if (modelPart.equals("leg3")) {
            return modelCreeper.field_78129_f;
         } else {
            return modelPart.equals("leg4") ? modelCreeper.field_78130_g : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "armor", "body", "leg1", "leg2", "leg3", "leg4"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderCreeper render = new RenderCreeper(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
