package net.TntClient.modules.movement;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.EventUpdate;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

public class FreeDJ extends Module {
    public FreeDJ() {
        super("Free DJ", Category.MOVEMENT, true, true, true);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        mc.thePlayer.motionY = 1;
        toggle();
    }
}
