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

    public static void startTime() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (isHypixel && (Config.config.tabStats.isActive() || Config.config.nicknameStats.isActive() || Config.config.tntGameStats.isActive())) {
                        updateStat();
                        if(mc.getCurrentServerData() == null)
                            isHypixel = false;
                    }
                } catch (Exception ignored) {
                }
            }
        }, 0, 501);
    }

    public static void updateStat() throws IOException {
        players = mc.thePlayer.sendQueue.getPlayerInfoMap().stream().map(NetworkPlayerInfo::getGameProfile).collect(Collectors.toList());
        final LinkedList<PlayerInfo> playerInfos = new LinkedList<>();
        for (GameProfile player : players) {
            final String name = player.getName();
            if (playerInfoMap.containsKey(name))
                playerInfos.add(playerInfoMap.get(name));
            else
                playerInfos.add(new PlayerInfo(name));
        }
        Collections.sort(playerInfos);
        final PlayerInfo upd = playerInfos.getFirst();
        upd.update();
        playerInfoMap.put(upd.name, upd);
        if (mc.thePlayer.getGameProfile().getName().equals(upd.name)) {
            TntGameStats.streak = upd.streak;
            TntGameStats.lose = upd.lose;
            TntGameStats.win = upd.win;
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
