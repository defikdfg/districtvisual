package com.district.visual.module.visual;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;

public class ThemeModule extends Module {

    // Primary accent color (default: Pulse-style cyan/purple gradient)
    public static int primaryColor = 0xFF7B61FF;   // purple
    public static int secondaryColor = 0xFF00D4FF; // cyan
    public static int bgColor = 0xCC0D0D0D;        // near-black
    public static int textColor = 0xFFFFFFFF;
    public static int accentAlpha = 200;

    public static float cornerRadius = 8f;
    public static boolean useGradient = true;
    public static boolean useBlur = true;

    public ThemeModule() {
        super("Theme", "Global color theme for all HUD elements", Category.VISUAL);
    }

    @Override
    public void onTick(MinecraftClient client) {}

    /** Lerp between primary and secondary by t [0..1] */
    public static int getGradientColor(float t) {
        int r1 = (primaryColor >> 16) & 0xFF;
        int g1 = (primaryColor >> 8) & 0xFF;
        int b1 = primaryColor & 0xFF;
        int r2 = (secondaryColor >> 16) & 0xFF;
        int g2 = (secondaryColor >> 8) & 0xFF;
        int b2 = secondaryColor & 0xFF;
        int r = (int)(r1 + (r2 - r1) * t);
        int g = (int)(g1 + (g2 - g1) * t);
        int b = (int)(b1 + (b2 - b1) * t);
        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }

    public static int withAlpha(int color, int alpha) {
        return (color & 0x00FFFFFF) | (alpha << 24);
    }
}
