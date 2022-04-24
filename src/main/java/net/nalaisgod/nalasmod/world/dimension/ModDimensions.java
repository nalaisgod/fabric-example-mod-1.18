package net.nalaisgod.nalasmod.world.dimension;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.nalaisgod.nalasmod.NalasMod;

public class ModDimensions {
    private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY,
            new Identifier(NalasMod.MOD_ID, "theorigin"));
    public static RegistryKey<World> THEORIGIN_KEY = RegistryKey.of(Registry.WORLD_KEY, DIMENSION_KEY.getValue());
    private static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            new Identifier(NalasMod.MOD_ID, "theorigin_type"));

    public static void register() {
        System.out.println("Registering ModDimensions for " + NalasMod.MOD_ID);
    }
}