package com.loserexe.vanillaarrowplus.client.render.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.projectile.RedstoneArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class RedstoneArrowEntityRenderer extends ProjectileEntityRenderer<RedstoneArrowEntity> {
    public RedstoneArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(RedstoneArrowEntity entity) {
        return Identifier.of(VanillaArrowPlus.MOD_ID, "textures/entity/projectiles/redstone_arrow.png");
    }
}
