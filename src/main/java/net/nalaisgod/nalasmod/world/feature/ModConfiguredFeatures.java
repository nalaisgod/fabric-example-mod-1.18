package net.nalaisgod.nalasmod.world.feature;

import net.minecraft.block.*;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryEntry <ConfiguredFeature<TreeFeatureConfig, ?>> SOUL_BLOSSOM_TREE =
            ConfiguredFeatures.register("soul_blossom", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.SOUL_BLOSSOM_LOG),
                    new ForkingTrunkPlacer(7, 7, 7),
                    BlockStateProvider.of(ModBlocks.SOUL_BLOSSOM_LEAVES),
                    new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                    new TwoLayersFeatureSize(1, 0, 2)).build());


    public static final RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>> SOUL_BLOSSOM_SPAWN =
            ConfiguredFeatures.register("soul_blossom_spawn",
                    Feature.HUGE_FUNGUS, new HugeFungusFeatureConfig(Blocks.END_STONE.getDefaultState(),
                            ModBlocks.SOUL_BLOSSOM_LOG.getDefaultState(), ModBlocks.SOUL_BLOSSOM_LEAVES.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(),
                            false));
    public static final RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>> SOUL_BLOSSOM_SPAWN_PLANTED =
            ConfiguredFeatures.register("soul_blossom_spawn_planted",
                    Feature.HUGE_FUNGUS, new HugeFungusFeatureConfig(Blocks.END_STONE.getDefaultState(),
                            ModBlocks.SOUL_BLOSSOM_LOG.getDefaultState(), ModBlocks.SOUL_BLOSSOM_LEAVES.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(),
                            true));



    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FLOWER_FOR_ALL =
            ConfiguredFeatures.register("flower_flower_for_all", Feature.FLOWER,
                    new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.FLOWER_FOR_ALL)))));

    public static final List<OreFeatureConfig.Target> OVERWORLD_ORIGINITE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ORIGINITE_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORIGINITE_ORE.getDefaultState()));

    public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);



    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORIGINITE_ORE = ConfiguredFeatures.register("originite_ore",
            Feature.ORE, new OreFeatureConfig(OVERWORLD_ORIGINITE_ORES, 9));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> END_ORIGINITE_ORE = ConfiguredFeatures.register("end_originite_ore",
            Feature.SCATTERED_ORE, new OreFeatureConfig(END_STONE, ModBlocks.END_ORIGINITE_ORE.getDefaultState(), 20));





    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> DEATH_VINE_PATCH = ConfiguredFeatures.register("death_vine_patch",
            Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(64, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.DEATH_VINE)), BlockPredicate.bothOf(BlockPredicate.replaceable(),
                            BlockPredicate.matchingBlock(Blocks.HORN_CORAL_BLOCK, new BlockPos(0, -1, 0))))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MOSSLIGHT_PATCH = ConfiguredFeatures.register("mosslight_patch",
            Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(64, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.MOSSLIGHT)), BlockPredicate.bothOf(BlockPredicate.replaceable(),
                            BlockPredicate.matchingBlock(Blocks.HORN_CORAL_BLOCK, new BlockPos(0, -1, 0))))));


    public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> DEATH_VINE_PATCH_1 = ConfiguredFeatures.register("death_vine_patch_1",
            Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.DRAGON_IMMUNE, BlockStateProvider.of(ModBlocks.DEATH_VINE),
                    PlacedFeatures.createEntry(DEATH_VINE_PATCH), VerticalSurfaceType.CEILING,
                    UniformIntProvider.create(1, 2), 0.0f, 5, 0.08f, UniformIntProvider.create(4, 7),
                    0.3f));

    public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> DEATH_VINE_PATCH_2 = ConfiguredFeatures.register("death_vine_patch_2",
            Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.DRAGON_IMMUNE, BlockStateProvider.of(ModBlocks.DEATH_VINE),
                    PlacedFeatures.createEntry(DEATH_VINE_PATCH), VerticalSurfaceType.FLOOR,
                    UniformIntProvider.create(1, 2), 0.0f, 5, 0.08f, UniformIntProvider.create(4, 7),
                    0.3f));

    public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> MOSSLIGHT_PATCH_1 = ConfiguredFeatures.register("mosslight_patch_1",
            Feature.VEGETATION_PATCH, new VegetationPatchFeatureConfig(BlockTags.DRAGON_IMMUNE, BlockStateProvider.of(ModBlocks.MOSSLIGHT),
                    PlacedFeatures.createEntry(MOSSLIGHT_PATCH), VerticalSurfaceType.CEILING,
                    UniformIntProvider.create(1, 2), 0.0f, 2, 0.08f, UniformIntProvider.create(4, 7),
                    0.3f));

    public static final RegistryEntry<ConfiguredFeature<GlowLichenFeatureConfig, ?>>
            GLOW_LICHEN_END = ConfiguredFeatures.register("glow_lichen_end", Feature.GLOW_LICHEN,
            new GlowLichenFeatureConfig(20, false, true, true, 0.5f,
                    RegistryEntryList.of(Block::getRegistryEntry,
                            Blocks.END_STONE)));

    public static final RegistryEntry<ConfiguredFeature<RootSystemFeatureConfig, ?>> ROOTED_SOUL_TREE
            = ConfiguredFeatures.register("rooted_soul_tree", Feature.ROOT_SYSTEM,
            new RootSystemFeatureConfig(PlacedFeatures.createEntry(ModConfiguredFeatures.SOUL_BLOSSOM_TREE),
                    3, 3, BlockTags.DRAGON_IMMUNE,
                    BlockStateProvider.of(ModBlocks.DEATH_VINE), 20, 100,
                    3, 2, BlockStateProvider.of(ModBlocks.DEATH_VINE),
                    20, 2,
                    BlockPredicate.bothOf(BlockPredicate.anyOf(BlockPredicate.matchingBlocks(List.of(Blocks.AIR,
                            Blocks.CAVE_AIR, Blocks.VOID_AIR, Blocks.WATER)), BlockPredicate.matchingBlockTag(BlockTags.LEAVES),
                            BlockPredicate.matchingBlockTag(BlockTags.REPLACEABLE_PLANTS)),
                            BlockPredicate.matchingBlockTag(BlockTags.DRAGON_IMMUNE, Direction.DOWN.getVector()))));

    public static final RegistryEntry<ConfiguredFeature<RootSystemFeatureConfig, ?>> ROOTED_LICHEN_TREE
            = ConfiguredFeatures.register("rooted_lichen_tree", Feature.ROOT_SYSTEM,
            new RootSystemFeatureConfig(PlacedFeatures.createEntry(ModConfiguredFeatures.GLOW_LICHEN_END),
                    3, 3, BlockTags.DRAGON_IMMUNE,
                    BlockStateProvider.of(ModBlocks.DEATH_VINE), 20, 100,
                    3, 2, BlockStateProvider.of(ModBlocks.DEATH_VINE),
                    20, 2,
                    BlockPredicate.bothOf(BlockPredicate.anyOf(BlockPredicate.matchingBlocks(List.of(Blocks.AIR,
                                            Blocks.CAVE_AIR, Blocks.VOID_AIR, Blocks.WATER)), BlockPredicate.matchingBlockTag(BlockTags.LEAVES),
                                    BlockPredicate.matchingBlockTag(BlockTags.REPLACEABLE_PLANTS)),
                            BlockPredicate.matchingBlockTag(BlockTags.DRAGON_IMMUNE, Direction.DOWN.getVector()))));


    public static final RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> LAKE_HONEY = ConfiguredFeatures.register("honey_lake",
            Feature.LAKE, new LakeFeature.Config(BlockStateProvider.of(ModBlocks.HONEY_FLUID_BLOCK.getDefaultState()),
                    BlockStateProvider.of(Blocks.HONEYCOMB_BLOCK.getDefaultState())));

    public static final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> DISK_SPEED = ConfiguredFeatures.register("disk_speed",
            Feature.DISK, new DiskFeatureConfig(ModBlocks.SPEEDY_BLOCK.getDefaultState(), UniformIntProvider.create(2, 3),
                    1, List.of(Blocks.END_STONE.getDefaultState(), ModBlocks.SPEEDY_BLOCK.getDefaultState())));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SOUL_BUSH
            = ConfiguredFeatures.register("soul_bush", Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.SOUL_BLOSSOM_LEAVES),
            new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.of(ModBlocks.SOUL_BLOSSOM_LOG), new BushFoliagePlacer(ConstantIntProvider.create(2),
            ConstantIntProvider.create(1), 2), new TwoLayersFeatureSize(0, 0, 0)).build());








    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + NalasMod.MOD_ID);
    }
}
