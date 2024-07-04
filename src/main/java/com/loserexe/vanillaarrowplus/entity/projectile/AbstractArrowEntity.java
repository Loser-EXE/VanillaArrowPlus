package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractArrowEntity extends PersistentProjectileEntity {
    public AbstractArrowEntity(World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(EntityType.ARROW, owner, world, stack, shotFrom);
    }

    public AbstractArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return null;
    }
}
