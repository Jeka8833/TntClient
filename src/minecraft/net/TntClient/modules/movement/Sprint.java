package net.TntClient.modules.movement;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.EventUpdate;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT, false);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    public void onDisable() {
        mc.thePlayer.setSprinting(false);
        super.onDisable();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (!mc.thePlayer.isSprinting() && !mc.thePlayer.isSneaking() && !mc.thePlayer.isCollidedHorizontally) {
            mc.thePlayer.setSprinting(true);
        }
    }
}
