package net.minecraft.block.state.pattern;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import java.util.Iterator;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class BlockPattern {
   private final Predicate<BlockWorldState>[][][] field_177689_a;
   private final int field_177687_b;
   private final int field_177688_c;
   private final int field_177686_d;

   public BlockPattern(Predicate<BlockWorldState>[][][] p_i45657_1_) {
      this.field_177689_a = p_i45657_1_;
      this.field_177687_b = p_i45657_1_.length;
      if (this.field_177687_b > 0) {
         this.field_177688_c = p_i45657_1_[0].length;
         if (this.field_177688_c > 0) {
            this.field_177686_d = p_i45657_1_[0][0].length;
         } else {
            this.field_177686_d = 0;
         }
      } else {
         this.field_177688_c = 0;
         this.field_177686_d = 0;
      }

   }

   public int func_177685_b() {
      return this.field_177688_c;
   }

   public int func_177684_c() {
      return this.field_177686_d;
   }

   private BlockPattern.PatternHelper func_177682_a(BlockPos p_177682_1_, EnumFacing p_177682_2_, EnumFacing p_177682_3_, LoadingCache<BlockPos, BlockWorldState> p_177682_4_) {
      for(int lvt_5_1_ = 0; lvt_5_1_ < this.field_177686_d; ++lvt_5_1_) {
         for(int lvt_6_1_ = 0; lvt_6_1_ < this.field_177688_c; ++lvt_6_1_) {
            for(int lvt_7_1_ = 0; lvt_7_1_ < this.field_177687_b; ++lvt_7_1_) {
               if (!this.field_177689_a[lvt_7_1_][lvt_6_1_][lvt_5_1_].apply(p_177682_4_.getUnchecked(func_177683_a(p_177682_1_, p_177682_2_, p_177682_3_, lvt_5_1_, lvt_6_1_, lvt_7_1_)))) {
                  return null;
               }
            }
         }
      }

      return new BlockPattern.PatternHelper(p_177682_1_, p_177682_2_, p_177682_3_, p_177682_4_, this.field_177686_d, this.field_177688_c, this.field_177687_b);
   }

   public BlockPattern.PatternHelper func_177681_a(World p_177681_1_, BlockPos p_177681_2_) {
      LoadingCache<BlockPos, BlockWorldState> lvt_3_1_ = func_181627_a(p_177681_1_, false);
      int lvt_4_1_ = Math.max(Math.max(this.field_177686_d, this.field_177688_c), this.field_177687_b);
      Iterator lvt_5_1_ = BlockPos.func_177980_a(p_177681_2_, p_177681_2_.func_177982_a(lvt_4_1_ - 1, lvt_4_1_ - 1, lvt_4_1_ - 1)).iterator();

      while(lvt_5_1_.hasNext()) {
         BlockPos lvt_6_1_ = (BlockPos)lvt_5_1_.next();
         EnumFacing[] lvt_7_1_ = EnumFacing.values();
         int lvt_8_1_ = lvt_7_1_.length;

         for(int lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
            EnumFacing lvt_10_1_ = lvt_7_1_[lvt_9_1_];
            EnumFacing[] lvt_11_1_ = EnumFacing.values();
            int lvt_12_1_ = lvt_11_1_.length;

            for(int lvt_13_1_ = 0; lvt_13_1_ < lvt_12_1_; ++lvt_13_1_) {
               EnumFacing lvt_14_1_ = lvt_11_1_[lvt_13_1_];
               if (lvt_14_1_ != lvt_10_1_ && lvt_14_1_ != lvt_10_1_.func_176734_d()) {
                  BlockPattern.PatternHelper lvt_15_1_ = this.func_177682_a(lvt_6_1_, lvt_10_1_, lvt_14_1_, lvt_3_1_);
                  if (lvt_15_1_ != null) {
                     return lvt_15_1_;
                  }
               }
            }
         }
      }

      return null;
   }

   public static LoadingCache<BlockPos, BlockWorldState> func_181627_a(World p_181627_0_, boolean p_181627_1_) {
      return CacheBuilder.newBuilder().build(new BlockPattern.CacheLoader(p_181627_0_, p_181627_1_));
   }

   protected static BlockPos func_177683_a(BlockPos p_177683_0_, EnumFacing p_177683_1_, EnumFacing p_177683_2_, int p_177683_3_, int p_177683_4_, int p_177683_5_) {
      if (p_177683_1_ != p_177683_2_ && p_177683_1_ != p_177683_2_.func_176734_d()) {
         Vec3i lvt_6_1_ = new Vec3i(p_177683_1_.func_82601_c(), p_177683_1_.func_96559_d(), p_177683_1_.func_82599_e());
         Vec3i lvt_7_1_ = new Vec3i(p_177683_2_.func_82601_c(), p_177683_2_.func_96559_d(), p_177683_2_.func_82599_e());
         Vec3i lvt_8_1_ = lvt_6_1_.func_177955_d(lvt_7_1_);
         return p_177683_0_.func_177982_a(lvt_7_1_.func_177958_n() * -p_177683_4_ + lvt_8_1_.func_177958_n() * p_177683_3_ + lvt_6_1_.func_177958_n() * p_177683_5_, lvt_7_1_.func_177956_o() * -p_177683_4_ + lvt_8_1_.func_177956_o() * p_177683_3_ + lvt_6_1_.func_177956_o() * p_177683_5_, lvt_7_1_.func_177952_p() * -p_177683_4_ + lvt_8_1_.func_177952_p() * p_177683_3_ + lvt_6_1_.func_177952_p() * p_177683_5_);
      } else {
         throw new IllegalArgumentException("Invalid forwards & up combination");
      }
   }

   public static class PatternHelper {
      private final BlockPos field_177674_a;
      private final EnumFacing field_177672_b;
      private final EnumFacing field_177673_c;
      private final LoadingCache<BlockPos, BlockWorldState> field_177671_d;
      private final int field_181120_e;
      private final int field_181121_f;
      private final int field_181122_g;

      public PatternHelper(BlockPos p_i46378_1_, EnumFacing p_i46378_2_, EnumFacing p_i46378_3_, LoadingCache<BlockPos, BlockWorldState> p_i46378_4_, int p_i46378_5_, int p_i46378_6_, int p_i46378_7_) {
         this.field_177674_a = p_i46378_1_;
         this.field_177672_b = p_i46378_2_;
         this.field_177673_c = p_i46378_3_;
         this.field_177671_d = p_i46378_4_;
         this.field_181120_e = p_i46378_5_;
         this.field_181121_f = p_i46378_6_;
         this.field_181122_g = p_i46378_7_;
      }

      public BlockPos func_181117_a() {
         return this.field_177674_a;
      }

      public EnumFacing func_177669_b() {
         return this.field_177672_b;
      }

      public EnumFacing func_177668_c() {
         return this.field_177673_c;
      }

      public int func_181118_d() {
         return this.field_181120_e;
      }

      public int func_181119_e() {
         return this.field_181121_f;
      }

      public BlockWorldState func_177670_a(int p_177670_1_, int p_177670_2_, int p_177670_3_) {
         return (BlockWorldState)this.field_177671_d.getUnchecked(BlockPattern.func_177683_a(this.field_177674_a, this.func_177669_b(), this.func_177668_c(), p_177670_1_, p_177670_2_, p_177670_3_));
      }

      public String toString() {
         return Objects.toStringHelper((Object)this).add("up", this.field_177673_c).add("forwards", this.field_177672_b).add("frontTopLeft", this.field_177674_a).toString();
      }
   }

   static class CacheLoader extends com.google.common.cache.CacheLoader<BlockPos, BlockWorldState> {
      private final World field_177680_a;
      private final boolean field_181626_b;

      public CacheLoader(World p_i46460_1_, boolean p_i46460_2_) {
         this.field_177680_a = p_i46460_1_;
         this.field_181626_b = p_i46460_2_;
      }

      public BlockWorldState load(BlockPos p_load_1_) throws Exception {
         return new BlockWorldState(this.field_177680_a, p_load_1_, this.field_181626_b);
      }
   }
}
