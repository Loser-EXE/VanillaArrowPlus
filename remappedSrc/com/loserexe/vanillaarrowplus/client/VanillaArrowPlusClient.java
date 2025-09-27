package com.loserexe.vanillaarrowplus.client;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.client.render.entity.AmethystShardRenderer;
import com.loserexe.vanillaarrowplus.client.render.entity.ModdedArrowRenderer;
import com.loserexe.vanillaarrowplus.client.render.item.property.numeric.BowPullProperty;
import com.loserexe.vanillaarrowplus.client.render.item.property.select.RangedWeaponProjectileTypeProperty;
import com.loserexe.vanillaarrowplus.client.render.item.tint.RangedWeaponTintSource;
import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.screen.FletchingTableScreen;
import com.loserexe.vanillaarrowplus.screen.LightningRodScreen;
import com.loserexe.vanillaarrowplus.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.item.property.numeric.NumericProperties;
import net.minecraft.client.render.item.property.select.SelectProperties;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class VanillaArrowPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntityTypes.AERIAL_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.AMETHYST_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BLAZING_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CARROT_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.COPPER_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CHARGED_COPPER_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ECHO_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.GOLD_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.HONEY_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.IRON_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.PRISMARINE_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.REDSTONE_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SLIME_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(EntityType.SPECTRAL_ARROW, ModdedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.AMETHYST_SHARD, AmethystShardRenderer::new);

        HandledScreens.register(ModScreenHandlers.FLETCHING_TABLE, FletchingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.LIGHTNING_ROD, LightningRodScreen::new);

        SelectProperties.ID_MAPPER.put(Identifier.of(VanillaArrowPlus.MOD_ID, "ranged_weapon_projectile_type_property"), RangedWeaponProjectileTypeProperty.TYPE);
        TintSourceTypes.ID_MAPPER.put(Identifier.of(VanillaArrowPlus.MOD_ID, "ranged_weapon_tint_source"), RangedWeaponTintSource.CODEC);
        NumericProperties.ID_MAPPER.put(Identifier.of(VanillaArrowPlus.MOD_ID, "bow_pull_property"), BowPullProperty.CODEC);
    }
}
