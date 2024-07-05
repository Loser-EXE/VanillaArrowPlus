package com.loserexe.vanillaarrowplus.client;

import com.loserexe.vanillaarrowplus.client.render.entity.VanillaArrowPlusArrowRenderer;
import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class VanillaArrowPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntityTypes.REDSTONE_ARROW, (VanillaArrowPlusArrowRenderer::new));
    }
}
