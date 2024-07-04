package com.loserexe.vanillaarrowplus.entity.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlazingArrowEntity extends AbstractArrowEntity {
    private final ItemStack bow;

    public BlazingArrowEntity(World world, ItemStack stack, LivingEntity owner, @Nullable ItemStack shotFrom) {
        super(world, stack, owner, shotFrom);
        this.bow = shotFrom;

    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        //Bruh
        boolean bowIsEnchantedWithFlame = EnchantmentHelper.getLevel(
                this.getWorld().getRegistryManager()
                        .getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FLAME), bow) != 0;

        if(bowIsEnchantedWithFlame) {
            entityHitResult.getEntity().setOnFireForTicks(200);
        } else {
            entityHitResult.getEntity().setOnFireForTicks(100);
        }
    }
}
