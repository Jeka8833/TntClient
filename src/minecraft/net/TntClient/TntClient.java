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
import org.lwjgl.input.Keyboard;

public class TntClient {

    public static final String version = "1.0.5";
    public static final boolean pussy = false;

    public static boolean isDebug = false;

    public static final EventManager eventManager = new EventManager();
    private final Minecraft mc = Minecraft.getMinecraft();

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
        if (pussy) {
            for (Module module : Config.config.getDangerMods()) {
                module.setActive(false);
                module.onDisable();
            }
        }
        eventManager.register(this);
    }

    @EventTarget
    public void onDraw(Event2D event) {
        for (Module m : Config.config.getModList())
            if (m.isDanger && m.isActive()) {
                mc.fontRendererObj.drawString("This can lead to a ban!!!", 2, (int) (event.getHeight() - 10), Util.getRainbow());
                return;
            }
    }

    @EventTarget
    public void onUpdateKey(EventKey event) {
        if (event.getKey() == Keyboard.KEY_RSHIFT)
            mc.displayGuiScreen(new ListMods());
        else if (mc.currentScreen == null)
            for (Module m : Config.config.getModList())
                if (m.keyBind == event.getKey())
                    m.toggle();
    }
}