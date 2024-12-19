package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
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
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        spawnShards(entityHitResult);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        spawnShards(blockHitResult);
    }

    private void spawnShards(HitResult hitResult) {
        World world = this.getWorld();
        if (world.isClient) return;
        int amount = random.nextBetween(3, 8);

        Direction direction = null;
        if (hitResult instanceof BlockHitResult blockHitResult) {
            direction = blockHitResult.getSide().getOpposite();
        }

        for (int x = 0; x < amount; x++) {
            AmethystShardEntity shard = new AmethystShardEntity(this.getX(), this.getY(), this.getZ(), world);
            shard.setVelocity(random.nextBetween(-1, 2) /8f, random.nextBetween(-1, 2) /8f, random.nextBetween(-1, 2) /8f);

            if (direction != null) {
                Vec3d shardVelocity = shard.getVelocity();
                double dotProduct = shardVelocity.dotProduct(direction.getDoubleVector());
                if (dotProduct > 0) {
                    shardVelocity = shardVelocity.negate();
                    shard.setVelocity(shardVelocity);
                }

                if (direction.equals(Direction.DOWN)) {
                    shardVelocity = shardVelocity.multiply(1, 1.3, 1);
                    shard.setVelocity(shardVelocity);
                }
            }

            world.spawnEntity(shard);
        }
    }
}
