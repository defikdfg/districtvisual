package com.district.visual.mixin;

import com.district.visual.DistrictVisualClient;
import com.district.visual.module.combat.TotemAnimationModule;
import com.district.visual.module.hud.DynamicIslandModule;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "handleStatus", at = @At("HEAD"))
    private void onStatus(byte status, CallbackInfo ci) {
        // Status 35 = totem of undying activation
        if (status == 35) {
            var mm = DistrictVisualClient.getInstance().getModuleManager();
            if (mm.totemAnimation.isEnabled()) {
                TotemAnimationModule.triggerActivation();
            }
            DynamicIslandModule.push("Totem", "You were saved!", 0xFFFFAA00);
        }
    }
}
