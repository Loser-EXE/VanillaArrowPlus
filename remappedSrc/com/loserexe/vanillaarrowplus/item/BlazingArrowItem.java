package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.BlazingArrowEntity;
import net.minecraft.item.Item;

public class BlazingArrowItem extends ModdedArrowItem {
    public BlazingArrowItem(Item.Settings settings) {
        super(BlazingArrowEntity.class, ModEntityTypes.BLAZING_ARROW, settings);
    }
}
