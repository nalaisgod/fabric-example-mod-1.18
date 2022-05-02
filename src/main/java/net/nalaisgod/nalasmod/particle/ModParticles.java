package net.nalaisgod.nalasmod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;

public class ModParticles {
    public static final DefaultParticleType CITRINE_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(NalasMod.MOD_ID, "citrine_particle"),
                CITRINE_PARTICLE);
    }
}