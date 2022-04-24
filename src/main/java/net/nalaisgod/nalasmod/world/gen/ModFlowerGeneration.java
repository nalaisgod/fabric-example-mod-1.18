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
    }
}