package com.owenxm.morepottedplants;

import com.google.common.collect.Maps;
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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.apache.commons.compress.harmony.unpack200.IMatcher;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AlternativeFlowerPotBlock extends Block {
    public static final BooleanProperty LEAF = BooleanProperty.of("leaf");
    public static final BooleanProperty SET = BooleanProperty.of("set");
    protected static final VoxelShape SHAPE = Block.createCuboidShape((double)5.0F, (double)0.0F, (double)5.0F, (double)11.0F, (double)6.0F, (double)11.0F);

    public AlternativeFlowerPotBlock(Settings settings) {
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

    public static boolean useRegex(final String input) {
        // Compile regular expression
        final Pattern pattern = Pattern.compile("[A-Za-z]+\\{[A-Za-z]+:leafless_[A-Za-z]+_[A-Za-z]+\\}", Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }

    //I wish to make LEAFLESS_* into * but I haven't a clue how to do that
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
            if (direction == Direction.UP && neighborState.isAir() && !state.get(SET)) {
//                String text = String.valueOf(this);
//                System.out.println(text);
                // I've a feeling I don't need to be doing all this cuz all the regex is doing is confirming something
                // that's always going to be true
//                if (useRegex(text)) {
                if (this == ModBlocks.LEAFLESS_POTTED_BAMBOO) {
                    // temp hack
                    // System.out.println("eugh");
                     world.setBlockState(pos, Blocks.POTTED_BAMBOO.getDefaultState(), 2);
                }
            }

            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        if (this == ModBlocks.LEAFLESS_POTTED_BAMBOO) {
            return new ItemStack(Blocks.BAMBOO);
        }
return null;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        BlockState blockState = world.getBlockState(pos);
        if (itemStack.getItem() instanceof BoneMealItem && !blockState.get(LEAF)) {
            state = state.with(StackedBambooBlock.LEAF, true);
            state = state.with(StackedBambooBlock.SET, true);
            world.setBlockState(pos, state);
            world.playSound(player, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1); // :D
            }
            player.incrementStat(Stats.USED.getOrCreateStat(Items.BONE_MEAL));
        }
        if (itemStack.getItem() instanceof ShearsItem && blockState.get(LEAF)) {
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

    private static final VoxelShape BAMBOO_SHAPE;


    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BAMBOO_SHAPE;
    }


    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    static {
        BAMBOO_SHAPE = VoxelShapes.union(Block.createCuboidShape((double) 5.0F, (double) 0.0F, (double) 5.0F, (double) 11.0F, (double) 6.0F, (double) 11.0F), Block.createCuboidShape((double)7.0F, (double)0.0F, (double)7.0F, (double)9.0F, (double)16.0F, (double)9.0F));
    }
}
