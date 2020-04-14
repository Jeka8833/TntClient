package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector3f;

public class ItemModelGenerator {
   public static final List<String> field_178398_a = Lists.newArrayList((Object[])("layer0", "layer1", "layer2", "layer3", "layer4"));

   public ModelBlock func_178392_a(TextureMap p_178392_1_, ModelBlock p_178392_2_) {
      Map<String, String> lvt_3_1_ = Maps.newHashMap();
      List<BlockPart> lvt_4_1_ = Lists.newArrayList();

      for(int lvt_5_1_ = 0; lvt_5_1_ < field_178398_a.size(); ++lvt_5_1_) {
         String lvt_6_1_ = (String)field_178398_a.get(lvt_5_1_);
         if (!p_178392_2_.func_178300_b(lvt_6_1_)) {
            break;
         }

         String lvt_7_1_ = p_178392_2_.func_178308_c(lvt_6_1_);
         lvt_3_1_.put(lvt_6_1_, lvt_7_1_);
         TextureAtlasSprite lvt_8_1_ = p_178392_1_.func_110572_b((new ResourceLocation(lvt_7_1_)).toString());
         lvt_4_1_.addAll(this.func_178394_a(lvt_5_1_, lvt_6_1_, lvt_8_1_));
      }

      if (lvt_4_1_.isEmpty()) {
         return null;
      } else {
         lvt_3_1_.put("particle", p_178392_2_.func_178300_b("particle") ? p_178392_2_.func_178308_c("particle") : (String)lvt_3_1_.get("layer0"));
         return new ModelBlock(lvt_4_1_, lvt_3_1_, false, false, p_178392_2_.func_181682_g());
      }
   }

   private List<BlockPart> func_178394_a(int p_178394_1_, String p_178394_2_, TextureAtlasSprite p_178394_3_) {
      Map<EnumFacing, BlockPartFace> lvt_4_1_ = Maps.newHashMap();
      lvt_4_1_.put(EnumFacing.SOUTH, new BlockPartFace((EnumFacing)null, p_178394_1_, p_178394_2_, new BlockFaceUV(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0)));
      lvt_4_1_.put(EnumFacing.NORTH, new BlockPartFace((EnumFacing)null, p_178394_1_, p_178394_2_, new BlockFaceUV(new float[]{16.0F, 0.0F, 0.0F, 16.0F}, 0)));
      List<BlockPart> lvt_5_1_ = Lists.newArrayList();
      lvt_5_1_.add(new BlockPart(new Vector3f(0.0F, 0.0F, 7.5F), new Vector3f(16.0F, 16.0F, 8.5F), lvt_4_1_, (BlockPartRotation)null, true));
      lvt_5_1_.addAll(this.func_178397_a(p_178394_3_, p_178394_2_, p_178394_1_));
      return lvt_5_1_;
   }

