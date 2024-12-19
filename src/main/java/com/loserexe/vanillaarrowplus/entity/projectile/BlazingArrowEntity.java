package com.loserexe.vanillaarrowplus.entity.projectile;

import net.fabricmc.fabric.impl.item.EnchantmentUtil;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BlazingArrowEntity extends ModdedPersistentProjectileEntity {
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
        boolean bowIsEnchantedWithFlame = false;
        try {
            Registry<Enchantment> enchantmentRegistry = getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
            Enchantment enchantment = enchantmentRegistry.get(Enchantments.FLAME);
            RegistryEntry<Enchantment> enchantmentRegistryEntry = enchantmentRegistry.getEntry(enchantment);
            ItemEnchantmentsComponent enchantments = EnchantmentHelper.getEnchantments(Objects.requireNonNull(this.getWeaponStack()));
            bowIsEnchantedWithFlame = enchantments.getLevel(enchantmentRegistryEntry) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(bowIsEnchantedWithFlame) {
            entityHitResult.getEntity().setOnFireForTicks(200);
        } else {
            entityHitResult.getEntity().setOnFireForTicks(100);
        }
    }
}
