package com.loserexe.vanillaarrowplus.block.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.block.ModdedFletchingTable;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {
    public static final BlockEntityType<FletchingTableBlockEntity> FLETCHING_TABLE = register("fletching_table", FletchingTableBlockEntity::new, Blocks.FLETCHING_TABLE);

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(VanillaArrowPlus.MOD_ID, id),
                FabricBlockEntityTypeBuilder.create(factory, block).build());
    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering BlockEntityTypes!");
    }
}
