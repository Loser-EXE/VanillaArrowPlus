package com.loserexe.vanillaarrowplus.client.render.item.property.numeric;

import com.loserexe.vanillaarrowplus.item.ModdedArrowItem;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import net.minecraft.client.render.item.property.numeric.UseDurationProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class BowPullProperty implements NumericProperty {
    public static final MapCodec<BowPullProperty> CODEC = MapCodec.unit(new BowPullProperty());

    public BowPullProperty() {

    }

    @Override
    public float getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity holder, int seed) {
        if (holder == null) {
            return 0.0F;
        }  else {
            int usedTicks = UseDurationProperty.getTicksUsedSoFar(stack, holder);

            ItemStack bowStack = holder.getStackInHand(holder.getActiveHand());
            ItemStack arrowStack = holder.getProjectileType(bowStack);

            if (arrowStack.getItem() instanceof ModdedArrowItem arrowItem) {
                float pullProgressMultiplier = arrowItem.getBowPullProgressMultiplier(holder);
                return BowItem.getPullProgress((int) Math.floor(usedTicks * pullProgressMultiplier));
            }

            return BowItem.getPullProgress(usedTicks);
        }
    }

    @Override
    public MapCodec<? extends NumericProperty> getCodec() {
        return CODEC;
    }
}
