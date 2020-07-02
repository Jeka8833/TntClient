package net.TntClient.gui.JekasMenu.components;

import net.TntClient.gui.JekasMenu.Component;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class UISlider extends Component {

    public float value = 0;
    private transient final String name;
    private transient final float min;
    private transient final float max;
    private transient final float step;

    public UISlider(final String name, final float min, final float max, final float step) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.step = step;
        height = 15;
    }

    public UISlider(final String name, final float min, final float max, final float step, final float value) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.step = step;
        this.value = value;
        height = 15;
    }

    @Override
    public void handleMouseInput() {

    }

    @Override
    public void mouseReleased() {

    }

    @Override
    public void mouseClicked() {

    }

    @Override
    public void mouseClickMove() {

    }

    @Override
    public void keyTyped() {

    }

    @Override
    public void drawScreen() {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        mc.fontRendererObj.drawString(name, posX, posY, 0xffffffff);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        glBegin(GL_LINES);
        glVertex2f(posX, posY + 12);
        glVertex2f(posX + width, posY + 12);
        glEnd();
    }
}
