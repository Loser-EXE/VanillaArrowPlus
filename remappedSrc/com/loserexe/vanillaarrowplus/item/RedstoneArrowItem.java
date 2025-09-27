package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.RedstoneArrowEntity;
import net.minecraft.item.Item;

public class RedstoneArrowItem extends ModdedArrowItem {
    public RedstoneArrowItem(Item.Settings settings) {
        super(RedstoneArrowEntity.class, ModEntityTypes.REDSTONE_ARROW, settings);
    }
}
