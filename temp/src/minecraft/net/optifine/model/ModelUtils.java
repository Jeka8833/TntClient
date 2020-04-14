package net.optifine.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.src.Config;
import net.minecraft.util.EnumFacing;

public class ModelUtils {
   public static void dbgModel(IBakedModel model) {
      if (model != null) {
         Config.dbg("Model: " + model + ", ao: " + model.func_177555_b() + ", gui3d: " + model.func_177556_c() + ", builtIn: " + model.func_177553_d() + ", particle: " + model.func_177554_e());
         EnumFacing[] faces = EnumFacing.field_82609_l;

         for(int i = 0; i < faces.length; ++i) {
            EnumFacing face = faces[i];
            List faceQuads = model.func_177551_a(face);
            dbgQuads(face.func_176610_l(), faceQuads, "  ");
         }

         List generalQuads = model.func_177550_a();
         dbgQuads("General", generalQuads, "  ");
      }
   }

   private static void dbgQuads(String name, List quads, String prefix) {
      Iterator it = quads.iterator();

      while(it.hasNext()) {
         BakedQuad quad = (BakedQuad)it.next();
         dbgQuad(name, quad, prefix);
      }

   }

   public static void dbgQuad(String name, BakedQuad quad, String prefix) {
      Config.dbg(prefix + "Quad: " + quad.getClass().getName() + ", type: " + name + ", face: " + quad.func_178210_d() + ", tint: " + quad.func_178211_c() + ", sprite: " + quad.getSprite());
      dbgVertexData(quad.func_178209_a(), "  " + prefix);
   }

   public static void dbgVertexData(int[] vd, String prefix) {
      int step = vd.length / 4;
      Config.dbg(prefix + "Length: " + vd.length + ", step: " + step);

      for(int i = 0; i < 4; ++i) {
         int pos = i * step;
         float x = Float.intBitsToFloat(vd[pos + 0]);
         float y = Float.intBitsToFloat(vd[pos + 1]);
         float z = Float.intBitsToFloat(vd[pos + 2]);
         int col = vd[pos + 3];
         float u = Float.intBitsToFloat(vd[pos + 4]);
         float v = Float.intBitsToFloat(vd[pos + 5]);
         Config.dbg(prefix + i + " xyz: " + x + "," + y + "," + z + " col: " + col + " u,v: " + u + "," + v);
      }

   }

   public static IBakedModel duplicateModel(IBakedModel model) {
      List generalQuads2 = duplicateQuadList(model.func_177550_a());
      EnumFacing[] faces = EnumFacing.field_82609_l;
      List faceQuads2 = new ArrayList();

      for(int i = 0; i < faces.length; ++i) {
         EnumFacing face = faces[i];
         List quads = model.func_177551_a(face);
         List quads2 = duplicateQuadList(quads);
         faceQuads2.add(quads2);
      }

      SimpleBakedModel model2 = new SimpleBakedModel(generalQuads2, faceQuads2, model.func_177555_b(), model.func_177556_c(), model.func_177554_e(), model.func_177552_f());
      return model2;
   }

   public static List duplicateQuadList(List list) {
      List list2 = new ArrayList();
      Iterator it = list.iterator();

      while(it.hasNext()) {
         BakedQuad quad = (BakedQuad)it.next();
         BakedQuad quad2 = duplicateQuad(quad);
         list2.add(quad2);
      }

      return list2;
   }

   public static BakedQuad duplicateQuad(BakedQuad quad) {
      BakedQuad quad2 = new BakedQuad((int[])quad.func_178209_a().clone(), quad.func_178211_c(), quad.func_178210_d(), quad.getSprite());
      return quad2;
   }
}
