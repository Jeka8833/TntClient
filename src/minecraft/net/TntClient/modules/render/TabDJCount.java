package net.TntClient.modules.render;

import net.TntClient.Config;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.minecraft.util.EnumChatFormatting;

import java.util.UUID;

public class TabDJCount extends Module {

    public TabDJCount() {
        super("Tab DJ Count", Category.RENDER, false, false, false);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static String getPlayerSyff(UUID uuid){
        if(!Config.config.tabDJCount.isActive() || !HypixelPlayers.playerInfoMap.containsKey(uuid))
            return "";
        final int jump = HypixelPlayers.playerInfoMap.get(uuid).jump;
        if(jump == Integer.MIN_VALUE)
            return "";
        return "" + EnumChatFormatting.GREEN + (jump + 4 ) + EnumChatFormatting.RESET;
    }
}
