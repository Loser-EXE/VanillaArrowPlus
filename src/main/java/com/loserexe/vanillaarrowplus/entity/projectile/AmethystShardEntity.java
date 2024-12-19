package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class AmethystShardEntity extends ThrownEntity {
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(AmethystShardEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public AmethystShardEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(ModEntityTypes.AMETHYST_SHARD, world);
    }

    public AmethystShardEntity(double x, double y, double z, World world) {
        super(ModEntityTypes.AMETHYST_SHARD, x, y, z, world);
    }


    @Override
    public void handleStatus(byte status) {
        super.handleStatus(status);
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            for (int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(Items.AMETHYST_SHARD)), this.getX(), this.getY(), this.getZ(), random.nextBetween(-1, 1) /10f, random.nextBetween(-1, 1) /10f, random.nextBetween(-1, 1) /10f);
            }
            this.getWorld().playSound(this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.NEUTRAL, 1f, 1.5f, true);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.getWorld().isClient) {
            return;
        }
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
        this.discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        World world = entityHitResult.getEntity().getWorld();

        if (world.isClient) {
            return;
        }

        Entity victim = entityHitResult.getEntity();
        victim.damage((ServerWorld) world, this.getDamageSources().generic(), 2);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(VARIANT, random.nextInt(8));
    }

    public void setVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    public int getVariant() {
        return this.dataTracker.get(VARIANT);
    }


    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("variant", this.getVariant());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("variant")) {
            setVariant(nbt.getInt("variant"));
        }
    }
}
