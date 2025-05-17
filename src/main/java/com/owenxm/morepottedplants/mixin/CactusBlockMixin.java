package com.owenxm.morepottedplants.mixin;

import com.owenxm.morepottedplants.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CactusBlock.class)
    public class CactusBlockMixin extends Block{
    public CactusBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At(value = "HEAD"), method = "canPlaceAt", cancellable = true)
    public void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        for(Direction direction : Direction.Type.HORIZONTAL) {
            BlockState blockState = world.getBlockState(pos.offset(direction));
            if (blockState.isSolid() || world.getFluidState(pos.offset(direction)).isIn(FluidTags.LAVA)) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }

        BlockState blockState2 = world.getBlockState(pos.down());

//            if (blockState2.isOf(Blocks.POTTED_CACTUS)) {
//
//            }
        cir.setReturnValue(blockState2.isOf(Blocks.CACTUS) || (blockState2.isOf(Blocks.POTTED_CACTUS) || blockState2.isIn(BlockTags.SAND)) && !world.getBlockState(pos.up()).isLiquid());
        cir.cancel();
    };

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos().down();
        BlockState blockState2 = ctx.getWorld().getBlockState(blockPos);
        if  (blockState2.isOf(Blocks.POTTED_CACTUS) || blockState2.isOf(ModBlocks.STACKED_CACTUS)) {
//            System.out.println("eugh");
        return ModBlocks.STACKED_CACTUS.getDefaultState();
        }
        return super.getPlacementState(ctx);
    }

//    @Inject(at = @At(value = "RETURN"), method = "scheduledTick")
//    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
//        BlockPos blockPos = pos.down();
//        BlockState blockState2 = world.getBlockState(blockPos);
//        if  (blockState2.isOf(Blocks.POTTED_CACTUS)) {
//            System.out.println("eugh");
//            world.setBlockState(pos.up(), ModBlocks.STACKED_CACTUS.getDefaultState());
//        }
//    }
 }
