package com.district.visual.module.combat;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;

public class AutoSwapModule extends Module {
    public static boolean swapToTotem = true;

    public AutoSwapModule() {
        super("AutoSwap", "Auto switch to totem when low health", Category.COMBAT);
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (client.player == null) return;
        var inv = client.player.getInventory();
        if (swapToTotem && client.player.getHealth() < 8f) {
            for (int i = 0; i < 9; i++) {
                if (inv.getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
                    inv.selectedSlot = i;
                    return;
                }
            }
        }
    }
}
