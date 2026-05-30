package com.district.visual.module.combat;

import com.district.visual.module.Module;

public class HitboxModule extends Module {
    public static int color = 0xFF00FFFF;
    public static float lineWidth = 1.0f;
    public static boolean ghostStyle = true;

    public HitboxModule() {
        super("Hitbox", "Renders entity hitboxes", Category.COMBAT);
        enabled = true;
    }
}
