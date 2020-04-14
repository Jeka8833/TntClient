package net.optifine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BetterSnow {
   private static IBakedModel modelSnowLayer = null;

   public static void update() {
      modelSnowLayer = Config.getMinecraft().func_175602_ab().func_175023_a().func_178125_b(Blocks.field_150431_aC.func_176223_P());
   }

   public static IBakedModel getModelSnowLayer() {
      return modelSnowLayer;
   }

   public static IBlockState getStateSnowLayer() {
      return Blocks.field_150431_aC.func_176223_P();
   }

   public static boolean shouldRender(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos) {
      Block block = blockState.func_177230_c();
      return !checkBlock(block, blockState) ? false : hasSnowNeighbours(blockAccess, blockPos);
   }

   private static boolean hasSnowNeighbours(IBlockAccess blockAccess, BlockPos pos) {
      Block blockSnow = Blocks.field_150431_aC;
      return blockAccess.func_180495_p(pos.func_177978_c()).func_177230_c() != blockSnow && blockAccess.func_180495_p(pos.func_177968_d()).func_177230_c() != blockSnow && blockAccess.func_180495_p(pos.func_177976_e()).func_177230_c() != blockSnow && blockAccess.func_180495_p(pos.func_177974_f()).func_177230_c() != blockSnow ? false : blockAccess.func_180495_p(pos.func_177977_b()).func_177230_c().func_149662_c();
   }

   private static boolean checkBlock(Block block, IBlockState blockState) {
      if (block.func_149686_d()) {
         return false;
      } else if (block.func_149662_c()) {
         return false;
      } else if (block instanceof BlockSnow) {
         return false;
      } else if (block instanceof BlockBush && (block instanceof BlockDoublePlant || block instanceof BlockFlower || block instanceof BlockMushroom || block instanceof BlockSapling || block instanceof BlockTallGrass)) {
         return true;
      } else if (!(block instanceof BlockFence) && !(block instanceof BlockFenceGate) && !(block instanceof BlockFlowerPot) && !(block instanceof BlockPane) && !(block instanceof BlockReed) && !(block instanceof BlockWall)) {
         if (block instanceof BlockRedstoneTorch && blockState.func_177229_b(BlockTorch.field_176596_a) == EnumFacing.UP) {
            return true;
         } else {
            if (block instanceof BlockLever) {
               Object orient = blockState.func_177229_b(BlockLever.field_176360_a);
               if (orient == BlockLever.EnumOrientation.UP_X || orient == BlockLever.EnumOrientation.UP_Z) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }
}
