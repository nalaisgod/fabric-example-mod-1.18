package net.nalaisgod.nalasmod.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.entity.projectile.Bullet;
import net.nalaisgod.nalasmod.entity.projectile.Gun_projectile;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ModCombotem extends Item {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public ModCombotem(Settings properties) {
        super(properties);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", 7.0, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", (double)-2.9f, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.setFrozenTicks(200);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0), attacker);
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0), attacker);
        target.setVelocity(0,4,0);
        target.takeKnockback(10,0,0);
        target.setFrozenTicks(200);
        target.setOnFireFor(10);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0), attacker);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }



    @Override
    public void onStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int remainingUseTicks) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity) entityLiving;
            if (stack.getDamage() < (stack.getMaxDamage() - 1)) {
                playerentity.getItemCooldownManager().set(this, 5);
                if (!worldIn.isClient) {
                    Bullet bullet = createArrow(worldIn, stack, playerentity);
                    bullet.setVelocity(playerentity, playerentity.getPitch(), playerentity.getYaw(), 0.0F,
                            1.0F * 3.0F, 1.0F);

                    bullet.setDamage(2.5);
                    bullet.age = 35;
                    bullet.hasNoGravity();

                    stack.damage(1, entityLiving, p -> p.sendToolBreakStatus(entityLiving.getActiveHand()));
                    worldIn.spawnEntity(bullet);
                }
            }
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }




    public Bullet createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        Bullet arrowentity = new Bullet(worldIn, shooter);
        return arrowentity;
    }

    public static float getArrowVelocity(int charge) {
        float f = (float) charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public Gun_projectile customeArrow(Gun_projectile arrow) {
        return arrow;
    }



    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.nalasmod.staff.tooltip.shift"));
        } else {
            tooltip.add(new TranslatableText("item.nalasmod.dowsing_rod.tooltip"));
        }
    }


    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }



}



