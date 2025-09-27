package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.AerialArrowEntity;
import net.minecraft.item.Item;

public class AerialArrowItem extends ModdedArrowItem {
    public AerialArrowItem(Item.Settings settings) {
        super(AerialArrowEntity.class, ModEntityTypes.AERIAL_ARROW, settings);
    }
}
