package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModdedArrowEntity extends PersistentProjectileEntity {
    private final EntityType<? extends PersistentProjectileEntity> entityType;

    protected ModdedArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.entityType = entityType;
    }

    protected ModdedArrowEntity(EntityType<? extends PersistentProjectileEntity> type, Double x, Double y, Double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack, null);
        entityType = type;
    }

    protected ModdedArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(type, owner, world, stack, shotFrom);
        entityType = type;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return null;
    }
}
