package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.src.Config;

public class ModelAdapterArmorStand extends ModelAdapterBiped {
   public ModelAdapterArmorStand() {
      super(EntityArmorStand.class, "armor_stand", 0.0F);
   }

   public ModelBase makeModel() {
      return new ModelArmorStand();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelArmorStand)) {
         return null;
      } else {
         ModelArmorStand modelArmorStand = (ModelArmorStand)model;
         if (modelPart.equals("right")) {
            return modelArmorStand.field_178740_a;
         } else if (modelPart.equals("left")) {
            return modelArmorStand.field_178738_b;
         } else if (modelPart.equals("waist")) {
            return modelArmorStand.field_178739_c;
         } else {
            return modelPart.equals("base") ? modelArmorStand.field_178737_d : super.getModelRenderer(modelArmorStand, modelPart);
         }
      }
   }

   public String[] getModelRendererNames() {
      String[] names = super.getModelRendererNames();
      names = (String[])((String[])Config.addObjectsToArray(names, new String[]{"right", "left", "waist", "base"}));
      return names;
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      ArmorStandRenderer render = new ArmorStandRenderer(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
