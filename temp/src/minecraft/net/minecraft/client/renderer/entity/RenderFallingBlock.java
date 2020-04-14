package net.minecraft.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderFallingBlock extends Render<EntityFallingBlock> {
   public RenderFallingBlock(RenderManager p_i46177_1_) {
      super(p_i46177_1_);
      this.field_76989_e = 0.5F;
   }

   public void func_76986_a(EntityFallingBlock p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
      if (p_76986_1_.func_175131_l() != null) {
         this.func_110776_a(TextureMap.field_110575_b);
         IBlockState lvt_10_1_ = p_76986_1_.func_175131_l();
         Block lvt_11_1_ = lvt_10_1_.func_177230_c();
         BlockPos lvt_12_1_ = new BlockPos(p_76986_1_);
         World lvt_13_1_ = p_76986_1_.func_145807_e();
         if (lvt_10_1_ != lvt_13_1_.func_180495_p(lvt_12_1_) && lvt_11_1_.func_149645_b() != -1) {
            if (lvt_11_1_.func_149645_b() == 3) {
               GlStateManager.func_179094_E();
               GlStateManager.func_179109_b((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
               GlStateManager.func_179140_f();
               Tessellator lvt_14_1_ = Tessellator.func_178181_a();
               WorldRenderer lvt_15_1_ = lvt_14_1_.func_178180_c();
               lvt_15_1_.func_181668_a(7, DefaultVertexFormats.field_176600_a);
               int lvt_16_1_ = lvt_12_1_.func_177958_n();
               int lvt_17_1_ = lvt_12_1_.func_177956_o();
               int lvt_18_1_ = lvt_12_1_.func_177952_p();
               lvt_15_1_.func_178969_c((double)((float)(-lvt_16_1_) - 0.5F), (double)(-lvt_17_1_), (double)((float)(-lvt_18_1_) - 0.5F));
               BlockRendererDispatcher lvt_19_1_ = Minecraft.func_71410_x().func_175602_ab();
               IBakedModel lvt_20_1_ = lvt_19_1_.func_175022_a(lvt_10_1_, lvt_13_1_, (BlockPos)null);
               lvt_19_1_.func_175019_b().func_178267_a(lvt_13_1_, lvt_20_1_, lvt_10_1_, lvt_12_1_, lvt_15_1_, false);
               lvt_15_1_.func_178969_c(0.0D, 0.0D, 0.0D);
               lvt_14_1_.func_78381_a();
               GlStateManager.func_179145_e();
               GlStateManager.func_179121_F();
               super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
            }
         }
      }
   }

   protected ResourceLocation func_110775_a(EntityFallingBlock p_110775_1_) {
      return TextureMap.field_110575_b;
   }
}
