package com.owenxm.morepottedplants;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {
    public static void initialize() {}
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of("morepottedplants", path), blockEntityType);
    }

    public static final BlockEntityType<EnchantedGoldenAppleFlowerPotBlockEntity> POTTED_ENCHANTED_GOLDEN_APPLE_ENTITY = register(
            "potted_enchanted_golden_apple",
            // For versions before 1.21.2, please use BlockEntityType.Builder.
            BlockEntityType.Builder.create(EnchantedGoldenAppleFlowerPotBlockEntity::new, ModBlocks.POTTED_ENCHANTED_GOLDEN_APPLE).build()
    );


}

