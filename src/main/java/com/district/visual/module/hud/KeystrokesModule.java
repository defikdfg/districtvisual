package com.district.visual.module.hud;

import com.district.visual.module.Module;
import com.district.visual.module.visual.ThemeModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class KeystrokesModule extends Module {
    public static int x = 0, y = 0; // auto-positioned bottom right
    private static int lmbClicks = 0, rmbClicks = 0;
    private static long lastLmb = 0, lastRmb = 0;
    private static float lmbGlow = 0f, rmbGlow = 0f;

    public KeystrokesModule() {
        super("Keystrokes", "WASD + mouse click display", Category.HUD);
        enabled = true;
    }

    public static void onLMB() { lmbClicks++; lastLmb = System.currentTimeMillis(); lmbGlow = 1f; }
    public static void onRMB() { rmbClicks++; lastRmb = System.currentTimeMillis(); rmbGlow = 1f; }

    @Override
    public void onTick(MinecraftClient client) {
        lmbGlow = Math.max(0, lmbGlow - 0.1f);
        rmbGlow = Math.max(0, rmbGlow - 0.1f);
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.textRenderer == null) return;

        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        int bx = sw - 72;
        int by = sh - 90;

        boolean w = mc.options.forwardKey.isPressed();
        boolean a = mc.options.leftKey.isPressed();
        boolean s = mc.options.backKey.isPressed();
        boolean d = mc.options.rightKey.isPressed();
        boolean sp = mc.options.jumpKey.isPressed();

        drawKey(ctx, mc, "W", bx + 22, by, w);
        drawKey(ctx, mc, "A", bx, by + 22, a);
        drawKey(ctx, mc, "S", bx + 22, by + 22, s);
        drawKey(ctx, mc, "D", bx + 44, by + 22, d);
        drawKey(ctx, mc, "SPACE", bx, by + 44, sp, 66, 18);

        // CPS
        long now = System.currentTimeMillis();
        drawKeyGlow(ctx, mc, "LMB", bx, by + 66, lmbGlow);
        drawKeyGlow(ctx, mc, "RMB", bx + 34, by + 66, rmbGlow);
    }

    private void drawKey(DrawContext ctx, MinecraftClient mc, String label, int x, int y, boolean pressed) {
        drawKey(ctx, mc, label, x, y, pressed, 20, 18);
    }

    private void drawKey(DrawContext ctx, MinecraftClient mc, String label, int x, int y, boolean pressed, int w, int h) {
        int bg = pressed ? ThemeModule.withAlpha(ThemeModule.primaryColor, 200) : 0xAA111111;
        int border = ThemeModule.withAlpha(ThemeModule.primaryColor, pressed ? 255 : 80);
        ctx.fill(x, y, x + w, y + h, bg);
        ctx.fill(x, y, x + w, y + 1, border);
        ctx.fill(x, y + h - 1, x + w, y + h, border);
        ctx.fill(x, y, x + 1, y + h, border);
        ctx.fill(x + w - 1, y, x + w, y + h, border);
        int tw = mc.textRenderer.getWidth(label);
        ctx.drawTextWithShadow(mc.textRenderer, label, x + (w - tw) / 2, y + (h - 8) / 2, 0xFFFFFFFF);
    }

    private void drawKeyGlow(DrawContext ctx, MinecraftClient mc, String label, int x, int y, float glow) {
        int alpha = (int)(180 + glow * 75);
        int bg = (glow > 0.1f) ? ThemeModule.withAlpha(ThemeModule.primaryColor, alpha) : 0xAA111111;
        ctx.fill(x, y, x + 30, y + 16, bg);
        int tw = mc.textRenderer.getWidth(label);
        ctx.drawTextWithShadow(mc.textRenderer, label, x + (30 - tw) / 2, y + 4, 0xFFFFFFFF);
    }
}
