package net.minecraft.block.state;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockPistonStructureHelper {
   private final World field_177261_a;
   private final BlockPos field_177259_b;
   private final BlockPos field_177260_c;
   private final EnumFacing field_177257_d;
   private final List<BlockPos> field_177258_e = Lists.newArrayList();
   private final List<BlockPos> field_177256_f = Lists.newArrayList();

   public BlockPistonStructureHelper(World p_i45664_1_, BlockPos p_i45664_2_, EnumFacing p_i45664_3_, boolean p_i45664_4_) {
      this.field_177261_a = p_i45664_1_;
      this.field_177259_b = p_i45664_2_;
      if (p_i45664_4_) {
         this.field_177257_d = p_i45664_3_;
         this.field_177260_c = p_i45664_2_.func_177972_a(p_i45664_3_);
      } else {
         this.field_177257_d = p_i45664_3_.func_176734_d();
         this.field_177260_c = p_i45664_2_.func_177967_a(p_i45664_3_, 2);
      }

   }

   public boolean func_177253_a() {
      this.field_177258_e.clear();
      this.field_177256_f.clear();
      Block lvt_1_1_ = this.field_177261_a.func_180495_p(this.field_177260_c).func_177230_c();
      if (!BlockPistonBase.func_180696_a(lvt_1_1_, this.field_177261_a, this.field_177260_c, this.field_177257_d, false)) {
         if (lvt_1_1_.func_149656_h() != 1) {
            return false;
         } else {
            this.field_177256_f.add(this.field_177260_c);
            return true;
         }
      } else if (!this.func_177251_a(this.field_177260_c)) {
         return false;
      } else {
         for(int lvt_2_1_ = 0; lvt_2_1_ < this.field_177258_e.size(); ++lvt_2_1_) {
            BlockPos lvt_3_1_ = (BlockPos)this.field_177258_e.get(lvt_2_1_);
            if (this.field_177261_a.func_180495_p(lvt_3_1_).func_177230_c() == Blocks.field_180399_cE && !this.func_177250_b(lvt_3_1_)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean func_177251_a(BlockPos p_177251_1_) {
      Block lvt_2_1_ = this.field_177261_a.func_180495_p(p_177251_1_).func_177230_c();
      if (lvt_2_1_.func_149688_o() == Material.field_151579_a) {
         return true;
      } else if (!BlockPistonBase.func_180696_a(lvt_2_1_, this.field_177261_a, p_177251_1_, this.field_177257_d, false)) {
         return true;
      } else if (p_177251_1_.equals(this.field_177259_b)) {
         return true;
      } else if (this.field_177258_e.contains(p_177251_1_)) {
         return true;
      } else {
         int lvt_3_1_ = 1;
         if (lvt_3_1_ + this.field_177258_e.size() > 12) {
            return false;
         } else {
            while(lvt_2_1_ == Blocks.field_180399_cE) {
               BlockPos lvt_4_1_ = p_177251_1_.func_177967_a(this.field_177257_d.func_176734_d(), lvt_3_1_);
               lvt_2_1_ = this.field_177261_a.func_180495_p(lvt_4_1_).func_177230_c();
               if (lvt_2_1_.func_149688_o() == Material.field_151579_a || !BlockPistonBase.func_180696_a(lvt_2_1_, this.field_177261_a, lvt_4_1_, this.field_177257_d, false) || lvt_4_1_.equals(this.field_177259_b)) {
                  break;
               }

               ++lvt_3_1_;
               if (lvt_3_1_ + this.field_177258_e.size() > 12) {
                  return false;
               }
            }

            int lvt_4_2_ = 0;

            int lvt_5_2_;
            for(lvt_5_2_ = lvt_3_1_ - 1; lvt_5_2_ >= 0; --lvt_5_2_) {
               this.field_177258_e.add(p_177251_1_.func_177967_a(this.field_177257_d.func_176734_d(), lvt_5_2_));
               ++lvt_4_2_;
            }

            lvt_5_2_ = 1;

            while(true) {
               BlockPos lvt_6_1_ = p_177251_1_.func_177967_a(this.field_177257_d, lvt_5_2_);
               int lvt_7_1_ = this.field_177258_e.indexOf(lvt_6_1_);
               if (lvt_7_1_ > -1) {
                  this.func_177255_a(lvt_4_2_, lvt_7_1_);

                  for(int lvt_8_1_ = 0; lvt_8_1_ <= lvt_7_1_ + lvt_4_2_; ++lvt_8_1_) {
                     BlockPos lvt_9_1_ = (BlockPos)this.field_177258_e.get(lvt_8_1_);
                     if (this.field_177261_a.func_180495_p(lvt_9_1_).func_177230_c() == Blocks.field_180399_cE && !this.func_177250_b(lvt_9_1_)) {
                        return false;
                     }
                  }

                  return true;
               }

               lvt_2_1_ = this.field_177261_a.func_180495_p(lvt_6_1_).func_177230_c();
               if (lvt_2_1_.func_149688_o() == Material.field_151579_a) {
                  return true;
               }

               if (!BlockPistonBase.func_180696_a(lvt_2_1_, this.field_177261_a, lvt_6_1_, this.field_177257_d, true) || lvt_6_1_.equals(this.field_177259_b)) {
                  return false;
               }

               if (lvt_2_1_.func_149656_h() == 1) {
                  this.field_177256_f.add(lvt_6_1_);
                  return true;
               }

               if (this.field_177258_e.size() >= 12) {
                  return false;
               }

               this.field_177258_e.add(lvt_6_1_);
               ++lvt_4_2_;
               ++lvt_5_2_;
            }
         }
      }
   }

   private void func_177255_a(int p_177255_1_, int p_177255_2_) {
      List<BlockPos> lvt_3_1_ = Lists.newArrayList();
      List<BlockPos> lvt_4_1_ = Lists.newArrayList();
      List<BlockPos> lvt_5_1_ = Lists.newArrayList();
      lvt_3_1_.addAll(this.field_177258_e.subList(0, p_177255_2_));
      lvt_4_1_.addAll(this.field_177258_e.subList(this.field_177258_e.size() - p_177255_1_, this.field_177258_e.size()));
      lvt_5_1_.addAll(this.field_177258_e.subList(p_177255_2_, this.field_177258_e.size() - p_177255_1_));
      this.field_177258_e.clear();
      this.field_177258_e.addAll(lvt_3_1_);
      this.field_177258_e.addAll(lvt_4_1_);
      this.field_177258_e.addAll(lvt_5_1_);
   }

   private boolean func_177250_b(BlockPos p_177250_1_) {
      EnumFacing[] lvt_2_1_ = EnumFacing.values();
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         EnumFacing lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         if (lvt_5_1_.func_176740_k() != this.field_177257_d.func_176740_k() && !this.func_177251_a(p_177250_1_.func_177972_a(lvt_5_1_))) {
            return false;
         }
      }

      return true;
   }

   public List<BlockPos> func_177254_c() {
      return this.field_177258_e;
   }

   public List<BlockPos> func_177252_d() {
      return this.field_177256_f;
   }
}
