package net.TntClient.modules;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.gui.JekasMenu.components.UISlider;

public class DebugModule extends Module {

    private static UISlider testSlider = new UISlider("Test", 0, 100, 1);

    public DebugModule() {
        super("Test module", Category.FUN, true);
        addOption(testSlider);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(Event2D event) {
        mc.fontRendererObj.drawString(String.valueOf(testSlider.value), (int)event.getWidth() -
                mc.fontRendererObj.getStringWidth(String.valueOf(testSlider.value)), (int)event.getHeight() - 10, -1);
    }
}