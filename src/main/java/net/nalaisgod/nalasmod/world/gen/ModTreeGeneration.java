package net.nalaisgod.nalasmod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.nalaisgod.nalasmod.world.feature.ModPlacedFeatures;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.THEEND, Biome.Category.NETHER),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SOUL_BLOSSOM_PLACED_KEY);
    }
}
