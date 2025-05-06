package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;


public class ModScreenHandlers {
    public static final ScreenHandlerType<FletchingTableScreenHandler> FLETCHING_TABLE = register(FletchingTableScreenHandler::new, "fletching_table");
    public static final ScreenHandlerType<LightningRodScreenHandler> LIGHTNING_ROD = register(LightningRodScreenHandler::new, "lightning_rod");

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(ScreenHandlerType.Factory<T> screenHandlerFactory, String path) {
        return Registry.register(
                Registries.SCREEN_HANDLER,
                Identifier.of(VanillaArrowPlus.MOD_ID, path),
                new ScreenHandlerType<>(screenHandlerFactory, FeatureFlags.VANILLA_FEATURES)
        );
    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Regisering Screens!");
    }
}
