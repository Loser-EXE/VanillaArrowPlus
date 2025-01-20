package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.block.entity.FletchingTableBlockEntity;
import com.loserexe.vanillaarrowplus.recpie.FletchingTableRecipeRegistry;
import com.loserexe.vanillaarrowplus.recpie.FletchingTableTippingMaterial;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FletchingTableScreenHandler extends ScreenHandler {
    private static final Identifier EMPTY_POTION_SLOT_TEXTURE = Identifier.ofVanilla("container/slot/potion");
    private static final Identifier FEATHER_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/feather");
    private static final Identifier SHAFT_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/shaft");
    private static final Identifier TIP_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/tip");
    private static final Identifier UPGRADE_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/upgrade");
    private static final Identifier ARROW_SLOT_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "container/slot/arrow");
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;
    private final PlayerEntity player;
    private final Inventory inventory;
    public static final int FEATHER_SLOT_INDEX = 0;
    public static final int SHAFT_SLOT_INDEX = 1;
    public static final int TIP_SLOT_INDEX = 2;
    public static final int UPGRADE_SLOT_INDEX = 3;
    public static final int ARROW_SLOT_INDEX = 4;
    public static final int RESULT_SLOT_INDEX = 5;
    public static final int TIPPING_MATERIAL_SLOT_INDEX = 6;
    public static final int[] TIPPING_SLOTS = new int[]{TIPPING_MATERIAL_SLOT_INDEX, ARROW_SLOT_INDEX};
    public static final int[] CRAFTING_SLOTS = new int[]{FEATHER_SLOT_INDEX, SHAFT_SLOT_INDEX, TIP_SLOT_INDEX, UPGRADE_SLOT_INDEX};

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(7), new FletchingTableTippingMaterial(), new ArrayPropertyDelegate(3), ScreenHandlerContext.EMPTY);
    }

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, FletchingTableTippingMaterial tippingMaterial, PropertyDelegate propertyDelegate, ScreenHandlerContext context) {
        super(ModScreenHandlers.FLETCHING_TABLE, syncId);
        checkSize(inventory, 7);
        checkDataCount(propertyDelegate, 3);
        this.context = context;
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.player = playerInventory.player;
        this.addSlot(new FletchingTableSlot(inventory, FEATHER_SLOT_INDEX, 9, 39, FEATHER_SLOT_TEXTURE, FletchingTableRecipeRegistry::isValidFeather));
        this.addSlot(new FletchingTableSlot(inventory, SHAFT_SLOT_INDEX, 31, 39, SHAFT_SLOT_TEXTURE, FletchingTableRecipeRegistry::isValidShaft));
        this.addSlot(new FletchingTableSlot(inventory, TIP_SLOT_INDEX, 53, 30, TIP_SLOT_TEXTURE, FletchingTableRecipeRegistry::isValidTip));
        this.addSlot(new FletchingTableSlot(inventory, UPGRADE_SLOT_INDEX, 53, 49, UPGRADE_SLOT_TEXTURE, FletchingTableRecipeRegistry::isValidUpgrade));
        this.addSlot(new ArrowSlot(inventory, ARROW_SLOT_INDEX , 148, 39));
        this.addSlot(new ResultSlot(inventory, RESULT_SLOT_INDEX, 100, 39, tippingMaterial));
        this.addSlot(new TippingMaterialSlot(inventory, TIPPING_MATERIAL_SLOT_INDEX, 148, 15, tippingMaterial));
        this.addPlayerInventorySlots(playerInventory, 8, 84);
        this.addPlayerSlots(playerInventory, 8, 84);
        this.addProperties(propertyDelegate);
        propertyDelegate.set(1, FletchingTableRecipeRegistry.getCraftingMethod(inventory).getValue());
    }

    private void contentChange(FletchingTableBlockEntity blockEntity) {
        FletchingTableRecipeRegistry.CraftingMethod craftingMethod = FletchingTableRecipeRegistry.getCraftingMethod(blockEntity);

        FletchingTableRecipeRegistry.craft(blockEntity, craftingMethod, blockEntity.getTippingMaterial());
        this.propertyDelegate.set(1, craftingMethod.getValue());
        if (craftingMethod == FletchingTableRecipeRegistry.CraftingMethod.NONE) {
            ItemStack resultStack = this.getSlot(RESULT_SLOT_INDEX).getStack();
            if (!resultStack.isEmpty()) {
                this.inventory.setStack(RESULT_SLOT_INDEX, ItemStack.EMPTY);
            }
        }

        if (this.player instanceof ServerPlayerEntity serverPlayerEntity) {
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(this.syncId, this.nextRevision(), RESULT_SLOT_INDEX, inventory.getStack(RESULT_SLOT_INDEX)));
            if (craftingMethod == FletchingTableRecipeRegistry.CraftingMethod.NONE) {
                serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(this.syncId, this.nextRevision(), TIPPING_MATERIAL_SLOT_INDEX, inventory.getStack(TIPPING_MATERIAL_SLOT_INDEX)));
            }
        }
    }

    public FletchingTableRecipeRegistry.CraftingMethod getCraftingMethod() {
        return intToCraftingMethod(this.propertyDelegate.get(1));
    }

    public FletchingTableRecipeRegistry.CraftingMethod getAttemptedCraftingMethod() {
        return intToCraftingMethod(this.propertyDelegate.get(2));
    }

    private FletchingTableRecipeRegistry.CraftingMethod intToCraftingMethod(int i) {
        switch (i) {
            case 1 -> {
                return FletchingTableRecipeRegistry.CraftingMethod.CRAFTING;
            }
            case 2 -> {
                return FletchingTableRecipeRegistry.CraftingMethod.TIPPING;
            }
            default -> {
                return FletchingTableRecipeRegistry.CraftingMethod.NONE;
            }
        }
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        context.run((world, blockPos) -> {
            BlockEntity entity = world.getBlockEntity(blockPos);
            if (entity instanceof FletchingTableBlockEntity fletchingTableBlockEntity) {
                contentChange(fletchingTableBlockEntity);
            }
        });
        super.onContentChanged(inventory);
    }

    public int getTippingFillAmount() {
        return propertyDelegate.get(0);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot targetSlot = this.getSlot(slotIndex);
        if (targetSlot != null && targetSlot.hasStack()) {
            ItemStack slotStack = targetSlot.getStack();
            itemStack = slotStack.copy();
            if (slotIndex > 6) {
                if (FletchingTableRecipeRegistry.isValidFeather(itemStack)) {
                    if (!this.insertItem(slotStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (FletchingTableRecipeRegistry.isValidShaft(itemStack)) {
                    if (!this.insertItem(slotStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (FletchingTableRecipeRegistry.isValidTip(itemStack)) {
                    if (!this.insertItem(slotStack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (FletchingTableRecipeRegistry.isValidUpgrade(itemStack)) {
                    if (!this.insertItem(slotStack, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemStack.isOf(Items.ARROW)) {
                    if (!this.insertItem(slotStack, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (FletchingTableTippingMaterial.validItems.contains(itemStack.getItem())) {
                    if (!this.insertItem(slotStack, 6, 7, false)) {
                        return ItemStack.EMPTY;
                    }
                    onContentChanged(this.inventory);
                } else if (slotIndex < 61) {
                    if (!this.insertItem(slotStack, 61, 70, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.insertItem(slotStack, 7, 61, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                if (!this.insertItem(slotStack, 7, 70, true)) {
                    return ItemStack.EMPTY;
                }

                targetSlot.onQuickTransfer(slotStack, itemStack);
            }

            if (slotStack.isEmpty()) {
                targetSlot.setStack(ItemStack.EMPTY);
            } else {
                targetSlot.markDirty();
            }

            if (slotStack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            targetSlot.onTakeItem(player, itemStack);
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
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

    public class TippingMaterialSlot extends Slot {
        private final FletchingTableTippingMaterial tippingMaterial;
        public TippingMaterialSlot(Inventory inventory, int index, int x, int y, FletchingTableTippingMaterial tippingMaterial) {
            super(inventory, index, x, y);
            this.tippingMaterial = tippingMaterial;
        }

        public ItemStack insertStack(ItemStack stack, int count) {
            if (!canInsert(stack) || stack.isEmpty()) return stack;
            ItemStack inputStack = stack.copy();
            inputStack.setCount(1);
            super.insertStack(inputStack, 1);
            stack.decrement(1);
            updateTippingMaterial();
            onContentChanged(this.inventory);
            return stack;
        }

        private void updateTippingMaterial() {
            tippingMaterial.setItem(this.getStack());
        }

        @Override
        public void markDirty() {
            super.markDirty();
            if (this.hasStack()) {
                updateTippingMaterial();
            }
        }

        @Override
        public boolean canTakeItems(PlayerEntity playerEntity) {
            return false;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            if (this.hasStack()) return false;
            boolean isValidItem =  FletchingTableTippingMaterial.validItems.contains(stack.getItem());
            if (!isValidItem) return false;
            return this.getStack().isEmpty();
        }

        @Override
        public int getMaxItemCount() {
            return 1;
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

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.getItem().equals(Items.ARROW);
        }
    }

    class ResultSlot extends Slot {
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
            context.run(((world, blockPos) -> world.playSound(null, blockPos, SoundEvents.ENTITY_VILLAGER_WORK_FLETCHER, SoundCategory.BLOCKS, 1, 1)));
            super.onTakeItem(player, stack);
        }
    }
}
