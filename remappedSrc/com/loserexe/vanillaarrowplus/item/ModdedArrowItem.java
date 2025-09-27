package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.projectile.ModdedPersistentProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModdedArrowItem extends ArrowItem {
    private final Class<?> entityClass;
    private final EntityType<?> entityType;

    public ModdedArrowItem(Class<? extends ModdedPersistentProjectileEntity> entityClass, EntityType<? extends ModdedPersistentProjectileEntity> entityType, Item.Settings settings) {
        super(settings);

        this.entityClass = entityClass;
        this.entityType = entityType;
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        try {
            return (PersistentProjectileEntity) entityClass.getConstructor(
                    EntityType.class, World.class, ItemStack.class, LivingEntity.class, ItemStack.class)
                    .newInstance(entityType, world, stack, shooter, shotFrom);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        try {
            ModdedPersistentProjectileEntity arrowEntity = (ModdedPersistentProjectileEntity) entityClass.getConstructor(
                    EntityType.class, Double.class, Double.class, Double.class, World.class, ItemStack.class)
                    .newInstance(entityType, pos.getX(), pos.getY(), pos.getZ(), world, stack.copyWithCount(1));
            arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
            return arrowEntity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public float getPullProgressMultiplier(LivingEntity player) {
        return 1;
    }
}
