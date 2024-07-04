package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class QuartzArrowEntity extends AbstractArrowEntity {
    public QuartzArrowEntity(World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(world, stack, owner, shotFrom);
    }

    @Override
    protected float getDragInWater() {
        return 1f;
    }
}
