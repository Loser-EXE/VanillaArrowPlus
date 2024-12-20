package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.block.ModBlocks;
import com.loserexe.vanillaarrowplus.block.PoweredAirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RedstoneArrowEntity extends ModdedPersistentProjectileEntity {
    private BlockPos poweredAirBlockPos;

    public RedstoneArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public RedstoneArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(type, world, stack, owner, shotFrom);
    }

    public RedstoneArrowEntity(EntityType<? extends PersistentProjectileEntity> type, Double x, Double y, Double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        if(poweredAirBlockPos != null) {
            removePoweredAirBlock();
        }

        Direction hitBlockSide = blockHitResult.getSide();
        poweredAirBlockPos = blockHitResult.getBlockPos().offset(hitBlockSide);
        BlockState poweredAirBlockState = ModBlocks.POWERED_AIR.getDefaultState().with(PoweredAirBlock.FACING, hitBlockSide);

        Block blockToBeReplaced = getWorld().getBlockState(poweredAirBlockPos).getBlock();

        if(blockToBeReplaced == Blocks.AIR || blockToBeReplaced.getDefaultState().isReplaceable()) {
            this.getWorld().setBlockState(poweredAirBlockPos, poweredAirBlockState, Block.NOTIFY_ALL);
        }
    }

    private void removePoweredAirBlock() {
        if(poweredAirBlockPos == null) {
            return;
        }

        if(getWorld().getBlockState(poweredAirBlockPos).getBlock() == ModBlocks.POWERED_AIR) {
            this.getWorld().setBlockState(poweredAirBlockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        removePoweredAirBlock();
    }

    @Override
    public void tick() {
        super.tick();

        if(this.inGroundTime % 10 == 0) {
            this.getWorld().addParticle(new DustParticleEffect(DustParticleEffect.RED, 1), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }
}
