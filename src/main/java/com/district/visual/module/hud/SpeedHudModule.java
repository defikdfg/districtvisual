package com.district.visual.module.hud;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class SpeedHudModule extends Module {
    public SpeedHudModule() {
        super("SpeedHUD", "Shows current movement speed", Category.HUD);
        enabled = true;
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.textRenderer == null) return;

        double vx = mc.player.getVelocity().x;
        double vz = mc.player.getVelocity().z;
        double speed = Math.sqrt(vx * vx + vz * vz) * 20;

        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        String text = String.format("%.2f bps", speed);
        ctx.drawTextWithShadow(mc.textRenderer, text, sw / 2 - mc.textRenderer.getWidth(text) / 2, sh - 80, 0xFFFFFFFF);
    }
}
