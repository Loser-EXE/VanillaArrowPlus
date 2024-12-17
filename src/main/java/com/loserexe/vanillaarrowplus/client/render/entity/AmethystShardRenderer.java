package com.loserexe.vanillaarrowplus.client.render.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.entity.projectile.AmethystShardEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class AmethystShardRenderer extends EntityRenderer<AmethystShardEntity> {

    public AmethystShardRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(AmethystShardEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        int shaftLength = 6;

        float shaftEndUV = shaftLength/6f;

        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevYaw, persistentProjectileEntity.getYaw()) - 90.0f));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, persistentProjectileEntity.prevPitch, persistentProjectileEntity.getPitch())));

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0f));
        matrixStack.scale(0.05625f, 0.05625f, 0.05625f);
        matrixStack.translate(-4.0f, 0.0f, 0.0f);

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture(persistentProjectileEntity)));
        MatrixStack.Entry entry = matrixStack.peek();

        for (int u = 0; u < 4; ++u) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0f));

            this.vertex(entry, vertexConsumer, 0, -2, 0, 0.0f, 0.0f, 0, 1, 0, i);
            this.vertex(entry, vertexConsumer, 5, -2, 0, shaftEndUV, 0.0f, 0, 1, 0, i);
            this.vertex(entry, vertexConsumer, 5, 2, 0, shaftEndUV, 5f/7, 0, 1, 0, i);
            this.vertex(entry, vertexConsumer, 0, 2, 0, 0.0f, 5f/7, 0, 1, 0, i);
        }

        matrixStack.pop();
    }

    public void vertex(MatrixStack.Entry matrix, VertexConsumer vertexConsumer, int x, int y, int z, float u, float v, int normalX, int normalZ, int normalY, int light) {
        vertexConsumer.vertex(matrix, (float)x, (float)y, (float)z).color(Colors.WHITE).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix, normalX, normalY, normalZ);
    }

    @Override
    public Identifier getTexture(AmethystShardEntity entity) {
        int variant = entity.getVariant();

        if (variant > 7 || variant < 0) {
            variant = 0;
        }

        return Identifier.of(VanillaArrowPlus.MOD_ID, "textures/entity/projectiles/cluster/" + variant + ".png");
    }
}
