package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.recpie.FletchingTableRecipeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
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
    private final Inventory inventory;

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(7));
    }

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.FLETCHING_TABLE, syncId);
        checkSize(inventory, 7);
        this.inventory = inventory;
        this.addSlot(new FeatherSlot(inventory, FEATHER_SLOT_INDEX, 9, 39));
        this.addSlot(new ShaftSlot(inventory, SHAFT_SLOT_INDEX, 31, 39));
        this.addSlot(new TipSlot(inventory, TIP_SLOT_INDEX, 53, 30));
        this.addSlot(new UpgradeSlot(inventory, UPGRADE_SLOT_INDEX, 53, 49));
        this.addSlot(new ArrowSlot(inventory, ARROW_SLOT_INDEX , 148, 39));
        this.addSlot(new ResultSlot(inventory, RESULT_SLOT_INDEX, 100, 39));
        this.addSlot(new TippingMaterialSlot(inventory, TIPPING_MATERIAL_SLOT_INDEX, 148, 15));
        this.addPlayerInventorySlots(playerInventory, 8, 84);
        this.addPlayerSlots(playerInventory, 8, 84);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    static class TippingMaterialSlot extends Slot {
        public TippingMaterialSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.EMPTY_POTION_SLOT_TEXTURE;
        }
    }

    static class TipSlot extends Slot {
        public TipSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return FletchingTableRecipeRegistry.isValidTip(stack);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.TIP_SLOT_TEXTURE;
        }
    }

    static class FeatherSlot extends Slot {
        public FeatherSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return FletchingTableRecipeRegistry.isValidFeather(stack);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.FEATHER_SLOT_TEXTURE;
        }
    }

    static class ShaftSlot extends Slot {
        public ShaftSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return FletchingTableRecipeRegistry.isValidShaft(stack);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.SHAFT_SLOT_TEXTURE;
        }
    }

    static class UpgradeSlot extends Slot {
        public UpgradeSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return FletchingTableRecipeRegistry.isValidUpgrade(stack);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.UPGRADE_SLOT_TEXTURE;
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
        public ResultSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean canInsert(ItemStack stack) {
            return false;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack stack) {
            for (int x = 0; x < 7; x++) {
                if (x == FletchingTableScreenHandler.RESULT_SLOT_INDEX) continue;
                inventory.getStack(x).decrement(1);
            }
            super.onTakeItem(player, stack);
        }
    }
}
