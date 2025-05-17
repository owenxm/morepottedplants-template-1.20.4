package com.owenxm.morepottedplants;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static void initialize() {}
    // an instance of our new item
    public static final Item ENCHANTED_GOLDEN_APPLE_ENTITY =
            // For versions below 1.21, use ''new Identifier("tutorial", "custom_item")''.
            Registry.register(Registries.ITEM, new Identifier("morepottedplants", "enchanted_golden_apple_entity"),
                    new EnchantedGoldenAppleEntityItem(new Item.Settings()));
}
