package com.loserexe.vanillaarrowplus.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;

public class HoneyedStatusEffect extends StatusEffect {
    protected HoneyedStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 8889187, ParticleTypes.FALLING_HONEY);
    }
}
