package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.effect.ModEffects;
import net.nalaisgod.nalasmod.entity.projectile.IceArrow;
import net.nalaisgod.nalasmod.entity.projectile.WitherArrow;

public class ModWitherItem
        extends ArrowItem {
        public ModWitherItem(Settings settings) {
            super(settings);
        }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new WitherArrow(world, shooter);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 0), attacker);

        return super.postHit(stack, target, attacker);
    }
}





