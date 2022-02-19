package net.nalaisgod.nalasmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.item.custom.*;

public class ModItems {
    public static final Item ORIGINITE_INGOT = registerItem("originite_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item ORIGINITE_NUGGET = registerItem("originite_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item RAW_ORIGINITE = registerItem("raw_originite",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ModItemGroup.ORIGINITE).maxDamage(16)));

    public static final Item FLOWER_FOR_ALL = registerItem("flower_for_all",
            new Item(new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

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
            new ModPaxelItem(ModToolMaterial.ORIGINITE, 1, 1f,
                    new FabricItemSettings().group(ModItemGroup.ORIGINITE)));

    public static final Item SOUL_SWORD = registerItem("soul_sword",
            new ModHealingSwordItem(ModToolMaterial.ORIGINITE, 5, -2f,
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




    private static Item registerItem(String name, Item item) {
       return Registry.register(Registry.ITEM, new Identifier(NalasMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NalasMod.LOGGER.info("Registering Mod Items for " + NalasMod.MOD_ID);
    }
}
