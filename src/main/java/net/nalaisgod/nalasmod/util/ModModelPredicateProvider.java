package net.nalaisgod.nalasmod.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.item.ModItems;

public class ModModelPredicateProvider {
    public static void registerModModels() {
        FabricModelPredicateProviderRegistry.register(ModItems.DATA_TABLET, new Identifier(NalasMod.MOD_ID,"on"),
                ((stack, world, entity, seed) -> stack.hasNbt() ? 1f : 0f));
    }
}
