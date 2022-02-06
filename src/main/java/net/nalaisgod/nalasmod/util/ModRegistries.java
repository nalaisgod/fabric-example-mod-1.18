package net.nalaisgod.nalasmod.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.item.ModItems;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
    }



    private static void registerFuels() {
        NalasMod.LOGGER.info("Registering Fuels For" + NalasMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.FLOWER_FOR_ALL, 200);

    }

}
