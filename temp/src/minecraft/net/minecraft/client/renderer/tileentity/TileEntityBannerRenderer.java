package net.minecraft.client.renderer.tileentity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBanner;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.LayeredColorMaskTexture;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TileEntityBannerRenderer extends TileEntitySpecialRenderer<TileEntityBanner> {
   private static final Map<String, TileEntityBannerRenderer.TimedBannerTexture> field_178466_c = Maps.newHashMap();
   private static final ResourceLocation field_178464_d = new ResourceLocation("textures/entity/banner_base.png");
   private ModelBanner field_178465_e = new ModelBanner();

   public void func_180535_a(TileEntityBanner p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      boolean lvt_10_1_ = p_180535_1_.func_145831_w() != null;
      boolean lvt_11_1_ = !lvt_10_1_ || p_180535_1_.func_145838_q() == Blocks.field_180393_cK;
      int lvt_12_1_ = lvt_10_1_ ? p_180535_1_.func_145832_p() : 0;
      long lvt_13_1_ = lvt_10_1_ ? p_180535_1_.func_145831_w().func_82737_E() : 0L;
      GlStateManager.func_179094_E();
      float lvt_15_1_ = 0.6666667F;
      float lvt_17_1_;
      if (lvt_11_1_) {
         GlStateManager.func_179109_b((float)p_180535_2_ + 0.5F, (float)p_180535_4_ + 0.75F * lvt_15_1_, (float)p_180535_6_ + 0.5F);
         float lvt_16_1_ = (float)(lvt_12_1_ * 360) / 16.0F;
         GlStateManager.func_179114_b(-lvt_16_1_, 0.0F, 1.0F, 0.0F);
         this.field_178465_e.field_178688_b.field_78806_j = true;
      } else {
         lvt_17_1_ = 0.0F;
         if (lvt_12_1_ == 2) {
            lvt_17_1_ = 180.0F;
         }

         if (lvt_12_1_ == 4) {
            lvt_17_1_ = 90.0F;
         }

         if (lvt_12_1_ == 5) {
            lvt_17_1_ = -90.0F;
         }

         GlStateManager.func_179109_b((float)p_180535_2_ + 0.5F, (float)p_180535_4_ - 0.25F * lvt_15_1_, (float)p_180535_6_ + 0.5F);
         GlStateManager.func_179114_b(-lvt_17_1_, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179109_b(0.0F, -0.3125F, -0.4375F);
         this.field_178465_e.field_178688_b.field_78806_j = false;
      }

      BlockPos lvt_16_3_ = p_180535_1_.func_174877_v();
      lvt_17_1_ = (float)(lvt_16_3_.func_177958_n() * 7 + lvt_16_3_.func_177956_o() * 9 + lvt_16_3_.func_177952_p() * 13) + (float)lvt_13_1_ + p_180535_8_;
      this.field_178465_e.field_178690_a.field_78795_f = (-0.0125F + 0.01F * MathHelper.func_76134_b(lvt_17_1_ * 3.1415927F * 0.02F)) * 3.1415927F;
      GlStateManager.func_179091_B();
      ResourceLocation lvt_18_1_ = this.func_178463_a(p_180535_1_);
      if (lvt_18_1_ != null) {
         this.func_147499_a(lvt_18_1_);
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(lvt_15_1_, -lvt_15_1_, -lvt_15_1_);
         this.field_178465_e.func_178687_a();
         GlStateManager.func_179121_F();
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179121_F();
   }

   private ResourceLocation func_178463_a(TileEntityBanner p_178463_1_) {
      String lvt_2_1_ = p_178463_1_.func_175116_e();
      if (lvt_2_1_.isEmpty()) {
         return null;
      } else {
         TileEntityBannerRenderer.TimedBannerTexture lvt_3_1_ = (TileEntityBannerRenderer.TimedBannerTexture)field_178466_c.get(lvt_2_1_);
         if (lvt_3_1_ == null) {
            if (field_178466_c.size() >= 256) {
               long lvt_4_1_ = System.currentTimeMillis();
               Iterator lvt_6_1_ = field_178466_c.keySet().iterator();

               while(lvt_6_1_.hasNext()) {
                  String lvt_7_1_ = (String)lvt_6_1_.next();
                  TileEntityBannerRenderer.TimedBannerTexture lvt_8_1_ = (TileEntityBannerRenderer.TimedBannerTexture)field_178466_c.get(lvt_7_1_);
                  if (lvt_4_1_ - lvt_8_1_.field_178472_a > 60000L) {
                     Minecraft.func_71410_x().func_110434_K().func_147645_c(lvt_8_1_.field_178471_b);
                     lvt_6_1_.remove();
                  }
               }

               if (field_178466_c.size() >= 256) {
                  return null;
               }
            }

            List<TileEntityBanner.EnumBannerPattern> lvt_4_2_ = p_178463_1_.func_175114_c();
            List<EnumDyeColor> lvt_5_1_ = p_178463_1_.func_175110_d();
            List<String> lvt_6_2_ = Lists.newArrayList();
            Iterator lvt_7_2_ = lvt_4_2_.iterator();

            while(lvt_7_2_.hasNext()) {
               TileEntityBanner.EnumBannerPattern lvt_8_2_ = (TileEntityBanner.EnumBannerPattern)lvt_7_2_.next();
               lvt_6_2_.add("textures/entity/banner/" + lvt_8_2_.func_177271_a() + ".png");
            }

            lvt_3_1_ = new TileEntityBannerRenderer.TimedBannerTexture();
            lvt_3_1_.field_178471_b = new ResourceLocation(lvt_2_1_);
            Minecraft.func_71410_x().func_110434_K().func_110579_a(lvt_3_1_.field_178471_b, new LayeredColorMaskTexture(field_178464_d, lvt_6_2_, lvt_5_1_));
            field_178466_c.put(lvt_2_1_, lvt_3_1_);
         }

         lvt_3_1_.field_178472_a = System.currentTimeMillis();
         return lvt_3_1_.field_178471_b;
      }
   }

   static class TimedBannerTexture {
      public long field_178472_a;
      public ResourceLocation field_178471_b;

      private TimedBannerTexture() {
      }
   }
}
