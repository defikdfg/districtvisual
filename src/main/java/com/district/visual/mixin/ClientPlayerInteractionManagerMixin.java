package com.district.visual.mixin;

import com.district.visual.DistrictVisualClient;
import com.district.visual.module.combat.DamageNumbersModule;
import com.district.visual.module.combat.HitParticlesModule;
import com.district.visual.module.combat.HitSoundModule;
import com.district.visual.module.hud.KeystrokesModule;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Inject(method = "attackEntity", at = @At("HEAD"))
    private void onAttackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
        var mm = DistrictVisualClient.getInstance().getModuleManager();
        net.minecraft.client.MinecraftClient mc = net.minecraft.client.MinecraftClient.getInstance();
        boolean isCrit = player.fallDistance > 0 && !player.isOnGround()
            && !player.isClimbing() && !player.isTouchingWater();

        if (target instanceof LivingEntity le) {
            if (mm.hitParticles.isEnabled()) HitParticlesModule.spawnParticles(mc, le, isCrit);
            if (mm.damageNumbers.isEnabled()) DamageNumbersModule.addNumber(le, 5f, isCrit);
            if (mm.hitSound.isEnabled()) HitSoundModule.playHit(mc, isCrit);
        }
        KeystrokesModule.onLMB();
    }

    @Inject(method = "interactItem", at = @At("HEAD"))
    private void onInteractItem(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        KeystrokesModule.onRMB();
    }

    @Inject(method = "interactBlock", at = @At("HEAD"))
    private void onInteractBlock(net.minecraft.client.network.ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        KeystrokesModule.onRMB();
    }
}
