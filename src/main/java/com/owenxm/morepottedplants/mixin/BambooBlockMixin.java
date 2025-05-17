package com.owenxm.morepottedplants.mixin;

import com.owenxm.morepottedplants.ModBlocks;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.owenxm.morepottedplants.StackedBambooBlock.LEAF;

@Mixin(BambooBlock.class)
public abstract class BambooBlockMixin extends Block {
    @Shadow public abstract BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos);

    private static final IntProperty AGE;
    public BambooBlockMixin(Settings settings) {
        super(settings);
    }
@Inject(at = @At(value = "TAIL"), method = "getStateForNeighborUpdate")
    public void getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
            if (direction == Direction.UP && neighborState.isOf(ModBlocks.STACKED_BAMBOO)) {
                world.setBlockState(pos, ModBlocks.LEAFLESS_POTTED_BAMBOO.getDefaultState(), 2);
            }
        }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        if (!fluidState.isEmpty()) {
            return null;
        } else {
            BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().down());
            if (blockState.isIn(BlockTags.BAMBOO_PLANTABLE_ON)) {
                if (blockState.isOf(Blocks.BAMBOO_SAPLING)) {
                    return (BlockState)this.getDefaultState().with(AGE, 0);
                } else if (blockState.isOf(Blocks.BAMBOO)) {
                    int i = (Integer)blockState.get(AGE) > 0 ? 1 : 0;
                    return (BlockState)this.getDefaultState().with(AGE, i);
                } else {
                    BlockState blockState2 = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
                    return blockState2.isOf(Blocks.BAMBOO) ? (BlockState)this.getDefaultState().with(AGE, (Integer)blockState2.get(AGE)) : Blocks.BAMBOO_SAPLING.getDefaultState();
                }
            } else if  (blockState.isOf(Blocks.POTTED_BAMBOO) || blockState.isOf(ModBlocks.LEAFLESS_POTTED_BAMBOO) || blockState.isOf(ModBlocks.STACKED_BAMBOO)) {
//            System.out.println("eugh");
                    return (BlockState)ModBlocks.STACKED_BAMBOO.getDefaultState().with(LEAF, true);
            }  else {
                return null;
            }

        }
    }
    static {
        AGE = Properties.AGE_1;
    }

}
