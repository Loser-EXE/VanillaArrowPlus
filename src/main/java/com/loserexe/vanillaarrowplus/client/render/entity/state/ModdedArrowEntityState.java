package com.loserexe.vanillaarrowplus.client.render.entity.state;

import com.loserexe.vanillaarrowplus.entity.projectile.ModdedPersistentProjectileEntity;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.entity.projectile.PersistentProjectileEntity;

public class ModdedArrowEntityState extends ProjectileEntityRenderState {
    public Class<? extends PersistentProjectileEntity> entityClass;
}
