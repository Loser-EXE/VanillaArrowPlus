package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.ChargedCopperArrowEntity;
import net.minecraft.item.Item;

public class ChargedCopperArrowItem extends ModdedArrowItem {
    public ChargedCopperArrowItem(Item.Settings settings) {
        super(ChargedCopperArrowEntity.class, ModEntityTypes.CHARGED_COPPER_ARROW, settings);
    }
}
