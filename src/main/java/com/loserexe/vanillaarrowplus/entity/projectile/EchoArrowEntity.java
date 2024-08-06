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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class EchoArrowEntity extends PersistentProjectileEntity {
    private LivingEntity target;

    public EchoArrowEntity(World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(ModEntityTypes.ECHO_ARROW, owner, world, stack, shotFrom);

        ((PersistentProjectileEntityAccessor) (this)).setDamage(6);

        this.target = getTarget();
    }

    public EchoArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    private LivingEntity getTarget() {
        List<Entity> entityList = this.getWorld().getOtherEntities(this, Box.of(this.getOwner().getPos(), 32, 32, 32));
        entityList = entityList.stream().filter(entity -> entity instanceof LivingEntity && entity != this.getOwner()).toList();

        try {
            return (LivingEntity) entityList.getFirst();
        } catch(NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(target != null && target.isAlive()) {
            Vec3d a = target.getPos().subtract(this.getPos());
            this.setVelocity(a.normalize());
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.ECHO_ARROW);
    }
}
