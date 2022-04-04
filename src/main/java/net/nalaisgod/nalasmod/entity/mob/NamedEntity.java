package net.nalaisgod.nalasmod.entity.mob;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SpiderNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.effect.ModEffects;
import net.nalaisgod.nalasmod.fluid.ModFluids;
import net.nalaisgod.nalasmod.util.ModTags;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class NamedEntity extends HostileEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final TrackedData<Byte> NAMED_FLAGS = DataTracker.registerData(NamedEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final float field_30498 = 0.1f;
    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS).setDarkenSky(true);



    public NamedEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return TameableEntity.createMobAttributes()

                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 10.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.3f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.4f));
        this.goalSelector.add(4, new NamedEntity.AttackGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(2, new NamedEntity.TargetGoal<PlayerEntity>(this, PlayerEntity.class));
        this.targetSelector.add(3, new NamedEntity.TargetGoal<IronGolemEntity>(this, IronGolemEntity.class));
    }

    public boolean canWalkOnFluid(Fluid fluid) {
        return fluid.isIn(FluidTags.WATER) || fluid.isIn(FluidTags.LAVA);
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        if (effect.getEffectType() == ModEffects.FREEZE) {
            return false;
        }
        return super.canHaveStatusEffect(effect);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new SpiderNavigation(this, world);
    }

    @Override
    public boolean isClimbing() {
        return this.isClimbingWall();
    }

    public boolean isClimbingWall() {
        return (this.dataTracker.get(NAMED_FLAGS) & 1) != 0;
    }

    public void setClimbingWall(boolean climbing) {
        byte b = this.dataTracker.get(NAMED_FLAGS);
        b = climbing ? (byte)(b | 1) : (byte)(b & 0xFFFFFFFE);
        this.dataTracker.set(NAMED_FLAGS, b);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClient) {
            this.setClimbingWall(this.horizontalCollision);
        }
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.named.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.named.attack", true));
        return PlayState.CONTINUE;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(NAMED_FLAGS, (byte)0);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }


    @Override
    public boolean tryAttack(Entity target) {
        if (super.tryAttack(target)) {
            if (target instanceof LivingEntity) {
                int i = 2;
                if (this.world.getDifficulty() == Difficulty.NORMAL) {
                    i = 2;
                } else if (this.world.getDifficulty() == Difficulty.HARD) {
                    i = 2;
                }
                if (i > 0) {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(ModEffects.FREEZE, i * 5, 0), this);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected float applyEnchantmentsToDamage(DamageSource source, float amount) {
        amount = super.applyEnchantmentsToDamage(source, amount);
        if (source.getAttacker() == this) {
            amount = 0.0f;
        }
        if (DamageSource.DRAGON_BREATH.bypassesArmor()) {
            amount *= 0.0f;
        }
        return amount;
    }
    static class AttackGoal
            extends MeleeAttackGoal {
        public AttackGoal(NamedEntity named) {
            super(named, 1.0, true);
        }

        @Override
        public boolean canStart() {
            return super.canStart() && !this.mob.hasPassengers();
        }

        @Override
        public boolean shouldContinue() {
            float f = this.mob.getBrightnessAtEyes();
            if (f >= 0.5f && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget(null);
                return false;
            }
            return super.shouldContinue();
        }

        @Override
        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return 4.0f + entity.getWidth();
        }
    }

    static class TargetGoal<T extends LivingEntity>
            extends ActiveTargetGoal<T> {
        public TargetGoal(NamedEntity named, Class<T> targetEntityClass) {
            super((MobEntity)named, targetEntityClass, true);
        }

        @Override
        public boolean canStart() {
            float f = this.mob.getBrightnessAtEyes();
            if (f >= 0.5f) {
                return false;
            }
            return super.canStart();
        }
    }

    //bossbar
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setInvulTimer(nbt.getInt("Invul"));
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }
    public void setInvulTimer(int ticks) {
        this.dataTracker.set(INVUL_TIMER, ticks);
    }


}