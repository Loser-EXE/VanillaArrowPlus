package com.loserexe.vanillaarrowplus.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.projectile.EchoArrowEntity;
import com.loserexe.vanillaarrowplus.entity.projectile.RedstoneArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntityTypes {
    //Only way I was able to modify the Builder :/
    private static final EntityType.Builder<RedstoneArrowEntity> redstoneArrowEntityBuilder = EntityType.Builder.create(RedstoneArrowEntity::new, SpawnGroup.MISC);
    public static EntityType<RedstoneArrowEntity> REDSTONE_ARROW = register("redstone_arrow", redstoneArrowEntityBuilder.dimensions(0.5f, 0.5f).eyeHeight(0.13f));

    private static final EntityType.Builder<EchoArrowEntity> echoArrowEntityBuilder = EntityType.Builder.create(EchoArrowEntity::new, SpawnGroup.MISC);
    public static EntityType<EchoArrowEntity> ECHO_ARROW = register("echo_arrow", echoArrowEntityBuilder.dimensions(0.5f, 0.5f).eyeHeight(0.13f));

    //Was only able to register redstone arrow through this method?!!?!??!?!?
    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> entity) {
        return Registry.register(Registries.ENTITY_TYPE,
                Identifier.of(VanillaArrowPlus.MOD_ID, name),
                entity.build());
    }

    public static void register() {
        VanillaArrowPlus.LOGGER.info("Registering Entities!");
    }
}
