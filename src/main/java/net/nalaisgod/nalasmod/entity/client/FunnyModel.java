package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.ExiterEntity;
import net.nalaisgod.nalasmod.entity.projectile.FunnyShot;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class FunnyModel extends AnimatedGeoModel<FunnyShot> {
    @Override
    public Identifier getModelLocation(FunnyShot object) {
        return new Identifier(NalasMod.MOD_ID, "geo/funny.geo.json");
    }

    @Override
    public Identifier getTextureLocation(FunnyShot object) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/projectile/funny.png");
    }

    @Override
    public Identifier getAnimationFileLocation(FunnyShot animatable) {
        return new Identifier(NalasMod.MOD_ID, "animations/funny.animation.json");
    }

}