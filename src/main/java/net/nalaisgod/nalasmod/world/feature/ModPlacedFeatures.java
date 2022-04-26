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

    public static final RegistryEntry<PlacedFeature> MOSSLIGHT_PATCH_PLACED_1 = PlacedFeatures.register("mosslight_patch_placed_1",
            ModConfiguredFeatures.MOSSLIGHT_PATCH_1, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(90), BiomePlacementModifier.of());


    public static final RegistryEntry<PlacedFeature> DEATH_VINE_PATCH_PLACED_1 = PlacedFeatures.register("death_vine_patch_placed_1",
            ModConfiguredFeatures.DEATH_VINE_PATCH_1, CountMultilayerPlacementModifier.of(3), RarityFilterPlacementModifier.of(10), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> DEATH_VINE_PATCH_PLACED_2 = PlacedFeatures.register("death_vine_patch_placed_2",
            ModConfiguredFeatures.DEATH_VINE_PATCH_2, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(70), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> GLOW_LICHEN_END_PLACED = PlacedFeatures.register("glow_lichen_end_placed",
            ModConfiguredFeatures.GLOW_LICHEN_END, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(70), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ROOTED_LICHEN_TREE_PLACED = PlacedFeatures.register("rooted_lichen_tree_placed",
            ModConfiguredFeatures.ROOTED_LICHEN_TREE, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(70), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ROOTED_SOUL_TREE_PLACED = PlacedFeatures.register("rooted_soul_tree_placed",
            ModConfiguredFeatures.ROOTED_SOUL_TREE, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(70), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> DISK_SPEED_PLACED = PlacedFeatures.register("disc_speed_placed",
            ModConfiguredFeatures.DISK_SPEED, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(70), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> SOUL_BUSH_PLACED = PlacedFeatures.register("soul_bush_placed",
            ModConfiguredFeatures.SOUL_BUSH, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(70), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> LAKE_HONEY_PLACED = PlacedFeatures.register("lake_honey_placed",
            ModConfiguredFeatures.LAKE_HONEY, CountMultilayerPlacementModifier.of(5), RarityFilterPlacementModifier.of(70), BiomePlacementModifier.of());




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