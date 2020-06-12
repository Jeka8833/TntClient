package net.minecraft.block;

import net.minecraft.tileentity.TileEntity;

public interface ITileEntityProvider
{
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    TileEntity createNewTileEntity(int meta);
}
