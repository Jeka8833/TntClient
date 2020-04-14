package net.minecraft.client.renderer.entity.layers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class LayerHeldItem implements LayerRenderer<EntityLivingBase> {
   private final RendererLivingEntity<?> field_177206_a;

   public LayerHeldItem(RendererLivingEntity<?> p_i46115_1_) {
      this.field_177206_a = p_i46115_1_;
   }

   public void func_177141_a(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
      ItemStack lvt_9_1_ = p_177141_1_.func_70694_bm();
      if (lvt_9_1_ != null) {
         GlStateManager.func_179094_E();
         if (this.field_177206_a.func_177087_b().field_78091_s) {
            float lvt_10_1_ = 0.5F;
            GlStateManager.func_179109_b(0.0F, 0.625F, 0.0F);
            GlStateManager.func_179114_b(-20.0F, -1.0F, 0.0F, 0.0F);
            GlStateManager.func_179152_a(lvt_10_1_, lvt_10_1_, lvt_10_1_);
         }

         ((ModelBiped)this.field_177206_a.func_177087_b()).func_178718_a(0.0625F);
         GlStateManager.func_179109_b(-0.0625F, 0.4375F, 0.0625F);
         if (p_177141_1_ instanceof EntityPlayer && ((EntityPlayer)p_177141_1_).field_71104_cf != null) {
            lvt_9_1_ = new ItemStack(Items.field_151112_aM, 0);
         }

         Item lvt_10_2_ = lvt_9_1_.func_77973_b();
         Minecraft lvt_11_1_ = Minecraft.func_71410_x();
         if (lvt_10_2_ instanceof ItemBlock && Block.func_149634_a(lvt_10_2_).func_149645_b() == 2) {
            GlStateManager.func_179109_b(0.0F, 0.1875F, -0.3125F);
            GlStateManager.func_179114_b(20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(45.0F, 0.0F, 1.0F, 0.0F);
            float lvt_12_1_ = 0.375F;
            GlStateManager.func_179152_a(-lvt_12_1_, -lvt_12_1_, lvt_12_1_);
         }

         if (p_177141_1_.func_70093_af()) {
            GlStateManager.func_179109_b(0.0F, 0.203125F, 0.0F);
         }

         lvt_11_1_.func_175597_ag().func_178099_a(p_177141_1_, lvt_9_1_, ItemCameraTransforms.TransformType.THIRD_PERSON);
         GlStateManager.func_179121_F();
      }
   }

   public boolean func_177142_b() {
      return false;
   }
}
