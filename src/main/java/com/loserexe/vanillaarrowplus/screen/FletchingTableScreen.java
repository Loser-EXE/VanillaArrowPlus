package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

public class FletchingTableScreen extends HandledScreen<FletchingTableScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "textures/gui/container/fletching_table.png");
    private static final Identifier TIPPING_FILL_AMOUNT_BAR = Identifier.of(VanillaArrowPlus.MOD_ID, "textures/gui/container/fletching_table/tipping_material_progress_bar.png");

    public FletchingTableScreen(FletchingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
        float tippingMaterialFill = handler.getTippingMaterial().getFillAmount();
        if (tippingMaterialFill > 0) {
            int offset = (int) Math.ceil(tippingMaterialFill * 16);
            context.drawTexture(RenderLayer::getGuiTextured, TIPPING_FILL_AMOUNT_BAR, i + 166, (j + 15) - (offset - 16), 0, offset, 2, offset, 2, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
