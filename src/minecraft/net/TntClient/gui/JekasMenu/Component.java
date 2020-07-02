package net.TntClient.gui.JekasMenu;

import net.minecraft.client.Minecraft;

public abstract class Component {

    protected static Minecraft mc = Minecraft.getMinecraft();

    public int posX,posY,width,height;

    public abstract void  handleMouseInput();
    public abstract void  mouseReleased();
    public abstract void  mouseClicked();
    public abstract void  mouseClickMove();
    public abstract void  keyTyped();
    public abstract void  drawScreen();
}
