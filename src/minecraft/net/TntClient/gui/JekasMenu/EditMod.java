package net.TntClient.gui.JekasMenu;

import net.TntClient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class EditMod extends GuiScreen {

    private final Module module;

    private static final int width = 250;
    private static final int height = 176;

    public EditMod(final Module module) {
        this.module = module;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        final String descryp = module.getDescription().isEmpty() ? "No description" : module.getDescription();
        final int ScX = (sr.getScaledWidth() - width) / 2;
        final int ScY = (sr.getScaledHeight() - height) / 2;
        final int factor = sr.getScaleFactor();


        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.enableBlend();
        GlStateManager.disableBlend();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(0, 0, 0, 0.5f);
        GL11.glVertex2f(ScX, ScY);
        GL11.glVertex2f(ScX + width, ScY);
        GL11.glVertex2f(ScX + width, ScY + height);
        GL11.glVertex2f(ScX, ScY + height);
        GL11.glEnd();

        GL11.glColor4f(1, 1, 1, 1);
        glBegin(GL_LINES);
        glVertex2f(ScX, ScY + 12);
        glVertex2f(ScX + width, ScY + 12);

        glVertex2f(ScX + 10, ScY);
        glVertex2f(ScX + 10, ScY + 12);
        glEnd();

        int posY = ScY + 15;

        for(Component component : module.getOptions()){
            component.posX = ScX;
            component.posY = (posY += component.height);
            component.width = width;
            component.drawScreen();
        }

        glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        mc.fontRendererObj.drawString(module.getName(), ScX + 14, ScY + 2, 0xffffffff);
        mc.fontRendererObj.drawString("<", ScX + 2, ScY + 2, 0xffffffff);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {

    }
}
