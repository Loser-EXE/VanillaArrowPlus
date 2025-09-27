package com.loserexe.vanillaarrowplus.mixin;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderers.class)
public class EntityRenderersMixin {
    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererFactory<T> factory, CallbackInfo ci) {
        if(type == EntityType.SPECTRAL_ARROW) {
            ci.cancel();
        }
    }
}
