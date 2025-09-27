package com.loserexe.vanillaarrowplus.block.entity;

import com.loserexe.vanillaarrowplus.screen.LightningRodScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class LightningRodBlockEntity extends LockableContainerBlockEntity {
    private static final int INVENTORY_SIZE = 1;
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

    public LightningRodBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntityTypes.LIGHTNING_ROD, blockPos, blockState);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.lightning_rod");
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
    public int size() {
        return INVENTORY_SIZE;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new LightningRodScreenHandler(syncId, playerInventory, this);
    }
}
