package com.district.visual.mixin;

import com.district.visual.DistrictVisualClient;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "render(Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("TAIL"))
    private void onRender(PlayerEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vcp, int light, CallbackInfo ci) {
        var mm = DistrictVisualClient.getInstance().getModuleManager();
        if (!mm.angelWings.isEnabled()) return;
        net.minecraft.client.MinecraftClient mc = net.minecraft.client.MinecraftClient.getInstance();
        if (mc.world != null && mc.player != null) {
            var pos = mc.player.getPos();
            long t = System.currentTimeMillis();
            if (t % 100 < 50) {
                mc.world.addParticle(net.minecraft.particle.ParticleTypes.END_ROD,
                    pos.x - 0.4, pos.y + 1.2 + Math.sin(t / 500.0) * 0.1, pos.z,
                    (Math.random() - 0.5) * 0.05, 0.02, (Math.random() - 0.5) * 0.05);
                mc.world.addParticle(net.minecraft.particle.ParticleTypes.END_ROD,
                    pos.x + 0.4, pos.y + 1.2 + Math.sin(t / 500.0) * 0.1, pos.z,
                    (Math.random() - 0.5) * 0.05, 0.02, (Math.random() - 0.5) * 0.05);
            }
        }
    }
}
