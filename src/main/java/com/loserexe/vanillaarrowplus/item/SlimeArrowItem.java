package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.SlimeArrowEntity;
import net.minecraft.item.Item;

public class SlimeArrowItem extends ModdedArrowItem {
    public SlimeArrowItem(Item.Settings settings) {
        super(SlimeArrowEntity.class, ModEntityTypes.SLIME_ARROW, settings);
    }
}
