package net.minecraft.world.gen.layer;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;

public abstract class GenLayer {
   private long field_75907_b;
   protected GenLayer field_75909_a;
   private long field_75908_c;
   protected long field_75906_d;

   public static GenLayer[] func_180781_a(long p_180781_0_, WorldType p_180781_2_, String p_180781_3_) {
      GenLayer lvt_4_1_ = new GenLayerIsland(1L);
      GenLayer lvt_4_1_ = new GenLayerFuzzyZoom(2000L, lvt_4_1_);
      GenLayer lvt_4_1_ = new GenLayerAddIsland(1L, lvt_4_1_);
      GenLayer lvt_4_1_ = new GenLayerZoom(2001L, lvt_4_1_);
      lvt_4_1_ = new GenLayerAddIsland(2L, lvt_4_1_);
      lvt_4_1_ = new GenLayerAddIsland(50L, lvt_4_1_);
      lvt_4_1_ = new GenLayerAddIsland(70L, lvt_4_1_);
      GenLayer lvt_4_1_ = new GenLayerRemoveTooMuchOcean(2L, lvt_4_1_);
      GenLayer lvt_4_1_ = new GenLayerAddSnow(2L, lvt_4_1_);
      lvt_4_1_ = new GenLayerAddIsland(3L, lvt_4_1_);
      GenLayer lvt_4_1_ = new GenLayerEdge(2L, lvt_4_1_, GenLayerEdge.Mode.COOL_WARM);
      lvt_4_1_ = new GenLayerEdge(2L, lvt_4_1_, GenLayerEdge.Mode.HEAT_ICE);
      lvt_4_1_ = new GenLayerEdge(3L, lvt_4_1_, GenLayerEdge.Mode.SPECIAL);
      lvt_4_1_ = new GenLayerZoom(2002L, lvt_4_1_);
      lvt_4_1_ = new GenLayerZoom(2003L, lvt_4_1_);
      lvt_4_1_ = new GenLayerAddIsland(4L, lvt_4_1_);
      GenLayer lvt_4_1_ = new GenLayerAddMushroomIsland(5L, lvt_4_1_);
      GenLayer lvt_4_1_ = new GenLayerDeepOcean(4L, lvt_4_1_);
      GenLayer lvt_4_1_ = GenLayerZoom.func_75915_a(1000L, lvt_4_1_, 0);
      ChunkProviderSettings lvt_5_1_ = null;
      int lvt_6_1_ = 4;
      int lvt_7_1_ = lvt_6_1_;
      if (p_180781_2_ == WorldType.field_180271_f && p_180781_3_.length() > 0) {
         lvt_5_1_ = ChunkProviderSettings.Factory.func_177865_a(p_180781_3_).func_177864_b();
         lvt_6_1_ = lvt_5_1_.field_177780_G;
         lvt_7_1_ = lvt_5_1_.field_177788_H;
      }

      if (p_180781_2_ == WorldType.field_77135_d) {
         lvt_6_1_ = 6;
      }

      GenLayer lvt_8_1_ = GenLayerZoom.func_75915_a(1000L, lvt_4_1_, 0);
      GenLayer lvt_8_1_ = new GenLayerRiverInit(100L, lvt_8_1_);
      GenLayer lvt_9_1_ = new GenLayerBiome(200L, lvt_4_1_, p_180781_2_, p_180781_3_);
      GenLayer lvt_9_1_ = GenLayerZoom.func_75915_a(1000L, lvt_9_1_, 2);
      GenLayer lvt_9_1_ = new GenLayerBiomeEdge(1000L, lvt_9_1_);
      GenLayer lvt_10_1_ = GenLayerZoom.func_75915_a(1000L, lvt_8_1_, 2);
      GenLayer lvt_9_1_ = new GenLayerHills(1000L, lvt_9_1_, lvt_10_1_);
      lvt_8_1_ = GenLayerZoom.func_75915_a(1000L, lvt_8_1_, 2);
      lvt_8_1_ = GenLayerZoom.func_75915_a(1000L, lvt_8_1_, lvt_7_1_);
      GenLayer lvt_8_1_ = new GenLayerRiver(1L, lvt_8_1_);
      GenLayer lvt_8_1_ = new GenLayerSmooth(1000L, lvt_8_1_);
      GenLayer lvt_9_1_ = new GenLayerRareBiome(1001L, lvt_9_1_);

      for(int lvt_11_1_ = 0; lvt_11_1_ < lvt_6_1_; ++lvt_11_1_) {
         lvt_9_1_ = new GenLayerZoom((long)(1000 + lvt_11_1_), (GenLayer)lvt_9_1_);
         if (lvt_11_1_ == 0) {
            lvt_9_1_ = new GenLayerAddIsland(3L, (GenLayer)lvt_9_1_);
         }

         if (lvt_11_1_ == 1 || lvt_6_1_ == 1) {
            lvt_9_1_ = new GenLayerShore(1000L, (GenLayer)lvt_9_1_);
         }
      }

      GenLayer lvt_9_1_ = new GenLayerSmooth(1000L, (GenLayer)lvt_9_1_);
      GenLayer lvt_9_1_ = new GenLayerRiverMix(100L, lvt_9_1_, lvt_8_1_);
      GenLayer lvt_12_1_ = new GenLayerVoronoiZoom(10L, lvt_9_1_);
      lvt_9_1_.func_75905_a(p_180781_0_);
      lvt_12_1_.func_75905_a(p_180781_0_);
      return new GenLayer[]{lvt_9_1_, lvt_12_1_, lvt_9_1_};
   }

