package net.TntClient.modules.movement;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.EventUpdate;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

public class Spider extends Module
{
    public Spider()
    {
        super("Spider", Category.MOVEMENT);
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
        if (mc.thePlayer.isCollidedHorizontally)
        {
            mc.thePlayer.motionY = 0.2;
        }
    }
}
