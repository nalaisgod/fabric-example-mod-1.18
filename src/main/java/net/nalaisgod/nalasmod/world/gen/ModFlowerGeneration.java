package net.nalaisgod.nalasmod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.world.feature.ModPlacedFeatures;

public class ModFlowerGeneration {
    public static void generateFlowers() {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_FOR_ALL_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FLOWER_FOR_ALL_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEATH_VINE_PATCH_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MOSSLIGHT_PATCH_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MOSSLIGHT_PATCH_PLACED_1.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEATH_VINE_PATCH_PLACED_1.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEATH_VINE_PATCH_PLACED_2.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ROOTED_SOUL_TREE_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ROOTED_LICHEN_TREE_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.GLOW_LICHEN_END_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LAKE_HONEY_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DISK_SPEED_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SOUL_BUSH_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.END_MINE_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MINE_PLACED.getKey().get());
    }
}