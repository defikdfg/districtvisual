package com.district.visual.module.combat;

import com.district.visual.module.Module;

public class CustomCursorModule extends Module {
    public static int color = 0xFFFF4444;
    public static float size = 8f;

    public CustomCursorModule() {
        super("CustomCursor", "Custom cursor when hovering over entities", Category.COMBAT);
        enabled = true;
    }
}
