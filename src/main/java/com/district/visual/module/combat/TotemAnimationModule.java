package com.district.visual.module.combat;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;

public class TotemAnimationModule extends Module {
    public static float scale = 1.5f;
    public static float spinSpeed = 10f;
    public static boolean particles = true;
    public static int glowColor = 0xFFFFAA00;

    public static boolean isActivating = false;
    public static int activationTick = 0;

    public TotemAnimationModule() {
        super("TotemAnimation", "Custom totem pop animation", Category.COMBAT);
        enabled = true;
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (isActivating) {
            activationTick++;
            if (activationTick > 40) {
                isActivating = false;
                activationTick = 0;
            }
        }
    }

    public static void triggerActivation() {
        isActivating = true;
        activationTick = 0;
    }
}
