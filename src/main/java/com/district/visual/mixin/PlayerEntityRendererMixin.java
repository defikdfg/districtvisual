package com.district.visual.mixin;

import com.district.visual.DistrictVisualClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.render.entity.PlayerEntityRenderer;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "render", at = @At("TAIL"), require = 0)
    private void onRender(CallbackInfo ci) {
        var mm = DistrictVisualClient.getInstance().getModuleManager();
        if (!mm.angelWings.isEnabled()) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world != null && mc.player != null) {
            var pos = mc.player.getPos();
            long t = System.currentTimeMillis();
            if (t % 100 < 50) {
                mc.world.addParticle(ParticleTypes.END_ROD,
                    pos.x - 0.4, pos.y + 1.2, pos.z, 0, 0.02, 0);
                mc.world.addParticle(ParticleTypes.END_ROD,
                    pos.x + 0.4, pos.y + 1.2, pos.z, 0, 0.02, 0);
            }
        }
    }
}
