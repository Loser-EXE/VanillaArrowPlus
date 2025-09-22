package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.HoneyArrowEntity;
import net.minecraft.item.Item;

public class HoneyArrowItem extends ModdedArrowItem {
    public HoneyArrowItem(Item.Settings settings) {
        super(HoneyArrowEntity.class, ModEntityTypes.HONEY_ARROW, settings);
    }
}
