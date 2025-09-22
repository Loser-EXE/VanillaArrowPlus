package com.loserexe.vanillaarrowplus.mixin;

import com.loserexe.vanillaarrowplus.block.entity.LightningRodBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LightningRodBlock.class)
public abstract class LightningRodBlockMixin extends RodBlock implements BlockEntityProvider {
    @Shadow @Final public static MapCodec<LightningRodBlock> CODEC;

    public LightningRodBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<? extends RodBlock> getCodec() {
        return null;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof LightningRodBlockEntity lightningRodBlockEntity) {
                player.openHandledScreen(lightningRodBlockEntity);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LightningRodBlockEntity(pos, state);
    }
}
