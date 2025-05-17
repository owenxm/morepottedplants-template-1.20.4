package com.owenxm.morepottedplants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.StemBlock;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import static net.minecraft.client.MinecraftClient.isFancyGraphicsOrBetter;


@Environment(EnvType.CLIENT)
public class MorePottedPlantsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
//        MinecraftClient instance = getInstance();
//        GameOptions options = getInstance().options;
//        public static boolean isFancyGraphicsOrBetter() {
//            return instance.options.getGraphicsMode().getValue().getId() >= GraphicsMode.FANCY.getId();
//
//        }


        // To make some parts of the block transparent (like glass, saplings and doors):
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SHORT_GRASS, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), ModBlocks.POTTED_SHORT_GRASS);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_TALL_GRASS, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), ModBlocks.POTTED_TALL_GRASS);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LARGE_FERN, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), ModBlocks.POTTED_LARGE_FERN);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PUMPKIN_CROP, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), ModBlocks.POTTED_PUMPKIN_CROP);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_MELON_CROP, RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            int i = (Integer) state.get(StemBlock.AGE);
            int j = i * 32;
            int k = 255 - i * 8;
            int l = i * 4;
            return j << 16 | k << 8 | l;
        }, ModBlocks.POTTED_MELON_CROP, ModBlocks.POTTED_PUMPKIN_CROP);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PEONY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LILAC, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SUNFLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PITCHER_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SMALL_DRIPLEAF, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_WHEAT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_POTATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_CARROT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BEETROOT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PUMPKIN_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_MELON_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_NETHER_WART, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_COCOA_BEAN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SUGAR_CANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_TWISTING_VINE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_WEEPING_VINE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEAFLESS_POTTED_BAMBOO, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STACKED_BAMBOO, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STACKED_SUGAR_CANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_NETHER_SPROUTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SCULK_VEIN, RenderLayer.getCutout());
//        if (isFancyGraphicsOrBetter()) {
//            BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_GLOW_LICHEN, RenderLayer.getCutout());
//        }
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_GLOW_LICHEN, RenderLayer.getCutout());

//        public static int getSpruceColor() {
//            return 6396257;
//        }
//
//        public static int getBirchColor() {
//            return 8431445;
//        }
//
//        public static int getMangroveColor() {
//            return 9619016;
//        }
//    }

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_OAK_LEAVES, RenderLayer.getCutout());                 // 👍👇
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getFoliageColor(view, pos), ModBlocks.POTTED_OAK_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BIRCH_LEAVES, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {return 8431445;}, ModBlocks.POTTED_BIRCH_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SPRUCE_LEAVES, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {return 6396257;}, ModBlocks.POTTED_SPRUCE_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_JUNGLE_LEAVES, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getFoliageColor(view, pos), ModBlocks.POTTED_JUNGLE_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ACACIA_LEAVES, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getFoliageColor(view, pos), ModBlocks.POTTED_ACACIA_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DARK_OAK_LEAVES, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getFoliageColor(view, pos), ModBlocks.POTTED_DARK_OAK_LEAVES);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_MANGROVE_LEAVES, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {return 9619016;}, ModBlocks.POTTED_MANGROVE_LEAVES);
        // special sallies
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_CHERRY_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_AZALEA_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FLOWERING_AZALEA_LEAVES, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SEAGRASS, RenderLayer.getCutout());
//        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getWaterColor(view, pos), ModBlocks.POTTED_SEAGRASS);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_KELP, RenderLayer.getCutout());
//        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getWaterColor(view, pos), ModBlocks.POTTED_KELP);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SEA_PICKLE, RenderLayer.getCutout());
//        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getWaterColor(view, pos), ModBlocks.POTTED_SEA_PICKLE);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BRAIN_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BUBBLE_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FIRE_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_HORN_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_TUBE_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BRAIN_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BUBBLE_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FIRE_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_HORN_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_TUBE_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BRAIN_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BUBBLE_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FIRE_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_HORN_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_TUBE_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_BRAIN_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_BUBBLE_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_FIRE_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_HORN_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_TUBE_CORAL_FAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_BRAIN_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_BUBBLE_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_FIRE_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_HORN_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_TUBE_CORAL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_BRAIN_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_BUBBLE_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_FIRE_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_HORN_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DEAD_TUBE_CORAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SPONGE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_WET_SPONGE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SWEET_BERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_CAVE_VINE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SPORE_BLOSSOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BIG_DRIPLEAF, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PINK_PETALS, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            if (tintIndex != 0) {
                return view != null && pos != null ? BiomeColors.getGrassColor(view, pos) : GrassColors.getDefaultColor();
            } else {
                return -1;
            }
        }, ModBlocks.POTTED_PINK_PETALS);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_TORCHFLOWER_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PITCHER_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_HANGING_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_VINE, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getFoliageColor(view, pos) : FoliageColors.getDefaultColor(), ModBlocks.POTTED_VINE);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SCULK_SENSOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FROGSPAWN, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getWaterColor(view, pos), ModBlocks.POTTED_FROGSPAWN);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LILY_PAD, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
                return tintIndex == 0 ? 2129968 : BiomeColors.getWaterColor(view, pos);
        }, ModBlocks.POTTED_LILY_PAD);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SCULK_SHRIEKER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_CALIBRATED_SCULK_SENSOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_TALL_SEAGRASS, RenderLayer.getCutout());

        // it's awesome, right?
        BlockEntityRendererFactories.register(ModBlockEntityTypes.POTTED_ENCHANTED_GOLDEN_APPLE_ENTITY, EnchantedGoldenAppleFlowerPotBlockEntityRenderer::new);
    }
}
