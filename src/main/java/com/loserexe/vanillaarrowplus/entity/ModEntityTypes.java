package com.loserexe.vanillaarrowplus.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.projectile.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntityTypes {
    public static final EntityType<AerialArrowEntity> AERIAL_ARROW = registerProjectileEntity("aerial_arrow", AerialArrowEntity::new);
    public static final EntityType<AmethystArrowEntity> AMETHYST_ARROW = registerProjectileEntity("amethyst_arrow", AmethystArrowEntity::new);
    public static final EntityType<BlazingArrowEntity> BLAZING_ARROW = registerProjectileEntity("blazing_arrow", BlazingArrowEntity::new);
    public static final EntityType<CarrotArrowEntity> CARROT_ARROW = registerProjectileEntity("carrot_arrow", CarrotArrowEntity::new);
    public static final EntityType<EchoArrowEntity> ECHO_ARROW = registerProjectileEntity("echo_arrow", EchoArrowEntity::new);
    public static final EntityType<GoldArrowEntity> GOLD_ARROW = registerProjectileEntity("gold_arrow", GoldArrowEntity::new);
    public static final EntityType<HoneyArrowEntity> HONEY_ARROW = registerProjectileEntity("honey_arrow", HoneyArrowEntity::new);
    public static final EntityType<IronArrowEntity> IRON_ARROW = registerProjectileEntity("iron_arrow", IronArrowEntity::new);
    public static final EntityType<PrismarineArrowEntity> PRISMARINE_ARROW = registerProjectileEntity("prismarine_arrow", PrismarineArrowEntity::new);
    public static final EntityType<RedstoneArrowEntity> REDSTONE_ARROW = registerProjectileEntity("redstone_arrow", RedstoneArrowEntity::new);
    public static final EntityType<SlimeArrowEntity> SLIME_ARROW = registerProjectileEntity("slime_arrow", SlimeArrowEntity::new);

    public static final EntityType<AmethystShardEntity> AMETHYST_SHARD = registerEntity("amethyst_shard", AmethystShardEntity::new, 0.25f, 0.1f);

    private static <T extends ProjectileEntity> EntityType<T> registerProjectileEntity(String name, EntityType.EntityFactory<T> entityBuilder) {
        EntityType.Builder<T> builder = EntityType.Builder.create(entityBuilder, SpawnGroup.MISC)
                .dimensions(0.5f, 0.5f)
                .eyeHeight(0.13f);

        return Registry.register(Registries.ENTITY_TYPE,
                Identifier.of(VanillaArrowPlus.MOD_ID, name),
                builder.build(keyOf(name)));
    }

    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.EntityFactory<T> entityBuilder, float size, float eyeHeight) {
        EntityType.Builder<T> builder = EntityType.Builder.create(entityBuilder, SpawnGroup.MISC)
                .dimensions(size, size)
                .eyeHeight(eyeHeight);

        return Registry.register(Registries.ENTITY_TYPE,
                Identifier.of(VanillaArrowPlus.MOD_ID, name),
                builder.build(keyOf(name)));
    }

    private static RegistryKey<EntityType<?>> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(VanillaArrowPlus.MOD_ID, id));
    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering Entities!");
    }
}
