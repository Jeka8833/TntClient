package net.TntClient.modules.movement;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.EventUpdate;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ClearSlowness extends Module {
    public ClearSlowness() {
        super("Clear Slowness", Category.MOVEMENT, false, false, false);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        System.out.println(mc.thePlayer.getActivePotionEffects());
    }
}
