package com.loserexe.vanillaarrowplus.block;

import com.loserexe.vanillaarrowplus.block.entity.FletchingTableBlockEntity;
import com.loserexe.vanillaarrowplus.block.entity.ModBlockEntityTypes;
import com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModdedFletchingTable extends BlockWithEntity {
    public static final MapCodec<ModdedFletchingTable> CODEC = createCodec(ModdedFletchingTable::new);

    public ModdedFletchingTable(Settings settings) {
        super(settings);
    }

    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FletchingTableBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : validateTicker(type, ModBlockEntityTypes.FLETCHING_TABLE, FletchingTableBlockEntity::tick);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FletchingTableBlockEntity fletchingTableBlockEntity) {
                player.openHandledScreen(fletchingTableBlockEntity);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            FletchingTableBlockEntity fletchingTableBlockEntity = world.getBlockEntity(pos, ModBlockEntityTypes.FLETCHING_TABLE).orElseThrow();
            fletchingTableBlockEntity.setStack(FletchingTableScreenHandler.RESULT_SLOT_INDEX, ItemStack.EMPTY);
            fletchingTableBlockEntity.setStack(FletchingTableScreenHandler.TIPPING_MATERIAL_SLOT_INDEX, ItemStack.EMPTY);
            ItemScatterer.onStateReplaced(state, newState, world, pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
