package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.item.ModItems;
import com.loserexe.vanillaarrowplus.mixin.PersistentProjectileEntityAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GoldArrowEntity extends ModdedArrowEntity {
    public GoldArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public GoldArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(type, world, stack, owner, shotFrom);
    }

    public GoldArrowEntity(EntityType<? extends PersistentProjectileEntity> type, Double x, Double y, Double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack);
    }

    @Override
    protected double getGravity() {
        return 0.02;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }
}
