package net.TntClient.modules.movement;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.EventUpdate;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

public class Dolphin extends Module
{
    public Dolphin()
    {
        super("Dolphin", Category.MOVEMENT, false, false, false);
    }

    @Override
    public void onToggle()
    {
    }

    @Override
    public void onSetup()
    {
    }

    @EventTarget
    public void onUpdate(EventUpdate event)
    {
        if (mc.thePlayer.isInWater() && !mc.thePlayer.isSneaking())
        {
            mc.thePlayer.motionY += 0.04;
        }
    }
}
