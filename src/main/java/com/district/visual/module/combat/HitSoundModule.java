package com.district.visual.module.combat;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;

public class HitSoundModule extends Module {
    public static float volume = 1.0f;
    public static float pitch = 1.8f;
    public static boolean critSound = true;

    public HitSoundModule() {
        super("HitSound", "Custom sound on hit", Category.COMBAT);
        enabled = true;
    }

    public static void playHit(MinecraftClient client, boolean isCrit) {
        if (client.player == null) return;
        float p = isCrit ? pitch * 1.3f : pitch;
        client.player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, volume, p);
    }
}
