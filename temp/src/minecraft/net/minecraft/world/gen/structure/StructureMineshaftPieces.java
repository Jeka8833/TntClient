package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class StructureMineshaftPieces {
   private static final List<WeightedRandomChestContent> field_175893_a;

   public static void func_143048_a() {
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Corridor.class, "MSCorridor");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Cross.class, "MSCrossing");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Room.class, "MSRoom");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Stairs.class, "MSStairs");
   }

   private static StructureComponent func_175892_a(List<StructureComponent> p_175892_0_, Random p_175892_1_, int p_175892_2_, int p_175892_3_, int p_175892_4_, EnumFacing p_175892_5_, int p_175892_6_) {
      int lvt_7_1_ = p_175892_1_.nextInt(100);
      StructureBoundingBox lvt_8_2_;
      if (lvt_7_1_ >= 80) {
         lvt_8_2_ = StructureMineshaftPieces.Cross.func_175813_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
         if (lvt_8_2_ != null) {
            return new StructureMineshaftPieces.Cross(p_175892_6_, p_175892_1_, lvt_8_2_, p_175892_5_);
         }
      } else if (lvt_7_1_ >= 70) {
         lvt_8_2_ = StructureMineshaftPieces.Stairs.func_175812_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
         if (lvt_8_2_ != null) {
            return new StructureMineshaftPieces.Stairs(p_175892_6_, p_175892_1_, lvt_8_2_, p_175892_5_);
         }
      } else {
         lvt_8_2_ = StructureMineshaftPieces.Corridor.func_175814_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
         if (lvt_8_2_ != null) {
            return new StructureMineshaftPieces.Corridor(p_175892_6_, p_175892_1_, lvt_8_2_, p_175892_5_);
         }
      }

      return null;
   }

   private static StructureComponent func_175890_b(StructureComponent p_175890_0_, List<StructureComponent> p_175890_1_, Random p_175890_2_, int p_175890_3_, int p_175890_4_, int p_175890_5_, EnumFacing p_175890_6_, int p_175890_7_) {
      if (p_175890_7_ > 8) {
         return null;
      } else if (Math.abs(p_175890_3_ - p_175890_0_.func_74874_b().field_78897_a) <= 80 && Math.abs(p_175890_5_ - p_175890_0_.func_74874_b().field_78896_c) <= 80) {
         StructureComponent lvt_8_1_ = func_175892_a(p_175890_1_, p_175890_2_, p_175890_3_, p_175890_4_, p_175890_5_, p_175890_6_, p_175890_7_ + 1);
         if (lvt_8_1_ != null) {
            p_175890_1_.add(lvt_8_1_);
            lvt_8_1_.func_74861_a(p_175890_0_, p_175890_1_, p_175890_2_);
         }

         return lvt_8_1_;
      } else {
         return null;
      }
   }

   static {
      field_175893_a = Lists.newArrayList((Object[])(new WeightedRandomChestContent(Items.field_151042_j, 0, 1, 5, 10), new WeightedRandomChestContent(Items.field_151043_k, 0, 1, 3, 5), new WeightedRandomChestContent(Items.field_151137_ax, 0, 4, 9, 5), new WeightedRandomChestContent(Items.field_151100_aR, EnumDyeColor.BLUE.func_176767_b(), 4, 9, 5), new WeightedRandomChestContent(Items.field_151045_i, 0, 1, 2, 3), new WeightedRandomChestContent(Items.field_151044_h, 0, 3, 8, 10), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151035_b, 0, 1, 1, 1), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150448_aq), 0, 4, 8, 1), new WeightedRandomChestContent(Items.field_151081_bc, 0, 2, 4, 10), new WeightedRandomChestContent(Items.field_151080_bb, 0, 2, 4, 10), new WeightedRandomChestContent(Items.field_151141_av, 0, 1, 1, 3), new WeightedRandomChestContent(Items.field_151138_bX, 0, 1, 1, 1)));
   }

   public static class Stairs extends StructureComponent {
      public Stairs() {
      }

      public Stairs(int p_i45623_1_, Random p_i45623_2_, StructureBoundingBox p_i45623_3_, EnumFacing p_i45623_4_) {
         super(p_i45623_1_);
         this.field_74885_f = p_i45623_4_;
         this.field_74887_e = p_i45623_3_;
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
      }

      public static StructureBoundingBox func_175812_a(List<StructureComponent> p_175812_0_, Random p_175812_1_, int p_175812_2_, int p_175812_3_, int p_175812_4_, EnumFacing p_175812_5_) {
         StructureBoundingBox lvt_6_1_ = new StructureBoundingBox(p_175812_2_, p_175812_3_ - 5, p_175812_4_, p_175812_2_, p_175812_3_ + 2, p_175812_4_);
         switch(p_175812_5_) {
         case NORTH:
            lvt_6_1_.field_78893_d = p_175812_2_ + 2;
            lvt_6_1_.field_78896_c = p_175812_4_ - 8;
            break;
         case SOUTH:
            lvt_6_1_.field_78893_d = p_175812_2_ + 2;
            lvt_6_1_.field_78892_f = p_175812_4_ + 8;
            break;
         case WEST:
            lvt_6_1_.field_78897_a = p_175812_2_ - 8;
            lvt_6_1_.field_78892_f = p_175812_4_ + 2;
            break;
         case EAST:
            lvt_6_1_.field_78893_d = p_175812_2_ + 8;
            lvt_6_1_.field_78892_f = p_175812_4_ + 2;
         }

         return StructureComponent.func_74883_a(p_175812_0_, lvt_6_1_) != null ? null : lvt_6_1_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int lvt_4_1_ = this.func_74877_c();
         if (this.field_74885_f != null) {
            switch(this.field_74885_f) {
            case NORTH:
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_);
               break;
            case SOUTH:
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_);
               break;
            case WEST:
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, EnumFacing.WEST, lvt_4_1_);
               break;
            case EAST:
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, EnumFacing.EAST, lvt_4_1_);
            }
         }

      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if (this.func_74860_a(p_74875_1_, p_74875_3_)) {
            return false;
         } else {
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 0, 2, 7, 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 7, 2, 2, 8, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);

            for(int lvt_4_1_ = 0; lvt_4_1_ < 5; ++lvt_4_1_) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5 - lvt_4_1_ - (lvt_4_1_ < 4 ? 1 : 0), 2 + lvt_4_1_, 2, 7 - lvt_4_1_, 2 + lvt_4_1_, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }

            return true;
         }
      }
   }

   public static class Cross extends StructureComponent {
      private EnumFacing field_74953_a;
      private boolean field_74952_b;

      public Cross() {
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         p_143012_1_.func_74757_a("tf", this.field_74952_b);
         p_143012_1_.func_74768_a("D", this.field_74953_a.func_176736_b());
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         this.field_74952_b = p_143011_1_.func_74767_n("tf");
         this.field_74953_a = EnumFacing.func_176731_b(p_143011_1_.func_74762_e("D"));
      }

      public Cross(int p_i45624_1_, Random p_i45624_2_, StructureBoundingBox p_i45624_3_, EnumFacing p_i45624_4_) {
         super(p_i45624_1_);
         this.field_74953_a = p_i45624_4_;
         this.field_74887_e = p_i45624_3_;
         this.field_74952_b = p_i45624_3_.func_78882_c() > 3;
      }

      public static StructureBoundingBox func_175813_a(List<StructureComponent> p_175813_0_, Random p_175813_1_, int p_175813_2_, int p_175813_3_, int p_175813_4_, EnumFacing p_175813_5_) {
         StructureBoundingBox lvt_6_1_ = new StructureBoundingBox(p_175813_2_, p_175813_3_, p_175813_4_, p_175813_2_, p_175813_3_ + 2, p_175813_4_);
         if (p_175813_1_.nextInt(4) == 0) {
            lvt_6_1_.field_78894_e += 4;
         }

         switch(p_175813_5_) {
         case NORTH:
            lvt_6_1_.field_78897_a = p_175813_2_ - 1;
            lvt_6_1_.field_78893_d = p_175813_2_ + 3;
            lvt_6_1_.field_78896_c = p_175813_4_ - 4;
            break;
         case SOUTH:
            lvt_6_1_.field_78897_a = p_175813_2_ - 1;
            lvt_6_1_.field_78893_d = p_175813_2_ + 3;
            lvt_6_1_.field_78892_f = p_175813_4_ + 4;
            break;
         case WEST:
            lvt_6_1_.field_78897_a = p_175813_2_ - 4;
            lvt_6_1_.field_78896_c = p_175813_4_ - 1;
            lvt_6_1_.field_78892_f = p_175813_4_ + 3;
            break;
         case EAST:
            lvt_6_1_.field_78893_d = p_175813_2_ + 4;
            lvt_6_1_.field_78896_c = p_175813_4_ - 1;
            lvt_6_1_.field_78892_f = p_175813_4_ + 3;
         }

         return StructureComponent.func_74883_a(p_175813_0_, lvt_6_1_) != null ? null : lvt_6_1_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int lvt_4_1_ = this.func_74877_c();
         switch(this.field_74953_a) {
         case NORTH:
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.WEST, lvt_4_1_);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.EAST, lvt_4_1_);
            break;
         case SOUTH:
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.WEST, lvt_4_1_);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.EAST, lvt_4_1_);
            break;
         case WEST:
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.WEST, lvt_4_1_);
            break;
         case EAST:
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.EAST, lvt_4_1_);
         }

         if (this.field_74952_b) {
            if (p_74861_3_.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_);
            }

            if (p_74861_3_.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c + 1, EnumFacing.WEST, lvt_4_1_);
            }

            if (p_74861_3_.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c + 1, EnumFacing.EAST, lvt_4_1_);
            }

            if (p_74861_3_.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_);
            }
         }

      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if (this.func_74860_a(p_74875_1_, p_74875_3_)) {
            return false;
         } else {
            if (this.field_74952_b) {
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b + 3 - 1, this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78895_b + 3 - 1, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e - 2, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78894_e - 2, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78896_c + 1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f - 1, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78896_c + 1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f - 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);

            for(int lvt_4_1_ = this.field_74887_e.field_78897_a; lvt_4_1_ <= this.field_74887_e.field_78893_d; ++lvt_4_1_) {
               for(int lvt_5_1_ = this.field_74887_e.field_78896_c; lvt_5_1_ <= this.field_74887_e.field_78892_f; ++lvt_5_1_) {
                  if (this.func_175807_a(p_74875_1_, lvt_4_1_, this.field_74887_e.field_78895_b - 1, lvt_5_1_, p_74875_3_).func_177230_c().func_149688_o() == Material.field_151579_a) {
                     this.func_175811_a(p_74875_1_, Blocks.field_150344_f.func_176223_P(), lvt_4_1_, this.field_74887_e.field_78895_b - 1, lvt_5_1_, p_74875_3_);
                  }
               }
            }

            return true;
         }
      }
   }

   public static class Corridor extends StructureComponent {
      private boolean field_74958_a;
      private boolean field_74956_b;
      private boolean field_74957_c;
      private int field_74955_d;

      public Corridor() {
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         p_143012_1_.func_74757_a("hr", this.field_74958_a);
         p_143012_1_.func_74757_a("sc", this.field_74956_b);
         p_143012_1_.func_74757_a("hps", this.field_74957_c);
         p_143012_1_.func_74768_a("Num", this.field_74955_d);
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         this.field_74958_a = p_143011_1_.func_74767_n("hr");
         this.field_74956_b = p_143011_1_.func_74767_n("sc");
         this.field_74957_c = p_143011_1_.func_74767_n("hps");
         this.field_74955_d = p_143011_1_.func_74762_e("Num");
      }

      public Corridor(int p_i45625_1_, Random p_i45625_2_, StructureBoundingBox p_i45625_3_, EnumFacing p_i45625_4_) {
         super(p_i45625_1_);
         this.field_74885_f = p_i45625_4_;
         this.field_74887_e = p_i45625_3_;
         this.field_74958_a = p_i45625_2_.nextInt(3) == 0;
         this.field_74956_b = !this.field_74958_a && p_i45625_2_.nextInt(23) == 0;
         if (this.field_74885_f != EnumFacing.NORTH && this.field_74885_f != EnumFacing.SOUTH) {
            this.field_74955_d = p_i45625_3_.func_78883_b() / 5;
         } else {
            this.field_74955_d = p_i45625_3_.func_78880_d() / 5;
         }

      }

      public static StructureBoundingBox func_175814_a(List<StructureComponent> p_175814_0_, Random p_175814_1_, int p_175814_2_, int p_175814_3_, int p_175814_4_, EnumFacing p_175814_5_) {
         StructureBoundingBox lvt_6_1_ = new StructureBoundingBox(p_175814_2_, p_175814_3_, p_175814_4_, p_175814_2_, p_175814_3_ + 2, p_175814_4_);

         int lvt_7_1_;
         for(lvt_7_1_ = p_175814_1_.nextInt(3) + 2; lvt_7_1_ > 0; --lvt_7_1_) {
            int lvt_8_1_ = lvt_7_1_ * 5;
            switch(p_175814_5_) {
            case NORTH:
               lvt_6_1_.field_78893_d = p_175814_2_ + 2;
               lvt_6_1_.field_78896_c = p_175814_4_ - (lvt_8_1_ - 1);
               break;
            case SOUTH:
               lvt_6_1_.field_78893_d = p_175814_2_ + 2;
               lvt_6_1_.field_78892_f = p_175814_4_ + (lvt_8_1_ - 1);
               break;
            case WEST:
               lvt_6_1_.field_78897_a = p_175814_2_ - (lvt_8_1_ - 1);
               lvt_6_1_.field_78892_f = p_175814_4_ + 2;
               break;
            case EAST:
               lvt_6_1_.field_78893_d = p_175814_2_ + (lvt_8_1_ - 1);
               lvt_6_1_.field_78892_f = p_175814_4_ + 2;
            }

            if (StructureComponent.func_74883_a(p_175814_0_, lvt_6_1_) == null) {
               break;
            }
         }

         return lvt_7_1_ > 0 ? lvt_6_1_ : null;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int lvt_4_1_ = this.func_74877_c();
         int lvt_5_1_ = p_74861_3_.nextInt(4);
         if (this.field_74885_f != null) {
            switch(this.field_74885_f) {
            case NORTH:
               if (lvt_5_1_ <= 1) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, this.field_74885_f, lvt_4_1_);
               } else if (lvt_5_1_ == 2) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, EnumFacing.WEST, lvt_4_1_);
               } else {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, EnumFacing.EAST, lvt_4_1_);
               }
               break;
            case SOUTH:
               if (lvt_5_1_ <= 1) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, this.field_74885_f, lvt_4_1_);
               } else if (lvt_5_1_ == 2) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f - 3, EnumFacing.WEST, lvt_4_1_);
               } else {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f - 3, EnumFacing.EAST, lvt_4_1_);
               }
               break;
            case WEST:
               if (lvt_5_1_ <= 1) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, this.field_74885_f, lvt_4_1_);
               } else if (lvt_5_1_ == 2) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_);
               } else {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_);
               }
               break;
            case EAST:
               if (lvt_5_1_ <= 1) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, this.field_74885_f, lvt_4_1_);
               } else if (lvt_5_1_ == 2) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d - 3, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_);
               } else {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d - 3, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_);
               }
            }
         }

         if (lvt_4_1_ < 8) {
            int lvt_6_1_;
            int lvt_7_1_;
            if (this.field_74885_f != EnumFacing.NORTH && this.field_74885_f != EnumFacing.SOUTH) {
               for(lvt_6_1_ = this.field_74887_e.field_78897_a + 3; lvt_6_1_ + 3 <= this.field_74887_e.field_78893_d; lvt_6_1_ += 5) {
                  lvt_7_1_ = p_74861_3_.nextInt(5);
                  if (lvt_7_1_ == 0) {
                     StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, lvt_6_1_, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_ + 1);
                  } else if (lvt_7_1_ == 1) {
                     StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, lvt_6_1_, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_ + 1);
                  }
               }
            } else {
               for(lvt_6_1_ = this.field_74887_e.field_78896_c + 3; lvt_6_1_ + 3 <= this.field_74887_e.field_78892_f; lvt_6_1_ += 5) {
                  lvt_7_1_ = p_74861_3_.nextInt(5);
                  if (lvt_7_1_ == 0) {
                     StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, lvt_6_1_, EnumFacing.WEST, lvt_4_1_ + 1);
                  } else if (lvt_7_1_ == 1) {
                     StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, lvt_6_1_, EnumFacing.EAST, lvt_4_1_ + 1);
                  }
               }
            }
         }

      }

      protected boolean func_180778_a(World p_180778_1_, StructureBoundingBox p_180778_2_, Random p_180778_3_, int p_180778_4_, int p_180778_5_, int p_180778_6_, List<WeightedRandomChestContent> p_180778_7_, int p_180778_8_) {
         BlockPos lvt_9_1_ = new BlockPos(this.func_74865_a(p_180778_4_, p_180778_6_), this.func_74862_a(p_180778_5_), this.func_74873_b(p_180778_4_, p_180778_6_));
         if (p_180778_2_.func_175898_b(lvt_9_1_) && p_180778_1_.func_180495_p(lvt_9_1_).func_177230_c().func_149688_o() == Material.field_151579_a) {
            int lvt_10_1_ = p_180778_3_.nextBoolean() ? 1 : 0;
            p_180778_1_.func_180501_a(lvt_9_1_, Blocks.field_150448_aq.func_176203_a(this.func_151555_a(Blocks.field_150448_aq, lvt_10_1_)), 2);
            EntityMinecartChest lvt_11_1_ = new EntityMinecartChest(p_180778_1_, (double)((float)lvt_9_1_.func_177958_n() + 0.5F), (double)((float)lvt_9_1_.func_177956_o() + 0.5F), (double)((float)lvt_9_1_.func_177952_p() + 0.5F));
            WeightedRandomChestContent.func_177630_a(p_180778_3_, p_180778_7_, lvt_11_1_, p_180778_8_);
            p_180778_1_.func_72838_d(lvt_11_1_);
            return true;
         } else {
            return false;
         }
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if (this.func_74860_a(p_74875_1_, p_74875_3_)) {
            return false;
         } else {
            int lvt_4_1_ = false;
            int lvt_5_1_ = true;
            int lvt_6_1_ = false;
            int lvt_7_1_ = true;
            int lvt_8_1_ = this.field_74955_d * 5 - 1;
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 2, 1, lvt_8_1_, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175805_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.8F, 0, 2, 0, 2, 2, lvt_8_1_, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            if (this.field_74956_b) {
               this.func_175805_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.6F, 0, 0, 0, 2, 1, lvt_8_1_, Blocks.field_150321_G.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }

            int lvt_9_3_;
            int lvt_10_1_;
            for(lvt_9_3_ = 0; lvt_9_3_ < this.field_74955_d; ++lvt_9_3_) {
               lvt_10_1_ = 2 + lvt_9_3_ * 5;
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, lvt_10_1_, 0, 1, lvt_10_1_, Blocks.field_180407_aO.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 2, 0, lvt_10_1_, 2, 1, lvt_10_1_, Blocks.field_180407_aO.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               if (p_74875_2_.nextInt(4) == 0) {
                  this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, lvt_10_1_, 0, 2, lvt_10_1_, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
                  this.func_175804_a(p_74875_1_, p_74875_3_, 2, 2, lvt_10_1_, 2, 2, lvt_10_1_, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               } else {
                  this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, lvt_10_1_, 2, 2, lvt_10_1_, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               }

               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 0, 2, lvt_10_1_ - 1, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 2, 2, lvt_10_1_ - 1, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 0, 2, lvt_10_1_ + 1, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 2, 2, lvt_10_1_ + 1, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 0, 2, lvt_10_1_ - 2, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 2, 2, lvt_10_1_ - 2, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 0, 2, lvt_10_1_ + 2, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 2, 2, lvt_10_1_ + 2, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 1, 2, lvt_10_1_ - 1, Blocks.field_150478_aa.func_176203_a(EnumFacing.UP.func_176745_a()));
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 1, 2, lvt_10_1_ + 1, Blocks.field_150478_aa.func_176203_a(EnumFacing.UP.func_176745_a()));
               if (p_74875_2_.nextInt(100) == 0) {
                  this.func_180778_a(p_74875_1_, p_74875_3_, p_74875_2_, 2, 0, lvt_10_1_ - 1, WeightedRandomChestContent.func_177629_a(StructureMineshaftPieces.field_175893_a, Items.field_151134_bR.func_92114_b(p_74875_2_)), 3 + p_74875_2_.nextInt(4));
               }

               if (p_74875_2_.nextInt(100) == 0) {
                  this.func_180778_a(p_74875_1_, p_74875_3_, p_74875_2_, 0, 0, lvt_10_1_ + 1, WeightedRandomChestContent.func_177629_a(StructureMineshaftPieces.field_175893_a, Items.field_151134_bR.func_92114_b(p_74875_2_)), 3 + p_74875_2_.nextInt(4));
               }

               if (this.field_74956_b && !this.field_74957_c) {
                  int lvt_11_1_ = this.func_74862_a(0);
                  int lvt_12_1_ = lvt_10_1_ - 1 + p_74875_2_.nextInt(3);
                  int lvt_13_1_ = this.func_74865_a(1, lvt_12_1_);
                  lvt_12_1_ = this.func_74873_b(1, lvt_12_1_);
                  BlockPos lvt_14_1_ = new BlockPos(lvt_13_1_, lvt_11_1_, lvt_12_1_);
                  if (p_74875_3_.func_175898_b(lvt_14_1_)) {
                     this.field_74957_c = true;
                     p_74875_1_.func_180501_a(lvt_14_1_, Blocks.field_150474_ac.func_176223_P(), 2);
                     TileEntity lvt_15_1_ = p_74875_1_.func_175625_s(lvt_14_1_);
                     if (lvt_15_1_ instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner)lvt_15_1_).func_145881_a().func_98272_a("CaveSpider");
                     }
                  }
               }
            }

            for(lvt_9_3_ = 0; lvt_9_3_ <= 2; ++lvt_9_3_) {
               for(lvt_10_1_ = 0; lvt_10_1_ <= lvt_8_1_; ++lvt_10_1_) {
                  int lvt_11_2_ = -1;
                  IBlockState lvt_12_2_ = this.func_175807_a(p_74875_1_, lvt_9_3_, lvt_11_2_, lvt_10_1_, p_74875_3_);
                  if (lvt_12_2_.func_177230_c().func_149688_o() == Material.field_151579_a) {
                     int lvt_13_2_ = -1;
                     this.func_175811_a(p_74875_1_, Blocks.field_150344_f.func_176223_P(), lvt_9_3_, lvt_13_2_, lvt_10_1_, p_74875_3_);
                  }
               }
            }

            if (this.field_74958_a) {
               for(lvt_9_3_ = 0; lvt_9_3_ <= lvt_8_1_; ++lvt_9_3_) {
                  IBlockState lvt_10_3_ = this.func_175807_a(p_74875_1_, 1, -1, lvt_9_3_, p_74875_3_);
                  if (lvt_10_3_.func_177230_c().func_149688_o() != Material.field_151579_a && lvt_10_3_.func_177230_c().func_149730_j()) {
                     this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.7F, 1, 0, lvt_9_3_, Blocks.field_150448_aq.func_176203_a(this.func_151555_a(Blocks.field_150448_aq, 0)));
                  }
               }
            }

            return true;
         }
      }
   }

   public static class Room extends StructureComponent {
      private List<StructureBoundingBox> field_74949_a = Lists.newLinkedList();

      public Room() {
      }

      public Room(int p_i2037_1_, Random p_i2037_2_, int p_i2037_3_, int p_i2037_4_) {
         super(p_i2037_1_);
         this.field_74887_e = new StructureBoundingBox(p_i2037_3_, 50, p_i2037_4_, p_i2037_3_ + 7 + p_i2037_2_.nextInt(6), 54 + p_i2037_2_.nextInt(6), p_i2037_4_ + 7 + p_i2037_2_.nextInt(6));
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int lvt_4_1_ = this.func_74877_c();
         int lvt_6_1_ = this.field_74887_e.func_78882_c() - 3 - 1;
         if (lvt_6_1_ <= 0) {
            lvt_6_1_ = 1;
         }

         int lvt_5_1_;
         StructureComponent lvt_7_4_;
         StructureBoundingBox lvt_8_4_;
         for(lvt_5_1_ = 0; lvt_5_1_ < this.field_74887_e.func_78883_b(); lvt_5_1_ += 4) {
            lvt_5_1_ += p_74861_3_.nextInt(this.field_74887_e.func_78883_b());
            if (lvt_5_1_ + 3 > this.field_74887_e.func_78883_b()) {
               break;
            }

            lvt_7_4_ = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + lvt_5_1_, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(lvt_6_1_) + 1, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, lvt_4_1_);
            if (lvt_7_4_ != null) {
               lvt_8_4_ = lvt_7_4_.func_74874_b();
               this.field_74949_a.add(new StructureBoundingBox(lvt_8_4_.field_78897_a, lvt_8_4_.field_78895_b, this.field_74887_e.field_78896_c, lvt_8_4_.field_78893_d, lvt_8_4_.field_78894_e, this.field_74887_e.field_78896_c + 1));
            }
         }

         for(lvt_5_1_ = 0; lvt_5_1_ < this.field_74887_e.func_78883_b(); lvt_5_1_ += 4) {
            lvt_5_1_ += p_74861_3_.nextInt(this.field_74887_e.func_78883_b());
            if (lvt_5_1_ + 3 > this.field_74887_e.func_78883_b()) {
               break;
            }

            lvt_7_4_ = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + lvt_5_1_, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(lvt_6_1_) + 1, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, lvt_4_1_);
            if (lvt_7_4_ != null) {
               lvt_8_4_ = lvt_7_4_.func_74874_b();
               this.field_74949_a.add(new StructureBoundingBox(lvt_8_4_.field_78897_a, lvt_8_4_.field_78895_b, this.field_74887_e.field_78892_f - 1, lvt_8_4_.field_78893_d, lvt_8_4_.field_78894_e, this.field_74887_e.field_78892_f));
            }
         }

         for(lvt_5_1_ = 0; lvt_5_1_ < this.field_74887_e.func_78880_d(); lvt_5_1_ += 4) {
            lvt_5_1_ += p_74861_3_.nextInt(this.field_74887_e.func_78880_d());
            if (lvt_5_1_ + 3 > this.field_74887_e.func_78880_d()) {
               break;
            }

            lvt_7_4_ = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(lvt_6_1_) + 1, this.field_74887_e.field_78896_c + lvt_5_1_, EnumFacing.WEST, lvt_4_1_);
            if (lvt_7_4_ != null) {
               lvt_8_4_ = lvt_7_4_.func_74874_b();
               this.field_74949_a.add(new StructureBoundingBox(this.field_74887_e.field_78897_a, lvt_8_4_.field_78895_b, lvt_8_4_.field_78896_c, this.field_74887_e.field_78897_a + 1, lvt_8_4_.field_78894_e, lvt_8_4_.field_78892_f));
            }
         }

         for(lvt_5_1_ = 0; lvt_5_1_ < this.field_74887_e.func_78880_d(); lvt_5_1_ += 4) {
            lvt_5_1_ += p_74861_3_.nextInt(this.field_74887_e.func_78880_d());
            if (lvt_5_1_ + 3 > this.field_74887_e.func_78880_d()) {
               break;
            }

            lvt_7_4_ = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(lvt_6_1_) + 1, this.field_74887_e.field_78896_c + lvt_5_1_, EnumFacing.EAST, lvt_4_1_);
            if (lvt_7_4_ != null) {
               lvt_8_4_ = lvt_7_4_.func_74874_b();
               this.field_74949_a.add(new StructureBoundingBox(this.field_74887_e.field_78893_d - 1, lvt_8_4_.field_78895_b, lvt_8_4_.field_78896_c, this.field_74887_e.field_78893_d, lvt_8_4_.field_78894_e, lvt_8_4_.field_78892_f));
            }
         }

      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if (this.func_74860_a(p_74875_1_, p_74875_3_)) {
            return false;
         } else {
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f, Blocks.field_150346_d.func_176223_P(), Blocks.field_150350_a.func_176223_P(), true);
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b + 1, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, Math.min(this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78894_e), this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            Iterator lvt_4_1_ = this.field_74949_a.iterator();

            while(lvt_4_1_.hasNext()) {
               StructureBoundingBox lvt_5_1_ = (StructureBoundingBox)lvt_4_1_.next();
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_5_1_.field_78897_a, lvt_5_1_.field_78894_e - 2, lvt_5_1_.field_78896_c, lvt_5_1_.field_78893_d, lvt_5_1_.field_78894_e, lvt_5_1_.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }

            this.func_180777_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b + 4, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), false);
            return true;
         }
      }

      public void func_181138_a(int p_181138_1_, int p_181138_2_, int p_181138_3_) {
         super.func_181138_a(p_181138_1_, p_181138_2_, p_181138_3_);
         Iterator lvt_4_1_ = this.field_74949_a.iterator();

         while(lvt_4_1_.hasNext()) {
            StructureBoundingBox lvt_5_1_ = (StructureBoundingBox)lvt_4_1_.next();
            lvt_5_1_.func_78886_a(p_181138_1_, p_181138_2_, p_181138_3_);
         }

      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         NBTTagList lvt_2_1_ = new NBTTagList();
         Iterator lvt_3_1_ = this.field_74949_a.iterator();

         while(lvt_3_1_.hasNext()) {
            StructureBoundingBox lvt_4_1_ = (StructureBoundingBox)lvt_3_1_.next();
            lvt_2_1_.func_74742_a(lvt_4_1_.func_151535_h());
         }

         p_143012_1_.func_74782_a("Entrances", lvt_2_1_);
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         NBTTagList lvt_2_1_ = p_143011_1_.func_150295_c("Entrances", 11);

         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.func_74745_c(); ++lvt_3_1_) {
            this.field_74949_a.add(new StructureBoundingBox(lvt_2_1_.func_150306_c(lvt_3_1_)));
         }

      }
   }
}
