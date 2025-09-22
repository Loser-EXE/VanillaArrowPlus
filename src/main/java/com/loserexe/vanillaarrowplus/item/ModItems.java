package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static final List<Item> arrows = new ArrayList<>();
    public static final List<Item> normal_arrows = new ArrayList<>();
    public static final List<Item> crossbow_arrows = new ArrayList<>();

    public static final Item AERIAL_ARROW = registerArrow("aerial_arrow", AerialArrowItem::new, true);
    public static final Item AMETHYST_ARROW = registerArrow("amethyst_arrow", AmethystArrowItem::new);
    public static final Item BLAZING_ARROW = registerArrow("blazing_arrow", BlazingArrowItem::new, true);
    public static final Item CARROT_ARROW = registerArrow("carrot_arrow", CarrotArrowItem::new);
    public static final Item COPPER_ARROW = registerArrow("copper_arrow", CopperArrowItem::new);
    public static final Item CHARGED_COPPER_ARROW = registerArrow("charged_copper_arrow", ChargedCopperArrowItem::new);
    public static final Item ECHO_ARROW = registerArrow("echo_arrow", EchoArrowItem::new,true);
    public static final Item GOLD_ARROW = registerArrow("gold_arrow", GoldArrowItem::new);
    public static final Item HONEY_ARROW = registerArrow("honey_arrow", HoneyArrowItem::new);
    public static final Item IRON_ARROW = registerArrow("iron_arrow", IronArrowItem::new);
    public static final Item PRISMARINE_ARROW = registerArrow("prismarine_arrow", PrismarineArrowItem::new);
    public static final Item REDSTONE_ARROW = registerArrow("redstone_arrow", RedstoneArrowItem::new);
    public static final Item SLIME_ARROW = registerArrow("slime_arrow", SlimeArrowItem::new);

    private static Item registerArrow(String name, Function<Item.Settings, Item> builder) {
        return registerArrow(name, builder, false);
    }

    private static Item registerArrow(String name, Function<Item.Settings, Item> builder, boolean crossbowExclusive) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VanillaArrowPlus.MOD_ID, name));
        Item item = Items.register(key, builder);
        DispenserBlock.registerProjectileBehavior(item);
        arrows.add(item);
        if (crossbowExclusive) {
            crossbow_arrows.add(item);
        } else {
            normal_arrows.add(item);
        }
        return item;
    }

//    private static Item register(String name, Class<? extends ModdedPersistentProjectileEntity> entityClass, EntityType<? extends ModdedPersistentProjectileEntity> entityType, float chargeTimeMultipler) {
//        return  register(name, entityClass, entityType, chargeTimeMultipler, false);
//    }

//    private static Item register(String name, Class<? extends ModdedPersistentProjectileEntity> entityClass, EntityType<? extends ModdedPersistentProjectileEntity> entityType, float chargeTimeMultiplier, boolean crossbowExclusive) {
//        Item.Settings settings = new Item.Settings();
//        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VanillaArrowPlus.MOD_ID, name));
//        settings.registryKey(key);
//        ItemBuilder builder = ModdedArrowItem::new;
//        Item item = builder.create(entityClass, entityType, settings);
//        Item registeredItem = Registry.register(Registries.ITEM, key, item);
//        DispenserBlock.registerProjectileBehavior(item);
//        arrows.add(registeredItem);
//        if (crossbowExclusive) {
//            crossbow_arrows.add(registeredItem);
//        } else {
//            normal_arrows.add(registeredItem);
//        }
//        return registeredItem;
//    }

//    private interface ItemBuilder {
//        ModdedArrowItem create(Class<? extends ModdedPersistentProjectileEntity> entityClass, EntityType<? extends ModdedPersistentProjectileEntity> entityType, Item.Settings settings);
//    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering Items!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> arrows.forEach(content::add));
    }
}
