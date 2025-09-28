package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlazingArrowEntity extends ModdedPersistentProjectileEntity {
    private int bounces = 0;

    public BlazingArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlazingArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(type, world, stack, owner, shotFrom);
        if (shotFrom == null) return;
        int projectileCount = EnchantmentHelper.getProjectileCount((ServerWorld) world, shotFrom, owner, 1);
        if (projectileCount > 1) {
            bounces++;
        }
    }

    public BlazingArrowEntity(EntityType<? extends PersistentProjectileEntity> type, Double x, Double y, Double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isSubmergedInWater()) {
            getWorld().playSound(this, this.getBlockPos(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.NEUTRAL, 1.0f, 10f);
            this.discard();
        }

        if (this.isSubmergedIn(TagKey.of(RegistryKeys.FLUID, Identifier.of("lava")))) {
            this.explode();
            this.discard();
        }

        if (this.getVelocity().length() < 0.1E-4) {
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        bounces++;
        explode();
        this.setVelocity(this.getVelocity()
                        .negate()
                        .add(this.random.nextBetween(-1, 1)/4f, 0, this.random.nextBetween(-1, 1)/4f)
                        .multiply(1.2f));
        if (bounces == 3) {
            this.discard();
        }
    }

    private void explode() {
        this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 2, false, World.ExplosionSourceType.TRIGGER);
    }
}
