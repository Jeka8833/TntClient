package net.TntClient.modules.render;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.Event2D;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

public class TntGameStats extends Module {

    public static int win = Integer.MIN_VALUE;
    public static int lose = Integer.MIN_VALUE;
    public static int streak = Integer.MIN_VALUE;

    public TntGameStats() {
        super("TntGame stats", Category.RENDER);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onUpdate(Event2D event) {
        if(!mc.gameSettings.showDebugInfo) {
            if(win != Integer.MIN_VALUE){
                final String line = "Wins: " +  win;
                mc.fontRendererObj.drawString(line, (int) (event.getWidth() - mc.fontRendererObj.getStringWidth(line)), 2, -2039584);
            }
            if(lose != Integer.MIN_VALUE){
                final String line = "Loses: " +  lose;
                mc.fontRendererObj.drawString(line, (int) (event.getWidth() - mc.fontRendererObj.getStringWidth(line)), 12, -2039584);
            }
            if(streak != Integer.MIN_VALUE){
                final String line = "Streak: " +  streak;
                mc.fontRendererObj.drawString(line, (int) (event.getWidth() - mc.fontRendererObj.getStringWidth(line)), 22, -2039584);
            }
        }
    }
}
