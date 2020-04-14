package net.TntClient.gui.JekasMenu.components;

import net.TntClient.gui.JekasMenu.Component;

public class UISlider extends Component {

    public float value = 0;
    private transient final String name;
    private transient final float min;
    private transient final float max;
    private transient final float step;

    public UISlider(final String name, final float min, final float max, final float step){
        this.name = name;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public UISlider(final String name, final float min, final float max, final float step, final float value){
        this.name = name;
        this.min = min;
        this.max = max;
        this.step = step;
        this.value = value;
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

    }
}
