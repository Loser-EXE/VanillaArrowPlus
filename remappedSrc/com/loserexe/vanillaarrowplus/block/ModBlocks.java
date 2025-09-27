package com.loserexe.vanillaarrowplus.block;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block POWERED_AIR = register("powered_air", new PoweredAirBlock(AbstractBlock.Settings.create()
            .replaceable()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(VanillaArrowPlus.MOD_ID, "powered_air")))));

    private static Block register(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(VanillaArrowPlus.MOD_ID, name), block);
    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering Blocks!");
    }
}
