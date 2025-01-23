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

import static com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler.*;

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

    public static CraftingMethod getCraftingMethod(Inventory inventory) {
        boolean validCrafting = recipies.containsKey(Recipie.getFromInventory(inventory));
        boolean validTipping = !inventory.getStack(ARROW_SLOT_INDEX).isEmpty() && !inventory.getStack(TIPPING_MATERIAL_SLOT_INDEX).isEmpty();

        if (validCrafting && validTipping) {
            return CraftingMethod.NONE;
        }

        if (validTipping) {
            return CraftingMethod.TIPPING;
        }

        if (validCrafting) {
            return CraftingMethod.CRAFTING;
        }

        return CraftingMethod.NONE;
    }

    public static void craft(Inventory inventory, CraftingMethod method, FletchingTableTippingMaterial tippingMaterial) {
        switch (method) {
            case CRAFTING -> {
                Recipie recipie = Recipie.getFromInventory(inventory);
                Result result = recipies.getOrDefault(recipie, null);
                if (result == null) return;
                ItemStack resulSlotStack = inventory.getStack(RESULT_SLOT_INDEX);
                if (resulSlotStack.isEmpty() || !resulSlotStack.isOf(result.item)) {
                    ItemStack resultStack = new ItemStack(result.item, result.amount);
                    inventory.setStack(RESULT_SLOT_INDEX, resultStack);
                }
            }
            case TIPPING -> tippingMaterial.craft(inventory);
        }
    }

    static {
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.AIR), new Result(Items.ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.GOLD_INGOT), new Result(ModItems.GOLD_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.IRON_INGOT), new Result(ModItems.IRON_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.COPPER_INGOT), new Result(ModItems.COPPER_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.AMETHYST_SHARD, Items.AIR), new Result(ModItems.AMETHYST_ARROW, 16));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.PRISMARINE_SHARD, Items.AIR), new Result(ModItems.PRISMARINE_ARROW, 8));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.QUARTZ, Items.SLIME_BALL), new Result(ModItems.SLIME_ARROW, 1));
        recipies.put(new Recipie(Items.FEATHER, Items.STICK, Items.FLINT, Items.ECHO_SHARD), new Result(ModItems.ECHO_ARROW, 8));
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

        public static Recipie getFromInventory(Inventory inventory) {
            return new Recipie(
                    inventory.getStack(FEATHER_SLOT_INDEX).getItem(),
                    inventory.getStack(SHAFT_SLOT_INDEX).getItem(),
                    inventory.getStack(TIP_SLOT_INDEX).getItem(),
                    inventory.getStack(UPGRADE_SLOT_INDEX).getItem()
            );
        }
    }

    record Result(Item item, int amount) { }

    public enum CraftingMethod {
        NONE(0),
        CRAFTING(1),
        TIPPING(2);

        private final int value;

        CraftingMethod(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
