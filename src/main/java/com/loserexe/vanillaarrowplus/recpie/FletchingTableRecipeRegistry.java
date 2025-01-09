package com.loserexe.vanillaarrowplus.recpie;

import com.loserexe.vanillaarrowplus.item.ModItems;
import com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FletchingTableRecipeRegistry {
    private static final Map<Recipie, Result> recipies = new HashMap<>();
    private static final List<Item> validFeathers = new ArrayList<>();
    private static final List<Item> validShafts = new ArrayList<>();
    private static final List<Item> validTips = new ArrayList<>();
    private static final List<Item> validUpgrades = new ArrayList<>();

    public static boolean isValidIngredient(ItemStack stack, List<Item> ingredientList) {
        Item item = stack.getItem();
        return ingredientList.contains(item);
    }

    public static boolean isValidFeather(ItemStack stack) {
        return isValidIngredient(stack, validFeathers);
    }

    public static boolean isValidShaft(ItemStack stack) {
        return isValidIngredient(stack, validShafts);
    }

    public static boolean isValidTip(ItemStack stack) {
        return isValidIngredient(stack, validTips);
    }

    public static boolean isValidUpgrade(ItemStack stack) {
        return isValidIngredient(stack, validUpgrades);
    }

    public static boolean craft(Inventory inventory, FletchingTableTippingMaterial tippingMaterial) {
        try {
            Recipie recipie = new Recipie(
                    inventory.getStack(FletchingTableScreenHandler.FEATHER_SLOT_INDEX).getItem(),
                    inventory.getStack(FletchingTableScreenHandler.SHAFT_SLOT_INDEX).getItem(),
                    inventory.getStack(FletchingTableScreenHandler.TIP_SLOT_INDEX).getItem(),
                    inventory.getStack(FletchingTableScreenHandler.UPGRADE_SLOT_INDEX).getItem());
            Result result = recipies.get(recipie);
            ItemStack resultStack = new ItemStack(result.item, result.amount);
            inventory.setStack(FletchingTableScreenHandler.RESULT_SLOT_INDEX, resultStack);
            return true;
        } catch (Exception e) {
             return tippingMaterial.craft(inventory);
        }
    }

    static {
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.AIR), new Result(Items.ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.GOLD_INGOT), new Result(ModItems.GOLD_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.IRON_INGOT), new Result(ModItems.IRON_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.COPPER_INGOT), new Result(ModItems.COPPER_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.AMETHYST_SHARD, Items.AIR), new Result(ModItems.AMETHYST_ARROW, 16));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.PRISMARINE_SHARD), new Result(ModItems.PRISMARINE_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.QUARTZ, Items.SLIME_BALL), new Result(ModItems.SLIME_ARROW, 1));
        recipies.put(new Recipie(Items.FEATHER, Items.BLAZE_ROD, Items.FLINT, Items.ECHO_SHARD), new Result(ModItems.ECHO_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.BLAZE_ROD, Items.FLINT, Items.FIRE_CHARGE), new Result(ModItems.BLAZING_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.BREEZE_ROD, Items.FLINT, Items.PHANTOM_MEMBRANE), new Result(ModItems.AERIAL_ARROW, 8));
    }

    record Recipie(Item feather, Item shaft, Item tip, Item upgrade) {
        public Recipie {
            validFeathers.add(feather);
            validShafts.add(shaft);
            validTips.add(tip);
            validUpgrades.add(upgrade);
        }
    }

    record Result(Item item, int amount) { }
}
