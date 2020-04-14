package net.TntClient.mods.hypixel;

import net.TntClient.Config;
import net.minecraft.client.Minecraft;

import java.util.Timer;
import java.util.TimerTask;

public class AutoTip {

    public static void startTime(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (HypixelPlayers.isHypixel && (Config.config.autoTip.isActive())) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/tip all");
                }
            }
        }, 0, 600000);
    }

}
