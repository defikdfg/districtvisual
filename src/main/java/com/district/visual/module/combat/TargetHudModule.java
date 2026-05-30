package com.district.visual.module.combat;

import com.district.visual.module.Module;
import com.district.visual.module.visual.ThemeModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;

public class TargetHudModule extends Module {

    public static LivingEntity currentTarget = null;
    public static int x = 10;
    public static int y = 10;
    public static float fadeAlpha = 0f;

    public TargetHudModule() {
        super("TargetHUD", "Shows target health, name, armor", Category.COMBAT);
        enabled = true;
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (client.crosshairTarget == null || client.world == null) {
            currentTarget = null;
            return;
        }
        if (client.crosshairTarget instanceof EntityHitResult ehr) {
            if (ehr.getEntity() instanceof LivingEntity le) {
                currentTarget = le;
            }
        }
    }

    @Override
    public void onRenderHud(DrawContext ctx, float tickDelta) {
        if (currentTarget == null || !currentTarget.isAlive()) {
            fadeAlpha = Math.max(0, fadeAlpha - 0.05f);
            if (fadeAlpha <= 0) return;
        } else {
            fadeAlpha = Math.min(1, fadeAlpha + 0.1f);
        }

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.textRenderer == null) return;

        int alpha = (int)(fadeAlpha * 200);
        int bg = ThemeModule.withAlpha(ThemeModule.bgColor, alpha);
        int accent = ThemeModule.withAlpha(ThemeModule.primaryColor, alpha);

        int panelW = 160;
        int panelH = 42;
        int px = x;
        int py = y;

        ctx.fill(px, py, px + panelW, py + panelH, bg);
        ctx.fill(px, py, px + panelW, py + 2, accent);

        if (currentTarget != null) {
            String name = currentTarget.getDisplayName().getString();
            float hp = currentTarget.getHealth();
            float maxHp = currentTarget.getMaxHealth();
            float hpPct = hp / maxHp;

            ctx.drawTextWithShadow(mc.textRenderer, name, px + 5, py + 5, ThemeModule.textColor);
            ctx.fill(px + 5, py + 18, px + panelW - 5, py + 26, 0xFF333333);
            int barW = (int)((panelW - 10) * hpPct);
            int barColor = hpPct > 0.5f ? 0xFF44FF44 : hpPct > 0.25f ? 0xFFFFAA00 : 0xFFFF4444;
            ctx.fill(px + 5, py + 18, px + 5 + barW, py + 26, barColor);
            String hpText = String.format("%.1f / %.1f", hp, maxHp);
            ctx.drawTextWithShadow(mc.textRenderer, hpText, px + 5, py + 29, 0xFFAAAAAA);
        }
    }
}
