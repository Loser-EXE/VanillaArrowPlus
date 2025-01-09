package com.loserexe.vanillaarrowplus.recpie;

import com.loserexe.vanillaarrowplus.item.ModItems;
import com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class FletchingTableTippingMaterial {
    private static final Item[] validItems = {Items.POTION, Items.GLOWSTONE_DUST, Items.REDSTONE};
    private float fillAmount;
    private PotionContentsComponent potionComponent;
    private Item of;

    public FletchingTableTippingMaterial() { }

    public void setItem(ItemStack stack) {
        this.of = stack.getItem();
        this.fillAmount = 1.0f;
        if (this.of == Items.POTION) {
            potionComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
        }
    }

    public static boolean isValidTippingMaterial(ItemStack stack) {
        Item stackItem = stack.getItem();
        for(Item item : validItems) {
            if (item == stackItem) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        this.of = null;
        this.fillAmount = 0.0f;
        this.potionComponent = null;
    }

    public float getFillAmount() {
        return fillAmount;
    }

    public void use(Inventory inventory) {
        fillAmount -= 1/32f;
        if (fillAmount <= 0) {
            reset();
            inventory.setStack(FletchingTableScreenHandler.TIPPING_MATERIAL_SLOT_INDEX, ItemStack.EMPTY);
        }
    }

    public boolean craft(Inventory inventory) {
        if(of == null || inventory.getStack(FletchingTableScreenHandler.ARROW_SLOT_INDEX).getItem().equals(Items.AIR)) {
            return false;
        }
        ItemStack resultStack = ItemStack.EMPTY;
        if (of.equals(Items.REDSTONE)) {
            resultStack = new ItemStack(ModItems.REDSTONE_ARROW, 1);
        } else if (of.equals(Items.GLOWSTONE_DUST)) {
            resultStack = new ItemStack(Items.SPECTRAL_ARROW, 1);
        } else if (of.equals(Items.POTION)) {
            ItemStack arrow = new ItemStack(Items.TIPPED_ARROW, 1);
            arrow.set(DataComponentTypes.POTION_CONTENTS, this.potionComponent);
            resultStack = arrow;
        }
        inventory.setStack(FletchingTableScreenHandler.RESULT_SLOT_INDEX, resultStack);
        return true;
    }
}
