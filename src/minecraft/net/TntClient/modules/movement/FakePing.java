package net.TntClient.modules.movement;

import net.TntClient.Util;
import net.TntClient.event.Event;
import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.event.events.EventPostMotionUpdate;
import net.TntClient.event.events.EventSendPacket;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

import java.util.Random;

public class FakePing extends Module {

    private static final Block tnt = Block.getBlockById(46);
    private static final IBlockState sand = Block.getBlockById(12).getDefaultState();
    private static final IBlockState redsand = Block.getBlockById(12).getStateFromMeta(1);
    private static final IBlockState gravel = Block.getBlockById(13).getDefaultState();
    private static final Random r = new Random();
    private static boolean isGood = false;

    public FakePing() {
        super("Fake ping", Category.FUN);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(EventSendPacket event) {
        if (event.getPacket() instanceof C03PacketPlayer && r.nextInt(6) > 0 && isGood)
            event.setCancelled(true);

    }

    @EventTarget
    public void onUpdate(EventPostMotionUpdate event) {
        if (mc.thePlayer.onGround)
            isGood = isBlock();
    }

    @EventTarget
    public void onUpdate(Event2D event) {
        if (isGood)
            mc.fontRendererObj.drawString("Warning Fack ping is on", (int) event.getWidth() -
                    mc.fontRendererObj.getStringWidth("Warning Fack ping is on"), (int) (event.getHeight() - 10), Util.getRainbow());
    }

    private static boolean isBlock() {
        final IBlockState block = mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ));
        return mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 2, mc.thePlayer.posZ)).getBlock() == tnt
                && (block == sand || block == gravel || block == redsand);
    }
}
