package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenScatteredFeature extends MapGenStructure {
   private static final List<BiomeGenBase> field_75061_e;
   private List<BiomeGenBase.SpawnListEntry> field_82668_f;
   private int field_82669_g;
   private int field_82670_h;

   public MapGenScatteredFeature() {
      this.field_82668_f = Lists.newArrayList();
      this.field_82669_g = 32;
      this.field_82670_h = 8;
      this.field_82668_f.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 1, 1, 1));
   }

   public MapGenScatteredFeature(Map<String, String> p_i2061_1_) {
      this();
      Iterator lvt_2_1_ = p_i2061_1_.entrySet().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<String, String> lvt_3_1_ = (Entry)lvt_2_1_.next();
         if (((String)lvt_3_1_.getKey()).equals("distance")) {
            this.field_82669_g = MathHelper.func_82714_a((String)lvt_3_1_.getValue(), this.field_82669_g, this.field_82670_h + 1);
         }
      }

   }

   public String func_143025_a() {
      return "Temple";
   }

   protected boolean func_75047_a(int p_75047_1_, int p_75047_2_) {
      int lvt_3_1_ = p_75047_1_;
      int lvt_4_1_ = p_75047_2_;
      if (p_75047_1_ < 0) {
         p_75047_1_ -= this.field_82669_g - 1;
      }

      if (p_75047_2_ < 0) {
         p_75047_2_ -= this.field_82669_g - 1;
      }

      int lvt_5_1_ = p_75047_1_ / this.field_82669_g;
      int lvt_6_1_ = p_75047_2_ / this.field_82669_g;
      Random lvt_7_1_ = this.field_75039_c.func_72843_D(lvt_5_1_, lvt_6_1_, 14357617);
      lvt_5_1_ *= this.field_82669_g;
      lvt_6_1_ *= this.field_82669_g;
      lvt_5_1_ += lvt_7_1_.nextInt(this.field_82669_g - this.field_82670_h);
      lvt_6_1_ += lvt_7_1_.nextInt(this.field_82669_g - this.field_82670_h);
      if (lvt_3_1_ == lvt_5_1_ && lvt_4_1_ == lvt_6_1_) {
         BiomeGenBase lvt_8_1_ = this.field_75039_c.func_72959_q().func_180631_a(new BlockPos(lvt_3_1_ * 16 + 8, 0, lvt_4_1_ * 16 + 8));
         if (lvt_8_1_ == null) {
            return false;
         }

         Iterator lvt_9_1_ = field_75061_e.iterator();

         while(lvt_9_1_.hasNext()) {
            BiomeGenBase lvt_10_1_ = (BiomeGenBase)lvt_9_1_.next();
            if (lvt_8_1_ == lvt_10_1_) {
               return true;
            }
         }
      }

      return false;
   }

   protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_) {
      return new MapGenScatteredFeature.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_);
   }

   public boolean func_175798_a(BlockPos p_175798_1_) {
      StructureStart lvt_2_1_ = this.func_175797_c(p_175798_1_);
      if (lvt_2_1_ != null && lvt_2_1_ instanceof MapGenScatteredFeature.Start && !lvt_2_1_.field_75075_a.isEmpty()) {
         StructureComponent lvt_3_1_ = (StructureComponent)lvt_2_1_.field_75075_a.getFirst();
         return lvt_3_1_ instanceof ComponentScatteredFeaturePieces.SwampHut;
      } else {
         return false;
      }
   }

   public List<BiomeGenBase.SpawnListEntry> func_82667_a() {
      return this.field_82668_f;
   }

   static {
      field_75061_e = Arrays.asList(BiomeGenBase.field_76769_d, BiomeGenBase.field_76786_s, BiomeGenBase.field_76782_w, BiomeGenBase.field_76792_x, BiomeGenBase.field_76780_h);
   }

   public static class Start extends StructureStart {
      public Start() {
      }

      public Start(World p_i2060_1_, Random p_i2060_2_, int p_i2060_3_, int p_i2060_4_) {
         super(p_i2060_3_, p_i2060_4_);
         BiomeGenBase lvt_5_1_ = p_i2060_1_.func_180494_b(new BlockPos(p_i2060_3_ * 16 + 8, 0, p_i2060_4_ * 16 + 8));
         if (lvt_5_1_ != BiomeGenBase.field_76782_w && lvt_5_1_ != BiomeGenBase.field_76792_x) {
            if (lvt_5_1_ == BiomeGenBase.field_76780_h) {
               ComponentScatteredFeaturePieces.SwampHut lvt_6_2_ = new ComponentScatteredFeaturePieces.SwampHut(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
               this.field_75075_a.add(lvt_6_2_);
            } else if (lvt_5_1_ == BiomeGenBase.field_76769_d || lvt_5_1_ == BiomeGenBase.field_76786_s) {
               ComponentScatteredFeaturePieces.DesertPyramid lvt_6_3_ = new ComponentScatteredFeaturePieces.DesertPyramid(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
               this.field_75075_a.add(lvt_6_3_);
            }
         } else {
            ComponentScatteredFeaturePieces.JunglePyramid lvt_6_1_ = new ComponentScatteredFeaturePieces.JunglePyramid(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
            this.field_75075_a.add(lvt_6_1_);
         }

         this.func_75072_c();
      }
   }
}
