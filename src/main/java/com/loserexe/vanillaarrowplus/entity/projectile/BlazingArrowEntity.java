package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.item.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BlazingArrowEntity extends ModdedArrowEntity {
    public BlazingArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlazingArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(type, world, stack, owner, shotFrom);
    }

    public BlazingArrowEntity(EntityType<? extends PersistentProjectileEntity> type, Double x, Double y, Double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        //Bruh
        boolean bowIsEnchantedWithFlame = EnchantmentHelper.getLevel(
                this.getWorld().getRegistryManager()
                        .getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FLAME), Objects.requireNonNull(this.getWeaponStack())) != 0;

        if(bowIsEnchantedWithFlame) {
            entityHitResult.getEntity().setOnFireForTicks(200);
        } else {
            entityHitResult.getEntity().setOnFireForTicks(100);
        }
    }
}
