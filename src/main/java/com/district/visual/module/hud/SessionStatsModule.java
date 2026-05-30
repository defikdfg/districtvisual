package com.district.visual.module.hud;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class SessionStatsModule extends Module {
    public static int kills = 0;
    public static int deaths = 0;

    public SessionStatsModule() {
        super("SessionStats", "Kills/deaths this session", Category.HUD);
        enabled = true;
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.textRenderer == null) return;
        ctx.drawTextWithShadow(mc.textRenderer, "K: " + kills + "  D: " + deaths, 2, 24, 0xFFCCCCCC);
    }
}
