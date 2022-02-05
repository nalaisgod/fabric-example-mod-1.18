package net.nalaisgod.nalasmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.item.ModItemGroup;

public class ModBlocks {

    public static final Block ORIGINITE_BLOCK = registerBlock("originite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_ORE = registerBlock("originite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block DEEPSLATE_ORIGINITE_ORE = registerBlock("deepslate_originite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block END_ORIGINITE_ORE = registerBlock("end_originite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block NETHER_ORIGINITE_ORE = registerBlock("nether_originite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()), ModItemGroup.ORIGINITE);


    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(NalasMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
    return Registry.register(Registry.ITEM, new Identifier(NalasMod.MOD_ID, name),
            new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
        NalasMod.LOGGER.info("registering ModBlocks for " + NalasMod.MOD_ID);
    }

}
