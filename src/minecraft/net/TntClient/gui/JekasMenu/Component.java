package net.TntClient.gui.JekasMenu;

public abstract class Component {

    public int posX,posY,width,height;

    public abstract void  handleMouseInput();
    public abstract void  mouseReleased();
    public abstract void  mouseClicked();
    public abstract void  mouseClickMove();
    public abstract void  keyTyped();
    public abstract void  drawScreen();
}
