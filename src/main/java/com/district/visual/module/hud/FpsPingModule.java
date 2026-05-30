package com.district.visual.module.hud;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FpsPingModule extends Module {
    public FpsPingModule() {
        super("FPS/Ping", "Shows FPS and network ping", Category.HUD);
        enabled = true;
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.textRenderer == null) return;

        int fps = mc.getCurrentFps();
        int fpsColor = fps > 60 ? 0xFF44FF44 : fps > 30 ? 0xFFFFAA00 : 0xFFFF4444;

        ctx.drawTextWithShadow(mc.textRenderer, fps + " FPS", 2, 2, fpsColor);

        if (mc.getNetworkHandler() != null && mc.player != null) {
            var entry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
            if (entry != null) {
                int ping = entry.getLatency();
                int pingColor = ping < 80 ? 0xFF44FF44 : ping < 150 ? 0xFFFFAA00 : 0xFFFF4444;
                ctx.drawTextWithShadow(mc.textRenderer, ping + " ms", 2, 12, pingColor);
            }
        }
    }
}
