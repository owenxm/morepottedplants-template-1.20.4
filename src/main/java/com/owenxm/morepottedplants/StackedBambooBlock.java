package com.owenxm.morepottedplants;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class StackedBambooBlock extends Block {
    public static final BooleanProperty LEAF = BooleanProperty.of("leaf");
    public static final BooleanProperty SET = BooleanProperty.of("set");
    protected static final VoxelShape SHAPE = Block.createCuboidShape((double)7.0F, (double)0.0F, (double)7.0F, (double)9.0F, (double)16.0F, (double)9.0F);

    public StackedBambooBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LEAF, false));
        setDefaultState(getDefaultState().with(SET, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LEAF);
        builder.add(SET);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return (floor.isOf(ModBlocks.LEAFLESS_POTTED_BAMBOO) || floor.isOf(Blocks.POTTED_BAMBOO) || floor.isOf(ModBlocks.STACKED_BAMBOO));
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        } else {
                if (direction == Direction.UP && neighborState.isOf(ModBlocks.STACKED_BAMBOO) && !state.get(SET)) {
                    world.setBlockState(pos, ModBlocks.STACKED_BAMBOO.getDefaultState().with(LEAF, false), 2);
                }
                if (direction == Direction.UP && neighborState.isAir() && !state.get(SET)) {
                    world.setBlockState(pos, ModBlocks.STACKED_BAMBOO.getDefaultState().with(LEAF, true), 2);
                }



            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
    }


    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(Blocks.BAMBOO);
    }


    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        BlockState blockState = world.getBlockState(pos);
        if (item instanceof BoneMealItem && !blockState.get(LEAF)) {
            state = state.with(StackedBambooBlock.LEAF, true);
            state = state.with(StackedBambooBlock.SET, true);
            world.setBlockState(pos, state);
            world.playSound(player, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1); // :D
            }
            player.incrementStat(Stats.USED.getOrCreateStat(Items.BONE_MEAL));
        }
        if (item instanceof ShearsItem && blockState.get(LEAF)) {
            state = state.with(StackedBambooBlock.LEAF, false);
            state = state.with(StackedBambooBlock.SET, true);
            world.setBlockState(pos, state);
            world.playSound(player, pos, SoundEvents.BLOCK_GROWING_PLANT_CROP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().creativeMode) {
                itemStack.damage(1, player, (playerx) -> playerx.sendToolBreakStatus(hand));
            }
            world.emitGameEvent(player, GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS)); // :D
        }
        return ActionResult.FAIL;

    }
//    but only sometimes.

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
