package net.TntClient.modules.block;

import net.TntClient.event.EventTarget;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

public class GlitchBlocks extends Module {

    private static final Block sand = Block.getBlockById(12);
    private static final Block gravel = Block.getBlockById(13);
    private static final Block tnt = Block.getBlockById(46);
    private static final Block air = Block.getBlockById(0);
    private static final IBlockState redSand = Block.getBlockById(20).getDefaultState();
    private static final IBlockState airSetter = air.getDefaultState();

    private static final BlockInfo[] blocks = new BlockInfo[3];

    public GlitchBlocks() {
        super("Glitch Blocks", Category.BLOCKS, Keyboard.KEY_G, true, true, true);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate() {
        if (mc.thePlayer.onGround) {
            final double ang = -Math.toRadians(mc.thePlayer.rotationYaw);
            final double posX = mc.thePlayer.posX + Math.sin(ang) * 3.5;
            final double posY = mc.thePlayer.posZ + Math.cos(ang) * 3.5;

            final BlockPos poss = new BlockPos(posX, mc.thePlayer.posY - 1, posY);
            final Block info = mc.theWorld.getBlockState(poss).getBlock();
            if ((info == sand || info == gravel || info == air) && (blocks[0] == null ||
                    !(poss.getX() == blocks[0].pos.getX() && poss.getY() == blocks[0].pos.getY() && poss.getZ() == blocks[0].pos.getZ()))) {
                if (blocks[0] != null) {
                    if (blocks[0].pos.getX() == Math.floor(mc.thePlayer.posX) &&
                            blocks[0].pos.getY() == (int) mc.thePlayer.posY - 1
                            && blocks[0].pos.getZ() == Math.floor(mc.thePlayer.posZ)) {
                        if (blocks[1] != null)
                            blocks[2] = new BlockInfo(blocks[1].pos, blocks[1].block);
                        blocks[1] = new BlockInfo(blocks[0].pos, blocks[0].block);
                    } else blocks[0].update();
                }
                if (blocks[2] != null)
                    blocks[2].update();
                blocks[0] = new BlockInfo(poss, mc.theWorld.getBlockState(poss));
                mc.theWorld.setBlockState(poss, redSand);
            }
        }
    }

    public static class BlockInfo {
        public final BlockPos pos;
        final IBlockState block;

        public BlockInfo(BlockPos pos, IBlockState block) {
            this.pos = pos;
            this.block = block;
        }

        public void update() {
            if (mc.theWorld.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() == tnt)
                mc.theWorld.setBlockState(pos, block);
            else
                mc.theWorld.setBlockState(pos, airSetter);
        }
    }
}