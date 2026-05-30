package com.district.visual.mixin;

import com.district.visual.gui.MacTitleScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @Inject(method = "initWidgetsNormal", at = @At("HEAD"), cancellable = true)
    private void onInitWidgets(CallbackInfo ci) {
        net.minecraft.client.MinecraftClient mc = net.minecraft.client.MinecraftClient.getInstance();
        mc.setScreen(new MacTitleScreen());
        ci.cancel();
    }
}
