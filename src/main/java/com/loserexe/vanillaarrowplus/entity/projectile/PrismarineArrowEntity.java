package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PrismarineArrowEntity extends PersistentProjectileEntity {
    public PrismarineArrowEntity(World world, ItemStack stack, LivingEntity shooter, ItemStack shotFrom) {
        super(ModEntityTypes.PRISMARINE_ARROW, shooter, world, stack, shotFrom);
    }

    public PrismarineArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return ModItems.PRISMARINE_ARROW.getDefaultStack();
    }
}
