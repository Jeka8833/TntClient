package net.minecraft.realms;

import net.minecraft.client.gui.GuiClickableScrolledSelectionListProxy;

public class RealmsClickableScrolledSelectionList
{
    private final GuiClickableScrolledSelectionListProxy proxy;

    public RealmsClickableScrolledSelectionList(int p_i46052_1_, int p_i46052_2_, int p_i46052_3_, int p_i46052_4_, int p_i46052_5_)
    {
        this.proxy = new GuiClickableScrolledSelectionListProxy(this, p_i46052_1_, p_i46052_2_, p_i46052_3_, p_i46052_4_, p_i46052_5_);
    }

    public void render(int p_render_1_, int p_render_2_)
    {
        this.proxy.drawScreen(p_render_1_, p_render_2_);
    }

    public int width()
    {
        return this.proxy.func_178044_e();
    }

    public int ym()
    {
        return this.proxy.func_178042_f();
    }

    public int xm()
    {
        return this.proxy.func_178045_g();
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
        return this.proxy.func_178044_e() / 2 + 124;
    }

    public void mouseEvent()
    {
        this.proxy.handleMouseInput();
    }

    public void customMouseEvent()
    {
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

    public void itemClicked()
    {
    }

    public void renderSelected()
    {
    }

    public void setLeftPos(int p_setLeftPos_1_)
    {
        this.proxy.setSlotXBoundsFromLeft(p_setLeftPos_1_);
    }
}
