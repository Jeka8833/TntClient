package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TileEntityEnchantmentTableRenderer extends TileEntitySpecialRenderer<TileEntityEnchantmentTable> {
   private static final ResourceLocation field_147540_b = new ResourceLocation("textures/entity/enchanting_table_book.png");
   private ModelBook field_147541_c = new ModelBook();

   public void func_180535_a(TileEntityEnchantmentTable p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b((float)p_180535_2_ + 0.5F, (float)p_180535_4_ + 0.75F, (float)p_180535_6_ + 0.5F);
      float lvt_10_1_ = (float)p_180535_1_.field_145926_a + p_180535_8_;
      GlStateManager.func_179109_b(0.0F, 0.1F + MathHelper.func_76126_a(lvt_10_1_ * 0.1F) * 0.01F, 0.0F);

      float lvt_11_1_;
      for(lvt_11_1_ = p_180535_1_.field_145928_o - p_180535_1_.field_145925_p; lvt_11_1_ >= 3.1415927F; lvt_11_1_ -= 6.2831855F) {
      }

      while(lvt_11_1_ < -3.1415927F) {
         lvt_11_1_ += 6.2831855F;
      }

      float lvt_12_1_ = p_180535_1_.field_145925_p + lvt_11_1_ * p_180535_8_;
      GlStateManager.func_179114_b(-lvt_12_1_ * 180.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
      GlStateManager.func_179114_b(80.0F, 0.0F, 0.0F, 1.0F);
      this.func_147499_a(field_147540_b);
      float lvt_13_1_ = p_180535_1_.field_145931_j + (p_180535_1_.field_145933_i - p_180535_1_.field_145931_j) * p_180535_8_ + 0.25F;
      float lvt_14_1_ = p_180535_1_.field_145931_j + (p_180535_1_.field_145933_i - p_180535_1_.field_145931_j) * p_180535_8_ + 0.75F;
      lvt_13_1_ = (lvt_13_1_ - (float)MathHelper.func_76140_b((double)lvt_13_1_)) * 1.6F - 0.3F;
      lvt_14_1_ = (lvt_14_1_ - (float)MathHelper.func_76140_b((double)lvt_14_1_)) * 1.6F - 0.3F;
      if (lvt_13_1_ < 0.0F) {
         lvt_13_1_ = 0.0F;
      }

      if (lvt_14_1_ < 0.0F) {
         lvt_14_1_ = 0.0F;
      }

      if (lvt_13_1_ > 1.0F) {
         lvt_13_1_ = 1.0F;
      }

      if (lvt_14_1_ > 1.0F) {
         lvt_14_1_ = 1.0F;
      }

      float lvt_15_1_ = p_180535_1_.field_145927_n + (p_180535_1_.field_145930_m - p_180535_1_.field_145927_n) * p_180535_8_;
      GlStateManager.func_179089_o();
      this.field_147541_c.func_78088_a((Entity)null, lvt_10_1_, lvt_13_1_, lvt_14_1_, lvt_15_1_, 0.0F, 0.0625F);
      GlStateManager.func_179121_F();
   }
}
