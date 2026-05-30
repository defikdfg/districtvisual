package com.district.visual.module.hud;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class InventoryHudModule extends Module {
    public static int x = 2;
    public static int y = 2;
    public static float scale = 1.0f;

    public InventoryHudModule() {
        super("InventoryHUD", "Show inventory items on screen", Category.HUD);
        enabled = true;
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        var inv = mc.player.getInventory();

        // Armor (top to bottom: helmet, chest, legs, boots)
        String[] armorLabels = {"H", "C", "L", "B"};
        int[] armorSlots = {39, 38, 37, 36};
        for (int i = 0; i < 4; i++) {
            ItemStack stack = inv.getStack(armorSlots[i]);
            if (!stack.isEmpty()) {
                ctx.drawItem(stack, x, y + i * 18);
                ctx.drawItemInSlot(mc.textRenderer, stack, x, y + i * 18);
            }
        }

        // Offhand
        ItemStack offhand = mc.player.getOffHandStack();
        if (!offhand.isEmpty()) {
            ctx.drawItem(offhand, x, y + 5 * 18);
            ctx.drawItemInSlot(mc.textRenderer, offhand, x, y + 5 * 18);
        }
    }
}
