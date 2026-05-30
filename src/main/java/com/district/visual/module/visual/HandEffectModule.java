package com.district.visual.module.visual;

import com.district.visual.module.Module;

public class HandEffectModule extends Module {
    public static int glowColor = 0xAA7B61FF;
    public static float particleIntensity = 1.0f;
    public static boolean rainbow = false;
    public static boolean trail = true;

    public HandEffectModule() {
        super("HandEffect", "Visual effects on player hand", Category.VISUAL);
        enabled = true;
    }
}
