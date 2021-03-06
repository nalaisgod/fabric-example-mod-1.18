package net.nalaisgod.nalasmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.fluid.ModFluids;
import net.nalaisgod.nalasmod.item.custom.*;
import net.nalaisgod.nalasmod.sound.ModSounds;
import software.bernie.example.item.PistolItem;
import software.bernie.example.registry.BlockRegistry;
import software.bernie.example.registry.RegistryUtils;

import java.util.Optional;

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
            new ModPickaxeItem(ModToolMaterial.ORIGINITE, -1, -1f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_AXE = registerItem("originite_axe",
            new ModAxeItem(ModToolMaterial.ORIGINITE, 6, -2.8f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_HOE = registerItem("originite_hoe",
            new ModHoeItem(ModToolMaterial.ORIGINITE, 0, 0f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_SHOVEL = registerItem("originite_shovel",
            new ShovelItem(ModToolMaterial.ORIGINITE, 0, 0f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_SWORD = registerItem("originite_sword",
            new ModSlowingSwordItem(ModToolMaterial.ORIGINITE, 0, 2f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_PAXEL = registerItem("originite_paxel",
            new ModPaxelItem(ModToolMaterial.PAXEL, 1, -2f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.EPIC)));

    public static final Item SOUL_SWORD = registerItem("soul_sword",
            new ModHealingSwordItem(ModToolMaterial.SOUL, 5, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_HELMET = registerItem("originite_helmet",
            new OriginiteArmorItem(ModArmorMaterials.ORIGINITE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.RARE)));

    public static final Item ORIGINITE_CHESTPLATE = registerItem("originite_chestplate",
            new OriginiteArmorItem(ModArmorMaterials.ORIGINITE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.RARE)));

    public static final Item ORIGINITE_LEGGINGS = registerItem("originite_leggings",
            new OriginiteArmorItem(ModArmorMaterials.ORIGINITE, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.RARE)));

    public static final Item ORIGINITE_BOOTS = registerItem("originite_boots",
            new OriginiteArmorItem(ModArmorMaterials.ORIGINITE, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.RARE)));

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
            new ModMusicDiscItem(9, ModSounds.BAR_BRAWL,new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item STAFF_OF_THE_ORB = registerItem("staff_of_the_orb",
            new ModStaffItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.EPIC)));

    public static final Item ORB_BOW = registerItem("orb_bow",
            new ModGunBowItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.EPIC)));

    public static final Item HONEY_BUCKET = registerItem("honey_bucket",
            new BucketItem(ModFluids.HONEY_STILL, new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1)));

    public static final Item SOUL_STCIK = registerItem("soul_stcik",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item SOUL_BLOSSOM_SIGN = registerItem("soul_blossom_sign",
            new SignItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(16),
                    ModBlocks.SOUL_BLOSSOM_SIGN_BLOCK, ModBlocks.SOUL_BLOSSOM_WALL_SIGN_BLOCK));

    public static final Item PAXEL_CHIP_A = registerItem("paxel_chip_a",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.UNCOMMON)));

    public static final Item PAXEL_CHIP_B = registerItem("paxel_chip_b",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.UNCOMMON)));

    public static final Item PAXEL_CHIP_C = registerItem("paxel_chip_c",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.RARE)));

    public static final Item ELDER_TRIDENT = registerItem("elder_trident",
            new TridentItem(new FabricItemSettings().maxDamage(250).group(ModItemGroup.ORIGINITE)));

    public static final Item RACCOON_SPAWN_EGG = registerItem("raccoon_spawn_egg",
            new SpawnEggItem(ModEntities.RACCOON,0x948e8d, 0x3b3635,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item EXITER_SPAWN_EGG = registerItem("exiter_spawn_egg",
            new SpawnEggItem(ModEntities.EXITER,99999999, 0x3b3635,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item NAMED_SPAWN_EGG = registerItem("named_spawn_egg",
            new SpawnEggItem(ModEntities.NAMED,22222222, 0x3b3635,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item TIGER_SPAWN_EGG = registerItem("tiger_spawn_egg",
            new SpawnEggItem(ModEntities.TIGER,0xfcb603, 0x242321,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ELEMENTAL_BLADE = registerItem("elemental_blade",
            new ModElemtalSwordItem(ModToolMaterial.ELEMENT, 5, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.UNCOMMON)));

    public static final Item GRAV_ROD = registerItem("grav_rod",
            new ModGravRodItem(new FabricItemSettings().maxDamage(2500).group(ModItemGroup.ORIGINITE).rarity(Rarity.RARE)));

    public static final Item TIGER_EYE = registerItem("tiger_eye",
            new ModVisionItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.UNCOMMON).maxCount(1).maxDamage(16)));

    public static final Item FREEZE_ROD = registerItem("freeze_rod",
            new ModFreezeItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.UNCOMMON)));

    public static final Item ICE_BOW = registerItem("ice_bow",
            new ModIceBowItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.RARE)));

    public static final Item WITHER_ROD = registerItem("wither_rod",
            new ModWitherItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.UNCOMMON)));

    public static final Item WITHER_BOW = registerItem("wither_bow",
            new ModWitherBowItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.EPIC)));

    public static final Item SHORT_BOW = registerItem("short_bow",
            new ModShortBowItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.RARE)));

    public static final Item BOW_EFFECT_CHIP_INFUSION = registerItem("bow_effect_chip_infusion",
            new ModChipItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.RARE).maxCount(1)));

    public static final Item UNPOWERED_ICE_BOW = registerItem("unpowered_ice_bow",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item UNPOWERED_WITHER_BOW = registerItem("unpowered_wither_bow",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.RARE)));

    public static final Item GRAV_STABLE = registerItem("grav_stable",
            new ModGravDisItemToggler(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.EPIC)));

    public static final Item POUCH_O_HOLDING = registerItem("pouch_o_holding",
            new ModBundleItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.EPIC).maxCount(1)));

    public static final Item EL_WALL = registerItem("el_wall",
            new ModShieldItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.EPIC).maxDamage(500)));

    public static final Item GRAVITY_PUSHER = registerItem("gravity_pusher",
            new ModIDKSwordItem(ModToolMaterial.ELEMENT, 5, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item RANDOM_BLADE = registerItem("random_blade",
            new random_blade(ModToolMaterial.ORIGINITE, 3, -2.2f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.RARE)));

    public static final Item CRIT_BLADE = registerItem("crit_blade",
            new ModCritBlade(ModToolMaterial.ORIGINITE, 3, -2.4f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item GOD_POT = registerItem("god_pot",
            new ModGodPotItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.UNCOMMON).maxCount(32).rarity(Rarity.UNCOMMON)));

    public static final Item ORB_OF_ORIGIN_PLACE_HOLDER = registerItem("orb_of_origin_place_holder",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item CHOCOLATE_COVERED_CEPHALOPOD = registerItem("chocolate_covered_cephalopod",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item CHOCOLATE_POWERED_GUN_WITH_PLACED_END_CRYSTAL_ON_TOP = registerItem("chocolate_powered_gun_with_placed_end_crystal_on_top",
            new ModSuperItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxCount(1).maxDamage(3).rarity(Rarity.RARE)));

    public static final Item FALLEN_KINGS_BLADE = registerItem("fallen_kings_blade",
            new ModFallenKingBladeItem(ModToolMaterial.FALLENKINGSBLADE, 7, -2.8f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE).rarity(Rarity.EPIC)));

    public static final Item APPLE_PIE = registerItem("apple_pie",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).food(ModFoodComponents.APPLE_PIE)));

    public static final Item CHOCOLATE_PIE = registerItem("chocolate_pie",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).food(ModFoodComponents.CHOCOLATE_PIE)));

    public static final Item CHOCOLATE_APPLE_PIE = registerItem("chocolate_apple_pie",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE).food(ModFoodComponents.CHOCOLATE_APPLE_PIE)));




    private static Item registerItem(String name, Item item) {
       return Registry.register(Registry.ITEM, new Identifier(NalasMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NalasMod.LOGGER.info("Registering Mod Items for " + NalasMod.MOD_ID);
    }
}

