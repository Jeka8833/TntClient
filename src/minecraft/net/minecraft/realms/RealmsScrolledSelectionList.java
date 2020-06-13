package net.minecraft.realms;

import net.minecraft.client.gui.GuiSlotRealmsProxy;

public class RealmsScrolledSelectionList
{
    private final GuiSlotRealmsProxy proxy;

    public RealmsScrolledSelectionList(int p_i1119_1_, int p_i1119_2_, int p_i1119_3_, int p_i1119_4_, int p_i1119_5_)
    {
        this.proxy = new GuiSlotRealmsProxy(this, p_i1119_1_, p_i1119_2_, p_i1119_3_, p_i1119_4_, p_i1119_5_);
    }

    public void render(int p_render_1_, int p_render_2_)
    {
        this.proxy.drawScreen(p_render_1_, p_render_2_);
    }

    public int width()
    {
        return this.proxy.func_154338_k();
    }

    public int ym()
    {
        return this.proxy.func_154339_l();
    }

    public int xm()
    {
        return this.proxy.func_154337_m();
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
        return this.proxy.func_154338_k() / 2 + 124;
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
