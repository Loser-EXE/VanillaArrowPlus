package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LightningRodScreen extends HandledScreen<LightningRodScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "textures/gui/container/lightning_rod.png");

    public LightningRodScreen(LightningRodScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
        super.init();
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int localX = (this.width - this.backgroundWidth) / 2;
        int localY = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE, localX, localY, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
