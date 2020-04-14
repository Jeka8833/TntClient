package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BlockSapling extends BlockBush implements IGrowable {
   public static final PropertyEnum<BlockPlanks.EnumType> field_176480_a = PropertyEnum.func_177709_a("type", BlockPlanks.EnumType.class);
   public static final PropertyInteger field_176479_b = PropertyInteger.func_177719_a("stage", 0, 1);

   protected BlockSapling() {
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176480_a, BlockPlanks.EnumType.OAK).func_177226_a(field_176479_b, 0));
      float lvt_1_1_ = 0.4F;
      this.func_149676_a(0.5F - lvt_1_1_, 0.0F, 0.5F - lvt_1_1_, 0.5F + lvt_1_1_, lvt_1_1_ * 2.0F, 0.5F + lvt_1_1_);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public String func_149732_F() {
      return StatCollector.func_74838_a(this.func_149739_a() + "." + BlockPlanks.EnumType.OAK.func_176840_c() + ".name");
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if (!p_180650_1_.field_72995_K) {
         super.func_180650_b(p_180650_1_, p_180650_2_, p_180650_3_, p_180650_4_);
         if (p_180650_1_.func_175671_l(p_180650_2_.func_177984_a()) >= 9 && p_180650_4_.nextInt(7) == 0) {
            this.func_176478_d(p_180650_1_, p_180650_2_, p_180650_3_, p_180650_4_);
         }

      }
   }

   public void func_176478_d(World p_176478_1_, BlockPos p_176478_2_, IBlockState p_176478_3_, Random p_176478_4_) {
      if ((Integer)p_176478_3_.func_177229_b(field_176479_b) == 0) {
         p_176478_1_.func_180501_a(p_176478_2_, p_176478_3_.func_177231_a(field_176479_b), 4);
      } else {
         this.func_176476_e(p_176478_1_, p_176478_2_, p_176478_3_, p_176478_4_);
      }

   }

   public void func_176476_e(World p_176476_1_, BlockPos p_176476_2_, IBlockState p_176476_3_, Random p_176476_4_) {
      WorldGenerator lvt_5_1_ = p_176476_4_.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
      int lvt_6_1_ = 0;
      int lvt_7_1_ = 0;
      boolean lvt_8_1_ = false;
      IBlockState lvt_9_1_;
      switch((BlockPlanks.EnumType)p_176476_3_.func_177229_b(field_176480_a)) {
      case SPRUCE:
         label68:
         for(lvt_6_1_ = 0; lvt_6_1_ >= -1; --lvt_6_1_) {
            for(lvt_7_1_ = 0; lvt_7_1_ >= -1; --lvt_7_1_) {
               if (this.func_181624_a(p_176476_1_, p_176476_2_, lvt_6_1_, lvt_7_1_, BlockPlanks.EnumType.SPRUCE)) {
                  lvt_5_1_ = new WorldGenMegaPineTree(false, p_176476_4_.nextBoolean());
                  lvt_8_1_ = true;
                  break label68;
               }
            }
         }

         if (!lvt_8_1_) {
            lvt_7_1_ = 0;
            lvt_6_1_ = 0;
            lvt_5_1_ = new WorldGenTaiga2(true);
         }
         break;
      case BIRCH:
         lvt_5_1_ = new WorldGenForest(true, false);
         break;
      case JUNGLE:
         lvt_9_1_ = Blocks.field_150364_r.func_176223_P().func_177226_a(BlockOldLog.field_176301_b, BlockPlanks.EnumType.JUNGLE);
         IBlockState lvt_10_1_ = Blocks.field_150362_t.func_176223_P().func_177226_a(BlockOldLeaf.field_176239_P, BlockPlanks.EnumType.JUNGLE).func_177226_a(BlockLeaves.field_176236_b, false);

         label82:
         for(lvt_6_1_ = 0; lvt_6_1_ >= -1; --lvt_6_1_) {
            for(lvt_7_1_ = 0; lvt_7_1_ >= -1; --lvt_7_1_) {
               if (this.func_181624_a(p_176476_1_, p_176476_2_, lvt_6_1_, lvt_7_1_, BlockPlanks.EnumType.JUNGLE)) {
                  lvt_5_1_ = new WorldGenMegaJungle(true, 10, 20, lvt_9_1_, lvt_10_1_);
                  lvt_8_1_ = true;
                  break label82;
               }
            }
         }

         if (!lvt_8_1_) {
            lvt_7_1_ = 0;
            lvt_6_1_ = 0;
            lvt_5_1_ = new WorldGenTrees(true, 4 + p_176476_4_.nextInt(7), lvt_9_1_, lvt_10_1_, false);
         }
         break;
      case ACACIA:
         lvt_5_1_ = new WorldGenSavannaTree(true);
         break;
      case DARK_OAK:
         label96:
         for(lvt_6_1_ = 0; lvt_6_1_ >= -1; --lvt_6_1_) {
            for(lvt_7_1_ = 0; lvt_7_1_ >= -1; --lvt_7_1_) {
               if (this.func_181624_a(p_176476_1_, p_176476_2_, lvt_6_1_, lvt_7_1_, BlockPlanks.EnumType.DARK_OAK)) {
                  lvt_5_1_ = new WorldGenCanopyTree(true);
                  lvt_8_1_ = true;
                  break label96;
               }
            }
         }

         if (!lvt_8_1_) {
            return;
         }
      case OAK:
      }

      lvt_9_1_ = Blocks.field_150350_a.func_176223_P();
      if (lvt_8_1_) {
         p_176476_1_.func_180501_a(p_176476_2_.func_177982_a(lvt_6_1_, 0, lvt_7_1_), lvt_9_1_, 4);
         p_176476_1_.func_180501_a(p_176476_2_.func_177982_a(lvt_6_1_ + 1, 0, lvt_7_1_), lvt_9_1_, 4);
         p_176476_1_.func_180501_a(p_176476_2_.func_177982_a(lvt_6_1_, 0, lvt_7_1_ + 1), lvt_9_1_, 4);
         p_176476_1_.func_180501_a(p_176476_2_.func_177982_a(lvt_6_1_ + 1, 0, lvt_7_1_ + 1), lvt_9_1_, 4);
      } else {
         p_176476_1_.func_180501_a(p_176476_2_, lvt_9_1_, 4);
      }

      if (!((WorldGenerator)lvt_5_1_).func_180709_b(p_176476_1_, p_176476_4_, p_176476_2_.func_177982_a(lvt_6_1_, 0, lvt_7_1_))) {
         if (lvt_8_1_) {
            p_176476_1_.func_180501_a(p_176476_2_.func_177982_a(lvt_6_1_, 0, lvt_7_1_), p_176476_3_, 4);
            p_176476_1_.func_180501_a(p_176476_2_.func_177982_a(lvt_6_1_ + 1, 0, lvt_7_1_), p_176476_3_, 4);
            p_176476_1_.func_180501_a(p_176476_2_.func_177982_a(lvt_6_1_, 0, lvt_7_1_ + 1), p_176476_3_, 4);
            p_176476_1_.func_180501_a(p_176476_2_.func_177982_a(lvt_6_1_ + 1, 0, lvt_7_1_ + 1), p_176476_3_, 4);
         } else {
            p_176476_1_.func_180501_a(p_176476_2_, p_176476_3_, 4);
         }
      }

   }

   private boolean func_181624_a(World p_181624_1_, BlockPos p_181624_2_, int p_181624_3_, int p_181624_4_, BlockPlanks.EnumType p_181624_5_) {
      return this.func_176477_a(p_181624_1_, p_181624_2_.func_177982_a(p_181624_3_, 0, p_181624_4_), p_181624_5_) && this.func_176477_a(p_181624_1_, p_181624_2_.func_177982_a(p_181624_3_ + 1, 0, p_181624_4_), p_181624_5_) && this.func_176477_a(p_181624_1_, p_181624_2_.func_177982_a(p_181624_3_, 0, p_181624_4_ + 1), p_181624_5_) && this.func_176477_a(p_181624_1_, p_181624_2_.func_177982_a(p_181624_3_ + 1, 0, p_181624_4_ + 1), p_181624_5_);
   }

   public boolean func_176477_a(World p_176477_1_, BlockPos p_176477_2_, BlockPlanks.EnumType p_176477_3_) {
      IBlockState lvt_4_1_ = p_176477_1_.func_180495_p(p_176477_2_);
      return lvt_4_1_.func_177230_c() == this && lvt_4_1_.func_177229_b(field_176480_a) == p_176477_3_;
   }

   public int func_180651_a(IBlockState p_180651_1_) {
      return ((BlockPlanks.EnumType)p_180651_1_.func_177229_b(field_176480_a)).func_176839_a();
   }

   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
      BlockPlanks.EnumType[] lvt_4_1_ = BlockPlanks.EnumType.values();
      int lvt_5_1_ = lvt_4_1_.length;

      for(int lvt_6_1_ = 0; lvt_6_1_ < lvt_5_1_; ++lvt_6_1_) {
         BlockPlanks.EnumType lvt_7_1_ = lvt_4_1_[lvt_6_1_];
         p_149666_3_.add(new ItemStack(p_149666_1_, 1, lvt_7_1_.func_176839_a()));
      }

   }

   public boolean func_176473_a(World p_176473_1_, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
      return true;
   }

   public boolean func_180670_a(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
      return (double)p_180670_1_.field_73012_v.nextFloat() < 0.45D;
   }

   public void func_176474_b(World p_176474_1_, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
      this.func_176478_d(p_176474_1_, p_176474_3_, p_176474_4_, p_176474_2_);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176480_a, BlockPlanks.EnumType.func_176837_a(p_176203_1_ & 7)).func_177226_a(field_176479_b, (p_176203_1_ & 8) >> 3);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int lvt_2_1_ = 0;
      int lvt_2_1_ = lvt_2_1_ | ((BlockPlanks.EnumType)p_176201_1_.func_177229_b(field_176480_a)).func_176839_a();
      lvt_2_1_ |= (Integer)p_176201_1_.func_177229_b(field_176479_b) << 3;
      return lvt_2_1_;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176480_a, field_176479_b});
   }
}
