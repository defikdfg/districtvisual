package com.district.visual.module.visual;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;

public class FullbrightModule extends Module {
    private float prevGamma = 1.0f;

    public FullbrightModule() {
        super("Fullbright", "Maximum brightness, no torch needed", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        prevGamma = mc.options.getGamma().getValue().floatValue();
        mc.options.getGamma().setValue(100.0);
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.options.getGamma().setValue((double) prevGamma);
    }
}
