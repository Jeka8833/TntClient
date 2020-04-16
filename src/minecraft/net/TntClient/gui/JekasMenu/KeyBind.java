package net.TntClient.gui.JekasMenu;

import net.TntClient.Config;
import net.TntClient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class KeyBind extends GuiScreen {

    private static final int width = 95;
    private static final int height = 46;

    private final Module module;

    public KeyBind(Module module) {
        this.module = module;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        final int ScX = (sr.getScaledWidth() - width) / 2;
        final int ScY = (sr.getScaledHeight() - height) / 2;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.enableBlend();
        GlStateManager.disableBlend();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        glBegin(GL_QUADS);
        glColor4f(0, 0, 0, 0.5F);
        glVertex2f(ScX, ScY);
        glVertex2f(ScX + width, ScY);
        glVertex2f(ScX + width, ScY + height);
        glVertex2f(ScX, ScY + height);
        glEnd();

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        mc.fontRendererObj.drawString("Key bind", ScX + (width - mc.fontRendererObj.getStringWidth("Key bind")) / 2, ScY + 2, -1);
        if ((System.currentTimeMillis() / 1000) % 2 == 0)
            mc.fontRendererObj.drawString("Press any key", ScX + (width - mc.fontRendererObj.getStringWidth("Press any key")) / 2, ScY + 14, -1);
        mc.fontRendererObj.drawString("ESC to close menu", ScX + (width - mc.fontRendererObj.getStringWidth("ESC to close menu")) / 2, ScY + 26, -1);
        mc.fontRendererObj.drawString("DEL to reset bind", ScX + (width - mc.fontRendererObj.getStringWidth("DEL to reset bind")) / 2, ScY + 36, -1);

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode != Keyboard.KEY_ESCAPE) {
            if (keyCode == Keyboard.KEY_DELETE) {
                module.keyBind = Integer.MAX_VALUE;
            } else {
                module.keyBind = keyCode;
            }
        }
        Config.write();
        mc.displayGuiScreen(null);
    }
}
