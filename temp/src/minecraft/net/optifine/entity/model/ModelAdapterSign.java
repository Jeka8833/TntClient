package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntitySign;
import net.optifine.reflect.Reflector;

public class ModelAdapterSign extends ModelAdapter {
   public ModelAdapterSign() {
      super(TileEntitySign.class, "sign", 0.0F);
   }

   public ModelBase makeModel() {
      return new ModelSign();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelSign)) {
         return null;
      } else {
         ModelSign modelSign = (ModelSign)model;
         if (modelPart.equals("board")) {
            return modelSign.field_78166_a;
         } else {
            return modelPart.equals("stick") ? modelSign.field_78165_b : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"board", "stick"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      TileEntityRendererDispatcher dispatcher = TileEntityRendererDispatcher.field_147556_a;
      TileEntitySpecialRenderer renderer = dispatcher.func_147546_a(TileEntitySign.class);
      if (!(renderer instanceof TileEntitySignRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)renderer).getEntityClass() == null) {
            renderer = new TileEntitySignRenderer();
            ((TileEntitySpecialRenderer)renderer).func_147497_a(dispatcher);
         }

         if (!Reflector.TileEntitySignRenderer_model.exists()) {
            Config.warn("Field not found: TileEntitySignRenderer.model");
            return null;
         } else {
            Reflector.setFieldValue(renderer, Reflector.TileEntitySignRenderer_model, modelBase);
            return (IEntityRenderer)renderer;
         }
      }
   }
}
