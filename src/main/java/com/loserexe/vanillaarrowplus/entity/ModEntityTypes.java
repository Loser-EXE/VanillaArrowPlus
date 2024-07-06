package com.loserexe.vanillaarrowplus.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.projectile.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntityTypes {
    public static final EntityType<AmethystArrowEntity> AMETHYST_ARROW = register("amethyst_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<AmethystArrowEntity>) AmethystArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    public static final EntityType<BlazingArrowEntity> BLAZING_ARROW = register("blazing_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<BlazingArrowEntity>) BlazingArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    public static final EntityType<CarrotArrowEntity> CARROT_ARROW = register("carrot_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<CarrotArrowEntity>) CarrotArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    public static final EntityType<EchoArrowEntity> ECHO_ARROW = register("echo_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<EchoArrowEntity>) EchoArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    public static final EntityType<GoldArrowEntity> GOLD_ARROW = register("gold_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<GoldArrowEntity>) GoldArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    public static final EntityType<HoneyArrowEntity> HONEY_ARROW = register("honey_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<HoneyArrowEntity>) HoneyArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    public static final EntityType<IronArrowEntity> IRON_ARROW = register("iron_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<IronArrowEntity>) IronArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    public static final EntityType<QuartzArrowEntity> QUARTZ_ARROW = register("quartz_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<QuartzArrowEntity>) QuartzArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    public static final EntityType<RedstoneArrowEntity> REDSTONE_ARROW = register("redstone_arrow",
            EntityType.Builder.create((EntityType.EntityFactory<RedstoneArrowEntity>) RedstoneArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .eyeHeight(0.13f));

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> entity) {
        return Registry.register(Registries.ENTITY_TYPE,
                Identifier.of(VanillaArrowPlus.MOD_ID, name),
                entity.build());
    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering Entities!");
    }
}
