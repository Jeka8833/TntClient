package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.SVertexBuilder;
import org.lwjgl.opengl.GL11;

public class WorldVertexBufferUploader {
   public void func_181679_a(WorldRenderer p_181679_1_) {
      if (p_181679_1_.func_178989_h() > 0) {
         if (p_181679_1_.func_178979_i() == 7 && Config.isQuadsToTriangles()) {
            p_181679_1_.quadsToTriangles();
         }

         VertexFormat vertexformat = p_181679_1_.func_178973_g();
         int i = vertexformat.func_177338_f();
         ByteBuffer bytebuffer = p_181679_1_.func_178966_f();
         List<VertexFormatElement> list = vertexformat.func_177343_g();
         boolean forgePreDrawExists = Reflector.ForgeVertexFormatElementEnumUseage_preDraw.exists();
         boolean forgePostDrawExists = Reflector.ForgeVertexFormatElementEnumUseage_postDraw.exists();

         for(int j = 0; j < list.size(); ++j) {
            VertexFormatElement vertexformatelement = (VertexFormatElement)list.get(j);
            VertexFormatElement.EnumUsage vertexformatelement$enumusage = vertexformatelement.func_177375_c();
            if (forgePreDrawExists) {
               Reflector.callVoid(vertexformatelement$enumusage, Reflector.ForgeVertexFormatElementEnumUseage_preDraw, vertexformat, j, i, bytebuffer);
            } else {
               int k = vertexformatelement.func_177367_b().func_177397_c();
               int l = vertexformatelement.func_177369_e();
               bytebuffer.position(vertexformat.func_181720_d(j));
               switch(vertexformatelement$enumusage) {
               case POSITION:
                  GL11.glVertexPointer(vertexformatelement.func_177370_d(), k, i, bytebuffer);
                  GL11.glEnableClientState(32884);
                  break;
               case UV:
                  OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a + l);
                  GL11.glTexCoordPointer(vertexformatelement.func_177370_d(), k, i, bytebuffer);
                  GL11.glEnableClientState(32888);
                  OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
                  break;
               case COLOR:
                  GL11.glColorPointer(vertexformatelement.func_177370_d(), k, i, bytebuffer);
                  GL11.glEnableClientState(32886);
                  break;
               case NORMAL:
                  GL11.glNormalPointer(k, i, bytebuffer);
                  GL11.glEnableClientState(32885);
               }
            }
         }

         if (p_181679_1_.isMultiTexture()) {
            p_181679_1_.drawMultiTexture();
         } else if (Config.isShaders()) {
            SVertexBuilder.drawArrays(p_181679_1_.func_178979_i(), 0, p_181679_1_.func_178989_h(), p_181679_1_);
         } else {
            GL11.glDrawArrays(p_181679_1_.func_178979_i(), 0, p_181679_1_.func_178989_h());
         }

         int i1 = 0;

         for(int j1 = list.size(); i1 < j1; ++i1) {
            VertexFormatElement vertexformatelement1 = (VertexFormatElement)list.get(i1);
            VertexFormatElement.EnumUsage vertexformatelement$enumusage1 = vertexformatelement1.func_177375_c();
            if (forgePostDrawExists) {
               Reflector.callVoid(vertexformatelement$enumusage1, Reflector.ForgeVertexFormatElementEnumUseage_postDraw, vertexformat, i1, i, bytebuffer);
            } else {
               int k1 = vertexformatelement1.func_177369_e();
               switch(vertexformatelement$enumusage1) {
               case POSITION:
                  GL11.glDisableClientState(32884);
                  break;
               case UV:
                  OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a + k1);
                  GL11.glDisableClientState(32888);
                  OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
                  break;
               case COLOR:
                  GL11.glDisableClientState(32886);
                  GlStateManager.func_179117_G();
                  break;
               case NORMAL:
                  GL11.glDisableClientState(32885);
               }
            }
         }
      }

      p_181679_1_.func_178965_a();
   }
}
