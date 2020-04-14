package net.optifine.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.optifine.reflect.Reflector;

public class ModelAdapterBook extends ModelAdapter {
   public ModelAdapterBook() {
      super(TileEntityEnchantmentTable.class, "book", 0.0F);
   }

   public ModelBase makeModel() {
      return new ModelBook();
   }

   public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
      if (!(model instanceof ModelBook)) {
         return null;
      } else {
         ModelBook modelBook = (ModelBook)model;
         if (modelPart.equals("cover_right")) {
            return modelBook.field_78102_a;
         } else if (modelPart.equals("cover_left")) {
            return modelBook.field_78100_b;
         } else if (modelPart.equals("pages_right")) {
            return modelBook.field_78101_c;
         } else if (modelPart.equals("pages_left")) {
            return modelBook.field_78098_d;
         } else if (modelPart.equals("flipping_page_right")) {
            return modelBook.field_78099_e;
         } else if (modelPart.equals("flipping_page_left")) {
            return modelBook.field_78096_f;
         } else {
            return modelPart.equals("book_spine") ? modelBook.field_78097_g : null;
         }
      }
   }

   public String[] getModelRendererNames() {
      return new String[]{"cover_right", "cover_left", "pages_right", "pages_left", "flipping_page_right", "flipping_page_left", "book_spine"};
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      TileEntityRendererDispatcher dispatcher = TileEntityRendererDispatcher.field_147556_a;
      TileEntitySpecialRenderer renderer = dispatcher.func_147546_a(TileEntityEnchantmentTable.class);
      if (!(renderer instanceof TileEntityEnchantmentTableRenderer)) {
         return null;
      } else {
         if (((TileEntitySpecialRenderer)renderer).getEntityClass() == null) {
            renderer = new TileEntityEnchantmentTableRenderer();
            ((TileEntitySpecialRenderer)renderer).func_147497_a(dispatcher);
         }

         if (!Reflector.TileEntityEnchantmentTableRenderer_modelBook.exists()) {
            Config.warn("Field not found: TileEntityEnchantmentTableRenderer.modelBook");
            return null;
         } else {
            Reflector.setFieldValue(renderer, Reflector.TileEntityEnchantmentTableRenderer_modelBook, modelBase);
            return (IEntityRenderer)renderer;
         }
      }
   }
}
