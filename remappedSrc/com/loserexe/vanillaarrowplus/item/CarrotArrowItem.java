package com.loserexe.vanillaarrowplus.item;

import com.loserexe.vanillaarrowplus.entity.ModEntityTypes;
import com.loserexe.vanillaarrowplus.entity.projectile.CarrotArrowEntity;
import net.minecraft.item.Item;

public class CarrotArrowItem extends ModdedArrowItem {
    public CarrotArrowItem(Item.Settings settings) {
        super(CarrotArrowEntity.class, ModEntityTypes.CARROT_ARROW, settings);
    }
}
