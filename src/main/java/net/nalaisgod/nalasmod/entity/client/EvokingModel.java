package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.DaveEntity;
import net.nalaisgod.nalasmod.entity.mob.EvokerKingEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EvokingModel extends AnimatedGeoModel<EvokerKingEntity> {
    @Override
    public Identifier getModelLocation(EvokerKingEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "geo/evoker_king.geo.json");
    }

    @Override
    public Identifier getTextureLocation(EvokerKingEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/evoker_king/evoker_king.png");
    }

    @Override
    public Identifier getAnimationFileLocation(EvokerKingEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "animations/evoker_king.animation.json");
    }
}