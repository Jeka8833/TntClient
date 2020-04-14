package net.TntClient.modules;

import net.TntClient.Config;
import net.TntClient.TntClient;
import net.TntClient.gui.JekasMenu.Component;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    protected static Minecraft mc = Minecraft.getMinecraft();
    private final List<Component> options = new ArrayList<>();

    private transient final String name;
    private boolean active;
    private transient final Category category;
    private transient final String description;

    public Module(final String name, final Category category) {
        description = "";
        this.name = name;
        this.category = category;
        onSetup();
    }

    public Module(final String name, final Category category, final String description) {
        this.description = description;
        this.name = name;
        this.category = category;
        onSetup();
    }
    public void onEnable() {
        TntClient.eventManager.register(this);
    }

    public void onDisable() {
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
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public void toggle() {
        active = !active;
        onToggle();

        if (active) {
            onEnable();
        } else {
            onDisable();
        }
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
