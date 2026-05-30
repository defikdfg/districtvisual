package com.district.visual.module.hud;

import com.district.visual.module.Module;
import com.district.visual.module.visual.ThemeModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class KeystrokesModule extends Module {

    private static float lmbGlow = 0f, rmbGlow = 0f;

    public KeystrokesModule() {
        super("Keystrokes", "WASD + mouse click display", Category.HUD);
        enabled = true;
    }

    public static void onLMB() { lmbGlow = 1f; }
    public static void onRMB() { rmbGlow = 1f; }

    @Override
    public void onTick(MinecraftClient client) {
        lmbGlow = Math.max(0, lmbGlow - 0.08f);
        rmbGlow = Math.max(0, rmbGlow - 0.08f);
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.textRenderer == null) return;

        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        int bx = sw - 76;
        int by = sh - 95;

        boolean w = mc.options.forwardKey.isPressed();
        boolean a = mc.options.leftKey.isPressed();
        boolean s = mc.options.backKey.isPressed();
        boolean d = mc.options.rightKey.isPressed();
        boolean sp = mc.options.jumpKey.isPressed();

        drawKey(ctx, mc, "W", bx + 23, by, w);
        drawKey(ctx, mc, "A", bx, by + 22, a);
        drawKey(ctx, mc, "S", bx + 23, by + 22, s);
        drawKey(ctx, mc, "D", bx + 46, by + 22, d);

        // Space bar
        int spColor = sp ? ThemeModule.primaryColor : 0xFF1A1A1A;
        ctx.fill(bx, by + 44, bx + 68, by + 58, spColor);
        ctx.fill(bx, by + 44, bx + 68, by + 45, sp ? 0xFFFFFFFF : 0xFF444444);
        ctx.drawTextWithShadow(mc.textRenderer, "SPACE", bx + 14, by + 48, 0xFFFFFFFF);

        // LMB
        int lmbBg = lmbGlow > 0.05f ? ThemeModule.withAlpha(ThemeModule.primaryColor, (int)(lmbGlow * 200)) : 0xFF1A1A1A;
        ctx.fill(bx, by + 62, bx + 32, by + 76, lmbBg);
        ctx.fill(bx, by + 62, bx + 32, by + 63, 0xFF444444);
        ctx.drawTextWithShadow(mc.textRenderer, "LMB", bx + 4, by + 66, 0xFFFFFFFF);

        // RMB
        int rmbBg = rmbGlow > 0.05f ? ThemeModule.withAlpha(ThemeModule.primaryColor, (int)(rmbGlow * 200)) : 0xFF1A1A1A;
        ctx.fill(bx + 36, by + 62, bx + 68, by + 76, rmbBg);
        ctx.fill(bx + 36, by + 62, bx + 68, by + 63, 0xFF444444);
        ctx.drawTextWithShadow(mc.textRenderer, "RMB", bx + 40, by + 66, 0xFFFFFFFF);
    }

    private void drawKey(DrawContext ctx, MinecraftClient mc, String label, int x, int y, boolean pressed) {
        int bg = pressed ? ThemeModule.primaryColor : 0xFF1A1A1A;
        int border = pressed ? 0xFFFFFFFF : 0xFF444444;
        ctx.fill(x, y, x + 20, y + 20, bg);
        ctx.fill(x, y, x + 20, y + 1, border);
        int tw = mc.textRenderer.getWidth(label);
        ctx.drawTextWithShadow(mc.textRenderer, label, x + (20 - tw) / 2, y + 6, 0xFFFFFFFF);
    }
}
