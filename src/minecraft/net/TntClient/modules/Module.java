package net.TntClient.modules;

import net.TntClient.Config;
import net.TntClient.TntClient;
import net.TntClient.gui.JekasMenu.Component;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    protected static final Minecraft mc = Minecraft.getMinecraft();
    private boolean active;
    public int keyBind;

    public transient final boolean isDanger;
    public transient final boolean onlyHypixel;
    public transient final boolean onlyTntGame;

    public transient boolean isBlocking;
    private transient final List<Component> options = new ArrayList<>();
    private transient final String name;
    private transient final Category category;
    private transient final String description;

    public Module(final String name, final Category category, final boolean isDanger, boolean onlyHypixel, boolean onlyTntGame) {
        this(name, category, "", Integer.MAX_VALUE, isDanger, onlyHypixel, onlyTntGame);
    }

    public Module(final String name, final Category category, final int keyBind, final boolean isDanger, boolean onlyHypixel, boolean onlyTntGame) {
        this(name, category, "", keyBind, isDanger, onlyHypixel, onlyTntGame);
    }

    public Module(final String name, final Category category, final String description, final boolean isDanger, boolean onlyHypixel, boolean onlyTntGame) {
        this(name, category, description, Integer.MAX_VALUE, isDanger, onlyHypixel, onlyTntGame);
    }

    public Module(final String name, final Category category, final String description, final int keyBind, final boolean isDanger, boolean onlyHypixel, boolean onlyTntGame) {
        this.description = description;
        this.name = name;
        this.category = category;
        this.keyBind = keyBind;
        this.isDanger = isDanger;
        this.onlyHypixel = onlyHypixel;
        this.onlyTntGame = onlyTntGame;
        onSetup();
    }

    public void onEnable() {
        if (!isBlocking)
            TntClient.eventManager.register(this);
    }

    public void onDisable() {
        if (!isBlocking)
            TntClient.eventManager.unregister(this);
    }

    public abstract void onToggle();

    public abstract void onSetup();

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isActive() {
        return active && !isBlocking;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public void setBlocking(final boolean state) {
        if (isBlocking == state) return;
        final boolean act = isActive();
        isBlocking = state;
        if(isActive() != act){
            if (!state)
                TntClient.eventManager.register(this);
            else
                TntClient.eventManager.unregister(this);
        }
    }

    public void toggle() {
        active = !active;
        onToggle();

        if (active)
            onEnable();
        else
            onDisable();
        Config.write();
    }

    public void addOption(final Component component) {
        options.add(component);
    }

    public List<Component> getOptions() {
        return options;
    }

    public String getDescription() {
        return description;
    }
}
