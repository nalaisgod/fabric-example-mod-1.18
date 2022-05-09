package net.nalaisgod.nalasmod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;

public class ModFoodComponents {
    public static final FoodComponent SUBSTANCE = new FoodComponent.Builder().hunger(2).alwaysEdible().saturationModifier(0.1f).statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 20, 0), 0.5f).statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20, 126), 0.1f).build();
    public static final FoodComponent CHOCOLATE_PIE = new FoodComponent.Builder().hunger(5).saturationModifier(0.8f).build();
    public static final FoodComponent APPLE_PIE = new FoodComponent.Builder().hunger(10).saturationModifier(0.4f).build();
    public static final FoodComponent CHOCOLATE_APPLE_PIE = new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();
}
