package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEnderman;

public class ModelAdapterEnderman extends ModelAdapterBiped {
   public ModelAdapterEnderman() {
      super(EntityEnderman.class, "enderman", 0.5F);
   }

   public ModelBase makeModel() {
      return new ModelEnderman(0.0F);
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      RenderEnderman render = new RenderEnderman(renderManager);
      render.field_77045_g = modelBase;
      render.field_76989_e = shadowSize;
      return render;
   }
}
