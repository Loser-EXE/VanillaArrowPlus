package com.loserexe.vanillaarrowplus.recpie;

import com.loserexe.vanillaarrowplus.item.ModItems;
import com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

import java.util.HashMap;
import java.util.Map;

public class FletchingTableRecipeRegistry {
    private static final Map<Recipie, Result> recipies = new HashMap<>();

    public static boolean isValidFeather(ItemStack stack) {
        Item item = stack.getItem();
        return recipies.entrySet().stream().anyMatch(recipies -> recipies.getKey().feather == item);
    }

    public static boolean isValidShaft(ItemStack stack) {
        Item item = stack.getItem();
        return recipies.entrySet().stream().anyMatch(recipies -> recipies.getKey().shaft == item);
    }

    public static boolean isValidTip(ItemStack stack) {
        Item item = stack.getItem();
        return recipies.entrySet().stream().anyMatch(recipies -> recipies.getKey().tip == item);
    }

    public static boolean isValidUpgrade(ItemStack stack) {
        Item item = stack.getItem();
        return recipies.entrySet().stream().anyMatch(recipies -> recipies.getKey().upgrade == item);
    }

    public static boolean craft(DefaultedList<ItemStack> inventory) {
        try {
            Recipie recipie = new Recipie(
                    inventory.get(FletchingTableScreenHandler.FEATHER_SLOT_INDEX).getItem(),
                    inventory.get(FletchingTableScreenHandler.SHAFT_SLOT_INDEX).getItem(),
                    inventory.get(FletchingTableScreenHandler.TIP_SLOT_INDEX).getItem(),
                    inventory.get(FletchingTableScreenHandler.UPGRADE_SLOT_INDEX).getItem());
            Result result = recipies.get(recipie);
            ItemStack resultStack = new ItemStack(result.item, result.amount);
            inventory.set(FletchingTableScreenHandler.RESULT_SLOT_INDEX, resultStack);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    static {
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.AIR), new Result(Items.ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.GOLD_NUGGET), new Result(ModItems.GOLD_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.IRON_NUGGET), new Result(ModItems.IRON_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.AMETHYST_SHARD, Items.AIR), new Result(ModItems.AMETHYST_ARROW, 16));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.PRISMARINE_SHARD), new Result(ModItems.PRISMARINE_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.QUARTZ, Items.SLIME_BALL), new Result(ModItems.SLIME_ARROW, 1));
        recipies.put(new Recipie(Items.FEATHER, Items.BLAZE_ROD, Items.FLINT, Items.ECHO_SHARD), new Result(ModItems.ECHO_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.BLAZE_ROD, Items.FLINT, Items.FIRE_CHARGE), new Result(ModItems.BLAZING_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.BREEZE_ROD, Items.FLINT, Items.PHANTOM_MEMBRANE), new Result(ModItems.AERIAL_ARROW, 8));
    }

    public record Recipie(Item feather, Item shaft, Item tip, Item upgrade) { }
    public record Result(Item item, int amount) { }
}