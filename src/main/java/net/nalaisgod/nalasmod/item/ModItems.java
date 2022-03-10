package net.nalaisgod.nalasmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.fluid.ModFluids;
import net.nalaisgod.nalasmod.item.custom.*;
import net.nalaisgod.nalasmod.sound.ModSounds;

public class ModItems {
    public static final Item ORIGINITE_INGOT = registerItem("originite_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_NUGGET = registerItem("originite_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item RAW_ORIGINITE = registerItem("raw_originite",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxDamage(16)));

    public static final Item SUBSTANCE = registerItem("substance",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).food(ModFoodComponents.SUBSTANCE)));

    public static final Item ORIGINITE_PICKAXE = registerItem("originite_pickaxe",
            new ModPickaxeItem(ModToolMaterial.ORIGINITE, 2, -1f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_AXE = registerItem("originite_axe",
            new ModAxeItem(ModToolMaterial.ORIGINITE, 7, -2.8f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_HOE = registerItem("originite_hoe",
            new ModHoeItem(ModToolMaterial.ORIGINITE, 0, 0f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_SHOVEL = registerItem("originite_shovel",
            new ShovelItem(ModToolMaterial.ORIGINITE, 0, 0f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_SWORD = registerItem("originite_sword",
            new ModSlowingSwordItem(ModToolMaterial.ORIGINITE, 1, 2f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_PAXEL = registerItem("originite_paxel",
            new ModPaxelItem(ModToolMaterial.PAXEL, 1, 1f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item SOUL_SWORD = registerItem("soul_sword",
            new ModHealingSwordItem(ModToolMaterial.SOUL, 7, -3f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_HELMET = registerItem("originite_helmet",
            new ModArmorItem(ModArmorMaterials.ORIGINITE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_CHESTPLATE = registerItem("originite_chestplate",
            new ArmorItem(ModArmorMaterials.ORIGINITE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_LEGGINGS = registerItem("originite_leggings",
            new ArmorItem(ModArmorMaterials.ORIGINITE, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_BOOTS = registerItem("originite_boots",
            new ArmorItem(ModArmorMaterials.ORIGINITE, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_HORSE_ARMOR = registerItem("originite_horse_armor",
            new HorseArmorItem( 15, "originite",
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item DATA_TABLET = registerItem("data_tablet",
            new DataTabletItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1)));

    public static final Item APPLE_CORE = registerItem("apple_core",
            new AliasedBlockItem(ModBlocks.APPLE_TREE, new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item SOUL_PICKAXE = registerItem("soul_pickaxe",
            new ModPickaxeItem(ModToolMaterial.SOUL, 8, -3.3f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item SOUL_AXE = registerItem("soul_axe",
            new ModAxeItem(ModToolMaterial.SOUL, 11, -3.5f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item SOUL_SCYTHE = registerItem("soul_scythe",
            new ModNauseaHoeItem(ModToolMaterial.SOUL, 15, -3.9f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item SOUL_SHOVEL = registerItem("soul_shovel",
            new ShovelItem(ModToolMaterial.SOUL, 7, -3.6f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            new ModMusicDiscItem(9, ModSounds.BAR_BRAWL,new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1)));

    public static final Item STAFF_OF_THE_ORB = registerItem("staff_of_the_orb",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1)));

    public static final Item ORB_BOW = registerItem("orb_bow",
            new ModGunBowItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1)));

    public static final Item HONEY_BUCKET = registerItem("honey_bucket",
            new BucketItem(ModFluids.HONEY_STILL, new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1)));

    public static final Item SOUL_STCIK = registerItem("soul_stcik",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item SOUL_BLOSSOM_SIGN = registerItem("soul_blossom_sign",
            new SignItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(16),
                    ModBlocks.SOUL_BLOSSOM_SIGN_BLOCK, ModBlocks.SOUL_BLOSSOM_WALL_SIGN_BLOCK));

    public static final Item PAXEL_CHIP_A = registerItem("paxel_chip_a",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item PAXEL_CHIP_B = registerItem("paxel_chip_b",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item PAXEL_CHIP_C = registerItem("paxel_chip_c",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));



    private static Item registerItem(String name, Item item) {
       return Registry.register(Registry.ITEM, new Identifier(NalasMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NalasMod.LOGGER.info("Registering Mod Items for " + NalasMod.MOD_ID);
    }
}
