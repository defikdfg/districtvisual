package com.district.visual.module.combat;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;

public class HitParticlesModule extends Module {
    public static int particleCount = 8;
    public static float spread = 0.3f;

    public HitParticlesModule() {
        super("HitParticles", "Spawn particles when hitting an entity", Category.COMBAT);
        enabled = true;
    }

    public static void spawnParticles(MinecraftClient client, Entity target, boolean isCrit) {
        if (client.world == null || target == null) return;
        Vec3d pos = target.getPos().add(0, target.getHeight() / 2.0, 0);
        int count = isCrit ? particleCount * 2 : particleCount;
        for (int i = 0; i < count; i++) {
            double vx = (Math.random() - 0.5) * spread;
            double vy = (Math.random() - 0.5) * spread;
            double vz = (Math.random() - 0.5) * spread;
            client.world.addParticle(
                isCrit ? ParticleTypes.CRIT : ParticleTypes.DAMAGE_INDICATOR,
                pos.x, pos.y, pos.z, vx, vy, vz
            );
        }
    }
}
