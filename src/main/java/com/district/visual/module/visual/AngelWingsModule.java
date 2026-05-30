package com.district.visual.module.visual;

import com.district.visual.module.Module;

public class AngelWingsModule extends Module {
    public static float scale = 1.0f;
    public static int primaryColor = 0xFFFFFFFF;
    public static int glowColor = 0xAA7B61FF;
    public static boolean animateFlap = true;
    public static float flapSpeed = 1.5f;

    public AngelWingsModule() {
        super("AngelWings", "Angel wings cosmetic on player back", Category.VISUAL);
        enabled = true;
    }
}
