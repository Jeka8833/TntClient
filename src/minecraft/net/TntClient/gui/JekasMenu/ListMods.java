package net.TntClient.gui.JekasMenu;

import net.TntClient.Config;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class ListMods extends GuiScreen {

    private Module[] modules = Config.config.getModList();
    private GuiTextField searchField;
    private float scroll;

    private boolean isSellectScroll = false;

    private static final int blockHeight = 18;
    private static final int width = 250;
    private static final int height = 176;

    @Override
    public void initGui() {
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        Keyboard.enableRepeatEvents(true);
        searchField = new GuiTextField(0, this.fontRendererObj, sr.getScaledWidth() / 2 - 50, sr.getScaledHeight() / 2 - height / 2 - 4, 100, this.fontRendererObj.FONT_HEIGHT);
        searchField.setMaxStringLength(32);
        searchField.setEnableBackgroundDrawing(false);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        this.searchField.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        // Сложная математика a/2 - b/2 = (a - b) / 2
        final int ScX = (sr.getScaledWidth() - width) / 2;
        final int ScY = (sr.getScaledHeight() - height) / 2;
        final int factor = sr.getScaleFactor();
        final int size = modules.length;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GlStateManager.enableBlend();
        GlStateManager.disableBlend();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        glBegin(GL_QUADS);
        glColor4f(0, 0, 0, 0.5F);
        glVertex2f(ScX, ScY + 15);
        glVertex2f(ScX + width, ScY + 15);
        glVertex2f(ScX + width, ScY + height);
        glVertex2f(ScX, ScY + height);
        glEnd();

        GL11.glColor4f(0, 0, 0, 0.5f);
        GL11.glBegin(GL11.GL_POLYGON);
        for (int i = 90; i <= 270; i++) {
            final float theta = (float) (i * Math.PI * 2 / 360);
            GL11.glVertex2f((float) (ScX + (6 * Math.cos(theta)) - 50 + width / 2), (float) (ScY + (6 * Math.sin(theta))));
        }

        for (int i = 270; i <= 450; i++) {
            final float theta = (float) (i * Math.PI * 2 / 360);
            GL11.glVertex2f((float) (ScX + (6 * Math.cos(theta)) + 50 + width / 2), (float) (ScY + (6 * Math.sin(theta))));
        }
        GL11.glEnd();
        GL11.glColor4f(1, 1, 1, 0.75f);
        glLineWidth(1.0f);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (int i = 90; i <= 270; i++) {
            final float theta = (float) (i * Math.PI * 2 / 360);
            GL11.glVertex2f((float) (ScX + (6 * Math.cos(theta)) - 50 + width / 2), (float) (ScY + (6 * Math.sin(theta))));
        }

        for (int i = 270; i <= 450; i++) {
            final float theta = (float) (i * Math.PI * 2 / 360);
            GL11.glVertex2f((float) (ScX + (6 * Math.cos(theta)) + 50 + width / 2), (float) (ScY + (6 * Math.sin(theta))));
        }
        GL11.glEnd();
        if ((size - 1) / 3 * blockHeight > height - 4) {
            /*
             * Спасибо Lukashenko
             * */
            final int fullSize = (size - 1) / 3 * blockHeight + blockHeight;
            final int curret = ((height - 16) * (height - 16)) / fullSize;
            final float track = -scroll * (height - 16) / fullSize + curret + ScY + 16;

            glBegin(GL_QUADS);
            glColor4f(1, 1, 1, 0.5f);
            glVertex2d(ScX + width - 1, track);
            glVertex2d(ScX + width - 5, track);
            glVertex2d(ScX + width - 5, track - curret);
            glVertex2d(ScX + width - 1, track - curret);
            glEnd();
        }

        GL11.glScissor(ScX * factor, ScY * factor, width * factor, (height - 14) * factor);
        GL11.glEnable(3089);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, scroll, 0.0F);
        final int whid = (size - 1) / 3 * blockHeight > height - 4 ? (width - 5) / 3 : width / 3;
        int selected = -1;
        for (int i = 0; i < size; i++) {
            final boolean isOptions = modules[i].getOptions().size() > 0;
            final int PosX = (i % 3) * whid + ScX + 2;
            final int PosY = (i / 3) * blockHeight + ScY + 17;

            if (isOptions) {
                glColor4f(1, 1, 1, 0.5f);
                glLineWidth(1.0f);
                glBegin(GL_LINES);
                glVertex2f(PosX + whid - 12, PosY);
                glVertex2f(PosX + whid - 12, PosY + blockHeight - 2);
                glEnd();
            }
            if (modules[i].isBlocking)
                glColor4f(0.5f, 0.5f, 0.5f, .5f);
            else if (modules[i].isActive())
                glColor4f(0, 1, 0, .5f);
            else
                glColor4f(1, .5f, .4f, .5f);

            glBegin(GL_QUADS);
            glVertex2f(PosX, PosY);
            glVertex2f(PosX + whid - 2, PosY);
            glVertex2f(PosX + whid - 2, PosY + blockHeight - 2);
            glVertex2f(PosX, PosY + blockHeight - 2);
            glEnd();
            if (mouseY > ScY + 13 && mouseY < ScY + height && mouseX > ScX && mouseX < ScX + width) {
                final int realY = PosY + (int) scroll;
                if (mouseX > PosX && mouseX < PosX + whid - (isOptions ? 12 : 0) && mouseY > realY && mouseY < realY + blockHeight - 2) {
                    final int x = PosX + whid - (isOptions ? 13 : 0);
                    glColor4f(0, 0, 0f, 0.25f);
                    glBegin(GL_QUADS);
                    glVertex2f(PosX, PosY);
                    glVertex2f(x, PosY);
                    glVertex2f(x, PosY + blockHeight - 2);
                    glVertex2f(PosX, PosY + blockHeight - 2);
                    glEnd();
                    selected = i;
                }
                if (isOptions && mouseX > PosX + whid - 13 && mouseX < PosX + whid - 2 && mouseY > realY && mouseY < realY + blockHeight - 2) {
                    glColor4f(0, 0, 0f, 0.25f);
                    glBegin(GL_QUADS);
                    glVertex2f(PosX + whid - 11, PosY);
                    glVertex2f(PosX + whid, PosY);
                    glVertex2f(PosX + whid, PosY + blockHeight - 2);
                    glVertex2f(PosX + whid - 11, PosY + blockHeight - 2);
                    glEnd();
                }
            }
            glColor4f(1, 1, 1, 1);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            mc.fontRendererObj.drawString(substringText((modules[i].keyBind != Integer.MAX_VALUE ? ("[" + Keyboard.getKeyName(modules[i].keyBind) + "]") : "")
                    + modules[i].getName(), whid - (isOptions ? 12 : 0) - 5), PosX + 5, PosY + (blockHeight / 2 - 5), 0xffffffff);
            if (isOptions)
                mc.fontRendererObj.drawString(">", PosX + whid - 8, PosY + (blockHeight / 2 - 5), 0xffffffff);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_TEXTURE_2D);

        }

        GL11.glPopMatrix();
        GL11.glDisable(3089);

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        if (!searchField.isFocused() && searchField.getText().equals(""))
            mc.fontRendererObj.drawString("Search...", ScX - 50 + width / 2, ScY - 4, 0x9f9f9fff);
        searchField.drawTextBox();
        if(selected != -1) {
            final Module md = modules[selected];
            if(!md.isActive()){
                if(md.onlyTntGame && !HypixelPlayers.isTntRun)
                    drawHoveringText(Collections.singletonList("Only TntRun"), mouseX, mouseY);
                else if(md.onlyHypixel && !HypixelPlayers.isHypixel)
                    drawHoveringText(Collections.singletonList("Only Hypixel"), mouseX, mouseY);
            } else if(!md.getDescription().isEmpty())
                drawHoveringText(Collections.singletonList(md.getDescription()), mouseX, mouseY);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        searchField.textboxKeyTyped(typedChar, keyCode);
        final List<Module> modules = new ArrayList<>();
        final String searchText = searchField.getText().toLowerCase();
        for (Module m : Config.config.getModList())
            if (m.getName().toLowerCase().contains(searchText))
                modules.add(m);
        this.modules = modules.toArray(new Module[0]);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (isSellectScroll)
            scroll = Math.min(0,
                    Math.max(-((modules.length - 1) / 3 * blockHeight + (17 + blockHeight - height)),
                            -(((modules.length + 2) * blockHeight * (2 * mouseY + height - new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - 32)) / (6 * (height - 16))) + height / 2 - 8));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.searchField.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
            final int ScX = (sr.getScaledWidth() - width) / 2;
            final int ScY = (sr.getScaledHeight() - height) / 2;
            if (mouseY > ScY + 13 && mouseY < ScY + height && mouseX > ScX && mouseX < ScX + width) {
                int realY = ScY + (int) scroll;
                final int whid = modules.length > 12 ? (width - 5) / 3 : width / 3;
                for (int i = 0; i < modules.length; i++) {
                    if(modules[i].isBlocking) continue;
                    final boolean isOptions = modules[i].getOptions().size() > 0;
                    final int PosX = (i % 3) * whid + ScX + 2;
                    final int PosY = (i / 3) * blockHeight + realY + 17;
                    if (mouseY > PosY && mouseY < PosY + blockHeight - 2 && mouseX > PosX && mouseX < PosX + whid - (isOptions ? 12 : 0)) {
                        modules[i].toggle();
                    }
                    if (isOptions && mouseY > PosY && mouseY < PosY + blockHeight - 2 && mouseX > PosX + whid - 13 && mouseX < PosX + whid - 2) {
                        mc.displayGuiScreen(new EditMod(modules[i]));
                    }
                }
                if (mouseX > ScX + width - 5 && mouseX < ScX + width - 1) {
                    isSellectScroll = true;
                }
            }
        } else if (mouseButton == 2) {
            final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
            final int ScX = (sr.getScaledWidth() - width) / 2;
            final int ScY = (sr.getScaledHeight() - height) / 2;
            if (mouseY > ScY + 13 && mouseY < ScY + height && mouseX > ScX && mouseX < ScX + width) {
                int realY = ScY + (int) scroll;
                final int whid = modules.length > 12 ? (width - 5) / 3 : width / 3;
                for (int i = 0; i < modules.length; i++) {
                    final int PosX = (i % 3) * whid + ScX + 2;
                    final int PosY = (i / 3) * blockHeight + realY + 17;
                    if (mouseY > PosY && mouseY < PosY + blockHeight - 2 && mouseX > PosX && mouseX < PosX + whid) {
                        mc.displayGuiScreen(new KeyBind(modules[i]));
                    }
                }
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (state == 0) isSellectScroll = false;
    }

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

    private String substringText(final String text, final int width) {
        if (mc.fontRendererObj.getStringWidth(text) < width) return text;
        final int len = text.length();
        for (int i = len; i > 0; i--) {
            final String sText = text.substring(0, i);
            if (mc.fontRendererObj.getStringWidth(sText) < width)
                return sText;
        }
        return "";
    }
}