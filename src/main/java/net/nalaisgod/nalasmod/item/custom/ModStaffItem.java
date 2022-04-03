package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class ModStaffItem extends RangedWeaponItem {
    public ModStaffItem(Item.Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.setFrozenTicks(200);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 200, 0), attacker);

        target.setVelocity(0,2,0);
        target.takeKnockback(10,0,0);
        target.setFrozenTicks(200);
        target.setOnFireFor(10);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 1), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), attacker);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 200, 1), attacker);
        return super.postHit(stack, target, attacker);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        user.getItemCooldownManager().set(this, 200);
        if (!world.isClient) {
            EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, user);
            enderPearlEntity.setItem(itemStack);
            enderPearlEntity.setVelocity(user, user.getPitch(), user.getYaw(), 1.0f, 3.0f, 0.0f);
            world.spawnEntity(enderPearlEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 100;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.nalasmod.staff.tooltip.shift"));
        } else {
            tooltip.add(new TranslatableText("item.nalasmod.dowsing_rod.tooltip"));
        }
    }
}



