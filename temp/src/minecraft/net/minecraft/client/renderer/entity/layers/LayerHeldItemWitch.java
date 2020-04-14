package net.minecraft.client.renderer.entity.layers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class LayerHeldItemWitch implements LayerRenderer<EntityWitch> {
   private final RenderWitch field_177144_a;

   public LayerHeldItemWitch(RenderWitch p_i46106_1_) {
      this.field_177144_a = p_i46106_1_;
   }

   public void func_177141_a(EntityWitch p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
      ItemStack lvt_9_1_ = p_177141_1_.func_70694_bm();
      if (lvt_9_1_ != null) {
         GlStateManager.func_179124_c(1.0F, 1.0F, 1.0F);
         GlStateManager.func_179094_E();
         if (this.field_177144_a.func_177087_b().field_78091_s) {
            GlStateManager.func_179109_b(0.0F, 0.625F, 0.0F);
            GlStateManager.func_179114_b(-20.0F, -1.0F, 0.0F, 0.0F);
            float lvt_10_1_ = 0.5F;
            GlStateManager.func_179152_a(lvt_10_1_, lvt_10_1_, lvt_10_1_);
         }

         ((ModelWitch)this.field_177144_a.func_177087_b()).field_82898_f.func_78794_c(0.0625F);
         GlStateManager.func_179109_b(-0.0625F, 0.53125F, 0.21875F);
         Item lvt_10_2_ = lvt_9_1_.func_77973_b();
         Minecraft lvt_11_1_ = Minecraft.func_71410_x();
         float lvt_12_3_;
         if (lvt_10_2_ instanceof ItemBlock && lvt_11_1_.func_175602_ab().func_175021_a(Block.func_149634_a(lvt_10_2_), lvt_9_1_.func_77960_j())) {
            GlStateManager.func_179109_b(0.0F, 0.0625F, -0.25F);
            GlStateManager.func_179114_b(30.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(-5.0F, 0.0F, 1.0F, 0.0F);
            lvt_12_3_ = 0.375F;
            GlStateManager.func_179152_a(lvt_12_3_, -lvt_12_3_, lvt_12_3_);
         } else if (lvt_10_2_ == Items.field_151031_f) {
            GlStateManager.func_179109_b(0.0F, 0.125F, -0.125F);
            GlStateManager.func_179114_b(-45.0F, 0.0F, 1.0F, 0.0F);
            lvt_12_3_ = 0.625F;
            GlStateManager.func_179152_a(lvt_12_3_, -lvt_12_3_, lvt_12_3_);
            GlStateManager.func_179114_b(-100.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(-20.0F, 0.0F, 1.0F, 0.0F);
         } else if (lvt_10_2_.func_77662_d()) {
            if (lvt_10_2_.func_77629_n_()) {
               GlStateManager.func_179114_b(180.0F, 0.0F, 0.0F, 1.0F);
               GlStateManager.func_179109_b(0.0F, -0.0625F, 0.0F);
            }

            this.field_177144_a.func_82422_c();
            GlStateManager.func_179109_b(0.0625F, -0.125F, 0.0F);
            lvt_12_3_ = 0.625F;
            GlStateManager.func_179152_a(lvt_12_3_, -lvt_12_3_, lvt_12_3_);
            GlStateManager.func_179114_b(0.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(0.0F, 0.0F, 1.0F, 0.0F);
         } else {
            GlStateManager.func_179109_b(0.1875F, 0.1875F, 0.0F);
            lvt_12_3_ = 0.875F;
            GlStateManager.func_179152_a(lvt_12_3_, lvt_12_3_, lvt_12_3_);
            GlStateManager.func_179114_b(-20.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179114_b(-60.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(-30.0F, 0.0F, 0.0F, 1.0F);
         }

         GlStateManager.func_179114_b(-15.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.func_179114_b(40.0F, 0.0F, 0.0F, 1.0F);
         lvt_11_1_.func_175597_ag().func_178099_a(p_177141_1_, lvt_9_1_, ItemCameraTransforms.TransformType.THIRD_PERSON);
         GlStateManager.func_179121_F();
      }
   }

   public boolean func_177142_b() {
      return false;
   }
}
