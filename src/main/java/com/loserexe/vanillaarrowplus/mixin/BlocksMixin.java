package com.loserexe.vanillaarrowplus.mixin;

import com.loserexe.vanillaarrowplus.block.ModdedFletchingTable;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(Blocks.class)
public abstract class BlocksMixin {
    // I realized I way overcomplicated this because I didn't understand how block entities worked at the time.
    // I'm too lazy to fix my mistake so im just going to leave it here till it starts causing issues.
    @Redirect(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;"))
    private static Block registerRedirect(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        if (id.equals("fletching_table")) {
            factory = ModdedFletchingTable::new;
        }
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(id));
        Block block = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.BLOCK, key, block);
    }
}
