package com.owenxm.morepottedplants;

// import com.google.common.cache.LocalCache;
import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.data.client.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.block;


public class GoopDataGenerator extends FabricModelProvider {
//    public static final Model TEMPLATE_POTTED_PLANT = block(new Identifier(Identifier.DEFAULT_NAMESPACE, "block/flower_pot_cross")); // does NOT need a key...

    public GoopDataGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerFlowerPotCross(blockStateModelGenerator, ModBlocks.POTTED_WHEAT_CROP, Properties.AGE_7, 0,1,2,3,4,5,6,7);
        registerFlowerPotCross(blockStateModelGenerator, ModBlocks.POTTED_CARROT_CROP, Properties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        registerFlowerPotCross(blockStateModelGenerator, ModBlocks.POTTED_POTATO_CROP, Properties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        registerFlowerPotCross(blockStateModelGenerator, ModBlocks.POTTED_BEETROOT_CROP, Properties.AGE_3, 0, 1, 2, 3);
        registerFlowerPotCross(blockStateModelGenerator, ModBlocks.POTTED_NETHER_WART, Properties.AGE_3, 0, 1, 1, 2);
//      registerFlowerPotCross(blockStateModelGenerator, ModBlocks.POTTED_PUMPKIN_CROP, Properties.AGE_7);
//      registerFlowerPotCross(blockStateModelGenerator, ModBlocks.POTTED_MELON_CROP, Properties.AGE_7);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    } // 👍

    public static final BiConsumer<Identifier, Supplier<JsonElement>> modelCollector = null;

    public final Identifier createSubModel(Block block, String suffix, Model model, Function<Identifier, TextureMap> texturesFactory) {
        return model.upload(block, suffix, (TextureMap)texturesFactory.apply(TextureMap.getSubId(block, suffix)), this.modelCollector);
    }

    public final void registerFlowerPotCross(BlockStateModelGenerator blockStateModelGenerator, Block crop, Property<Integer> ageProperty, int... ageTextureIndices) {
        if (ageProperty.getValues().size() != ageTextureIndices.length) {
            throw new IllegalArgumentException();
        } else {
            Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap<>();
            BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(ageProperty).register((integer) -> {
                int i = ageTextureIndices[integer];
                Identifier identifier = int2ObjectMap.computeIfAbsent(i, (j) -> blockStateModelGenerator.createSubModel(crop, "_stage" + i, Models.FLOWER_POT_CROSS, TextureMap::plant));
                return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
            });
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop).coordinate(blockStateVariantMap));
        }
    }

    //public void registerPottedPlant(BlockStateModelGenerator blockStateModelGenerator, Block block, @Nullable Block textureSource) {
    //    Block blockTexture = textureSource == null ? block : textureSource;
    //    Identifier identifier = TEMPLATE_POTTED_PLANT.upload(block, TextureMap.plant(blockTexture), blockStateModelGenerator.modelCollector);
    //    Int2ObjectMap<Identifier> int2ObjectMap = new Int2ObjectOpenHashMap();
//
    //    // where layton stopped giving help
    //    BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(Properties.AGE_7).register((integer) -> {
//
    //        int i = ((PottedCropBlock) block).getMaxAge();
    //        System.out.println(i);
    //        Identifier identifier2 = (Identifier)int2ObjectMap.computeIfAbsent(i, (j) -> this.createSubModel(block, "_stage" + i, Models.FLOWER_POT_CROSS, TextureMap::texture));
    //        return BlockStateVariant.create().put(VariantSettings.MODEL, identifier2);
    //    });
    //    blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
    //            .coordinate(BlockStateModelGenerator.createAxisRotatedVariantMap())
    //    );
    //    Models.GENERATED.upload(ModelIds.getItemModelId(block.asItem()), TextureMap.layer0(blockTexture.asItem()), blockStateModelGenerator.modelCollector);
    //}
//
    //private static Model block(Identifier parent, TextureKey... requiredTextureKeys) {
    //    return new Model(Optional.of(parent), Optional.empty(), requiredTextureKeys);
    //} // BLESS you x2
}

//memory issue................... there we go I am NOT I did get it to actually run though so that['s something
// mustnt see programming as a stopping errors thing though.