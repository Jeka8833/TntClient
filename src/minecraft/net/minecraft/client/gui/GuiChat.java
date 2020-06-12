package net.minecraft.client.gui;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

import net.TntClient.mods.SpellCecker.SpellChecker;
import net.TntClient.mods.SpellCecker.Word;
import net.TntClient.mods.translate.GuiGoogleTranslate;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiChat extends GuiScreen {
    private static final Logger logger = LogManager.getLogger();
    private String historyBuffer = "";

    /**
     * keeps position of which chat message you will select when you press up, (does not increase for duplicated
     * messages sent immediately after each other)
     */
    private int sentHistoryCursor = -1;
    private boolean playerNamesFound;
    private boolean waitingOnAutocomplete;
    private int autocompleteIndex;
    private final List<String> foundPlayerNames = Lists.newArrayList();

    /**
     * Chat entry field
     */
    protected GuiTextField inputField;

    /**
     * is the text that appears when you press the chat key and the input box appears pre-filled
     */
    private String defaultInputFieldText = "";

    private int menu = -1;
    private int scroll = 0;

    public GuiChat() {
    }

    public GuiChat(String defaultText) {
        this.defaultInputFieldText = defaultText;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.sentHistoryCursor = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
        this.inputField = new GuiTextField(0, this.fontRendererObj, 4, this.height - 12, this.width - 4, 12);
        this.inputField.setMaxStringLength(100);
        this.inputField.setEnableBackgroundDrawing(false);
        this.inputField.setFocused(true);
        this.inputField.setText(this.defaultInputFieldText);
        this.inputField.setCanLoseFocus(false);
        buttonList.add(new GuiButton(0, this.width - 14, height - 28, 12, 12, "..."));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled && button.id == 0) {
            mc.displayGuiScreen(new GuiGoogleTranslate());
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        this.mc.ingameGUI.getChatGUI().resetScroll();
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen() {
        this.inputField.updateCursorCounter();
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.waitingOnAutocomplete = false;

        if (keyCode == 15) {
            this.autocompletePlayerNames();
        } else {
            this.playerNamesFound = false;
        }

        if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
        } else if (keyCode != 28 && keyCode != 156) {
            if (keyCode == 200) {
                this.getSentHistory(-1);
            } else if (keyCode == 208) {
                this.getSentHistory(1);
            } else if (keyCode == 201) {
                this.mc.ingameGUI.getChatGUI().scroll(this.mc.ingameGUI.getChatGUI().getLineCount() - 1);
            } else if (keyCode == 209) {
                this.mc.ingameGUI.getChatGUI().scroll(-this.mc.ingameGUI.getChatGUI().getLineCount() + 1);
            } else {
                this.inputField.textboxKeyTyped(typedChar, keyCode);
            }
        } else {
            String s = this.inputField.getText().trim();

            if (s.length() > 0) {
                this.sendChatMessage(s);
            }

            this.mc.displayGuiScreen(null);
        }
        SpellChecker.setText(inputField.getText());
        menu = -1;
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();

        if (i != 0) {
            if(menu != -1){
                final List<Word> words = SpellChecker.getWords();
                if(words.size() > menu) {
                    scroll += i / 120;
                    if (scroll < 0)
                        scroll = 0;
                    final int endItem = words.get(menu).getWords().size() - 6;
                    if (scroll > endItem)
                        scroll = endItem;
                }
            }else {
                if (i > 1) {
                    i = 1;
                }

                if (i < -1) {
                    i = -1;
                }

                if (!isShiftKeyDown()) {
                    i *= 7;
                }

                this.mc.ingameGUI.getChatGUI().scroll(i);
            }
        }
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        try {
            if (mouseButton == 0) {
                if (menu != -1) {
                    final List<Word> words = SpellChecker.getWords();
                    if (words.size() > menu) {
                        final Word word = words.get(menu);
                        final String text = inputField.getText();
                        if (word.getIndex() + word.getLength() < text.length()) {
                            final int scrolledIndex = Math.max(inputField.lineScrollOffset, word.getIndex());
                            final int posX = mc.fontRendererObj.getStringWidth(text.substring(inputField.lineScrollOffset, scrolledIndex)) + 4;
                            final List<String> fills = word.getWords();
                            final int fillsLen = fills.size();
                            int maxSize = 0;
                            for (int j = 0; j < fillsLen; j++)
                                maxSize = Math.max(maxSize, mc.fontRendererObj.getStringWidth(fills.get(j)) + 4);
                            int textY = height - 24;
                            for (int a = scroll; a < fillsLen; a++) {
                                if (scroll + 5 < a)
                                    break;
                                if (posX - 2 < mouseX && posX - 2 + maxSize > mouseX && textY < mouseY && textY + 11 > mouseY)
                                    inputField.setText(text.substring(0, word.getIndex()) + fills.get(a) + text.substring(word.getIndex() + word.getLength()));

                                textY -= 10;
                            }

                        }
                    }
                    menu = -1;
                }
                if (this.handleComponentClick(mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY()))) {
                    return;
                }


            } else if (mouseButton == 1) {
                final List<Word> words = SpellChecker.getWords();
                final String text = inputField.getText();
                for (int i = 0; i < words.size(); i++) {
                    final Word word = words.get(i);
                    if (word.getIndex() + word.getLength() < text.length()) {
                        final int scrolledIndex = Math.max(inputField.lineScrollOffset, word.getIndex());
                        final int scrollIndexedLen = Math.max(inputField.lineScrollOffset, word.getIndex() + word.getLength());
                        if (scrolledIndex == scrollIndexedLen)
                            continue;
                        final int posX = mc.fontRendererObj.getStringWidth(text.substring(inputField.lineScrollOffset, scrolledIndex)) + 4;
                        final int width = mc.fontRendererObj.getStringWidth(text.substring(scrolledIndex, scrollIndexedLen));
                        if (posX < mouseX && posX + width > mouseX && height - 14 < mouseY && height - 2 > mouseY) {
                            menu = i;
                            scroll = 0;
                        }
                    }
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        this.inputField.mouseClicked(mouseX, mouseY, mouseButton);
        SpellChecker.setText(inputField.getText());

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * Sets the text of the chat
     */
    protected void setText(String newChatText, boolean shouldOverwrite) {
        if (shouldOverwrite) {
            this.inputField.setText(newChatText);
        } else {
            this.inputField.writeText(newChatText);
        }
    }

    public void autocompletePlayerNames() {
        if (this.playerNamesFound) {
            this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());

            if (this.autocompleteIndex >= this.foundPlayerNames.size()) {
                this.autocompleteIndex = 0;
            }
        } else {
            int i = this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false);
            this.foundPlayerNames.clear();
            this.autocompleteIndex = 0;
            String s = this.inputField.getText().substring(i).toLowerCase();
            String s1 = this.inputField.getText().substring(0, this.inputField.getCursorPosition());
            this.sendAutocompleteRequest(s1);

            if (this.foundPlayerNames.isEmpty()) {
                return;
            }

            this.playerNamesFound = true;
            this.inputField.deleteFromCursor(i - this.inputField.getCursorPosition());
        }

        if (this.foundPlayerNames.size() > 1) {
            StringBuilder stringbuilder = new StringBuilder();

            for (String s2 : this.foundPlayerNames) {
                if (stringbuilder.length() > 0) {
                    stringbuilder.append(", ");
                }

                stringbuilder.append(s2);
            }

            this.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(stringbuilder.toString()), 1);
        }

        this.inputField.writeText(this.foundPlayerNames.get(this.autocompleteIndex++));
    }

    private void sendAutocompleteRequest(String p_146405_1_) {
        if (p_146405_1_.length() >= 1) {
            BlockPos blockpos = null;

            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                blockpos = this.mc.objectMouseOver.getBlockPos();
            }

            this.mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(p_146405_1_, blockpos));
            this.waitingOnAutocomplete = true;
        }
    }

    /**
     * input is relative and is applied directly to the sentHistoryCursor so -1 is the previous message, 1 is the next
     * message from the current cursor position
     */
    public void getSentHistory(int msgPos) {
        int i = this.sentHistoryCursor + msgPos;
        int j = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
        i = MathHelper.clamp_int(i, 0, j);

        if (i != this.sentHistoryCursor) {
            if (i == j) {
                this.sentHistoryCursor = j;
                this.inputField.setText(this.historyBuffer);
            } else {
                if (this.sentHistoryCursor == j) {
                    this.historyBuffer = this.inputField.getText();
                }

                this.inputField.setText(this.mc.ingameGUI.getChatGUI().getSentMessages().get(i));
                this.sentHistoryCursor = i;
            }
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
        this.inputField.drawTextBox();
        IChatComponent ichatcomponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());

        if (ichatcomponent != null && ichatcomponent.getChatStyle().getChatHoverEvent() != null) {
            this.handleComponentHover(ichatcomponent, mouseX, mouseY);
        }

        try {
            final List<Word> words = SpellChecker.getWords();
            final String text = inputField.getText();
            final int len = words.size();
            for (int i = 0; i < len; i++) {
                final Word word = words.get(i);
                if (word.getIndex() + word.getLength() < text.length()) {
                    final int scrolledIndex = Math.max(inputField.lineScrollOffset, word.getIndex());
                    final int scrollIndexedLen = Math.max(inputField.lineScrollOffset, word.getIndex() + word.getLength());
                    if (scrolledIndex == scrollIndexedLen)
                        continue;
                    final int posX = mc.fontRendererObj.getStringWidth(text.substring(inputField.lineScrollOffset, scrolledIndex)) + 4;
                    final int width = mc.fontRendererObj.getStringWidth(text.substring(scrolledIndex, scrollIndexedLen));
                    drawRect(posX, height - 4, posX + width, height - 3, 0x6fff0000);

                    if (menu == i) {
                        final List<String> fills = word.getWords();
                        final int fillsLen = fills.size();
                        int maxSize = 0;
                        for (int j = 0; j < fillsLen; j++)
                            maxSize = Math.max(maxSize, mc.fontRendererObj.getStringWidth(fills.get(j)) + 4);
                        int textY = height - 24;
                        for (int a = scroll; a < fillsLen; a++) {
                            if (scroll + 5 < a)
                                break;
                            if (posX - 2 < mouseX && posX - 2 + maxSize > mouseX && textY < mouseY && textY + 11 > mouseY)
                                drawRect(posX - 2, textY + 10, posX - 2 + maxSize, textY, 0xcf000000);
                            else
                                drawRect(posX - 2, textY + 10, posX - 2 + maxSize, textY, 0x9f000000);
                            mc.fontRendererObj.drawString(fills.get(a), posX, textY, 0xffffffff);
                            textY -= 10;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void onAutocompleteResponse(String[] p_146406_1_) {
        if (this.waitingOnAutocomplete) {
            this.playerNamesFound = false;
            this.foundPlayerNames.clear();

            for (String s : p_146406_1_) {
                if (s.length() > 0) {
                    this.foundPlayerNames.add(s);
                }
            }

            String s1 = this.inputField.getText().substring(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false));
            String s2 = StringUtils.getCommonPrefix(p_146406_1_);

            if (s2.length() > 0 && !s1.equalsIgnoreCase(s2)) {
                this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
                this.inputField.writeText(s2);
            } else if (this.foundPlayerNames.size() > 0) {
                this.playerNamesFound = true;
                this.autocompletePlayerNames();
            }
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame() {
        return false;
    }
}
