package net.optifine.entity.model;

import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.src.Config;

public class ModelAdapterSheepWool extends ModelAdapterQuadruped {
   public ModelAdapterSheepWool() {
      super(EntitySheep.class, "sheep_wool", 0.7F);
   }

   public ModelBase makeModel() {
      return new ModelSheep1();
   }

   public IEntityRenderer makeEntityRender(ModelBase modelBase, float shadowSize) {
      RenderManager renderManager = Minecraft.func_71410_x().func_175598_ae();
      Render render = (Render)renderManager.getEntityRenderMap().get(EntitySheep.class);
      if (!(render instanceof RenderSheep)) {
         Config.warn("Not a RenderSheep: " + render);
         return null;
      } else {
         if (((Render)render).getEntityClass() == null) {
            render = new RenderSheep(renderManager, new ModelSheep2(), 0.7F);
         }

         RenderSheep renderSheep = (RenderSheep)render;
         List<LayerRenderer<EntitySheep>> list = renderSheep.getLayerRenderers();
         Iterator it = list.iterator();

         while(it.hasNext()) {
            LayerRenderer layerRenderer = (LayerRenderer)it.next();
            if (layerRenderer instanceof LayerSheepWool) {
               it.remove();
            }
         }

         LayerSheepWool layer = new LayerSheepWool(renderSheep);
         layer.field_177164_c = (ModelSheep1)modelBase;
         renderSheep.func_177094_a(layer);
         return renderSheep;
      }
   }
}
