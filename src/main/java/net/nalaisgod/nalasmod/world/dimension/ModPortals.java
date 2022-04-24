package net.nalaisgod.nalasmod.world.dimension;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.item.ModItems;

public class ModPortals {
    public static void registerPortals() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.ORIGINITE_BLOCK)
                .lightWithItem(ModItems.DOWSING_ROD)
                .destDimID(ModDimensions.THEORIGIN_KEY.getValue())
                .tintColor(150, 200, 60)
                .registerPortal();
    }
}