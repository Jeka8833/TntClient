package net.optifine.player;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.src.Config;

public class PlayerItemsLayer implements LayerRenderer {
   private RenderPlayer renderPlayer = null;

   public PlayerItemsLayer(RenderPlayer renderPlayer) {
      this.renderPlayer = renderPlayer;
   }

   public void func_177141_a(EntityLivingBase entityLiving, float limbSwing, float limbSwingAmount, float partialTicks, float ticksExisted, float headYaw, float rotationPitch, float scale) {
      this.renderEquippedItems(entityLiving, scale, partialTicks);
   }

   protected void renderEquippedItems(EntityLivingBase entityLiving, float scale, float partialTicks) {
      if (Config.isShowCapes()) {
         if (entityLiving instanceof AbstractClientPlayer) {
            AbstractClientPlayer player = (AbstractClientPlayer)entityLiving;
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179101_C();
            GlStateManager.func_179089_o();
            ModelBiped modelBipedMain = this.renderPlayer.func_177087_b();
            PlayerConfigurations.renderPlayerItems(modelBipedMain, player, scale, partialTicks);
            GlStateManager.func_179129_p();
         }
      }
   }

   public boolean func_177142_b() {
      return false;
   }

   public static void register(Map renderPlayerMap) {
      Set keys = renderPlayerMap.keySet();
      boolean registered = false;
      Iterator it = keys.iterator();

      while(it.hasNext()) {
         Object key = it.next();
         Object renderer = renderPlayerMap.get(key);
         if (renderer instanceof RenderPlayer) {
            RenderPlayer renderPlayer = (RenderPlayer)renderer;
            renderPlayer.func_177094_a(new PlayerItemsLayer(renderPlayer));
            registered = true;
         }
      }

      if (!registered) {
         Config.warn("PlayerItemsLayer not registered");
      }

   }
}
