package net.minecraft.realms;

import net.minecraft.client.gui.GuiSimpleScrolledSelectionListProxy;

public class RealmsSimpleScrolledSelectionList
{
    private final GuiSimpleScrolledSelectionListProxy proxy;

    public RealmsSimpleScrolledSelectionList(int p_i45803_1_, int p_i45803_2_, int p_i45803_3_, int p_i45803_4_, int p_i45803_5_)
    {
        this.proxy = new GuiSimpleScrolledSelectionListProxy(this, p_i45803_1_, p_i45803_2_, p_i45803_3_, p_i45803_4_, p_i45803_5_);
    }

    public void render(int p_render_1_, int p_render_2_)
    {
        this.proxy.drawScreen(p_render_1_, p_render_2_);
    }

    public int width()
    {
        return this.proxy.getWidth();
    }

    public int ym()
    {
        return this.proxy.getMouseY();
    }

    public int xm()
    {
        return this.proxy.getMouseX();
    }

    protected void renderItem()
    {
    }

    public void renderItem(int p_renderItem_1_, int p_renderItem_2_, int p_renderItem_3_, int p_renderItem_4_, int p_renderItem_5_, int p_renderItem_6_)
    {
        this.renderItem();
    }

    public int getItemCount()
    {
        return 0;
    }

    public void selectItem()
    {
    }

    public boolean isSelectedItem()
    {
        return false;
    }

    public void renderBackground()
    {
    }

    public int getMaxPosition()
    {
        return 0;
    }

    public int getScrollbarPosition()
    {
        return this.proxy.getWidth() / 2 + 124;
    }

    public void mouseEvent()
    {
        this.proxy.handleMouseInput();
    }

    public void scroll(int p_scroll_1_)
    {
        this.proxy.scrollBy(p_scroll_1_);
    }

    public int getScroll()
    {
        return this.proxy.getAmountScrolled();
    }

    protected void renderList()
    {
    }
}
