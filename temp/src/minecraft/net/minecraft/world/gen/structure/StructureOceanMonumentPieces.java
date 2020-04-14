package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureOceanMonumentPieces {
   public static void func_175970_a() {
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.MonumentBuilding.class, "OMB");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.MonumentCoreRoom.class, "OMCR");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.DoubleXRoom.class, "OMDXR");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.DoubleXYRoom.class, "OMDXYR");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.DoubleYRoom.class, "OMDYR");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.DoubleYZRoom.class, "OMDYZR");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.DoubleZRoom.class, "OMDZR");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.EntryRoom.class, "OMEntry");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.Penthouse.class, "OMPenthouse");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.SimpleRoom.class, "OMSimple");
      MapGenStructureIO.func_143031_a(StructureOceanMonumentPieces.SimpleTopRoom.class, "OMSimpleT");
   }

   static class YZDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      private YZDoubleRoomFitHelper() {
      }

      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
         if (p_175969_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()] && !p_175969_1_.field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175963_d && p_175969_1_.field_175966_c[EnumFacing.UP.func_176745_a()] && !p_175969_1_.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d) {
            StructureOceanMonumentPieces.RoomDefinition lvt_2_1_ = p_175969_1_.field_175965_b[EnumFacing.NORTH.func_176745_a()];
            return lvt_2_1_.field_175966_c[EnumFacing.UP.func_176745_a()] && !lvt_2_1_.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d;
         } else {
            return false;
         }
      }

      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
         p_175968_2_.field_175963_d = true;
         p_175968_2_.field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175963_d = true;
         p_175968_2_.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         p_175968_2_.field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         return new StructureOceanMonumentPieces.DoubleYZRoom(p_175968_1_, p_175968_2_, p_175968_3_);
      }
   }

   static class XYDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      private XYDoubleRoomFitHelper() {
      }

      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
         if (p_175969_1_.field_175966_c[EnumFacing.EAST.func_176745_a()] && !p_175969_1_.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175963_d && p_175969_1_.field_175966_c[EnumFacing.UP.func_176745_a()] && !p_175969_1_.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d) {
            StructureOceanMonumentPieces.RoomDefinition lvt_2_1_ = p_175969_1_.field_175965_b[EnumFacing.EAST.func_176745_a()];
            return lvt_2_1_.field_175966_c[EnumFacing.UP.func_176745_a()] && !lvt_2_1_.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d;
         } else {
            return false;
         }
      }

      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
         p_175968_2_.field_175963_d = true;
         p_175968_2_.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175963_d = true;
         p_175968_2_.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         p_175968_2_.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         return new StructureOceanMonumentPieces.DoubleXYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
      }
   }

   static class ZDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      private ZDoubleRoomFitHelper() {
      }

      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
         return p_175969_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()] && !p_175969_1_.field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175963_d;
      }

      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
         StructureOceanMonumentPieces.RoomDefinition lvt_4_1_ = p_175968_2_;
         if (!p_175968_2_.field_175966_c[EnumFacing.NORTH.func_176745_a()] || p_175968_2_.field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175963_d) {
            lvt_4_1_ = p_175968_2_.field_175965_b[EnumFacing.SOUTH.func_176745_a()];
         }

         lvt_4_1_.field_175963_d = true;
         lvt_4_1_.field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175963_d = true;
         return new StructureOceanMonumentPieces.DoubleZRoom(p_175968_1_, lvt_4_1_, p_175968_3_);
      }
   }

   static class XDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      private XDoubleRoomFitHelper() {
      }

      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
         return p_175969_1_.field_175966_c[EnumFacing.EAST.func_176745_a()] && !p_175969_1_.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175963_d;
      }

      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
         p_175968_2_.field_175963_d = true;
         p_175968_2_.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175963_d = true;
         return new StructureOceanMonumentPieces.DoubleXRoom(p_175968_1_, p_175968_2_, p_175968_3_);
      }
   }

   static class YDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      private YDoubleRoomFitHelper() {
      }

      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
         return p_175969_1_.field_175966_c[EnumFacing.UP.func_176745_a()] && !p_175969_1_.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d;
      }

      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
         p_175968_2_.field_175963_d = true;
         p_175968_2_.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         return new StructureOceanMonumentPieces.DoubleYRoom(p_175968_1_, p_175968_2_, p_175968_3_);
      }
   }

   static class FitSimpleRoomTopHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      private FitSimpleRoomTopHelper() {
      }

      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
         return !p_175969_1_.field_175966_c[EnumFacing.WEST.func_176745_a()] && !p_175969_1_.field_175966_c[EnumFacing.EAST.func_176745_a()] && !p_175969_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()] && !p_175969_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()] && !p_175969_1_.field_175966_c[EnumFacing.UP.func_176745_a()];
      }

      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
         p_175968_2_.field_175963_d = true;
         return new StructureOceanMonumentPieces.SimpleTopRoom(p_175968_1_, p_175968_2_, p_175968_3_);
      }
   }

   static class FitSimpleRoomHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      private FitSimpleRoomHelper() {
      }

      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition p_175969_1_) {
         return true;
      }

      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing p_175968_1_, StructureOceanMonumentPieces.RoomDefinition p_175968_2_, Random p_175968_3_) {
         p_175968_2_.field_175963_d = true;
         return new StructureOceanMonumentPieces.SimpleRoom(p_175968_1_, p_175968_2_, p_175968_3_);
      }
   }

   interface MonumentRoomFitHelper {
      boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition var1);

      StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3);
   }

   static class RoomDefinition {
      int field_175967_a;
      StructureOceanMonumentPieces.RoomDefinition[] field_175965_b = new StructureOceanMonumentPieces.RoomDefinition[6];
      boolean[] field_175966_c = new boolean[6];
      boolean field_175963_d;
      boolean field_175964_e;
      int field_175962_f;

      public RoomDefinition(int p_i45584_1_) {
         this.field_175967_a = p_i45584_1_;
      }

      public void func_175957_a(EnumFacing p_175957_1_, StructureOceanMonumentPieces.RoomDefinition p_175957_2_) {
         this.field_175965_b[p_175957_1_.func_176745_a()] = p_175957_2_;
         p_175957_2_.field_175965_b[p_175957_1_.func_176734_d().func_176745_a()] = this;
      }

      public void func_175958_a() {
         for(int lvt_1_1_ = 0; lvt_1_1_ < 6; ++lvt_1_1_) {
            this.field_175966_c[lvt_1_1_] = this.field_175965_b[lvt_1_1_] != null;
         }

      }

      public boolean func_175959_a(int p_175959_1_) {
         if (this.field_175964_e) {
            return true;
         } else {
            this.field_175962_f = p_175959_1_;

            for(int lvt_2_1_ = 0; lvt_2_1_ < 6; ++lvt_2_1_) {
               if (this.field_175965_b[lvt_2_1_] != null && this.field_175966_c[lvt_2_1_] && this.field_175965_b[lvt_2_1_].field_175962_f != p_175959_1_ && this.field_175965_b[lvt_2_1_].func_175959_a(p_175959_1_)) {
                  return true;
               }
            }

            return false;
         }
      }

      public boolean func_175961_b() {
         return this.field_175967_a >= 75;
      }

      public int func_175960_c() {
         int lvt_1_1_ = 0;

         for(int lvt_2_1_ = 0; lvt_2_1_ < 6; ++lvt_2_1_) {
            if (this.field_175966_c[lvt_2_1_]) {
               ++lvt_1_1_;
            }
         }

         return lvt_1_1_;
      }
   }

   public static class Penthouse extends StructureOceanMonumentPieces.Piece {
      public Penthouse() {
      }

      public Penthouse(EnumFacing p_i45591_1_, StructureBoundingBox p_i45591_2_) {
         super(p_i45591_1_, p_i45591_2_);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, -1, 2, 11, -1, 11, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, -1, 0, 1, -1, 11, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 12, -1, 0, 13, -1, 11, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, -1, 0, 11, -1, 1, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, -1, 12, 11, -1, 13, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 0, 0, 13, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 13, 0, 0, 13, 0, 13, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 0, 0, 12, 0, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 0, 13, 12, 0, 13, field_175826_b, field_175826_b, false);

         for(int lvt_4_1_ = 2; lvt_4_1_ <= 11; lvt_4_1_ += 3) {
            this.func_175811_a(p_74875_1_, field_175825_e, 0, 0, lvt_4_1_, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 13, 0, lvt_4_1_, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, lvt_4_1_, 0, 0, p_74875_3_);
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 0, 3, 4, 0, 9, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 0, 3, 11, 0, 9, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 4, 0, 9, 9, 0, 11, field_175826_b, field_175826_b, false);
         this.func_175811_a(p_74875_1_, field_175826_b, 5, 0, 8, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 8, 0, 8, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 10, 0, 10, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 3, 0, 10, p_74875_3_);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 0, 3, 3, 0, 7, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 0, 3, 10, 0, 7, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 0, 10, 7, 0, 10, field_175827_c, field_175827_c, false);
         int lvt_4_2_ = 3;

         for(int lvt_5_1_ = 0; lvt_5_1_ < 2; ++lvt_5_1_) {
            for(int lvt_6_1_ = 2; lvt_6_1_ <= 8; lvt_6_1_ += 3) {
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_2_, 0, lvt_6_1_, lvt_4_2_, 2, lvt_6_1_, field_175826_b, field_175826_b, false);
            }

            lvt_4_2_ = 10;
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 0, 10, 5, 2, 10, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 8, 0, 10, 8, 2, 10, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, -1, 7, 7, -1, 8, field_175827_c, field_175827_c, false);
         this.func_181655_a(p_74875_1_, p_74875_3_, 6, -1, 3, 7, -1, 4, false);
         this.func_175817_a(p_74875_1_, p_74875_3_, 6, 1, 6);
         return true;
      }
   }

   public static class WingRoom extends StructureOceanMonumentPieces.Piece {
      private int field_175834_o;

      public WingRoom() {
      }

      public WingRoom(EnumFacing p_i45585_1_, StructureBoundingBox p_i45585_2_, int p_i45585_3_) {
         super(p_i45585_1_, p_i45585_2_);
         this.field_175834_o = p_i45585_3_ & 1;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if (this.field_175834_o == 0) {
            int lvt_4_2_;
            for(lvt_4_2_ = 0; lvt_4_2_ < 4; ++lvt_4_2_) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 10 - lvt_4_2_, 3 - lvt_4_2_, 20 - lvt_4_2_, 12 + lvt_4_2_, 3 - lvt_4_2_, 20, field_175826_b, field_175826_b, false);
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 0, 6, 15, 0, 16, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, 0, 6, 6, 3, 20, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 16, 0, 6, 16, 3, 20, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 7, 7, 1, 20, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 15, 1, 7, 15, 1, 20, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 6, 9, 3, 6, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 13, 1, 6, 15, 3, 6, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 8, 1, 7, 9, 1, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 13, 1, 7, 14, 1, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 9, 0, 5, 13, 0, 5, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 10, 0, 7, 12, 0, 7, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 8, 0, 10, 8, 0, 12, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 14, 0, 10, 14, 0, 12, field_175827_c, field_175827_c, false);

            for(lvt_4_2_ = 18; lvt_4_2_ >= 7; lvt_4_2_ -= 3) {
               this.func_175811_a(p_74875_1_, field_175825_e, 6, 3, lvt_4_2_, p_74875_3_);
               this.func_175811_a(p_74875_1_, field_175825_e, 16, 3, lvt_4_2_, p_74875_3_);
            }

            this.func_175811_a(p_74875_1_, field_175825_e, 10, 0, 10, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 12, 0, 10, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 10, 0, 12, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 12, 0, 12, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 8, 3, 6, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 14, 3, 6, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 4, 2, 4, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 4, 1, 4, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 4, 0, 4, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 18, 2, 4, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 18, 1, 4, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 18, 0, 4, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 4, 2, 18, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 4, 1, 18, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 4, 0, 18, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 18, 2, 18, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 18, 1, 18, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 18, 0, 18, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 9, 7, 20, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175826_b, 13, 7, 20, p_74875_3_);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, 0, 21, 7, 4, 21, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 15, 0, 21, 16, 4, 21, field_175826_b, field_175826_b, false);
            this.func_175817_a(p_74875_1_, p_74875_3_, 11, 2, 16);
         } else if (this.field_175834_o == 1) {
            this.func_175804_a(p_74875_1_, p_74875_3_, 9, 3, 18, 13, 3, 20, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 9, 0, 18, 9, 2, 18, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 13, 0, 18, 13, 2, 18, field_175826_b, field_175826_b, false);
            int lvt_4_3_ = 9;
            int lvt_5_1_ = 20;
            int lvt_6_1_ = 5;

            int lvt_7_3_;
            for(lvt_7_3_ = 0; lvt_7_3_ < 2; ++lvt_7_3_) {
               this.func_175811_a(p_74875_1_, field_175826_b, lvt_4_3_, lvt_6_1_ + 1, lvt_5_1_, p_74875_3_);
               this.func_175811_a(p_74875_1_, field_175825_e, lvt_4_3_, lvt_6_1_, lvt_5_1_, p_74875_3_);
               this.func_175811_a(p_74875_1_, field_175826_b, lvt_4_3_, lvt_6_1_ - 1, lvt_5_1_, p_74875_3_);
               lvt_4_3_ = 13;
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 3, 7, 15, 3, 14, field_175826_b, field_175826_b, false);
            lvt_4_3_ = 10;

            for(lvt_7_3_ = 0; lvt_7_3_ < 2; ++lvt_7_3_) {
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_3_, 0, 10, lvt_4_3_, 6, 10, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_3_, 0, 12, lvt_4_3_, 6, 12, field_175826_b, field_175826_b, false);
               this.func_175811_a(p_74875_1_, field_175825_e, lvt_4_3_, 0, 10, p_74875_3_);
               this.func_175811_a(p_74875_1_, field_175825_e, lvt_4_3_, 0, 12, p_74875_3_);
               this.func_175811_a(p_74875_1_, field_175825_e, lvt_4_3_, 4, 10, p_74875_3_);
               this.func_175811_a(p_74875_1_, field_175825_e, lvt_4_3_, 4, 12, p_74875_3_);
               lvt_4_3_ = 12;
            }

            lvt_4_3_ = 8;

            for(lvt_7_3_ = 0; lvt_7_3_ < 2; ++lvt_7_3_) {
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_3_, 0, 7, lvt_4_3_, 2, 7, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_3_, 0, 14, lvt_4_3_, 2, 14, field_175826_b, field_175826_b, false);
               lvt_4_3_ = 14;
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, 8, 3, 8, 8, 3, 13, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 14, 3, 8, 14, 3, 13, field_175827_c, field_175827_c, false);
            this.func_175817_a(p_74875_1_, p_74875_3_, 11, 5, 13);
         }

         return true;
      }
   }

   public static class MonumentCoreRoom extends StructureOceanMonumentPieces.Piece {
      public MonumentCoreRoom() {
      }

      public MonumentCoreRoom(EnumFacing p_i45598_1_, StructureOceanMonumentPieces.RoomDefinition p_i45598_2_, Random p_i45598_3_) {
         super(1, p_i45598_1_, p_i45598_2_, 2, 2, 2);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175819_a(p_74875_1_, p_74875_3_, 1, 8, 0, 14, 8, 14, field_175828_a);
         int lvt_4_1_ = 7;
         IBlockState lvt_5_2_ = field_175826_b;
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_4_1_, 0, 0, lvt_4_1_, 15, lvt_5_2_, lvt_5_2_, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 15, lvt_4_1_, 0, 15, lvt_4_1_, 15, lvt_5_2_, lvt_5_2_, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_4_1_, 0, 15, lvt_4_1_, 0, lvt_5_2_, lvt_5_2_, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_4_1_, 15, 14, lvt_4_1_, 15, lvt_5_2_, lvt_5_2_, false);

         int lvt_4_3_;
         for(lvt_4_3_ = 1; lvt_4_3_ <= 6; ++lvt_4_3_) {
            lvt_5_2_ = field_175826_b;
            if (lvt_4_3_ == 2 || lvt_4_3_ == 6) {
               lvt_5_2_ = field_175828_a;
            }

            for(int lvt_6_1_ = 0; lvt_6_1_ <= 15; lvt_6_1_ += 15) {
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_6_1_, lvt_4_3_, 0, lvt_6_1_, lvt_4_3_, 1, lvt_5_2_, lvt_5_2_, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_6_1_, lvt_4_3_, 6, lvt_6_1_, lvt_4_3_, 9, lvt_5_2_, lvt_5_2_, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_6_1_, lvt_4_3_, 14, lvt_6_1_, lvt_4_3_, 15, lvt_5_2_, lvt_5_2_, false);
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_4_3_, 0, 1, lvt_4_3_, 0, lvt_5_2_, lvt_5_2_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, lvt_4_3_, 0, 9, lvt_4_3_, 0, lvt_5_2_, lvt_5_2_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 14, lvt_4_3_, 0, 14, lvt_4_3_, 0, lvt_5_2_, lvt_5_2_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_4_3_, 15, 14, lvt_4_3_, 15, lvt_5_2_, lvt_5_2_, false);
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 3, 6, 9, 6, 9, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 4, 7, 8, 5, 8, Blocks.field_150340_R.func_176223_P(), Blocks.field_150340_R.func_176223_P(), false);

         for(lvt_4_3_ = 3; lvt_4_3_ <= 6; lvt_4_3_ += 3) {
            for(int lvt_5_3_ = 6; lvt_5_3_ <= 9; lvt_5_3_ += 3) {
               this.func_175811_a(p_74875_1_, field_175825_e, lvt_5_3_, lvt_4_3_, 6, p_74875_3_);
               this.func_175811_a(p_74875_1_, field_175825_e, lvt_5_3_, lvt_4_3_, 9, p_74875_3_);
            }
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 6, 5, 2, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 9, 5, 2, 9, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 1, 6, 10, 2, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 1, 9, 10, 2, 9, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 5, 6, 2, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 1, 5, 9, 2, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 10, 6, 2, 10, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 1, 10, 9, 2, 10, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 5, 5, 6, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 10, 5, 6, 10, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 2, 5, 10, 6, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 2, 10, 10, 6, 10, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 7, 1, 5, 7, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 7, 1, 10, 7, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 7, 9, 5, 7, 14, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 7, 9, 10, 7, 14, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 7, 5, 6, 7, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 7, 10, 6, 7, 10, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 7, 5, 14, 7, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 7, 10, 14, 7, 10, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 1, 2, 2, 1, 3, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 2, 3, 1, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 13, 1, 2, 13, 1, 3, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 12, 1, 2, 12, 1, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 1, 12, 2, 1, 13, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 13, 3, 1, 13, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 13, 1, 12, 13, 1, 13, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 12, 1, 13, 12, 1, 13, field_175826_b, field_175826_b, false);
         return true;
      }
   }

   public static class DoubleYZRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleYZRoom() {
      }

      public DoubleYZRoom(EnumFacing p_i45594_1_, StructureOceanMonumentPieces.RoomDefinition p_i45594_2_, Random p_i45594_3_) {
         super(1, p_i45594_1_, p_i45594_2_, 1, 2, 2);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         StructureOceanMonumentPieces.RoomDefinition lvt_4_1_ = this.field_175830_k.field_175965_b[EnumFacing.NORTH.func_176745_a()];
         StructureOceanMonumentPieces.RoomDefinition lvt_5_1_ = this.field_175830_k;
         StructureOceanMonumentPieces.RoomDefinition lvt_6_1_ = lvt_4_1_.field_175965_b[EnumFacing.UP.func_176745_a()];
         StructureOceanMonumentPieces.RoomDefinition lvt_7_1_ = lvt_5_1_.field_175965_b[EnumFacing.UP.func_176745_a()];
         if (this.field_175830_k.field_175967_a / 25 > 0) {
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 8, lvt_4_1_.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 0, lvt_5_1_.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
         }

         if (lvt_7_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 8, 1, 6, 8, 7, field_175828_a);
         }

         if (lvt_6_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 8, 8, 6, 8, 14, field_175828_a);
         }

         int lvt_8_2_;
         IBlockState lvt_9_2_;
         for(lvt_8_2_ = 1; lvt_8_2_ <= 7; ++lvt_8_2_) {
            lvt_9_2_ = field_175826_b;
            if (lvt_8_2_ == 2 || lvt_8_2_ == 6) {
               lvt_9_2_ = field_175828_a;
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_8_2_, 0, 0, lvt_8_2_, 15, lvt_9_2_, lvt_9_2_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, lvt_8_2_, 0, 7, lvt_8_2_, 15, lvt_9_2_, lvt_9_2_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_8_2_, 0, 6, lvt_8_2_, 0, lvt_9_2_, lvt_9_2_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_8_2_, 15, 6, lvt_8_2_, 15, lvt_9_2_, lvt_9_2_, false);
         }

         for(lvt_8_2_ = 1; lvt_8_2_ <= 7; ++lvt_8_2_) {
            lvt_9_2_ = field_175827_c;
            if (lvt_8_2_ == 2 || lvt_8_2_ == 6) {
               lvt_9_2_ = field_175825_e;
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, 3, lvt_8_2_, 7, 4, lvt_8_2_, 8, lvt_9_2_, lvt_9_2_, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 2, 0, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 7, 1, 3, 7, 2, 4, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 1, 3, 0, 2, 4, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 15, 4, 2, 15, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 1, 11, 0, 2, 12, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 7, 1, 11, 7, 2, 12, false);
         }

         if (lvt_7_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 5, 0, 4, 6, 0, false);
         }

         if (lvt_7_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 7, 5, 3, 7, 6, 4, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 4, 2, 6, 4, 5, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 2, 6, 3, 2, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 5, 6, 3, 5, field_175826_b, field_175826_b, false);
         }

         if (lvt_7_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 5, 3, 0, 6, 4, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 2, 2, 4, 5, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 2, 1, 3, 2, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 5, 1, 3, 5, field_175826_b, field_175826_b, false);
         }

         if (lvt_6_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 5, 15, 4, 6, 15, false);
         }

         if (lvt_6_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 5, 11, 0, 6, 12, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 10, 2, 4, 13, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 10, 1, 3, 10, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 13, 1, 3, 13, field_175826_b, field_175826_b, false);
         }

         if (lvt_6_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 7, 5, 11, 7, 6, 12, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 4, 10, 6, 4, 13, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 10, 6, 3, 10, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 13, 6, 3, 13, field_175826_b, field_175826_b, false);
         }

         return true;
      }
   }

   public static class DoubleXYRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleXYRoom() {
      }

      public DoubleXYRoom(EnumFacing p_i45596_1_, StructureOceanMonumentPieces.RoomDefinition p_i45596_2_, Random p_i45596_3_) {
         super(1, p_i45596_1_, p_i45596_2_, 2, 2, 1);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         StructureOceanMonumentPieces.RoomDefinition lvt_4_1_ = this.field_175830_k.field_175965_b[EnumFacing.EAST.func_176745_a()];
         StructureOceanMonumentPieces.RoomDefinition lvt_5_1_ = this.field_175830_k;
         StructureOceanMonumentPieces.RoomDefinition lvt_6_1_ = lvt_5_1_.field_175965_b[EnumFacing.UP.func_176745_a()];
         StructureOceanMonumentPieces.RoomDefinition lvt_7_1_ = lvt_4_1_.field_175965_b[EnumFacing.UP.func_176745_a()];
         if (this.field_175830_k.field_175967_a / 25 > 0) {
            this.func_175821_a(p_74875_1_, p_74875_3_, 8, 0, lvt_4_1_.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 0, lvt_5_1_.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
         }

         if (lvt_6_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 8, 1, 7, 8, 6, field_175828_a);
         }

         if (lvt_7_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 8, 8, 1, 14, 8, 6, field_175828_a);
         }

         for(int lvt_8_1_ = 1; lvt_8_1_ <= 7; ++lvt_8_1_) {
            IBlockState lvt_9_1_ = field_175826_b;
            if (lvt_8_1_ == 2 || lvt_8_1_ == 6) {
               lvt_9_1_ = field_175828_a;
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_8_1_, 0, 0, lvt_8_1_, 7, lvt_9_1_, lvt_9_1_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 15, lvt_8_1_, 0, 15, lvt_8_1_, 7, lvt_9_1_, lvt_9_1_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_8_1_, 0, 15, lvt_8_1_, 0, lvt_9_1_, lvt_9_1_, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, lvt_8_1_, 7, 14, lvt_8_1_, 7, lvt_9_1_, lvt_9_1_, false);
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 1, 3, 2, 7, 4, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 2, 4, 7, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 5, 4, 7, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 13, 1, 3, 13, 7, 4, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 11, 1, 2, 12, 7, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 11, 1, 5, 12, 7, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 3, 5, 3, 4, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 1, 3, 10, 3, 4, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 7, 2, 10, 7, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 5, 2, 5, 7, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 5, 2, 10, 7, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 5, 5, 5, 7, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 10, 5, 5, 10, 7, 5, field_175826_b, field_175826_b, false);
         this.func_175811_a(p_74875_1_, field_175826_b, 6, 6, 2, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 9, 6, 2, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 6, 6, 5, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 9, 6, 5, p_74875_3_);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 4, 3, 6, 4, 4, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 9, 4, 3, 10, 4, 4, field_175826_b, field_175826_b, false);
         this.func_175811_a(p_74875_1_, field_175825_e, 5, 4, 2, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175825_e, 5, 4, 5, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175825_e, 10, 4, 2, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175825_e, 10, 4, 5, p_74875_3_);
         if (lvt_5_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 2, 0, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 7, 4, 2, 7, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 1, 3, 0, 2, 4, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 11, 1, 0, 12, 2, 0, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 11, 1, 7, 12, 2, 7, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 15, 1, 3, 15, 2, 4, false);
         }

         if (lvt_6_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 5, 0, 4, 6, 0, false);
         }

         if (lvt_6_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 5, 7, 4, 6, 7, false);
         }

         if (lvt_6_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 5, 3, 0, 6, 4, false);
         }

         if (lvt_7_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 11, 5, 0, 12, 6, 0, false);
         }

         if (lvt_7_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 11, 5, 7, 12, 6, 7, false);
         }

         if (lvt_7_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 15, 5, 3, 15, 6, 4, false);
         }

         return true;
      }
   }

   public static class DoubleZRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleZRoom() {
      }

      public DoubleZRoom(EnumFacing p_i45593_1_, StructureOceanMonumentPieces.RoomDefinition p_i45593_2_, Random p_i45593_3_) {
         super(1, p_i45593_1_, p_i45593_2_, 1, 1, 2);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         StructureOceanMonumentPieces.RoomDefinition lvt_4_1_ = this.field_175830_k.field_175965_b[EnumFacing.NORTH.func_176745_a()];
         StructureOceanMonumentPieces.RoomDefinition lvt_5_1_ = this.field_175830_k;
         if (this.field_175830_k.field_175967_a / 25 > 0) {
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 8, lvt_4_1_.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 0, lvt_5_1_.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
         }

         if (lvt_5_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 4, 1, 6, 4, 7, field_175828_a);
         }

         if (lvt_4_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 4, 8, 6, 4, 14, field_175828_a);
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 0, 3, 15, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 3, 0, 7, 3, 15, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 0, 7, 3, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 15, 6, 3, 15, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 2, 15, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 0, 7, 2, 15, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 0, 7, 2, 0, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 15, 6, 2, 15, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 1, 15, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 0, 7, 1, 15, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 0, 7, 1, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 15, 6, 1, 15, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 1, 1, 1, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 1, 6, 1, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 1, 1, 3, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 3, 1, 6, 3, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 13, 1, 1, 14, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 13, 6, 1, 14, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 13, 1, 3, 14, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 3, 13, 6, 3, 14, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 1, 6, 2, 3, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 6, 5, 3, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 1, 9, 2, 3, 9, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 9, 5, 3, 9, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 2, 6, 4, 2, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 2, 9, 4, 2, 9, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 2, 7, 2, 2, 8, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 7, 5, 2, 8, field_175826_b, field_175826_b, false);
         this.func_175811_a(p_74875_1_, field_175825_e, 2, 2, 5, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175825_e, 5, 2, 5, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175825_e, 2, 2, 10, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175825_e, 5, 2, 10, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 2, 3, 5, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 5, 3, 5, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 2, 3, 10, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175826_b, 5, 3, 10, p_74875_3_);
         if (lvt_5_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 2, 0, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 7, 1, 3, 7, 2, 4, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 1, 3, 0, 2, 4, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 15, 4, 2, 15, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 1, 11, 0, 2, 12, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 7, 1, 11, 7, 2, 12, false);
         }

         return true;
      }
   }

   public static class DoubleXRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleXRoom() {
      }

      public DoubleXRoom(EnumFacing p_i45597_1_, StructureOceanMonumentPieces.RoomDefinition p_i45597_2_, Random p_i45597_3_) {
         super(1, p_i45597_1_, p_i45597_2_, 2, 1, 1);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         StructureOceanMonumentPieces.RoomDefinition lvt_4_1_ = this.field_175830_k.field_175965_b[EnumFacing.EAST.func_176745_a()];
         StructureOceanMonumentPieces.RoomDefinition lvt_5_1_ = this.field_175830_k;
         if (this.field_175830_k.field_175967_a / 25 > 0) {
            this.func_175821_a(p_74875_1_, p_74875_3_, 8, 0, lvt_4_1_.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 0, lvt_5_1_.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
         }

         if (lvt_5_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 4, 1, 7, 4, 6, field_175828_a);
         }

         if (lvt_4_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 8, 4, 1, 14, 4, 6, field_175828_a);
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 0, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 15, 3, 0, 15, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 0, 15, 3, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 7, 14, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 2, 7, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 15, 2, 0, 15, 2, 7, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 0, 15, 2, 0, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 7, 14, 2, 7, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 1, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 15, 1, 0, 15, 1, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 0, 15, 1, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 7, 14, 1, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 0, 10, 1, 4, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 2, 0, 9, 2, 3, field_175828_a, field_175828_a, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 3, 0, 10, 3, 4, field_175826_b, field_175826_b, false);
         this.func_175811_a(p_74875_1_, field_175825_e, 6, 2, 3, p_74875_3_);
         this.func_175811_a(p_74875_1_, field_175825_e, 9, 2, 3, p_74875_3_);
         if (lvt_5_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 2, 0, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 7, 4, 2, 7, false);
         }

         if (lvt_5_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 1, 3, 0, 2, 4, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 11, 1, 0, 12, 2, 0, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 11, 1, 7, 12, 2, 7, false);
         }

         if (lvt_4_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 15, 1, 3, 15, 2, 4, false);
         }

         return true;
      }
   }

   public static class DoubleYRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleYRoom() {
      }

      public DoubleYRoom(EnumFacing p_i45595_1_, StructureOceanMonumentPieces.RoomDefinition p_i45595_2_, Random p_i45595_3_) {
         super(1, p_i45595_1_, p_i45595_2_, 1, 2, 1);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if (this.field_175830_k.field_175967_a / 25 > 0) {
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
         }

         StructureOceanMonumentPieces.RoomDefinition lvt_4_1_ = this.field_175830_k.field_175965_b[EnumFacing.UP.func_176745_a()];
         if (lvt_4_1_.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 8, 1, 6, 8, 6, field_175828_a);
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 4, 0, 0, 4, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 4, 0, 7, 4, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 0, 6, 4, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 7, 6, 4, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 4, 1, 2, 4, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 2, 1, 4, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 4, 1, 5, 4, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 4, 2, 6, 4, 2, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 2, 4, 5, 2, 4, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 4, 5, 1, 4, 5, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 4, 5, 5, 4, 6, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 4, 5, 6, 4, 5, field_175826_b, field_175826_b, false);
         StructureOceanMonumentPieces.RoomDefinition lvt_5_1_ = this.field_175830_k;

         for(int lvt_6_1_ = 1; lvt_6_1_ <= 5; lvt_6_1_ += 4) {
            int lvt_7_1_ = 0;
            if (lvt_5_1_.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 2, lvt_6_1_, lvt_7_1_, 2, lvt_6_1_ + 2, lvt_7_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 5, lvt_6_1_, lvt_7_1_, 5, lvt_6_1_ + 2, lvt_7_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, lvt_6_1_ + 2, lvt_7_1_, 4, lvt_6_1_ + 2, lvt_7_1_, field_175826_b, field_175826_b, false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_6_1_, lvt_7_1_, 7, lvt_6_1_ + 2, lvt_7_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_6_1_ + 1, lvt_7_1_, 7, lvt_6_1_ + 1, lvt_7_1_, field_175828_a, field_175828_a, false);
            }

            lvt_7_1_ = 7;
            if (lvt_5_1_.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 2, lvt_6_1_, lvt_7_1_, 2, lvt_6_1_ + 2, lvt_7_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 5, lvt_6_1_, lvt_7_1_, 5, lvt_6_1_ + 2, lvt_7_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, lvt_6_1_ + 2, lvt_7_1_, 4, lvt_6_1_ + 2, lvt_7_1_, field_175826_b, field_175826_b, false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_6_1_, lvt_7_1_, 7, lvt_6_1_ + 2, lvt_7_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, lvt_6_1_ + 1, lvt_7_1_, 7, lvt_6_1_ + 1, lvt_7_1_, field_175828_a, field_175828_a, false);
            }

            int lvt_8_1_ = 0;
            if (lvt_5_1_.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_, 2, lvt_8_1_, lvt_6_1_ + 2, 2, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_, 5, lvt_8_1_, lvt_6_1_ + 2, 5, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_ + 2, 3, lvt_8_1_, lvt_6_1_ + 2, 4, field_175826_b, field_175826_b, false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_, 0, lvt_8_1_, lvt_6_1_ + 2, 7, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_ + 1, 0, lvt_8_1_, lvt_6_1_ + 1, 7, field_175828_a, field_175828_a, false);
            }

            lvt_8_1_ = 7;
            if (lvt_5_1_.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_, 2, lvt_8_1_, lvt_6_1_ + 2, 2, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_, 5, lvt_8_1_, lvt_6_1_ + 2, 5, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_ + 2, 3, lvt_8_1_, lvt_6_1_ + 2, 4, field_175826_b, field_175826_b, false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_, 0, lvt_8_1_, lvt_6_1_ + 2, 7, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, lvt_8_1_, lvt_6_1_ + 1, 0, lvt_8_1_, lvt_6_1_ + 1, 7, field_175828_a, field_175828_a, false);
            }

            lvt_5_1_ = lvt_4_1_;
         }

         return true;
      }
   }

   public static class SimpleTopRoom extends StructureOceanMonumentPieces.Piece {
      public SimpleTopRoom() {
      }

      public SimpleTopRoom(EnumFacing p_i45586_1_, StructureOceanMonumentPieces.RoomDefinition p_i45586_2_, Random p_i45586_3_) {
         super(1, p_i45586_1_, p_i45586_2_, 1, 1, 1);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if (this.field_175830_k.field_175967_a / 25 > 0) {
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
         }

         if (this.field_175830_k.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 4, 1, 6, 4, 6, field_175828_a);
         }

         for(int lvt_4_1_ = 1; lvt_4_1_ <= 6; ++lvt_4_1_) {
            for(int lvt_5_1_ = 1; lvt_5_1_ <= 6; ++lvt_5_1_) {
               if (p_74875_2_.nextInt(3) != 0) {
                  int lvt_6_1_ = 2 + (p_74875_2_.nextInt(4) == 0 ? 0 : 1);
                  this.func_175804_a(p_74875_1_, p_74875_3_, lvt_4_1_, lvt_6_1_, lvt_5_1_, lvt_4_1_, 3, lvt_5_1_, Blocks.field_150360_v.func_176203_a(1), Blocks.field_150360_v.func_176203_a(1), false);
               }
            }
         }

         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 1, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 0, 7, 1, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 0, 6, 1, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 7, 6, 1, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 2, 7, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 0, 7, 2, 7, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 0, 6, 2, 0, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 7, 6, 2, 7, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 0, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 3, 0, 7, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 0, 6, 3, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 7, 6, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 3, 7, 2, 4, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175827_c, field_175827_c, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 7, 4, 2, 7, field_175827_c, field_175827_c, false);
         if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 2, 0, false);
         }

         return true;
      }
   }

   public static class SimpleRoom extends StructureOceanMonumentPieces.Piece {
      private int field_175833_o;

      public SimpleRoom() {
      }

      public SimpleRoom(EnumFacing p_i45587_1_, StructureOceanMonumentPieces.RoomDefinition p_i45587_2_, Random p_i45587_3_) {
         super(1, p_i45587_1_, p_i45587_2_, 1, 1, 1);
         this.field_175833_o = p_i45587_3_.nextInt(3);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if (this.field_175830_k.field_175967_a / 25 > 0) {
            this.func_175821_a(p_74875_1_, p_74875_3_, 0, 0, this.field_175830_k.field_175966_c[EnumFacing.DOWN.func_176745_a()]);
         }

         if (this.field_175830_k.field_175965_b[EnumFacing.UP.func_176745_a()] == null) {
            this.func_175819_a(p_74875_1_, p_74875_3_, 1, 4, 1, 6, 4, 6, field_175828_a);
         }

         boolean lvt_4_1_ = this.field_175833_o != 0 && p_74875_2_.nextBoolean() && !this.field_175830_k.field_175966_c[EnumFacing.DOWN.func_176745_a()] && !this.field_175830_k.field_175966_c[EnumFacing.UP.func_176745_a()] && this.field_175830_k.func_175960_c() > 1;
         if (this.field_175833_o == 0) {
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 0, 2, 1, 2, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 2, 3, 2, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 2, 2, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 0, 2, 2, 0, field_175828_a, field_175828_a, false);
            this.func_175811_a(p_74875_1_, field_175825_e, 1, 2, 1, p_74875_3_);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 0, 7, 1, 2, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 3, 0, 7, 3, 2, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 0, 7, 2, 2, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 0, 6, 2, 0, field_175828_a, field_175828_a, false);
            this.func_175811_a(p_74875_1_, field_175825_e, 6, 2, 1, p_74875_3_);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 5, 2, 1, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 5, 2, 3, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 5, 0, 2, 7, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 7, 2, 2, 7, field_175828_a, field_175828_a, false);
            this.func_175811_a(p_74875_1_, field_175825_e, 1, 2, 6, p_74875_3_);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 5, 7, 1, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 3, 5, 7, 3, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 5, 7, 2, 7, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 2, 7, 6, 2, 7, field_175828_a, field_175828_a, false);
            this.func_175811_a(p_74875_1_, field_175825_e, 6, 2, 6, p_74875_3_);
            if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, 3, 0, 4, 3, 0, field_175826_b, field_175826_b, false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, 3, 0, 4, 3, 1, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, 2, 0, 4, 2, 0, field_175828_a, field_175828_a, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 1, 1, field_175826_b, field_175826_b, false);
            }

            if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, 3, 7, 4, 3, 7, field_175826_b, field_175826_b, false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, 3, 6, 4, 3, 7, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, 2, 7, 4, 2, 7, field_175828_a, field_175828_a, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 6, 4, 1, 7, field_175826_b, field_175826_b, false);
            }

            if (this.field_175830_k.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 3, 0, 3, 4, field_175826_b, field_175826_b, false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 3, 1, 3, 4, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 3, 0, 2, 4, field_175828_a, field_175828_a, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 3, 1, 1, 4, field_175826_b, field_175826_b, false);
            }

            if (this.field_175830_k.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 7, 3, 3, 7, 3, 4, field_175826_b, field_175826_b, false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, 6, 3, 3, 7, 3, 4, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 3, 7, 2, 4, field_175828_a, field_175828_a, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 3, 7, 1, 4, field_175826_b, field_175826_b, false);
            }
         } else if (this.field_175833_o == 1) {
            this.func_175804_a(p_74875_1_, p_74875_3_, 2, 1, 2, 2, 3, 2, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 2, 1, 5, 2, 3, 5, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 5, 5, 3, 5, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 2, 5, 3, 2, field_175826_b, field_175826_b, false);
            this.func_175811_a(p_74875_1_, field_175825_e, 2, 2, 2, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 2, 2, 5, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 5, 2, 5, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175825_e, 5, 2, 2, p_74875_3_);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 0, 1, 3, 0, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 1, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 7, 1, 3, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 6, 0, 3, 6, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 7, 7, 3, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 6, 7, 3, 6, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 6, 1, 0, 7, 3, 0, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 1, 7, 3, 1, field_175826_b, field_175826_b, false);
            this.func_175811_a(p_74875_1_, field_175828_a, 1, 2, 0, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175828_a, 0, 2, 1, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175828_a, 1, 2, 7, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175828_a, 0, 2, 6, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175828_a, 6, 2, 7, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175828_a, 7, 2, 6, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175828_a, 6, 2, 0, p_74875_3_);
            this.func_175811_a(p_74875_1_, field_175828_a, 7, 2, 1, p_74875_3_);
            if (!this.field_175830_k.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 0, 6, 3, 0, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 0, 6, 2, 0, field_175828_a, field_175828_a, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 0, 6, 1, 0, field_175826_b, field_175826_b, false);
            }

            if (!this.field_175830_k.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 7, 6, 3, 7, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 7, 6, 2, 7, field_175828_a, field_175828_a, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 7, 6, 1, 7, field_175826_b, field_175826_b, false);
            }

            if (!this.field_175830_k.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 1, 0, 3, 6, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 1, 0, 2, 6, field_175828_a, field_175828_a, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 1, 6, field_175826_b, field_175826_b, false);
            }

            if (!this.field_175830_k.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 7, 3, 1, 7, 3, 6, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 1, 7, 2, 6, field_175828_a, field_175828_a, false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 1, 7, 1, 6, field_175826_b, field_175826_b, false);
            }
         } else if (this.field_175833_o == 2) {
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 1, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 0, 7, 1, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 0, 6, 1, 0, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 7, 6, 1, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 2, 7, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 2, 0, 7, 2, 7, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 0, 6, 2, 0, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 2, 7, 6, 2, 7, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 0, 3, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 3, 0, 7, 3, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 0, 6, 3, 0, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 1, 3, 7, 6, 3, 7, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 3, 0, 2, 4, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 3, 7, 2, 4, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 2, 0, field_175827_c, field_175827_c, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 7, 4, 2, 7, field_175827_c, field_175827_c, false);
            if (this.field_175830_k.field_175966_c[EnumFacing.SOUTH.func_176745_a()]) {
               this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 0, 4, 2, 0, false);
            }

            if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
               this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 7, 4, 2, 7, false);
            }

            if (this.field_175830_k.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
               this.func_181655_a(p_74875_1_, p_74875_3_, 0, 1, 3, 0, 2, 4, false);
            }

            if (this.field_175830_k.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
               this.func_181655_a(p_74875_1_, p_74875_3_, 7, 1, 3, 7, 2, 4, false);
            }
         }

         if (lvt_4_1_) {
            this.func_175804_a(p_74875_1_, p_74875_3_, 3, 1, 3, 4, 1, 4, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 3, 2, 3, 4, 2, 4, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 3, 3, 3, 4, 3, 4, field_175826_b, field_175826_b, false);
         }

         return true;
      }
   }

   public static class EntryRoom extends StructureOceanMonumentPieces.Piece {
      public EntryRoom() {
      }

      public EntryRoom(EnumFacing p_i45592_1_, StructureOceanMonumentPieces.RoomDefinition p_i45592_2_) {
         super(1, p_i45592_1_, p_i45592_2_, 1, 1, 1);
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 3, 0, 2, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 3, 0, 7, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, 0, 1, 2, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 6, 2, 0, 7, 2, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 0, 0, 1, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 7, 1, 0, 7, 1, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 0, 1, 7, 7, 3, 7, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 1, 1, 0, 2, 3, 0, field_175826_b, field_175826_b, false);
         this.func_175804_a(p_74875_1_, p_74875_3_, 5, 1, 0, 6, 3, 0, field_175826_b, field_175826_b, false);
         if (this.field_175830_k.field_175966_c[EnumFacing.NORTH.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 3, 1, 7, 4, 2, 7, false);
         }

         if (this.field_175830_k.field_175966_c[EnumFacing.WEST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 0, 1, 3, 1, 2, 4, false);
         }

         if (this.field_175830_k.field_175966_c[EnumFacing.EAST.func_176745_a()]) {
            this.func_181655_a(p_74875_1_, p_74875_3_, 6, 1, 3, 7, 2, 4, false);
         }

         return true;
      }
   }

   public static class MonumentBuilding extends StructureOceanMonumentPieces.Piece {
      private StructureOceanMonumentPieces.RoomDefinition field_175845_o;
      private StructureOceanMonumentPieces.RoomDefinition field_175844_p;
      private List<StructureOceanMonumentPieces.Piece> field_175843_q = Lists.newArrayList();

      public MonumentBuilding() {
      }

      public MonumentBuilding(Random p_i45599_1_, int p_i45599_2_, int p_i45599_3_, EnumFacing p_i45599_4_) {
         super(0);
         this.field_74885_f = p_i45599_4_;
         switch(this.field_74885_f) {
         case NORTH:
         case SOUTH:
            this.field_74887_e = new StructureBoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
            break;
         default:
            this.field_74887_e = new StructureBoundingBox(p_i45599_2_, 39, p_i45599_3_, p_i45599_2_ + 58 - 1, 61, p_i45599_3_ + 58 - 1);
         }

         List<StructureOceanMonumentPieces.RoomDefinition> lvt_5_1_ = this.func_175836_a(p_i45599_1_);
         this.field_175845_o.field_175963_d = true;
         this.field_175843_q.add(new StructureOceanMonumentPieces.EntryRoom(this.field_74885_f, this.field_175845_o));
         this.field_175843_q.add(new StructureOceanMonumentPieces.MonumentCoreRoom(this.field_74885_f, this.field_175844_p, p_i45599_1_));
         List<StructureOceanMonumentPieces.MonumentRoomFitHelper> lvt_6_1_ = Lists.newArrayList();
         lvt_6_1_.add(new StructureOceanMonumentPieces.XYDoubleRoomFitHelper());
         lvt_6_1_.add(new StructureOceanMonumentPieces.YZDoubleRoomFitHelper());
         lvt_6_1_.add(new StructureOceanMonumentPieces.ZDoubleRoomFitHelper());
         lvt_6_1_.add(new StructureOceanMonumentPieces.XDoubleRoomFitHelper());
         lvt_6_1_.add(new StructureOceanMonumentPieces.YDoubleRoomFitHelper());
         lvt_6_1_.add(new StructureOceanMonumentPieces.FitSimpleRoomTopHelper());
         lvt_6_1_.add(new StructureOceanMonumentPieces.FitSimpleRoomHelper());
         Iterator lvt_7_1_ = lvt_5_1_.iterator();

         while(true) {
            while(true) {
               StructureOceanMonumentPieces.RoomDefinition lvt_8_1_;
               do {
                  do {
                     if (!lvt_7_1_.hasNext()) {
                        int lvt_7_2_ = this.field_74887_e.field_78895_b;
                        int lvt_8_2_ = this.func_74865_a(9, 22);
                        int lvt_9_2_ = this.func_74873_b(9, 22);
                        Iterator lvt_10_2_ = this.field_175843_q.iterator();

                        while(lvt_10_2_.hasNext()) {
                           StructureOceanMonumentPieces.Piece lvt_11_1_ = (StructureOceanMonumentPieces.Piece)lvt_10_2_.next();
                           lvt_11_1_.func_74874_b().func_78886_a(lvt_8_2_, lvt_7_2_, lvt_9_2_);
                        }

                        StructureBoundingBox lvt_10_3_ = StructureBoundingBox.func_175899_a(this.func_74865_a(1, 1), this.func_74862_a(1), this.func_74873_b(1, 1), this.func_74865_a(23, 21), this.func_74862_a(8), this.func_74873_b(23, 21));
                        StructureBoundingBox lvt_11_2_ = StructureBoundingBox.func_175899_a(this.func_74865_a(34, 1), this.func_74862_a(1), this.func_74873_b(34, 1), this.func_74865_a(56, 21), this.func_74862_a(8), this.func_74873_b(56, 21));
                        StructureBoundingBox lvt_12_1_ = StructureBoundingBox.func_175899_a(this.func_74865_a(22, 22), this.func_74862_a(13), this.func_74873_b(22, 22), this.func_74865_a(35, 35), this.func_74862_a(17), this.func_74873_b(35, 35));
                        int lvt_13_1_ = p_i45599_1_.nextInt();
                        this.field_175843_q.add(new StructureOceanMonumentPieces.WingRoom(this.field_74885_f, lvt_10_3_, lvt_13_1_++));
                        this.field_175843_q.add(new StructureOceanMonumentPieces.WingRoom(this.field_74885_f, lvt_11_2_, lvt_13_1_++));
                        this.field_175843_q.add(new StructureOceanMonumentPieces.Penthouse(this.field_74885_f, lvt_12_1_));
                        return;
                     }

                     lvt_8_1_ = (StructureOceanMonumentPieces.RoomDefinition)lvt_7_1_.next();
                  } while(lvt_8_1_.field_175963_d);
               } while(lvt_8_1_.func_175961_b());

               Iterator lvt_9_1_ = lvt_6_1_.iterator();

               while(lvt_9_1_.hasNext()) {
                  StructureOceanMonumentPieces.MonumentRoomFitHelper lvt_10_1_ = (StructureOceanMonumentPieces.MonumentRoomFitHelper)lvt_9_1_.next();
                  if (lvt_10_1_.func_175969_a(lvt_8_1_)) {
                     this.field_175843_q.add(lvt_10_1_.func_175968_a(this.field_74885_f, lvt_8_1_, p_i45599_1_));
                     break;
                  }
               }
            }
         }
      }

      private List<StructureOceanMonumentPieces.RoomDefinition> func_175836_a(Random p_175836_1_) {
         StructureOceanMonumentPieces.RoomDefinition[] lvt_2_1_ = new StructureOceanMonumentPieces.RoomDefinition[75];

         int lvt_3_4_;
         int lvt_4_4_;
         byte lvt_5_3_;
         int lvt_6_4_;
         for(lvt_3_4_ = 0; lvt_3_4_ < 5; ++lvt_3_4_) {
            for(lvt_4_4_ = 0; lvt_4_4_ < 4; ++lvt_4_4_) {
               lvt_5_3_ = 0;
               lvt_6_4_ = func_175820_a(lvt_3_4_, lvt_5_3_, lvt_4_4_);
               lvt_2_1_[lvt_6_4_] = new StructureOceanMonumentPieces.RoomDefinition(lvt_6_4_);
            }
         }

         for(lvt_3_4_ = 0; lvt_3_4_ < 5; ++lvt_3_4_) {
            for(lvt_4_4_ = 0; lvt_4_4_ < 4; ++lvt_4_4_) {
               lvt_5_3_ = 1;
               lvt_6_4_ = func_175820_a(lvt_3_4_, lvt_5_3_, lvt_4_4_);
               lvt_2_1_[lvt_6_4_] = new StructureOceanMonumentPieces.RoomDefinition(lvt_6_4_);
            }
         }

         for(lvt_3_4_ = 1; lvt_3_4_ < 4; ++lvt_3_4_) {
            for(lvt_4_4_ = 0; lvt_4_4_ < 2; ++lvt_4_4_) {
               lvt_5_3_ = 2;
               lvt_6_4_ = func_175820_a(lvt_3_4_, lvt_5_3_, lvt_4_4_);
               lvt_2_1_[lvt_6_4_] = new StructureOceanMonumentPieces.RoomDefinition(lvt_6_4_);
            }
         }

         this.field_175845_o = lvt_2_1_[field_175823_g];

         int lvt_8_1_;
         int lvt_9_1_;
         int lvt_11_1_;
         int lvt_12_2_;
         int lvt_13_1_;
         for(lvt_3_4_ = 0; lvt_3_4_ < 5; ++lvt_3_4_) {
            for(lvt_4_4_ = 0; lvt_4_4_ < 5; ++lvt_4_4_) {
               for(int lvt_5_4_ = 0; lvt_5_4_ < 3; ++lvt_5_4_) {
                  lvt_6_4_ = func_175820_a(lvt_3_4_, lvt_5_4_, lvt_4_4_);
                  if (lvt_2_1_[lvt_6_4_] != null) {
                     EnumFacing[] lvt_7_1_ = EnumFacing.values();
                     lvt_8_1_ = lvt_7_1_.length;

                     for(lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
                        EnumFacing lvt_10_1_ = lvt_7_1_[lvt_9_1_];
                        lvt_11_1_ = lvt_3_4_ + lvt_10_1_.func_82601_c();
                        lvt_12_2_ = lvt_5_4_ + lvt_10_1_.func_96559_d();
                        lvt_13_1_ = lvt_4_4_ + lvt_10_1_.func_82599_e();
                        if (lvt_11_1_ >= 0 && lvt_11_1_ < 5 && lvt_13_1_ >= 0 && lvt_13_1_ < 5 && lvt_12_2_ >= 0 && lvt_12_2_ < 3) {
                           int lvt_14_1_ = func_175820_a(lvt_11_1_, lvt_12_2_, lvt_13_1_);
                           if (lvt_2_1_[lvt_14_1_] != null) {
                              if (lvt_13_1_ != lvt_4_4_) {
                                 lvt_2_1_[lvt_6_4_].func_175957_a(lvt_10_1_.func_176734_d(), lvt_2_1_[lvt_14_1_]);
                              } else {
                                 lvt_2_1_[lvt_6_4_].func_175957_a(lvt_10_1_, lvt_2_1_[lvt_14_1_]);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         StructureOceanMonumentPieces.RoomDefinition lvt_3_5_;
         lvt_2_1_[field_175831_h].func_175957_a(EnumFacing.UP, lvt_3_5_ = new StructureOceanMonumentPieces.RoomDefinition(1003));
         StructureOceanMonumentPieces.RoomDefinition lvt_4_5_;
         lvt_2_1_[field_175832_i].func_175957_a(EnumFacing.SOUTH, lvt_4_5_ = new StructureOceanMonumentPieces.RoomDefinition(1001));
         StructureOceanMonumentPieces.RoomDefinition lvt_5_5_;
         lvt_2_1_[field_175829_j].func_175957_a(EnumFacing.SOUTH, lvt_5_5_ = new StructureOceanMonumentPieces.RoomDefinition(1002));
         lvt_3_5_.field_175963_d = true;
         lvt_4_5_.field_175963_d = true;
         lvt_5_5_.field_175963_d = true;
         this.field_175845_o.field_175964_e = true;
         this.field_175844_p = lvt_2_1_[func_175820_a(p_175836_1_.nextInt(4), 0, 2)];
         this.field_175844_p.field_175963_d = true;
         this.field_175844_p.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175963_d = true;
         this.field_175844_p.field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175963_d = true;
         this.field_175844_p.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175963_d = true;
         this.field_175844_p.field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         this.field_175844_p.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         this.field_175844_p.field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         this.field_175844_p.field_175965_b[EnumFacing.EAST.func_176745_a()].field_175965_b[EnumFacing.NORTH.func_176745_a()].field_175965_b[EnumFacing.UP.func_176745_a()].field_175963_d = true;
         List<StructureOceanMonumentPieces.RoomDefinition> lvt_6_5_ = Lists.newArrayList();
         StructureOceanMonumentPieces.RoomDefinition[] lvt_7_2_ = lvt_2_1_;
         lvt_8_1_ = lvt_2_1_.length;

         for(lvt_9_1_ = 0; lvt_9_1_ < lvt_8_1_; ++lvt_9_1_) {
            StructureOceanMonumentPieces.RoomDefinition lvt_10_2_ = lvt_7_2_[lvt_9_1_];
            if (lvt_10_2_ != null) {
               lvt_10_2_.func_175958_a();
               lvt_6_5_.add(lvt_10_2_);
            }
         }

         lvt_3_5_.func_175958_a();
         Collections.shuffle(lvt_6_5_, p_175836_1_);
         int lvt_7_3_ = 1;
         Iterator lvt_8_3_ = lvt_6_5_.iterator();

         label95:
         while(lvt_8_3_.hasNext()) {
            StructureOceanMonumentPieces.RoomDefinition lvt_9_3_ = (StructureOceanMonumentPieces.RoomDefinition)lvt_8_3_.next();
            int lvt_10_3_ = 0;
            lvt_11_1_ = 0;

            while(true) {
               while(true) {
                  do {
                     if (lvt_10_3_ >= 2 || lvt_11_1_ >= 5) {
                        continue label95;
                     }

                     ++lvt_11_1_;
                     lvt_12_2_ = p_175836_1_.nextInt(6);
                  } while(!lvt_9_3_.field_175966_c[lvt_12_2_]);

                  lvt_13_1_ = EnumFacing.func_82600_a(lvt_12_2_).func_176734_d().func_176745_a();
                  lvt_9_3_.field_175966_c[lvt_12_2_] = false;
                  lvt_9_3_.field_175965_b[lvt_12_2_].field_175966_c[lvt_13_1_] = false;
                  if (lvt_9_3_.func_175959_a(lvt_7_3_++) && lvt_9_3_.field_175965_b[lvt_12_2_].func_175959_a(lvt_7_3_++)) {
                     ++lvt_10_3_;
                  } else {
                     lvt_9_3_.field_175966_c[lvt_12_2_] = true;
                     lvt_9_3_.field_175965_b[lvt_12_2_].field_175966_c[lvt_13_1_] = true;
                  }
               }
            }
         }

         lvt_6_5_.add(lvt_3_5_);
         lvt_6_5_.add(lvt_4_5_);
         lvt_6_5_.add(lvt_5_5_);
         return lvt_6_5_;
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         int lvt_4_1_ = Math.max(p_74875_1_.func_181545_F(), 64) - this.field_74887_e.field_78895_b;
         this.func_181655_a(p_74875_1_, p_74875_3_, 0, 0, 0, 58, lvt_4_1_, 58, false);
         this.func_175840_a(false, 0, p_74875_1_, p_74875_2_, p_74875_3_);
         this.func_175840_a(true, 33, p_74875_1_, p_74875_2_, p_74875_3_);
         this.func_175839_b(p_74875_1_, p_74875_2_, p_74875_3_);
         this.func_175837_c(p_74875_1_, p_74875_2_, p_74875_3_);
         this.func_175841_d(p_74875_1_, p_74875_2_, p_74875_3_);
         this.func_175835_e(p_74875_1_, p_74875_2_, p_74875_3_);
         this.func_175842_f(p_74875_1_, p_74875_2_, p_74875_3_);
         this.func_175838_g(p_74875_1_, p_74875_2_, p_74875_3_);

         int lvt_5_1_;
         label72:
         for(lvt_5_1_ = 0; lvt_5_1_ < 7; ++lvt_5_1_) {
            int lvt_6_1_ = 0;

            while(true) {
               while(true) {
                  if (lvt_6_1_ >= 7) {
                     continue label72;
                  }

                  if (lvt_6_1_ == 0 && lvt_5_1_ == 3) {
                     lvt_6_1_ = 6;
                  }

                  int lvt_7_1_ = lvt_5_1_ * 9;
                  int lvt_8_1_ = lvt_6_1_ * 9;

                  for(int lvt_9_1_ = 0; lvt_9_1_ < 4; ++lvt_9_1_) {
                     for(int lvt_10_1_ = 0; lvt_10_1_ < 4; ++lvt_10_1_) {
                        this.func_175811_a(p_74875_1_, field_175826_b, lvt_7_1_ + lvt_9_1_, 0, lvt_8_1_ + lvt_10_1_, p_74875_3_);
                        this.func_175808_b(p_74875_1_, field_175826_b, lvt_7_1_ + lvt_9_1_, -1, lvt_8_1_ + lvt_10_1_, p_74875_3_);
                     }
                  }

                  if (lvt_5_1_ != 0 && lvt_5_1_ != 6) {
                     lvt_6_1_ += 6;
                  } else {
                     ++lvt_6_1_;
                  }
               }
            }
         }

         for(lvt_5_1_ = 0; lvt_5_1_ < 5; ++lvt_5_1_) {
            this.func_181655_a(p_74875_1_, p_74875_3_, -1 - lvt_5_1_, 0 + lvt_5_1_ * 2, -1 - lvt_5_1_, -1 - lvt_5_1_, 23, 58 + lvt_5_1_, false);
            this.func_181655_a(p_74875_1_, p_74875_3_, 58 + lvt_5_1_, 0 + lvt_5_1_ * 2, -1 - lvt_5_1_, 58 + lvt_5_1_, 23, 58 + lvt_5_1_, false);
            this.func_181655_a(p_74875_1_, p_74875_3_, 0 - lvt_5_1_, 0 + lvt_5_1_ * 2, -1 - lvt_5_1_, 57 + lvt_5_1_, 23, -1 - lvt_5_1_, false);
            this.func_181655_a(p_74875_1_, p_74875_3_, 0 - lvt_5_1_, 0 + lvt_5_1_ * 2, 58 + lvt_5_1_, 57 + lvt_5_1_, 23, 58 + lvt_5_1_, false);
         }

         Iterator lvt_5_3_ = this.field_175843_q.iterator();

         while(lvt_5_3_.hasNext()) {
            StructureOceanMonumentPieces.Piece lvt_6_2_ = (StructureOceanMonumentPieces.Piece)lvt_5_3_.next();
            if (lvt_6_2_.func_74874_b().func_78884_a(p_74875_3_)) {
               lvt_6_2_.func_74875_a(p_74875_1_, p_74875_2_, p_74875_3_);
            }
         }

         return true;
      }

      private void func_175840_a(boolean p_175840_1_, int p_175840_2_, World p_175840_3_, Random p_175840_4_, StructureBoundingBox p_175840_5_) {
         int lvt_6_1_ = true;
         if (this.func_175818_a(p_175840_5_, p_175840_2_, 0, p_175840_2_ + 23, 20)) {
            this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 0, 0, 0, p_175840_2_ + 24, 0, 20, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175840_3_, p_175840_5_, p_175840_2_ + 0, 1, 0, p_175840_2_ + 24, 10, 20, false);

            int lvt_7_2_;
            for(lvt_7_2_ = 0; lvt_7_2_ < 4; ++lvt_7_2_) {
               this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + lvt_7_2_, lvt_7_2_ + 1, lvt_7_2_, p_175840_2_ + lvt_7_2_, lvt_7_2_ + 1, 20, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + lvt_7_2_ + 7, lvt_7_2_ + 5, lvt_7_2_ + 7, p_175840_2_ + lvt_7_2_ + 7, lvt_7_2_ + 5, 20, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 17 - lvt_7_2_, lvt_7_2_ + 5, lvt_7_2_ + 7, p_175840_2_ + 17 - lvt_7_2_, lvt_7_2_ + 5, 20, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 24 - lvt_7_2_, lvt_7_2_ + 1, lvt_7_2_, p_175840_2_ + 24 - lvt_7_2_, lvt_7_2_ + 1, 20, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + lvt_7_2_ + 1, lvt_7_2_ + 1, lvt_7_2_, p_175840_2_ + 23 - lvt_7_2_, lvt_7_2_ + 1, lvt_7_2_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + lvt_7_2_ + 8, lvt_7_2_ + 5, lvt_7_2_ + 7, p_175840_2_ + 16 - lvt_7_2_, lvt_7_2_ + 5, lvt_7_2_ + 7, field_175826_b, field_175826_b, false);
            }

            this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 4, 4, 4, p_175840_2_ + 6, 4, 20, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 7, 4, 4, p_175840_2_ + 17, 4, 6, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 18, 4, 4, p_175840_2_ + 20, 4, 20, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 11, 8, 11, p_175840_2_ + 13, 8, 20, field_175828_a, field_175828_a, false);
            this.func_175811_a(p_175840_3_, field_175824_d, p_175840_2_ + 12, 9, 12, p_175840_5_);
            this.func_175811_a(p_175840_3_, field_175824_d, p_175840_2_ + 12, 9, 15, p_175840_5_);
            this.func_175811_a(p_175840_3_, field_175824_d, p_175840_2_ + 12, 9, 18, p_175840_5_);
            lvt_7_2_ = p_175840_1_ ? p_175840_2_ + 19 : p_175840_2_ + 5;
            int lvt_8_1_ = p_175840_1_ ? p_175840_2_ + 5 : p_175840_2_ + 19;

            int lvt_9_3_;
            for(lvt_9_3_ = 20; lvt_9_3_ >= 5; lvt_9_3_ -= 3) {
               this.func_175811_a(p_175840_3_, field_175824_d, lvt_7_2_, 5, lvt_9_3_, p_175840_5_);
            }

            for(lvt_9_3_ = 19; lvt_9_3_ >= 7; lvt_9_3_ -= 3) {
               this.func_175811_a(p_175840_3_, field_175824_d, lvt_8_1_, 5, lvt_9_3_, p_175840_5_);
            }

            for(lvt_9_3_ = 0; lvt_9_3_ < 4; ++lvt_9_3_) {
               int lvt_10_1_ = p_175840_1_ ? p_175840_2_ + (24 - (17 - lvt_9_3_ * 3)) : p_175840_2_ + 17 - lvt_9_3_ * 3;
               this.func_175811_a(p_175840_3_, field_175824_d, lvt_10_1_, 5, 5, p_175840_5_);
            }

            this.func_175811_a(p_175840_3_, field_175824_d, lvt_8_1_, 5, 5, p_175840_5_);
            this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 11, 1, 12, p_175840_2_ + 13, 7, 12, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175840_3_, p_175840_5_, p_175840_2_ + 12, 1, 11, p_175840_2_ + 12, 7, 13, field_175828_a, field_175828_a, false);
         }

      }

      private void func_175839_b(World p_175839_1_, Random p_175839_2_, StructureBoundingBox p_175839_3_) {
         if (this.func_175818_a(p_175839_3_, 22, 5, 35, 17)) {
            this.func_181655_a(p_175839_1_, p_175839_3_, 25, 0, 0, 32, 8, 20, false);

            for(int lvt_4_1_ = 0; lvt_4_1_ < 4; ++lvt_4_1_) {
               this.func_175804_a(p_175839_1_, p_175839_3_, 24, 2, 5 + lvt_4_1_ * 4, 24, 4, 5 + lvt_4_1_ * 4, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175839_1_, p_175839_3_, 22, 4, 5 + lvt_4_1_ * 4, 23, 4, 5 + lvt_4_1_ * 4, field_175826_b, field_175826_b, false);
               this.func_175811_a(p_175839_1_, field_175826_b, 25, 5, 5 + lvt_4_1_ * 4, p_175839_3_);
               this.func_175811_a(p_175839_1_, field_175826_b, 26, 6, 5 + lvt_4_1_ * 4, p_175839_3_);
               this.func_175811_a(p_175839_1_, field_175825_e, 26, 5, 5 + lvt_4_1_ * 4, p_175839_3_);
               this.func_175804_a(p_175839_1_, p_175839_3_, 33, 2, 5 + lvt_4_1_ * 4, 33, 4, 5 + lvt_4_1_ * 4, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175839_1_, p_175839_3_, 34, 4, 5 + lvt_4_1_ * 4, 35, 4, 5 + lvt_4_1_ * 4, field_175826_b, field_175826_b, false);
               this.func_175811_a(p_175839_1_, field_175826_b, 32, 5, 5 + lvt_4_1_ * 4, p_175839_3_);
               this.func_175811_a(p_175839_1_, field_175826_b, 31, 6, 5 + lvt_4_1_ * 4, p_175839_3_);
               this.func_175811_a(p_175839_1_, field_175825_e, 31, 5, 5 + lvt_4_1_ * 4, p_175839_3_);
               this.func_175804_a(p_175839_1_, p_175839_3_, 27, 6, 5 + lvt_4_1_ * 4, 30, 6, 5 + lvt_4_1_ * 4, field_175828_a, field_175828_a, false);
            }
         }

      }

      private void func_175837_c(World p_175837_1_, Random p_175837_2_, StructureBoundingBox p_175837_3_) {
         if (this.func_175818_a(p_175837_3_, 15, 20, 42, 21)) {
            this.func_175804_a(p_175837_1_, p_175837_3_, 15, 0, 21, 42, 0, 21, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 26, 1, 21, 31, 3, 21, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 21, 12, 21, 36, 12, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 17, 11, 21, 40, 11, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 16, 10, 21, 41, 10, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 15, 7, 21, 42, 9, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 16, 6, 21, 41, 6, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 17, 5, 21, 40, 5, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 21, 4, 21, 36, 4, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 22, 3, 21, 26, 3, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 31, 3, 21, 35, 3, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 23, 2, 21, 25, 2, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 32, 2, 21, 34, 2, 21, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175837_1_, p_175837_3_, 28, 4, 20, 29, 4, 21, field_175826_b, field_175826_b, false);
            this.func_175811_a(p_175837_1_, field_175826_b, 27, 3, 21, p_175837_3_);
            this.func_175811_a(p_175837_1_, field_175826_b, 30, 3, 21, p_175837_3_);
            this.func_175811_a(p_175837_1_, field_175826_b, 26, 2, 21, p_175837_3_);
            this.func_175811_a(p_175837_1_, field_175826_b, 31, 2, 21, p_175837_3_);
            this.func_175811_a(p_175837_1_, field_175826_b, 25, 1, 21, p_175837_3_);
            this.func_175811_a(p_175837_1_, field_175826_b, 32, 1, 21, p_175837_3_);

            int lvt_4_3_;
            for(lvt_4_3_ = 0; lvt_4_3_ < 7; ++lvt_4_3_) {
               this.func_175811_a(p_175837_1_, field_175827_c, 28 - lvt_4_3_, 6 + lvt_4_3_, 21, p_175837_3_);
               this.func_175811_a(p_175837_1_, field_175827_c, 29 + lvt_4_3_, 6 + lvt_4_3_, 21, p_175837_3_);
            }

            for(lvt_4_3_ = 0; lvt_4_3_ < 4; ++lvt_4_3_) {
               this.func_175811_a(p_175837_1_, field_175827_c, 28 - lvt_4_3_, 9 + lvt_4_3_, 21, p_175837_3_);
               this.func_175811_a(p_175837_1_, field_175827_c, 29 + lvt_4_3_, 9 + lvt_4_3_, 21, p_175837_3_);
            }

            this.func_175811_a(p_175837_1_, field_175827_c, 28, 12, 21, p_175837_3_);
            this.func_175811_a(p_175837_1_, field_175827_c, 29, 12, 21, p_175837_3_);

            for(lvt_4_3_ = 0; lvt_4_3_ < 3; ++lvt_4_3_) {
               this.func_175811_a(p_175837_1_, field_175827_c, 22 - lvt_4_3_ * 2, 8, 21, p_175837_3_);
               this.func_175811_a(p_175837_1_, field_175827_c, 22 - lvt_4_3_ * 2, 9, 21, p_175837_3_);
               this.func_175811_a(p_175837_1_, field_175827_c, 35 + lvt_4_3_ * 2, 8, 21, p_175837_3_);
               this.func_175811_a(p_175837_1_, field_175827_c, 35 + lvt_4_3_ * 2, 9, 21, p_175837_3_);
            }

            this.func_181655_a(p_175837_1_, p_175837_3_, 15, 13, 21, 42, 15, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 15, 1, 21, 15, 6, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 16, 1, 21, 16, 5, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 17, 1, 21, 20, 4, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 21, 1, 21, 21, 3, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 22, 1, 21, 22, 2, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 23, 1, 21, 24, 1, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 42, 1, 21, 42, 6, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 41, 1, 21, 41, 5, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 37, 1, 21, 40, 4, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 36, 1, 21, 36, 3, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 33, 1, 21, 34, 1, 21, false);
            this.func_181655_a(p_175837_1_, p_175837_3_, 35, 1, 21, 35, 2, 21, false);
         }

      }

      private void func_175841_d(World p_175841_1_, Random p_175841_2_, StructureBoundingBox p_175841_3_) {
         if (this.func_175818_a(p_175841_3_, 21, 21, 36, 36)) {
            this.func_175804_a(p_175841_1_, p_175841_3_, 21, 0, 22, 36, 0, 36, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175841_1_, p_175841_3_, 21, 1, 22, 36, 23, 36, false);

            for(int lvt_4_1_ = 0; lvt_4_1_ < 4; ++lvt_4_1_) {
               this.func_175804_a(p_175841_1_, p_175841_3_, 21 + lvt_4_1_, 13 + lvt_4_1_, 21 + lvt_4_1_, 36 - lvt_4_1_, 13 + lvt_4_1_, 21 + lvt_4_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175841_1_, p_175841_3_, 21 + lvt_4_1_, 13 + lvt_4_1_, 36 - lvt_4_1_, 36 - lvt_4_1_, 13 + lvt_4_1_, 36 - lvt_4_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175841_1_, p_175841_3_, 21 + lvt_4_1_, 13 + lvt_4_1_, 22 + lvt_4_1_, 21 + lvt_4_1_, 13 + lvt_4_1_, 35 - lvt_4_1_, field_175826_b, field_175826_b, false);
               this.func_175804_a(p_175841_1_, p_175841_3_, 36 - lvt_4_1_, 13 + lvt_4_1_, 22 + lvt_4_1_, 36 - lvt_4_1_, 13 + lvt_4_1_, 35 - lvt_4_1_, field_175826_b, field_175826_b, false);
            }

            this.func_175804_a(p_175841_1_, p_175841_3_, 25, 16, 25, 32, 16, 32, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175841_1_, p_175841_3_, 25, 17, 25, 25, 19, 25, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_175841_1_, p_175841_3_, 32, 17, 25, 32, 19, 25, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_175841_1_, p_175841_3_, 25, 17, 32, 25, 19, 32, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_175841_1_, p_175841_3_, 32, 17, 32, 32, 19, 32, field_175826_b, field_175826_b, false);
            this.func_175811_a(p_175841_1_, field_175826_b, 26, 20, 26, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175826_b, 27, 21, 27, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175825_e, 27, 20, 27, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175826_b, 26, 20, 31, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175826_b, 27, 21, 30, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175825_e, 27, 20, 30, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175826_b, 31, 20, 31, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175826_b, 30, 21, 30, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175825_e, 30, 20, 30, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175826_b, 31, 20, 26, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175826_b, 30, 21, 27, p_175841_3_);
            this.func_175811_a(p_175841_1_, field_175825_e, 30, 20, 27, p_175841_3_);
            this.func_175804_a(p_175841_1_, p_175841_3_, 28, 21, 27, 29, 21, 27, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175841_1_, p_175841_3_, 27, 21, 28, 27, 21, 29, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175841_1_, p_175841_3_, 28, 21, 30, 29, 21, 30, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175841_1_, p_175841_3_, 30, 21, 28, 30, 21, 29, field_175828_a, field_175828_a, false);
         }

      }

      private void func_175835_e(World p_175835_1_, Random p_175835_2_, StructureBoundingBox p_175835_3_) {
         int lvt_4_3_;
         if (this.func_175818_a(p_175835_3_, 0, 21, 6, 58)) {
            this.func_175804_a(p_175835_1_, p_175835_3_, 0, 0, 21, 6, 0, 57, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175835_1_, p_175835_3_, 0, 1, 21, 6, 7, 57, false);
            this.func_175804_a(p_175835_1_, p_175835_3_, 4, 4, 21, 6, 4, 53, field_175828_a, field_175828_a, false);

            for(lvt_4_3_ = 0; lvt_4_3_ < 4; ++lvt_4_3_) {
               this.func_175804_a(p_175835_1_, p_175835_3_, lvt_4_3_, lvt_4_3_ + 1, 21, lvt_4_3_, lvt_4_3_ + 1, 57 - lvt_4_3_, field_175826_b, field_175826_b, false);
            }

            for(lvt_4_3_ = 23; lvt_4_3_ < 53; lvt_4_3_ += 3) {
               this.func_175811_a(p_175835_1_, field_175824_d, 5, 5, lvt_4_3_, p_175835_3_);
            }

            this.func_175811_a(p_175835_1_, field_175824_d, 5, 5, 52, p_175835_3_);

            for(lvt_4_3_ = 0; lvt_4_3_ < 4; ++lvt_4_3_) {
               this.func_175804_a(p_175835_1_, p_175835_3_, lvt_4_3_, lvt_4_3_ + 1, 21, lvt_4_3_, lvt_4_3_ + 1, 57 - lvt_4_3_, field_175826_b, field_175826_b, false);
            }

            this.func_175804_a(p_175835_1_, p_175835_3_, 4, 1, 52, 6, 3, 52, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175835_1_, p_175835_3_, 5, 1, 51, 5, 3, 53, field_175828_a, field_175828_a, false);
         }

         if (this.func_175818_a(p_175835_3_, 51, 21, 58, 58)) {
            this.func_175804_a(p_175835_1_, p_175835_3_, 51, 0, 21, 57, 0, 57, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175835_1_, p_175835_3_, 51, 1, 21, 57, 7, 57, false);
            this.func_175804_a(p_175835_1_, p_175835_3_, 51, 4, 21, 53, 4, 53, field_175828_a, field_175828_a, false);

            for(lvt_4_3_ = 0; lvt_4_3_ < 4; ++lvt_4_3_) {
               this.func_175804_a(p_175835_1_, p_175835_3_, 57 - lvt_4_3_, lvt_4_3_ + 1, 21, 57 - lvt_4_3_, lvt_4_3_ + 1, 57 - lvt_4_3_, field_175826_b, field_175826_b, false);
            }

            for(lvt_4_3_ = 23; lvt_4_3_ < 53; lvt_4_3_ += 3) {
               this.func_175811_a(p_175835_1_, field_175824_d, 52, 5, lvt_4_3_, p_175835_3_);
            }

            this.func_175811_a(p_175835_1_, field_175824_d, 52, 5, 52, p_175835_3_);
            this.func_175804_a(p_175835_1_, p_175835_3_, 51, 1, 52, 53, 3, 52, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175835_1_, p_175835_3_, 52, 1, 51, 52, 3, 53, field_175828_a, field_175828_a, false);
         }

         if (this.func_175818_a(p_175835_3_, 0, 51, 57, 57)) {
            this.func_175804_a(p_175835_1_, p_175835_3_, 7, 0, 51, 50, 0, 57, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175835_1_, p_175835_3_, 7, 1, 51, 50, 10, 57, false);

            for(lvt_4_3_ = 0; lvt_4_3_ < 4; ++lvt_4_3_) {
               this.func_175804_a(p_175835_1_, p_175835_3_, lvt_4_3_ + 1, lvt_4_3_ + 1, 57 - lvt_4_3_, 56 - lvt_4_3_, lvt_4_3_ + 1, 57 - lvt_4_3_, field_175826_b, field_175826_b, false);
            }
         }

      }

      private void func_175842_f(World p_175842_1_, Random p_175842_2_, StructureBoundingBox p_175842_3_) {
         int lvt_4_6_;
         if (this.func_175818_a(p_175842_3_, 7, 21, 13, 50)) {
            this.func_175804_a(p_175842_1_, p_175842_3_, 7, 0, 21, 13, 0, 50, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175842_1_, p_175842_3_, 7, 1, 21, 13, 10, 50, false);
            this.func_175804_a(p_175842_1_, p_175842_3_, 11, 8, 21, 13, 8, 53, field_175828_a, field_175828_a, false);

            for(lvt_4_6_ = 0; lvt_4_6_ < 4; ++lvt_4_6_) {
               this.func_175804_a(p_175842_1_, p_175842_3_, lvt_4_6_ + 7, lvt_4_6_ + 5, 21, lvt_4_6_ + 7, lvt_4_6_ + 5, 54, field_175826_b, field_175826_b, false);
            }

            for(lvt_4_6_ = 21; lvt_4_6_ <= 45; lvt_4_6_ += 3) {
               this.func_175811_a(p_175842_1_, field_175824_d, 12, 9, lvt_4_6_, p_175842_3_);
            }
         }

         if (this.func_175818_a(p_175842_3_, 44, 21, 50, 54)) {
            this.func_175804_a(p_175842_1_, p_175842_3_, 44, 0, 21, 50, 0, 50, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175842_1_, p_175842_3_, 44, 1, 21, 50, 10, 50, false);
            this.func_175804_a(p_175842_1_, p_175842_3_, 44, 8, 21, 46, 8, 53, field_175828_a, field_175828_a, false);

            for(lvt_4_6_ = 0; lvt_4_6_ < 4; ++lvt_4_6_) {
               this.func_175804_a(p_175842_1_, p_175842_3_, 50 - lvt_4_6_, lvt_4_6_ + 5, 21, 50 - lvt_4_6_, lvt_4_6_ + 5, 54, field_175826_b, field_175826_b, false);
            }

            for(lvt_4_6_ = 21; lvt_4_6_ <= 45; lvt_4_6_ += 3) {
               this.func_175811_a(p_175842_1_, field_175824_d, 45, 9, lvt_4_6_, p_175842_3_);
            }
         }

         if (this.func_175818_a(p_175842_3_, 8, 44, 49, 54)) {
            this.func_175804_a(p_175842_1_, p_175842_3_, 14, 0, 44, 43, 0, 50, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175842_1_, p_175842_3_, 14, 1, 44, 43, 10, 50, false);

            for(lvt_4_6_ = 12; lvt_4_6_ <= 45; lvt_4_6_ += 3) {
               this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 9, 45, p_175842_3_);
               this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 9, 52, p_175842_3_);
               if (lvt_4_6_ == 12 || lvt_4_6_ == 18 || lvt_4_6_ == 24 || lvt_4_6_ == 33 || lvt_4_6_ == 39 || lvt_4_6_ == 45) {
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 9, 47, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 9, 50, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 10, 45, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 10, 46, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 10, 51, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 10, 52, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 11, 47, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 11, 50, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 12, 48, p_175842_3_);
                  this.func_175811_a(p_175842_1_, field_175824_d, lvt_4_6_, 12, 49, p_175842_3_);
               }
            }

            for(lvt_4_6_ = 0; lvt_4_6_ < 3; ++lvt_4_6_) {
               this.func_175804_a(p_175842_1_, p_175842_3_, 8 + lvt_4_6_, 5 + lvt_4_6_, 54, 49 - lvt_4_6_, 5 + lvt_4_6_, 54, field_175828_a, field_175828_a, false);
            }

            this.func_175804_a(p_175842_1_, p_175842_3_, 11, 8, 54, 46, 8, 54, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_175842_1_, p_175842_3_, 14, 8, 44, 43, 8, 53, field_175828_a, field_175828_a, false);
         }

      }

      private void func_175838_g(World p_175838_1_, Random p_175838_2_, StructureBoundingBox p_175838_3_) {
         int lvt_4_6_;
         if (this.func_175818_a(p_175838_3_, 14, 21, 20, 43)) {
            this.func_175804_a(p_175838_1_, p_175838_3_, 14, 0, 21, 20, 0, 43, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175838_1_, p_175838_3_, 14, 1, 22, 20, 14, 43, false);
            this.func_175804_a(p_175838_1_, p_175838_3_, 18, 12, 22, 20, 12, 39, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175838_1_, p_175838_3_, 18, 12, 21, 20, 12, 21, field_175826_b, field_175826_b, false);

            for(lvt_4_6_ = 0; lvt_4_6_ < 4; ++lvt_4_6_) {
               this.func_175804_a(p_175838_1_, p_175838_3_, lvt_4_6_ + 14, lvt_4_6_ + 9, 21, lvt_4_6_ + 14, lvt_4_6_ + 9, 43 - lvt_4_6_, field_175826_b, field_175826_b, false);
            }

            for(lvt_4_6_ = 23; lvt_4_6_ <= 39; lvt_4_6_ += 3) {
               this.func_175811_a(p_175838_1_, field_175824_d, 19, 13, lvt_4_6_, p_175838_3_);
            }
         }

         if (this.func_175818_a(p_175838_3_, 37, 21, 43, 43)) {
            this.func_175804_a(p_175838_1_, p_175838_3_, 37, 0, 21, 43, 0, 43, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175838_1_, p_175838_3_, 37, 1, 22, 43, 14, 43, false);
            this.func_175804_a(p_175838_1_, p_175838_3_, 37, 12, 22, 39, 12, 39, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175838_1_, p_175838_3_, 37, 12, 21, 39, 12, 21, field_175826_b, field_175826_b, false);

            for(lvt_4_6_ = 0; lvt_4_6_ < 4; ++lvt_4_6_) {
               this.func_175804_a(p_175838_1_, p_175838_3_, 43 - lvt_4_6_, lvt_4_6_ + 9, 21, 43 - lvt_4_6_, lvt_4_6_ + 9, 43 - lvt_4_6_, field_175826_b, field_175826_b, false);
            }

            for(lvt_4_6_ = 23; lvt_4_6_ <= 39; lvt_4_6_ += 3) {
               this.func_175811_a(p_175838_1_, field_175824_d, 38, 13, lvt_4_6_, p_175838_3_);
            }
         }

         if (this.func_175818_a(p_175838_3_, 15, 37, 42, 43)) {
            this.func_175804_a(p_175838_1_, p_175838_3_, 21, 0, 37, 36, 0, 43, field_175828_a, field_175828_a, false);
            this.func_181655_a(p_175838_1_, p_175838_3_, 21, 1, 37, 36, 14, 43, false);
            this.func_175804_a(p_175838_1_, p_175838_3_, 21, 12, 37, 36, 12, 39, field_175828_a, field_175828_a, false);

            for(lvt_4_6_ = 0; lvt_4_6_ < 4; ++lvt_4_6_) {
               this.func_175804_a(p_175838_1_, p_175838_3_, 15 + lvt_4_6_, lvt_4_6_ + 9, 43 - lvt_4_6_, 42 - lvt_4_6_, lvt_4_6_ + 9, 43 - lvt_4_6_, field_175826_b, field_175826_b, false);
            }

            for(lvt_4_6_ = 21; lvt_4_6_ <= 36; lvt_4_6_ += 3) {
               this.func_175811_a(p_175838_1_, field_175824_d, lvt_4_6_, 13, 38, p_175838_3_);
            }
         }

      }
   }

   public abstract static class Piece extends StructureComponent {
      protected static final IBlockState field_175828_a;
      protected static final IBlockState field_175826_b;
      protected static final IBlockState field_175827_c;
      protected static final IBlockState field_175824_d;
      protected static final IBlockState field_175825_e;
      protected static final IBlockState field_175822_f;
      protected static final int field_175823_g;
      protected static final int field_175831_h;
      protected static final int field_175832_i;
      protected static final int field_175829_j;
      protected StructureOceanMonumentPieces.RoomDefinition field_175830_k;

      protected static final int func_175820_a(int p_175820_0_, int p_175820_1_, int p_175820_2_) {
         return p_175820_1_ * 25 + p_175820_2_ * 5 + p_175820_0_;
      }

      public Piece() {
         super(0);
      }

      public Piece(int p_i45588_1_) {
         super(p_i45588_1_);
      }

      public Piece(EnumFacing p_i45589_1_, StructureBoundingBox p_i45589_2_) {
         super(1);
         this.field_74885_f = p_i45589_1_;
         this.field_74887_e = p_i45589_2_;
      }

      protected Piece(int p_i45590_1_, EnumFacing p_i45590_2_, StructureOceanMonumentPieces.RoomDefinition p_i45590_3_, int p_i45590_4_, int p_i45590_5_, int p_i45590_6_) {
         super(p_i45590_1_);
         this.field_74885_f = p_i45590_2_;
         this.field_175830_k = p_i45590_3_;
         int lvt_7_1_ = p_i45590_3_.field_175967_a;
         int lvt_8_1_ = lvt_7_1_ % 5;
         int lvt_9_1_ = lvt_7_1_ / 5 % 5;
         int lvt_10_1_ = lvt_7_1_ / 25;
         if (p_i45590_2_ != EnumFacing.NORTH && p_i45590_2_ != EnumFacing.SOUTH) {
            this.field_74887_e = new StructureBoundingBox(0, 0, 0, p_i45590_6_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_4_ * 8 - 1);
         } else {
            this.field_74887_e = new StructureBoundingBox(0, 0, 0, p_i45590_4_ * 8 - 1, p_i45590_5_ * 4 - 1, p_i45590_6_ * 8 - 1);
         }

         switch(p_i45590_2_) {
         case NORTH:
            this.field_74887_e.func_78886_a(lvt_8_1_ * 8, lvt_10_1_ * 4, -(lvt_9_1_ + p_i45590_6_) * 8 + 1);
            break;
         case SOUTH:
            this.field_74887_e.func_78886_a(lvt_8_1_ * 8, lvt_10_1_ * 4, lvt_9_1_ * 8);
            break;
         case WEST:
            this.field_74887_e.func_78886_a(-(lvt_9_1_ + p_i45590_6_) * 8 + 1, lvt_10_1_ * 4, lvt_8_1_ * 8);
            break;
         default:
            this.field_74887_e.func_78886_a(lvt_9_1_ * 8, lvt_10_1_ * 4, lvt_8_1_ * 8);
         }

      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
      }

      protected void func_181655_a(World p_181655_1_, StructureBoundingBox p_181655_2_, int p_181655_3_, int p_181655_4_, int p_181655_5_, int p_181655_6_, int p_181655_7_, int p_181655_8_, boolean p_181655_9_) {
         for(int lvt_10_1_ = p_181655_4_; lvt_10_1_ <= p_181655_7_; ++lvt_10_1_) {
            for(int lvt_11_1_ = p_181655_3_; lvt_11_1_ <= p_181655_6_; ++lvt_11_1_) {
               for(int lvt_12_1_ = p_181655_5_; lvt_12_1_ <= p_181655_8_; ++lvt_12_1_) {
                  if (!p_181655_9_ || this.func_175807_a(p_181655_1_, lvt_11_1_, lvt_10_1_, lvt_12_1_, p_181655_2_).func_177230_c().func_149688_o() != Material.field_151579_a) {
                     if (this.func_74862_a(lvt_10_1_) >= p_181655_1_.func_181545_F()) {
                        this.func_175811_a(p_181655_1_, Blocks.field_150350_a.func_176223_P(), lvt_11_1_, lvt_10_1_, lvt_12_1_, p_181655_2_);
                     } else {
                        this.func_175811_a(p_181655_1_, field_175822_f, lvt_11_1_, lvt_10_1_, lvt_12_1_, p_181655_2_);
                     }
                  }
               }
            }
         }

      }

      protected void func_175821_a(World p_175821_1_, StructureBoundingBox p_175821_2_, int p_175821_3_, int p_175821_4_, boolean p_175821_5_) {
         if (p_175821_5_) {
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 2, 0, p_175821_4_ + 8 - 1, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 5, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 0, p_175821_3_ + 4, 0, p_175821_4_ + 2, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 8 - 1, field_175828_a, field_175828_a, false);
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 2, p_175821_3_ + 4, 0, p_175821_4_ + 2, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 3, 0, p_175821_4_ + 5, p_175821_3_ + 4, 0, p_175821_4_ + 5, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 2, 0, p_175821_4_ + 3, p_175821_3_ + 2, 0, p_175821_4_ + 4, field_175826_b, field_175826_b, false);
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 5, 0, p_175821_4_ + 3, p_175821_3_ + 5, 0, p_175821_4_ + 4, field_175826_b, field_175826_b, false);
         } else {
            this.func_175804_a(p_175821_1_, p_175821_2_, p_175821_3_ + 0, 0, p_175821_4_ + 0, p_175821_3_ + 8 - 1, 0, p_175821_4_ + 8 - 1, field_175828_a, field_175828_a, false);
         }

      }

      protected void func_175819_a(World p_175819_1_, StructureBoundingBox p_175819_2_, int p_175819_3_, int p_175819_4_, int p_175819_5_, int p_175819_6_, int p_175819_7_, int p_175819_8_, IBlockState p_175819_9_) {
         for(int lvt_10_1_ = p_175819_4_; lvt_10_1_ <= p_175819_7_; ++lvt_10_1_) {
            for(int lvt_11_1_ = p_175819_3_; lvt_11_1_ <= p_175819_6_; ++lvt_11_1_) {
               for(int lvt_12_1_ = p_175819_5_; lvt_12_1_ <= p_175819_8_; ++lvt_12_1_) {
                  if (this.func_175807_a(p_175819_1_, lvt_11_1_, lvt_10_1_, lvt_12_1_, p_175819_2_) == field_175822_f) {
                     this.func_175811_a(p_175819_1_, p_175819_9_, lvt_11_1_, lvt_10_1_, lvt_12_1_, p_175819_2_);
                  }
               }
            }
         }

      }

      protected boolean func_175818_a(StructureBoundingBox p_175818_1_, int p_175818_2_, int p_175818_3_, int p_175818_4_, int p_175818_5_) {
         int lvt_6_1_ = this.func_74865_a(p_175818_2_, p_175818_3_);
         int lvt_7_1_ = this.func_74873_b(p_175818_2_, p_175818_3_);
         int lvt_8_1_ = this.func_74865_a(p_175818_4_, p_175818_5_);
         int lvt_9_1_ = this.func_74873_b(p_175818_4_, p_175818_5_);
         return p_175818_1_.func_78885_a(Math.min(lvt_6_1_, lvt_8_1_), Math.min(lvt_7_1_, lvt_9_1_), Math.max(lvt_6_1_, lvt_8_1_), Math.max(lvt_7_1_, lvt_9_1_));
      }

      protected boolean func_175817_a(World p_175817_1_, StructureBoundingBox p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_) {
         int lvt_6_1_ = this.func_74865_a(p_175817_3_, p_175817_5_);
         int lvt_7_1_ = this.func_74862_a(p_175817_4_);
         int lvt_8_1_ = this.func_74873_b(p_175817_3_, p_175817_5_);
         if (p_175817_2_.func_175898_b(new BlockPos(lvt_6_1_, lvt_7_1_, lvt_8_1_))) {
            EntityGuardian lvt_9_1_ = new EntityGuardian(p_175817_1_);
            lvt_9_1_.func_175467_a(true);
            lvt_9_1_.func_70691_i(lvt_9_1_.func_110138_aP());
            lvt_9_1_.func_70012_b((double)lvt_6_1_ + 0.5D, (double)lvt_7_1_, (double)lvt_8_1_ + 0.5D, 0.0F, 0.0F);
            lvt_9_1_.func_180482_a(p_175817_1_.func_175649_E(new BlockPos(lvt_9_1_)), (IEntityLivingData)null);
            p_175817_1_.func_72838_d(lvt_9_1_);
            return true;
         } else {
            return false;
         }
      }

      static {
         field_175828_a = Blocks.field_180397_cI.func_176203_a(BlockPrismarine.field_176331_b);
         field_175826_b = Blocks.field_180397_cI.func_176203_a(BlockPrismarine.field_176333_M);
         field_175827_c = Blocks.field_180397_cI.func_176203_a(BlockPrismarine.field_176334_N);
         field_175824_d = field_175826_b;
         field_175825_e = Blocks.field_180398_cJ.func_176223_P();
         field_175822_f = Blocks.field_150355_j.func_176223_P();
         field_175823_g = func_175820_a(2, 0, 0);
         field_175831_h = func_175820_a(2, 2, 0);
         field_175832_i = func_175820_a(0, 1, 0);
         field_175829_j = func_175820_a(4, 1, 0);
      }
   }
}
