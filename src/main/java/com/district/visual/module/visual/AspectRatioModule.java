package com.district.visual.module.visual;

import com.district.visual.module.Module;

public class AspectRatioModule extends Module {
    public static float ratio = 4f / 3f; // 4:3 default
    public static boolean enabled2 = false;

    public AspectRatioModule() {
        super("AspectRatio", "Force a custom screen aspect ratio", Category.VISUAL);
    }
}
