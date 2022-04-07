package net.nalaisgod.nalasmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.config.ModConfigs;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.entity.client.*;
import net.nalaisgod.nalasmod.entity.client.armor.OriginiteArmorRenderer;
import net.nalaisgod.nalasmod.event.ReplaceTitleScreenEvent;
import net.nalaisgod.nalasmod.fluid.ModFluids;
import net.nalaisgod.nalasmod.item.ModItems;
import net.nalaisgod.nalasmod.screen.ModScreenHandlers;
import net.nalaisgod.nalasmod.screen.OriginiteBlasterScreen;
import net.nalaisgod.nalasmod.util.ModModelPredicateProvider;
import software.bernie.example.registry.ItemRegistry;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class NalasModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfigs.registerConfigs();

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOUL_BLOSSOM_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOUL_BLOSSOM_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPLE_TREE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLOWER_FOR_ALL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FLOWER_FOR_ALL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORIGINITE_BLASTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOUL_BLOSSOM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOUL_BLOSSOM_SAPLING, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WINTER_WINDOW, RenderLayer.getTranslucent());


        ModModelPredicateProvider.registerModModels();


        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_STILL,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0xe9860c));

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HONEY_FLOWING,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY, 0xe9860c));

        ScreenRegistry.register(ModScreenHandlers.ORIGINITE_BLASTER_SCREEN_HANDLER, OriginiteBlasterScreen::new);

        ScreenEvents.BEFORE_INIT.register(new ReplaceTitleScreenEvent());
        EntityRendererRegistry.register(ModEntities.RACCOON, RaccoonRenderer::new);
        EntityRendererRegistry.register(ModEntities.TIGER, TigerRenderer::new);
        EntityRendererRegistry.register(ModEntities.EXITER, ExiterRenderer::new);
        EntityRendererRegistry.register(ModEntities.NAMED, NamedRenderer::new);


        GeoArmorRenderer.registerArmorRenderer(new OriginiteArmorRenderer(), ModItems.ORIGINITE_BOOTS,
                ModItems.ORIGINITE_LEGGINGS, ModItems.ORIGINITE_CHESTPLATE, ModItems.ORIGINITE_HELMET);
        GeoItemRenderer.registerItemRenderer(ModItems.STAFF_OF_THE_ORB, new StaffRenderer());


    }
}
