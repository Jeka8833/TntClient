package net.optifine.player;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;

public class PlayerItemModel {
   private Dimension textureSize = null;
   private boolean usePlayerTexture = false;
   private PlayerItemRenderer[] modelRenderers = new PlayerItemRenderer[0];
   private ResourceLocation textureLocation = null;
   private BufferedImage textureImage = null;
   private DynamicTexture texture = null;
   private ResourceLocation locationMissing = new ResourceLocation("textures/blocks/wool_colored_red.png");
   public static final int ATTACH_BODY = 0;
   public static final int ATTACH_HEAD = 1;
   public static final int ATTACH_LEFT_ARM = 2;
   public static final int ATTACH_RIGHT_ARM = 3;
   public static final int ATTACH_LEFT_LEG = 4;
   public static final int ATTACH_RIGHT_LEG = 5;
   public static final int ATTACH_CAPE = 6;

   public PlayerItemModel(Dimension textureSize, boolean usePlayerTexture, PlayerItemRenderer[] modelRenderers) {
      this.textureSize = textureSize;
      this.usePlayerTexture = usePlayerTexture;
      this.modelRenderers = modelRenderers;
   }

   public void render(ModelBiped modelBiped, AbstractClientPlayer player, float scale, float partialTicks) {
      TextureManager textureManager = Config.getTextureManager();
      if (this.usePlayerTexture) {
         textureManager.func_110577_a(player.func_110306_p());
      } else if (this.textureLocation != null) {
         if (this.texture == null && this.textureImage != null) {
            this.texture = new DynamicTexture(this.textureImage);
            Minecraft.func_71410_x().func_110434_K().func_110579_a(this.textureLocation, this.texture);
         }

         textureManager.func_110577_a(this.textureLocation);
      } else {
         textureManager.func_110577_a(this.locationMissing);
      }

      for(int i = 0; i < this.modelRenderers.length; ++i) {
         PlayerItemRenderer pir = this.modelRenderers[i];
         GlStateManager.func_179094_E();
         if (player.func_70093_af()) {
            GlStateManager.func_179109_b(0.0F, 0.2F, 0.0F);
         }

         pir.render(modelBiped, scale);
         GlStateManager.func_179121_F();
      }

   }

   public static ModelRenderer getAttachModel(ModelBiped modelBiped, int attachTo) {
      switch(attachTo) {
      case 0:
         return modelBiped.field_78115_e;
      case 1:
         return modelBiped.field_78116_c;
      case 2:
         return modelBiped.field_178724_i;
      case 3:
         return modelBiped.field_178723_h;
      case 4:
         return modelBiped.field_178722_k;
      case 5:
         return modelBiped.field_178721_j;
      default:
         return null;
      }
   }

   public BufferedImage getTextureImage() {
      return this.textureImage;
   }

   public void setTextureImage(BufferedImage textureImage) {
      this.textureImage = textureImage;
   }

   public DynamicTexture getTexture() {
      return this.texture;
   }

   public ResourceLocation getTextureLocation() {
      return this.textureLocation;
   }

   public void setTextureLocation(ResourceLocation textureLocation) {
      this.textureLocation = textureLocation;
   }

   public boolean isUsePlayerTexture() {
      return this.usePlayerTexture;
   }
}
