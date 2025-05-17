package com.owenxm.morepottedplants.mixin;

import com.owenxm.morepottedplants.FastFlowerPotBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(RenderLayers.class)
public class RenderLayersMixin2 {
    @Shadow private static boolean fancyGraphicsOrBetter;

    //    @Redirect(method = "getBlockLayer", at = @At(value = "CONSTANT", args = "classValue=net/minecraft/block/LeavesBlock", shift = At.Shift.AFTER, ordinal = 0))
//    private static boolean mixin(Block targetObj, Class<?> classValue, BlockState block) {
//        return block.getBlock() instanceof LeavesBlock || block.getBlock() instanceof FastFlowerPotBlock;
//    }
@Inject(method = "getBlockLayer", at = @At(value = "HEAD"), cancellable = true)
private static void getBlockLayer(BlockState state, CallbackInfoReturnable<RenderLayer> cir) {
    Block block = state.getBlock();
    if (block instanceof FastFlowerPotBlock) {
        cir.setReturnValue(fancyGraphicsOrBetter ? RenderLayer.getCutoutMipped() : RenderLayer.getSolid());
    }
}
//    private static boolean getLeafBlock(BlockState state) {
//        if (state.getBlock() instanceof LeavesBlock || state.getBlock() instanceof FastFlowerPotBlock)
//            return true;
//        else
//        return false;
//    }
}


