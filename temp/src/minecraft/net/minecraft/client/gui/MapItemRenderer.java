package net.minecraft.client.gui;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec4b;
import net.minecraft.world.storage.MapData;

public class MapItemRenderer {
   private static final ResourceLocation field_148253_a = new ResourceLocation("textures/map/map_icons.png");
   private final TextureManager field_148251_b;
   private final Map<String, MapItemRenderer.Instance> field_148252_c = Maps.newHashMap();

   public MapItemRenderer(TextureManager p_i45009_1_) {
      this.field_148251_b = p_i45009_1_;
   }

   public void func_148246_a(MapData p_148246_1_) {
      this.func_148248_b(p_148246_1_).func_148236_a();
   }

   public void func_148250_a(MapData p_148250_1_, boolean p_148250_2_) {
      this.func_148248_b(p_148250_1_).func_148237_a(p_148250_2_);
   }

   private MapItemRenderer.Instance func_148248_b(MapData p_148248_1_) {
      MapItemRenderer.Instance lvt_2_1_ = (MapItemRenderer.Instance)this.field_148252_c.get(p_148248_1_.field_76190_i);
      if (lvt_2_1_ == null) {
         lvt_2_1_ = new MapItemRenderer.Instance(p_148248_1_);
         this.field_148252_c.put(p_148248_1_.field_76190_i, lvt_2_1_);
      }

      return lvt_2_1_;
   }

   public void func_148249_a() {
      Iterator lvt_1_1_ = this.field_148252_c.values().iterator();

      while(lvt_1_1_.hasNext()) {
         MapItemRenderer.Instance lvt_2_1_ = (MapItemRenderer.Instance)lvt_1_1_.next();
         this.field_148251_b.func_147645_c(lvt_2_1_.field_148240_d);
      }

      this.field_148252_c.clear();
   }

   class Instance {
      private final MapData field_148242_b;
      private final DynamicTexture field_148243_c;
      private final ResourceLocation field_148240_d;
      private final int[] field_148241_e;

      private Instance(MapData p_i45007_2_) {
         this.field_148242_b = p_i45007_2_;
         this.field_148243_c = new DynamicTexture(128, 128);
         this.field_148241_e = this.field_148243_c.func_110565_c();
         this.field_148240_d = MapItemRenderer.this.field_148251_b.func_110578_a("map/" + p_i45007_2_.field_76190_i, this.field_148243_c);

         for(int lvt_3_1_ = 0; lvt_3_1_ < this.field_148241_e.length; ++lvt_3_1_) {
            this.field_148241_e[lvt_3_1_] = 0;
         }

      }

      private void func_148236_a() {
         for(int lvt_1_1_ = 0; lvt_1_1_ < 16384; ++lvt_1_1_) {
            int lvt_2_1_ = this.field_148242_b.field_76198_e[lvt_1_1_] & 255;
            if (lvt_2_1_ / 4 == 0) {
               this.field_148241_e[lvt_1_1_] = (lvt_1_1_ + lvt_1_1_ / 128 & 1) * 8 + 16 << 24;
            } else {
               this.field_148241_e[lvt_1_1_] = MapColor.field_76281_a[lvt_2_1_ / 4].func_151643_b(lvt_2_1_ & 3);
            }
         }

         this.field_148243_c.func_110564_a();
      }

