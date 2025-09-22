package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModdedPersistentProjectileEntity extends PersistentProjectileEntity {

    protected ModdedPersistentProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    protected ModdedPersistentProjectileEntity(EntityType<? extends PersistentProjectileEntity> type, Double x, Double y, Double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack, null);
        initProjectile();
    }

    protected ModdedPersistentProjectileEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(type, owner, world, stack, shotFrom);
        initProjectile();
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        // Not really sure what this does. Prevents some spawning conditions if null.
        return new ItemStack(Items.ARROW);
    }

    protected void initProjectile() {

    }
}
