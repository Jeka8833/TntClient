package optifine;

import net.minecraft.block.state.BlockStateBase;

public class MatchBlock
{
    private final int blockId;
    private int[] metadatas = null;

    public MatchBlock(int p_i63_1_)
    {
        this.blockId = p_i63_1_;
    }

    public MatchBlock(int p_i64_1_, int p_i64_2_)
    {
        this.blockId = p_i64_1_;

        if (p_i64_2_ >= 0 && p_i64_2_ <= 15)
        {
            this.metadatas = new int[] {p_i64_2_};
        }
    }

    public MatchBlock(int p_i65_1_, int[] p_i65_2_)
    {
        this.blockId = p_i65_1_;
        this.metadatas = p_i65_2_;
    }

    public int getBlockId()
    {
        return this.blockId;
    }

    public int[] getMetadatas()
    {
        return this.metadatas;
    }

    public boolean matches(BlockStateBase p_matches_1_)
    {
        return p_matches_1_.getBlockId() == this.blockId && Matches.metadata(p_matches_1_.getMetadata(), this.metadatas);
    }

    public boolean matches(int p_matches_1_, int p_matches_2_)
    {
        return p_matches_1_ == this.blockId && Matches.metadata(p_matches_2_, this.metadatas);
    }

    public String toString()
    {
        return "" + this.blockId + ":" + Config.arrayToString(this.metadatas);
    }
}
