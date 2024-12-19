package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> arrows = new ArrayList<>();

    public static final Item AERIAL_ARROW = register("aerial_arrow", ModdedArrowItem::new, AerialArrowEntity.class, ModEntityTypes.AERIAL_ARROW);
    public static final Item AMETHYST_ARROW = register("amethyst_arrow", ModdedArrowItem::new, AmethystArrowEntity.class, ModEntityTypes.AMETHYST_ARROW);
    public static final Item BLAZING_ARROW = register("blazing_arrow", ModdedArrowItem::new, BlazingArrowEntity.class, ModEntityTypes.BLAZING_ARROW);
    public static final Item CARROT_ARROW = register("carrot_arrow", ModdedArrowItem::new, CarrotArrowEntity.class, ModEntityTypes.CARROT_ARROW);
    public static final Item ECHO_ARROW = register("echo_arrow", ModdedArrowItem::new, EchoArrowEntity.class, ModEntityTypes.ECHO_ARROW);
    public static final Item GOLD_ARROW = register("gold_arrow", ModdedArrowItem::new, GoldArrowEntity.class, ModEntityTypes.GOLD_ARROW);
    public static final Item HONEY_ARROW = register("honey_arrow", ModdedArrowItem::new, HoneyArrowEntity.class, ModEntityTypes.HONEY_ARROW);
    public static final Item IRON_ARROW = register("iron_arrow", ModdedArrowItem::new, IronArrowEntity.class, ModEntityTypes.IRON_ARROW);
    public static final Item PRISMARINE_ARROW = register("prismarine_arrow", ModdedArrowItem::new, PrismarineArrowEntity.class, ModEntityTypes.PRISMARINE_ARROW);
    public static final Item REDSTONE_ARROW = register("redstone_arrow", ModdedArrowItem::new, RedstoneArrowEntity.class, ModEntityTypes.REDSTONE_ARROW);
    public static final Item SLIME_ARROW = register("slime_arrow", ModdedArrowItem::new, SlimeArrowEntity.class, ModEntityTypes.SLIME_ARROW);


    private static Item register(String name, ItemBuilder builder, Class<? extends ModdedPersistentProjectileEntity> entityClass, EntityType<? extends ModdedPersistentProjectileEntity> entityType) {
        Item.Settings settings = new Item.Settings();
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VanillaArrowPlus.MOD_ID, name));
        settings.registryKey(key);
        Item item = builder.create(entityClass, entityType, settings);
        Item registeredItem = Registry.register(Registries.ITEM, key, item);
        DispenserBlock.registerProjectileBehavior(item);
        arrows.add(registeredItem);
        return registeredItem;
    }

    private interface ItemBuilder {
        ModdedArrowItem create(Class<? extends ModdedPersistentProjectileEntity> entityClass, EntityType<? extends ModdedPersistentProjectileEntity> entityType, Item.Settings settings);
    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering Items!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> arrows.forEach(content::add));
    }
}
