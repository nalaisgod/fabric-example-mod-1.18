package net.nalaisgod.nalasmod.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.example.registry.SoundRegistry;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Predicate;


public class ModStaffItem extends RangedWeaponItem implements IAnimatable, ISyncable {
    public AnimationFactory factory = new AnimationFactory(this);
    private static final String controllerName = "controller";
    private static final int ANIM_OPEN = 0;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;


    public ModStaffItem(Settings properties) {
        super(properties);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", 7.0, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", (double)-2.9f, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
        GeckoLibNetwork.registerSyncable(this);
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
            final int id = GeckoLibUtil.guaranteeIDForStack(user.getStackInHand(hand), (ServerWorld) world);
            GeckoLibNetwork.syncAnimation(user, this, id, ANIM_OPEN);
            for (PlayerEntity otherPlayer : PlayerLookup.tracking(user)) {
                GeckoLibNetwork.syncAnimation(otherPlayer, this, id, ANIM_OPEN);
            }
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



    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        AnimationController<ModStaffItem> controller = new AnimationController(this, controllerName, 20,
                this::predicate);

        // Registering a sound listener just makes it so when any sound keyframe is hit
        // the method will be called.
        // To register a particle listener or custom event listener you do the exact
        // same thing, just with registerParticleListener and
        // registerCustomInstructionListener, respectively.
        controller.registerSoundListener(this::soundListener);
        data.addAnimationController(controller);
    }

    @SuppressWarnings("resource")
    private <ENTITY extends IAnimatable> void soundListener(SoundKeyframeEvent<ENTITY> event) {
        // The animation for the jackinthebox has a sound keyframe at time 0:00.
        // As soon as that keyframe gets hit this method fires and it starts playing the
        // sound to the current player.
        // The music is synced with the animation so the box opens as soon as the music
        // plays the box opening sound
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        player.playSound(SoundRegistry.JACK_MUSIC, 1, 1);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @SuppressWarnings("resource")
    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM_OPEN) {
            // Always use GeckoLibUtil to get AnimationControllers when you don't have
            // access to an AnimationEvent
            @SuppressWarnings("rawtypes")
            final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, controllerName);

            if (controller.getAnimationState() == AnimationState.Stopped) {
                final ClientPlayerEntity player = MinecraftClient.getInstance().player;
                if (player != null) {
                    player.sendMessage(new LiteralText("Using overseer o thyne orb"), true);
                }
                // If you don't do this, the popup animation will only play once because the
                // animation will be cached.
                controller.markNeedsReload();
                // Set the animation to open the JackInTheBoxItem which will start playing music
                // and
                // eventually do the actual animation. Also sets it to not loop
                controller.setAnimation(new AnimationBuilder().addAnimation("spin", true));
            }
        }
    }
}



