/*
 * Decompiled with CFR 0.0.9 (FabricMC cc05e23f).
 */
package net.nalaisgod.nalasmod.entity.mob;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;

public class EvokerKingEntity
extends SpellcastingIllagerEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    @Nullable
    private SheepEntity wololoTarget;

    public EvokerKingEntity(EntityType<? extends EvokerKingEntity> entityType, World world) {
        super((EntityType<? extends SpellcastingIllagerEntity>)entityType, world);
        this.experiencePoints = 10;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtTargetOrWololoTarget());
        this.goalSelector.add(2, new FleeEntityGoal<PlayerEntity>(this, PlayerEntity.class, 8.0f, 0.6, 2.0));
        this.goalSelector.add(4, new SummonVexGoal());
        this.goalSelector.add(5, new ConjureFangsGoal());
        this.goalSelector.add(6, new WololoGoal());
        this.goalSelector.add(8, new WanderAroundGoal(this, 0.6));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0f, 1.0f));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0f));
        this.targetSelector.add(1, new RevengeGoal(this, RaiderEntity.class).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true).setMaxTimeWithoutVisibility(300));
        this.targetSelector.add(3, new ActiveTargetGoal<MerchantEntity>((MobEntity)this, MerchantEntity.class, false).setMaxTimeWithoutVisibility(300));
        this.targetSelector.add(3, new ActiveTargetGoal<IronGolemEntity>((MobEntity)this, IronGolemEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                0.5).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0).add(EntityAttributes.GENERIC_MAX_HEALTH, 240.0);
    }
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.evoker_king.idle", true));
            return PlayState.CONTINUE;
        }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.evoker_king.attack", false));
            return PlayState.CONTINUE;

    }


    @Override
    public AnimationFactory getFactory() {
        return factory;
    }





    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    public SoundEvent getCelebratingSound() {
        return SoundEvents.ENTITY_EVOKER_CELEBRATE;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    protected void mobTick() {
        super.mobTick();
    }

    @Override
    public boolean isTeammate(Entity other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (super.isTeammate(other)) {
            return true;
        }
        if (other instanceof VexEntity) {
            return this.isTeammate(((VexEntity)other).getOwner());
        }
        if (other instanceof LivingEntity && ((LivingEntity)other).getGroup() == EntityGroup.ILLAGER) {
            return this.getScoreboardTeam() == null && other.getScoreboardTeam() == null;
        }
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_EVOKER_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_EVOKER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_EVOKER_HURT;
    }

    void setWololoTarget(@Nullable SheepEntity wololoTarget) {
        this.wololoTarget = wololoTarget;
    }

    @Nullable
    SheepEntity getWololoTarget() {
        return this.wololoTarget;
    }

    @Override
    protected SoundEvent getCastSpellSound() {
        return SoundEvents.ENTITY_EVOKER_CAST_SPELL;
    }

    @Override
    public void addBonusForWave(int wave, boolean unused) {
    }

    class LookAtTargetOrWololoTarget
    extends LookAtTargetGoal {
        LookAtTargetOrWololoTarget() {
        }

        @Override
        public void tick() {
            if (EvokerKingEntity.this.getTarget() != null) {
                EvokerKingEntity.this.getLookControl().lookAt(EvokerKingEntity.this.getTarget(), EvokerKingEntity.this.getMaxHeadRotation(), EvokerKingEntity.this.getMaxLookPitchChange());
            } else if (EvokerKingEntity.this.getWololoTarget() != null) {
                EvokerKingEntity.this.getLookControl().lookAt(EvokerKingEntity.this.getWololoTarget(), EvokerKingEntity.this.getMaxHeadRotation(), EvokerKingEntity.this.getMaxLookPitchChange());
            }
        }
    }

    class SummonVexGoal
    extends CastSpellGoal {
        private final TargetPredicate closeVexPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(16.0).ignoreVisibility().ignoreDistanceScalingFactor();

        SummonVexGoal() {
        }

        @Override
        public boolean canStart() {
            if (!super.canStart()) {
                return false;
            }
            int i = EvokerKingEntity.this.world.getTargets(VexEntity.class, this.closeVexPredicate, EvokerKingEntity.this, EvokerKingEntity.this.getBoundingBox().expand(16.0)).size();
            return EvokerKingEntity.this.random.nextInt(8) + 1 > i;
        }

        @Override
        protected int getSpellTicks() {
            return 100;
        }

        @Override
        protected int startTimeDelay() {
            return 340;
        }

        @Override
        protected void castSpell() {
            ServerWorld serverWorld = (ServerWorld) EvokerKingEntity.this.world;
            for (int i = 0; i < 3; ++i) {
                BlockPos blockPos = EvokerKingEntity.this.getBlockPos().add(-1 + EvokerKingEntity.this.random.nextInt(10), 1, -1 + EvokerKingEntity.this.random.nextInt(10));
                VexEntity vexEntity = EntityType.VEX.create(EvokerKingEntity.this.world);
                vexEntity.refreshPositionAndAngles(blockPos, 0.0f, 0.0f);
                vexEntity.initialize(serverWorld, EvokerKingEntity.this.world.getLocalDifficulty(blockPos), SpawnReason.MOB_SUMMONED, null, null);
                vexEntity.setOwner(EvokerKingEntity.this);
                vexEntity.setBounds(blockPos);
                vexEntity.setLifeTicks(20 * (30 + EvokerKingEntity.this.random.nextInt(90)));
                serverWorld.spawnEntityAndPassengers(vexEntity);
            }
        }

        @Override
        protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON;
        }

        @Override
        protected Spell getSpell() {
            return Spell.SUMMON_VEX;
        }
    }

    class ConjureFangsGoal
    extends CastSpellGoal {
        ConjureFangsGoal() {
        }

        @Override
        protected int getSpellTicks() {
            return 40;
        }

        @Override
        protected int startTimeDelay() {
            return 10;
        }

        @Override
        protected void castSpell() {
            LivingEntity livingEntity = EvokerKingEntity.this.getTarget();
            double d = Math.min(livingEntity.getY(), EvokerKingEntity.this.getY());
            double e = Math.max(livingEntity.getY(), EvokerKingEntity.this.getY()) + 1.0;
            float f = (float)MathHelper.atan2(livingEntity.getZ() - EvokerKingEntity.this.getZ(), livingEntity.getX() - EvokerKingEntity.this.getX());
            if (EvokerKingEntity.this.squaredDistanceTo(livingEntity) < 9.0) {
                float g;
                int i;
                for (i = 0; i < 5; ++i) {
                    g = f + (float)i * (float)Math.PI * 0.6f;
                    this.conjureFangs(EvokerKingEntity.this.getX() + (double)MathHelper.cos(g) * 0.5, EvokerKingEntity.this.getZ() + (double)MathHelper.sin(g) * 1.5, d, e, g, 0);
                }
                for (i = 0; i < 8; ++i) {
                    g = f + (float)i * (float)Math.PI * 4.0f / 6.0f + 1.2566371f;
                    this.conjureFangs(EvokerKingEntity.this.getX() + (double)MathHelper.cos(g) * 1.5, EvokerKingEntity.this.getZ() + (double)MathHelper.sin(g) * 2.5, d, e, g, 3);
                }
            } else {
                for (int i = 0; i < 16; ++i) {
                    double h = 1.25 * (double)(i + 1);
                    int j = 1 * i;
                    this.conjureFangs(EvokerKingEntity.this.getX() + (double)MathHelper.cos(f) * h, EvokerKingEntity.this.getZ() + (double)MathHelper.sin(f) * h, d, e, f, j);
                }
            }
        }

        private void conjureFangs(double x, double z, double maxY, double y, float yaw, int warmup) {
            BlockPos blockPos = new BlockPos(x, y, z);
            boolean bl = false;
            double d = 0.0;
            do {
                BlockState blockState2;
                VoxelShape voxelShape;
                BlockPos blockPos2;
                BlockState blockState;
                if (!(blockState = EvokerKingEntity.this.world.getBlockState(blockPos2 = blockPos.down())).isSideSolidFullSquare(EvokerKingEntity.this.world, blockPos2, Direction.UP)) continue;
                if (!EvokerKingEntity.this.world.isAir(blockPos) && !(voxelShape = (blockState2 = EvokerKingEntity.this.world.getBlockState(blockPos)).getCollisionShape(EvokerKingEntity.this.world, blockPos)).isEmpty()) {
                    d = voxelShape.getMax(Direction.Axis.Y);
                }
                bl = true;
                break;
            } while ((blockPos = blockPos.down()).getY() >= MathHelper.floor(maxY) - 1);
            if (bl) {
                EvokerKingEntity.this.world.spawnEntity(new EvokerFangsEntity(EvokerKingEntity.this.world, x, (double)blockPos.getY() + d, z, yaw, warmup, EvokerKingEntity.this));
            }
        }

        @Override
        protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_ATTACK;
        }

        @Override
        protected Spell getSpell() {
            return Spell.FANGS;
        }
    }

    public class WololoGoal
    extends CastSpellGoal {
        private final TargetPredicate convertibleSheepPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(16.0).setPredicate(livingEntity -> ((SheepEntity)livingEntity).getColor() == DyeColor.BLUE);

        @Override
        public boolean canStart() {
            if (EvokerKingEntity.this.getTarget() != null) {
                return false;
            }
            if (EvokerKingEntity.this.isSpellcasting()) {
                return false;
            }
            if (EvokerKingEntity.this.age < this.startTime) {
                return false;
            }
            if (!EvokerKingEntity.this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                return false;
            }
            List<SheepEntity> list = EvokerKingEntity.this.world.getTargets(SheepEntity.class, this.convertibleSheepPredicate, EvokerKingEntity.this, EvokerKingEntity.this.getBoundingBox().expand(16.0, 4.0, 16.0));
            if (list.isEmpty()) {
                return false;
            }
            EvokerKingEntity.this.setWololoTarget(list.get(EvokerKingEntity.this.random.nextInt(list.size())));
            return true;
        }

        @Override
        public boolean shouldContinue() {
            return EvokerKingEntity.this.getWololoTarget() != null && this.spellCooldown > 0;
        }

        @Override
        public void stop() {
            super.stop();
            EvokerKingEntity.this.setWololoTarget(null);
        }

        @Override
        protected void castSpell() {
            SheepEntity sheepEntity = EvokerKingEntity.this.getWololoTarget();
            if (sheepEntity != null && sheepEntity.isAlive()) {
                sheepEntity.setColor(DyeColor.RED);
            }
        }

        @Override
        protected int getInitialCooldown() {
            return 40;
        }

        @Override
        protected int getSpellTicks() {
            return 60;
        }

        @Override
        protected int startTimeDelay() {
            return 140;
        }

        @Override
        protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_WOLOLO;
        }

        @Override
        protected Spell getSpell() {
            return Spell.WOLOLO;
        }
    }
}

