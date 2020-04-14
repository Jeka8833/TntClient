package net.minecraft.world.gen.structure;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

public abstract class MapGenStructure extends MapGenBase {
   private MapGenStructureData field_143029_e;
   protected Map<Long, StructureStart> field_75053_d = Maps.newHashMap();

   public abstract String func_143025_a();

   protected final void func_180701_a(World p_180701_1_, final int p_180701_2_, final int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {
      this.func_143027_a(p_180701_1_);
      if (!this.field_75053_d.containsKey(ChunkCoordIntPair.func_77272_a(p_180701_2_, p_180701_3_))) {
         this.field_75038_b.nextInt();

         try {
            if (this.func_75047_a(p_180701_2_, p_180701_3_)) {
               StructureStart lvt_7_1_ = this.func_75049_b(p_180701_2_, p_180701_3_);
               this.field_75053_d.put(ChunkCoordIntPair.func_77272_a(p_180701_2_, p_180701_3_), lvt_7_1_);
               this.func_143026_a(p_180701_2_, p_180701_3_, lvt_7_1_);
            }

         } catch (Throwable var10) {
            CrashReport lvt_8_1_ = CrashReport.func_85055_a(var10, "Exception preparing structure feature");
            CrashReportCategory lvt_9_1_ = lvt_8_1_.func_85058_a("Feature being prepared");
            lvt_9_1_.func_71500_a("Is feature chunk", new Callable<String>() {
               public String call() throws Exception {
                  return MapGenStructure.this.func_75047_a(p_180701_2_, p_180701_3_) ? "True" : "False";
               }
            });
            lvt_9_1_.func_71507_a("Chunk location", String.format("%d,%d", p_180701_2_, p_180701_3_));
            lvt_9_1_.func_71500_a("Chunk pos hash", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf(ChunkCoordIntPair.func_77272_a(p_180701_2_, p_180701_3_));
               }
            });
            lvt_9_1_.func_71500_a("Structure type", new Callable<String>() {
               public String call() throws Exception {
                  return MapGenStructure.this.getClass().getCanonicalName();
               }
            });
            throw new ReportedException(lvt_8_1_);
         }
      }
   }

   public boolean func_175794_a(World p_175794_1_, Random p_175794_2_, ChunkCoordIntPair p_175794_3_) {
      this.func_143027_a(p_175794_1_);
      int lvt_4_1_ = (p_175794_3_.field_77276_a << 4) + 8;
      int lvt_5_1_ = (p_175794_3_.field_77275_b << 4) + 8;
      boolean lvt_6_1_ = false;
      Iterator lvt_7_1_ = this.field_75053_d.values().iterator();

      while(lvt_7_1_.hasNext()) {
         StructureStart lvt_8_1_ = (StructureStart)lvt_7_1_.next();
         if (lvt_8_1_.func_75069_d() && lvt_8_1_.func_175788_a(p_175794_3_) && lvt_8_1_.func_75071_a().func_78885_a(lvt_4_1_, lvt_5_1_, lvt_4_1_ + 15, lvt_5_1_ + 15)) {
            lvt_8_1_.func_75068_a(p_175794_1_, p_175794_2_, new StructureBoundingBox(lvt_4_1_, lvt_5_1_, lvt_4_1_ + 15, lvt_5_1_ + 15));
            lvt_8_1_.func_175787_b(p_175794_3_);
            lvt_6_1_ = true;
            this.func_143026_a(lvt_8_1_.func_143019_e(), lvt_8_1_.func_143018_f(), lvt_8_1_);
         }
      }

      return lvt_6_1_;
   }

   public boolean func_175795_b(BlockPos p_175795_1_) {
      this.func_143027_a(this.field_75039_c);
      return this.func_175797_c(p_175795_1_) != null;
   }

   protected StructureStart func_175797_c(BlockPos p_175797_1_) {
      Iterator lvt_2_1_ = this.field_75053_d.values().iterator();

      while(true) {
         StructureStart lvt_3_1_;
         do {
            do {
               if (!lvt_2_1_.hasNext()) {
                  return null;
               }

               lvt_3_1_ = (StructureStart)lvt_2_1_.next();
            } while(!lvt_3_1_.func_75069_d());
         } while(!lvt_3_1_.func_75071_a().func_175898_b(p_175797_1_));

         Iterator lvt_4_1_ = lvt_3_1_.func_75073_b().iterator();

         while(lvt_4_1_.hasNext()) {
            StructureComponent lvt_5_1_ = (StructureComponent)lvt_4_1_.next();
            if (lvt_5_1_.func_74874_b().func_175898_b(p_175797_1_)) {
               return lvt_3_1_;
            }
         }
      }
   }

   public boolean func_175796_a(World p_175796_1_, BlockPos p_175796_2_) {
      this.func_143027_a(p_175796_1_);
      Iterator lvt_3_1_ = this.field_75053_d.values().iterator();

      StructureStart lvt_4_1_;
      do {
         if (!lvt_3_1_.hasNext()) {
            return false;
         }

         lvt_4_1_ = (StructureStart)lvt_3_1_.next();
      } while(!lvt_4_1_.func_75069_d() || !lvt_4_1_.func_75071_a().func_175898_b(p_175796_2_));

      return true;
   }

   public BlockPos func_180706_b(World p_180706_1_, BlockPos p_180706_2_) {
      this.field_75039_c = p_180706_1_;
      this.func_143027_a(p_180706_1_);
      this.field_75038_b.setSeed(p_180706_1_.func_72905_C());
      long lvt_3_1_ = this.field_75038_b.nextLong();
      long lvt_5_1_ = this.field_75038_b.nextLong();
      long lvt_7_1_ = (long)(p_180706_2_.func_177958_n() >> 4) * lvt_3_1_;
      long lvt_9_1_ = (long)(p_180706_2_.func_177952_p() >> 4) * lvt_5_1_;
      this.field_75038_b.setSeed(lvt_7_1_ ^ lvt_9_1_ ^ p_180706_1_.func_72905_C());
      this.func_180701_a(p_180706_1_, p_180706_2_.func_177958_n() >> 4, p_180706_2_.func_177952_p() >> 4, 0, 0, (ChunkPrimer)null);
      double lvt_11_1_ = Double.MAX_VALUE;
      BlockPos lvt_13_1_ = null;
      Iterator lvt_14_1_ = this.field_75053_d.values().iterator();

      BlockPos lvt_17_2_;
      double lvt_18_2_;
      while(lvt_14_1_.hasNext()) {
         StructureStart lvt_15_1_ = (StructureStart)lvt_14_1_.next();
         if (lvt_15_1_.func_75069_d()) {
            StructureComponent lvt_16_1_ = (StructureComponent)lvt_15_1_.func_75073_b().get(0);
            lvt_17_2_ = lvt_16_1_.func_180776_a();
            lvt_18_2_ = lvt_17_2_.func_177951_i(p_180706_2_);
            if (lvt_18_2_ < lvt_11_1_) {
               lvt_11_1_ = lvt_18_2_;
               lvt_13_1_ = lvt_17_2_;
            }
         }
      }

      if (lvt_13_1_ != null) {
         return lvt_13_1_;
      } else {
         List<BlockPos> lvt_14_2_ = this.func_75052_o_();
         if (lvt_14_2_ != null) {
            BlockPos lvt_15_2_ = null;
            Iterator lvt_16_2_ = lvt_14_2_.iterator();

            while(lvt_16_2_.hasNext()) {
               lvt_17_2_ = (BlockPos)lvt_16_2_.next();
               lvt_18_2_ = lvt_17_2_.func_177951_i(p_180706_2_);
               if (lvt_18_2_ < lvt_11_1_) {
                  lvt_11_1_ = lvt_18_2_;
                  lvt_15_2_ = lvt_17_2_;
               }
            }

            return lvt_15_2_;
         } else {
            return null;
         }
      }
   }

   protected List<BlockPos> func_75052_o_() {
      return null;
   }

   private void func_143027_a(World p_143027_1_) {
      if (this.field_143029_e == null) {
         this.field_143029_e = (MapGenStructureData)p_143027_1_.func_72943_a(MapGenStructureData.class, this.func_143025_a());
         if (this.field_143029_e == null) {
            this.field_143029_e = new MapGenStructureData(this.func_143025_a());
            p_143027_1_.func_72823_a(this.func_143025_a(), this.field_143029_e);
         } else {
            NBTTagCompound lvt_2_1_ = this.field_143029_e.func_143041_a();
            Iterator lvt_3_1_ = lvt_2_1_.func_150296_c().iterator();

            while(lvt_3_1_.hasNext()) {
               String lvt_4_1_ = (String)lvt_3_1_.next();
               NBTBase lvt_5_1_ = lvt_2_1_.func_74781_a(lvt_4_1_);
               if (lvt_5_1_.func_74732_a() == 10) {
                  NBTTagCompound lvt_6_1_ = (NBTTagCompound)lvt_5_1_;
                  if (lvt_6_1_.func_74764_b("ChunkX") && lvt_6_1_.func_74764_b("ChunkZ")) {
                     int lvt_7_1_ = lvt_6_1_.func_74762_e("ChunkX");
                     int lvt_8_1_ = lvt_6_1_.func_74762_e("ChunkZ");
                     StructureStart lvt_9_1_ = MapGenStructureIO.func_143035_a(lvt_6_1_, p_143027_1_);
                     if (lvt_9_1_ != null) {
                        this.field_75053_d.put(ChunkCoordIntPair.func_77272_a(lvt_7_1_, lvt_8_1_), lvt_9_1_);
                     }
                  }
               }
            }
         }
      }

   }

   private void func_143026_a(int p_143026_1_, int p_143026_2_, StructureStart p_143026_3_) {
      this.field_143029_e.func_143043_a(p_143026_3_.func_143021_a(p_143026_1_, p_143026_2_), p_143026_1_, p_143026_2_);
      this.field_143029_e.func_76185_a();
   }

   protected abstract boolean func_75047_a(int var1, int var2);

   protected abstract StructureStart func_75049_b(int var1, int var2);
}
