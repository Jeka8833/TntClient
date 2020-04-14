package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TileEntityPistonRenderer extends TileEntitySpecialRenderer<TileEntityPiston> {
   private final BlockRendererDispatcher field_178462_c = Minecraft.func_71410_x().func_175602_ab();

   public void func_180535_a(TileEntityPiston p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      BlockPos lvt_10_1_ = p_180535_1_.func_174877_v();
      IBlockState lvt_11_1_ = p_180535_1_.func_174927_b();
      Block lvt_12_1_ = lvt_11_1_.func_177230_c();
      if (lvt_12_1_.func_149688_o() != Material.field_151579_a && p_180535_1_.func_145860_a(p_180535_8_) < 1.0F) {
         Tessellator lvt_13_1_ = Tessellator.func_178181_a();
         WorldRenderer lvt_14_1_ = lvt_13_1_.func_178180_c();
         this.func_147499_a(TextureMap.field_110575_b);
         RenderHelper.func_74518_a();
         GlStateManager.func_179112_b(770, 771);
         GlStateManager.func_179147_l();
         GlStateManager.func_179129_p();
         if (Minecraft.func_71379_u()) {
            GlStateManager.func_179103_j(7425);
         } else {
            GlStateManager.func_179103_j(7424);
         }

         lvt_14_1_.func_181668_a(7, DefaultVertexFormats.field_176600_a);
         lvt_14_1_.func_178969_c((double)((float)p_180535_2_ - (float)lvt_10_1_.func_177958_n() + p_180535_1_.func_174929_b(p_180535_8_)), (double)((float)p_180535_4_ - (float)lvt_10_1_.func_177956_o() + p_180535_1_.func_174928_c(p_180535_8_)), (double)((float)p_180535_6_ - (float)lvt_10_1_.func_177952_p() + p_180535_1_.func_174926_d(p_180535_8_)));
         World lvt_15_1_ = this.func_178459_a();
         if (lvt_12_1_ == Blocks.field_150332_K && p_180535_1_.func_145860_a(p_180535_8_) < 0.5F) {
            lvt_11_1_ = lvt_11_1_.func_177226_a(BlockPistonExtension.field_176327_M, true);
            this.field_178462_c.func_175019_b().func_178267_a(lvt_15_1_, this.field_178462_c.func_175022_a(lvt_11_1_, lvt_15_1_, lvt_10_1_), lvt_11_1_, lvt_10_1_, lvt_14_1_, true);
         } else if (p_180535_1_.func_145867_d() && !p_180535_1_.func_145868_b()) {
            BlockPistonExtension.EnumPistonType lvt_16_1_ = lvt_12_1_ == Blocks.field_150320_F ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT;
            IBlockState lvt_17_1_ = Blocks.field_150332_K.func_176223_P().func_177226_a(BlockPistonExtension.field_176325_b, lvt_16_1_).func_177226_a(BlockPistonExtension.field_176326_a, lvt_11_1_.func_177229_b(BlockPistonBase.field_176321_a));
            lvt_17_1_ = lvt_17_1_.func_177226_a(BlockPistonExtension.field_176327_M, p_180535_1_.func_145860_a(p_180535_8_) >= 0.5F);
            this.field_178462_c.func_175019_b().func_178267_a(lvt_15_1_, this.field_178462_c.func_175022_a(lvt_17_1_, lvt_15_1_, lvt_10_1_), lvt_17_1_, lvt_10_1_, lvt_14_1_, true);
            lvt_14_1_.func_178969_c((double)((float)p_180535_2_ - (float)lvt_10_1_.func_177958_n()), (double)((float)p_180535_4_ - (float)lvt_10_1_.func_177956_o()), (double)((float)p_180535_6_ - (float)lvt_10_1_.func_177952_p()));
            lvt_11_1_.func_177226_a(BlockPistonBase.field_176320_b, true);
            this.field_178462_c.func_175019_b().func_178267_a(lvt_15_1_, this.field_178462_c.func_175022_a(lvt_11_1_, lvt_15_1_, lvt_10_1_), lvt_11_1_, lvt_10_1_, lvt_14_1_, true);
         } else {
            this.field_178462_c.func_175019_b().func_178267_a(lvt_15_1_, this.field_178462_c.func_175022_a(lvt_11_1_, lvt_15_1_, lvt_10_1_), lvt_11_1_, lvt_10_1_, lvt_14_1_, false);
         }

         lvt_14_1_.func_178969_c(0.0D, 0.0D, 0.0D);
         lvt_13_1_.func_78381_a();
         RenderHelper.func_74519_b();
      }
   }
}
