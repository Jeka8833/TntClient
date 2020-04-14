package net.optifine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.src.Config;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.optifine.model.ModelUtils;

public class SmartLeaves {
   private static IBakedModel modelLeavesCullAcacia = null;
   private static IBakedModel modelLeavesCullBirch = null;
   private static IBakedModel modelLeavesCullDarkOak = null;
   private static IBakedModel modelLeavesCullJungle = null;
   private static IBakedModel modelLeavesCullOak = null;
   private static IBakedModel modelLeavesCullSpruce = null;
   private static List generalQuadsCullAcacia = null;
   private static List generalQuadsCullBirch = null;
   private static List generalQuadsCullDarkOak = null;
   private static List generalQuadsCullJungle = null;
   private static List generalQuadsCullOak = null;
   private static List generalQuadsCullSpruce = null;
   private static IBakedModel modelLeavesDoubleAcacia = null;
   private static IBakedModel modelLeavesDoubleBirch = null;
   private static IBakedModel modelLeavesDoubleDarkOak = null;
   private static IBakedModel modelLeavesDoubleJungle = null;
   private static IBakedModel modelLeavesDoubleOak = null;
   private static IBakedModel modelLeavesDoubleSpruce = null;

   public static IBakedModel getLeavesModel(IBakedModel model, IBlockState stateIn) {
      if (!Config.isTreesSmart()) {
         return model;
      } else {
         List generalQuads = model.func_177550_a();
         if (generalQuads == generalQuadsCullAcacia) {
            return modelLeavesDoubleAcacia;
         } else if (generalQuads == generalQuadsCullBirch) {
            return modelLeavesDoubleBirch;
         } else if (generalQuads == generalQuadsCullDarkOak) {
            return modelLeavesDoubleDarkOak;
         } else if (generalQuads == generalQuadsCullJungle) {
            return modelLeavesDoubleJungle;
         } else if (generalQuads == generalQuadsCullOak) {
            return modelLeavesDoubleOak;
         } else {
            return generalQuads == generalQuadsCullSpruce ? modelLeavesDoubleSpruce : model;
         }
      }
   }

   public static boolean isSameLeaves(IBlockState state1, IBlockState state2) {
      if (state1 == state2) {
         return true;
      } else {
         Block block1 = state1.func_177230_c();
         Block block2 = state2.func_177230_c();
         if (block1 != block2) {
            return false;
         } else if (block1 instanceof BlockOldLeaf) {
            return ((BlockPlanks.EnumType)state1.func_177229_b(BlockOldLeaf.field_176239_P)).equals(state2.func_177229_b(BlockOldLeaf.field_176239_P));
         } else {
            return block1 instanceof BlockNewLeaf ? ((BlockPlanks.EnumType)state1.func_177229_b(BlockNewLeaf.field_176240_P)).equals(state2.func_177229_b(BlockNewLeaf.field_176240_P)) : false;
         }
      }
   }

   public static void updateLeavesModels() {
      List updatedTypes = new ArrayList();
      modelLeavesCullAcacia = getModelCull("acacia", updatedTypes);
      modelLeavesCullBirch = getModelCull("birch", updatedTypes);
      modelLeavesCullDarkOak = getModelCull("dark_oak", updatedTypes);
      modelLeavesCullJungle = getModelCull("jungle", updatedTypes);
      modelLeavesCullOak = getModelCull("oak", updatedTypes);
      modelLeavesCullSpruce = getModelCull("spruce", updatedTypes);
      generalQuadsCullAcacia = getGeneralQuadsSafe(modelLeavesCullAcacia);
      generalQuadsCullBirch = getGeneralQuadsSafe(modelLeavesCullBirch);
      generalQuadsCullDarkOak = getGeneralQuadsSafe(modelLeavesCullDarkOak);
      generalQuadsCullJungle = getGeneralQuadsSafe(modelLeavesCullJungle);
      generalQuadsCullOak = getGeneralQuadsSafe(modelLeavesCullOak);
      generalQuadsCullSpruce = getGeneralQuadsSafe(modelLeavesCullSpruce);
      modelLeavesDoubleAcacia = getModelDoubleFace(modelLeavesCullAcacia);
      modelLeavesDoubleBirch = getModelDoubleFace(modelLeavesCullBirch);
      modelLeavesDoubleDarkOak = getModelDoubleFace(modelLeavesCullDarkOak);
      modelLeavesDoubleJungle = getModelDoubleFace(modelLeavesCullJungle);
      modelLeavesDoubleOak = getModelDoubleFace(modelLeavesCullOak);
      modelLeavesDoubleSpruce = getModelDoubleFace(modelLeavesCullSpruce);
      if (updatedTypes.size() > 0) {
         Config.dbg("Enable face culling: " + Config.arrayToString(updatedTypes.toArray()));
      }

   }

