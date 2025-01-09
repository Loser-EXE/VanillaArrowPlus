package com.loserexe.vanillaarrowplus.data;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.effect.ModStatusEffects;
import com.loserexe.vanillaarrowplus.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class EnglishLangProvider extends FabricLanguageProvider {
    protected EnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.AERIAL_ARROW, "Aerial Arrow");
        translationBuilder.add(ModItems.AMETHYST_ARROW, "Amethyst Arrow");
        translationBuilder.add(ModItems.BLAZING_ARROW, "Blazing Arrow");
        translationBuilder.add(ModItems.CARROT_ARROW, "Carrow");
        translationBuilder.add(ModItems.COPPER_ARROW, "Copper Arrow");
        translationBuilder.add(ModItems.CHARGED_COPPER_ARROW, "Charged Copper Arrow");
        translationBuilder.add(ModItems.ECHO_ARROW, "Echo Arrow");
        translationBuilder.add(ModItems.GOLD_ARROW, "Gold Arrow");
        translationBuilder.add(ModItems.HONEY_ARROW, "Honey Arrow");
        translationBuilder.add(ModItems.IRON_ARROW, "Iron Arrow");
        translationBuilder.add(ModItems.PRISMARINE_ARROW, "Prismarine Arrow");
        translationBuilder.add(ModItems.REDSTONE_ARROW, "Redstone Arrow");
        translationBuilder.add(ModItems.SLIME_ARROW, "Slime Arrow");

        translationBuilder.add(ModStatusEffects.HONEYED.value(), "Honeyed");

        translationBuilder.add(ModEntityTypes.AERIAL_ARROW, "Aerial Arrow");
        translationBuilder.add(ModEntityTypes.AMETHYST_ARROW, "Amethyst Arrow");
        translationBuilder.add(ModEntityTypes.BLAZING_ARROW, "Blazing Arrow");
        translationBuilder.add(ModEntityTypes.CARROT_ARROW, "Carrow");
        translationBuilder.add(ModEntityTypes.ECHO_ARROW, "Echo Arrow");
        translationBuilder.add(ModEntityTypes.GOLD_ARROW, "Gold Arrow");
        translationBuilder.add(ModEntityTypes.HONEY_ARROW, "Honey Arrow");
        translationBuilder.add(ModEntityTypes.IRON_ARROW, "Iron Arrow");
        translationBuilder.add(ModEntityTypes.PRISMARINE_ARROW, "Prismarine Arrow");
        translationBuilder.add(ModEntityTypes.REDSTONE_ARROW, "Redstone Arrow");
        translationBuilder.add(ModEntityTypes.SLIME_ARROW, "Slime Arrow");

        translationBuilder.add(ModEntityTypes.AMETHYST_SHARD, "Amethyst Shard");

        translationBuilder.add("container.fletching_table", "Fletching Table");

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/vanillaarrowplus/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
