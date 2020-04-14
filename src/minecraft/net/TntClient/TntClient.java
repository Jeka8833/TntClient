package net.TntClient;

import net.TntClient.event.EventManager;
import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.event.events.EventKey;
import net.TntClient.gui.JekasMenu.ListMods;
import net.TntClient.mods.SpellCecker.SpellChecker;
import net.TntClient.mods.hypixel.AutoTip;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.mods.translate.TranslateGoogle;
import net.TntClient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class TntClient {

    public static final String version = "1.0.4";
    public static final boolean pussy = false;

    private static final Minecraft mc = Minecraft.getMinecraft();
    public static EventManager eventManager = new EventManager();
    private boolean keyPress = false;

    public TntClient() {
        AutoTip.startTime();
        HypixelPlayers.startTime();
        SpellChecker.startTimer();
        TranslateGoogle.startTime();
        Config.read();
        for (Module module : Config.config.getModList()) {
            if (module.isActive())
                module.onEnable();
        }
        if(pussy) {
            for (Module module : Config.config.getDangerMods()) {
                module.setActive(false);
                module.onDisable();
            }
        }
        eventManager.register(this);
    }

    @EventTarget
    public void onDraw(Event2D event) {
        if(!pussy) {
            if (mc.currentScreen == null) {
                if (GameSettings.isKeyDown(mc.gameSettings.jumpHelper)) {
                    if (!keyPress) {
                        keyPress = true;
                        Config.config.jumpHelper.toggle();
                    }
                } else keyPress = false;
            }
            if (Config.config.jumpHelper.isActive() || Config.config.spider.isActive())
                mc.fontRendererObj.drawString("This can lead to a ban!!!", 2, (int) (event.getHeight() - 10), Util.getRainbow());
        }
    }

    @EventTarget
    public void onUpdateKey(EventKey event) {
        if (event.getKey() == 54) {
            mc.displayGuiScreen(new ListMods());
        }
    }
}