package com.loserexe.vanillaarrowplus.data;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.client.render.item.property.select.RangedWeaponProjectileTypeProperty;
import com.loserexe.vanillaarrowplus.client.render.item.tint.RangedWeaponTintSource;
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

        Identifier crossbowTipped = registerTippedArrowStage("", Items.CROSSBOW, Models.CROSSBOW, itemModelGenerator.modelCollector);
        crossbowSwtichCase.add(ItemModels.switchCase(ProjectileType.TIPPED_ARROW, ItemModels.tinted(crossbowTipped, new RangedWeaponTintSource())));

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

        Identifier bowTippedArrowStage0Id = registerTippedArrowStage("_pulling_0", Items.BOW, Models.BOW, itemModelGenerator.modelCollector);
        ItemModel.Unbaked bowTippedArrowStage0 = ItemModels.tinted(bowTippedArrowStage0Id, new RangedWeaponTintSource());
        Identifier bowTippedArrowStage1Id = registerTippedArrowStage("_pulling_1", Items.BOW, Models.BOW, itemModelGenerator.modelCollector);
        ItemModel.Unbaked bowTippedArrowStage1 = ItemModels.tinted(bowTippedArrowStage1Id, new RangedWeaponTintSource());
        Identifier bowTippedArrowStage2Id = registerTippedArrowStage("_pulling_2", Items.BOW, Models.BOW, itemModelGenerator.modelCollector);
        ItemModel.Unbaked bowTippedArrowStage2 = ItemModels.tinted(bowTippedArrowStage2Id, new RangedWeaponTintSource());

         bowSwtichCase.add(ItemModels.switchCase(ProjectileType.TIPPED_ARROW, ItemModels.rangeDispatch(
                 new UseDurationProperty(false),
                 0.05F,
                 bowTippedArrowStage0,
                 ItemModels.rangeDispatchEntry(bowTippedArrowStage1, 0.65F),
                 ItemModels.rangeDispatchEntry(bowTippedArrowStage2, 0.9F)
        )));

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

    private Identifier registerTippedArrowStage(String suffix, Item item, Model model, BiConsumer<Identifier, ModelSupplier> modelCollector) {
        Identifier tippedArrowModel = getItemSubModelIdLocalNameSpace(item, "_tipped_arrow" + suffix);
        Identifier tippedArrowModelBase = getItemSubModelIdLocalNameSpace(item, "_tipped_arrow_base" + suffix);
        Identifier tippedArrowModelHead = getItemSubModelIdLocalNameSpace(item, "_tipped_arrow_head" + suffix);
        return model.upload(tippedArrowModel, TextureMap.layer0(tippedArrowModelHead).register(TextureKey.LAYER1, tippedArrowModelBase), modelCollector);
    }

    private Identifier registerSubModel(Model model, Item item, String suffix, boolean vanilla, BiConsumer<Identifier, ModelSupplier> modelCollector) {
        Identifier modelIdentifier = vanilla ? ModelIds.getItemSubModelId(item, suffix) : getItemSubModelIdLocalNameSpace(item, suffix);
        return model.upload(modelIdentifier, TextureMap.layer0(modelIdentifier), modelCollector);
    }

    public static Identifier getItemSubModelIdLocalNameSpace(Item item, String suffix) {
        Identifier identifier = Identifier.of(VanillaArrowPlus.MOD_ID, Registries.ITEM.getId(item).getPath());
        return identifier.withPath((path) -> "item/" + path + suffix);
    }
}
