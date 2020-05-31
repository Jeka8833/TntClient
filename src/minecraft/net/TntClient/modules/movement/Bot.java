package net.TntClient.modules.movement;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event3D;
import net.TntClient.event.events.EventCollide;
import net.TntClient.event.events.EventUpdate;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.TntClient.modules.block.GlitchBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class Bot extends Module {

    private static final IBlockState air = Block.getBlockById(0).getDefaultState();
    private static final IBlockState sand = Block.getBlockById(12).getDefaultState();
    private static final IBlockState redsand = Block.getBlockById(12).getStateFromMeta(1);
    private static final IBlockState gravel = Block.getBlockById(13).getDefaultState();

    private static int lastY = 0;
    private static List<SkipBlock> skipBlocks = new ArrayList<>();
    private static GlitchBlocks.BlockInfo blockInfo;
    private static Vec3i toBlock;

    private static double deltaYaw;
    private static long time = System.currentTimeMillis();
    private static long airTime = System.currentTimeMillis();

    public Bot() {
        super("TntRun Bot", Category.MOVEMENT, Keyboard.KEY_F, true, false, false);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        final double posX = mc.thePlayer.posX;
        final double posY = mc.thePlayer.posY;
        final double posZ = mc.thePlayer.posZ;

        if (playerStayInBlock()) {
            lastY = (int) posY;
            toBlock = getNextStep(posX, posY, posZ);
            if (toBlock != null) {
                skipBlocks.add(0, new SkipBlock(toBlock.getX(), lastY, toBlock.getZ()));
                final Vec3i nextBlock = getNextStep((toBlock.getX() + 0.5), toBlock.getY() + 1, (toBlock.getZ() + 0.5));
                final double len = Math.sqrt((posX - (toBlock.getX() + 0.5)) * (posX - (toBlock.getX() + 0.5)) + (posZ - (toBlock.getZ() + 0.5)) * (posZ - (toBlock.getZ() + 0.5)));
                if (nextBlock != null) {
                    final double minusAngle = getAngle((720 + mc.thePlayer.rotationYaw) % 360,
                            Math.toDegrees(Math.PI - Math.atan2((toBlock.getX() + 0.5) - (nextBlock.getX() + 0.5),
                                    (toBlock.getZ() + 0.5) - (nextBlock.getZ() + 0.5))));
                    if (len > 2.5) {
                        if (minusAngle > 30 || minusAngle < -30) {
                            deltaYaw = minusAngle * 1.6666666666;
                            airTime = System.currentTimeMillis();
                        }
                    } else {
                        if (minusAngle > 95 || minusAngle < -95) {
                            deltaYaw = minusAngle * 1.6666666666;
                            airTime = System.currentTimeMillis();
                        }
                    }
                } else deltaYaw = 0;

                final double angle = -Math.atan2((toBlock.getX() + 0.5) - posX, (toBlock.getZ() + 0.5) - posZ);
                mc.thePlayer.motionX = -Math.sin(angle) * (len * 0.0784) / 0.42;
                mc.thePlayer.motionY = 0.42F;
                mc.thePlayer.motionZ = Math.cos(angle) * (len * 0.0784) / 0.42;
            } else deltaYaw = 0;
        }
        skipBlocks.removeIf(skipBlock -> System.currentTimeMillis() - skipBlock.time > 10000 || lastY != skipBlock.y);
    }

    @EventTarget
    public void onUpdate(Event3D event) {
        if (System.currentTimeMillis() - airTime < 600)
            mc.thePlayer.rotationYaw = (float) ((mc.thePlayer.rotationYaw + (deltaYaw * ((System.currentTimeMillis() - time) / 1000.))) % 360);
        time = System.currentTimeMillis();

        if (toBlock != null) {
            if (blockInfo == null || toBlock.getX() != blockInfo.pos.getX() || toBlock.getY() != blockInfo.pos.getY() || toBlock.getZ() != blockInfo.pos.getZ()) {
                if (blockInfo != null)
                    blockInfo.update();
                blockInfo = new GlitchBlocks.BlockInfo(new BlockPos(toBlock), mc.theWorld.getBlockState(new BlockPos(toBlock)));
            }
        }
        if (blockInfo != null) {
            if (toBlock == null)
                blockInfo.update();
            else
                mc.theWorld.setBlockState(new BlockPos(blockInfo.pos), redsand);
        }
    }

    @EventTarget
    public void onUpdate(EventCollide event) {
        if (event.getBoundingBox() != null && event.getEntity() instanceof EntityPlayer)
            skipBlocks.add(0, new SkipBlock(event.getPosX(), event.getPosY(), event.getPosZ()));
    }

    private static Vec3i getNextStep(final double playerX, final double playerY, final double playerZ) {
        final int poxX = MathHelper.floor_double(playerX);
        final int poxZ = MathHelper.floor_double(playerZ);
        Vec3i blockPos = null;
        double len = Double.MAX_VALUE;
        final List<Vec4f> blocks = new ArrayList<>();
        for (int z = poxZ - 4; z <= poxZ + 4; z++) {
            for (int x = poxX - 4; x <= poxX + 4; x++) {
                if (mc.theWorld.getBlockState(new BlockPos(x, playerY + 1, z)) != air)
                    blocks.add(new Vec4f(x - .32, z - .32, 1.64, 1.64));
            }
        }

        for (int z = poxZ - 4; z <= poxZ + 4; z++) {
            for (int x = poxX - 4; x <= poxX + 4; x++) {
                if (skipBlock(x, z))
                    continue;
                final double lens = getDistanceOrBarrier((float) playerX, (float) playerZ, x + 0.5f, z + 0.5f, blocks);
                if (lens > 5)
                    continue;
                if (lens < len && isBlock(x, (int) playerY, z)) {
                    len = lens;
                    blockPos = new Vec3i(x, playerY - 1, z);
                }
            }
        }
        return blockPos;
    }

    private static double getDistanceOrBarrier(final float fromX, final float fromZ, final float toX, final float toZ, final List<Vec4f> blocks) {
        for (Vec4f block : blocks)
            if (lineRect(fromX, fromZ, toX, toZ, block.x, block.z, block.w, block.h))
                return Double.MAX_EXPONENT;
        final float mx = fromX - toX;
        final float my = fromZ - toZ;
        return Math.sqrt(mx * mx + my * my);
    }

    private static boolean lineRect(float x1, float y1, float x2, float y2, float rx, float ry, float rw, float rh) {
        return lineLine(x1, y1, x2, y2, rx, ry, rx, ry + rh) ||
                lineLine(x1, y1, x2, y2, rx + rw, ry, rx + rw, ry + rh) ||
                lineLine(x1, y1, x2, y2, rx, ry, rx + rw, ry) ||
                lineLine(x1, y1, x2, y2, rx, ry + rh, rx + rw, ry + rh);
    }

    private static boolean lineLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        final float v = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        float uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / v;
        float uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / v;
        return uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1;
    }

    private static boolean playerStayInBlock() {
        if (!mc.thePlayer.onGround)
            return false;
        final int playerX = MathHelper.floor_double(mc.thePlayer.posX);
        final int playerY = (int) mc.thePlayer.posY;
        final int playerZ = MathHelper.floor_double(mc.thePlayer.posZ);

        if (isBlock(playerX, playerY, playerZ))
            return true;

        final double modX = mc.thePlayer.posX % 1;
        final double modZ = mc.thePlayer.posZ % 1;
        int posX = playerX;
        int posZ = playerZ;
        if ((modX > 0 && modX < .35) || (modX < -.68))
            posX--;
        else if ((modX > .68) || (modX < 0 && modX > -.35))
            posX++;
        if ((modZ > 0 && modZ < .35) || (modZ < -.68))
            posZ--;
        else if ((modZ > .68) || (modZ < 0 && modZ > -.35))
            posZ++;

        return isBlock(posX, playerY, posZ);
    }

    private static boolean isBlock(final int x, final int y, final int z) {
        final IBlockState block = mc.theWorld.getBlockState(new BlockPos(x, y - 1, z));
        return mc.theWorld.getBlockState(new BlockPos(x, y, z)) == air && (block == sand || block == gravel || block == redsand);
    }

    private static boolean skipBlock(final int x, final int z) {
        final int size = skipBlocks.size();
        for (int i = 0; i < size; i++) {
            final SkipBlock block = skipBlocks.get(i);
            if (block != null && block.x == x && block.z == z)
                return true;
        }
        return false;
    }

    private static double getAngle(final double angle1, final double angle2) {
        double max = angle1;
        double min = angle2;
        if (angle1 < angle2) {
            max = angle2;
            min = angle1;
        }

        double to = Math.min(max - min, 360 - max + min);
        final double check = (angle1 + to) - angle2;
        if (!(check > -1 && check < 1))
            to *= -1;
        return to;
    }

    private static class Vec4f {
        private final float x;
        private final float z;
        private final float w;
        private final float h;

        private Vec4f(double x, double z, double w, double h) {
            this.x = (float) x;
            this.z = (float) z;
            this.w = (float) w;
            this.h = (float) h;
        }
    }

    private static class SkipBlock {
        private final int x;
        private final int y;
        private final int z;
        private final long time = System.currentTimeMillis();

        private SkipBlock(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        private SkipBlock(double x, double y, double z) {
            this.x = MathHelper.floor_double(x);
            this.y = MathHelper.floor_double(y);
            this.z = MathHelper.floor_double(z);
        }
    }
}
