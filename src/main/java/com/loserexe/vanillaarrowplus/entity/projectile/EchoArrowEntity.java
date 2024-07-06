package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.item.ModItems;
import com.loserexe.vanillaarrowplus.mixin.PersistentProjectileEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EchoArrowEntity extends PersistentProjectileEntity {
    public EchoArrowEntity(World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(ModEntityTypes.ECHO_ARROW, owner, world, stack, shotFrom);

        ((PersistentProjectileEntityAccessor) (this)).setDamage(6);
    }

    public EchoArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getOwner() == null) {
            return;
        }

        List<Entity> entityList = this.getWorld().getOtherEntities(this, Box.of(this.getOwner().getPos(), 32, 32, 32));
        entityList = entityList.stream().filter(entity -> (entity instanceof LivingEntity)).toList();
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.ECHO_ARROW);
    }
}
