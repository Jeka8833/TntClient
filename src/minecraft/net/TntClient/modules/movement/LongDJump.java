package net.TntClient.modules.movement;

import net.TntClient.Util;
import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.event.events.EventPreMotionUpdate;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

public class LongDJump extends Module {

    private static boolean jump = false;
    private static long time = 0;

    public LongDJump() {
        super("Long DJump", Category.MOVEMENT);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(EventPreMotionUpdate event) {
        if (mc.thePlayer.motionY > 0.6 || mc.thePlayer.motionZ > 0.6 || mc.thePlayer.motionZ < -0.6 || mc.thePlayer.motionX > 0.6 || mc.thePlayer.motionX < -0.6) {
            if (!jump) {
                mc.thePlayer.motionX *= 1.5;
                mc.thePlayer.motionY *= 1.16;
                mc.thePlayer.motionZ *= 1.5;
                jump = true;
                time = System.currentTimeMillis();
            }
        } else jump = false;
    }

    @EventTarget
    public void onUpdate(Event2D event) {
        if (System.currentTimeMillis() - time < 3000) {
            mc.fontRendererObj.drawString("Long jump", 5, (int) (event.getHeight() - 10), Util.getRainbow());
        }
    }
}
