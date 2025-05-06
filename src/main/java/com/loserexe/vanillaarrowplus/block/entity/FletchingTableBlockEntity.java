package com.loserexe.vanillaarrowplus.block.entity;

import com.loserexe.vanillaarrowplus.recpie.FletchingTableRecipeRegistry;
import com.loserexe.vanillaarrowplus.recpie.FletchingTableTippingMaterial;
import com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler.*;

public class FletchingTableBlockEntity extends LockableContainerBlockEntity {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);
    private FletchingTableTippingMaterial tippingMaterial = new FletchingTableTippingMaterial();
    private FletchingTableScreenHandler screenHandler;
    private final PropertyDelegate propertyDelegate;

    public FletchingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.FLETCHING_TABLE, pos, state);
        propertyDelegate = new PropertyDelegate() {
            int craftingMethod = 0;

            @Override
            public int get(int index) {
                if (index == 0) {
                    return FletchingTableBlockEntity.this.tippingMaterial.getFillAmount();
                } else if (index == 1) {
                    return this.craftingMethod;
                } else if (index == 2) {
                    if (!FletchingTableBlockEntity.this.getStack(ARROW_SLOT_INDEX).isEmpty()) {
                        return FletchingTableRecipeRegistry.CraftingMethod.TIPPING.getValue();
                    }
                    for (int slot : CRAFTING_SLOTS) {
                        if (!FletchingTableBlockEntity.this.getStack(slot).isEmpty()) {
                            return FletchingTableRecipeRegistry.CraftingMethod.CRAFTING.getValue();
                        }
                    }
                    return FletchingTableRecipeRegistry.CraftingMethod.NONE.getValue();
                }
                return 0;
            }

            @Override
            public void set(int index, int value) {
                if (index == 1) {
                    this.craftingMethod = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    public static void tick(World world, BlockPos pos, BlockState state, FletchingTableBlockEntity blockEntity) {
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        Inventories.readNbt(nbt, this.inventory, registries);
        if (nbt.contains("tippingMaterial")) {
            this.tippingMaterial = FletchingTableTippingMaterial.CODEC.parse(NbtOps.INSTANCE, nbt.get("tippingMaterial")).getOrThrow();
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        Inventories.writeNbt(nbt, this.inventory, registries);
        nbt.put("tippingMaterial", FletchingTableTippingMaterial.CODEC.encodeStart(NbtOps.INSTANCE, tippingMaterial).getOrThrow());
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
        ItemStack stack = super.removeStack(slot, amount);
        if (this.screenHandler != null) {
            this.screenHandler.onContentChanged(this);
            this.screenHandler.setPreviousTrackedSlot(slot, stack);
        }
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
        if (this.screenHandler != null) {
            this.screenHandler.onContentChanged(this);
            this.screenHandler.setPreviousTrackedSlot(slot, stack);
        }
    }

    @Override
    public int size() {
        return inventory.size();
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        screenHandler = new FletchingTableScreenHandler(syncId, playerInventory, this, this.tippingMaterial, propertyDelegate, ScreenHandlerContext.create(world, this.pos));
        return screenHandler;
    }
}
