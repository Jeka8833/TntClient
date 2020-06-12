package optifine;

import java.util.BitSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.util.EnumFacing;

public class RenderEnv
{
    private IBlockState blockState;
    private int blockId = -1;
    private int metadata = -1;
    private int breakingAnimation = -1;
    private final float[] quadBounds = new float[EnumFacing.VALUES.length * 2];
    private final BitSet boundsFlags = new BitSet(3);
    private final BlockModelRenderer.AmbientOcclusionFace aoFace = new BlockModelRenderer.AmbientOcclusionFace();
    private BlockPosM colorizerBlockPosM = null;
    private boolean[] borderFlags = null;
    private static final ThreadLocal threadLocalInstance = new ThreadLocal();

    private RenderEnv(IBlockState p_i94_2_)
    {
        this.blockState = p_i94_2_;
    }

    public static RenderEnv getInstance(IBlockState p_getInstance_1_)
    {
        RenderEnv renderenv = (RenderEnv)threadLocalInstance.get();

        if (renderenv == null)
        {
            renderenv = new RenderEnv(p_getInstance_1_);
            threadLocalInstance.set(renderenv);
        }
        else
        {
            renderenv.reset(p_getInstance_1_);
        }
        return renderenv;
    }

    private void reset(IBlockState p_reset_2_)
    {
        this.blockState = p_reset_2_;
        this.blockId = -1;
        this.metadata = -1;
        this.breakingAnimation = -1;
        this.boundsFlags.clear();
    }

    public int getBlockId()
    {
        if (this.blockId < 0)
        {
            if (this.blockState instanceof BlockStateBase)
            {
                BlockStateBase blockstatebase = (BlockStateBase)this.blockState;
                this.blockId = blockstatebase.getBlockId();
            }
            else
            {
                this.blockId = Block.getIdFromBlock(this.blockState.getBlock());
            }
        }

        return this.blockId;
    }

    public int getMetadata()
    {
        if (this.metadata < 0)
        {
            if (this.blockState instanceof BlockStateBase)
            {
                BlockStateBase blockstatebase = (BlockStateBase)this.blockState;
                this.metadata = blockstatebase.getMetadata();
            }
            else
            {
                this.metadata = this.blockState.getBlock().getMetaFromState(this.blockState);
            }
        }

        return this.metadata;
    }

    public float[] getQuadBounds()
    {
        return this.quadBounds;
    }

    public BitSet getBoundsFlags()
    {
        return this.boundsFlags;
    }

    public BlockModelRenderer.AmbientOcclusionFace getAoFace()
    {
        return this.aoFace;
    }

    public boolean isBreakingAnimation(List p_isBreakingAnimation_1_)
    {
        if (this.breakingAnimation < 0 && p_isBreakingAnimation_1_.size() > 0)
        {
            if (p_isBreakingAnimation_1_.get(0) instanceof BreakingFour)
            {
                this.breakingAnimation = 1;
            }
            else
            {
                this.breakingAnimation = 0;
            }
        }

        return this.breakingAnimation != 1;
    }

    public boolean isBreakingAnimation(BakedQuad p_isBreakingAnimation_1_)
    {
        if (this.breakingAnimation < 0)
        {
            if (p_isBreakingAnimation_1_ instanceof BreakingFour)
            {
                this.breakingAnimation = 1;
            }
            else
            {
                this.breakingAnimation = 0;
            }
        }

        return this.breakingAnimation != 1;
    }

    public boolean isBreakingAnimation()
    {
        return this.breakingAnimation != 1;
    }

    public IBlockState getBlockState()
    {
        return this.blockState;
    }

    public BlockPosM getColorizerBlockPosM()
    {
        if (this.colorizerBlockPosM == null)
        {
            this.colorizerBlockPosM = new BlockPosM(0, 0, 0);
        }

        return this.colorizerBlockPosM;
    }

    public boolean[] getBorderFlags()
    {
        if (this.borderFlags == null)
        {
            this.borderFlags = new boolean[4];
        }

        return this.borderFlags;
    }
}
