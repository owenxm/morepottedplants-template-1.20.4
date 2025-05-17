package com.owenxm.morepottedplants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnchantedGoldenAppleFlowerPotBlockEntity extends BlockEntity {
    public EnchantedGoldenAppleFlowerPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.POTTED_ENCHANTED_GOLDEN_APPLE_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, EnchantedGoldenAppleFlowerPotBlockEntity blockEntity) {
    }
}
