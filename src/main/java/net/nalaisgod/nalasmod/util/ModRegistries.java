package net.nalaisgod.nalasmod.util;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.ComposterBlock;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.command.ReturnHomeCommand;
import net.nalaisgod.nalasmod.command.SetHomeCommand;
import net.nalaisgod.nalasmod.event.ModPlayerEventCopyFrom;
import net.nalaisgod.nalasmod.item.ModItems;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
        registerModComposterChances();
        registerCommands();
        registerEvents();
        registerStrippables();
    }



    private static void registerFuels() {
        NalasMod.LOGGER.info("Registering Fuels For" + NalasMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.HONEY_BUCKET, 2000);

    }

    private static void registerModComposterChances() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.APPLE_CORE, 0.3f);
    }


    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
    }

    private static void registerEvents() {
        ServerPlayerEvents.COPY_FROM.register(new ModPlayerEventCopyFrom());
    }


    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.SOUL_BLOSSOM_LOG, ModBlocks.STRIPED_SOUL_BLOSSOM_LOG);
        StrippableBlockRegistry.register(ModBlocks.SOUL_BLOSSOM_WOOD, ModBlocks.STRIPPED_SOUL_BLOSSOM_WOOD);
    }
}
