package net.nalaisgod.nalasmod.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
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
            Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                    new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.DEATH_VINE))));



    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + NalasMod.MOD_ID);
    }
}
