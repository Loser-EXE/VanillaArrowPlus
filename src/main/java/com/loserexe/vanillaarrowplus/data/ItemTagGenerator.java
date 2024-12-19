package com.loserexe.vanillaarrowplus.data;

import com.loserexe.vanillaarrowplus.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider{

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(ModItems.AERIAL_ARROW)
                .add(ModItems.AMETHYST_ARROW)
                .add(ModItems.BLAZING_ARROW)
                .add(ModItems.CARROT_ARROW)
                .add(ModItems.ECHO_ARROW)
                .add(ModItems.GOLD_ARROW)
                .add(ModItems.HONEY_ARROW)
                .add(ModItems.IRON_ARROW)
                .add(ModItems.PRISMARINE_ARROW)
                .add(ModItems.REDSTONE_ARROW)
                .add(ModItems.SLIME_ARROW);
    }
}
