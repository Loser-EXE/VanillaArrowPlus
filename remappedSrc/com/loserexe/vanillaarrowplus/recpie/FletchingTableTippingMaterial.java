package com.loserexe.vanillaarrowplus.recpie;

import com.loserexe.vanillaarrowplus.item.ModItems;
import com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

import static com.loserexe.vanillaarrowplus.screen.FletchingTableScreenHandler.*;

public class FletchingTableTippingMaterial {
    public static final Codec<FletchingTableTippingMaterial> CODEC;
    public static final List<Item> validItems;
    public static final FletchingTableTippingMaterial DEFAULT = new FletchingTableTippingMaterial(0, PotionContentsComponent.DEFAULT, Items.AIR);
    private int fillAmount;
    private PotionContentsComponent potionComponent = PotionContentsComponent.DEFAULT;
    private Item of = Items.AIR;

    public FletchingTableTippingMaterial() { }

    public FletchingTableTippingMaterial(int fillAmount, PotionContentsComponent potionComponent, Item of) {
        this.fillAmount = fillAmount;
        this.potionComponent = potionComponent;
        this.of = of;
    }

    public void setItem(ItemStack stack) {
        this.of = stack.getItem();
        this.fillAmount = 32;
        if (this.of == Items.POTION) {
            potionComponent = stack.get(DataComponentTypes.POTION_CONTENTS);
        }
    }

    public void reset() {
        this.of = Items.AIR;
        this.fillAmount = 0;
        this.potionComponent = PotionContentsComponent.DEFAULT;
    }

    public int getFillAmount() {
        return fillAmount;
    }

    public PotionContentsComponent getPotionComponent() {
        return potionComponent;
    }

    public Item getItem() {
        return of;
    }

    public void use(Inventory inventory) {
        fillAmount -= 1;
        if (fillAmount <= 0) {
            reset();
            inventory.setStack(TIPPING_MATERIAL_SLOT_INDEX, ItemStack.EMPTY);
        }
    }

    public void craft(Inventory inventory) {
        if(of == Items.AIR || inventory.getStack(ARROW_SLOT_INDEX).isEmpty()) {
            return;
        }
        ItemStack resultSlotStack = inventory.getStack(RESULT_SLOT_INDEX);
        if (!resultSlotStack.isEmpty()) return;
        ItemStack resultStack = ItemStack.EMPTY;
        if (of.equals(Items.REDSTONE)) {
            resultStack = new ItemStack(ModItems.REDSTONE_ARROW, 1);
        } else if (of.equals(Items.GLOWSTONE_DUST)) {
            resultStack = new ItemStack(Items.SPECTRAL_ARROW, 1);
        } else if (of.equals(Items.HONEY_BOTTLE)) {
            resultStack = new ItemStack(ModItems.HONEY_ARROW, 1);
        } else if (of.equals(Items.POTION)) {
            ItemStack arrow = new ItemStack(Items.TIPPED_ARROW, 1);
            arrow.set(DataComponentTypes.POTION_CONTENTS, this.potionComponent);
            resultStack = arrow;
        }
        inventory.setStack(RESULT_SLOT_INDEX, resultStack);
    }

    static {
        CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.optionalFieldOf("fillAmount", 0).forGetter(FletchingTableTippingMaterial::getFillAmount),
                PotionContentsComponent.CODEC.optionalFieldOf("potionComponent", PotionContentsComponent.DEFAULT).forGetter(FletchingTableTippingMaterial::getPotionComponent),
                Registries.ITEM.getCodec().optionalFieldOf("of", Items.AIR).forGetter(FletchingTableTippingMaterial::getItem)
        ).apply(instance, FletchingTableTippingMaterial::new));
        validItems = new ArrayList<>();
        validItems.add(Items.POTION);
        validItems.add(Items.GLOWSTONE_DUST);
        validItems.add(Items.REDSTONE);
        validItems.add(Items.HONEY_BOTTLE);
    }
}
