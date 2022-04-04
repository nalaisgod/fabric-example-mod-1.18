package net.nalaisgod.nalasmod.entity.mob;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.DragonFireballEntityRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.nalaisgod.nalasmod.item.ModItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.function.Predicate;

public class ExiterEntity extends HostileEntity implements Mount, IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(Items.CHORUS_FRUIT);
    private static final TrackedData<Boolean> SHOOTING = DataTracker.registerData(GhastEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int fireballStrength = 1;





    public ExiterEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return TameableEntity.createMobAttributes()

                .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 18.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.exiter.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.exiter.idle", true));
        return PlayState.CONTINUE;
    }


    protected void initGoals() {
        this.goalSelector.add(7, new ExiterEntity.ShootFireballGoal(this));
        this.goalSelector.add(5, new GoToWalkTargetGoal(this, 1.0));
        this.goalSelector.add(7, new WanderAroundFarGoal((PathAwareEntity)this, 1.0, 0.0f));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true));
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }


    public boolean isShooting() {
        return this.dataTracker.get(SHOOTING);
    }

    public void setShooting(boolean shooting) {
        this.dataTracker.set(SHOOTING, shooting);
    }

    public int getFireballStrength() {
        return this.fireballStrength;
    }


    @Override
    protected float applyEnchantmentsToDamage(DamageSource source, float amount) {
        amount = super.applyEnchantmentsToDamage(source, amount);
        if (DamageSource.DRAGON_BREATH.bypassesArmor()) {
            amount *= 0.15f;
        }
        return amount;
    }

    static class ShootFireballGoal
            extends Goal {
        private final ExiterEntity exiter;
        public int cooldown;

        public ShootFireballGoal(ExiterEntity exiter) {
            this.exiter = exiter;
        }

        @Override
        public boolean canStart() {
            return this.exiter.getTarget() != null;
        }

        @Override
        public void start() {
            this.cooldown = 0;
        }

        @Override
        public void stop() {
            this.exiter.setShooting(false);
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.exiter.getTarget();
            if (livingEntity == null) {
                return;
            }
            double d = 64.0;
            if (livingEntity.squaredDistanceTo(this.exiter) < 4096.0 && this.exiter.canSee(livingEntity)) {
                World world = this.exiter.world;
                ++this.cooldown;
                if (this.cooldown == 10 && !this.exiter.isSilent()) {
                    world.syncWorldEvent(null, WorldEvents.GHAST_WARNS, this.exiter.getBlockPos(), 0);
                }
                if (this.cooldown == 20) {
                    double e = 4.0;
                    Vec3d vec3d = this.exiter.getRotationVec(1.0f);
                    double f = livingEntity.getX() - (this.exiter.getX() + vec3d.x * 4.0);
                    double g = livingEntity.getBodyY(0.5) - (0.5 + this.exiter.getBodyY(0.5));
                    double h = livingEntity.getZ() - (this.exiter.getZ() + vec3d.z * 4.0);
                    if (!this.exiter.isSilent()) {
                        world.syncWorldEvent(null, WorldEvents.GHAST_SHOOTS, this.exiter.getBlockPos(), 0);
                    }
                    DragonFireballEntity dragonFireballEntity = new DragonFireballEntity(world, (LivingEntity)this.exiter, f, g, h);
                    dragonFireballEntity.setPosition(this.exiter.getX() + vec3d.x * 4.0, this.exiter.getBodyY(0.5) + 0.5, dragonFireballEntity.getZ() + vec3d.z * 4.0);
                    world.spawnEntity(dragonFireballEntity);
                    this.cooldown = -20;
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }
            this.exiter.setShooting(this.cooldown > 10);
        }
    }


    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<ExiterEntity>(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    /* TAMEABLE */
    private static final TrackedData<Boolean> SITTING =
            DataTracker.registerData(ExiterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);



    @Override
    public AbstractTeam getScoreboardTeam() {
        return super.getScoreboardTeam();
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHOOTING, false);
        this.dataTracker.startTracking(SITTING, false);
    }

    /* RIDEABLE */
    @Override
    public boolean canBeControlledByRider() {
        return this.getPrimaryPassenger() instanceof LivingEntity;
    }

    @Override
    @Nullable
    public Entity getPrimaryPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (!this.isAlive()) {
            return;
        }

        if (!(this.hasPassengers() && this.canBeControlledByRider())) {
            this.airStrafingSpeed = 0.02f;
            super.travel(movementInput);
            return;
        }

        LivingEntity livingEntity = (LivingEntity) this.getPrimaryPassenger();
        this.setYaw(livingEntity.getYaw());
        this.prevYaw = this.getYaw();
        this.setPitch(livingEntity.getPitch() * 0.5f);
        this.setRotation(this.getYaw(), this.getPitch());
        this.headYaw = this.bodyYaw = this.getYaw();
        float f = livingEntity.sidewaysSpeed * 0.5f;
        float g = livingEntity.forwardSpeed;
        if (g <= 0.0f) {
            g *= 0.25f;
        }

        if (this.isLogicalSideForUpdatingMovement()) {
            this.setMovementSpeed((float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
            super.travel(new Vec3d(f, movementInput.y, g));
        } else if (livingEntity instanceof PlayerEntity) {
            this.setVelocity(Vec3d.ZERO);
        }

        this.updateLimbs(this, false);
        this.tryCheckBlockCollision();
    }


    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Vec3d vec3d = getPassengerDismountOffset(this.getWidth(), passenger.getWidth(),
                this.getYaw() + (passenger.getMainArm() == Arm.RIGHT ? 90.0f : -90.0f));
        Vec3d vec3d2 = this.locateSafeDismountingPos(vec3d, passenger);
        if (vec3d2 != null) {
            return vec3d2;
        }
        Vec3d vec3d3 = getPassengerDismountOffset(this.getWidth(), passenger.getWidth(),
                this.getYaw() + (passenger.getMainArm() == Arm.LEFT ? 90.0f : -90.0f));
        Vec3d vec3d4 = this.locateSafeDismountingPos(vec3d3, passenger);
        if (vec3d4 != null) {
            return vec3d4;
        }
        return this.getPos();
    }

    @Nullable
    private Vec3d locateSafeDismountingPos(Vec3d offset, LivingEntity passenger) {
        double d = this.getX() + offset.x;
        double e = this.getBoundingBox().minY;
        double f = this.getZ() + offset.z;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        block0:
        for (EntityPose entityPose : passenger.getPoses()) {
            mutable.set(d, e, f);
            double g = this.getBoundingBox().maxY + 0.75;
            do {
                Vec3d vec3d;
                Box box;
                double h = this.world.getDismountHeight(mutable);
                if ((double) mutable.getY() + h > g) continue block0;
                if (Dismounting.canDismountInBlock(h) && Dismounting.canPlaceEntityAt(this.world, passenger, (box = passenger.getBoundingBox(entityPose)).offset(vec3d = new Vec3d(d, (double) mutable.getY() + h, f)))) {
                    passenger.setPose(entityPose);
                    return vec3d;
                }
                mutable.move(Direction.UP);
            } while ((double) mutable.getY() < g);
        }
        return null;
    }
}