package net.nalaisgod.nalasmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.custom.RaccoonEntity;
import net.nalaisgod.nalasmod.entity.custom.TigerEntity;
import net.nalaisgod.nalasmod.entity.mob.DaveEntity;
import net.nalaisgod.nalasmod.entity.mob.EvokerKingEntity;
import net.nalaisgod.nalasmod.entity.mob.ExiterEntity;
import net.nalaisgod.nalasmod.entity.mob.NamedEntity;
import net.nalaisgod.nalasmod.entity.projectile.DeathBomb;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.example.registry.EntityRegistryBuilder;
import software.bernie.geckolib3.GeckoLib;

public class ModEntities {


    public static final EntityType<RaccoonEntity> RACCOON = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(NalasMod.MOD_ID, "raccoon"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RaccoonEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.3f)).build());
    public static final EntityType<TigerEntity> TIGER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(NalasMod.MOD_ID, "tiger"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TigerEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.75f)).build());
    public static final EntityType<ExiterEntity> EXITER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(NalasMod.MOD_ID, "exiter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ExiterEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.75f)).build());
    public static final EntityType<NamedEntity> NAMED = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(NalasMod.MOD_ID, "named"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NamedEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.75f)).build());
    public static final EntityType<DaveEntity> DAVE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(NalasMod.MOD_ID, "dave"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, DaveEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.75f)).build());
    public static final EntityType<EvokerKingEntity> EVOKER_KING = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(NalasMod.MOD_ID, "evoker_king"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EvokerKingEntity::new)
                    .dimensions(EntityDimensions.fixed(3f, 5f)).build());



}