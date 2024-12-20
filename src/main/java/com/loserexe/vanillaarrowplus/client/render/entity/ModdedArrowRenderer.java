package com.loserexe.vanillaarrowplus.client.render.entity;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.client.render.entity.state.ModdedArrowEntityState;
import com.loserexe.vanillaarrowplus.entity.projectile.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import java.util.HashMap;
import java.util.Map;

public class ModdedArrowRenderer extends ProjectileEntityRenderer<PersistentProjectileEntity, ModdedArrowEntityState> {
    private static final Map<Class<? extends PersistentProjectileEntity>, String> arrowTextures = new HashMap<>();
    private static final Map<Class<? extends PersistentProjectileEntity>, Integer> arrowShaftLengths = new HashMap<>();

    public ModdedArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public ModdedArrowEntityState createRenderState() {
        return new ModdedArrowEntityState();
    }

    @Override
    public void updateRenderState(PersistentProjectileEntity entity, ModdedArrowEntityState state, float f) {
        super.updateRenderState(entity, state, f);
        state.entityClass = entity.getClass();
        state.yaw = entity.getLerpedYaw(f);
        state.pitch = entity.getLerpedPitch(f);
        state.shake = entity.shake;
        state.delta = f;

    }

    @Override
    protected Identifier getTexture(ModdedArrowEntityState state) {
        return Identifier.of(VanillaArrowPlus.MOD_ID, "textures/entity/projectiles/" + arrowTextures.get(state.entityClass) + ".png");
    }

    @Override
    public void render(ModdedArrowEntityState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        int shaftLength = arrowShaftLengths.getOrDefault(state.getClass(), 16);

        float shaftEndUV = shaftLength/32f;

        int backX = (shaftLength/2);
        int frontX = shaftLength - backX;
        backX = -backX;

        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.yaw - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.pitch));

        float s = (float) state.shake - state.delta;
        if (s > 0.0f) {
            float t = -MathHelper.sin(s * 3.0f) * s;
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(t));
        }

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0f));
        matrixStack.scale(0.05625f, 0.05625f, 0.05625f);
        matrixStack.translate(-4.0f, 0.0f, 0.0f);

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(this.getTexture(state)));
        MatrixStack.Entry entry = matrixStack.peek();

        int feather = backX--;

        this.vertex(entry, vertexConsumer, feather, -2, -2, 0.0f, 0.15625f, -1, 0, 0, i); //0, 5
        this.vertex(entry, vertexConsumer, feather, -2, 2, 0.15625f, 0.15625f, -1, 0, 0, i); //5, 5
        this.vertex(entry, vertexConsumer, feather, 2, 2, 0.15625f, 0.3125f, -1, 0, 0, i); //5, 10
        this.vertex(entry, vertexConsumer, feather, 2, -2, 0.0f, 0.3125f, -1, 0, 0, i);// 0, 10

        this.vertex(entry, vertexConsumer, feather, 2, -2, 0.0f, 0.15625f, 1, 0, 0, i);//0, 5
        this.vertex(entry, vertexConsumer, feather, 2, 2, 0.15625f, 0.15625f, 1, 0, 0, i);//5, 5
        this.vertex(entry, vertexConsumer, feather, -2, 2, 0.15625f, 0.3125f, 1, 0, 0, i);//5, 10
        this.vertex(entry, vertexConsumer, feather, -2, -2, 0.0f, 0.3125f, 1, 0, 0, i);//0, 10

        for (int u = 0; u < 4; ++u) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0f));

            this.vertex(entry, vertexConsumer, backX, -2, 0, 0.0f, 0.0f, 0, 1, 0, i); //0, 0
            this.vertex(entry, vertexConsumer, frontX, -2, 0, shaftEndUV, 0.0f, 0, 1, 0, i);//16, 0
            this.vertex(entry, vertexConsumer, frontX, 2, 0, shaftEndUV, 0.15625f, 0, 1, 0, i);//16, 5
            this.vertex(entry, vertexConsumer, backX, 2, 0, 0.0f, 0.15625f, 0, 1, 0, i);//0, 5
        }

        matrixStack.pop();
    }

    private void vertex(MatrixStack.Entry matrix, VertexConsumer vertexConsumer, int x, int y, int z, float u, float v, int normalX, int normalZ, int normalY, int light) {
        vertexConsumer.vertex(matrix, (float)x, (float)y, (float)z).color(Colors.WHITE).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix, normalX, normalY, normalZ);
    }

    static {
        arrowTextures.put(AerialArrowEntity.class, "aerial_arrow");
        arrowTextures.put(AmethystArrowEntity.class, "amethyst_arrow");
        arrowTextures.put(BlazingArrowEntity.class, "blazing_arrow");
        arrowTextures.put(CarrotArrowEntity.class, "carrot_arrow");
        arrowTextures.put(EchoArrowEntity.class, "echo_arrow");
        arrowTextures.put(GoldArrowEntity.class, "gold_arrow");
        arrowTextures.put(HoneyArrowEntity.class, "honey_arrow");
        arrowTextures.put(IronArrowEntity.class, "iron_arrow");
        arrowTextures.put(PrismarineArrowEntity.class, "prismarine_arrow");
        arrowTextures.put(RedstoneArrowEntity.class, "redstone_arrow");
        arrowTextures.put(SlimeArrowEntity.class, "slime_arrow");

        arrowShaftLengths.put(AerialArrowEntity.class, 17);
        arrowShaftLengths.put(AmethystArrowEntity.class, 11);
        arrowShaftLengths.put(BlazingArrowEntity.class, 17);
        arrowShaftLengths.put(EchoArrowEntity.class, 19);
        arrowShaftLengths.put(IronArrowEntity.class, 15);
        arrowShaftLengths.put(SlimeArrowEntity.class, 17);

        arrowTextures.put(SpectralArrowEntity.class, "spectral_arrow");
        arrowShaftLengths.put(SpectralArrowEntity.class, 15);
    }
}
