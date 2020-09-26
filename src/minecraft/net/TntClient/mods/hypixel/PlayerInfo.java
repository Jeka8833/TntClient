package net.TntClient.mods.hypixel;

import com.google.gson.Gson;
import com.mojang.authlib.GameProfile;
import net.TntClient.Config;
import net.TntClient.Util;
import net.TntClient.mods.hypixel.parser.Info;
import net.minecraft.util.EnumChatFormatting;

import java.io.IOException;

public class PlayerInfo implements Comparable<PlayerInfo> {

    private static final Gson GSON = new Gson();

    public final GameProfile profile;
    public Info info = new Info();

    public long time = Long.MIN_VALUE;

    public PlayerInfo(GameProfile profile) {
        this.profile = profile;
    }

    public void update() throws IOException {
        time = System.currentTimeMillis();
        info = GSON.fromJson(Util.readSite("https://api.hypixel.net/player?key="
                + Config.config.apiKey + "&uuid=" + profile.getId()), Info.class);
        if(info == null || info.player == null || info.player.stats == null || info.player.stats.TNTGames == null)
            info = new Info();
        HypixelPlayers.sendPerSecond.add(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "[Win: " + info.player.stats.TNTGames.wins_tntrun + ", Lose: " + info.player.stats.TNTGames.deaths_tntrun +
                ", Streak: " + info.player.stats.TNTGames.winstreak + ", Coin: " + info.player.stats.TNTGames.coins +
                ", Jumps: " + info.player.stats.TNTGames.new_tntrun_double_jumps + "]";
    }

    public String getTabPrefix() {
        if (info.player.stats.TNTGames.wins_tntrun != 0) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.AQUA + EnumChatFormatting.ITALIC + "Wi: " +
                    EnumChatFormatting.RESET + EnumChatFormatting.AQUA + info.player.stats.TNTGames.wins_tntrun +
                    EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        } else if ( info.player.stats.TNTGames.deaths_tntrun != 0) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.GREEN + EnumChatFormatting.ITALIC + "Lo: " +
                    EnumChatFormatting.RESET + EnumChatFormatting.GREEN +  info.player.stats.TNTGames.deaths_tntrun +
                    EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        } else if (info.player.stats.TNTGames.winstreak != 0) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.LIGHT_PURPLE + EnumChatFormatting.ITALIC + "St: " +
                    EnumChatFormatting.RESET + EnumChatFormatting.LIGHT_PURPLE + info.player.stats.TNTGames.winstreak +
                    EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        } else if (info.player.stats.TNTGames.coins != 0) {
            return EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.YELLOW + EnumChatFormatting.ITALIC + "Co: " +
                    EnumChatFormatting.RESET + EnumChatFormatting.YELLOW + info.player.stats.TNTGames.coins +
                    EnumChatFormatting.DARK_RED + "]" + EnumChatFormatting.RESET;
        }
        return "";
    }

    public String nickName() {
        if (info.player.stats.TNTGames.wins_tntrun != 0) {
            return "Wins: " + info.player.stats.TNTGames.wins_tntrun;
        } else if ( info.player.stats.TNTGames.deaths_tntrun != 0) {
            return "Loses: " +  info.player.stats.TNTGames.deaths_tntrun;
        } else if (info.player.stats.TNTGames.winstreak != 0) {
            return "Streak: " + info.player.stats.TNTGames.winstreak;
        } else if (info.player.stats.TNTGames.coins != 0) {
            return "Coins: " + info.player.stats.TNTGames.coins;
        }
        return "";
    }

    @Override
    public int compareTo(PlayerInfo o) {
        return Long.compare(time, o.time);
    }
}