   private List<BlockPart> func_178397_a(TextureAtlasSprite p_178397_1_, String p_178397_2_, int p_178397_3_) {
      float lvt_4_1_ = (float)p_178397_1_.func_94211_a();
      float lvt_5_1_ = (float)p_178397_1_.func_94216_b();
      List<BlockPart> lvt_6_1_ = Lists.newArrayList();
      Iterator lvt_7_1_ = this.func_178393_a(p_178397_1_).iterator();

      while(lvt_7_1_.hasNext()) {
         ItemModelGenerator.Span lvt_8_1_ = (ItemModelGenerator.Span)lvt_7_1_.next();
         float lvt_9_1_ = 0.0F;
         float lvt_10_1_ = 0.0F;
         float lvt_11_1_ = 0.0F;
         float lvt_12_1_ = 0.0F;
         float lvt_13_1_ = 0.0F;
         float lvt_14_1_ = 0.0F;
         float lvt_15_1_ = 0.0F;
         float lvt_16_1_ = 0.0F;
         float lvt_17_1_ = 0.0F;
         float lvt_18_1_ = 0.0F;
         float lvt_19_1_ = (float)lvt_8_1_.func_178385_b();
         float lvt_20_1_ = (float)lvt_8_1_.func_178384_c();
         float lvt_21_1_ = (float)lvt_8_1_.func_178381_d();
         ItemModelGenerator.SpanFacing lvt_22_1_ = lvt_8_1_.func_178383_a();
         switch(lvt_22_1_) {
         case UP:
            lvt_13_1_ = lvt_19_1_;
            lvt_9_1_ = lvt_19_1_;
            lvt_11_1_ = lvt_14_1_ = lvt_20_1_ + 1.0F;
            lvt_15_1_ = lvt_21_1_;
            lvt_10_1_ = lvt_21_1_;
            lvt_16_1_ = lvt_21_1_;
            lvt_12_1_ = lvt_21_1_;
            lvt_17_1_ = 16.0F / lvt_4_1_;
            lvt_18_1_ = 16.0F / (lvt_5_1_ - 1.0F);
            break;
         case DOWN:
            lvt_16_1_ = lvt_21_1_;
            lvt_15_1_ = lvt_21_1_;
            lvt_13_1_ = lvt_19_1_;
            lvt_9_1_ = lvt_19_1_;
            lvt_11_1_ = lvt_14_1_ = lvt_20_1_ + 1.0F;
            lvt_10_1_ = lvt_21_1_ + 1.0F;
            lvt_12_1_ = lvt_21_1_ + 1.0F;
            lvt_17_1_ = 16.0F / lvt_4_1_;
            lvt_18_1_ = 16.0F / (lvt_5_1_ - 1.0F);
            break;
         case LEFT:
            lvt_13_1_ = lvt_21_1_;
            lvt_9_1_ = lvt_21_1_;
            lvt_14_1_ = lvt_21_1_;
            lvt_11_1_ = lvt_21_1_;
            lvt_16_1_ = lvt_19_1_;
            lvt_10_1_ = lvt_19_1_;
            lvt_12_1_ = lvt_15_1_ = lvt_20_1_ + 1.0F;
            lvt_17_1_ = 16.0F / (lvt_4_1_ - 1.0F);
            lvt_18_1_ = 16.0F / lvt_5_1_;
            break;
         case RIGHT:
            lvt_14_1_ = lvt_21_1_;
            lvt_13_1_ = lvt_21_1_;
            lvt_9_1_ = lvt_21_1_ + 1.0F;
            lvt_11_1_ = lvt_21_1_ + 1.0F;
            lvt_16_1_ = lvt_19_1_;
            lvt_10_1_ = lvt_19_1_;
            lvt_12_1_ = lvt_15_1_ = lvt_20_1_ + 1.0F;
            lvt_17_1_ = 16.0F / (lvt_4_1_ - 1.0F);
            lvt_18_1_ = 16.0F / lvt_5_1_;
         }

         float lvt_23_1_ = 16.0F / lvt_4_1_;
         float lvt_24_1_ = 16.0F / lvt_5_1_;
         lvt_9_1_ *= lvt_23_1_;
         lvt_11_1_ *= lvt_23_1_;
         lvt_10_1_ *= lvt_24_1_;
         lvt_12_1_ *= lvt_24_1_;
         lvt_10_1_ = 16.0F - lvt_10_1_;
         lvt_12_1_ = 16.0F - lvt_12_1_;
         lvt_13_1_ *= lvt_17_1_;
         lvt_14_1_ *= lvt_17_1_;
         lvt_15_1_ *= lvt_18_1_;
         lvt_16_1_ *= lvt_18_1_;
         Map<EnumFacing, BlockPartFace> lvt_25_1_ = Maps.newHashMap();
         lvt_25_1_.put(lvt_22_1_.func_178367_a(), new BlockPartFace((EnumFacing)null, p_178397_3_, p_178397_2_, new BlockFaceUV(new float[]{lvt_13_1_, lvt_15_1_, lvt_14_1_, lvt_16_1_}, 0)));
         switch(lvt_22_1_) {
         case UP:
            lvt_6_1_.add(new BlockPart(new Vector3f(lvt_9_1_, lvt_10_1_, 7.5F), new Vector3f(lvt_11_1_, lvt_10_1_, 8.5F), lvt_25_1_, (BlockPartRotation)null, true));
            break;
         case DOWN:
            lvt_6_1_.add(new BlockPart(new Vector3f(lvt_9_1_, lvt_12_1_, 7.5F), new Vector3f(lvt_11_1_, lvt_12_1_, 8.5F), lvt_25_1_, (BlockPartRotation)null, true));
            break;
         case LEFT:
            lvt_6_1_.add(new BlockPart(new Vector3f(lvt_9_1_, lvt_10_1_, 7.5F), new Vector3f(lvt_9_1_, lvt_12_1_, 8.5F), lvt_25_1_, (BlockPartRotation)null, true));
            break;
         case RIGHT:
            lvt_6_1_.add(new BlockPart(new Vector3f(lvt_11_1_, lvt_10_1_, 7.5F), new Vector3f(lvt_11_1_, lvt_12_1_, 8.5F), lvt_25_1_, (BlockPartRotation)null, true));
         }
      }

      return lvt_6_1_;
   }

