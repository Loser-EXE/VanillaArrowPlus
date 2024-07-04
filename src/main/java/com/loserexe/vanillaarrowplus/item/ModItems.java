package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.projectile.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    private static final List<Item> arrows = new ArrayList<>();

    public static final Item AMETHYST_ARROW = register("amethyst_arrow", new ModdedArrowItem<>(AmethystArrowEntity.class));
    public static final Item BLAZING_ARROW = register("blazing_arrow", new ModdedArrowItem<>(BlazingArrowEntity.class));
    public static final Item CARROT_ARROW = register("carrot_arrow", new ModdedArrowItem<>(CarrotArrowEntity.class));
    public static final Item ECHO_ARROW = register("echo_arrow", new ModdedArrowItem<>(EchoArrowEntity.class));
    public static final Item GOLD_ARROW = register("gold_arrow", new ModdedArrowItem<>(GoldArrowEntity.class));
    public static final Item HONEY_ARROW = register("honey_arrow", new ModdedArrowItem<>(HoneyArrowEntity.class));
    public static final Item IRON_ARROW = register("iron_arrow", new ModdedArrowItem<>(IronArrowEntity.class));
    public static final Item QUARTZ_ARROW = register("quartz_arrow", new ModdedArrowItem<>(QuartzArrowEntity.class));
    public static final Item REDSTONE_ARROW = register("redstone_arrow", new ModdedArrowItem<>(RedstoneArrowEntity.class));

    private static Item register(String name, Item item) {
        Item registeredItem = Registry.register(Registries.ITEM, Identifier.of(VanillaArrowPlus.MOD_ID, name), item);
        arrows.add(registeredItem);
        return registeredItem;
    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering Items!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> arrows.forEach(content::add));
    }
}
