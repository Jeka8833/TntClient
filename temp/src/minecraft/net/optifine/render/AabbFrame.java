package net.optifine.render;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.util.AxisAlignedBB;

public class AabbFrame extends AxisAlignedBB {
   private int frameCount = -1;
   private boolean inFrustumFully = false;

   public AabbFrame(double x1, double y1, double z1, double x2, double y2, double z2) {
      super(x1, y1, z1, x2, y2, z2);
   }

   public boolean isBoundingBoxInFrustumFully(ICamera camera, int frameCount) {
      if (this.frameCount != frameCount) {
         this.inFrustumFully = camera instanceof Frustum ? ((Frustum)camera).isBoxInFrustumFully(this.field_72340_a, this.field_72338_b, this.field_72339_c, this.field_72336_d, this.field_72337_e, this.field_72334_f) : false;
         this.frameCount = frameCount;
      }

      return this.inFrustumFully;
   }
}
