package com.owenxm.morepottedplants;


import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static net.minecraft.block.Blocks.*;

public class ModBlocks {
    public static void initialize() {}
    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        // Register the block and its item.
        Identifier id = new Identifier(MorePottedPlants.MOD_ID, name);

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }
//    public static Block registerItemPot(Item item, Block block, String name, boolean shouldRegisterItem) {
//        // Register the block and its item.
//        Identifier id = new Identifier(MorePottedPlants.MOD_ID, name);
//
//        // Sometimes, you may not want to register an item for the block.
//        // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
//        if (shouldRegisterItem) {
//            BlockItem blockItem = new BlockItem(block, new Item.Settings());
//            Registry.register(Registries.ITEM, id, blockItem);
//        }
//
//        return Registry.register(Registries.BLOCK, id, block);
//    }

    //
    // single tall
    //

    public static final Block POTTED_SUGAR_CANE = register(
            new FlowerPotBlock(Blocks.SUGAR_CANE, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_sugar_cane",
            false
    );

    public static final Block POTTED_TWISTING_VINE = register(
            new FlowerPotBlock(Blocks.TWISTING_VINES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_twisting_vine",
            false
    );

    public static final Block POTTED_WEEPING_VINE = register(
            new HangingFlowerPotBlock(Blocks.WEEPING_VINES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_weeping_vine",
            false
    );
    public static final Block POTTED_SCULK_VEIN = register(
            new HangingFlowerPotBlock(SCULK_VEIN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_sculk_vein",
            false
    );

    public static final Block POTTED_GLOW_LICHEN = register(
            new HangingFlowerPotBlock(GLOW_LICHEN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).luminance((state) -> 7)),
            "potted_glow_lichen",
            false
    );

    public static final Block POTTED_PUMPKIN = register(
            new FlowerPotBlock(Blocks.PUMPKIN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_pumpkin",
            false
    );

    public static final Block POTTED_CARVED_PUMPKIN = register(
            new FacingFlowerPotBlock(Blocks.CARVED_PUMPKIN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_carved_pumpkin",
            false
    );

    public static final Block POTTED_JACK_O_LANTERN = register(
            new FacingFlowerPotBlock(Blocks.JACK_O_LANTERN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).luminance((state) -> 15)),
            "potted_jack_o_lantern",
            false
    );

    public static final Block POTTED_MELON = register(
            new FlowerPotBlock(Blocks.MELON, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_melon",
            false
    );

        public static final Block LEAFLESS_POTTED_BAMBOO = register(
            new AlternativeFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "leafless_potted_bamboo",
            false
    );

    public static final Block POTTED_NETHER_SPROUTS = register(
            new FlowerPotBlock(NETHER_SPROUTS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_nether_sprouts",
            false
    );

    public static final Block POTTED_OAK_LEAVES = register(
            new FastFlowerPotBlock(OAK_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_oak_leaves",
            false
    );

    public static final Block POTTED_BIRCH_LEAVES = register(
            new FastFlowerPotBlock(BIRCH_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_birch_leaves",
            false
    );

    public static final Block POTTED_SPRUCE_LEAVES = register(
            new FastFlowerPotBlock(SPRUCE_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_spruce_leaves",
            false
    );

    public static final Block POTTED_JUNGLE_LEAVES = register(
            new FastFlowerPotBlock(JUNGLE_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_jungle_leaves",
            false
    );

    public static final Block POTTED_ACACIA_LEAVES = register(
            new FastFlowerPotBlock(ACACIA_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_acacia_leaves",
            false
    );

    public static final Block POTTED_DARK_OAK_LEAVES = register(
            new FastFlowerPotBlock(DARK_OAK_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_dark_oak_leaves",
            false
    );

    public static final Block POTTED_MANGROVE_LEAVES = register(
            new FastFlowerPotBlock(MANGROVE_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_mangrove_leaves",
            false
    );

    public static final Block POTTED_CHERRY_LEAVES = register(
            new FastFlowerPotBlock(CHERRY_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_cherry_leaves",
            false
    );

    public static final Block POTTED_AZALEA_LEAVES = register(
            new FastFlowerPotBlock(AZALEA_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_azalea_leaves",
            false
    );

    public static final Block POTTED_FLOWERING_AZALEA_LEAVES = register(
            new FastFlowerPotBlock(FLOWERING_AZALEA_LEAVES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_flowering_azalea_leaves",
            false
    );

    public static final Block POTTED_CHORUS_PLANT = register(
            new FlowerPotBlock(CHORUS_PLANT, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_chorus_plant",
            false
    );

    public static final Block POTTED_CHORUS_FLOWER = register(
            new FlowerPotBlock(CHORUS_FLOWER, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_chorus_flower",
            false
    );

    public static final Block POTTED_CHORUS_FRUIT = register(
            new ChorusFruitFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_chorus_fruit",
            false
    );

    public static final Block POTTED_SEAGRASS = register(
            new SeagrassFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_seagrass",
            false
    );

    public static final Block POTTED_KELP = register(
            new FlowerPotBlock(KELP, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_kelp",
            false
    );

    public static final Block POTTED_SEA_PICKLE = register(
            new FlowerPotBlock(SEA_PICKLE, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS).luminance((state) -> 8)),
            "potted_sea_pickle",
            false
    );

    public static final Block POTTED_BRAIN_CORAL = register(
            new FlowerPotBlock(BRAIN_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_brain_coral",
            false
    );

    public static final Block POTTED_BUBBLE_CORAL = register(
            new FlowerPotBlock(BUBBLE_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_bubble_coral",
            false
    );

    public static final Block POTTED_FIRE_CORAL = register(
            new FlowerPotBlock(FIRE_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_fire_coral",
            false
    );

    public static final Block POTTED_HORN_CORAL = register(
            new FlowerPotBlock(HORN_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_horn_coral",
            false
    );

    public static final Block POTTED_TUBE_CORAL = register(
            new FlowerPotBlock(TUBE_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_tube_coral",
            false
    );

    public static final Block POTTED_BRAIN_CORAL_FAN = register(
            new FlowerPotBlock(BRAIN_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_brain_coral_fan",
            false
    );

    public static final Block POTTED_BUBBLE_CORAL_FAN = register(
            new FlowerPotBlock(BUBBLE_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_bubble_coral_fan",
            false
    );

    public static final Block POTTED_FIRE_CORAL_FAN = register(
            new FlowerPotBlock(FIRE_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_fire_coral_fan",
            false
    );

    public static final Block POTTED_HORN_CORAL_FAN = register(
            new FlowerPotBlock(HORN_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_horn_coral_fan",
            false
    );

    public static final Block POTTED_TUBE_CORAL_FAN = register(
            new FlowerPotBlock(TUBE_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_tube_coral_fan",
            false
    );

    public static final Block POTTED_BRAIN_CORAL_BLOCK = register(
            new FlowerPotBlock(BRAIN_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_brain_coral_block",
            false
    );

    public static final Block POTTED_BUBBLE_CORAL_BLOCK = register(
            new FlowerPotBlock(BUBBLE_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_bubble_coral_block",
            false
    );

    public static final Block POTTED_FIRE_CORAL_BLOCK = register(
            new FlowerPotBlock(FIRE_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_fire_coral_block",
            false
    );

    public static final Block POTTED_HORN_CORAL_BLOCK = register(
            new FlowerPotBlock(HORN_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_horn_coral_block",
            false
    );

    public static final Block POTTED_TUBE_CORAL_BLOCK = register(
            new FlowerPotBlock(TUBE_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_tube_coral_block",
            false
    );

    public static final Block POTTED_DEAD_BRAIN_CORAL = register(
            new FlowerPotBlock(DEAD_BRAIN_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_brain_coral",
            false
    );

    public static final Block POTTED_DEAD_BUBBLE_CORAL = register(
            new FlowerPotBlock(DEAD_BUBBLE_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_bubble_coral",
            false
    );

    public static final Block POTTED_DEAD_FIRE_CORAL = register(
            new FlowerPotBlock(DEAD_FIRE_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_fire_coral",
            false
    );

    public static final Block POTTED_DEAD_HORN_CORAL = register(
            new FlowerPotBlock(DEAD_HORN_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_horn_coral",
            false
    );

    public static final Block POTTED_DEAD_TUBE_CORAL = register(
            new FlowerPotBlock(DEAD_TUBE_CORAL, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_tube_coral",
            false
    );

    public static final Block POTTED_DEAD_BRAIN_CORAL_FAN = register(
            new FlowerPotBlock(DEAD_BRAIN_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_brain_coral_fan",
            false
    );

    public static final Block POTTED_DEAD_BUBBLE_CORAL_FAN = register(
            new FlowerPotBlock(DEAD_BUBBLE_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_bubble_coral_fan",
            false
    );

    public static final Block POTTED_DEAD_FIRE_CORAL_FAN = register(
            new FlowerPotBlock(DEAD_FIRE_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_fire_coral_fan",
            false
    );

    public static final Block POTTED_DEAD_HORN_CORAL_FAN = register(
            new FlowerPotBlock(DEAD_HORN_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_horn_coral_fan",
            false
    );

    public static final Block POTTED_DEAD_TUBE_CORAL_FAN = register(
            new FlowerPotBlock(DEAD_TUBE_CORAL_FAN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_tube_coral_fan",
            false
    );

    public static final Block POTTED_DEAD_BRAIN_CORAL_BLOCK = register(
            new FlowerPotBlock(DEAD_BRAIN_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_brain_coral_block",
            false
    );

    public static final Block POTTED_DEAD_BUBBLE_CORAL_BLOCK = register(
            new FlowerPotBlock(DEAD_BUBBLE_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_bubble_coral_block",
            false
    );

    public static final Block POTTED_DEAD_FIRE_CORAL_BLOCK = register(
            new FlowerPotBlock(DEAD_FIRE_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_fire_coral_block",
            false
    );

    public static final Block POTTED_DEAD_HORN_CORAL_BLOCK = register(
            new FlowerPotBlock(DEAD_HORN_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_horn_coral_block",
            false
    );

    public static final Block POTTED_DEAD_TUBE_CORAL_BLOCK = register(
            new FlowerPotBlock(DEAD_TUBE_CORAL_BLOCK, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_dead_tube_coral_block",
            false
    );

    public static final Block POTTED_SPONGE = register(
            new SpongeFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_sponge",
            false
    );

    public static final Block POTTED_WET_SPONGE = register(
            new FlowerPotBlock(WET_SPONGE, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS)),
            "potted_wet_sponge",
            false
    );

    public static final Block POTTED_SPORE_BLOSSOM = register(
            new HangingFlowerPotBlock(SPORE_BLOSSOM, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_spore_blossom",
            false
    );

    public static final Block POTTED_BIG_DRIPLEAF = register(
            new FacingFlowerPotBlock(BIG_DRIPLEAF, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_big_dripleaf",
            false
    );

    public static final Block POTTED_APPLE = register(
            new AppleFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_apple",
            false
    );

    public static final Block POTTED_GOLDEN_APPLE = register(
            new GoldenAppleFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_golden_apple",
            false
    );

    public static final Block POTTED_ENCHANTED_GOLDEN_APPLE = register(
            new EnchantedGoldenAppleFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_enchanted_golden_apple",
            false
    );

    public static final Block POTTED_EGG = register(
            new EggFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_egg",
            false
    );

    public static final Block POTTED_SNIFFER_EGG = register(
            new SnifferEggFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_sniffer_egg",
            false
    );

    public static final Block POTTED_DRAGON_EGG = register(
            new DragonEggFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_dragon_egg",
            false
    );

    public static final Block POTTED_TURTLE_EGG = register(
            new TurtleEggFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_turtle_egg",
            false
    );

    public static final Block POTTED_PINK_PETALS = register(
            new FlowerbedFlowerPotBlock(PINK_PETALS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_pink_petals",
            false
    );

    // I did these last ones in bulk cuz I wasn't 100% sure what they'd look like
    // so I just got them ready ahead of time to save time whilst thinking

    public static final Block POTTED_HANGING_ROOTS = register(
            new HangingFlowerPotBlock(HANGING_ROOTS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_hanging_roots",
            false
    );

    public static final Block POTTED_VINE = register(
            new HangingFlowerPotBlock(VINE, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_vine",
            false
    );

    public static final Block POTTED_SCULK_SENSOR = register(
            new FlowerPotBlock(SCULK_SENSOR, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_sculk_sensor",
            false
    );

    public static final Block POTTED_FROGSPAWN = register(
            new FrogspawnFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_frogspawn",
            false
    );

    public static final Block POTTED_LILY_PAD = register(
            new FlowerPotBlock(LILY_PAD, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_lily_pad",
            false
    );

    public static final Block POTTED_SCULK_SHRIEKER = register(
            new FlowerPotBlock(SCULK_SHRIEKER, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_sculk_shrieker",
            false
    );

    public static final Block POTTED_CALIBRATED_SCULK_SENSOR = register(
            new FlowerPotBlock(CALIBRATED_SCULK_SENSOR, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_calibrated_sculk_sensor",
            false
    );

    public static final Block POTTED_TALL_SEAGRASS = register(
            new TallFlowerPotBlock(SEAGRASS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.GLASS).nonOpaque()),
            "potted_tall_seagrass",
            false
    );

//    public static final Block POTTED_BAMBOO = register(
//            new LeafFlowerPotBlock(Blocks.BAMBOO, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
//            "potted_bamboo",
//            false
//    );


    //
    // tinted single tall
    //

    public static final Block POTTED_SHORT_GRASS = register(
            new FlowerPotBlock(Blocks.SHORT_GRASS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_short_grass",
            false
    );

    //
    // tinted double tall
    //

    public static final Block POTTED_TALL_GRASS = register(
            new FlowerPotBlock(Blocks.TALL_GRASS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_tall_grass",
            false
    );
    public static final Block POTTED_LARGE_FERN = register(
            new FlowerPotBlock(Blocks.LARGE_FERN, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_large_fern",
            false
    );

    //
    // double tall
    //

    public static final Block POTTED_ROSE_BUSH = register(
            new FlowerPotBlock(Blocks.ROSE_BUSH, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_rose_bush",
            false
    );
    public static final Block POTTED_PEONY = register(
            new FlowerPotBlock(Blocks.PEONY, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_peony",
            false
    );
    public static final Block POTTED_LILAC = register(
            new FlowerPotBlock(Blocks.LILAC, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_lilac",
            false
    );
    public static final Block POTTED_SUNFLOWER = register(
            new FlowerPotBlock(Blocks.SUNFLOWER, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_sunflower",
            false
    );
    public static final Block POTTED_PITCHER_PLANT  = register(
            new FlowerPotBlock(Blocks.PITCHER_PLANT, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_pitcher_plant",
            false
    );
    public static final Block POTTED_SMALL_DRIPLEAF  = register(
            new FlowerPotBlock(Blocks.SMALL_DRIPLEAF, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_small_dripleaf",
            false
    );

    //
    // crops
    //

    public static final Block POTTED_WHEAT_CROP = register(
            new PottedCropBlock(Blocks.WHEAT, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_wheat_crop",
            false
    );
//
    public static final Block POTTED_BEETROOT_CROP = register(
            new Age3PottedCropBlock(Blocks.BEETROOTS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_beetroot_crop",
            false
    );
//
    public static final Block POTTED_CARROT_CROP = register(
            new PottedCropBlock(Blocks.CARROTS, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_carrot_crop",
            false
    );
//
    public static final Block POTTED_POTATO_CROP = register(
            new PottedCropBlock(Blocks.POTATOES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_potato_crop",
            false
    );
//
    public static final Block POTTED_PUMPKIN_CROP = register(
            new PottedCropBlock(Blocks.PUMPKIN_STEM, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_pumpkin_crop",
            false
    );
//
    public static final Block POTTED_MELON_CROP = register(
            new PottedCropBlock(Blocks.MELON_STEM, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_melon_crop",
            false
    );

    public static final Block POTTED_NETHER_WART = register(
            new Age3PottedCropBlock(Blocks.NETHER_WART, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_nether_wart",
            false
    );

    public static final Block POTTED_COCOA_BEAN = register(
            new Age2PottedCropBlock(Blocks.COCOA, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_cocoa_bean",
            false
    );

    public static final Block POTTED_SWEET_BERRY_BUSH = register(
            new SweetBerryBushFlowerPotBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_sweet_berry_bush",
            false
    );

    public static final Block POTTED_CAVE_VINE = register(
            new CaveVineFlowerPotBlock(Blocks.CAVE_VINES, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE).luminance((state) -> CaveVineFlowerPotBlock.hasBerries(state) ? 14 : 0)),
            "potted_cave_vine",
            false
    );

    public static final Block POTTED_TORCHFLOWER_CROP = register(
            new Age2PottedCropBlock(TORCHFLOWER_CROP, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_torchflower_crop",
            false
    );

    public static final Block POTTED_PITCHER_CROP = register(
            new Age4PottedCropBlock(PITCHER_CROP, AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)),
            "potted_pitcher_crop",
            false
    );

    //
    // other
    //

    public static final Block STACKED_CACTUS = register(
            new StackedCactusBlock(AbstractBlock.Settings.create().noCollision().sounds(BlockSoundGroup.WOOL).breakInstantly().nonOpaque().dropsLike(CACTUS).pistonBehavior(PistonBehavior.DESTROY)),
            "stacked_cactus",
            false
    );

    public static final Block STACKED_BAMBOO = register(
            new StackedBambooBlock(AbstractBlock.Settings.create().noCollision().sounds(BlockSoundGroup.BAMBOO).breakInstantly().nonOpaque().dropsLike(BAMBOO).pistonBehavior(PistonBehavior.DESTROY)),
            "stacked_bamboo",
            false
    );

    public static final Block STACKED_SUGAR_CANE = register(
            new StackedSugarCaneBlock(AbstractBlock.Settings.create().noCollision().sounds(BlockSoundGroup.GRASS).breakInstantly().nonOpaque().dropsLike(SUGAR_CANE).pistonBehavior(PistonBehavior.DESTROY)),
            "stacked_sugar_cane",
            false
    );
};