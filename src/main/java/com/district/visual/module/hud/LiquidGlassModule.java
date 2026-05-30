package com.district.visual.module.hud;

import com.district.visual.module.Module;
import net.minecraft.client.gui.DrawContext;

public class LiquidGlassModule extends Module {
    public static float blurRadius = 8f;
    public static float opacity = 0.75f;
    public static int tint = 0x44FFFFFF;

    public LiquidGlassModule() {
        super("LiquidGlass", "Frosted glass blur effect on HUD panels", Category.HUD);
        enabled = true;
    }

    /** Draw a glass panel at the given bounds */
    public static void drawGlassPanel(DrawContext ctx, int x, int y, int w, int h) {
        // Layered semi-transparent fills to simulate blur
        ctx.fill(x, y, x + w, y + h, 0x99000000);
        ctx.fill(x, y, x + w, y + h, 0x22FFFFFF);
        ctx.fill(x, y, x + w, y + 1, 0x66FFFFFF); // top highlight
        ctx.fill(x, y, x + 1, y + h, 0x33FFFFFF); // left highlight
        ctx.fill(x, y + h - 1, x + w, y + h, 0x11FFFFFF);
    }
}
