package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import net.TntClient.event.events.EventSendMessage;
import net.TntClient.mods.translate.TranslateGoogle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiNewChat extends Gui {
    private static final Logger logger = LogManager.getLogger();
    private final Minecraft mc;
    private final List<String> sentMessages = Lists.newArrayList();
    public final List<ChatLine> chatLines = Lists.newArrayList();
    private final List<ChatLine> field_146253_i = Lists.newArrayList();
    private final List<String> texts = new ArrayList<>();
    private int scrollPos;

    private long startTimePos = 0;
    private int hashReset;
    private int move = 0;

    public GuiNewChat(Minecraft mcIn) {
        this.mc = mcIn;
    }

    public void drawChat(int p_146230_1_) {
        if (this.mc.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
            final int k = this.field_146253_i.size();
            if (k > 0) {
                int j = 0;

                final boolean flag = getChatOpen();
                final float f1 = this.getChatScale();
                final int f = (int) (229.5 * this.mc.gameSettings.chatOpacity + 0.1F);
                final int l = MathHelper.ceiling_float_int((float) this.getChatWidth() / f1) + 4;
                final int i = this.getLineCount();
                GlStateManager.pushMatrix();
                GlStateManager.translate(2.0F, 20.0F, 0.0F);
                GlStateManager.scale(f1, f1, 1.0F);

                for (int i1 = 0; i1 + this.scrollPos < this.field_146253_i.size() && i1 < i; ++i1) {
                    final ChatLine chatline = this.field_146253_i.get(i1 + this.scrollPos);

                    if (chatline != null) {
                        final int j1 = p_146230_1_ - chatline.getUpdatedCounter();

                        if (j1 < 200 || flag) {
                            final double d0 = MathHelper.clamp_double((1 - j1 / 200.0D) * 10, 0, 1);
                            final int l1 = flag ? f : (int) (d0 * d0 * f);
                            ++j;

                            if (l1 > 3) {
                                int j2 = -i1 * 9;
                                drawRect(0, j2 - 9, l, j2, l1 >> 1 << 24);
                                GlStateManager.enableBlend();
                                mc.fontRendererObj.drawStringWithShadow(chatline.getChatComponent().getFormattedText(),
                                        0, j2 - 8, 16777215 + (l1 << 24));
                                GlStateManager.disableAlpha();
                                GlStateManager.disableBlend();
                            }
                        }
                    }
                }

                if (flag) {
                    final int l2 = k * mc.fontRendererObj.FONT_HEIGHT;
                    final int i3 = j * mc.fontRendererObj.FONT_HEIGHT;
                    if (l2 + k != i3 + j) {
                        final int j3 = -(this.scrollPos * i3 / k);
                        GlStateManager.translate(-3.0F, 0.0F, 0.0F);
                        drawRect(2, j3, 1, j3 - i3 * i3 / l2, j3 < 0 ? 13421772 + (170 << 24) : 0x60cccccc);
                    }
                }
                GlStateManager.popMatrix();
            }
        }
    }

    /**
     * Clears the chat.
     */
    public void clearChatMessages() {
        this.field_146253_i.clear();
        this.chatLines.clear();
        this.sentMessages.clear();
        texts.clear();
    }

    public void printChatMessage(IChatComponent p_146227_1_) {
        this.printChatMessageWithOptionalDeletion(p_146227_1_, 0);
    }

    /**
     * prints the ChatComponent to Chat. If the ID is not 0, deletes an existing Chat Line of that ID from the GUI
     */
    public void printChatMessageWithOptionalDeletion(IChatComponent p_146234_1_, int p_146234_2_) {
        this.setChatLine(p_146234_1_, p_146234_2_, this.mc.ingameGUI.getUpdateCounter(), false);
        logger.info("[CHAT] " + p_146234_1_.getUnformattedText());
    }

    private void setChatLine(IChatComponent p_146237_1_, int p_146237_2_, int p_146237_3_, boolean p_146237_4_) {
        final EventSendMessage message = new EventSendMessage(p_146237_1_.getUnformattedText());
        message.call();
        if (message.isCancelled())
            return;

        texts.add(p_146237_1_.getUnformattedText());
        if (p_146237_2_ != 0) {
            this.deleteChatLine(p_146237_2_);
        }

        int i = MathHelper.floor_float((float) this.getChatWidth() / this.getChatScale());
        List<IChatComponent> list = GuiUtilRenderComponents.func_178908_a(p_146237_1_, i, this.mc.fontRendererObj, false, false);
        boolean flag = this.getChatOpen();

        for (IChatComponent ichatcomponent : list) {
            if (flag && this.scrollPos > 0) {
                this.scroll(1);
            }

            this.field_146253_i.add(0, new ChatLine(p_146237_3_, ichatcomponent, p_146237_2_));
        }

        while (this.field_146253_i.size() > 100) {
            this.field_146253_i.remove(this.field_146253_i.size() - 1);
        }

        if (!p_146237_4_) {
            this.chatLines.add(0, new ChatLine(p_146237_3_, p_146237_1_, p_146237_2_));

            while (this.chatLines.size() > 100) {
                this.chatLines.remove(this.chatLines.size() - 1);
            }
        }
    }

    public void refreshChat() {
        this.field_146253_i.clear();
        this.resetScroll();

        for (int i = this.chatLines.size() - 1; i >= 0; --i) {
            ChatLine chatline = this.chatLines.get(i);
            this.setChatLine(chatline.getChatComponent(), chatline.getChatLineID(), chatline.getUpdatedCounter(), true);
        }
    }

    public List<String> getSentMessages() {
        return this.sentMessages;
    }

    /**
     * Adds this string to the list of sent messages, for recall using the up/down arrow keys
     */
    public void addToSentMessages(String p_146239_1_) {
        if (this.sentMessages.isEmpty() || !this.sentMessages.get(this.sentMessages.size() - 1).equals(p_146239_1_)) {
            this.sentMessages.add(p_146239_1_);
        }
    }

    /**
     * Resets the chat scroll (executed when the GUI is closed, among others)
     */
    public void resetScroll() {
        this.scrollPos = 0;
    }

    /**
     * Scrolls the chat by the given number of lines.
     */
    public void scroll(int p_146229_1_) {
        this.scrollPos += p_146229_1_;
        int i = this.field_146253_i.size();

        if (this.scrollPos > i - this.getLineCount()) {
            this.scrollPos = i - this.getLineCount();
        }

        if (this.scrollPos <= 0) {
            this.scrollPos = 0;
        }
    }

    /**
     * Gets the chat component under the mouse
     */
    public IChatComponent getChatComponent(int p_146236_1_, int p_146236_2_) {
        if (this.getChatOpen()) {
            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            int i = scaledresolution.getScaleFactor();
            float f = this.getChatScale();
            int j = p_146236_1_ / i - 3;
            int k = p_146236_2_ / i - 27;
            j = MathHelper.floor_float((float) j / f);
            k = MathHelper.floor_float((float) k / f);

            if (j >= 0 && k >= 0) {
                int l = Math.min(this.getLineCount(), this.field_146253_i.size());

                if (j <= MathHelper.floor_float((float) this.getChatWidth() / this.getChatScale()) && k < this.mc.fontRendererObj.FONT_HEIGHT * l + l) {
                    int i1 = k / this.mc.fontRendererObj.FONT_HEIGHT + this.scrollPos;

                    if (i1 >= 0 && i1 < this.field_146253_i.size()) {
                        IChatComponent chatline = this.field_146253_i.get(i1).getChatComponent();
                        TranslateGoogle.setText(getChatLine(chatline));
                        final String translate = TranslateGoogle.getText();
                        final int widthAllText = mc.fontRendererObj.getStringWidth(translate);

                        int j1 = 0;

                        final int scaledWidth = scaledresolution.getScaledWidth() - 27;
                        final int scaledHeight = scaledresolution.getScaledHeight() - 25;
                        if (scaledWidth > widthAllText) {
                            drawRect(2, scaledHeight - 2, widthAllText + 8, scaledresolution.getScaledHeight() - 15, 0x80000000);
                            mc.fontRendererObj.drawString(translate, 5, scaledHeight, 0xffffffff);
                        } else {
                            drawRect(2, scaledHeight - 2, scaledresolution.getScaledWidth() - 20, scaledresolution.getScaledHeight() - 15, 0x80000000);
                            final int hash = translate.hashCode();
                            if (hashReset != hash) {
                                hashReset = hash;
                                startTimePos = System.currentTimeMillis();
                            }
                            GL11.glScissor(5 * i, 5 * i, scaledWidth * i, scaledHeight * i);
                            GL11.glEnable(3089);
                            GL11.glPushMatrix();
                            GL11.glTranslatef(move, 0.0f, 0.0F);
                            mc.fontRendererObj.drawString(translate, 5, scaledHeight, 0xffffffff);
                            mc.fontRendererObj.drawString(translate, 5 + 36 + widthAllText, scaledHeight, 0xffffffff);
                            move = (int) -(((System.currentTimeMillis() - startTimePos) / 20) % (36 + widthAllText));
                            GL11.glPopMatrix();
                            GL11.glDisable(3089);
                        }

                        for (IChatComponent ichatcomponent : chatline) {
                            if (ichatcomponent instanceof ChatComponentText) {
                                j1 += this.mc.fontRendererObj.getStringWidth(GuiUtilRenderComponents.func_178909_a(((ChatComponentText) ichatcomponent).getChatComponentText_TextValue(), false));

                                if (j1 > j) {
                                    return ichatcomponent;
                                }
                            }
                        }
                    }

                }
            }
        }
        return null;
    }

    private String getChatLine(final IChatComponent chatComponent) {
        final String found = chatComponent.getUnformattedText();
        for (String line : texts)
            if (line.contains(found))
                return StringUtils.stripControlCodes(line);
        return StringUtils.stripControlCodes(found);
    }

    /**
     * Returns true if the chat GUI is open
     */
    public boolean getChatOpen() {
        return this.mc.currentScreen instanceof GuiChat;
    }

    /**
     * finds and deletes a Chat line by ID
     */
    public void deleteChatLine(int p_146242_1_) {
        Iterator<ChatLine> iterator = this.field_146253_i.iterator();

        while (iterator.hasNext()) {
            ChatLine chatline = iterator.next();

            if (chatline.getChatLineID() == p_146242_1_) {
                iterator.remove();
            }
        }

        iterator = this.chatLines.iterator();

        while (iterator.hasNext()) {
            ChatLine chatline1 = iterator.next();

            if (chatline1.getChatLineID() == p_146242_1_) {
                iterator.remove();
                break;
            }
        }
    }

    public int getChatWidth() {
        return calculateChatboxWidth(this.mc.gameSettings.chatWidth);
    }

    public int getChatHeight() {
        return calculateChatboxHeight(this.getChatOpen() ? this.mc.gameSettings.chatHeightFocused : this.mc.gameSettings.chatHeightUnfocused);
    }

    /**
     * Returns the chatscale from mc.gameSettings.chatScale
     */
    public float getChatScale() {
        return this.mc.gameSettings.chatScale;
    }

    public static int calculateChatboxWidth(float p_146233_0_) {
        return MathHelper.floor_float(p_146233_0_ * 280 + 40);
    }

    public static int calculateChatboxHeight(float p_146243_0_) {
        return MathHelper.floor_float(p_146243_0_ * 160 + 20);
    }

    public int getLineCount() {
        return this.getChatHeight() / 9;
    }
}
