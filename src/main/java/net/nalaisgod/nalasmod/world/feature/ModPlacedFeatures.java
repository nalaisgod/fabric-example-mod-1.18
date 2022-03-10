package net.nalaisgod.nalasmod.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.heightprovider.VeryBiasedToBottomHeightProvider;
import net.nalaisgod.nalasmod.NalasMod;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> SOUL_BLOSSOM_PLACED_KEY = registerKey("soul_blossom_placed");
    public static final RegistryKey<PlacedFeature> FLOWER_FOR_ALL_PLACED_KEY = registerKey("flower_for_all_placed");
    public static final RegistryKey<PlacedFeature> ORIGINITE_ORE_PLACED_KEY = registerKey("originite_ore_placed");
    public static final RegistryKey<PlacedFeature> END_ORIGINITE_ORE_PLACED_KEY = registerKey("end_originite_ore_placed");



    public static final PlacedFeature SOUL_BLOSSOM_PLACED = registerPlacedFeature("soul_blossom_placed",
            ModConfiguredFeatures.SOUL_BLOSSOM_TREE_RANDOM.withPlacement(CountMultilayerPlacementModifier.of(1), BiomePlacementModifier.of()));

    public static final PlacedFeature FLOWER_FOR_ALL_PLACED = registerPlacedFeature("flower_for_all_placed",
            ModConfiguredFeatures.FLOWER_FOR_ALL.withPlacement(RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));


    public static final PlacedFeature ORIGINITE_ORE_PLACED = registerPlacedFeature("originite_ore_placed",
            ModConfiguredFeatures.ORIGINITE_ORE.withPlacement(ModOreFeatures.modifiersWithCount(7,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))));

    public static final PlacedFeature END_ORIGINITE_ORE_PLACED = registerPlacedFeature("end_originite_ore_placed",
            ModConfiguredFeatures.END_ORIGINITE_ORE.withPlacement(ModOreFeatures.modifiersWithCount(7,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))));


    private static PlacedFeature registerPlacedFeature(String name, PlacedFeature placedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(NalasMod.MOD_ID, name), placedFeature);
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(NalasMod.MOD_ID, name));
    }


}