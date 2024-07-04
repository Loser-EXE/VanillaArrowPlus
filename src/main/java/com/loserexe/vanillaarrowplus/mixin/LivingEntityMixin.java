package com.loserexe.vanillaarrowplus.mixin;

import com.loserexe.vanillaarrowplus.entity.effect.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    @Shadow protected abstract float getJumpVelocity(float strength);

    @Inject(method = "getJumpVelocity()F", at = @At("HEAD"), cancellable = true)
    public void getJumpVelocity(CallbackInfoReturnable<Float> cir) {
        if(this.hasStatusEffect(ModStatusEffects.HONEYED)) {
            cir.setReturnValue(this.getJumpVelocity(0.5f));
        }
    }
}
