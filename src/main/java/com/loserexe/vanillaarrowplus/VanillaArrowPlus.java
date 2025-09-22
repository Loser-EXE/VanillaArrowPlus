package com.loserexe.vanillaarrowplus;

import com.loserexe.vanillaarrowplus.block.ModBlocks;
import com.loserexe.vanillaarrowplus.block.entity.ModBlockEntityTypes;
import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.effect.ModStatusEffects;
import com.loserexe.vanillaarrowplus.item.ModItems;
import com.loserexe.vanillaarrowplus.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaArrowPlus implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("VanillaArrowPlus");
    public static final String MOD_ID = "vanillaarrowplus";
    public static final TagKey<Item> CROSSBOW_ARROWS = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "crossbow_arrows"));

    @Override
    public void onInitialize() {
        ModEntityTypes.register();
        ModItems.register();
        ModBlocks.register();
        ModStatusEffects.register();
        ModBlockEntityTypes.register();
        ModScreenHandlers.register();
    }
}
