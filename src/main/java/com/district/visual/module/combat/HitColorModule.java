package com.district.visual.module.combat;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;

public class HitColorModule extends Module {
    public static float red = 1.0f;
    public static float green = 0.2f;
    public static float blue = 0.2f;
    public static float alpha = 0.6f;
    public static int flashDurationTicks = 3;

    public HitColorModule() {
        super("HitColor", "Changes entity color when damaged", Category.COMBAT);
        enabled = true;
    }
}
