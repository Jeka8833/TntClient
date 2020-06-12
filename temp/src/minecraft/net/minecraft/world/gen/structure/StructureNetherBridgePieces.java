package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class StructureNetherBridgePieces {
   private static final StructureNetherBridgePieces.PieceWeight[] field_78742_a = new StructureNetherBridgePieces.PieceWeight[]{new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Straight.class, 30, 0, true), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing3.class, 10, 4), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing.class, 10, 4), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Stairs.class, 10, 3), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Throne.class, 5, 2), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Entrance.class, 5, 1)};
   private static final StructureNetherBridgePieces.PieceWeight[] field_78741_b = new StructureNetherBridgePieces.PieceWeight[]{new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor5.class, 25, 0, true), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing2.class, 15, 5), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor2.class, 5, 10), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor.class, 5, 10), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor3.class, 10, 3, true), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor4.class, 7, 2), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.NetherStalkRoom.class, 5, 2)};

   public static void func_143049_a() {
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Crossing3.class, "NeBCr");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.End.class, "NeBEF");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Straight.class, "NeBS");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor3.class, "NeCCS");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor4.class, "NeCTB");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Entrance.class, "NeCE");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Crossing2.class, "NeSCSC");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor.class, "NeSCLT");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor5.class, "NeSC");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor2.class, "NeSCRT");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.NetherStalkRoom.class, "NeCSR");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Throne.class, "NeMT");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Crossing.class, "NeRC");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Stairs.class, "NeSR");
      MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Start.class, "NeStart");
   }

   private static StructureNetherBridgePieces.Piece func_175887_b(StructureNetherBridgePieces.PieceWeight p_175887_0_, List<StructureComponent> p_175887_1_, Random p_175887_2_, int p_175887_3_, int p_175887_4_, int p_175887_5_, EnumFacing p_175887_6_, int p_175887_7_) {
      Class<? extends StructureNetherBridgePieces.Piece> lvt_8_1_ = p_175887_0_.field_78828_a;
      StructureNetherBridgePieces.Piece lvt_9_1_ = null;
      if (lvt_8_1_ == StructureNetherBridgePieces.Straight.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Straight.func_175882_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Crossing3.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Crossing3.func_175885_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Crossing.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Crossing.func_175873_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Stairs.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Stairs.func_175872_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Throne.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Throne.func_175874_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Entrance.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Entrance.func_175881_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Corridor5.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Corridor5.func_175877_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Corridor2.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Corridor2.func_175876_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Corridor.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Corridor.func_175879_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Corridor3.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Corridor3.func_175883_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Corridor4.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Corridor4.func_175880_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.Crossing2.class) {
         lvt_9_1_ = StructureNetherBridgePieces.Crossing2.func_175878_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      } else if (lvt_8_1_ == StructureNetherBridgePieces.NetherStalkRoom.class) {
         lvt_9_1_ = StructureNetherBridgePieces.NetherStalkRoom.func_175875_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
      }

      return (StructureNetherBridgePieces.Piece)lvt_9_1_;
   }

   public static class Corridor4 extends StructureNetherBridgePieces.Piece {
      public Corridor4() {
      }

      public Corridor4(int p_i45618_1_, Random p_i45618_2_, StructureBoundingBox p_i45618_3_, EnumFacing p_i45618_4_) {
         super(p_i45618_1_);
         this.field_74885_f = p_i45618_4_;
         this.field_74887_e = p_i45618_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int lvt_4_1_ = 1;
         if (this.field_74885_f == EnumFacing.WEST || this.field_74885_f == EnumFacing.NORTH) {
            lvt_4_1_ = 5;
         }

         this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, lvt_4_1_, p_74861_3_.nextInt(8) > 0);
         this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, lvt_4_1_, p_74861_3_.nextInt(8) > 0);
      }

      public static StructureNetherBridgePieces.Corridor4 func_175880_a(List<StructureComponent> p_175880_0_, Random p_175880_1_, int p_175880_2_, int p_175880_3_, int p_175880_4_, EnumFacing p_175880_5_, int p_175880_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175880_2_, p_175880_3_, p_175880_4_, -3, 0, 0, 9, 7, 9, p_175880_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175880_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Corridor4(p_175880_6_, p_175880_1_, lvt_7_1_, p_175880_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 8, 1, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 8, 5, 8, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 6, 0, 8, 6, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 2, 5, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 2, 0, 8, 5, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 0, 1, 4, 0, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 3, 0, 7, 4, 0, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 4, 8, 2, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 4, 2, 2, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 4, 7, 2, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 8, 8, 3, 8, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 6, 0, 3, 7, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 3, 6, 8, 3, 7, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 4, 0, 5, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 3, 4, 8, 5, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 5, 2, 5, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 3, 5, 7, 5, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 5, 1, 5, 5, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 4, 5, 7, 5, 5, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);

         for(int lvt_4_1_ = 0; lvt_4_1_ <= 5; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ <= 8; ++lvt_5_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_5_1_, -1, lvt_4_1_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Corridor3 extends StructureNetherBridgePieces.Piece {
      public Corridor3() {
      }

      public Corridor3(int p_i45619_1_, Random p_i45619_2_, StructureBoundingBox p_i45619_3_, EnumFacing p_i45619_4_) {
         super(p_i45619_1_);
         this.field_74885_f = p_i45619_4_;
         this.field_74887_e = p_i45619_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
      }

      public static StructureNetherBridgePieces.Corridor3 func_175883_a(List<StructureComponent> p_175883_0_, Random p_175883_1_, int p_175883_2_, int p_175883_3_, int p_175883_4_, EnumFacing p_175883_5_, int p_175883_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175883_2_, p_175883_3_, p_175883_4_, -1, -7, 0, 5, 14, 10, p_175883_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175883_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Corridor3(p_175883_6_, p_175883_1_, lvt_7_1_, p_175883_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         int lvt_4_1_ = this.func_151555_a(Blocks.field_150387_bl, 2);

         for(int lvt_5_1_ = 0; lvt_5_1_ <= 9; ++lvt_5_1_) {
            int lvt_6_1_ = Math.max(1, 7 - lvt_5_1_);
            int lvt_7_1_ = Math.min(Math.max(lvt_6_1_ + 5, 14 - lvt_5_1_), 13);
            int lvt_8_1_ = lvt_5_1_;
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, lvt_5_1_, 4, lvt_6_1_, lvt_5_1_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_6_1_ + 1, lvt_5_1_, 3, lvt_7_1_ - 1, lvt_5_1_, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            if (lvt_5_1_ <= 6) {
               this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_4_1_), 1, lvt_6_1_ + 1, lvt_5_1_, p_74875_3_);
               this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_4_1_), 2, lvt_6_1_ + 1, lvt_5_1_, p_74875_3_);
               this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_4_1_), 3, lvt_6_1_ + 1, lvt_5_1_, p_74875_3_);
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_7_1_, lvt_5_1_, 4, lvt_7_1_, lvt_5_1_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_6_1_ + 1, lvt_5_1_, 0, lvt_7_1_ - 1, lvt_5_1_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 4, lvt_6_1_ + 1, lvt_5_1_, 4, lvt_7_1_ - 1, lvt_5_1_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
            if ((lvt_5_1_ & 1) == 0) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_6_1_ + 2, lvt_5_1_, 0, lvt_6_1_ + 3, lvt_5_1_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 4, lvt_6_1_ + 2, lvt_5_1_, 4, lvt_6_1_ + 3, lvt_5_1_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            }

            for(int lvt_9_1_ = 0; lvt_9_1_ <= 4; ++lvt_9_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_9_1_, -1, lvt_8_1_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Corridor extends StructureNetherBridgePieces.Piece {
      private boolean field_111021_b;

      public Corridor() {
      }

      public Corridor(int p_i45615_1_, Random p_i45615_2_, StructureBoundingBox p_i45615_3_, EnumFacing p_i45615_4_) {
         super(p_i45615_1_);
         this.field_74885_f = p_i45615_4_;
         this.field_74887_e = p_i45615_3_;
         this.field_111021_b = p_i45615_2_.nextInt(3) == 0;
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         super.func_143011_b(p_143011_1_);
         this.field_111021_b = p_143011_1_.func_74767_n("Chest");
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         super.func_143012_a(p_143012_1_);
         p_143012_1_.func_74757_a("Chest", this.field_111021_b);
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
      }

      public static StructureNetherBridgePieces.Corridor func_175879_a(List<StructureComponent> p_175879_0_, Random p_175879_1_, int p_175879_2_, int p_175879_3_, int p_175879_4_, EnumFacing p_175879_5_, int p_175879_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175879_2_, p_175879_3_, p_175879_4_, -1, 0, 0, 5, 7, 5, p_175879_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175879_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Corridor(p_175879_6_, p_175879_1_, lvt_7_1_, p_175879_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 2, 0, 4, 5, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 3, 1, 4, 4, 1, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 3, 3, 4, 4, 3, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 5, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 4, 3, 5, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 4, 1, 4, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 3, 4, 3, 4, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         if (this.field_111021_b && p_74875_3_.func_175898_b(new BlockPos(this.func_74865_a(3, 3), this.func_74862_a(2), this.func_74873_b(3, 3)))) {
            this.field_111021_b = false;
            this.func_180778_a(p_74875_1_, p_74875_3_, p_74875_2_, 3, 2, 3, field_111019_a, 2 + p_74875_2_.nextInt(4));
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         for(int lvt_4_1_ = 0; lvt_4_1_ <= 4; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ <= 4; ++lvt_5_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_1_, -1, lvt_5_1_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Corridor2 extends StructureNetherBridgePieces.Piece {
      private boolean field_111020_b;

      public Corridor2() {
      }

      public Corridor2(int p_i45613_1_, Random p_i45613_2_, StructureBoundingBox p_i45613_3_, EnumFacing p_i45613_4_) {
         super(p_i45613_1_);
         this.field_74885_f = p_i45613_4_;
         this.field_74887_e = p_i45613_3_;
         this.field_111020_b = p_i45613_2_.nextInt(3) == 0;
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         super.func_143011_b(p_143011_1_);
         this.field_111020_b = p_143011_1_.func_74767_n("Chest");
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         super.func_143012_a(p_143012_1_);
         p_143012_1_.func_74757_a("Chest", this.field_111020_b);
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
      }

      public static StructureNetherBridgePieces.Corridor2 func_175876_a(List<StructureComponent> p_175876_0_, Random p_175876_1_, int p_175876_2_, int p_175876_3_, int p_175876_4_, EnumFacing p_175876_5_, int p_175876_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175876_2_, p_175876_3_, p_175876_4_, -1, 0, 0, 5, 7, 5, p_175876_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175876_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Corridor2(p_175876_6_, p_175876_1_, lvt_7_1_, p_175876_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 5, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 1, 0, 4, 1, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 3, 0, 4, 3, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 2, 0, 4, 5, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 4, 4, 5, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 4, 1, 4, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 3, 4, 3, 4, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         if (this.field_111020_b && p_74875_3_.func_175898_b(new BlockPos(this.func_74865_a(1, 3), this.func_74862_a(2), this.func_74873_b(1, 3)))) {
            this.field_111020_b = false;
            this.func_180778_a(p_74875_1_, p_74875_3_, p_74875_2_, 1, 2, 3, field_111019_a, 2 + p_74875_2_.nextInt(4));
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         for(int lvt_4_1_ = 0; lvt_4_1_ <= 4; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ <= 4; ++lvt_5_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_1_, -1, lvt_5_1_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Crossing2 extends StructureNetherBridgePieces.Piece {
      public Crossing2() {
      }

      public Crossing2(int p_i45616_1_, Random p_i45616_2_, StructureBoundingBox p_i45616_3_, EnumFacing p_i45616_4_) {
         super(p_i45616_1_);
         this.field_74885_f = p_i45616_4_;
         this.field_74887_e = p_i45616_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
         this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
         this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
      }

      public static StructureNetherBridgePieces.Crossing2 func_175878_a(List<StructureComponent> p_175878_0_, Random p_175878_1_, int p_175878_2_, int p_175878_3_, int p_175878_4_, EnumFacing p_175878_5_, int p_175878_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175878_2_, p_175878_3_, p_175878_4_, -1, 0, 0, 5, 7, 5, p_175878_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175878_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Crossing2(p_175878_6_, p_175878_1_, lvt_7_1_, p_175878_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 5, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 2, 0, 4, 5, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 4, 0, 5, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 2, 4, 4, 5, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         for(int lvt_4_1_ = 0; lvt_4_1_ <= 4; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ <= 4; ++lvt_5_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_1_, -1, lvt_5_1_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Corridor5 extends StructureNetherBridgePieces.Piece {
      public Corridor5() {
      }

      public Corridor5(int p_i45614_1_, Random p_i45614_2_, StructureBoundingBox p_i45614_3_, EnumFacing p_i45614_4_) {
         super(p_i45614_1_);
         this.field_74885_f = p_i45614_4_;
         this.field_74887_e = p_i45614_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
      }

      public static StructureNetherBridgePieces.Corridor5 func_175877_a(List<StructureComponent> p_175877_0_, Random p_175877_1_, int p_175877_2_, int p_175877_3_, int p_175877_4_, EnumFacing p_175877_5_, int p_175877_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175877_2_, p_175877_3_, p_175877_4_, -1, 0, 0, 5, 7, 5, p_175877_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175877_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Corridor5(p_175877_6_, p_175877_1_, lvt_7_1_, p_175877_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 5, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 2, 0, 4, 5, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 1, 0, 4, 1, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 3, 0, 4, 3, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 3, 1, 4, 4, 1, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 3, 3, 4, 4, 3, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         for(int lvt_4_1_ = 0; lvt_4_1_ <= 4; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ <= 4; ++lvt_5_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_1_, -1, lvt_5_1_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class NetherStalkRoom extends StructureNetherBridgePieces.Piece {
      public NetherStalkRoom() {
      }

      public NetherStalkRoom(int p_i45612_1_, Random p_i45612_2_, StructureBoundingBox p_i45612_3_, EnumFacing p_i45612_4_) {
         super(p_i45612_1_);
         this.field_74885_f = p_i45612_4_;
         this.field_74887_e = p_i45612_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 3, true);
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 11, true);
      }

      public static StructureNetherBridgePieces.NetherStalkRoom func_175875_a(List<StructureComponent> p_175875_0_, Random p_175875_1_, int p_175875_2_, int p_175875_3_, int p_175875_4_, EnumFacing p_175875_5_, int p_175875_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175875_2_, p_175875_3_, p_175875_4_, -5, -3, 0, 13, 14, 13, p_175875_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175875_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.NetherStalkRoom(p_175875_6_, p_175875_1_, lvt_7_1_, p_175875_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 12, 4, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 0, 12, 13, 12, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 0, 1, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 11, 5, 0, 12, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 11, 4, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 5, 11, 10, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 9, 11, 7, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 0, 4, 12, 1, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 5, 0, 10, 12, 1, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 9, 0, 7, 12, 1, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 11, 2, 10, 12, 10, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         int lvt_4_3_;
         for(lvt_4_3_ = 1; lvt_4_3_ <= 11; lvt_4_3_ += 2) {
            this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_3_, 10, 0, lvt_4_3_, 11, 0, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_3_, 10, 12, lvt_4_3_, 11, 12, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 10, lvt_4_3_, 0, 11, lvt_4_3_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 12, 10, lvt_4_3_, 12, 11, lvt_4_3_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_3_, 13, 0, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_3_, 13, 12, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 0, 13, lvt_4_3_, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 12, 13, lvt_4_3_, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), lvt_4_3_ + 1, 13, 0, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), lvt_4_3_ + 1, 13, 12, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 0, 13, lvt_4_3_ + 1, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 12, 13, lvt_4_3_ + 1, p_74875_3_);
         }

         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 0, 13, 0, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 0, 13, 12, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 0, 13, 0, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 12, 13, 0, p_74875_3_);

         for(lvt_4_3_ = 3; lvt_4_3_ <= 9; lvt_4_3_ += 2) {
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 7, lvt_4_3_, 1, 8, lvt_4_3_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 11, 7, lvt_4_3_, 11, 8, lvt_4_3_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         }

         lvt_4_3_ = this.func_151555_a(Blocks.field_150387_bl, 3);

         int lvt_5_1_;
         int lvt_6_1_;
         int lvt_7_3_;
         for(lvt_5_1_ = 0; lvt_5_1_ <= 6; ++lvt_5_1_) {
            lvt_6_1_ = lvt_5_1_ + 4;

            for(lvt_7_3_ = 5; lvt_7_3_ <= 7; ++lvt_7_3_) {
               this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_4_3_), lvt_7_3_, 5 + lvt_5_1_, lvt_6_1_, p_74875_3_);
            }

            if (lvt_6_1_ >= 5 && lvt_6_1_ <= 8) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 5, 5, lvt_6_1_, 7, lvt_5_1_ + 4, lvt_6_1_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
            } else if (lvt_6_1_ >= 9 && lvt_6_1_ <= 10) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 5, 8, lvt_6_1_, 7, lvt_5_1_ + 4, lvt_6_1_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
            }

            if (lvt_5_1_ >= 1) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 5, 6 + lvt_5_1_, lvt_6_1_, 7, 9 + lvt_5_1_, lvt_6_1_, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }
         }

         for(lvt_5_1_ = 5; lvt_5_1_ <= 7; ++lvt_5_1_) {
            this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_4_3_), lvt_5_1_, 12, 11, p_74875_3_);
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 6, 7, 5, 7, 7, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 6, 7, 7, 7, 7, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 13, 12, 7, 13, 12, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 2, 3, 5, 3, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 9, 3, 5, 10, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 4, 2, 5, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 5, 2, 10, 5, 3, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 5, 9, 10, 5, 10, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 5, 4, 10, 5, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         lvt_5_1_ = this.func_151555_a(Blocks.field_150387_bl, 0);
         lvt_6_1_ = this.func_151555_a(Blocks.field_150387_bl, 1);
         this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_6_1_), 4, 5, 2, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_6_1_), 4, 5, 3, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_6_1_), 4, 5, 9, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_6_1_), 4, 5, 10, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_5_1_), 8, 5, 2, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_5_1_), 8, 5, 3, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_5_1_), 8, 5, 9, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150387_bl.func_176203_a(lvt_5_1_), 8, 5, 10, p_74875_3_);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 4, 4, 4, 4, 8, Blocks.field_150425_aM.func_176223_P(), Blocks.field_150425_aM.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 4, 4, 9, 4, 8, Blocks.field_150425_aM.func_176223_P(), Blocks.field_150425_aM.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 5, 4, 4, 5, 8, Blocks.field_150388_bm.func_176223_P(), Blocks.field_150388_bm.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 5, 4, 9, 5, 8, Blocks.field_150388_bm.func_176223_P(), Blocks.field_150388_bm.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 2, 0, 8, 2, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 4, 12, 2, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 0, 0, 8, 1, 3, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 0, 9, 8, 1, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 4, 3, 1, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 0, 4, 12, 1, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         int lvt_8_2_;
         for(lvt_7_3_ = 4; lvt_7_3_ <= 8; ++lvt_7_3_) {
            for(lvt_8_2_ = 0; lvt_8_2_ <= 2; ++lvt_8_2_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_7_3_, -1, lvt_8_2_, p_74875_3_);
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_7_3_, -1, 12 - lvt_8_2_, p_74875_3_);
            }
         }

         for(lvt_7_3_ = 0; lvt_7_3_ <= 2; ++lvt_7_3_) {
            for(lvt_8_2_ = 4; lvt_8_2_ <= 8; ++lvt_8_2_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_7_3_, -1, lvt_8_2_, p_74875_3_);
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 12 - lvt_7_3_, -1, lvt_8_2_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Entrance extends StructureNetherBridgePieces.Piece {
      public Entrance() {
      }

      public Entrance(int p_i45617_1_, Random p_i45617_2_, StructureBoundingBox p_i45617_3_, EnumFacing p_i45617_4_) {
         super(p_i45617_1_);
         this.field_74885_f = p_i45617_4_;
         this.field_74887_e = p_i45617_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 3, true);
      }

      public static StructureNetherBridgePieces.Entrance func_175881_a(List<StructureComponent> p_175881_0_, Random p_175881_1_, int p_175881_2_, int p_175881_3_, int p_175881_4_, EnumFacing p_175881_5_, int p_175881_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175881_2_, p_175881_3_, p_175881_4_, -5, -3, 0, 13, 14, 13, p_175881_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175881_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Entrance(p_175881_6_, p_175881_1_, lvt_7_1_, p_175881_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 12, 4, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 0, 12, 13, 12, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 0, 1, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 11, 5, 0, 12, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 11, 4, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 5, 11, 10, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 9, 11, 7, 12, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 0, 4, 12, 1, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 5, 0, 10, 12, 1, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 9, 0, 7, 12, 1, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 11, 2, 10, 12, 10, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 8, 0, 7, 8, 0, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);

         int lvt_4_4_;
         for(lvt_4_4_ = 1; lvt_4_4_ <= 11; lvt_4_4_ += 2) {
            this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_4_, 10, 0, lvt_4_4_, 11, 0, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_4_, 10, 12, lvt_4_4_, 11, 12, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 10, lvt_4_4_, 0, 11, lvt_4_4_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 12, 10, lvt_4_4_, 12, 11, lvt_4_4_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_4_, 13, 0, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_4_, 13, 12, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 0, 13, lvt_4_4_, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 12, 13, lvt_4_4_, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), lvt_4_4_ + 1, 13, 0, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), lvt_4_4_ + 1, 13, 12, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 0, 13, lvt_4_4_ + 1, p_74875_3_);
            this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 12, 13, lvt_4_4_ + 1, p_74875_3_);
         }

         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 0, 13, 0, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 0, 13, 12, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 0, 13, 0, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 12, 13, 0, p_74875_3_);

         for(lvt_4_4_ = 3; lvt_4_4_ <= 9; lvt_4_4_ += 2) {
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 7, lvt_4_4_, 1, 8, lvt_4_4_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 11, 7, lvt_4_4_, 11, 8, lvt_4_4_, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 2, 0, 8, 2, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 4, 12, 2, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 0, 0, 8, 1, 3, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 0, 9, 8, 1, 12, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 4, 3, 1, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 0, 4, 12, 1, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         int lvt_5_2_;
         for(lvt_4_4_ = 4; lvt_4_4_ <= 8; ++lvt_4_4_) {
            for(lvt_5_2_ = 0; lvt_5_2_ <= 2; ++lvt_5_2_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_4_, -1, lvt_5_2_, p_74875_3_);
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_4_, -1, 12 - lvt_5_2_, p_74875_3_);
            }
         }

         for(lvt_4_4_ = 0; lvt_4_4_ <= 2; ++lvt_4_4_) {
            for(lvt_5_2_ = 4; lvt_5_2_ <= 8; ++lvt_5_2_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_4_, -1, lvt_5_2_, p_74875_3_);
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 12 - lvt_4_4_, -1, lvt_5_2_, p_74875_3_);
            }
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 5, 5, 7, 5, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 6, 6, 4, 6, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 6, 0, 6, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150356_k.func_176223_P(), 6, 5, 6, p_74875_3_);
         BlockPos lvt_4_5_ = new BlockPos(this.func_74865_a(6, 6), this.func_74862_a(5), this.func_74873_b(6, 6));
         if (p_74875_3_.func_175898_b(lvt_4_5_)) {
            p_74875_1_.func_175637_a(Blocks.field_150356_k, lvt_4_5_, p_74875_2_);
         }

         return true;
      }
   }

   public static class Throne extends StructureNetherBridgePieces.Piece {
      private boolean field_74976_a;

      public Throne() {
      }

      public Throne(int p_i45611_1_, Random p_i45611_2_, StructureBoundingBox p_i45611_3_, EnumFacing p_i45611_4_) {
         super(p_i45611_1_);
         this.field_74885_f = p_i45611_4_;
         this.field_74887_e = p_i45611_3_;
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         super.func_143011_b(p_143011_1_);
         this.field_74976_a = p_143011_1_.func_74767_n("Mob");
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         super.func_143012_a(p_143012_1_);
         p_143012_1_.func_74757_a("Mob", this.field_74976_a);
      }

      public static StructureNetherBridgePieces.Throne func_175874_a(List<StructureComponent> p_175874_0_, Random p_175874_1_, int p_175874_2_, int p_175874_3_, int p_175874_4_, int p_175874_5_, EnumFacing p_175874_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175874_2_, p_175874_3_, p_175874_4_, -2, 0, 0, 7, 8, 9, p_175874_6_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175874_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Throne(p_175874_5_, p_175874_1_, lvt_7_1_, p_175874_6_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 6, 7, 7, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 0, 0, 5, 1, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 1, 5, 2, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 2, 5, 3, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 3, 5, 4, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 0, 1, 4, 2, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 0, 5, 4, 2, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 5, 2, 1, 5, 3, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 5, 2, 5, 5, 3, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 3, 0, 5, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 5, 3, 6, 5, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 5, 8, 5, 5, 8, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 1, 6, 3, p_74875_3_);
         this.func_175811_a(p_74875_1_, Blocks.field_150386_bk.func_176223_P(), 5, 6, 3, p_74875_3_);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 6, 3, 0, 6, 8, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 6, 3, 6, 6, 8, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 6, 8, 5, 7, 8, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 8, 8, 4, 8, 8, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         if (!this.field_74976_a) {
            BlockPos lvt_4_1_ = new BlockPos(this.func_74865_a(3, 5), this.func_74862_a(5), this.func_74873_b(3, 5));
            if (p_74875_3_.func_175898_b(lvt_4_1_)) {
               this.field_74976_a = true;
               p_74875_1_.func_180501_a(lvt_4_1_, Blocks.field_150474_ac.func_176223_P(), 2);
               TileEntity lvt_5_1_ = p_74875_1_.func_175625_s(lvt_4_1_);
               if (lvt_5_1_ instanceof TileEntityMobSpawner) {
                  ((TileEntityMobSpawner)lvt_5_1_).func_145881_a().func_98272_a("Blaze");
               }
            }
         }

         for(int lvt_4_2_ = 0; lvt_4_2_ <= 6; ++lvt_4_2_) {
            for(int lvt_5_2_ = 0; lvt_5_2_ <= 6; ++lvt_5_2_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_2_, -1, lvt_5_2_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Stairs extends StructureNetherBridgePieces.Piece {
      public Stairs() {
      }

      public Stairs(int p_i45609_1_, Random p_i45609_2_, StructureBoundingBox p_i45609_3_, EnumFacing p_i45609_4_) {
         super(p_i45609_1_);
         this.field_74885_f = p_i45609_4_;
         this.field_74887_e = p_i45609_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 6, 2, false);
      }

      public static StructureNetherBridgePieces.Stairs func_175872_a(List<StructureComponent> p_175872_0_, Random p_175872_1_, int p_175872_2_, int p_175872_3_, int p_175872_4_, int p_175872_5_, EnumFacing p_175872_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175872_2_, p_175872_3_, p_175872_4_, -2, 0, 0, 7, 11, 7, p_175872_6_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175872_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Stairs(p_175872_5_, p_175872_1_, lvt_7_1_, p_175872_6_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 6, 1, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 6, 10, 6, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 1, 8, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 0, 6, 8, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 1, 0, 8, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 2, 1, 6, 8, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 6, 5, 8, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 2, 0, 5, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 3, 2, 6, 5, 2, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 3, 4, 6, 5, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175811_a(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 5, 2, 5, p_74875_3_);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 2, 5, 4, 3, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 2, 5, 3, 4, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 2, 5, 2, 5, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 5, 1, 6, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 7, 1, 5, 7, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 8, 2, 6, 8, 4, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 6, 0, 4, 8, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 0, 4, 5, 0, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);

         for(int lvt_4_1_ = 0; lvt_4_1_ <= 6; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ <= 6; ++lvt_5_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_1_, -1, lvt_5_1_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Crossing extends StructureNetherBridgePieces.Piece {
      public Crossing() {
      }

      public Crossing(int p_i45610_1_, Random p_i45610_2_, StructureBoundingBox p_i45610_3_, EnumFacing p_i45610_4_) {
         super(p_i45610_1_);
         this.field_74885_f = p_i45610_4_;
         this.field_74887_e = p_i45610_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 2, 0, false);
         this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 2, false);
         this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 2, false);
      }

      public static StructureNetherBridgePieces.Crossing func_175873_a(List<StructureComponent> p_175873_0_, Random p_175873_1_, int p_175873_2_, int p_175873_3_, int p_175873_4_, EnumFacing p_175873_5_, int p_175873_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175873_2_, p_175873_3_, p_175873_4_, -2, 0, 0, 7, 9, 7, p_175873_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175873_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Crossing(p_175873_6_, p_175873_1_, lvt_7_1_, p_175873_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 6, 1, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 6, 7, 6, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 1, 6, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 6, 1, 6, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 0, 6, 6, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 6, 6, 6, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 6, 1, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 5, 0, 6, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 2, 0, 6, 6, 1, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 2, 5, 6, 6, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 6, 0, 4, 6, 0, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 0, 4, 5, 0, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 6, 6, 4, 6, 6, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 5, 6, 4, 5, 6, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 6, 2, 0, 6, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 2, 0, 5, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 6, 2, 6, 6, 4, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 5, 2, 6, 5, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);

         for(int lvt_4_1_ = 0; lvt_4_1_ <= 6; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ <= 6; ++lvt_5_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_1_, -1, lvt_5_1_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class Crossing3 extends StructureNetherBridgePieces.Piece {
      public Crossing3() {
      }

      public Crossing3(int p_i45622_1_, Random p_i45622_2_, StructureBoundingBox p_i45622_3_, EnumFacing p_i45622_4_) {
         super(p_i45622_1_);
         this.field_74885_f = p_i45622_4_;
         this.field_74887_e = p_i45622_3_;
      }

      protected Crossing3(Random p_i2042_1_, int p_i2042_2_, int p_i2042_3_) {
         super(0);
         this.field_74885_f = EnumFacing.Plane.HORIZONTAL.func_179518_a(p_i2042_1_);
         switch(this.field_74885_f) {
         case NORTH:
         case SOUTH:
            this.field_74887_e = new StructureBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
            break;
         default:
            this.field_74887_e = new StructureBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
         }

      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 8, 3, false);
         this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 3, 8, false);
         this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 3, 8, false);
      }

      public static StructureNetherBridgePieces.Crossing3 func_175885_a(List<StructureComponent> p_175885_0_, Random p_175885_1_, int p_175885_2_, int p_175885_3_, int p_175885_4_, EnumFacing p_175885_5_, int p_175885_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175885_2_, p_175885_3_, p_175885_4_, -8, -3, 0, 19, 10, 19, p_175885_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175885_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Crossing3(p_175885_6_, p_175885_1_, lvt_7_1_, p_175885_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 3, 0, 11, 4, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 7, 18, 4, 11, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 5, 0, 10, 7, 18, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 8, 18, 7, 10, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 5, 0, 7, 5, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 5, 11, 7, 5, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 11, 5, 0, 11, 5, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 11, 5, 11, 11, 5, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 7, 7, 5, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 11, 5, 7, 18, 5, 7, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 11, 7, 5, 11, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 11, 5, 11, 18, 5, 11, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 0, 11, 2, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 13, 11, 2, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 0, 0, 11, 1, 3, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 0, 15, 11, 1, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         int lvt_4_2_;
         int lvt_5_2_;
         for(lvt_4_2_ = 7; lvt_4_2_ <= 11; ++lvt_4_2_) {
            for(lvt_5_2_ = 0; lvt_5_2_ <= 2; ++lvt_5_2_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_2_, -1, lvt_5_2_, p_74875_3_);
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_2_, -1, 18 - lvt_5_2_, p_74875_3_);
            }
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 7, 5, 2, 11, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 13, 2, 7, 18, 2, 11, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 7, 3, 1, 11, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 15, 0, 7, 18, 1, 11, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         for(lvt_4_2_ = 0; lvt_4_2_ <= 2; ++lvt_4_2_) {
            for(lvt_5_2_ = 7; lvt_5_2_ <= 11; ++lvt_5_2_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_2_, -1, lvt_5_2_, p_74875_3_);
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), 18 - lvt_4_2_, -1, lvt_5_2_, p_74875_3_);
            }
         }

         return true;
      }
   }

   public static class End extends StructureNetherBridgePieces.Piece {
      private int field_74972_a;

      public End() {
      }

      public End(int p_i45621_1_, Random p_i45621_2_, StructureBoundingBox p_i45621_3_, EnumFacing p_i45621_4_) {
         super(p_i45621_1_);
         this.field_74885_f = p_i45621_4_;
         this.field_74887_e = p_i45621_3_;
         this.field_74972_a = p_i45621_2_.nextInt();
      }

      public static StructureNetherBridgePieces.End func_175884_a(List<StructureComponent> p_175884_0_, Random p_175884_1_, int p_175884_2_, int p_175884_3_, int p_175884_4_, EnumFacing p_175884_5_, int p_175884_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175884_2_, p_175884_3_, p_175884_4_, -1, -3, 0, 5, 10, 8, p_175884_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175884_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.End(p_175884_6_, p_175884_1_, lvt_7_1_, p_175884_5_) : null;
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         super.func_143011_b(p_143011_1_);
         this.field_74972_a = p_143011_1_.func_74762_e("Seed");
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         super.func_143012_a(p_143012_1_);
         p_143012_1_.func_74768_a("Seed", this.field_74972_a);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         Random lvt_4_1_ = new Random((long)this.field_74972_a);

         int lvt_5_5_;
         int lvt_6_3_;
         int lvt_7_2_;
         for(lvt_5_5_ = 0; lvt_5_5_ <= 4; ++lvt_5_5_) {
            for(lvt_6_3_ = 3; lvt_6_3_ <= 4; ++lvt_6_3_) {
               lvt_7_2_ = lvt_4_1_.nextInt(8);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_5_5_, lvt_6_3_, 0, lvt_5_5_, lvt_6_3_, lvt_7_2_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
            }
         }

         lvt_5_5_ = lvt_4_1_.nextInt(8);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 0, 0, 5, lvt_5_5_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         lvt_5_5_ = lvt_4_1_.nextInt(8);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 5, 0, 4, 5, lvt_5_5_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         for(lvt_5_5_ = 0; lvt_5_5_ <= 4; ++lvt_5_5_) {
            lvt_6_3_ = lvt_4_1_.nextInt(5);
            this.func_175804_a(p_74875_1_, p_74875_3_, lvt_5_5_, 2, 0, lvt_5_5_, 2, lvt_6_3_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         }

         for(lvt_5_5_ = 0; lvt_5_5_ <= 4; ++lvt_5_5_) {
            for(lvt_6_3_ = 0; lvt_6_3_ <= 1; ++lvt_6_3_) {
               lvt_7_2_ = lvt_4_1_.nextInt(3);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_5_5_, lvt_6_3_, 0, lvt_5_5_, lvt_6_3_, lvt_7_2_, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
            }
         }

         return true;
      }
   }

   public static class Straight extends StructureNetherBridgePieces.Piece {
      public Straight() {
      }

      public Straight(int p_i45620_1_, Random p_i45620_2_, StructureBoundingBox p_i45620_3_, EnumFacing p_i45620_4_) {
         super(p_i45620_1_);
         this.field_74885_f = p_i45620_4_;
         this.field_74887_e = p_i45620_3_;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 3, false);
      }

      public static StructureNetherBridgePieces.Straight func_175882_a(List<StructureComponent> p_175882_0_, Random p_175882_1_, int p_175882_2_, int p_175882_3_, int p_175882_4_, EnumFacing p_175882_5_, int p_175882_6_) {
         StructureBoundingBox lvt_7_1_ = StructureBoundingBox.func_175897_a(p_175882_2_, p_175882_3_, p_175882_4_, -1, -3, 0, 5, 10, 19, p_175882_5_);
         return func_74964_a(lvt_7_1_) && StructureComponent.func_74883_a(p_175882_0_, lvt_7_1_) == null ? new StructureNetherBridgePieces.Straight(p_175882_6_, p_175882_1_, lvt_7_1_, p_175882_5_) : null;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 4, 4, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 5, 0, 3, 7, 18, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 0, 0, 5, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 5, 0, 4, 5, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 2, 5, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 13, 4, 2, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 3, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 15, 4, 1, 18, Blocks.field_150385_bj.func_176223_P(), Blocks.field_150385_bj.func_176223_P(), false);

         for(int lvt_4_1_ = 0; lvt_4_1_ <= 4; ++lvt_4_1_) {
            for(int lvt_5_1_ = 0; lvt_5_1_ <= 2; ++lvt_5_1_) {
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_1_, -1, lvt_5_1_, p_74875_3_);
               this.func_175808_b(p_74875_1_, Blocks.field_150385_bj.func_176223_P(), lvt_4_1_, -1, 18 - lvt_5_1_, p_74875_3_);
            }
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 4, 1, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 4, 0, 4, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 14, 0, 4, 14, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 17, 0, 4, 17, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 1, 1, 4, 4, 1, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 3, 4, 4, 4, 4, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 3, 14, 4, 4, 14, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 1, 17, 4, 4, 17, Blocks.field_150386_bk.func_176223_P(), Blocks.field_150386_bk.func_176223_P(), false);
         return true;
      }
   }

   public static class Start extends StructureNetherBridgePieces.Crossing3 {
      public StructureNetherBridgePieces.PieceWeight field_74970_a;
      public List<StructureNetherBridgePieces.PieceWeight> field_74968_b;
      public List<StructureNetherBridgePieces.PieceWeight> field_74969_c;
      public List<StructureComponent> field_74967_d = Lists.newArrayList();

      public Start() {
      }

      public Start(Random p_i2059_1_, int p_i2059_2_, int p_i2059_3_) {
         super(p_i2059_1_, p_i2059_2_, p_i2059_3_);
         this.field_74968_b = Lists.newArrayList();
         StructureNetherBridgePieces.PieceWeight[] lvt_4_2_ = StructureNetherBridgePieces.field_78742_a;
         int lvt_5_2_ = lvt_4_2_.length;

         int lvt_6_2_;
         StructureNetherBridgePieces.PieceWeight lvt_7_2_;
         for(lvt_6_2_ = 0; lvt_6_2_ < lvt_5_2_; ++lvt_6_2_) {
            lvt_7_2_ = lvt_4_2_[lvt_6_2_];
            lvt_7_2_.field_78827_c = 0;
            this.field_74968_b.add(lvt_7_2_);
         }

         this.field_74969_c = Lists.newArrayList();
         lvt_4_2_ = StructureNetherBridgePieces.field_78741_b;
         lvt_5_2_ = lvt_4_2_.length;

         for(lvt_6_2_ = 0; lvt_6_2_ < lvt_5_2_; ++lvt_6_2_) {
            lvt_7_2_ = lvt_4_2_[lvt_6_2_];
            lvt_7_2_.field_78827_c = 0;
            this.field_74969_c.add(lvt_7_2_);
         }

      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         super.func_143011_b(p_143011_1_);
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         super.func_143012_a(p_143012_1_);
      }
   }

   abstract static class Piece extends StructureComponent {
      protected static final List<WeightedRandomChestContent> field_111019_a;

      public Piece() {
      }

      protected Piece(int p_i2054_1_) {
         super(p_i2054_1_);
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
      }

      private int func_74960_a(List<StructureNetherBridgePieces.PieceWeight> p_74960_1_) {
         boolean lvt_2_1_ = false;
         int lvt_3_1_ = 0;

         StructureNetherBridgePieces.PieceWeight lvt_5_1_;
         for(Iterator lvt_4_1_ = p_74960_1_.iterator(); lvt_4_1_.hasNext(); lvt_3_1_ += lvt_5_1_.field_78826_b) {
            lvt_5_1_ = (StructureNetherBridgePieces.PieceWeight)lvt_4_1_.next();
            if (lvt_5_1_.field_78824_d > 0 && lvt_5_1_.field_78827_c < lvt_5_1_.field_78824_d) {
               lvt_2_1_ = true;
            }
         }

         return lvt_2_1_ ? lvt_3_1_ : -1;
      }

      private StructureNetherBridgePieces.Piece func_175871_a(StructureNetherBridgePieces.Start p_175871_1_, List<StructureNetherBridgePieces.PieceWeight> p_175871_2_, List<StructureComponent> p_175871_3_, Random p_175871_4_, int p_175871_5_, int p_175871_6_, int p_175871_7_, EnumFacing p_175871_8_, int p_175871_9_) {
         int lvt_10_1_ = this.func_74960_a(p_175871_2_);
         boolean lvt_11_1_ = lvt_10_1_ > 0 && p_175871_9_ <= 30;
         int lvt_12_1_ = 0;

         while(lvt_12_1_ < 5 && lvt_11_1_) {
            ++lvt_12_1_;
            int lvt_13_1_ = p_175871_4_.nextInt(lvt_10_1_);
            Iterator lvt_14_1_ = p_175871_2_.iterator();

            while(lvt_14_1_.hasNext()) {
               StructureNetherBridgePieces.PieceWeight lvt_15_1_ = (StructureNetherBridgePieces.PieceWeight)lvt_14_1_.next();
               lvt_13_1_ -= lvt_15_1_.field_78826_b;
               if (lvt_13_1_ < 0) {
                  if (!lvt_15_1_.func_78822_a() || lvt_15_1_ == p_175871_1_.field_74970_a && !lvt_15_1_.field_78825_e) {
                     break;
                  }

                  StructureNetherBridgePieces.Piece lvt_16_1_ = StructureNetherBridgePieces.func_175887_b(lvt_15_1_, p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
                  if (lvt_16_1_ != null) {
                     ++lvt_15_1_.field_78827_c;
                     p_175871_1_.field_74970_a = lvt_15_1_;
                     if (!lvt_15_1_.func_78823_a()) {
                        p_175871_2_.remove(lvt_15_1_);
                     }

                     return lvt_16_1_;
                  }
               }
            }
         }

         return StructureNetherBridgePieces.End.func_175884_a(p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
      }

      private StructureComponent func_175870_a(StructureNetherBridgePieces.Start p_175870_1_, List<StructureComponent> p_175870_2_, Random p_175870_3_, int p_175870_4_, int p_175870_5_, int p_175870_6_, EnumFacing p_175870_7_, int p_175870_8_, boolean p_175870_9_) {
         if (Math.abs(p_175870_4_ - p_175870_1_.func_74874_b().field_78897_a) <= 112 && Math.abs(p_175870_6_ - p_175870_1_.func_74874_b().field_78896_c) <= 112) {
            List<StructureNetherBridgePieces.PieceWeight> lvt_10_1_ = p_175870_1_.field_74968_b;
            if (p_175870_9_) {
               lvt_10_1_ = p_175870_1_.field_74969_c;
            }

            StructureComponent lvt_11_1_ = this.func_175871_a(p_175870_1_, lvt_10_1_, p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_ + 1);
            if (lvt_11_1_ != null) {
               p_175870_2_.add(lvt_11_1_);
               p_175870_1_.field_74967_d.add(lvt_11_1_);
            }

            return lvt_11_1_;
         } else {
            return StructureNetherBridgePieces.End.func_175884_a(p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_);
         }
      }

      protected StructureComponent func_74963_a(StructureNetherBridgePieces.Start p_74963_1_, List<StructureComponent> p_74963_2_, Random p_74963_3_, int p_74963_4_, int p_74963_5_, boolean p_74963_6_) {
         if (this.field_74885_f != null) {
            switch(this.field_74885_f) {
            case NORTH:
               return this.func_175870_a(p_74963_1_, p_74963_2_, p_74963_3_, this.field_74887_e.field_78897_a + p_74963_4_, this.field_74887_e.field_78895_b + p_74963_5_, this.field_74887_e.field_78896_c - 1, this.field_74885_f, this.func_74877_c(), p_74963_6_);
            case SOUTH:
               return this.func_175870_a(p_74963_1_, p_74963_2_, p_74963_3_, this.field_74887_e.field_78897_a + p_74963_4_, this.field_74887_e.field_78895_b + p_74963_5_, this.field_74887_e.field_78892_f + 1, this.field_74885_f, this.func_74877_c(), p_74963_6_);
            case WEST:
               return this.func_175870_a(p_74963_1_, p_74963_2_, p_74963_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74963_5_, this.field_74887_e.field_78896_c + p_74963_4_, this.field_74885_f, this.func_74877_c(), p_74963_6_);
            case EAST:
               return this.func_175870_a(p_74963_1_, p_74963_2_, p_74963_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74963_5_, this.field_74887_e.field_78896_c + p_74963_4_, this.field_74885_f, this.func_74877_c(), p_74963_6_);
            }
         }

         return null;
      }

      protected StructureComponent func_74961_b(StructureNetherBridgePieces.Start p_74961_1_, List<StructureComponent> p_74961_2_, Random p_74961_3_, int p_74961_4_, int p_74961_5_, boolean p_74961_6_) {
         if (this.field_74885_f != null) {
            switch(this.field_74885_f) {
            case NORTH:
               return this.func_175870_a(p_74961_1_, p_74961_2_, p_74961_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74961_4_, this.field_74887_e.field_78896_c + p_74961_5_, EnumFacing.WEST, this.func_74877_c(), p_74961_6_);
            case SOUTH:
               return this.func_175870_a(p_74961_1_, p_74961_2_, p_74961_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74961_4_, this.field_74887_e.field_78896_c + p_74961_5_, EnumFacing.WEST, this.func_74877_c(), p_74961_6_);
            case WEST:
               return this.func_175870_a(p_74961_1_, p_74961_2_, p_74961_3_, this.field_74887_e.field_78897_a + p_74961_5_, this.field_74887_e.field_78895_b + p_74961_4_, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, this.func_74877_c(), p_74961_6_);
            case EAST:
               return this.func_175870_a(p_74961_1_, p_74961_2_, p_74961_3_, this.field_74887_e.field_78897_a + p_74961_5_, this.field_74887_e.field_78895_b + p_74961_4_, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, this.func_74877_c(), p_74961_6_);
            }
         }

         return null;
      }

      protected StructureComponent func_74965_c(StructureNetherBridgePieces.Start p_74965_1_, List<StructureComponent> p_74965_2_, Random p_74965_3_, int p_74965_4_, int p_74965_5_, boolean p_74965_6_) {
         if (this.field_74885_f != null) {
            switch(this.field_74885_f) {
            case NORTH:
               return this.func_175870_a(p_74965_1_, p_74965_2_, p_74965_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74965_4_, this.field_74887_e.field_78896_c + p_74965_5_, EnumFacing.EAST, this.func_74877_c(), p_74965_6_);
            case SOUTH:
               return this.func_175870_a(p_74965_1_, p_74965_2_, p_74965_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74965_4_, this.field_74887_e.field_78896_c + p_74965_5_, EnumFacing.EAST, this.func_74877_c(), p_74965_6_);
            case WEST:
               return this.func_175870_a(p_74965_1_, p_74965_2_, p_74965_3_, this.field_74887_e.field_78897_a + p_74965_5_, this.field_74887_e.field_78895_b + p_74965_4_, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, this.func_74877_c(), p_74965_6_);
            case EAST:
               return this.func_175870_a(p_74965_1_, p_74965_2_, p_74965_3_, this.field_74887_e.field_78897_a + p_74965_5_, this.field_74887_e.field_78895_b + p_74965_4_, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, this.func_74877_c(), p_74965_6_);
            }
         }

         return null;
      }

      protected static boolean func_74964_a(StructureBoundingBox p_74964_0_) {
         return p_74964_0_ != null && p_74964_0_.field_78895_b > 10;
      }

      static {
         field_111019_a = Lists.newArrayList((Object[])(new WeightedRandomChestContent(Items.field_151045_i, 0, 1, 3, 5), new WeightedRandomChestContent(Items.field_151042_j, 0, 1, 5, 5), new WeightedRandomChestContent(Items.field_151043_k, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151010_B, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151171_ah, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151033_d, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151075_bm, 0, 3, 7, 5), new WeightedRandomChestContent(Items.field_151141_av, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151136_bY, 0, 1, 1, 8), new WeightedRandomChestContent(Items.field_151138_bX, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151125_bZ, 0, 1, 1, 3), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150343_Z), 0, 2, 4, 2)));
      }
   }

   static class PieceWeight {
      public Class<? extends StructureNetherBridgePieces.Piece> field_78828_a;
      public final int field_78826_b;
      public int field_78827_c;
      public int field_78824_d;
      public boolean field_78825_e;

      public PieceWeight(Class<? extends StructureNetherBridgePieces.Piece> p_i2055_1_, int p_i2055_2_, int p_i2055_3_, boolean p_i2055_4_) {
         this.field_78828_a = p_i2055_1_;
         this.field_78826_b = p_i2055_2_;
         this.field_78824_d = p_i2055_3_;
         this.field_78825_e = p_i2055_4_;
      }

      public PieceWeight(Class<? extends StructureNetherBridgePieces.Piece> p_i2056_1_, int p_i2056_2_, int p_i2056_3_) {
         this(p_i2056_1_, p_i2056_2_, p_i2056_3_, false);
      }

      public boolean func_78822_a(int p_78822_1_) {
         return this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d;
      }

      public boolean func_78823_a() {
         return this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d;
      }
   }
}
