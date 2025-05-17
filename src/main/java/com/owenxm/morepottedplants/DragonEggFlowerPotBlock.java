package com.owenxm.morepottedplants;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;


public class DragonEggFlowerPotBlock extends Block {
    public static final float field_31095 = 3.0F;
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)6.0F, (double)14.0F), Block.createCuboidShape((double) 3.0F, (double) 0.0F, (double) 3.0F, (double) 13.0F, (double) 16.0F, (double) 13.0F));
    Item content = Items.DRAGON_EGG;

    public DragonEggFlowerPotBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        ItemStack itemStack2 = new ItemStack(content);
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
