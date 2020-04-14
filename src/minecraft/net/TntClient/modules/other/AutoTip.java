package net.TntClient.modules.other;

import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.minecraft.client.Minecraft;

public class AutoTip extends Module {
    public AutoTip() {
        super("Auto tip", Category.OTHER);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    public void onEnable() {
        if (HypixelPlayers.isHypixel)
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/tip all");
        super.onEnable();
    }
}
