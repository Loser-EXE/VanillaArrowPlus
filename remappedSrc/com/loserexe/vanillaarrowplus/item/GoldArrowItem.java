package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.GoldArrowEntity;
import net.minecraft.item.Item;

public class GoldArrowItem extends ModdedArrowItem {
    public GoldArrowItem(Item.Settings settings) {
        super(GoldArrowEntity.class, ModEntityTypes.GOLD_ARROW, settings);
    }
}