   private List<ItemModelGenerator.Span> func_178393_a(TextureAtlasSprite p_178393_1_) {
      int lvt_2_1_ = p_178393_1_.func_94211_a();
      int lvt_3_1_ = p_178393_1_.func_94216_b();
      List<ItemModelGenerator.Span> lvt_4_1_ = Lists.newArrayList();

      for(int lvt_5_1_ = 0; lvt_5_1_ < p_178393_1_.func_110970_k(); ++lvt_5_1_) {
         int[] lvt_6_1_ = p_178393_1_.func_147965_a(lvt_5_1_)[0];

         for(int lvt_7_1_ = 0; lvt_7_1_ < lvt_3_1_; ++lvt_7_1_) {
            for(int lvt_8_1_ = 0; lvt_8_1_ < lvt_2_1_; ++lvt_8_1_) {
               boolean lvt_9_1_ = !this.func_178391_a(lvt_6_1_, lvt_8_1_, lvt_7_1_, lvt_2_1_, lvt_3_1_);
               this.func_178396_a(ItemModelGenerator.SpanFacing.UP, lvt_4_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, lvt_2_1_, lvt_3_1_, lvt_9_1_);
               this.func_178396_a(ItemModelGenerator.SpanFacing.DOWN, lvt_4_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, lvt_2_1_, lvt_3_1_, lvt_9_1_);
               this.func_178396_a(ItemModelGenerator.SpanFacing.LEFT, lvt_4_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, lvt_2_1_, lvt_3_1_, lvt_9_1_);
               this.func_178396_a(ItemModelGenerator.SpanFacing.RIGHT, lvt_4_1_, lvt_6_1_, lvt_8_1_, lvt_7_1_, lvt_2_1_, lvt_3_1_, lvt_9_1_);
            }
         }
      }

      return lvt_4_1_;
   }

   private void func_178396_a(ItemModelGenerator.SpanFacing p_178396_1_, List<ItemModelGenerator.Span> p_178396_2_, int[] p_178396_3_, int p_178396_4_, int p_178396_5_, int p_178396_6_, int p_178396_7_, boolean p_178396_8_) {
      boolean lvt_9_1_ = this.func_178391_a(p_178396_3_, p_178396_4_ + p_178396_1_.func_178372_b(), p_178396_5_ + p_178396_1_.func_178371_c(), p_178396_6_, p_178396_7_) && p_178396_8_;
      if (lvt_9_1_) {
         this.func_178395_a(p_178396_2_, p_178396_1_, p_178396_4_, p_178396_5_);
      }

   }

