package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BowItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class ModGunBowItem extends BowItem {
    public ModGunBowItem(Settings settings) {
        super(settings);
    }





    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireFor(10);
        target.setVelocity(0,2,0);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 2), attacker);
        return super.postHit(stack, target, attacker);
    }

}
