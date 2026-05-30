package com.district.visual.mixin;

import com.district.visual.DistrictVisualClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext ctx, RenderTickCounter counter, CallbackInfo ci) {
        if (DistrictVisualClient.getInstance() != null) {
            DistrictVisualClient.getInstance().getModuleManager().onRenderHud(ctx, counter.getTickDelta(true));
        }
    }
}
