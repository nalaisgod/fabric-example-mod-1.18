package net.nalaisgod.nalasmod.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.effect.ModEffects;
import net.nalaisgod.nalasmod.item.ModItems;
import net.nalaisgod.nalasmod.mixin.BrewingRecipeRegistryMixin;

public class ModPotions {
    public static Potion FREEZE_POTION;

    public static Potion registerPotion(String name) {
        return Registry.register(Registry.POTION, new Identifier(NalasMod.MOD_ID, name),
                new Potion(new StatusEffectInstance(ModEffects.FREEZE, 40, 0)));
    }

    public static void registerPotions() {
        FREEZE_POTION = registerPotion("freeze_potion");


        registerPotionRecipes();
    }

    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.POWDER_SNOW_BUCKET,
                ModPotions.FREEZE_POTION);
    }

}
