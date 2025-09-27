package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PrismarineArrowEntity extends ModdedPersistentProjectileEntity {
    public PrismarineArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public PrismarineArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(type, world, stack, owner, shotFrom);
    }

    public PrismarineArrowEntity(EntityType<? extends PersistentProjectileEntity> type, Double x, Double y, Double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack);
    }

    @Override
    public void tick() {
        super.tick();

        boolean ownerSubmerged = this.getOwner() == null || this.getOwner().isSubmergedInWater();

        if (ownerSubmerged && this.isSubmergedInWater()) {
            super.setDamage(4);
        } else {
            super.setDamage(0.5);
        }
    }

    @Override
    protected float getDragInWater() {
        return 1f;
    }
}
