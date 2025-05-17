package com.owenxm.morepottedplants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.Direction;

public class FacingFlowerPotBlock extends FlowerPotBlock {
    public static final DirectionProperty FACING;
    public FacingFlowerPotBlock(Block content, Settings settings) {
        super(content, settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }

}
