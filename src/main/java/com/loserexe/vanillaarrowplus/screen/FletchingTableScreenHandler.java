package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class FletchingTableScreenHandler extends ScreenHandler {
    private static final Identifier EMPTY_POTION_SLOT_TEXTURE = Identifier.ofVanilla("container/slot/potion");
    private static final Identifier FEATHER_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/feather");
    private static final Identifier SHAFT_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/shaft");
    private static final Identifier TIP_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/tip");
    private static final Identifier UPGRADE_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/upgrade");
    private static final Identifier ARROW_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/arrow");
    private final Inventory inventory;

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(7));
    }

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreens.FLETCHING_TABLE, syncId);
        checkSize(inventory, 7);
        this.inventory = inventory;
        this.addSlot(new FeatherSlot(inventory, 0, 9, 39));
        this.addSlot(new ShaftSlot(inventory, 1, 31, 39));
        this.addSlot(new TipSlot(inventory, 2, 53, 30));
        this.addSlot(new UpgradeSlot(inventory, 3, 53, 49));
        this.addSlot(new ArrowSlot(inventory, 4, 148, 39));
        this.addSlot(new ResultSlot(inventory, 5, 100, 39));
        this.addSlot(new TippingMaterialSlot(inventory, 6, 148, 15));
        this.addPlayerInventorySlots(playerInventory, 8, 84);
        this.addPlayerSlots(playerInventory, 8, 84);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
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

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.TIP_SLOT_TEXTURE;
        }
    }

    static class FeatherSlot extends Slot {
        public FeatherSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.FEATHER_SLOT_TEXTURE;
        }
    }

    static class ShaftSlot extends Slot {
        public ShaftSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public Identifier getBackgroundSprite() {
            return FletchingTableScreenHandler.SHAFT_SLOT_TEXTURE;
        }
    }

    static class UpgradeSlot extends Slot {
        public UpgradeSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
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
    }
}
