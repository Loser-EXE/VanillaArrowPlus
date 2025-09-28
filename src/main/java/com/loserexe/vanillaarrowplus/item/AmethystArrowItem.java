package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.AmethystArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;

public class AmethystArrowItem extends ModdedArrowItem {
    public AmethystArrowItem(Item.Settings settings) {
        super(AmethystArrowEntity.class, ModEntityTypes.AMETHYST_ARROW, settings);
    }

    @Override
    public float getBowPullProgressMultiplier(LivingEntity player) {
        return 1.75f;
    }
}
