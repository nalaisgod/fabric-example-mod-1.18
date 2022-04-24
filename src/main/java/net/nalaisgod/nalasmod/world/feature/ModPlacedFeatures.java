package net.nalaisgod.nalasmod.world.feature;


import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;


public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> SOUL_BLOSSOM_PLACED = PlacedFeatures.register("soul_blossom_placed",
            ModConfiguredFeatures.SOUL_BLOSSOM_SPAWN, CountMultilayerPlacementModifier.of(1), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> SOUL_BLOSSOM_PLACED_END = PlacedFeatures.register("soul_blossom_placed_end",
            ModConfiguredFeatures.SOUL_BLOSSOM_SPAWN, CountMultilayerPlacementModifier.of(15), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> FLOWER_FOR_ALL_PLACED = PlacedFeatures.register("flower_for_all_placed",
            ModConfiguredFeatures.FLOWER_FOR_ALL, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> DEATH_VINE_PATCH_PLACED = PlacedFeatures.register("death_vine_patch_placed",
            ModConfiguredFeatures.DEATH_VINE_PATCH, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(70), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MOSSLIGHT_PATCH_PLACED = PlacedFeatures.register("mosslight_patch_placed",
            ModConfiguredFeatures.MOSSLIGHT_PATCH, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(90), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ENDSTONE_PATCH_PLACED = PlacedFeatures.register("endstone_patch_placed",
            ModConfiguredFeatures.ENDSTONE_PATCH, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(60), BiomePlacementModifier.of());


    public static final RegistryEntry<PlacedFeature> ORIGINITE_ORE_PLACED = PlacedFeatures.register("originite_ore_placed",
            ModConfiguredFeatures.ORIGINITE_ORE, ModOreFeatures.modifiersWithCount(7,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));

    public static final RegistryEntry<PlacedFeature> END_ORIGINITE_ORE_PLACED = PlacedFeatures.register("end_originite_ore_placed",
            ModConfiguredFeatures.END_ORIGINITE_ORE, ModOreFeatures.modifiersWithCount(7,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));

    public static final RegistryEntry<PlacedFeature> ORIGINAL_ORIGINITE_ORE_PLACED = PlacedFeatures.register("original_originite_ore_placed",
            ModConfiguredFeatures.END_ORIGINITE_ORE, ModOreFeatures.modifiersWithCount(70,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(0), YOffset.aboveBottom(300))));

}