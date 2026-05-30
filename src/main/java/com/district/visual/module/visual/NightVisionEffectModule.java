package com.district.visual.module.visual;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class NightVisionEffectModule extends Module {
    public NightVisionEffectModule() {
        super("NightVision", "Green-tinted night vision effect", Category.VISUAL);
    }

    @Override
    public void onTick(MinecraftClient mc) {
        if (mc.player == null) return;
        if (!mc.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
            mc.player.addStatusEffect(
                new StatusEffectInstance(StatusEffects.NIGHT_VISION, 999999, 0, false, false)
            );
        }
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }
}
