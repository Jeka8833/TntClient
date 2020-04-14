package net.TntClient.mods.translate;

import net.TntClient.Config;
import net.TntClient.mods.Language;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class GuiGoogleTranslate extends GuiScreen {


    private int sellected = 0;
    private List list;

    public GuiGoogleTranslate() {

    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui() {
        this.list = new List(this.mc);
        this.buttonList.add(new GuiOptionButton(101, this.width / 2 - 155 + 160, this.height - 38, I18n.format("gui.done")));
        this.buttonList.add(new GuiOptionButton(102, this.width / 2 - 155, this.height - 38, I18n.format("gui.cancel")));
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button.id == 101) {
                Config.config.googleLang = list.lang[sellected];
                Config.write();
                mc.displayGuiScreen(null);
            } else if (button.id == 102)
                mc.displayGuiScreen(null);
            this.list.actionPerformed(button);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRendererObj, "Choose a language for translation", this.width / 2, 16, 16777215);
        this.drawCenteredString(this.fontRendererObj, "This is the language into which everything will be translated.", this.width / 2, this.height - 56, 8421504);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    class List extends GuiSlot {

        public Language[] lang;

        public List(Minecraft mcIn) {
            super(mcIn, GuiGoogleTranslate.this.width, GuiGoogleTranslate.this.height, 32, GuiGoogleTranslate.this.height - 65 + 4, 18);
            lang = TranslateGoogle.lang;
            for (int i = 0; i < lang.length; i++)
                if (Config.config.googleLang.getCode().equals(lang[i].getCode()))
                    sellected = i;
        }

        @Override
        protected int getSize() {
            return lang.length;
        }

        @Override
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
            sellected = slotIndex;
        }

        @Override
        protected boolean isSelected(int slotIndex) {
            return slotIndex == sellected;
        }

        @Override
        protected void drawBackground() {
            GuiGoogleTranslate.this.drawDefaultBackground();
        }

        @Override
        protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int mouseXIn, int mouseYIn) {
            GuiGoogleTranslate.this.drawCenteredString(GuiGoogleTranslate.this.fontRendererObj, lang[entryID].getName(), this.width / 2, p_180791_3_ + 1, 16777215);
        }
    }

}
