package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreens {
    public static ScreenHandlerType<FletchingTableScreenHandler> FLETCHING_TABLE = Registry.register(
            Registries.SCREEN_HANDLER,
            Identifier.of(VanillaArrowPlus.MOD_ID, "fletching_table")
            , new ScreenHandlerType<>(FletchingTableScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Regisering Screens!");
    }
}
