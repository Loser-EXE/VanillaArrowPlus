package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.IronArrowEntity;
import net.minecraft.item.Item;

public class IronArrowItem extends ModdedArrowItem {
    public IronArrowItem(Item.Settings settings) {
        super(IronArrowEntity.class, ModEntityTypes.IRON_ARROW, settings);
    }
}
