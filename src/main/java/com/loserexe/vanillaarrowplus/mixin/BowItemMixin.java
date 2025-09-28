package com.loserexe.vanillaarrowplus.mixin;

import com.loserexe.vanillaarrowplus.item.ModdedArrowItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowItemMixin {
    @Unique
    private float pullProgressMultiplier = 1.0f;

    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfoReturnable<Boolean> cir) {
        stack.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
    }

    @Inject(method = "use", at = @At("HEAD"))
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = user.getStackInHand(hand);

        ItemStack projectileType = user.getProjectileType(stack);
        if (!stack.isEmpty()) stack.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(projectileType));
        if (projectileType.getItem() instanceof ModdedArrowItem arrow) {
            this.pullProgressMultiplier = arrow.getBowPullProgressMultiplier(user);
        }
    }

    @Redirect(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"))
    public float getPullProgressOverride(int useTicks) {
        return BowItem.getPullProgress((int) Math.floor(useTicks * this.pullProgressMultiplier));
    }
}
