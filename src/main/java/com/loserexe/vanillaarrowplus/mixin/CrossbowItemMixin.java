package com.loserexe.vanillaarrowplus.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.item.BlazingArrowItem;
import com.loserexe.vanillaarrowplus.item.ModdedArrowItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;


@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin extends RangedWeaponItem {
    @Shadow public abstract Predicate<ItemStack> getHeldProjectiles();

    @Shadow public abstract Predicate<ItemStack> getProjectiles();

    @Shadow private boolean charged;

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

    @ModifyArg(method = "getPullTime", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getCrossbowChargeTime(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;F)F"), index = 2)
    private static float baseCrossbowChargeTimeInjector(float baseCrossbowChargeTime, @Local(argsOnly = true) LivingEntity user, @Local(argsOnly = true) ItemStack crossbow) {
        ItemStack projectileType = user.getProjectileType(crossbow);
        if (projectileType.getItem() instanceof ModdedArrowItem moddedArrowItem) {
            return baseCrossbowChargeTime * moddedArrowItem.getCrossbowPullProgressMultiplier(user);
        }
        return baseCrossbowChargeTime;
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);
        World world = attacker.getWorld();
        if (world.isClient) return;
        ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent == null) return;
        ItemStack projectile = chargedProjectilesComponent.getProjectiles().getLast();
        if (projectile.getItem() instanceof BlazingArrowItem) {
            Vec3d direction = attacker.getPos().subtract(target.getPos()).normalize();

            attacker.setVelocity(attacker.getVelocity().add(direction.multiply(1.2)));
            target.setVelocity(target.getVelocity().add(direction.multiply(1.2)).negate());

            // For some reason the players velocity won't update unless this is set to true. Spent HOURS on this set the target to true as well for consistency i guess.
            attacker.velocityModified = true;
            target.velocityModified = true;

            stack.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
            stack.damage(5, attacker, attacker.getActiveHand());
            world.createExplosion(attacker, target.getX(), target.getY(), target.getZ(), 1.5f, true, World.ExplosionSourceType.MOB);
        }
    }
}
