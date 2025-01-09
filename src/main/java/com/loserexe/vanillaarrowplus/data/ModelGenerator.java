package com.loserexe.vanillaarrowplus.data;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.client.render.item.property.select.RangedWeaponProjectileTypeProperty;
import com.loserexe.vanillaarrowplus.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.client.render.item.property.numeric.CrossbowPullProperty;
import net.minecraft.client.render.item.property.numeric.UseDurationProperty;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.loserexe.vanillaarrowplus.client.render.item.property.select.RangedWeaponProjectileTypeProperty.ProjectileType;

@Environment(EnvType.CLIENT)
public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.AERIAL_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZING_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARROT_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHARGED_COPPER_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.ECHO_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLD_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.IRON_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.PRISMARINE_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.REDSTONE_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLIME_ARROW, Models.GENERATED);

        ItemModel.Unbaked emptyCrossbow = ItemModels.basic(ModelIds.getItemModelId(Items.CROSSBOW));
        ItemModel.Unbaked crossbowPullingStage0 = ItemModels.basic(itemModelGenerator.registerSubModel(Items.CROSSBOW, "_pulling_0", Models.CROSSBOW));
        ItemModel.Unbaked crossbowPullingStage1 = ItemModels.basic(itemModelGenerator.registerSubModel(Items.CROSSBOW, "_pulling_1", Models.CROSSBOW));
        ItemModel.Unbaked crossbowPullingStage2 = ItemModels.basic(itemModelGenerator.registerSubModel(Items.CROSSBOW, "_pulling_2", Models.CROSSBOW));
        List<SelectItemModel.SwitchCase<ProjectileType>> crossbowSwtichCase = new ArrayList<>();
        crossbowSwtichCase.add(getCrossbowSwtichCase(ProjectileType.ARROW, true, itemModelGenerator.modelCollector));
        crossbowSwtichCase.add(getCrossbowSwtichCase(ProjectileType.FIREWORK_ROCKET, true, itemModelGenerator.modelCollector));
        crossbowSwtichCase.add(getCrossbowSwtichCase(ProjectileType.SPECTRAL_ARROW, false, itemModelGenerator.modelCollector));
        for (Item item : ModItems.arrows) {
            ProjectileType type = ProjectileType.valueOf(Registries.ITEM.getId(item).getPath().toUpperCase());
            crossbowSwtichCase.add(getCrossbowSwtichCase(type, false, itemModelGenerator.modelCollector));
        }
        ItemModel.Unbaked crossbowModel = ItemModels.condition(
                ItemModels.usingItemProperty(),
                ItemModels.rangeDispatch(
                        new CrossbowPullProperty(),
                        crossbowPullingStage0,
                        ItemModels.rangeDispatchEntry(crossbowPullingStage1, 0.58F),
                        ItemModels.rangeDispatchEntry(crossbowPullingStage2, 1.0F)),
                ItemModels.select(
                        new RangedWeaponProjectileTypeProperty(),
                        emptyCrossbow,
                        crossbowSwtichCase.stream().toList()));
        itemModelGenerator.output.accept(Items.CROSSBOW, crossbowModel);

        ItemModel.Unbaked emptyBow = ItemModels.basic(ModelIds.getItemModelId(Items.BOW));
        List<SelectItemModel.SwitchCase<ProjectileType>> bowSwtichCase = new ArrayList<>();
        bowSwtichCase.add(getBowSwtichCase(ProjectileType.ARROW, true, itemModelGenerator.modelCollector));
        bowSwtichCase.add(getBowSwtichCase(ProjectileType.SPECTRAL_ARROW, false, itemModelGenerator.modelCollector));
        for (Item item : ModItems.arrows) {
            ProjectileType type = ProjectileType.valueOf(Registries.ITEM.getId(item).getPath().toUpperCase());
            bowSwtichCase.add(getBowSwtichCase(type, false, itemModelGenerator.modelCollector));
        }
        ItemModel.Unbaked bowModel = ItemModels.condition(
                ItemModels.usingItemProperty(),
                ItemModels.select(
                        new RangedWeaponProjectileTypeProperty(),
                        emptyBow,
                        bowSwtichCase.stream().toList()
                ),
                emptyBow);

        itemModelGenerator.output.accept(Items.BOW, bowModel);
    }

    private SelectItemModel.SwitchCase<ProjectileType> getCrossbowSwtichCase(ProjectileType type, boolean vanilla, BiConsumer<Identifier, ModelSupplier> modelCollector) {
        String suffix = "_" + type.asString();
        Identifier modelIdentifier = registerSubModel(Models.CROSSBOW, Items.CROSSBOW, suffix, vanilla, modelCollector);
        return ItemModels.switchCase(type, ItemModels.basic(modelIdentifier));
    }

    private SelectItemModel.SwitchCase<ProjectileType> getBowSwtichCase(ProjectileType type, boolean vanilla, BiConsumer<Identifier, ModelSupplier> modelCollector) {
        String arrowName = type != ProjectileType.ARROW ? "_" + type.asString() : "";
        ItemModel.Unbaked bowPullingStatge0 = ItemModels.basic(registerSubModel(Models.BOW, Items.BOW, arrowName + "_pulling_0", vanilla, modelCollector));
        ItemModel.Unbaked bowPullingStage1 = ItemModels.basic(registerSubModel(Models.BOW, Items.BOW, arrowName + "_pulling_1", vanilla, modelCollector));
        ItemModel.Unbaked bowPullingStage2 = ItemModels.basic(registerSubModel(Models.BOW, Items.BOW, arrowName + "_pulling_2", vanilla, modelCollector));
        return ItemModels.switchCase(type, ItemModels.rangeDispatch(
                new UseDurationProperty(false),
                0.05F,
                bowPullingStatge0,
                ItemModels.rangeDispatchEntry(bowPullingStage1, 0.65F),
                ItemModels.rangeDispatchEntry(bowPullingStage2, 0.9F)
        ));
    }

    private Identifier registerSubModel(Model model, Item item, String suffix, boolean vanilla, BiConsumer<Identifier, ModelSupplier> modelCollector) {
        Identifier modelIdentifier = ModelIds.getItemSubModelId(item, suffix);
        if (!vanilla) {
            modelIdentifier = Identifier.of(VanillaArrowPlus.MOD_ID, modelIdentifier.getPath());
        }
        return model.upload(modelIdentifier, TextureMap.layer0(modelIdentifier), modelCollector);
    }
}
