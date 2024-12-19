package com.loserexe.vanillaarrowplus.data;

import com.loserexe.vanillaarrowplus.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

@Environment(EnvType.CLIENT)
public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.AERIAL_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZING_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARROT_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ECHO_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.PRISMARINE_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.REDSTONE_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLIME_ARROW, Models.GENERATED);
    }
}
