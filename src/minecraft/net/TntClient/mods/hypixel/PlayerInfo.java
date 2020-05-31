package net.TntClient.mods.hypixel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import net.TntClient.Config;
import net.minecraft.util.EnumChatFormatting;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlayerInfo implements Comparable<PlayerInfo> {

    private static final JsonParser parser = new JsonParser();

    public final GameProfile profile;

    public int win = Integer.MIN_VALUE;
    public int lose = Integer.MIN_VALUE;
    public int streak = Integer.MIN_VALUE;
    public int coin = Integer.MIN_VALUE;
    public int jump = Integer.MIN_VALUE;

    public long time = Long.MIN_VALUE;

    public PlayerInfo(GameProfile profile) {
        this.profile = profile;
    }

    public void update() throws IOException {
        time = System.currentTimeMillis();
        final JsonObject tntgame = parser.parse(HypixelPlayers.urlToText(new URL("https://api.hypixel.net/player?key="
                + Config.config.apiKey + "&name=" + profile.getName()))).getAsJsonObject().getAsJsonObject("player")
                .getAsJsonObject("stats").getAsJsonObject("TNTGames");
        win = getInt(tntgame.get("wins_tntrun"));
        lose = getInt(tntgame.get("deaths_tntrun"));
        streak = getInt(tntgame.get("winstreak"));
        coin = getInt(tntgame.get("coins"));
        jump = getInt(tntgame.get("assists_capture"));
    }

    @Override
    public String toString() {
        return "[Win: " + win + ", Lose: " + lose + ", Streak: " + streak + ", Coin: " + coin + ", Jumps: " + jump + "]";
    }

    private int getInt(final JsonElement element) {
        if (element != null)
            return element.getAsInt();
        return Integer.MIN_VALUE;
    }

    public String getTabPrefix() {
        if (win != Integer.MIN_VALUE) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.AQUA + EnumChatFormatting.ITALIC + "Wi: " + EnumChatFormatting.RESET + EnumChatFormatting.AQUA + win + EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        } else if (lose != Integer.MIN_VALUE) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.GREEN + EnumChatFormatting.ITALIC + "Lo: " + EnumChatFormatting.RESET + EnumChatFormatting.GREEN + lose + EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        } else if (streak != Integer.MIN_VALUE) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.LIGHT_PURPLE + EnumChatFormatting.ITALIC + "St: " + EnumChatFormatting.RESET + EnumChatFormatting.LIGHT_PURPLE + streak + EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        } else if (jump != Integer.MIN_VALUE) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.GRAY + EnumChatFormatting.ITALIC + "Ju: " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + jump + EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        } else if (coin != Integer.MIN_VALUE) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.YELLOW + EnumChatFormatting.ITALIC + "Co: " + EnumChatFormatting.RESET + EnumChatFormatting.YELLOW + coin + EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        }
        return "";
    }

    public String nickName() {
        if (win != Integer.MIN_VALUE) {
            return "Wins: " + win;
        } else if (lose != Integer.MIN_VALUE) {
            return "Loses: " + lose;
        } else if (streak != Integer.MIN_VALUE) {
            return "Streak: " + streak;
        } else if (jump != Integer.MIN_VALUE) {
            return "Jump: " + jump;
        } else if (coin != Integer.MIN_VALUE) {
            return "Coins: " + coin;
        }
        return "";
    }

    @Override
    public int compareTo(PlayerInfo o) {
        return Long.compare(time, o.time);
    }
}