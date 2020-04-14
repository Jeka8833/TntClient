package net.optifine;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public enum BlockDir {
   DOWN(EnumFacing.DOWN),
   UP(EnumFacing.UP),
   NORTH(EnumFacing.NORTH),
   SOUTH(EnumFacing.SOUTH),
   WEST(EnumFacing.WEST),
   EAST(EnumFacing.EAST),
   NORTH_WEST(EnumFacing.NORTH, EnumFacing.WEST),
   NORTH_EAST(EnumFacing.NORTH, EnumFacing.EAST),
   SOUTH_WEST(EnumFacing.SOUTH, EnumFacing.WEST),
   SOUTH_EAST(EnumFacing.SOUTH, EnumFacing.EAST),
   DOWN_NORTH(EnumFacing.DOWN, EnumFacing.NORTH),
   DOWN_SOUTH(EnumFacing.DOWN, EnumFacing.SOUTH),
   UP_NORTH(EnumFacing.UP, EnumFacing.NORTH),
   UP_SOUTH(EnumFacing.UP, EnumFacing.SOUTH),
   DOWN_WEST(EnumFacing.DOWN, EnumFacing.WEST),
   DOWN_EAST(EnumFacing.DOWN, EnumFacing.EAST),
   UP_WEST(EnumFacing.UP, EnumFacing.WEST),
   UP_EAST(EnumFacing.UP, EnumFacing.EAST);

   private EnumFacing facing1;
   private EnumFacing facing2;

   private BlockDir(EnumFacing facing1) {
      this.facing1 = facing1;
   }

   private BlockDir(EnumFacing facing1, EnumFacing facing2) {
      this.facing1 = facing1;
      this.facing2 = facing2;
   }

   public EnumFacing getFacing1() {
      return this.facing1;
   }

   public EnumFacing getFacing2() {
      return this.facing2;
   }

   BlockPos offset(BlockPos pos) {
      pos = pos.func_177967_a(this.facing1, 1);
      if (this.facing2 != null) {
         pos = pos.func_177967_a(this.facing2, 1);
      }

      return pos;
   }

   public int getOffsetX() {
      int offset = this.facing1.func_82601_c();
      if (this.facing2 != null) {
         offset += this.facing2.func_82601_c();
      }

      return offset;
   }

   public int getOffsetY() {
      int offset = this.facing1.func_96559_d();
      if (this.facing2 != null) {
         offset += this.facing2.func_96559_d();
      }

      return offset;
   }

   public int getOffsetZ() {
      int offset = this.facing1.func_82599_e();
      if (this.facing2 != null) {
         offset += this.facing2.func_82599_e();
      }

      return offset;
   }

   public boolean isDouble() {
      return this.facing2 != null;
   }
}
