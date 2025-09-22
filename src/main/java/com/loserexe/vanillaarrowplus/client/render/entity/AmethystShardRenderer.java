package com.loserexe.vanillaarrowplus.client.render.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.client.render.entity.state.AmethystShardEntityRenderState;
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
import net.minecraft.util.math.RotationAxis;

public class AmethystShardRenderer extends EntityRenderer<AmethystShardEntity, AmethystShardEntityRenderState> {
    public AmethystShardRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public AmethystShardEntityRenderState createRenderState() {
        return new AmethystShardEntityRenderState();
    }

    @Override
    public void updateRenderState(AmethystShardEntity entity, AmethystShardEntityRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.variant = entity.getVariant();
        state.yaw = entity.getLerpedYaw(tickDelta);
        state.pitch = entity.getLerpedPitch(tickDelta);
    }

    @Override
    public void render(AmethystShardEntityRenderState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light) {
        int shaftLength = 6;

        float shaftEndUV = shaftLength/6f;

        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.yaw - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.pitch));

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0f));
        matrixStack.scale(0.05625f, 0.05625f, 0.05625f);
        matrixStack.translate(-4.0f, 0.0f, 0.0f);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(this.getTexture(state)));
        MatrixStack.Entry entry = matrixStack.peek();

        for (int u = 0; u < 4; ++u) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0f));

            this.vertex(entry, vertexConsumer, 0, -2, 0, 0.0f, 0.0f, 0, 1, 0, light);
            this.vertex(entry, vertexConsumer, 5, -2, 0, shaftEndUV, 0.0f, 0, 1, 0, light);
            this.vertex(entry, vertexConsumer, 5, 2, 0, shaftEndUV, 5f/7, 0, 1, 0, light);
            this.vertex(entry, vertexConsumer, 0, 2, 0, 0.0f, 5f/7, 0, 1, 0, light);
        }

        matrixStack.pop();
    }

    private void vertex(MatrixStack.Entry matrix, VertexConsumer vertexConsumer, int x, int y, int z, float u, float v, int normalX, int normalZ, int normalY, int light) {
        vertexConsumer.vertex(matrix, (float)x, (float)y, (float)z).color(Colors.WHITE).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix, normalX, normalY, normalZ);
    }

    public Identifier getTexture(AmethystShardEntityRenderState state) {
        int variant = state.variant;

        if (variant > 7 || variant < 0) {
            variant = 0;
        }

        return Identifier.of(VanillaArrowPlus.MOD_ID, "textures/entity/projectiles/cluster/" + variant + ".png");
    }
}
