package net.TntClient.modules.render;

import net.TntClient.Config;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.mods.hypixel.parser.Info;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.minecraft.util.EnumChatFormatting;

import java.util.UUID;

public class TabDJCount extends Module {

    public TabDJCount() {
        super("Tab DJ Count", Category.RENDER, false, true, false);
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

    public static String getPlayerSyff(UUID uuid) {
        if (!Config.config.tabDJCount.isActive() || !HypixelPlayers.playerInfoMap.containsKey(uuid))
            return "";
        final Info.Player player = HypixelPlayers.playerInfoMap.get(uuid).info.player;
        if (player.stats.TNTGames.new_tntrun_double_jumps == 0)
            return "";
        return EnumChatFormatting.GREEN.toString() + (player.stats.TNTGames.new_tntrun_double_jumps + 4) + EnumChatFormatting.RESET;
    }
}
