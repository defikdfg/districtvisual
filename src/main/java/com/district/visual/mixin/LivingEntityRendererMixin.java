package com.district.visual.mixin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    // Hit color handled via entity overlay color in renderer
}
