/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.item.ModItems;

public class ModShieldItem
extends Item {
    public ModShieldItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 1200);
        user.setCurrentHand(hand);
        itemStack.damage(3, user, p -> p.sendToolBreakStatus(hand));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 3));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 5));

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        PlayerEntity playerEntity = (PlayerEntity)user;
        if (!world.isClient) {
            user.clearStatusEffects();
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 600;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModItems.RAW_ORIGINITE) || super.canRepair(stack, ingredient);
    }
}

