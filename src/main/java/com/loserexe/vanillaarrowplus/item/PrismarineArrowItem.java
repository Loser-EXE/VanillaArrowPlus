package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.PrismarineArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;

public class PrismarineArrowItem extends ModdedArrowItem {
    public PrismarineArrowItem(Item.Settings settings) {
        super(PrismarineArrowEntity.class, ModEntityTypes.PRISMARINE_ARROW, settings);
    }

    @Override
    public float getPullProgressMultiplier(LivingEntity entity) {
        if (entity.isSubmergedInWater()) {
            return 1.25f;
        } else {
            return 0.75f;
        }
    }
}
