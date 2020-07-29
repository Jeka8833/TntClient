package net.TntClient.modules.other;

import net.TntClient.event.EventTarget;
import net.TntClient.event.events.EventReceiverMessage;
import net.TntClient.modules.Category;
import net.TntClient.modules.Module;

public class AutoGG extends Module {

    private static long delay = 0;
    private static long trotle = 0;

    public AutoGG() {
        super("Auto GG", Category.OTHER, false, true, false);
    }

    @Override
    public void onToggle() {

    }

    @Override
    public void onSetup() {

    }

    @EventTarget
    public void onMessage(EventReceiverMessage event) {
        if (trotle + 5000 > System.currentTimeMillis()) return;
        if (event.text.contains("▬▬▬▬▬▬▬▬▬▬▬▬")) delay = System.currentTimeMillis();
        else if (event.text.contains(mc.getSession().getProfile().getName()) && delay + 100 > System.currentTimeMillis()) {
            trotle = System.currentTimeMillis();
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mc.thePlayer.sendChatMessage("gg");
            }).start();
        }
    }
}
