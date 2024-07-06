package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AmethystArrowEntity extends PersistentProjectileEntity {
    public AmethystArrowEntity(World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(ModEntityTypes.AMETHYST_ARROW, owner, world, stack, shotFrom);
    }

    public AmethystArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.AMETHYST_ARROW);
    }
}
