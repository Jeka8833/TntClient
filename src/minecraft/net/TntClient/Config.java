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
    public JumpHelper jumpHelper = new JumpHelper();
    public ShowPotions showPotions = new ShowPotions();
    public Brightness brightness = new Brightness();
    public Dolphin dolphin = new Dolphin();
    public Spider spider = new Spider();
    public Sprint sprint = new Sprint();
    public TntGameStats tntGameStats = new TntGameStats();
    public NicknameStats nicknameStats = new NicknameStats();
    public TabStats tabStats = new TabStats();
    public GlitchBlocks glitchBlocks = new GlitchBlocks();
    public AutoTip autoTip = new AutoTip();
    public LongDJump longDJump = new LongDJump();
    public DebugModule debugModule = new DebugModule();

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
        return new Module[]{config.jumpHelper, config.spider, config.longDJump, config.glitchBlocks, config.dolphin};
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
