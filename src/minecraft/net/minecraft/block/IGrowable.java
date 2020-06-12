package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IGrowable
{
    /**
     * Whether this IGrowable can grow
     */
    boolean canGrow(World worldIn, BlockPos pos, IBlockState state);

    boolean canUseBonemeal(World worldIn, Random rand);

    void grow(World worldIn, Random rand, BlockPos pos, IBlockState state);
}
