package com.district.visual.module.hud;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class ArmorHudModule extends Module {
    public ArmorHudModule() {
        super("ArmorHUD", "Show armor durability", Category.HUD);
        enabled = true;
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.textRenderer == null) return;

        int sw = mc.getWindow().getScaledWidth();
        int sh = mc.getWindow().getScaledHeight();
        int bx = sw / 2 - 90;
        int by = sh - 55;

        int[] slots = {39, 38, 37, 36};
        for (int i = 0; i < 4; i++) {
            ItemStack stack = mc.player.getInventory().getStack(slots[i]);
            if (!stack.isEmpty() && stack.isDamageable()) {
                int dmg = stack.getDamage();
                int maxDmg = stack.getMaxDamage();
                float pct = 1f - (float) dmg / maxDmg;
                int color = pct > 0.5f ? 0xFF44FF44 : pct > 0.25f ? 0xFFFFAA00 : 0xFFFF4444;
                ctx.drawItem(stack, bx + i * 20, by);
                ctx.fill(bx + i * 20, by + 17, bx + i * 20 + (int)(16 * pct), by + 18, color);
            }
        }
    }
}
