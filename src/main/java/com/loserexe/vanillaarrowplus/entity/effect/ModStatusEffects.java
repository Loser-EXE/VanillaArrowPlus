package com.loserexe.vanillaarrowplus.entity.effect;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final RegistryEntry.Reference<StatusEffect> HONEYED =
            Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(
                    VanillaArrowPlus.MOD_ID, "honeyed"),
                    new HoneyedStatusEffect());

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering StatusEffects!");
    }
}
