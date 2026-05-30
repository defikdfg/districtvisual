package com.district.visual.module.combat;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.hit.EntityHitResult;

public class CrosshairModule extends Module {
    public static int color = 0xFFFFFFFF;
    public static int targetColor = 0xFFFF4444;
    public static int size = 5;
    public static int gap = 3;
    public static int thickness = 1;
    public static boolean dot = true;
    public static boolean dynamicOnTarget = true;

    public CrosshairModule() {
        super("Crosshair", "Custom crosshair with target highlight", Category.COMBAT);
        enabled = true;
    }

    @Override
    public void onRenderHud(DrawContext ctx, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (!mc.options.getPerspective().isFirstPerson()) return;
        if (mc.currentScreen != null) return;

        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        int cx = sw / 2;
        int cy = sh / 2;

        boolean onTarget = mc.crosshairTarget instanceof EntityHitResult;
        int c = (dynamicOnTarget && onTarget) ? targetColor : color;
        int s = size + (dynamicOnTarget && onTarget ? 2 : 0);

        ctx.fill(cx - s - gap, cy - 1, cx - gap, cy + 1, c);
        ctx.fill(cx + gap, cy - 1, cx + s + gap, cy + 1, c);
        ctx.fill(cx - 1, cy - s - gap, cx + 1, cy - gap, c);
        ctx.fill(cx - 1, cy + gap, cx + 1, cy + s + gap, c);
        if (dot) ctx.fill(cx - 1, cy - 1, cx + 1, cy + 1, c);
    }
}
