package com.loserexe.vanillaarrowplus.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModdedArrowItem<T extends PersistentProjectileEntity> extends ArrowItem {
    private final ModdedArrowEntityBuilder builder;

    public ModdedArrowItem(ModdedArrowEntityBuilder builder) {
        super(new Item.Settings());

        this.builder = builder;
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return builder.create(world, stack, shooter, shotFrom);
    }

    public interface ModdedArrowEntityBuilder {
        PersistentProjectileEntity create(World world, ItemStack stack, LivingEntity shooter, ItemStack shotFrom);
    }
}
