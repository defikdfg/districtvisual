package com.district.visual.module.combat;

import com.district.visual.module.Module;

public class GhostCrystalModule extends Module {
    public static float radius = 1.2f;
    public static float rotationSpeed = 2.0f;
    public static int color = 0xAA7B61FF;
    public static boolean rainbow = false;

    public GhostCrystalModule() {
        super("GhostCrystal", "Ghost crystal aura around target", Category.COMBAT);
        enabled = true;
    }
}
