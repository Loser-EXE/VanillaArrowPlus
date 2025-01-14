package com.loserexe.vanillaarrowplus.block.entity;

import com.loserexe.vanillaarrowplus.recpie.FletchingTableRecipeRegistry;
import com.loserexe.vanillaarrowplus.recpie.FletchingTableTippingMaterial;
import com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FletchingTableBlockEntity extends LockableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);
    private final FletchingTableTippingMaterial tippingMaterial = new FletchingTableTippingMaterial();

    public FletchingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.FLETCHING_TABLE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, FletchingTableBlockEntity blockEntity) {
        if (!FletchingTableRecipeRegistry.craft(blockEntity, blockEntity.getTippingMaterial())) {
            blockEntity.setStack(FletchingTableScreenHandler.RESULT_SLOT_INDEX, ItemStack.EMPTY);
        }
        markDirty(world, pos, state);
    }

    public FletchingTableTippingMaterial getTippingMaterial() {
        return this.tippingMaterial;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.fletching_table");
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (slot == FletchingTableScreenHandler.RESULT_SLOT_INDEX) {
            amount = this.getStack(slot).getCount();
        }
        return super.removeStack(slot, amount);
    }

    @Override
    public int size() {
        return inventory.size();
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new FletchingTableScreenHandler(syncId, playerInventory, this, this.tippingMaterial);
    }
}
