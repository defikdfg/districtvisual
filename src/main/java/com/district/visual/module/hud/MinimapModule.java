package com.district.visual.module.hud;

import com.district.visual.module.Module;
import com.district.visual.module.visual.ThemeModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;

public class MinimapModule extends Module {
    public static int size = 80;
    public static int zoom = 1;
    public static boolean showPlayers = true;
    public static boolean circular = true;

    public MinimapModule() {
        super("Minimap", "Mini map with player markers", Category.HUD);
        enabled = true;
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;

        int sw = mc.getWindow().getScaledWidth();
        int mx = sw - size - 5;
        int my = 5;

        // Background
        LiquidGlassModule.drawGlassPanel(ctx, mx, my, size, size);
        ctx.fill(mx, my, mx + size, my + size, 0x44000000);

        // Border
        int border = ThemeModule.primaryColor;
        ctx.fill(mx, my, mx + size, my + 1, border);
        ctx.fill(mx, my + size - 1, mx + size, my + size, border);
        ctx.fill(mx, my, mx + 1, my + size, border);
        ctx.fill(mx + size - 1, my, mx + size, my + size, border);

        // Player dot (center)
        int cx = mx + size / 2;
        int cy = my + size / 2;
        ctx.fill(cx - 2, cy - 2, cx + 2, cy + 2, 0xFF00FF88);

        // Other players
        if (showPlayers && mc.world != null) {
            double px = mc.player.getX();
            double pz = mc.player.getZ();
            for (PlayerEntity other : mc.world.getPlayers()) {
                if (other == mc.player) continue;
                double dx = (other.getX() - px) / (10.0 * zoom);
                double dz = (other.getZ() - pz) / (10.0 * zoom);
                if (Math.abs(dx) > 0.5 || Math.abs(dz) > 0.5) continue;
                int ox = (int)(cx + dx * size);
                int oz = (int)(cy + dz * size);
                ctx.fill(ox - 2, oz - 2, ox + 2, oz + 2, 0xFFFF4444);
            }
        }

        // Coordinates label
        if (mc.textRenderer != null) {
            String coords = String.format("%.0f %.0f", mc.player.getX(), mc.player.getZ());
            ctx.drawTextWithShadow(mc.textRenderer, coords, mx + 2, my + size + 2, 0xFF888888);
        }
    }
}
