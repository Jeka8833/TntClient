package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterMinecart extends ModelAdapter {
   public ModelAdapterMinecart() {
      super(EntityMinecart.class, "minecart", 0.5F);
   }

   protected ModelAdapterMinecart(Class entityClass, String name, float shadow) {
      super(entityClass, name, shadow);
   }

   public ModelBase makeModel() {
      return new ModelMinecart();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelMinecart)) {
         return null;
      } else {
         ModelMinecart modelMinecart = (ModelMinecart)model;
         if (modelPart.equals("bottom")) {
            return modelMinecart.field_78154_a[0];
         } else if (modelPart.equals("back")) {
            return modelMinecart.field_78154_a[1];
         } else if (modelPart.equals("front")) {
            return modelMinecart.field_78154_a[2];
         } else if (modelPart.equals("right")) {
            return modelMinecart.field_78154_a[3];
         } else if (modelPart.equals("left")) {
            return modelMinecart.field_78154_a[4];
         } else {
            return modelPart.equals("dirt") ? modelMinecart.field_78154_a[5] : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"bottom", "back", "front", "right", "left", "dirt"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderMinecart render = new RenderMinecart(renderManager);
      if (!Reflector.RenderMinecart_modelMinecart.exists()) {
         Config.warn("Field not found: RenderMinecart.modelMinecart");
         return null;
      } else {
         Reflector.setFieldValue(render, Reflector.RenderMinecart_modelMinecart, modelBase);
         render.field_76989_e = shadowSize;
         return render;
      }
   }
}
