package com.owenxm.morepottedplants.mixin;

import com.owenxm.morepottedplants.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin extends Block {
    public SugarCaneBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At(value = "TAIL"), method = "canPlaceAt", cancellable = true)
    public void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(pos.down());
        if (blockState.isOf(ModBlocks.POTTED_SUGAR_CANE)  || blockState.isOf(ModBlocks.STACKED_SUGAR_CANE)) {
            cir.setReturnValue(true);
        }
        cir.setReturnValue(false);
    }

    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos().down();
        BlockState blockState2 = ctx.getWorld().getBlockState(blockPos);
        if  (blockState2.isOf(ModBlocks.POTTED_SUGAR_CANE) || blockState2.isOf(ModBlocks.STACKED_SUGAR_CANE)) {
//            System.out.println("eugh");
            return ModBlocks.STACKED_SUGAR_CANE.getDefaultState();
        }
        return super.getPlacementState(ctx);
    }
}
