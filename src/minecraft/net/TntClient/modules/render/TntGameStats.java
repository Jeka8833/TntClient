package net.TntClient.modules.render;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

import java.time.LocalTime;

public class TntGameStats extends Module {

    public static int win = Integer.MIN_VALUE;
    public static int lose = Integer.MIN_VALUE;
    public static int streak = Integer.MIN_VALUE;

    public TntGameStats() {
        super("TntGame stats", Category.RENDER, false, false, false);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(Event2D event) {
        if (!mc.gameSettings.showDebugInfo) {
            int posX = -8;
            final LocalTime time = LocalTime.now();
            final String line3 = "Time: " + addNull(time.getHour()) + ":" + addNull(time.getMinute());
            mc.fontRendererObj.drawString(line3, (int) (event.getWidth() - mc.fontRendererObj.getStringWidth(line3)), posX += 10, -2039584);
            if (HypixelPlayers.isHypixel) {
                final String line = "Wins: " + toNull(win);
                mc.fontRendererObj.drawString(line, (int) (event.getWidth() - mc.fontRendererObj.getStringWidth(line)), posX += 10, -2039584);
                final String line1 = "Loses: " + toNull(lose);
                mc.fontRendererObj.drawString(line1, (int) (event.getWidth() - mc.fontRendererObj.getStringWidth(line1)), posX += 10, -2039584);
                final String line2 = "Streak: " + toNull(streak);
                mc.fontRendererObj.drawString(line2, (int) (event.getWidth() - mc.fontRendererObj.getStringWidth(line2)), posX + 10, -2039584);
            }
        }
    }

    private static String addNull(final int num) {
        if (num > 9)
            return "" + num;
        return "0" + num;
    }

    private static int toNull(int in) {
        if (in == Integer.MIN_VALUE)
            return 0;
        return in;
    }
}
