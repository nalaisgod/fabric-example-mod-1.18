package net.nalaisgod.nalasmod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.world.feature.ModPlacedFeatures;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.spawnsOneOf(ModEntities.EXITER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SOUL_BLOSSOM_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SOUL_BLOSSOM_PLACED.getKey().get());
    }

}
