package com.loserexe.vanillaarrowplus.screen;

import com.loserexe.vanillaarrowplus.VanillaArrowPlus;
import com.loserexe.vanillaarrowplus.recpie.FletchingTableRecipeRegistry;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;


public class FletchingTableScreen extends HandledScreen<FletchingTableScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "textures/gui/container/fletching_table.png");
    private static final Identifier TIPPING_FILL_AMOUNT_BAR_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "textures/gui/sprites/container/fletching_table/tipping_material_progress_bar.png");
    private static final Identifier DISABLED_SLOT_TEXTURE = Identifier.ofVanilla("container/crafter/disabled_slot");
    private static final Identifier X_TEXTURE = Identifier.of(VanillaArrowPlus.MOD_ID, "textures/gui/sprites/container/fletching_table/x.png");


    public FletchingTableScreen(FletchingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }

    private void drawX(DrawContext context, int x, int y) {
        context.drawTexture(RenderPipelines.GUI_TEXTURED, X_TEXTURE, x, y, 0f, 0f, 15, 15, 15,15);
    }

    @Override
    protected void drawSlot(DrawContext context, Slot slot) {
        handler.updateSlotsState();
        if (slot instanceof FletchingTableScreenHandler.TippingMaterialSlot tippingMaterialSlot) {
            if (tippingMaterialSlot.hasStack()) {
                context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, DISABLED_SLOT_TEXTURE, slot.x - 1, slot.y - 1, 18, 18);
                context.drawItem(slot.getStack(), slot.x, slot.y);
                return;
            }
        }

        super.drawSlot(context, slot);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int localX = (this.width - this.backgroundWidth) / 2;
        int localY = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, localX, localY, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
        int tippingMaterialFill = handler.getTippingFillAmount();
        if (tippingMaterialFill > 0) {
            int offset = (int) Math.ceil((tippingMaterialFill/32F) * 16);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, TIPPING_FILL_AMOUNT_BAR_TEXTURE, localX + 166, (localY + 15) - (offset - 16), 0, offset, 2, offset, 2, 16);
        }
        FletchingTableRecipeRegistry.CraftingMethod craftingMethod = handler.getAttemptedCraftingMethod();
        if (craftingMethod == FletchingTableRecipeRegistry.CraftingMethod.TIPPING) {
            drawX(context, localX + 77, localY + 40);
        } else if (craftingMethod == FletchingTableRecipeRegistry.CraftingMethod.CRAFTING) {
            drawX(context, localX + 125, localY + 40);
        }
    }

    @Override
    protected void drawMouseoverTooltip(DrawContext drawContext, int x, int y) {
        super.drawMouseoverTooltip(drawContext, x, y);
        FletchingTableRecipeRegistry.CraftingMethod craftingMethod = handler.getAttemptedCraftingMethod();
        Optional<Text> text = Optional.empty();

        if (craftingMethod == FletchingTableRecipeRegistry.CraftingMethod.CRAFTING && this.isPointWithinBounds(121, 41, 21, 13, x, y)) {
            text = Optional.of(Text.translatable("container.fletching_table.cant_tip"));
        } else if (craftingMethod == FletchingTableRecipeRegistry.CraftingMethod.TIPPING && this.isPointWithinBounds(74, 41, 20, 13, x, y)) {
            text = Optional.of(Text.translatable("container.fletching_table.cant_craft"));
        }

        text.ifPresent(tooltip -> drawContext.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(tooltip,233), x, y));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
