package com.loserexe.vanillaarrowplus.entity.projectile;

import com.loserexe.vanillaarrowplus.entity.effect.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HoneyArrowEntity extends AbstractArrowEntity {
    public HoneyArrowEntity(World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(world, stack, owner, shotFrom);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        if(entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            StatusEffectInstance honeyed = new StatusEffectInstance(ModStatusEffects.HONEYED, 100);
            livingEntity.addStatusEffect(honeyed);
        }
    }
}
