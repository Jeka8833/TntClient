package net.TntClient.modules.render;

import net.TntClient.Config;
import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.mods.hypixel.parser.Info;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.util.EnumChatFormatting;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TabDJCount extends Module {

    public static Map<UUID, Integer> jumpCount = new HashMap<>();

    public TabDJCount() {
        super("Tab DJ Count", Category.RENDER, false, true, false);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(Event2D event) {
        if (mc.currentScreen instanceof GuiDownloadTerrain)
            jumpCount.clear();
    }

    public static String getPlayerSyff(UUID uuid) {
        if (!Config.config.tabDJCount.isActive() || !HypixelPlayers.playerInfoMap.containsKey(uuid))
            return "";
        final int jumps = HypixelPlayers.playerInfoMap.get(uuid).info.player.stats.TNTGames.new_tntrun_double_jumps + 4;
        if (jumps == 4)
            return "";
        final int usedJumps = (jumpCount.containsKey(uuid) ? (jumps - jumpCount.get(uuid)) : jumps);

        if (usedJumps < 0)
            return EnumChatFormatting.AQUA.toString() + "Mod" + EnumChatFormatting.RESET;

        final EnumChatFormatting color;
        if (usedJumps < 1) {
            color = EnumChatFormatting.RED;
        } else if (usedJumps < 4) {
            color = EnumChatFormatting.YELLOW;
        } else {
            color = EnumChatFormatting.GREEN;
        }
        return color.toString() + usedJumps + EnumChatFormatting.RESET;
    }
}
