package com.owenxm.morepottedplants;

import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

import static net.minecraft.command.argument.ItemStackArgumentType.itemStack;

public class PottedCropBlock extends FlowerPotBlock implements Fertilizable {
    public static final BooleanProperty STUNTED = BooleanProperty.of("stunted");

    public static final int MAX_AGE = 7;

    private static final Map<Item, Item> CONTENT_TO_POTTED = Maps.newHashMap();

    public PottedCropBlock(Block content, Settings settings) {
        super(content, settings);
        setDefaultState(getDefaultState().with(STUNTED, false));
    }




    // public MapCodec<PottedCropBlock> getCodec() {
    //    return CODEC;
    //}



    protected IntProperty getAgeProperty() {
        return Properties.AGE_7;
    }

    public int getMaxAge() {
        return 7;
    }

    public int getAge(BlockState state) {
        return (Integer)state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age) {
        return (BlockState)this.getDefaultState().with(this.getAgeProperty(), age);
    }

    public final boolean isMature(BlockState state) {
        return this.getAge(state) >= this.getMaxAge();
    }

    public boolean hasRandomTicks(BlockState state) {

        return !(this.isMature(state) || state.get(STUNTED));
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = 3.0f;
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), 2);
                }
            }
        }

    }

    protected int getGrowthAmountStem(World world) {
        return MathHelper.nextInt(world.random, 2, 5);
    }

    public void applyGrowthStem(World world, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getGrowthAmountStem(world);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        world.setBlockState(pos, this.withAge(i), 2);
    }

    public void applyGrowthCocoa(World world, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + 1;
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        world.setBlockState(pos, this.withAge(i), 2);
    }


        public void applyGrowth(World world, BlockPos pos, BlockState state) {
        if (this == ModBlocks.POTTED_MELON_CROP || this == ModBlocks.POTTED_PUMPKIN_CROP)
            applyGrowthStem(world, pos, state);
        else if (this == ModBlocks.POTTED_COCOA_BEAN || this == ModBlocks.POTTED_TORCHFLOWER_CROP || this == ModBlocks.POTTED_PITCHER_CROP)
            applyGrowthCocoa(world, pos, state);
            else
        ((CropBlock) getContent()).applyGrowth(world, pos, state);
    world.setBlockState(pos, state.with(getAgeProperty(), world.getBlockState(pos).get(getAgeProperty())));
    }

    protected ItemConvertible getSeedsItem() {
        return this.getContent();
    }

    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        if (this == ModBlocks.POTTED_NETHER_WART)
            return false;
        return !isMature(state);
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
                    applyGrowth(world, pos, state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(getAgeProperty());
        builder.add(STUNTED);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (itemStack.getItem() instanceof ShearsItem) {
            if (!state.get(PottedCropBlock.STUNTED)) {
                state = state.with(PottedCropBlock.STUNTED, true);
                world.setBlockState(pos, state);
                world.playSound(player, pos, SoundEvents.BLOCK_GROWING_PLANT_CROP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                itemStack.damage(1, player, (playerx) -> playerx.sendToolBreakStatus(hand));
                world.emitGameEvent(player, GameEvent.SHEAR, pos);
                player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS)); // :D
            }
        }
        return ActionResult.FAIL;

    }
}
