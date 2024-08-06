package com.loserexe.vanillaarrowplus.client;

import com.loserexe.vanillaarrowplus.client.render.entity.VanillaArrowPlusArrowRenderer;
import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;

public class VanillaArrowPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntityTypes.AERIAL_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.AMETHYST_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.BLAZING_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.CARROT_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.ECHO_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.GOLD_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.HONEY_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.IRON_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.PRISMARINE_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.QUARTZ_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.REDSTONE_ARROW, (VanillaArrowPlusArrowRenderer::new));
        EntityRendererRegistry.register(ModEntityTypes.SLIME_ARROW, (VanillaArrowPlusArrowRenderer::new));

        EntityRendererRegistry.register(EntityType.SPECTRAL_ARROW, (VanillaArrowPlusArrowRenderer::new));
    }
}
