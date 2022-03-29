package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.effect.ModEffects;
import net.nalaisgod.nalasmod.entity.projectile.IceArrow;
import net.nalaisgod.nalasmod.potion.ModPotions;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModFreezeItem
        extends ArrowItem {
        public ModFreezeItem(Item.Settings settings) {
            super(settings);
        }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new IceArrow(world, shooter);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.setFrozenTicks(200);
        target.addStatusEffect(new StatusEffectInstance(ModEffects.FREEZE, 60, 0), attacker);

        return super.postHit(stack, target, attacker);
    }
}





