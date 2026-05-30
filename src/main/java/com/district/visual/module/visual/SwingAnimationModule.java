package com.district.visual.module.visual;

import com.district.visual.module.Module;

public class SwingAnimationModule extends Module {
    public static float swingSpeed = 2.5f;
    public static float swingAmplitude = 1.2f;
    public static int style = 1; // 1=normal, 2=1.7, 3=hypixel

    public SwingAnimationModule() {
        super("SwingAnimation", "Custom hand swing animation", Category.VISUAL);
        enabled = true;
    }
}
