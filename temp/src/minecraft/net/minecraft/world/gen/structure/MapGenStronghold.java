package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenStronghold extends MapGenStructure {
   private List<BiomeGenBase> field_151546_e;
   private boolean field_75056_f;
   private ChunkCoordIntPair[] field_75057_g;
   private double field_82671_h;
   private int field_82672_i;

   public MapGenStronghold() {
      this.field_75057_g = new ChunkCoordIntPair[3];
      this.field_82671_h = 32.0D;
      this.field_82672_i = 3;
      this.field_151546_e = Lists.newArrayList();
      BiomeGenBase[] lvt_1_1_ = BiomeGenBase.func_150565_n();
      int lvt_2_1_ = lvt_1_1_.length;

      for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_; ++lvt_3_1_) {
         BiomeGenBase lvt_4_1_ = lvt_1_1_[lvt_3_1_];
         if (lvt_4_1_ != null && lvt_4_1_.field_76748_D > 0.0F) {
            this.field_151546_e.add(lvt_4_1_);
         }
      }

   }

   public MapGenStronghold(Map<String, String> p_i2068_1_) {
      this();
      Iterator lvt_2_1_ = p_i2068_1_.entrySet().iterator();

      while(lvt_2_1_.hasNext()) {
         Entry<String, String> lvt_3_1_ = (Entry)lvt_2_1_.next();
         if (((String)lvt_3_1_.getKey()).equals("distance")) {
            this.field_82671_h = MathHelper.func_82713_a((String)lvt_3_1_.getValue(), this.field_82671_h, 1.0D);
         } else if (((String)lvt_3_1_.getKey()).equals("count")) {
            this.field_75057_g = new ChunkCoordIntPair[MathHelper.func_82714_a((String)lvt_3_1_.getValue(), this.field_75057_g.length, 1)];
         } else if (((String)lvt_3_1_.getKey()).equals("spread")) {
            this.field_82672_i = MathHelper.func_82714_a((String)lvt_3_1_.getValue(), this.field_82672_i, 1);
         }
      }

   }

   public String func_143025_a() {
      return "Stronghold";
   }

   protected boolean func_75047_a(int p_75047_1_, int p_75047_2_) {
      if (!this.field_75056_f) {
         Random lvt_3_1_ = new Random();
         lvt_3_1_.setSeed(this.field_75039_c.func_72905_C());
         double lvt_4_1_ = lvt_3_1_.nextDouble() * 3.141592653589793D * 2.0D;
         int lvt_6_1_ = 1;

         for(int lvt_7_1_ = 0; lvt_7_1_ < this.field_75057_g.length; ++lvt_7_1_) {
            double lvt_8_1_ = (1.25D * (double)lvt_6_1_ + lvt_3_1_.nextDouble()) * this.field_82671_h * (double)lvt_6_1_;
            int lvt_10_1_ = (int)Math.round(Math.cos(lvt_4_1_) * lvt_8_1_);
            int lvt_11_1_ = (int)Math.round(Math.sin(lvt_4_1_) * lvt_8_1_);
            BlockPos lvt_12_1_ = this.field_75039_c.func_72959_q().func_180630_a((lvt_10_1_ << 4) + 8, (lvt_11_1_ << 4) + 8, 112, this.field_151546_e, lvt_3_1_);
            if (lvt_12_1_ != null) {
               lvt_10_1_ = lvt_12_1_.func_177958_n() >> 4;
               lvt_11_1_ = lvt_12_1_.func_177952_p() >> 4;
            }

            this.field_75057_g[lvt_7_1_] = new ChunkCoordIntPair(lvt_10_1_, lvt_11_1_);
            lvt_4_1_ += 6.283185307179586D * (double)lvt_6_1_ / (double)this.field_82672_i;
            if (lvt_7_1_ == this.field_82672_i) {
               lvt_6_1_ += 2 + lvt_3_1_.nextInt(5);
               this.field_82672_i += 1 + lvt_3_1_.nextInt(2);
            }
         }

         this.field_75056_f = true;
      }

      ChunkCoordIntPair[] lvt_3_2_ = this.field_75057_g;
      int lvt_4_2_ = lvt_3_2_.length;

      for(int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_2_; ++lvt_5_1_) {
         ChunkCoordIntPair lvt_6_2_ = lvt_3_2_[lvt_5_1_];
         if (p_75047_1_ == lvt_6_2_.field_77276_a && p_75047_2_ == lvt_6_2_.field_77275_b) {
            return true;
         }
      }

      return false;
   }

   protected List<BlockPos> func_75052_o_() {
      List<BlockPos> lvt_1_1_ = Lists.newArrayList();
      ChunkCoordIntPair[] lvt_2_1_ = this.field_75057_g;
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         ChunkCoordIntPair lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         if (lvt_5_1_ != null) {
            lvt_1_1_.add(lvt_5_1_.func_180619_a(64));
         }
      }

      return lvt_1_1_;
   }

   protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_) {
      MapGenStronghold.Start lvt_3_1_;
      for(lvt_3_1_ = new MapGenStronghold.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_); lvt_3_1_.func_75073_b().isEmpty() || ((StructureStrongholdPieces.Stairs2)lvt_3_1_.func_75073_b().get(0)).field_75025_b == null; lvt_3_1_ = new MapGenStronghold.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_)) {
      }

      return lvt_3_1_;
   }

   public static class Start extends StructureStart {
      public Start() {
      }

      public Start(World p_i2067_1_, Random p_i2067_2_, int p_i2067_3_, int p_i2067_4_) {
         super(p_i2067_3_, p_i2067_4_);
         StructureStrongholdPieces.func_75198_a();
         StructureStrongholdPieces.Stairs2 lvt_5_1_ = new StructureStrongholdPieces.Stairs2(0, p_i2067_2_, (p_i2067_3_ << 4) + 2, (p_i2067_4_ << 4) + 2);
         this.field_75075_a.add(lvt_5_1_);
         lvt_5_1_.func_74861_a(lvt_5_1_, this.field_75075_a, p_i2067_2_);
         List lvt_6_1_ = lvt_5_1_.field_75026_c;

         while(!lvt_6_1_.isEmpty()) {
            int lvt_7_1_ = p_i2067_2_.nextInt(lvt_6_1_.size());
            StructureComponent lvt_8_1_ = (StructureComponent)lvt_6_1_.remove(lvt_7_1_);
            lvt_8_1_.func_74861_a(lvt_5_1_, this.field_75075_a, p_i2067_2_);
         }

         this.func_75072_c();
         this.func_75067_a(p_i2067_1_, p_i2067_2_, 10);
      }
   }
}
