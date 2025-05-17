package com.owenxm.morepottedplants;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class FlowerbedFlowerPotBlock extends FlowerPotBlock implements Fertilizable {
    public static final DirectionProperty FACING;
    public static final IntProperty FLOWER_AMOUNT;
    private final Block content;

    public FlowerbedFlowerPotBlock(Block content, Settings settings) {
        super(content, settings);
        this.content = content;
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(FLOWER_AMOUNT, 1));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, FLOWER_AMOUNT});
    }

    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int i = (Integer)state.get(FLOWER_AMOUNT);
        if (i < 4) {
            world.setBlockState(pos, (BlockState)state.with(FLOWER_AMOUNT, i + 1), 2);
        } else {
            dropStack(world, pos, new ItemStack(this));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        BlockState blockState = world.getBlockState(pos);
        int i = (Integer)state.get(FLOWER_AMOUNT);
        if (item instanceof BoneMealItem) {
            if (i < 4) {
                state = state.with(FLOWER_AMOUNT, i + 1);
            } else {
                dropStack(world, pos, new ItemStack(content));
            }
            world.setBlockState(pos, state);
            world.playSound(player, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1); // :D
            }
            player.incrementStat(Stats.USED.getOrCreateStat(Items.BONE_MEAL));
            return ActionResult.SUCCESS;
        }
        if (item == content.asItem() && i < 4) {
            state = state.with(FLOWER_AMOUNT, i + 1);
            world.setBlockState(pos, state);
            player.incrementStat(Stats.USED.getOrCreateStat(content.asItem())); // :D
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
            ItemStack itemStack2 = new ItemStack(content, i);
            if (itemStack.isEmpty()) {
                player.setStackInHand(hand, itemStack2);
            } else if (!player.giveItemStack(itemStack2)) {
                player.dropItem(itemStack2, false);
            }

            world.setBlockState(pos, Blocks.FLOWER_POT.getDefaultState(), 3);

            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        return ActionResult.SUCCESS;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        FLOWER_AMOUNT = Properties.FLOWER_AMOUNT;
    }
}
