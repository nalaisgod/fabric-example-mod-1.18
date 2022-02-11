package net.nalaisgod.nalasmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.NalasMod;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.block.custom.*;
import net.nalaisgod.nalasmod.item.ModItemGroup;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class ModBlocks {

    public static final Block ORIGINITE_BLOCK = registerBlock("originite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(150f).requiresTool()),
            ModItemGroup.ORIGINITE, "tooltip.nalasmod.originite_block");

    public static final Block ORIGINITE_ORE = registerBlock("originite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block DEEPSLATE_ORIGINITE_ORE = registerBlock("deepslate_originite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(5.5f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block END_ORIGINITE_ORE = registerBlock("end_originite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block NETHER_ORIGINITE_ORE = registerBlock("nether_originite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block RAW_ORIGINITE_BLOCK = registerBlock("raw_originite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block SPEEDY_BLOCK = registerBlock("speedy_block",
            new SpeedyBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE, "tooltip.nalasmod.speedy_block");

    public static final Block ORIGINITE_STAIRS = registerBlock("originite_stairs",
            new ModStairsBlock(ModBlocks.ORIGINITE_BLOCK.getDefaultState(),
                    FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_SLAB = registerBlock("originite_slab",
            new SlabBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_BUTTON = registerBlock("originite_button",
            new ModStoneButtonBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_PRESURE_PLATE = registerBlock("originite_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_FENCE = registerBlock("originite_fence",
            new FenceBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_FENCE_GATE = registerBlock("originite_fence_gate",
            new FenceGateBlock(FabricBlockSettings.of(Material.METAL).strength(2.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_WALL = registerBlock("originite_wall",
            new WallBlock(FabricBlockSettings.of(Material.METAL).strength(2.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block SOUL_BLOSSOM_DOOR = registerBlock("soul_blossom_door",
            new ModDoorBlock(FabricBlockSettings.of(Material.WOOD).strength(4.0f).requiresTool().nonOpaque()), ModItemGroup.ORIGINITE);

    public static final Block SOUL_BLOSSOM_TRAPDOOR = registerBlock("soul_blossom_trapdoor",
            new ModTrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(4.0f).requiresTool().nonOpaque()), ModItemGroup.ORIGINITE);

    private static Block registerBlock(String name, Block block, ItemGroup group, String tooltipKey) {
    registerBlockItem(name, block, group, tooltipKey);
    return Registry.register(Registry.BLOCK, new Identifier(NalasMod.MOD_ID, name), block);
}

private static Item registerBlockItem(String name, Block block, ItemGroup group, String tooltipKey) {
    return Registry.register(Registry.ITEM, new Identifier(NalasMod.MOD_ID, name),
            new BlockItem(block, new FabricItemSettings().group(group)) {
                @Override
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    tooltip.add(new TranslatableText(tooltipKey));
                }
            });
}

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
