package net.TntClient.mods.hypixel;

import com.mojang.authlib.GameProfile;
import net.TntClient.Config;
import net.TntClient.modules.render.TntGameStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HypixelPlayers {

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static boolean isHypixel = false;
    public static Map<String, PlayerInfo> playerInfoMap = new HashMap<>();
    public static List<GameProfile> players;
    public static int count = 0;

    public static void startTime() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (isHypixel && (Config.config.tabStats.isActive() || Config.config.nicknameStats.isActive() || Config.config.tntGameStats.isActive()))
                        updateStat();
                } catch (Exception ignored) {
                }
            }
        }, 0, 501);
    }

    public static void updateStat() throws IOException {
        players = Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfoMap().stream().map(NetworkPlayerInfo::getGameProfile).collect(Collectors.toList());
        final int len = players.size();
        for (int i = 0; i < len; i++) {
            final String name = players.get(i).getName();
            if (!playerInfoMap.containsKey(name))
                playerInfoMap.put(name, null);
        }

        if (count >= len)
            count = 0;

        final String name = players.get(count++).getName();
        final PlayerInfo info = new PlayerInfo(urlToText(new URL("https://api.hypixel.net/player?key=" + Config.config.apiKey + "&name=" + name)));
        playerInfoMap.put(name, info);
        if (mc.thePlayer.getGameProfile().getName().equals(name)) {
            TntGameStats.streak = info.streak;
            TntGameStats.lose = info.lose;
            TntGameStats.win = info.win;
        }
    }

    public static void connected(final String ip) {
        if (ip.toLowerCase().contains("hypixel.net")) {
            isHypixel = true;
            if (!isValidKey()) {
                new Thread(() -> {
                    int count = 0;
                    boolean good = false;
                    while (!good && count < 10) {
                        try {
                            Thread.sleep(2000);
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("/api new");
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
                        Minecraft.getMinecraft().thePlayer.sendChatMessage("/tip all");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } else {
            isHypixel = false;
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

    private static String urlToText(URL url) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        final StringBuilder buf = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buf.append(line).append('\n');
        }
        return buf.toString();
    }

}
