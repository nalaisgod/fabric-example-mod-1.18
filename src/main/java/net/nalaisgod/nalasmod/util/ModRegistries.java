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
import net.nalaisgod.nalasmod.entity.mob.DaveEntity;
import net.nalaisgod.nalasmod.entity.mob.ExiterEntity;
import net.nalaisgod.nalasmod.entity.mob.NamedEntity;
import net.nalaisgod.nalasmod.event.ModPlayerEventCopyFrom;
import net.nalaisgod.nalasmod.item.ModItems;
import net.nalaisgod.nalasmod.villager.ModVillagers;

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
        FabricDefaultAttributeRegistry.register(ModEntities.NAMED, NamedEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.DAVE, DaveEntity.setAttributes());
    }

    private static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(ModItems.APPLE_CORE, 16),
                            6,2,0.02f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 9),
                            new ItemStack(ModItems.DATA_TABLET, 1),
                            12,3,0.08f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.RAW_ORIGINITE, 8),
                            new ItemStack(Items.EMERALD, 6),
                            12,3,0.08f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 5),
                            new ItemStack(Items.BUCKET),
                            new ItemStack(ModItems.HONEY_BUCKET, 1),
                            12,3,0.08f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 30),
                            new ItemStack(ModItems.ORIGINITE_INGOT),
                            new ItemStack(ModItems.ORIGINITE_SHOVEL, 1),
                            12,10,0.08f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 30),
                            new ItemStack(ModItems.ORIGINITE_INGOT),
                            new ItemStack(ModItems.ORIGINITE_PICKAXE, 1),
                            12,10,0.08f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 30),
                            new ItemStack(ModItems.ORIGINITE_INGOT),
                            new ItemStack(ModItems.ORIGINITE_AXE, 1),
                            12,10,0.08f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 30),
                            new ItemStack(ModItems.ORIGINITE_INGOT),
                            new ItemStack(ModItems.ORIGINITE_HOE, 1),
                            12,10,0.08f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 64),
                            new ItemStack(Items.HEART_OF_THE_SEA),
                            new ItemStack(ModItems.ELDER_TRIDENT, 1),
                            12,13,0.08f));
                });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BLAST_MASTER, 4,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 50),
                            new ItemStack(ModItems.BOW_EFFECT_CHIP_INFUSION, 1),
                            12,30,0.08f));
                });
    }

}
