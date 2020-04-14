package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnderChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.optifine.reflect.Reflector;

public class ModelAdapterEnderChest extends ModelAdapter {
   public ModelAdapterEnderChest() {
      super(TileEntityEnderChest.class, "ender_chest", 0.0F);
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
      TileEntitySpecialRenderer renderer = dispatcher.func_147546_a(TileEntityEnderChest.class);
      if (!(renderer instanceof TileEntityEnderChestRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)renderer).getEntityClass() == null) {
            renderer = new TileEntityEnderChestRenderer();
            ((TileEntitySpecialRenderer)renderer).func_147497_a(dispatcher);
         }

         if (!Reflector.TileEntityEnderChestRenderer_modelChest.exists()) {
            Config.warn("Field not found: TileEntityEnderChestRenderer.modelChest");
            return null;
         } else {
            Reflector.setFieldValue(renderer, Reflector.TileEntityEnderChestRenderer_modelChest, modelBase);
            return (IEntityRenderer)renderer;
         }
      }
   }
}
