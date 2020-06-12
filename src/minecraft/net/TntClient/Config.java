package net.TntClient;

import com.google.gson.Gson;
import net.TntClient.mods.Language;
import net.TntClient.mods.translate.TranslateGoogle;
import net.TntClient.modules.DebugModule;
import net.TntClient.modules.Module;
import net.TntClient.modules.block.GlitchBlocks;
import net.TntClient.modules.movement.*;
import net.TntClient.modules.other.AutoTip;
import net.TntClient.modules.render.*;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.file.Files;

public class Config {
    public final Bot bot = new Bot();
    public final ShowPotions showPotions = new ShowPotions();
    public final Brightness brightness = new Brightness();
    public final Dolphin dolphin = new Dolphin();
    public final Sprint sprint = new Sprint();
    public final TntGameStats tntGameStats = new TntGameStats();
    public final NicknameStats nicknameStats = new NicknameStats();
    public final TabStats tabStats = new TabStats();
    public final GlitchBlocks glitchBlocks = new GlitchBlocks();
    public final AutoTip autoTip = new AutoTip();
    public final LongDJump longDJump = new LongDJump();
    public final DebugModule debugModule = new DebugModule();
    public final FreeDJ freeDJ = new FreeDJ();

    public Language googleLang = TranslateGoogle.lang[3];
    public String apiKey = "";

    public static Config config = new Config();

    public static void read() {
        try {
            config = new Gson().fromJson(new String(Files.readAllBytes(Minecraft.getMinecraft().mcDataDir.toPath().resolve("HC3Config.json"))), Config.class);
        } catch (Exception e) {
            write();
        }
    }

    public static void write() {
        try {
            Files.write(Minecraft.getMinecraft().mcDataDir.toPath().resolve("HC3Config.json"), new Gson().toJson(config).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Module[] getDangerMods() {
        return new Module[]{config.bot, config.longDJump, config.glitchBlocks, config.dolphin, config.freeDJ};
    }

    public Module[] getPussyMods() {
        return new Module[]{config.showPotions, config.brightness, config.sprint, config.tntGameStats,
                config.nicknameStats, config.tabStats, config.autoTip};
    }

    public Module[] getModList() {
        if (TntClient.pussy) {
            return getPussyMods();
        } else {
            if (TntClient.isDebug)
                return ArrayUtils.addAll(ArrayUtils.addAll(getPussyMods(), getDangerMods()), config.debugModule);
            else
                return ArrayUtils.addAll(getPussyMods(), getDangerMods());
        }
    }
}