      private void func_148237_a(boolean p_148237_1_) {
         int lvt_2_1_ = 0;
         int lvt_3_1_ = 0;
         Tessellator lvt_4_1_ = Tessellator.func_178181_a();
         WorldRenderer lvt_5_1_ = lvt_4_1_.func_178180_c();
         float lvt_6_1_ = 0.0F;
         MapItemRenderer.this.field_148251_b.func_110577_a(this.field_148240_d);
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(1, 771, 0, 1);
         GlStateManager.func_179118_c();
         lvt_5_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
         lvt_5_1_.func_181662_b((double)((float)(lvt_2_1_ + 0) + lvt_6_1_), (double)((float)(lvt_3_1_ + 128) - lvt_6_1_), -0.009999999776482582D).func_181673_a(0.0D, 1.0D).func_181675_d();
         lvt_5_1_.func_181662_b((double)((float)(lvt_2_1_ + 128) - lvt_6_1_), (double)((float)(lvt_3_1_ + 128) - lvt_6_1_), -0.009999999776482582D).func_181673_a(1.0D, 1.0D).func_181675_d();
         lvt_5_1_.func_181662_b((double)((float)(lvt_2_1_ + 128) - lvt_6_1_), (double)((float)(lvt_3_1_ + 0) + lvt_6_1_), -0.009999999776482582D).func_181673_a(1.0D, 0.0D).func_181675_d();
         lvt_5_1_.func_181662_b((double)((float)(lvt_2_1_ + 0) + lvt_6_1_), (double)((float)(lvt_3_1_ + 0) + lvt_6_1_), -0.009999999776482582D).func_181673_a(0.0D, 0.0D).func_181675_d();
         lvt_4_1_.func_78381_a();
         GlStateManager.func_179141_d();
         GlStateManager.func_179084_k();
         MapItemRenderer.this.field_148251_b.func_110577_a(MapItemRenderer.field_148253_a);
         int lvt_7_1_ = 0;
         Iterator lvt_8_1_ = this.field_148242_b.field_76203_h.values().iterator();

         while(true) {
            Vec4b lvt_9_1_;
            do {
               if (!lvt_8_1_.hasNext()) {
                  GlStateManager.func_179094_E();
                  GlStateManager.func_179109_b(0.0F, 0.0F, -0.04F);
                  GlStateManager.func_179152_a(1.0F, 1.0F, 1.0F);
                  GlStateManager.func_179121_F();
                  return;
               }

               lvt_9_1_ = (Vec4b)lvt_8_1_.next();
            } while(p_148237_1_ && lvt_9_1_.func_176110_a() != 1);

            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)lvt_2_1_ + (float)lvt_9_1_.func_176112_b() / 2.0F + 64.0F, (float)lvt_3_1_ + (float)lvt_9_1_.func_176113_c() / 2.0F + 64.0F, -0.02F);
            GlStateManager.func_179114_b((float)(lvt_9_1_.func_176111_d() * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179152_a(4.0F, 4.0F, 3.0F);
            GlStateManager.func_179109_b(-0.125F, 0.125F, 0.0F);
            byte lvt_10_1_ = lvt_9_1_.func_176110_a();
            float lvt_11_1_ = (float)(lvt_10_1_ % 4 + 0) / 4.0F;
            float lvt_12_1_ = (float)(lvt_10_1_ / 4 + 0) / 4.0F;
            float lvt_13_1_ = (float)(lvt_10_1_ % 4 + 1) / 4.0F;
            float lvt_14_1_ = (float)(lvt_10_1_ / 4 + 1) / 4.0F;
            lvt_5_1_.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            float lvt_15_1_ = -0.001F;
            lvt_5_1_.func_181662_b(-1.0D, 1.0D, (double)((float)lvt_7_1_ * -0.001F)).func_181673_a((double)lvt_11_1_, (double)lvt_12_1_).func_181675_d();
            lvt_5_1_.func_181662_b(1.0D, 1.0D, (double)((float)lvt_7_1_ * -0.001F)).func_181673_a((double)lvt_13_1_, (double)lvt_12_1_).func_181675_d();
            lvt_5_1_.func_181662_b(1.0D, -1.0D, (double)((float)lvt_7_1_ * -0.001F)).func_181673_a((double)lvt_13_1_, (double)lvt_14_1_).func_181675_d();
            lvt_5_1_.func_181662_b(-1.0D, -1.0D, (double)((float)lvt_7_1_ * -0.001F)).func_181673_a((double)lvt_11_1_, (double)lvt_14_1_).func_181675_d();
            lvt_4_1_.func_78381_a();
            GlStateManager.func_179121_F();
            ++lvt_7_1_;
         }
      }
   }
}
