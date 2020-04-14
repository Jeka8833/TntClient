package net.optifine.entity.model.anim;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.expr.IExpression;

public class ModelResolver implements IModelResolver {
   private ModelAdapter modelAdapter;
   private ModelBase model;
   private CustomModelRenderer[] customModelRenderers;
   private ModelRenderer thisModelRenderer;
   private ModelRenderer partModelRenderer;
   private IRenderResolver renderResolver;

   public ModelResolver(ModelAdapter modelAdapter, ModelBase model, CustomModelRenderer[] customModelRenderers) {
      this.modelAdapter = modelAdapter;
      this.model = model;
      this.customModelRenderers = customModelRenderers;
      Class entityClass = modelAdapter.getEntityClass();
      if (TileEntity.class.isAssignableFrom(entityClass)) {
         this.renderResolver = new RenderResolverTileEntity();
      } else {
         this.renderResolver = new RenderResolverEntity();
      }

   }

   public IExpression getExpression(String name) {
      IExpression mv = this.getModelVariable(name);
      if (mv != null) {
         return mv;
      } else {
         IExpression param = this.renderResolver.getParameter(name);
         return param != null ? param : null;
      }
   }

   public ModelRenderer getModelRenderer(String name) {
      if (name == null) {
         return null;
      } else {
         ModelRenderer mrChild;
         if (name.indexOf(":") >= 0) {
            String[] parts = Config.tokenize(name, ":");
            ModelRenderer mr = this.getModelRenderer(parts[0]);

            for(int i = 1; i < parts.length; ++i) {
               String part = parts[i];
               mrChild = mr.getChildDeep(part);
               if (mrChild == null) {
                  return null;
               }

               mr = mrChild;
            }

            return mr;
         } else if (this.thisModelRenderer != null && name.equals("this")) {
            return this.thisModelRenderer;
         } else if (this.partModelRenderer != null && name.equals("part")) {
            return this.partModelRenderer;
         } else {
            ModelRenderer mrPart = this.modelAdapter.getModelRenderer(this.model, name);
            if (mrPart != null) {
               return mrPart;
            } else {
               for(int i = 0; i < this.customModelRenderers.length; ++i) {
                  CustomModelRenderer cmr = this.customModelRenderers[i];
                  ModelRenderer mr = cmr.getModelRenderer();
                  if (name.equals(mr.getId())) {
                     return mr;
                  }

                  mrChild = mr.getChildDeep(name);
                  if (mrChild != null) {
                     return mrChild;
                  }
               }

               return null;
            }
         }
      }
   }

   public ModelVariableFloat getModelVariable(String name) {
      String[] parts = Config.tokenize(name, ".");
      if (parts.length != 2) {
         return null;
      } else {
         String modelName = parts[0];
         String varName = parts[1];
         ModelRenderer mr = this.getModelRenderer(modelName);
         if (mr == null) {
            return null;
         } else {
            ModelVariableType varType = ModelVariableType.parse(varName);
            return varType == null ? null : new ModelVariableFloat(name, mr, varType);
         }
      }
   }

   public void setPartModelRenderer(ModelRenderer partModelRenderer) {
      this.partModelRenderer = partModelRenderer;
   }

   public void setThisModelRenderer(ModelRenderer thisModelRenderer) {
      this.thisModelRenderer = thisModelRenderer;
   }
}
