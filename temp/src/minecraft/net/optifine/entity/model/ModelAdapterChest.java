package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityChest;
import net.optifine.reflect.Reflector;

public class ModelAdapterChest extends ModelAdapter {
   public ModelAdapterChest() {
      super(TileEntityChest.class, "chest", 0.0F);
   }

   public ModelBase makeModel() {
      return new ModelChest();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelChest)) {
         return null;
      } else {
         ModelChest modelChest = (ModelChest)model;
         if (modelPart.equals("lid")) {
            return modelChest.field_78234_a;
         } else if (modelPart.equals("base")) {
            return modelChest.field_78232_b;
         } else {
            return modelPart.equals("knob") ? modelChest.field_78233_c : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"lid", "base", "knob"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      TileEntityRendererDispatcher dispatcher = TileEntityRendererDispatcher.field_147556_a;
      TileEntitySpecialRenderer renderer = dispatcher.func_147546_a(TileEntityChest.class);
      if (!(renderer instanceof TileEntityChestRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)renderer).getEntityClass() == null) {
            renderer = new TileEntityChestRenderer();
            ((TileEntitySpecialRenderer)renderer).func_147497_a(dispatcher);
         }

         if (!Reflector.TileEntityChestRenderer_simpleChest.exists()) {
            Config.warn("Field not found: TileEntityChestRenderer.simpleChest");
            return null;
         } else {
            Reflector.setFieldValue(renderer, Reflector.TileEntityChestRenderer_simpleChest, modelBase);
            return (IEntityRenderer)renderer;
         }
      }
   }
}
