package com.loserexe.vanillaarrowplus.client.render.item.property.select;

import com.loserexe.vanillaarrowplus.item.ModItems;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.property.select.SelectProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class RangedWeaponProjectileTypeProperty implements SelectProperty<RangedWeaponProjectileTypeProperty.ProjectileType> {
    public static final SelectProperty.Type<RangedWeaponProjectileTypeProperty, ProjectileType> TYPE;

    public RangedWeaponProjectileTypeProperty() {}

    @Override
    public @Nullable ProjectileType getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ModelTransformationMode modelTransformationMode) {
        ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if (chargedProjectilesComponent == null || chargedProjectilesComponent.isEmpty()) return ProjectileType.NONE;
        String itemName = Registries.ITEM.getId(chargedProjectilesComponent.getProjectiles().getFirst().getItem()).getPath(); // Assumes they are all the same
        try {
            return ProjectileType.valueOf(itemName.toUpperCase());
        } catch (Exception e) {
            return ProjectileType.ARROW;
        }

    }

    @Override
    public Type<? extends SelectProperty<ProjectileType>, ProjectileType> getType() {
        return TYPE;
    }

    static {
        TYPE = Type.create(MapCodec.unit(new RangedWeaponProjectileTypeProperty()), ProjectileType.CODEC);
    }

    public enum ProjectileType implements StringIdentifiable {
        NONE("none"),
        FIREWORK_ROCKET("firework"),
        ARROW("arrow"),
        TIPPED_ARROW("tipped_arrow"),
        SPECTRAL_ARROW("spectral_arrow"),
        AERIAL_ARROW("aerial_arrow"),
        AMETHYST_ARROW("amethyst_arrow"),
        BLAZING_ARROW("blazing_arrow"),
        CARROT_ARROW("carrot_arrow"),
        CHARGED_COPPER_ARROW("charged_copper_arrow"),
        COPPER_ARROW("copper_arrow"),
        ECHO_ARROW("echo_arrow"),
        GOLD_ARROW("gold_arrow"),
        HONEY_ARROW("honey_arrow"),
        IRON_ARROW("iron_arrow"),
        PRISMARINE_ARROW("prismarine_arrow"),
        REDSTONE_ARROW("redstone_arrow"),
        SLIME_ARROW("slime_arrow");

        public static final Codec<RangedWeaponProjectileTypeProperty.ProjectileType> CODEC = StringIdentifiable.createCodec(RangedWeaponProjectileTypeProperty.ProjectileType::values);
        private final String name;

        ProjectileType(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }
}
