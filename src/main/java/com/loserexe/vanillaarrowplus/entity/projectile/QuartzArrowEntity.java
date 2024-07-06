package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class QuartzArrowEntity extends PersistentProjectileEntity {
    public QuartzArrowEntity(World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(ModEntityTypes.QUARTZ_ARROW, owner, world, stack, shotFrom);
    }

    public QuartzArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getDragInWater() {
        return 1f;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.QUARTZ_ARROW);
    }
}
