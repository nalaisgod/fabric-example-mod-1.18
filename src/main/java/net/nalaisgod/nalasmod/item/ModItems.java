package net.nalaisgod.nalasmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;

public class ModItems {
    public static final Item ORIGINITE_INGOT = registerItem("originite_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_NUGGET = registerItem("originite_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item RAW_ORIGINITE = registerItem("raw_originite",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));


    private static Item registerItem(String name, Item item) {
       return Registry.register(Registry.ITEM, new Identifier(NalasMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NalasMod.LOGGER.info("Registering Mod Items for " + NalasMod.MOD_ID);
    }
}
