package net.TntClient.mods.hypixel;

import com.mojang.authlib.GameProfile;
import net.TntClient.Config;
import net.TntClient.modules.Module;
import net.TntClient.modules.render.TntGameStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HypixelPlayers {

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static Map<String, PlayerInfo> playerInfoMap = new HashMap<>();
    public static List<GameProfile> players;

    public static boolean isHypixel;
    public static boolean isTntRun;


    public static void startTime() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if ((isHypixel = isHypixel()) && (Config.config.tabStats.isActive() || Config.config.nicknameStats.isActive() || Config.config.tntGameStats.isActive()))
                    updateStat();
                try {
                    isTntRun = isTntRun();
                    for (Module m : Config.config.getModList())
                        m.setBlocking((m.onlyHypixel && !isHypixel) || (m.onlyTntGame && !isTntRun));
                } catch (Exception exception) {

                }
            }
        }, 0, 501);
    }

    public static void updateStat() {
        try {
            players = mc.thePlayer.sendQueue.getPlayerInfoMap().stream().map(NetworkPlayerInfo::getGameProfile).collect(Collectors.toList());
            final LinkedList<PlayerInfo> playerInfos = new LinkedList<>();
            for (GameProfile player : players) {
                final String name = player.getName();
                if (playerInfoMap.containsKey(name))
                    playerInfos.add(playerInfoMap.get(name));
                else
                    playerInfos.add(new PlayerInfo(player));
            }
            playerInfos.sort(null);
            final PlayerInfo upd = playerInfos.getFirst();
            upd.update();
            playerInfoMap.put(upd.profile.getName(), upd);
            if (mc.thePlayer.getGameProfile().equals(upd.profile)) {
                TntGameStats.streak = upd.streak;
                TntGameStats.lose = upd.lose;
                TntGameStats.win = upd.win;
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
        if(!isHypixel) return false;
        for (ScoreObjective dd : mc.theWorld.getScoreboard().getScoreObjectives())
            if (StringUtils.stripControlCodes(dd.getDisplayName()).toLowerCase().contains("tnt run"))
                return true;
        return false;
    }

    public static void connected(final String ip) {
        if (ip.toLowerCase().contains("hypixel.net")) {
            if (!isValidKey()) {
                new Thread(() -> {
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
                        count = 0;
                        while (count < 10) {
                            try {
                                Thread.sleep(2000);
                                for (ChatLine line : Minecraft.getMinecraft().ingameGUI.getChatGUI().chatLines) {
                                    final String text = StringUtils.stripControlCodes(line.getChatComponent().getUnformattedText()).toLowerCase();
                                    if (text.contains("new api key")) {
                                        Config.config.apiKey = text.substring(text.indexOf(" is ") + 4).trim();
                                        Config.write();
                                        return;
                                    }
                                }
                            } catch (Exception ex) {
                                count++;
                            }
                        }
                    }
                }).start();
            }
            if (Config.config.autoTip.isActive()) {
                new Thread(() -> {
                    try {
                        Thread.sleep(10000);
                        mc.thePlayer.sendChatMessage("/tip all");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    private static boolean isValidKey() {
        if (Config.config.apiKey.isEmpty())
            return false;
        try {
            if (!urlToText(new URL("https://api.hypixel.net/key?key=" + Config.config.apiKey)).contains("Invalid API key"))
                return true;
        } catch (IOException ignored) {
        }
        return false;
    }

    public static String urlToText(URL url) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        final StringBuilder buf = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buf.append(line).append('\n');
        }
        return buf.toString();
    }

}
