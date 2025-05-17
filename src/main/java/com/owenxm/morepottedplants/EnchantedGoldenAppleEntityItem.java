package com.owenxm.morepottedplants;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantedGoldenAppleEntityItem extends Item {
    public EnchantedGoldenAppleEntityItem(Item.Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}