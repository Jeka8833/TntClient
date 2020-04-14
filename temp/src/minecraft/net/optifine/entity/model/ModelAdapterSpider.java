package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.monster.EntitySpider;

public class ModelAdapterSpider extends ModelAdapter {
   public ModelAdapterSpider() {
      super(EntitySpider.class, "spider", 1.0F);
   }

   protected ModelAdapterSpider(Class entityClass, String name, float shadowSize) {
      super(entityClass, name, shadowSize);
   }

   public ModelBase makeModel() {
      return new ModelSpider();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelSpider)) {
         return null;
      } else {
         ModelSpider modelSpider = (ModelSpider)model;
         if (modelPart.equals("head")) {
            return modelSpider.field_78209_a;
         } else if (modelPart.equals("neck")) {
            return modelSpider.field_78207_b;
         } else if (modelPart.equals("body")) {
            return modelSpider.field_78208_c;
         } else if (modelPart.equals("leg1")) {
            return modelSpider.field_78205_d;
         } else if (modelPart.equals("leg2")) {
            return modelSpider.field_78206_e;
         } else if (modelPart.equals("leg3")) {
            return modelSpider.field_78203_f;
         } else if (modelPart.equals("leg4")) {
            return modelSpider.field_78204_g;
         } else if (modelPart.equals("leg5")) {
            return modelSpider.field_78212_h;
         } else if (modelPart.equals("leg6")) {
            return modelSpider.field_78213_i;
         } else if (modelPart.equals("leg7")) {
            return modelSpider.field_78210_j;
         } else {
            return modelPart.equals("leg8") ? modelSpider.field_78211_k : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"head", "neck", "body", "leg1", "leg2", "leg3", "leg4", "leg5", "leg6", "leg7", "leg8"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderSpider render = new RenderSpider(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
