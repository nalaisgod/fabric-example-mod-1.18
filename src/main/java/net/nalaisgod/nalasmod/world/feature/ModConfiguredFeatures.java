package net.nalaisgod.nalasmod.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
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
    public static final ConfiguredFeature<TreeFeatureConfig, ?> SOUL_BLOSSOM_TREE =
            register("soul_blossom", Feature.TREE.configure(new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.SOUL_BLOSSOM_LOG),
                    new ForkingTrunkPlacer(7, 7, 7),
                    BlockStateProvider.of(ModBlocks.SOUL_BLOSSOM_LEAVES),
                    new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                    new TwoLayersFeatureSize(1, 0, 2)).build()));


    public static final ConfiguredFeature<HugeFungusFeatureConfig, ?> SOUL_BLOSSOM_TREE_RANDOM = ConfiguredFeatures.register("soul_blossom_feature",
            Feature.HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(Blocks.END_STONE.getDefaultState(),
                    ModBlocks.SOUL_BLOSSOM_LOG.getDefaultState(), ModBlocks.SOUL_BLOSSOM_LEAVES.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(),
                    false)));
    public static final ConfiguredFeature<HugeFungusFeatureConfig, ?> SOUL_BLOSSOM_TREE_RANDOM_PLANTED = ConfiguredFeatures.register("soul_blossom_feature_planted",
            Feature.HUGE_FUNGUS.configure(new HugeFungusFeatureConfig(Blocks.END_STONE.getDefaultState(),
                    ModBlocks.SOUL_BLOSSOM_LOG.getDefaultState(), ModBlocks.SOUL_BLOSSOM_LEAVES.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(),
                    true)));

    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> FLOWER_FOR_ALL =
            ModConfiguredFeatures.register("flower_for_all", Feature.FLOWER.configure(
                    createRandomPatchFeatureConfig(BlockStateProvider.of(ModBlocks.FLOWER_FOR_ALL), 64)));


    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries,
                Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }

    public static final List<OreFeatureConfig.Target> OVERWORLD_ORIGINITE_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ORIGINITE_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORIGINITE_ORE.getDefaultState()));

    public static final RuleTest END_STONE = new BlockMatchRuleTest(Blocks.END_STONE);



    public static final ConfiguredFeature<?, ?> ORIGINITE_ORE = register("originite_ore",
            Feature.ORE.configure(new OreFeatureConfig(OVERWORLD_ORIGINITE_ORES, 9)));

    public static final ConfiguredFeature<?, ?> END_ORIGINITE_ORE = ConfiguredFeatures.register("end_originite_ore",
            Feature.SCATTERED_ORE.configure(new OreFeatureConfig(END_STONE, ModBlocks.END_ORIGINITE_ORE.getDefaultState(), 20)));



    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(NalasMod.MOD_ID, name),
                configuredFeature);
    }

    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + NalasMod.MOD_ID);
    }
}
