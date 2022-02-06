package net.nalaisgod.nalasmod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent SUBSTANCE = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 20, 0), 0.5f).statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 400, 10), 0.1f).build();
}
