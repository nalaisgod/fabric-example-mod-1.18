package net.nalaisgod.nalasmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.nalaisgod.nalasmod.NalasMod;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.block.custom.*;
import net.nalaisgod.nalasmod.entity.custom.PedistalEntity;
import net.nalaisgod.nalasmod.fluid.ModFluids;
import net.nalaisgod.nalasmod.item.ModItemGroup;
import net.nalaisgod.nalasmod.sound.ModSounds;
import net.nalaisgod.nalasmod.world.feature.ModConfiguredFeatures;
import net.nalaisgod.nalasmod.world.feature.tree.SoulBlossomSaplingGenerator;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.example.registry.BlockRegistry;
import software.bernie.geckolib3.GeckoLib;

import java.util.List;

public class ModBlocks {

    public static final Block ORIGINITE_BLOCK = registerBlock("originite_block",
            new ModUnmoveableBlock(FabricBlockSettings.of(Material.METAL).strength(150f, 10000000f).requiresTool()),
            ModItemGroup.ORIGINITE, "tooltip.nalasmod.originite_block");

    public static final Block ORIGINITE_ORE = registerBlock("originite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.5f, 8f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ORIGINITE);

    public static final Block DEEPSLATE_ORIGINITE_ORE = registerBlock("deepslate_originite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(5.5f, 8f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ORIGINITE);

    public static final Block END_ORIGINITE_ORE = registerBlock("end_originite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.5f, 8f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ORIGINITE);

    public static final Block NETHER_ORIGINITE_ORE = registerBlock("nether_originite_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4.5f, 8f).requiresTool(),
                    UniformIntProvider.create(7, 7)), ModItemGroup.ORIGINITE);

    public static final Block RAW_ORIGINITE_BLOCK = registerBlock("raw_originite_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6.0f, 12f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block SPEEDY_BLOCK = registerBlock("speedy_block",
            new SpeedyBlock(FabricBlockSettings.of(Material.STONE).strength(4.0f, 8f).requiresTool()), ModItemGroup.ORIGINITE, "tooltip.nalasmod.speedy_block");

    public static final Block ORIGINITE_STAIRS = registerBlock("originite_stairs",
            new ModStairsBlock(ModBlocks.ORIGINITE_BLOCK.getDefaultState(),
                    FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_SLAB = registerBlock("originite_slab",
            new SlabBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_BUTTON = registerBlock("originite_button",
            new ModStoneButtonBlock(FabricBlockSettings.of(Material.METAL).noCollision().strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block ORIGINITE_PRESURE_PLATE = registerBlock("originite_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.of(Material.METAL).noCollision().strength(4.0f).requiresTool()), ModItemGroup.ORIGINITE);

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

    public static final Block ORIGINITE_LAMP = registerBlock("originite_lamp",
            new OriginiteLampBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()
                    .luminance((state) -> state.get(OriginiteLampBlock.CLICKED) ? 15 : 0).sounds(ModSounds.ORIGINITE_SOUNDS)), ModItemGroup.ORIGINITE);

    public static final Block APPLE_TREE = registerBlockWithoutBlockItem("apple_tree",
            new ModAppleBlock(FabricBlockSettings.copy(Blocks.BEETROOTS)));

    public static final Block FLOWER_FOR_ALL = registerBlock("flower_for_all",
            new ModEndFlowerBlock(StatusEffects.LEVITATION, 5,
                    FabricBlockSettings.copy(Blocks.PINK_TULIP)), ModItemGroup.ORIGINITE);

    public static final Block POTTED_FLOWER_FOR_ALL = registerBlockWithoutBlockItem("potted_flower_for_all",
            new FlowerPotBlock(ModBlocks.FLOWER_FOR_ALL, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM)));

    public static final Block ORIGINITE_BLASTER = registerBlock("originite_blaster",
            new OriginiteBlasterBlock(FabricBlockSettings.of(Material.METAL).strength(6.0f).requiresTool().nonOpaque()), ModItemGroup.ORIGINITE);

    public static final Block HONEY_FLUID_BLOCK = registerBlockWithoutBlockItem("honey_fluid_block",
            new ModFluidBlock(ModFluids.HONEY_STILL, FabricBlockSettings.of(Material.WATER).noCollision().nonOpaque().dropsNothing()));


    public static final Block SOUL_BLOSSOM_LOG = registerBlock("soul_blossom_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), ModItemGroup.ORIGINITE);
    public static final Block SOUL_BLOSSOM_WOOD = registerBlock("soul_blossom_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)), ModItemGroup.ORIGINITE);

    public static final Block STRIPED_SOUL_BLOSSOM_LOG = registerBlock("stripped_soul_blossom_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG)), ModItemGroup.ORIGINITE);
    public static final Block STRIPPED_SOUL_BLOSSOM_WOOD = registerBlock("stripped_soul_blossom_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD)), ModItemGroup.ORIGINITE);

    public static final Block SOUL_BLOSSOM_PLANKS = registerBlock("soul_blossom_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)), ModItemGroup.ORIGINITE);


    public static final Block SOUL_BLOSSOM_WALL_SIGN_BLOCK = registerBlockWithoutBlockItem("soul_blossom_wall_sign_block",
            new WallSignBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).noCollision(), ModSignTypes.SOUL_BLOSSOM));

    public static final Block SOUL_BLOSSOM_SIGN_BLOCK = registerBlockWithoutBlockItem("soul_blossom_sign_block",
            new SignBlock(FabricBlockSettings.copy(Blocks.OAK_PLANKS).noCollision(), ModSignTypes.SOUL_BLOSSOM));


    public static final Block SOUL_BLOSSOM_LEAVES = registerBlock("soul_blossom_leaves",
            new GlassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.BRIGHT_TEAL).nonOpaque().strength(1.0f).sounds(BlockSoundGroup.WART_BLOCK)),
            ModItemGroup.ORIGINITE);

    public static final Block SOUL_BLOSSOM_SAPLING = registerBlock("soul_blossom_sapling",
            new ModSaplingBlock(FabricBlockSettings.of(Material.PLANT).breakInstantly().noCollision().sounds(BlockSoundGroup.FUNGUS),
                    () -> ModConfiguredFeatures.SOUL_BLOSSOM_SPAWN_PLANTED), ModItemGroup.ORIGINITE);

    public static final Block WINTER_WINDOW = registerBlock("winter_window",
            new ModGlassBlock(FabricBlockSettings.copy(Blocks.GLASS).nonOpaque().strength(100.0f, 10000000f).requiresTool()), ModItemGroup.ORIGINITE);


    public static final Block BOB_HEAD = registerBlock("bob_head",
            new ModHeadClass(FabricBlockSettings.of(Material.METAL).strength(150f, 10000000f)),
            ModItemGroup.ORIGINITE);

    public static final Block BOB_CORE = registerBlock("bob_core",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6.0f, 12f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block DEATH_VINE = registerBlock("death_vine",
            new DeathVine(FabricBlockSettings.of(Material.METAL).strength(6.0f, 12f).requiresTool()), ModItemGroup.ORIGINITE);

    public static final Block MOSSLIGHT = registerBlock("mosslight",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6.0f, 12f).requiresTool().luminance(state -> 15)), ModItemGroup.ORIGINITE);

    public static final Block PEDISTAL = registerBlock("pedistal",
            new ModSummonDave(FabricBlockSettings.of(Material.METAL).strength(6.0f, 12f).requiresTool()), ModItemGroup.ORIGINITE);



    public static final Block ENERGY_CRYSTAL = registerBlock("energy_crystal",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6.0f, 12f).requiresTool().luminance(state -> 15)), ModItemGroup.ORIGINITE);


    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(NalasMod.MOD_ID, name), block);
    }

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