   private void func_178395_a(List<ItemModelGenerator.Span> p_178395_1_, ItemModelGenerator.SpanFacing p_178395_2_, int p_178395_3_, int p_178395_4_) {
      ItemModelGenerator.Span lvt_5_1_ = null;
      Iterator lvt_6_1_ = p_178395_1_.iterator();

      while(lvt_6_1_.hasNext()) {
         ItemModelGenerator.Span lvt_7_1_ = (ItemModelGenerator.Span)lvt_6_1_.next();
         if (lvt_7_1_.func_178383_a() == p_178395_2_) {
            int lvt_8_1_ = p_178395_2_.func_178369_d() ? p_178395_4_ : p_178395_3_;
            if (lvt_7_1_.func_178381_d() == lvt_8_1_) {
               lvt_5_1_ = lvt_7_1_;
               break;
            }
         }
      }

      int lvt_6_2_ = p_178395_2_.func_178369_d() ? p_178395_4_ : p_178395_3_;
      int lvt_7_2_ = p_178395_2_.func_178369_d() ? p_178395_3_ : p_178395_4_;
      if (lvt_5_1_ == null) {
         p_178395_1_.add(new ItemModelGenerator.Span(p_178395_2_, lvt_7_2_, lvt_6_2_));
      } else {
         lvt_5_1_.func_178382_a(lvt_7_2_);
      }

   }

   private boolean func_178391_a(int[] p_178391_1_, int p_178391_2_, int p_178391_3_, int p_178391_4_, int p_178391_5_) {
      if (p_178391_2_ >= 0 && p_178391_3_ >= 0 && p_178391_2_ < p_178391_4_ && p_178391_3_ < p_178391_5_) {
         return (p_178391_1_[p_178391_3_ * p_178391_4_ + p_178391_2_] >> 24 & 255) == 0;
      } else {
         return true;
      }
   }

   static class Span {
      private final ItemModelGenerator.SpanFacing field_178389_a;
      private int field_178387_b;
      private int field_178388_c;
      private final int field_178386_d;

      public Span(ItemModelGenerator.SpanFacing p_i46216_1_, int p_i46216_2_, int p_i46216_3_) {
         this.field_178389_a = p_i46216_1_;
         this.field_178387_b = p_i46216_2_;
         this.field_178388_c = p_i46216_2_;
         this.field_178386_d = p_i46216_3_;
      }

      public void func_178382_a(int p_178382_1_) {
         if (p_178382_1_ < this.field_178387_b) {
            this.field_178387_b = p_178382_1_;
         } else if (p_178382_1_ > this.field_178388_c) {
            this.field_178388_c = p_178382_1_;
         }

      }

      public ItemModelGenerator.SpanFacing func_178383_a() {
         return this.field_178389_a;
      }

      public int func_178385_b() {
         return this.field_178387_b;
      }

      public int func_178384_c() {
         return this.field_178388_c;
      }

      public int func_178381_d() {
         return this.field_178386_d;
      }
   }

   static enum SpanFacing {
      UP(EnumFacing.UP, 0, -1),
      DOWN(EnumFacing.DOWN, 0, 1),
      LEFT(EnumFacing.EAST, -1, 0),
      RIGHT(EnumFacing.WEST, 1, 0);

      private final EnumFacing field_178376_e;
      private final int field_178373_f;
      private final int field_178374_g;

      private SpanFacing(EnumFacing p_i46215_3_, int p_i46215_4_, int p_i46215_5_) {
         this.field_178376_e = p_i46215_3_;
         this.field_178373_f = p_i46215_4_;
         this.field_178374_g = p_i46215_5_;
      }

      public EnumFacing func_178367_a() {
         return this.field_178376_e;
      }

      public int func_178372_b() {
         return this.field_178373_f;
      }

      public int func_178371_c() {
         return this.field_178374_g;
      }

      private boolean func_178369_d() {
         return this == DOWN || this == UP;
      }
   }
}
