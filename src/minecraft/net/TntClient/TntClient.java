package net.TntClient;

import net.TntClient.event.EventManager;
import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.event.events.EventKey;
import net.TntClient.event.events.EventReceiverMessage;
import net.TntClient.gui.JekasMenu.ListMods;
import net.TntClient.mods.SpellCecker.SpellChecker;
import net.TntClient.mods.hypixel.AutoTip;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.mods.hypixel.PlayerInfo;
import net.TntClient.mods.translate.TranslateGoogle;
import net.TntClient.modules.Module;
import net.TntClient.modules.render.TabDJCount;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import org.lwjgl.input.Keyboard;

import java.util.Map;
import java.util.UUID;

public class TntClient {

    public static final String version = "1.0.11";

    public static boolean isDebug = false;

    public static final EventManager eventManager = new EventManager();
    private final Minecraft mc = Minecraft.getMinecraft();

    public TntClient() {
        AutoTip.startTime();
        HypixelPlayers.startTime();
        SpellChecker.startTimer();
        TranslateGoogle.startTime();
        Config.read();
        for (Module module : Config.modules) {
            if (module.isActive())
                module.onEnable();
        }
        eventManager.register(this);
    }

    @EventTarget
    public void onDraw(Event2D event) {
        for (Module m : Config.modules)
            if (m.isDanger && m.isActive()) {
                mc.fontRendererObj.drawString("This can l" +
                        "ead to a ban!!!", 2, (int) (event.getHeight() - 10), Util.getRainbow());
                return;
            }
    }

    @EventTarget
    public void onUpdateKey(EventKey event) {
        if (event.getKey() == Keyboard.KEY_RSHIFT)
            mc.displayGuiScreen(new ListMods());
        else if (mc.currentScreen == null)
            for (Module m : Config.modules)
                if (!m.isBlocking && m.keyBind == event.getKey())
                    m.toggle();
    }

    @EventTarget
    public void onMessage(EventReceiverMessage event) {
        if (HypixelPlayers.waitKey && event.text.toLowerCase().contains("new api key")) {
            Config.config.apiKey = event.text.substring(event.text.indexOf(" is ") + 4).trim();
            Config.write();
            event.setCancelled(true);
            HypixelPlayers.waitKey = false;
        }
    }

    @EventTarget
    public void onUpdate(Event2D event) {
        if (Config.config.tabDJCount.isActive()) {
            if (mc.currentScreen instanceof GuiDownloadTerrain)
                TabDJCount.jumpCount.clear();
        }
        final long time = System.currentTimeMillis() - 20 * 60 * 1000;
        for (Map.Entry<UUID, PlayerInfo> playerInfo : HypixelPlayers.playerInfoMap.entrySet()) {
            if (playerInfo.getValue().time < time)
                HypixelPlayers.playerInfoMap.remove(playerInfo.getKey());
        }
    }
}