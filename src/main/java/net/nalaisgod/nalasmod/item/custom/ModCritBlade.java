package net.nalaisgod.nalasmod.item.custom;


import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.effect.ModEffects;
import net.nalaisgod.nalasmod.entity.custom.RaccoonEntity;
import net.nalaisgod.nalasmod.particle.ModParticles;
import software.bernie.geckolib3.network.GeckoLibNetwork;

import java.util.Random;
import java.util.logging.Level;

public class ModCritBlade extends SwordItem {



    public ModCritBlade(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        var chance = Math.random();
        if (chance < 0.1) {
            target.setFrozenTicks(139);
                target.addStatusEffect(new StatusEffectInstance(ModEffects.FREEZE, 70, 3), attacker);
            spawnFoundParticles(target);

        }


        return super.postHit(stack, target, attacker);
    }
    private void spawnFoundParticles(LivingEntity target) {
                target.getWorld().addParticle(ModParticles.CITRINE_PARTICLE,
                        target.getX() + 0.5d, target.getY() + 1, target.getZ() + 0.5d,
                         1d, 1d, 1d);

    }



}