   private static List getGeneralQuadsSafe(IBakedModel model) {
      return model == null ? null : model.func_177550_a();
   }

   static IBakedModel getModelCull(String type, List updatedTypes) {
      ModelManager modelManager = Config.getModelManager();
      if (modelManager == null) {
         return null;
      } else {
         ResourceLocation locState = new ResourceLocation("blockstates/" + type + "_leaves.json");
         if (Config.getDefiningResourcePack(locState) != Config.getDefaultResourcePack()) {
            return null;
         } else {
            ResourceLocation locModel = new ResourceLocation("models/block/" + type + "_leaves.json");
            if (Config.getDefiningResourcePack(locModel) != Config.getDefaultResourcePack()) {
               return null;
            } else {
               ModelResourceLocation mrl = new ModelResourceLocation(type + "_leaves", "normal");
               IBakedModel model = modelManager.func_174953_a(mrl);
               if (model != null && model != modelManager.func_174951_a()) {
                  List listGeneral = model.func_177550_a();
                  if (listGeneral.size() == 0) {
                     return model;
                  } else if (listGeneral.size() != 6) {
                     return null;
                  } else {
                     Iterator it = listGeneral.iterator();

                     while(it.hasNext()) {
                        BakedQuad quad = (BakedQuad)it.next();
                        List listFace = model.func_177551_a(quad.func_178210_d());
                        if (listFace.size() > 0) {
                           return null;
                        }

                        listFace.add(quad);
                     }

                     listGeneral.clear();
                     updatedTypes.add(type + "_leaves");
                     return model;
                  }
               } else {
                  return null;
               }
            }
         }
      }
   }

   private static IBakedModel getModelDoubleFace(IBakedModel model) {
      if (model == null) {
         return null;
      } else if (model.func_177550_a().size() > 0) {
         Config.warn("SmartLeaves: Model is not cube, general quads: " + model.func_177550_a().size() + ", model: " + model);
         return model;
      } else {
         EnumFacing[] faces = EnumFacing.field_82609_l;

         for(int i = 0; i < faces.length; ++i) {
            EnumFacing face = faces[i];
            List<BakedQuad> quads = model.func_177551_a(face);
            if (quads.size() != 1) {
               Config.warn("SmartLeaves: Model is not cube, side: " + face + ", quads: " + quads.size() + ", model: " + model);
               return model;
            }
         }

         IBakedModel model2 = ModelUtils.duplicateModel(model);
         List[] faceQuads = new List[faces.length];

         for(int i = 0; i < faces.length; ++i) {
            EnumFacing face = faces[i];
            List<BakedQuad> quads = model2.func_177551_a(face);
            BakedQuad quad = (BakedQuad)quads.get(0);
            BakedQuad quad2 = new BakedQuad((int[])quad.func_178209_a().clone(), quad.func_178211_c(), quad.func_178210_d(), quad.getSprite());
            int[] vd = quad2.func_178209_a();
            int[] vd2 = (int[])vd.clone();
            int step = vd.length / 4;
            System.arraycopy(vd, 0 * step, vd2, 3 * step, step);
            System.arraycopy(vd, 1 * step, vd2, 2 * step, step);
            System.arraycopy(vd, 2 * step, vd2, 1 * step, step);
            System.arraycopy(vd, 3 * step, vd2, 0 * step, step);
            System.arraycopy(vd2, 0, vd, 0, vd2.length);
            quads.add(quad2);
         }

         return model2;
      }
   }
}
