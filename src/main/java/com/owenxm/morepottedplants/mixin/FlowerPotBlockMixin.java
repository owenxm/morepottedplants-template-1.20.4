package com.owenxm.morepottedplants.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.owenxm.morepottedplants.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stat.Stats;
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
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

import static com.owenxm.morepottedplants.FacingFlowerPotBlock.FACING;
import static com.owenxm.morepottedplants.TallFlowerPotBlock.HALF;


@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlockMixin extends Block {
    public FlowerPotBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    public abstract Block getContent();

    @Shadow
    @Final
    private Block content;

    @Shadow
    @Final
    protected static VoxelShape SHAPE;
    private static final VoxelShape COLLISION_SHAPE;


    //  shears in remove content
    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z", ordinal = 0), method = "onUse")
    public boolean onUse(World instance, BlockPos pos, BlockState state, int flags, Operation<Boolean> original, @Local PlayerEntity player, @Local Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (state.getBlock() instanceof HangingFlowerPotBlock hangingFlowerPotBlock) {
            BlockPos blockPos = pos.down();
            BlockState blockState2 = instance.getBlockState(blockPos);
            if (blockState2.isAir()) {
                state = state.with(HangingFlowerPotBlock.HANGING, true);
            }
        }
        else if (state.getBlock() instanceof FacingFlowerPotBlock facingFlowerPotBlock || state.getBlock() instanceof FlowerbedFlowerPotBlock) {
            Direction facing = player.getHorizontalFacing().getOpposite();
            state = state.with(FACING, facing);
//        } else if (state.isOf(ModBlocks.POTTED_PUMPKIN) && item instanceof ShearsItem) {
//            Direction facing = player.getHorizontalFacing().getOpposite();
//            instance.setBlockState(pos, (BlockState)ModBlocks.POTTED_CARVED_PUMPKIN.getDefaultState().with(CarvedPumpkinBlock.FACING, facing), 11);
        }
//        if (this.content == Blocks.BAMBOO) {
//            instance.setBlockState(pos, ModBlocks.POTTED_BAMBOO.getDefaultState(), 2);
//        }
//        if (state.getBlock() instanceof LeafFlowerPotBlock leafFlowerPotBlock) {
//                state = state.with(LeafFlowerPotBlock.LEAF, true);
//        }
        //System.out.println(item);
        return original.call(instance, pos, state, flags);
    }

    @Inject(at = @At(value = "HEAD"), method = "onUse", cancellable = true)
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (item == Items.CHORUS_FRUIT) {
//            System.out.println(item);
            world.setBlockState(pos, ModBlocks.POTTED_CHORUS_FRUIT.getDefaultState(), 3);
            player.incrementStat(Stats.POT_FLOWER);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Items.SWEET_BERRIES) {
            world.setBlockState(pos, ModBlocks.POTTED_SWEET_BERRY_BUSH.getDefaultState(), 3);
            player.incrementStat(Stats.POT_FLOWER);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Items.APPLE) {
            world.setBlockState(pos, ModBlocks.POTTED_APPLE.getDefaultState(), 3);
            player.incrementStat(Stats.POT_FLOWER);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Items.GOLDEN_APPLE) {
            world.setBlockState(pos, ModBlocks.POTTED_GOLDEN_APPLE.getDefaultState(), 3);
            player.incrementStat(Stats.POT_FLOWER);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Items.ENCHANTED_GOLDEN_APPLE || item == ModItems.ENCHANTED_GOLDEN_APPLE_ENTITY) {
            world.setBlockState(pos, ModBlocks.POTTED_ENCHANTED_GOLDEN_APPLE.getDefaultState(), 3);
            player.incrementStat(Stats.POT_FLOWER);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));

            // if egg, don't increment plants potted stat

        } else if (item == Items.EGG) {
            world.setBlockState(pos, ModBlocks.POTTED_EGG.getDefaultState(), 3);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Blocks.SPONGE.asItem()) {
            world.setBlockState(pos, ModBlocks.POTTED_SPONGE.getDefaultState(), 3);
            player.incrementStat(Stats.POT_FLOWER);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Blocks.TURTLE_EGG.asItem()) {
            world.setBlockState(pos, ModBlocks.POTTED_TURTLE_EGG.getDefaultState(), 3);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Blocks.DRAGON_EGG.asItem()) {
            world.setBlockState(pos, ModBlocks.POTTED_DRAGON_EGG.getDefaultState(), 3);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Blocks.SNIFFER_EGG.asItem()) {
            world.setBlockState(pos, ModBlocks.POTTED_SNIFFER_EGG.getDefaultState(), 3);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Blocks.FROGSPAWN.asItem()) {
            world.setBlockState(pos, ModBlocks.POTTED_FROGSPAWN.getDefaultState(), 3);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        } else if (item == Items.SEAGRASS.asItem()) {
            world.setBlockState(pos, ModBlocks.POTTED_SEAGRASS.getDefaultState(), 3);
            player.incrementStat(Stats.POT_FLOWER);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
        }
