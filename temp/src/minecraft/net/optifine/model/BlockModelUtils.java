package net.optifine.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.src.Config;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

public class BlockModelUtils {
   private static final float VERTEX_COORD_ACCURACY = 1.0E-6F;

   public static IBakedModel makeModelCube(String spriteName, int tintIndex) {
      TextureAtlasSprite sprite = Config.getMinecraft().func_147117_R().func_110572_b(spriteName);
      return makeModelCube(sprite, tintIndex);
   }

   public static IBakedModel makeModelCube(TextureAtlasSprite sprite, int tintIndex) {
      List generalQuads = new ArrayList();
      EnumFacing[] facings = EnumFacing.field_82609_l;
      List<List<BakedQuad>> faceQuads = new ArrayList();

      for(int i = 0; i < facings.length; ++i) {
         EnumFacing facing = facings[i];
         List quads = new ArrayList();
         quads.add(makeBakedQuad(facing, sprite, tintIndex));
         faceQuads.add(quads);
      }

      IBakedModel bakedModel = new SimpleBakedModel(generalQuads, faceQuads, true, true, sprite, ItemCameraTransforms.field_178357_a);
      return bakedModel;
   }

   public static IBakedModel joinModelsCube(IBakedModel modelBase, IBakedModel modelAdd) {
      List<BakedQuad> generalQuads = new ArrayList();
      generalQuads.addAll(modelBase.func_177550_a());
      generalQuads.addAll(modelAdd.func_177550_a());
      EnumFacing[] facings = EnumFacing.field_82609_l;
      List faceQuads = new ArrayList();

      for(int i = 0; i < facings.length; ++i) {
         EnumFacing facing = facings[i];
         List quads = new ArrayList();
         quads.addAll(modelBase.func_177551_a(facing));
         quads.addAll(modelAdd.func_177551_a(facing));
         faceQuads.add(quads);
      }

      boolean ao = modelBase.func_177555_b();
      boolean builtIn = modelBase.func_177553_d();
      TextureAtlasSprite sprite = modelBase.func_177554_e();
      ItemCameraTransforms transforms = modelBase.func_177552_f();
      IBakedModel bakedModel = new SimpleBakedModel(generalQuads, faceQuads, ao, builtIn, sprite, transforms);
      return bakedModel;
   }

   public static BakedQuad makeBakedQuad(EnumFacing facing, TextureAtlasSprite sprite, int tintIndex) {
      Vector3f posFrom = new Vector3f(0.0F, 0.0F, 0.0F);
      Vector3f posTo = new Vector3f(16.0F, 16.0F, 16.0F);
      BlockFaceUV uv = new BlockFaceUV(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0);
      BlockPartFace face = new BlockPartFace(facing, tintIndex, "#" + facing.func_176610_l(), uv);
      ModelRotation modelRotation = ModelRotation.X0_Y0;
      BlockPartRotation partRotation = null;
      boolean uvLocked = false;
      boolean shade = true;
      FaceBakery faceBakery = new FaceBakery();
      BakedQuad quad = faceBakery.func_178414_a(posFrom, posTo, face, sprite, facing, modelRotation, (BlockPartRotation)partRotation, uvLocked, shade);
      return quad;
   }

   public static IBakedModel makeModel(String modelName, String spriteOldName, String spriteNewName) {
      TextureMap textureMap = Config.getMinecraft().func_147117_R();
      TextureAtlasSprite spriteOld = textureMap.getSpriteSafe(spriteOldName);
      TextureAtlasSprite spriteNew = textureMap.getSpriteSafe(spriteNewName);
      return makeModel(modelName, spriteOld, spriteNew);
   }

   public static IBakedModel makeModel(String modelName, TextureAtlasSprite spriteOld, TextureAtlasSprite spriteNew) {
      if (spriteOld != null && spriteNew != null) {
         ModelManager modelManager = Config.getModelManager();
         if (modelManager == null) {
            return null;
         } else {
            ModelResourceLocation mrl = new ModelResourceLocation(modelName, "normal");
            IBakedModel model = modelManager.func_174953_a(mrl);
            if (model != null && model != modelManager.func_174951_a()) {
               IBakedModel modelNew = ModelUtils.duplicateModel(model);
               EnumFacing[] faces = EnumFacing.field_82609_l;

               for(int i = 0; i < faces.length; ++i) {
                  EnumFacing face = faces[i];
                  List<BakedQuad> quads = modelNew.func_177551_a(face);
                  replaceTexture(quads, spriteOld, spriteNew);
               }

               List<BakedQuad> quadsGeneral = modelNew.func_177550_a();
               replaceTexture(quadsGeneral, spriteOld, spriteNew);
               return modelNew;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private static void replaceTexture(List<BakedQuad> quads, TextureAtlasSprite spriteOld, TextureAtlasSprite spriteNew) {
      List<BakedQuad> quadsNew = new ArrayList();

      Object quad;
      for(Iterator it = quads.iterator(); it.hasNext(); quadsNew.add(quad)) {
         quad = (BakedQuad)it.next();
         if (((BakedQuad)quad).getSprite() == spriteOld) {
            quad = new BreakingFour((BakedQuad)quad, spriteNew);
         }
      }

      quads.clear();
      quads.addAll(quadsNew);
   }

   public static void snapVertexPosition(Vector3f pos) {
      pos.setX(snapVertexCoord(pos.getX()));
      pos.setY(snapVertexCoord(pos.getY()));
      pos.setZ(snapVertexCoord(pos.getZ()));
   }

   private static float snapVertexCoord(float x) {
      if (x > -1.0E-6F && x < 1.0E-6F) {
         return 0.0F;
      } else {
         return x > 0.999999F && x < 1.000001F ? 1.0F : x;
      }
   }

   public static AxisAlignedBB getOffsetBoundingBox(AxisAlignedBB aabb, Block.EnumOffsetType offsetType, BlockPos pos) {
      int x = pos.func_177958_n();
      int z = pos.func_177952_p();
      long k = (long)(x * 3129871) ^ (long)z * 116129781L;
      k = k * k * 42317861L + k * 11L;
      double dx = ((double)((float)(k >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
      double dz = ((double)((float)(k >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
      double dy = 0.0D;
      if (offsetType == Block.EnumOffsetType.XYZ) {
         dy = ((double)((float)(k >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
      }

      return aabb.func_72317_d(dx, dy, dz);
   }
}
