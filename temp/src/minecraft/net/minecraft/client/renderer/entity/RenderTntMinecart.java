package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;

public class RenderTntMinecart extends RenderMinecart<EntityMinecartTNT> {
   public RenderTntMinecart(RenderManager p_i46135_1_) {
      super(p_i46135_1_);
   }

   protected void func_180560_a(EntityMinecartTNT p_180560_1_, float p_180560_2_, IBlockState p_180560_3_) {
      int lvt_4_1_ = p_180560_1_.func_94104_d();
      if (lvt_4_1_ > -1 && (float)lvt_4_1_ - p_180560_2_ + 1.0F < 10.0F) {
         float lvt_5_1_ = 1.0F - ((float)lvt_4_1_ - p_180560_2_ + 1.0F) / 10.0F;
         lvt_5_1_ = MathHelper.func_76131_a(lvt_5_1_, 0.0F, 1.0F);
         lvt_5_1_ *= lvt_5_1_;
         lvt_5_1_ *= lvt_5_1_;
         float lvt_6_1_ = 1.0F + lvt_5_1_ * 0.3F;
         GlStateManager.func_179152_a(lvt_6_1_, lvt_6_1_, lvt_6_1_);
      }

      super.func_180560_a(p_180560_1_, p_180560_2_, p_180560_3_);
      if (lvt_4_1_ > -1 && lvt_4_1_ / 5 % 2 == 0) {
         BlockRendererDispatcher lvt_5_2_ = Minecraft.func_71410_x().func_175602_ab();
         GlStateManager.func_179090_x();
         GlStateManager.func_179140_f();
         GlStateManager.func_179147_l();
         GlStateManager.func_179112_b(770, 772);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, (1.0F - ((float)lvt_4_1_ - p_180560_2_ + 1.0F) / 100.0F) * 0.8F);
         GlStateManager.func_179094_E();
         lvt_5_2_.func_175016_a(Blocks.field_150335_W.func_176223_P(), 1.0F);
         GlStateManager.func_179121_F();
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179084_k();
         GlStateManager.func_179145_e();
         GlStateManager.func_179098_w();
      }

   }
}