//        } else if (item == Items.GLOW_BERRIES) {
//            world.setBlockState(pos, ModBlocks.POTTED_CAVE_VINE.getDefaultState(), 3);
//            player.incrementStat(Stats.POT_FLOWER);
//            if (!player.getAbilities().creativeMode) {
//                itemStack.decrement(1);
//            }
//            cir.setReturnValue(ActionResult.success(world.isClient));
    }

    private static final VoxelShape CACTUS_SHAPE;
    private static final VoxelShape BAMBOO_SHAPE;
    private static final VoxelShape BOTTLE_SHAPE;
//  private static final VoxelShape DRAGON_EGG_SHAPE;
//  private static final VoxelShape SNIFFER_EGG_SHAPE;
    private static final VoxelShape LILY_PAD_SHAPE;

    static {
        COLLISION_SHAPE = SHAPE;
        CACTUS_SHAPE = VoxelShapes.union(SHAPE, Block.createCuboidShape((double) 6.0F, (double) 0.0F, (double) 6.0F, (double) 10.0F, (double) 16.0F, (double) 10.0F));
        BAMBOO_SHAPE = VoxelShapes.union(SHAPE, Block.createCuboidShape((double)7.0F, (double)0.0F, (double)7.0F, (double)9.0F, (double)16.0F, (double)9.0F));
        // BOTTLE_SHAPE could've been just a Block.createCuboidShape, but I wanted
        // to make it so that you don't fall into them if you stand on a pot when
        // potting a water plant
        // future comment: this didn't work you can't have intersecting cuboids
        BOTTLE_SHAPE = Block.createCuboidShape((double) 3.0F, (double) 0.0F, (double) 3.0F, (double) 13.0F, (double) 10.0F, (double) 13.0F);
//      DRAGON_EGG_SHAPE = VoxelShapes.union(Block.createCuboidShape((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)6.0F, (double)14.0F), Block.createCuboidShape((double) 3.0F, (double) 0.0F, (double) 3.0F, (double) 13.0F, (double) 16.0F, (double) 13.0F));
//      SNIFFER_EGG_SHAPE = VoxelShapes.union(Block.createCuboidShape((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)6.0F, (double)14.0F), Block.createCuboidShape((double) 3.0F, (double) 0.0F, (double) 4.0F, (double) 13.0F, (double) 16.0F, (double) 12.0F));
        LILY_PAD_SHAPE = Block.createCuboidShape((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)6.0F, (double)14.0F);
    }

    // private static final Set<Block> CORALS = Set.of(Blocks.BRAIN_CORAL, Blocks.BUBBLE_CORAL, Blocks.FIRE_CORAL, Blocks.HORN_CORAL, Blocks.TUBE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BRAIN_CORAL_FAN, Blocks.FIRE_CORAL_FAN, Blocks.HORN_CORAL_FAN, Blocks.TUBE_CORAL_FAN);
    //DONE: make turtle, dragon, and sniffer eggs not FlowerPotBlock sothat they dont increment the plants potted stat
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (this.content == Blocks.CACTUS || this.content == Blocks.SUGAR_CANE) {
            return CACTUS_SHAPE;
        } else if (this.content == Blocks.BAMBOO) {
            return BAMBOO_SHAPE;
        } else if (this.content == Blocks.KELP || this.content == Blocks.SEAGRASS || this.content == Blocks.SEA_PICKLE
        || this.content == Blocks.BRAIN_CORAL || this.content == Blocks.BUBBLE_CORAL || this.content == Blocks.FIRE_CORAL
        || this.content == Blocks.HORN_CORAL || this.content == Blocks.TUBE_CORAL
        || this.content == Blocks.BRAIN_CORAL_FAN || this.content == Blocks.BUBBLE_CORAL_FAN || this.content == Blocks.FIRE_CORAL_FAN
        || this.content == Blocks.HORN_CORAL_FAN || this.content == Blocks.TUBE_CORAL_FAN
        || this.content == Blocks.BRAIN_CORAL_BLOCK || this.content == Blocks.BUBBLE_CORAL_BLOCK || this.content == Blocks.FIRE_CORAL_BLOCK
        || this.content == Blocks.HORN_CORAL_BLOCK || this.content == Blocks.TUBE_CORAL_BLOCK
        || this.content == Blocks.DEAD_BRAIN_CORAL || this.content == Blocks.DEAD_BUBBLE_CORAL || this.content == Blocks.DEAD_FIRE_CORAL
        || this.content == Blocks.DEAD_HORN_CORAL || this.content == Blocks.DEAD_TUBE_CORAL
        || this.content == Blocks.DEAD_BRAIN_CORAL_FAN || this.content == Blocks.DEAD_BUBBLE_CORAL_FAN || this.content == Blocks.DEAD_FIRE_CORAL_FAN
        || this.content == Blocks.DEAD_HORN_CORAL_FAN || this.content == Blocks.DEAD_TUBE_CORAL_FAN
        || this.content == Blocks.DEAD_BRAIN_CORAL_BLOCK || this.content == Blocks.DEAD_BUBBLE_CORAL_BLOCK || this.content == Blocks.DEAD_FIRE_CORAL_BLOCK
        || this.content == Blocks.DEAD_HORN_CORAL_BLOCK || this.content == Blocks.DEAD_TUBE_CORAL_BLOCK
        || this.content == Blocks.WET_SPONGE
        ) {
            return BOTTLE_SHAPE;
        }   else if (this.content == Blocks.LILY_PAD) {
            return LILY_PAD_SHAPE;
        }
        //   return   (this.content == Blocks.CACTUS) ? CACTUS_SHAPE : SHAPE;
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (this.content == Blocks.LILY_PAD) {
        return LILY_PAD_SHAPE;
        } else return
                     !(this.content == Blocks.KELP || this.content == Blocks.SEAGRASS || this.content == Blocks.SEA_PICKLE
                    || this.content == Blocks.BRAIN_CORAL || this.content == Blocks.BUBBLE_CORAL || this.content == Blocks.FIRE_CORAL
                    || this.content == Blocks.HORN_CORAL || this.content == Blocks.TUBE_CORAL
                    || this.content == Blocks.BRAIN_CORAL_FAN || this.content == Blocks.BUBBLE_CORAL_FAN || this.content == Blocks.FIRE_CORAL_FAN
                    || this.content == Blocks.HORN_CORAL_FAN || this.content == Blocks.TUBE_CORAL_FAN
                    || this.content == Blocks.BRAIN_CORAL_BLOCK || this.content == Blocks.BUBBLE_CORAL_BLOCK || this.content == Blocks.FIRE_CORAL_BLOCK
                    || this.content == Blocks.HORN_CORAL_BLOCK || this.content == Blocks.TUBE_CORAL_BLOCK
                    || this.content == Blocks.DEAD_BRAIN_CORAL || this.content == Blocks.DEAD_BUBBLE_CORAL || this.content == Blocks.DEAD_FIRE_CORAL
                    || this.content == Blocks.DEAD_HORN_CORAL || this.content == Blocks.DEAD_TUBE_CORAL
                    || this.content == Blocks.DEAD_BRAIN_CORAL_FAN || this.content == Blocks.DEAD_BUBBLE_CORAL_FAN || this.content == Blocks.DEAD_FIRE_CORAL_FAN
                    || this.content == Blocks.DEAD_HORN_CORAL_FAN || this.content == Blocks.DEAD_TUBE_CORAL_FAN
                    || this.content == Blocks.DEAD_BRAIN_CORAL_BLOCK || this.content == Blocks.DEAD_BUBBLE_CORAL_BLOCK || this.content == Blocks.DEAD_FIRE_CORAL_BLOCK
                    || this.content == Blocks.DEAD_HORN_CORAL_BLOCK || this.content == Blocks.DEAD_TUBE_CORAL_BLOCK
                    || this.content == Blocks.WET_SPONGE)
                    ? COLLISION_SHAPE : BOTTLE_SHAPE;
    }

    // temp hack
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && neighborState.isOf(ModBlocks.STACKED_BAMBOO)) {
            world.setBlockState(pos, ModBlocks.LEAFLESS_POTTED_BAMBOO.getDefaultState(), 2);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

}
//
//        if (item.isOf()) {
//            BlockPos blockPos = pos.down();
//            BlockState blockState2 = instance.getBlockState(blockPos);
//            if  (blockState2.isOf(Blocks.POTTED_CACTUS)) {
//                instance.setBlockState(pos.up(), ModBlocks.STACKED_CACTUS.getDefaultState());
//            }
//        }
//        return original.call(instance, pos, state, flags);
//    }


//onuse cactus state is of potted cactus


//world setblockstate