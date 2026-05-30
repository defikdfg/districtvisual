package com.district.visual.module.visual;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;

public class MechvibesModule extends Module {
    public static float volume = 0.3f;
    public static float pitch = 1.0f;
    private static long lastSound = 0;

    public MechvibesModule() {
        super("Mechvibes", "Mechanical keyboard sounds on keystroke", Category.VISUAL);
        enabled = true;
    }

    public static void onKeyPress(MinecraftClient client) {
        if (client.player == null) return;
        long now = System.currentTimeMillis();
        if (now - lastSound < 30) return;
        lastSound = now;
        float p = 0.9f + (float)(Math.random() * 0.2f);
        client.player.playSound(SoundEvents.BLOCK_STONE_HIT, volume, pitch * p);
    }
}
