package net.nalaisgod.nalasmod.util;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.command.ReturnHomeCommand;
import net.nalaisgod.nalasmod.command.SetHomeCommand;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.entity.custom.RaccoonEntity;
import net.nalaisgod.nalasmod.entity.custom.TigerEntity;
import net.nalaisgod.nalasmod.entity.mob.ExiterEntity;
import net.nalaisgod.nalasmod.event.ModPlayerEventCopyFrom;
import net.nalaisgod.nalasmod.item.ModItems;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
        registerModComposterChances();
        registerCommands();
        registerEvents();
        registerStrippables();
        registerAttributes();
        registerCustomTrades();
    }



    private static void registerFuels() {
        System.out.println("Registering Fuels For + " + NalasMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.HONEY_BUCKET, 400);

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

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.RACCOON, RaccoonEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.TIGER, TigerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.EXITER, ExiterEntity.setAttributes());
    }

    private static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(ModItems.APPLE_CORE, 16),
                            6,2,0.02f));
                });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 4,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 52),
                            new ItemStack(ModItems.ELEMENTAL_BLADE, 1),
                            2,3,0.08f));
                });
    }

}
