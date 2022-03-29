package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.entity.projectile.WitherArrow;

public class ModOrbItem
        extends ArrowItem {
        public ModOrbItem(Settings settings) {
            super(settings);
        }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new WitherArrow(world, shooter);
    }

}





