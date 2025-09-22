package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.CopperArrowEntity;
import net.minecraft.item.Item;

public class CopperArrowItem extends ModdedArrowItem {
    public CopperArrowItem(Item.Settings settings) {
        super(CopperArrowEntity.class, ModEntityTypes.COPPER_ARROW, settings);
    }
}
