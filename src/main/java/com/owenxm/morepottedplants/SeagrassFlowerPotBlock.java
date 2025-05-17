package com.owenxm.morepottedplants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

import static com.owenxm.morepottedplants.TallFlowerPotBlock.HALF;

public class SeagrassFlowerPotBlock extends Block {
    public static final float field_31095 = 3.0F;
    protected static final VoxelShape SHAPE = Block.createCuboidShape((double) 3.0F, (double) 0.0F, (double) 3.0F, (double) 13.0F, (double) 10.0F, (double) 13.0F);
    Item content = Items.SEAGRASS;

    public SeagrassFlowerPotBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        ItemStack itemStack2 = new ItemStack(content);
        if (item instanceof BoneMealItem && state.getBlock() == ModBlocks.POTTED_SEAGRASS) {
            BlockPos blockPos = pos.up();
            BlockState blockState2 = world.getBlockState(blockPos);
            if (blockState2.isAir()) {
                world.setBlockState(pos, ModBlocks.POTTED_TALL_SEAGRASS.getDefaultState().with(HALF, DoubleBlockHalf.LOWER), 3);
                world.setBlockState(blockPos, ModBlocks.POTTED_TALL_SEAGRASS.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 3);
                world.playSound(player, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
                return ActionResult.success(world.isClient);
            }
            return ActionResult.CONSUME;
        }
                if (itemStack.isEmpty()) {
                    player.setStackInHand(hand, itemStack2);
                } else if (!player.giveItemStack(itemStack2)) {
                    player.dropItem(itemStack2, false);
                }

                world.setBlockState(pos, Blocks.FLOWER_POT.getDefaultState(), 3);

            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return ActionResult.success(world.isClient);
        }


    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(content);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
