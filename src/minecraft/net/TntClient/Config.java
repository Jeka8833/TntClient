package net.TntClient;

import com.google.gson.Gson;
import net.TntClient.mods.Language;
import net.TntClient.mods.translate.TranslateGoogle;
import net.TntClient.modules.DebugModule;
import net.TntClient.modules.Module;
import net.TntClient.modules.block.GlitchBlocks;
import net.TntClient.modules.movement.*;
import net.TntClient.modules.other.AutoGG;
import net.TntClient.modules.other.AutoTip;
import net.TntClient.modules.render.*;
import net.minecraft.client.Minecraft;

import java.nio.file.Files;
import java.nio.file.Path;

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
    public final TabDJCount tabDJCount = new TabDJCount();
    public final AutoGG autoGG = new AutoGG();

    public Language googleLang = TranslateGoogle.lang[3];
    public String apiKey = "";

    public static Config config = new Config();
    public static Module[] modules;

    private static final Gson GSON = new Gson();
    private static final Path path = Minecraft.getMinecraft().mcDataDir.toPath().resolve("HC3Config.json");

    public static void read() {
        try {
            config = GSON.fromJson(new String(Files.readAllBytes(path)), Config.class);
            setModules();
        } catch (Exception e) {
            write();
            setModules();
        }
    }

    public static void write() {
        try {
            Files.write(path, GSON.toJson(config).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setModules() {
        if (TntClient.isDebug)
            modules = new Module[]{config.bot, config.longDJump, config.glitchBlocks, config.dolphin, config.freeDJ, config.showPotions, config.brightness, config.sprint, config.tntGameStats,
                    config.nicknameStats, config.tabStats, config.autoTip, config.tabDJCount, config.autoGG, config.debugModule};
        else
            modules = new Module[]{config.bot, config.longDJump, config.glitchBlocks, config.dolphin, config.freeDJ, config.showPotions, config.brightness, config.sprint, config.tntGameStats,
                    config.nicknameStats, config.tabStats, config.autoTip, config.tabDJCount, config.autoGG};
    }
}
