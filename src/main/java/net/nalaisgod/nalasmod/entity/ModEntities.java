package net.nalaisgod.nalasmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.custom.RaccoonEntity;
import net.nalaisgod.nalasmod.entity.custom.TigerEntity;
import net.nalaisgod.nalasmod.entity.mob.ExiterEntity;
import net.nalaisgod.nalasmod.entity.mob.NamedEntity;
import net.nalaisgod.nalasmod.entity.projectile.FunnyShot;
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

    public static EntityType<FunnyShot> FUNNY_SHOT = buildEntity(FunnyShot::new, FunnyShot.class, 0.5F,
            0.5F, SpawnGroup.MISC);

    public static <T extends Entity> EntityType<T> buildEntity(EntityType.EntityFactory<T> entity, Class<T> entityClass,
                                                               float width, float height, SpawnGroup group) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            String name = entityClass.getSimpleName().toLowerCase();
            return EntityRegistryBuilder.<T>createBuilder(new Identifier(GeckoLib.ModID, name)).entity(entity)
                    .category(group).dimensions(EntityDimensions.changing(width, height)).build();
        }
        return null;
    }
}