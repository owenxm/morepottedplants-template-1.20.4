package com.owenxm.morepottedplants;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class EnchantedGoldenAppleFlowerPotBlockEntityRenderer implements BlockEntityRenderer<EnchantedGoldenAppleFlowerPotBlockEntity > {
    // A jukebox itemstack
    private static final ItemStack stack = new ItemStack(ModItems.ENCHANTED_GOLDEN_APPLE_ENTITY);

    public EnchantedGoldenAppleFlowerPotBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(EnchantedGoldenAppleFlowerPotBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        // Calculate the current offset in the y value
//        double offset = Math.sin((blockEntity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
        // Move the item
        matrices.translate(0.5, 1, 0.5);

        // Rotate the item
//        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((blockEntity.getWorld().getTime() + tickDelta) * 4));

        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.FIXED, light, overlay, matrices, vertexConsumers, blockEntity.getWorld(), 0);

        // Mandatory call after GL calls
        matrices.pop();
    }
}
