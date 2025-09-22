package com.loserexe.vanillaarrowplus.client.render.item.tint;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record RangedWeaponTintSource(int defaultColor) implements TintSource {
    public static final MapCodec<RangedWeaponTintSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codecs.RGB.fieldOf("default").forGetter(RangedWeaponTintSource::defaultColor)).apply(instance, RangedWeaponTintSource::new));

    public RangedWeaponTintSource() {
        this(-13083194);
    }


    @Override
    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent == null) return defaultColor;
        ItemStack arrow = chargedProjectilesComponent.getProjectiles().getFirst();
        PotionContentsComponent potionContentsComponent = arrow.get(DataComponentTypes.POTION_CONTENTS);
        if (potionContentsComponent == null) return defaultColor;
        return potionContentsComponent.getColor();
    }

    @Override
    public MapCodec<? extends TintSource> getCodec() {
        return CODEC;
    }
}
