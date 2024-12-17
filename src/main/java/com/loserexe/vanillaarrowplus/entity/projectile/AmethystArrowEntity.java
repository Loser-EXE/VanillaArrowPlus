package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AmethystArrowEntity extends ModdedPersistentProjectileEntity {
    public AmethystArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public AmethystArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(type, world, stack, owner, shotFrom);
    }

    public AmethystArrowEntity(EntityType<? extends PersistentProjectileEntity> type, Double x, Double y, Double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack);
    }

    @Override
    protected void initProjectile() {
        super.initProjectile();
        setDamage(1.5);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        spawnShards();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        spawnShards();
        discard();
    }

    private void spawnShards() {
        World world = this.getWorld();
        int amount = random.nextBetween(3, 8);

        for (int x = 0; x < amount; x++) {
            AmethystShardEntity shard = new AmethystShardEntity(this.getX(), this.getY(), this.getZ(), world);
            shard.setVelocity(random.nextBetween(-1, 2) /8f, random.nextBetween(-1, 2) /8f, random.nextBetween(-1, 2) /8f);
            if (!world.isClient) {
                world.spawnEntity(shard);
            }
        }
    }
}
