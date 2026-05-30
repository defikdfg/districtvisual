package com.district.visual.module.visual;

import com.district.visual.module.Module;

public class CustomFogModule extends Module {
    public static float fogStart = 0.1f;
    public static float fogEnd = 0.6f;
    public static int fogColor = 0xFF87CEEB;
    public static float density = 0.5f;

    public CustomFogModule() {
        super("CustomFog", "Custom fog color, distance and density", Category.VISUAL);
        enabled = false;
    }
}
