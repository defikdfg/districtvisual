package com.district.visual.module.visual;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;

public class TrailEffectModule extends Module {
    public static int color = 0xAA7B61FF;
    public static float density = 1.0f;

    public TrailEffectModule() {
        super("TrailEffect", "Particle trail behind player", Category.VISUAL);
        enabled = false;
    }

    @Override
    public void onTick(MinecraftClient mc) {
        if (mc.player == null || mc.world == null) return;
        if (mc.player.isSneaking()) return;
        var pos = mc.player.getPos();
        mc.world.addParticle(ParticleTypes.END_ROD,
            pos.x + (Math.random() - 0.5) * 0.3,
            pos.y + 0.1,
            pos.z + (Math.random() - 0.5) * 0.3,
            0, 0.02, 0);
    }
}
