package net.TntClient.mods.hypixel;

import com.mojang.authlib.GameProfile;
import net.TntClient.Config;
import net.TntClient.Util;
import net.TntClient.modules.Module;
import net.TntClient.modules.render.TntGameStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HypixelPlayers {

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static final Map<UUID, PlayerInfo> playerInfoMap = new HashMap<>();
    public static List<GameProfile> players;

    public static boolean isHypixel;
    public static boolean isTntRun;

    public static volatile boolean waitKey = false;

    public static final LinkedList<Long> sendPerSecond = new LinkedList<>();

    public static void startTime() {
        new Thread(() -> {
            while (true) {
                try {
                    if ((isHypixel = isHypixel()) && (Config.config.tabDJCount.isActive() || Config.config.tabStats.isActive()
                            || Config.config.nicknameStats.isActive() || Config.config.tntGameStats.isActive())) {
                        updateStat();
                        final long to = System.currentTimeMillis() - 60000;
                        sendPerSecond.removeIf(aLong -> aLong < to);
                        if (sendPerSecond.size() > 120)
                            Thread.sleep(500);
                    } else {
                        Thread.sleep(500);
                    }
                    try {
                        isTntRun = isTntRun();
                        for (Module m : Config.modules)
                            m.setBlocking((m.onlyHypixel && !isHypixel) || (m.onlyTntGame && !isTntRun));
                    } catch (Exception ignored) {
                    }
                } catch (Exception ignored) {
                }
            }
        }).start();
    }

    public static void updateStat() {
        try {
            players = mc.thePlayer.sendQueue.getPlayerInfoMap().stream().map(NetworkPlayerInfo::getGameProfile).collect(Collectors.toList());
            final LinkedList<PlayerInfo> playerInfos = new LinkedList<>();
            for (GameProfile player : players) {
                final UUID id = player.getId();
                if (playerInfoMap.containsKey(id))
                    playerInfos.add(playerInfoMap.get(id));
                else
                    playerInfos.add(new PlayerInfo(player));
            }
            playerInfos.sort(null);
            final PlayerInfo upd = playerInfos.getFirst();
            try {
                upd.update();
            } catch (Exception ignored) {

            }
            playerInfoMap.put(upd.profile.getId(), upd);
            if (mc.thePlayer.getGameProfile().equals(upd.profile)) {
                TntGameStats.streak = upd.info.player.stats.TNTGames.winstreak;
                TntGameStats.lose = upd.info.player.stats.TNTGames.deaths_tntrun;
                TntGameStats.win = upd.info.player.stats.TNTGames.wins_tntrun;
            }
        } catch (Exception ignored) {
        }
    }

    private static boolean isHypixel() {
        final ServerData serverData = mc.getCurrentServerData();
        if (serverData == null)
            return false;
        return serverData.serverIP.toLowerCase().contains("hypixel.net");
    }

    private static boolean isTntRun() {
        if (!isHypixel) return false;
        for (ScoreObjective dd : mc.theWorld.getScoreboard().getScoreObjectives())
            if (StringUtils.stripControlCodes(dd.getDisplayName()).toLowerCase().contains("tnt run"))
                return true;
        return false;
    }

    public static void connected(final String ip) {
        if (ip.toLowerCase().contains("hypixel.net")) {
            new Thread(() -> {
                if (!isValidKey()) {
                    waitKey = true;
                    int count = 0;
                    boolean good = false;
                    while (!good && count < 10) {
                        try {
                            Thread.sleep(2000);
                            mc.thePlayer.sendChatMessage("/api new");
                            good = true;
                        } catch (Exception ex) {
                            count++;
                        }
                    }
                    if (good) {
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    waitKey = false;
                }
                if (Config.config.autoTip.isActive()) {
                    try {
                        Thread.sleep(10000);
                        mc.thePlayer.sendChatMessage("/tip all");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static boolean isValidKey() {
        if (Config.config.apiKey.isEmpty()) return false;
        try {
            if (!Util.readSite("https://api.hypixel.net/key?key=" + Config.config.apiKey).contains("Invalid API key"))
                return true;
        } catch (IOException ignored) {
        }
        return false;
    }
}
