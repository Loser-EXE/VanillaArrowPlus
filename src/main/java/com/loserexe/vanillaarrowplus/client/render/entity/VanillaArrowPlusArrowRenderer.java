package com.loserexe.vanillaarrowplus.client.render.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.projectile.RedstoneArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class VanillaArrowPlusArrowRenderer extends ProjectileEntityRenderer<PersistentProjectileEntity> {
    private static final Map<Class<? extends PersistentProjectileEntity>, String> arrowTextures = new HashMap<>();

    public VanillaArrowPlusArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PersistentProjectileEntity entity) {
        return Identifier.of(VanillaArrowPlus.MOD_ID, "textures/entity/projectiles/" + arrowTextures.get(entity.getClass()));
    }

    static {
        arrowTextures.put(RedstoneArrowEntity.class, "redstone_arrow.png");
    }
}
