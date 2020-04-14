package net.TntClient.gui.JekasMenu;

import net.TntClient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class EditMod extends GuiScreen {

    private float scroll;
    private boolean isSellectScroll;
    private final Module module;

    public EditMod(final Module module) {
        this.module = module;
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        // Сложная математика a/2 - b/2 = (a - b) / 2
        final int ScX = (sr.getScaledWidth() - width) / 2;
        final int ScY = (sr.getScaledHeight() - height) / 2;
        final int factor = sr.getScaleFactor();


        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.enableBlend();
        GlStateManager.disableBlend();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(0, 0, 0, 0.5F);
        GL11.glVertex2f(ScX, ScY);
        GL11.glVertex2f(ScX + width, ScY );
        GL11.glVertex2f(ScX + width, ScY + height);
        GL11.glVertex2f(ScX, ScY + height);
        GL11.glEnd();

        GL11.glScissor(ScX * factor, ScY * factor, width * factor, (height - 14) * factor);
        GL11.glEnable(3089);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, scroll, 0.0F);





        GL11.glPopMatrix();
        GL11.glDisable(3089);

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
    }

/*
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (isSellectScroll)
            scroll = Math.min(0,
                    Math.max(-((modules.length - 1) / 3 * blockHeight + (17 + blockHeight - height)),
                            -(((modules.length + 2) * blockHeight * (2 * mouseY + height - new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - 32)) / (6 * (height - 16))) + height / 2 - 8));
    }
*/
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
            final int ScX = (sr.getScaledWidth() - width) / 2;
            final int ScY = (sr.getScaledHeight() - height) / 2;
            if (mouseY > ScY + 13 && mouseY < ScY + height && mouseX > ScX && mouseX < ScX + width) {

                if (mouseX > ScX + width - 5 && mouseX < ScX + width - 1) {
                    isSellectScroll = true;
                }
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (state == 0) isSellectScroll = false;
    }
/*
    @Override
    public void handleMouseInput() throws IOException {
        if (modules.length > 12) {
            assert mc.currentScreen != null;
            final int i = Mouse.getEventDWheel();
            if (i != 0) {
                scroll += i / 120.0f * 5;
                if (scroll > 0)
                    scroll = 0;
                else {
                    final int itemLen = -((modules.length - 1) / 3 * blockHeight + (17 + blockHeight - height));
                    if (scroll < itemLen)
                        scroll = itemLen;
                }
            }
        }
        super.handleMouseInput();
    }
*/
}
