package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.EchoArrowEntity;
import net.minecraft.item.Item;

public class EchoArrowItem extends ModdedArrowItem {
    public EchoArrowItem(Item.Settings settings) {
        super(EchoArrowEntity.class, ModEntityTypes.ECHO_ARROW, settings);
    }
}
