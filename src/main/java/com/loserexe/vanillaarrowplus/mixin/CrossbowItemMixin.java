package com.loserexe.vanillaarrowplus.mixin;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;


@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends RangedWeaponItem {
    @Shadow public abstract Predicate<ItemStack> getHeldProjectiles();

    @Shadow public abstract Predicate<ItemStack> getProjectiles();

    public CrossbowItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getHeldProjectiles", at = @At("HEAD"), cancellable = true)
    public void getHeldProjectiles(CallbackInfoReturnable<Predicate<ItemStack>> cir) {
        cir.setReturnValue(CROSSBOW_HELD_PROJECTILES.or(itemStack -> itemStack.isIn(VanillaArrowPlus.CROSSBOW_ARROWS)));
    }

    @Inject(method = "getProjectiles", at = @At("HEAD"), cancellable = true)
    public void getProjectiles(CallbackInfoReturnable<Predicate<ItemStack>> cir) {
        cir.setReturnValue(BOW_PROJECTILES.or(itemStack -> itemStack.isIn(VanillaArrowPlus.CROSSBOW_ARROWS)));
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.LEFT) return false;
        ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent == null || chargedProjectilesComponent.isEmpty()) return false;
        ItemStack projectile = chargedProjectilesComponent.getProjectiles().getLast(); // The only time it would have multiple is with multishot. Prevents arrows from being duplicated.
        projectile.remove(DataComponentTypes.INTANGIBLE_PROJECTILE);
        stack.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
        cursorStackReference.set(projectile);
        return true;
    }
}
