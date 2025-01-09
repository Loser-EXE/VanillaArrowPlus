package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.recpie.FletchingTableRecipeRegistry;
import com.loserexe.vanillaarrowplus.recpie.FletchingTableTippingMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class FletchingTableScreenHandler extends ScreenHandler {
    private static final Identifier EMPTY_POTION_SLOT_TEXTURE = Identifier.ofVanilla("container/slot/potion");
    private static final Identifier FEATHER_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/feather");
    private static final Identifier SHAFT_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/shaft");
    private static final Identifier TIP_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/tip");
    private static final Identifier UPGRADE_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/upgrade");
    private static final Identifier ARROW_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/arrow");
    public static final int FEATHER_SLOT_INDEX = 0;
    public static final int SHAFT_SLOT_INDEX = 1;
    public static final int TIP_SLOT_INDEX = 2;
    public static final int UPGRADE_SLOT_INDEX = 3;
    public static final int ARROW_SLOT_INDEX = 4;
    public static final int RESULT_SLOT_INDEX = 5;
    public static final int TIPPING_MATERIAL_SLOT_INDEX = 6;
    public static final int[] CRAFTING_SLOTS = {FEATHER_SLOT_INDEX, SHAFT_SLOT_INDEX, TIP_SLOT_INDEX, UPGRADE_SLOT_INDEX};
    public static final int[] TIPPING_SLOTS = {ARROW_SLOT_INDEX, TIPPING_MATERIAL_SLOT_INDEX};
    private final Inventory inventory;
    private final FletchingTableTippingMaterial tippingMaterial;

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(7), new FletchingTableTippingMaterial());
    }

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, FletchingTableTippingMaterial tippingMaterial) {
        super(ModScreenHandlers.FLETCHING_TABLE, syncId);
        checkSize(inventory, 7);
        this.inventory = inventory;
        this.tippingMaterial = tippingMaterial;
        this.addSlot(new FletchingTableSlot(inventory, FEATHER_SLOT_INDEX, 9, 39, FEATHER_SLOT_TEXTURE, FletchingTableRecipeRegistry::isValidFeather));
        this.addSlot(new FletchingTableSlot(inventory, SHAFT_SLOT_INDEX, 31, 39, SHAFT_SLOT_TEXTURE, FletchingTableRecipeRegistry::isValidShaft));
        this.addSlot(new FletchingTableSlot(inventory, TIP_SLOT_INDEX, 53, 30, TIP_SLOT_TEXTURE, FletchingTableRecipeRegistry::isValidTip));
        this.addSlot(new FletchingTableSlot(inventory, UPGRADE_SLOT_INDEX, 53, 49, UPGRADE_SLOT_TEXTURE, FletchingTableRecipeRegistry::isValidUpgrade));
        this.addSlot(new ArrowSlot(inventory, ARROW_SLOT_INDEX , 148, 39));
        this.addSlot(new ResultSlot(inventory, RESULT_SLOT_INDEX, 100, 39, this.tippingMaterial));
        this.addSlot(new TippingMaterialSlot(inventory, TIPPING_MATERIAL_SLOT_INDEX, 148, 15, this.tippingMaterial));
        this.addPlayerInventorySlots(playerInventory, 8, 84);
        this.addPlayerSlots(playerInventory, 8, 84);
    }

    public FletchingTableTippingMaterial getTippingMaterial() {
        return tippingMaterial;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    static class FletchingTableSlot extends Slot {
        private final Identifier SLOT_BACKGROUND;
        private final InputValidator INPUT_VALIDATOR;

        public FletchingTableSlot(Inventory inventory, int index, int x, int y, Identifier slotBackground, InputValidator validator) {
            super(inventory, index, x, y);
            this.SLOT_BACKGROUND = slotBackground;
            this.INPUT_VALIDATOR = validator;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return INPUT_VALIDATOR.validate(stack);
        }

        public Identifier getBackgroundSprite() {
            return this.SLOT_BACKGROUND;
        }
    }

     interface InputValidator {
        boolean validate(ItemStack stack);
    }

    static class TippingMaterialSlot extends Slot {
        private final FletchingTableTippingMaterial tippingMaterial;
        public TippingMaterialSlot(Inventory inventory, int index, int x, int y, FletchingTableTippingMaterial tippingMaterial) {
            super(inventory, index, x, y);
            this.tippingMaterial = tippingMaterial;
        }

        public ItemStack insertStack(ItemStack stack, int count) {
            if (!canInsert(stack)) return stack;
            tippingMaterial.setItem(stack);
            ItemStack inputStack = stack.copy();
            inputStack.setCount(1);
            super.insertStack(inputStack, 1);
            stack.decrement(1);
            return stack;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack stack) {
            tippingMaterial.reset();
            super.onTakeItem(player, stack);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return FletchingTableTippingMaterial.isValidTippingMaterial(stack);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.EMPTY_POTION_SLOT_TEXTURE;
        }
    }

    static class ArrowSlot extends Slot {
        public ArrowSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.ARROW_SLOT_TEXTURE;
        }
    }

    static class ResultSlot extends Slot {
        private final FletchingTableTippingMaterial tippingMaterial;
        public ResultSlot(Inventory inventory, int index, int x, int y, FletchingTableTippingMaterial tippingMaterial) {
            super(inventory, index, x, y);
            this.tippingMaterial = tippingMaterial;
        }

        public boolean canInsert(ItemStack stack) {
            return false;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack stack) {
            for (int x = 0; x < 7; x++) {
                if (x == FletchingTableScreenHandler.RESULT_SLOT_INDEX || x == FletchingTableScreenHandler.TIPPING_MATERIAL_SLOT_INDEX) continue;
                inventory.getStack(x).decrement(1);
            }
            tippingMaterial.use(inventory);
            super.onTakeItem(player, stack);
        }
    }
}
