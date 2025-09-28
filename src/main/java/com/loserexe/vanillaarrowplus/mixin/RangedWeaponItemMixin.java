package com.loserexe.vanillaarrowplus.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.loserexe.vanillaarrowplus.item.ModdedArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(RangedWeaponItem.class)
public class RangedWeaponItemMixin {
    @ModifyVariable(method = "shootAll", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float modifySpeed(float speed, @Local(argsOnly = true) List<ItemStack> projectiles) {
        ItemStack projectile = projectiles.getLast(); //Only time it has multiple is with multishot and they are all the same projectile.
        if (projectile.getItem() instanceof ModdedArrowItem moddedArrowItem) {
            return speed * moddedArrowItem.getSpeedMultiplier();
        }
        return speed;
    }
}
