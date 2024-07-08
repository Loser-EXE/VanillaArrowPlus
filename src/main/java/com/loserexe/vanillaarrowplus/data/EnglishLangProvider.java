package com.loserexe.vanillaarrowplus.data;

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
        translationBuilder.add(ModItems.ECHO_ARROW, "Echo Arrow");
        translationBuilder.add(ModItems.GOLD_ARROW, "Gold Arrow");
        translationBuilder.add(ModItems.HONEY_ARROW, "Honey Arrow");
        translationBuilder.add(ModItems.IRON_ARROW, "Iron Arrow");
        translationBuilder.add(ModItems.PRISMARINE_ARROW, "Prismarine Arrow");
        translationBuilder.add(ModItems.QUARTZ_ARROW, "Quartz Arrow");
        translationBuilder.add(ModItems.REDSTONE_ARROW, "Redstone Arrow");
        translationBuilder.add(ModItems.SLIME_ARROW, "Slime Arrow");

        translationBuilder.add(ModStatusEffects.HONEYED.value(), "Honeyed");

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/vanillaarrowplus/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
