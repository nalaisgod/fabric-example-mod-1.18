package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.nalaisgod.nalasmod.effect.ModEffects;

public class random_blade extends SwordItem {
    public random_blade(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int chance = target.getRandom().nextInt(100);

        if (chance < 5)  {
            target.setVelocity(0, 1,0);

        }
        else if (chance < 10) {
            target.setFrozenTicks(139);
            if (!target.hasStatusEffect(ModEffects.FREEZE)) {
                target.addStatusEffect(new StatusEffectInstance(ModEffects.FREEZE, 70, 3), attacker);
            }
        }
        else if (chance < 15)  {
            target.setOnFireFor(100);

        }
        else if (chance < 20)  {
            target.setFrozenTicks(139);
            if (!target.hasStatusEffect(StatusEffects.WITHER)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 70, 3), attacker);
            }
        }
        else if (chance < 25)  {
            target.heal(-3);
        }
        else if (chance < 30)  {
            if (!target.hasStatusEffect(StatusEffects.HEALTH_BOOST)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 70, 3), attacker);
            }
        }
        else if (chance < 35)  {
            target.heal(3);

        }
        else if (chance < 40)  {
            if (!target.hasStatusEffect(StatusEffects.REGENERATION)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 70, 3), attacker);
            }
        }
        else if (chance < 45)  {
            target.setCurrentHand(Hand.OFF_HAND);
        }
        else if (chance < 50)  {
            if (!target.hasStatusEffect(StatusEffects.NAUSEA)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 70, 3), attacker);
            }
        }
        else if (chance < 55)  {
            target.setStuckArrowCount(10);

        }
        else if (chance < 60)  {
            if (!target.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 70, 3), attacker);
            }
        }
        else if (chance < 65)  {
            target.setInvisible(false);

        }
        else if (chance < 70)  {
            if (!target.hasStatusEffect(StatusEffects.SATURATION)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 70, 3), attacker);
            }
        }
        else if (chance < 75)  {
            target.setNoGravity(false);

        }
        else if (chance < 80)  {
            if (!target.hasStatusEffect(StatusEffects.HUNGER)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 70, 3), attacker);
            }
        }
        else if (chance < 85)  {
            target.setSprinting(false);

        }
        else if (chance < 90)  {
            if (!target.hasStatusEffect(StatusEffects.BAD_OMEN)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 70, 3), attacker);
            }
        }
        else if (chance < 95)  {
            attacker.heal(1);

        }
        else if (chance < 100)  {
            if (!target.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE)) {
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 70, 3), attacker);
            }
        }
        return super.postHit(stack, target, attacker);
    }



}



