package net.nalaisgod.nalasmod.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;


public class ModEnchantments {
    public static Enchantment LIGHTNING_STRIKER = register("lightning_striker",
            new LightningStrikerEnchantment(Enchantment.Rarity.UNCOMMON,
                    EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND));

    public static Enchantment ILLAGERS_BANE = register("illagers_bane",
            new IllagersBaneEnchantment(Enchantment.Rarity.COMMON,
                    EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND));

    public static Enchantment ELEMENTAL_BANE = register("elemental_bane",
            new ElementalBaneEnchantment(Enchantment.Rarity.COMMON,
                    EnchantmentTarget.TRIDENT, EquipmentSlot.MAINHAND));


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(NalasMod.MOD_ID, name), enchantment);
    }



    public static void registerModEnchantments() {
        System.out.println("Registering ModEnchantments for" + NalasMod.MOD_ID);
    }

}
