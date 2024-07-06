package com.loserexe.vanillaarrowplus.client.render.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.projectile.EchoArrowEntity;
import com.loserexe.vanillaarrowplus.entity.projectile.RedstoneArrowEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import java.util.HashMap;
import java.util.Map;

public class VanillaArrowPlusArrowRenderer extends ProjectileEntityRenderer<PersistentProjectileEntity> {
    private static final Map<Class<? extends PersistentProjectileEntity>, String> arrowTextures = new HashMap<>();
    private static final Map<Class<? extends PersistentProjectileEntity>, Integer> arrowShaftLengths = new HashMap<>();

    public VanillaArrowPlusArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(PersistentProjectileEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        int shaftLength = arrowShaftLengths.getOrDefault(persistentProjectileEntity.getClass(), 16);
        float shaftEndUV = shaftLength/32f;

        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevYaw, persistentProjectileEntity.getYaw()) - 90.0f));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevPitch, persistentProjectileEntity.getPitch())));

        float s = (float) persistentProjectileEntity.shake - g;
        if (s > 0.0f) {
            float t = -MathHelper.sin(s * 3.0f) * s;
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(t));
        }

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0f));
        matrixStack.scale(0.05625f, 0.05625f, 0.05625f);
        matrixStack.translate(-4.0f, 0.0f, 0.0f);

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture(persistentProjectileEntity)));
        MatrixStack.Entry entry = matrixStack.peek();

        this.vertex(entry, vertexConsumer, -7, -2, -2, 0.0f, 0.15625f, -1, 0, 0, i); //0, 5
        this.vertex(entry, vertexConsumer, -7, -2, 2, 0.15625f, 0.15625f, -1, 0, 0, i); //5, 5
        this.vertex(entry, vertexConsumer, -7, 2, 2, 0.15625f, 0.3125f, -1, 0, 0, i); //5, 10
        this.vertex(entry, vertexConsumer, -7, 2, -2, 0.0f, 0.3125f, -1, 0, 0, i);// 0, 10

        this.vertex(entry, vertexConsumer, -7, 2, -2, 0.0f, 0.15625f, 1, 0, 0, i);//0, 5
        this.vertex(entry, vertexConsumer, -7, 2, 2, 0.15625f, 0.15625f, 1, 0, 0, i);//5, 5
        this.vertex(entry, vertexConsumer, -7, -2, 2, 0.15625f, 0.3125f, 1, 0, 0, i);//5, 10
        this.vertex(entry, vertexConsumer, -7, -2, -2, 0.0f, 0.3125f, 1, 0, 0, i);//0, 10

        for (int u = 0; u < 4; ++u) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0f));

            this.vertex(entry, vertexConsumer, -8, -2, 0, 0.0f, 0.0f, 0, 1, 0, i); //0, 0
            this.vertex(entry, vertexConsumer, 8, -2, 0, shaftEndUV, 0.0f, 0, 1, 0, i);//16, 0
            this.vertex(entry, vertexConsumer, 8, 2, 0, shaftEndUV, 0.15625f, 0, 1, 0, i);//16, 5
            this.vertex(entry, vertexConsumer, -8, 2, 0, 0.0f, 0.15625f, 0, 1, 0, i);//0, 5
        }

        matrixStack.pop();
    }

    @Override
    public Identifier getTexture(PersistentProjectileEntity entity) {
        return Identifier.of(VanillaArrowPlus.MOD_ID, "textures/entity/projectiles/" + arrowTextures.get(entity.getClass()));
    }

    static {
        arrowTextures.put(RedstoneArrowEntity.class, "redstone_arrow.png");
        arrowTextures.put(EchoArrowEntity.class, "echo_arrow.png");

        arrowShaftLengths.put(EchoArrowEntity.class, 19);
    }
}
