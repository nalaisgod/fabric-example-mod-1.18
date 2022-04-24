package net.nalaisgod.nalasmod.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.effect.ModEffects;

import java.util.Random;

public class DeathVine extends Block {
    private static final int SCHEDULED_TICK_DELAY = 1;

    public DeathVine(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = ((LivingEntity) entity);
            livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.FREEZE, 2));
            entity.damage(DamageSource.SWEET_BERRY_BUSH, 2.0f);
        }
        super.onSteppedOn(world, pos, state, entity);
    }


    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.createAndScheduleBlockTick(pos, this, 3);
    }
}
