package net.nalaisgod.nalasmod.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;

public class ModRecipes {
    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(NalasMod.MOD_ID, OriginiteBlasterRecipe.Serializer.ID),
                OriginiteBlasterRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(NalasMod.MOD_ID, OriginiteBlasterRecipe.Type.ID),
                OriginiteBlasterRecipe.Type.INSTANCE);


        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(NalasMod.MOD_ID, OrbitalTransfuzerRecipe.Serializer.ID),
                OrbitalTransfuzerRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(NalasMod.MOD_ID, OrbitalTransfuzerRecipe.Type.ID),
                OrbitalTransfuzerRecipe.Type.INSTANCE);
    }
}