   public GenLayer(long p_i2125_1_) {
      this.field_75906_d = p_i2125_1_;
      this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
      this.field_75906_d += p_i2125_1_;
      this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
      this.field_75906_d += p_i2125_1_;
      this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
      this.field_75906_d += p_i2125_1_;
   }

   public void func_75905_a(long p_75905_1_) {
      this.field_75907_b = p_75905_1_;
      if (this.field_75909_a != null) {
         this.field_75909_a.func_75905_a(p_75905_1_);
      }

      this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
      this.field_75907_b += this.field_75906_d;
      this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
      this.field_75907_b += this.field_75906_d;
      this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
      this.field_75907_b += this.field_75906_d;
   }

   public void func_75903_a(long p_75903_1_, long p_75903_3_) {
      this.field_75908_c = this.field_75907_b;
      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += p_75903_1_;
      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += p_75903_3_;
      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += p_75903_1_;
      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += p_75903_3_;
   }

   protected int func_75902_a(int p_75902_1_) {
      int lvt_2_1_ = (int)((this.field_75908_c >> 24) % (long)p_75902_1_);
      if (lvt_2_1_ < 0) {
         lvt_2_1_ += p_75902_1_;
      }

      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += this.field_75907_b;
      return lvt_2_1_;
   }

   public abstract int[] func_75904_a(int var1, int var2, int var3, int var4);

   protected static boolean func_151616_a(int p_151616_0_, int p_151616_1_) {
      if (p_151616_0_ == p_151616_1_) {
         return true;
      } else if (p_151616_0_ != BiomeGenBase.field_150607_aa.field_76756_M && p_151616_0_ != BiomeGenBase.field_150608_ab.field_76756_M) {
         final BiomeGenBase lvt_2_1_ = BiomeGenBase.func_150568_d(p_151616_0_);
         final BiomeGenBase lvt_3_1_ = BiomeGenBase.func_150568_d(p_151616_1_);

         try {
            return lvt_2_1_ != null && lvt_3_1_ != null ? lvt_2_1_.func_150569_a(lvt_3_1_) : false;
         } catch (Throwable var7) {
            CrashReport lvt_5_1_ = CrashReport.func_85055_a(var7, "Comparing biomes");
            CrashReportCategory lvt_6_1_ = lvt_5_1_.func_85058_a("Biomes being compared");
            lvt_6_1_.func_71507_a("Biome A ID", p_151616_0_);
            lvt_6_1_.func_71507_a("Biome B ID", p_151616_1_);
            lvt_6_1_.func_71500_a("Biome A", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf(lvt_2_1_);
               }
            });
            lvt_6_1_.func_71500_a("Biome B", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf(lvt_3_1_);
               }
            });
            throw new ReportedException(lvt_5_1_);
         }
      } else {
         return p_151616_1_ == BiomeGenBase.field_150607_aa.field_76756_M || p_151616_1_ == BiomeGenBase.field_150608_ab.field_76756_M;
      }
   }

   protected static boolean func_151618_b(int p_151618_0_) {
      return p_151618_0_ == BiomeGenBase.field_76771_b.field_76756_M || p_151618_0_ == BiomeGenBase.field_150575_M.field_76756_M || p_151618_0_ == BiomeGenBase.field_76776_l.field_76756_M;
   }

   protected int func_151619_a(int... p_151619_1_) {
      return p_151619_1_[this.func_75902_a(p_151619_1_.length)];
   }

   protected int func_151617_b(int p_151617_1_, int p_151617_2_, int p_151617_3_, int p_151617_4_) {
      if (p_151617_2_ == p_151617_3_ && p_151617_3_ == p_151617_4_) {
         return p_151617_2_;
      } else if (p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_3_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_4_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_3_ && p_151617_1_ == p_151617_4_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_2_ && p_151617_3_ != p_151617_4_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_3_ && p_151617_2_ != p_151617_4_) {
         return p_151617_1_;
      } else if (p_151617_1_ == p_151617_4_ && p_151617_2_ != p_151617_3_) {
         return p_151617_1_;
      } else if (p_151617_2_ == p_151617_3_ && p_151617_1_ != p_151617_4_) {
         return p_151617_2_;
      } else if (p_151617_2_ == p_151617_4_ && p_151617_1_ != p_151617_3_) {
         return p_151617_2_;
      } else {
         return p_151617_3_ == p_151617_4_ && p_151617_1_ != p_151617_2_ ? p_151617_3_ : this.func_151619_a(p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_);
      }
   }
}
