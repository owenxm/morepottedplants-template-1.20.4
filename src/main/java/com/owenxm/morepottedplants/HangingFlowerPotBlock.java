package com.owenxm.morepottedplants;

import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

import static org.apache.commons.lang3.BooleanUtils.or;

public class HangingFlowerPotBlock extends FlowerPotBlock {
    public static final BooleanProperty HANGING = BooleanProperty.of("hanging");
    protected static final VoxelShape SHAPE = Block.createCuboidShape((double)5.0F, (double)10.0F, (double)5.0F, (double)11.0F, (double)16.0F, (double)11.0F);
    private static final Map<Block, Block> CONTENT_TO_POTTED = Maps.newHashMap();
    private final Block content;

    protected static final VoxelShape STANDING_SHAPE;
    protected static final VoxelShape HANGING_SHAPE;

    public HangingFlowerPotBlock(Block content, Settings settings) {
        super(content, settings);
        this.content = content;
        CONTENT_TO_POTTED.put(content, this);
        setDefaultState(getDefaultState().with(HANGING, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(HANGING);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Boolean)state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Boolean)state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }




    static {
        HANGING_SHAPE = Block.createCuboidShape((double)5.0F, (double)10.0F, (double)5.0F, (double)11.0F, (double)16.0F, (double)11.0F);
        STANDING_SHAPE = Block.createCuboidShape((double)5.0F, (double)0.0F, (double)5.0F, (double)11.0F, (double)6.0F, (double)11.0F);
    }
}
