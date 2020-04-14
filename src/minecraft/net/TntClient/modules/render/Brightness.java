package net.TntClient.modules.render;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.EventUpdate;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

public class Brightness extends Module {

    public Brightness() {
        super("Brightness", Category.RENDER);
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = 1f;
        super.onDisable();
    }

    @Override
    public void onToggle() {
    }

    @Override
    public void onSetup() {
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        mc.gameSettings.gammaSetting = 100f;
    }
}