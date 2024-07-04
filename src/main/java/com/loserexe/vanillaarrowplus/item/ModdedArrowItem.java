package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModdedArrowItem<T extends AbstractArrowEntity> extends ArrowItem {
    private final Class<T> arrowEntity;

    public ModdedArrowItem(Class<T> arrowEntity) {
        super(new Item.Settings());
        this.arrowEntity = arrowEntity;
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        try {
            return arrowEntity.getConstructor(World.class, ItemStack.class, LivingEntity.class, ItemStack.class)
                    .newInstance(world, stack, shooter, shotFrom);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create arrow entity!", e);
        }
    }
}
