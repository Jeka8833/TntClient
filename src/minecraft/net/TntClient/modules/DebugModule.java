package net.TntClient.modules;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.EventUpdate;
import net.TntClient.gui.JekasMenu.components.UISlider;

public class DebugModule extends Module {

    private static final UISlider testSlider = new UISlider("Test", 0, 100, 1);

    public DebugModule() {
        super("Test module", Category.FUN, true, false, false);
        addOption(testSlider);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(EventUpdate event) {

    }
}