package com.loserexe.vanillaarrowplus;

import com.loserexe.vanillaarrowplus.block.ModBlocks;
import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.effect.ModStatusEffects;
import com.loserexe.vanillaarrowplus.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaArrowPlus implements ModInitializer{
    public static final Logger LOGGER = LoggerFactory.getLogger("VanillaArrowPlus");
    public static final String MOD_ID = "vanillaarrowplus";

    @Override
    public void onInitialize() {
        ModEntityTypes.register();
        ModItems.register();
        ModBlocks.register();
        ModStatusEffects.register();
    }
}
