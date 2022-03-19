/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package net.nalaisgod.nalasmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.nalaisgod.nalasmod.item.custom.ModElemtalSwordItem;

public class ElementalBaneEnchantment
        extends Enchantment  {
    public ElementalBaneEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }


    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }


    @Override
    public float getAttackDamage(int level, EntityGroup group) {

        if (group == EntityGroup.UNDEAD) {
            return (float)level * 1.5f;
        }
        if (group == EntityGroup.ARTHROPOD) {
            return (float)level * 1.5f;
        }
        if (group == EntityGroup.ILLAGER) {
            return (float)level * 1.5f;
        }
        if (group == EntityGroup.AQUATIC) {
            return (float)level * 1.5f;
        }
        return 0.0f;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if (stack.getItem() instanceof ModElemtalSwordItem) {
            return true;
        }
        return super.isAcceptableItem(stack);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) target;
            {
                int i = 20 + user.getRandom().nextInt(10 * level);
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, i, 3));
            }
        }
    }
}



