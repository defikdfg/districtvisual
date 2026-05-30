package com.district.visual.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {

    protected final String name;
    protected final String description;
    protected final Category category;
    protected boolean enabled;

    public enum Category {
        COMBAT, HUD, VISUAL, MENU
    }

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = false;
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick(MinecraftClient client) {}
    public void onRenderHud(net.minecraft.client.gui.DrawContext context, float tickDelta) {}

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable(); else onDisable();
    }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean v) {
        if (v != enabled) toggle();
    }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
}
