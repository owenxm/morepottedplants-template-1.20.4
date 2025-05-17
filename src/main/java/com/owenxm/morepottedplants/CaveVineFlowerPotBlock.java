package com.owenxm.morepottedplants;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class CaveVineFlowerPotBlock extends CropHangingFlowerPotBlock {
    public static final BooleanProperty BERRIES = BooleanProperty.of("berries");

    public CaveVineFlowerPotBlock(Block content, Settings settings) {
        super(content, settings);
        setDefaultState(getDefaultState().with(HANGING, false));
        setDefaultState(getDefaultState().with(BERRIES, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BERRIES);
    }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(Items.GLOW_BERRIES);
    }
    public static boolean hasBerries(BlockState state) {
        return (Boolean)state.get(BERRIES);
    }

    static ActionResult pickBerries(@Nullable Entity picker, BlockState state, World world, BlockPos pos) {
        if ((Boolean) state.get(BERRIES)) {
            Block.dropStack(world, pos, new ItemStack(Items.GLOW_BERRIES, 1));
            float f = MathHelper.nextBetween(world.random, 0.8F, 1.2F);
            world.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, f);
            BlockState blockState = (BlockState) state.with(BERRIES, false);
            world.setBlockState(pos, blockState, 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(picker, blockState));
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }
@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        BlockState blockState = world.getBlockState(pos);
        if (item instanceof BoneMealItem && !blockState.get(BERRIES)) {
                state = state.with(CaveVineFlowerPotBlock.BERRIES, true);

            world.setBlockState(pos, state);
            world.playSound(player, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1); // :D
            }
            player.incrementStat(Stats.USED.getOrCreateStat(Items.BONE_MEAL));
            return ActionResult.SUCCESS;
        }
        return pickBerries(player, state, world, pos);
    }
    }


